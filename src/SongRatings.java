import java.util.LinkedList;
import java.util.Scanner;
import java.util.ArrayList;

class SongRatings {
    
    /**
     * Stores song objects created by user.
     */
    private LinkedList<Song> songRatings;

    /**
     * Sets songRatings field to the value of the parameter.
     * @param songRatings
     */
    public SongRatings(LinkedList<Song> songRatings) {
        this.songRatings = songRatings;
    }

    /**
     * @return LinkedList of songs.
     */
    public ArrayList<Song> getSongRatings() {
        ArrayList<Song> resultList = new ArrayList<>();
        resultList.addAll(songRatings);
        
        return resultList;
    }

    /**
     * @param artist
     * @return A list of songs by a specified artist.
     */
    public ArrayList<Song> getSongsByArtist(Scanner scnr) {
        ArrayList<Song> resultList = new ArrayList<>();

        System.out.println("What artist would you like to see songs by?\n");
        String userInput = scnr.nextLine();

        for (Song s : songRatings) {
            if (s.getArtist().equals(userInput)) {
                resultList.add(s);
            }
        }

        return resultList;
    }

    /**
     * @param year
     * @return A list of songs released in a certain year.
     */
    public ArrayList<Song> getSongsByYear(Scanner scnr) {
        ArrayList<Song> resultList = new ArrayList<>();

        System.out.println("What year would you like to see songs from?");
        String userInput = scnr.nextLine();

        while (!userInput.matches("\\d+")) {
            System.out.println("Invalid Input. " + CLInterface.reprimands[(int) (Math.random() * 4)]);
            userInput = scnr.nextLine();
        }

        for (Song s : songRatings) {
            if (s.getYear() == Integer.parseInt(userInput)) {
                resultList.add(s);
            }
        }

        return resultList;
    }

    /**
     * @param tag
     * @return A list of songs associated with a certain tag.
     */
    public ArrayList<Song> getSongsByTag(Scanner scnr) {
        ArrayList<Song> resultList = new ArrayList<>();

        System.out.println("What tag would you like to see songs with?\nEnter \"?\" to show all tags in use.");
        String userInput = scnr.nextLine();
        
        while (userInput.equals("?")) {
            for (String s : TagList.getAllTags()) {
                System.out.println(s);
            }
            System.out.println();
            userInput = scnr.nextLine();
        }

        for (Song s : songRatings) {
            for (String t : s.getTags()) {
                if (t.equals(userInput)) {
                    resultList.add(s);
                    break;
                }
            }
        }

        return resultList;
    }

    /**
     * @param value
     * @return A list of songs based on their status as a non album single.
     */
    public ArrayList<Song> getSongsByAlbumStatus(Scanner scnr) {
        ArrayList<Song> resultList = new ArrayList<>();

        System.out.println("Would you like to see songs\n\n[1] Originally part of an album\n[2] Not originally part of an album\n");
        String userInput = scnr.nextLine();

        while (!userInput.equals("1") && !userInput.equals("2")) {
            System.out.println("Invalid Input. " + CLInterface.reprimands[(int) (Math.random() * 4)]);
            userInput = scnr.nextLine();
        }

        boolean response = userInput.equals("1");

        for (Song s : songRatings) {
            if (s.getNonAlbumSingle() == response) {
                resultList.add(s);
            } 
        }

        return resultList;
    }

    /**
     * @param low
     * @param high
     * @return A list of songs based on whether or not they fall between the high and low parameters, inclusive.
     */
    public ArrayList<Song> getSongsByRating(Scanner scnr) {
        ArrayList<Song> resultList = new ArrayList<>();

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

        for (Song s : songRatings) {
            int num = s.getRating();

            if ((low <= num) && (num <= high)) {
                resultList.add(s);
            }
        }

        return resultList;
    }
}