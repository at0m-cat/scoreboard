package matveyodintsov.scoreboard.servlet;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.model.Player;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import matveyodintsov.scoreboard.repository.PlayerRepository;
import matveyodintsov.scoreboard.service.OngoingGameService;
import matveyodintsov.scoreboard.service.PlayerService;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@WebServlet("/new-match")
public class RegisterGameServlet extends HttpServlet {

    private PlayerService playerService;
    private OngoingGameService ongoingGameService;

    @Override
    public void init() throws ServletException {
        this.playerService = new PlayerService(new PlayerRepository());
        this.ongoingGameService = OngoingGameService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/match-reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String p1 = request.getParameter("p1");
        String p2 = request.getParameter("p2");

        if (p1 == null || p2 == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("message", "Invalid parameters");
            request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
        }

        if (p1.equals(p2)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("message", "Player cannot play against themselves.");
            request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
            return;
        }

        Player firstPlayer;
        Player secondPlayer;

        if (playerService.isPlayer(p1)) {
            firstPlayer = playerService.getByKey(p1);
        } else {
            firstPlayer = new Player(p1);
            playerService.save(firstPlayer);
        }

        if (playerService.isPlayer(p2)) {
            secondPlayer = playerService.getByKey(p2);
        } else {
            secondPlayer = new Player(p2);
            playerService.save(secondPlayer);
        }

        Game game = new Game(firstPlayer, secondPlayer);

        HttpSession session = request.getSession();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> {

            try {
                Thread.sleep(2800);
                ongoingGameService.save(game);
                synchronized (session) {
                    session.setAttribute("localGames", ongoingGameService.getAll());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        session.setAttribute("currentGame", game);
        response.sendRedirect("update-score");

        //todo: убрать GET запрос к update-score (match-control.jsp)
        // подумать над логикой передачи локального репозитория к update-score (match-control.jsp)

        //todo: первые шаги к потокобезопасности:
        // 1 - задержка, для добавления в concurrentHashMap игры (синглет локального репозитория)
        // 2 - в game-control из репозитория вытащить игру
    }
}