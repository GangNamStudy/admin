document.addEventListener('DOMContentLoaded', () => {
    fetch('/api/admin/parking/history')
        .then(response => {
            if (!response.ok) {
                throw new Error('API 오류');
            }
            return response.json();
        })
        .then(data => {
            const tbody = document.getElementById('parkingBody');
            tbody.textContent = '';
            data.forEach(car => {
                const row = document.createElement('tr');
                row.classList.add('border-t', 'border-t-[#cedce8]');

                const plate = document.createElement('td');
                plate.textContent = car.plate;
                plate.className = 'table-16318142-bfb7-424a-86a9-23013da155f9-column-120 h-[72px] px-4 py-2 w-[400px] text-[#49749c] text-sm font-normal leading-normal';

                const entry = document.createElement('td');
                entry.textContent = car.entryTime;
                entry.className = 'table-16318142-bfb7-424a-86a9-23013da155f9-column-240 h-[72px] px-4 py-2 w-[400px] text-[#49749c] text-sm font-normal leading-normal';

                const departure = document.createElement('td');
                departure.textContent = car.exitTime ? car.exitTime : '-';
                departure.className = 'table-16318142-bfb7-424a-86a9-23013da155f9-column-360 h-[72px] px-4 py-2 w-[400px] text-[#49749c] text-sm font-normal leading-normal';

                row.appendChild(plate);
                row.appendChild(entry);
                row.appendChild(departure);

                tbody.appendChild(row);
            });
        })
        .catch(error => console.error('API 오류!', error));
});