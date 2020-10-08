/*     */ package org.bukkit.help;
/*     */ 
/*     */ import org.bukkit.command.CommandSender;
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
/*     */ public abstract class HelpTopic
/*     */ {
/*  21 */   protected String name = "";
/*  22 */   protected String shortText = "";
/*  23 */   protected String fullText = "";
/*  24 */   protected String amendedPermission = null;
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
/*     */   public abstract boolean canSee(@NotNull CommandSender paramCommandSender);
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
/*     */   public void amendCanSee(@Nullable String amendedPermission) {
/*  49 */     this.amendedPermission = amendedPermission;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getName() {
/*  59 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getShortText() {
/*  69 */     return this.shortText;
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
/*     */   @NotNull
/*     */   public String getFullText(@NotNull CommandSender forWho) {
/*  86 */     return this.fullText;
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
/*     */   public void amendTopic(@Nullable String amendedShortText, @Nullable String amendedFullText) {
/* 104 */     this.shortText = applyAmendment(this.shortText, amendedShortText);
/* 105 */     this.fullText = applyAmendment(this.fullText, amendedFullText);
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
/*     */   @NotNull
/*     */   protected String applyAmendment(@NotNull String baseText, @Nullable String amendment) {
/* 121 */     if (amendment == null) {
/* 122 */       return baseText;
/*     */     }
/* 124 */     return amendment.replaceAll("<text>", baseText);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\help\HelpTopic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */