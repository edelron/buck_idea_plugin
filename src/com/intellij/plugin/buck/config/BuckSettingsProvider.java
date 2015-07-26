package com.intellij.plugin.buck.config;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.components.*;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@State(
    name = "BuckOptionsProvider",
    storages = {
        @Storage(
            file = StoragePathMacros.APP_CONFIG + "/buck.xml"
        )}
)
public class BuckSettingsProvider implements PersistentStateComponent<BuckSettingsProvider.State>,
    ExportableApplicationComponent {
  private State myState = new State();

  public static BuckSettingsProvider getInstance() {
    return ApplicationManager.getApplication().getComponent(BuckSettingsProvider.class);
  }

  @Override
  public State getState() {
    return myState;
  }

  @Override
  public void loadState(State state) {
    myState = state;
  }

  @Override
  public void initComponent() {
  }

  @Override
  public void disposeComponent() {
  }

  @NotNull
  @Override
  public File[] getExportFiles() {
    return new File[]{new File(PathManager.getOptionsPath() + File.separatorChar + "buck.xml")};
  }

  @NotNull
  @Override
  public String getPresentableName() {
    return "BuckOptions";
  }

  @NotNull
  @Override
  public String getComponentName() {
    return "BuckOptionsProvider";
  }

  public static class State {

    private static final String DEFAULT_BUCK_BINARY = "buck";

    // Remember the last used buck alias for each historical project
    public Map<String, String> lastAlias = new HashMap<String, String>();

    // Default is "buck", but user can set it to a specific one
    public String buckBinary = DEFAULT_BUCK_BINARY;
  }
}