class Rating {
    
    //Stores a numerical rating for an object.
    private int rating;

    //Stores a review for an object.
    private String review;


    public Rating() {
        this.rating = -1;
        this.review = "";
    }

    /**
     * @return Rating for object.
     */
    public int getRating() {
        return rating;
    }

    /**
     * @param rating New rating for object.
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * @return Review for object.
     */
    public String getReview() {
        return review;
    }

    /**
     * @param review New review for object.
     */
    public void setReview(String review) {
        this.review = review;
    }
}