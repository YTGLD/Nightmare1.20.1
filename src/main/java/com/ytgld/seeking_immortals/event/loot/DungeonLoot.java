package com.ytgld.seeking_immortals.event.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ytgld.seeking_immortals.event.old.AdvancementEvt;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

public class DungeonLoot extends LootModifier {
    public static final Codec<DungeonLoot> CODEC= RecordCodecBuilder.create((inst) -> codecStart(inst)
            .apply(inst, DungeonLoot::new));
    protected DungeonLoot(LootItemCondition[] conditionsIn) {
        super(conditionsIn);

    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }


    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        ResourceLocation s = context.getQueriedLootTableId();
        String idSting = String.valueOf(s);
        Entity entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);

        if (idSting.contains("chests/")) {
            if (idSting.contains("ancient")) {
                AdvancementEvt.addLoot(generatedLoot, entity, 5);
                AdvancementEvt.nightmare_base_reversal_mysteriousLOOT(generatedLoot, entity);

            }
        }


        if (idSting.contains("chests/")) {
            if (idSting.contains("dungeon")) {
                AdvancementEvt.nightmare_base_start_pod(generatedLoot, entity);
            }
            if (idSting.contains("mansion")) {
                AdvancementEvt.tricky_puppets(generatedLoot, entity);
            }
        }
        return generatedLoot;
    }
}


