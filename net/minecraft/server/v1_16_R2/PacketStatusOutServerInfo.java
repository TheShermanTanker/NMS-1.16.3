/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.GsonBuilder;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketStatusOutServerInfo
/*    */   implements Packet<PacketStatusOutListener>
/*    */ {
/* 15 */   private static final Gson a = (new GsonBuilder())
/* 16 */     .registerTypeAdapter(ServerPing.ServerData.class, new ServerPing.ServerData.Serializer())
/* 17 */     .registerTypeAdapter(ServerPing.ServerPingPlayerSample.class, new ServerPing.ServerPingPlayerSample.Serializer())
/* 18 */     .registerTypeAdapter(ServerPing.class, new ServerPing.Serializer())
/* 19 */     .registerTypeHierarchyAdapter(IChatBaseComponent.class, new IChatBaseComponent.ChatSerializer())
/* 20 */     .registerTypeHierarchyAdapter(ChatModifier.class, new ChatModifier.ChatModifierSerializer())
/* 21 */     .registerTypeAdapterFactory(new ChatTypeAdapterFactory())
/* 22 */     .create();
/*    */   
/*    */   private ServerPing b;
/*    */ 
/*    */   
/*    */   public PacketStatusOutServerInfo() {}
/*    */   
/*    */   public PacketStatusOutServerInfo(ServerPing var0) {
/* 30 */     this.b = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 35 */     this.b = ChatDeserializer.<ServerPing>a(a, var0.e(32767), ServerPing.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 40 */     var0.a(a.toJson(this.b));
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketStatusOutListener var0) {
/* 45 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketStatusOutServerInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */