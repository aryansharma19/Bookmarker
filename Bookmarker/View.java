package Bookmarker;

import Constants.KidFriendlyStatus;
import Constants.UserType;
import Controller.BookmarkController;
import Entities.Bookmark;
import Entities.User;

public class View {

    //Depicts the UI interface

    public static void bookmark(User user, Bookmark[][] bookmarks){
        System.out.println("\n"+user.getEmail()+" is bookmarking ");

        for(int i =0;i<DataStore.USER_LIMIT;i++){
            int typeOffSet = (int)(Math.random() * DataStore.BOOKMARK_TYPES_COUNT);
            int bookmarkOffSet = (int)(Math.random() * DataStore.BOOKMARK_COUNT_PER_TYPE);
            Bookmark bookmark = bookmarks[typeOffSet][bookmarkOffSet];

            BookmarkController.getInstance().saveUserBookmark(user,bookmark);
            System.out.println(bookmark);
        }
    }

    public static void bookmark1(User user,Bookmark[][] bookmarks){
        System.out.println("\n"+user.getEmail()+" is browsing items");
        int bookMarkCount = 0;

        for(Bookmark[] bookmarkList :bookmarks){
            for(Bookmark bookmark: bookmarkList){
                if(bookMarkCount< DataStore.USER_BOOKMARK_LIMIT){
                    boolean isBookmarked = getBookmarkDecision(bookmark);
                    if(isBookmarked){
                        bookMarkCount++;
                        BookmarkController.getInstance().saveUserBookmark(user,bookmark);
                    }
                    if(user.getUserType().equals(UserType.EDITOR) || user.getUserType().equals(UserType.CHIEF_EDITOR)){
                        if(bookmark.isKidFriendlyEligible() && bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)){
                            String kidFriendlyStatus = getKidFriendlyDecision(bookmark);
                            bookmark.setKidFriendlyStatus(kidFriendlyStatus);
                            System.out.println("kid-friendly status : "+kidFriendlyStatus+" , "+ bookmark);
                        }
                    }
                }
            }
        }
    }

    private static boolean getBookmarkDecision(Bookmark bookmark){
        return Math.random() < 0.5 ? true : false;
    }

    private static String getKidFriendlyDecision(Bookmark bookmark){
        double random = Math.random();
        return random < 0.4 ? KidFriendlyStatus.APPROVED :((random >= 0.4 && random < 0.8)? KidFriendlyStatus.REJECTED:KidFriendlyStatus.UNKNOWN);
    }
}
