package uwu.lopyluna.create_dd.content.packet;

import com.simibubi.create.foundation.networking.SimplePacketBase;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import uwu.lopyluna.create_dd.content.items.equipment.jetpack.JetpackItem;

public class JetpackActivatePacket extends SimplePacketBase {

    private final boolean activate;
    private final boolean havesJetpackOn;

    public JetpackActivatePacket(boolean activate, boolean hadJetpackOn) {
        this.activate = activate;
        this.havesJetpackOn = hadJetpackOn;
    }

    public JetpackActivatePacket(FriendlyByteBuf buf) {
        this.activate = buf.readBoolean();
        this.havesJetpackOn = buf.readBoolean();
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeBoolean(activate);
        buf.writeBoolean(havesJetpackOn);
    }

    @Override
    public boolean handle(NetworkEvent.Context context) {
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                ItemStack chestItem = player.getItemBySlot(EquipmentSlot.CHEST);
                if (chestItem.getItem() instanceof JetpackItem item && havesJetpackOn) {
                    item.activateFlying(chestItem, player, activate);
                } else if (!havesJetpackOn && player.getAbilities().flying && !player.isCreative() && !player.isSpectator()) {
                    player.getAbilities().flying = false;
                    player.onUpdateAbilities();
                }
            }
        });
        return true;
    }
}
