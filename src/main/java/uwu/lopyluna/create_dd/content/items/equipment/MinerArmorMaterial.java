package uwu.lopyluna.create_dd.content.items.equipment;

import com.google.common.base.Suppliers;
import com.simibubi.create.AllSoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;
import uwu.lopyluna.create_dd.DesireUtil;
import uwu.lopyluna.create_dd.registry.DesiresItems;

import java.util.function.Supplier;

public enum MinerArmorMaterial implements ArmorMaterial {

    MINER("miner", 80, new int[] { 2, 5, 6, 3 }, 25, AllSoundEvents.COPPER_ARMOR_EQUIP::getMainEvent, 0.5F, 0.5F,
            () -> Ingredient.of(DesiresItems.BURY_BLEND.get()))

    ;

    private static final int[] MAX_DAMAGE_ARRAY = new int[] { 13, 15, 16, 11 };
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final Supplier<SoundEvent> soundEvent;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairMaterial;

    MinerArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability,
                       Supplier<SoundEvent> soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) {
        this.name = DesireUtil.asResource(name).toString();
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairMaterial = Suppliers.memoize(repairMaterial::get);
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlot slot) {
        return MAX_DAMAGE_ARRAY[slot.getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot slot) {
        return this.damageReductionAmountArray[slot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public @NotNull SoundEvent getEquipSound() {
        return this.soundEvent.get();
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }

    @Override
    public @NotNull String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

}
