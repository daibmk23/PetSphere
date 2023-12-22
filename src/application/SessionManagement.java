package application;

public class SessionManagement {
    private static User currentUser;

    public static void loginUser(User user) {
        currentUser = user;
    }

    public static void logoutUser() {
        currentUser = null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static boolean isUserLoggedIn() {
        return currentUser != null;
    }

    public static boolean isCurrentUserAgency() {
        return currentUser != null && "Agency".equals(currentUser.getRole());
    }

    public static boolean isCurrentUserStandardUser() {
        return currentUser != null && "User".equals(currentUser.getRole());
    }
}
