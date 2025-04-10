document.addEventListener("DOMContentLoaded", function () {
    const fileInput = document.getElementById("fileInput");
    const uploadButton = document.getElementById("uploadButton");
    const uploadForm = document.getElementById("uploadForm");

    // Открываем окно выбора файла при клике на кнопку
    uploadButton.addEventListener("click", function () {
        fileInput.click();
    });

    // Отправляем форму автоматически после выбора файла
    fileInput.addEventListener("change", function () {
        if (fileInput.files.length > 0) {
            uploadForm.submit();
        }
    });
});