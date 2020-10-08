/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class EntityAmbient
/*    */   extends EntityInsentient
/*    */ {
/*    */   protected EntityAmbient(EntityTypes<? extends EntityAmbient> var0, World var1) {
/* 10 */     super((EntityTypes)var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(EntityHuman var0) {
/* 15 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityAmbient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */