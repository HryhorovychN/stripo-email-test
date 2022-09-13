package commons.pages.admin;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class AdminBlogPage extends AdminPage {

    public AdminBlogPage checkCreateArticleButton() {
        $(".btn-success").shouldBe(Condition.visible).shouldHave(Condition.text("Create new article"));
        return this;
    }
}
