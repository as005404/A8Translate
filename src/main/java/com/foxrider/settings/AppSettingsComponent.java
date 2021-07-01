package com.foxrider.settings;

import com.foxrider.lang.ContextReverseLanguage;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Arrays;

public class AppSettingsComponent {

    private final String[] languages = Arrays.stream(ContextReverseLanguage.values())
            .map(item -> item.detectedLang)
            .filter(item -> !"not_found".equalsIgnoreCase(item))
            .toArray(String[]::new);

    private final JPanel myMainPanel;
    private final ComboBox<String> targetLanguage = new ComboBox<>(languages);
    private final JBTextField detectLanguageApiKey = new JBTextField();
    private final JBTextField userAgent = new JBTextField();


    public AppSettingsComponent() {
        myMainPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent(new JBLabel("Choose language to translate into: "), targetLanguage, 5, true)
                .addLabeledComponent(new JBLabel("Enter api from https://detectlanguage.com\n (due to 1000 uses per day): "), detectLanguageApiKey, 5, true)
                .addLabeledComponent(new JBLabel("Enter your user-agent value(can be taken from your browser request)\n default value can be broken any time: "), userAgent, 5, true)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    public JPanel getPanel() {
        return myMainPanel;
    }

    public JComponent getPreferredFocusedComponent() {
        return targetLanguage;
    }

    @NotNull
    public String getTargetLanguageText() {
        return targetLanguage.getItem();
    }

    public void setTargetLanguageText(@NotNull String newText) {
        targetLanguage.setItem(newText);
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
