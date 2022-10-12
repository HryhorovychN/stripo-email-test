package commons.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import commons.data.dataPage.ItemType;
import commons.data.dataPage.Lang;
import commons.data.dataPage.TemplateType;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class TemplatePage extends BasePage {
    private final ElementsCollection premiumTemplates = $$(".template__premium");
    private final ElementsCollection templates = $$(".template__item");

    public ElementsCollection getPremiumTemplates() {return premiumTemplates;}
    public ElementsCollection getTemplates() {return templates;}

    public TemplatePage checkOnPage(Lang locale, String expectedText) {
        return super.checkOnPage(locale, $("h1.text-center"), expectedText, TemplatePage.class);
    }

    public TemplatePage setTemplateType(TemplateType templateType) {
        $(templateType.getSelector()).closest("label").click();
        return this;
    }

    public TemplatePage setItemType(ItemType itemType) {
        $(itemType.getSelector()).click();
        return this;
    }

    public boolean checkTemplatesType(TemplateType templateType) {
        sleep(2000);
        switch (templateType) {
            case PREMIUM:
                return getPremiumTemplates().size() == getTemplates().size();
            case FREE:
                return getPremiumTemplates().size() == 0;
            case ALL:
                return (getPremiumTemplates().size() != 0);
            default: return false;
        }
    }


    public TemplatePage expandTemplateCategory(String category) {
        ElementsCollection categoryPanel = $$(".templates-search-extended-wrapper .anchor-link");
        categoryPanel.findBy(Condition.text(category)).click();
        return this;
    }

    public TemplatePage selectTemplateCategories(List<String> categoriesName) {
        ElementsCollection categories = $$(".templates-search-extended-wrapper li");
        for (String categoryName: categoriesName) {
            for (SelenideElement category: categories) {
             if (category.has(Condition.text(categoryName))) {
                 category.$("svg").click();
             }
            }
        }
        return this;
    }

    public TemplatePage checkChosenCategories(List<String> categoriesName) {
        sleep(2500);
        ElementsCollection templateChosenCategories = $$("#chosenCategories .template__filters-item");
        List<String> templateChosenCategoriesName = templateChosenCategories.texts();
        CollectionUtils.isEqualCollection(templateChosenCategoriesName, categoriesName);
        return this;
    }

    public TemplatePage closeItemCategory(String categoryName) {
        $$(".template__filters-item").get(0).shouldBe(Condition.visible);
        $$(".template__filters-item")
                .findBy(Condition.text(categoryName))
                .find(".template__filters-item-close")
                .click();
        return this;
    }

    public TemplatePage closeAllItemCategory() {
        $(".template__filters-closeall").shouldBe(Condition.visible)
                .click();
        return this;
    }

}

