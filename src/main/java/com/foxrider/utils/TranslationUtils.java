package com.foxrider.utils;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.JBColor;

import java.awt.*;

public class TranslationUtils {
// todo: implement ability t ochange colors to distinct different cases
    //todo: mby change colors
    public static void showPopupWindow(Editor editor, String result) {
        JBPopupFactory.getInstance()
                .createHtmlTextBalloonBuilder(result, null, new JBColor(new Color(238, 172, 62), new Color(73, 117, 73)), null)
                .setFadeoutTime(15000)
                .setHideOnAction(true)
                .createBalloon()
                .show(JBPopupFactory.getInstance().guessBestPopupLocation(editor), Balloon.Position.below);
    }
}
