/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityInsentient;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntitySlime;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftSlime extends CraftMob implements Slime {
/*    */   public CraftSlime(CraftServer server, EntitySlime entity) {
/* 11 */     super(server, (EntityInsentient)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSize() {
/* 16 */     return getHandle().getSize();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSize(int size) {
/* 21 */     getHandle().setSize(size, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntitySlime getHandle() {
/* 26 */     return (EntitySlime)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 31 */     return "CraftSlime";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 36 */     return EntityType.SLIME;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canWander() {
/* 41 */     return getHandle().canWander();
/*    */   }
/*    */   
/*    */   public void setWander(boolean canWander) {
/* 45 */     getHandle().setWander(canWander);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftSlime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */