function clearQuery() {
    const textarea = document.getElementById('sql-query');
    textarea.value = ''; // Очищает поле
}

function insertQuery(query) {
    const textarea = document.getElementById('sql-query');
    textarea.value = query;
}


document.getElementById('sql-form').addEventListener('submit', async (e) => {
    e.preventDefault(); // Предотвращаем отправку формы

    const query = document.getElementById('sql-query').value;
    const resultDiv = document.getElementById('result');
    resultDiv.innerHTML = "Выполняется запрос...";

    try {
        const response = await fetch('/execute-sql', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ query })
        });

        if (!response.ok) {
            const error = await response.text();
            resultDiv.innerHTML = `<pre style="color: red;">Ошибка: ${error}</pre>`;
            return;
        }

        const result = await response.json();
        if (Array.isArray(result)) {
            let table = '<table border="1" cellspacing="0" cellpadding="5"><thead><tr>';
            Object.keys(result[0]).forEach(col => {
                table += `<th>${col}</th>`;
            });
            table += '</tr></thead><tbody>';
            result.forEach(row => {
                table += '<tr>';
                Object.values(row).forEach(val => {
                    table += `<td>${val !== null ? val : ''}</td>`;
                });
                table += '</tr>';
            });
            table += '</tbody></table>';
            resultDiv.innerHTML = table;
        } else {
            resultDiv.innerHTML = `<pre>${JSON.stringify(result, null, 2)}</pre>`;
        }
    } catch (error) {
        resultDiv.innerHTML = `<pre style="color: red;">Ошибка: ${error.message}</pre>`;
    }
});