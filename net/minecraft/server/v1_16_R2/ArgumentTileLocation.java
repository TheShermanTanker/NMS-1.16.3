/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Set;
/*    */ import java.util.function.Predicate;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArgumentTileLocation
/*    */   implements Predicate<ShapeDetectorBlock>
/*    */ {
/*    */   private final IBlockData a;
/*    */   private final Set<IBlockState<?>> b;
/*    */   @Nullable
/*    */   private final NBTTagCompound c;
/*    */   
/*    */   public ArgumentTileLocation(IBlockData var0, Set<IBlockState<?>> var1, @Nullable NBTTagCompound var2) {
/* 24 */     this.a = var0;
/* 25 */     this.b = var1;
/* 26 */     this.c = var2;
/*    */   }
/*    */   
/*    */   public IBlockData a() {
/* 30 */     return this.a;
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
/*    */ 
/*    */   
/*    */   public boolean test(ShapeDetectorBlock var0) {
/* 44 */     IBlockData var1 = var0.a();
/*    */     
/* 46 */     if (!var1.a(this.a.getBlock())) {
/* 47 */       return false;
/*    */     }
/*    */     
/* 50 */     for (IBlockState<?> var3 : this.b) {
/* 51 */       if (var1.get(var3) != this.a.get(var3)) {
/* 52 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 56 */     if (this.c != null) {
/* 57 */       TileEntity var2 = var0.b();
/* 58 */       return (var2 != null && GameProfileSerializer.a(this.c, var2.save(new NBTTagCompound()), true));
/*    */     } 
/*    */     
/* 61 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean a(WorldServer var0, BlockPosition var1, int var2) {
/* 69 */     IBlockData var3 = Block.b(this.a, var0, var1);
/* 70 */     if (var3.isAir()) {
/* 71 */       var3 = this.a;
/*    */     }
/* 73 */     if (!var0.setTypeAndData(var1, var3, var2)) {
/* 74 */       return false;
/*    */     }
/*    */     
/* 77 */     if (this.c != null) {
/* 78 */       TileEntity var4 = var0.getTileEntity(var1);
/* 79 */       if (var4 != null) {
/* 80 */         NBTTagCompound var5 = this.c.clone();
/* 81 */         var5.setInt("x", var1.getX());
/* 82 */         var5.setInt("y", var1.getY());
/* 83 */         var5.setInt("z", var1.getZ());
/* 84 */         var4.load(var3, var5);
/*    */       } 
/*    */     } 
/*    */     
/* 88 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentTileLocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */