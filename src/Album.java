import java.util.HashSet;
import java.util.Scanner;

class Album extends Music {

    //Stores songs in album
    private HashSet<String> songSet;

    /**
     * Constructor for album object, only requiring a title.
     * @param title
     */
    public Album(String title) {
        super();
        super.setTitle(title);
        songSet = new HashSet<>();
    }

    /**
     * @return List of songs in album.
     */
    public String[] getsongSet() {
        String[] songList = new String[songSet.size()];

        int i = 0;
        for (String song : songSet) {
            songList[i] = song;
            i++;
        }

        return songList;
    }

    /**
     * Adds a song to the songs on the album.
     * @param S 
     */
    public boolean addSong(String S) {
        return songSet.add(S);
    }

    public boolean removeSong(String S) {
        return songSet.remove(S);
    }

    public void queryAddSongs(Scanner scnr) {

        System.out.println("Add songs to album? Enter \"q\" to quit");
        String temp = scnr.nextLine();

        while (!temp.equals("q")) {
            if (addSong(temp)) {
                System.out.println("Song added successfully");
            }
            else {
                System.out.println("Song already in album");
            }

            temp = scnr.nextLine();
        }
    }

    public void queryRemoveSongs(Scanner scnr) {

        System.out.println("Remove songs from album? Enter \"q\" to quit");
        String temp = scnr.nextLine();

        while (!temp.equals("q")) {
            if (addSong(temp)) {
                System.out.println("Song removed successfully");
            }
            else {
                System.out.println("Song not present in album");
            }

            temp = scnr.nextLine();
        }
    }

    public String write() {
        String tagString = "";
        String songString = "";

        String[] tags = getTags();
        String[] songs = getsongSet();

        for (int i = 0; i < tags.length; i++) {
            if (i == tags.length-1) {
                tagString += tags[i];
            }
            else {
                tagString += tags[i] + "|";
            }
        }

        for (int i = 0; i < songs.length; i++) {
            if (i == songs.length-1) {
                tagString += songs[i];
            }
            else {
                tagString += songs[i] + "|";
            }
        }

        return String.format("Album,%s,%s,%d,%s,%s,%d,%s",
                            getName(), getArtist(),
                            getYear(), tagString,
                            songString, getRating(),
                            getReview());
    }

    @Override
    public String toString() {
        String resultString = super.toString();

        String[] Songs = getsongSet();
        
        resultString += "\nSongs:";
        for (String song : Songs) {
            if (song.equals(Songs[Songs.length-1])) {
                resultString += " " + song + "\n";
            }
            else {
                resultString += " " + song + ",";
            }
        }


        return resultString;
    }
}