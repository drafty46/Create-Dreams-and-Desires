package uwu.lopyluna.create_dd;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import uwu.lopyluna.create_dd.infrastructure.client.DebugOutliner;
import uwu.lopyluna.create_dd.infrastructure.gui.DesiresBaseConfigScreen;
import uwu.lopyluna.create_dd.infrastructure.ponder.DesirePonderTags;
import uwu.lopyluna.create_dd.infrastructure.ponder.DesiresPonderIndex;
import uwu.lopyluna.create_dd.registry.DesiresParticleTypes;

import static uwu.lopyluna.create_dd.DesiresCreate.MOD_ID;

@SuppressWarnings({"unused"})
public class DesireClient {
    
    public static DebugOutliner DEBUG_OUTLINER = new DebugOutliner();

    public static void onCtorClient(IEventBus modEventBus, IEventBus forgeEventBus) {
        modEventBus.addListener(DesireClient::clientInit);
        modEventBus.addListener(DesiresParticleTypes::registerFactories);
    }

    public static void clientInit(final FMLClientSetupEvent event) {

        ModLoadingContext.get().getActiveContainer().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory((minecraft, screen) -> new DesiresBaseConfigScreen(screen, MOD_ID)));

        DesirePonderTags.register();
        DesiresPonderIndex.register();
    }


    @Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModBusEvents {

        @SubscribeEvent
        public static void onLoadComplete(FMLLoadCompleteEvent event) {
            ModContainer createContainer = ModList.get()
                    .getModContainerById(MOD_ID)
                    .orElseThrow(() -> new IllegalStateException("Create mod container missing on LoadComplete"));
            createContainer.registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                    () -> new ConfigScreenHandler.ConfigScreenFactory(
                            (mc, previousScreen) -> DesiresBaseConfigScreen.forDesires(previousScreen)));
        }

    }

}
