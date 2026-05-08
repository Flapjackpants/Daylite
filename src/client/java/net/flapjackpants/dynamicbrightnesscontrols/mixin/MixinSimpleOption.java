package net.flapjackpants.dynamicbrightnesscontrols.mixin;

import net.minecraft.client.OptionInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(OptionInstance.class)
public interface MixinSimpleOption<T> {
	@Accessor("value")
	void setValueNoCheck(T value);
}
