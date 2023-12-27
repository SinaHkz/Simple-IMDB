public abstract class Report {
    private ReportType reportType;
    private String morDetail;
    private User reporter;
    private ReportStatus reportStatus;

    public ReportType getReportType() {
        return reportType;
    }

    public String getMorDetail() {
        return morDetail;
    }

    public User getReporter() {
        return reporter;
    }

    public ReportStatus getReportStatus() {
        return reportStatus;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    public void setMorDetail(String morDetail) {
        this.morDetail = morDetail;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public void setReportStatus(ReportStatus reportStatus) {
        this.reportStatus = reportStatus;
    }

    public Report(ReportType reportType, String morDetail, User reporter, ReportStatus reportStatus) {
        this.reportType = reportType;
        this.morDetail = morDetail;
        this.reporter = reporter;
        this.reportStatus = reportStatus;
    }
}
