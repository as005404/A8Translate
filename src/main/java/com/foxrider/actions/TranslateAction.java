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
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

// todo: fix bug when too long text is disappearing
@Slf4j
public class TranslateAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        if (editor == null) {
            log.info("TranslateAction. Editor is null");
            return;
        }
        String selectedText = Objects.requireNonNull(editor.getSelectionModel().getSelectedText());

        selectedText = new CamelCaseUtils(selectedText.trim()).divideCamelCaseIntoLowerCaseWordsOrDefault();

        ContextReverseLanguage detectedLanguage = DefaultLanguageDetector.detectLanguage(selectedText);
        AppSettingsState settings = AppSettingsState.getInstance();
        List<String> translatedText;

        if (detectedLanguage.equals(ContextReverseLanguage.valueOf("NOT_FOUND"))) {

            TranslationUtils.showPopupWindow(editor, "This language is not supported.\n" +
                    "List of supported languages: [en, ru, fr, pl, it, es, de]");
        } else if (detectedLanguage.equals(ContextReverseLanguage.valueFor(settings.targetLanguage))) {
            // if language is not english and equal to our target language then translate selected text to english
            if (!settings.targetLanguage.equalsIgnoreCase(ContextReverseLanguage.ENGLISH.detectedLang)) {

                translatedText = new ContextReverseTranslator().getTranslationForText(
                        ContextReverseLanguage.valueFor(settings.targetLanguage).lang,
                        ContextReverseLanguage.ENGLISH.lang,
                        selectedText
                );

                TranslationUtils.showLookup(editor, translatedText);
            }
        } else {
            translatedText = new ContextReverseTranslator().getTranslationForText(
                    detectedLanguage.lang,
                    ContextReverseLanguage.valueFor(settings.targetLanguage).lang,
                    selectedText
            );

            TranslationUtils.showLookup(editor, translatedText);
        }
    }

    // todo: mby make it work before indexes
    @Override
    public boolean isDumbAware() {
        return false;
    }
}
