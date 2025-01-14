package matveyodintsov.scoreboard.util;

import lombok.Getter;

@Getter
public class StringContainer {

    public static String redirectToErrorPage = "/WEB-INF/error.jsp";
    public static String redirectToGameControlPage = "/WEB-INF/game-control.jsp";
    public static String redirectToGameRegPage = "/WEB-INF/match-reg.jsp";
    public static String redirectToScoreboardPage = "/WEB-INF/match-score.jsp";
    public static String redirectToSingleGamePage = "/WEB-INF/single-match.jsp";
    public static String redirectToPlayersTablePage = "/WEB-INF/players-table.jsp";
    public static String redirectToPlayerPage = "/WEB-INF/player-info.jsp";
    public static String redirectToSingleGameServlet = "/match";
    public static String redirectToGameScoreUpdateServlet = "/match-score";

    public static String msgInvalidParam = "Invalid parameters";
    public static String msgPlayerThemselves = "Player cannot play against themselves";
    public static String msgErrorUUID = "Missing required parameter 'uuid'";
    public static String msgPlayerNameEmpty = "Name cannot be empty";
    public static String msgPlayerNotFound = "Player not found";
    public static String msgGameNotExist = "Match does not exist";
}
