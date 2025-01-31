package uwu.lopyluna.create_dd.registry;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.decoration.encasing.EncasedCTBehaviour;
import com.simibubi.create.content.decoration.palettes.AllPaletteStoneTypes;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.registries.ForgeRegistries;
import uwu.lopyluna.create_dd.DesiresCreate;

import static com.simibubi.create.foundation.data.CreateRegistrate.casingConnectivity;
import static com.simibubi.create.foundation.data.CreateRegistrate.connectedTextures;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;
import static com.tterrag.registrate.providers.RegistrateRecipeProvider.has;
import static uwu.lopyluna.create_dd.DesiresCreate.REGISTRATE;
import static uwu.lopyluna.create_dd.registry.DesiresTags.optionalTag;
import static uwu.lopyluna.create_dd.registry.helper.BlockTransformer.blueprintBlocks;
import static uwu.lopyluna.create_dd.registry.helper.BlockTransformer.rubber_decor;

@SuppressWarnings({"unused", "deprecation",  "all"})
public class DesiresPaletteBlocks {

	public static SoundType rubberSoundType = new ForgeSoundType(0.9f, .6f, () -> DesiresSoundEvents.RUBBER_BREAK.get(), () -> SoundEvents.STEM_STEP, () -> DesiresSoundEvents.RUBBER_PLACE.get(), () -> SoundEvents.STEM_HIT, () -> SoundEvents.STEM_FALL);
	public static TagKey<Item> rubberDecorTag = optionalTag(ForgeRegistries.ITEMS, new ResourceLocation("create_dd", "rubber_decor"));
	public static TagKey<Item> rawRubberDecorTag = optionalTag(ForgeRegistries.ITEMS, new ResourceLocation("create_dd", "raw_rubber_decor"));
	public static TagKey<Item> industrialIronDecorTag = optionalTag(ForgeRegistries.ITEMS, new ResourceLocation("create_dd", "industrial_iron_decor"));
	public static TagKey<Item> darkMetalDecorTag = optionalTag(ForgeRegistries.ITEMS, new ResourceLocation("create_dd", "dark_metal_decor"));
	public static TagKey<Item> asphaltBlocks = optionalTag(ForgeRegistries.ITEMS, new ResourceLocation("create_dd", "asphalt_blocks"));

	public static TagKey<Block> stairsBlockTag = optionalTag(ForgeRegistries.BLOCKS, new ResourceLocation("minecraft", "stairs"));
	public static TagKey<Item> stairsItemTag = optionalTag(ForgeRegistries.ITEMS, new ResourceLocation("minecraft", "stairs"));
	public static TagKey<Block> slabsBlockTag = optionalTag(ForgeRegistries.BLOCKS, new ResourceLocation("minecraft", "slabs"));
	public static TagKey<Item> slabsItemTag = optionalTag(ForgeRegistries.ITEMS, new ResourceLocation("minecraft", "slabs"));

	public static final BlockEntry<Block> HAZARD_BLOCK = REGISTRATE.block("hazard_block", Block::new)
			.properties(p -> p.destroyTime(1.25f)
					.speedFactor(0.8F)
					.jumpFactor(0.8F)
					.mapColor(MapColor.COLOR_BLACK)
					.sound(SoundType.POLISHED_DEEPSLATE))
			.blockstate((c, p) -> p.simpleBlock(c.get()))
			.onRegister(connectedTextures(() -> new EncasedCTBehaviour(DesiresSpriteShifts.HAZARD_BLOCK)))
			.onRegister(casingConnectivity((block, cc) -> cc.make(block, DesiresSpriteShifts.HAZARD_BLOCK)))
			.transform(pickaxeOnly())
			.recipe((c, p) -> p.stonecutting(DataIngredient.tag(asphaltBlocks), RecipeCategory.BUILDING_BLOCKS, c, 2))
			.tag(AllTags.AllBlockTags.WRENCH_PICKUP.tag)
			.item()
			.tab(DesiresCreativeModeTabs.PALETTES_CREATIVE_TAB.getKey())
			.build()
			.register();

	public static final BlockEntry<Block> DARK_METAL_BLOCK = REGISTRATE.block("dark_metal_block", Block::new)
			.properties(p -> p.mapColor(MapColor.COLOR_BLACK)
					.sound(SoundType.NETHERITE_BLOCK)
					.strength(0.5f,1.5f))
			.blockstate((c, p) -> p.simpleBlock(c.get()))
			.transform(pickaxeOnly())
			.recipe((c, p) -> {
				p.stonecutting(DataIngredient.items(AllBlocks.INDUSTRIAL_IRON_BLOCK.get()), RecipeCategory.BUILDING_BLOCKS, c, 2);
				p.stonecutting(DataIngredient.tag(darkMetalDecorTag), RecipeCategory.BUILDING_BLOCKS, c, 1);
				ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, c.get(), 4)
						.pattern("CC")
						.pattern("CC")
						.define('C', AllBlocks.INDUSTRIAL_IRON_BLOCK.get())
						.unlockedBy("has_" + c.getName(), has(c.get()))
						.save(p, DesiresCreate.asResource("crafting/" + c.getName() + "_from_" + c.getName()));
			})
			.tag(AllTags.AllBlockTags.WRENCH_PICKUP.tag)
			.item()
			.tab(DesiresCreativeModeTabs.PALETTES_CREATIVE_TAB.getKey())
			.tag(darkMetalDecorTag)
			.build()
			.register();

	public static final BlockEntry<Block> DARK_METAL_PLATING = REGISTRATE.block("dark_metal_plating", Block::new)
			.properties(p -> p.mapColor(MapColor.COLOR_BLACK)
					.sound(SoundType.NETHERITE_BLOCK)
					.strength(0.5f,1.5f))
			.blockstate((c, p) -> p.simpleBlock(c.get()))
			.onRegister(connectedTextures(() -> new EncasedCTBehaviour(DesiresSpriteShifts.DARK_METAL_PLATING)))
			.onRegister(casingConnectivity((block, cc) -> cc.make(block, DesiresSpriteShifts.DARK_METAL_PLATING)))
			.transform(pickaxeOnly())
			.recipe((c, p) -> {
				p.stonecutting(DataIngredient.items(AllBlocks.INDUSTRIAL_IRON_BLOCK.get()), RecipeCategory.BUILDING_BLOCKS, c, 2);
				p.stonecutting(DataIngredient.items(DARK_METAL_BLOCK.get()), RecipeCategory.BUILDING_BLOCKS, c, 1);
				p.stonecutting(DataIngredient.items(c.get()), RecipeCategory.BUILDING_BLOCKS, DARK_METAL_BLOCK, 1);
				p.stonecutting(DataIngredient.tag(darkMetalDecorTag), RecipeCategory.BUILDING_BLOCKS, c, 1);
				ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, c.get(), 9)
						.pattern("CCC")
						.pattern("CCC")
						.pattern("CCC")
						.define('C', DARK_METAL_BLOCK.get())
						.unlockedBy("has_" + c.getName(), has(c.get()))
						.save(p, DesiresCreate.asResource("crafting/" + c.getName() + "_from_" + c.getName()));
			})
			.tag(AllTags.AllBlockTags.WRENCH_PICKUP.tag)
			.item()
			.tab(DesiresCreativeModeTabs.PALETTES_CREATIVE_TAB.getKey())
			.tag(darkMetalDecorTag)
			.build()
			.register();

	public static final BlockEntry<SlabBlock> DARK_METAL_SLAB = REGISTRATE.block("dark_metal_block_slab", SlabBlock::new)
			.properties(p -> p.mapColor(MapColor.COLOR_BLACK)
					.sound(SoundType.NETHERITE_BLOCK)
					.strength(0.5f,1.5f))
			.blockstate((c, p) -> p.slabBlock(c.get(), DesiresCreate.asResource("block/dark_metal_block"),
					DesiresCreate.asResource("block/dark_metal_block_slab"),
					DesiresCreate.asResource("block/dark_metal_block"),
					DesiresCreate.asResource("block/dark_metal_block")))
			.transform(pickaxeOnly())
			.tag(stairsBlockTag)
			.recipe((c, p) -> {
				p.stonecutting(DataIngredient.items(AllBlocks.INDUSTRIAL_IRON_BLOCK.get()), RecipeCategory.BUILDING_BLOCKS, c, 4);
				p.stonecutting(DataIngredient.tag(darkMetalDecorTag), RecipeCategory.BUILDING_BLOCKS, c, 2);
				ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, c.get(), 6)
						.pattern("CCC")
						.define('C', DARK_METAL_BLOCK.get())
						.unlockedBy("has_" + c.getName(), has(c.get()))
						.save(p, DesiresCreate.asResource("crafting/" + c.getName() + "_from_" + c.getName()));
			})
			.tag(AllTags.AllBlockTags.WRENCH_PICKUP.tag)
			.item()
			.tab(DesiresCreativeModeTabs.PALETTES_CREATIVE_TAB.getKey())
			.tag(slabsItemTag)
			.build()
			.register();

	public static final BlockEntry<StairBlock> DARK_METAL_STAIRS = REGISTRATE.block("dark_metal_block_stairs", p -> new StairBlock(DesiresPaletteBlocks.DARK_METAL_BLOCK.getDefaultState(), p))
			.properties(p -> p.mapColor(MapColor.COLOR_BLACK)
					.sound(SoundType.NETHERITE_BLOCK)
					.strength(0.5f,1.5f))
			.blockstate((c, p) -> p.stairsBlock(c.get(), DesiresCreate.asResource("block/dark_metal_block")))
			.transform(pickaxeOnly())
			.tag(stairsBlockTag)
			.recipe((c, p) -> {
				p.stonecutting(DataIngredient.items(AllBlocks.INDUSTRIAL_IRON_BLOCK.get()), RecipeCategory.BUILDING_BLOCKS, c, 2);
				p.stonecutting(DataIngredient.tag(darkMetalDecorTag), RecipeCategory.BUILDING_BLOCKS, c, 1);
				ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, c.get(), 4)
						.pattern("X  ").pattern("XX ").pattern("XXX")
						.define('X', DARK_METAL_BLOCK.get())
						.unlockedBy("has_" + c.getName(), has(c.get()))
						.save(p, DesiresCreate.asResource("crafting/" + c.getName() + "_from_" + c.getName()));
			})
			.item()
			.tab(DesiresCreativeModeTabs.PALETTES_CREATIVE_TAB.getKey())
			.tag(darkMetalDecorTag, stairsItemTag)
			.build()
			.register();

	public static final BlockEntry<Block> DARK_METAL_BRICKS = REGISTRATE.block("dark_metal_bricks", Block::new)
			.properties(p -> p.mapColor(MapColor.COLOR_BLACK)
					.sound(SoundType.NETHERITE_BLOCK)
					.strength(0.5f,1.5f))
			.blockstate((c, p) -> p.simpleBlock(c.get()))
			.transform(pickaxeOnly())
			.recipe((c, p) -> {
				p.stonecutting(DataIngredient.items(AllBlocks.INDUSTRIAL_IRON_BLOCK.get()), RecipeCategory.BUILDING_BLOCKS, c, 2);
				p.stonecutting(DataIngredient.tag(darkMetalDecorTag), RecipeCategory.BUILDING_BLOCKS, c, 1);
				ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, c.get(), 4)
						.pattern("CC")
						.pattern("CC")
						.define('C', DARK_METAL_BLOCK.get())
						.unlockedBy("has_" + c.getName(), has(c.get()))
						.save(p, DesiresCreate.asResource("crafting/" + c.getName() + "_from_" + c.getName()));
			})
			.tag(AllTags.AllBlockTags.WRENCH_PICKUP.tag)
			.item()
			.tab(DesiresCreativeModeTabs.PALETTES_CREATIVE_TAB.getKey())
			.tag(darkMetalDecorTag)
			.build()
			.register();

	public static final BlockEntry<SlabBlock> DARK_METAL_BRICK_SLAB = REGISTRATE.block("dark_metal_brick_slab", SlabBlock::new)
			.properties(p -> p.mapColor(MapColor.COLOR_BLACK)
					.sound(SoundType.NETHERITE_BLOCK)
					.strength(0.5f,1.5f))
			.blockstate((c, p) -> p.slabBlock(c.get(), DesiresCreate.asResource("block/dark_metal_bricks"),
					DesiresCreate.asResource("block/dark_metal_bricks"),
					DesiresCreate.asResource("block/dark_metal_bricks"),
					DesiresCreate.asResource("block/dark_metal_bricks")))
			.transform(pickaxeOnly())
			.tag(stairsBlockTag)
			.recipe((c, p) -> {
				p.stonecutting(DataIngredient.items(AllBlocks.INDUSTRIAL_IRON_BLOCK.get()), RecipeCategory.BUILDING_BLOCKS, c, 4);
				p.stonecutting(DataIngredient.tag(darkMetalDecorTag), RecipeCategory.BUILDING_BLOCKS, c, 2);
				ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, c.get(), 6)
						.pattern("CCC")
						.define('C', DARK_METAL_BRICKS.get())
						.unlockedBy("has_" + c.getName(), has(c.get()))
						.save(p, DesiresCreate.asResource("crafting/" + c.getName() + "_from_" + c.getName()));
			})
			.tag(AllTags.AllBlockTags.WRENCH_PICKUP.tag)
			.item()
			.tab(DesiresCreativeModeTabs.PALETTES_CREATIVE_TAB.getKey())
			.tag(slabsItemTag)
			.build()
			.register();

	public static final BlockEntry<StairBlock> DARK_METAL_BRICK_STAIRS = REGISTRATE.block("dark_metal_brick_stairs", p -> new StairBlock(DesiresPaletteBlocks.DARK_METAL_BLOCK.getDefaultState(), p))
			.properties(p -> p.mapColor(MapColor.COLOR_BLACK)
					.sound(SoundType.NETHERITE_BLOCK)
					.strength(0.5f,1.5f))
			.blockstate((c, p) -> p.stairsBlock(c.get(), DesiresCreate.asResource("block/dark_metal_bricks")))
			.transform(pickaxeOnly())
			.tag(stairsBlockTag)
			.recipe((c, p) -> {
				p.stonecutting(DataIngredient.items(AllBlocks.INDUSTRIAL_IRON_BLOCK.get()), RecipeCategory.BUILDING_BLOCKS, c, 2);
				p.stonecutting(DataIngredient.tag(darkMetalDecorTag), RecipeCategory.BUILDING_BLOCKS, c, 1);
				ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, c.get(), 4)
						.pattern("X  ").pattern("XX ").pattern("XXX")
						.define('X', DARK_METAL_BRICKS.get())
						.unlockedBy("has_" + c.getName(), has(c.get()))
						.save(p, DesiresCreate.asResource("crafting/" + c.getName() + "_from_" + c.getName()));
			})
			.item()
			.tab(DesiresCreativeModeTabs.PALETTES_CREATIVE_TAB.getKey())
			.tag(darkMetalDecorTag, stairsItemTag)
			.build()
			.register();

	public static final BlockEntry<Block> PADDED_RUBBER = REGISTRATE.block("padded_rubber", Block::new)
			.properties(p -> p.mapColor(MapColor.TERRACOTTA_GRAY))
			.properties(p -> p.sound(rubberSoundType))
			.properties(p -> p.strength(0.5f,1.5f))
			.recipe((c, p) -> p.stonecutting(DataIngredient.tag(rubberDecorTag), RecipeCategory.BUILDING_BLOCKS, c, 1))
			.recipe((c, p) -> ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, c.get(), 4)
					.pattern("CC")
					.pattern("CC")
					.define('C', DesiresBlocks.RUBBER_BLOCK.get())
					.unlockedBy("has_" + c.getName(), has(c.get()))
					.save(p, DesiresCreate.asResource("crafting/" + c.getName() + "_from_" + c.getName())))
			.item()
			.tab(DesiresCreativeModeTabs.PALETTES_CREATIVE_TAB.getKey())
			.tag(rubberDecorTag, optionalTag(ForgeRegistries.ITEMS, new ResourceLocation("create_dd", "rubber_decors")))
			.build()
			.register();

	public static final BlockEntry<Block> PADDED_TILED_RUBBER =
			REGISTRATE.block("padded_tiled_rubber", Block::new)
					.properties(p -> p.mapColor(MapColor.TERRACOTTA_GRAY)).properties(p -> p.sound(rubberSoundType)).properties(p -> p.strength(0.5f,1.5f))
					.recipe((c, p) -> p.stonecutting(DataIngredient.tag(rubberDecorTag), RecipeCategory.BUILDING_BLOCKS, c, 1))
					.item()
			.tab(DesiresCreativeModeTabs.PALETTES_CREATIVE_TAB.getKey())
					.tag(rubberDecorTag)
					.build()
					.register();

	public static final BlockEntry<Block> PADDED_MOSAIC_RUBBER =
			REGISTRATE.block("padded_mosaic_rubber", Block::new)
					.properties(p -> p.mapColor(MapColor.TERRACOTTA_GRAY)).properties(p -> p.sound(rubberSoundType)).properties(p -> p.strength(0.5f,1.5f))
					.recipe((c, p) -> p.stonecutting(DataIngredient.tag(rubberDecorTag), RecipeCategory.BUILDING_BLOCKS, c, 1))
					.item()
			.tab(DesiresCreativeModeTabs.PALETTES_CREATIVE_TAB.getKey())
					.tag(rubberDecorTag)
					.build()
					.register();

	public static final BlockEntry<SlabBlock> PADDED_RUBBER_SLAB =
			REGISTRATE.block("padded_rubber_slab", SlabBlock::new)
					.properties(p -> p.mapColor(MapColor.TERRACOTTA_GRAY)).properties(p -> p.sound(rubberSoundType)).properties(p -> p.strength(0.5f,1.5f))
					.tag(slabsBlockTag)
					.recipe((c, p) -> p.stonecutting(DataIngredient.tag(rubberDecorTag), RecipeCategory.BUILDING_BLOCKS, c, 2))
					.blockstate((c, p) -> p.slabBlock(c.get(), DesiresCreate.asResource("block/padded_rubber"),
							DesiresCreate.asResource("block/padded_rubber_slab"),
							DesiresCreate.asResource("block/padded_rubber"),
							DesiresCreate.asResource("block/padded_rubber")))
					.item()
			.tab(DesiresCreativeModeTabs.PALETTES_CREATIVE_TAB.getKey())
					.tag(slabsItemTag)
					.build()
					.register();

	public static final BlockEntry<StairBlock> PADDED_RUBBER_STAIRS =
			REGISTRATE.block("padded_rubber_stairs", p -> new StairBlock(DesiresPaletteBlocks.PADDED_RUBBER.getDefaultState(), p))
					.properties(p -> p.mapColor(MapColor.TERRACOTTA_GRAY)).properties(p -> p.sound(rubberSoundType)).properties(p -> p.strength(0.5f,1.5f))
					.tag(stairsBlockTag)
					.recipe((c, p) -> p.stonecutting(DataIngredient.tag(rubberDecorTag), RecipeCategory.BUILDING_BLOCKS, c, 1))
					.blockstate((c, p) -> p.stairsBlock(c.get(), DesiresCreate.asResource("block/padded_tiled_rubber")))
					.item()
			.tab(DesiresCreativeModeTabs.PALETTES_CREATIVE_TAB.getKey())
					.tag(rubberDecorTag, stairsItemTag)
					.build()
					.register();

	public static final BlockEntry<Block> RAW_PADDED_RUBBER = REGISTRATE.block("raw_padded_rubber", Block::new)
			.properties(p -> p.mapColor(MapColor.TERRACOTTA_WHITE))
			.properties(p -> p.sound(rubberSoundType))
			.properties(p -> p.strength(0.5f,1.5f))
			.recipe((c, p) -> ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, c.get(), 4)
					.pattern("CC")
					.pattern("CC")
					.define('C', DesiresBlocks.RAW_RUBBER_BLOCK.get())
					.unlockedBy("has_" + c.getName(), has(c.get()))
					.save(p, DesiresCreate.asResource("crafting/" + c.getName() + "_from_" + c.getName())))
			.item()
			.tab(DesiresCreativeModeTabs.PALETTES_CREATIVE_TAB.getKey())
			.tag(rawRubberDecorTag)
			.build()
			.register();

	public static final BlockEntry<Block> RAW_PADDED_TILED_RUBBER =
			REGISTRATE.block("raw_padded_tiled_rubber", Block::new)
					.properties(p -> p.mapColor(MapColor.TERRACOTTA_WHITE)).properties(p -> p.sound(rubberSoundType)).properties(p -> p.strength(0.5f,1.5f))
					.recipe((c, p) -> p.stonecutting(DataIngredient.tag(rawRubberDecorTag), RecipeCategory.BUILDING_BLOCKS, c, 1))
					.item()
			.tab(DesiresCreativeModeTabs.PALETTES_CREATIVE_TAB.getKey())
					.tag(rawRubberDecorTag)
					.build()
					.register();
	
	public static final BlockEntry<Block> RAW_PADDED_MOSAIC_RUBBER =
			REGISTRATE.block("raw_padded_mosaic_rubber", Block::new)
					.properties(p -> p.mapColor(MapColor.TERRACOTTA_WHITE)).properties(p -> p.sound(rubberSoundType)).properties(p -> p.strength(0.5f,1.5f))
					.recipe((c, p) -> p.stonecutting(DataIngredient.tag(rawRubberDecorTag), RecipeCategory.BUILDING_BLOCKS, c, 1))
					.item()
			.tab(DesiresCreativeModeTabs.PALETTES_CREATIVE_TAB.getKey())
					.tag(rawRubberDecorTag)
					.build()
					.register();
	
	public static final BlockEntry<SlabBlock> RAW_PADDED_RUBBER_SLAB =
			REGISTRATE.block("raw_padded_rubber_slab", SlabBlock::new)
					.properties(p -> p.mapColor(MapColor.TERRACOTTA_WHITE)).properties(p -> p.sound(rubberSoundType)).properties(p -> p.strength(0.5f,1.5f))
					.tag(slabsBlockTag)
					.recipe((c, p) -> p.stonecutting(DataIngredient.tag(rawRubberDecorTag), RecipeCategory.BUILDING_BLOCKS, c, 2))
					.blockstate((c, p) -> p.slabBlock(c.get(), DesiresCreate.asResource("block/" + "raw_padded_rubber"),
							DesiresCreate.asResource("block/" + "raw_padded_rubber_slab"),
							DesiresCreate.asResource("block/" + "raw_padded_rubber"),
							DesiresCreate.asResource("block/" + "raw_padded_rubber")))
					.item()
			.tab(DesiresCreativeModeTabs.PALETTES_CREATIVE_TAB.getKey())
					.tag(slabsItemTag)
					.build()
					.register();
	
	public static final BlockEntry<StairBlock> RAW_PADDED_RUBBER_STAIRS =
			REGISTRATE.block("raw_padded_rubber_stairs", p -> new StairBlock(DesiresPaletteBlocks.PADDED_RUBBER.getDefaultState(), p))
					.properties(p -> p.mapColor(MapColor.TERRACOTTA_WHITE)).properties(p -> p.sound(rubberSoundType)).properties(p -> p.strength(0.5f,1.5f))
					.tag(stairsBlockTag)
					.recipe((c, p) -> p.stonecutting(DataIngredient.tag(rawRubberDecorTag), RecipeCategory.BUILDING_BLOCKS, c, 1))
					.blockstate((c, p) -> p.stairsBlock(c.get(), DesiresCreate.asResource("block/" + "raw_padded_tiled_rubber")))
					.item()
			.tab(DesiresCreativeModeTabs.PALETTES_CREATIVE_TAB.getKey())
					.tag(rawRubberDecorTag, stairsItemTag)
					.build()
					.register();

	public static final BlockEntry<Block>
			BLACK_RUBBER_BLOCKS = rubber_decor("black", MapColor.COLOR_BLACK, Items.BLACK_DYE),
			WHITE_RUBBER_BLOCKS = rubber_decor("white", MapColor.SNOW, Items.WHITE_DYE),
			BLUE_RUBBER_BLOCKS = rubber_decor("blue", MapColor.COLOR_BLUE, Items.BLUE_DYE),
			LIGHT_BLUE_RUBBER_BLOCKS = rubber_decor("light_blue", MapColor.COLOR_LIGHT_BLUE, Items.LIGHT_BLUE_DYE),
			RED_RUBBER_BLOCKS = rubber_decor("red", MapColor.COLOR_RED, Items.RED_DYE),
			GREEN_RUBBER_BLOCKS = rubber_decor("green", MapColor.COLOR_GREEN, Items.GREEN_DYE),
			LIME_RUBBER_BLOCKS = rubber_decor("lime", MapColor.COLOR_LIGHT_GREEN, Items.LIME_DYE),
			PINK_RUBBER_BLOCKS = rubber_decor("pink", MapColor.COLOR_PINK, Items.PINK_DYE),
			MAGENTA_RUBBER_BLOCKS = rubber_decor("magenta", MapColor.COLOR_MAGENTA, Items.MAGENTA_DYE),
			YELLOW_RUBBER_BLOCKS = rubber_decor("yellow", MapColor.COLOR_YELLOW, Items.YELLOW_DYE),
			GRAY_RUBBER_BLOCKS = rubber_decor("gray", MapColor.COLOR_GRAY, Items.GRAY_DYE),
			LIGHT_GRAY_RUBBER_BLOCKS = rubber_decor("light_gray", MapColor.COLOR_LIGHT_GRAY, Items.LIGHT_GRAY_DYE),
			BROWN_RUBBER_BLOCKS = rubber_decor("brown", MapColor.COLOR_BROWN, Items.BROWN_DYE),
			CYAN_RUBBER_BLOCKS = rubber_decor("cyan", MapColor.COLOR_CYAN, Items.CYAN_DYE),
			PURPLE_RUBBER_BLOCKS = rubber_decor("purple", MapColor.COLOR_PURPLE, Items.PURPLE_DYE),
			ORANGE_RUBBER_BLOCKS = rubber_decor("orange", MapColor.COLOR_ORANGE, Items.ORANGE_DYE);

	public static final BlockEntry<Block>
			BLACK_BLUEPRINT_BLOCK = blueprintBlocks("black", "Black", Items.BLACK_DYE, DesiresSpriteShifts.BLACK_BLUEPRINT_BLOCK, MapColor.COLOR_BLACK),
			WHITE_BLUEPRINT_BLOCK = blueprintBlocks("white", "White", Items.WHITE_DYE, DesiresSpriteShifts.WHITE_BLUEPRINT_BLOCK, MapColor.SNOW),
			BLUE_BLUEPRINT_BLOCK = blueprintBlocks("", "", Items.BLUE_DYE, DesiresSpriteShifts.BLUE_BLUEPRINT_BLOCK, MapColor.COLOR_BLUE, ""),
			LIGHT_BLUE_BLUEPRINT_BLOCK = blueprintBlocks("light", "Light", Items.LIGHT_BLUE_DYE, DesiresSpriteShifts.LIGHT_BLUE_BLUEPRINT_BLOCK, MapColor.COLOR_LIGHT_BLUE),
			RED_BLUEPRINT_BLOCK = blueprintBlocks("red", "Red", Items.RED_DYE, DesiresSpriteShifts.RED_BLUEPRINT_BLOCK, MapColor.COLOR_RED),
			GREEN_BLUEPRINT_BLOCK = blueprintBlocks("green", "Green", Items.GREEN_DYE, DesiresSpriteShifts.GREEN_BLUEPRINT_BLOCK, MapColor.COLOR_GREEN),
			LIME_BLUEPRINT_BLOCK = blueprintBlocks("lime", "Lime", Items.LIME_DYE, DesiresSpriteShifts.LIME_BLUEPRINT_BLOCK, MapColor.COLOR_LIGHT_GREEN),
			PINK_BLUEPRINT_BLOCK = blueprintBlocks("pink", "Pink", Items.PINK_DYE, DesiresSpriteShifts.PINK_BLUEPRINT_BLOCK, MapColor.COLOR_PINK),
			MAGENTA_BLUEPRINT_BLOCK = blueprintBlocks("magenta", "Magenta", Items.MAGENTA_DYE, DesiresSpriteShifts.MAGENTA_BLUEPRINT_BLOCK, MapColor.COLOR_MAGENTA),
			YELLOW_BLUEPRINT_BLOCK = blueprintBlocks("yellow", "Yellow", Items.YELLOW_DYE, DesiresSpriteShifts.YELLOW_BLUEPRINT_BLOCK, MapColor.COLOR_YELLOW),
			GRAY_BLUEPRINT_BLOCK = blueprintBlocks("gray", "Gray", Items.GRAY_DYE, DesiresSpriteShifts.GRAY_BLUEPRINT_BLOCK, MapColor.COLOR_GRAY),
			LIGHT_GRAY_BLUEPRINT_BLOCK = blueprintBlocks("light_gray", "Light Gray", Items.LIGHT_GRAY_DYE, DesiresSpriteShifts.LIGHT_GRAY_BLUEPRINT_BLOCK, MapColor.COLOR_LIGHT_GRAY),
			BROWN_BLUEPRINT_BLOCK = blueprintBlocks("brown", "Brown", Items.BROWN_DYE, DesiresSpriteShifts.BROWN_BLUEPRINT_BLOCK, MapColor.COLOR_BROWN),
			CYAN_BLUEPRINT_BLOCK = blueprintBlocks("cyan", "Cyan", Items.CYAN_DYE, DesiresSpriteShifts.CYAN_BLUEPRINT_BLOCK, MapColor.COLOR_CYAN),
			PURPLE_BLUEPRINT_BLOCK = blueprintBlocks("purple", "Purple", Items.PURPLE_DYE, DesiresSpriteShifts.PURPLE_BLUEPRINT_BLOCK, MapColor.COLOR_PURPLE),
			ORANGE_BLUEPRINT_BLOCK = blueprintBlocks("orange", "Orange", Items.ORANGE_DYE, DesiresSpriteShifts.ORANGE_BLUEPRINT_BLOCK, MapColor.COLOR_ORANGE)
	;


	public static final int COLORED_BLOCKS = generateColorBlocks();

	public static int generateColorBlocks(){
		String[] colors = {"black", "white", "blue", "light_blue", "red", "green", "lime", "pink", "magenta", "yellow", "gray", "light_gray", "brown", "cyan", "purple", "orange"};
		for (String color : colors) {
			String firstLetter = color.substring(0, 1).toUpperCase();
			String colorWithoutC = color.substring(1);

			String upColor = firstLetter + colorWithoutC;
			String light = "Light";
			if (upColor.contains(light)) {
				String nameWithoutLight = upColor.substring(6);

				String firstLetter2 = nameWithoutLight.substring(0, 1).toUpperCase();
				String colorWithoutC2 = nameWithoutLight.substring(1);

				upColor = light + " " + firstLetter2 + colorWithoutC2;
			}

			REGISTRATE.block(color + "_asphalt_block", Block::new)
					.initialProperties(SharedProperties::stone)
					.blockstate((c, p) -> p.simpleBlock(c.get(), p.models()
								.cubeAll(c.getName(), p.modLoc("block/asphalt/" + color))))
					.properties(p -> p.destroyTime(1.25f)
							.speedFactor(0.001F)
							.jumpFactor(1.25F)
							.friction(0.35F)
							.mapColor(MapColor.COLOR_BLACK)
							.sound(SoundType.POLISHED_DEEPSLATE))
					.transform(pickaxeOnly())
					.tag(AllTags.AllBlockTags.WRENCH_PICKUP.tag)
					.item()
			.tab(DesiresCreativeModeTabs.PALETTES_CREATIVE_TAB.getKey())
					.tag(asphaltBlocks)
					.recipe((c, p) -> ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, c.get(), 4)
                            .define('D',
                                color.equals("black") ? Tags.Items.DYES_BLACK :
                                color.equals("white") ? Tags.Items.DYES_WHITE :
                                color.equals("blue") ? Tags.Items.DYES_BLUE :
                                color.equals("light_blue") ? Tags.Items.DYES_LIGHT_BLUE :
                                color.equals("red") ? Tags.Items.DYES_RED :
                                color.equals("green") ? Tags.Items.DYES_GREEN :
                                color.equals("lime") ? Tags.Items.DYES_LIME :
                                color.equals("pink") ? Tags.Items.DYES_PINK :
                                color.equals("magenta") ? Tags.Items.DYES_MAGENTA :
                                color.equals("yellow") ? Tags.Items.DYES_YELLOW :
                                color.equals("gray") ? Tags.Items.DYES_GRAY :
                                color.equals("light_gray") ? Tags.Items.DYES_LIGHT_GRAY :
                                color.equals("brown") ? Tags.Items.DYES_BROWN :
                                color.equals("cyan") ? Tags.Items.DYES_CYAN :
                                color.equals("purple") ? Tags.Items.DYES_PURPLE :
                                color.equals("orange") ? Tags.Items.DYES_ORANGE : Tags.Items.DYES)
                            .define('B', Items.SLIME_BALL)
                            .define('S', AllPaletteStoneTypes.SCORCHIA.baseBlock.get())
                            .pattern("BSB")
                            .pattern("SDS")
                            .pattern("BSB")
                            .unlockedBy("has_dyed_item", has(Tags.Items.DYES))
                            .save(p, DesiresCreate.asResource("crafting/decor/" + c.getName())))
					.build()
					.lang(upColor + " Asphalt Block")
					.register();

		}
		return 0;
	}


	static {
		DesiresPaletteStoneTypes.register(DesiresCreate.REGISTRATE);
	}
	// Load this class
	public static void register() {}
}
