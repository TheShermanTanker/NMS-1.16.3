/*     */ package com.destroystokyo.paper.profile;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
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
/*     */ public interface PlayerProfile
/*     */ {
/*     */   @Nullable
/*     */   String getName();
/*     */   
/*     */   @NotNull
/*     */   String setName(@Nullable String paramString);
/*     */   
/*     */   @Nullable
/*     */   UUID getId();
/*     */   
/*     */   @Nullable
/*     */   UUID setId(@Nullable UUID paramUUID);
/*     */   
/*     */   @NotNull
/*     */   Set<ProfileProperty> getProperties();
/*     */   
/*     */   boolean hasProperty(@Nullable String paramString);
/*     */   
/*     */   void setProperty(@NotNull ProfileProperty paramProfileProperty);
/*     */   
/*     */   void setProperties(@NotNull Collection<ProfileProperty> paramCollection);
/*     */   
/*     */   boolean removeProperty(@Nullable String paramString);
/*     */   
/*     */   default boolean removeProperty(@NotNull ProfileProperty property) {
/*  81 */     return removeProperty(property.getName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default boolean removeProperties(@NotNull Collection<ProfileProperty> properties) {
/*  90 */     boolean removed = false;
/*  91 */     for (ProfileProperty property : properties) {
/*  92 */       if (removeProperty(property)) {
/*  93 */         removed = true;
/*     */       }
/*     */     } 
/*  96 */     return removed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void clearProperties();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isComplete();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean completeFromCache();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean completeFromCache(boolean paramBoolean);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean completeFromCache(boolean paramBoolean1, boolean paramBoolean2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default boolean complete() {
/* 144 */     return complete(true);
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
/*     */   boolean complete(boolean paramBoolean);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean complete(boolean paramBoolean1, boolean paramBoolean2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default boolean hasTextures() {
/* 175 */     return hasProperty("textures");
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\profile\PlayerProfile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */