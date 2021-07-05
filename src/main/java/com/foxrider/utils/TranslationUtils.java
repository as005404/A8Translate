package com.foxrider.utils;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupManager;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.Gray;
import com.intellij.ui.JBColor;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
public class TranslationUtils {
    private static final String MESSAGE_WHEN_RESULT_IS_EMPTY = "There is no translation for this text.\n Try to select a different part of the text.";
    private static final Integer MAX_NUMBER_OF_TRANSLATIONS = 7;

    public static void showPopupWindow(Editor editor, List<String> result) {
        if (result == null) {
            log.info("TranslationUtils. No translation result");
            buildBalloon(editor, MESSAGE_WHEN_RESULT_IS_EMPTY);
            return;
        }

        String formattedTranslation = result
                .stream()
                .limit(MAX_NUMBER_OF_TRANSLATIONS)
                .collect(Collectors.joining("\n"));
        buildBalloon(editor, formattedTranslation);
    }

    private static void buildBalloon(Editor editor, String result) {
        JBPopupFactory.getInstance()
                .createHtmlTextBalloonBuilder(result, null, new JBColor(new Color(255, 192, 203), Gray._68), null)
                .setFadeoutTime(15000)
                .setHideOnAction(true)
                .createBalloon()
                .show(JBPopupFactory.getInstance().guessBestPopupLocation(editor), Balloon.Position.below);
    }


    public static void showLookup(Editor editor, List<String> translatedText) {
        if (translatedText == null || translatedText.size() == 0) {
            log.info("TranslationUtils. No translation result");
            buildBalloon(editor, MESSAGE_WHEN_RESULT_IS_EMPTY);
            return;
        }
        if (editor != null && editor.getProject() != null) {
            LookupManager lookupManager = LookupManager.getInstance(editor.getProject());
            ApplicationManager.getApplication().invokeLater(() -> lookupManager.showLookup(editor, getProposeList(translatedText)));
        }
    }

    private static LookupElement[] getProposeList(List<String> translations) {
        return translations.stream()
                .map(LookupElementBuilder::create)
                .toArray(LookupElement[]::new);
    }

}
