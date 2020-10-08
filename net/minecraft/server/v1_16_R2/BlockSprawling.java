/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.EnumMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockSprawling
/*    */   extends Block
/*    */ {
/* 18 */   private static final EnumDirection[] i = EnumDirection.values();
/*    */   
/* 20 */   public static final BlockStateBoolean a = BlockProperties.I;
/* 21 */   public static final BlockStateBoolean b = BlockProperties.J;
/* 22 */   public static final BlockStateBoolean c = BlockProperties.K;
/* 23 */   public static final BlockStateBoolean d = BlockProperties.L;
/* 24 */   public static final BlockStateBoolean e = BlockProperties.G;
/* 25 */   public static final BlockStateBoolean f = BlockProperties.H; public static final Map<EnumDirection, BlockStateBoolean> g;
/*    */   static {
/* 27 */     g = SystemUtils.<Map<EnumDirection, BlockStateBoolean>>a(Maps.newEnumMap(EnumDirection.class), var0 -> {
/*    */           var0.put(EnumDirection.NORTH, a);
/*    */           var0.put(EnumDirection.EAST, b);
/*    */           var0.put(EnumDirection.SOUTH, c);
/*    */           var0.put(EnumDirection.WEST, d);
/*    */           var0.put(EnumDirection.UP, e);
/*    */           var0.put(EnumDirection.DOWN, f);
/*    */         });
/*    */   }
/*    */   protected final VoxelShape[] h;
/*    */   
/*    */   protected BlockSprawling(float var0, BlockBase.Info var1) {
/* 39 */     super(var1);
/*    */     
/* 41 */     this.h = a(var0);
/*    */   }
/*    */   
/*    */   private VoxelShape[] a(float var0) {
/* 45 */     float var1 = 0.5F - var0;
/* 46 */     float var2 = 0.5F + var0;
/*    */     
/* 48 */     VoxelShape var3 = Block.a((var1 * 16.0F), (var1 * 16.0F), (var1 * 16.0F), (var2 * 16.0F), (var2 * 16.0F), (var2 * 16.0F));
/*    */     
/* 50 */     VoxelShape[] var4 = new VoxelShape[i.length];
/*    */     
/* 52 */     for (int i = 0; i < i.length; i++) {
/* 53 */       EnumDirection enumDirection = i[i];
/* 54 */       var4[i] = VoxelShapes.create(0.5D + 
/* 55 */           Math.min(-var0, enumDirection.getAdjacentX() * 0.5D), 0.5D + 
/* 56 */           Math.min(-var0, enumDirection.getAdjacentY() * 0.5D), 0.5D + 
/* 57 */           Math.min(-var0, enumDirection.getAdjacentZ() * 0.5D), 0.5D + 
/* 58 */           Math.max(var0, enumDirection.getAdjacentX() * 0.5D), 0.5D + 
/* 59 */           Math.max(var0, enumDirection.getAdjacentY() * 0.5D), 0.5D + 
/* 60 */           Math.max(var0, enumDirection.getAdjacentZ() * 0.5D));
/*    */     } 
/*    */ 
/*    */     
/* 64 */     VoxelShape[] var5 = new VoxelShape[64];
/* 65 */     for (int var6 = 0; var6 < 64; var6++) {
/* 66 */       VoxelShape var7 = var3;
/* 67 */       for (int var8 = 0; var8 < i.length; var8++) {
/* 68 */         if ((var6 & 1 << var8) != 0) {
/* 69 */           var7 = VoxelShapes.a(var7, var4[var8]);
/*    */         }
/*    */       } 
/* 72 */       var5[var6] = var7;
/*    */     } 
/* 74 */     return var5;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b(IBlockData var0, IBlockAccess var1, BlockPosition var2) {
/* 79 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 84 */     return this.h[h(var0)];
/*    */   }
/*    */   
/*    */   protected int h(IBlockData var0) {
/* 88 */     int var1 = 0;
/* 89 */     for (int var2 = 0; var2 < i.length; var2++) {
/* 90 */       if (((Boolean)var0.get(g.get(i[var2]))).booleanValue()) {
/* 91 */         var1 |= 1 << var2;
/*    */       }
/*    */     } 
/* 94 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockSprawling.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */