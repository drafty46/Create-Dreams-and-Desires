package uwu.lopyluna.create_dd.infrastructure.world;

import com.simibubi.create.infrastructure.worldgen.ConfigDrivenPlacement;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static uwu.lopyluna.create_dd.Desires.MOD_ID;

public class DesiresPlacementModifiers {
    private static final DeferredRegister<PlacementModifierType<?>> REGISTER = DeferredRegister.create(Registry.PLACEMENT_MODIFIER_REGISTRY, MOD_ID);

    public static final RegistryObject<PlacementModifierType<? extends ConfigDrivenPlacement>> CONFIG_DRIVEN = REGISTER.register("config_driven", () -> () -> CDrivenPlacement.CODEC);

    public static void register(IEventBus modEventBus) {
        REGISTER.register(modEventBus);
    }
}
