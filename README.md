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
    "id": 123,
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
  
---

## **2. 차량 입출차 관리**

### **2.1 입,출차 정보 조회** (GET)

- **URL**: `/api/admin/management/history`
- **Query Parameters (optional)**:
  - `plate` (string, optional): 특정 차량 번호로 검색
  - `startDate` (string, ISO 8601 format)
  - `endDate` (string, ISO 8601 format)
- **설명**: 차량 입출차 정보와 결제 데이터를 통합 조회합니다.
- **예제 cURL 요청**:
  ```bash
  curl -X GET "http://localhost:8080/api/admin/management?car-id=89거 9821&startDate=2025-11-08&endDate=2025-11-08" \
  -H "Content-Type: application/json"
  ```
- **예제 응답**:
  ```json
  {
    "result": [
      {
        "plate": "89거 9821",
        "entry_time": "2025-11-08T11:44:30",
        "exit_time": "2025-11-08T14:44:30"
      }
    ]
  }
  ```

### **2.2 차량 입차** (POST)

- **URL**: `/api/admin/management/entrance`
- **Body Parameters**:
  - `plate` (string): 차량 번호
- **설명**: 차량을 입차시키고 기록을 생성합니다.
- **예제 cURL 요청**:
  ```bash
  curl -X POST "http://localhost:8080/api/admin/management/entrance" \
  -H "Content-Type: application/json" \
  -d '{"car_id": "89거 9821", "entry_time": "2025-11-08T11:44:30"}'
  ```
- **예제 응답**:
  ```json
  {
    "plate": "89거 9821",
    "entry_time": "2025-11-08T11:44:30",
    "car_status": "entry"
  }
  ```

### **2.3 차량 출차** (PATCH)

- **URL**: `/api/admin/management/{plate}/departure`
- **Path Parameters**:
  - `plate` (string): 차량 번호
- **설명**: 차량을 출차시키고 해당 차량의 기록을 수정합니다.
- **예제 cURL 요청**:
  ```bash
  curl -X PATCH "http://localhost:8080/api/admin/management/123/departure" \
  -H "Content-Type: application/json" \
  -d '{"car_id": "89거 9821", "exit_time": "2025-11-08T14:44:30"}'
  ```
- **예제 응답**:
  ```json
  {
    "plate": "89거 9821",
    "entry_time": "2025-11-08T14:44:30",
    "exit_time": "2025-11-08T14:44:31",
    "car_status": "exit"
  }
  ```

---

## **3. 매출 관리 **

### **3.1 기간 별 매출 조회** (GET)

- **URL**: `/api/admin/payment`
- **Query Parameters (optional)**:
  - `startTime` (string, ISO 8601 format)
  - `endTime` (string, ISO 8601 format)
- **설명**: 일정 기간동안 벌어들인 매출의 총 합을 조회합니다.
- **예제 cURL 요청**:
  ```bash
  curl -X GET "http://localhost:8080/api/admin/management?startTime=2025-11-08T11:44&endTime=2025-11-08T13:44" \
  -H "Content-Type: application/json"
  ```
- **예제 응답**:
  ```json
  {
    "totalAmount": 1200000,
  
    "days" : [
      {
        "date" : 12,
        "dayFee" : 21000
      }
    ]
  }
  ```

