/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class EntityPerchable
/*    */   extends EntityTameableAnimal
/*    */ {
/*    */   private int bq;
/*    */   
/*    */   protected EntityPerchable(EntityTypes<? extends EntityPerchable> var0, World var1) {
/* 15 */     super((EntityTypes)var0, var1);
/*    */   }
/*    */   
/*    */   public boolean d(EntityPlayer var0) {
/* 19 */     NBTTagCompound var1 = new NBTTagCompound();
/* 20 */     var1.setString("id", getSaveID());
/* 21 */     save(var1);
/*    */     
/* 23 */     if (var0.g(var1)) {
/* 24 */       die();
/* 25 */       return true;
/*    */     } 
/*    */     
/* 28 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick() {
/* 33 */     this.bq++;
/* 34 */     super.tick();
/*    */   }
/*    */   
/*    */   public boolean eY() {
/* 38 */     return (this.bq > 100);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityPerchable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */