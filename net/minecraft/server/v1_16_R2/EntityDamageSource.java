/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityDamageSource
/*    */   extends DamageSource
/*    */ {
/*    */   @Nullable
/*    */   protected final Entity w;
/*    */   private boolean x;
/*    */   
/*    */   public EntityDamageSource(String var0, @Nullable Entity var1) {
/* 19 */     super(var0);
/* 20 */     this.w = var1;
/*    */   }
/*    */   
/*    */   public EntityDamageSource x() {
/* 24 */     this.x = true;
/* 25 */     return this;
/*    */   }
/*    */   
/*    */   public boolean y() {
/* 29 */     return this.x;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Entity getEntity() {
/* 35 */     return this.w;
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatBaseComponent getLocalizedDeathMessage(EntityLiving var0) {
/* 40 */     ItemStack var1 = (this.w instanceof EntityLiving) ? ((EntityLiving)this.w).getItemInMainHand() : ItemStack.b;
/* 41 */     String var2 = "death.attack." + this.translationIndex;
/*    */     
/* 43 */     if (!var1.isEmpty() && var1.hasName()) {
/* 44 */       return new ChatMessage(var2 + ".item", new Object[] { var0.getScoreboardDisplayName(), this.w.getScoreboardDisplayName(), var1.C() });
/*    */     }
/* 46 */     return new ChatMessage(var2, new Object[] { var0.getScoreboardDisplayName(), this.w.getScoreboardDisplayName() });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean s() {
/* 52 */     return (this.w != null && this.w instanceof EntityLiving && !(this.w instanceof EntityHuman));
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Vec3D w() {
/* 58 */     return (this.w != null) ? this.w.getPositionVector() : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 63 */     return "EntityDamageSource (" + this.w + ")";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityDamageSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */