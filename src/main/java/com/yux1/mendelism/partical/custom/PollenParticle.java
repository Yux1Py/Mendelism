package com.yux1.mendelism.partical.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class PollenParticle extends SpriteBillboardParticle {
    private final float rotationSpeed;
    protected PollenParticle(ClientWorld level, double xCoord, double yCoord, double zCoord,
                             SpriteProvider spriteSet, double xd, double yd, double zd) {
        super(level, xCoord, yCoord, zCoord, xd, yd, zd);
        this.velocityMultiplier = 0.6F;
        this.x = xd;
        this.y = yd;
        this.z = zd;
        this.scale *= 0.75F;
        this.maxAge = 20;
        this.setSpriteForAge(spriteSet);

        this.rotationSpeed = ((float)Math.random() - 0.5F) * 0.1F;
        this.angle = (float)Math.random() * 6.2831855F;

        this.red = 1f;
        this.green = 1f;
        this.blue = 1f;
    }

    @Override
    public void tick() {
        super.tick();
        this.prevAngle = this.angle;
        this.angle += 3.1415927F * this.rotationSpeed * 2.0F;
        if (this.onGround) {
            this.prevAngle = this.angle = 0.0F;
        }
        fadeOut();
    }

    private void fadeOut() {
        this.alpha = (-(1/(float)maxAge) * age + 1);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider sprites;

        public Factory(SpriteProvider spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(DefaultParticleType particleType, ClientWorld level, double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new PollenParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }
}
