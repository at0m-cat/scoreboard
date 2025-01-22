async function updateScore(buttonElement) {
    const playerName = buttonElement.getAttribute('data-player');
    if (!playerName) {
        alert('Player identifier not found');
        return;
    }

    try {
        const body = new URLSearchParams();
        body.append("uuid", gameUuid);
        body.append("playerName", playerName);

        const response = await fetch('match-score', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: body.toString(),
        });

        if (response.ok) {
            const updatedGame = await response.json();
            updateUI(updatedGame);
        } else {
            alert('Failed to update the score');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('An error occurred while updating the score');
    }
}

function updateUI(updatedGame) {
    document.getElementById('firstPlayerScore').innerText = updatedGame.firstPlayerScore;
    document.getElementById('secondPlayerScore').innerText = updatedGame.secondPlayerScore;
    document.getElementById('firstPlayerGames').innerText = updatedGame.firstPlayerGames;
    document.getElementById('secondPlayerGames').innerText = updatedGame.secondPlayerGames;
    document.getElementById('firstPlayerSets').innerText = updatedGame.firstPlayerSets;
    document.getElementById('secondPlayerSets').innerText = updatedGame.secondPlayerSets;
}