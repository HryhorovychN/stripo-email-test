package commons.pages;

import static com.codeborne.selenide.Selenide.$;

public class AmpExamplePage extends BasePage {

    public AmpExamplePage clickGamification(boolean expected) {
        doInEsputnikFrame(() -> {
            if (expected) {
                $("[value='Gamification']").parent().click();
            }
        });
        return this;
    }

    public AmpExamplePage clickAmpExamples(boolean expected) {
        doInEsputnikFrame(() -> {
            if (expected) {
                $("[value=\"AMP\"]").parent().click();
            }
        });
        return this;
    }
}
