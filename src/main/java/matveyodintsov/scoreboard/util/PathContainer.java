package matveyodintsov.scoreboard.util;

import lombok.Getter;

@Getter
public class PathContainer {

    public static String redirectToErrorPage () {
        return "/WEB-INF/error.jsp";
    }

    public static String redirectToGameControlPage() {
        return "/WEB-INF/game-control.jsp";
    }

    public static String redirectToGameRegPage() {
        return "/WEB-INF/match-reg.jsp";
    }

    public static String redirectToScoreboardPage() {
        return "/WEB-INF/match-score.jsp";
    }

    public static String redirectToSingleMatchServlet() {
        return "/match-score";
    }
}
