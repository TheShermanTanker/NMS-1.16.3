/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MemoryTarget
/*    */ {
/*    */   private final BehaviorPosition a;
/*    */   private final float b;
/*    */   private final int c;
/*    */   
/*    */   public MemoryTarget(BlockPosition var0, float var1, int var2) {
/* 16 */     this(new BehaviorTarget(var0), var1, var2);
/*    */   }
/*    */   
/*    */   public MemoryTarget(Vec3D var0, float var1, int var2) {
/* 20 */     this(new BehaviorTarget(new BlockPosition(var0)), var1, var2);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MemoryTarget(BehaviorPosition var0, float var1, int var2) {
/* 28 */     this.a = var0;
/* 29 */     this.b = var1;
/* 30 */     this.c = var2;
/*    */   }
/*    */   
/*    */   public BehaviorPosition a() {
/* 34 */     return this.a;
/*    */   }
/*    */   
/*    */   public float b() {
/* 38 */     return this.b;
/*    */   }
/*    */   
/*    */   public int c() {
/* 42 */     return this.c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MemoryTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */