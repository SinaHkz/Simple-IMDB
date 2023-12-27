public enum Genre {
    ACTION(1),COMEDY(2),DRAMA(3),SCIENCE_FICTION(4),FAMILY(5),BIOGRAPHY(6),ROMANCE(7),ANIMATION(8);

    private int value;

    Genre(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Genre getByValue(int value) {
        for (Genre number : Genre.values()) {
            if (number.getValue() == value) {
                return number;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }
}
