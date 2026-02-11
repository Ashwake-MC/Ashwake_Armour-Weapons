package com.ashwake.armourweapons;

import java.util.List;
import java.util.Set;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@EventBusSubscriber(modid = AshwakeArmourWeapons.MODID, value = Dist.CLIENT)
public final class AshwakeTooltipEvents {
    private static final int WOODEN_AGE_COLOR = 0x8B5A2B;
    private static final Style WOODEN_AGE_STYLE = Style.EMPTY.withColor(TextColor.fromRgb(WOODEN_AGE_COLOR)).withBold(true);
    private static final Style WOODEN_TITLE_STYLE = Style.EMPTY.withColor(TextColor.fromRgb(WOODEN_AGE_COLOR));
    private static final Style WOODEN_DETAIL_STYLE = Style.EMPTY.withColor(TextColor.fromRgb(WOODEN_AGE_COLOR));
    private static final int DIAMOND_AGE_COLOR = 0x7AF2E0;
    private static final Style DIAMOND_AGE_STYLE = Style.EMPTY.withColor(TextColor.fromRgb(DIAMOND_AGE_COLOR)).withBold(true);
    private static final Style DIAMOND_TITLE_STYLE = Style.EMPTY.withColor(TextColor.fromRgb(DIAMOND_AGE_COLOR));
    private static final Style DIAMOND_DETAIL_STYLE = Style.EMPTY.withColor(TextColor.fromRgb(DIAMOND_AGE_COLOR));
    private static final int NETHERITE_AGE_COLOR = 0x7A1E1E;
    private static final Style NETHERITE_AGE_STYLE = Style.EMPTY.withColor(TextColor.fromRgb(NETHERITE_AGE_COLOR)).withBold(true);
    private static final Style NETHERITE_TITLE_STYLE = Style.EMPTY.withColor(TextColor.fromRgb(NETHERITE_AGE_COLOR));
    private static final Style NETHERITE_DETAIL_STYLE = Style.EMPTY.withColor(TextColor.fromRgb(NETHERITE_AGE_COLOR));
    private static final int GOLDEN_AGE_COLOR = 0xD99413;
    private static final Style GOLDEN_AGE_STYLE = Style.EMPTY.withColor(TextColor.fromRgb(GOLDEN_AGE_COLOR)).withBold(true);
    private static final Style GOLDEN_TITLE_STYLE = Style.EMPTY.withColor(TextColor.fromRgb(GOLDEN_AGE_COLOR));
    private static final Style GOLDEN_DETAIL_STYLE = Style.EMPTY.withColor(TextColor.fromRgb(GOLDEN_AGE_COLOR));
    private static final int IRON_AGE_COLOR = 0xACABAA;
    private static final Style IRON_AGE_STYLE = Style.EMPTY.withColor(TextColor.fromRgb(IRON_AGE_COLOR)).withBold(true);
    private static final Style IRON_TITLE_STYLE = Style.EMPTY.withColor(TextColor.fromRgb(IRON_AGE_COLOR));
    private static final Style IRON_DETAIL_STYLE = Style.EMPTY.withColor(TextColor.fromRgb(IRON_AGE_COLOR));
    private static final int STONE_AGE_COLOR = 0x8A8D91;
    private static final Style STONE_AGE_STYLE = Style.EMPTY.withColor(TextColor.fromRgb(STONE_AGE_COLOR)).withBold(true);
    private static final Style STONE_TITLE_STYLE = Style.EMPTY.withColor(TextColor.fromRgb(STONE_AGE_COLOR));
    private static final Style STONE_DETAIL_STYLE = Style.EMPTY.withColor(TextColor.fromRgb(STONE_AGE_COLOR));
    private static final Identifier WOODEN_ELYTRA_ID = Identifier.fromNamespaceAndPath(AshwakeArmourWeapons.MODID, "wooden_elytra");
    private static final Identifier IRON_ELYTRA_ID = Identifier.fromNamespaceAndPath(AshwakeArmourWeapons.MODID, "iron_elytra");
    private static final Identifier GOLDEN_ELYTRA_ID = Identifier.fromNamespaceAndPath(AshwakeArmourWeapons.MODID, "golden_elytra");
    private static final Identifier NETHERITE_ELYTRA_ID = Identifier.fromNamespaceAndPath(AshwakeArmourWeapons.MODID, "netherite_elytra");
    private static final Identifier STONE_SPEAR_ID = Identifier.fromNamespaceAndPath("minecraft", "stone_spear");
    private static final Identifier GOLDEN_SPEAR_ID = Identifier.fromNamespaceAndPath("minecraft", "golden_spear");
    private static final Identifier DIAMOND_SPEAR_ID = Identifier.fromNamespaceAndPath("minecraft", "diamond_spear");
    private static final Identifier NETHERITE_SPEAR_ID = Identifier.fromNamespaceAndPath("minecraft", "netherite_spear");
    private static final Identifier IRON_SPEAR_ID = Identifier.fromNamespaceAndPath("minecraft", "iron_spear");
    private static final Identifier VANILLA_ELYTRA_ID = Identifier.fromNamespaceAndPath("minecraft", "elytra");
    private static final Set<Identifier> WOODEN_AGE_ITEMS = Set.of(
            Identifier.fromNamespaceAndPath("minecraft", "wooden_sword"),
            Identifier.fromNamespaceAndPath("minecraft", "wooden_pickaxe"),
            Identifier.fromNamespaceAndPath("minecraft", "wooden_axe"),
            Identifier.fromNamespaceAndPath("minecraft", "wooden_shovel"),
            Identifier.fromNamespaceAndPath("minecraft", "wooden_hoe"),
            Identifier.fromNamespaceAndPath("minecraft", "wooden_spear"),
            Identifier.fromNamespaceAndPath("minecraft", "leather_helmet"),
            Identifier.fromNamespaceAndPath("minecraft", "leather_chestplate"),
            Identifier.fromNamespaceAndPath("minecraft", "leather_leggings"),
            Identifier.fromNamespaceAndPath("minecraft", "leather_boots"),
            WOODEN_ELYTRA_ID
    );
    private static final Set<Identifier> IRON_AGE_ITEMS = Set.of(
            Identifier.fromNamespaceAndPath("minecraft", "iron_sword"),
            Identifier.fromNamespaceAndPath("minecraft", "iron_pickaxe"),
            Identifier.fromNamespaceAndPath("minecraft", "iron_axe"),
            Identifier.fromNamespaceAndPath("minecraft", "iron_shovel"),
            Identifier.fromNamespaceAndPath("minecraft", "iron_hoe"),
            Identifier.fromNamespaceAndPath("minecraft", "iron_helmet"),
            Identifier.fromNamespaceAndPath("minecraft", "iron_chestplate"),
            Identifier.fromNamespaceAndPath("minecraft", "iron_leggings"),
            Identifier.fromNamespaceAndPath("minecraft", "iron_boots"),
            IRON_SPEAR_ID,
            IRON_ELYTRA_ID
    );
    private static final Set<Identifier> DIAMOND_AGE_ITEMS = Set.of(
            Identifier.fromNamespaceAndPath("minecraft", "diamond_sword"),
            Identifier.fromNamespaceAndPath("minecraft", "diamond_pickaxe"),
            Identifier.fromNamespaceAndPath("minecraft", "diamond_axe"),
            Identifier.fromNamespaceAndPath("minecraft", "diamond_shovel"),
            Identifier.fromNamespaceAndPath("minecraft", "diamond_hoe"),
            Identifier.fromNamespaceAndPath("minecraft", "diamond_helmet"),
            Identifier.fromNamespaceAndPath("minecraft", "diamond_chestplate"),
            Identifier.fromNamespaceAndPath("minecraft", "diamond_leggings"),
            Identifier.fromNamespaceAndPath("minecraft", "diamond_boots"),
            DIAMOND_SPEAR_ID,
            VANILLA_ELYTRA_ID
    );
    private static final Set<Identifier> NETHERITE_AGE_ITEMS = Set.of(
            Identifier.fromNamespaceAndPath("minecraft", "netherite_sword"),
            Identifier.fromNamespaceAndPath("minecraft", "netherite_pickaxe"),
            Identifier.fromNamespaceAndPath("minecraft", "netherite_axe"),
            Identifier.fromNamespaceAndPath("minecraft", "netherite_shovel"),
            Identifier.fromNamespaceAndPath("minecraft", "netherite_hoe"),
            Identifier.fromNamespaceAndPath("minecraft", "netherite_helmet"),
            Identifier.fromNamespaceAndPath("minecraft", "netherite_chestplate"),
            Identifier.fromNamespaceAndPath("minecraft", "netherite_leggings"),
            Identifier.fromNamespaceAndPath("minecraft", "netherite_boots"),
            NETHERITE_SPEAR_ID,
            NETHERITE_ELYTRA_ID
    );
    private static final Set<Identifier> GOLDEN_AGE_ITEMS = Set.of(
            Identifier.fromNamespaceAndPath("minecraft", "golden_sword"),
            Identifier.fromNamespaceAndPath("minecraft", "golden_pickaxe"),
            Identifier.fromNamespaceAndPath("minecraft", "golden_axe"),
            Identifier.fromNamespaceAndPath("minecraft", "golden_shovel"),
            Identifier.fromNamespaceAndPath("minecraft", "golden_hoe"),
            Identifier.fromNamespaceAndPath("minecraft", "golden_helmet"),
            Identifier.fromNamespaceAndPath("minecraft", "golden_chestplate"),
            Identifier.fromNamespaceAndPath("minecraft", "golden_leggings"),
            Identifier.fromNamespaceAndPath("minecraft", "golden_boots"),
            GOLDEN_SPEAR_ID,
            GOLDEN_ELYTRA_ID
    );
    private static final Set<Identifier> STONE_AGE_ITEMS = Set.of(
            Identifier.fromNamespaceAndPath("minecraft", "stone_sword"),
            Identifier.fromNamespaceAndPath("minecraft", "stone_pickaxe"),
            Identifier.fromNamespaceAndPath("minecraft", "stone_axe"),
            Identifier.fromNamespaceAndPath("minecraft", "stone_shovel"),
            Identifier.fromNamespaceAndPath("minecraft", "stone_hoe"),
            STONE_SPEAR_ID
    );

    private AshwakeTooltipEvents() {
    }

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        if (event.getItemStack().isEmpty()) {
            return;
        }

        Identifier itemId = BuiltInRegistries.ITEM.getKey(event.getItemStack().getItem());
        boolean isWoodenAge = WOODEN_AGE_ITEMS.contains(itemId);
        boolean isDiamondAge = DIAMOND_AGE_ITEMS.contains(itemId);
        boolean isNetheriteAge = NETHERITE_AGE_ITEMS.contains(itemId);
        boolean isGoldenAge = GOLDEN_AGE_ITEMS.contains(itemId);
        boolean isIronAge = IRON_AGE_ITEMS.contains(itemId);
        boolean isStoneAge = STONE_AGE_ITEMS.contains(itemId);
        if (!isWoodenAge && !isDiamondAge && !isNetheriteAge && !isGoldenAge && !isIronAge && !isStoneAge) {
            return;
        }

        Style titleStyle = isNetheriteAge
                ? NETHERITE_TITLE_STYLE
                : (isDiamondAge
                        ? DIAMOND_TITLE_STYLE
                        : (isGoldenAge
                                ? GOLDEN_TITLE_STYLE
                                : (isIronAge
                                        ? IRON_TITLE_STYLE
                                        : (isStoneAge ? STONE_TITLE_STYLE : WOODEN_TITLE_STYLE))));
        Style detailStyle = isNetheriteAge
                ? NETHERITE_DETAIL_STYLE
                : (isDiamondAge
                        ? DIAMOND_DETAIL_STYLE
                        : (isGoldenAge
                                ? GOLDEN_DETAIL_STYLE
                                : (isIronAge
                                        ? IRON_DETAIL_STYLE
                                        : (isStoneAge ? STONE_DETAIL_STYLE : WOODEN_DETAIL_STYLE))));
        Style ageStyle = isNetheriteAge
                ? NETHERITE_AGE_STYLE
                : (isDiamondAge
                        ? DIAMOND_AGE_STYLE
                        : (isGoldenAge
                                ? GOLDEN_AGE_STYLE
                                : (isIronAge
                                        ? IRON_AGE_STYLE
                                        : (isStoneAge ? STONE_AGE_STYLE : WOODEN_AGE_STYLE))));
        String ageKey = isNetheriteAge
                ? "tooltip.ashwake_armour_weapons.netherite_age"
                : (isDiamondAge
                        ? "tooltip.ashwake_armour_weapons.diamond_age"
                        : (isGoldenAge
                                ? "tooltip.ashwake_armour_weapons.golden_age"
                                : (isIronAge
                                        ? "tooltip.ashwake_armour_weapons.iron_age"
                                        : (isStoneAge ? "tooltip.ashwake_armour_weapons.stone_age" : "tooltip.ashwake_armour_weapons.wooden_age"))));

        List<Component> tooltip = event.getToolTip();
        if (!tooltip.isEmpty()) {
            Component title = tooltip.get(0);
            tooltip.set(0, title.copy().withStyle(titleStyle));
        }

        Component line = Component.translatable(ageKey).withStyle(ageStyle);
        int insertIndex = tooltip.isEmpty() ? 0 : 1;
        tooltip.add(insertIndex, line);

        int detailIndex = Math.min(tooltip.size(), insertIndex + 1);
        if (itemId.equals(WOODEN_ELYTRA_ID)) {
            tooltip.add(detailIndex, Component.translatable("tooltip.ashwake_armour_weapons.wooden_elytra.glide").withStyle(detailStyle));
            tooltip.add(detailIndex + 1, Component.translatable("tooltip.ashwake_armour_weapons.wooden_elytra.double_jump").withStyle(detailStyle));
        } else if (itemId.equals(IRON_ELYTRA_ID)) {
            tooltip.add(detailIndex, Component.translatable("tooltip.ashwake_armour_weapons.iron_elytra.glide").withStyle(detailStyle));
            tooltip.add(detailIndex + 1, Component.translatable("tooltip.ashwake_armour_weapons.iron_elytra.double_jump").withStyle(detailStyle));
        } else if (itemId.equals(GOLDEN_ELYTRA_ID)) {
            tooltip.add(detailIndex, Component.translatable("tooltip.ashwake_armour_weapons.golden_elytra.glide").withStyle(detailStyle));
            tooltip.add(detailIndex + 1, Component.translatable("tooltip.ashwake_armour_weapons.golden_elytra.double_jump").withStyle(detailStyle));
        } else if (itemId.equals(NETHERITE_ELYTRA_ID)) {
            tooltip.add(detailIndex, Component.translatable("tooltip.ashwake_armour_weapons.netherite_elytra.glide").withStyle(detailStyle));
            tooltip.add(detailIndex + 1, Component.translatable("tooltip.ashwake_armour_weapons.netherite_elytra.double_jump").withStyle(detailStyle));
        } else if (itemId.equals(STONE_SPEAR_ID)) {
            tooltip.add(detailIndex, Component.translatable("tooltip.ashwake_armour_weapons.stone_spear.weight").withStyle(detailStyle));
        }
    }
}
