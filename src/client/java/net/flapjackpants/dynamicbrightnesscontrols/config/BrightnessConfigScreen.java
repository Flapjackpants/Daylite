package net.flapjackpants.dynamicbrightnesscontrols.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class BrightnessConfigScreen {

	public static Screen create(Screen parent) {
		ConfigBuilder builder = ConfigBuilder.create()
				.setParentScreen(parent)
				.setTitle(Component.literal("Daylite"));

		builder.setSavingRunnable(() -> System.out.println("Saved brightness config!"));

		ConfigCategory gammaSettings = builder.getOrCreateCategory(Component.literal("Daylite"));

		builder.setSavingRunnable(ModConfig::save);
		ConfigEntryBuilder entryBuilder = builder.entryBuilder();

		gammaSettings.addEntry(entryBuilder.startBooleanToggle(
						Component.literal("Enable Dynamic Brightness"), ModConfig.get().modEnabled)
				.setDefaultValue(true)
				.setTooltip(Component.literal("Toggle all Daylite mod features"))
				.setSaveConsumer(newValue -> ModConfig.get().modEnabled = newValue)
				.build()
		);

		gammaSettings.addEntry(entryBuilder.startDoubleField(
						Component.literal("Overworld Noon Gamma (0.0 - 1.0)"), ModConfig.get().overworldGamma)
				.setDefaultValue(0.0)
				.setTooltip(Component.literal("Lowest overworld outside gamma"))
				.setMin(0.0)
				.setMax(20.0)
				.setSaveConsumer(newValue -> ModConfig.get().overworldGamma = newValue)
				.build()
		);

		gammaSettings.addEntry(entryBuilder.startDoubleField(
						Component.literal("Overworld Midnight Gamma (0.0 - 1.0)"), ModConfig.get().nightGamma)
				.setDefaultValue(1.0)
				.setTooltip(Component.literal("Highest overworld outside gamma"))
				.setMin(0.0)
				.setMax(20.0)
				.setSaveConsumer(newValue -> ModConfig.get().nightGamma = newValue)
				.build()
		);

		gammaSettings.addEntry(entryBuilder.startDoubleField(
						Component.literal("Overworld Cave Gamma (0.0 - 1.0)"), ModConfig.get().caveGamma)
				.setDefaultValue(1.0)
				.setTooltip(Component.literal("Overworld gamma in areas outside natural sky light"))
				.setMin(0.0)
				.setMax(20.0)
				.setSaveConsumer(newValue -> ModConfig.get().caveGamma = newValue)
				.build()
		);

		gammaSettings.addEntry(entryBuilder.startDoubleField(
						Component.literal("Nether Gamma (0.0 - 1.0)"), ModConfig.get().netherGamma)
				.setDefaultValue(1.0)
				.setTooltip(Component.literal("Gamma all over the nether"))
				.setMin(0.0)
				.setMax(20.0)
				.setSaveConsumer(newValue -> ModConfig.get().netherGamma = newValue)
				.build()
		);

		gammaSettings.addEntry(entryBuilder.startDoubleField(
						Component.literal("End Gamma (0.0 - 1.0)"), ModConfig.get().endGamma)
				.setDefaultValue(1.0)
				.setTooltip(Component.literal("Gamma all over the end"))
				.setMin(0.0)
				.setMax(20.0)
				.setSaveConsumer(newValue -> ModConfig.get().endGamma = newValue)
				.build()
		);

		gammaSettings.addEntry(entryBuilder.startDoubleField(
						Component.literal("Transition Time"), ModConfig.get().gammaTransitionTime)
				.setDefaultValue(5.0)
				.setTooltip(Component.literal("Seconds it takes to transition from one gamma value to another"))
				.setMin(0.1)
				.setSaveConsumer(newValue -> ModConfig.get().gammaTransitionTime = newValue)
				.build()
		);

		return builder.build();
	}
}
