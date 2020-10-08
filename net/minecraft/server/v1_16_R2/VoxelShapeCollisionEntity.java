/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.Predicate;
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
/*    */ public class VoxelShapeCollisionEntity
/*    */   implements VoxelShapeCollision
/*    */ {
/* 17 */   protected static final VoxelShapeCollision a = new VoxelShapeCollisionEntity(false, -1.7976931348623157E308D, Items.AIR, var0 -> false)
/*    */     {
/*    */       public boolean a(VoxelShape var0, BlockPosition var1, boolean var2) {
/* 20 */         return var2;
/*    */       }
/*    */     };
/*    */   
/*    */   private final boolean b;
/*    */   private final double c;
/*    */   private final Item d;
/*    */   private final Predicate<FluidType> e;
/*    */   
/*    */   protected VoxelShapeCollisionEntity(boolean var0, double var1, Item var3, Predicate<FluidType> var4) {
/* 30 */     this.b = var0;
/* 31 */     this.c = var1;
/* 32 */     this.d = var3;
/* 33 */     this.e = var4;
/*    */   }
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   protected VoxelShapeCollisionEntity(Entity var0) {
/* 39 */     this(var0
/* 40 */         .bx(), var0
/* 41 */         .locY(), (var0 instanceof EntityLiving) ? ((EntityLiving)var0)
/* 42 */         .getItemInMainHand().getItem() : Items.AIR, (var0 instanceof EntityLiving) ? (EntityLiving)var0::a : (var0 -> false));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean a(Item var0) {
/* 49 */     return (this.d == var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(Fluid var0, FluidTypeFlowing var1) {
/* 54 */     return (this.e.test(var1) && !var0.getType().a(var1));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/* 59 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(VoxelShape var0, BlockPosition var1, boolean var2) {
/* 64 */     return (this.c > var1.getY() + var0.c(EnumDirection.EnumAxis.Y) - 9.999999747378752E-6D);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\VoxelShapeCollisionEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */