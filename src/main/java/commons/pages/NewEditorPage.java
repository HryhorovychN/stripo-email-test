package commons.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;

import static com.codeborne.selenide.Selectors.shadowCss;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.sleep;

public class NewEditorPage extends BasePage {

    public NewEditorPage sendBetaTesterForm(String email) {
        sendEmailForm("beta_test_email", "#submit-btn", email);
        return this;
    }

    public NewEditorPage checkBetaTesterFormMessage(String expectedMessage) {
        checkFormMessage($("#editor-tester-form"), expectedMessage);
        return this;
    }

    public NewEditorPage checkFeedBackFormMessage(String expectedMessage) {
        checkFormMessage($(".feedback-form"), expectedMessage);
        return this;
    }

    public NewEditorPage checkFeedBackSuccessMessage(String message) {
        checkSuccessMessage("#success-feedback", message);
        return this;
    }

    public NewEditorPage sendFeedBackForm(String email, String message) {
        $("#feedback_message").shouldBe(Condition.visible).setValue(message);
        sendEmailForm("feedback_email", "#feedback-btn", email);
        return this;
    }

    public NewEditorPage clickDesktopButton() {
        SelenideElement button = $(shadowCss(".e2e-view-mode-desktop", "#stripoEditorContainer ui-editor"));
        button.shouldBe(Condition.visible).click();
        return this;
    }

    public NewEditorPage clickMobileButton() {
        SelenideElement button = $(shadowCss(".e2e-view-mode-mobile", "#stripoEditorContainer ui-editor"));
        button.shouldBe(Condition.visible).click();
        return this;
    }

    public NewEditorPage clickCodeEditorButton() {
        SelenideElement button = $(shadowCss("#codeEditorButtonId", "#stripoEditorContainer ui-editor"));
        button.shouldBe(Condition.visible).click();
        return this;
    }

    public NewEditorPage checkCodeEditorEnabled() {
        doInCodeEditorFrame(() -> {
            sleep(1500);
            $("ue-monaco-container .code-editor-frame-container").shouldBe(Condition.enabled);
        });
        return this;
    }

    public NewEditorPage checkCodeEditorDisabled() {
        SelenideElement codeEditor = $(shadowCss("ue-code-editor .code-editor-open-btn", "#stripoEditorContainer ui-editor"));
        return this;
    }

    public NewEditorPage checkWidthValue(String width) {
        doInEditorFrame(() -> {
            sleep(3000);
            Assert.assertEquals($("td.esd-structure").getAttribute("offsetWidth"), width);
        });
        return this;
    }

    public NewEditorPage scrollBy(String y) {
        executeJavaScript(String.format("window.scrollBy(0,%s)", y));
        return this;
    }

}
