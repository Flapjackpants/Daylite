package net.flapjackpants.dynamicbrightnesscontrols;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.flapjackpants.dynamicbrightnesscontrols.config.BrightnessConfigScreen;

public class DynamicBrightnessControlsModMenu implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return BrightnessConfigScreen::create;
	}
}
