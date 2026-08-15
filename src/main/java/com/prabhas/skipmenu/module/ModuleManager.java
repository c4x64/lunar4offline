package com.prabhas.skipmenu.module;

import com.prabhas.skipmenu.SkipMenuConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ModuleManager {
	private final List<Module> modules = new ArrayList<>();

	public ModuleManager() {
		modules.add(new Module("x", "X", true));
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
		Module x = get("x");
		if (x != null) {
			x.enabled = config.moduleX;
		}
	}

	public void saveToConfig(SkipMenuConfig config) {
		Module x = get("x");
		if (x != null) {
			config.moduleX = x.enabled;
		}
	}
}