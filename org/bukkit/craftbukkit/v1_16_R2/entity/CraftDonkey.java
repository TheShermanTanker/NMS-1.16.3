/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.EntityHorseChestedAbstract;
/*    */ import net.minecraft.server.v1_16_R2.EntityHorseDonkey;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.Donkey;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Horse;
/*    */ 
/*    */ public class CraftDonkey extends CraftChestedHorse implements Donkey {
/*    */   public CraftDonkey(CraftServer server, EntityHorseDonkey entity) {
/* 12 */     super(server, (EntityHorseChestedAbstract)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 17 */     return "CraftDonkey";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 22 */     return EntityType.DONKEY;
/*    */   }
/*    */ 
/*    */   
/*    */   public Horse.Variant getVariant() {
/* 27 */     return Horse.Variant.DONKEY;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftDonkey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */