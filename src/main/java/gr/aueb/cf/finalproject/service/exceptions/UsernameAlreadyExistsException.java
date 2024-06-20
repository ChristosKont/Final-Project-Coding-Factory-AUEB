package gr.aueb.cf.finalproject.service.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String username) {
        super("This username @" + username + " already exists.");
    }
}
