package net.flapjackpants.dynamicbrightnesscontrols;

import net.fabricmc.api.ModInitializer;

import net.flapjackpants.dynamicbrightnesscontrols.config.ModConfig;

public class DynamicBrightnessControls implements ModInitializer {

	@Override
	public void onInitialize() {
		// Initialization logic here
		ModConfig.load();
		System.out.println("Simple Brightness Transitions initialized!");
		// Maybe register events here
	}
}

