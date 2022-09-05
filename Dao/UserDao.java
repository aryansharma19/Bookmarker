package Dao;

import Bookmarker.DataStore;
import Entities.User;

import java.util.List;

public class UserDao {
    public List<User> getUsers(){
        return DataStore.getUser();
    }
}
