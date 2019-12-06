package m2.info.exceptions;

public class UnexpectedRoleException extends Exception {

    private final static String message = "Identifiant de rôle inconnu";

    public UnexpectedRoleException() { super(message); }
}
