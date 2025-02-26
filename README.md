# Admin

주차장 관리 프로그램의 주요 기능 중 **관리자 API**

- **기반**: Spring Boot
- **기능**:
  - 주차 요금 정책 관리 (생성, 조회, 활성화, 비활성화)
  - 차량 입출차 관리
  - 입출차 및 결제 데이터 통합 조회

---

## **1. 주차 요금 정책 관리**

### **1.1 주차 요금 정책 생성** (POST)

- **URL**: `/api/admin/policy`
- **Body Parameters**:
  - `policy_name` (string): 정책 이름
  - `base_fee` (int): 기본 요금
  - `fee_time` (int): 기준 시간 (분 단위)
  - `additional_fee` (int): 추가 요금
  - `additional_time` (int): 추가 요금 적용 시간 (분 단위)
- **설명**: 새로운 주차 요금 정책을 생성합니다.
- **예제 cURL 요청**:
  ```bash
  curl -X POST "http://localhost:8080/api/admin/policy" \
  -H "Content-Type: application/json" \
  -d '{"policy_name": "기본 요금 정책", "base_fee": 1000, "fee_time": 30, "additional_fee": 500, "additional_time": 10}'
  ```
- **예제 응답**:
  ```json
  {
    "policy_name": "기본 요금 정책",
    "base_fee": 1000,
    "fee_time": 30,
    "additional_fee": 500,
    "additional_time": 10,
    "policy_status": "activate"
  }
  ```

### **1.2 주차 요금 정책 조회** (GET)

- **URL**: `/api/admin/policy/check-all`
- **설명**: 등록된 모든 주차 요금 정책을 조회합니다.
- **예제 cURL 요청**:
  ```bash
  curl -X GET "http://localhost:8080/api/admin/policy/check-all" \
  -H "Content-Type: application/json"
  ```
- **예제 응답**:
  ```json
  {
    "result": [
      {
        "id": 123,
        "policy_name": "기본 요금 정책",
        "base_fee": 1000,
        "fee_time": 30,
        "additional_fee": 500,
        "additional_time": 10,
        "policy_status": "activate"
      }
    ]
  }
  ```

### **1.3 주차 요금 정책 활성화** (PATCH)

- **URL**: `/api/admin/policy/{policy-id}/activate`
- **Body Parameters**:
  - `id` (string): 정책 ID
  - `policy_status` (enum: `activate`)
- **설명**: 특정 주차 요금 정책을 활성화합니다.
- **예제 cURL 요청**:
  ```bash
  curl -X PATCH "http://localhost:8080/api/admin/policy/123/activate" \
  -H "Content-Type: application/json" \
  -d '{"id": "123", "policy_status": "activate"}'
  ```
- **예제 응답**:
  ```json
  {
    "id": "123",
    "policy_status": "activate"
  }
  ```

### **1.4 주차 요금 정책 비활성화** (PATCH)

- **URL**: `/api/admin/policy/{policy-id}/deactivate`
- **Body Parameters**:
  - `id` (string): 정책 ID
  - `policy_status` (enum: `deactivate`)
- **설명**: 특정 주차 요금 정책을 비활성화합니다.
- **예제 cURL 요청**:
  ```bash
  curl -X PATCH "http://localhost:8080/api/admin/policy/124/deactivate" \
  -H "Content-Type: application/json" \
  -d '{"id": "124", "policy_status": "deactivate"}'
  ```
- **예제 응답**:
  ```json
  {
    "id": "124",
    "policy_status": "deactivate"
  }
  ```

---

## **2. 차량 입출차 관리**

### **2.1 입출차 및 결제 정보 조회** (GET)

- **URL**: `/api/admin/management`
- **Query Parameters (optional)**:
  - `car-id` (string, optional): 특정 차량 번호로 검색
  - `period` (object, optional): 특정 기간 검색
    - `start_time` (string, ISO 8601 format)
    - `end_time` (string, ISO 8601 format)
- **설명**: 차량 입출차 정보와 결제 데이터를 통합 조회합니다.
- **예제 cURL 요청**:
  ```bash
  curl -X GET "http://localhost:8080/api/admin/management?car-id=89거 9821&period[start_time]=2025-11-08T11:44&period[end_time]=2025-11-08T13:44" \
  -H "Content-Type: application/json"
  ```
- **예제 응답**:
  ```json
  {
    "result": [
      {
        "car_id": "89거 9821",
        "fee": 2000,
        "entry_time": "2025-11-08T11:44:30",
        "exit_time": "2025-11-08T14:44:30",
        "payment_method": "credit_card"
      }
    ]
  }
  ```

### **2.2 차량 입차** (POST)

- **URL**: `/api/admin/entrance`
- **Body Parameters**:
  - `car_id` (string): 차량 번호
  - `entry_time` (optional, string): 입차 시간 (ISO 8601 형식, 기본값: 현재시간)
- **설명**: 차량을 입차시키고 기록을 생성합니다.
- **예제 cURL 요청**:
  ```bash
  curl -X POST "http://localhost:8080/api/admin/entrance" \
  -H "Content-Type: application/json" \
  -d '{"car_id": "89거 9821", "entry_time": "2025-11-08T11:44:30"}'
  ```
- **예제 응답**:
  ```json
  {
    "car_id": "89거 9821",
    "entry_time": "2025-11-08T11:44:30",
    "car_status": "entry"
  }
  ```

### **2.3 차량 출차** (PATCH)

- **URL**: `/api/admin/{parking-id}/departure`
- **Body Parameters**:
  - `car_id` (string): 차량 번호
  - `exit_time` (optional, string): 출차 시간 (ISO 8601 형식, 기본값: 현재시간)
- **설명**: 차량을 출차시키고 해당 차량의 기록을 수정합니다.
- **예제 cURL 요청**:
  ```bash
  curl -X PATCH "http://localhost:8080/api/admin/123/departure" \
  -H "Content-Type: application/json" \
  -d '{"car_id": "89거 9821", "exit_time": "2025-11-08T14:44:30"}'
  ```
- **예제 응답**:
  ```json
  {
    "car_id": "89거 9821",
    "exit_time": "2025-11-08T14:44:30",
    "car_status": "exit"
  }
  ```

