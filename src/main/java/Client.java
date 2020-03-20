public class Client extends User {

    private String phoneNumber;
    private int age;

    public Client(String username, String password, String email, String firstName,
                  String lastName, String phoneNumber, int age) {
        super(username, password, email, firstName, lastName);
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

}
