package Bookmarker;

import Constants.BookGenre;
import Constants.Gender;
import Constants.MovieGenre;
import Constants.UserType;
import Entities.Bookmark;
import Entities.User;
import Entities.UserBookmark;
import Manager.BookmarkManager;
import Manager.UserManager;

public class DataStore {
    public static final int USER_LIMIT = 5;
    public static final int BOOKMARK_TYPES_COUNT = 3;
    public static final int BOOKMARK_COUNT_PER_TYPE = 5;
    private static int counter;

    public static User[] getUser() {
        return user;
    }

    public static void setUser(User[] user) {
        DataStore.user = user;
    }

    public static Bookmark[][] getBookmarks() {
        return bookmarks;
    }

    public static void setBookmarks(Bookmark[][] bookmarks) {
        DataStore.bookmarks = bookmarks;
    }

    public static UserBookmark[] getUserBookmarks() {
        return userBookmarks;
    }

    public static void setUserBookmarks(UserBookmark[] userBookmarks) {
        DataStore.userBookmarks = userBookmarks;
    }

    private static final int USER_BOOKMARK_LIMIT = 5;


    private static User[] user = new User[USER_LIMIT];
    private static Bookmark[][] bookmarks = new Bookmark[BOOKMARK_TYPES_COUNT][BOOKMARK_COUNT_PER_TYPE];
    private static UserBookmark[] userBookmarks = new UserBookmark[USER_LIMIT*USER_BOOKMARK_LIMIT];

    public static void loadData(){
        loadUsers();
        loadWebLinks();
        loadMovies();
        loadBooks();
    }

    public static void loadUsers(){
        user[0] = UserManager.getInstance().createUser(1000,"user0@semanticsquare.com","test","John","M",Gender.MALE, UserType.USER);
        user[1] = UserManager.getInstance().createUser(1001,"user1@semanticsquare.com","test","Sam","M",Gender.MALE,UserType.USER);
        user[2] = UserManager.getInstance().createUser(1002,"user2@semanticsquare.com","test","Anita","M",Gender.FEMALE,UserType.EDITOR);
        user[3] = UserManager.getInstance().createUser(1003,"user3@semanticsquare.com","test","Sara","M",Gender.FEMALE,UserType.EDITOR);
        user[4] = UserManager.getInstance().createUser(1004,"user4@semanticsquare.com","test","Dheeru","M",Gender.MALE,UserType.CHIEF_EDITOR);
    }

    public static void loadWebLinks(){
        bookmarks[0][0] = BookmarkManager.getInstance().createWebLink(2000,"Taming Tiger, Part 2","http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html","http://www.javaworld.com");
        bookmarks[0][1] = BookmarkManager.getInstance().createWebLink(2001,"How do I import a pre-existing Java project into Eclipse and get up and running?","http://stackoverflow.com/questions/142863/how-do-i-import-a-pre-existing-java-project-into-eclipse-and-get-up-and-running","http://www.stackoverflow.com");
        bookmarks[0][2] = BookmarkManager.getInstance().createWebLink(2002,"Interface vs Abstract Class","http://mindprod.com/jgloss/interfacevsabstract.html","http://mindprod.com");
        bookmarks[0][3] = BookmarkManager.getInstance().createWebLink(2003,"NIO tutorial by Greg Travis","http://cs.brown.edu/courses/cs161/papers/j-nio-ltr.pdf","http://cs.brown.edu");
        bookmarks[0][4] = BookmarkManager.getInstance().createWebLink(2004,"Virtual Hosting and Tomcat","http://tomcat.apache.org/tomcat-6.0-doc/virtual-hosting-howto.html","http://tomcat.apache.org");
    }

    public static void loadMovies(){
        bookmarks[1][0] = BookmarkManager.getInstance().createMovie(3000,"Citizen Kane",1941,new String[]{"Orson Welles","Joseph Cotten"},new String[]{"Orson Welles"}, MovieGenre.CLASSICS,8.5);
        bookmarks[1][1] = BookmarkManager.getInstance().createMovie(3001,"The Grapes of Wrath",1940,new String[]{"Henry Fonda","Jane Darwell"},new String[]{"John Ford"},MovieGenre.CLASSICS,8.2);
        bookmarks[1][2] = BookmarkManager.getInstance().createMovie(3002,"A Touch of Greatness",2004,new String[]{"Albert Cullum"},new String[]{"Leslie Sullivan"},MovieGenre.DOCUMENTARIES,7.3);
        bookmarks[1][3] = BookmarkManager.getInstance().createMovie(3003,"The Big Bang Theory",2007,new String[]{"Kaley Cuoco","Jim Parsons"},new String[]{"Chuck Lorre","Bill Prady"},MovieGenre.TV_SHOWS,8.7);
        bookmarks[1][4] = BookmarkManager.getInstance().createMovie(3004,"Ikiru",1952,new String[]{"Takashi Shimura","Minoru Chiaki"},new String[]{"Akira Kurosawa"},MovieGenre.FOREIGN_MOVIES,8.4);
    }

    public static void loadBooks(){
        bookmarks[2][0] = BookmarkManager.getInstance().createBook(4000,"Walden",1854,"Wilder Publications",new String[]{"Wilder Publications"}, BookGenre.PHILOSOPHY,4.3);
        bookmarks[2][1] = BookmarkManager.getInstance().createBook(4001,"Self-Reliance and Other Essays",1993,"Dover Publications",new String[]{"Ralph Waldo Emerson"},BookGenre.PHILOSOPHY,4.5);
        bookmarks[2][2] = BookmarkManager.getInstance().createBook(4002,"Light From Many Lamps",1988,"Touchstone",new String[]{"Lillian Eichler Watson"},BookGenre.PHILOSOPHY,5.0);
        bookmarks[2][3] = BookmarkManager.getInstance().createBook(4003,"Head First Design Patterns",2004,"O'Reilly Media",new String[]{"Eric Freeman","Bert Bates","Kathy Sierra","Elisabeth Robson"},BookGenre.TECHNICAL,4.5);
        bookmarks[2][4] = BookmarkManager.getInstance().createBook(4004,"Effective Java Programming Language Guide",2007,"Prentice Hall",new String[]{"Joshua Bloch"},BookGenre.TECHNICAL,4.9);

    }

    public static void add(UserBookmark userBookmark){
        userBookmarks[counter] = userBookmark;
        counter++;
    }
}
