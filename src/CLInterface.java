import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

class CLInterface { //For my implementation of the CLI, each submenu is a different method.

    /**
     * List of greetings to give the user.
     */
    final static String[] greetings = {"Hello Human, how are you today?", "Your wish is my command, Human", 
                              "Listen to any good songs lately Human?", "What would you like to do today Human?"};

    /**
     * List of reprimenads to give the user when they navigate the menu wrong.
     */
    final  static String[] reprimands = {"Why have you done this?", "You are testing my patience, Human.",
                                "You have options for a reason.", "You have disobeyed me for the last time, Human."};

    /**
     * Temporarily stores albums before exporting them.
     */
    private ArrayList<Album> ALbuffer;

    /**
     * Temporarily stores songs before exporting them.
     */
    private ArrayList<Song> Sbuffer;

    /**
     * Temporarily stores artists before exporting them.
     */
    private ArrayList<Artist> ARbuffer;

    /**
     * Stores Items the user creates in an instance.
     */
    private Ratings ratings;

    private CLInterface() {
        ALbuffer = new ArrayList<>();
        Sbuffer = new ArrayList<>();
        ARbuffer = new ArrayList<>();
        ratings = new Ratings();
    }

    private CLInterface(String fileName) throws IOException{
        ALbuffer = new ArrayList<>();
        Sbuffer = new ArrayList<>();
        ARbuffer = new ArrayList<>();
        ratings = new Ratings(fileName);

    }

    /**
     * Submenu for selecting a new item to make.
     * @see #main
     * @param scnr
     */
    public void addItem(Scanner scnr) {
        while (true) {
            System.out.println("[1] Add New Albums\n[2] Add New Songs\n[3] Add New Artists\n[4] Back\n");
            String userInput = scnr.nextLine();
            while (!userInput.matches("[1-4]")) {
                System.out.println("Invalid input. " + reprimands[(int) (Math.random() * 4)]);
                userInput = scnr.nextLine();
            }

            ALbuffer.clear();
            if      (userInput.equals("1")) {addAlbum(scnr);}
            else if (userInput.equals("2")) {addSong(scnr);}
            else if (userInput.equals("3")) {addArtist(scnr);}
            else {return;}
        }
    }

    /**
     * Submenu for creating / editing albums in buffer.
     * @see #addItem
     * @param scnr
     */
    public void addAlbum(Scanner scnr) {
        
        System.out.println("Albums added in this instance will appear here.\nChoose save to export these albums to the list.");
        while (true) {
            System.out.println("[1] Create New Album\n[2] Remove Album");
            for (int i = 0; i < ALbuffer.size(); i++) {
                System.out.println(String.format("[%d] %s", i+3, ALbuffer.get(i).getName()));
            }
            System.out.println();
            System.out.println("[s] Save");
            System.out.println("[q] Back\n");
            String userInput = scnr.nextLine();

            while   ((!userInput.matches("\\d+") && 
                    (!userInput.equals("s")) && 
                    (!userInput.equals("q")))) {
                System.out.println("Invalid input. " + reprimands[(int) (Math.random() * 4)]);
                userInput = scnr.nextLine();
            }

            if (userInput.matches("\\d+") && (!userInput.matches("[1-2]")) ) {
                while (true) {
                    try {
                        ALbuffer.get(Integer.parseInt(userInput)-3);
                        break;
                    }
                    catch (IndexOutOfBoundsException e) {
                        System.out.println("Not a valid number." + reprimands[(int) (Math.random() * 4)]);
                        userInput = scnr.nextLine();
                    }
                }
            }

            if (userInput.equals("1")) {createAlbum(scnr);}
            else if (userInput.equals("2")) {removeItem(scnr, ALbuffer);}
            else if (userInput.equals("s")) {save(ALbuffer);}
            else if (userInput.equals("q")) {break;}
            else {editAlbum(scnr, ALbuffer.get(Integer.parseInt(userInput)-3));}
        }
    }

    /**
     * Walkthrough to create a new album. Only edits title.
     * @see #addAlbum
     * @param scnr
     */
    public void createAlbum(Scanner scnr) {
        String userInput;
        System.out.print("Title of Album?\t");
        userInput = scnr.nextLine();
        Album a = new Album(userInput);
        this.ALbuffer.add(a);
    }

    /**
     * Submenu to edit the attributes of an album.
     * @see #addAlbum
     * @see #viewAlbums
     * @param scnr
     * @param a Album object to edit.
     */
    public void editAlbum(Scanner scnr, Album a) {
        while (true) {
            System.out.println(a);
            System.out.println("\nWhat attributes would you like to edit?");
            System.out.println("[1] Title\n[2] Artist\n[3] Year\n[4] Tags\n[5] Songlist\n[6] Rating\n[7] Back\n");

            String userInput = scnr.nextLine();

            while (!userInput.matches("[1-8]")) {
                System.out.println("Invalid input. " + reprimands[(int) (Math.random() * 4)]);
                userInput = scnr.nextLine();
            }

            if      (userInput.equals("1")) {a.queryTitle(scnr);}
            else if (userInput.equals("2")) {a.queryArtist(scnr);}
            else if (userInput.equals("3")) {a.queryYear(scnr);}
            else if (userInput.equals("4")) {modifyTags(scnr, a);}
            else if (userInput.equals("5")) {modifySonglist(scnr, a);}
            else if (userInput.equals("6")) {modifyRating(scnr, a);}
            else {return;}
        }
    }

    /**
     * Submenu to modify an album's song list.
     * @see #editAlbum
     * @param scnr
     * @param a
     */
    public void modifySonglist(Scanner scnr, Album a) {
        while (true) {
            System.out.println("[1] Add Songs\n[2] Remove Songs\n[3] Back");
            String userInput = scnr.nextLine();

            while (!userInput.matches("[1-3]")) {
                System.out.println("Invalid input. " + reprimands[(int) (Math.random() * 4)]);
                userInput = scnr.nextLine();
            }

            if (userInput.equals("1")) {a.queryAddSongs(scnr);}
            else if (userInput.equals("2")) {a.queryRemoveSongs(scnr);}
            else {return;}
        }
    }

    /**
     * Submenu for creating / editing new songs in buffer.
     * @see #addItem
     * @param scnr
     */
    public void addSong(Scanner scnr) {
        System.out.println("Songs added in this instance will appear here.\nChoose save to export these albums to the list.");

        while (true) {
            System.out.println("[1] New Song\n[2] Remove Song");
            for (int i = 0; i < Sbuffer.size(); i++) {
                System.out.println(String.format("[%d] %s", i+3, Sbuffer.get(i).getName()));
            }
            System.out.println();
            System.out.println("[s] Save\n[q] Back\n");
            String userInput = scnr.nextLine();

            while (!(userInput.matches("\\d+")) && !(userInput.equals("s")) && !(userInput.equals("q"))) {
                System.out.println("Invalid input. " + reprimands[(int) (Math.random() * 4)]);
                userInput = scnr.nextLine();
            }

            if (userInput.matches("\\d+") && (!userInput.matches("[1-2]")) ){
                while (true) {
                    try {
                        Sbuffer.get(Integer.parseInt(userInput)-3);
                        break;
                    }
                    catch (IndexOutOfBoundsException e ) {
                        System.out.println("Invalid input. " + reprimands[(int) (Math.random() * 4)]);
                        userInput = scnr.nextLine();
                    }
                }
            }

            if (userInput.equals("1")) {createSong(scnr);}
            else if (userInput.equals("2")) {removeItem(scnr, Sbuffer);}
            else if (userInput.equals("s")) {save(Sbuffer);}
            else if (userInput.equals("q")) {return;}
            else {editSong(scnr, Sbuffer.get(Integer.parseInt(userInput)-3));}
            
        }
    }

    /**
     * Walkthrough to create a new song.
     * @see #addSong
     * @param scnr
     */
    public void createSong(Scanner scnr) {
        System.out.print("Title of song?\t");
        Song s = new Song(scnr.nextLine());
        this.Sbuffer.add(s);
    }

    /**
     * Submenu for editing a song item.
     * @see #addSong
     * @see #viewSongs
     * @param scnr
     * @param s Song object to edit.
     */
    public void editSong(Scanner scnr, Song s) {
        while (true) {
            System.out.println(s);
            System.out.println("\nWhat attributes would you like to edit?");
            System.out.println("[1] Title\n[2] Artist\n[3] Year\n[4] Tags\n[5] Album Single\n[6] Rating\n[7] Back\n");
            String userInput = scnr.nextLine();

            while (!userInput.matches("[1-7]")) {
                System.out.println("Invalid input. " + reprimands[(int) (Math.random() * 4)]);
                userInput = scnr.nextLine();
            }

            if      (userInput.equals("1")) {s.queryTitle(scnr);}
            else if (userInput.equals("2")) {s.queryArtist(scnr);}
            else if (userInput.equals("3")) {s.queryYear(scnr);}
            else if (userInput.equals("4")) {modifyTags(scnr, s);}
            else if (userInput.equals("5")) {s.queryNonAlbumSingle(scnr);}
            else if (userInput.equals("6")) {modifyRating(scnr, s);}
            else {return;}
        }
    }


    /**
     * Submenu for adding / editing Artists.
     * @see #addItem
     */
    public void addArtist(Scanner scnr) {
        System.out.println("Artists added in this instance will appear here.\nChoose save to export these albums to the list.");

        while (true) {
            System.out.println("[1] Add Artist\n[2] Remove Artist");
            for (int i = 0; i < ARbuffer.size(); i++) {
                System.out.println(String.format("[%d] %s", i+3, ARbuffer.get(i).getName()));
            }
            System.out.println();
            System.out.println("[s] Save\n[q] Back");
            String userInput = scnr.nextLine();

            while (!userInput.matches("\\d+") && !userInput.equals("s") && !userInput.equals("q")) {
                System.out.println("Invalid input. " + reprimands[(int) (Math.random() * 4)]);
                userInput = scnr.nextLine();
            }

            if (userInput.matches("\\d+") && (!userInput.matches("[1-2]"))) {
                while (true) {
                    try {
                        ARbuffer.get(Integer.parseInt(userInput)-3);
                        break;
                    }
                    catch (IndexOutOfBoundsException e ) {
                        System.out.println("Invalid input. " + reprimands[(int) (Math.random() * 4)]);
                        userInput = scnr.nextLine();
                    }
                }
            }

            if (userInput.equals("1")) {createArtist(scnr);}
            else if (userInput.equals("2")) {removeItem(scnr, ARbuffer);}
            else if (userInput.equals("s")) {save(ARbuffer);}
            else if (userInput.equals("q")) {return;}
            else {editArtist(scnr, ARbuffer.get(Integer.parseInt(userInput)-3));}
        }
    }

    /**
     * Walkthrough to create new artist.
     * @param scnr
     */
    public void createArtist(Scanner scnr) {
        System.out.print("Artist Name?\t");
        Artist a2 = new Artist(scnr.nextLine());
        ARbuffer.add(a2);
    }

    /**
     * Submenu to edit an artist object.
     * @see #addArtist
     * @see #viewArtists
     * @param scnr
     * @param a2 Artist object to edit.
     */
    public void editArtist(Scanner scnr, Artist a2) {
        while (true) {
            System.out.println(a2);
            System.out.println("\nWhat attributes would you like to edit?");
            System.out.println("[1] Name\n[2] Tags\n[3] Rating\n[4] Back\n");
            String userInput = scnr.nextLine();

            while (!userInput.matches("[1-4]")) {
                System.out.println("Invalid input. " + reprimands[(int) (Math.random() * 4)]);
                userInput = scnr.nextLine();
            }

            if      (userInput.equals("1")) {a2.queryName(scnr);}
            else if (userInput.equals("2")) {modifyTags(scnr, a2);}
            else if (userInput.equals("3")) {modifyRating(scnr, a2);}
            else {return;}
        }
    }

    /**
     * Submenu to modify an object's tags.
     * @see #editAlbum
     * @see #editSong
     * @see #editArtist
     * @param scnr
     * @param Item
     */
    public void modifyTags(Scanner scnr, Object Item) {
        while (true) {
            if (Item instanceof Album) {
                Album a = (Album) Item;

                System.out.println("[1] Add tags\n[2] Remove tags\n[3] Back\nEnter \"?\" to show all tags on item.");
                String userInput = scnr.nextLine();

                while (!userInput.matches("[1-3]")) {
                    if (userInput.equals("?")) {
                        for (String tag : a.getTags()) {
                            System.out.println(tag);
                        }
                        userInput = scnr.nextLine();
                        continue;
                    }

                    System.out.println("Invalid input. " + reprimands[(int) (Math.random() * 4)]);
                    userInput = scnr.nextLine();
                }

                if (userInput.equals("1")) {a.queryAddTags(scnr);}
                else if (userInput.equals("2")) {a.queryRemoveTags(scnr);}
                else {return;}
            }
            else if (Item instanceof Song) {
                Song s = (Song) Item;

                System.out.println("[1] Add tags\n[2] Remove tags\n[3] Back\nEnter \"?\" to show all tags on item.");
                String userInput = scnr.nextLine();

                while (!userInput.matches("[1-3]")) {
                    if (userInput.equals("?")) {
                        for (String tag : s.getTags()) {
                            System.out.println(tag);
                        }
                        userInput = scnr.nextLine();
                        continue;
                    }

                    System.out.println("Invalid input. " + reprimands[(int) (Math.random() * 4)]);
                    userInput = scnr.nextLine();
                }


                if (userInput.equals("1")) {s.queryAddTags(scnr);}
                else if (userInput.equals("2")) {s.queryRemoveTags(scnr);}
                else {return;}
            }
            else if (Item instanceof Artist) {
                Artist a2 = (Artist) Item;

                System.out.println("[1] Add tags\n[2] Remove tags\n[3] Back\nEnter \"?\" to show all tags on item.");
                String userInput = scnr.nextLine();

                while (!userInput.matches("[1-3]")) {
                    if (userInput.equals("?")) {
                        for (String tag : a2.getTags()) {
                            System.out.println(tag);
                        }
                        userInput = scnr.nextLine();
                        continue;
                    }

                    System.out.println("Invalid input. " + reprimands[(int) (Math.random() * 4)]);
                    userInput = scnr.nextLine();
                }

                if (userInput.equals("1")) {a2.queryAddTags(scnr);}
                else if (userInput.equals("2")) {a2.queryRemoveTags(scnr);}
                else {return;}
            }
        }
    }

    /**
     * Submenu to modify the ratings for an object.
     * @see #editAlbum
     * @see #editSong
     * @see #editArtist
     * @param scnr
     * @param Item
     */
    public void modifyRating(Scanner scnr, Ratable Item) {

        while (true) {
            System.out.println("[1] Rating\n[2] Review\n[3] Back\n");
            
            String userInput = scnr.nextLine();

            while (!(userInput.equals("1")) && !(userInput.equals("2")) && !(userInput.equals("3"))) {
                System.out.println("Invalid input. " + reprimands[(int) (Math.random() * 4)]);
                userInput = scnr.nextLine();
            }

            if (userInput.equals("1")) {Item.rateRating(scnr);}
            else if (userInput.equals("2")) {Item.rateReview(scnr);}
            else {return;}
        }
    }

    /**
     * Submenu to remove an item from the passed in buffer list.
     */
    public void removeItem(Scanner scnr, ArrayList<? extends Ratable> buffer) {

        System.out.println("What item would you like to remove?");
        for (int i = 0; i < buffer.size(); i++) {
            System.out.println(String.format("[%d] %s", i+1, buffer.get(i).getName()));
        }
        System.out.println();
        String userInput = scnr.nextLine();

        while (true) {
            try{
                int temp = Integer.parseInt(userInput);
                buffer.get(temp-1);
                break;
            }
            catch (RuntimeException e) {
                System.out.println("Invalid input. " + reprimands[(int) (Math.random() * 4)]);
                userInput = scnr.nextLine();    
            }
        }

        buffer.remove(Integer.parseInt(userInput)-1);
    }

    /**
     * Adds all elements of the passed in buffer to ratings.
     * @see #addAlbum
     * @see #addSong
     * @see #addArtist
     */
    public void save(ArrayList<? extends Ratable> buffer) {
        for (Ratable item : buffer) {
            ratings.add(item);
        }

        buffer.clear();
        System.out.println("Items Saved");
    }

    
    /**
     * Submenu for selecting a category of items to view.
     * @param scnr
     */
    public void viewItems(Scanner scnr) {
        System.out.println("Choose a category to view:");
        while(true) {
            System.out.println("[1] View Albums\n[2] View Songs\n[3] View Artists\n[4] Back\n");
            String userInput = scnr.nextLine();

            while (!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3") && !userInput.equals("4")) {
                System.out.println("Invalid Input. " + reprimands[(int) (Math.random() * 4)]);
                userInput = scnr.nextLine();
            }

            if      (userInput.equals("1")) {viewAlbums(scnr);}
            else if (userInput.equals("2")) {viewSongs(scnr);}
            else if (userInput.equals("3")) {viewArtists(scnr);}
            else {return;}
        }
    }

    /**
     * Submenu for choosing a criteria to view albums.
     * @param scnr
     */
    public void viewAlbums(Scanner scnr) {
        while(true) {
            AlbumRatings albumRatings = ratings.getAlbumRatings();

            System.out.print("[1] View All Albums\n[2] View Albums by Artist\n[3] View Albums by Year\n");
            System.out.println("[4] View Albums by Tag\n[5] View Albums by Rating\n[6] Back\n");
            String userInput = scnr.nextLine();

            while (!userInput.matches("[1-6]")) {
                System.out.println("Invalid Input. " + reprimands[(int) (Math.random() * 4)]);
                userInput = scnr.nextLine();
            }

            if      (userInput.equals("1")) {view(scnr, albumRatings.getAlbumRatings());}
            else if (userInput.equals("2")) {view(scnr, albumRatings.getAlbumsByArtist(scnr));}
            else if (userInput.equals("3")) {view(scnr, albumRatings.getAlbumsbyYear(scnr));}
            else if (userInput.equals("4")) {view(scnr, albumRatings.getAlbumsByTag(scnr));}
            else if (userInput.equals("5")) {view(scnr, albumRatings.getAlbumsByRating(scnr));}
            else {return;}
        }
    }

    /**
     * Submenu for choosing a criteria to view songs
     * @param scnr
     */
    public void viewSongs(Scanner scnr) {
        while (true) {
            SongRatings songRatings = ratings.getSongRatings();

            System.out.println("[1] View All Songs\n[2] View Songs by Artist\n[3] View Songs by Year");
            System.out.println("[4] View Songs by Tag\n[5] View Songs by Rating\n[6] Back\n");
            String userInput = scnr.nextLine();

            
            while (!userInput.matches("[1-6]")) {
                System.out.println("Invalid Input. " + reprimands[(int) (Math.random() * 4)]);
                userInput = scnr.nextLine();
            }

            if      (userInput.equals("1")) {view(scnr, songRatings.getSongRatings());}
            else if (userInput.equals("2")) {view(scnr, songRatings.getSongsByArtist(scnr));}
            else if (userInput.equals("3")) {view(scnr, songRatings.getSongsByYear(scnr));}
            else if (userInput.equals("4")) {view(scnr, songRatings.getSongsByTag(scnr));}
            else if (userInput.equals("5")) {view(scnr, songRatings.getSongsByRating(scnr));}
            else {return;}
        }
    }

    /**
     * Submenu for choosing a criteria to view artists
     * @param scnr
     */
    public void viewArtists(Scanner scnr) {
        while (true) {
            ArtistRatings artistRatings = ratings.getArtistRatings();

            System.out.println("[1] View All Artists\n[2] View Artists by Tag\n[3] View Artists by Rating\n[4] Back\n");
            String userInput = scnr.nextLine();

            while (!userInput.matches("[1-4]")) {
                System.out.println("Invalid Input. " + reprimands[(int) (Math.random() * 4)]);
                userInput = scnr.nextLine();
            }

            if (userInput.equals("1")) {view(scnr, artistRatings.getArtistRatings());}
            else if (userInput.equals("2")) {view(scnr, artistRatings.getArtistsByTag(scnr));}
            else if (userInput.equals("3")) {view(scnr, artistRatings.getArtistsByRating(scnr));}
            else {return;}
        }
    }

    /**
     * Submenu to view / edit all items in a passed in list.
     * @see #viewAlbums
     * @see #viewSongs
     * @see #viewArtists
     * @param scnr
     * @param list
     */
    public void view(Scanner scnr, ArrayList<? extends Ratable> list) {
        while (true) {
            System.out.println();
            for (int i = 0; i < list.size(); i++) {
                System.out.println(String.format("[%d] %s", i+1, list.get(i).getName()));
            }
            System.out.println("["+(list.size()+1)+"] Back\n\nSelect an item to edit it.\n");
            String userInput = scnr.nextLine();

            while (!userInput.matches("\\d+")) {
                System.out.println("Invalid Input. " + reprimands[(int) (Math.random() * 4)]);
                userInput = scnr.nextLine();
            }

            int num = Integer.parseInt(userInput);
            while ((num < 1) || (num > list.size()+1)) {
                System.out.println("Invalid Input. " + reprimands[(int) (Math.random() * 4)]);
                userInput = scnr.nextLine();
                num = Integer.parseInt(userInput);
            }

            if (num == list.size()+1) {return;}
            else {
                Ratable temp = list.get(num-1);
                if      (temp instanceof Album) {editAlbum(scnr, (Album) temp);}
                else if (temp instanceof Song)  {editSong(scnr, (Song) temp);}
                else if (temp instanceof Artist){editArtist(scnr, (Artist) temp);}
            }
        }
    }

    public void writeToFile(String filename) {
        FileWriter writer = null;

        try {
            writer = new FileWriter(filename);
            for (Ratable item : ratings.getRatings()) {
                writer.write(item.write()+ "\n");
            }

            writer.close();
        }
        catch (IOException e) {
            System.out.println("How");
            try{writer.close();}
            catch (IOException e2) {System.out.println("How^2");}
        }
    }


    public static void main(String[] args) {
        final String fileName = "Ratings.txt";
        CLInterface interface1 = null;
        Scanner scnr = new Scanner(System.in);
        String userInput = "";
        File ratingsFile = new File(fileName);

        try {
            if (!ratingsFile.createNewFile()) {
                interface1 = new CLInterface(fileName);
            }
            else {
                interface1 = new CLInterface();
            }
        }
        catch (IOException e) {
            System.out.println("How");
        }


        System.out.println(greetings[(int) (Math.random() * 4)]);

        while (!userInput.equals("q")) { //Branch of menu
            System.out.println("\n[1] Add New Items\n[2] View/Edit Items\n[3] Export Items\n[4] Exit\n");
            userInput = scnr.nextLine();

            while (!userInput.matches("[1-4]")) {
                System.out.println("Invalid input. " + reprimands[(int) (Math.random() * 4)]);
                userInput = scnr.nextLine();
            }

            if (userInput.equals("1")) {interface1.addItem(scnr);}
            else if (userInput.equals("2")) {interface1.viewItems(scnr);}
            else if (userInput.equals("3")) {interface1.writeToFile(fileName);}
            else {break;}
            
        }


        scnr.close();
    }
}