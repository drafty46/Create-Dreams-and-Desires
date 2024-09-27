package uwu.lopyluna.create_dd.registry;

import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import uwu.lopyluna.create_dd.Desires;


public class DesiresEntityDataSerializers {
	private static final DeferredRegister<EntityDataSerializer<?>> REGISTER = DeferredRegister.create(ForgeRegistries.Keys.ENTITY_DATA_SERIALIZERS, Desires.MOD_ID);

	public static void register(IEventBus modEventBus) {
		REGISTER.register(modEventBus);
	}
}
