package codes.dreaming.farminacube.tile;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.lwjgl.opengl.GL11;

public class TileGreenhouseRender extends TileEntitySpecialRenderer<TileGreenhouse> {
    @Override
    public void render(TileGreenhouse te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        ItemStackHandler inventory = (ItemStackHandler)te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
        ItemStack stack = inventory.getStackInSlot(0);
        Block BlockToRender = BlockContainer.getBlockFromItem(stack.getItem());
        BlockStateContainer BlockStateToRender = BlockToRender.getBlockState();
        System.out.println("CIAO");
        System.out.println(BlockStateToRender.getProperties());
        if (!stack.isEmpty()) {
            GlStateManager.enableRescaleNormal();
            GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1f);
            GlStateManager.enableBlend();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
            GlStateManager.pushMatrix();
            GlStateManager.translate(x + 0.5, y + 1.25 + 2, z + 0.5);
            GlStateManager.rotate((te.getWorld().getTotalWorldTime() + partialTicks) * 4, 0, 1, 0);

            IBakedModel model = Minecraft.getMinecraft().getBlockRendererDispatcher().getModelForState(BlockStateToRender.getBaseState());
            //model = ForgeHooksClient.handleCameraTransforms(model, ItemCameraTransforms.TransformType.GROUND, false);

            Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            //Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);
            Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlock(BlockStateToRender.getBaseState(), new BlockPos(x, y, z), BlockToRender.);

            GlStateManager.popMatrix();
            GlStateManager.disableRescaleNormal();
            GlStateManager.disableBlend();
        }
    }
}