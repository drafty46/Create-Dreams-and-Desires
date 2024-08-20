package uwu.lopyluna.create_dd.events;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.foundation.render.SuperRenderTypeBuffer;
import com.simibubi.create.foundation.utility.AnimationTickHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import uwu.lopyluna.create_dd.DesireClient;

@SuppressWarnings({"removal"})
@Mod.EventBusSubscriber(Dist.CLIENT)
public class ClientEvents {
    
    @SubscribeEvent
    public static void onTick(TickEvent.ClientTickEvent event) {
        DesireClient.DEBUG_OUTLINER.tickOutlines();
    }
    
    @SubscribeEvent
    public static void onRenderWorld(net.minecraftforge.client.event.RenderLevelLastEvent event) {
        PoseStack ms = event.getPoseStack();
        ms.pushPose();
        SuperRenderTypeBuffer buffer = SuperRenderTypeBuffer.getInstance();
        float partialTicks = AnimationTickHolder.getPartialTicks();
        Vec3 camera = Minecraft.getInstance().gameRenderer.getMainCamera()
            .getPosition();
        DesireClient.DEBUG_OUTLINER.renderOutlines(ms, buffer, camera, partialTicks);
    }
    
}
