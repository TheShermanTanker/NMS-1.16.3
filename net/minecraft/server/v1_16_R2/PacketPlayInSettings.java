/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayInSettings
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   public String locale;
/*    */   public int viewDistance;
/*    */   private EnumChatVisibility c;
/*    */   private boolean d;
/*    */   private int e;
/*    */   private EnumMainHand f;
/*    */   
/*    */   public void a(PacketDataSerializer packetdataserializer) throws IOException {
/* 18 */     this.locale = packetdataserializer.e(16);
/* 19 */     this.viewDistance = packetdataserializer.readByte();
/* 20 */     this.c = packetdataserializer.<EnumChatVisibility>a(EnumChatVisibility.class);
/* 21 */     this.d = packetdataserializer.readBoolean();
/* 22 */     this.e = packetdataserializer.readUnsignedByte();
/* 23 */     this.f = packetdataserializer.<EnumMainHand>a(EnumMainHand.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer packetdataserializer) throws IOException {
/* 28 */     packetdataserializer.a(this.locale);
/* 29 */     packetdataserializer.writeByte(this.viewDistance);
/* 30 */     packetdataserializer.a(this.c);
/* 31 */     packetdataserializer.writeBoolean(this.d);
/* 32 */     packetdataserializer.writeByte(this.e);
/* 33 */     packetdataserializer.a(this.f);
/*    */   }
/*    */   
/*    */   public void a(PacketListenerPlayIn packetlistenerplayin) {
/* 37 */     packetlistenerplayin.a(this);
/*    */   }
/*    */   public EnumChatVisibility getChatVisibility() {
/* 40 */     return d();
/*    */   } public EnumChatVisibility d() {
/* 42 */     return this.c;
/*    */   }
/*    */   public boolean hasChatColorsEnabled() {
/* 45 */     return e();
/*    */   } public boolean e() {
/* 47 */     return this.d;
/*    */   }
/*    */   public int getSkinParts() {
/* 50 */     return f();
/*    */   } public int f() {
/* 52 */     return this.e;
/*    */   }
/*    */   
/*    */   public EnumMainHand getMainHand() {
/* 56 */     return this.f;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */