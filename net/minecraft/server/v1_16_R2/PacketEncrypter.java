/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.MessageToByteEncoder;
/*    */ import javax.crypto.Cipher;
/*    */ 
/*    */ public class PacketEncrypter
/*    */   extends MessageToByteEncoder<ByteBuf> {
/*    */   private final PacketEncryptionHandler a;
/*    */   
/*    */   public PacketEncrypter(Cipher var0) {
/* 13 */     this.a = new PacketEncryptionHandler(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void encode(ChannelHandlerContext var0, ByteBuf var1, ByteBuf var2) throws Exception {
/* 18 */     this.a.a(var1, var2);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketEncrypter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */