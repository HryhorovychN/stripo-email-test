package commons.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TemplateOrderPage extends BasePage {

    SelenideElement templateOrderForm = $(By.id("template-order-form"));

    public TemplateOrderPage setFullName(String fullName) {
        setFullName(templateOrderForm, fullName);
        return this;
    }

    public TemplateOrderPage setEmail(String email) {
        setEmail(templateOrderForm, email);
        return this;
    }

    public TemplateOrderPage clickSendButton() {
        clickSendButton(templateOrderForm);
        return this;
    }

    public TemplateOrderPage clickDevelopmentType(String developmentType) {
        switch (developmentType.toUpperCase()) {
            case "DESIGN DEVELOPMENT":
                $$(".switcher_check__item").get(1).click();
            default: $$(".switcher_check__item").get(0).shouldBe(Condition.visible);
        }
        return this;
    }

    public TemplateOrderPage checkTotalPrice(String developmentType, String price) {
        if (developmentType.toUpperCase().equals("DESIGN DEVELOPMENT")) {
            $("#design-development-price").shouldHave(Condition.text(price));
        }
        else  {
            $("#development-only-price").shouldHave(Condition.text(price));
        }
        return this;
    }

    public TemplateOrderPage clickCorrections(boolean expected) {
        if (expected) {$("#price-one").parent().click();}
        return this;
    }

    public TemplateOrderPage clickUrgency(boolean expected) {
        if (expected) {$(By.id("price-two")).parent().click();}
        return this;
    }

    public TemplateOrderPage clickTestReport(boolean expected) {
        if (expected) {$(By.id("price-three")).parent().click();}
        return this;
    }

    //compare uploaded files with expected count
    public TemplateOrderPage checkUploadedFilesCount(int count) {
        Assert.assertEquals($$(".upload__file-list .upload__file").size(), count);
        return this;
    }
}
