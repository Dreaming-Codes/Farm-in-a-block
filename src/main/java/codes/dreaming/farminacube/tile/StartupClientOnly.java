package codes.dreaming.farminacube.tile;

import net.minecraftforge.fml.client.registry.ClientRegistry;

public class StartupClientOnly {
    public static void initClientOnly() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileGreenhouse.class, new TileGreenhouseRender());
    }
}
