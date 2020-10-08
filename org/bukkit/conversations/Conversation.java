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
/*     */ public class Conversation
/*     */ {
/*     */   private Prompt firstPrompt;
/*     */   private boolean abandoned;
/*     */   protected Prompt currentPrompt;
/*     */   protected ConversationContext context;
/*     */   protected boolean modal;
/*     */   protected boolean localEchoEnabled;
/*     */   protected ConversationPrefix prefix;
/*     */   protected List<ConversationCanceller> cancellers;
/*     */   protected List<ConversationAbandonedListener> abandonedListeners;
/*     */   
/*     */   public Conversation(@Nullable Plugin plugin, @NotNull Conversable forWhom, @Nullable Prompt firstPrompt) {
/*  56 */     this(plugin, forWhom, firstPrompt, new HashMap<>());
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
/*     */   public Conversation(@Nullable Plugin plugin, @NotNull Conversable forWhom, @Nullable Prompt firstPrompt, @NotNull Map<Object, Object> initialSessionData) {
/*  69 */     this.firstPrompt = firstPrompt;
/*  70 */     this.context = new ConversationContext(plugin, forWhom, initialSessionData);
/*  71 */     this.modal = true;
/*  72 */     this.localEchoEnabled = true;
/*  73 */     this.prefix = new NullConversationPrefix();
/*  74 */     this.cancellers = new ArrayList<>();
/*  75 */     this.abandonedListeners = new ArrayList<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Conversable getForWhom() {
/*  85 */     return this.context.getForWhom();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isModal() {
/*  96 */     return this.modal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setModal(boolean modal) {
/* 107 */     this.modal = modal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLocalEchoEnabled() {
/* 118 */     return this.localEchoEnabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocalEchoEnabled(boolean localEchoEnabled) {
/* 129 */     this.localEchoEnabled = localEchoEnabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ConversationPrefix getPrefix() {
/* 140 */     return this.prefix;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setPrefix(@NotNull ConversationPrefix prefix) {
/* 150 */     this.prefix = prefix;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addConversationCanceller(@NotNull ConversationCanceller canceller) {
/* 159 */     canceller.setConversation(this);
/* 160 */     this.cancellers.add(canceller);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<ConversationCanceller> getCancellers() {
/* 170 */     return this.cancellers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ConversationContext getContext() {
/* 180 */     return this.context;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void begin() {
/* 188 */     if (this.currentPrompt == null) {
/* 189 */       this.abandoned = false;
/* 190 */       this.currentPrompt = this.firstPrompt;
/* 191 */       this.context.getForWhom().beginConversation(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ConversationState getState() {
/* 202 */     if (this.currentPrompt != null)
/* 203 */       return ConversationState.STARTED; 
/* 204 */     if (this.abandoned) {
/* 205 */       return ConversationState.ABANDONED;
/*     */     }
/* 207 */     return ConversationState.UNSTARTED;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void acceptInput(@NotNull String input) {
/* 218 */     if (this.currentPrompt != null) {
/*     */ 
/*     */       
/* 221 */       if (this.localEchoEnabled) {
/* 222 */         this.context.getForWhom().sendRawMessage(this.prefix.getPrefix(this.context) + input);
/*     */       }
/*     */ 
/*     */       
/* 226 */       for (ConversationCanceller canceller : this.cancellers) {
/* 227 */         if (canceller.cancelBasedOnInput(this.context, input)) {
/* 228 */           abandon(new ConversationAbandonedEvent(this, canceller));
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */       
/* 234 */       this.currentPrompt = this.currentPrompt.acceptInput(this.context, input);
/* 235 */       outputNextPrompt();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void addConversationAbandonedListener(@NotNull ConversationAbandonedListener listener) {
/* 245 */     this.abandonedListeners.add(listener);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void removeConversationAbandonedListener(@NotNull ConversationAbandonedListener listener) {
/* 254 */     this.abandonedListeners.remove(listener);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void abandon() {
/* 262 */     abandon(new ConversationAbandonedEvent(this, new ManuallyAbandonedConversationCanceller()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void abandon(@NotNull ConversationAbandonedEvent details) {
/* 272 */     if (!this.abandoned) {
/* 273 */       this.abandoned = true;
/* 274 */       this.currentPrompt = null;
/* 275 */       this.context.getForWhom().abandonConversation(this);
/* 276 */       for (ConversationAbandonedListener listener : this.abandonedListeners) {
/* 277 */         listener.conversationAbandoned(details);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void outputNextPrompt() {
/* 287 */     if (this.currentPrompt == null) {
/* 288 */       abandon(new ConversationAbandonedEvent(this));
/*     */     } else {
/* 290 */       this.context.getForWhom().sendRawMessage(this.prefix.getPrefix(this.context) + this.currentPrompt.getPromptText(this.context));
/* 291 */       if (!this.currentPrompt.blocksForInput(this.context)) {
/* 292 */         this.currentPrompt = this.currentPrompt.acceptInput(this.context, null);
/* 293 */         outputNextPrompt();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public enum ConversationState {
/* 299 */     UNSTARTED,
/* 300 */     STARTED,
/* 301 */     ABANDONED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\conversations\Conversation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */