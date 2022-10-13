import commons.App;
import commons.data.User;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static commons.data.dataPage.Lang.EN;

public class NewEditorTest extends BaseTest {


    @Test
    public void shouldBePossibleToSwitchMobileDesktopMode() {
        App
                .openNewEditorPage(EN)
                .scrollBy("350")
                .clickMobileButton()
                .checkWidthValue("353")
                .clickDesktopButton()
                .checkWidthValue("600");
    }

    @Test
    public void shouldBePossibleOpenCloseCodeEditor() {
        App
                .openNewEditorPage(EN)
                .scrollBy("350")
                .clickCodeEditorButton()
                .checkCodeEditorEnabled()
                .clickCodeEditorButton()
                .checkCodeEditorDisabled();
    }

    @Test
    @Description("This test verify sent Beta Tester form with valid data")
    public void checkSendBetaTesterFormWithValidData() {
        App
                .openNewEditorPage(EN)
                .sendBetaTesterForm(User.getValidTestEmail())
                .checkSuccessMessage("Thanks for your interest in beta testing the new editor - we appreciate you giving us a chance to improve our product!\n" +
                        "Shortly you start receiving instructions on how to start testing.");

    }

    @Test(dataProviderClass = DataProviderForm.class, dataProvider = "invalidDataForEmailForm")
    @Description("This test verify sent Beta Tester form with invalid data")
    public void checkSendBetaTesterFormWithInvalidData(String email, String message) {
        App
                .openNewEditorPage(EN)
                .sendBetaTesterForm(email)
                .checkBetaTesterFormMessage(message);
    }

    @Test()
    public void sendFeedBackFormWithValidData() {
        App
                .openNewEditorPage(EN)
                .sendFeedBackForm(User.getValidTestEmail(), "test")
                .checkFeedBackSuccessMessage("Thanks for your feedback! Your involvement and help in crafting our new editor are greatly appreciated!");
    }

    @Test(dataProviderClass = DataProviderForm.class, dataProvider = "invalidDataForEmailForm")
    public void sendFeedBackFormWithInvalidData(String email, String message) {
        App
                .openNewEditorPage(EN)
                .sendFeedBackForm(email, "test")
                .checkFeedBackFormMessage(message);
    }

}
