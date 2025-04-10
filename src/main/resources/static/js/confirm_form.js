document.addEventListener("DOMContentLoaded", function () {
    // Обрабатываем формы с подтверждением
    const forms = document.querySelectorAll("form[data-confirm]");
    forms.forEach(form => {
        form.addEventListener("submit", function (event) {
            const confirmMessage = form.getAttribute("data-confirm") || "Are you sure you want to proceed?";
            if (!confirm(confirmMessage)) {
                event.preventDefault();
            }
        });
    });

    // Обрабатываем ссылки с подтверждением
    const links = document.querySelectorAll("a[data-confirm]");
    links.forEach(link => {
        link.addEventListener("click", function (event) {
            const confirmMessage = link.getAttribute("data-confirm") || "Are you sure you want to proceed?";
            if (!confirm(confirmMessage)) {
                event.preventDefault();
            }
        });
    });
});
