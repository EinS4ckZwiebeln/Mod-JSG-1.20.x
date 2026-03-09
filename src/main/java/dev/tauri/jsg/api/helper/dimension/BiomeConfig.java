package dev.tauri.jsg.api.helper.dimension;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.ArrayList;
import java.util.List;

/**
 * Configuration for a biome within a stargate dimension.
 * Specifies biome properties such as temperature, downfall, colors, and ore generation.
 */
public class BiomeConfig implements INBTSerializable<CompoundTag> {

    private ResourceLocation biomeId;
    private float temperature;
    private float downfall;
    private boolean hasPrecipitation;
    private int skyColor;
    private int fogColor;
    private int waterColor;
    private int waterFogColor;
    private List<OreConfig> ores;
    private double weight;

    public BiomeConfig(ResourceLocation biomeId, float temperature, float downfall, boolean hasPrecipitation,
                       int skyColor, int fogColor, int waterColor, int waterFogColor,
                       List<OreConfig> ores, double weight) {
        this.biomeId = biomeId;
        this.temperature = temperature;
        this.downfall = downfall;
        this.hasPrecipitation = hasPrecipitation;
        this.skyColor = skyColor;
        this.fogColor = fogColor;
        this.waterColor = waterColor;
        this.waterFogColor = waterFogColor;
        this.ores = ores != null ? new ArrayList<>(ores) : new ArrayList<>();
        this.weight = weight;
    }

    public BiomeConfig(CompoundTag compound) {
        this.ores = new ArrayList<>();
        deserializeNBT(compound);
    }

    public ResourceLocation getBiomeId() {
        return biomeId;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getDownfall() {
        return downfall;
    }

    public boolean hasPrecipitation() {
        return hasPrecipitation;
    }

    public int getSkyColor() {
        return skyColor;
    }

    public int getFogColor() {
        return fogColor;
    }

    public int getWaterColor() {
        return waterColor;
    }

    public int getWaterFogColor() {
        return waterFogColor;
    }

    public List<OreConfig> getOres() {
        return ores;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compound = new CompoundTag();
        compound.putString("biomeId", biomeId.toString());
        compound.putFloat("temperature", temperature);
        compound.putFloat("downfall", downfall);
        compound.putBoolean("hasPrecipitation", hasPrecipitation);
        compound.putInt("skyColor", skyColor);
        compound.putInt("fogColor", fogColor);
        compound.putInt("waterColor", waterColor);
        compound.putInt("waterFogColor", waterFogColor);
        compound.putDouble("weight", weight);

        ListTag oreList = new ListTag();
        for (OreConfig ore : ores) {
            oreList.add(ore.serializeNBT());
        }
        compound.put("ores", oreList);

        return compound;
    }

    @Override
    public void deserializeNBT(CompoundTag compound) {
        this.biomeId = new ResourceLocation(compound.getString("biomeId"));
        this.temperature = compound.getFloat("temperature");
        this.downfall = compound.getFloat("downfall");
        this.hasPrecipitation = compound.getBoolean("hasPrecipitation");
        this.skyColor = compound.getInt("skyColor");
        this.fogColor = compound.getInt("fogColor");
        this.waterColor = compound.getInt("waterColor");
        this.waterFogColor = compound.getInt("waterFogColor");
        this.weight = compound.getDouble("weight");

        this.ores = new ArrayList<>();
        ListTag oreList = compound.getList("ores", Tag.TAG_COMPOUND);
        for (int i = 0; i < oreList.size(); i++) {
            ores.add(new OreConfig(oreList.getCompound(i)));
        }
    }

    public static class Builder {
        private ResourceLocation biomeId;
        private float temperature = 0.5f;
        private float downfall = 0.5f;
        private boolean hasPrecipitation = true;
        private int skyColor = 7907327;
        private int fogColor = 12638463;
        private int waterColor = 4159204;
        private int waterFogColor = 329011;
        private final List<OreConfig> ores = new ArrayList<>();
        private double weight = 1.0;

        public Builder(ResourceLocation biomeId) {
            this.biomeId = biomeId;
        }

        public Builder temperature(float temperature) {
            this.temperature = temperature;
            return this;
        }

        public Builder downfall(float downfall) {
            this.downfall = downfall;
            return this;
        }

        public Builder hasPrecipitation(boolean hasPrecipitation) {
            this.hasPrecipitation = hasPrecipitation;
            return this;
        }

        public Builder skyColor(int skyColor) {
            this.skyColor = skyColor;
            return this;
        }

        public Builder fogColor(int fogColor) {
            this.fogColor = fogColor;
            return this;
        }

        public Builder waterColor(int waterColor) {
            this.waterColor = waterColor;
            return this;
        }

        public Builder waterFogColor(int waterFogColor) {
            this.waterFogColor = waterFogColor;
            return this;
        }

        public Builder addOre(OreConfig ore) {
            this.ores.add(ore);
            return this;
        }

        public Builder weight(double weight) {
            this.weight = weight;
            return this;
        }

        public BiomeConfig build() {
            return new BiomeConfig(biomeId, temperature, downfall, hasPrecipitation,
                    skyColor, fogColor, waterColor, waterFogColor, ores, weight);
        }
    }
}
