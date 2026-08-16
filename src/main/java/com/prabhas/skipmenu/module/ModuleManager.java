package com.prabhas.skipmenu.module;

import com.prabhas.skipmenu.SkipMenuConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ModuleManager {
	private final List<Module> modules = new ArrayList<>();

	public ModuleManager() {
		Module nametag = new Module("nametag", "Nametag", false)
			.addSetting(new Setting("showIcon", "Show icon for offline player", Setting.Type.BOOLEAN, "true"))
			.addSetting(new Setting("iconAll", "Show icon for every player", Setting.Type.BOOLEAN, "false"))
			.addSetting(new Setting("iconSize", "Icon size", Setting.Type.TEXT, "12"));
		modules.add(nametag);
	}

	public List<Module> getModules() {
		return Collections.unmodifiableList(modules);
	}

	public Module get(String id) {
		for (Module module : modules) {
			if (module.id.equals(id)) {
				return module;
			}
		}
		return null;
	}

	public void loadFromConfig(SkipMenuConfig config) {
		setEnabled("nametag", config.moduleNametag);
		setSetting("nametag", "showIcon", String.valueOf(config.nametagShowIcon));
		setSetting("nametag", "iconAll", String.valueOf(config.nametagIconAll));
		setSetting("nametag", "iconSize", String.valueOf(config.nametagIconSize));
	}

	public void saveToConfig(SkipMenuConfig config) {
		config.moduleNametag = isEnabled("nametag");
		config.nametagShowIcon = settingValue("nametag", "showIcon").equals("true");
		config.nametagIconAll = settingValue("nametag", "iconAll").equals("true");
		config.nametagIconSize = get("nametag").getSetting("iconSize").asInt(12);
	}

	private void setEnabled(String id, boolean enabled) {
		Module module = get(id);
		if (module != null) {
			module.enabled = enabled;
		}
	}

	private void setSetting(String moduleId, String settingId, String value) {
		Module module = get(moduleId);
		if (module != null && module.getSetting(settingId) != null) {
			module.getSetting(settingId).setValue(value);
		}
	}

	private boolean isEnabled(String id) {
		Module module = get(id);
		return module != null && module.enabled;
	}

	private String settingValue(String moduleId, String settingId) {
		Module module = get(moduleId);
		if (module == null || module.getSetting(settingId) == null) {
			return "";
		}
		return module.getSetting(settingId).value();
	}
}