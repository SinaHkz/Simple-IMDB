public enum ReportType {
    SPAM(1),HATESPEECH(2),FALSEINFORMATION(3),SPOILER(4);

    private int value;

    ReportType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ReportType getBsyValue(int value) {
        for (ReportType number : ReportType.values()) {
            if (number.getValue() == value) {
                return number;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }
}
