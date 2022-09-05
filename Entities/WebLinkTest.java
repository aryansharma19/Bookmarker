package Entities;

import Manager.BookmarkManager;

import static org.junit.jupiter.api.Assertions.*;

class WebLinkTest {

    @org.junit.jupiter.api.Test
    void isKidFriendlyEligible() {
        //Test1 - If porn is present in url then the method should return false
        WebLink testLink = BookmarkManager.getInstance().createWebLink(2000,"Taming Tiger, Part 2","http://www.javaworld.com/porn/2072759/core-java/taming-tiger--part-2.html","http://www.javaworld.com");
        boolean isKidFriendly = testLink.isKidFriendlyEligible();
        assertFalse(isKidFriendly, "if porn is present in url of the webLink, then the method should return false");
        //Test2 - If porn is present in title then the method should return false;
        testLink = BookmarkManager.getInstance().createWebLink(2000,"Taming porn, Part 2","http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html","http://www.javaworld.com");
        isKidFriendly = testLink.isKidFriendlyEligible();
        assertFalse(isKidFriendly,"if porn is present in the title then the method should return false");
        //Test3 - If adult is present in host,then the method should return false
        testLink = BookmarkManager.getInstance().createWebLink(2000,"Taming Tiger, Part 2","http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html","http://www.adult.com");
        isKidFriendly = testLink.isKidFriendlyEligible();
        assertFalse(isKidFriendly,"if adult is present in host of the weblink then the method should return false");
        //Test4 - If adult is present in url but not in host, then the method should return true
        testLink = BookmarkManager.getInstance().createWebLink(2000,"Taming Tiger, Part 2","http://www.javaworld.com/url/2072759/core-java/taming-tiger--part-2.html","http://www.javaworld.com");
        isKidFriendly = testLink.isKidFriendlyEligible();
        assertTrue(isKidFriendly,"if adult is only present in url then the method should return true");
        //Test5 - If adult is present only in title then the method should return true

        testLink = BookmarkManager.getInstance().createWebLink(2000,"adult Tiger, Part 2","http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html","http://www.javaworld.com");
        isKidFriendly = testLink.isKidFriendlyEligible();
        assertTrue(isKidFriendly,"if adult is only present in title then the method should return true");
    }
}