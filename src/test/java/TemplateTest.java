import commons.App;
import commons.data.dataPage.Lang;
import commons.data.dataPage.TemplateType;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.List;

import static commons.data.User.getRandomInxBySize;
import static commons.data.dataPage.Lang.EN;
import static commons.data.dataPage.TemplateType.ALL;
import static commons.data.dataPage.TemplateType.FREE;
import static commons.data.dataPage.TemplateType.PREMIUM;

public class TemplateTest extends BaseTest {

    @Test(dataProviderClass = DataProviderForm.class, dataProvider = "validDataForNewSubscribeForm")
    @Description("This test verify sent subscribe form with valid data")
    public void verifySentSubscribeFormWithValidDataTest(String email, String message) {
        App
                .openTemplatesPage(EN)
                .sendShadowRootSubscribeForm(email)
                .checkMessageShadowRootSubscribeForm(message);
    }

    @Test(dataProviderClass = DataProviderForm.class, dataProvider = "invalidDataForNewSubscribeForm")
    @Description("This test verify sent subscribe form with invalid data")
    public void verifySentSubscribeFormWithInvalidDataTest(String email, String message) {
        App
                .openTemplatesPage(EN)
                .sendShadowRootSubscribeForm(email)
                .checkErrorTooltipShadowRootSubscribeForm(message);
    }

    @DataProvider(name = "templateFilter")
    public Object[][] TemplateFilter() {
        return new Object[][]{
                {EN, PREMIUM},
                {EN, FREE},
                {EN, ALL}
        };
    }

    @Test(dataProvider = "templateFilter")
    public void templateFiltersShouldWorkCorrectlyTest(Lang lang, TemplateType templateType) {
        App
                .openTemplatesPage(lang)
                .setTemplateType(templateType)
                .checkTemplatesType(templateType);
    }

    @DataProvider(name = "templateCategory")
    public Object[][] TemplateCategory() {
        return new Object[][]{
                {"Type", List.of("Holidays", "Abandoned Cart"), "Industry", List.of("Hobbies")},
                {"Seasons", List.of("Black Friday", "Feature"), "Feature", List.of("CSS Animations", "Countdown timer")}

        };
    }

    @Test(dataProvider = "templateCategory")
    public void openedTemplateShouldHaveSelectedCategoriesTest(String firstCategoryName, List<String> firstListCategories, String secondCategoryName, List<String> secondListCategories) {
        App
                .openTemplatesPage(EN)
                .expandTemplateCategory(firstCategoryName)
                .selectTemplateCategories(firstListCategories)
                .expandTemplateCategory(secondCategoryName)
                .selectTemplateCategories(secondListCategories)
                .openRandomTemplate()
                .checkChosenCategories(firstCategoryName, firstListCategories)
                .checkChosenCategories(secondCategoryName, secondListCategories);
    }

    @Test(dataProviderClass = DataProviderForm.class, dataProvider = "keyWord")
    public void shouldBePossibleToFindTemplateOnDifferentLocaleTest(Lang language, String keyWord) {
        App
                .openTemplatesPage(language)
                .searchTemplateByKeyWord(keyWord)
                .openRandomTemplate();
    }
}
