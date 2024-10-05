package uwu.lopyluna.create_dd.content.items.equipment.jetpack;

import com.jozufozu.flywheel.core.PartialModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import com.simibubi.create.foundation.utility.AngleHelper;
import com.simibubi.create.foundation.utility.AnimationTickHolder;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import uwu.lopyluna.create_dd.registry.DesiresPartialModels;

public class JetpackRenderer extends KineticBlockEntityRenderer<JetpackBlockEntity> {
    public JetpackRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void renderSafe(JetpackBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer,
                              int light, int overlay) {
        super.renderSafe(be, partialTicks, ms, buffer, light, overlay);

        BlockState blockState = be.getBlockState();
        SuperByteBuffer cogs = CachedBufferer.partial(getCogsModel(), blockState);
        cogs.centre()
                .rotateY(180 + AngleHelper.horizontalAngle(blockState.getValue(JetpackBlock.HORIZONTAL_FACING)))
                .unCentre()
                .translate(0, 6.5f / 16, 11f / 16)
                .rotate(Direction.EAST,
                        AngleHelper.rad(be.getSpeed() / 4f * AnimationTickHolder.getRenderTime(be.getLevel()) % 360))
                .translate(0, -6.5f / 16, -11f / 16);
        cogs.light(light)
                .renderInto(ms, buffer.getBuffer(RenderType.solid()));
    }

    @Override
    protected SuperByteBuffer getRotatedModel(JetpackBlockEntity be, BlockState state) {
        return CachedBufferer.partial(getShaftModel(), state);
    }

    public static PartialModel getCogsModel() {
        return DesiresPartialModels.JETPACK_COGS;
    }

    public static PartialModel getShaftModel() {
        return DesiresPartialModels.JETPACK_SHAFT;
    }
}
