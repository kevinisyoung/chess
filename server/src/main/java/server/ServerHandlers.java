package server;

public class ServerHandlers {

    public static Object registerHandler(){
        /*
        Register a user
            If successful, an authorization authToken is returned.
            You may use the authToken with future requests that require authorization.
            No authorization authToken is required to call this endpoint.
         */
        return null;
    }
    public static Object loginHandler(){
        /*
        Log in a user
            If successful, an authorization authToken is returned.
            You may use the authToken with future requests that require authorization.
            No authorization authToken is required to call this endpoint.
         */
        return null;
    }
    public static Object logoutHandler(){
        /*
        Logs out an authenticated user
            An authToken is required to call this endpoint.
         */
        return null;
    }
    public static Object listGamesHandler(){
        /*
        Lists all the games in the database
            This API does not take a request body.
            The response JSON lists all the games, including the board.
            An authToken is required to call this endpoint.
         */
        return null;
    }
    public static Object createGameHandler(){
        /*
        Create a new Chess Game
            The request body must contain a name for the game.
            The response JSON contains the ID of created game, or if failed,
            an error message describing the reason.
            An authToken is required to call this endpoint.
         */
        return null;
    }
    public static Object joinGameHandler(){
        /*
        Join a Chess Game
            The request body must contain the game ID.
            If no color is specified then the user is joined as an observer.
            An authToken is required to call this endpoint.
         */
        return null;
    }
    public static Object clearAllHandler(){
        /*
        Clear ALL data from the database
               This includes users and all game data.
               No authorization authToken is required.
         */
        return null;
    }

}


/*
        Spark.post("/user", (req,res) -> ServerHandlers.registerHandler());
        Spark.post("/session", (req,res) -> ServerHandlers.loginHandler());
        Spark.delete("/session", (req,res) -> ServerHandlers.logoutHandler());
        Spark.get("/game", (req,res) -> ServerHandlers.listGamesHandler());
        Spark.post("/game", (req,res) -> ServerHandlers.createGameHandler());
        Spark.put("/game", (req,res) -> ServerHandlers.joinGameHandler());
        Spark.delete("/db", (req,res) -> ServerHandlers.clearAllHandler());

 */