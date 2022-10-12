import commons.App;
import commons.data.User;
import commons.data.dataPage.Lang;
import commons.pages.BlogPage;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.page;
import static commons.App.baseUrl;
import static commons.Driver.open;
import static commons.Driver.waitForUrlContains;
import static commons.data.dataPage.BlogCategory.CRASH_TEST;
import static commons.data.dataPage.BlogCategory.DESIGN;
import static commons.data.dataPage.BlogCategory.HOW_TO;
import static commons.data.dataPage.BlogCategory.STRUCTURE;
import static commons.data.dataPage.Lang.*;

public class AdminTest extends BaseTest {

    @Override
    public void clearCookie() {
    }

    @Test
    public void loginAdminTest() {
        App
                .openAdminLoginPage()
                .loginAdmin(User.getAdminEmail(), User.getAdminPassword(), true);
        waitForUrlContains("/article/");
    }

    @Test(dependsOnMethods = {"loginAdminTest"})
    public void createNewArticleDraftTest() {
        App
                .openAdminArticlePage()
                .checkCreateArticleButton()
                .clickCreateArticleButton()
                .checkOnPage("New Article")
                .setArticleSlug("TestSlug")
                .selectAuthor("Guest Author")
                .setReadTime("10")
                .selectCategories(List.of(STRUCTURE, CRASH_TEST, HOW_TO))
                .clickArticleLocale(EN)
                .setTitle(EN, "TestTitle")
                .setPreview(EN, "TestPreview")
                .setContent("TestContent")
                .setBackgroundImg("https://staging.stripo.email/photos/shares/Blog/01-1-1024x206.png")
                .clickSaveButton()
                .checkFirstArticleName("TestTitle")
                .checkFirstArticlePublish(false);
    }

    @Test(dependsOnMethods = {"loginAdminTest"}, priority = 2)
    public void articleShouldPublishSuccessfully() {
        App
                .openAdminArticlePage()
                .clickFirstArticle()
                .clickPublishButton()
                .clickSaveButton()
                .clickBackButton()
                .checkFirstArticlePublish(true)
                .openArticleByName(EN, "TestSlug")
                .checkArticleReadTimeValue("10")
                .checkTitleValue("TestTitle")
                .checkContentValue("TestContent")
                .checkAuthorNameValue("Guest Author");
    }

    @Test(dependsOnMethods = {"loginAdminTest"}, priority = 3)
    public void articleShouldBeDelete() {
        App
                .openAdminArticlePage()
                .clickDeleteArticleByIdx(0)
                .checkModalDialogConfirm(true)
                .confirmArticleDelete()
                .checkNotExistArticleName("TestTitle");
    }
}