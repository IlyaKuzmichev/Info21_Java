document.addEventListener("DOMContentLoaded", function () {
    let tabs = document.querySelectorAll(".tab-link");
    let contents = document.querySelectorAll(".tab-content");

    // Устанавливаем активную вкладку и контент при загрузке страницы
    if (tabs.length > 0) {
        tabs[0].classList.add("active"); // Активируем первую вкладку
        if (contents.length > 0) {
            contents[0].classList.add("active"); // Активируем первый контент
        }
    }

    tabs.forEach(tab => {
        tab.addEventListener("click", function () {
            tabs.forEach(t => t.classList.remove("active"));
            contents.forEach(c => c.classList.remove("active"));

            tab.classList.add("active");
            document.getElementById(tab.getAttribute("data-tab")).classList.add("active");
        });
    });
});

function insertQuery(query, description) {
    document.getElementById("sql-query").value = query;
    document.getElementById("query-description").textContent = description || "Тут будет описание операции.";
}

function clearQuery() {
    document.getElementById("sql-query").value = "";
    document.getElementById("query-description").textContent = "Выберите операцию, чтобы увидеть описание.";
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

        if (result.affectedRows !== undefined && result.message) {
                    resultDiv.innerHTML = `
                        <div style="color: green;">
                            ${result.message}<br>
                            Затронуто строк: ${result.affectedRows}
                        </div>`;
        } else if (Array.isArray(result)) {
            if (result.length > 0) {
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
                resultDiv.innerHTML = `<div style="color: gray;">Запрос не вернул данных.</div>`;
            }
        } else {
            resultDiv.innerHTML = `<div>${JSON.stringify(result, null, 2)}</div>`;
        }
    } catch (error) {
        resultDiv.innerHTML = `<div style="color: red;">Ошибка: ${error.message}</div>`;
    }
});