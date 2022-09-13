import commons.App;
import commons.data.User;
import org.testng.annotations.Test;

public class AdminTest extends BaseTest{

    @Override
    public void clearCookie() {
    }

    @Test
    public void loginAdminTest() {
        App
                .openAdminLoginPage()
                .loginAdmin(User.getAdminEmail(), User.getAdminPassword(),true).checkOnPage("Stripo Admin");
    }

    @Test(dependsOnMethods = {"loginAdminTest"})
    public void createNewArticleTest() {
        App
                .openAdminArticlePage()
                .checkCreateArticleButton();
    }

}
