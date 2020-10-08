/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ public interface ICommandListener {
/*  7 */   public static final ICommandListener DUMMY = new ICommandListener()
/*    */     {
/*    */       public void sendMessage(IChatBaseComponent ichatbasecomponent, UUID uuid) {}
/*    */ 
/*    */       
/*    */       public boolean shouldSendSuccess() {
/* 13 */         return false;
/*    */       }
/*    */ 
/*    */       
/*    */       public boolean shouldSendFailure() {
/* 18 */         return false;
/*    */       }
/*    */ 
/*    */       
/*    */       public boolean shouldBroadcastCommands() {
/* 23 */         return false;
/*    */       }
/*    */ 
/*    */ 
/*    */       
/*    */       public CommandSender getBukkitSender(CommandListenerWrapper wrapper) {
/* 29 */         throw new UnsupportedOperationException("Not supported yet.");
/*    */       }
/*    */     };
/*    */   
/*    */   void sendMessage(IChatBaseComponent paramIChatBaseComponent, UUID paramUUID);
/*    */   
/*    */   boolean shouldSendSuccess();
/*    */   
/*    */   boolean shouldSendFailure();
/*    */   
/*    */   boolean shouldBroadcastCommands();
/*    */   
/*    */   CommandSender getBukkitSender(CommandListenerWrapper paramCommandListenerWrapper);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ICommandListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */