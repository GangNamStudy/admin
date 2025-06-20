function openEntrySuccessModal() {
    const modal = document.getElementById("entrySuccessModal");
    modal.classList.remove("hidden");
}

function closeEntrySuccessModal() {
    const modal = document.getElementById("entrySuccessModal");
    modal.classList.add("hidden");
}

document.getElementById('exitBtn').addEventListener('click', () => {

    const plate = document.getElementById('car-number').value.replaceAll(/\s+/g, '');
    console.log(plate);

    fetch(`/api/admin/parking/${plate}/departure`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('API 오류');
            }
            return response.json();
        })
        .then(data => {
            console.log('전송 성공!', data);
            openEntrySuccessModal();
        })
        .catch(error => {
            console.error('전송 실패!', error);
        });
});