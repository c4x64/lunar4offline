package com.prabhas.skipmenu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;

public final class SkipMenuConfig {
	private static final File FILE = new File(
		System.getProperty("user.home"),
		".lunarclient/offline/multiver/skipmenu-config.json"
	);
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	public String offlineUsername = "123";
	public boolean moduleX = true;

	private SkipMenuConfig() {
	}

	public static SkipMenuConfig load() {
		try {
			if (FILE.isFile()) {
				SkipMenuConfig config = GSON.fromJson(new FileReader(FILE), SkipMenuConfig.class);
				if (config != null) {
					return config;
				}
			}
		} catch (Exception ignored) {
		}
		return new SkipMenuConfig();
	}

	public void save() {
		try {
			File parent = FILE.getParentFile();
			if (parent != null && !parent.isDirectory()) {
				parent.mkdirs();
			}
			Writer writer = new FileWriter(FILE);
			GSON.toJson(this, writer);
			writer.close();
		} catch (Exception ignored) {
		}
	}
}