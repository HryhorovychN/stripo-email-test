package commons;

import commons.data.Const;
import commons.data.dataPage.Lang;
import commons.pages.*;
import commons.pages.admin.AdminBlogPage;
import commons.pages.admin.AdminPage;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;

public class App {

    /* base url */
    public static final String STAGING_BASE_URL = "https://staging.stripo.email/";
    public static final String LIVE_BASE_URL = "https://stripo.email/";

    public static String baseUrl = STAGING_BASE_URL;

    /* Site pages */

    public static <T> T openPage(String pageUrl, Class<T> classToReturn) {
        return open(String.format("%s%s", baseUrl, pageUrl), classToReturn);
    }

    public static <T> T openPage(Lang locale, String pageUrl, Class<T> classToReturn) {
        return open(String.format("%s%s%s", baseUrl, locale.getLocale(), pageUrl), classToReturn);
    }

    public static BlogPage openBlogPage(Lang locale) {
        return openPage(locale, Const.BLOG, BlogPage.class);
    }

    public static TemplatePage openTemplatesPage(Lang locale) {
        return openPage(locale, Const.TEMPLATES, TemplatePage.class );
    }

    public static HomePage openHomePage(Lang locale) {
        return openPage(locale, Const.HOME, HomePage.class);
    }

    public static CustomerStoriesPage openCustomerStoriesPage(Lang locale) {
        return openPage(locale, Const.CUSTOMER_STORIES, CustomerStoriesPage.class);
    }

    public static PluginPage openPluginPage(Lang locale) {
        return openPage(locale, Const.PLUGIN, PluginPage.class);
    }

    public static TemplateOrderPage openTemplateOrderPage(Lang locale) {
        return openPage(locale, Const.TEMPLATE_ORDER, TemplateOrderPage.class);
    }

    public static AmpExamplePage openAmpExamplePage(Lang locale) {
        return openPage(locale, Const.AMP_EXAMPLE, AmpExamplePage.class);
    }

    public static NewEditorPage openNewEditorPage(Lang locale) {
        return openPage(locale, Const.NEW_EDITOR, NewEditorPage.class);
    }

    /* Admin pages */
    public static AdminPage openAdminLoginPage() {
        return openPage(Const.ADMIN, AdminPage.class);
    }

    public static AdminBlogPage openAdminArticlePage() {
        return openPage(Const.A_ARTICLE, AdminBlogPage.class);
    }


    public static void mockConfirm() {
        executeJavaScript("windows.confirm = function() { return true;});");
    }
}
