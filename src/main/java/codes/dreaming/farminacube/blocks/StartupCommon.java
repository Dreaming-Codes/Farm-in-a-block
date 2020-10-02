package codes.dreaming.farminacube.blocks;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class StartupCommon
{
    public static GreenhouseBlock greenhouseBlock;

    public static void preInitCommon()
    {
        greenhouseBlock = (GreenhouseBlock)(new GreenhouseBlock().setUnlocalizedName("greenhouse"));
        greenhouseBlock.setRegistryName("greenhouse");
        ForgeRegistries.BLOCKS.register(greenhouseBlock);
    }

}