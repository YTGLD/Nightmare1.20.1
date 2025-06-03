package com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend;

import com.ytgld.seeking_immortals.Handler;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class nightmare extends Item implements ICurioItem, INightmare {
    public nightmare() {

        super(new Properties().stacksTo(1)
                .durability(1000000000).rarity(Rarity.UNCOMMON));
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (stack.getTag() == null) {
            stack.getOrCreateTag();
        }
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        if (Handler.hascurio(slotContext.entity(),this)){
            return false;
        }
        return true;
    }

    @NotNull
    @Override
    public ICurio.DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit, ItemStack stack) {
        return ICurio.DropRule.ALWAYS_KEEP;
    }

}

