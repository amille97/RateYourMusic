import java.util.Scanner;

class Song extends Music {
    
    private boolean nonAlbumSingle;

    public Song(String title) {
        super.setTitle(title);
    }

    /**
     * @return True if track is not originally from an album.
     */
    public boolean getNonAlbumSingle() {
        return nonAlbumSingle;
    }

    /**
     * @param nonAlbumSingle True or false whether track is a part of an album orginally.
     */
    public void setNonAlbumSingle(boolean nonAlbumSingle) {
        this.nonAlbumSingle = nonAlbumSingle;
    }

    /**
     * Asks the user whether the song is a non album single.
     * Modified corresponding field.
     */
    public void queryNonAlbumSingle(Scanner scnr) {

        System.out.print("Is this song originally part of an album? (t/f)\t");
        setNonAlbumSingle( (scnr.nextLine().charAt(0) == 't') ? true : false);

    }

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

        return String.format("Song,%s,%s,%d,%s,%b,%d,%s",
                            getName(), getArtist(),
                            getYear(), tagString,
                            getNonAlbumSingle(),
                            getRating(), getReview());
    }

    /**
     * @return String implementation of song object.
     */
    @Override
    public String toString() {
        String resultString = super.toString() + "\n";

        resultString += "Originally part of an album: " + getNonAlbumSingle();

        return resultString;
    }
}