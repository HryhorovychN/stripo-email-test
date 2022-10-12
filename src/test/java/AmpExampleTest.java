import commons.App;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static commons.data.dataPage.Lang.EN;

public class AmpExampleTest extends BaseTest {

    @Test(dataProviderClass = DataProviderForm.class, dataProvider = "validDataForEmailForm")
    @Description("This test verify sent subscribe form with valid data")
    public static void verifySentSubscribeFormWithValidDataTest(String email, String message) {
        App
                .openAmpExamplePage(EN)
                .sendSubscribeForm(email)
                .checkSubscribeFormMessage(message);
    }

    @Test(dataProviderClass = DataProviderForm.class, dataProvider = "invalidDataForEmailForm")
    @Description("This test verify sent subscribe form with invalid data")
    public static void verifySentSubscribeFormWithInvalidDataTest(String email, String message) {
        App
                .openAmpExamplePage(EN)
                .sendSubscribeForm(email)
                .checkSubscribeFormMessage(message);
    }

    @Test(dataProviderClass = DataProviderForm.class, dataProvider = "esputnikGamificationForm", priority = 2)
    public void sendGamificationForm(String email, String message) {

        App
                .openAmpExamplePage(EN)
                .clickGamification(true)
                .clickGamification(true)
                .sendEsputnikForm(email)
                .checkEsputnikMessage(message);
    }
}
