import commons.App;
import commons.data.dataPage.TemplatePreview;
import commons.pages.TemplateGeneratorPage;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.page;
import static commons.data.dataPage.Lang.EN;
import static commons.pages.TemplateGeneratorPage.EntityColorName.BUTTON;
import static commons.pages.TemplateGeneratorPage.EntityColorName.FONT;
import static commons.pages.TemplateGeneratorPage.EntityColorName.FOOTER;
import static commons.pages.TemplateGeneratorPage.EntityColorName.GENERAL_BACKGROUND;
import static commons.pages.TemplateGeneratorPage.EntityColorName.HEADER;
import static commons.pages.TemplateGeneratorPage.EntityColorName.LINK;

public class TemplateGeneratorTest extends BaseTest {

    @Test
    public void shouldBePossibleToParseSite() {
        App
                .openTemplateGeneratorPage(EN)
                .setSiteUrl("https://esputnik.com/uk")
                .clickTuneUpStyle()
                .setBrandingName("Stripo")
                .setAddress("Dnipro")
                .setEmail("dnipro.com")
                .setPhone("0999846085")
                .setLogo("https://vjoy.cc/wp-content/uploads/2020/06/white-and-black-panda-illustration-png-clip-art.pngq")
                .clickStyleTab()
                .setColor(GENERAL_BACKGROUND, "#DD13EC")
                .setColor(HEADER, "#06D0CC")
                .setColor(FOOTER, "#0340CE")
                .setColor(BUTTON, "#40D600")
                .setColor(FONT, "#F60404")
                .setColor(LINK, "#E0C40B")
                .getPreview();
//                .checkTemplatePreview(TemplatePreview
//                        .builder()
//                        .brandName("Stripo")
//                        .address("Dnipro")
//                        .email("dnipro.com")
//                        .phone("0999846085")
//                        .generalBackgroundColor("DD13EC")
//                        .headerColor("06D0CC")
//                        .fontColor("F60404")
//                        .buttonColor("40D600")
//                        .headerColor("06D0CC")
//                        .linkColor("E0C40B")
//                        .footerColor("DD13EC")
//                        .build());
    }
}
