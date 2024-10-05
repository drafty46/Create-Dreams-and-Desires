package uwu.lopyluna.create_dd.content.items.equipment.visor_helmet;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import static uwu.lopyluna.create_dd.DesiresCreate.MOD_ID;

public class XrayParticle extends TextureSheetParticle {
    static final RandomSource RANDOM = RandomSource.create();

    protected XrayParticle(ClientLevel level, double x, double y, double z, double pXSpeed, double pYSpeed, double pZSpeed, SpriteSet spriteSet) {
        super(level, x, y, z, pXSpeed, pYSpeed, pZSpeed);
        this.friction = 0.96F;
        this.setSize(0.8F, 0.8F);
        this.hasPhysics = false;
        this.setSpriteFromAge(spriteSet);
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return XrayParticleRenderType.INSTANCE;
    }

    public int getLightColor(float pPartialTick) {
        float f = ((float)this.age + pPartialTick) / (float)this.lifetime;
        f = Mth.clamp(f, 0.0F, 1.0F);
        int i = super.getLightColor(pPartialTick);
        int j = i & 255;
        int k = i >> 16 & 255;
        j += (int)(f * 15.0F * 16.0F);
        if (j > 240) {
            j = 240;
        }

        return j | k << 16;
    }

    @Override
    public void render(@NotNull VertexConsumer buffer, @NotNull Camera renderInfo, float partialTicks) {
        super.render(buffer, renderInfo, partialTicks);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.age++ >= this.lifetime) {
            this.remove();
        }
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Provider(SpriteSet sprite) {
            this.sprite = sprite;
        }

        @Override
        public Particle createParticle(@NotNull SimpleParticleType type, @NotNull ClientLevel level, double x, double y, double z, double xd, double yd, double zd) {
            XrayParticle glowParticle = new XrayParticle(level, x, y, z, 0.5D - RANDOM.nextDouble(), yd, 0.5D - RANDOM.nextDouble(), this.sprite);
            glowParticle.pickSprite(this.sprite);
            if (level.random.nextBoolean()) {
                glowParticle.setColor(1.0F, 1.0F, 1.0F);
            } else if (level.random.nextBoolean()) {
                glowParticle.setColor(1.0F, 1.0F, 0.5F);
            } else if (level.random.nextBoolean()) {
                glowParticle.setColor(0.5F, 1.0F, 1.0F);
            } else if (level.random.nextBoolean()) {
                glowParticle.setColor(0.5F, 1.0F, 0.5F);
            } else {
                glowParticle.setColor(0.5F, 0.5F, 0.5F);
            }

            glowParticle.yd *= 0.2F;
            if (xd == 0.0D && zd == 0.0D) {
                glowParticle.xd *= 0.1F;
                glowParticle.zd *= 0.1F;
            }

            glowParticle.setLifetime((int)(8.0D / (level.random.nextDouble() * 0.8D + 0.2D)));
            return glowParticle;
        }
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class regParticle {

        public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MOD_ID);

        public static final RegistryObject<SimpleParticleType> XRAY_PARTICLE = PARTICLES.register("xray_particle", () -> new SimpleParticleType(true));

        @Mod.EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
        public static class ClientOnly {

            @SubscribeEvent
            public static void registerParticles(RegisterParticleProvidersEvent event) {
                event.register(XRAY_PARTICLE.get(), XrayParticle.Provider::new);
            }
        }
    }
}