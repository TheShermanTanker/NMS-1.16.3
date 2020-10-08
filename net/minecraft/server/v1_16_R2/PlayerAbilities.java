/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerAbilities
/*    */ {
/*    */   public boolean isInvulnerable;
/*    */   public boolean isFlying;
/*    */   public boolean canFly;
/*    */   public boolean canInstantlyBuild;
/*    */   public boolean mayBuild = true;
/* 12 */   public float flySpeed = 0.05F;
/* 13 */   public float walkSpeed = 0.1F;
/*    */   
/*    */   public void a(NBTTagCompound var0) {
/* 16 */     NBTTagCompound var1 = new NBTTagCompound();
/*    */     
/* 18 */     var1.setBoolean("invulnerable", this.isInvulnerable);
/* 19 */     var1.setBoolean("flying", this.isFlying);
/* 20 */     var1.setBoolean("mayfly", this.canFly);
/* 21 */     var1.setBoolean("instabuild", this.canInstantlyBuild);
/* 22 */     var1.setBoolean("mayBuild", this.mayBuild);
/* 23 */     var1.setFloat("flySpeed", this.flySpeed);
/* 24 */     var1.setFloat("walkSpeed", this.walkSpeed);
/* 25 */     var0.set("abilities", var1);
/*    */   }
/*    */   
/*    */   public void b(NBTTagCompound var0) {
/* 29 */     if (var0.hasKeyOfType("abilities", 10)) {
/* 30 */       NBTTagCompound var1 = var0.getCompound("abilities");
/*    */       
/* 32 */       this.isInvulnerable = var1.getBoolean("invulnerable");
/* 33 */       this.isFlying = var1.getBoolean("flying");
/* 34 */       this.canFly = var1.getBoolean("mayfly");
/* 35 */       this.canInstantlyBuild = var1.getBoolean("instabuild");
/*    */       
/* 37 */       if (var1.hasKeyOfType("flySpeed", 99)) {
/* 38 */         this.flySpeed = var1.getFloat("flySpeed");
/* 39 */         this.walkSpeed = var1.getFloat("walkSpeed");
/*    */       } 
/* 41 */       if (var1.hasKeyOfType("mayBuild", 1)) {
/* 42 */         this.mayBuild = var1.getBoolean("mayBuild");
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public float a() {
/* 48 */     return this.flySpeed;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float b() {
/* 56 */     return this.walkSpeed;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PlayerAbilities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */