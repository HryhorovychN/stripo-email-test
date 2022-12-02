package commons.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import commons.Driver;
import commons.data.dataPage.TemplatePreview;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.element;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;
import static commons.Driver.currentDriver;
import static commons.Driver.executeJs;

public class TemplateGeneratorPage extends BasePage {

    public static class CompanyInfoComponent extends TemplateGeneratorPage {

        public CompanyInfoComponent setBrandingName(String brandingName) {
            $(By.id("brandName")).shouldBe(Condition.visible).setValue(brandingName);
            sleep(1000);
            return page(CompanyInfoComponent.class);
        }

        public CompanyInfoComponent setLogo(String logoUrl) {
            sleep(500);
            $(By.id("logo")).shouldBe(Condition.visible).setValue(logoUrl);
            sleep(1000);
            return page(CompanyInfoComponent.class);
        }

        public CompanyInfoComponent setEmail(String email) {
            $(By.id("email")).shouldBe(Condition.visible).setValue(email);
            sleep(1000);
            return page(CompanyInfoComponent.class);
        }

        public CompanyInfoComponent setPhone(String phone) {
            $(By.id("phone")).shouldBe(Condition.visible).setValue(phone);
            sleep(1000);
            return page(CompanyInfoComponent.class);
        }

        public CompanyInfoComponent setAddress(String address) {
            $(By.id("address")).shouldBe(Condition.visible).setValue(address);
            sleep(1000);
            return page(CompanyInfoComponent.class);
        }

        public StylesComponent clickStyleTab() {
            sleep(1000);
            $$(".switcher__item").get(1).click();
            return page(StylesComponent.class);
        }
    }

    public static class StylesComponent extends TemplateGeneratorPage {

        public StylesComponent setColor(EntityColorName entity, String color) {
            ElementsCollection colorInputs = $$(".template-generator-tune-tab .input-color-value");
            colorInputs.get(entity.getEntity()).clear();
            colorInputs.get(entity.getEntity()).click();
            colorInputs.get(entity.getEntity()).sendKeys(color);
            sleep(1000);
            return page(StylesComponent.class);
        }

        public StylesComponent setFontStyle(String fontStyle) {
            $(By.id("fontName")).shouldBe(Condition.visible).setValue(fontStyle);
            sleep(1000);
            return page(StylesComponent.class);
        }

        public StylesComponent setHeaderFontStyle(String headerFontStyle) {
            $(By.id("headerFontName")).shouldBe(Condition.visible).setValue(headerFontStyle);
            sleep(1000);
            return page(StylesComponent.class);
        }
    }

    public enum EntityColorName {

        GENERAL_BACKGROUND(0), HEADER(1), FOOTER(2), BUTTON(3), FONT(4), LINK(5);

        private final int entity;

        EntityColorName(int entity) {this.entity = entity;}

        public int getEntity() {
            return entity;
        }
    }


public static class PreviewComponent extends TemplateGeneratorPage {


    public PreviewComponent checkTemplatePreview(TemplatePreview control) {
            doInTemplatePreviewFrame(() -> {
                SelenideElement header = $(".es-wrapper-color .es-header-body");
                SelenideElement content = $(".es-content-body");
                SelenideElement footer = $(".es-footer-body");
                SelenideElement preview =$(".es-wrapper");

                header.shouldHave(Condition.text(control.getEmail()));
                header.shouldHave(Condition.text(control.getPhone()));
                header.find("img").shouldBe(Condition.visible);
                content.shouldHave(Condition.text(control.getBrandName()));
                footer.$(".esd-block-text").shouldHave(Condition.text(control.getEmail()));
                footer.$(".esd-block-text").shouldHave(Condition.text(control.getAddress()));
                footer.$(".esd-block-text").shouldHave(Condition.text(control.getPhone()));
                preview.shouldHave(Condition.attribute("background-color", control.getGeneralBackgroundColor()));
                header.shouldHave(Condition.attribute("background-color", control.getHeaderColor()));
                footer.shouldHave(Condition.attribute("background-color", control.getFooterColor()));
                preview.find("button").shouldHave(Condition.attribute("background-color", control.getButtonColor()));
                preview.$$("p").shouldHave(CollectionCondition.allMatch("text", element -> element.getCssValue("color").equals(control.getFontColor())));
                preview.$$("a").shouldHave(CollectionCondition.allMatch("link", element -> element.getCssValue("color").equals(control.getLinkColor())));
            });
            return this;
        }
    }

    public TemplateGeneratorPage setSiteUrl(String siteUrl) {
        $(By.id("template-generator-input")).shouldBe(Condition.visible).setValue(siteUrl);
        clickSendButton($(By.id("template-send-form")));
        return this;
    }

    public CompanyInfoComponent clickTuneUpStyle() {
        $(".template-generator-buttons-wrap").find(".tune-btn").click();
        return page(CompanyInfoComponent.class);
    }

    public PreviewComponent getPreview() {
        return page(PreviewComponent.class);
    }

}
