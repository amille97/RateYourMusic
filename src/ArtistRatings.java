import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Scanner;

class ArtistRatings {
    
    /**
     * Stores artist objects created by user.
     */
    private LinkedList<Artist> artistRatings;

    /**
     * Sets artistRatings field to the value of the parameter.
     * @param artistRatings
     */
    public ArtistRatings(LinkedList<Artist> artistRatings) {
        this.artistRatings = artistRatings;
    }

    /**
     * @return List of all artists.
     */
    public ArrayList<Artist> getArtistRatings() {
        ArrayList<Artist> resultList = new ArrayList<>();
        resultList.addAll(artistRatings);

        return resultList;
    }

    /**
     * @param tag
     * @return A list of artist associated with a certain tag.
     */
    public ArrayList<Artist> getArtistsByTag(Scanner scnr) {
        ArrayList<Artist> resultList = new ArrayList<>();
        
        System.out.println("What tag would you like to see artists with?\nEnter \"?\" to show all tags in use.");
        String userInput = scnr.nextLine();
        
        while (userInput.equals("?")) {
            for (String s : TagList.getAllTags()) {
                System.out.println(s);
            }
            System.out.println();
            userInput = scnr.nextLine();
        }

        for (Artist a2 : artistRatings) {
            for (String t : a2.getTags()) {
                if (t.equals(userInput)) {
                    resultList.add(a2);
                    break;
                }
            }
        }

        return resultList;
    }

    /**
     * @param low
     * @param high
     * @return A list of artists whose ratings fall between the high and low parameters, inclusive.
     */
    public ArrayList<Artist> getArtistsByRating(Scanner scnr) {
        ArrayList<Artist> resultList = new ArrayList<>();

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

        for (Artist a2 : artistRatings) {
            int num = a2.getRating();

            if ((low <= num) && (num <= high)) {
                resultList.add(a2);
            }
        }

        return resultList;
    }

}