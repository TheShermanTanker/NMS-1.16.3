/*     */ package org.bukkit.conversations;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
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
/*     */ public class ConversationFactory
/*     */ {
/*     */   protected Plugin plugin;
/*     */   protected boolean isModal;
/*     */   protected boolean localEchoEnabled;
/*     */   protected ConversationPrefix prefix;
/*     */   protected Prompt firstPrompt;
/*     */   protected Map<Object, Object> initialSessionData;
/*     */   protected String playerOnlyMessage;
/*     */   protected List<ConversationCanceller> cancellers;
/*     */   protected List<ConversationAbandonedListener> abandonedListeners;
/*     */   
/*     */   public ConversationFactory(@NotNull Plugin plugin) {
/*  40 */     this.plugin = plugin;
/*  41 */     this.isModal = true;
/*  42 */     this.localEchoEnabled = true;
/*  43 */     this.prefix = new NullConversationPrefix();
/*  44 */     this.firstPrompt = Prompt.END_OF_CONVERSATION;
/*  45 */     this.initialSessionData = new HashMap<>();
/*  46 */     this.playerOnlyMessage = null;
/*  47 */     this.cancellers = new ArrayList<>();
/*  48 */     this.abandonedListeners = new ArrayList<>();
/*     */   }
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
/*     */   @NotNull
/*     */   public ConversationFactory withModality(boolean modal) {
/*  63 */     this.isModal = modal;
/*  64 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ConversationFactory withLocalEcho(boolean localEchoEnabled) {
/*  77 */     this.localEchoEnabled = localEchoEnabled;
/*  78 */     return this;
/*     */   }
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
/*     */   @NotNull
/*     */   public ConversationFactory withPrefix(@NotNull ConversationPrefix prefix) {
/*  92 */     this.prefix = prefix;
/*  93 */     return this;
/*     */   }
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
/*     */   @NotNull
/*     */   public ConversationFactory withTimeout(int timeoutSeconds) {
/* 107 */     return withConversationCanceller(new InactivityConversationCanceller(this.plugin, timeoutSeconds));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ConversationFactory withFirstPrompt(@Nullable Prompt firstPrompt) {
/* 120 */     this.firstPrompt = firstPrompt;
/* 121 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ConversationFactory withInitialSessionData(@NotNull Map<Object, Object> initialSessionData) {
/* 134 */     this.initialSessionData = initialSessionData;
/* 135 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ConversationFactory withEscapeSequence(@NotNull String escapeSequence) {
/* 147 */     return withConversationCanceller(new ExactMatchConversationCanceller(escapeSequence));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ConversationFactory withConversationCanceller(@NotNull ConversationCanceller canceller) {
/* 158 */     this.cancellers.add(canceller);
/* 159 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ConversationFactory thatExcludesNonPlayersWithMessage(@Nullable String playerOnlyMessage) {
/* 172 */     this.playerOnlyMessage = playerOnlyMessage;
/* 173 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ConversationFactory addConversationAbandonedListener(@NotNull ConversationAbandonedListener listener) {
/* 185 */     this.abandonedListeners.add(listener);
/* 186 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Conversation buildConversation(@NotNull Conversable forWhom) {
/* 199 */     if (this.playerOnlyMessage != null && !(forWhom instanceof org.bukkit.entity.Player)) {
/* 200 */       return new Conversation(this.plugin, forWhom, new NotPlayerMessagePrompt());
/*     */     }
/*     */ 
/*     */     
/* 204 */     Map<Object, Object> copiedInitialSessionData = new HashMap<>();
/* 205 */     copiedInitialSessionData.putAll(this.initialSessionData);
/*     */ 
/*     */     
/* 208 */     Conversation conversation = new Conversation(this.plugin, forWhom, this.firstPrompt, copiedInitialSessionData);
/* 209 */     conversation.setModal(this.isModal);
/* 210 */     conversation.setLocalEchoEnabled(this.localEchoEnabled);
/* 211 */     conversation.setPrefix(this.prefix);
/*     */ 
/*     */     
/* 214 */     for (ConversationCanceller canceller : this.cancellers) {
/* 215 */       conversation.addConversationCanceller(canceller.clone());
/*     */     }
/*     */ 
/*     */     
/* 219 */     for (ConversationAbandonedListener listener : this.abandonedListeners) {
/* 220 */       conversation.addConversationAbandonedListener(listener);
/*     */     }
/*     */     
/* 223 */     return conversation;
/*     */   }
/*     */   
/*     */   private class NotPlayerMessagePrompt extends MessagePrompt {
/*     */     private NotPlayerMessagePrompt() {}
/*     */     
/*     */     @NotNull
/*     */     public String getPromptText(@NotNull ConversationContext context) {
/* 231 */       return ConversationFactory.this.playerOnlyMessage;
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     protected Prompt getNextPrompt(@NotNull ConversationContext context) {
/* 237 */       return Prompt.END_OF_CONVERSATION;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\conversations\ConversationFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */