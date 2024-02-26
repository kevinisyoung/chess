package dataAccess;

public class GameDAO {
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