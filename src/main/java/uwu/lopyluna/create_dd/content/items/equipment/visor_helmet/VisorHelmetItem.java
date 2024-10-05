package uwu.lopyluna.create_dd.content.items.equipment.visor_helmet;

import com.simibubi.create.AllTags;
import com.simibubi.create.content.equipment.armor.BaseArmorItem;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket;

import uwu.lopyluna.create_dd.DesireUtil;
import uwu.lopyluna.create_dd.content.items.equipment.MinerArmorMaterial;
import uwu.lopyluna.create_dd.infrastructure.config.DesiresConfigs;
import uwu.lopyluna.create_dd.registry.DesiresTags;

import static net.minecraft.world.effect.MobEffects.NIGHT_VISION;
import static uwu.lopyluna.create_dd.DesireUtil.randomChance;

public class VisorHelmetItem extends BaseArmorItem {

    public VisorHelmetItem(Item.Properties properties) {
        super(MinerArmorMaterial.MINER, EquipmentSlot.HEAD, properties, DesireUtil.asResource("miner"));
    }

    @Override
    public void onArmorTick(ItemStack pStack, Level level, Player player) {
        super.onArmorTick(pStack, level, player);

        if (level.dimensionType().hasSkyLight() && !level.dimensionType().hasCeiling() && ((level.getLightEmission(player.blockPosition()) == 0 && !level.canSeeSky(player.blockPosition())) || level.isNight())) {
            if (!player.hasEffect(NIGHT_VISION) && DesireUtil.randomChance(25, level)) {
                player.addEffect(new MobEffectInstance(NIGHT_VISION, 20 * 2, 0, true, true, false), player);
                if (randomChance(5, level)) {
                    pStack.hurtAndBreak(randomChance(25, level) ? 2 : 1, player, e -> e.broadcastBreakEvent(net.minecraft.world.entity.EquipmentSlot.HEAD));
                }
                if (!level.isClientSide) {
                    if (player instanceof ServerPlayer sPlayer) {
                        sPlayer.playNotifySound(SoundEvents.BEACON_ACTIVATE, SoundSource.PLAYERS, 0.05f, 0.5f);
                    }
                }
            }
        }

        if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof VisorHelmetItem) {
            CompoundTag nbt = pStack.getOrCreateTag();
            nbt.putInt("Cooldown", nbt.getInt("Cooldown") + 1);

            if (nbt.getInt("Cooldown") >= getMaxCoolDown()) {
                nbt.putInt("Cooldown", 0);

                if (player.getMainHandItem().getItem() instanceof PickaxeItem ||
                        player.getMainHandItem().is(AllTags.forgeItemTag("tools/pickaxe")) ||
                        player.getMainHandItem().is(AllTags.forgeItemTag("tools/drill"))) {

                    if (randomChance(10, level)) {
                        pStack.hurtAndBreak(randomChance(25, level) ? 10 : 5, player, e -> e.broadcastBreakEvent(net.minecraft.world.entity.EquipmentSlot.HEAD));
                    }
                    if (!level.isClientSide) {
                        if (level instanceof ServerLevel sLevel && player instanceof ServerPlayer sPlayer) {
                            boolean oreFound = findAndEmitOreParticles(sLevel, sPlayer);

                            if (oreFound) {
                                sPlayer.playNotifySound(SoundEvents.BEACON_DEACTIVATE, SoundSource.PLAYERS, 0.1f, 0.5f);
                            }
                        }
                    }
                }
            }
        }
    }

    private static int getMaxCoolDown() {
        return DesiresConfigs.server().equipment.visorCooldownMaxTick.get();
    }

    private static int getRange() {
        return DesiresConfigs.server().equipment.visorXRAYRange.get();
    }

    private boolean findAndEmitOreParticles(ServerLevel level, ServerPlayer player) {
        BlockPos playerPos = player.blockPosition();
        AABB searchBox = new AABB(playerPos).inflate(getRange());
        boolean oreFound = false;

        for (BlockPos pos : BlockPos.betweenClosedStream(searchBox).map(BlockPos::immutable).toList()) {
            BlockState blockState = level.getBlockState(pos);

            if (isOreBlock(blockState)) {
                oreFound = true;
                sendGlowingParticlesToPlayer(pos, player);
            }
        }

        return oreFound;
    }

    private boolean isOreBlock(BlockState state) {
        return state.is(DesiresTags.AllBlockTags.EXCAVATION_DRILL_VEIN_VALID.tag);
    }

    private void sendGlowingParticlesToPlayer(BlockPos pos, ServerPlayer player) {
        Vec3 particlePos = Vec3.atCenterOf(pos);

        ClientboundLevelParticlesPacket particlePacket = new ClientboundLevelParticlesPacket(
                XrayParticle.regParticle.XRAY_PARTICLE.get().getType(), true, particlePos.x, particlePos.y, particlePos.z,
                0.0F, 0.0F, 0.0F, 0.0F, 1
        );
        player.connection.send(particlePacket);
    }
}

