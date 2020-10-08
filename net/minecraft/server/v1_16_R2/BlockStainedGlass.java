/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class BlockStainedGlass
/*    */   extends BlockGlassAbstract
/*    */   implements IBeaconBeam {
/*    */   private final EnumColor color;
/*    */   
/*    */   public BlockStainedGlass(EnumColor var0, BlockBase.Info var1) {
/*  9 */     super(var1);
/* 10 */     this.color = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumColor a() {
/* 15 */     return this.color;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockStainedGlass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */