/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class BlockStainedGlassPane
/*    */   extends BlockIronBars
/*    */   implements IBeaconBeam {
/*    */   private final EnumColor color;
/*    */   
/*    */   public BlockStainedGlassPane(EnumColor var0, BlockBase.Info var1) {
/*  9 */     super(var1);
/* 10 */     this.color = var0;
/* 11 */     j(((IBlockData)this.blockStateList.getBlockData()).set(NORTH, Boolean.valueOf(false)).set(EAST, Boolean.valueOf(false)).set(SOUTH, Boolean.valueOf(false)).set(WEST, Boolean.valueOf(false)).set(e, Boolean.valueOf(false)));
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumColor a() {
/* 16 */     return this.color;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockStainedGlassPane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */