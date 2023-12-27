public class Rate {
    private Movie movie;
    private Star star;
    private User rater;

    public Star getStar() {
        return star;
    }

    public User getRater() {
        return rater;
    }

    public Movie getMovie() {
        return movie;
    }

    public Rate(Star star, User rater, Movie movie) {
        this.star = star;
        this.rater = rater;
        this.movie = movie;
    }
}
