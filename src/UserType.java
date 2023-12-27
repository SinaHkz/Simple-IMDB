public enum UserType {
    MEMBER(1),ADMIN(2),EDITOR(3);

    private int value;

    UserType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static UserType getBsyValue(int value) {
        for (UserType number : UserType.values()) {
            if (number.getValue() == value) {
                return number;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }
}
