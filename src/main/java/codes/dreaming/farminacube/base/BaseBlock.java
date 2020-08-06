package codes.dreaming.farminacube.base;

import codes.dreaming.farminacube.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BaseBlock extends Block {
    public BaseBlock(Material material) {
        super(material);
    }

    @Override
    public Block setUnlocalizedName(String name) {
        if (!name.startsWith(Reference.MOD_ID + ".")) {
            name = Reference.MOD_ID + "." + name;
        }
        return super.setUnlocalizedName(name);
    }
}