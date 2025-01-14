package matveyodintsov.scoreboard.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import matveyodintsov.scoreboard.util.StringContainer;

import java.io.IOException;

@WebServlet("/error")
public class ErrorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String message = request.getParameter("message");
        session.setAttribute("message", message);
        request.getRequestDispatcher(StringContainer.Route.ERROR_JSP).forward(request, response);
    }
}