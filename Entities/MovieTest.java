package Entities;

import Constants.MovieGenre;
import Manager.BookmarkManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    void isKidFriendlyEligible() {
        //Test1 -- if the movie genre is horror then the method should return false
        Movie testMovie = BookmarkManager.getInstance().createMovie(3000,"Citizen Kane",1941,new String[]{"Orson Welles","Joseph Cotten"},new String[]{"Orson Welles"}, MovieGenre.HORROR,8.5);
        boolean isKidFriendly = testMovie.isKidFriendlyEligible();
        assertFalse(isKidFriendly,"If the movie genre is horror then the method should return false");

        //Test2 -- if the movie genre is thriller then the method should return false
        testMovie = BookmarkManager.getInstance().createMovie(3000,"Citizen Kane",1941,new String[]{"Orson Welles","Joseph Cotten"},new String[]{"Orson Welles"}, MovieGenre.THRILLERS,8.5);
        isKidFriendly = testMovie.isKidFriendlyEligible();
        assertFalse(isKidFriendly,"If the genre of the movie is thriller then the method should return false");
    }
}