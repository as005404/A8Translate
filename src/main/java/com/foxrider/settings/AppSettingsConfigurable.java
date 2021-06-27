package com.foxrider.settings;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Provides controller functionality for application settings.
 */
public class AppSettingsConfigurable implements Configurable {

    private AppSettingsComponent settingsComponent;

    // A default constructor with no arguments is required because this implementation
    // is registered as an applicationConfigurable EP

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "AITranslate+ Settings";
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return settingsComponent.getPreferredFocusedComponent();
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        settingsComponent = new AppSettingsComponent();
        return settingsComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        AppSettingsState settings = AppSettingsState.getInstance();
        boolean isApiModified = !settingsComponent.getDetectLanguageApiKey().equalsIgnoreCase(settings.detectionLanguageApiKey);
        boolean isLanguageModified = !settingsComponent.getTranslateLanguageText().equalsIgnoreCase(settings.translateLanguageInto);
        boolean isUserAgentModified = !settingsComponent.getUserAgent().equalsIgnoreCase(settings.userAgent);
        return isApiModified || isLanguageModified || isUserAgentModified;
    }

    @Override
    public void apply() {
        AppSettingsState settings = AppSettingsState.getInstance();
        settings.detectionLanguageApiKey = settingsComponent.getDetectLanguageApiKey();
        settings.translateLanguageInto = settingsComponent.getTranslateLanguageText();
        settings.userAgent = settingsComponent.getUserAgent();
    }

    @Override
    public void reset() {
        AppSettingsState settings = AppSettingsState.getInstance();
        settingsComponent.setDetectLanguageApiKey(settings.detectionLanguageApiKey);
        settingsComponent.setTranslateLanguageText(settings.translateLanguageInto);
        settingsComponent.setUserAgent(settings.userAgent);
    }

    @Override
    public void disposeUIResources() {
        settingsComponent = null;
    }

}
