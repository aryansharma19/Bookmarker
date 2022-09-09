package Bookmarker;

import Entities.Bookmark;
import Entities.User;
import Manager.BookmarkManager;
import Manager.UserManager;
import bgJobs.WebpageDownloaderTask;

import java.io.File;
import java.util.List;

public class Launch {
    private static List<User> users;
    private static List<List<Bookmark>> bookmarks;
    private static void loadData(){
        System.out.println("1) Loading data . . .  ");
        DataStore.loadData();
        users = UserManager.getInstance().getUsers();
        bookmarks = BookmarkManager.getInstance().getBookmarks();

        //System.out.println("Printing data . . . ");
        printUsers();
        printBookmarks();
    }
    public static void main(String[] args) {
        loadData();
        startBookmarking();
        runDownloaderJob();
        /*File file = new File(".");
        for(String fileNames : file.list()) System.out.println(fileNames);*/
    }

    public static void runDownloaderJob(){
        WebpageDownloaderTask task = new WebpageDownloaderTask(true);
        new Thread(task).start();
    }

    private static void printUsers(){
        for(User user:users){
            System.out.println(user);
        }
        System.out.println();
    }

    private static void printBookmarks(){
        for(List<Bookmark> bookType:bookmarks){
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
