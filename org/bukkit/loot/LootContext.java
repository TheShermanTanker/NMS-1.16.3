/*     */ package org.bukkit.loot;
/*     */ 
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class LootContext
/*     */ {
/*     */   public static final int DEFAULT_LOOT_MODIFIER = -1;
/*     */   private final Location location;
/*     */   private final float luck;
/*     */   private final int lootingModifier;
/*     */   private final Entity lootedEntity;
/*     */   private final HumanEntity killer;
/*     */   
/*     */   private LootContext(@NotNull Location location, float luck, int lootingModifier, @Nullable Entity lootedEntity, @Nullable HumanEntity killer) {
/*  25 */     Validate.notNull(location, "LootContext location cannot be null");
/*  26 */     Validate.notNull(location.getWorld(), "LootContext World cannot be null");
/*  27 */     this.location = location;
/*  28 */     this.luck = luck;
/*  29 */     this.lootingModifier = lootingModifier;
/*  30 */     this.lootedEntity = lootedEntity;
/*  31 */     this.killer = killer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Location getLocation() {
/*  41 */     return this.location;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getLuck() {
/*  52 */     return this.luck;
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
/*     */   public int getLootingModifier() {
/*  67 */     return this.lootingModifier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity getLootedEntity() {
/*  77 */     return this.lootedEntity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public HumanEntity getKiller() {
/*  88 */     return this.killer;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Builder
/*     */   {
/*     */     private final Location location;
/*     */ 
/*     */     
/*     */     private float luck;
/*     */     
/* 100 */     private int lootingModifier = -1;
/*     */ 
/*     */     
/*     */     private Entity lootedEntity;
/*     */ 
/*     */     
/*     */     private HumanEntity killer;
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder(@NotNull Location location) {
/* 111 */       this.location = location;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder luck(float luck) {
/* 122 */       this.luck = luck;
/* 123 */       return this;
/*     */     }
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
/*     */     @NotNull
/*     */     public Builder lootingModifier(int modifier) {
/* 137 */       this.lootingModifier = modifier;
/* 138 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder lootedEntity(@Nullable Entity lootedEntity) {
/* 149 */       this.lootedEntity = lootedEntity;
/* 150 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder killer(@Nullable HumanEntity killer) {
/* 163 */       this.killer = killer;
/* 164 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public LootContext build() {
/* 175 */       return new LootContext(this.location, this.luck, this.lootingModifier, this.lootedEntity, this.killer);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\loot\LootContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */