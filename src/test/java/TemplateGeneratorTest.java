import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import commons.App;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static commons.data.dataPage.Lang.EN;


public class TemplateGeneratorTest extends BaseTest {

    @Test
    public void shouldBePossibleParseSiteAndChangeStyle() {
        SelenideElement header = $(".es-header-body");
        SelenideElement content = $(".es-content-body");
        SelenideElement footer = $(".es-footer-body");
        SelenideElement preview =$(".es-wrapper");

        App
                .openTemplateGeneratorPage(EN)
                .setSiteUrl("https://kyivstar.ua/")
                .clickTuneUpStyle()
                .setBrandingName("TestBrandName")
                .setAddress("DniproTestAddress")
                .setEmail("dnipro.com")
                .setPhone("111111111")
                .clickStyleTab()
                .setGeneralBackgroundColor("#DD13EC")
                .setHeaderBackgroundColor("#06D0CC")
                .setFooterBackgroundColor("#0340CE")
                .setButtonColor("#40D600")
                .setFontColor("#F60404")
                .setLinksColor("#E0C40B")
                .setFontName("Secular One")
                .setHeaderFontNameName("Petemoss")
                .getPreview()
                .checkPreviewStyleValue(header, "background-color", "rgb(6, 208, 204)")
                .checkPreviewStyleValue(preview, "background-color", "rgb(221, 19, 236)")
                .checkPreviewStyleValue(footer, "background-color", "rgb(3, 64, 206)")
                .checkPreviewStyleValue(content.$(".esd-block-button span"), "background", "rgb(64, 214, 0)")
                .checkPreviewStyleValue(header.$("a"), "color", "rgb(224, 196, 11)")
                .checkPreviewStyleValue(content.$("h1"), "font-family", "Petemoss")
                .checkPreviewStyleValue(content.$("p"), "color", "rgb(246, 4, 4)")
                .checkPreviewStyleValue(content.$("p"), "font-family", "\"Secular One\"")
                .checkPreviewStyleValue(content.$("a"), "color", "rgb(0, 135, 230)")
                .checkPreviewStyleValue(footer.$("p"), "color", "rgb(246, 4, 4)")
                .checkPreviewStyleValue(footer.$("a"), "color", "rgb(224, 196, 11)");
    }
}
