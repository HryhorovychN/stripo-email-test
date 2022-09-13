package commons.pages;

import com.codeborne.selenide.Condition;
import commons.data.dataPage.PluginDto;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PluginPage extends BasePage {

    public PluginPage sendEnterpriseForm(PluginDto pluginDto) {
        $(By.id("request-enterprise-solution")).shouldBe(Condition.visible);
        $(By.id("name")).shouldBe(Condition.visible).setValue(pluginDto.getSenderName());
        $(By.id("work_email")).shouldBe(Condition.visible).setValue(pluginDto.getEmail());
        $(By.id("company_name")).shouldBe(Condition.visible).setValue(pluginDto.getWebsite());
        $(".number_emails").shouldBe(Condition.visible).click();
        $$(".number_emails .select-items div").filterBy(Condition.text(pluginDto.getEmailsCreated())).first().click();
        $$(".number_of_employees span").filterBy(Condition.text(pluginDto.getEmployees())).first().click();
        $(".number_of_customers").shouldBe(Condition.visible).click();
        $$(".number_of_customers .select-items div").filterBy(Condition.text(pluginDto.getCustomers())).first().click();
        $(By.id("position")).shouldBe(Condition.visible).setValue(pluginDto.getPosition());
        $(By.id("phone")).shouldBe(Condition.visible).setValue(pluginDto.getPhone());

        $(By.id("submit-btn")).click();
        return this;
    }

    public PluginPage openCustomForm() {
        $(".plugin__tile .modal-open").shouldBe(Condition.visible).click();
        return this;
    }

}
