import commons.App;
import commons.data.User;
import org.testng.annotations.Test;

import static commons.Driver.waitForUrlContains;

public class AdminTest extends BaseTest{

    @Override
    public void clearCookie() {
    }

    @Test
    public void loginAdminTest() {
        App
                .openAdminLoginPage()
                .loginAdmin(User.getAdminEmail(), User.getAdminPassword(),true);
                 waitForUrlContains("/article/");
    }

    @Test(dependsOnMethods = {"loginAdminTest"})
    public void createNewArticleTest() {
        App
                .openAdminArticlePage()
                .checkCreateArticleButton();
    }

}
