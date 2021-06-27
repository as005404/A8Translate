package com.foxrider.settings;

import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class AppSettingsComponent {

    private final JPanel myMainPanel;
    private final JBTextField translateLanguage = new JBTextField();
    private final JBTextField detectLanguageApiKey = new JBTextField();
    private final JBTextField userAgent = new JBTextField();

    public AppSettingsComponent() {
        // todo: add menu to translateLanguage instead of plain text
        myMainPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent(new JBLabel("Enter the language to translate into\n (ru/en/de/it/fr/es/pl): "), translateLanguage, 5, true)
                .addLabeledComponent(new JBLabel("Enter api from https://detectlanguage.com\n (due to 1000 uses per day): "), detectLanguageApiKey, 5, true)
                .addLabeledComponent(new JBLabel("Enter your user-agent value(can be taken from your browser request)\n default value can be broken any time: "), userAgent, 5, true)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    public JPanel getPanel() {
        return myMainPanel;
    }

    public JComponent getPreferredFocusedComponent() {
        return translateLanguage;
    }

    @NotNull
    public String getTranslateLanguageText() {
        return translateLanguage.getText();
    }

    public void setTranslateLanguageText(@NotNull String newText) {
        translateLanguage.setText(newText);
    }

    @NotNull
    public String getDetectLanguageApiKey() {
        return detectLanguageApiKey.getText();
    }

    public void setDetectLanguageApiKey(@NotNull String newText) {
        detectLanguageApiKey.setText(newText);
    }

    @NotNull
    public String getUserAgent() {
        return userAgent.getText();
    }

    public void setUserAgent(@NotNull String newText) {
        userAgent.setText(newText);
    }

}
