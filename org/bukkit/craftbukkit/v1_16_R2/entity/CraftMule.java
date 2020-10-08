/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.EntityHorseChestedAbstract;
/*    */ import net.minecraft.server.v1_16_R2.EntityHorseMule;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Horse;
/*    */ import org.bukkit.entity.Mule;
/*    */ 
/*    */ public class CraftMule extends CraftChestedHorse implements Mule {
/*    */   public CraftMule(CraftServer server, EntityHorseMule entity) {
/* 12 */     super(server, (EntityHorseChestedAbstract)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 17 */     return "CraftMule";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 22 */     return EntityType.MULE;
/*    */   }
/*    */ 
/*    */   
/*    */   public Horse.Variant getVariant() {
/* 27 */     return Horse.Variant.MULE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftMule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */