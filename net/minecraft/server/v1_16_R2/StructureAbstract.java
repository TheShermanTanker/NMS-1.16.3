/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class StructureAbstract<C extends WorldGenFeatureConfiguration>
/*    */   extends StructureStart<C>
/*    */ {
/*    */   public StructureAbstract(StructureGenerator<C> var0, int var1, int var2, StructureBoundingBox var3, int var4, long var5) {
/*  9 */     super(var0, var1, var2, var3, var4, var5);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void b() {
/* 14 */     super.b();
/*    */ 
/*    */     
/* 17 */     int var0 = 12;
/*    */     
/* 19 */     this.c.a -= 12;
/* 20 */     this.c.b -= 12;
/* 21 */     this.c.c -= 12;
/* 22 */     this.c.d += 12;
/* 23 */     this.c.e += 12;
/* 24 */     this.c.f += 12;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\StructureAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */