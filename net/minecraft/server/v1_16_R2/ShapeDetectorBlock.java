/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ShapeDetectorBlock
/*    */ {
/*    */   private final IWorldReader a;
/*    */   private final BlockPosition b;
/*    */   private final boolean c;
/*    */   private IBlockData d;
/*    */   private TileEntity e;
/*    */   private boolean f;
/*    */   
/*    */   public ShapeDetectorBlock(IWorldReader var0, BlockPosition var1, boolean var2) {
/* 20 */     this.a = var0;
/* 21 */     this.b = var1.immutableCopy();
/* 22 */     this.c = var2;
/*    */   }
/*    */   
/*    */   public IBlockData a() {
/* 26 */     if (this.d == null && (this.c || this.a.isLoaded(this.b))) {
/* 27 */       this.d = this.a.getType(this.b);
/*    */     }
/*    */     
/* 30 */     return this.d;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public TileEntity b() {
/* 35 */     if (this.e == null && !this.f) {
/* 36 */       this.e = this.a.getTileEntity(this.b);
/* 37 */       this.f = true;
/*    */     } 
/*    */     
/* 40 */     return this.e;
/*    */   }
/*    */   
/*    */   public IWorldReader c() {
/* 44 */     return this.a;
/*    */   }
/*    */   
/*    */   public BlockPosition getPosition() {
/* 48 */     return this.b;
/*    */   }
/*    */   
/*    */   public static Predicate<ShapeDetectorBlock> a(Predicate<IBlockData> var0) {
/* 52 */     return var1 -> (var1 != null && var0.test(var1.a()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ShapeDetectorBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */