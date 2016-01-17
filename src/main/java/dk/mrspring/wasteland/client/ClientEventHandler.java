package dk.mrspring.wasteland.client;

import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.List;

/**
 * Created on 16-01-2016 for TheWastelandMod2.
 */
public class ClientEventHandler
{
    int width = 20, height = 20;
    int[] biomes = new int[width * height];
    List<BiomeGenBase> foundBiomes = Lists.newArrayList();

    @SubscribeEvent
    public void onHudRender(RenderGameOverlayEvent.Post event)
    {
        if (biomes != null && foundBiomes.size() > 0)
        {
            float scale = 2F;
            for (int y = 0; y < height; y++)
                for (int x = 0; x < width; x++)
                {
                    BiomeGenBase biome = BiomeGenBase.getBiome(biomes[x + y * width]);
                    float xR = 5 + (x * scale), yR = 5 + (y * scale);
                    drawRect(xR, yR, xR + scale, yR + scale, biome.color);
                }
        }
    }

    public static void drawRect(float left, float top, float right, float bottom, int color)
    {
        if (left < right)
        {
            float i = left;
            left = right;
            right = i;
        }

        if (top < bottom)
        {
            float j = top;
            top = bottom;
            bottom = j;
        }

        float f = (float) (color >> 16 & 255) / 255.0F;
        float f1 = (float) (color >> 8 & 255) / 255.0F;
        float f2 = (float) (color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f, f1, f2, 1F);
        worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
        worldrenderer.func_181662_b((double) left, (double) bottom, 0.0D).func_181675_d();
        worldrenderer.func_181662_b((double) right, (double) bottom, 0.0D).func_181675_d();
        worldrenderer.func_181662_b((double) right, (double) top, 0.0D).func_181675_d();
        worldrenderer.func_181662_b((double) left, (double) top, 0.0D).func_181675_d();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event)
    {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer != null && mc.theWorld != null)
        {
            biomes = new int[width * height];
            foundBiomes.clear();
            World world = mc.theWorld;
            BlockPos start = mc.thePlayer.getPosition().add(-width / 2, 0, -height / 2);
            for (int y = 0; y < height; y++)
                for (int x = 0; x < width; x++)
                {
                    BlockPos pos = start.add(x, 0, y);
                    BiomeGenBase base = world.getBiomeGenForCoords(pos);
                    if (!foundBiomes.contains(base)) foundBiomes.add(base);
                    biomes[x + y * width] = base.biomeID;
                }
        }
    }
}
