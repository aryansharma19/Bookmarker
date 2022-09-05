package Bookmarker;

import Entities.Bookmark;
import Entities.User;
import Manager.BookmarkManager;
import Manager.UserManager;

import java.io.File;

public class Launch {
    private static User[] users;
    private static Bookmark[][] bookmarks;
    private static void loadData(){
        System.out.println("1) Loading data . . .  ");
        DataStore.loadData();
        users = UserManager.getInstance().getUsers();
        bookmarks = BookmarkManager.getInstance().getBookmarks();

        //System.out.println("Printing data . . . ");
        //printUsers();
        //printBookmarks();
    }
    public static void main(String[] args) {
        loadData();
        startBookmarking();
        /*File file = new File(".");
        for(String fileNames : file.list()) System.out.println(fileNames);*/
    }

    private static void printUsers(){
        for(User user:users){
            System.out.println(user);
        }
        System.out.println();
    }

    private static void printBookmarks(){
        for(Bookmark[] bookType:bookmarks){
            for(Bookmark book:bookType){
                System.out.println(book);
            }
            System.out.println();
        }
    }

    public static void startBookmarking(){
        //System.out.println("2) Start bookmarking . . . ");
        for(User user:users){
            View.bookmark(user,bookmarks);
        }
    }
}
