/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class WorldGenScatteredPiece
/*    */   extends StructurePiece
/*    */ {
/*    */   protected final int a;
/*    */   protected final int b;
/*    */   protected final int c;
/* 17 */   protected int d = -1;
/*    */   
/*    */   protected WorldGenScatteredPiece(WorldGenFeatureStructurePieceType var0, Random var1, int var2, int var3, int var4, int var5, int var6, int var7) {
/* 20 */     super(var0, 0);
/*    */     
/* 22 */     this.a = var5;
/* 23 */     this.b = var6;
/* 24 */     this.c = var7;
/*    */     
/* 26 */     a(EnumDirection.EnumDirectionLimit.HORIZONTAL.a(var1));
/*    */     
/* 28 */     if (i().n() == EnumDirection.EnumAxis.Z) {
/* 29 */       this.n = new StructureBoundingBox(var2, var3, var4, var2 + var5 - 1, var3 + var6 - 1, var4 + var7 - 1);
/*    */     } else {
/* 31 */       this.n = new StructureBoundingBox(var2, var3, var4, var2 + var7 - 1, var3 + var6 - 1, var4 + var5 - 1);
/*    */     } 
/*    */   }
/*    */   
/*    */   protected WorldGenScatteredPiece(WorldGenFeatureStructurePieceType var0, NBTTagCompound var1) {
/* 36 */     super(var0, var1);
/* 37 */     this.a = var1.getInt("Width");
/* 38 */     this.b = var1.getInt("Height");
/* 39 */     this.c = var1.getInt("Depth");
/* 40 */     this.d = var1.getInt("HPos");
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(NBTTagCompound var0) {
/* 45 */     var0.setInt("Width", this.a);
/* 46 */     var0.setInt("Height", this.b);
/* 47 */     var0.setInt("Depth", this.c);
/* 48 */     var0.setInt("HPos", this.d);
/*    */   }
/*    */   
/*    */   protected boolean a(GeneratorAccess var0, StructureBoundingBox var1, int var2) {
/* 52 */     if (this.d >= 0) {
/* 53 */       return true;
/*    */     }
/*    */     
/* 56 */     int var3 = 0;
/* 57 */     int var4 = 0;
/* 58 */     BlockPosition.MutableBlockPosition var5 = new BlockPosition.MutableBlockPosition();
/* 59 */     for (int var6 = this.n.c; var6 <= this.n.f; var6++) {
/* 60 */       for (int var7 = this.n.a; var7 <= this.n.d; var7++) {
/* 61 */         var5.d(var7, 64, var6);
/* 62 */         if (var1.b(var5)) {
/* 63 */           var3 += var0.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, var5).getY();
/* 64 */           var4++;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 69 */     if (var4 == 0) {
/* 70 */       return false;
/*    */     }
/* 72 */     this.d = var3 / var4;
/* 73 */     this.n.a(0, this.d - this.n.b + var2, 0);
/* 74 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenScatteredPiece.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */