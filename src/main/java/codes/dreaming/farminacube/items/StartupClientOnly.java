package codes.dreaming.farminacube.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class StartupClientOnly {
    public static void preInitClientOnly(){
        ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("farminablock:greenhouse", "normal");
        ModelLoader.setCustomModelResourceLocation(StartupCommon.greenhouseItem, 0, itemModelResourceLocation);
    }
}
