package uwu.lopyluna.create_dd.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.simibubi.create.content.contraptions.Contraption;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uwu.lopyluna.create_dd.content.blocks.logistics.fluid_reservoir.FluidReservoirBlockEntity;
import uwu.lopyluna.create_dd.content.blocks.logistics.item_stockpile.ItemStockpileBlockEntity;

@Mixin(value = Contraption.class, remap = false)
public abstract class MixinContraption {
    @WrapOperation(method = "getBlockEntityNBT", at = @At(value = "CONSTANT", args = "classValue=com/simibubi/create/content/fluids/tank/FluidTankBlockEntity"))
    protected boolean create_dd$fixControllerPosForDDBlocks(Object object, Operation<Boolean> original) {
        return original.call(object) || object instanceof ItemStockpileBlockEntity || object instanceof FluidReservoirBlockEntity;
    }
}
