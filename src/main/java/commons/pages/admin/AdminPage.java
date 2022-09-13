package commons.pages.admin;

import com.codeborne.selenide.Condition;
import commons.pages.BasePage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class AdminPage extends BasePage {

    public  AdminPage loginAdmin(String email, String password, boolean remember) {
        $(By.name("email")).shouldBe(Condition.visible).setValue(email);
        $(By.name("password")).shouldBe(Condition.visible).setValue(password);
        if (remember) {
            $(By.name("remember")).parent().click();
        }
        $("[type=\"submit\"]").click();
        return page(AdminPage.class);
    }

    public AdminPage checkOnPage(String expectedText) {
        return super.checkOnPage($(".logo-lg"), expectedText, AdminPage.class);
    }

}
