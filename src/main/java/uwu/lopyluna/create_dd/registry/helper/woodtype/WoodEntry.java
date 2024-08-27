package uwu.lopyluna.create_dd.registry.helper.woodtype;

import static com.simibubi.create.foundation.data.WindowGen.woodenWindowBlock;
import static com.simibubi.create.foundation.data.WindowGen.woodenWindowPane;

import com.simibubi.create.content.decoration.palettes.ConnectedGlassPaneBlock;
import com.simibubi.create.content.decoration.palettes.WindowBlock;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class WoodEntry {
    public WoodType woodType;

    public BlockEntry<? extends Block> plank;
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


    public WoodEntry(WoodType woodType,

                     BlockEntry<? extends Block> plank, BlockEntry<? extends Block> slab, BlockEntry<? extends Block> stairs,
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
