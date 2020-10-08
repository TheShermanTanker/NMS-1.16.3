/*     */ package org.bukkit.help;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.bukkit.ChatColor;
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
/*     */ public class IndexHelpTopic
/*     */   extends HelpTopic
/*     */ {
/*     */   protected String permission;
/*     */   protected String preamble;
/*     */   protected Collection<HelpTopic> allTopics;
/*     */   
/*     */   public IndexHelpTopic(@NotNull String name, @Nullable String shortText, @Nullable String permission, @NotNull Collection<HelpTopic> topics) {
/*  27 */     this(name, shortText, permission, topics, (String)null);
/*     */   }
/*     */   
/*     */   public IndexHelpTopic(@NotNull String name, @Nullable String shortText, @Nullable String permission, @NotNull Collection<HelpTopic> topics, @Nullable String preamble) {
/*  31 */     this.name = name;
/*  32 */     this.shortText = (shortText == null) ? "" : shortText;
/*  33 */     this.permission = permission;
/*  34 */     this.preamble = (preamble == null) ? "" : preamble;
/*  35 */     setTopicsCollection(topics);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setTopicsCollection(@NotNull Collection<HelpTopic> topics) {
/*  44 */     this.allTopics = topics;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSee(@NotNull CommandSender sender) {
/*  49 */     if (sender instanceof org.bukkit.command.ConsoleCommandSender) {
/*  50 */       return true;
/*     */     }
/*  52 */     if (this.permission == null) {
/*  53 */       return true;
/*     */     }
/*  55 */     return sender.hasPermission(this.permission);
/*     */   }
/*     */ 
/*     */   
/*     */   public void amendCanSee(@Nullable String amendedPermission) {
/*  60 */     this.permission = amendedPermission;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getFullText(@NotNull CommandSender sender) {
/*  66 */     StringBuilder sb = new StringBuilder();
/*     */     
/*  68 */     if (this.preamble != null) {
/*  69 */       sb.append(buildPreamble(sender));
/*  70 */       sb.append("\n");
/*     */     } 
/*     */     
/*  73 */     for (HelpTopic topic : this.allTopics) {
/*  74 */       if (topic.canSee(sender)) {
/*  75 */         String lineStr = buildIndexLine(sender, topic).replace("\n", ". ");
/*  76 */         if (sender instanceof org.bukkit.entity.Player && lineStr.length() > 55) {
/*  77 */           sb.append(lineStr, 0, 52);
/*  78 */           sb.append("...");
/*     */         } else {
/*  80 */           sb.append(lineStr);
/*     */         } 
/*  82 */         sb.append("\n");
/*     */       } 
/*     */     } 
/*  85 */     return sb.toString();
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
/*     */   protected String buildPreamble(@NotNull CommandSender sender) {
/*  97 */     return ChatColor.GRAY + this.preamble;
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
/*     */   protected String buildIndexLine(@NotNull CommandSender sender, @NotNull HelpTopic topic) {
/* 110 */     StringBuilder line = new StringBuilder();
/* 111 */     line.append(ChatColor.GOLD);
/* 112 */     line.append(topic.getName());
/* 113 */     line.append(": ");
/* 114 */     line.append(ChatColor.WHITE);
/* 115 */     line.append(topic.getShortText());
/* 116 */     return line.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\help\IndexHelpTopic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */