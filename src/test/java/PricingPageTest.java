import commons.App;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static commons.data.dataPage.Lang.EN;

public class PricingPageTest extends BaseTest {

    @Test(dataProviderClass = DataProviderForm.class, dataProvider = "validDataForEmailForm")
    @Description("This test verify sent subscribe form with valid data")
    public void verifySentSubscribeFormWithValidDataTest(String email, String message) {
        App
                .openPricingPage(EN)
                .sendSubscribeForm(email)
                .checkSubscribeFormMessage(message);
    }

    @Test(dataProviderClass = DataProviderForm.class, dataProvider = "invalidDataForEmailForm")
    @Description("This test verify sent subscribe form with invalid data")
    public void verifySentSubscribeFormWithInvalidDataTest(String email, String message) {
        App
                .openPricingPage(EN)
                .sendSubscribeForm(email)
                .checkSubscribeFormMessage(message);
    }
}
