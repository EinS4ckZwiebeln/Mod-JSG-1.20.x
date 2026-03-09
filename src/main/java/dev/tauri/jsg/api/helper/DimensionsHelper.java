package dev.tauri.jsg.api.helper;

import dev.tauri.jsg.api.JSGApi;
import dev.tauri.jsg.api.helper.dimension.IStargateDimensionManager;
import dev.tauri.jsg.api.stargate.network.address.StargateAddress;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class DimensionsHelper {
    public static ServerLevel getLevel(ResourceKey<Level> dim) {
        return JSGApi.currentServer.getLevel(dim);
    }

    public static ResourceKey<Level> getDimension(ResourceLocation rl) {
        return ResourceKey.create(Registries.DIMENSION, rl);
    }

    public static List<ResourceKey<Level>> getDims() {
        var list = new ArrayList<ResourceKey<Level>>();
        JSGApi.currentServer.getAllLevels().forEach(e -> list.add(e.dimension()));
        return list;
    }

    /**
     * Gets the stargate dimension manager instance.
     *
     * @return The dimension manager, or null if not initialized.
     */
    @Nullable
    public static IStargateDimensionManager getDimensionManager() {
        return JSGApi.dimensionManager;
    }

    /**
     * Gets or creates a dimension for the given stargate address.
     * Delegates to {@link IStargateDimensionManager#getOrCreateDimension(StargateAddress)}.
     *
     * @param address The stargate address.
     * @return The ServerLevel for the dimension, or null if unavailable.
     */
    @Nullable
    public static ServerLevel getOrCreateDimensionForAddress(StargateAddress address) {
        var manager = getDimensionManager();
        if (manager == null) return null;
        return manager.getOrCreateDimension(address);
    }

    /**
     * Checks whether a dimension exists for the given stargate address.
     *
     * @param address The stargate address to check.
     * @return true if a dimension is mapped to this address.
     */
    public static boolean hasDimensionForAddress(StargateAddress address) {
        var manager = getDimensionManager();
        if (manager == null) return false;
        return manager.hasDimension(address);
    }

    @SuppressWarnings("deprecation")
    public static BlockPos getTopBlockWithPos(ServerLevel level, int x, int z, int structureX, int structureZ) {
        int startY = 255;
        if (level.dimension() == Level.NETHER) startY = 110;
        else {
            structureX = 1;
            structureZ = 1;
        }
        for (int offX = 0; offX < 4; offX++) {
            for (int offZ = 0; offZ < 4; offZ++) {
                boolean foundAir = false;
                int minHeight = Integer.MAX_VALUE;
                int countHeights = 0;
                int sumHeights = 0;
                int hillCoef = 0;
                for (int i = 0; i < structureX; i++) {
                    for (int j = 0; j < structureZ; j++) {
                        var currentX = x + (offX * structureX) + i;
                        var currentZ = z + (offZ * structureZ) + j;
                        level.getPoiManager().ensureLoadedAndValid(level, new BlockPos(currentX, startY, currentZ), 128);
                        for (int y = startY; y > 0; y--) {
                            if (level.getBlockState(new BlockPos(currentX, y, currentZ)).canBeReplaced()) {
                                if (level.getBlockState(new BlockPos(currentX, y, currentZ)).liquid()) break;
                                foundAir = true;
                                continue;
                            }
                            if (!foundAir) continue;
                            if (countHeights == 0)
                                sumHeights = y;
                            else
                                sumHeights += y;
                            countHeights++;
                            if (((sumHeights / (double) countHeights) - y) > 3) hillCoef++;
                            if (y < minHeight) minHeight = y;
                            break;
                        }
                    }
                }
                if (hillCoef > 5) continue;
                var currentX = x + (offX * structureX);
                var currentZ = z + (offZ * structureZ);
                return new BlockPos(currentX, minHeight + 1, currentZ);
            }
        }
        return new BlockPos(x, 255, z);
    }
}
