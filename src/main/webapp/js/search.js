function search(event) {
    event.preventDefault();

    const playerName = document.getElementById("playerNameInput").value.trim();

    if (!playerName) {
        alert("Please enter a player name.");
        return false;
    }

    const url = new URL(window.location.href);
    url.searchParams.set("filter_by_player_name", playerName);
    url.searchParams.delete("page")

    window.location.href = url.toString();
}