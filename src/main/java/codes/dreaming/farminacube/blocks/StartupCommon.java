package codes.dreaming.farminacube.blocks;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Objects;

public class StartupCommon
{
    public static GreenhouseBlock greenhouseBlock;

    public static void preInitCommon()
    {
        greenhouseBlock = (GreenhouseBlock)(new GreenhouseBlock().setUnlocalizedName("Greenhouse"));
        greenhouseBlock.setRegistryName("greenhouse");
        ForgeRegistries.BLOCKS.register(greenhouseBlock);
    }

    public static void initCommon()
    {
    }

    public static void postInitCommon()
    {
    }

}