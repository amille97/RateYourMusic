import java.util.HashSet;
import java.util.Scanner;

abstract class Music implements Ratable{
    
    //Stores title of object.
    private String title;

    //Stores artist who made the object.
    private String artist;

    //Stores year the object was made.
    private int year;

    //Stores tags associated with object.
    private HashSet<String> tags;

    //Stores rating for object.
    private Rating rating;


    public Music() {
        this.title = "";
        this.artist = "";
        this.year = -1;
        this.tags = new HashSet<>();
        this.rating = new Rating();
    }

    /**
     * @return Title of object.
     */
    public String getName() {
        return title;
    }

    /**
     * @param title New title of object.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return Artist of object.
     */
    public String getArtist() {
        return artist;
    }

    /**
     * @param artist New artist of object.
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * @return Year object was made.
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year New year object was made.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return Tags associated with object.
     */
    public String[] getTags() {
        String[] resultString = new String[tags.size()];
        int i = 0;
        for (String tag : tags) {
            resultString[i] = tag;
            i++;
        }

        return resultString;
    }

    /**
     * Removes a tag from object.
     * @param tag
     * @return True if successful.
     */
    public boolean removeTag(String tag) {
        return tags.remove(tag);
    }

    /**
     * Adds a tag to an object.
     * @param tag
     * @return True if successful.
     */
    public boolean addTag(String tag) {
        return tags.add(tag);
    }

    /**
     * @return Numberical rating for object.
     */
    @Override
    public int getRating() {
        return rating.getRating();
    }

    /**
     * @param value New numerical rating for object.
     */
    @Override
    public boolean setRating(int value) {
        rating.setRating(value);

        return (rating.getRating() == value) ? true : false;
    }

    /**
     * @return Review of object.
     */
    @Override
    public String getReview() {
        return rating.getReview();
    }
    
    /**
     * @param value New review for object.
     */
    @Override
    public boolean setReview(String value) {
        rating.setReview(value);

        return (rating.getReview().equals(value)) ? true : false;
    }


    /**
     * Asks the user for the title of an object. 
     * Sets title field.
     */
    public void queryTitle(Scanner scnr) {

        System.out.print("Title?\t");
        setTitle(scnr.nextLine());
        
    }

    /**
     * Asks the user for the artist of an object. 
     * Sets artist field.
     */
    public void queryArtist(Scanner scnr) {

        System.out.print("Artist?\t");
        setArtist(scnr.nextLine());

    }

    /**
     * Asks the user for the year of an object.
     * Sets year field.
     */
    public void queryYear(Scanner scnr) {

        System.out.print("Year?\t");
        String userInput = scnr.nextLine();

        while (!(userInput.matches("\\d+"))) {
            System.out.println("Invalid input. " + CLInterface.reprimands[(int) (Math.random() * 4)]);
            userInput = scnr.nextLine();
        }

        setYear(Integer.parseInt(userInput));
    }

    /**
     * Asks the user for any tags to add to an object.
     * Modifies tags field.
     */
    public void queryAddTags(Scanner scnr) {

        System.out.println("Add tags? Enter \"q\" to quit");
        String temp = scnr.nextLine();
        
        while (!temp.equals("q")) {
            
            if (!TagList.contains(temp)) {
                System.out.println("Tag has not been used before. Continue? (y/n)\t");
                if (scnr.nextLine().charAt(0) == 'y') {TagList.addTag(temp);}
                else {
                    System.out.println("Tag not added");
                    temp = scnr.nextLine();
                    continue;
                }
            }

            if (addTag(temp)) {
                System.out.println("Tag added");
            }
            else {
                System.out.println("Tag already present");
            }

            temp = scnr.nextLine();
        }

    }

    /**
     * Asks the user for any tags to remove from an object.
     * Modifies tags field.
     */
    public void queryRemoveTags(Scanner scnr) {

        System.out.println("Remove tags? Enter \"q\" to quit");
        String temp = scnr.nextLine();
        
        while (!temp.equals("q")) {
            if (removeTag(temp)) {
                System.out.println("Tag removed");
            }
            else {
                System.out.println("Tag not present");
            }

            temp = scnr.nextLine();
        } 
    }

    /**
     * Asks user for rating of object.
     */
    public void rateRating(Scanner scnr) {

        System.out.print("What is your rating?\t");
        String userInput = scnr.nextLine();
        
        while (!userInput.matches("\\d+")) {
            System.out.println("Invalid input. " + CLInterface.reprimands[(int) (Math.random() * 4)]);
            userInput = scnr.nextLine();
        }

        setRating(Integer.parseInt(userInput));
        
    }

    /**
     * Asks user for review of object.
     */
    public void rateReview(Scanner scnr) {

        System.out.println("Write your review below:");
        setReview(scnr.nextLine());
    }

    @Override
    public String toString() {
        String resultString = String.format("Title: %s\nArtist: %s\nYear: %d\n",
        getName(), getArtist(), getYear(), getTags());

        String[] Tags = getTags();

        resultString += "Tag(s):";
        for (String tag : Tags) {
            if (tag.equals(Tags[Tags.length-1])) {
                resultString += " " + tag;
            }
            else {
                resultString += " " + tag + ",";
            }
        }

        resultString += String.format("\nRating: %d\nReview: %s", getRating(), getReview());

        return resultString;
    }
}