package net.flapjackpants.dynamicbrightnesscontrols;

import java.lang.reflect.Method;

import net.fabricmc.loader.api.FabricLoader;

public final class TweakerooCompat {
	private static final String[] FEATURE_TOGGLE_CLASSES = {
			"fi.dy.masa.tweakeroo.config.FeatureToggle",
			"tweakeroo.config.FeatureToggle"
	};

	private static boolean resolved;
	private static Object gammaOverrideToggle;
	private static Method getBooleanValue;

	private TweakerooCompat() {
	}

	public static boolean isGammaOverrideEnabled() {
		if (!FabricLoader.getInstance().isModLoaded("tweakeroo")) {
			return false;
		}

		resolve();
		if (gammaOverrideToggle == null || getBooleanValue == null) {
			return false;
		}

		try {
			return (Boolean) getBooleanValue.invoke(gammaOverrideToggle);
		} catch (ReflectiveOperationException e) {
			return false;
		}
	}

	private static void resolve() {
		if (resolved) {
			return;
		}
		resolved = true;

		for (String className : FEATURE_TOGGLE_CLASSES) {
			try {
				Class<?> featureToggle = Class.forName(className);
				Object toggle = findEnumConstant(featureToggle, "TWEAK_GAMMA_OVERRIDE");
				if (toggle == null) {
					continue;
				}
				Method method = featureToggle.getMethod("getBooleanValue");
				gammaOverrideToggle = toggle;
				getBooleanValue = method;
				return;
			} catch (ClassNotFoundException | NoSuchMethodException ignored) {
			}
		}
	}

	private static Object findEnumConstant(Class<?> enumClass, String name) {
		Object[] constants = enumClass.getEnumConstants();
		if (constants == null) {
			return null;
		}
		for (Object constant : constants) {
			if (constant instanceof Enum<?> value && value.name().equals(name)) {
				return constant;
			}
		}
		return null;
	}
}
