package dev.tauri.jsg.api.helper.dimension;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * Configuration for ore generation within a stargate dimension.
 * Specifies which ore to generate, its frequency, vein size, and height range.
 */
public class OreConfig implements INBTSerializable<CompoundTag> {

    private ResourceLocation oreBlock;
    private int veinSize;
    private int veinsPerChunk;
    private int minHeight;
    private int maxHeight;

    public OreConfig(ResourceLocation oreBlock, int veinSize, int veinsPerChunk, int minHeight, int maxHeight) {
        this.oreBlock = oreBlock;
        this.veinSize = veinSize;
        this.veinsPerChunk = veinsPerChunk;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    public OreConfig(CompoundTag compound) {
        deserializeNBT(compound);
    }

    public ResourceLocation getOreBlock() {
        return oreBlock;
    }

    public int getVeinSize() {
        return veinSize;
    }

    public int getVeinsPerChunk() {
        return veinsPerChunk;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compound = new CompoundTag();
        compound.putString("oreBlock", oreBlock.toString());
        compound.putInt("veinSize", veinSize);
        compound.putInt("veinsPerChunk", veinsPerChunk);
        compound.putInt("minHeight", minHeight);
        compound.putInt("maxHeight", maxHeight);
        return compound;
    }

    @Override
    public void deserializeNBT(CompoundTag compound) {
        this.oreBlock = new ResourceLocation(compound.getString("oreBlock"));
        this.veinSize = compound.getInt("veinSize");
        this.veinsPerChunk = compound.getInt("veinsPerChunk");
        this.minHeight = compound.getInt("minHeight");
        this.maxHeight = compound.getInt("maxHeight");
    }

    public static class Builder {
        private ResourceLocation oreBlock;
        private int veinSize = 8;
        private int veinsPerChunk = 10;
        private int minHeight = 0;
        private int maxHeight = 64;

        public Builder(ResourceLocation oreBlock) {
            this.oreBlock = oreBlock;
        }

        public Builder veinSize(int veinSize) {
            this.veinSize = veinSize;
            return this;
        }

        public Builder veinsPerChunk(int veinsPerChunk) {
            this.veinsPerChunk = veinsPerChunk;
            return this;
        }

        public Builder minHeight(int minHeight) {
            this.minHeight = minHeight;
            return this;
        }

        public Builder maxHeight(int maxHeight) {
            this.maxHeight = maxHeight;
            return this;
        }

        public OreConfig build() {
            return new OreConfig(oreBlock, veinSize, veinsPerChunk, minHeight, maxHeight);
        }
    }
}
