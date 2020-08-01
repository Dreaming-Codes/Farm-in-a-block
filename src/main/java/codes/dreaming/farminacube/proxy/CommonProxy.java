package codes.dreaming.farminacube.proxy;

import net.minecraft.entity.player.EntityPlayer;

public abstract class CommonProxy {

    public void preInit()
    {
        codes.dreaming.farminacube.blocks.StartupCommon.preInitCommon();
    }

    public void init()
    {
        codes.dreaming.farminacube.blocks.StartupCommon.initCommon();
    }

    public void postInit()
    {
        codes.dreaming.farminacube.blocks.StartupCommon.postInitCommon();
    }

    abstract public boolean playerIsInCreativeMode(EntityPlayer player);

    abstract public boolean isDedicatedServer();
}