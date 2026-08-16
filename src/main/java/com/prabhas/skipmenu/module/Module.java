package com.prabhas.skipmenu.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Module {
	public final String id;
	public final String name;
	public boolean enabled;
	private final List<Setting> settings = new ArrayList<>();

	public Module(String id, String name, boolean enabled) {
		this.id = id;
		this.name = name;
		this.enabled = enabled;
	}

	public Module addSetting(Setting setting) {
		settings.add(setting);
		return this;
	}

	public List<Setting> getSettings() {
		return Collections.unmodifiableList(settings);
	}

	public Setting getSetting(String id) {
		for (Setting setting : settings) {
			if (setting.id().equals(id)) {
				return setting;
			}
		}
		return null;
	}
}