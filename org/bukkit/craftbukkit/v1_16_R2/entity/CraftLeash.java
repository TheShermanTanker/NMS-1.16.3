/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityHanging;
/*    */ import net.minecraft.server.v1_16_R2.EntityLeash;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftLeash extends CraftHanging implements LeashHitch {
/*    */   public CraftLeash(CraftServer server, EntityLeash entity) {
/* 10 */     super(server, (EntityHanging)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityLeash getHandle() {
/* 15 */     return (EntityLeash)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 20 */     return "CraftLeash";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 25 */     return EntityType.LEASH_HITCH;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftLeash.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */