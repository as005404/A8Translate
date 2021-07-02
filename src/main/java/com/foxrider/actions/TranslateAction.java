package com.foxrider.actions;

import com.foxrider.detect.DefaultLanguageDetector;
import com.foxrider.lang.ContextReverseLanguage;
import com.foxrider.settings.AppSettingsState;
import com.foxrider.tranlate.ContextReverseTranslator;
import com.foxrider.utils.CamelCaseUtils;
import com.foxrider.utils.TranslationUtils;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

// todo: если язык с которого переводят является английским и язык на который переводят является английским (как когда target language русский и он переводит на англ),
//  то ничего не делать, потому что нет смысла переводить на какой-то другой общий язык кроме ангельского
//todo: make listener on balloon when click on balloon with translation it will replace selected text
@Slf4j
public class TranslateAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        if(editor == null){
            log.info("TranslateAction. Editor is null");
            return;
        }
        String selectedText = Objects.requireNonNull(editor.getSelectionModel().getSelectedText());

        if (StringUtils.isEmpty(selectedText)) {
            log.info("TranslateAction. Selected text is empty");
            return;
        }

        selectedText = new CamelCaseUtils(selectedText.trim()).divideCamelCaseIntoLowerCaseWordsOrDefault();

        ContextReverseLanguage detectedLanguage = DefaultLanguageDetector.detectLanguage(selectedText);
        AppSettingsState settings = AppSettingsState.getInstance();
        String translatedText;

        if (detectedLanguage.equals(ContextReverseLanguage.valueOf("NOT_FOUND"))) {

            TranslationUtils.showPopupWindow(editor, "This language is not supported.\n" +
                    "List of supported languages: [en, ru, fr, pl, it, es, de]");
        } else if (detectedLanguage.equals(ContextReverseLanguage.valueFor(settings.targetLanguage))) {

            translatedText = new ContextReverseTranslator().getTranslationForText(
                    ContextReverseLanguage.valueFor(settings.targetLanguage).lang,
                    ContextReverseLanguage.ENGLISH.lang,
                    selectedText
            );

            TranslationUtils.showPopupWindow(editor, translatedText);
        } else {
            translatedText = new ContextReverseTranslator().getTranslationForText(
                    detectedLanguage.lang,
                    ContextReverseLanguage.valueFor(settings.targetLanguage).lang,
                    selectedText
            );

            TranslationUtils.showPopupWindow(editor, translatedText);
        }
    }


    @Override
    public boolean isDumbAware() {
        return false;
    }
}
