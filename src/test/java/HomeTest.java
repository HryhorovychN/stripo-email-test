import commons.App;
import commons.data.dataPage.Lang;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static commons.data.dataPage.Lang.*;

public class HomeTest extends BaseTest {

    @Test(dataProviderClass = DataProviderForm.class, dataProvider = "validDataForNewSubscribeForm")
    @Description("This test verify sent subscribe form with valid data")
    public void verifySentSubscribeFormWithValidDataTest(String email, String message) {
        App
                .openHomePage(EN)
                .sendShadowRootSubscribeForm(email)
                .checkMessageShadowRootSubscribeForm(message);
    }

    @Test(dataProviderClass = DataProviderForm.class, dataProvider = "invalidDataForNewSubscribeForm")
    @Description("This test verify sent subscribe form with invalid data")
    public void verifySentSubscribeFormWithInvalidDataTest(String email, String message) {
        App
                .openHomePage(EN)
                .sendShadowRootSubscribeForm(email)
                .checkErrorTooltipShadowRootSubscribeForm(message);
    }

    @DataProvider(name = "seoTitleTranslateList")
    public Object[][] localizationMap() {
        return new Object[][]{
                {FR, "Stripo — générateur GRATUIT de modèles d'e-mails : éditeur de courrier électronique HTML par glisser/déposer."},
                {IT, "Stripo — FREE Email Template Builder: editor HTML trascina e rilascia per la posta elettronica"},
                {UA, "Stripo — безкоштовний конструктор email-шаблонів: візуальний та HTML редактори"},
                {PT, "Stripo — Criador de Modelos de E-mail GRÁTIS: Editor \"Arraste e Solte\" de E-mail em HTML"},
                {EN, "Stripo! — FREE Email Template Builder: Drag and Drop Html Email Editor"},
                {RU, "Stripo — бесплатный конструктор email-шаблонов: визуальный и HTML редакторы"},
                {ES, "Stripo: creador de plantillas de correo electrónico GRATUITO: Editor visual (arrastrar y soltar) HTML de correo electrónico"},
                {DE, "Stripo — KOSTENLOSER E-Mail-Vorlagen-Generator: Drag and Drop HTML-E-Mail-Editor"}};
    }

    @Test(dataProvider = "seoTitleTranslateList")
    public void seoTitleShouldBeTranslatedTest(Lang locale, String expectedTitle) {
        App
                .openHomePage(locale)
                .checkTitlePage(locale, expectedTitle);
    }

}
