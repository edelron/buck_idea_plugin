package com.intellij.plugin.buck.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.plugin.buck.storage.BuckSettingsStorage;

public class BuckInstall extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
    }

    @Override
    public void update(final AnActionEvent e) {
        super.update(e);
        boolean hasHistory = !BuckSettingsStorage.HISTORICAL_PROJECT_NAMES.isEmpty();
        e.getPresentation().setEnabled(hasHistory);
        e.getPresentation().setVisible(hasHistory);

        if (hasHistory) {
            e.getPresentation().setText("Buck install " + BuckSettingsStorage.peekHistory());
        }
    }
}