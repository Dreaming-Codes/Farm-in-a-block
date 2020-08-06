package codes.dreaming.farminacube.blocks;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

public class StartupClientOnly
{
    public static void preInitClientOnly()
    {
        ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("farminablock:greenhouse", "normal");
    }
}