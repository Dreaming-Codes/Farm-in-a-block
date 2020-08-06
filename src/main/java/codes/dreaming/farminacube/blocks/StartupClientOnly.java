package codes.dreaming.farminacube.blocks;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class StartupClientOnly
{
    public static void preInitClientOnly()
    {
        ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("farminablock:greenhouse", "normal");
    }

    public static void initClientOnly()
    {
    }

    public static void postInitClientOnly()
    {
    }
}