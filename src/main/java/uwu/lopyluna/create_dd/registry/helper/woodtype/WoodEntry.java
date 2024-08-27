package uwu.lopyluna.create_dd.registry.helper.woodtype;

import static com.simibubi.create.foundation.data.WindowGen.woodenWindowBlock;
import static com.simibubi.create.foundation.data.WindowGen.woodenWindowPane;
import static uwu.lopyluna.create_dd.DesiresCreate.REGISTRATE;

import com.simibubi.create.content.decoration.palettes.ConnectedGlassPaneBlock;
import com.simibubi.create.content.decoration.palettes.WindowBlock;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.core.Holder;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
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


    public static WoodEntry material(String name, WoodTypes type, boolean flammable, SoundType pSoundType) {
        boolean isWood = type == WoodTypes.WOOD;
        boolean hasBoat = type == WoodTypes.WOOD_BOAT;
        boolean hasTree = type == WoodTypes.WOOD_TREE;
        boolean hasSign = type == WoodTypes.WOOD_SIGN;
        boolean hasSlidingDoor = type == WoodTypes.WOOD_SLIDING_DOOR;


        NonNullSupplier<? extends CreativeModeTab> tab = () -> DesiresCreativeModeTabs.PALETTES_CREATIVE_TAB;
        String id = name.toLowerCase().replace(" ", "_");

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

        if (type == WoodTypes.ALL) {
            woodType = WoodType.create(id);

            plank = REGISTRATE.block(id + "_planks", flammable ? p -> CombustibleBlock.create(p, 5, 20) : CombustibleBlock::create)
                    .initialProperties(() -> Blocks.OAK_PLANKS)
                    .lang(name + " Planks")
                    .item().tab(tab).build()
                    .register();
            slab = REGISTRATE.block(id + "_slab", flammable ? p -> CombustibleSlabBlock.create(p, 5, 20) : CombustibleSlabBlock::create)
                    .initialProperties(() -> Blocks.OAK_SLAB)
                    .lang(name + " Slab")
                    .item().tab(tab).build()
                    .register();
            BlockState plankState = plank.get().defaultBlockState();
            stairs = REGISTRATE.block(id + "_stairs", flammable ? p -> CombustibleStairBlock.create(plankState, p, 5, 20) : p -> CombustibleStairBlock.create(plankState, p))
                    .initialProperties(() -> Blocks.OAK_STAIRS)
                    .lang(name + " Stairs")
                    .item().tab(tab).build()
                    .register();
            strippedLog = REGISTRATE.block("stripped_" + id + "_log", flammable ? p -> AxisBlock.create(p, 5, 5) : AxisBlock::create)
                    .initialProperties(() -> Blocks.STRIPPED_OAK_LOG)
                    .lang("Stripped " + name + " Log")
                    .item().tab(tab).build()
                    .register();
            BlockState strippedLogStateDefault = strippedLog.get().defaultBlockState();
            log = REGISTRATE.block(id + "_log", flammable ? p -> AxisBlock.create(p, strippedLogStateDefault, 5, 5) : p -> AxisBlock.create(p, strippedLogStateDefault))
                    .initialProperties(() -> Blocks.OAK_LOG)
                    .lang(name + " Log")
                    .item().tab(tab).build()
                    .register();
            strippedWood = REGISTRATE.block("stripped_" + id + "_wood", flammable ? p -> AxisBlock.create(p, 5, 5) : AxisBlock::create)
                    .initialProperties(() -> Blocks.STRIPPED_OAK_WOOD)
                    .lang("Stripped " + name + " Wood")
                    .item().tab(tab).build()
                    .register();
            BlockState strippedWoodStateDefault = strippedWood.get().defaultBlockState();
            wood = REGISTRATE.block(id + "_wood", flammable ? p -> AxisBlock.create(p, strippedWoodStateDefault, 5, 5) : p -> AxisBlock.create(p, strippedWoodStateDefault))
                    .initialProperties(() -> Blocks.OAK_WOOD)
                    .lang(name + " Wood")
                    .item().tab(tab).build()
                    .register();;
            fence = REGISTRATE.block(id + "_fence", flammable ? p -> CombustibleFenceBlock.create(p, 5, 20) : CombustibleFenceBlock::create)
                    .initialProperties(() -> Blocks.OAK_FENCE)
                    .lang(name + " Fence")
                    .item().tab(tab).build()
                    .register();;
            fenceGate = REGISTRATE.block(id + "_fence_gate", flammable ? p -> CombustibleFenceGateBlock.create(p, 5, 20) : CombustibleFenceGateBlock::create)
                    .initialProperties(() -> Blocks.OAK_FENCE_GATE)
                    .lang(name + " Fence Gate")
                    .item().tab(tab).build()
                    .register();;
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
            window = woodenWindowBlock(woodType, plank.get());
            window_pane = woodenWindowPane(woodType, window);

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
