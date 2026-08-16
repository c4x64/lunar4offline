package com.prabhas.skipmenu;

import com.prabhas.skipmenu.module.Module;
import com.prabhas.skipmenu.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

public class SkipMenuClient implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("skipmenu");
	public static final SkipMenuConfig CONFIG = SkipMenuConfig.load();
	public static final ModuleManager MODULES = new ModuleManager();

	private static final AtomicBoolean initialized = new AtomicBoolean();

	@Override
	public void onInitializeClient() {
		init();
	}

	public static void init() {
		if (!initialized.compareAndSet(false, true)) {
			return;
		}
		try {
			OfflineLogin.apply(CONFIG.offlineUsername);
			MODULES.loadFromConfig(CONFIG);

			Module nametag = MODULES.get("nametag");
			LOGGER.info(
				"SkipMenu loaded. Offline user = '{}', nametag = {}.",
				OfflineLogin.getUsername(),
				nametag == null ? "?" : nametag.enabled
			);
		} catch (Throwable t) {
			LOGGER.warn("SkipMenu init failed: {}", t.toString(), t);
		}
	}

	public static void persistConfig() {
		MODULES.saveToConfig(CONFIG);
		CONFIG.save();
	}
}