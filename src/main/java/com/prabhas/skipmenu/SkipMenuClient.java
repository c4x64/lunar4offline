package com.prabhas.skipmenu;

import com.prabhas.skipmenu.module.Module;
import com.prabhas.skipmenu.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkipMenuClient implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("skipmenu");
	public static final SkipMenuConfig CONFIG = SkipMenuConfig.load();
	public static final ModuleManager MODULES = new ModuleManager();

	@Override
	public void onInitializeClient() {
		OfflineLogin.apply(CONFIG.offlineUsername);
		MODULES.loadFromConfig(CONFIG);

		Module x = MODULES.get("x");
		LOGGER.info(
			"SkipMenu loaded. Offline user = '{}', module X = {}.",
			OfflineLogin.getUsername(),
			x == null ? "?" : x.enabled
		);
	}
}