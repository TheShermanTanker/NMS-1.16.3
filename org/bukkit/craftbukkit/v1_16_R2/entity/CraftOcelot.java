/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityAnimal;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityOcelot;
/*    */ import org.bukkit.entity.Ocelot;
/*    */ 
/*    */ public class CraftOcelot extends CraftAnimals implements Ocelot {
/*    */   public CraftOcelot(CraftServer server, EntityOcelot ocelot) {
/* 10 */     super(server, (EntityAnimal)ocelot);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityOcelot getHandle() {
/* 15 */     return (EntityOcelot)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public Ocelot.Type getCatType() {
/* 20 */     return Ocelot.Type.WILD_OCELOT;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCatType(Ocelot.Type type) {
/* 25 */     throw new UnsupportedOperationException("Cats are now a different entity!");
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 30 */     return "CraftOcelot";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 35 */     return EntityType.OCELOT;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftOcelot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */