package Dao;

import Bookmarker.DataStore;
import Entities.Bookmark;
import Entities.UserBookmark;

public class BookmarkDao {
    public Bookmark[][] getBookmarks(){
        return DataStore.getBookmarks();
    }

    public void saveUserBookmark(UserBookmark userBookmark){
        DataStore.add(userBookmark);
    }
}
