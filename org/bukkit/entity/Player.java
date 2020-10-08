/*      */ package org.bukkit.entity;
/*      */ 
/*      */ import com.destroystokyo.paper.ClientOption;
/*      */ import com.destroystokyo.paper.Title;
/*      */ import com.destroystokyo.paper.network.NetworkClient;
/*      */ import com.destroystokyo.paper.profile.PlayerProfile;
/*      */ import java.net.InetSocketAddress;
/*      */ import java.util.Date;
/*      */ import java.util.Set;
/*      */ import net.md_5.bungee.api.ChatMessageType;
/*      */ import net.md_5.bungee.api.chat.BaseComponent;
/*      */ import org.bukkit.BanEntry;
/*      */ import org.bukkit.BanList;
/*      */ import org.bukkit.Bukkit;
/*      */ import org.bukkit.DyeColor;
/*      */ import org.bukkit.Effect;
/*      */ import org.bukkit.Instrument;
/*      */ import org.bukkit.Location;
/*      */ import org.bukkit.Material;
/*      */ import org.bukkit.Note;
/*      */ import org.bukkit.OfflinePlayer;
/*      */ import org.bukkit.Particle;
/*      */ import org.bukkit.Sound;
/*      */ import org.bukkit.SoundCategory;
/*      */ import org.bukkit.WeatherType;
/*      */ import org.bukkit.advancement.Advancement;
/*      */ import org.bukkit.advancement.AdvancementProgress;
/*      */ import org.bukkit.block.data.BlockData;
/*      */ import org.bukkit.command.CommandSender;
/*      */ import org.bukkit.conversations.Conversable;
/*      */ import org.bukkit.event.player.PlayerResourcePackStatusEvent;
/*      */ import org.bukkit.inventory.ItemStack;
/*      */ import org.bukkit.map.MapView;
/*      */ import org.bukkit.plugin.Plugin;
/*      */ import org.bukkit.plugin.messaging.PluginMessageRecipient;
/*      */ import org.bukkit.scoreboard.Scoreboard;
/*      */ import org.jetbrains.annotations.NotNull;
/*      */ import org.jetbrains.annotations.Nullable;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public interface Player
/*      */   extends HumanEntity, Conversable, OfflinePlayer, PluginMessageRecipient, NetworkClient
/*      */ {
/*      */   @Nullable
/*      */   default BanEntry banPlayerFull(@Nullable String reason) {
/*  501 */     return banPlayerFull(reason, (Date)null, (String)null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   default BanEntry banPlayerFull(@Nullable String reason, @Nullable String source) {
/*  513 */     return banPlayerFull(reason, (Date)null, source);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   default BanEntry banPlayerFull(@Nullable String reason, @Nullable Date expires) {
/*  525 */     return banPlayerFull(reason, expires, (String)null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   default BanEntry banPlayerFull(@Nullable String reason, @Nullable Date expires, @Nullable String source) {
/*  538 */     banPlayer(reason, expires, source);
/*  539 */     return banPlayerIP(reason, expires, source, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   default BanEntry banPlayerIP(@Nullable String reason, boolean kickPlayer) {
/*  552 */     return banPlayerIP(reason, (Date)null, (String)null, kickPlayer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   default BanEntry banPlayerIP(@Nullable String reason, @Nullable String source, boolean kickPlayer) {
/*  565 */     return banPlayerIP(reason, (Date)null, source, kickPlayer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   default BanEntry banPlayerIP(@Nullable String reason, @Nullable Date expires, boolean kickPlayer) {
/*  578 */     return banPlayerIP(reason, expires, (String)null, kickPlayer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   default BanEntry banPlayerIP(@Nullable String reason) {
/*  590 */     return banPlayerIP(reason, (Date)null, (String)null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   default BanEntry banPlayerIP(@Nullable String reason, @Nullable String source) {
/*  602 */     return banPlayerIP(reason, (Date)null, source);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   default BanEntry banPlayerIP(@Nullable String reason, @Nullable Date expires) {
/*  614 */     return banPlayerIP(reason, expires, (String)null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   default BanEntry banPlayerIP(@Nullable String reason, @Nullable Date expires, @Nullable String source) {
/*  627 */     return banPlayerIP(reason, expires, source, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   default BanEntry banPlayerIP(@Nullable String reason, @Nullable Date expires, @Nullable String source, boolean kickPlayer) {
/*  641 */     BanEntry banEntry = Bukkit.getServer().getBanList(BanList.Type.IP).addBan(getAddress().getAddress().getHostAddress(), reason, expires, source);
/*  642 */     if (kickPlayer && isOnline()) {
/*  643 */       getPlayer().kickPlayer(reason);
/*      */     }
/*      */     
/*  646 */     return banEntry;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   default void sendMessage(@NotNull BaseComponent component) {
/*  682 */     spigot().sendMessage(component);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void sendMessage(@NotNull BaseComponent... components) {
/*  692 */     spigot().sendMessage(components);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   void sendMessage(ChatMessageType position, BaseComponent... components) {
/*  704 */     spigot().sendMessage(position, components);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   default void giveExp(int amount) {
/*  909 */     giveExp(amount, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   String getDisplayName();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setDisplayName(@Nullable String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   String getPlayerListName();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setPlayerListName(@Nullable String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   String getPlayerListHeader();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   String getPlayerListFooter();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setPlayerListHeader(@Nullable String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setPlayerListFooter(@Nullable String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setPlayerListHeaderFooter(@Nullable String paramString1, @Nullable String paramString2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setCompassTarget(@NotNull Location paramLocation);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Location getCompassTarget();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   InetSocketAddress getAddress();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void sendRawMessage(@NotNull String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void kickPlayer(@Nullable String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void chat(@NotNull String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean performCommand(@NotNull String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   boolean isOnGround();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isSneaking();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setSneaking(boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isSprinting();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setSprinting(boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void saveData();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void loadData();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setSleepingIgnored(boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isSleepingIgnored();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   Location getBedSpawnLocation();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setBedSpawnLocation(@Nullable Location paramLocation);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setBedSpawnLocation(@Nullable Location paramLocation, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   void playNote(@NotNull Location paramLocation, byte paramByte1, byte paramByte2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void playNote(@NotNull Location paramLocation, @NotNull Instrument paramInstrument, @NotNull Note paramNote);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void playSound(@NotNull Location paramLocation, @NotNull Sound paramSound, float paramFloat1, float paramFloat2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void playSound(@NotNull Location paramLocation, @NotNull String paramString, float paramFloat1, float paramFloat2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void playSound(@NotNull Location paramLocation, @NotNull Sound paramSound, @NotNull SoundCategory paramSoundCategory, float paramFloat1, float paramFloat2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void playSound(@NotNull Location paramLocation, @NotNull String paramString, @NotNull SoundCategory paramSoundCategory, float paramFloat1, float paramFloat2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void stopSound(@NotNull Sound paramSound);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void stopSound(@NotNull String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void stopSound(@NotNull Sound paramSound, @Nullable SoundCategory paramSoundCategory);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void stopSound(@NotNull String paramString, @Nullable SoundCategory paramSoundCategory);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   void playEffect(@NotNull Location paramLocation, @NotNull Effect paramEffect, int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   <T> void playEffect(@NotNull Location paramLocation, @NotNull Effect paramEffect, @Nullable T paramT);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   void sendBlockChange(@NotNull Location paramLocation, @NotNull Material paramMaterial, byte paramByte);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void sendBlockChange(@NotNull Location paramLocation, @NotNull BlockData paramBlockData);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   boolean sendChunkChange(@NotNull Location paramLocation, int paramInt1, int paramInt2, int paramInt3, @NotNull byte[] paramArrayOfbyte);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void sendSignChange(@NotNull Location paramLocation, @Nullable String[] paramArrayOfString) throws IllegalArgumentException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void sendSignChange(@NotNull Location paramLocation, @Nullable String[] paramArrayOfString, @NotNull DyeColor paramDyeColor) throws IllegalArgumentException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void sendMap(@NotNull MapView paramMapView);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void sendActionBar(@NotNull String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void sendActionBar(char paramChar, @NotNull String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void sendActionBar(@NotNull BaseComponent... paramVarArgs);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setPlayerListHeaderFooter(@Nullable BaseComponent[] paramArrayOfBaseComponent1, @Nullable BaseComponent[] paramArrayOfBaseComponent2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setPlayerListHeaderFooter(@Nullable BaseComponent paramBaseComponent1, @Nullable BaseComponent paramBaseComponent2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   void setTitleTimes(int paramInt1, int paramInt2, int paramInt3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   void setSubtitle(BaseComponent[] paramArrayOfBaseComponent);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   void setSubtitle(BaseComponent paramBaseComponent);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   void showTitle(@Nullable BaseComponent[] paramArrayOfBaseComponent);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   void showTitle(@Nullable BaseComponent paramBaseComponent);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   void showTitle(@Nullable BaseComponent[] paramArrayOfBaseComponent1, @Nullable BaseComponent[] paramArrayOfBaseComponent2, int paramInt1, int paramInt2, int paramInt3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   void showTitle(@Nullable BaseComponent paramBaseComponent1, @Nullable BaseComponent paramBaseComponent2, int paramInt1, int paramInt2, int paramInt3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void sendTitle(@NotNull Title paramTitle);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTitle(@NotNull Title paramTitle);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void hideTitle();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateInventory();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setPlayerTime(long paramLong, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   long getPlayerTime();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   long getPlayerTimeOffset();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isPlayerTimeRelative();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void resetPlayerTime();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setPlayerWeather(@NotNull WeatherType paramWeatherType);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   WeatherType getPlayerWeather();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void resetPlayerWeather();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void giveExp(int paramInt, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int applyMending(int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void giveExpLevels(int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   float getExp();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setExp(float paramFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getLevel();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setLevel(int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getTotalExperience();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setTotalExperience(int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void sendExperienceChange(float paramFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void sendExperienceChange(float paramFloat, int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   float getExhaustion();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setExhaustion(float paramFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   float getSaturation();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setSaturation(float paramFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getFoodLevel();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setFoodLevel(int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean getAllowFlight();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setAllowFlight(boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   void hidePlayer(@NotNull Player paramPlayer);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void hidePlayer(@NotNull Plugin paramPlugin, @NotNull Player paramPlayer);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   void showPlayer(@NotNull Player paramPlayer);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void showPlayer(@NotNull Plugin paramPlugin, @NotNull Player paramPlayer);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean canSee(@NotNull Player paramPlayer);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isFlying();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setFlying(boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setFlySpeed(float paramFloat) throws IllegalArgumentException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setWalkSpeed(float paramFloat) throws IllegalArgumentException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   float getFlySpeed();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   float getWalkSpeed();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   void setTexturePack(@NotNull String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   void setResourcePack(@NotNull String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setResourcePack(@NotNull String paramString, @NotNull byte[] paramArrayOfbyte);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Scoreboard getScoreboard();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setScoreboard(@NotNull Scoreboard paramScoreboard) throws IllegalArgumentException, IllegalStateException;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isHealthScaled();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setHealthScaled(boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setHealthScale(double paramDouble) throws IllegalArgumentException;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   double getHealthScale();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   Entity getSpectatorTarget();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setSpectatorTarget(@Nullable Entity paramEntity);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   void sendTitle(@Nullable String paramString1, @Nullable String paramString2);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void sendTitle(@Nullable String paramString1, @Nullable String paramString2, int paramInt1, int paramInt2, int paramInt3);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void resetTitle();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void spawnParticle(@NotNull Particle paramParticle, @NotNull Location paramLocation, int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void spawnParticle(@NotNull Particle paramParticle, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   <T> void spawnParticle(@NotNull Particle paramParticle, @NotNull Location paramLocation, int paramInt, @Nullable T paramT);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   <T> void spawnParticle(@NotNull Particle paramParticle, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt, @Nullable T paramT);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void spawnParticle(@NotNull Particle paramParticle, @NotNull Location paramLocation, int paramInt, double paramDouble1, double paramDouble2, double paramDouble3);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void spawnParticle(@NotNull Particle paramParticle, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt, double paramDouble4, double paramDouble5, double paramDouble6);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   <T> void spawnParticle(@NotNull Particle paramParticle, @NotNull Location paramLocation, int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, @Nullable T paramT);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   <T> void spawnParticle(@NotNull Particle paramParticle, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt, double paramDouble4, double paramDouble5, double paramDouble6, @Nullable T paramT);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void spawnParticle(@NotNull Particle paramParticle, @NotNull Location paramLocation, int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void spawnParticle(@NotNull Particle paramParticle, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   <T> void spawnParticle(@NotNull Particle paramParticle, @NotNull Location paramLocation, int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, @Nullable T paramT);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   <T> void spawnParticle(@NotNull Particle paramParticle, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7, @Nullable T paramT);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   AdvancementProgress getAdvancementProgress(@NotNull Advancement paramAdvancement);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getClientViewDistance();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   String getLocale();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean getAffectsSpawning();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setAffectsSpawning(boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getViewDistance();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setViewDistance(int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateCommands();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void openBook(@NotNull ItemStack paramItemStack);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setResourcePack(@NotNull String paramString1, @NotNull String paramString2);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   PlayerResourcePackStatusEvent.Status getResourcePackStatus();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   @Nullable
/*      */   String getResourcePackHash();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean hasResourcePack();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   PlayerProfile getPlayerProfile();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setPlayerProfile(@NotNull PlayerProfile paramPlayerProfile);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   float getCooldownPeriod();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   float getCooledAttackStrength(float paramFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void resetCooldown();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   <T> T getClientOption(@NotNull ClientOption<T> paramClientOption);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   String getClientBrandName();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Spigot spigot();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class Spigot
/*      */     extends Entity.Spigot
/*      */   {
/*      */     @NotNull
/*      */     public InetSocketAddress getRawAddress() {
/* 1793 */       throw new UnsupportedOperationException("Not supported yet.");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     @Deprecated
/*      */     public boolean getCollidesWithEntities() {
/* 1804 */       throw new UnsupportedOperationException("Not supported yet.");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     @Deprecated
/*      */     public void setCollidesWithEntities(boolean collides) {
/* 1816 */       throw new UnsupportedOperationException("Not supported yet.");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void respawn() {
/* 1823 */       throw new UnsupportedOperationException("Not supported yet.");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     @NotNull
/*      */     public Set<Player> getHiddenPlayers() {
/* 1833 */       throw new UnsupportedOperationException("Not supported yet.");
/*      */     }
/*      */ 
/*      */     
/*      */     public void sendMessage(@NotNull BaseComponent component) {
/* 1838 */       throw new UnsupportedOperationException("Not supported yet.");
/*      */     }
/*      */ 
/*      */     
/*      */     public void sendMessage(@NotNull BaseComponent... components) {
/* 1843 */       throw new UnsupportedOperationException("Not supported yet.");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     @Deprecated
/*      */     public void sendMessage(@NotNull ChatMessageType position, @NotNull BaseComponent component) {
/* 1855 */       throw new UnsupportedOperationException("Not supported yet.");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     @Deprecated
/*      */     public void sendMessage(@NotNull ChatMessageType position, @NotNull BaseComponent... components) {
/* 1867 */       throw new UnsupportedOperationException("Not supported yet.");
/*      */     }
/*      */ 
/*      */     
/*      */     public int getPing() {
/* 1872 */       throw new UnsupportedOperationException("Not supported yet.");
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Player.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */