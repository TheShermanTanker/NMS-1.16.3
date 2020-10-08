/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class BlockChestAbstract<E extends TileEntity>
/*    */   extends BlockTileEntity
/*    */ {
/*    */   protected final Supplier<TileEntityTypes<? extends E>> a;
/*    */   
/*    */   protected BlockChestAbstract(BlockBase.Info var0, Supplier<TileEntityTypes<? extends E>> var1) {
/* 16 */     super(var0);
/* 17 */     this.a = var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockChestAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */