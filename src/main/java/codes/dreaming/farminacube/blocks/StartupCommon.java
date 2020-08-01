package codes.dreaming.farminacube.blocks;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class StartupCommon
{
    public static GreenhouseBlock greenhouseBlock;
    public static ItemBlock itemGreenhouse;

    public static void preInitCommon()
    {
        greenhouseBlock = (GreenhouseBlock)(new GreenhouseBlock().setUnlocalizedName("Greenhouse"));
        greenhouseBlock.setRegistryName("greenHouse");
        ForgeRegistries.BLOCKS.register(greenhouseBlock);

        itemGreenhouse = new ItemBlock(greenhouseBlock);
        itemGreenhouse.setRegistryName(greenhouseBlock.getRegistryName());
        ForgeRegistries.ITEMS.register(itemGreenhouse);
    }

    public static void initCommon()
    {
    }

    public static void postInitCommon()
    {
    }

}