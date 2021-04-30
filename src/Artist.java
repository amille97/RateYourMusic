import java.util.HashSet;
import java.util.Scanner;

class Artist implements Ratable{
    
    //Stores name of artist.
    private String name;

    //Stores tags associated with artist.
    private HashSet<String> tags;

    //Stores rating for artist.
    private Rating rating;

    /**
     * Constructor only takes name of artist.
     */
    public Artist(String name) {
        this.name = name;
        this.tags = new HashSet<>();
        this.rating = new Rating();
    }

    /**
     * @return Name of artist.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name New name for artist.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String Array of tags for artist.
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
     * Adds a tag to an artist.
     * @param tag
     * @return True if successful.
     */
    public boolean addTag(String tag) {
        return tags.add(tag);
    }

    /**
     * Removes a tag from artist.
     * @param tag
     * @return True if successful.
     */
    public boolean removeTag(String tag) {
        return tags.remove(tag);
    }

    /**
     * @return Numerical rating for artist.
     */
    @Override
    public int getRating() {
        return rating.getRating();
    }

    /**
     * @param value New numerical rating for artist.
     */
    @Override
    public boolean setRating(int value) {
        rating.setRating(value);

        return (rating.getRating() == value) ? true : false;
    }

    /**
     * @return Review for artist.
     */
    @Override
    public String getReview() {
        return rating.getReview();
    }

    /**
     * @param value New review for artist.
     */
    @Override
    public boolean setReview(String value) {
        rating.setReview(value);

        return (rating.getReview().equals(value)) ? true : false;
    }

    /**
     * Asks user to rate artist.
     */
    @Override
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
     * Asks user to review artist.
     */
    @Override
    public void rateReview(Scanner scnr) {

        System.out.println("Write your review below:");
        setReview(scnr.nextLine());
    }

    /**
     * Asks user for the name of the artist.
     * Modifies the name field.
     * @param scnr
     */
    public void queryName(Scanner scnr) {
        System.out.print("Name?\t");
        setName(scnr.nextLine());
    }


    /**
     * Asks the user for any tags to add to an artist.
     * Modifies the tags field.
     * @param scnr
     */
    public void queryAddTags(Scanner scnr) {
        System.out.println("Add tags? Enter \"q\" to quit");
        String userInput = scnr.nextLine();

        while (!userInput.equals("q")) {
            if (!TagList.contains(userInput)) {
                System.out.println("Tag has not been used before. Continue? (y/n)\t");
                if (scnr.nextLine().charAt(0) == 'y') {TagList.addTag(userInput);}
                else {
                    System.out.println("Tag not added");
                    userInput = scnr.nextLine();
                    continue;
                }
            }
            
            if (addTag(userInput)) {
                System.out.println("Tag added");
            }
            else {
                System.out.println("Tag already present");
            }

            userInput = scnr.nextLine();
        }
    }

    /**
     * Asks the user to to remove any tags associated with artist.
     * Modifies tags field.
     */
    public void queryRemoveTags(Scanner scnr) {
        System.out.println("Remove tags? Enter \"q\" to quit");
        String userInput = scnr.nextLine();

        while (!userInput.equals("q")) {
            if (removeTag(userInput)) {
                System.out.println("Tag removed");
            }
            else {
                System.out.println("Tag not present");
            }

            userInput = scnr.nextLine();
        }
    }

    /**
     * Creates a string representation of object for storage.
     */
    public String write() {
        String tagString = "";

        String[] tags = getTags();

        for (int i = 0; i < tags.length; i++) {
            if (i == tags.length-1) {
                tagString += tags[i];
            }
            else {
                tagString += tags[i] + "|";
            }
        }

        return String.format("Artist,%s,%s,%d,%s",
                            getName(), tagString,
                            getRating(), getReview());
    }

    @Override
    public String toString() {
        String resultString = String.format("Name: %s\n",
        getName(), getTags());

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