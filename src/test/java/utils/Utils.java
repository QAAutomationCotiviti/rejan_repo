package utils;

public class Utils {
    private static final String email = "standard_user";
    private static final String password = "secret_sauce";
    private static final String invalidEmail = "secret_sauce";
    private static final String lockedUser = "locked_out_user";
    private static final String invalidPassword = "secret_sauce_invalid";

    public static String getEmail() {
        return email;
    }

    public static String getPassword() {
        return password;
    }

    public static String getInvalidEmail() {
        return invalidEmail;
    }

    public static String getInvalidPassword() {
        return invalidPassword;
    }

    public static String getLockedUser() {return lockedUser; }
}
