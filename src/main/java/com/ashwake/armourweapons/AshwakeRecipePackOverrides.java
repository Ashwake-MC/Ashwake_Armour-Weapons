package com.ashwake.armourweapons;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddPackFindersEvent;

@EventBusSubscriber(modid = AshwakeArmourWeapons.MODID)
public final class AshwakeRecipePackOverrides {
    private AshwakeRecipePackOverrides() {
    }

    @SubscribeEvent
    public static void addPackFinders(AddPackFindersEvent event) {
        if (event.getPackType() != PackType.SERVER_DATA) {
            return;
        }

        // Load recipe overrides as a built-in data pack with top priority.
        event.addPackFinders(
                Identifier.fromNamespaceAndPath(AshwakeArmourWeapons.MODID, "ashwake_recipe_overrides"),
                PackType.SERVER_DATA,
                Component.literal("Ashwake Recipe Overrides"),
                PackSource.BUILT_IN,
                true,
                Pack.Position.TOP
        );
    }
}
