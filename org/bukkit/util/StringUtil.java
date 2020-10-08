/*    */ package org.bukkit.util;
/*    */ 
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StringUtil
/*    */ {
/*    */   @NotNull
/*    */   public static <T extends java.util.Collection<? super String>> T copyPartialMatches(@NotNull String token, @NotNull Iterable<String> originals, @NotNull T collection) throws UnsupportedOperationException, IllegalArgumentException {
/* 28 */     Validate.notNull(token, "Search token cannot be null");
/* 29 */     Validate.notNull(collection, "Collection cannot be null");
/* 30 */     Validate.notNull(originals, "Originals cannot be null");
/*    */     
/* 32 */     for (String string : originals) {
/* 33 */       if (startsWithIgnoreCase(string, token)) {
/* 34 */         collection.add(string);
/*    */       }
/*    */     } 
/*    */     
/* 38 */     return collection;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean startsWithIgnoreCase(@NotNull String string, @NotNull String prefix) throws IllegalArgumentException, NullPointerException {
/* 54 */     Validate.notNull(string, "Cannot check a null string for a match");
/* 55 */     if (string.length() < prefix.length()) {
/* 56 */       return false;
/*    */     }
/* 58 */     return string.regionMatches(true, 0, prefix, 0, prefix.length());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukki\\util\StringUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */