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
import Util.IOUtil;

import java.util.ArrayList;
import java.util.List;

public class DataStore {
    /*public static final int USER_LIMIT = 5;
    public static final int BOOKMARK_TYPES_COUNT = 3;
    public static final int BOOKMARK_COUNT_PER_TYPE = 5;*/
    private static int counter;

    public static List<User> getUser() {
        return user;
    }

    public static void setUser(List<User> user) {
        DataStore.user = user;
    }

    public static List<List<Bookmark>> getBookmarks() {
        return bookmarks;
    }

    public static void setBookmarks(List<List<Bookmark>> bookmarks) {
        DataStore.bookmarks = bookmarks;
    }

    public static UserBookmark[] getUserBookmarks() {
        return userBookmarks;
    }

    public static void setUserBookmarks(UserBookmark[] userBookmarks) {
        DataStore.userBookmarks = userBookmarks;
    }

    //public static final int USER_BOOKMARK_LIMIT = 5;


    private static List<User> user = new ArrayList<>();
    private static List<List<Bookmark>> bookmarks = new ArrayList<>();
    private static List<UserBookmark> userBookmarks = new ArrayList<>();

    public static void loadData(){
        loadUsers();
        loadWebLinks();
        loadMovies();
        loadBooks();
    }

    public static void loadUsers(){
        //String[] userDataFromFile = new String[USER_LIMIT];
        List<String> userDataFromFile = new ArrayList<>();
        IOUtil.read(userDataFromFile, "./src/files/User");
        int counter = 0;
        for(String row:userDataFromFile){
            String[] value = row.split("\t");
            int gender = Gender.MALE;
            if(value[4].equals("f")){
                gender = Gender.FEMALE;
            }
            else if(value[4].equals("t")){
                gender = Gender.TRANSGENDER;
            }
            user.add(UserManager.getInstance().createUser(Long.parseLong(value[0]),value[1],value[2],value[3],value[4],gender,value[6]));
        }
        /*user[0] = UserManager.getInstance().createUser(1000,"user0@semanticsquare.com","test","John","M",Gender.MALE, UserType.USER);
        user[1] = UserManager.getInstance().createUser(1001,"user1@semanticsquare.com","test","Sam","M",Gender.MALE,UserType.USER);
        user[2] = UserManager.getInstance().createUser(1002,"user2@semanticsquare.com","test","Anita","M",Gender.FEMALE,UserType.EDITOR);
        user[3] = UserManager.getInstance().createUser(1003,"user3@semanticsquare.com","test","Sara","M",Gender.FEMALE,UserType.EDITOR);
        user[4] = UserManager.getInstance().createUser(1004,"user4@semanticsquare.com","test","Dheeru","M",Gender.MALE, UserType.CHIEF_EDITOR);*/
    }

    public static void loadWebLinks(){
        //String[] weblinkDataFromFile = new String[BOOKMARK_COUNT_PER_TYPE];
        List<String> weblinkDataFromFile = new ArrayList<>();
        IOUtil.read(weblinkDataFromFile,"./src/files/WebLink");
        List<Bookmark> values = new ArrayList<>();
        for(String row:weblinkDataFromFile){
            String[] data = row.split("\t");
            values.add(BookmarkManager.getInstance().createWebLink(Long.parseLong(data[0]),data[1],data[2],data[3]));
        }
        bookmarks.add(values);
        /*bookmarks[0][0] = BookmarkManager.getInstance().createWebLink(2000,"Taming Tiger, Part 2","http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html","http://www.javaworld.com");
        bookmarks[0][1] = BookmarkManager.getInstance().createWebLink(2001,"How do I import a pre-existing Java project into Eclipse and get up and running?","http://stackoverflow.com/questions/142863/how-do-i-import-a-pre-existing-java-project-into-eclipse-and-get-up-and-running","http://www.stackoverflow.com");
        bookmarks[0][2] = BookmarkManager.getInstance().createWebLink(2002,"Interface vs Abstract Class","http://mindprod.com/jgloss/interfacevsabstract.html","http://mindprod.com");
        bookmarks[0][3] = BookmarkManager.getInstance().createWebLink(2003,"NIO tutorial by Greg Travis","http://cs.brown.edu/courses/cs161/papers/j-nio-ltr.pdf","http://cs.brown.edu");
        bookmarks[0][4] = BookmarkManager.getInstance().createWebLink(2004,"Virtual Hosting and Tomcat","http://tomcat.apache.org/tomcat-6.0-doc/virtual-hosting-howto.html","http://tomcat.apache.org");*/
    }

    public static void loadMovies(){
        //String[] moviesDataFromFile = new String[BOOKMARK_COUNT_PER_TYPE];
        List<String> moviesDataFromFile = new ArrayList<>();
        IOUtil.read(moviesDataFromFile,"./src/files/Movie");
        List<Bookmark> values = new ArrayList<>();
        for(String row:moviesDataFromFile){
            String[] data = row.split("\t");
            values.add(BookmarkManager.getInstance().createMovie(Long.parseLong(data[0]),data[1],Integer.parseInt(data[2]),data[3].split(","),data[4].split(","),data[5],Double.parseDouble(data[6])));
        }
        bookmarks.add(values);
        /*bookmarks[1][0] = BookmarkManager.getInstance().createMovie(3000,"Citizen Kane",1941,new String[]{"Orson Welles","Joseph Cotten"},new String[]{"Orson Welles"}, MovieGenre.CLASSICS,8.5);
        bookmarks[1][1] = BookmarkManager.getInstance().createMovie(3001,"The Grapes of Wrath",1940,new String[]{"Henry Fonda","Jane Darwell"},new String[]{"John Ford"},MovieGenre.CLASSICS,8.2);
        bookmarks[1][2] = BookmarkManager.getInstance().createMovie(3002,"A Touch of Greatness",2004,new String[]{"Albert Cullum"},new String[]{"Leslie Sullivan"},MovieGenre.DOCUMENTARIES,7.3);
        bookmarks[1][3] = BookmarkManager.getInstance().createMovie(3003,"The Big Bang Theory",2007,new String[]{"Kaley Cuoco","Jim Parsons"},new String[]{"Chuck Lorre","Bill Prady"},MovieGenre.TV_SHOWS,8.7);
        bookmarks[1][4] = BookmarkManager.getInstance().createMovie(3004,"Ikiru",1952,new String[]{"Takashi Shimura","Minoru Chiaki"},new String[]{"Akira Kurosawa"},MovieGenre.FOREIGN_MOVIES,8.4);*/
    }

    public static void loadBooks(){
        //String[] booksDataFromFile = new String[BOOKMARK_COUNT_PER_TYPE];
        List<String> booksDataFromFile = new ArrayList<>();
        IOUtil.read(booksDataFromFile,"./src/files/Book");
        List<Bookmark> values = new ArrayList<>();
        for(String row:booksDataFromFile){
            String[] data = row.split("\t");
            values.add(BookmarkManager.getInstance().createBook(Long.parseLong(data[0]),data[1],Integer.parseInt(data[2]),data[3],data[4].split(","),data[5],Double.parseDouble(data[6])));
        }
        bookmarks.add(values);

        /*bookmarks[2][0] = BookmarkManager.getInstance().createBook(4000,"Walden",1854,"Wilder Publications",new String[]{"Wilder Publications"}, BookGenre.PHILOSOPHY,4.3);
        bookmarks[2][1] = BookmarkManager.getInstance().createBook(4001,"Self-Reliance and Other Essays",1993,"Dover Publications",new String[]{"Ralph Waldo Emerson"},BookGenre.PHILOSOPHY,4.5);
        bookmarks[2][2] = BookmarkManager.getInstance().createBook(4002,"Light From Many Lamps",1988,"Touchstone",new String[]{"Lillian Eichler Watson"},BookGenre.PHILOSOPHY,5.0);
        bookmarks[2][3] = BookmarkManager.getInstance().createBook(4003,"Head First Design Patterns",2004,"O'Reilly Media",new String[]{"Eric Freeman","Bert Bates","Kathy Sierra","Elisabeth Robson"},BookGenre.TECHNICAL,4.5);
        bookmarks[2][4] = BookmarkManager.getInstance().createBook(4004,"Effective Java Programming Language Guide",2007,"Prentice Hall",new String[]{"Joshua Bloch"},BookGenre.TECHNICAL,4.9);*/

    }

    public static void add(UserBookmark userBookmark){
        userBookmarks.add(userBookmark);
    }
}
