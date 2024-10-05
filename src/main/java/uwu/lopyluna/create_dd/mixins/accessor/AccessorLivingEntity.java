package uwu.lopyluna.create_dd.mixins.accessor;

import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LivingEntity.class)
public interface AccessorLivingEntity {
    @Accessor("jumping")
    boolean isJumping$DnDesires();
}
