package com.ytgld.seeking_immortals.init;

import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AttReg {
    public static final DeferredRegister<Attribute> REGISTRY = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, SeekingImmortalsMod.MODID);
    public static final RegistryObject<Attribute> alL_attack = REGISTRY.register("allattack",()->{
        return new RangedAttribute("attribute.name.moonstone.allattack", 1, -1024, 1024).setSyncable(true);
    });
    public static final RegistryObject<Attribute> heal = REGISTRY.register("heal",()->{
        return new RangedAttribute("attribute.name.moonstone.heal", 1, -1024, 1024).setSyncable(true);
    });
    public static final RegistryObject<Attribute> cit = REGISTRY.register("cit",()->{
        return new RangedAttribute("attribute.name.moonstone.cit", 1, -1024, 1024).setSyncable(true);
    });
    public static final RegistryObject<Attribute> dig = REGISTRY.register("dig",()->{
        return new RangedAttribute("attribute.name.moonstone.dig", 1, -1024, 1024).setSyncable(true);
    });
    public static final RegistryObject<Attribute> hurt = REGISTRY.register("hurt",()->{
        return new RangedAttribute("attribute.name.moonstone.res", 1, -1024, 1024).setSyncable(true);
    });
}
