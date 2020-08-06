package codes.dreaming.farminacube.proxy;

import net.minecraft.entity.player.EntityPlayer;

public abstract class CommonProxy {

    public void preInit() {
        codes.dreaming.farminacube.blocks.StartupCommon.preInitCommon();
        codes.dreaming.farminacube.items.StartupCommon.preInitCommon();
    }

    public void init() {
        codes.dreaming.farminacube.blocks.StartupCommon.initCommon();
        codes.dreaming.farminacube.items.StartupCommon.initCommon();
    }

    public void postInit() {
        codes.dreaming.farminacube.blocks.StartupCommon.postInitCommon();
        codes.dreaming.farminacube.items.StartupCommon.postInitCommon();
    }

    abstract public boolean playerIsInCreativeMode(EntityPlayer player);

    abstract public boolean isDedicatedServer();
}