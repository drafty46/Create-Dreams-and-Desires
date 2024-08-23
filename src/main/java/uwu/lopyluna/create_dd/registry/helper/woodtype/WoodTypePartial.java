package uwu.lopyluna.create_dd.registry.helper.woodtype;

import com.simibubi.create.AllTags;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonnullType;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.client.model.generators.ModelFile;
import uwu.lopyluna.create_dd.DesiresCreate;
import uwu.lopyluna.create_dd.content.blocks.functional.AxisBlock;
import uwu.lopyluna.create_dd.content.blocks.functional.DesireSlidingDoorBlock;
import uwu.lopyluna.create_dd.registry.DesiresWoodType;
import uwu.lopyluna.create_dd.registry.helper.Lang;

import java.util.List;
import java.util.function.Supplier;

import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;

public abstract class WoodTypePartial<B extends Block> {

    public static final WoodTypePartial<AxisBlock> LOG = new Log(false, false);
    public static final WoodTypePartial<AxisBlock> WOOD = new Log(true, false);
    public static final WoodTypePartial<AxisBlock> STRIPPED_LOG = new Log(false, true);
    public static final WoodTypePartial<AxisBlock> STRIPPED_WOOD = new Log(true, true);
    public static final WoodTypePartial<StairBlock> STAIR = new Stairs();
    public static final WoodTypePartial<SlabBlock> SLAB = new Slab();
    public static final WoodTypePartial<TrapDoorBlock> TRAPDOOR = new TrapDoor();
    public static final WoodTypePartial<DoorBlock> DOOR = new Door();
    public static final WoodTypePartial<DesireSlidingDoorBlock> SLIDING_DOOR = new SlidingDoor();
    public static final WoodTypePartial<FenceBlock> FENCE = new Fence();
    public static final WoodTypePartial<FenceGateBlock> FENCE_GATE = new FenceGate();
    public static final WoodTypePartial<WoodButtonBlock> BUTTON = new Button();
    public static final WoodTypePartial<PressurePlateBlock> PRESSURE_PLATE = new PressurePlate();

    public static final WoodTypePartial<?>[] ALL_PARTIALS = { LOG, WOOD, STRIPPED_LOG, STRIPPED_WOOD, STAIR, SLAB, TRAPDOOR, DOOR, FENCE, FENCE_GATE, BUTTON, PRESSURE_PLATE };
    public static final WoodTypePartial<?>[] ALL_PARTIALS_FANCY = { LOG, WOOD, STRIPPED_LOG, STRIPPED_WOOD, STAIR, SLAB, TRAPDOOR, SLIDING_DOOR, FENCE, FENCE_GATE, BUTTON, PRESSURE_PLATE };

    private final String name;

    private WoodTypePartial(String name) {
        this.name = name;
    }

    public @NonnullType BlockBuilder<B, CreateRegistrate> create(String variantName, WoodTypeBlockPattern pattern,
                                                                 BlockEntry<? extends Block> block, DesiresWoodType variant) {
        String patternName = Lang.nonPluralId(pattern.createName(variantName));
        String blockName = patternName + "_" + this.name;

        BlockBuilder<B, CreateRegistrate> blockBuilder = DesiresCreate.REGISTRATE
                .block(blockName, p -> createBlock(block))
                .blockstate((c, p) -> generateBlockState(c, p, variantName, pattern, block))
                .recipe((c, p) -> createRecipes(variant, block, c, p))
                .transform(b -> transformBlock(b, variantName, pattern));

        ItemBuilder<BlockItem, BlockBuilder<B, CreateRegistrate>> itemBuilder = blockBuilder.item()
                .transform(b -> transformItem(b, variantName, pattern));

        if (canRecycle())
            itemBuilder.tag(variant.materialTag);

        return itemBuilder.build();
    }


    protected ResourceLocation getTexture(String variantName, WoodTypeBlockPattern pattern, int index) {
        return WoodTypeBlockPattern.toLocation(variantName, pattern.getTexture(index));
    }

    protected BlockBuilder<B, CreateRegistrate> transformBlock(BlockBuilder<B, CreateRegistrate> builder,
                                                               String variantName, WoodTypeBlockPattern pattern) {
        getBlockTags().forEach(builder::tag);
        return builder.transform(pickaxeOnly());
    }

    protected ItemBuilder<BlockItem, BlockBuilder<B, CreateRegistrate>> transformItem(
            ItemBuilder<BlockItem, BlockBuilder<B, CreateRegistrate>> builder, String variantName, WoodTypeBlockPattern pattern) {
        getItemTags().forEach(builder::tag);
        return builder;
    }

    protected boolean canRecycle() {
        return true;
    }

    protected abstract Iterable<TagKey<Block>> getBlockTags();

    protected abstract Iterable<TagKey<Item>> getItemTags();

    protected abstract B createBlock(Supplier<? extends Block> block);

    protected abstract void createRecipes(DesiresWoodType type, BlockEntry<? extends Block> patternBlock,
                                          DataGenContext<Block, ? extends Block> c, RegistrateRecipeProvider p);

    protected abstract void generateBlockState(DataGenContext<Block, B> ctx, RegistrateBlockstateProvider prov,
                                               String variantName, WoodTypeBlockPattern pattern, Supplier<? extends Block> block);

    //AHHHHHHH

    private static class Stairs extends WoodTypePartial<StairBlock> {

        public Stairs() {
            super("stairs");
        }

        @Override
        protected StairBlock createBlock(Supplier<? extends Block> block) {
            return new StairBlock(() -> block.get()
                    .defaultBlockState(), BlockBehaviour.Properties.copy(block.get()));
        }

        @Override
        protected void generateBlockState(DataGenContext<Block, StairBlock> ctx, RegistrateBlockstateProvider prov,
                                          String variantName, WoodTypeBlockPattern pattern, Supplier<? extends Block> block) {
            prov.stairsBlock(ctx.get(), getTexture(variantName, pattern, 0));
        }

        @Override
        protected Iterable<TagKey<Block>> getBlockTags() {
            return List.of(BlockTags.STAIRS);
        }

        @Override
        protected Iterable<TagKey<Item>> getItemTags() {
            return List.of(ItemTags.STAIRS);
        }

        @Override
        protected void createRecipes(DesiresWoodType type, BlockEntry<? extends Block> patternBlock,
                                     DataGenContext<Block, ? extends Block> c, RegistrateRecipeProvider p) {
            p.stairs(DataIngredient.items(patternBlock), c, c.getName(), false);
            p.stonecutting(DataIngredient.tag(type.materialTag), c, 1);
        }

    }

    private static class Slab extends WoodTypePartial<SlabBlock> {

        public Slab() {
            super("slab");
        }

        @Override
        protected SlabBlock createBlock(Supplier<? extends Block> block) {
            return new SlabBlock(BlockBehaviour.Properties.copy(block.get()));
        }

        @Override
        protected boolean canRecycle() {
            return false;
        }

        @Override
        protected void generateBlockState(DataGenContext<Block, SlabBlock> ctx, RegistrateBlockstateProvider prov,
                                          String variantName, WoodTypeBlockPattern pattern, Supplier<? extends Block> block) {
            String name = ctx.getName();
            ResourceLocation mainTexture = getTexture(variantName, pattern, 0);

            ModelFile bottom = prov.models()
                    .slab(name, mainTexture, mainTexture, mainTexture);
            ModelFile top = prov.models()
                    .slabTop(name + "_top", mainTexture, mainTexture, mainTexture);
            ModelFile doubleSlab;

            doubleSlab = prov.models()
                    .getExistingFile(prov.modLoc(pattern.createName(variantName)));

            prov.slabBlock(ctx.get(), bottom, top, doubleSlab);
        }

        @Override
        protected Iterable<TagKey<Block>> getBlockTags() {
            return List.of(BlockTags.SLABS);
        }

        @Override
        protected Iterable<TagKey<Item>> getItemTags() {
            return List.of(ItemTags.SLABS);
        }

        @Override
        protected void createRecipes(DesiresWoodType type, BlockEntry<? extends Block> patternBlock,
                                     DataGenContext<Block, ? extends Block> c, RegistrateRecipeProvider p) {
            p.slab(DataIngredient.items(patternBlock), c, c.getName(), false);
            p.stonecutting(DataIngredient.tag(type.materialTag), c, 2);
            DataIngredient ingredient = DataIngredient.items(c.get());
            ShapelessRecipeBuilder.shapeless(patternBlock.get())
                    .requires(ingredient)
                    .requires(ingredient)
                    .unlockedBy("has_" + c.getName(), ingredient.getCritereon(p))
                    .save(p, DesiresCreate.MOD_ID + ":" + c.getName() + "_recycling");
        }

