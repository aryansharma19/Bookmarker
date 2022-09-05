package Dao;

import Bookmarker.DataStore;
import Entities.Bookmark;
import Entities.UserBookmark;

import java.util.List;

public class BookmarkDao {
    public List<List<Bookmark>> getBookmarks(){
        return DataStore.getBookmarks();
    }

    public void saveUserBookmark(UserBookmark userBookmark){
        DataStore.add(userBookmark);
    }
}
