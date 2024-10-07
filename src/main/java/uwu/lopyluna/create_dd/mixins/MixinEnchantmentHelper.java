package uwu.lopyluna.create_dd.mixins;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uwu.lopyluna.create_dd.registry.DesiresTags;

@Mixin(EnchantmentHelper.class)
public class MixinEnchantmentHelper {


    @SuppressWarnings("deprecation")
    @Inject(method = "getMobLooting(Lnet/minecraft/world/entity/LivingEntity;)I", at = @At(value = "HEAD"), cancellable = true)
    private static void getMobLooting(LivingEntity pEntity, CallbackInfoReturnable<Integer> cir) {
        ItemStack stack = desires$isRightForItem(Enchantments.MOB_LOOTING, pEntity);
        if (stack != ItemStack.EMPTY)
            cir.setReturnValue((net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MOB_LOOTING, stack) * 3) + 1);
    }

    @SuppressWarnings("all")
    @Unique
    private static ItemStack desires$isRightForItem(Enchantment pEnchantment, LivingEntity pEntity) {
        Iterable<ItemStack> iterable = pEnchantment.getSlotItems(pEntity).values();
        if (iterable == null) {
            return ItemStack.EMPTY;
        } else {
            ItemStack i = ItemStack.EMPTY;
            for(ItemStack itemstack : iterable) {
                if (!itemstack.isEmpty() && itemstack.is(DesiresTags.AllItemTags.ADDITIONAL_DROPS_TOOL.tag))
                    i = itemstack;
            }
            return i;
        }
    }
}
