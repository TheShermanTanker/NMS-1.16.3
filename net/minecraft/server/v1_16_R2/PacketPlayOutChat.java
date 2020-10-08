/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.UUID;
/*    */ import net.md_5.bungee.api.chat.BaseComponent;
/*    */ import net.md_5.bungee.chat.ComponentSerializer;
/*    */ 
/*    */ public class PacketPlayOutChat
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private static final int MAX_LENGTH = 262144;
/*    */   private IChatBaseComponent a;
/*    */   public BaseComponent[] components;
/*    */   
/*    */   public PacketPlayOutChat(IChatBaseComponent ichatbasecomponent, ChatMessageType chatmessagetype, UUID uuid) {
/* 16 */     this.a = ichatbasecomponent;
/* 17 */     this.b = chatmessagetype;
/* 18 */     this.c = (uuid != null) ? uuid : SystemUtils.getNullUUID();
/*    */   }
/*    */   private ChatMessageType b; private UUID c;
/*    */   public PacketPlayOutChat() {}
/*    */   public void a(PacketDataSerializer packetdataserializer) throws IOException {
/* 23 */     this.a = packetdataserializer.h();
/* 24 */     this.b = ChatMessageType.a(packetdataserializer.readByte());
/* 25 */     this.c = packetdataserializer.k();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer packetdataserializer) throws IOException {
/* 31 */     if (this.components != null) {
/*    */ 
/*    */       
/* 34 */       if (this.components.length == 1) {
/* 35 */         packetdataserializer.a(ComponentSerializer.toString(this.components[0]), 262144);
/*    */       } else {
/* 37 */         packetdataserializer.a(ComponentSerializer.toString(this.components), 262144);
/*    */       } 
/*    */     } else {
/*    */       
/* 41 */       packetdataserializer.a(this.a);
/*    */     } 
/*    */     
/* 44 */     packetdataserializer.writeByte(this.b.a());
/* 45 */     packetdataserializer.a(this.c);
/*    */   }
/*    */   
/*    */   public void a(PacketListenerPlayOut packetlistenerplayout) {
/* 49 */     packetlistenerplayout.a(this);
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 53 */     return (this.b == ChatMessageType.SYSTEM || this.b == ChatMessageType.GAME_INFO);
/*    */   }
/*    */   
/*    */   public ChatMessageType d() {
/* 57 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 62 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutChat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */