async function updateScore(buttonElement) {
    const playerName = buttonElement.getAttribute('data-player');
    const playerNumber = buttonElement.getAttribute('player-number');

    if (!playerName || !playerNumber) {
        alert('Invalid player details');
        return;
    }

    try {
        const body = new URLSearchParams();
        body.append("uuid", gameUuid);
        body.append("playerName", playerName);
        body.append("playerNumber", playerNumber);

        const response = await fetch('match-score', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: body.toString(),
        });

        if (response.ok) {
            const updatedGame = await response.json();
            if (updatedGame.error) {
                alert(updatedGame.error);
                return;
            }

            updateUI(updatedGame.scoreboard);
            if (updatedGame.winner !== "none") {
                postRedirect('/finish-game', {uuid: gameUuid});
                return;
            }

        } else {
            alert('Failed to update the score');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('An error occurred while updating the score');
    }
}

function updateUI(scoreboard) {
    document.getElementById('firstPlayerScore').innerText = scoreboard.firstPlayerScore;
    document.getElementById('secondPlayerScore').innerText = scoreboard.secondPlayerScore;
    document.getElementById('firstPlayerGames').innerText = scoreboard.firstPlayerGames;
    document.getElementById('secondPlayerGames').innerText = scoreboard.secondPlayerGames;
    document.getElementById('firstPlayerSets').innerText = scoreboard.firstPlayerSets;
    document.getElementById('secondPlayerSets').innerText = scoreboard.secondPlayerSets;
}

function postRedirect(url, data) {
    const form = document.createElement('form');
    form.method = 'POST';
    form.action = url;

    for (const key in data) {
        if (data.hasOwnProperty(key)) {
            const input = document.createElement('input');
            input.type = 'hidden';
            input.name = key;
            input.value = data[key];
            form.appendChild(input);
        }
    }

    document.body.appendChild(form);
    form.submit();
}