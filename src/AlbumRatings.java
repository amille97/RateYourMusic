import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Scanner;

class AlbumRatings {
    
    /**
     * Stores all album objects created by user.
     */
    private LinkedList<Album> albumRatings;

    /**
     * Sets albumRatings field to the value of the parameter.
     * @param albumRatings
     */
    public AlbumRatings(LinkedList<Album> albumRatings) {
        this.albumRatings = albumRatings;
    }

    /**
     * @return LinkedList of albums.
     */
    public ArrayList<Album> getAlbumRatings() {
        ArrayList<Album> resultList = new ArrayList<>();

        resultList.addAll(albumRatings);

        return resultList;
    }

    /**
     * @param artist
     * @return A list of albums from a specified artist.
     */
    public ArrayList<Album> getAlbumsByArtist(Scanner scnr) {
        ArrayList<Album> resultList = new ArrayList<>();

        System.out.println("What artist would you like to see albums from?\n");
        String userInput = scnr.nextLine();

        for (Album a : albumRatings) {
            if (a.getArtist().equals(userInput)) {
                resultList.add(a);
            }
        }

        return resultList;
    }

    /**
     * @param year
     * @return A list of albums made in a specific year.
     */
    public ArrayList<Album> getAlbumsbyYear(Scanner scnr) {
        ArrayList<Album> resultList = new ArrayList<>();

        System.out.println("What year would you like to see albums from?\n");
        String userInput = scnr.nextLine();

        while (!userInput.matches("\\d+")) {
            System.out.println("Invalid Input. " + CLInterface.reprimands[(int) (Math.random() * 4)]);
            userInput = scnr.nextLine();    
        }

        for (Album a : albumRatings) {
            if (a.getYear() == Integer.parseInt(userInput)) {
                resultList.add(a);
            }
        }

        return resultList;
    }

    /**
     * @param tag
     * @return A list of albums associated with a certain tag. Null if tag does not exist.
     */
    public ArrayList<Album> getAlbumsByTag(Scanner scnr) {
        ArrayList<Album> resultList = new ArrayList<>();

        System.out.println("What tag would you like to see albums with?\nEnter \"?\" to show all tags in use.");
        String userInput = scnr.nextLine();
        
        while (userInput.equals("?")) {
            for (String s : TagList.getAllTags()) {
                System.out.println(s);
            }
            System.out.println();
            userInput = scnr.nextLine();
        }

        for (Album a : albumRatings) {
            for (String t : a.getTags()) {
                if (t.equals(userInput)) {
                    resultList.add(a);
                    break;
                }
            }
        }
        return resultList;
    }

    /**
     * @param low
     * @param high
     * @return A list of albums with ratings that fall between the low and high values, inclusive.
     */
    public ArrayList<Album> getAlbumsByRating(Scanner scnr) {
        ArrayList<Album> resultList = new ArrayList<>();
        int low, high;

        System.out.println("What is the lowest (inclusive) rating you want to see");
        String userInput = scnr.nextLine();

        while (!userInput.matches("\\d+")) {
            System.out.println("Invalid Input. " + CLInterface.reprimands[(int) (Math.random() * 4)]);
            userInput = scnr.nextLine();
        }
        low = Integer.parseInt(userInput);

        System.out.println("What is the highest (inclusive) rating you want to see?");
        userInput = scnr.nextLine();

        while (!userInput.matches("\\d+")) {
            System.out.println("Invalid Input. " + CLInterface.reprimands[(int) (Math.random() * 4)]);
            userInput = scnr.nextLine();
        }
        high = Integer.parseInt(userInput);

        for (Album a : albumRatings) {
            int num = a.getRating();

            if ((low <= num) && (num <= high)) {
                resultList.add(a);
            }
        }

        return resultList;
    }
}