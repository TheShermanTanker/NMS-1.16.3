/*     */ package org.bukkit.util;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.bukkit.ChatColor;
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
/*     */ public class ChatPaginator
/*     */ {
/*     */   public static final int GUARANTEED_NO_WRAP_CHAT_PAGE_WIDTH = 55;
/*     */   public static final int AVERAGE_CHAT_PAGE_WIDTH = 65;
/*     */   public static final int UNBOUNDED_PAGE_WIDTH = 2147483647;
/*     */   public static final int OPEN_CHAT_PAGE_HEIGHT = 20;
/*     */   public static final int CLOSED_CHAT_PAGE_HEIGHT = 10;
/*     */   public static final int UNBOUNDED_PAGE_HEIGHT = 2147483647;
/*     */   
/*     */   @NotNull
/*     */   public static ChatPage paginate(@Nullable String unpaginatedString, int pageNumber) {
/*  32 */     return paginate(unpaginatedString, pageNumber, 55, 10);
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
/*     */   public static ChatPage paginate(@Nullable String unpaginatedString, int pageNumber, int lineLength, int pageHeight) {
/*  46 */     String[] lines = wordWrap(unpaginatedString, lineLength);
/*     */     
/*  48 */     int totalPages = lines.length / pageHeight + ((lines.length % pageHeight == 0) ? 0 : 1);
/*  49 */     int actualPageNumber = (pageNumber <= totalPages) ? pageNumber : totalPages;
/*     */     
/*  51 */     int from = (actualPageNumber - 1) * pageHeight;
/*  52 */     int to = (from + pageHeight <= lines.length) ? (from + pageHeight) : lines.length;
/*  53 */     String[] selectedLines = Arrays.<String>copyOfRange(lines, from, to);
/*     */     
/*  55 */     return new ChatPage(selectedLines, actualPageNumber, totalPages);
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
/*     */   public static String[] wordWrap(@Nullable String rawString, int lineLength) {
/*  69 */     if (rawString == null) {
/*  70 */       return new String[] { "" };
/*     */     }
/*     */ 
/*     */     
/*  74 */     if (rawString.length() <= lineLength && !rawString.contains("\n")) {
/*  75 */       return new String[] { rawString };
/*     */     }
/*     */     
/*  78 */     char[] rawChars = (rawString + ' ').toCharArray();
/*  79 */     StringBuilder word = new StringBuilder();
/*  80 */     StringBuilder line = new StringBuilder();
/*  81 */     List<String> lines = new LinkedList<>();
/*  82 */     int lineColorChars = 0;
/*     */     int i;
/*  84 */     for (i = 0; i < rawChars.length; i++) {
/*  85 */       char c = rawChars[i];
/*     */ 
/*     */       
/*  88 */       if (c == '§') {
/*  89 */         word.append(ChatColor.getByChar(rawChars[i + 1]));
/*  90 */         lineColorChars += 2;
/*  91 */         i++;
/*     */ 
/*     */       
/*     */       }
/*  95 */       else if (c == ' ' || c == '\n') {
/*  96 */         if (line.length() == 0 && word.length() > lineLength) {
/*  97 */           for (String partialWord : word.toString().split("(?<=\\G.{" + lineLength + "})")) {
/*  98 */             lines.add(partialWord);
/*     */           }
/* 100 */         } else if (line.length() + 1 + word.length() - lineColorChars == lineLength) {
/* 101 */           if (line.length() > 0) {
/* 102 */             line.append(' ');
/*     */           }
/* 104 */           line.append(word);
/* 105 */           lines.add(line.toString());
/* 106 */           line = new StringBuilder();
/* 107 */           lineColorChars = 0;
/* 108 */         } else if (line.length() + 1 + word.length() - lineColorChars > lineLength) {
/* 109 */           for (String partialWord : word.toString().split("(?<=\\G.{" + lineLength + "})")) {
/* 110 */             lines.add(line.toString());
/* 111 */             line = new StringBuilder(partialWord);
/*     */           } 
/* 113 */           lineColorChars = 0;
/*     */         } else {
/* 115 */           if (line.length() > 0) {
/* 116 */             line.append(' ');
/*     */           }
/* 118 */           line.append(word);
/*     */         } 
/* 120 */         word = new StringBuilder();
/*     */         
/* 122 */         if (c == '\n') {
/* 123 */           lines.add(line.toString());
/* 124 */           line = new StringBuilder();
/*     */         } 
/*     */       } else {
/* 127 */         word.append(c);
/*     */       } 
/*     */     } 
/*     */     
/* 131 */     if (line.length() > 0) {
/* 132 */       lines.add(line.toString());
/*     */     }
/*     */ 
/*     */     
/* 136 */     if (((String)lines.get(0)).length() == 0 || ((String)lines.get(0)).charAt(0) != '§') {
/* 137 */       lines.set(0, ChatColor.WHITE + (String)lines.get(0));
/*     */     }
/* 139 */     for (i = 1; i < lines.size(); i++) {
/* 140 */       String pLine = lines.get(i - 1);
/* 141 */       String subLine = lines.get(i);
/*     */       
/* 143 */       char color = pLine.charAt(pLine.lastIndexOf('§') + 1);
/* 144 */       if (subLine.length() == 0 || subLine.charAt(0) != '§') {
/* 145 */         lines.set(i, ChatColor.getByChar(color) + subLine);
/*     */       }
/*     */     } 
/*     */     
/* 149 */     return lines.<String>toArray(new String[lines.size()]);
/*     */   }
/*     */   
/*     */   public static class ChatPage
/*     */   {
/*     */     private String[] lines;
/*     */     private int pageNumber;
/*     */     private int totalPages;
/*     */     
/*     */     public ChatPage(@NotNull String[] lines, int pageNumber, int totalPages) {
/* 159 */       this.lines = lines;
/* 160 */       this.pageNumber = pageNumber;
/* 161 */       this.totalPages = totalPages;
/*     */     }
/*     */     
/*     */     public int getPageNumber() {
/* 165 */       return this.pageNumber;
/*     */     }
/*     */     
/*     */     public int getTotalPages() {
/* 169 */       return this.totalPages;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public String[] getLines() {
/* 174 */       return this.lines;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukki\\util\ChatPaginator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */