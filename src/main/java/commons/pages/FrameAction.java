package commons.pages;

import com.codeborne.selenide.SelenideElement;
import commons.data.dataPage.Lang;

import java.util.Locale;
import java.util.function.Supplier;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.shadowCss;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class FrameAction {
    private static final String ARTICLE_CONTENT_SELECTOR = ".cke_wysiwyg_frame";
    private static final String ESPUTNIK_FRAME_SELECTOR = ".iframe-esputnik iframe";
    private static final String EDITOR_FRAME_SELECTOR = ".e2e-content-loaded";
    private static final String CODE_EDITOR_FRAME_SELECTOR = "ue-code-editor-container iframe";
    private static final SelenideElement SHADOW_ROOT_EDITOR_FRAME = $(shadowCss(EDITOR_FRAME_SELECTOR, "#stripoEditorContainer ui-editor"));
    private static final SelenideElement SHADOW_ROOT_CODE_EDITOR_FRAME = $(shadowCss(CODE_EDITOR_FRAME_SELECTOR, "#stripoEditorContainer ui-editor"));

    public void doInEditorFrame(Runnable r) {
        doInFrame(() -> SHADOW_ROOT_EDITOR_FRAME.shouldBe(visible), ()->{
            r.run();
            return null;
        });
    }

    public void doInCodeEditorFrame(Runnable r) {
        doInFrame(() -> SHADOW_ROOT_CODE_EDITOR_FRAME.shouldBe(visible), ()->{
            r.run();
            return null;
        });
    }

    public void doInEsputnikFrame(Runnable r) {
        doInFrame(() -> $(getEsputnikFrameSelector()).shouldBe(visible), ()->{
            r.run();
            return null;
        });
    }

    public void doInArticleContentFrame(Runnable r) {
        doInFrame(() -> $(getArticleContentSelector()).shouldBe(visible), ()->{
            r.run();
            return null;
        });
    }


    protected <T> T doInFrame(Supplier<SelenideElement> elementGetter, FrameRunner<T> f) {
        switchTo().defaultContent();
        switchTo().frame(elementGetter.get());
        T result = f.run();
        switchTo().defaultContent();
        return result;
    }

    private String getEsputnikFrameSelector() {
        return ESPUTNIK_FRAME_SELECTOR;
    }

    private String getArticleContentSelector() {
        return ARTICLE_CONTENT_SELECTOR;
    }



}
