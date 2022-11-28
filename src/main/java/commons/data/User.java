package commons.data;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class User {

    private static final List<String > invalidEmails = Arrays.asList("e2e_test@gmailcom", "e2e_testgmail.com", "e2e_test@gmail", "e2e test@gmail.com");
    private static final String adminEmail = "e2e_bot@gmail.com";
    private static final String adminPassword = "$ecret";
    private static final Random random = new Random();

    public static String getValidTestEmail() {
        return String.format("e2e_test%s@gmail.com", random.nextInt(10000));
    }

    public static String getInvalidEmail() {
        return invalidEmails.get(random.nextInt(invalidEmails.size()));
    }

    public static String getAdminEmail() {
        return adminEmail;
    }

    public static String getAdminPassword() {
        return adminPassword;
    }

    public static String getRandomSlug() {return String.format("TestSlug%s", random.nextInt(100));}

    public static String getRandomTitle() {return String.format("TestTitle%s", random.nextInt(100));}

    public static int getRandomInxBySize(int size) {return random.nextInt(size);}



}

