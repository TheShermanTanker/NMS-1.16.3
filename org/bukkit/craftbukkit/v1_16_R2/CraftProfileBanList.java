/*     */ package org.bukkit.craftbukkit.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.io.IOException;
/*     */ import java.util.Date;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.logging.Level;
/*     */ import net.minecraft.server.v1_16_R2.GameProfileBanEntry;
/*     */ import net.minecraft.server.v1_16_R2.GameProfileBanList;
/*     */ import net.minecraft.server.v1_16_R2.JsonListEntry;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.BanEntry;
/*     */ import org.bukkit.BanList;
/*     */ import org.bukkit.Bukkit;
/*     */ 
/*     */ public class CraftProfileBanList implements BanList {
/*     */   public CraftProfileBanList(GameProfileBanList list) {
/*  22 */     this.list = list;
/*     */   }
/*     */ 
/*     */   
/*     */   public BanEntry getBanEntry(String target) {
/*  27 */     Validate.notNull(target, "Target cannot be null");
/*     */     
/*  29 */     GameProfile profile = getProfile(target);
/*  30 */     if (profile == null) {
/*  31 */       return null;
/*     */     }
/*     */     
/*  34 */     GameProfileBanEntry entry = (GameProfileBanEntry)this.list.get(profile);
/*  35 */     if (entry == null) {
/*  36 */       return null;
/*     */     }
/*     */     
/*  39 */     return new CraftProfileBanEntry(profile, entry, this.list);
/*     */   }
/*     */   private final GameProfileBanList list;
/*     */   
/*     */   public BanEntry addBan(String target, String reason, Date expires, String source) {
/*  44 */     Validate.notNull(target, "Ban target cannot be null");
/*     */     
/*  46 */     GameProfile profile = getProfile(target);
/*  47 */     if (profile == null) {
/*  48 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  53 */     GameProfileBanEntry entry = new GameProfileBanEntry(profile, new Date(), StringUtils.isBlank(source) ? null : source, expires, StringUtils.isBlank(reason) ? null : reason);
/*     */     
/*  55 */     this.list.add((JsonListEntry)entry);
/*     */     
/*     */     try {
/*  58 */       this.list.save();
/*  59 */     } catch (IOException ex) {
/*  60 */       Bukkit.getLogger().log(Level.SEVERE, "Failed to save banned-players.json, {0}", ex.getMessage());
/*     */     } 
/*     */     
/*  63 */     return new CraftProfileBanEntry(profile, entry, this.list);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<BanEntry> getBanEntries() {
/*  68 */     ImmutableSet.Builder<BanEntry> builder = ImmutableSet.builder();
/*     */     
/*  70 */     for (JsonListEntry entry : this.list.getValues()) {
/*  71 */       GameProfile profile = (GameProfile)entry.getKey();
/*  72 */       builder.add(new CraftProfileBanEntry(profile, (GameProfileBanEntry)entry, this.list));
/*     */     } 
/*     */     
/*  75 */     return (Set<BanEntry>)builder.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBanned(String target) {
/*  80 */     Validate.notNull(target, "Target cannot be null");
/*     */     
/*  82 */     GameProfile profile = getProfile(target);
/*  83 */     if (profile == null) {
/*  84 */       return false;
/*     */     }
/*     */     
/*  87 */     return this.list.isBanned(profile);
/*     */   }
/*     */ 
/*     */   
/*     */   public void pardon(String target) {
/*  92 */     Validate.notNull(target, "Target cannot be null");
/*     */     
/*  94 */     GameProfile profile = getProfile(target);
/*  95 */     this.list.remove(profile);
/*     */   }
/*     */   
/*     */   private GameProfile getProfile(String target) {
/*  99 */     UUID uuid = null;
/*     */     
/*     */     try {
/* 102 */       uuid = UUID.fromString(target);
/* 103 */     } catch (IllegalArgumentException illegalArgumentException) {}
/*     */ 
/*     */ 
/*     */     
/* 107 */     return (uuid != null) ? MinecraftServer.getServer().getUserCache().getProfile(uuid) : MinecraftServer.getServer().getUserCache().getProfile(target);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\CraftProfileBanList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */