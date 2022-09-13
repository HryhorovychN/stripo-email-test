import commons.App;
import commons.data.dataPage.Locale;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static commons.data.dataPage.Locale.*;

public class HomeTest extends BaseTest {

    @DataProvider(name = "seoTitleTranslateList")
    public Object[][] localizationMap() {
        return new Object[][]{
                {FR, "Plate-forme de conception d'e-mails", "Stripo — générateur GRATUIT de modèles d'e-mails : éditeur de courrier électronique HTML par glisser/déposer."},
                {IT, "Piattaforma per la Progettazione di E-mail", "Stripo — FREE Email Template Builder: editor HTML trascina e rilascia per la posta elettronica"},
                {UA, "Універсальна платформа для створення HTML-листів", "Stripo — безкоштовний конструктор email-шаблонів: візуальний та HTML редактори"},
                {PT, "Plataforma de Criação de Emails", "Stripo — Criador de Modelos de E-mail GRÁTIS: Editor \"Arraste e Solte\" de E-mail em HTML"},
                {EN, "Email Design Platform", "Stripo — FREE Email Template Builder: Drag and Drop Html Email Editor"},
                {RU, "Универсальная платформа для создания HTML-писем", "Stripo — бесплатный конструктор email-шаблонов: визуальный и HTML редакторы"},
                {ES, "Plataforma de Diseño de Correo Electrónico", "Stripo: creador de plantillas de correo electrónico GRATUITO: Editor visual (arrastrar y soltar) HTML de correo electrónico"},
                {DE, "E-Mail-Design-Plattform", "Stripo — KOSTENLOSER E-Mail-Vorlagen-Generator: Drag and Drop HTML-E-Mail-Editor"}};
    }

    @Test(dataProvider = "seoTitleTranslateList")
    public void seoTitleShouldBeTranslatedTest(Locale locale, String expectedText, String expectedTitle) {
        App
                .openHomePage(locale)
                .checkOnPage(locale, expectedText)
                .checkTitlePage(locale, expectedTitle);
    }

    @Test(dataProviderClass = DataProviderForm.class, dataProvider = "validDataForSubscribeForm")
    @Description("This test verify sent subscribe form with valid data")
    public void verifySentSubscribeFormWithValidDataTest(String email, String message) {
        App
                .openHomePage(EN)
                .sendSubscribeForm(email)
                .checkSubscribeFormMessage(message);
    }

    @Test(dataProviderClass = DataProviderForm.class, dataProvider = "invalidDataForSubscribeForm")
    @Description("This test verify sent subscribe form with invalid data")
    public void verifySentSubscribeFormWithInvalidDataTest(String email, String message) {
        App
                .openHomePage(EN)
                .sendSubscribeForm(email)
                .checkSubscribeFormMessage(message);
    }

    @Test(dataProviderClass = DataProviderForm.class, dataProvider = "esputnikSubscribeForm", priority = 2)
    public void sendEsputnikBookDemoFormTest(String email, String message) {
        App
                .openHomePage(UA)
                .sendEsputnikForm(email)
                .checkEsputnikMessage(message);
    }

}
