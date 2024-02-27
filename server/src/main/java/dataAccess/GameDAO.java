package dataAccess;

public interface GameDAO {

    public void clearAll();
}
/*    For the most part, the methods on your DAO classes will be CRUD operations that:

        Create objects in the data store
        Read objects from the data store
        Update objects already in the data store
        Delete objects from the data store
        Oftentimes, the parameters and return values of your DAO methods will be the model objects described in the previous section (UserData, GameData, and AuthData). For example, your DAO classes will certainly need to provide a method for creating new UserData objects in the data store. This method might have a signature that looks like this:

        void insertUser(UserData u) throws DataAccessException
*/

/*
DataAccessException:
This exception should be thrown by data access methods that could fail. If a method call fails,
it should throw a DataAccessException. For example, the DataAccessException is thrown if a user
attempts to update a non-existent game.
*/

/*
Example Data Access Methods
Here are some examples of the kinds of methods your DAOs will need to support. This list is not exhaustive. You should consult your server design in order to determine all of the methods you need to provide.

clear: A method for clearing all data from the database. This is used during testing.
createUser: Create a new user.
getUser: Retrieve a user with the given username.
createGame: Create a new game.
getGame: Retrieve a specified game with the given game ID.
listGames: Retrieve all games.
updateGame: Updates a chess game. It should replace the chess game string corresponding to a given gameID. This is used when players join a game or when a move is made.
createAuth: Create a new authorization.
getAuth: Retrieve an authorization given an authToken.
deleteAuth: Delete an authorization so that it is no longer valid.

 */
