/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileEntityBed
/*    */   extends TileEntity
/*    */ {
/*    */   public EnumColor color;
/*    */   
/*    */   public TileEntityBed() {
/* 11 */     super(TileEntityTypes.BED);
/*    */   }
/*    */   
/*    */   public TileEntityBed(EnumColor var0) {
/* 15 */     this();
/* 16 */     a(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public PacketPlayOutTileEntityData getUpdatePacket() {
/* 21 */     return new PacketPlayOutTileEntityData(this.position, 11, b());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(EnumColor var0) {
/* 32 */     this.color = var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityBed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */