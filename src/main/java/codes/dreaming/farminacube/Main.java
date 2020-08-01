package codes.dreaming.farminacube;

import codes.dreaming.farminacube.proxy.CommonProxy;
import codes.dreaming.farminacube.util.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {
    @SidedProxy(clientSide=Reference.ClientOnlyProxy_CLASS, serverSide=Reference.DedicatedServerProxy_CLASS)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }
    public static String prependModID(String name) {return Reference.MOD_ID + ":" + name;}
}