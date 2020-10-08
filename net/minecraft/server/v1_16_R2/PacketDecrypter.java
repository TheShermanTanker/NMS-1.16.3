/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.MessageToMessageDecoder;
/*    */ import java.util.List;
/*    */ import javax.crypto.Cipher;
/*    */ 
/*    */ public class PacketDecrypter
/*    */   extends MessageToMessageDecoder<ByteBuf> {
/*    */   private final PacketEncryptionHandler a;
/*    */   
/*    */   public PacketDecrypter(Cipher var0) {
/* 14 */     this.a = new PacketEncryptionHandler(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void decode(ChannelHandlerContext var0, ByteBuf var1, List<Object> var2) throws Exception {
/* 19 */     var2.add(this.a.a(var0, var1));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketDecrypter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */