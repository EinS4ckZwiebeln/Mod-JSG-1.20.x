package dev.tauri.jsg.api.helper.dimension;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.ArrayList;
import java.util.List;

/**
 * Complete configuration for a stargate dimension.
 * Contains dimension type properties, biome configurations, and other settings
 * needed to generate a new dimension accessible via a stargate.
 */
public class StargateDimensionConfig implements INBTSerializable<CompoundTag> {

    private ResourceLocation dimensionId;
    private List<BiomeConfig> biomes;

    // Dimension type properties
    private boolean ultrawarm;
    private boolean natural;
    private boolean hasSkylight;
    private boolean hasCeiling;
    private boolean bedWorks;
    private boolean respawnAnchorWorks;
    private boolean hasRaids;
    private boolean piglinSafe;
    private float ambientLight;
    private int logicalHeight;
    private int minY;
    private int height;
    private int seaLevel;
    private ResourceLocation effects;

    public StargateDimensionConfig(ResourceLocation dimensionId, List<BiomeConfig> biomes,
                                   boolean ultrawarm, boolean natural, boolean hasSkylight,
                                   boolean hasCeiling, boolean bedWorks, boolean respawnAnchorWorks,
                                   boolean hasRaids, boolean piglinSafe, float ambientLight,
                                   int logicalHeight, int minY, int height, int seaLevel,
                                   ResourceLocation effects) {
        this.dimensionId = dimensionId;
        this.biomes = biomes != null ? new ArrayList<>(biomes) : new ArrayList<>();
        this.ultrawarm = ultrawarm;
        this.natural = natural;
        this.hasSkylight = hasSkylight;
        this.hasCeiling = hasCeiling;
        this.bedWorks = bedWorks;
        this.respawnAnchorWorks = respawnAnchorWorks;
        this.hasRaids = hasRaids;
        this.piglinSafe = piglinSafe;
        this.ambientLight = ambientLight;
        this.logicalHeight = logicalHeight;
        this.minY = minY;
        this.height = height;
        this.seaLevel = seaLevel;
        this.effects = effects;
    }

    public StargateDimensionConfig(CompoundTag compound) {
        this.biomes = new ArrayList<>();
        deserializeNBT(compound);
    }

    public ResourceLocation getDimensionId() {
        return dimensionId;
    }

    public List<BiomeConfig> getBiomes() {
        return biomes;
    }

    public boolean isUltrawarm() {
        return ultrawarm;
    }

    public boolean isNatural() {
        return natural;
    }

    public boolean hasSkylight() {
        return hasSkylight;
    }

    public boolean hasCeiling() {
        return hasCeiling;
    }

    public boolean bedWorks() {
        return bedWorks;
    }

    public boolean respawnAnchorWorks() {
        return respawnAnchorWorks;
    }

    public boolean hasRaids() {
        return hasRaids;
    }

    public boolean isPiglinSafe() {
        return piglinSafe;
    }

    public float getAmbientLight() {
        return ambientLight;
    }

    public int getLogicalHeight() {
        return logicalHeight;
    }

    public int getMinY() {
        return minY;
    }

    public int getHeight() {
        return height;
    }

    public int getSeaLevel() {
        return seaLevel;
    }

    public ResourceLocation getEffects() {
        return effects;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compound = new CompoundTag();
        compound.putString("dimensionId", dimensionId.toString());
        compound.putBoolean("ultrawarm", ultrawarm);
        compound.putBoolean("natural", natural);
        compound.putBoolean("hasSkylight", hasSkylight);
        compound.putBoolean("hasCeiling", hasCeiling);
        compound.putBoolean("bedWorks", bedWorks);
        compound.putBoolean("respawnAnchorWorks", respawnAnchorWorks);
        compound.putBoolean("hasRaids", hasRaids);
        compound.putBoolean("piglinSafe", piglinSafe);
        compound.putFloat("ambientLight", ambientLight);
        compound.putInt("logicalHeight", logicalHeight);
        compound.putInt("minY", minY);
        compound.putInt("height", height);
        compound.putInt("seaLevel", seaLevel);
        compound.putString("effects", effects.toString());

        ListTag biomeList = new ListTag();
        for (BiomeConfig biome : biomes) {
            biomeList.add(biome.serializeNBT());
        }
        compound.put("biomes", biomeList);

        return compound;
    }

    @Override
    public void deserializeNBT(CompoundTag compound) {
        this.dimensionId = new ResourceLocation(compound.getString("dimensionId"));
        this.ultrawarm = compound.getBoolean("ultrawarm");
        this.natural = compound.getBoolean("natural");
        this.hasSkylight = compound.getBoolean("hasSkylight");
        this.hasCeiling = compound.getBoolean("hasCeiling");
        this.bedWorks = compound.getBoolean("bedWorks");
        this.respawnAnchorWorks = compound.getBoolean("respawnAnchorWorks");
        this.hasRaids = compound.getBoolean("hasRaids");
        this.piglinSafe = compound.getBoolean("piglinSafe");
        this.ambientLight = compound.getFloat("ambientLight");
        this.logicalHeight = compound.getInt("logicalHeight");
        this.minY = compound.getInt("minY");
        this.height = compound.getInt("height");
        this.seaLevel = compound.getInt("seaLevel");
        this.effects = new ResourceLocation(compound.getString("effects"));

        this.biomes = new ArrayList<>();
        ListTag biomeList = compound.getList("biomes", Tag.TAG_COMPOUND);
        for (int i = 0; i < biomeList.size(); i++) {
            biomes.add(new BiomeConfig(biomeList.getCompound(i)));
        }
    }

    /**
     * Builder for creating StargateDimensionConfig instances with sensible defaults.
     * Default values are modeled after the existing Abydos dimension.
     */
    public static class Builder {
        private ResourceLocation dimensionId;
        private final List<BiomeConfig> biomes = new ArrayList<>();
        private boolean ultrawarm = false;
        private boolean natural = true;
        private boolean hasSkylight = true;
        private boolean hasCeiling = false;
        private boolean bedWorks = true;
        private boolean respawnAnchorWorks = false;
        private boolean hasRaids = true;
        private boolean piglinSafe = false;
        private float ambientLight = 0f;
        private int logicalHeight = 256;
        private int minY = 0;
        private int height = 256;
        private int seaLevel = 0;
        private ResourceLocation effects = new ResourceLocation("minecraft", "overworld");

        public Builder(ResourceLocation dimensionId) {
            this.dimensionId = dimensionId;
        }

        public Builder addBiome(BiomeConfig biome) {
            this.biomes.add(biome);
            return this;
        }

        public Builder ultrawarm(boolean ultrawarm) {
            this.ultrawarm = ultrawarm;
            return this;
        }

        public Builder natural(boolean natural) {
            this.natural = natural;
            return this;
        }

        public Builder hasSkylight(boolean hasSkylight) {
            this.hasSkylight = hasSkylight;
            return this;
        }

        public Builder hasCeiling(boolean hasCeiling) {
            this.hasCeiling = hasCeiling;
            return this;
        }

        public Builder bedWorks(boolean bedWorks) {
            this.bedWorks = bedWorks;
            return this;
        }

        public Builder respawnAnchorWorks(boolean respawnAnchorWorks) {
            this.respawnAnchorWorks = respawnAnchorWorks;
            return this;
        }

        public Builder hasRaids(boolean hasRaids) {
            this.hasRaids = hasRaids;
            return this;
        }

        public Builder piglinSafe(boolean piglinSafe) {
            this.piglinSafe = piglinSafe;
            return this;
        }

        public Builder ambientLight(float ambientLight) {
            this.ambientLight = ambientLight;
            return this;
        }

        public Builder logicalHeight(int logicalHeight) {
            this.logicalHeight = logicalHeight;
            return this;
        }

        public Builder minY(int minY) {
            this.minY = minY;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder seaLevel(int seaLevel) {
            this.seaLevel = seaLevel;
            return this;
        }

        public Builder effects(ResourceLocation effects) {
            this.effects = effects;
            return this;
        }

        public StargateDimensionConfig build() {
            return new StargateDimensionConfig(dimensionId, biomes, ultrawarm, natural,
                    hasSkylight, hasCeiling, bedWorks, respawnAnchorWorks,
                    hasRaids, piglinSafe, ambientLight, logicalHeight, minY, height,
                    seaLevel, effects);
        }
    }
}
