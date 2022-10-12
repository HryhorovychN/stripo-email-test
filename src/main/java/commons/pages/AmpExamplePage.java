package commons.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class AmpExamplePage extends BasePage {

    @Step("Click check box 'Gamification'")
    public AmpExamplePage clickGamification(boolean expected) {
        doInEsputnikFrame(() -> {
            if (expected) {
                $("[value='Gamification']").parent().click();
            }
        });
        return this;
    }

    @Step("Click check box 'Amp Examples'")
    public AmpExamplePage clickAmpExamples(boolean expected) {
        doInEsputnikFrame(() -> {
            if (expected) {
                $("[value=\"AMP\"]").parent().click();
            }
        });
        return this;
    }
}
