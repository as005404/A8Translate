package com.foxrider.utils;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.Gray;
import com.intellij.ui.JBColor;

import java.awt.*;

public class TranslationUtils {

    public static void showPopupWindow(Editor editor, String result) {
        JBPopupFactory.getInstance()
                .createHtmlTextBalloonBuilder(result, null, new JBColor(new Color(255, 192, 203), Gray._68), null)
                .setFadeoutTime(15000)
                .setHideOnAction(true)
                .createBalloon()
                .show(JBPopupFactory.getInstance().guessBestPopupLocation(editor), Balloon.Position.below);
    }
}
