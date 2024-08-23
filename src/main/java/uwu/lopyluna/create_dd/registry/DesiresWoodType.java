package uwu.lopyluna.create_dd.registry;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import uwu.lopyluna.create_dd.DesiresCreate;
import uwu.lopyluna.create_dd.registry.helper.Lang;
import uwu.lopyluna.create_dd.registry.helper.woodtype.WoodTypeBlockPattern;
import uwu.lopyluna.create_dd.registry.helper.woodtype.WoodTypeVariantEntry;

import java.util.function.Function;

public enum DesiresWoodType {

    ;

    private final Function<CreateRegistrate, NonNullSupplier<Block>> factory;
    private WoodTypeVariantEntry variants;

    public NonNullSupplier<Block> baseBlock;
    public final WoodTypeBlockPattern[] variantTypes;
    public TagKey<Item> materialTag;

    DesiresWoodType(WoodTypeBlockPattern[] variantTypes,
                    Function<CreateRegistrate, NonNullSupplier<Block>> factory) {
        this.factory = factory;
        this.variantTypes = variantTypes;
    }

    public NonNullSupplier<Block> getBaseBlock() {
        return baseBlock;
    }

    public WoodTypeVariantEntry getVariants() {
        return variants;
    }

    public static void register(CreateRegistrate registrate) {
        for (DesiresWoodType paletteStoneVariants : values()) {
            paletteStoneVariants.baseBlock = paletteStoneVariants.factory.apply(registrate);
            String id = Lang.asId(paletteStoneVariants.name());
            paletteStoneVariants.materialTag =
                    DesiresTags.optionalTag(ForgeRegistries.ITEMS, DesiresCreate.asResource("stone_types/" + id));
            paletteStoneVariants.variants = new WoodTypeVariantEntry(id, paletteStoneVariants);
        }
    }
}
