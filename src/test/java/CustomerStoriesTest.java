import commons.App;
import commons.data.User;
import commons.data.dataPage.Locale;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CustomerStoriesTest extends BaseTest {

    @DataProvider(name = "сustomerStoriesForm")
    public Object[][] сustomerStoriesForm() {
        return new Object[][]{
                {"", User.getInvalidEmail(), false, "Please enter a valid email address", "Please enter your name"},
                {"User", User.getValidTestEmail(), true, "We will contact you within our working hours (9:00 AM - 6:00 PM EET).\n" +
                        "Thank you for your patience!"},
        };
    }

    @Test(dataProvider = "сustomerStoriesForm")
    public void sendCustomerStoriesForm(String name, String email, boolean expectedStatus, String... messages) {
        App
                .openCustomerStoriesPage(Locale.EN)
                .sendCustomerStoriesForm(name, email)
                .checkCustomerStoriesFormMessage(expectedStatus, messages);
    }

    @Test
    public void checkCarouselControls() {
        App
                .openCustomerStoriesPage(Locale.EN)
                .checkCommentEnabled("I've been working in Mailchimp for a long time", true)
                .clickNextSlide()
                .checkCommentEnabled("I've been working in Mailchimp for a long time", false)
                .checkCommentEnabled("One would think that coming across a decent email template builder is an easy task.", true)
                .clickPreviousSlide()
                .checkCommentEnabled("I've been working in Mailchimp for a long time", true)
                .checkCommentEnabled("One would think that coming across a decent email template builder is an easy task.", false);

    }
}
