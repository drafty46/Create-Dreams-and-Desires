package uwu.lopyluna.create_dd.mixins.accessor;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Entity.class)
public interface AccessorEntity {

    @Invoker("setSharedFlag")
    void setSharedFlag$DnDesires(int pFlag, boolean pSet);
}