        @Override
        protected BlockBuilder<SlabBlock, CreateRegistrate> transformBlock(
                BlockBuilder<SlabBlock, CreateRegistrate> builder, String variantName, WoodTypeBlockPattern pattern) {
            builder.loot((lt, block) -> lt.add(block, RegistrateBlockLootTables.createSlabItemTable(block)));
            return super.transformBlock(builder, variantName, pattern);
        }

    }
    private static class Log extends WoodTypePartial<AxisBlock> {
        private final boolean isFlammable = false;
        private final boolean isStripped;
        private final boolean isWood;
        public Log(boolean isStripped, boolean isWood) {
            super(isWood ? "wood" : "log");
            this.isStripped = isStripped;
            this.isWood = isWood;
        }

        @Override
        protected Iterable<TagKey<Block>> getBlockTags() {
            return null;
        }

        @SuppressWarnings("unused")
        @Override
        protected Iterable<TagKey<Item>> getItemTags() {
            TagKey<Item> logsBurn = ItemTags.LOGS_THAT_BURN;
            TagKey<Item> logsNonBurn = ItemTags.NON_FLAMMABLE_WOOD;
            TagKey<Item> logsFlammable = isFlammable ? logsBurn : logsNonBurn;

            TagKey<Item> logs = ItemTags.LOGS;
            TagKey<Item> moddedStrippedWood = AllTags.AllItemTags.MODDED_STRIPPED_WOOD.tag;
            TagKey<Item> moddedStrippedLog = AllTags.AllItemTags.MODDED_STRIPPED_LOGS.tag;
            TagKey<Item> strippedWood = AllTags.AllItemTags.STRIPPED_WOOD.tag;
            TagKey<Item> strippedLog = AllTags.AllItemTags.STRIPPED_LOGS.tag;

            return isStripped ? isWood ? List.of(moddedStrippedWood, strippedWood) : List.of(moddedStrippedLog, strippedLog) : isWood ? List.of() : List.of(logs);
        }

        @Override
        protected AxisBlock createBlock(Supplier<? extends Block> block) {
            return null;
        }

        @Override
        protected void createRecipes(DesiresWoodType type, BlockEntry<? extends Block> patternBlock, DataGenContext<Block, ? extends Block> c, RegistrateRecipeProvider p) {

        }

        @Override
        protected void generateBlockState(DataGenContext<Block, AxisBlock> ctx, RegistrateBlockstateProvider prov, String variantName, WoodTypeBlockPattern pattern, Supplier<? extends Block> block) {

        }
    }

    private static class TrapDoor extends WoodTypePartial<TrapDoorBlock> {
        public TrapDoor() {
            super("trapdoor");
        }

        @Override
        protected Iterable<TagKey<Block>> getBlockTags() {
            return null;
        }

        @Override
        protected Iterable<TagKey<Item>> getItemTags() {
            return null;
        }

        @Override
        protected TrapDoorBlock createBlock(Supplier<? extends Block> block) {
            return null;
        }

        @Override
        protected void createRecipes(DesiresWoodType type, BlockEntry<? extends Block> patternBlock, DataGenContext<Block, ? extends Block> c, RegistrateRecipeProvider p) {

        }

        @Override
        protected void generateBlockState(DataGenContext<Block, TrapDoorBlock> ctx, RegistrateBlockstateProvider prov, String variantName, WoodTypeBlockPattern pattern, Supplier<? extends Block> block) {

        }
    }
    private static class SlidingDoor extends WoodTypePartial<DesireSlidingDoorBlock> {
        public SlidingDoor() {
            super("door");
        }

        @Override
        protected Iterable<TagKey<Block>> getBlockTags() {
            return null;
        }

        @Override
        protected Iterable<TagKey<Item>> getItemTags() {
            return null;
        }

        @Override
        protected DesireSlidingDoorBlock createBlock(Supplier<? extends Block> block) {
            return null;
        }

        @Override
        protected void createRecipes(DesiresWoodType type, BlockEntry<? extends Block> patternBlock, DataGenContext<Block, ? extends Block> c, RegistrateRecipeProvider p) {

        }

        @Override
        protected void generateBlockState(DataGenContext<Block, DesireSlidingDoorBlock> ctx, RegistrateBlockstateProvider prov, String variantName, WoodTypeBlockPattern pattern, Supplier<? extends Block> block) {

        }
    }

