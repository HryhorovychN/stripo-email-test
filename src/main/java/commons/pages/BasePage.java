package commons.pages;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import commons.data.dataPage.Locale;
import commons.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.List;
import java.util.function.Supplier;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class BasePage {

    protected <T> T checkOnPage(Locale locale, SelenideElement cssSelector, String expectedText, Class<T> classToReturn) {
        $(cssSelector).shouldBe(Condition.visible).has(Condition.text(expectedText));
        return page(classToReturn);
    }

    protected <T> T checkOnPage(SelenideElement cssSelector, String expectedText, Class<T> classToReturn) {
        $(cssSelector).shouldBe(Condition.visible).has(Condition.text(expectedText));
        return page(classToReturn);
    }

    protected <T> T checkTitlePage(Locale locale, String expectedTitle, Class<T> classToReturn) {
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


    public BasePage sendSubscribeForm(String email) {
        executeJavaScript("window.scrollBy(0,550)", "");
        $(By.name("subscribe-email")).shouldBe(Condition.visible).setValue(email);
        $(".subscribe-button").click();
        return this;
    }

    @Step("Check message after sent subscribe form")
    public BasePage checkSubscribeFormMessage(String expectedMessage) {
        checkFormMessage($(".subscribe"), expectedMessage);
        return this;
    }

    public BasePage checkFormMessage(SelenideElement selector, String...expectedMessage) {
           for (int i=0; i < expectedMessage.length; i++) {
            int messageId = i;
             selector.$$("p")
                    .shouldHave(CollectionCondition.anyMatch("", element ->
                            element.getText()
                                    .equals(expectedMessage[messageId])));
        }
           return this;
    }


    public BasePage sendEsputnikForm(String email) {
        doInEsputnikFrame(() -> {
        $(By.name("email")).shouldBe(visible).setValue(email);
        $(By.name("subscribe")).click();
        });
        return this;
    }

    @Step("Check message after sent Esputnik form")
    public BasePage checkEsputnikMessage(String expectedMessage) {
        doInEsputnikFrame(() -> {
        $("body").shouldHave(visible).shouldBe(text(expectedMessage));
        });
        return this;
    }

    public BasePage checkSuccessMessage(String message) {
        $(".success-template-wrap").should(Condition.have(Condition.text(message)));
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

    private static final String ESPUTNIK_FRAME_SELECTOR = ".iframe-esputnik iframe";

    public void doInEsputnikFrame(Runnable r) {
        doInEsputnikFrame(() -> $(getFrameSelector()).shouldBe(visible), ()->{
            r.run();
            return null;
        });
    }

    protected <T> T doInEsputnikFrame(Supplier<SelenideElement> elementGetter, FrameAction<T> f) {
        switchTo().defaultContent();
        switchTo().frame(elementGetter.get());
        T result = f.run();
        switchTo().defaultContent();
        return result;
    }

    protected String getFrameSelector() {
        return ESPUTNIK_FRAME_SELECTOR;
    }


}
