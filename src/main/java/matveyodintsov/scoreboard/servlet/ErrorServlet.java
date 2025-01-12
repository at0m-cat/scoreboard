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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String message = request.getParameter("message");
        session.setAttribute("message", message);
        request.getRequestDispatcher(errorPage).forward(request, response);
    }
}