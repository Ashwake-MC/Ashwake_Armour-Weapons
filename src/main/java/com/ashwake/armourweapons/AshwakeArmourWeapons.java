package com.ashwake.armourweapons;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Unit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(AshwakeArmourWeapons.MODID)
public class AshwakeArmourWeapons {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "ashwake_armour_weapons";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the mod namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    // Create a Deferred Register to hold Items which will all be registered under the mod namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the mod namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final ResourceKey<EquipmentAsset> WOODEN_ELYTRA_ASSET = ResourceKey.create(
            EquipmentAssets.ROOT_ID,
            Identifier.fromNamespaceAndPath(MODID, "wooden_elytra")
    );
    public static final ResourceKey<EquipmentAsset> IRON_ELYTRA_ASSET = ResourceKey.create(
            EquipmentAssets.ROOT_ID,
            Identifier.fromNamespaceAndPath(MODID, "iron_elytra")
    );
    public static final ResourceKey<EquipmentAsset> GOLDEN_ELYTRA_ASSET = ResourceKey.create(
            EquipmentAssets.ROOT_ID,
            Identifier.fromNamespaceAndPath(MODID, "golden_elytra")
    );
    public static final ResourceKey<EquipmentAsset> NETHERITE_ELYTRA_ASSET = ResourceKey.create(
            EquipmentAssets.ROOT_ID,
            Identifier.fromNamespaceAndPath(MODID, "netherite_elytra")
    );
    private static final Identifier WOODEN_ELYTRA_JUMP_ID = Identifier.fromNamespaceAndPath(MODID, "wooden_elytra_jump");
    private static final Identifier IRON_ELYTRA_JUMP_ID = Identifier.fromNamespaceAndPath(MODID, "iron_elytra_jump");
    private static final Identifier GOLDEN_ELYTRA_JUMP_ID = Identifier.fromNamespaceAndPath(MODID, "golden_elytra_jump");
    private static final Identifier NETHERITE_ELYTRA_JUMP_ID = Identifier.fromNamespaceAndPath(MODID, "netherite_elytra_jump");
    private static final ItemAttributeModifiers WOODEN_ELYTRA_ATTRIBUTES = ItemAttributeModifiers.builder()
            .add(
                    Attributes.JUMP_STRENGTH,
                    new AttributeModifier(WOODEN_ELYTRA_JUMP_ID, 0.25, AttributeModifier.Operation.ADD_VALUE),
                    EquipmentSlotGroup.CHEST
            )
            .build();
    private static final ItemAttributeModifiers IRON_ELYTRA_ATTRIBUTES = ItemAttributeModifiers.builder()
            .add(
                    Attributes.JUMP_STRENGTH,
                    new AttributeModifier(IRON_ELYTRA_JUMP_ID, 0.32, AttributeModifier.Operation.ADD_VALUE),
                    EquipmentSlotGroup.CHEST
            )
            .build();
    private static final ItemAttributeModifiers GOLDEN_ELYTRA_ATTRIBUTES = ItemAttributeModifiers.builder()
            .add(
                    Attributes.JUMP_STRENGTH,
                    new AttributeModifier(GOLDEN_ELYTRA_JUMP_ID, 0.38, AttributeModifier.Operation.ADD_VALUE),
                    EquipmentSlotGroup.CHEST
            )
            .build();
    private static final ItemAttributeModifiers NETHERITE_ELYTRA_ATTRIBUTES = ItemAttributeModifiers.builder()
            .add(
                    Attributes.JUMP_STRENGTH,
                    new AttributeModifier(NETHERITE_ELYTRA_JUMP_ID, 0.45, AttributeModifier.Operation.ADD_VALUE),
                    EquipmentSlotGroup.CHEST
            )
            .build();

    // Creates a new Block with the id "ashwake_armour_weapons:ashwake_block", combining the namespace and path
    public static final DeferredBlock<Block> ASHWAKE_BLOCK = BLOCKS.registerSimpleBlock("ashwake_block", p -> p.mapColor(MapColor.STONE));
    // Creates a new BlockItem with the id "ashwake_armour_weapons:ashwake_block", combining the namespace and path
    public static final DeferredItem<BlockItem> ASHWAKE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("ashwake_block", ASHWAKE_BLOCK);

    public static final DeferredItem<WoodenElytraItem> WOODEN_ELYTRA = ITEMS.registerItem("wooden_elytra",
            WoodenElytraItem::new,
            () -> new Item.Properties()
                    .durability(64)
                    .attributes(WOODEN_ELYTRA_ATTRIBUTES)
                    .component(
                            DataComponents.EQUIPPABLE,
                            Equippable.builder(EquipmentSlot.CHEST)
                                    .setEquipSound(SoundEvents.ARMOR_EQUIP_LEATHER)
                                    .setAsset(WOODEN_ELYTRA_ASSET)
                                    .setDamageOnHurt(true)
                                    .build()
                    )
                    .component(DataComponents.GLIDER, Unit.INSTANCE)
                    .repairable(Items.OAK_PLANKS)
    );
    public static final DeferredItem<WoodenElytraItem> IRON_ELYTRA = ITEMS.registerItem("iron_elytra",
            WoodenElytraItem::new,
            () -> new Item.Properties()
                    .durability(96)
                    .attributes(IRON_ELYTRA_ATTRIBUTES)
                    .component(
                            DataComponents.EQUIPPABLE,
                            Equippable.builder(EquipmentSlot.CHEST)
                                    .setEquipSound(SoundEvents.ARMOR_EQUIP_IRON)
                                    .setAsset(IRON_ELYTRA_ASSET)
                                    .setDamageOnHurt(true)
                                    .build()
                    )
                    .component(DataComponents.GLIDER, Unit.INSTANCE)
                    .repairable(Items.IRON_INGOT)
    );
    public static final DeferredItem<WoodenElytraItem> GOLDEN_ELYTRA = ITEMS.registerItem("golden_elytra",
            WoodenElytraItem::new,
            () -> new Item.Properties()
                    .durability(128)
                    .attributes(GOLDEN_ELYTRA_ATTRIBUTES)
                    .component(
                            DataComponents.EQUIPPABLE,
                            Equippable.builder(EquipmentSlot.CHEST)
                                    .setEquipSound(SoundEvents.ARMOR_EQUIP_GOLD)
                                    .setAsset(GOLDEN_ELYTRA_ASSET)
                                    .setDamageOnHurt(true)
                                    .build()
                    )
                    .component(DataComponents.GLIDER, Unit.INSTANCE)
                    .repairable(Items.GOLD_INGOT)
    );
    public static final DeferredItem<WoodenElytraItem> NETHERITE_ELYTRA = ITEMS.registerItem("netherite_elytra",
            WoodenElytraItem::new,
            () -> new Item.Properties()
                    .durability(192)
                    .attributes(NETHERITE_ELYTRA_ATTRIBUTES)
                    .component(
                            DataComponents.EQUIPPABLE,
                            Equippable.builder(EquipmentSlot.CHEST)
                                    .setEquipSound(SoundEvents.ARMOR_EQUIP_NETHERITE)
                                    .setAsset(NETHERITE_ELYTRA_ASSET)
                                    .setDamageOnHurt(true)
                                    .build()
                    )
                    .component(DataComponents.GLIDER, Unit.INSTANCE)
                    .repairable(Items.NETHERITE_INGOT)
    );
    // Creates a creative tab with the id "ashwake_armour_weapons:ashwake_tab", placed after the combat tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ASHWAKE_TAB = CREATIVE_MODE_TABS.register("ashwake_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.ashwake_armour_weapons")) // The language key for the title of your CreativeModeTab
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> WOODEN_ELYTRA.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(WOODEN_ELYTRA.get());
                output.accept(IRON_ELYTRA.get());
                output.accept(GOLDEN_ELYTRA.get());
                output.accept(NETHERITE_ELYTRA.get());
            }).build());

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public AshwakeArmourWeapons(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::modifyDefaultComponents);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (AshwakeArmourWeapons) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.LOG_DIRT_BLOCK.getAsBoolean()) {
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
        }

        LOGGER.info("{}{}", Config.MAGIC_NUMBER_INTRODUCTION.get(), Config.MAGIC_NUMBER.getAsInt());

        Config.ITEM_STRINGS.get().forEach((item) -> LOGGER.info("ITEM >> {}", item));
    }

    private void modifyDefaultComponents(ModifyDefaultComponentsEvent event) {
        boostDurability(event, Items.GOLDEN_SWORD, Items.IRON_SWORD);
        boostDurability(event, Items.GOLDEN_PICKAXE, Items.IRON_PICKAXE);
        boostDurability(event, Items.GOLDEN_AXE, Items.IRON_AXE);
        boostDurability(event, Items.GOLDEN_SHOVEL, Items.IRON_SHOVEL);
        boostDurability(event, Items.GOLDEN_HOE, Items.IRON_HOE);
        boostDurability(event, Items.GOLDEN_HELMET, Items.IRON_HELMET);
        boostDurability(event, Items.GOLDEN_CHESTPLATE, Items.IRON_CHESTPLATE);
        boostDurability(event, Items.GOLDEN_LEGGINGS, Items.IRON_LEGGINGS);
        boostDurability(event, Items.GOLDEN_BOOTS, Items.IRON_BOOTS);
        boostDurabilityById(
                event,
                Identifier.fromNamespaceAndPath("minecraft", "golden_spear"),
                Identifier.fromNamespaceAndPath("minecraft", "iron_spear")
        );
        boostDurabilityByPercent(event, Items.ELYTRA, 0.25f, 64);

        // Weapon progression (wood -> netherite).
        setWeaponStats(event, Items.WOODEN_SWORD, 3.5D, 1.45D);
        setWeaponStats(event, Items.STONE_SWORD, 4.0D, 1.50D);
        setWeaponStats(event, Items.IRON_SWORD, 4.5D, 1.55D);
        setWeaponStats(event, Items.GOLDEN_SWORD, 5.0D, 1.65D);
        setWeaponStats(event, Items.DIAMOND_SWORD, 5.5D, 1.70D);
        setWeaponStats(event, Items.NETHERITE_SWORD, 6.0D, 1.75D);

        setAxeStats(event, Items.WOODEN_AXE, 6.0D, 0.85D, 0.6D);
        setAxeStats(event, Items.STONE_AXE, 6.5D, 0.90D, 0.8D);
        setAxeStats(event, Items.IRON_AXE, 7.0D, 0.95D, 1.0D);
        setAxeStats(event, Items.GOLDEN_AXE, 7.5D, 1.00D, 1.2D);
        setAxeStats(event, Items.DIAMOND_AXE, 8.0D, 1.05D, 1.4D);
        setAxeStats(event, Items.NETHERITE_AXE, 8.5D, 1.10D, 1.6D);

        setPickaxeMiningStats(event, Items.WOODEN_PICKAXE, 0.6D);
        setPickaxeMiningStats(event, Items.STONE_PICKAXE, 0.9D);
        setPickaxeMiningStats(event, Items.IRON_PICKAXE, 1.2D);
        setPickaxeMiningStats(event, Items.GOLDEN_PICKAXE, 1.6D);
        setPickaxeMiningStats(event, Items.DIAMOND_PICKAXE, 2.0D);
        setPickaxeMiningStats(event, Items.NETHERITE_PICKAXE, 2.4D);

        setShovelDiggingStats(event, Items.WOODEN_SHOVEL, 0.4D);
        setShovelDiggingStats(event, Items.STONE_SHOVEL, 0.7D);
        setShovelDiggingStats(event, Items.IRON_SHOVEL, 1.0D);
        setShovelDiggingStats(event, Items.GOLDEN_SHOVEL, 1.3D);
        setShovelDiggingStats(event, Items.DIAMOND_SHOVEL, 1.6D);
        setShovelDiggingStats(event, Items.NETHERITE_SHOVEL, 2.0D);

        setHoeFarmingStats(event, Items.WOODEN_HOE, 0.2D);
        setHoeFarmingStats(event, Items.STONE_HOE, 0.3D);
        setHoeFarmingStats(event, Items.IRON_HOE, 0.4D);
        setHoeFarmingStats(event, Items.GOLDEN_HOE, 0.5D);
        setHoeFarmingStats(event, Items.DIAMOND_HOE, 0.6D);
        setHoeFarmingStats(event, Items.NETHERITE_HOE, 0.7D);

        setWeaponStatsById(event, Identifier.fromNamespaceAndPath("minecraft", "wooden_spear"), 3.0D, 0.85D);
        setWeaponStatsById(event, Identifier.fromNamespaceAndPath("minecraft", "stone_spear"), 3.5D, 0.90D);
        setWeaponStatsById(event, Identifier.fromNamespaceAndPath("minecraft", "iron_spear"), 4.0D, 0.95D);
        setWeaponStatsById(event, Identifier.fromNamespaceAndPath("minecraft", "golden_spear"), 4.5D, 1.00D);
        setWeaponStatsById(event, Identifier.fromNamespaceAndPath("minecraft", "diamond_spear"), 5.0D, 1.05D);
        setWeaponStatsById(event, Identifier.fromNamespaceAndPath("minecraft", "netherite_spear"), 5.5D, 1.10D);

        // Armor progression (wood/leather -> netherite).
        setArmorStats(event, Items.LEATHER_HELMET, EquipmentSlotGroup.HEAD, 1.0D, 0.0D, 0.0D);
        setArmorStats(event, Items.LEATHER_CHESTPLATE, EquipmentSlotGroup.CHEST, 2.5D, 0.0D, 0.0D);
        setArmorStats(event, Items.LEATHER_LEGGINGS, EquipmentSlotGroup.LEGS, 2.0D, 0.0D, 0.0D);
        setArmorStats(event, Items.LEATHER_BOOTS, EquipmentSlotGroup.FEET, 1.5D, 0.0D, 0.0D);

        setArmorStats(event, Items.CHAINMAIL_HELMET, EquipmentSlotGroup.HEAD, 2.0D, 0.5D, 0.0D);
        setArmorStats(event, Items.CHAINMAIL_CHESTPLATE, EquipmentSlotGroup.CHEST, 4.5D, 0.5D, 0.0D);
        setArmorStats(event, Items.CHAINMAIL_LEGGINGS, EquipmentSlotGroup.LEGS, 3.5D, 0.5D, 0.0D);
        setArmorStats(event, Items.CHAINMAIL_BOOTS, EquipmentSlotGroup.FEET, 2.0D, 0.5D, 0.0D);

        setArmorStats(event, Items.IRON_HELMET, EquipmentSlotGroup.HEAD, 2.5D, 1.0D, 0.0D);
        setArmorStats(event, Items.IRON_CHESTPLATE, EquipmentSlotGroup.CHEST, 5.5D, 1.0D, 0.0D);
        setArmorStats(event, Items.IRON_LEGGINGS, EquipmentSlotGroup.LEGS, 4.5D, 1.0D, 0.0D);
        setArmorStats(event, Items.IRON_BOOTS, EquipmentSlotGroup.FEET, 2.5D, 1.0D, 0.0D);

        setArmorStats(event, Items.GOLDEN_HELMET, EquipmentSlotGroup.HEAD, 3.0D, 1.5D, 0.0D);
        setArmorStats(event, Items.GOLDEN_CHESTPLATE, EquipmentSlotGroup.CHEST, 6.0D, 1.5D, 0.0D);
        setArmorStats(event, Items.GOLDEN_LEGGINGS, EquipmentSlotGroup.LEGS, 5.0D, 1.5D, 0.0D);
        setArmorStats(event, Items.GOLDEN_BOOTS, EquipmentSlotGroup.FEET, 3.0D, 1.5D, 0.0D);

        setArmorStats(event, Items.DIAMOND_HELMET, EquipmentSlotGroup.HEAD, 3.5D, 3.0D, 0.0D);
        setArmorStats(event, Items.DIAMOND_CHESTPLATE, EquipmentSlotGroup.CHEST, 7.0D, 3.0D, 0.0D);
        setArmorStats(event, Items.DIAMOND_LEGGINGS, EquipmentSlotGroup.LEGS, 6.0D, 3.0D, 0.0D);
        setArmorStats(event, Items.DIAMOND_BOOTS, EquipmentSlotGroup.FEET, 3.5D, 3.0D, 0.0D);

        setArmorStats(event, Items.NETHERITE_HELMET, EquipmentSlotGroup.HEAD, 4.0D, 5.0D, 0.2D);
        setArmorStats(event, Items.NETHERITE_CHESTPLATE, EquipmentSlotGroup.CHEST, 8.0D, 5.0D, 0.2D);
        setArmorStats(event, Items.NETHERITE_LEGGINGS, EquipmentSlotGroup.LEGS, 7.0D, 5.0D, 0.2D);
        setArmorStats(event, Items.NETHERITE_BOOTS, EquipmentSlotGroup.FEET, 4.0D, 5.0D, 0.2D);
    }

    private static void boostDurability(ModifyDefaultComponentsEvent event, Item target, Item reference) {
        Integer ironMax = reference.components().get(DataComponents.MAX_DAMAGE);
        if (ironMax == null) {
            return;
        }
        int boosted = ironMax + Math.max(10, ironMax / 5);
        event.modify(target, builder -> builder.set(DataComponents.MAX_DAMAGE, boosted));
    }

    private static void boostDurabilityById(ModifyDefaultComponentsEvent event, Identifier targetId, Identifier referenceId) {
        Item target = BuiltInRegistries.ITEM.getValue(targetId);
        Item reference = BuiltInRegistries.ITEM.getValue(referenceId);
        if (target == Items.AIR || reference == Items.AIR) {
            return;
        }
        Integer ironMax = reference.components().get(DataComponents.MAX_DAMAGE);
        if (ironMax == null) {
            return;
        }
        int boosted = ironMax + Math.max(10, ironMax / 5);
        event.modify(target, builder -> builder.set(DataComponents.MAX_DAMAGE, boosted));
    }

    private static void boostDurabilityByPercent(ModifyDefaultComponentsEvent event, Item target, float percent, int minimumBonus) {
        Integer baseMax = target.components().get(DataComponents.MAX_DAMAGE);
        if (baseMax == null) {
            return;
        }
        int bonus = Math.max(minimumBonus, Math.round(baseMax * percent));
        event.modify(target, builder -> builder.set(DataComponents.MAX_DAMAGE, baseMax + bonus));
    }

    private static void setWeaponStats(ModifyDefaultComponentsEvent event, Item item, double attackDamage, double attackSpeed) {
        ItemAttributeModifiers existing = item.components().get(DataComponents.ATTRIBUTE_MODIFIERS);
        ItemAttributeModifiers.Builder attrBuilder = ItemAttributeModifiers.builder();
        if (existing != null) {
            for (ItemAttributeModifiers.Entry entry : existing.modifiers()) {
                if (entry.attribute().equals(Attributes.ATTACK_DAMAGE) || entry.attribute().equals(Attributes.ATTACK_SPEED)) {
                    continue;
                }
                attrBuilder.add(entry.attribute(), entry.modifier(), entry.slot(), entry.display());
            }
        }

        attrBuilder.add(
                Attributes.ATTACK_DAMAGE,
                new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, attackDamage - 1.0D, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND
        );
        attrBuilder.add(
                Attributes.ATTACK_SPEED,
                new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, attackSpeed - 4.0D, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND
        );

        ItemAttributeModifiers modifiers = attrBuilder.build();
        event.modify(item, builder -> builder.set(DataComponents.ATTRIBUTE_MODIFIERS, modifiers));
    }

    private static void setWeaponStatsById(ModifyDefaultComponentsEvent event, Identifier itemId, double attackDamage, double attackSpeed) {
        Item item = BuiltInRegistries.ITEM.getValue(itemId);
        if (item == Items.AIR) {
            return;
        }
        setWeaponStats(event, item, attackDamage, attackSpeed);
    }

    private static void setAxeStats(ModifyDefaultComponentsEvent event, Item item, double attackDamage, double attackSpeed, double woodcutSpeed) {
        ItemAttributeModifiers existing = item.components().get(DataComponents.ATTRIBUTE_MODIFIERS);
        ItemAttributeModifiers.Builder attrBuilder = ItemAttributeModifiers.builder();
        if (existing != null) {
            for (ItemAttributeModifiers.Entry entry : existing.modifiers()) {
                if (entry.attribute().equals(Attributes.ATTACK_DAMAGE)
                        || entry.attribute().equals(Attributes.ATTACK_SPEED)
                        || entry.attribute().equals(Attributes.BLOCK_BREAK_SPEED)) {
                    continue;
                }
                attrBuilder.add(entry.attribute(), entry.modifier(), entry.slot(), entry.display());
            }
        }

        attrBuilder.add(
                Attributes.ATTACK_DAMAGE,
                new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, attackDamage - 1.0D, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND
        );
        attrBuilder.add(
                Attributes.ATTACK_SPEED,
                new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, attackSpeed - 4.0D, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND
        );
        Identifier itemId = BuiltInRegistries.ITEM.getKey(item);
        attrBuilder.add(
                Attributes.BLOCK_BREAK_SPEED,
                new AttributeModifier(
                        Identifier.fromNamespaceAndPath(AshwakeArmourWeapons.MODID, itemId.getPath() + "_woodcut"),
                        woodcutSpeed,
                        AttributeModifier.Operation.ADD_VALUE
                ),
                EquipmentSlotGroup.MAINHAND
        );

        ItemAttributeModifiers modifiers = attrBuilder.build();
        event.modify(item, builder -> builder.set(DataComponents.ATTRIBUTE_MODIFIERS, modifiers));
    }

    private static void setPickaxeMiningStats(ModifyDefaultComponentsEvent event, Item item, double miningEfficiency) {
        ItemAttributeModifiers existing = item.components().get(DataComponents.ATTRIBUTE_MODIFIERS);
        ItemAttributeModifiers.Builder attrBuilder = ItemAttributeModifiers.builder();
        if (existing != null) {
            for (ItemAttributeModifiers.Entry entry : existing.modifiers()) {
                if (entry.attribute().equals(Attributes.MINING_EFFICIENCY)) {
                    continue;
                }
                attrBuilder.add(entry.attribute(), entry.modifier(), entry.slot(), entry.display());
            }
        }

        Identifier itemId = BuiltInRegistries.ITEM.getKey(item);
        attrBuilder.add(
                Attributes.MINING_EFFICIENCY,
                new AttributeModifier(
                        Identifier.fromNamespaceAndPath(AshwakeArmourWeapons.MODID, itemId.getPath() + "_mining"),
                        miningEfficiency,
                        AttributeModifier.Operation.ADD_VALUE
                ),
                EquipmentSlotGroup.MAINHAND
        );

        ItemAttributeModifiers modifiers = attrBuilder.build();
        event.modify(item, builder -> builder.set(DataComponents.ATTRIBUTE_MODIFIERS, modifiers));
    }

    private static void setShovelDiggingStats(ModifyDefaultComponentsEvent event, Item item, double diggingSpeed) {
        ItemAttributeModifiers existing = item.components().get(DataComponents.ATTRIBUTE_MODIFIERS);
        ItemAttributeModifiers.Builder attrBuilder = ItemAttributeModifiers.builder();
        if (existing != null) {
            for (ItemAttributeModifiers.Entry entry : existing.modifiers()) {
                if (entry.attribute().equals(Attributes.BLOCK_BREAK_SPEED)) {
                    continue;
                }
                attrBuilder.add(entry.attribute(), entry.modifier(), entry.slot(), entry.display());
            }
        }

        Identifier itemId = BuiltInRegistries.ITEM.getKey(item);
        attrBuilder.add(
                Attributes.BLOCK_BREAK_SPEED,
                new AttributeModifier(
                        Identifier.fromNamespaceAndPath(AshwakeArmourWeapons.MODID, itemId.getPath() + "_dig"),
                        diggingSpeed,
                        AttributeModifier.Operation.ADD_VALUE
                ),
                EquipmentSlotGroup.MAINHAND
        );

        ItemAttributeModifiers modifiers = attrBuilder.build();
        event.modify(item, builder -> builder.set(DataComponents.ATTRIBUTE_MODIFIERS, modifiers));
    }

    private static void setHoeFarmingStats(ModifyDefaultComponentsEvent event, Item item, double interactionRange) {
        ItemAttributeModifiers existing = item.components().get(DataComponents.ATTRIBUTE_MODIFIERS);
        ItemAttributeModifiers.Builder attrBuilder = ItemAttributeModifiers.builder();
        if (existing != null) {
            for (ItemAttributeModifiers.Entry entry : existing.modifiers()) {
                if (entry.attribute().equals(Attributes.BLOCK_INTERACTION_RANGE)) {
                    continue;
                }
                attrBuilder.add(entry.attribute(), entry.modifier(), entry.slot(), entry.display());
            }
        }

        Identifier itemId = BuiltInRegistries.ITEM.getKey(item);
        attrBuilder.add(
                Attributes.BLOCK_INTERACTION_RANGE,
                new AttributeModifier(
                        Identifier.fromNamespaceAndPath(AshwakeArmourWeapons.MODID, itemId.getPath() + "_farming"),
                        interactionRange,
                        AttributeModifier.Operation.ADD_VALUE
                ),
                EquipmentSlotGroup.MAINHAND
        );

        ItemAttributeModifiers modifiers = attrBuilder.build();
        event.modify(item, builder -> builder.set(DataComponents.ATTRIBUTE_MODIFIERS, modifiers));
    }

    private static void setArmorStats(ModifyDefaultComponentsEvent event, Item item, EquipmentSlotGroup slot, double armor, double toughness, double knockbackResistance) {
        ItemAttributeModifiers existing = item.components().get(DataComponents.ATTRIBUTE_MODIFIERS);
        ItemAttributeModifiers.Builder attrBuilder = ItemAttributeModifiers.builder();
        if (existing != null) {
            for (ItemAttributeModifiers.Entry entry : existing.modifiers()) {
                if (entry.attribute().equals(Attributes.ARMOR)
                        || entry.attribute().equals(Attributes.ARMOR_TOUGHNESS)
                        || entry.attribute().equals(Attributes.KNOCKBACK_RESISTANCE)) {
                    continue;
                }
                attrBuilder.add(entry.attribute(), entry.modifier(), entry.slot(), entry.display());
            }
        }

        Identifier itemId = BuiltInRegistries.ITEM.getKey(item);
        attrBuilder.add(
                Attributes.ARMOR,
                new AttributeModifier(
                        Identifier.fromNamespaceAndPath(AshwakeArmourWeapons.MODID, itemId.getPath() + "_armor"),
                        armor,
                        AttributeModifier.Operation.ADD_VALUE
                ),
                slot
        );
        if (toughness != 0.0D) {
            attrBuilder.add(
                    Attributes.ARMOR_TOUGHNESS,
                    new AttributeModifier(
                            Identifier.fromNamespaceAndPath(AshwakeArmourWeapons.MODID, itemId.getPath() + "_toughness"),
                            toughness,
                            AttributeModifier.Operation.ADD_VALUE
                    ),
                    slot
            );
        }
        if (knockbackResistance != 0.0D) {
            attrBuilder.add(
                    Attributes.KNOCKBACK_RESISTANCE,
                    new AttributeModifier(
                            Identifier.fromNamespaceAndPath(AshwakeArmourWeapons.MODID, itemId.getPath() + "_knockback"),
                            knockbackResistance,
                            AttributeModifier.Operation.ADD_VALUE
                    ),
                    slot
            );
        }

        ItemAttributeModifiers modifiers = attrBuilder.build();
        event.modify(item, builder -> builder.set(DataComponents.ATTRIBUTE_MODIFIERS, modifiers));
    }

    // Add the block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ASHWAKE_BLOCK_ITEM);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}
