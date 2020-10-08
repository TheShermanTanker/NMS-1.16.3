/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.destroystokyo.paper.network.StandardPaperServerListPingEventImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PacketStatusListener
/*     */   implements PacketStatusInListener
/*     */ {
/*  14 */   private static final IChatBaseComponent a = new ChatMessage("multiplayer.status.request_handled");
/*     */   private final MinecraftServer minecraftServer;
/*     */   private final NetworkManager networkManager;
/*     */   private boolean d;
/*     */   
/*     */   public PacketStatusListener(MinecraftServer minecraftserver, NetworkManager networkmanager) {
/*  20 */     this.minecraftServer = minecraftserver;
/*  21 */     this.networkManager = networkmanager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IChatBaseComponent ichatbasecomponent) {}
/*     */ 
/*     */   
/*     */   public NetworkManager a() {
/*  29 */     return this.networkManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketStatusInStart packetstatusinstart) {
/*  34 */     if (this.d) {
/*  35 */       this.networkManager.close(a);
/*     */     } else {
/*  37 */       this.d = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 136 */       StandardPaperServerListPingEventImpl.processRequest(this.minecraftServer, this.networkManager);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(PacketStatusInPing packetstatusinping) {
/* 144 */     this.networkManager.sendPacket(new PacketStatusOutPong(packetstatusinping.b()));
/* 145 */     this.networkManager.close(a);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketStatusListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */