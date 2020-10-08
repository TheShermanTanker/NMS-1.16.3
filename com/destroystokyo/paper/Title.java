/*     */ package com.destroystokyo.paper;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import net.md_5.bungee.api.chat.BaseComponent;
/*     */ import net.md_5.bungee.api.chat.TextComponent;
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
/*     */ public final class Title
/*     */ {
/*     */   public static final int DEFAULT_FADE_IN = 20;
/*     */   public static final int DEFAULT_STAY = 200;
/*     */   public static final int DEFAULT_FADE_OUT = 20;
/*     */   private final BaseComponent[] title;
/*     */   private final BaseComponent[] subtitle;
/*     */   private final int fadeIn;
/*     */   private final int stay;
/*     */   private final int fadeOut;
/*     */   
/*     */   public Title(@NotNull BaseComponent title) {
/*  49 */     this(title, (BaseComponent)null);
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
/*     */   public Title(@NotNull BaseComponent[] title) {
/*  61 */     this(title, (BaseComponent[])null);
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
/*     */   public Title(@NotNull String title) {
/*  73 */     this(title, (String)null);
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
/*     */   public Title(@NotNull BaseComponent title, @Nullable BaseComponent subtitle) {
/*  85 */     this(title, subtitle, 20, 200, 20);
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
/*     */   public Title(@NotNull BaseComponent[] title, @Nullable BaseComponent[] subtitle) {
/*  97 */     this(title, subtitle, 20, 200, 20);
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
/*     */   public Title(@NotNull String title, @Nullable String subtitle) {
/* 109 */     this(title, subtitle, 20, 200, 20);
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
/*     */   public Title(@NotNull BaseComponent title, @Nullable BaseComponent subtitle, int fadeIn, int stay, int fadeOut) {
/* 123 */     this(new BaseComponent[1], 
/*     */         
/* 125 */         (subtitle == null) ? null : new BaseComponent[1], fadeIn, stay, fadeOut);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Title(@Nullable BaseComponent[] title, @NotNull BaseComponent[] subtitle, int fadeIn, int stay, int fadeOut) {
/* 143 */     Preconditions.checkArgument((fadeIn >= 0), "Negative fadeIn: %s", fadeIn);
/* 144 */     Preconditions.checkArgument((stay >= 0), "Negative stay: %s", stay);
/* 145 */     Preconditions.checkArgument((fadeOut >= 0), "Negative fadeOut: %s", fadeOut);
/* 146 */     this.title = (BaseComponent[])Preconditions.checkNotNull(title, "title");
/* 147 */     this.subtitle = subtitle;
/* 148 */     this.fadeIn = fadeIn;
/* 149 */     this.stay = stay;
/* 150 */     this.fadeOut = fadeOut;
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
/*     */   
/*     */   public Title(@NotNull String title, @Nullable String subtitle, int fadeIn, int stay, int fadeOut) {
/* 165 */     this(
/* 166 */         TextComponent.fromLegacyText((String)Preconditions.checkNotNull(title, "title")), 
/* 167 */         (subtitle == null) ? null : TextComponent.fromLegacyText(subtitle), fadeIn, stay, fadeOut);
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
/*     */   public BaseComponent[] getTitle() {
/* 181 */     return this.title;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public BaseComponent[] getSubtitle() {
/* 191 */     return this.subtitle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFadeIn() {
/* 202 */     return this.fadeIn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStay() {
/* 213 */     return this.stay;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFadeOut() {
/* 224 */     return this.fadeOut;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static Builder builder() {
/* 229 */     return new Builder();
/*     */   }
/*     */ 
/*     */   
/*     */   public static final class Builder
/*     */   {
/*     */     private BaseComponent[] title;
/*     */     
/*     */     private BaseComponent[] subtitle;
/*     */     
/* 239 */     private int fadeIn = 20;
/* 240 */     private int stay = 200;
/* 241 */     private int fadeOut = 20;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder title(@NotNull BaseComponent title) {
/* 252 */       return title(new BaseComponent[] { (BaseComponent)Preconditions.checkNotNull(title, "title") });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder title(@NotNull BaseComponent[] title) {
/* 264 */       this.title = (BaseComponent[])Preconditions.checkNotNull(title, "title");
/* 265 */       return this;
/*     */     }
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
/*     */     @NotNull
/*     */     public Builder title(@NotNull String title) {
/* 279 */       return title(TextComponent.fromLegacyText((String)Preconditions.checkNotNull(title, "title")));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder subtitle(@Nullable BaseComponent subtitle) {
/* 290 */       (new BaseComponent[1])[0] = subtitle; return subtitle((subtitle == null) ? null : new BaseComponent[1]);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder subtitle(@Nullable BaseComponent[] subtitle) {
/* 301 */       this.subtitle = subtitle;
/* 302 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder subtitle(@Nullable String subtitle) {
/* 315 */       return subtitle((subtitle == null) ? null : TextComponent.fromLegacyText(subtitle));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder fadeIn(int fadeIn) {
/* 327 */       Preconditions.checkArgument((fadeIn >= 0), "Negative fadeIn: %s", fadeIn);
/* 328 */       this.fadeIn = fadeIn;
/* 329 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder stay(int stay) {
/* 342 */       Preconditions.checkArgument((stay >= 0), "Negative stay: %s", stay);
/* 343 */       this.stay = stay;
/* 344 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder fadeOut(int fadeOut) {
/* 356 */       Preconditions.checkArgument((fadeOut >= 0), "Negative fadeOut: %s", fadeOut);
/* 357 */       this.fadeOut = fadeOut;
/* 358 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Title build() {
/* 369 */       Preconditions.checkState((this.title != null), "Title not specified");
/* 370 */       return new Title(this.title, this.subtitle, this.fadeIn, this.stay, this.fadeOut);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\Title.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */