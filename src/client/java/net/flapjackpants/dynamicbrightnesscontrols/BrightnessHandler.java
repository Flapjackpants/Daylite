package net.flapjackpants.dynamicbrightnesscontrols;

import net.flapjackpants.dynamicbrightnesscontrols.config.ModConfig;
import net.flapjackpants.dynamicbrightnesscontrols.mixin.MixinSimpleOption;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.Holder;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;

public class BrightnessHandler {
	private static final Minecraft client = Minecraft.getInstance();
	private static double targetGamma = 0.0;

	public static void updateBrightness() {
		if (client.player == null || client.level == null || !ModConfig.get().modEnabled) return;

		ClientLevel level = client.level;
		Holder<DimensionType> dimType = level.dimensionTypeRegistration();

		int skyLight = level.getLightEngine().getLayerListener(LightLayer.SKY).getLightValue(client.player.blockPosition());
		boolean hasSkyLight = skyLight > 7;
		double day = ModConfig.get().overworldGamma;
		double night = ModConfig.get().nightGamma;
		double radTime = level.getOverworldClockTime() / 12000.0 * Math.PI;

		if (dimType.is(BuiltinDimensionTypes.OVERWORLD) || dimType.is(BuiltinDimensionTypes.OVERWORLD_CAVES)) {
			targetGamma = Math.max((Math.min((night - day) * (-.707107) * Math.sin(radTime) + (night + day) / 2.0, night)), day);
			if (!hasSkyLight) targetGamma = ModConfig.get().caveGamma;
		} else if (dimType.is(BuiltinDimensionTypes.NETHER)) {
			targetGamma = ModConfig.get().netherGamma;
		} else if (dimType.is(BuiltinDimensionTypes.END)) {
			targetGamma = ModConfig.get().endGamma;
		}

		Options options = client.options;
		double currentGamma = options.gamma().get();
		if (currentGamma < targetGamma) {
			currentGamma = Math.round(Math.min(currentGamma + (targetGamma - currentGamma) / ModConfig.get().gammaTransitionTime / 10.0, targetGamma) * 10000) / 10000.0;
		} else if (currentGamma > targetGamma) {
			currentGamma = Math.round(Math.max(currentGamma + (targetGamma - currentGamma) / ModConfig.get().gammaTransitionTime / 10.0, targetGamma) * 10000) / 10000.0;
		}

		@SuppressWarnings("unchecked")
		MixinSimpleOption<Double> gammaOption = (MixinSimpleOption<Double>) (Object) options.gamma();
		gammaOption.setValueNoCheck(currentGamma);
	}
}
