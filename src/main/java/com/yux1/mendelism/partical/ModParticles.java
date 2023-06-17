package com.yux1.mendelism.partical;


import com.yux1.mendelism.Mendelism;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModParticles {
    public static final DefaultParticleType RED_POLLEN_PARTICLE = FabricParticleTypes.simple();
    public static final DefaultParticleType YELLOW_POLLEN_PARTICLE = FabricParticleTypes.simple();
    public static final DefaultParticleType PINK_POLLEN_PARTICLE = FabricParticleTypes.simple();
    public static final DefaultParticleType WHITE_POLLEN_PARTICLE = FabricParticleTypes.simple();

    public static final DefaultParticleType FEATHER_PARTICLE = FabricParticleTypes.simple();

    public static void registerParticles() {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(Mendelism.MOD_ID, "red_pollen_particle"),
                RED_POLLEN_PARTICLE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(Mendelism.MOD_ID, "yellow_pollen_particle"),
                YELLOW_POLLEN_PARTICLE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(Mendelism.MOD_ID, "pink_pollen_particle"),
                PINK_POLLEN_PARTICLE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(Mendelism.MOD_ID, "white_pollen_particle"),
                WHITE_POLLEN_PARTICLE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(Mendelism.MOD_ID, "feather_particle"),
                FEATHER_PARTICLE);
    }
}
