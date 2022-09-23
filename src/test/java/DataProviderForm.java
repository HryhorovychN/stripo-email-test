import commons.data.User;
import org.testng.annotations.DataProvider;

public class DataProviderForm {

    @DataProvider(name = "esputnikSubscribeForm")
    public static Object[][] esputnikForm() {
        String email;
        return new Object[][] {
                {User.getInvalidEmail(), "Please enter a valid email address."},
                {email = User.getValidTestEmail(), String.format("Check your inbox %s", email)}
        };
    }

    @DataProvider(name = "esputnikGamificationForm")
    public static Object[][] esputnikGamificationForm() {
        String email;
        return new Object[][] {
                {User.getInvalidEmail(), "Need a valid email"},
                {email = User.getValidTestEmail(), String.format("Check your inbox %s", email)}
        };
    }

    @DataProvider(name = "validDataForSubscribeForm")
    public static Object[][] baseSubscribeFormValidData() {
        return new Object[][] {
                {User.getValidTestEmail(), "Thanks! You're subscribed, look for a confirmation email shortly."},
        };
    }

    @DataProvider(name = "invalidDataForSubscribeForm")
    public static Object[][] baseSubscribeFormInvalidData() {
        return new Object[][] {
                {User.getInvalidEmail(), "Please enter a valid email address"},
                {"", "This action is unauthorized"}
        };
    }


}
