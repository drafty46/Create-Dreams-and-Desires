package uwu.lopyluna.create_dd.content.items.equipment.jetpack;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.content.equipment.armor.BacktankUtil;
import com.simibubi.create.foundation.gui.element.GuiGameElement;
import com.simibubi.create.foundation.utility.Components;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import uwu.lopyluna.create_dd.registry.DesiresItems;

import java.util.List;
import java.util.Objects;

public class JetpackOverlay implements IGuiOverlay {
    public static final JetpackOverlay INSTANCE = new JetpackOverlay();

    @Override
    @SuppressWarnings("all")
    public void render(ForgeGui gui, PoseStack poseStack, float partialTick, int width, int height) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.options.hideGui || Objects.requireNonNull(mc.gameMode).getPlayerMode() == GameType.SPECTATOR)
            return;

        LocalPlayer player = mc.player;
        if (player == null)
            return;
        if (!player.getPersistentData().contains("VisualJetpackAir"))
            return;
        if (!player.getPersistentData().contains("VisualJetpackCooldown"))
            return;
        if (!player.getPersistentData().contains("VisualJetpackHeating"))
            return;

        String a = player.getPersistentData().getString("VisualJetpackAir");
        String c = player.getPersistentData().getString("VisualJetpackCooldown");
        String h = player.getPersistentData().getString("VisualJetpackHeating");

        poseStack.pushPose();

        ItemStack backtank = getDisplayedBacktank(player);
        poseStack.translate(width / 2 + 90, height - 53 + (backtank.getItem().isFireResistant() ? 9 : 0), 0);

        Component textA = Components.literal(a);
        Component textC = Components.literal(c);
        Component textH = Components.literal(h);

        GuiGameElement.of(backtank)
                .at(0, 0)
                .render(poseStack);
        int color = 0xFF_FFFFFF;
        boolean cNA = !c.equals("NA");
        boolean hNA = !h.equals("NA");

        float top = hNA ? -5 : 0;
        float center = cNA ? 5 : 0;
        float bottom = hNA != cNA ? 10 : hNA && cNA ? 15 : 5;

        if (cNA)
            mc.font.drawShadow(poseStack, textC, 18, top, color);
        if (hNA)
            mc.font.drawShadow(poseStack, textH, 18, center, color);
        mc.font.drawShadow(poseStack, textA, 18, bottom, color);

        poseStack.popPose();
    }

    public static ItemStack getDisplayedBacktank(LocalPlayer player) {
        List<ItemStack> backtanks = BacktankUtil.getAllWithAir(player);
        if (!backtanks.isEmpty()) {
            return backtanks.get(0);
        }
        return DesiresItems.JETPACK.asStack();
    }
}
