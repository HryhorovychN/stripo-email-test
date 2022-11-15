package commons.pages;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import commons.data.dataPage.Lang;
import commons.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.List;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.shadowCss;
import static com.codeborne.selenide.Selenide.*;

public class BasePage extends FrameAction {

    protected <T> T checkOnPage(Lang locale, SelenideElement cssSelector, String expectedText, Class<T> classToReturn) {
        $(cssSelector).shouldBe(Condition.visible).has(Condition.text(expectedText));
        return page(classToReturn);
    }

    protected <T> T checkOnPage(SelenideElement cssSelector, String expectedText, Class<T> classToReturn) {
        $(cssSelector).shouldBe(Condition.visible).has(Condition.text(expectedText));
        return page(classToReturn);
    }

    protected <T> T checkTitlePage(Lang locale, String expectedTitle, Class<T> classToReturn) {
        String currentTitle = Driver.currentDriver().getTitle();
        currentTitle = expectedTitle;
        return page(classToReturn);
    }

    public void clickViaJS(SelenideElement element) {
        WebElement webElement = element.toWebElement();
        executeJavaScript("arguments[0].click();", webElement);
    }

    public void setFullName(SelenideElement selenideElement, String fullName) {
        selenideElement.find(By.name("full_name")).setValue(fullName);
    }

    public void setEmail(SelenideElement selenideElement, String email) {
        selenideElement.find(By.name("email")).setValue(email);
    }

    public void clickSendButton(SelenideElement selenideElement) {
        selenideElement.find("button").shouldBe(visible).click();
    }

    @Step("Set email and send subscribe form")
    public BasePage sendSubscribeForm(String email) {
        executeJavaScript("window.scrollBy(0,550)", "");
        sendEmailForm("subscribe-email", ".subscribe-button", email);
        return this;
    }

    protected BasePage sendEmailForm(String inputSelector, String buttonSelector, String email) {
        $(By.name(inputSelector)).shouldBe(Condition.enabled).setValue(email);
        $(buttonSelector).click();
        return this;
    }

    public BasePage sendShadowRootSubscribeForm(String email) {
        SelenideElement input = $(shadowCss("[name=\"email\"]", "[cl-type=\"VIEW\"]", "[cl-type=\"CONTAINER\"]", "[cl-type=\"INPUT\"]"));
        SelenideElement button = $(shadowCss(".main-container button", "[cl-type=\"VIEW\"]", "[cl-type=\"BUTTON\"]"));
        input.shouldBe(visible).setValue(email);
        button.click();
        return this;
    }

    public BasePage checkMessageShadowRootSubscribeForm(String expectedMessage) {
        SelenideElement text = $(shadowCss("[cl-element=\"text\"]", "[cl-type=\"VIEW\"]", "[cl-type=\"TEXT\"]"));
        text.shouldBe(visible).shouldHave(text(expectedMessage));
        return this;
    }

    public BasePage checkErrorTooltipShadowRootSubscribeForm(String expectedMessage) {
        SelenideElement tooltip = $(shadowCss(".input-tooltip", "[cl-type=\"VIEW\"]", "[cl-type=\"CONTAINER\"]","[cl-type=\"INPUT\"]"));
        actions().moveToElement(tooltip).build().perform();
        tooltip.shouldBe(visible).shouldHave(text(expectedMessage));
        return this;
    }


    @Step("Check message after send subscribe form")
    public BasePage checkSubscribeFormMessage(String expectedMessage) {
        checkFormMessage($(".subscribe"), expectedMessage);
        return this;
    }

    protected BasePage checkFormMessage(SelenideElement selector, String...expectedMessage) {
           for (int i=0; i < expectedMessage.length; i++) {
            int messageId = i;
             selector.$$("p")
                    .shouldHave(CollectionCondition.anyMatch("", element ->
                            element.getText()
                                    .equals(expectedMessage[messageId])));
        }
           return this;
    }

    @Step("Set email and send 'Esputnik' form")
    public BasePage sendEsputnikForm(String email) {
        doInEsputnikFrame(() -> {
        $(By.name("email")).shouldBe(visible).setValue(email);
        $(By.name("subscribe")).click();
        });
        return this;
    }

    @Step("Check message after send 'Esputnik' form")
    public BasePage checkEsputnikMessage(String expectedMessage) {
        doInEsputnikFrame(() -> {
        $("body").shouldHave(visible).shouldBe(text(expectedMessage));
        });
        return this;
    }

    public BasePage checkSuccessMessage(String message) {
        checkSuccessMessage(".success-template-wrap", message);
        return this;
    }

    protected BasePage checkSuccessMessage(String selector, String message) {
        $(selector).waitUntil(Condition.have(Condition.text(message)), 6000);
        return this;
    }

    public BasePage uploadListFile(List<File> file) {
        executeJavaScript("window.scrollBy(0,1000)", "");
        sleep(1000);
        for (File testFile: file) {
            $("[type='file']")
                    .should(Condition.exist)
                    .uploadFile(testFile);
        }
        return this;
    }
}
