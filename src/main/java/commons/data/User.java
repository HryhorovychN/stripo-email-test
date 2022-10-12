package commons.data;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class User {

    private static final List<String > invalidEmails = Arrays.asList("e2e_test@gmailcom", "e2e_testgmail.com", "e2e_test@gmail", "e2e test@gmail.com");
    private static final String adminEmail = "e2e_bot@gmail.com";
    private static final String adminPassword = "$ecret";

    public static String getValidTestEmail() {
        Random random = new Random();
        return String.format("e2e_test%s@gmail.com", random.nextInt(10000));
    }

    public static String getInvalidEmail() {
        Random random = new Random();
        return invalidEmails.get(random.nextInt(invalidEmails.size()));
    }

    public static String getAdminEmail() {
        return adminEmail;
    }

    public static String getAdminPassword() {
        return adminPassword;
    }

}

