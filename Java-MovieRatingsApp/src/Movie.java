public class Movie {
    private String title;
    private double totalRating;
    private int numRatings;

    public Movie(String title) {
        this.title = title;
        this.totalRating = 0;
        this.numRatings = 0;
    }

    public int getnumRatings() {
        return numRatings;
    }
    
    public String getTitle() {
        return title;
    }

    public double getAverageRating() {
        return numRatings > 0 ? totalRating / numRatings : 0;
    }

    public void rateMovie(int rating) {
        totalRating += rating;
        numRatings++;
    }
}
