/*    */ package org.bukkit.craftbukkit.libs.org.apache.commons.io.serialization;
/*    */ 
/*    */ import org.bukkit.craftbukkit.libs.org.apache.commons.io.FilenameUtils;
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
/*    */ final class WildcardClassNameMatcher
/*    */   implements ClassNameMatcher
/*    */ {
/*    */   private final String pattern;
/*    */   
/*    */   public WildcardClassNameMatcher(String pattern) {
/* 40 */     this.pattern = pattern;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean matches(String className) {
/* 45 */     return FilenameUtils.wildcardMatch(className, this.pattern);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\apache\commons\io\serialization\WildcardClassNameMatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */