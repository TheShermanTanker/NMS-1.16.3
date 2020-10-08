/*    */ package org.bukkit;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public interface BanList {
/*    */   @Nullable
/*    */   BanEntry getBanEntry(@NotNull String paramString);
/*    */   
/*    */   @Nullable
/*    */   BanEntry addBan(@NotNull String paramString1, @Nullable String paramString2, @Nullable Date paramDate, @Nullable String paramString3);
/*    */   
/*    */   @NotNull
/*    */   Set<BanEntry> getBanEntries();
/*    */   
/*    */   boolean isBanned(@NotNull String paramString);
/*    */   
/*    */   void pardon(@NotNull String paramString);
/*    */   
/*    */   public enum Type {
/* 20 */     NAME,
/*    */ 
/*    */ 
/*    */     
/* 24 */     IP;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\BanList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */