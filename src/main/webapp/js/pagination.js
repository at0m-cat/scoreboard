let currentPage = 1;
function goToPage(selectedPage, totalPages) {
    if (selectedPage < 1 || selectedPage > totalPages) {
        return;
    }

    currentPage = selectedPage;
    document.getElementById("currentPageDisplay").innerText = `Page ${currentPage}`;
    document.getElementById("prevPage").disabled = currentPage === 1;
    document.getElementById("nextPage").disabled = currentPage === totalPages;

    loadPageData(currentPage);
}

function loadPageData(page) {
    // todo: логика для загрузки страниц, номер страницы отправлять в сервлет
}