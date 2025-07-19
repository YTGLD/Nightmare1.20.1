package com.ytgld.seeking_immortals.init;

import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
@Mod.EventBusSubscriber(modid = SeekingImmortalsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AttReg {
    public static final DeferredRegister<Attribute> REGISTRY = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, SeekingImmortalsMod.MODID);
    public static final RegistryObject<Attribute> alL_attack = REGISTRY.register("allattack",()->{
        return new RangedAttribute("attribute.name.seeking_immortals.allattack", 1, -1024, 1024).setSyncable(true);
    });
    public static final RegistryObject<Attribute> heal = REGISTRY.register("heal",()->{
        return new RangedAttribute("attribute.name.seeking_immortals.heal", 1, -1024, 1024).setSyncable(true);
    });
    public static final RegistryObject<Attribute> cit = REGISTRY.register("cit",()->{
        return new RangedAttribute("attribute.name.seeking_immortals.cit", 1, -1024, 1024).setSyncable(true);
    });
    public static final RegistryObject<Attribute> dig = REGISTRY.register("dig",()->{
        return new RangedAttribute("attribute.name.seeking_immortals.dig", 1, -1024, 1024).setSyncable(true);
    });
    public static final RegistryObject<Attribute> hurt = REGISTRY.register("hurt",()->{
        return new RangedAttribute("attribute.name.seeking_immortals.res", 1, -1024, 1024).setSyncable(true);
    });
    @SubscribeEvent
    public static void EntityAttributeCreationEvent(EntityAttributeModificationEvent event){
        event.add(EntityType.PLAYER , AttReg.alL_attack.get(),1);
        event.add(EntityType.PLAYER , AttReg.heal.get(),1);
        event.add(EntityType.PLAYER , AttReg.cit.get(),1);
        event.add(EntityType.PLAYER , AttReg.dig.get(),1);
        event.add(EntityType.PLAYER , AttReg.hurt.get(),1);

    }
}
