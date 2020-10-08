/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.doubles.DoubleList;
/*    */ 
/*    */ public class VoxelShapeSlice
/*    */   extends VoxelShape {
/*    */   private final VoxelShape b;
/*    */   private final EnumDirection.EnumAxis c;
/*  9 */   private static final DoubleList d = (DoubleList)new VoxelShapeCubePoint(1);
/*    */   
/*    */   public VoxelShapeSlice(VoxelShape var0, EnumDirection.EnumAxis var1, int var2) {
/* 12 */     super(a(var0.a, var1, var2));
/* 13 */     this.b = var0;
/* 14 */     this.c = var1;
/*    */   }
/*    */   
/*    */   private static VoxelShapeDiscrete a(VoxelShapeDiscrete var0, EnumDirection.EnumAxis var1, int var2) {
/* 18 */     return new VoxelShapeDiscreteSlice(var0, var1
/* 19 */         .a(var2, 0, 0), var1
/* 20 */         .a(0, var2, 0), var1
/* 21 */         .a(0, 0, var2), var1
/* 22 */         .a(var2 + 1, var0.a, var0.a), var1
/* 23 */         .a(var0.b, var2 + 1, var0.b), var1
/* 24 */         .a(var0.c, var0.c, var2 + 1));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected DoubleList a(EnumDirection.EnumAxis var0) {
/* 30 */     if (var0 == this.c) {
/* 31 */       return d;
/*    */     }
/* 33 */     return this.b.a(var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\VoxelShapeSlice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */