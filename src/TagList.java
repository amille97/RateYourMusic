import java.util.HashSet;

class TagList {
    
    //Stores set of all tags in use.
    private static HashSet<String> tagSet = new HashSet<>();

    /**
     * @return String array of all tags in use.
     */
    public static String[] getAllTags() {
        String[] resultString = new String[tagSet.size()];

        int i = 0;
        for (String tag : tagSet) {
            resultString[i] = tag;
            i++;
        }

        return resultString;
    }

    /**
     * @param tag Tag to add to set.
     * @return True if the add was successful.
     */
    public static boolean addTag(String tag) {
        return tagSet.add(tag);
    }

    /**
     * 
     * @param tag Tag to remove from set.
     * @return True if the remove was successful.
     */
    public static boolean removeTag(String tag) {
        return tagSet.remove(tag);
    }

    /**
     * Determines whether or not a tag exists in TagList.
     * @param tag Tag to determine 
     * @return
     */
    public static boolean contains(String tag) {
        return tagSet.contains(tag);
    }
}