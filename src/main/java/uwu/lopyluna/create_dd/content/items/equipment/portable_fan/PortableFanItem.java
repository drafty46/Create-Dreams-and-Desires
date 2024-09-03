package uwu.lopyluna.create_dd.content.items.equipment.portable_fan;

import com.simibubi.create.AllSoundEvents;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@SuppressWarnings({"deprecation"})
public class PortableFanItem extends Item {
    private FanMode mode = FanMode.VACUUMING;
    private boolean isActive = false;

    public PortableFanItem(Properties properties) {
        super(properties);
    }

    public enum FanMode {
        VACUUMING,
        BLOWING
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (player.isShiftKeyDown()) {
            if (!level.isClientSide) {
                mode = (mode == FanMode.VACUUMING) ? FanMode.BLOWING : FanMode.VACUUMING;
                player.displayClientMessage(Component.literal("Mode switched to: " + mode), true);
                level.playSound(null, player.blockPosition(), AllSoundEvents.CONFIRM.getMainEvent(), SoundSource.PLAYERS, 1.0F, 1.0F);
            }
        } else {
            player.startUsingItem(hand);
            isActive = true;
        }
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int count) {
        if (entity instanceof Player player) {
            if (isActive && !player.isShiftKeyDown()) {
                if (mode == FanMode.VACUUMING) {
                    handleVacuuming(level, player);
                } else {
                    handleBlowing(level, player);
                }
            }
        }
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        isActive = false;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.NONE;
    }

    private void handleVacuuming(Level level, Player player) {
        Vec3 playerPos = player.position();
        AABB range = new AABB(playerPos.subtract(16, 16, 16), playerPos.add(16, 16, 16)); // 16-block range
        List<Entity> entities = level.getEntities(player, range);

        for (Entity entity : entities) {
            double distance = playerPos.distanceTo(entity.position());

            if (distance <= 2.0) {
                continue;
            }

            Vec3 direction = playerPos.subtract(entity.position()).normalize();
            double strength = Math.max(0, (1 - (distance / 16)) * 0.5);
            entity.push(direction.x * strength, direction.y * strength, direction.z * strength);
        }
    }

    private void handleBlowing(Level level, Player player) {
        Vec3 playerPos = player.position();
        AABB range = new AABB(playerPos.subtract(16, 16, 16), playerPos.add(16, 16, 16));
        List<Entity> entities = level.getEntities(player, range);

        for (Entity entity : entities) {
            Vec3 direction = entity.position().subtract(playerPos).normalize();
            double distance = playerPos.distanceTo(entity.position());
            double strength = Math.max(0, (1 - (distance / 16)) * 0.5);
            entity.push(direction.x * strength, direction.y * strength, direction.z * strength);
        }
    }
}