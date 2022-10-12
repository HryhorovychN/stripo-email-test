package commons.data.dataPage;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;

public enum BlogCategory {
    DESIGN("Design"),
    AMP ("AMP"),
    HOW_TO("How-to"),
    TEMPLATES("Templates"),
    STRUCTURE("Structure"),
    MARKETING("Marketing"),
    GAMIFICATION("Gamification"),
    CRASH_TEST("Сrash-test");

    private final String category;

    BlogCategory(String category) {this.category = category;}

    public String getCategoryName() {
        return category;
    }
    public ElementsCollection getCategorySelector() {
        return $$(".check__value");
    }

}