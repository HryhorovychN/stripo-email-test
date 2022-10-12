import commons.App;
import commons.data.dataPage.Lang;
import commons.data.dataPage.TemplateType;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.List;

import static commons.data.dataPage.ItemType.POPULAR;
import static commons.data.dataPage.Lang.EN;
import static commons.data.dataPage.TemplateType.ALL;
import static commons.data.dataPage.TemplateType.FREE;
import static commons.data.dataPage.TemplateType.PREMIUM;

public class TemplateTest extends BaseTest {

    @Override
    public void clearCookie() {
    }

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

//    @Ignore
//    @Test(dataProvider = "templateFilter")
//    public void templateFiltersShouldWorkCorrectlyTest(Lang lang, TemplateType templateType) {
//        App
//                .openTemplatesPage(lang)
//                .setTemplateType(templateType)
//                .setItemType(POPULAR)
//                .checkTemplatesType(templateType);
//    }

    @DataProvider(name = "templateCategory")
    public Object[][] TemplateCategory() {
        return new Object[][]{
                {"Type", List.of("Apology", "Cold Emails", "Alerts & Notifications")}
        };
    }

//    @Ignore
//    @Test(dataProvider = "templateCategory")
//    public void selectedCategoriesShouldBeSaved(String category, List<String> categoriesName) {
//        App
//                .openTemplatesPage(EN)
//                .expandTemplateCategory(category)
//                .selectTemplateCategories(categoriesName)
//                .checkChosenCategories(categoriesName);
//    }

//    @Ignore
//    @Test(dependsOnMethods = {"selectedCategoriesShouldBeSaved"})
//    public void selectedCategoriesShouldBeRemoved() {
//
//        App
//                .openTemplatesPage(EN)
//                .closeItemCategory("Apology")
//                .checkChosenCategories(List.of("Cold Emails", "Alerts & Notifications"))
//                .closeAllItemCategory()
//                .checkChosenCategories(List.of(""));
//    }
}
