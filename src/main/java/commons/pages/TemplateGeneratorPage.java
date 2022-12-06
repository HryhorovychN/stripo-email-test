package commons.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.element;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

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

        private void setColor(SelenideElement input, String color) {
            input.parent().parent().$(".input-color-value").click();
            input.parent().parent().$(".input-color-value").sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
            input.parent().parent().$(".input-color-value").shouldBe(Condition.visible).setValue(color);
        }

        public StylesComponent setGeneralBackgroundColor(String color) {
            setColor($("#generalBackgroundColor"), color);
            return page(StylesComponent.class);
        }

        public StylesComponent setHeaderBackgroundColor(String color) {
            setColor($("#headerBackgroundColor"), color);
            return page(StylesComponent.class);
        }

        public StylesComponent setFooterBackgroundColor(String color) {
            setColor($("#footerBackgroundColor"), color);
            return page(StylesComponent.class);

        }

        public StylesComponent setButtonColor(String color) {
            setColor($("#buttonColor"), color);
            return page(StylesComponent.class);
        }

        public StylesComponent setFontColor(String color) {
            setColor($("#fontColor"), color);
            return page(StylesComponent.class);
        }

        public StylesComponent setLinksColor(String color) {
            setColor($("#linksColor"), color);
            return page(StylesComponent.class);
        }


        public StylesComponent setFontName(String fontName) {
            sleep(1000);
                $("#fontName").shouldBe(Condition.visible).setValue(fontName);
                $("#fontName").parent().$(".active").click();
            return this;
        }

        public StylesComponent setHeaderFontNameName(String fontName) {
            sleep(1000);
                $("#headerFontName").shouldBe(Condition.visible).setValue(fontName);
                $("#headerFontName").parent().$(".active").click();
            return this;
        }
    }


public static class PreviewComponent extends TemplateGeneratorPage {

    public PreviewComponent checkPreviewStyleValue(SelenideElement element, String attribute, String value) {
        sleep(1500);
        doInTemplatePreviewFrame(() -> {
            List<String> allStyles = Arrays.asList(element.getAttribute("style").split(";"));
            Map<String, String> mapStyles;
            mapStyles = allStyles.stream().map(s -> s.split(":")).collect(Collectors.toMap(s -> s[0].trim(), s -> s[1].trim()));
            System.out.println(mapStyles);
            Assert.assertEquals(mapStyles.get(attribute), value);
        });
        return this;
    }

    public PreviewComponent checkPreviewText(SelenideElement element, String expectedText) {
        doInTemplatePreviewFrame(() -> {
            System.out.println(element.getText());
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
