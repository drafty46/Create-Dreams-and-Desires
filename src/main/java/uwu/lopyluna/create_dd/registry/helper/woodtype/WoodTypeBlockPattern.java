package uwu.lopyluna.create_dd.registry.helper.woodtype;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import uwu.lopyluna.create_dd.DesiresCreate;

import java.util.function.Function;

import static uwu.lopyluna.create_dd.registry.helper.woodtype.WoodTypePartial.ALL_PARTIALS;
import static uwu.lopyluna.create_dd.registry.helper.woodtype.WoodTypePartial.ALL_PARTIALS_FANCY;

public class WoodTypeBlockPattern {
    public static final WoodTypeBlockPattern

            NORMAL = create("normal", PatternNameType.PREFIX, ALL_PARTIALS),
            FANCY = create("fancy", PatternNameType.PREFIX, ALL_PARTIALS_FANCY)
                    ;


    public static final WoodTypeBlockPattern[] NORMAL_RANGE = { NORMAL };

    public static final WoodTypeBlockPattern[] FANCY_RANGE = { FANCY };

    static final String TEXTURE_LOCATION = "block/palettes/wood_types/%s/%s";

    private PatternNameType nameType;
    private String[] textures;
    private String id;
    private boolean isTranslucent;
    private TagKey<Block>[] blockTags;
    private TagKey<Item>[] itemTags;

    private IPatternBlockStateGenerator blockStateGenerator;
    private NonNullFunction<BlockBehaviour.Properties, ? extends Block> blockFactory;
    private NonNullFunction<NonNullSupplier<Block>, NonNullBiConsumer<DataGenContext<Block, ? extends Block>, RegistrateRecipeProvider>> additionalRecipes;
    private WoodTypePartial<? extends Block>[] partials;

    @OnlyIn(Dist.CLIENT)
    private RenderType renderType;

    private static WoodTypeBlockPattern create(String name, PatternNameType nameType, WoodTypePartial<?>... partials) {
        WoodTypeBlockPattern pattern = new WoodTypeBlockPattern();
        pattern.id = name;
        pattern.nameType = nameType;
        pattern.partials = partials;
        pattern.additionalRecipes = $ -> NonNullBiConsumer.noop();
        pattern.isTranslucent = false;
        pattern.blockFactory = Block::new;
        pattern.textures = new String[] { name };
        pattern.blockStateGenerator = p -> p::cubeAll;
        return pattern;
    }

    public IPatternBlockStateGenerator getBlockStateGenerator() {
        return blockStateGenerator;
    }

    public boolean isTranslucent() {
        return isTranslucent;
    }

    public TagKey<Block>[] getBlockTags() {
        return blockTags;
    }

    public TagKey<Item>[] getItemTags() {
        return itemTags;
    }

    public NonNullFunction<BlockBehaviour.Properties, ? extends Block> getBlockFactory() {
        return blockFactory;
    }

    public WoodTypePartial<? extends Block>[] getPartials() {
        return partials;
    }

    public String getTexture(int index) {
        return textures[index];
    }

    public void addRecipes(NonNullSupplier<Block> baseBlock, DataGenContext<Block, ? extends Block> c,
                           RegistrateRecipeProvider p) {
        additionalRecipes.apply(baseBlock)
                .accept(c, p);
    }

    // Builder

    private WoodTypeBlockPattern blockStateFactory(IPatternBlockStateGenerator factory) {
        blockStateGenerator = factory;
        return this;
    }

    private WoodTypeBlockPattern textures(String... textures) {
        this.textures = textures;
        return this;
    }

    private WoodTypeBlockPattern block(NonNullFunction<BlockBehaviour.Properties, ? extends Block> blockFactory) {
        this.blockFactory = blockFactory;
        return this;
    }

    // Model generators

    public IBlockStateProvider cubeAll(String variant) {
        ResourceLocation all = toLocation(variant, textures[0]);
        return (ctx, prov) -> prov.simpleBlock(ctx.get(), prov.models()
                .cubeAll(createName(variant), all));
    }





    // Utility

    protected String createName(String variant) {
        if (nameType == PatternNameType.WRAP) {
            String[] split = id.split("_");
            if (split.length == 2) {
                String formatString = "%s_%s_%s";
                return String.format(formatString, split[0], variant, split[1]);
            }
        }
        String formatString = "%s_%s";
        return nameType == PatternNameType.SUFFIX ? String.format(formatString, variant, id) : String.format(formatString, id, variant);
    }

    protected static ResourceLocation toLocation(String variant, String texture) {
        return DesiresCreate.asResource(
                String.format(TEXTURE_LOCATION, texture, variant + (texture.equals("cut") ? "_" : "_cut_") + texture));
    }

    @FunctionalInterface
    static interface IPatternBlockStateGenerator
            extends Function<WoodTypeBlockPattern, Function<String, IBlockStateProvider>> {
    }

    @FunctionalInterface
    static interface IBlockStateProvider
            extends NonNullBiConsumer<DataGenContext<Block, ? extends Block>, RegistrateBlockstateProvider> {
    }

    enum PatternNameType {
        PREFIX, SUFFIX, WRAP
    }
}
