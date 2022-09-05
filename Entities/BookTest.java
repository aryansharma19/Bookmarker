package Entities;

import Constants.BookGenre;
import Manager.BookmarkManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void isKidFriendlyEligible() {

        //Test1 -- If the genre of the book is philosophy then the method should return false
        Book testBook = BookmarkManager.getInstance().createBook(4000,"Walden",1854,"Wilder Publications",new String[]{"Wilder Publications"}, BookGenre.PHILOSOPHY,4.3);
        boolean isKidFriendly = testBook.isKidFriendlyEligible();
        assertFalse(isKidFriendly,"If the genre of the book is philosophy then the method should return false");

        //Test2 -- If the genre of the book is self-help then the method should return false
        testBook = BookmarkManager.getInstance().createBook(4000,"Walden",1854,"Wilder Publications",new String[]{"Wilder Publications"}, BookGenre.SELF_HELP,4.3);
        isKidFriendly = testBook.isKidFriendlyEligible();
        assertFalse(isKidFriendly,"If the genre of the book is self help then the method should return false");
    }
}