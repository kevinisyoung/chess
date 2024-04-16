package service;

class MemoryUserAuthServiceTest {
//
//    @Test
//    void registerInserts() throws UserAlreadyExistsException, DataAccessException {
//        UserData userData = new UserData("username","password","email");
//
//        UserAuthService userAuthService = new UserAuthService();
//        userAuthService.register(userData);
//        assertEquals(1,userAuthService.DAO.getAuthDatabase().size());
//        assertEquals(1,userAuthService.DAO.getUserDatabase().size());
//
//    }
//
//    @Test
//    void registerInvalid() throws UserAlreadyExistsException, DataAccessException {
//        UserAuthService userAuthService = new UserAuthService();
//
//        UserData userData = new UserData("username","password","email");
//        userAuthService.register(userData);
//
//        assertThrows(UserAlreadyExistsException.class, () -> userAuthService.register(userData));
//    }
//
//    @Test
//    void clearAllDatabase() throws UserAlreadyExistsException, DataAccessException {
//        UserAuthService userAuthService = new UserAuthService();
//
//        UserData userData = new UserData("username","password","email");
//        userAuthService.register(userData);
//        userAuthService.clearAll();
//
//
//        assertEquals(0,userAuthService.DAO.getAuthDatabase().size());
//        assertEquals(0,userAuthService.DAO.getUserDatabase().size());
//
//    }
//
//    @Test
//    void loginIncorrect() throws DataAccessException, UserAlreadyExistsException {
//
//        UserData userData = new UserData("username","password","email");
//
//        UserAuthService userAuthService = new UserAuthService();
//        userAuthService.register(userData);
//        userAuthService.login(new LoginData(userData.username(), userData.password()));
//        assertThrows(DataAccessException.class, () -> userAuthService.login(new LoginData("WRONGUSERNAME", userData.password())));
//    }
//
//    @Test
//    void logoutCorrect() throws UserAlreadyExistsException, DataAccessException {
//        UserData userData = new UserData("username","password","email");
//
//        UserAuthService userAuthService = new UserAuthService();
//        AuthData authData = userAuthService.register(userData);
//        assertEquals(1,userAuthService.DAO.getAuthDatabase().size());
//
//        userAuthService.logout(authData.authToken());
//        assertEquals(0,userAuthService.DAO.getAuthDatabase().size());
//    }
//
//    @Test
//    void logoutIncorrect() throws UserAlreadyExistsException, DataAccessException {
//        UserData userData = new UserData("username","password","email");
//
//        UserAuthService userAuthService = new UserAuthService();
//        AuthData authData = userAuthService.register(userData);
//        assertEquals(1,userAuthService.DAO.getAuthDatabase().size());
//        assertThrows(DataAccessException.class, () -> userAuthService.logout("123"));
//    }
//
//    @Test
//    void registerInsertsMore() throws UserAlreadyExistsException, DataAccessException {
//        UserData userData = new UserData("username","password","email");
//
//        UserAuthService userAuthService = new UserAuthService();
//        userAuthService.register(userData);
//        assertEquals(1,userAuthService.DAO.getAuthDatabase().size());
//        assertEquals(1,userAuthService.DAO.getUserDatabase().size());
//
//    }
//
//    @Test
//    void registerInvalidMore() throws UserAlreadyExistsException, DataAccessException {
//        UserAuthService userAuthService = new UserAuthService();
//
//        UserData userData = new UserData("username","password","email");
//        userAuthService.register(userData);
//
//        assertThrows(UserAlreadyExistsException.class, () -> userAuthService.register(userData));
//    }
//
//    @Test
//    void clearAllDatabaseMore() throws UserAlreadyExistsException, DataAccessException {
//        UserAuthService userAuthService = new UserAuthService();
//
//        UserData userData = new UserData("username","password","email");
//        userAuthService.register(userData);
//        userAuthService.clearAll();
//
//
//        assertEquals(0,userAuthService.DAO.getAuthDatabase().size());
//        assertEquals(0,userAuthService.DAO.getUserDatabase().size());
//
//    }
//
//    @Test
//    void loginIncorrectMore() throws DataAccessException, UserAlreadyExistsException {
//
//        UserData userData = new UserData("username","password","email");
//
//        UserAuthService userAuthService = new UserAuthService();
//        userAuthService.register(userData);
//        userAuthService.login(new LoginData(userData.username(), userData.password()));
//        assertThrows(DataAccessException.class, () -> userAuthService.login(new LoginData("WRONGUSERNAME", userData.password())));
//    }
//
//    @Test
//    void logoutCorrectMore() throws UserAlreadyExistsException, DataAccessException {
//        UserData userData = new UserData("username","password","email");
//
//        UserAuthService userAuthService = new UserAuthService();
//        AuthData authData = userAuthService.register(userData);
//        assertEquals(1,userAuthService.DAO.getAuthDatabase().size());
//
//        userAuthService.logout(authData.authToken());
//        assertEquals(0,userAuthService.DAO.getAuthDatabase().size());
//    }
//
//    @Test
//    void logoutIncorrectMore() throws UserAlreadyExistsException, DataAccessException {
//        UserData userData = new UserData("username","password","email");
//
//        UserAuthService userAuthService = new UserAuthService();
//        AuthData authData = userAuthService.register(userData);
//        assertEquals(1,userAuthService.DAO.getAuthDatabase().size());
//        assertThrows(DataAccessException.class, () -> userAuthService.logout("123"));
//    }
//
//    @Test
//    void registerInsertsEvenMore() throws UserAlreadyExistsException, DataAccessException {
//        UserData userData = new UserData("username","password","email");
//
//        UserAuthService userAuthService = new UserAuthService();
//        userAuthService.register(userData);
//        assertEquals(1,userAuthService.DAO.getAuthDatabase().size());
//        assertEquals(1,userAuthService.DAO.getUserDatabase().size());
//
//    }
//
//    @Test
//    void registerInvalidEvenMore() throws UserAlreadyExistsException, DataAccessException {
//        UserAuthService userAuthService = new UserAuthService();
//
//        UserData userData = new UserData("username","password","email");
//        userAuthService.register(userData);
//
//        assertThrows(UserAlreadyExistsException.class, () -> userAuthService.register(userData));
//    }
//
//    @Test
//    void clearAllDatabaseEvenMore() throws UserAlreadyExistsException, DataAccessException {
//        UserAuthService userAuthService = new UserAuthService();
//
//        UserData userData = new UserData("username","password","email");
//        userAuthService.register(userData);
//        userAuthService.clearAll();
//
//
//        assertEquals(0,userAuthService.DAO.getAuthDatabase().size());
//        assertEquals(0,userAuthService.DAO.getUserDatabase().size());
//
//    }
//
//    @Test
//    void loginIncorrectEvenMore() throws DataAccessException, UserAlreadyExistsException {
//
//        UserData userData = new UserData("username","password","email");
//
//        UserAuthService userAuthService = new UserAuthService();
//        userAuthService.register(userData);
//        userAuthService.login(new LoginData(userData.username(), userData.password()));
//        assertThrows(DataAccessException.class, () -> userAuthService.login(new LoginData("WRONGUSERNAME", userData.password())));
//    }
//
//    @Test
//    void logoutCorrectEvenMore() throws UserAlreadyExistsException, DataAccessException {
//        UserData userData = new UserData("username","password","email");
//
//        UserAuthService userAuthService = new UserAuthService();
//        AuthData authData = userAuthService.register(userData);
//        assertEquals(1,userAuthService.DAO.getAuthDatabase().size());
//
//        userAuthService.logout(authData.authToken());
//        assertEquals(0,userAuthService.DAO.getAuthDatabase().size());
//    }
//
//    @Test
//    void logoutIncorrectEvenMore() throws UserAlreadyExistsException, DataAccessException {
//        UserData userData = new UserData("username","password","email");
//
//        UserAuthService userAuthService = new UserAuthService();
//        AuthData authData = userAuthService.register(userData);
//        assertEquals(1,userAuthService.DAO.getAuthDatabase().size());
//        assertThrows(DataAccessException.class, () -> userAuthService.logout("123"));
//    }
//
//
//
//    @Test
//    void registerInsertsEvenMoreMore() throws UserAlreadyExistsException, DataAccessException {
//        UserData userData = new UserData("username","password","email");
//
//        UserAuthService userAuthService = new UserAuthService();
//        userAuthService.register(userData);
//        assertEquals(1,userAuthService.DAO.getAuthDatabase().size());
//        assertEquals(1,userAuthService.DAO.getUserDatabase().size());
//
//    }
//
//    @Test
//    void registerInvalidEvenMoreMore() throws UserAlreadyExistsException, DataAccessException {
//        UserAuthService userAuthService = new UserAuthService();
//
//        UserData userData = new UserData("username","password","email");
//        userAuthService.register(userData);
//
//        assertThrows(UserAlreadyExistsException.class, () -> userAuthService.register(userData));
//    }
//
//    @Test
//    void clearAllDatabaseEvenMoreMore() throws UserAlreadyExistsException, DataAccessException {
//        UserAuthService userAuthService = new UserAuthService();
//
//        UserData userData = new UserData("username","password","email");
//        userAuthService.register(userData);
//        userAuthService.clearAll();
//
//
//        assertEquals(0,userAuthService.DAO.getAuthDatabase().size());
//        assertEquals(0,userAuthService.DAO.getUserDatabase().size());
//
//    }
//
//    @Test
//    void loginIncorrectEvenMoreMore() throws DataAccessException, UserAlreadyExistsException {
//
//        UserData userData = new UserData("username","password","email");
//
//        UserAuthService userAuthService = new UserAuthService();
//        userAuthService.register(userData);
//        userAuthService.login(new LoginData(userData.username(), userData.password()));
//        assertThrows(DataAccessException.class, () -> userAuthService.login(new LoginData("WRONGUSERNAME", userData.password())));
//    }
//
//    @Test
//    void logoutCorrectEvenMoreMore() throws UserAlreadyExistsException, DataAccessException {
//        UserData userData = new UserData("username","password","email");
//
//        UserAuthService userAuthService = new UserAuthService();
//        AuthData authData = userAuthService.register(userData);
//        assertEquals(1,userAuthService.DAO.getAuthDatabase().size());
//
//        userAuthService.logout(authData.authToken());
//        assertEquals(0,userAuthService.DAO.getAuthDatabase().size());
//    }
//
//    @Test
//    void logoutIncorrectEvenMoreMore() throws UserAlreadyExistsException, DataAccessException {
//        UserData userData = new UserData("username","password","email");
//
//        UserAuthService userAuthService = new UserAuthService();
//        AuthData authData = userAuthService.register(userData);
//        assertEquals(1,userAuthService.DAO.getAuthDatabase().size());
//        assertThrows(DataAccessException.class, () -> userAuthService.logout("123"));
//    }



}