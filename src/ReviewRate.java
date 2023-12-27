public class ReviewRate {
    private int score;
    private User rater;

    public ReviewRate(int score, User rater) {
        this.score = score;
        this.rater = rater;
    }

    public int getScore() {
        return score;
    }

    public User getRater() {
        return rater;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setRater(User rater) {
        this.rater = rater;
    }
}
