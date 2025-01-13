function redirectToMatch(uuid) {
    window.location.href = `/match?uuid=` + encodeURIComponent(uuid.trim());
}