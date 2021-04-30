import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;


class Ratings {
    
    /**
     * List of all Albums / Songs / Artists user made.
     */
    private LinkedList<Ratable> ratings;

    /**
     * Constructor just instantiates ratings.
     */
    public Ratings() {
        this.ratings = new LinkedList<>();
    }

    public Ratings(String fileName) throws IOException{
        this.ratings = new LinkedList<>();
        File ratingsFile = new File(fileName);
        Scanner scnr = new Scanner(ratingsFile);
        String[] itemAttributes;

        while (scnr.hasNextLine()) {
            itemAttributes = scnr.nextLine().split(",");

            if (itemAttributes[0].equals("Album")) {
                Album a = new Album(itemAttributes[1]);
                a.setArtist(itemAttributes[2]);
                a.setYear(Integer.parseInt(itemAttributes[3]));

                String[] tags = itemAttributes[4].split("\\|");

                
                for (String tag : tags) {
                    a.addTag(tag);
                    TagList.addTag(tag);
                }
                
                String[] songs = itemAttributes[5].split("\\|");
                for (String song : songs) {
                    a.addSong(song);
                }

                a.setRating(Integer.parseInt(itemAttributes[6]));

                String reviewConcat = "";
                for (int i = 7; i < itemAttributes.length; i++) {
                    if (i == itemAttributes.length - 1) {
                        reviewConcat += itemAttributes[i];
                    }
                    else {
                        reviewConcat += itemAttributes[i] + ",";
                    } 
                }
                a.setReview(reviewConcat);

                ratings.add(a);
            }

            else if (itemAttributes[0].equals("Song")) {
                Song s = new Song(itemAttributes[1]);
                s.setArtist(itemAttributes[2]);
                s.setYear(Integer.parseInt(itemAttributes[3]));

                String[] tags = itemAttributes[4].split("\\|");
                for (String tag : tags) {
                    s.addTag(tag);
                    TagList.addTag(tag);
                }
                
                s.setNonAlbumSingle(Boolean.parseBoolean(itemAttributes[5]));
                s.setRating(Integer.parseInt(itemAttributes[6]));

                String reviewConcat = "";
                for (int i = 7; i < itemAttributes.length; i++) {
                    if (i == itemAttributes.length - 1) {
                        reviewConcat += itemAttributes[i];
                    }
                    else {
                        reviewConcat += itemAttributes[i] + ",";
                    } 
                }
                s.setReview(reviewConcat);

                ratings.add(s);
            }
            
            else if (itemAttributes[0].equals("Artist")) {
                Artist a2 = new Artist(itemAttributes[1]);

                String[] tags = itemAttributes[2].split("\\|");
                for (String tag : tags) {
                    a2.addTag(tag);
                    TagList.addTag(tag);
                }

                a2.setRating(Integer.parseInt(itemAttributes[3]));
                String reviewConcat = "";
                for (int i = 7; i < itemAttributes.length; i++) {
                    if (i == itemAttributes.length - 1) {
                        reviewConcat += itemAttributes[i];
                    }
                    else {
                        reviewConcat += itemAttributes[i] + ",";
                    } 
                }

                a2.setReview(reviewConcat);
                ratings.add(a2);
            }
        }

        scnr.close();
    }



    /**
     * Adds item to list. Items are sorted by rating upon being added.
     * @param item Ratable item to add.
     */
    public void add(Ratable item) {
        boolean added = false;

        if (ratings.isEmpty()) {
            ratings.add(item);
        }

        else {
            int i = 0;
            for (Ratable r : ratings) {
                if (item.getRating() > r.getRating()) {
                    added = true;
                    break;
                }
                i++;
            }

            if (!added) {ratings.addLast(item);}
            else {ratings.add(i, item);;}
        }
    }

    /**
     * Creates AlbumRatings object from albums user has created in order of their rating.
     * @return
     */
    public AlbumRatings getAlbumRatings() {
        LinkedList<Album> albumRatings = new LinkedList<>();

        for (Ratable r : ratings) {
            if (r instanceof Album) {
                albumRatings.addLast((Album) r);
            }
        }

        return new AlbumRatings(albumRatings);

    }

    /**
     * Creates a SongRatings object out of songs user has created in order of their rating.
     * @return
     */
    public SongRatings getSongRatings() {
        LinkedList<Song> songRatings = new LinkedList<>();

        for (Ratable r : ratings) {
            if (r instanceof Song) {
                songRatings.addLast((Song) r);
            }
        }

        return new SongRatings(songRatings);
    }

    /**
     * Creates a ArtistRatings object from Artists the user has created in order of their rating.
     * @return
     */
    public ArtistRatings getArtistRatings() {
        LinkedList<Artist> artistRatings = new LinkedList<>();

        for (Ratable r : ratings) {
            if (r instanceof Artist) {
                artistRatings.addLast((Artist) r);
            }
        }

        return new ArtistRatings(artistRatings);
    }

    public ArrayList<Ratable> getRatings() {
        ArrayList<Ratable> list = new ArrayList<>();
        list.addAll(ratings);
        return list;
    }
}