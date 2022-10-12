package commons.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CustomerStoriesPage extends BasePage {

    SelenideElement customerStoriesForm = $("#customer-stories-form");

    @Step("Set name, email and send 'Customer Stories' form")
    public CustomerStoriesPage sendCustomerStoriesForm(String name, String email) {
        setFullName(customerStoriesForm, name);
        setEmail(customerStoriesForm, email);
        clickSendButton(customerStoriesForm);
        return this;
    }

    @Step("Check message after send 'Customer Stories' form")
    public void checkCustomerStoriesFormMessage(boolean expectedStatus, String... message) {
        if (expectedStatus) {
            $("#success-story").shouldHave(Condition.text(message[0]));
        } else {
            checkFormMessage(customerStoriesForm, message);
        }
    }

    @Step("Check if comment exist on the page")
    public CustomerStoriesPage checkCommentEnabled(String text, boolean expected) {
        $$(".section__users-say-item").shouldBe(expected?
                CollectionCondition.anyMatch("", item -> item.getText().contains(text)):
                CollectionCondition.noneMatch("", item -> item.getText().contains(text)));
        return this;
    }

    @Step("Click previous slide button")
    public CustomerStoriesPage clickPreviousSlide() {
        $("[aria-label=\"Previous slide\"]").click();
        return this;
    }

    @Step("Click next slide button")
    public CustomerStoriesPage clickNextSlide() {
       $("[aria-label=\"Next slide\"]").click();
        return this;
    }
}
