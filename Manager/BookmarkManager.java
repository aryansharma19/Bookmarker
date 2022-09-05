package Manager;

import Dao.BookmarkDao;
import Entities.*;
import Util.HttpConnect;
import Util.IOUtil;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

public class BookmarkManager {
    private static BookmarkManager instance = new BookmarkManager();
    private static BookmarkDao dao = new BookmarkDao();
    private BookmarkManager(){};
    public static BookmarkManager getInstance(){
        return instance;
    }

   public Movie createMovie(long id,String title,int releaseYear, String[] cast, String[] directors, String genre, double imdbRating){
        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle(title);
        movie.setReleaseYear(releaseYear);
        movie.setCast(cast);
        movie.setDirectors(directors);
        movie.setGenre(genre);
        movie.setImdbRating(imdbRating);

        return movie;
    }

    public Book createBook(long id,String title,int publicationYear,String publisher, String[] authors,String genre,double amazonRating){
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setPublicationYear(publicationYear);
        book.setPublisher(publisher);
        book.setAuthors(authors);
        book.setGenre(genre);
        book.setAmazonRating(amazonRating);

        return book;
    }

    public WebLink createWebLink(long id,String title,String url,String host){
        WebLink weblink = new WebLink();
        weblink.setId(id);
        weblink.setTitle(title);
        weblink.setHost(host);
        weblink.setUrl(url);

        return weblink;
    }

    public List<List<Bookmark>> getBookmarks(){
        return dao.getBookmarks();
    }

    public void saveUserBookmark(User user, Bookmark bookmark){
        UserBookmark userBookmark = new UserBookmark();
        userBookmark.setBookmark(bookmark);
        userBookmark.setUser(user);
        dao.saveUserBookmark(userBookmark);

        if(bookmark instanceof WebLink){
            String url = ((WebLink) bookmark).getUrl();
            if(!url.endsWith(".pdf")){
                try {
                    String data = HttpConnect.download(url);
                    if(data != null){
                        IOUtil.write(data,bookmark.getId());
                    }
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void setKidFriendlyStatus(User user,String kidFriendlyStatus,Bookmark bookmark){
        bookmark.setKidFriendlyStatus(kidFriendlyStatus);
        bookmark.setKidFriendlyStatusMarkedBy(user);
        System.out.println("kid-friendly status : "+kidFriendlyStatus+" , "+ bookmark+" ,Marked by :"+user.getEmail());
    }

    public void share(User user,Bookmark bookmark){
        bookmark.setSharedBy(user);
        System.out.println("Data to be shared: ");
        if(bookmark instanceof Book){
            System.out.println(((Book) bookmark).getItemData());
        }
        else if(bookmark instanceof WebLink){
            System.out.println(((WebLink) bookmark).getItemData());
        }
    }
}
