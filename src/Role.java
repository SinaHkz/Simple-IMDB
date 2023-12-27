public enum Role {
    DIRECTOR(1),ACTOR(2),PRODUCER(3);
    private int value;

    Role(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Role getByValue(int value) {
        for (Role role : Role.values()) {
            if (role.getValue() == value) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }
}
