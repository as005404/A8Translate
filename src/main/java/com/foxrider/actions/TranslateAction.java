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
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

//todo: make listener on baloon when click on baloon with translation it will replace selected text
public class TranslateAction extends AnAction {


    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        String selectedText = editor.getSelectionModel().getSelectedText();

        if (StringUtils.isEmpty(selectedText)) {
            return;
        }

        selectedText = new CamelCaseUtils(selectedText).divideCamelCaseIntoLowerCaseWordsOrDefault();

        ContextReverseLanguage detectedLanguage = DefaultLanguageDetector.detectLanguage(selectedText);
        AppSettingsState settings = AppSettingsState.getInstance();
        String translatedText;

        if (detectedLanguage.equals(ContextReverseLanguage.valueOf("NOT_FOUND"))) {

            TranslationUtils.showPopupWindow(editor, "This language is not supported.\n" +
                    "List of supported languages: [en, ru, fr, pl, it, es, de]");
        } else if (detectedLanguage.equals(ContextReverseLanguage.valueFor(settings.translateLanguageInto))) {

            translatedText = new ContextReverseTranslator().getTranslationForText(
                    ContextReverseLanguage.valueFor(settings.translateLanguageInto).lang,
                    ContextReverseLanguage.ENGLISH.lang,
                    selectedText
            );

            TranslationUtils.showPopupWindow(editor, translatedText);
        } else {
            translatedText = new ContextReverseTranslator().getTranslationForText(
                    detectedLanguage.lang,
                    ContextReverseLanguage.valueFor(settings.translateLanguageInto).lang,
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
