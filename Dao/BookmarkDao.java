package Dao;

import Bookmarker.DataStore;
import Entities.Bookmark;

public class BookmarkDao {
    public Bookmark[][] getBookmarks(){
        return DataStore.getBookmarks();
    }
}