    private static class Door extends WoodTypePartial<DoorBlock> {
        public Door() {
            super("door");
        }

        @Override
        protected Iterable<TagKey<Block>> getBlockTags() {
            return null;
        }

        @Override
        protected Iterable<TagKey<Item>> getItemTags() {
            return null;
        }

        @Override
        protected DoorBlock createBlock(Supplier<? extends Block> block) {
            return null;
        }

        @Override
        protected void createRecipes(DesiresWoodType type, BlockEntry<? extends Block> patternBlock, DataGenContext<Block, ? extends Block> c, RegistrateRecipeProvider p) {

        }

        @Override
        protected void generateBlockState(DataGenContext<Block, DoorBlock> ctx, RegistrateBlockstateProvider prov, String variantName, WoodTypeBlockPattern pattern, Supplier<? extends Block> block) {

        }
    }
    private static class Fence extends WoodTypePartial<FenceBlock> {
        public Fence() {
            super("fence");
        }

        @Override
        protected Iterable<TagKey<Block>> getBlockTags() {
            return null;
        }

        @Override
        protected Iterable<TagKey<Item>> getItemTags() {
            return null;
        }

        @Override
        protected FenceBlock createBlock(Supplier<? extends Block> block) {
            return null;
        }

        @Override
        protected void createRecipes(DesiresWoodType type, BlockEntry<? extends Block> patternBlock, DataGenContext<Block, ? extends Block> c, RegistrateRecipeProvider p) {

        }

        @Override
        protected void generateBlockState(DataGenContext<Block, FenceBlock> ctx, RegistrateBlockstateProvider prov, String variantName, WoodTypeBlockPattern pattern, Supplier<? extends Block> block) {

        }
    }
    private static class FenceGate extends WoodTypePartial<FenceGateBlock> {
        public FenceGate() {
            super("fence_gate");
        }

        @Override
        protected Iterable<TagKey<Block>> getBlockTags() {
            return null;
        }

        @Override
        protected Iterable<TagKey<Item>> getItemTags() {
            return null;
        }

        @Override
        protected FenceGateBlock createBlock(Supplier<? extends Block> block) {
            return null;
        }

        @Override
        protected void createRecipes(DesiresWoodType type, BlockEntry<? extends Block> patternBlock, DataGenContext<Block, ? extends Block> c, RegistrateRecipeProvider p) {

        }

        @Override
        protected void generateBlockState(DataGenContext<Block, FenceGateBlock> ctx, RegistrateBlockstateProvider prov, String variantName, WoodTypeBlockPattern pattern, Supplier<? extends Block> block) {

        }
    }
    private static class Button extends WoodTypePartial<WoodButtonBlock> {
        public Button() {
            super("button");
        }

        @Override
        protected Iterable<TagKey<Block>> getBlockTags() {
            return null;
        }

        @Override
        protected Iterable<TagKey<Item>> getItemTags() {
            return null;
        }

        @Override
        protected WoodButtonBlock createBlock(Supplier<? extends Block> block) {
            return null;
        }

        @Override
        protected void createRecipes(DesiresWoodType type, BlockEntry<? extends Block> patternBlock, DataGenContext<Block, ? extends Block> c, RegistrateRecipeProvider p) {

        }

        @Override
        protected void generateBlockState(DataGenContext<Block, WoodButtonBlock> ctx, RegistrateBlockstateProvider prov, String variantName, WoodTypeBlockPattern pattern, Supplier<? extends Block> block) {

        }
    }
    private static class PressurePlate extends WoodTypePartial<PressurePlateBlock> {
        public PressurePlate() {
            super("pressure_plate");
        }

        @Override
        protected Iterable<TagKey<Block>> getBlockTags() {
            return null;
        }

        @Override
        protected Iterable<TagKey<Item>> getItemTags() {
            return null;
        }

        @Override
        protected PressurePlateBlock createBlock(Supplier<? extends Block> block) {
            return null;
        }

        @Override
        protected void createRecipes(DesiresWoodType type, BlockEntry<? extends Block> patternBlock, DataGenContext<Block, ? extends Block> c, RegistrateRecipeProvider p) {

        }

        @Override
        protected void generateBlockState(DataGenContext<Block, PressurePlateBlock> ctx, RegistrateBlockstateProvider prov, String variantName, WoodTypeBlockPattern pattern, Supplier<? extends Block> block) {

        }
    }

}
