package Dao;

import Bookmarker.DataStore;
import Entities.Bookmark;
import Entities.UserBookmark;
import Entities.WebLink;

import java.util.ArrayList;
import java.util.List;

public class BookmarkDao {
    public List<List<Bookmark>> getBookmarks(){
        return DataStore.getBookmarks();
    }

    public void saveUserBookmark(UserBookmark userBookmark){
        DataStore.add(userBookmark);
    }

    public List<WebLink> getAllWebLinks(){
        List<WebLink> webLinks = new ArrayList<>();
        List<List<Bookmark>> bookmarks = getBookmarks();
        List<Bookmark> weblinks = bookmarks.get(0);
        for(Bookmark weblink:webLinks){
            weblinks.add((WebLink)weblink);
        }
        return webLinks;
    }

    public List<WebLink> getWebLinks(WebLink.DownloadStatus downloadStatus){
        List<WebLink> webLinks = new ArrayList<>();

        List<WebLink> weblinks = getAllWebLinks();
        for(WebLink webLink:weblinks){
            if(webLink.getDownloadStatus().equals(downloadStatus)){
                webLinks.add(webLink);
            }
        }
        return webLinks;
    }

}
