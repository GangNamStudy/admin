document.getElementById('sendBtn').addEventListener('click', () => {
    // 1. Input에서 값을 가져온다
    const baseFee = document.getElementById('updateBaseFee').value;
    const freeTime = document.getElementById('updateBaseTime').value;
    const additionalFee = document.getElementById('updateAdditionalFee').value;
    const additionalTime = document.getElementById('updateAdditionalTime').value;

    // 2. API에 보낼 데이터 준비
    const payload = { freeTime, baseFee, additionalTime, additionalFee };

    // 3. fetch API로 POST 요청
    fetch('/api/admin/policy', { // <- 이 URL은 변경해야 함
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(payload),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('API 오류');
            }
            return response.json();
        })
        .then(data => {
            console.log('전송 성공!', data);

            document.querySelector("#baseTime").innerText = `최초 ${data.freeTime}분`;
            document.querySelector("#baseFee").innerText = `${data.baseFee}원`;
            document.querySelector("#additionalTime").innerText = `추가 ${data.additionalTime}분 당`;
            document.querySelector("#additionalFee").innerText = `${data.additionalFee}원`;
        })
        .catch(error => {
            console.error('전송 실패!', error);
        });

});