/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ public class MaterialPredicate
/*    */   implements Predicate<IBlockData>
/*    */ {
/* 10 */   private static final MaterialPredicate a = new MaterialPredicate(Material.AIR)
/*    */     {
/*    */       public boolean test(@Nullable IBlockData var0) {
/* 13 */         return (var0 != null && var0.isAir());
/*    */       }
/*    */     };
/*    */   
/*    */   private final Material b;
/*    */   
/*    */   private MaterialPredicate(Material var0) {
/* 20 */     this.b = var0;
/*    */   }
/*    */   
/*    */   public static MaterialPredicate a(Material var0) {
/* 24 */     return (var0 == Material.AIR) ? a : new MaterialPredicate(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean test(@Nullable IBlockData var0) {
/* 29 */     return (var0 != null && var0.getMaterial() == this.b);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MaterialPredicate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */