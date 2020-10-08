/*     */ package org.bukkit.event.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class PlayerDeathEvent
/*     */   extends EntityDeathEvent {
/*  15 */   private int newExp = 0;
/*  16 */   private String deathMessage = "";
/*  17 */   private int newLevel = 0;
/*  18 */   private int newTotalExp = 0;
/*     */   
/*     */   private boolean keepLevel = false;
/*     */   
/*     */   private boolean keepInventory = false;
/*  23 */   private List<ItemStack> itemsToKeep = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean doExpDrop;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<ItemStack> getItemsToKeep() {
/*  52 */     return this.itemsToKeep;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldDropExperience() {
/*  61 */     return this.doExpDrop;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShouldDropExperience(boolean doExpDrop) {
/*  68 */     this.doExpDrop = doExpDrop;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PlayerDeathEvent(@NotNull Player player, @NotNull List<ItemStack> drops, int droppedExp, @Nullable String deathMessage) {
/*  74 */     this(player, drops, droppedExp, 0, deathMessage);
/*     */   }
/*     */   
/*     */   public PlayerDeathEvent(@NotNull Player player, @NotNull List<ItemStack> drops, int droppedExp, int newExp, @Nullable String deathMessage) {
/*  78 */     this(player, drops, droppedExp, newExp, 0, 0, deathMessage);
/*     */   }
/*     */ 
/*     */   
/*     */   public PlayerDeathEvent(@NotNull Player player, @NotNull List<ItemStack> drops, int droppedExp, int newExp, int newTotalExp, int newLevel, @Nullable String deathMessage) {
/*  83 */     this(player, drops, droppedExp, newExp, newTotalExp, newLevel, deathMessage, (player.getGameMode() != GameMode.SPECTATOR));
/*     */   }
/*     */   
/*     */   public PlayerDeathEvent(@NotNull Player player, @NotNull List<ItemStack> drops, int droppedExp, int newExp, int newTotalExp, int newLevel, @Nullable String deathMessage, boolean doExpDrop) {
/*  87 */     super((LivingEntity)player, drops, droppedExp);
/*  88 */     this.newExp = newExp;
/*  89 */     this.newTotalExp = newTotalExp;
/*  90 */     this.newLevel = newLevel;
/*  91 */     this.deathMessage = deathMessage;
/*  92 */     this.doExpDrop = doExpDrop;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Player getEntity() {
/*  98 */     return (Player)this.entity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDeathMessage(@Nullable String deathMessage) {
/* 107 */     this.deathMessage = deathMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public String getDeathMessage() {
/* 117 */     return this.deathMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNewExp() {
/* 129 */     return this.newExp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNewExp(int exp) {
/* 141 */     this.newExp = exp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNewLevel() {
/* 150 */     return this.newLevel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNewLevel(int level) {
/* 159 */     this.newLevel = level;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNewTotalExp() {
/* 168 */     return this.newTotalExp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNewTotalExp(int totalExp) {
/* 177 */     this.newTotalExp = totalExp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getKeepLevel() {
/* 188 */     return this.keepLevel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKeepLevel(boolean keepLevel) {
/* 203 */     this.keepLevel = keepLevel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKeepInventory(boolean keepInventory) {
/* 216 */     this.keepInventory = keepInventory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getKeepInventory() {
/* 225 */     return this.keepInventory;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\PlayerDeathEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */