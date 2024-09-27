package uwu.lopyluna.create_dd.infrastructure.world;

import com.simibubi.create.infrastructure.worldgen.LayeredOreFeature;
import com.simibubi.create.infrastructure.worldgen.StandardOreFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static uwu.lopyluna.create_dd.Desires.MOD_ID;

@SuppressWarnings("unused")
public class DesiresFeatures {
    private static final DeferredRegister<Feature<?>> REGISTER = DeferredRegister.create(ForgeRegistries.FEATURES, MOD_ID);

    public static final RegistryObject<StandardOreFeature> STANDARD_ORE = REGISTER.register("standard_ore", StandardOreFeature::new);
    public static final RegistryObject<LayeredOreFeature> LAYERED_ORE = REGISTER.register("layered_ore", LayeredOreFeature::new);

    public static void register(IEventBus modEventBus) {
        REGISTER.register(modEventBus);
    }
}
