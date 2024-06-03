public class IllegalArgumentNameExeption extends Exception {

    public IllegalArgumentNameExeption(String message) {
        super(message);
        System.out.println(message);
    }
}
