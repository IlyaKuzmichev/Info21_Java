document.addEventListener("DOMContentLoaded", function () {
    const forms = document.querySelectorAll("form[data-confirm]");

    forms.forEach(form => {
        form.addEventListener("submit", function (event) {
            const confirmMessage = form.getAttribute("data-confirm") || "Are you sure you want to proceed?";
            if (!confirm(confirmMessage)) {
                event.preventDefault();
            }
        });
    });
});
