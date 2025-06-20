document.addEventListener('DOMContentLoaded', () => {
    fetch('/api/admin/policy')
        .then(response => response.json()) //baseFee, freeTime, additionalFee, additionalTime
        .then(data => {
            document.querySelector("#baseTime").innerText = `최초 ${data.freeTime}분`;
            document.querySelector("#baseFee").innerText = `${data.baseFee}원`;
            document.querySelector("#additionalTime").innerText = `추가 ${data.additionalTime}분 당`;
            document.querySelector("#additionalFee").innerText = `${data.additionalFee}원`;
        })
        .catch(err => console.error(err));
});