import java.util.Scanner;

public interface Ratable {

    public int getRating();

    public String getName();

    public boolean setRating(int value);

    public String getReview();

    public boolean setReview(String value);

    public void rateRating(Scanner scnr);

    public void rateReview(Scanner scnr);

    public String write();
}