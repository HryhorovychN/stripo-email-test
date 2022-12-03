import commons.data.User;
import org.testng.annotations.DataProvider;

import static commons.data.dataPage.Lang.EN;
import static commons.data.dataPage.Lang.RU;
import static commons.data.dataPage.Lang.UA;

public class DataProviderForm {

    @DataProvider(name = "esputnikSubscribeForm")
    public static Object[][] esputnikForm() {
        String email;
        return new Object[][] {
                {User.getInvalidEmail(), "Please enter a valid email address"},
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

    @DataProvider(name = "validDataForEmailForm")
    public static Object[][] validDataForEmailForm() {
        return new Object[][] {
                {User.getValidTestEmail(), "Thanks! You're subscribed, look for a confirmation email shortly."},
        };
    }

    @DataProvider(name = "invalidDataForEmailForm")
    public static Object[][] invalidDataForEmailForm() {
        return new Object[][] {
                {User.getInvalidEmail(), "Please enter a valid email address"}
        };
    }

    @DataProvider(name = "validDataForNewSubscribeForm")
    public static Object[][] validDataForNewSubscribeForm() {
        return new Object[][] {
                {User.getValidTestEmail(), "THANKS FOR SUBSCRIBE"}
        };
    }

    @DataProvider(name = "invalidDataForNewSubscribeForm")
    public static Object[][] invalidDataForNewSubscribeForm() {
        return new Object[][] {
                {User.getInvalidEmail(), "Email is invalid"},
                {"", "Required field"}
        };
    }

    @DataProvider(name = "keyWord")
    public Object[][] keyWord() {
        return new Object[][]{
                {EN, "Black Friday"},
                {UA, "Чорна п'ятниця"}
        };
    }


}
