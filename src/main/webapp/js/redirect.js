function redirectToMatch(uuid) {
    window.location.href = `/match?uuid=` + encodeURIComponent(uuid.trim());
}

function redirectToPlayerInfo(name) {
    window.location.href = `/player?name=` + encodeURIComponent(name.trim());
}