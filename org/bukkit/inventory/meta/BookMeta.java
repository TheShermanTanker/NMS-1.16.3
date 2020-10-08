/*     */ package org.bukkit.inventory.meta;public interface BookMeta extends ItemMeta { boolean hasTitle(); @Nullable
/*     */   String getTitle(); boolean setTitle(@Nullable String paramString); boolean hasAuthor(); @Nullable
/*     */   String getAuthor(); void setAuthor(@Nullable String paramString);
/*     */   boolean hasGeneration();
/*     */   @Nullable
/*     */   Generation getGeneration();
/*     */   void setGeneration(@Nullable Generation paramGeneration);
/*     */   boolean hasPages();
/*     */   @NotNull
/*     */   String getPage(int paramInt);
/*     */   void setPage(int paramInt, @NotNull String paramString);
/*     */   @NotNull
/*     */   List<String> getPages();
/*     */   void setPages(@NotNull List<String> paramList);
/*     */   void setPages(@NotNull String... paramVarArgs);
/*     */   void addPage(@NotNull String... paramVarArgs);
/*     */   int getPageCount();
/*     */   @NotNull
/*     */   BookMeta clone();
/*     */   @NotNull
/*     */   Spigot spigot();
/*  22 */   public enum Generation { ORIGINAL,
/*     */ 
/*     */ 
/*     */     
/*  26 */     COPY_OF_ORIGINAL,
/*     */ 
/*     */ 
/*     */     
/*  30 */     COPY_OF_COPY,
/*     */ 
/*     */ 
/*     */     
/*  34 */     TATTERED; }
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
/*     */   public static class Spigot
/*     */   {
/*     */     @NotNull
/*     */     public BaseComponent[] getPage(int page) {
/* 201 */       throw new UnsupportedOperationException("Not supported yet.");
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
/*     */     
/*     */     public void setPage(int page, @Nullable BaseComponent... data) {
/* 215 */       throw new UnsupportedOperationException("Not supported yet.");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public List<BaseComponent[]> getPages() {
/* 225 */       throw new UnsupportedOperationException("Not supported yet.");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setPages(@NotNull List<BaseComponent[]> pages) {
/* 235 */       throw new UnsupportedOperationException("Not supported yet.");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setPages(@NotNull BaseComponent[]... pages) {
/* 245 */       throw new UnsupportedOperationException("Not supported yet.");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void addPage(@NotNull BaseComponent[]... pages) {
/* 255 */       throw new UnsupportedOperationException("Not supported yet.");
/*     */     }
/*     */   } }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\meta\BookMeta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */