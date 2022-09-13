package commons.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CustomerStoriesPage extends BasePage {

    SelenideElement customerStoriesForm = $("#customer-stories-form");

    public CustomerStoriesPage sendCustomerStoriesForm(String name, String email) {
        setFullName(customerStoriesForm, name);
        setEmail(customerStoriesForm, email);
        clickSendButton(customerStoriesForm);
        return this;
    }

    public void checkCustomerStoriesFormMessage(boolean expectedStatus, String... message) {
        if (expectedStatus) {
            $("#success-story").shouldHave(Condition.text(message[0]));
        } else {
            checkFormMessage(customerStoriesForm, message);
        }
    }

    public CustomerStoriesPage checkCommentEnabled(String text, boolean expected) {
        $$(".section__users-say-item").shouldBe(expected?
                CollectionCondition.anyMatch("", item -> item.getText().contains(text)):
                CollectionCondition.noneMatch("", item -> item.getText().contains(text)));
        return this;
    }

    public CustomerStoriesPage clickPreviousSlide() {
        $("[aria-label=\"Previous slide\"]").click();
        return this;
    }

    public CustomerStoriesPage clickNextSlide() {
       $("[aria-label=\"Next slide\"]").click();
        return this;
    }
}
