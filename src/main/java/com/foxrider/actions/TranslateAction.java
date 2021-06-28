package com.foxrider.actions;

import com.foxrider.detect.DefaultLanguageDetector;
import com.foxrider.lang.ContextReverseLanguage;
import com.foxrider.settings.AppSettingsState;
import com.foxrider.tranlate.ContextReverseTranslator;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranslateAction extends AnAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(TranslateAction.class);
    private String translatedText;

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        String selectedText = editor.getSelectionModel().getSelectedText();
//        String encode = URLEncoder.encode(selectedText, StandardCharsets.UTF_8);
        if (StringUtils.isNotEmpty(selectedText)) {
            ContextReverseLanguage contextReverseLanguage = DefaultLanguageDetector.detectLanguage(selectedText);
            if (!contextReverseLanguage.equals(ContextReverseLanguage.valueOf("NOT_FOUND"))) {
                AppSettingsState settings = AppSettingsState.getInstance();
                translatedText = new ContextReverseTranslator().getTranslationForText(
                        contextReverseLanguage.lang,
                        ContextReverseLanguage.valueFor(settings.translateLanguageInto).lang,
                        selectedText
                );
            }else {
                // todo: show some pop up with this "language is not maintained"
            }

        }

        // сделать запрос какому-нибудь сервису переводов с параметрами определить язык с переводом на заданный в настройках язык
        // отобразить ответ сервиса в окошке возможно даже взять из оригинального AITranslate
    }

    @Override
    public boolean isDumbAware() {
        return false;
    }
}
