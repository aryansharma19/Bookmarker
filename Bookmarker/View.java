package Bookmarker;

import Constants.KidFriendlyStatus;
import Constants.UserType;
import Controller.BookmarkController;
import Entities.Bookmark;
import Entities.User;
import Partner.Shareable;

import java.util.List;

public class View {

    //Depicts the UI interface
    public static void bookmark(User user, List<List<Bookmark>> bookmarks){
        System.out.println("\n"+user.getEmail()+" is browsing items");
        int bookMarkCount = 0;

        for(List<Bookmark> bookmarkList :bookmarks){
            for(Bookmark bookmark: bookmarkList){
                boolean isBookmarked = getBookmarkDecision(bookmark);
                if (isBookmarked) {
                    bookMarkCount++;
                    BookmarkController.getInstance().saveUserBookmark(user, bookmark);
                    System.out.println("NEW ITEM BOOKMARKED -- " + bookmark);

                    //setting the kid friendly status
                    if(user.getUserType().equals(UserType.EDITOR) || user.getUserType().equals(UserType.CHIEF_EDITOR)){
                        if(bookmark.isKidFriendlyEligible() && bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)){
                            String kidFriendlyStatus = getKidFriendlyDecision(bookmark);
                            BookmarkController.getInstance().setKidFriendlyStatus(user,kidFriendlyStatus,bookmark);
                            //bookmark.setKidFriendlyStatus(kidFriendlyStatus);
                        }
                    }

                    //sharing kid friendly books with partner websites
                    if(bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.APPROVED) && bookmark instanceof Shareable){
                        boolean sharingDecision = getSharingDecision(bookmark);
                        if(sharingDecision){
                            BookmarkController.getInstance().share(user,bookmark);
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

    private static boolean getSharingDecision(Bookmark bookmark){
        return Math.random()<0.5;
    }
}
