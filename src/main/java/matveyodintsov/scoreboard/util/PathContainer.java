package matveyodintsov.scoreboard.util;

import lombok.Getter;

@Getter
public class PathContainer {

    public static String redirectToErrorPage() {
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

    public static String redirectToSingleGamePage() {
        return "/WEB-INF/single-match.jsp";
    }

    public static String redirectToPlayersTablePage() {
        return "/WEB-INF/players-table.jsp";
    }

    public static String redirectToPlayerPage() {
        return "/WEB-INF/player-info.jsp";
    }

    public static String redirectToSingleGameServlet() {
        return "/match";
    }

    public static String redirectToGameScoreUpdateServlet() {
        return "/match-score";
    }
}
