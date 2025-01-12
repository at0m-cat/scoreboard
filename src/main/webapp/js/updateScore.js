async function updateScore(player, action) {
    try {
        const body = new URLSearchParams();
        body.append("uuid", gameUuid);
        body.append("player", player);
        body.append("action", action);

        const response = await fetch('match-score', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: body.toString(),
        });

        if (response.ok) {
            const updatedGame = await response.json();
            document.getElementById('firstPlayerScore').innerText = updatedGame.firstPlayerScore;
            document.getElementById('secondPlayerScore').innerText = updatedGame.secondPlayerScore;
        } else {
            alert('Failed to update the score');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('An error occurred while updating the score');
    }
}