package uwu.lopyluna.create_dd.mixins;

import com.simibubi.create.api.connectivity.ConnectivityHandler;
import com.simibubi.create.content.contraptions.BlockMovementChecks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uwu.lopyluna.create_dd.content.blocks.logistics.fluid_reservoir.FluidReservoirBlock;
import uwu.lopyluna.create_dd.content.blocks.logistics.item_stockpile.ItemStockpileBlock;

@Mixin(value = BlockMovementChecks.class, remap = false)
public class MixinBlockMovementChecks {

    @Inject(at = @At("TAIL"), method = "isBlockAttachedTowardsFallback(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;)Z", cancellable = true)
    private static void isBlockAttachedTowardsFallback(BlockState state, Level world, BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {

        if (state.getBlock() instanceof FluidReservoirBlock)
            cir.setReturnValue(ConnectivityHandler.isConnected(world, pos, pos.relative(direction)));
        if (state.getBlock() instanceof ItemStockpileBlock)
            cir.setReturnValue(ConnectivityHandler.isConnected(world, pos, pos.relative(direction)));
    }
}
