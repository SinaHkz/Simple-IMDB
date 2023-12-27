import java.util.ArrayList;
import java.util.Scanner;

public class Review {
    Scanner scanner = new Scanner(System.in);
    private String review;
    private User reviewer;
    private boolean spoilerAlert;
    private ArrayList<ReviewReport> reviewReports;
    private ArrayList<ReviewRate> reviewRates;

    public String getReview() {
        return review;
    }

    public User getReviewer() {
        return reviewer;
    }

    public ArrayList<ReviewReport> getReviewReports() {
        return reviewReports;
    }

    public boolean isSpoilerAlert() {
        return spoilerAlert;
    }

    public Review(String review, User reviewer, boolean spoilerAlert, ArrayList<ReviewReport> reviewReports, ArrayList<ReviewRate> reviewRates) {
        this.review = review;
        this.reviewer = reviewer;
        this.spoilerAlert = spoilerAlert;
        this.reviewReports = reviewReports;
        this.reviewRates = reviewRates;
    }

    void reportReview(User user) {
        System.out.println("What's your report type?\n1.spam  2.hate speech  3.false information  4.spoiler");
        int type = Integer.parseInt(scanner.nextLine());
        System.out.println("If you want to add something else type it or press enter.");
        String moreInfo = scanner.nextLine();
        reviewReports.add(new ReviewReport(ReportType.getBsyValue(type), moreInfo, user, ReportStatus.NOTSEEN));
    }

    void showReviewReports() {
        for (int i = 0; i < this.reviewReports.size(); i++) {
            System.out.println(i + 1 + " " + "Review: " + this.reviewReports.get(i).getReportType() + " | more detail: " + this.reviewReports.get(i).getMorDetail() + " | Reporter: " + this.reviewReports.get(i).getReporter().getUserName() + " | Status: " + this.reviewReports.get(i).getReportStatus() + "\n");
            this.reviewReports.get(i).setReportStatus(ReportStatus.SEEN);
        }
    }

    void editReview() {
        System.out.println("1.edit review text  2.edit review spoiler alert  0.back");
        int respond = Integer.parseInt(scanner.nextLine());
        switch (respond) {
            case 1:
                System.out.println("Enter your new text: ");
                this.review = scanner.nextLine();
                break;
            case 2:
                System.out.println("1.on  2.off");
                int spoilerAlertNum = Integer.parseInt(scanner.nextLine());
                this.spoilerAlert = spoilerAlertNum == 1; //if spoilerAlertNum == 1 then spoiler alert is true
                break;
            default:
                break;

        }
    }

    void likeReview(User user) {
        for (ReviewRate reviewRate : reviewRates) {
            if (user == reviewRate.getRater()){
                reviewRate.setScore(1);
                return;
            }
        }
        reviewRates.add(new ReviewRate(1, user));
    }

    void dislikeReview(User user) {
        for (ReviewRate reviewRate : reviewRates) {
            if (user == reviewRate.getRater()){
                reviewRate.setScore(-1);
                return;
            }
        }
        reviewRates.add(new ReviewRate(-1, user));
    }

    String isHelpful() {
        int status = 0;
        for (ReviewRate reviewRate : reviewRates) {
            status += reviewRate.getScore();
        }
        if (status > 0)
            return "Helpful";
        else if (status == 0)
            return "-";
        else
            return "Not helpful";
    }
}
