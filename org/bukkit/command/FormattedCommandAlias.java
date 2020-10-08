/*     */ package org.bukkit.command;
/*     */ 
/*     */ import co.aikar.timings.TimingsManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class FormattedCommandAlias
/*     */   extends Command {
/*     */   public FormattedCommandAlias(@NotNull String alias, @NotNull String[] formatStrings) {
/*  14 */     super(alias);
/*  15 */     this.timings = TimingsManager.getCommandTiming("minecraft", this);
/*  16 */     this.formatStrings = formatStrings;
/*     */   }
/*     */   private final String[] formatStrings;
/*     */   
/*     */   public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
/*  21 */     boolean result = false;
/*  22 */     ArrayList<String> commands = new ArrayList<>();
/*  23 */     for (String formatString : this.formatStrings) {
/*     */       try {
/*  25 */         commands.add(buildCommand(sender, formatString, args));
/*  26 */       } catch (Throwable throwable) {
/*  27 */         if (throwable instanceof IllegalArgumentException) {
/*  28 */           sender.sendMessage(throwable.getMessage());
/*     */         } else {
/*  30 */           sender.sendMessage(ChatColor.RED + "An internal error occurred while attempting to perform this command");
/*     */         } 
/*  32 */         return false;
/*     */       } 
/*     */     } 
/*     */     
/*  36 */     for (String command : commands) {
/*  37 */       result |= Bukkit.dispatchCommand(sender, command);
/*     */     }
/*     */     
/*  40 */     return result;
/*     */   }
/*     */   
/*     */   private String buildCommand(@NotNull CommandSender sender, @NotNull String formatString, @NotNull String[] args) {
/*  44 */     if (formatString.contains("$sender")) {
/*  45 */       formatString = formatString.replaceAll(Pattern.quote("$sender"), Matcher.quoteReplacement(sender.getName()));
/*     */     }
/*  47 */     int index = formatString.indexOf('$');
/*  48 */     while (index != -1) {
/*  49 */       int start = index;
/*     */       
/*  51 */       if (index > 0 && formatString.charAt(start - 1) == '\\') {
/*  52 */         formatString = formatString.substring(0, start - 1) + formatString.substring(start);
/*  53 */         index = formatString.indexOf('$', index);
/*     */         
/*     */         continue;
/*     */       } 
/*  57 */       boolean required = false;
/*  58 */       if (formatString.charAt(index + 1) == '$') {
/*  59 */         required = true;
/*     */         
/*  61 */         index++;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  66 */       int argStart = ++index;
/*  67 */       while (index < formatString.length() && inRange(formatString.charAt(index) - 48, 0, 9))
/*     */       {
/*  69 */         index++;
/*     */       }
/*     */ 
/*     */       
/*  73 */       if (argStart == index) {
/*  74 */         throw new IllegalArgumentException("Invalid replacement token");
/*     */       }
/*     */       
/*  77 */       int position = Integer.parseInt(formatString.substring(argStart, index));
/*     */ 
/*     */       
/*  80 */       if (position == 0) {
/*  81 */         throw new IllegalArgumentException("Invalid replacement token");
/*     */       }
/*     */ 
/*     */       
/*  85 */       position--;
/*     */       
/*  87 */       boolean rest = false;
/*  88 */       if (index < formatString.length() && formatString.charAt(index) == '-') {
/*  89 */         rest = true;
/*     */         
/*  91 */         index++;
/*     */       } 
/*     */       
/*  94 */       int end = index;
/*     */       
/*  96 */       if (required && position >= args.length) {
/*  97 */         throw new IllegalArgumentException("Missing required argument " + (position + 1));
/*     */       }
/*     */       
/* 100 */       StringBuilder replacement = new StringBuilder();
/* 101 */       if (rest && position < args.length) {
/* 102 */         for (int i = position; i < args.length; i++) {
/* 103 */           if (i != position) {
/* 104 */             replacement.append(' ');
/*     */           }
/* 106 */           replacement.append(args[i]);
/*     */         } 
/* 108 */       } else if (position < args.length) {
/* 109 */         replacement.append(args[position]);
/*     */       } 
/*     */       
/* 112 */       formatString = formatString.substring(0, start) + replacement.toString() + formatString.substring(end);
/*     */       
/* 114 */       index = start + replacement.length();
/*     */ 
/*     */       
/* 117 */       index = formatString.indexOf('$', index);
/*     */     } 
/*     */     
/* 120 */     return formatString;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public String getTimingName() {
/* 125 */     return "Command Forwarder - " + super.getTimingName();
/*     */   }
/*     */   private static boolean inRange(int i, int j, int k) {
/* 128 */     return (i >= j && i <= k);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\command\FormattedCommandAlias.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */