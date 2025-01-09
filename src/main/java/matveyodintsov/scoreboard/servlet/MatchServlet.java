package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.service.GameService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/match")
public class MatchServlet extends HttpServlet {

    private GameService gameService;

    @Override
    public void init() throws ServletException {
        this.gameService = new GameService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");

//        todo: redirect match?uuid=000
//        todo: create jsp

    }
}