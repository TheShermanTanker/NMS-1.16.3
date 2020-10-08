/*    */ package com.destroystokyo.paper;
/*    */ 
/*    */ import org.bukkit.inventory.MainHand;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public final class ClientOption<T>
/*    */ {
/*  9 */   public static final ClientOption<SkinParts> SKIN_PARTS = new ClientOption((Class)SkinParts.class);
/* 10 */   public static final ClientOption<Boolean> CHAT_COLORS_ENABLED = new ClientOption((Class)Boolean.class);
/* 11 */   public static final ClientOption<ChatVisibility> CHAT_VISIBILITY = new ClientOption((Class)ChatVisibility.class);
/* 12 */   public static final ClientOption<String> LOCALE = new ClientOption((Class)String.class);
/* 13 */   public static final ClientOption<MainHand> MAIN_HAND = new ClientOption((Class)MainHand.class);
/* 14 */   public static final ClientOption<Integer> VIEW_DISTANCE = new ClientOption((Class)Integer.class);
/*    */   
/*    */   private final Class<T> type;
/*    */   
/*    */   private ClientOption(@NotNull Class<T> type) {
/* 19 */     this.type = type;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public Class<T> getType() {
/* 24 */     return this.type;
/*    */   }
/*    */   
/*    */   public enum ChatVisibility {
/* 28 */     FULL,
/* 29 */     SYSTEM,
/* 30 */     HIDDEN,
/* 31 */     UNKNOWN;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\ClientOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */