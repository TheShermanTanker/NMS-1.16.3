/*     */ package com.destroystokyo.paper.event.player;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.event.player.PlayerEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerArmorChangeEvent
/*     */   extends PlayerEvent
/*     */ {
/*  24 */   private static final HandlerList HANDLERS = new HandlerList();
/*     */   
/*     */   @NotNull
/*     */   private final SlotType slotType;
/*     */ 
/*     */   
/*     */   public PlayerArmorChangeEvent(@NotNull Player player, @NotNull SlotType slotType, @Nullable ItemStack oldItem, @Nullable ItemStack newItem) {
/*  31 */     super(player);
/*  32 */     this.slotType = slotType;
/*  33 */     this.oldItem = oldItem;
/*  34 */     this.newItem = newItem;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private final ItemStack oldItem;
/*     */   @Nullable
/*     */   private final ItemStack newItem;
/*     */   
/*     */   @NotNull
/*     */   public SlotType getSlotType() {
/*  44 */     return this.slotType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ItemStack getOldItem() {
/*  54 */     return this.oldItem;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ItemStack getNewItem() {
/*  64 */     return this.newItem;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  69 */     return "ArmorChangeEvent{player=" + this.player + ", slotType=" + this.slotType + ", oldItem=" + this.oldItem + ", newItem=" + this.newItem + '}';
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/*  75 */     return HANDLERS;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/*  80 */     return HANDLERS;
/*     */   }
/*     */   
/*     */   public enum SlotType {
/*  84 */     HEAD((String)new Material[] { Material.NETHERITE_HELMET, Material.DIAMOND_HELMET, Material.GOLDEN_HELMET, Material.IRON_HELMET, Material.CHAINMAIL_HELMET, Material.LEATHER_HELMET, Material.CARVED_PUMPKIN, Material.PLAYER_HEAD, Material.SKELETON_SKULL, Material.ZOMBIE_HEAD, Material.CREEPER_HEAD, Material.WITHER_SKELETON_SKULL, Material.TURTLE_HELMET }),
/*  85 */     CHEST((String)new Material[] { Material.NETHERITE_CHESTPLATE, Material.DIAMOND_CHESTPLATE, Material.GOLDEN_CHESTPLATE, Material.IRON_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE, Material.LEATHER_CHESTPLATE, Material.ELYTRA }),
/*  86 */     LEGS((String)new Material[] { Material.NETHERITE_LEGGINGS, Material.DIAMOND_LEGGINGS, Material.GOLDEN_LEGGINGS, Material.IRON_LEGGINGS, Material.CHAINMAIL_LEGGINGS, Material.LEATHER_LEGGINGS }),
/*  87 */     FEET((String)new Material[] { Material.NETHERITE_BOOTS, Material.DIAMOND_BOOTS, Material.GOLDEN_BOOTS, Material.IRON_BOOTS, Material.CHAINMAIL_BOOTS, Material.LEATHER_BOOTS });
/*     */     
/*  89 */     private final Set<Material> mutableTypes = new HashSet<>();
/*     */     private Set<Material> immutableTypes;
/*     */     
/*     */     SlotType(Material... types) {
/*  93 */       this.mutableTypes.addAll(Arrays.asList(types));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Set<Material> getTypes() {
/* 104 */       if (this.immutableTypes == null) {
/* 105 */         this.immutableTypes = Collections.unmodifiableSet(this.mutableTypes);
/*     */       }
/*     */       
/* 108 */       return this.immutableTypes;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public static SlotType getByMaterial(@NotNull Material material) {
/* 119 */       for (SlotType slotType : values()) {
/* 120 */         if (slotType.getTypes().contains(material)) {
/* 121 */           return slotType;
/*     */         }
/*     */       } 
/* 124 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static boolean isEquipable(@NotNull Material material) {
/* 134 */       return (getByMaterial(material) != null);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\player\PlayerArmorChangeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */