package codes.dreaming.farminacube.tile;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupCommon {
    public static void preInitCommon() {
        GameRegistry.registerTileEntity(TileGreenhouse.class, "farminacube:TileGreenhouse");
    }
}
