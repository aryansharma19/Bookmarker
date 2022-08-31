package Dao;

import Bookmarker.DataStore;
import Entities.User;

public class UserDao {
    public User[] getUsers(){
        return DataStore.getUser();
    }
}
