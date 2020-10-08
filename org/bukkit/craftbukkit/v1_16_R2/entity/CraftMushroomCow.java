/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityCow;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityMushroomCow;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.MushroomCow;
/*    */ 
/*    */ public class CraftMushroomCow extends CraftCow implements MushroomCow {
/*    */   public CraftMushroomCow(CraftServer server, EntityMushroomCow entity) {
/* 11 */     super(server, (EntityCow)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityMushroomCow getHandle() {
/* 16 */     return (EntityMushroomCow)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public MushroomCow.Variant getVariant() {
/* 21 */     return MushroomCow.Variant.values()[getHandle().getVariant().ordinal()];
/*    */   }
/*    */ 
/*    */   
/*    */   public void setVariant(MushroomCow.Variant variant) {
/* 26 */     Preconditions.checkArgument((variant != null), "variant");
/*    */     
/* 28 */     getHandle().setVariant(EntityMushroomCow.Type.values()[variant.ordinal()]);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 33 */     return "CraftMushroomCow";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 38 */     return EntityType.MUSHROOM_COW;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftMushroomCow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */