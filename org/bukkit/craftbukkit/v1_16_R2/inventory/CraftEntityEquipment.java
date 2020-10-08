/*     */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import net.minecraft.server.v1_16_R2.EntityInsentient;
/*     */ import net.minecraft.server.v1_16_R2.EnumItemSlot;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftEquipmentSlot;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftLivingEntity;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.inventory.EntityEquipment;
/*     */ import org.bukkit.inventory.EquipmentSlot;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class CraftEntityEquipment
/*     */   implements EntityEquipment {
/*     */   private final CraftLivingEntity entity;
/*     */   
/*     */   public CraftEntityEquipment(CraftLivingEntity entity) {
/*  18 */     this.entity = entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItem(EquipmentSlot slot, ItemStack item) {
/*  23 */     Preconditions.checkArgument((slot != null), "slot must not be null");
/*  24 */     EnumItemSlot nmsSlot = CraftEquipmentSlot.getNMS(slot);
/*  25 */     setEquipment(nmsSlot, item);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItem(EquipmentSlot slot) {
/*  30 */     Preconditions.checkArgument((slot != null), "slot must not be null");
/*  31 */     EnumItemSlot nmsSlot = CraftEquipmentSlot.getNMS(slot);
/*  32 */     return getEquipment(nmsSlot);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItemInMainHand() {
/*  37 */     return getEquipment(EnumItemSlot.MAINHAND);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemInMainHand(ItemStack item) {
/*  42 */     setEquipment(EnumItemSlot.MAINHAND, item);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItemInOffHand() {
/*  47 */     return getEquipment(EnumItemSlot.OFFHAND);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemInOffHand(ItemStack item) {
/*  52 */     setEquipment(EnumItemSlot.OFFHAND, item);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItemInHand() {
/*  57 */     return getItemInMainHand();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemInHand(ItemStack stack) {
/*  62 */     setItemInMainHand(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getHelmet() {
/*  67 */     return getEquipment(EnumItemSlot.HEAD);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHelmet(ItemStack helmet) {
/*  72 */     setEquipment(EnumItemSlot.HEAD, helmet);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getChestplate() {
/*  77 */     return getEquipment(EnumItemSlot.CHEST);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setChestplate(ItemStack chestplate) {
/*  82 */     setEquipment(EnumItemSlot.CHEST, chestplate);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getLeggings() {
/*  87 */     return getEquipment(EnumItemSlot.LEGS);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLeggings(ItemStack leggings) {
/*  92 */     setEquipment(EnumItemSlot.LEGS, leggings);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getBoots() {
/*  97 */     return getEquipment(EnumItemSlot.FEET);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBoots(ItemStack boots) {
/* 102 */     setEquipment(EnumItemSlot.FEET, boots);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack[] getArmorContents() {
/* 111 */     ItemStack[] armor = { getEquipment(EnumItemSlot.FEET), getEquipment(EnumItemSlot.LEGS), getEquipment(EnumItemSlot.CHEST), getEquipment(EnumItemSlot.HEAD) };
/*     */     
/* 113 */     return armor;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setArmorContents(ItemStack[] items) {
/* 118 */     setEquipment(EnumItemSlot.FEET, (items.length >= 1) ? items[0] : null);
/* 119 */     setEquipment(EnumItemSlot.LEGS, (items.length >= 2) ? items[1] : null);
/* 120 */     setEquipment(EnumItemSlot.CHEST, (items.length >= 3) ? items[2] : null);
/* 121 */     setEquipment(EnumItemSlot.HEAD, (items.length >= 4) ? items[3] : null);
/*     */   }
/*     */   
/*     */   private ItemStack getEquipment(EnumItemSlot slot) {
/* 125 */     return CraftItemStack.asBukkitCopy(this.entity.getHandle().getEquipment(slot));
/*     */   }
/*     */   
/*     */   private void setEquipment(EnumItemSlot slot, ItemStack stack) {
/* 129 */     this.entity.getHandle().setSlot(slot, CraftItemStack.asNMSCopy(stack));
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 134 */     for (EnumItemSlot slot : EnumItemSlot.values()) {
/* 135 */       setEquipment(slot, null);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity getHolder() {
/* 141 */     return (Entity)this.entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getItemInHandDropChance() {
/* 146 */     return getItemInMainHandDropChance();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemInHandDropChance(float chance) {
/* 151 */     setItemInMainHandDropChance(chance);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getItemInMainHandDropChance() {
/* 156 */     return getDropChance(EnumItemSlot.MAINHAND);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemInMainHandDropChance(float chance) {
/* 161 */     setDropChance(EnumItemSlot.MAINHAND, chance);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getItemInOffHandDropChance() {
/* 166 */     return getDropChance(EnumItemSlot.OFFHAND);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemInOffHandDropChance(float chance) {
/* 171 */     setDropChance(EnumItemSlot.OFFHAND, chance);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getHelmetDropChance() {
/* 176 */     return getDropChance(EnumItemSlot.HEAD);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHelmetDropChance(float chance) {
/* 181 */     setDropChance(EnumItemSlot.HEAD, chance);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getChestplateDropChance() {
/* 186 */     return getDropChance(EnumItemSlot.CHEST);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setChestplateDropChance(float chance) {
/* 191 */     setDropChance(EnumItemSlot.CHEST, chance);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getLeggingsDropChance() {
/* 196 */     return getDropChance(EnumItemSlot.LEGS);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLeggingsDropChance(float chance) {
/* 201 */     setDropChance(EnumItemSlot.LEGS, chance);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getBootsDropChance() {
/* 206 */     return getDropChance(EnumItemSlot.FEET);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBootsDropChance(float chance) {
/* 211 */     setDropChance(EnumItemSlot.FEET, chance);
/*     */   }
/*     */   
/*     */   private void setDropChance(EnumItemSlot slot, float chance) {
/* 215 */     if (slot == EnumItemSlot.MAINHAND || slot == EnumItemSlot.OFFHAND) {
/* 216 */       ((EntityInsentient)this.entity.getHandle()).dropChanceHand[slot.b()] = chance;
/*     */     } else {
/* 218 */       ((EntityInsentient)this.entity.getHandle()).dropChanceArmor[slot.b()] = chance;
/*     */     } 
/*     */   }
/*     */   
/*     */   private float getDropChance(EnumItemSlot slot) {
/* 223 */     if (slot == EnumItemSlot.MAINHAND || slot == EnumItemSlot.OFFHAND) {
/* 224 */       return ((EntityInsentient)this.entity.getHandle()).dropChanceHand[slot.b()];
/*     */     }
/* 226 */     return ((EntityInsentient)this.entity.getHandle()).dropChanceArmor[slot.b()];
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftEntityEquipment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */