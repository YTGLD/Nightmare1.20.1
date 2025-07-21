package com.ytgld.seeking_immortals.item.nightmare.super_nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.seeking_immortals.Config;
import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import com.ytgld.seeking_immortals.init.AttReg;
import com.ytgld.seeking_immortals.init.DataReg;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.AllTip;
import com.ytgld.seeking_immortals.item.nightmare.ToolTip;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.*;

public class nightmare_base_stone extends nightmare implements SuperNightmare, AllTip {

    public static void LivingHurtEvent(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.nightmare_base_stone.get())) {
                if (Handler.hascurio(player,Items.candle.get())){
                    return;
                }
                if (player.getHealth() >= player.getMaxHealth()) {

                    double d = Config.SERVER.nightmare_base_stone.get();
                    event.setAmount((float) (event.getAmount() * d+1));

                    if (!player.getCooldowns().isOnCooldown(Items.nightmare_base_stone.get())) {
                        if (event.getAmount() > player.getHealth()) {
                            event.setAmount(0);
                            player.setHealth(1);
                            player.getCooldowns().addCooldown(Items.nightmare_base_stone.get(),200);
                        }
                    }
                }
            }
        }
    }
    public static final String  uDead = "undead";
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (stack.getTag()==null){
            stack.getOrCreateTag();
        }
        if (slotContext.entity() instanceof Player player){

            float lv = player.getHealth() / player.getMaxHealth();

            lv *= 100;
            int now = (int) (100 -(lv));
            if (stack.getTag()==null){
                stack.getOrCreateTag();
            }
            if (stack.getTag()!=null){
                stack.getTag().putInt(uDead,now);
            }

            player.getAttributes().addTransientAttributeModifiers(ad(stack));
        }
    }
    public Multimap<Attribute, AttributeModifier> ad(ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

        if (stack.getTag()!=null) {
            int lvl = stack.getTag().getInt(uDead);
            float heal = 0.85f / 100f * lvl;
            float speed = 0.8f / 100f * lvl;
            float damage = 0.75f / 100f * lvl;
            float attSpeed = 0.5f / 100f * lvl;
            float armor = 0.35f / 100f * lvl;



            modifiers.put(AttReg.heal.get(),new AttributeModifier(UUID.fromString("8b8434b7-4246-3db8-89e4-a51a52bc8fc9"),"asd",
                    heal, AttributeModifier.Operation.MULTIPLY_BASE));
            modifiers.put(Attributes.MOVEMENT_SPEED,new AttributeModifier(UUID.fromString("8b8434b7-4246-3db8-89e4-a51a52bc8fc9"),"iosnadjas",
                    speed, AttributeModifier.Operation.MULTIPLY_BASE));
            modifiers.put(Attributes.ATTACK_DAMAGE,new AttributeModifier(UUID.fromString("8b8434b7-4246-3db8-89e4-a51a52bc8fc9"),"iosnadjas",
                    damage, AttributeModifier.Operation.MULTIPLY_BASE));
            modifiers.put(Attributes.ATTACK_SPEED,new AttributeModifier(UUID.fromString("8b8434b7-4246-3db8-89e4-a51a52bc8fc9"),"iosnadjas",
                    attSpeed, AttributeModifier.Operation.MULTIPLY_BASE));
            modifiers.put(Attributes.ARMOR,new AttributeModifier(UUID.fromString("8b8434b7-4246-3db8-89e4-a51a52bc8fc9"),"iosnadjas",
                    armor, AttributeModifier.Operation.MULTIPLY_BASE));



        }
        return modifiers;
    }
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
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> linkedHashMultimap = com.google.common.collect.LinkedHashMultimap.create();
        CuriosApi
                .addSlotModifier(linkedHashMultimap, "nightmare",
                        uuid, 3, AttributeModifier.Operation.ADDITION);

        return linkedHashMultimap;
    }
    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.candle.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone.tool.string.1").withStyle(ChatFormatting.RED));

        pTooltipComponents.add(Component.translatable("item.seeking_immortals.nightmare_base_stone_virus").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.seeking_immortals.nightmare_base_stone_meet").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.seeking_immortals.nightmare_base_stone_brain").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.seeking_immortals.end_bone").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));

        pTooltipComponents.add(Component.translatable("item.nightmareeye.tool.string.2").withStyle(ChatFormatting.DARK_RED));


    }
    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return Optional.of(new ToolTip(this,stack));
    }

    @Override
    public Map<Integer, String> tooltip() {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"这是试炼  磨练你的意志");
        map.put(2,"生命值越低 各方面属性越高");
        return map;
    }

    @Override
    public Map<Integer, String> element(ItemStack stack) {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"这是试炼  磨练你的意志");
        map.put(2,"生命值越低 各方面属性越高");
        return map;
    }
}

