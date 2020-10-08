/*    */ package org.bukkit.plugin.messaging;
/*    */ 
/*    */ import java.util.Set;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface Messenger
/*    */ {
/*    */   public static final int MAX_MESSAGE_SIZE = 32766;
/* 27 */   public static final int MAX_CHANNEL_SIZE = Integer.getInteger("paper.maxCustomChannelName", 64).intValue();
/*    */   
/*    */   boolean isReservedChannel(@NotNull String paramString);
/*    */   
/*    */   void registerOutgoingPluginChannel(@NotNull Plugin paramPlugin, @NotNull String paramString);
/*    */   
/*    */   void unregisterOutgoingPluginChannel(@NotNull Plugin paramPlugin, @NotNull String paramString);
/*    */   
/*    */   void unregisterOutgoingPluginChannel(@NotNull Plugin paramPlugin);
/*    */   
/*    */   @NotNull
/*    */   PluginMessageListenerRegistration registerIncomingPluginChannel(@NotNull Plugin paramPlugin, @NotNull String paramString, @NotNull PluginMessageListener paramPluginMessageListener);
/*    */   
/*    */   void unregisterIncomingPluginChannel(@NotNull Plugin paramPlugin, @NotNull String paramString, @NotNull PluginMessageListener paramPluginMessageListener);
/*    */   
/*    */   void unregisterIncomingPluginChannel(@NotNull Plugin paramPlugin, @NotNull String paramString);
/*    */   
/*    */   void unregisterIncomingPluginChannel(@NotNull Plugin paramPlugin);
/*    */   
/*    */   @NotNull
/*    */   Set<String> getOutgoingChannels();
/*    */   
/*    */   @NotNull
/*    */   Set<String> getOutgoingChannels(@NotNull Plugin paramPlugin);
/*    */   
/*    */   @NotNull
/*    */   Set<String> getIncomingChannels();
/*    */   
/*    */   @NotNull
/*    */   Set<String> getIncomingChannels(@NotNull Plugin paramPlugin);
/*    */   
/*    */   @NotNull
/*    */   Set<PluginMessageListenerRegistration> getIncomingChannelRegistrations(@NotNull Plugin paramPlugin);
/*    */   
/*    */   @NotNull
/*    */   Set<PluginMessageListenerRegistration> getIncomingChannelRegistrations(@NotNull String paramString);
/*    */   
/*    */   @NotNull
/*    */   Set<PluginMessageListenerRegistration> getIncomingChannelRegistrations(@NotNull Plugin paramPlugin, @NotNull String paramString);
/*    */   
/*    */   boolean isRegistrationValid(@NotNull PluginMessageListenerRegistration paramPluginMessageListenerRegistration);
/*    */   
/*    */   boolean isIncomingChannelRegistered(@NotNull Plugin paramPlugin, @NotNull String paramString);
/*    */   
/*    */   boolean isOutgoingChannelRegistered(@NotNull Plugin paramPlugin, @NotNull String paramString);
/*    */   
/*    */   void dispatchIncomingMessage(@NotNull Player paramPlayer, @NotNull String paramString, @NotNull byte[] paramArrayOfbyte);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\plugin\messaging\Messenger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */