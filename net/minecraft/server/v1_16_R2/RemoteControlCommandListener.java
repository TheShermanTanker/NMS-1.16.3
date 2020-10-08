/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ public class RemoteControlCommandListener implements ICommandListener {
/*  7 */   private static final ChatComponentText b = new ChatComponentText("Rcon");
/*  8 */   private final StringBuffer buffer = new StringBuffer();
/*    */   private final MinecraftServer server;
/*    */   
/*    */   public RemoteControlCommandListener(MinecraftServer minecraftserver) {
/* 12 */     this.server = minecraftserver;
/*    */   }
/*    */   
/*    */   public void clearMessages() {
/* 16 */     this.buffer.setLength(0);
/*    */   }
/*    */   
/*    */   public String getMessages() {
/* 20 */     return this.buffer.toString();
/*    */   }
/*    */   
/*    */   public CommandListenerWrapper getWrapper() {
/* 24 */     WorldServer worldserver = this.server.E();
/*    */     
/* 26 */     return new CommandListenerWrapper(this, Vec3D.b(worldserver.getSpawn()), Vec2F.a, worldserver, 4, "Rcon", b, this.server, (Entity)null);
/*    */   }
/*    */ 
/*    */   
/*    */   public void sendMessage(String message) {
/* 31 */     this.buffer.append(message);
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandSender getBukkitSender(CommandListenerWrapper wrapper) {
/* 36 */     return (CommandSender)this.server.remoteConsole;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void sendMessage(IChatBaseComponent ichatbasecomponent, UUID uuid) {
/* 42 */     this.buffer.append(ichatbasecomponent.getString());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldSendSuccess() {
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldSendFailure() {
/* 52 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldBroadcastCommands() {
/* 57 */     return this.server.i();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RemoteControlCommandListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */