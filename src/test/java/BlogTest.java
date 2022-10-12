import commons.App;
import commons.data.dataPage.BlogCategory;
import commons.data.dataPage.Lang;
import commons.pages.BlogPage;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.page;
import static commons.data.dataPage.BlogCategory.*;
import static commons.data.dataPage.Lang.*;

public class BlogTest extends BaseTest {

    @Test(dataProviderClass = DataProviderForm.class, dataProvider = "validDataForEmailForm")
    @Description("This test verify sent subscribe form with valid data")
    public void verifySentSubscribeFormWithValidDataTest(String email, String message) {
        App
                .openBlogPage(EN)
                .sendSubscribeForm(email)
                .checkSubscribeFormMessage(message);
    }

    @Test(dataProviderClass = DataProviderForm.class, dataProvider = "invalidDataForEmailForm")
    @Description("This test verify sent subscribe form with invalid data")
    public void verifySentSubscribeFormWithInvalidDataTest(String email, String message) {
        App
                .openBlogPage(EN)
                .sendSubscribeForm(email)
                .checkSubscribeFormMessage(message);
    }

    @DataProvider(name = "categoryFilter")
    public Object[][] ArticleCategories() {
        return new Object[][]{
                {EN, List.of(TEMPLATES, HOW_TO)},
                {EN, List.of(MARKETING, STRUCTURE)}
        };
    }

    @Test(dataProvider = "categoryFilter")
    public void categoryFiltersShouldWorkCorrectlyTest(Lang locale, List<BlogCategory> blogCategories) {
        App
                .openBlogPage(locale)
                .selectBlogFilters(blogCategories)
                .clickNext9Articles(1)
                .checkArticleItemCategories(blogCategories);
    }

    @DataProvider(name = "keyWord")
    public Object[][] keyWord() {
        return new Object[][]{
                {EN, "Black Friday"},
                {RU, "версия"}
        };
    }

    @Test(dataProvider = "keyWord")
    public void shouldBePossibleToFindArticlesOnDifferentLocale(Lang language, String keyWord) {
        App
                .openBlogPage(language)
                .searchArticleByKeyWord(keyWord)
                .checkAnyArticleHasText(keyWord);
        }

    @Test
    public void paginationShouldWorkCorrectly() {
        App
                .openBlogPage(EN);

        executeJavaScript("window.scrollBy(0,450)", "");
        BlogPage blogPage = page(BlogPage.class);
        int articles =  blogPage.getArticleItemCount();

        blogPage
                .checkPreviousButton(false)
                .clickNextButton()
                .checkPreviousButton(false)
                .checkArticleItemCount(articles + 9)
                .checkSelectedPage(1)
                .checkSelectedPage(2)
                .checkNext9Articles(true)
                .clickPage(8)
                .checkSelectedPage(1)
                .checkSelectedPage(2)
                .checkSelectedPage(8)
                .checkPreviousButton(true)
                .checkArticleItemCount(articles + 9 * 2)
                .clickNext9Articles(1)
                .checkSelectedPage(9)
                .checkArticleItemCount(articles + 9 * 3)
                .checkPreviousButton(true)
                .checkNextButton(true);
    }
}

