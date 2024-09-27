package uwu.lopyluna.create_dd.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import uwu.lopyluna.create_dd.Desires;

public class DesiresSoundEvents {
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
			DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Desires.MOD_ID);

	public static RegistryObject<SoundEvent> CREATVEDITE_BREAK = registerSoundEvent("creatvedite_break");
	public static RegistryObject<SoundEvent> CREATVEDITE_STEP = registerSoundEvent("creatvedite_step");
	public static RegistryObject<SoundEvent> CREATVEDITE_PLACE = registerSoundEvent("creatvedite_place");
	public static RegistryObject<SoundEvent> CREATVEDITE_HIT = registerSoundEvent("creatvedite_hit");
	public static RegistryObject<SoundEvent> CREATVEDITE_FALL = registerSoundEvent("creatvedite_fall");
	public static RegistryObject<SoundEvent> CRACKLE_BREAK = registerSoundEvent("crackle_break");
	public static RegistryObject<SoundEvent> CRACKLE_STEP = registerSoundEvent("crackle_step");
	public static RegistryObject<SoundEvent> CRACKLE_PLACE = registerSoundEvent("crackle_place");
	public static RegistryObject<SoundEvent> CRACKLE_HIT = registerSoundEvent("crackle_hit");
	public static RegistryObject<SoundEvent> CRACKLE_FALL = registerSoundEvent("crackle_fall");
	public static RegistryObject<SoundEvent> RUBBER_BREAK = registerSoundEvent("rubber_break");
	public static RegistryObject<SoundEvent> RUBBER_PLACE = registerSoundEvent("rubber_place");
	public static RegistryObject<SoundEvent> BLOCK_ZAPPER_UPGRADE = registerSoundEvent("block_zapper_upgrade");
	public static RegistryObject<SoundEvent> MUSIC_DISC_WALTZ_OF_THE_FLOWERS = registerSoundEvent("music_disc.waltz_of_the_flowers");

	public static SoundType CREATVEDITE = new ForgeSoundType(0.8f, .8f, () -> DesiresSoundEvents.CREATVEDITE_BREAK.get(),
			() -> DesiresSoundEvents.CREATVEDITE_STEP.get(), () -> DesiresSoundEvents.CREATVEDITE_PLACE.get(),
			() -> DesiresSoundEvents.CREATVEDITE_HIT.get(), () -> DesiresSoundEvents.CREATVEDITE_FALL.get());

	public static SoundType RUBBER = new ForgeSoundType(0.9f, .6f, () -> DesiresSoundEvents.RUBBER_BREAK.get(),
			() -> SoundEvents.STEM_STEP, () -> DesiresSoundEvents.RUBBER_PLACE.get(),
			() -> SoundEvents.STEM_HIT, () -> SoundEvents.STEM_FALL);

	public static SoundType CRACKLE_STONE = new ForgeSoundType(0.9f, 0.7f, () -> DesiresSoundEvents.CRACKLE_BREAK.get(),
			() -> DesiresSoundEvents.CRACKLE_STEP.get(), () -> DesiresSoundEvents.CRACKLE_PLACE.get(),
			() -> DesiresSoundEvents.CRACKLE_HIT.get(), () -> DesiresSoundEvents.CRACKLE_FALL.get());

	private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
		return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(Desires.MOD_ID, name)));
	}

	public static void register(IEventBus eventBus) {
		SOUND_EVENTS.register(eventBus);
	}
}
