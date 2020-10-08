/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutCustomPayload
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/* 14 */   public static final MinecraftKey a = new MinecraftKey("brand");
/* 15 */   public static final MinecraftKey b = new MinecraftKey("debug/path");
/* 16 */   public static final MinecraftKey c = new MinecraftKey("debug/neighbors_update");
/* 17 */   public static final MinecraftKey d = new MinecraftKey("debug/caves");
/* 18 */   public static final MinecraftKey e = new MinecraftKey("debug/structures");
/* 19 */   public static final MinecraftKey f = new MinecraftKey("debug/worldgen_attempt");
/* 20 */   public static final MinecraftKey g = new MinecraftKey("debug/poi_ticket_count");
/* 21 */   public static final MinecraftKey h = new MinecraftKey("debug/poi_added");
/* 22 */   public static final MinecraftKey i = new MinecraftKey("debug/poi_removed");
/* 23 */   public static final MinecraftKey j = new MinecraftKey("debug/village_sections");
/* 24 */   public static final MinecraftKey k = new MinecraftKey("debug/goal_selector");
/* 25 */   public static final MinecraftKey l = new MinecraftKey("debug/brain");
/* 26 */   public static final MinecraftKey m = new MinecraftKey("debug/bee");
/* 27 */   public static final MinecraftKey n = new MinecraftKey("debug/hive");
/* 28 */   public static final MinecraftKey o = new MinecraftKey("debug/game_test_add_marker");
/* 29 */   public static final MinecraftKey p = new MinecraftKey("debug/game_test_clear");
/* 30 */   public static final MinecraftKey q = new MinecraftKey("debug/raids");
/*    */   
/*    */   private MinecraftKey r;
/*    */   
/*    */   private PacketDataSerializer s;
/*    */   
/*    */   public PacketPlayOutCustomPayload() {}
/*    */   
/*    */   public PacketPlayOutCustomPayload(MinecraftKey var0, PacketDataSerializer var1) {
/* 39 */     this.r = var0;
/* 40 */     this.s = var1;
/*    */     
/* 42 */     if (var1.writerIndex() > 1048576) {
/* 43 */       throw new IllegalArgumentException("Payload may not be larger than 1048576 bytes");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 49 */     this.r = var0.p();
/* 50 */     int var1 = var0.readableBytes();
/* 51 */     if (var1 < 0 || var1 > 1048576) {
/* 52 */       throw new IOException("Payload may not be larger than 1048576 bytes");
/*    */     }
/* 54 */     this.s = new PacketDataSerializer(var0.readBytes(var1));
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 59 */     var0.a(this.r);
/* 60 */     var0.writeBytes(this.s.copy());
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 65 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutCustomPayload.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */