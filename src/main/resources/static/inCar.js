function openEntrySuccessModal() {
    const modal = document.getElementById("entrySuccessModal");
    modal.classList.remove("hidden");
}

function closeEntrySuccessModal() {
    const modal = document.getElementById("entrySuccessModal");
    modal.classList.add("hidden");
}

document.getElementById('entryBtn').addEventListener('click', () => {
    const plate = document.getElementById('car-number').value;

    const payload = { plate };

    fetch('/api/admin/parking/entrance', {
        method: 'POST',
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
            openEntrySuccessModal();  // ✅ 입차 성공 시 모달 열기
        })
        .catch(error => {
            console.error('전송 실패!', error);
            alert("입차에 실패했습니다.");
        });
});