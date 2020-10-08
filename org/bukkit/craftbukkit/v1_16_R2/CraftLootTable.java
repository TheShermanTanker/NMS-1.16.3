/*     */ package org.bukkit.craftbukkit.v1_16_R2;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.server.v1_16_R2.DamageSource;
/*     */ import net.minecraft.server.v1_16_R2.Entity;
/*     */ import net.minecraft.server.v1_16_R2.EntityHuman;
/*     */ import net.minecraft.server.v1_16_R2.IInventory;
/*     */ import net.minecraft.server.v1_16_R2.ItemStack;
/*     */ import net.minecraft.server.v1_16_R2.LootContextParameter;
/*     */ import net.minecraft.server.v1_16_R2.LootContextParameterSet;
/*     */ import net.minecraft.server.v1_16_R2.LootContextParameters;
/*     */ import net.minecraft.server.v1_16_R2.LootTable;
/*     */ import net.minecraft.server.v1_16_R2.LootTableInfo;
/*     */ import net.minecraft.server.v1_16_R2.Vec3D;
/*     */ import net.minecraft.server.v1_16_R2.WorldServer;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftEntity;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventory;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.loot.LootContext;
/*     */ import org.bukkit.loot.LootTable;
/*     */ 
/*     */ public class CraftLootTable implements LootTable {
/*     */   private final LootTable handle;
/*     */   
/*     */   public CraftLootTable(NamespacedKey key, LootTable handle) {
/*  34 */     this.handle = handle;
/*  35 */     this.key = key;
/*     */   }
/*     */   private final NamespacedKey key;
/*     */   public LootTable getHandle() {
/*  39 */     return this.handle;
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<ItemStack> populateLoot(Random random, LootContext context) {
/*  44 */     LootTableInfo nmsContext = convertContext(context);
/*  45 */     List<ItemStack> nmsItems = this.handle.populateLoot(nmsContext);
/*  46 */     Collection<ItemStack> bukkit = new ArrayList<>(nmsItems.size());
/*     */     
/*  48 */     for (ItemStack item : nmsItems) {
/*  49 */       if (item.isEmpty()) {
/*     */         continue;
/*     */       }
/*  52 */       bukkit.add(CraftItemStack.asBukkitCopy(item));
/*     */     } 
/*     */     
/*  55 */     return bukkit;
/*     */   }
/*     */ 
/*     */   
/*     */   public void fillInventory(Inventory inventory, Random random, LootContext context) {
/*  60 */     LootTableInfo nmsContext = convertContext(context);
/*  61 */     CraftInventory craftInventory = (CraftInventory)inventory;
/*  62 */     IInventory handle = craftInventory.getInventory();
/*     */ 
/*     */     
/*  65 */     getHandle().fillInventory(handle, nmsContext);
/*     */   }
/*     */ 
/*     */   
/*     */   public NamespacedKey getKey() {
/*  70 */     return this.key;
/*     */   }
/*     */   
/*     */   private LootTableInfo convertContext(LootContext context) {
/*  74 */     Location loc = context.getLocation();
/*  75 */     WorldServer handle = ((CraftWorld)loc.getWorld()).getHandle();
/*     */     
/*  77 */     LootTableInfo.Builder builder = new LootTableInfo.Builder(handle);
/*  78 */     if (getHandle() != LootTable.EMPTY) {
/*     */ 
/*     */       
/*  81 */       if (context.getLootedEntity() != null) {
/*  82 */         Entity nmsLootedEntity = ((CraftEntity)context.getLootedEntity()).getHandle();
/*  83 */         builder.set(LootContextParameters.THIS_ENTITY, nmsLootedEntity);
/*  84 */         builder.set(LootContextParameters.DAMAGE_SOURCE, DamageSource.GENERIC);
/*  85 */         builder.set(LootContextParameters.ORIGIN, nmsLootedEntity.getPositionVector());
/*     */       } 
/*     */       
/*  88 */       if (context.getKiller() != null) {
/*  89 */         EntityHuman nmsKiller = ((CraftHumanEntity)context.getKiller()).getHandle();
/*  90 */         builder.set(LootContextParameters.KILLER_ENTITY, nmsKiller);
/*     */         
/*  92 */         builder.set(LootContextParameters.DAMAGE_SOURCE, DamageSource.playerAttack(nmsKiller));
/*  93 */         builder.set(LootContextParameters.LAST_DAMAGE_PLAYER, nmsKiller);
/*     */       } 
/*     */ 
/*     */       
/*  97 */       if (context.getLootingModifier() != -1) {
/*  98 */         builder.set(LootContextParameters.LOOTING_MOD, Integer.valueOf(context.getLootingModifier()));
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 103 */     LootContextParameterSet.Builder nmsBuilder = new LootContextParameterSet.Builder();
/* 104 */     for (LootContextParameter<?> param : (Iterable<LootContextParameter<?>>)getHandle().getLootContextParameterSet().getRequired()) {
/* 105 */       nmsBuilder.addRequired(param);
/*     */     }
/* 107 */     for (LootContextParameter<?> param : (Iterable<LootContextParameter<?>>)getHandle().getLootContextParameterSet().getOptional()) {
/* 108 */       if (!getHandle().getLootContextParameterSet().getRequired().contains(param)) {
/* 109 */         nmsBuilder.addOptional(param);
/*     */       }
/*     */     } 
/* 112 */     nmsBuilder.addOptional(LootContextParameters.LOOTING_MOD);
/*     */     
/* 114 */     return builder.build(nmsBuilder.build());
/*     */   }
/*     */   
/*     */   public static LootContext convertContext(LootTableInfo info) {
/* 118 */     Vec3D position = (Vec3D)info.getContextParameter(LootContextParameters.ORIGIN);
/* 119 */     Location location = new Location(info.getWorld().getWorld(), position.getX(), position.getY(), position.getZ());
/* 120 */     LootContext.Builder contextBuilder = new LootContext.Builder(location);
/*     */     
/* 122 */     if (info.hasContextParameter(LootContextParameters.KILLER_ENTITY)) {
/* 123 */       CraftEntity killer = ((Entity)info.getContextParameter(LootContextParameters.KILLER_ENTITY)).getBukkitEntity();
/* 124 */       if (killer instanceof CraftHumanEntity) {
/* 125 */         contextBuilder.killer((HumanEntity)killer);
/*     */       }
/*     */     } 
/*     */     
/* 129 */     if (info.hasContextParameter(LootContextParameters.THIS_ENTITY)) {
/* 130 */       contextBuilder.lootedEntity((Entity)((Entity)info.getContextParameter(LootContextParameters.THIS_ENTITY)).getBukkitEntity());
/*     */     }
/*     */     
/* 133 */     if (info.hasContextParameter(LootContextParameters.LOOTING_MOD)) {
/* 134 */       contextBuilder.lootingModifier(((Integer)info.getContextParameter(LootContextParameters.LOOTING_MOD)).intValue());
/*     */     }
/*     */     
/* 137 */     contextBuilder.luck(info.getLuck());
/* 138 */     return contextBuilder.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 143 */     return getKey().toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 148 */     if (!(obj instanceof LootTable)) {
/* 149 */       return false;
/*     */     }
/*     */     
/* 152 */     LootTable table = (LootTable)obj;
/* 153 */     return table.getKey().equals(getKey());
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\CraftLootTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */