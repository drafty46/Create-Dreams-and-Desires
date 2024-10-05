package uwu.lopyluna.create_dd.registry;

import net.minecraft.world.level.ItemLike;

import java.util.function.BiConsumer;

@SuppressWarnings({"unused"})
public class DesiresLangPartial {
    public static void provideLang(BiConsumer<String, String> consumer) {
        consume(consumer, "item.create_dd.handheld_block_zapper.tooltip.behaviour1", "_Targeted block_ will become the _material_ placed by the shaper.");
        consume(consumer, "item.create_dd.handheld_block_zapper.tooltip.behaviour2", "Applies currently selected _Brush_ and _Tool_ at the targeted location.");
        consume(consumer, "item.create_dd.handheld_block_zapper.tooltip.behaviour3", "Opens the _Configuration Interface_");
        consume(consumer, "item.create_dd.handheld_block_zapper.tooltip.condition1", "L-Click at Block");
        consume(consumer, "item.create_dd.handheld_block_zapper.tooltip.condition2", "R-Click at Block");
        consume(consumer, "item.create_dd.handheld_block_zapper.tooltip.condition3", "R-Click while Sneaking");
        consume(consumer, "item.create_dd.handheld_block_zapper.tooltip.summary", "Survival mode_ tool for medium-scale _landscaping_ from a decent distance.");

        //item description are kinda temp since it was mostly wrote by an AI since i was bored ill fix it when i have time || id be glad to let anyone fix these up
        tooltipBehaviour(consumer, DesiresItems.JETPACK.get(), "Provides _Pressurized Air_ to enable flight.", 1);
        tooltipBehaviour(consumer, DesiresItems.JETPACK.get(), "Consumes _Air_ during flight. Avoid _overheating_ to maintain efficiency.", 2);
        tooltipCondition(consumer, DesiresItems.JETPACK.get(), "When equipped in Chest Slot.", 1);
        tooltipCondition(consumer, DesiresItems.JETPACK.get(), "Flight enabled by _Holding Space_ (requires sufficient _Air_).", 2);
        tooltipSummary(consumer, DesiresItems.JETPACK.get(), "A _Wearable Jetpack_ powered by _Pressurized Air_. Efficient but _overheats_ in extreme environments. Recharges Air over time when stationary.");

        tooltipBehaviour(consumer, DesiresItems.VISOR_HELMET.get(), "Automatically grants _Night Vision_ when in dark areas.", 1);
        tooltipBehaviour(consumer, DesiresItems.VISOR_HELMET.get(), "Helps detect nearby ores when using a _Pickaxe_ or _Drill_.", 2);
        tooltipCondition(consumer, DesiresItems.VISOR_HELMET.get(), "Night Vision activates during _Nighttime_ or _low-light conditions_ outdoors.", 1);
        tooltipCondition(consumer, DesiresItems.VISOR_HELMET.get(), "Ore detection activates when wielding a _Pickaxe_ or _Drill_.", 2);
        tooltipSummary(consumer, DesiresItems.VISOR_HELMET.get(), "A _specialized helmet_ that grants _Night Vision_ and enhances ore detection.");

        tooltipBehaviour(consumer, DesiresItems.DEFORESTER_SAW.get(), "Cuts down entire trees with a single strike when _Shift_ is not held.", 1);
        tooltipBehaviour(consumer, DesiresItems.DEFORESTER_SAW.get(), "Can be repaired with _Andesite Alloy_ when slightly damaged.", 2);
        tooltipCondition(consumer, DesiresItems.DEFORESTER_SAW.get(), "Tree cutting requires holding _without Shift_.", 1);
        tooltipCondition(consumer, DesiresItems.DEFORESTER_SAW.get(), "Repairs with Andesite Alloy in the _Off-Hand_.", 2);
        tooltipSummary(consumer, DesiresItems.DEFORESTER_SAW.get(), "A powerful _axe_ designed for quick and efficient _tree cutting_. Comes with a repair mechanism using _Andesite Alloy_.");

        tooltipBehaviour(consumer, DesiresItems.EXCAVATION_DRILL.get(), "Excavates entire _ore veins_ when _Shift_ is held.", 1);
        tooltipBehaviour(consumer, DesiresItems.EXCAVATION_DRILL.get(), "Can be repaired with _Bury Blend_ or _Brass Ingot_ when damaged.", 2);
        tooltipCondition(consumer, DesiresItems.EXCAVATION_DRILL.get(), "Vein mining requires holding _Shift_ on ores.", 1);
        tooltipCondition(consumer, DesiresItems.EXCAVATION_DRILL.get(), "Repairs with _Bury Blend_ or _Brass Ingot_ in the _Off-Hand_.", 2);
        tooltipSummary(consumer, DesiresItems.EXCAVATION_DRILL.get(), "A specialized drill for rapid _ore vein mining_. Repairable with _Bury Blend_ or _Brass Ingot_.");

        GildedRoseTools(consumer, DesiresItems.GILDED_ROSE_SWORD.get(), true);
        GildedRoseTools(consumer, DesiresItems.GILDED_ROSE_AXE.get(), false);
        GildedRoseTools(consumer, DesiresItems.GILDED_ROSE_PICKAXE.get(), false);
        GildedRoseTools(consumer, DesiresItems.GILDED_ROSE_SHOVEL.get(), false);
        GildedRoseTools(consumer, DesiresItems.GILDED_ROSE_HOE.get(), false);

        tooltipBehaviour(consumer, DesiresItems.HANDHELD_NOZZLE.get(), "Can be switched between _Blowing_ and _Vacuuming_ modes.", 1);
        tooltipBehaviour(consumer, DesiresItems.HANDHELD_NOZZLE.get(), "Repairable with _Propeller_ or _Copper Ingot_ when damaged.", 2);
        tooltipCondition(consumer, DesiresItems.HANDHELD_NOZZLE.get(), "Switch modes by _Sneak-Right-Clicking_ while holding.", 1);
        tooltipCondition(consumer, DesiresItems.HANDHELD_NOZZLE.get(), "Repairs when _Propeller_ or _Copper Ingot_ is held in the _Off-Hand_.", 2);
        tooltipSummary(consumer, DesiresItems.HANDHELD_NOZZLE.get(), "A versatile _air manipulation tool_, perfect for pushing or pulling entities. Repairable with _Propeller_ or _Copper Ingot_.");

        tooltipBehaviour(consumer, DesiresItems.SEETHING_ABLAZE_ROD.get(), "Burns at an _extremely high temperature_ for an extended duration.", 1);
        tooltipCondition(consumer, DesiresItems.SEETHING_ABLAZE_ROD.get(), "Specifically fuels _Blaze Burners_ with maximum efficiency.", 1);
        tooltipSummary(consumer, DesiresItems.SEETHING_ABLAZE_ROD.get(), "A _special fuel_ designed to supercharge your _Blaze Burner_. Lasts significantly longer than ordinary fuels.");

        tooltipBehaviour(consumer, DesiresItems.MUSIC_DISC_WALTZ_OF_THE_FLOWERS.get(), "_Dance gracefully_ to the enchanting melodies of Tchaikovsky.", 1);
        tooltipCondition(consumer, DesiresItems.MUSIC_DISC_WALTZ_OF_THE_FLOWERS.get(), "A _rare find_—best enjoyed during quiet moments.", 1);
        tooltipSummary(consumer, DesiresItems.MUSIC_DISC_WALTZ_OF_THE_FLOWERS.get(), "Let the _Waltz of the Flowers_ whisk you away to a world of elegance and wonder.");

        tooltipBehaviour(consumer, DesiresBlocks.BURDEN_CHUTE.get(), "Transfers items _with enhanced speed and capacity_ like no ordinary chute.", 1);
        tooltipSummary(consumer, DesiresBlocks.BURDEN_CHUTE.get(), "The _Burden Chute_ handles _heavy loads_ and accelerates item movement with style");

        tooltipBehaviour(consumer, DesiresBlocks.FURNACE_ENGINE.get(), "Powered by _any Furnace_, as long as it's burning,", 1);
        tooltipBehaviour(consumer, DesiresBlocks.FURNACE_ENGINE.get(), "Pair it with a _Flywheel_ to transfer the _power_", 2);
        tooltipSummary(consumer, DesiresBlocks.FURNACE_ENGINE.get(), "Generates _power_ when attached to a _burning furnace_ and a _Flywheel_");

        consume(consumer, "create.menu.discord", "Discord Server");

        consume(consumer, "create_dd.block_zapper.modifiers", "Modifiers:");
        consume(consumer, "create_dd.block_zapper.no_modifiers", "You have no modifiers applied.");

        consume(consumer, "create_dd.block_zapper.ctrl", " for Modifiers");
        consume(consumer, "create_dd.tooltip.holdForModifiers", "Hold ");

        consume(consumer, "create_dd.block_zapper.breaker_modifier", "Breaker");
        consume(consumer, "create_dd.block_zapper.breaker_modifier.desc", "Drops blocks when clearing & replacing.");

        consume(consumer, "create_dd.block_zapper.void_notice", "Blocks will be voided when clearing & replacing!");
        consume(consumer, "create_dd.block_zapper.breaker_notice", "Apply the Breaker Modifier to obtain blocks when destroy.");

        consume(consumer, "create_dd.block_zapper.size_modifier", "Bulking");
        consume(consumer, "create_dd.block_zapper.size_modifier.desc", "Can place 2x more blocks.")
        ;
        consume(consumer, "create_dd.block_zapper.range_modifier", "Range");
        consume(consumer, "create_dd.block_zapper.range_modifier.desc", "Can place blocks farther up to ");
        consume(consumer, "create_dd.block_zapper.range_modifier.desc2", " blocks.");

        consume(consumer, "create_dd.block_zapper.speed_modifier", "Quick Charging");
        consume(consumer, "create_dd.block_zapper.speed_modifier.desc", "Reduce the cooldown to ");
        consume(consumer, "create_dd.block_zapper.speed_modifier.desc2", " seconds.");

        consume(consumer, "create_dd.block_zapper.need_upgrade_below", "Need Upgrade before this Tier!");
        consume(consumer, "create_dd.block_zapper.too_much", "Too much levels for this modifier!");
        consume(consumer, "create_dd.block_zapper.not_enough_blocks", "Not holding enough selected blocks!");
        consume(consumer, "create_dd.recipe.fan_sanding.fan", "Fan behind Sand");
        consume(consumer, "create_dd.recipe.fan_freezing.fan", "Fan behind Powdered Snow");
        consume(consumer, "create_dd.recipe.fan_seething.fan", "Fan behind Super Heated Blaze Burner");
        consume(consumer, "create_dd.recipe.fan_sanding", "Bulk Sanding");
        consume(consumer, "create_dd.recipe.fan_freezing", "Bulk Freezing");
        consume(consumer, "create_dd.recipe.fan_seething", "Bulk Seething");
        consume(consumer, "create_dd.recipe.hydraulic_compacting", "Hydraulic Compacting");
        consume(consumer, "itemGroup.create_dd.base", "Create: Dreams n' Desires");
        consume(consumer, "itemGroup.create_dd.palettes", "DnDesires Building Blocks");
        consume(consumer, "itemGroup.create_dd.beta", "DnDesires Beta Stuff");
        consume(consumer, "itemGroup.create_dd.classic", "DnDesires Classic Stuff");

    }

    private static void GildedRoseTools(BiConsumer<String, String> consumer, ItemLike item, boolean isSword) {
        tooltipBehaviour(consumer, item, "Increases _damage_ or _mining speed_ when off-cooldown.", 1);
        tooltipBehaviour(consumer, item, "May grant _additional drops_ with proper enchantments.", 2);
        tooltipCondition(consumer, item, "Performs _best_ when not on _Cooldown_.", 1);
        tooltipCondition(consumer, item, "Can be _enchanted_ with " + (isSword ? "_Looting_" : "_Fortune_") + " for extra drops.", 2);
        tooltipSummary(consumer, item, "_Gilded Rose_ tools offer enhanced performance with a unique _cooldown mechanic_ and potential for additional drops.");
    }

    private static void tooltipBehaviour(BiConsumer<String, String> consumer, ItemLike item, String desc, int line) {
        consume(consumer, item.asItem().getDescriptionId() + ".tooltip.behaviour" + line, desc);
    }
    private static void tooltipCondition(BiConsumer<String, String> consumer, ItemLike item, String desc, int line) {
        consume(consumer, item.asItem().getDescriptionId() + ".tooltip.condition" + line, desc);
    }
    private static void tooltipSummary(BiConsumer<String, String> consumer, ItemLike item, String desc) {
        consume(consumer, item.asItem().getDescriptionId() + ".tooltip.summary", desc);
    }

    private static void consume(BiConsumer<String, String> consumer, String key, String enUS) {
        consumer.accept(key, enUS);
    }
}
