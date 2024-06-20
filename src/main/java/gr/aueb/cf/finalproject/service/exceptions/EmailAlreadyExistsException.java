package gr.aueb.cf.finalproject.service.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String email) {
        super("This email " + email + " already exists.");
    }
}
