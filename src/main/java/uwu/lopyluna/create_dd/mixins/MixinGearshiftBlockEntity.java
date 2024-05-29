package uwu.lopyluna.create_dd.mixins;

import com.simibubi.create.content.kinetics.transmission.GearshiftBlockEntity;
import com.simibubi.create.content.kinetics.transmission.SplitShaftBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = GearshiftBlockEntity.class, remap = false)
public class MixinGearshiftBlockEntity extends SplitShaftBlockEntity {

    private MixinGearshiftBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    /**
     * @author _
     * @reason _
     */
    @Overwrite
    @Override
    public float getRotationSpeedModifier(Direction face) {
        boolean powered = getBlockState().getValue(BlockStateProperties.POWERED);
        boolean inverted = !getBlockState().getValue(BlockStateProperties.INVERTED);
        if (hasSource()) {
            if (face != getSourceFacing() && ((powered && !inverted) || !powered && inverted))
                return -1;
        }
        return 1;
    }


}
