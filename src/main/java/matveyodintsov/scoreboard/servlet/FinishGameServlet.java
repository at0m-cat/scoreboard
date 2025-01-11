package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.service.GameService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import java.io.IOException;

@WebServlet("/finish-game")
public class FinishGameServlet extends HttpServlet {

    private GameService gameService;

    @Override
    public void init() throws ServletException {
        this.gameService = new GameService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Game currentGame = (Game) session.getAttribute("currentGame");

        if (currentGame != null) {
            gameService.save(currentGame);
            session.removeAttribute("currentGame");
        }

        response.sendRedirect("match-score?uuid=" + currentGame.getUuid());
    }
}