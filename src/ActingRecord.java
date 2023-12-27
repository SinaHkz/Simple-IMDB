public class ActingRecord {
    private Role role;
    private Person person;

    public ActingRecord(Role role, Person person) {
        this.role = role;
        this.person = person;
    }
    public Role getRole() {
        return role;
    }
    public Person getPerson() {
        return person;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public void setPerson(Person person) {
        this.person = person;
    }

}
