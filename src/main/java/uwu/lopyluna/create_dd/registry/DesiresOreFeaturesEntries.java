package uwu.lopyluna.create_dd.registry;

import com.simibubi.create.content.decoration.palettes.AllPaletteStoneTypes;
import com.simibubi.create.foundation.utility.Couple;
import com.simibubi.create.infrastructure.worldgen.OreFeatureConfigEntry;
import net.minecraft.tags.BiomeTags;
import net.minecraftforge.common.ForgeConfigSpec;
import uwu.lopyluna.create_dd.DesireUtil;

import java.util.Objects;

import static uwu.lopyluna.create_dd.DesiresCreate.MOD_ID;

@SuppressWarnings({"unused", "SameParameterValue"})
public class DesiresOreFeaturesEntries {

    public static final OreFeatureConfigEntry GABBRO_BLOB =
            Objects.requireNonNull(create("gabbro_blob", 64, 0.85F, -64, 0).standardDatagenExt())
                    .withBlocks(Couple.create(AllPaletteStoneTypes.GRANITE.baseBlock, DesiresPaletteStoneTypes.GABBRO.baseBlock))
                    .biomeTag(BiomeTags.IS_OVERWORLD)
                    .parent();

    public static final OreFeatureConfigEntry DOLOMITE_BLOB =
            Objects.requireNonNull(create("dolomite_blob", 64, 0.85F, -64, 0).standardDatagenExt())
                    .withBlocks(Couple.create(AllPaletteStoneTypes.DIORITE.baseBlock, DesiresPaletteStoneTypes.DOLOMITE.baseBlock))
                    .biomeTag(BiomeTags.IS_OVERWORLD)
                    .parent();

    private static OreFeatureConfigEntry create(String name, int clusterSize, float frequency, int minHeight, int maxHeight) {
        return new OreFeatureConfigEntry(DesireUtil.asResource(name), clusterSize, frequency, minHeight, maxHeight);
    }

    public static void init() {}


    public static void fillConfig(ForgeConfigSpec.Builder builder) {
        OreFeatureConfigEntry.ALL
                .forEach((id, entry) -> {
                    if (id.getNamespace().equals(MOD_ID)) {
                        builder.push(entry.getName());
                        entry.addToConfig(builder);
                        builder.pop();
                    }
                });
    }
}
