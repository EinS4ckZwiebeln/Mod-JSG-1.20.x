package dev.tauri.jsg.api.helper.dimension;

import dev.tauri.jsg.api.stargate.network.address.StargateAddress;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a custom dimension entry with a fixed stargate address.
 * Developers can register custom dimensions that are always reachable
 * at the same address and have predefined dimension configurations.
 */
public class CustomDimensionEntry implements INBTSerializable<CompoundTag> {

    private ResourceLocation dimensionId;
    private List<StargateAddress> fixedAddresses;
    private StargateDimensionConfig dimensionConfig;

    public CustomDimensionEntry(ResourceLocation dimensionId,
                                List<StargateAddress> fixedAddresses,
                                @Nullable StargateDimensionConfig dimensionConfig) {
        this.dimensionId = dimensionId;
        this.fixedAddresses = fixedAddresses != null ? new ArrayList<>(fixedAddresses) : new ArrayList<>();
        this.dimensionConfig = dimensionConfig;
    }

    public CustomDimensionEntry(CompoundTag compound) {
        this.fixedAddresses = new ArrayList<>();
        deserializeNBT(compound);
    }

    public ResourceLocation getDimensionId() {
        return dimensionId;
    }

    public List<StargateAddress> getFixedAddresses() {
        return fixedAddresses;
    }

    @Nullable
    public StargateDimensionConfig getDimensionConfig() {
        return dimensionConfig;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compound = new CompoundTag();
        compound.putString("dimensionId", dimensionId.toString());

        ListTag addressList = new ListTag();
        for (StargateAddress address : fixedAddresses) {
            addressList.add(address.serializeNBT());
        }
        compound.put("fixedAddresses", addressList);

        if (dimensionConfig != null) {
            compound.put("dimensionConfig", dimensionConfig.serializeNBT());
        }

        return compound;
    }

    @Override
    public void deserializeNBT(CompoundTag compound) {
        this.dimensionId = new ResourceLocation(compound.getString("dimensionId"));

        this.fixedAddresses = new ArrayList<>();
        ListTag addressList = compound.getList("fixedAddresses", Tag.TAG_COMPOUND);
        for (int i = 0; i < addressList.size(); i++) {
            fixedAddresses.add(new StargateAddress(addressList.getCompound(i)));
        }

        if (compound.contains("dimensionConfig")) {
            this.dimensionConfig = new StargateDimensionConfig(compound.getCompound("dimensionConfig"));
        }
    }

    public static class Builder {
        private ResourceLocation dimensionId;
        private final List<StargateAddress> fixedAddresses = new ArrayList<>();
        private StargateDimensionConfig dimensionConfig;

        public Builder(ResourceLocation dimensionId) {
            this.dimensionId = dimensionId;
        }

        public Builder addFixedAddress(StargateAddress address) {
            this.fixedAddresses.add(address);
            return this;
        }

        public Builder dimensionConfig(StargateDimensionConfig config) {
            this.dimensionConfig = config;
            return this;
        }

        public CustomDimensionEntry build() {
            return new CustomDimensionEntry(dimensionId, fixedAddresses, dimensionConfig);
        }
    }
}
