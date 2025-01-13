function redirectToPlayerInfo(name) {
    window.location.href = `/player?name=` + encodeURIComponent(name.trim());
}