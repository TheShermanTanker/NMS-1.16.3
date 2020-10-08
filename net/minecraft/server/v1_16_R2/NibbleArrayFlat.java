/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class NibbleArrayFlat
/*    */   extends NibbleArray {
/*    */   public NibbleArrayFlat() {
/*  6 */     super(128);
/*    */   }
/*    */   
/*    */   public NibbleArrayFlat(NibbleArray nibblearray, int i) {
/* 10 */     super(128);
/* 11 */     System.arraycopy(nibblearray.getIfSet(), i * 128, this.a, 0, 128);
/*    */   }
/*    */ 
/*    */   
/*    */   protected int b(int i, int j, int k) {
/* 16 */     return k << 4 | i;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] asBytes() {
/* 21 */     byte[] abyte = (byte[])BYTE_2048.acquire();
/*    */     
/* 23 */     for (int i = 0; i < 16; i++) {
/* 24 */       System.arraycopy(this.a, 0, abyte, i * 128, 128);
/*    */     }
/*    */     
/* 27 */     return abyte;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NibbleArrayFlat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */