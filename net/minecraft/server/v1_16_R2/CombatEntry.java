/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CombatEntry
/*    */ {
/*    */   private final DamageSource a;
/*    */   private final int b;
/*    */   private final float c;
/*    */   private final float d;
/*    */   private final String e;
/*    */   private final float f;
/*    */   
/*    */   public CombatEntry(DamageSource var0, int var1, float var2, float var3, String var4, float var5) {
/* 18 */     this.a = var0;
/* 19 */     this.b = var1;
/* 20 */     this.c = var3;
/* 21 */     this.d = var2;
/* 22 */     this.e = var4;
/* 23 */     this.f = var5;
/*    */   }
/*    */   
/*    */   public DamageSource a() {
/* 27 */     return this.a;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float c() {
/* 35 */     return this.c;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean f() {
/* 47 */     return this.a.getEntity() instanceof EntityLiving;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public String g() {
/* 52 */     return this.e;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public IChatBaseComponent h() {
/* 57 */     return (a().getEntity() == null) ? null : a().getEntity().getScoreboardDisplayName();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float j() {
/* 66 */     if (this.a == DamageSource.OUT_OF_WORLD) {
/* 67 */       return Float.MAX_VALUE;
/*    */     }
/* 69 */     return this.f;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CombatEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */