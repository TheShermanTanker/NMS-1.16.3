/*     */ package com.destroystokyo.paper.event.profile;
/*     */ 
/*     */ import com.destroystokyo.paper.profile.ProfileProperty;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.HandlerList;
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
/*     */ public class PreLookupProfileEvent
/*     */   extends Event
/*     */ {
/*  28 */   private static final HandlerList handlers = new HandlerList(); @NotNull
/*     */   private final String name;
/*     */   @NotNull
/*  31 */   private Set<ProfileProperty> properties = new HashSet<>(); private UUID uuid;
/*     */   
/*     */   public PreLookupProfileEvent(@NotNull String name) {
/*  34 */     super(!Bukkit.isPrimaryThread());
/*  35 */     this.name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getName() {
/*  43 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public UUID getUUID() {
/*  55 */     return this.uuid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUUID(@Nullable UUID uuid) {
/*  66 */     this.uuid = uuid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Set<ProfileProperty> getProfileProperties() {
/*  75 */     return this.properties;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setProfileProperties(@NotNull Set<ProfileProperty> properties) {
/*  84 */     this.properties = new HashSet<>();
/*  85 */     this.properties.addAll(properties);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addProfileProperties(@NotNull Set<ProfileProperty> properties) {
/*  94 */     this.properties.addAll(properties);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 100 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 105 */     return handlers;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\profile\PreLookupProfileEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */