package Controller;

import Entities.Bookmark;
import Entities.User;
import Manager.BookmarkManager;
import Manager.UserManager;

public class BookmarkController {
    private static BookmarkController instance = new BookmarkController();

    private BookmarkController(){};

    public static BookmarkController getInstance(){
        return instance;
    }

    public void saveUserBookmark(User user, Bookmark bookmark){
        BookmarkManager.getInstance().saveUserBookmark(user,bookmark);
    }
}
