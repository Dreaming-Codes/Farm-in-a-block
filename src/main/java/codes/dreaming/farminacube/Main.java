package codes.dreaming.farminacube;

//import codes.dreaming.farminacube.network.PacketUpdateGrow;
import codes.dreaming.farminacube.proxy.CommonProxy;
import codes.dreaming.farminacube.util.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {
    @SidedProxy(clientSide=Reference.ClientOnlyProxy_CLASS, serverSide=Reference.DedicatedServerProxy_CLASS)
    public static CommonProxy proxy;
    public static final String MODID = Reference.MOD_ID;

    @Mod.Instance(Main.MODID)
    public static codes.dreaming.farminacube.Main instance;

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