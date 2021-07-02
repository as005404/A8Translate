package com.foxrider.utils;

import com.google.common.base.Strings;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.Gray;
import com.intellij.ui.JBColor;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;

@Slf4j
public class TranslationUtils {
    private static final String MESSAGE_WHEN_RESULT_IS_EMPTY = "There is no translation for this text.\n Try to select a different part of the text.";

    public static void showPopupWindow(Editor editor, String result) {
        if (Strings.isNullOrEmpty(result)) {
            log.info("TranslationUtils. No translation result");
            buildBaloon(editor, MESSAGE_WHEN_RESULT_IS_EMPTY);
        }
        buildBaloon(editor, result);
    }

    private static void buildBaloon(Editor editor, String result) {
        JBPopupFactory.getInstance()
                .createHtmlTextBalloonBuilder(result, null, new JBColor(new Color(255, 192, 203), Gray._68), null)
                .setFadeoutTime(15000)
                .setHideOnAction(true)
                .createBalloon()
                .show(JBPopupFactory.getInstance().guessBestPopupLocation(editor), Balloon.Position.below);
    }
}
