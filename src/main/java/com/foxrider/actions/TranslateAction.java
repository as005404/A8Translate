package com.foxrider.actions;

import com.detectlanguage.errors.APIError;
import com.foxrider.detect.LanguageDetection;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

public class TranslateAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {

        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        String selectedText = editor.getSelectionModel().getSelectedText();
//        String encode = URLEncoder.encode(selectedText, StandardCharsets.UTF_8);
        if(StringUtils.isNotEmpty(selectedText)){
            try {
                Messages.showMessageDialog(LanguageDetection.detectLanguage(selectedText).detectedLang, "AITranslate+", Messages.getInformationIcon());
            } catch (APIError apiError) {
                apiError.printStackTrace();
            }
        }
        // сделать запрос на сервис определения языка
        // сделать запрос какому-нибудь сервису переводов с параметрами определить язык с переводом на заданный в настройках язык
        // отобразить ответ сервиса в окошке возможно даже взять из оригинального AITranslate
    }

    @Override
    public boolean isDumbAware() {
        return false;
    }
}
