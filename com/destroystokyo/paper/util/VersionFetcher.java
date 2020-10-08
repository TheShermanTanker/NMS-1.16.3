/*    */ package com.destroystokyo.paper.util;
/*    */ 
/*    */ import org.bukkit.Bukkit;
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
/*    */ public interface VersionFetcher
/*    */ {
/*    */   long getCacheTime();
/*    */   
/*    */   @NotNull
/*    */   String getVersionMessage(@NotNull String paramString);
/*    */   
/*    */   public static class DummyVersionFetcher
/*    */     implements VersionFetcher
/*    */   {
/*    */     public long getCacheTime() {
/* 32 */       return -1L;
/*    */     }
/*    */ 
/*    */     
/*    */     @NotNull
/*    */     public String getVersionMessage(@NotNull String serverVersion) {
/* 38 */       Bukkit.getLogger().warning("Version provider has not been set, cannot check for updates!");
/* 39 */       Bukkit.getLogger().info("Override the default implementation of org.bukkit.UnsafeValues#getVersionFetcher()");
/* 40 */       (new Throwable()).printStackTrace();
/* 41 */       return "Unable to check for updates. No version provider set.";
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\pape\\util\VersionFetcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */