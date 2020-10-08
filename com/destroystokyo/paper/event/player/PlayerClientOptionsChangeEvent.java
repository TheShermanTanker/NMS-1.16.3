/*    */ package com.destroystokyo.paper.event.player;
/*    */ 
/*    */ import com.destroystokyo.paper.ClientOption;
/*    */ import com.destroystokyo.paper.SkinParts;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.player.PlayerEvent;
/*    */ import org.bukkit.inventory.MainHand;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerClientOptionsChangeEvent
/*    */   extends PlayerEvent
/*    */ {
/* 19 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private final String locale;
/*    */   private final int viewDistance;
/*    */   private final ClientOption.ChatVisibility chatVisibility;
/*    */   private final boolean chatColors;
/*    */   private final SkinParts skinparts;
/*    */   private final MainHand mainHand;
/*    */   
/*    */   public PlayerClientOptionsChangeEvent(@NotNull Player player, @NotNull String locale, int viewDistance, @NotNull ClientOption.ChatVisibility chatVisibility, boolean chatColors, @NotNull SkinParts skinParts, @NotNull MainHand mainHand) {
/* 29 */     super(player);
/* 30 */     this.locale = locale;
/* 31 */     this.viewDistance = viewDistance;
/* 32 */     this.chatVisibility = chatVisibility;
/* 33 */     this.chatColors = chatColors;
/* 34 */     this.skinparts = skinParts;
/* 35 */     this.mainHand = mainHand;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public String getLocale() {
/* 40 */     return this.locale;
/*    */   }
/*    */   
/*    */   public boolean hasLocaleChanged() {
/* 44 */     return !this.locale.equals(this.player.getClientOption(ClientOption.LOCALE));
/*    */   }
/*    */   
/*    */   public int getViewDistance() {
/* 48 */     return this.viewDistance;
/*    */   }
/*    */   
/*    */   public boolean hasViewDistanceChanged() {
/* 52 */     return (this.viewDistance != ((Integer)this.player.getClientOption(ClientOption.VIEW_DISTANCE)).intValue());
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public ClientOption.ChatVisibility getChatVisibility() {
/* 57 */     return this.chatVisibility;
/*    */   }
/*    */   
/*    */   public boolean hasChatVisibilityChanged() {
/* 61 */     return (this.chatVisibility != this.player.getClientOption(ClientOption.CHAT_VISIBILITY));
/*    */   }
/*    */   
/*    */   public boolean hasChatColorsEnabled() {
/* 65 */     return this.chatColors;
/*    */   }
/*    */   
/*    */   public boolean hasChatColorsEnabledChanged() {
/* 69 */     return (this.chatColors != ((Boolean)this.player.getClientOption(ClientOption.CHAT_COLORS_ENABLED)).booleanValue());
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public SkinParts getSkinParts() {
/* 74 */     return this.skinparts;
/*    */   }
/*    */   
/*    */   public boolean hasSkinPartsChanged() {
/* 78 */     return (this.skinparts.getRaw() != ((SkinParts)this.player.getClientOption(ClientOption.SKIN_PARTS)).getRaw());
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public MainHand getMainHand() {
/* 83 */     return this.mainHand;
/*    */   }
/*    */   
/*    */   public boolean hasMainHandChanged() {
/* 87 */     return (this.mainHand != this.player.getClientOption(ClientOption.MAIN_HAND));
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 93 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 98 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\player\PlayerClientOptionsChangeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */