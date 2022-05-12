package mk.ukim.finki.models.exeptions;

public class PasswordDoNotMatchException extends  RuntimeException{
    public PasswordDoNotMatchException() {
        super("Password do not match exception");
    }
}
