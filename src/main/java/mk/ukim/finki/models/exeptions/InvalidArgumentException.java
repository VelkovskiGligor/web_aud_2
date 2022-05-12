package mk.ukim.finki.models.exeptions;

public class InvalidArgumentException extends RuntimeException{
    public InvalidArgumentException() {
        super("Invalid Arguments exception");
    }
}
