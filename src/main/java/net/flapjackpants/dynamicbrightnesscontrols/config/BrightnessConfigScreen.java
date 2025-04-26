package net.flapjackpants.dynamicbrightnesscontrols.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class BrightnessConfigScreen {

    public static Screen create(Screen parent) {
        ModConfig config = ModConfig.get();
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.literal("Daylite"));

        builder.setSavingRunnable(() -> {
            // Save config to file here if you implement persistence
            System.out.println("Saved brightness config!");
        });

        ConfigCategory gammaSettings = builder.getOrCreateCategory(Text.literal("Daylite"));

        builder.setSavingRunnable(ModConfig::save);
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        gammaSettings.addEntry(entryBuilder.startBooleanToggle(
                        Text.literal("Enable Dynamic Brightness"), ModConfig.get().modEnabled)
                .setDefaultValue(true)
                .setTooltip(Text.literal("Toggle all Daylite mod features"))
                .setSaveConsumer(newValue -> ModConfig.get().modEnabled = newValue)
                .build()
        );

        gammaSettings.addEntry(entryBuilder.startDoubleField(
                        Text.literal("Overworld Noon Gamma (0.0 - 1.0)"), ModConfig.get().overworldGamma)
                .setDefaultValue(0.0)
                .setTooltip(Text.literal("Lowest overworld outside gamma"))
                .setMin(0.0)
                .setMax(20.0)
                .setSaveConsumer(newValue -> ModConfig.get().overworldGamma = newValue)
                .build()
        );

        gammaSettings.addEntry(entryBuilder.startDoubleField(
                        Text.literal("Overworld Midnight Gamma (0.0 - 1.0)"), ModConfig.get().nightGamma)
                .setDefaultValue(1.0)
                .setTooltip(Text.literal("Highest overworld outside gamma"))
                .setMin(0.0)
                .setMax(20.0)
                .setSaveConsumer(newValue -> ModConfig.get().nightGamma = newValue)
                .build()
        );

        gammaSettings.addEntry(entryBuilder.startDoubleField(
                        Text.literal("Overworld Cave Gamma (0.0 - 1.0)"), ModConfig.get().caveGamma)
                .setDefaultValue(1.0)
                .setTooltip(Text.literal("Overworld gamma in areas outside natural sky light"))
                .setMin(0.0)
                .setMax(20.0)
                .setSaveConsumer(newValue -> ModConfig.get().caveGamma = newValue)
                .build()
        );

        gammaSettings.addEntry(entryBuilder.startDoubleField(
                        Text.literal("Nether Gamma (0.0 - 1.0)"), ModConfig.get().netherGamma)
                .setDefaultValue(1.0)
                .setTooltip(Text.literal("Gamma all over the nether"))
                .setMin(0.0)
                .setMax(20.0)
                .setSaveConsumer(newValue -> ModConfig.get().netherGamma = newValue)
                .build()
        );

        gammaSettings.addEntry(entryBuilder.startDoubleField(
                        Text.literal("End Gamma (0.0 - 1.0)"), ModConfig.get().endGamma)
                .setDefaultValue(1.0)
                .setTooltip(Text.literal("Gamma all over the end"))
                .setMin(0.0)
                .setMax(20.0)
                .setSaveConsumer(newValue -> ModConfig.get().endGamma = newValue)
                .build()
        );

        gammaSettings.addEntry(entryBuilder.startDoubleField(
                        Text.literal("Transition Time"), ModConfig.get().gammaTransitionTime)
                .setDefaultValue(5.0)
                .setTooltip(Text.literal("Seconds it takes to transition from one gamma value to another"))
                .setMin(0.1)
                .setSaveConsumer(newValue -> ModConfig.get().gammaTransitionTime = newValue)
                .build()
        );



        return builder.build();
    }
}

