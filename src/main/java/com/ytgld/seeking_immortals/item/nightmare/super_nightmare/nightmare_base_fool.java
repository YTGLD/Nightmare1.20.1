package com.ytgld.seeking_immortals.item.nightmare.super_nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.seeking_immortals.Config;
import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class nightmare_base_fool extends nightmare implements SuperNightmare {
    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (CuriosApi.getCuriosInventory(player).resolve().isPresent()
                    && CuriosApi.getCuriosInventory(player).resolve().get().isEquipped(Items.immortal.get())){
                return true;
            }
            if (player.isCreative()){
                return true;
            }
        }
        return false;
    }


    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        slotContext.entity().getAttributes().addTransientAttributeModifiers(gets(slotContext));
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(gets(slotContext));
    }

    public Multimap<Attribute, AttributeModifier> gets(SlotContext slotContext) {
        Multimap<Attribute, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        LivingEntity living = slotContext.entity();

        {
            List<Integer> integersATTACK_DAMAGE = new ArrayList<>();
            CuriosApi.getCuriosInventory(living).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            integersATTACK_DAMAGE.add(1);
                        }
                    }
                }
            });
            float dam = 0;
            for (int ignored : integersATTACK_DAMAGE) {
                dam++;
            }
            int j = 5;
            if (Handler.hascurio(living, Items.nightmare_base_fool_soul.get())) {
                j += 9;
            }
            dam -= j;
            if (dam < 0) {
                dam = 0;
            }
            dam /= 100f;
            dam *= 2;

            dam = -dam;
            double s = Config.SERVER.nightmare_base_fool.get();
            if (dam <= -s) {
                dam = (float) -s;
            }
            linkedHashMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("5034b672-e4ca-4b58-9e81-78b01cc5e12d"),"as", dam, AttributeModifier.Operation.MULTIPLY_BASE));
        }
        {
            List<Integer> integersHealth = new ArrayList<>();
            CuriosApi.getCuriosInventory(living).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            integersHealth.add(1);
                        }
                    }
                }
            });
            float health = 0;
            for (int ignored : integersHealth) {
                health++;
            }
            int j = 2;
            if (Handler.hascurio(living, Items.nightmare_base_fool_soul.get())) {
                j += 7;
            }
            health -= j;
            if (health < 0) {
                health = 0;
            }
            health /= 100f;
            health *= 1;
            health = -health;
            double s = Config.SERVER.nightmare_base_fool.get();

            if (health <= -s) {
                health = (float) -s;
            }
            linkedHashMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("eeb039c5-599c-45c4-b03c-ccbf3480753e"),"a", health, AttributeModifier.Operation.MULTIPLY_BASE));
        }


        return linkedHashMultimap;
    }


    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_fool.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_fool.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_fool.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye.tool.string.1").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.translatable("item.seeking_immortals.nightmare_base_fool_betray").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.seeking_immortals.nightmare_base_fool_bone").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.seeking_immortals.nightmare_base_fool_soul").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.seeking_immortals.apple").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));

        pTooltipComponents.add(Component.translatable("item.nightmareeye.tool.string.2").withStyle(ChatFormatting.DARK_RED));

    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> linkedHashMultimap = com.google.common.collect.LinkedHashMultimap.create();
        CuriosApi
                .addSlotModifier(linkedHashMultimap, "nightmare",
                        uuid, 3, AttributeModifier.Operation.ADDITION);

        return linkedHashMultimap;
    }
}

