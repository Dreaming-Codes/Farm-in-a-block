package codes.dreaming.farminacube.items;

import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class StartupCommon {
    public static GreenhouseItem greenhouseItem;
    public static void preInitCommon(){
        greenhouseItem = (GreenhouseItem)(new GreenhouseItem().setUnlocalizedName("greenhouse"));
        greenhouseItem.setRegistryName("greenhouse");
        ForgeRegistries.ITEMS.register(greenhouseItem);
    }
    public static void initCommon(){
    }
    public static void postInitCommon(){
    }
}
