package uwu.lopyluna.create_dd.content.items.equipment;

import com.simibubi.create.foundation.networking.SimplePacketBase;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import uwu.lopyluna.create_dd.infrastructure.config.DesiresConfigs;

public class InvertFunctionPacket extends SimplePacketBase {

    public InvertFunctionPacket() {}
    public InvertFunctionPacket(FriendlyByteBuf buffer) {}

    @Override
    public void write(FriendlyByteBuf buffer) {}

    @Override
    public boolean handle(NetworkEvent.Context context) {
        if (context.getDirection() != NetworkDirection.PLAY_TO_SERVER)
            return false;
        context.enqueueWork(() -> {
            DesiresConfigs.client().invertDeforesterSawFunction.get();
            DesiresConfigs.client().invertExcavationDrillFunction.get();
        });
        return true;
    }
}
