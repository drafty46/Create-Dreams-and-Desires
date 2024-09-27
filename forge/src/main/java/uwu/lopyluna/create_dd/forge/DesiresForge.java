package uwu.lopyluna.create_dd.forge;

import uwu.lopyluna.create_dd.DesireClient;
import uwu.lopyluna.create_dd.Desires;
import uwu.lopyluna.create_dd.infrastructure.data.DesiresDatagen;
import uwu.lopyluna.create_dd.registry.DesiresFluids;
import uwu.lopyluna.create_dd.registry.DesiresWoodType;

public class DesiresForge {
    public DesiresForge() {
        Desires.init();

        ModLoadingContext modLoadingContext = ModLoadingContext.get();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

        REGISTRATE.registerEventListeners(modEventBus);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> DesiresPartialModels::init);

        modEventBus.addListener(DesiresForge::init);
        modEventBus.addListener(EventPriority.LOWEST, DesiresDatagen::gatherData);
        //modEventBus.addListener(DesiresAllSoundEvents::register);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> DesireClient.onCtorClient(modEventBus, forgeEventBus));

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        DesiresConfigs.register(modLoadingContext);
    }

    public static void init(final FMLCommonSetupEvent event) {
        DesiresFluids.registerFluidInteractions();

        event.enqueueWork(() -> DesiresWoodType.regStrippables());
    }
}
