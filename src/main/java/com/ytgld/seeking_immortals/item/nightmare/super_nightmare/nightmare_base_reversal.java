package com.ytgld.seeking_immortals.item.nightmare.super_nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.event.old.AdvancementEvt;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class nightmare_base_reversal extends nightmare implements SuperNightmare {


    public static final String att = "Attrib";


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


    public static void LivingDeathEvent(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.nightmare_base_reversal.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base_reversal.get())) {
                                if (stack.getTag() != null) {
                                    stack.getTag().putInt(att, 96);
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide) {
            if (slotContext.entity().tickCount>=20) {
                slotContext.entity().getAttributes().addTransientAttributeModifiers(geta(stack));
            }
        }
        if (slotContext.entity().hasEffect(MobEffects.POISON)) {

            if (slotContext.entity().getHealth() <= 1) {
                if (stack.getTag() != null) {
                    if (!stack.getTag().getBoolean(AdvancementEvt.nightmare_base_reversal_orb)) {
                        if (slotContext.entity() instanceof Player player) {
                            player.addItem(new ItemStack(Items.nightmare_base_reversal_orb.get()));
                        }
                        stack.getTag().putBoolean(AdvancementEvt.nightmare_base_reversal_orb, true);
                    }
                }
                slotContext.entity().kill();
            }
        }
        if (stack.getTag() != null) {
            if (!Handler.hascurio(slotContext.entity(), Items.nightmare_base_reversal_card.get())) {
                if (stack.getTag().getInt(att) >= 4) {
                    if (slotContext.entity() instanceof Player player && !player.getCooldowns().isOnCooldown(stack.getItem())) {
                        stack.getTag().putInt(att, stack.getTag().getInt(att) - 4);
                        player.getCooldowns().addCooldown(stack.getItem(), 20);
                    }
                }
            } else {
                if (stack.getTag().getInt(att) >= -46) {
                    if (slotContext.entity() instanceof Player player && !player.getCooldowns().isOnCooldown(stack.getItem())) {
                        stack.getTag().putInt(att, stack.getTag().getInt(att) - 2);
                        player.getCooldowns().addCooldown(stack.getItem(), 20);
                    }
                }
            }
        } else {
            stack.getOrCreateTag();
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(geta(stack));
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> linkedHashMultimap = com.google.common.collect.LinkedHashMultimap.create();
        CuriosApi
                .addSlotModifier(linkedHashMultimap, "nightmare",
                        uuid, 3, AttributeModifier.Operation.ADDITION);

        return linkedHashMultimap;
    }
    public Multimap<Attribute, AttributeModifier> geta(ItemStack stack) {
        Multimap<Attribute, AttributeModifier> get = HashMultimap.create();

        if (stack.getTag() != null) {
            double as = -stack.getTag().getInt(att);
            as /= 100;
            for (Holder<Attribute> attribute : BuiltInRegistries.ATTRIBUTE.asHolderIdMap()) {
                if (attribute != (Attributes.MAX_HEALTH)) {
                    get.put(attribute.get(), new AttributeModifier(UUID.fromString("59c51eea-e27e-410d-aaef-286f2ce555a5"),"a", as, AttributeModifier.Operation.MULTIPLY_BASE));
                }
            }
        }
        return get;
    }

    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_reversal.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_reversal.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_reversal.tool.string.3").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye.tool.string.1").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.translatable("item.seeking_immortals.nightmare_base_reversal_orb").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.seeking_immortals.nightmare_base_reversal_card").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.seeking_immortals.nightmare_base_reversal_mysterious").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.seeking_immortals.candle").withStyle(ChatFormatting.DARK_RED));

        pTooltipComponents.add(Component.literal(""));

        pTooltipComponents.add(Component.translatable("item.nightmareeye.tool.string.2").withStyle(ChatFormatting.DARK_RED));

    }
}

