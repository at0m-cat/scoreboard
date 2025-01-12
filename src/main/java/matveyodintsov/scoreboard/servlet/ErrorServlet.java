package matveyodintsov.scoreboard.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import matveyodintsov.scoreboard.util.PathContainer;

import java.io.IOException;

@WebServlet("/error")
public class ErrorServlet extends HttpServlet {

    private String errorPage;

    @Override
    public void init() throws ServletException {
        this.errorPage = PathContainer.redirectToErrorPage();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String message = req.getParameter("message");
        session.setAttribute("message", message);
        req.getRequestDispatcher(errorPage).forward(req, resp);
    }
}