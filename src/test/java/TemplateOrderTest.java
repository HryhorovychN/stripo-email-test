import commons.App;
import commons.data.User;
import commons.pages.TemplateOrderPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Arrays;

import static com.codeborne.selenide.Selenide.page;
import static commons.data.dataPage.Lang.EN;

public class TemplateOrderTest {

    ClassLoader classLoader = getClass().getClassLoader();

    @DataProvider(name = "templateOrderForm")
    public Object[][] TemplateOrderForm() {
        return new Object[][]{
                {"DESIGN DEVELOPMENT", false, true, true, "254"},
                {"DEVELOPMENT", true, true, false, "180"}

        };
    }

    @Test
    public void shouldBeSendOrderTemplateFormWithFile() {
        TemplateOrderPage templateOrderPage = App.openTemplateOrderPage(EN);

        templateOrderPage
                .setFullName("User")
                .setEmail(User.getValidTestEmail())
                .uploadListFile(Arrays.asList(
                        new File(classLoader.getResource("image.jpg").getFile())));

        templateOrderPage
                .clickSendButton()
                .checkSuccessMessage("Thank you for your brief!\n" +
                        "Your data is on the way to our professionals!\n" +
                        "\n" +
                        "We will get acquainted with your request and will send you a confirmation email with some clarifications. " +
                        "And/or we will issue you the invoice to the email you have mentioned in the brief by COB of the next business day.");
    }



    @Test(dataProvider = "templateOrderForm")
    public void checkOrderTemplateForm(String expectedType, boolean expectedCorrection, boolean expectedReport, boolean expectedUrgency, String totalPrice) {
        App
                .openTemplateOrderPage(EN)
                .setFullName("User")
                .setEmail(User.getValidTestEmail())
                .clickDevelopmentType(expectedType)
                .clickCorrections(expectedCorrection)
                .clickTestReport(expectedReport)
                .clickUrgency(expectedUrgency)
                .checkTotalPrice(expectedType, totalPrice)
                .clickSendButton()
                .checkSuccessMessage("Thank you for your brief!\n" +
                        "Your data is on the way to our professionals!\n" +
                        "\n" +
                        "We will get acquainted with your request and will send you a confirmation email with some clarifications. " +
                        "And/or we will issue you the invoice to the email you have mentioned in the brief by COB of the next business day.");
    }

    @Test
    public void uploadFilesToOrder() {
        App
                .openTemplateOrderPage(EN)
                .setFullName("User")
                .setEmail(User.getValidTestEmail())
                .uploadListFile(Arrays.asList(
                        new File(classLoader.getResource("archive.zip").getFile()),
                        new File(classLoader.getResource("image.gif").getFile()),
                        new File(classLoader.getResource("template.pdf").getFile()),
                        new File(classLoader.getResource("image.png").getFile()),
                        new File(classLoader.getResource("image.jpg").getFile())));

        TemplateOrderPage templateOrderPage = page(TemplateOrderPage.class);

        templateOrderPage
                .checkUploadedFilesCount(5);
    }

}
