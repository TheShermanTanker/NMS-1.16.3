/*     */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*     */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*     */ import net.minecraft.server.v1_16_R2.Entity;
/*     */ import net.minecraft.server.v1_16_R2.EntityHanging;
/*     */ import net.minecraft.server.v1_16_R2.EntityItemFrame;
/*     */ import net.minecraft.server.v1_16_R2.EnumDirection;
/*     */ import net.minecraft.server.v1_16_R2.ItemStack;
/*     */ import net.minecraft.server.v1_16_R2.World;
/*     */ import net.minecraft.server.v1_16_R2.WorldServer;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Rotation;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.ItemFrame;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class CraftItemFrame extends CraftHanging implements ItemFrame {
/*     */   public CraftItemFrame(CraftServer server, EntityItemFrame entity) {
/*  21 */     super(server, (EntityHanging)entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setFacingDirection(BlockFace face, boolean force) {
/*  26 */     EntityItemFrame entityItemFrame = getHandle();
/*  27 */     EnumDirection oldDir = entityItemFrame.getDirection();
/*  28 */     EnumDirection newDir = CraftBlock.blockFaceToNotch(face);
/*     */     
/*  30 */     getHandle().setDirection(newDir);
/*  31 */     if (!force && !entityItemFrame.survives()) {
/*  32 */       entityItemFrame.setDirection(oldDir);
/*  33 */       return false;
/*     */     } 
/*     */     
/*  36 */     update();
/*     */     
/*  38 */     return true;
/*     */   }
/*     */   
/*     */   private void update() {
/*  42 */     EntityItemFrame old = getHandle();
/*     */     
/*  44 */     WorldServer world = ((CraftWorld)getWorld()).getHandle();
/*  45 */     BlockPosition position = old.getBlockPosition();
/*  46 */     EnumDirection direction = old.getDirection();
/*  47 */     ItemStack item = (old.getItem() != null) ? old.getItem().cloneItemStack() : null;
/*     */     
/*  49 */     old.die();
/*     */     
/*  51 */     EntityItemFrame frame = new EntityItemFrame((World)world, position, direction);
/*  52 */     frame.setItem(item, true, false);
/*  53 */     world.addEntity((Entity)frame);
/*  54 */     this.entity = (Entity)frame;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItem(ItemStack item) {
/*  59 */     setItem(item, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItem(ItemStack item, boolean playSound) {
/*  64 */     getHandle().setItem(CraftItemStack.asNMSCopy(item), true, playSound);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItem() {
/*  69 */     return CraftItemStack.asBukkitCopy(getHandle().getItem());
/*     */   }
/*     */ 
/*     */   
/*     */   public Rotation getRotation() {
/*  74 */     return toBukkitRotation(getHandle().getRotation());
/*     */   }
/*     */ 
/*     */   
/*     */   Rotation toBukkitRotation(int value) {
/*  79 */     switch (value) {
/*     */       case 0:
/*  81 */         return Rotation.NONE;
/*     */       case 1:
/*  83 */         return Rotation.CLOCKWISE_45;
/*     */       case 2:
/*  85 */         return Rotation.CLOCKWISE;
/*     */       case 3:
/*  87 */         return Rotation.CLOCKWISE_135;
/*     */       case 4:
/*  89 */         return Rotation.FLIPPED;
/*     */       case 5:
/*  91 */         return Rotation.FLIPPED_45;
/*     */       case 6:
/*  93 */         return Rotation.COUNTER_CLOCKWISE;
/*     */       case 7:
/*  95 */         return Rotation.COUNTER_CLOCKWISE_45;
/*     */     } 
/*  97 */     throw new AssertionError("Unknown rotation " + value + " for " + getHandle());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotation(Rotation rotation) {
/* 103 */     Validate.notNull(rotation, "Rotation cannot be null");
/* 104 */     getHandle().setRotation(toInteger(rotation));
/*     */   }
/*     */ 
/*     */   
/*     */   static int toInteger(Rotation rotation) {
/* 109 */     switch (rotation) {
/*     */       case NONE:
/* 111 */         return 0;
/*     */       case CLOCKWISE_45:
/* 113 */         return 1;
/*     */       case CLOCKWISE:
/* 115 */         return 2;
/*     */       case CLOCKWISE_135:
/* 117 */         return 3;
/*     */       case FLIPPED:
/* 119 */         return 4;
/*     */       case FLIPPED_45:
/* 121 */         return 5;
/*     */       case COUNTER_CLOCKWISE:
/* 123 */         return 6;
/*     */       case COUNTER_CLOCKWISE_45:
/* 125 */         return 7;
/*     */     } 
/* 127 */     throw new IllegalArgumentException(rotation + " is not applicable to an ItemFrame");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVisible() {
/* 133 */     return !getHandle().isInvisible();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisible(boolean visible) {
/* 138 */     getHandle().setInvisible(!visible);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFixed() {
/* 143 */     return (getHandle()).fixed;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFixed(boolean fixed) {
/* 148 */     (getHandle()).fixed = fixed;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityItemFrame getHandle() {
/* 153 */     return (EntityItemFrame)this.entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 158 */     return "CraftItemFrame{item=" + getItem() + ", rotation=" + getRotation() + "}";
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityType getType() {
/* 163 */     return EntityType.ITEM_FRAME;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftItemFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */