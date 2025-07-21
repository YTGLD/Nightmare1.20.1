package com.ytgld.seeking_immortals;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.logging.LogUtils;
import com.ytgld.seeking_immortals.event.old.AdvancementEvt;
import com.ytgld.seeking_immortals.event.old.NewEvent;
import com.ytgld.seeking_immortals.init.*;
import com.ytgld.seeking_immortals.item.nightmare.ToolTip;
import com.ytgld.seeking_immortals.renderer.MRender;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.function.Function;

@Mod(SeekingImmortalsMod.MODID)
public class SeekingImmortalsMod
{
    public static final String MODID = "seeking_immortals";
    public static final Logger LOGGER = LogUtils.getLogger();


    public SeekingImmortalsMod() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(new NewEvent());
        MinecraftForge.EVENT_BUS.register(new AdvancementEvt());
        Effects.REGISTRY.register(eventBus);
        AttReg.REGISTRY.register(eventBus);
        LootReg.REGISTRY.register(eventBus);
        Items.REGISTRY.register(eventBus);

        Tab.TABS.register(eventBus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.fc);
    }


    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void RegisterClientTooltipComponentFactoriesEvent(RegisterClientTooltipComponentFactoriesEvent event){
            event.register(ToolTip.class, Function.identity());
        }
        @SubscribeEvent
        public static void EntityRenderersEvent(RegisterShadersEvent event) {
            try {


                event.registerShader(new ShaderInstance(event.getResourceProvider(),
                        new ResourceLocation(MODID,"rendertype_gateway"),
                        DefaultVertexFormat.POSITION_TEX_COLOR), MRender::setShaderInstance_gateway);

                event.registerShader(new ShaderInstance(event.getResourceProvider(),
                        new ResourceLocation(MODID,"rendertype_mls"),
                        DefaultVertexFormat.POSITION_TEX_COLOR),MRender::setShaderInstance_mls);

                event.registerShader(new ShaderInstance(event.getResourceProvider(),
                        new ResourceLocation(MODID, "rendertype_ging"),
                        DefaultVertexFormat.POSITION_TEX_COLOR),MRender::setShaderInstance_ging);



                event.registerShader(new ShaderInstance(event.getResourceProvider(),
                        new ResourceLocation(MODID,"trail"),
                        DefaultVertexFormat.POSITION_TEX_COLOR),MRender::setShaderInstance_trail);

                event.registerShader(new ShaderInstance(event.getResourceProvider(),
                        new ResourceLocation(MODID,"eye"),
                        DefaultVertexFormat.POSITION_TEX_COLOR),MRender::setShaderInstance_EYE);

                event.registerShader(new ShaderInstance(event.getResourceProvider(),
                        new ResourceLocation(MODID,"snake"),
                        DefaultVertexFormat.POSITION_TEX_COLOR),MRender::setShader_snake);

                event.registerShader(new ShaderInstance(event.getResourceProvider(),
                        new ResourceLocation(MODID,"p_blood"),
                        DefaultVertexFormat.POSITION_TEX_COLOR),MRender::setShaderInstance_p_blood);

            }catch (IOException exception){
                exception.printStackTrace();
            }
        }
    }
}
