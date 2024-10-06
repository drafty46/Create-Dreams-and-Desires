package uwu.lopyluna.create_dd;

import com.mojang.logging.LogUtils;
import com.simibubi.create.*;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipHelper;
import com.simibubi.create.foundation.item.TooltipModifier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import uwu.lopyluna.create_dd.compat.DesiresMods;
import uwu.lopyluna.create_dd.compat.registry.EncasedCompat;
import uwu.lopyluna.create_dd.content.blocks.kinetics.furnace_engine.FurnaceEngineBlock;
import uwu.lopyluna.create_dd.content.items.equipment.visor_helmet.XrayParticle;
import uwu.lopyluna.create_dd.infrastructure.config.DesiresConfigs;
import uwu.lopyluna.create_dd.infrastructure.data.DesiresDatagen;
import uwu.lopyluna.create_dd.infrastructure.world.DesireBuiltinRegistration;
import uwu.lopyluna.create_dd.infrastructure.world.DesiresFeatures;
import uwu.lopyluna.create_dd.infrastructure.world.DesiresPlacementModifiers;
import uwu.lopyluna.create_dd.registry.*;
import uwu.lopyluna.create_dd.registry.addons.DreamsAddons;
import uwu.lopyluna.create_dd.registry.darkness.MagicItems;
import uwu.lopyluna.create_dd.registry.delighted.EndItems;
import uwu.lopyluna.create_dd.registry.destinies.ExtrasItems;

import java.util.Random;


@SuppressWarnings({"deprecation", "unused"})
@Mod(DesiresCreate.MOD_ID)
public class DesiresCreate {
    
    public static final String NAME = "Create: Dreams n' Desires";
    public static final String MOD_ID = "create_dd";
    public static final String VERSION = "1.2b.Beta Mid-Dev";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final Random RANDOM = Create.RANDOM;

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);

    @Nullable
    public static KineticStats create(Item item) {
        if (item instanceof BlockItem blockItem) {
            Block block = blockItem.getBlock();
            if (block instanceof IRotate || block instanceof FurnaceEngineBlock) {
                return new KineticStats(block);
            }
        }
        return null;
    }

    static {
        REGISTRATE.setTooltipModifierFactory(item -> new ItemDescription.Modifier(item, TooltipHelper.Palette.STANDARD_CREATE)
                .andThen(TooltipModifier.mapNull(DesiresCreate.create(item))));
    }

    public DesiresCreate() {
        ModLoadingContext modLoadingContext = ModLoadingContext.get();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

        REGISTRATE.registerEventListeners(modEventBus);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> DesiresPartialModels::init);

        //DesiresAllSoundEvents.prepare();
        XrayParticle.regParticle.PARTICLES.register(modEventBus);
        DesiresSoundEvents.register(modEventBus);
        DesiresTags.init();
        DesiresCreativeModeTabs.init();
        DesiresSpriteShifts.register();
        DesiresWoodType.register();
        DesiresBlocks.register();
        DesiresItems.register();

        DesiresFluids.register();

        DesiresPaletteBlocks.register();
        ClassicBlocks.register();
        ClassicItems.register();
        ClassicBlockEntityTypes.register();
        DesiresEntityTypes.register();
        DesiresBlockEntityTypes.register();
        DesireFanProcessingTypes.register();
        DesiresRecipeTypes.register(modEventBus);
        DesiresParticleTypes.register(modEventBus);
        DesiresEntityDataSerializers.register(modEventBus);
        DesiresPackets.registerPackets();
        DesiresOreFeaturesEntries.init();
        DesiresPlacementModifiers.register(modEventBus);
        DesiresFeatures.register(modEventBus);
        DesireBuiltinRegistration.register(modEventBus);
        DesiresBlockMovementChecks.register();

        //ADDONS
        if (DreamsAddons.EXTRAS.isLoaded()) {
            ExtrasItems.register();
        }
        if (DreamsAddons.MAGIC.isLoaded()) {
            MagicItems.register();
        }
        if (DreamsAddons.ENDGAME.isLoaded()) {
            EndItems.register();
        }

        //COMPAT
        if (DesiresMods.CREATECASING.isLoaded()) {
            EncasedCompat.register();
        }


        DesiresConfigs.register(modLoadingContext);

        modEventBus.addListener(DesiresCreate::init);
        modEventBus.addListener(EventPriority.LOWEST, DesiresDatagen::gatherData);
        //modEventBus.addListener(DesiresAllSoundEvents::register);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> DesireClient.onCtorClient(modEventBus, forgeEventBus));

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static void init(final FMLCommonSetupEvent event) {
        DesiresFluids.registerFluidInteractions();

        event.enqueueWork(() -> DesiresWoodType.regStrippables());
    }
}
