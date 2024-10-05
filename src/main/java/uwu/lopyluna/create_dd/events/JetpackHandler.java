package uwu.lopyluna.create_dd.events;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import uwu.lopyluna.create_dd.content.items.equipment.jetpack.JetpackItem;
import uwu.lopyluna.create_dd.content.packet.JetpackActivatePacket;
import uwu.lopyluna.create_dd.registry.DesiresPackets;

import static uwu.lopyluna.create_dd.DesireUtil.randomChance;
import static uwu.lopyluna.create_dd.DesiresCreate.MOD_ID;
import static uwu.lopyluna.create_dd.content.items.equipment.jetpack.JetpackItem.getCenterPos;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class JetpackHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        Minecraft mc = Minecraft.getInstance();
        if (player != null && mc.getConnection() != null) {
            DesiresPackets.sendToServer(new JetpackActivatePacket(mc.options.keyJump.isDown(), player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof JetpackItem));
            ItemStack stack = player.getItemBySlot(EquipmentSlot.CHEST);
            if (stack.getItem() instanceof JetpackItem item) {
                CompoundTag nbt = stack.getOrCreateTag();
                if (mc.options.keyJump.isDown() && nbt.getInt("Air") > 0 && nbt.getInt("Heating") < item.getMaxHeating() && nbt.getInt("Cooldown") <= 0) {
                    if (player.getLevel().isClientSide() && randomChance(25, player.getLevel()) && !player.isPassenger() && !player.isSpectator() && !player.isCreative() && !player.isShiftKeyDown()) {
                        Vec3 center = getCenterPos(player);
                        player.getLevel().addParticle(ParticleTypes.POOF, center.x, center.y, center.z, player.getDeltaMovement().x * -1.25, player.getDeltaMovement().y * -1.5, player.getDeltaMovement().x * -1.25);
                    }
                }

                player.getPersistentData().putString("VisualJetpackAir", item.getAirVisual(nbt));
                player.getPersistentData().putString("VisualJetpackCooldown", item.getCooldownVisual(nbt));
                player.getPersistentData().putString("VisualJetpackHeating", item.getHeatingVisual(nbt));
            } else {
                if (player.getPersistentData().contains("VisualJetpackAir"))
                    player.getPersistentData().remove("VisualBacktankAir");
                if (player.getPersistentData().contains("VisualJetpackCooldown"))
                    player.getPersistentData().remove("VisualJetpackCooldown");
                if (player.getPersistentData().contains("VisualJetpackHeating"))
                    player.getPersistentData().remove("VisualJetpackHeating");

            }
        }
    }
}
