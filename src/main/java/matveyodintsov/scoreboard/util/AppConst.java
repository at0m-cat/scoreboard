package matveyodintsov.scoreboard.util;

public class AppConst {

    private AppConst() {
    }

    public static final class Constants {
        public static final int PAGE_SIZE = 7;
    }

    public static final class Route {
        public static final String ERROR_JSP = "/WEB-INF/error.jsp";
        public static final String GAME_CONTROL_JSP = "/WEB-INF/game-control.jsp";
        public static final String REG_JSP = "/WEB-INF/match-reg.jsp";
        public static final String SCOREBOARD_JSP = "/WEB-INF/match-score.jsp";
        public static final String MATCH_JSP = "/WEB-INF/single-match.jsp";
        public static final String PLAYERS_TABLE_JSP = "/WEB-INF/players-table.jsp";
        public static final String PLAYER_INFO_JSP = "/WEB-INF/player-info.jsp";
        public static final String MATCH_SERVLET = "/match";
        public static final String MATCH_SCORE_SERVLET = "/match-score";
    }

    public static final class Message {
        public static final String INVALID_PARAM = "Invalid parameters";
        public static final String PLAYER_THEMSELVES = "Player cannot play against themselves";
        public static final String ERROR_UUID = "Missing required parameter 'uuid'";
        public static final String PLAYER_NAME_EMPTY = "Name cannot be empty";
        public static final String PLAYER_NOT_FOUND = "Player not found";
        public static final String GAME_NOT_EXIST = "Match does not exist";
        public static final String GAME_NOT_FOUND = "Match not found";
        public static final String PAGE_NOT_FOUND = "Page not found";
    }
}