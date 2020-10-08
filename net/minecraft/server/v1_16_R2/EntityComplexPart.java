/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityComplexPart
/*    */   extends Entity
/*    */ {
/*    */   public final EntityEnderDragon owner;
/*    */   public final String c;
/*    */   private final EntitySize d;
/*    */   
/*    */   public EntityComplexPart(EntityEnderDragon var0, String var1, float var2, float var3) {
/* 18 */     super(var0.getEntityType(), var0.world);
/* 19 */     this.d = EntitySize.b(var2, var3);
/* 20 */     updateSize();
/* 21 */     this.owner = var0;
/* 22 */     this.c = var1;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void initDatawatcher() {}
/*    */ 
/*    */ 
/*    */   
/*    */   protected void loadData(NBTTagCompound var0) {}
/*    */ 
/*    */ 
/*    */   
/*    */   protected void saveData(NBTTagCompound var0) {}
/*    */ 
/*    */   
/*    */   public boolean isInteractable() {
/* 39 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean damageEntity(DamageSource var0, float var1) {
/* 44 */     if (isInvulnerable(var0)) {
/* 45 */       return false;
/*    */     }
/* 47 */     return this.owner.a(this, var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean s(Entity var0) {
/* 52 */     return (this == var0 || this.owner == var0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Packet<?> P() {
/* 58 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */   
/*    */   public EntitySize a(EntityPose var0) {
/* 63 */     return this.d;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityComplexPart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */