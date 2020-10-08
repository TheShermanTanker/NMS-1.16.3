/*    */ package org.yaml.snakeyaml;
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
/*    */ public class LoaderOptions
/*    */ {
/*    */   private boolean allowDuplicateKeys = true;
/*    */   private boolean wrappedToRootException = false;
/* 22 */   private int maxAliasesForCollections = 50;
/*    */   private boolean allowRecursiveKeys;
/*    */   
/*    */   public boolean isAllowDuplicateKeys() {
/* 26 */     return this.allowDuplicateKeys;
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
/*    */ 
/*    */ 
/*    */   
/*    */   public void setAllowDuplicateKeys(boolean allowDuplicateKeys) {
/* 45 */     this.allowDuplicateKeys = allowDuplicateKeys;
/*    */   }
/*    */   
/*    */   public boolean isWrappedToRootException() {
/* 49 */     return this.wrappedToRootException;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setWrappedToRootException(boolean wrappedToRootException) {
/* 60 */     this.wrappedToRootException = wrappedToRootException;
/*    */   }
/*    */   
/*    */   public int getMaxAliasesForCollections() {
/* 64 */     return this.maxAliasesForCollections;
/*    */   }
/*    */   
/*    */   public void setMaxAliasesForCollections(int maxAliasesForCollections) {
/* 68 */     this.maxAliasesForCollections = maxAliasesForCollections;
/*    */   }
/*    */   
/*    */   public void setAllowRecursiveKeys(boolean allowRecursiveKeys) {
/* 72 */     this.allowRecursiveKeys = allowRecursiveKeys;
/*    */   }
/*    */   
/*    */   public boolean getAllowRecursiveKeys() {
/* 76 */     return this.allowRecursiveKeys;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\yaml\snakeyaml\LoaderOptions.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */