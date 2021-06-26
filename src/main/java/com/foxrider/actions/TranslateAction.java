package com.foxrider.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TranslateAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Messages.showMessageDialog("hello", "AITranslate+",Messages.getInformationIcon());
        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        String selectedText = editor.getSelectionModel().getSelectedText();
//        String encode = URLEncoder.encode(selectedText, StandardCharsets.UTF_8);

         // сделать запрос какому-нибудь сервису переводов с параметрами определить язык с переводом на заданный в настройках язык
         // отобразить ответ сервиса в окошке возможно даже взять из оригинального AITranslate
    }

    @Override
    public boolean isDumbAware() {
        return false;
    }
}
