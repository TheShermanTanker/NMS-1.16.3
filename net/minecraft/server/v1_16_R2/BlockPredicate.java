/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ public class BlockPredicate
/*    */   implements Predicate<IBlockData>
/*    */ {
/*    */   private final Block a;
/*    */   
/*    */   public BlockPredicate(Block var0) {
/* 13 */     this.a = var0;
/*    */   }
/*    */   
/*    */   public static BlockPredicate a(Block var0) {
/* 17 */     return new BlockPredicate(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean test(@Nullable IBlockData var0) {
/* 22 */     return (var0 != null && var0.a(this.a));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPredicate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */