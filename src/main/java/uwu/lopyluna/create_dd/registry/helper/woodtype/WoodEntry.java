package uwu.lopyluna.create_dd.registry.helper.woodtype;

import static com.simibubi.create.foundation.data.WindowGen.woodenWindowBlock;
import static com.simibubi.create.foundation.data.WindowGen.woodenWindowPane;
import static uwu.lopyluna.create_dd.DesiresCreate.REGISTRATE;

import com.simibubi.create.content.decoration.palettes.ConnectedGlassPaneBlock;
import com.simibubi.create.content.decoration.palettes.WindowBlock;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.ForgeRegistries;
import uwu.lopyluna.create_dd.DesiresCreate;
import uwu.lopyluna.create_dd.content.blocks.functional.AxisBlock;
import uwu.lopyluna.create_dd.content.blocks.functional.Combustible.*;
import uwu.lopyluna.create_dd.registry.DesiresCreativeModeTabs;

public class WoodEntry {
    public WoodType woodType;

    public BlockEntry<CombustibleBlock> plank;
    public BlockEntry<? extends Block> slab;
    public BlockEntry<? extends Block> stairs;
    public BlockEntry<? extends Block> log;
    public BlockEntry<? extends Block> strippedLog;
    public BlockEntry<? extends Block> wood;
    public BlockEntry<? extends Block> strippedWood;

    public BlockEntry<? extends Block> trapdoors;
    public BlockEntry<? extends Block> door;

    //WOOD_SLIDING_DOOR

    public BlockEntry<? extends Block> slidingDoor;
    public BlockEntityEntry<? extends BlockEntity> slidingDoorBE;

    //fences but no tag

    public BlockEntry<? extends Block> fence;
    public BlockEntry<? extends Block> fenceGate;
    public BlockEntry<? extends Block> pressurePlate;
    public BlockEntry<? extends Block> button;

    //WOOD_SIGN

    public BlockEntityEntry<? extends BlockEntity> signBE;
    public BlockEntry<? extends StandingSignBlock> standingSign;
    public BlockEntry<? extends WallSignBlock> wallSign;
    public BlockEntry<? extends Block> hangingSign1_20;
    public ItemEntry<? extends SignItem> sign;

    //WOOD_BOAT

    public ItemEntry<? extends Item> boat;
    public ItemEntry<? extends Item> chestBoat;

    //WOOD_SAPLING

    public BlockEntry<? extends Block> leaves;
    public BlockEntry<? extends Block> sapling;

    public Holder<ConfiguredFeature<TreeConfiguration, ?>> tree;
    public Holder<PlacedFeature> tree_checked;
    public Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> tree_spawn;

    public Holder<PlacedFeature>  tree_placed;

    public BlockEntry<WindowBlock> window;
    public BlockEntry<ConnectedGlassPaneBlock> window_pane;


    @SuppressWarnings("removal")
    public static WoodEntry create(String name, WoodTypes type, boolean hasSlidingDoor, boolean hasBoat, boolean hasSign, boolean hasTree, boolean flammable) {
        boolean isWood = type == WoodTypes.WOOD;
        boolean anyAll = isWood || type == WoodTypes.ALL;

        NonNullSupplier<? extends CreativeModeTab> tab = () -> DesiresCreativeModeTabs.PALETTES_CREATIVE_TAB;
        String id = name.toLowerCase().replace(" ", "_");

        String palettesPath = "palettes/wood_types/" + id + "/";
        String texturePath = "block/" + palettesPath;
        String texturePathID = texturePath + id;

        WoodType woodType;

        BlockEntry<CombustibleBlock> plank;
        BlockEntry<CombustibleSlabBlock> slab;
        BlockEntry<CombustibleStairBlock> stairs;
        BlockEntry<AxisBlock> log;
        BlockEntry<AxisBlock> strippedLog;
        BlockEntry<AxisBlock> wood;
        BlockEntry<AxisBlock> strippedWood;

        BlockEntry<CombustibleTrapdoorBlock> trapdoors;
        BlockEntry<? extends Block> door;

        BlockEntry<? extends Block> slidingDoor;
        BlockEntityEntry<? extends BlockEntity> slidingDoorBE;

        BlockEntry<CombustibleFenceBlock> fence;
        BlockEntry<CombustibleFenceGateBlock> fenceGate;
        BlockEntry<? extends Block> pressurePlate;
        BlockEntry<? extends Block> button;

        BlockEntityEntry<? extends BlockEntity> signBE;
        BlockEntry<? extends StandingSignBlock> standingSign;
        BlockEntry<? extends WallSignBlock> wallSign;
        BlockEntry<? extends Block> hangingSign1_20;
        ItemEntry<? extends SignItem> sign;

        ItemEntry<? extends Item> boat;
        ItemEntry<? extends Item> chestBoat;

        BlockEntry<? extends Block> leaves;
        BlockEntry<? extends Block> sapling;

        Holder<ConfiguredFeature<TreeConfiguration, ?>> tree;
        Holder<PlacedFeature> tree_checked;
        Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> tree_spawn;

        Holder<PlacedFeature>  tree_placed;

        BlockEntry<WindowBlock> window;
        BlockEntry<ConnectedGlassPaneBlock> window_pane;

        if (anyAll) {
            woodType = WoodType.create(id);

            plank = REGISTRATE.block(id + "_planks", flammable ? p -> CombustibleBlock.create(p, 5, 20) : CombustibleBlock::create)
                    .initialProperties(() -> Blocks.OAK_PLANKS)
                    .lang(name + " Planks")
                    .blockstate((c, p) -> BlockStateGen.cubeAll(c, p, palettesPath))
                    .item()
                    .tab(tab)
                    .build()
                    .register();
            ResourceLocation plankTextures = DesiresCreate.asResource(texturePathID + "_planks");
            slab = REGISTRATE.block(id + "_slab", flammable ? p -> CombustibleSlabBlock.create(p, 5, 20) : CombustibleSlabBlock::create)
                    .initialProperties(() -> Blocks.OAK_SLAB)
                    .lang(name + " Slab")
                    .blockstate((c, p) -> p.slabBlock(c.get(), DesiresCreate.asResource("block/" + id + "_planks"), plankTextures))
                    .item()
                    .tab(tab)
                    .build()
                    .register();
            stairs = REGISTRATE.block(id + "_stairs", flammable ? p -> CombustibleStairBlock.create(plank.get().defaultBlockState(), p, 5, 20) : p -> CombustibleStairBlock.create(plank.get().defaultBlockState(), p))
                    .initialProperties(() -> Blocks.OAK_STAIRS)
                    .lang(name + " Stairs")
                    .blockstate((c, p) -> p.stairsBlock(c.get(), plankTextures))
                    .item()
                    .tab(tab)
                    .build()
                    .register();
            String strippedSuffix = "block/palettes/wood_types/" + id + "/stripped_" + id;
            strippedLog = REGISTRATE.block("stripped_" + id + "_log", flammable ? p -> AxisBlock.create(p, 5, 5) : AxisBlock::create)
                    .initialProperties(() -> Blocks.STRIPPED_OAK_LOG)
                    .lang("Stripped " + name + " Log")
                    .blockstate((c, p) -> p.axisBlock(c.get(), DesiresCreate.asResource(strippedSuffix + "_log"), DesiresCreate.asResource(texturePathID + "_log_top")))
                    .item()
                    .tab(tab)
                    .build()
                    .register();
            log = REGISTRATE.block(id + "_log", flammable ? p -> AxisBlock.create(p, strippedLog.get().defaultBlockState(), 5, 5) : p -> AxisBlock.create(p, strippedLog.get().defaultBlockState()))
                    .initialProperties(() -> Blocks.OAK_LOG)
                    .lang(name + " Log")
                    .blockstate((c, p) -> p.axisBlock(c.get(), DesiresCreate.asResource(texturePathID + "_log"), DesiresCreate.asResource(texturePathID + "_log_top")))
                    .item()
                    .tab(tab)
                    .build()
                    .register();
            strippedWood = REGISTRATE.block("stripped_" + id + "_wood", flammable ? p -> AxisBlock.create(p, 5, 5) : AxisBlock::create)
                    .initialProperties(() -> Blocks.STRIPPED_OAK_WOOD)
                    .lang("Stripped " + name + " Wood")
                    .blockstate((c, p) -> p.axisBlock(c.get(), DesiresCreate.asResource(strippedSuffix + "_log"), DesiresCreate.asResource(strippedSuffix + "_log")))
                    .item()
                    .tab(tab)
                    .build()
                    .register();
            wood = REGISTRATE.block(id + "_wood", flammable ? p -> AxisBlock.create(p, strippedWood.get().defaultBlockState(), 5, 5) : p -> AxisBlock.create(p, strippedWood.get().defaultBlockState()))
                    .initialProperties(() -> Blocks.OAK_WOOD)
                    .lang(name + " Wood")
                    .blockstate((c, p) -> p.axisBlock(c.get(), DesiresCreate.asResource(texturePathID + "_log"), DesiresCreate.asResource(texturePathID + "_log")))
                    .item()
                    .tab(tab)
                    .build()
                    .register();
            fence = REGISTRATE.block(id + "_fence", flammable ? p -> CombustibleFenceBlock.create(p, 5, 20) : CombustibleFenceBlock::create)
                    .initialProperties(() -> Blocks.OAK_FENCE)
                    .lang(name + " Fence")

                    .blockstate((c, p) -> p.models().cubeAll(name(c.get()), plankTextures))
                    //.blockstate((c, p) -> p.fenceBlock(c.get(), plankTextures))

                    .item()
                    .tab(tab)
                    .build()
                    .register();
            fenceGate = REGISTRATE.block(id + "_fence_gate", flammable ? p -> CombustibleFenceGateBlock.create(p, 5, 20) : CombustibleFenceGateBlock::create)
                    .initialProperties(() -> Blocks.OAK_FENCE_GATE)
                    .lang(name + " Fence Gate")
                    .blockstate((c, p) -> p.fenceGateBlock(c.get(), plankTextures))
                    .item()
                    .tab(tab)
                    .build()
                    .register();
            pressurePlate = REGISTRATE.block(id + "_pressure_plate", p -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, p))
                    .initialProperties(() -> Blocks.OAK_BUTTON)
                    .lang(name + " Pressure Plate")
                    .blockstate((c, p) -> p.pressurePlateBlock(c.get(), plankTextures))
                    .item()
                    .tab(tab)
                    .build()
                    .register();
            button = REGISTRATE.block(id + "_button", WoodButtonBlock::new)
                    .initialProperties(() -> Blocks.OAK_BUTTON)
                    .lang(name + " Button")
                    .blockstate((c, p) -> p.buttonBlock(c.get(), plankTextures))
                    .item()
                    .tab(tab)
                    .build()
                    .register();
            trapdoors = REGISTRATE.block(id + "_trapdoor", flammable ? p -> CombustibleTrapdoorBlock.create(p, 5, 20) : CombustibleTrapdoorBlock::create)
                    .initialProperties(() -> Blocks.OAK_TRAPDOOR)
                    .properties(BlockBehaviour.Properties::noOcclusion)
                    .addLayer(() -> RenderType::cutoutMipped)
                    .lang(name + " Trapdoor")
                    .blockstate((c, p) -> p.models().cubeAll(name(c.get()), plankTextures))
                    //.blockstate((c, p) -> p.trapdoorBlock(c.get(), DesiresCreate.asResource(texturePathID + "_trapdoor"), true))
                    .item()
                    .tab(tab)
                    .build()
                    .register();
            if (hasSlidingDoor) {
                slidingDoor = null;
                slidingDoorBE = null;

                door = null;
            } else {
                door = REGISTRATE.block(id + "_door", DoorBlock::new)
                        .initialProperties(() -> Blocks.OAK_DOOR)
                        .properties(p -> p.sound(SoundType.WOOD).noOcclusion())
                        .addLayer(() -> RenderType::cutoutMipped)
                        .lang(name + " Door")
                        .blockstate((c, p) -> p.models().cubeAll(name(c.get()), plankTextures))
                        //.blockstate((c, p) -> p.doorBlock(c.get(), DesiresCreate.asResource(texturePathID + "_door_bottom"), DesiresCreate.asResource(texturePathID + "_door_top")))
                        .item()
                        .tab(tab)
                        .build()
                        .register();

                slidingDoor = null;
                slidingDoorBE = null;
            }
            if (plank.isPresent()) {
                window = woodenWindowBlock(woodType, plank.get());
                window_pane = woodenWindowPane(woodType, window);
            } else {
                window = null;
                window_pane = null;
            }
            if (hasSign) {
                signBE = null;
                standingSign = null;
                wallSign = null;
                sign = null;

                hangingSign1_20 = null;
            } else {
                signBE = null;
                standingSign = null;
                wallSign = null;
                hangingSign1_20 = null;
                sign = null;
            }

            if (hasBoat) {
                boat = REGISTRATE.item(id + "_boat", p -> new BoatItem(false, Boat.Type.OAK, p))
                        .tab(tab)
                        .register();
                chestBoat = REGISTRATE.item(id + "_chest_boat", p -> new BoatItem(true, Boat.Type.OAK, p))
                        .tab(tab)
                        .register();

            } else {
                boat = null;
                chestBoat = null;
            }
            if (hasTree) {
                tree = null;
                tree_checked = null;
                tree_spawn = null;
                tree_placed = null;

                leaves = REGISTRATE.block(id + "_leaves", p -> CombustibleLeavesBlock.create(p, true))
                        .initialProperties(() -> Blocks.AZALEA_LEAVES)
                        .lang(name + " Leaves")
                        .blockstate((c, p) -> BlockStateGen.cubeAll(c, p, texturePathID + "_leaves"))
                        .simpleItem()
                        .register();
                sapling = null;
            } else {
                tree = null;
                tree_checked = null;
                tree_spawn = null;
                tree_placed = null;

                leaves = null;
                sapling = null;
            }

        } else {
            woodType = null;
            plank = null;
            slab = null;
            stairs = null;
            log = null;
            strippedLog = null;
            wood = null;
            strippedWood = null;
            fence = null;
            fenceGate = null;
            pressurePlate = null;
            button = null;
            trapdoors = null;
            door = null;
            slidingDoor = null;
            slidingDoorBE = null;
            signBE = null;
            standingSign = null;
            wallSign = null;
            hangingSign1_20 = null;
            sign = null;
            boat = null;
            chestBoat = null;
            leaves = null;
            sapling = null;
            tree = null;
            tree_checked = null;
            tree_spawn = null;
            tree_placed = null;
            window = null;
            window_pane = null;
        }

        return new WoodEntry(woodType, plank, slab, stairs, log, strippedLog, wood, strippedWood, fence, fenceGate, pressurePlate, button, trapdoors, door, slidingDoor, slidingDoorBE, signBE, standingSign, wallSign, hangingSign1_20, sign, boat, chestBoat, leaves, sapling, tree, tree_checked, tree_spawn, tree_placed, window, window_pane
        );
    }

    private static String name(Block block) {
        return key(block).getPath();
    }
    private static ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }



    public WoodEntry(WoodType woodType,

                     BlockEntry<CombustibleBlock> plank, BlockEntry<? extends Block> slab, BlockEntry<? extends Block> stairs,
                     BlockEntry<? extends Block> log, BlockEntry<? extends Block> strippedLog, BlockEntry<? extends Block> wood, BlockEntry<? extends Block> strippedWood, BlockEntry<? extends Block> fence, BlockEntry<? extends Block> fenceGate, BlockEntry<? extends Block> pressurePlate, BlockEntry<? extends Block> button,

                     BlockEntry<? extends Block> trapdoors, BlockEntry<? extends Block> door, BlockEntry<? extends Block> slidingDoor, BlockEntityEntry<? extends BlockEntity> slidingDoorBE,
                     BlockEntityEntry<? extends BlockEntity> signBE, BlockEntry<? extends StandingSignBlock> standingSign, BlockEntry<? extends WallSignBlock> wallSign, BlockEntry<? extends Block> hangingSign1_20, ItemEntry<? extends SignItem> sign,
                     ItemEntry<? extends Item> boat, ItemEntry<? extends Item> chestBoat,
                     BlockEntry<? extends Block> leaves, BlockEntry<? extends Block> sapling,
                     Holder<ConfiguredFeature<TreeConfiguration, ?>> tree, Holder<PlacedFeature> tree_checked, Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> tree_spawn, Holder<PlacedFeature>  tree_placed,
                     BlockEntry<WindowBlock> window, BlockEntry<ConnectedGlassPaneBlock> window_pane
    ) {
        this.woodType = woodType;
        this.plank = plank;
        this.slab = slab;
        this.stairs = stairs;
        this.log = log;
        this.strippedLog = strippedLog;
        this.wood = wood;
        this.strippedWood = strippedWood;
        this.fence = fence;
        this.fenceGate = fenceGate;
        this.pressurePlate = pressurePlate;
        this.button = button;
        this.trapdoors = trapdoors;
        this.door = door;
        this.slidingDoor = slidingDoor;
        this.slidingDoorBE = slidingDoorBE;
        this.signBE = signBE;
        this.standingSign = standingSign;
        this.wallSign = wallSign;
        this.hangingSign1_20 = hangingSign1_20;
        this.sign = sign;
        this.boat = boat;
        this.chestBoat = chestBoat;
        this.leaves = leaves;
        this.sapling = sapling;
        this.tree = tree;
        this.tree_checked = tree_checked;
        this.tree_spawn = tree_spawn;
        this.tree_placed = tree_placed;
        this.window = window;
        this.window_pane = window_pane;
    }


}
