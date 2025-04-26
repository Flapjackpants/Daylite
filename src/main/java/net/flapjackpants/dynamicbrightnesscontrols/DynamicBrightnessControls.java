package net.flapjackpants.dynamicbrightnesscontrols;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import net.flapjackpants.dynamicbrightnesscontrols.config.ModConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicBrightnessControls implements ModInitializer {

	@Override
	public void onInitialize() {
		// Initialization logic here
		ModConfig.load();
		System.out.println("Simple Brightness Transitions initialized!");
		// Maybe register events here
	}
}

