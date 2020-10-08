/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityGolem;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityShulker;
/*    */ import org.bukkit.DyeColor;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftShulker extends CraftGolem implements Shulker {
/*    */   public CraftShulker(CraftServer server, EntityShulker entity) {
/* 12 */     super(server, (EntityGolem)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 17 */     return "CraftShulker";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 22 */     return EntityType.SHULKER;
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityShulker getHandle() {
/* 27 */     return (EntityShulker)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public DyeColor getColor() {
/* 32 */     return DyeColor.getByWoolData(((Byte)getHandle().getDataWatcher().get(EntityShulker.COLOR)).byteValue());
/*    */   }
/*    */ 
/*    */   
/*    */   public void setColor(DyeColor color) {
/* 37 */     getHandle().getDataWatcher().set(EntityShulker.COLOR, Byte.valueOf((color == null) ? 16 : color.getWoolData()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftShulker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */