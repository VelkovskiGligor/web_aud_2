package mk.ukim.finki.models.exeptions;

public class InvalidUserCredentialsException extends RuntimeException  {
    public InvalidUserCredentialsException() {
        super("Invalid user credentials exception");
    }
}
