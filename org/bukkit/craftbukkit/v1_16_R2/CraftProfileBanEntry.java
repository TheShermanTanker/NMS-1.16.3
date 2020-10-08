/*    */ package org.bukkit.craftbukkit.v1_16_R2;
/*    */ 
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.io.IOException;
/*    */ import java.util.Date;
/*    */ import java.util.logging.Level;
/*    */ import net.minecraft.server.v1_16_R2.GameProfileBanEntry;
/*    */ import net.minecraft.server.v1_16_R2.GameProfileBanList;
/*    */ import net.minecraft.server.v1_16_R2.JsonListEntry;
/*    */ import org.bukkit.BanEntry;
/*    */ import org.bukkit.Bukkit;
/*    */ 
/*    */ public final class CraftProfileBanEntry
/*    */   implements BanEntry {
/*    */   private final GameProfileBanList list;
/*    */   private final GameProfile profile;
/*    */   private Date created;
/*    */   
/*    */   public CraftProfileBanEntry(GameProfile profile, GameProfileBanEntry entry, GameProfileBanList list) {
/* 20 */     this.list = list;
/* 21 */     this.profile = profile;
/* 22 */     this.created = (entry.getCreated() != null) ? new Date(entry.getCreated().getTime()) : null;
/* 23 */     this.source = entry.getSource();
/* 24 */     this.expiration = (entry.getExpires() != null) ? new Date(entry.getExpires().getTime()) : null;
/* 25 */     this.reason = entry.getReason();
/*    */   }
/*    */   private String source; private Date expiration; private String reason;
/*    */   
/*    */   public String getTarget() {
/* 30 */     return this.profile.getName();
/*    */   }
/*    */ 
/*    */   
/*    */   public Date getCreated() {
/* 35 */     return (this.created == null) ? null : (Date)this.created.clone();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCreated(Date created) {
/* 40 */     this.created = created;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getSource() {
/* 45 */     return this.source;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSource(String source) {
/* 50 */     this.source = source;
/*    */   }
/*    */ 
/*    */   
/*    */   public Date getExpiration() {
/* 55 */     return (this.expiration == null) ? null : (Date)this.expiration.clone();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setExpiration(Date expiration) {
/* 60 */     if (expiration != null && expiration.getTime() == (new Date(0, 0, 0, 0, 0, 0)).getTime()) {
/* 61 */       expiration = null;
/*    */     }
/*    */     
/* 64 */     this.expiration = expiration;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getReason() {
/* 69 */     return this.reason;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setReason(String reason) {
/* 74 */     this.reason = reason;
/*    */   }
/*    */ 
/*    */   
/*    */   public void save() {
/* 79 */     GameProfileBanEntry entry = new GameProfileBanEntry(this.profile, this.created, this.source, this.expiration, this.reason);
/* 80 */     this.list.add((JsonListEntry)entry);
/*    */     try {
/* 82 */       this.list.save();
/* 83 */     } catch (IOException ex) {
/* 84 */       Bukkit.getLogger().log(Level.SEVERE, "Failed to save banned-players.json, {0}", ex.getMessage());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\CraftProfileBanEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */