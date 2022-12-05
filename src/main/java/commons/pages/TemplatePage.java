package commons.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import commons.data.dataPage.ItemType;
import commons.data.dataPage.Lang;
import commons.data.dataPage.TemplateType;
import io.qameta.allure.Step;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.collections4.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static commons.data.User.getRandomInxBySize;
import static commons.data.dataPage.TemplateType.ALL;

@CommonsLog
public class TemplatePage extends BasePage {
    private final ElementsCollection premiumTemplates = $$(".template-item__premium-label");
    private final ElementsCollection templates = $$(".template-item__content");

    public ElementsCollection getPremiumTemplates() {return premiumTemplates;}
    public ElementsCollection getTemplates() {return templates;}

    SelenideElement search = $(".page-search__btn");

    public TemplatePage checkOnPage(Lang locale, String expectedText) {
        return super.checkOnPage(locale, $("h1.text-center"), expectedText, TemplatePage.class);
    }

    public TemplatePage setTemplateType(TemplateType templateType) {
        if (templateType != ALL) {
            $(templateType.getSelector()).closest("label").click();
        }
        return this;
    }


    public void checkTemplatesType(TemplateType templateType) {
        sleep(2500);
        switch (templateType) {
            case PREMIUM:
                Assert.assertFalse(getTemplates().isEmpty());
                Assert.assertEquals(getTemplates().size(), getPremiumTemplates().size());
                break;
            case FREE:
                Assert.assertFalse(getTemplates().isEmpty());
                Assert.assertTrue(getPremiumTemplates().isEmpty());
                break;
            case ALL:
                Assert.assertFalse(getTemplates().isEmpty());
                Assert.assertFalse(getPremiumTemplates().isEmpty());
                break;
        }
    }


    public TemplatePage expandTemplateCategory(String categoryName) {
        ElementsCollection categoryPanel = $$(".page-filter .page-filter__item");
        SelenideElement filterItem = categoryPanel.findBy(Condition.text(categoryName));
        filterItem.click();
        filterItem.shouldHave(Condition.cssClass("active"));
        return this;
    }

    public TemplatePage selectTemplateCategories(List<String> categories) {
        ElementsCollection categoriesElement = $$(".page-filter__dropdown.active li");
        for (String category: categories) {
            for (SelenideElement categoryElement: categoriesElement) {
             if (categoryElement.has(Condition.text(category))) {
                 categoryElement.$("svg").click();
                 $("page-filter__dropdown-item loading")
                         .shouldNotBe(Condition.exist);
             }
            }
        }
        return this;
    }

    public TemplatePage openRandomTemplate() {
        sleep(2000);
        $(".page-filter__sticky").click(-400, 50);
        int index = getRandomInxBySize(getTemplates().size());;
        templates.get(index).hover().$(".template-item__button:last-child").click();
        return this;
    }

    public TemplatePage checkChosenCategories(String categoryName, List<String> expectedCategories) {
        SelenideElement actualCategoryName = $$(".d-md-table-row").findBy(Condition.text(categoryName));
        ElementsCollection actualCategories = actualCategoryName.$$(".template-item__category-item");
        System.out.printf("Actual categories: %s doesn't match expected categories: %s", actualCategories, expectedCategories);

        actualCategories
                    .shouldHave(CollectionCondition
                            .anyMatch("expectedCategory:",
                                    element ->
                                            expectedCategories.stream()
                                                    .anyMatch(expectedCategory ->
                                                            expectedCategory.toLowerCase()
                                                                    .equals(
                                                                            element.getText().toLowerCase()))
                            ));



//        ElementsCollection templateChosenCategories = $$("#chosenCategories .template__filters-item");
//        List<String> templateChosenCategoriesName = templateChosenCategories.texts();
//        CollectionUtils.isEqualCollection(templateChosenCategoriesName, categories);
        return this;
    }

    @Step("Enter value into search input and press ENTER")
    public TemplatePage searchTemplateByKeyWord(String templateName) {
        search.click();
        $(By.id("templates-search-input")).shouldBe(Condition.visible).setValue(templateName).pressEnter();
        return this;
    }

}

