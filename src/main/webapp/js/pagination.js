function goToPage(selectedPage, totalPages) {
    if (selectedPage < 1 || selectedPage > totalPages) {
        return;
    }

    const url = new URL(window.location.href);
    url.searchParams.set("page", selectedPage);
    window.location.href = url.toString();
}