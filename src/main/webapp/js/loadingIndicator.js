document.addEventListener("DOMContentLoaded", function () {
    const loadingIndicator = document.getElementById("loadingIndicator");
    const content = document.getElementById("content");

    loadingIndicator.style.display = "block";

    setTimeout(() => {
        loadingIndicator.style.display = "none";
        content.style.display = "block";
    }, 3000);
});