package com.nekokittygames.thaumictinkerer.client.rendering.tileentities;

import com.nekokittygames.thaumictinkerer.common.tileentity.TileEntityFunnel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.client.lib.RenderCubes;
import thaumcraft.client.renderers.tile.TileJarRenderer;
import thaumcraft.common.blocks.essentia.BlockJarItem;

import java.awt.*;

public class TileEntityFunnelRenderer extends TileEntitySpecialRenderer<TileEntityFunnel> {



    TileJarRenderer jarRenderer=new TileJarRenderer();
    ResourceLocation  TEX_LABEL= new ResourceLocation("thaumcraft", "textures/models/label.png");
    ResourceLocation  TEX_BRINE= new ResourceLocation("thaumcraft", "textures/models/jarbrine.png");
    @Override
    public void render(TileEntityFunnel te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        super.render(te, x, y, z, partialTicks, destroyStage, alpha);
        if(te.getInventory().getStackInSlot(0)!= ItemStack.EMPTY && ((BlockJarItem) te.getInventory().getStackInSlot(0).getItem()).getAspects(te.getInventory().getStackInSlot(0))!=null && ((BlockJarItem) te.getInventory().getStackInSlot(0).getItem()).getAspects(te.getInventory().getStackInSlot(0)).size() > 0)
        {
            GL11.glPushMatrix();
            GL11.glDisable(2884);
            GL11.glTranslatef((float)x + 0.5F, (float)y + 0.01F, (float)z + 0.5F);
            GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(2896);
            if(((BlockJarItem)te.getInventory().getStackInSlot(0).getItem()).getAspects(te.getInventory().getStackInSlot(0)).size()>0) {
                Aspect aspect = ((BlockJarItem) te.getInventory().getStackInSlot(0).getItem()).getAspects(te.getInventory().getStackInSlot(0)).getAspects()[0];
                int amount = ((BlockJarItem) te.getInventory().getStackInSlot(0).getItem()).getAspects(te.getInventory().getStackInSlot(0)).getAmount(aspect);
                if (amount > 0)
                    renderTCJar((float) x, (float) y, (float) z, amount, aspect);
            }
            GL11.glEnable(2896);
            GL11.glEnable(2884);
            GL11.glPopMatrix();
        }

    }

    private void renderTCJar(float x, float y, float z, int amount, Aspect aspect) {
        GL11.glPushMatrix();
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        //GL11.glTranslatef(x + 0.5F, y + 0.1F, 0);
        World world = getWorld();
        RenderCubes renderBlocks = new RenderCubes();
        GL11.glDisable(2896);
        float level = (float)amount / 250.0F * 0.625F;
        Tessellator t = Tessellator.getInstance();
        renderBlocks.setRenderBounds(0.25D, 0.0625D, 0.25D, 0.75D, 0.1875D + (double)level, 0.75D);
        t.getBuffer().begin(7, DefaultVertexFormats.POSITION_TEX_LMAP_COLOR);
        Color co = new Color(0);
        if (aspect != null) {
            co = new Color(aspect.getColor());
        }

        TextureAtlasSprite icon = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("thaumcraft:blocks/animatedglow");
        this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        renderBlocks.renderFaceYNeg(BlocksTC.jarNormal, -0.5D, 0.0D, -0.5D, icon, (float)co.getRed() / 255.0F, (float)co.getGreen() / 255.0F, (float)co.getBlue() / 255.0F, 200);
        renderBlocks.renderFaceYPos(BlocksTC.jarNormal, -0.5D, 0.0D, -0.5D, icon, (float)co.getRed() / 255.0F, (float)co.getGreen() / 255.0F, (float)co.getBlue() / 255.0F, 200);
        renderBlocks.renderFaceZNeg(BlocksTC.jarNormal, -0.5D, 0.0D, -0.5D, icon, (float)co.getRed() / 255.0F, (float)co.getGreen() / 255.0F, (float)co.getBlue() / 255.0F, 200);
        renderBlocks.renderFaceZPos(BlocksTC.jarNormal, -0.5D, 0.0D, -0.5D, icon, (float)co.getRed() / 255.0F, (float)co.getGreen() / 255.0F, (float)co.getBlue() / 255.0F, 200);
        renderBlocks.renderFaceXNeg(BlocksTC.jarNormal, -0.5D, 0.0D, -0.5D, icon, (float)co.getRed() / 255.0F, (float)co.getGreen() / 255.0F, (float)co.getBlue() / 255.0F, 200);
        renderBlocks.renderFaceXPos(BlocksTC.jarNormal, -0.5D, 0.0D, -0.5D, icon, (float)co.getRed() / 255.0F, (float)co.getGreen() / 255.0F, (float)co.getBlue() / 255.0F, 200);
        t.draw();
        GL11.glEnable(2896);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }




}