/*    */ package org.bukkit.craftbukkit.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.Date;
/*    */ import java.util.logging.Level;
/*    */ import net.minecraft.server.v1_16_R2.IpBanEntry;
/*    */ import net.minecraft.server.v1_16_R2.IpBanList;
/*    */ import net.minecraft.server.v1_16_R2.JsonListEntry;
/*    */ import org.bukkit.BanEntry;
/*    */ import org.bukkit.Bukkit;
/*    */ 
/*    */ public final class CraftIpBanEntry
/*    */   implements BanEntry {
/*    */   private final IpBanList list;
/*    */   private final String target;
/*    */   private Date created;
/*    */   
/*    */   public CraftIpBanEntry(String target, IpBanEntry entry, IpBanList list) {
/* 19 */     this.list = list;
/* 20 */     this.target = target;
/* 21 */     this.created = (entry.getCreated() != null) ? new Date(entry.getCreated().getTime()) : null;
/* 22 */     this.source = entry.getSource();
/* 23 */     this.expiration = (entry.getExpires() != null) ? new Date(entry.getExpires().getTime()) : null;
/* 24 */     this.reason = entry.getReason();
/*    */   }
/*    */   private String source; private Date expiration; private String reason;
/*    */   
/*    */   public String getTarget() {
/* 29 */     return this.target;
/*    */   }
/*    */ 
/*    */   
/*    */   public Date getCreated() {
/* 34 */     return (this.created == null) ? null : (Date)this.created.clone();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCreated(Date created) {
/* 39 */     this.created = created;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getSource() {
/* 44 */     return this.source;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSource(String source) {
/* 49 */     this.source = source;
/*    */   }
/*    */ 
/*    */   
/*    */   public Date getExpiration() {
/* 54 */     return (this.expiration == null) ? null : (Date)this.expiration.clone();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setExpiration(Date expiration) {
/* 59 */     if (expiration != null && expiration.getTime() == (new Date(0, 0, 0, 0, 0, 0)).getTime()) {
/* 60 */       expiration = null;
/*    */     }
/*    */     
/* 63 */     this.expiration = expiration;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getReason() {
/* 68 */     return this.reason;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setReason(String reason) {
/* 73 */     this.reason = reason;
/*    */   }
/*    */ 
/*    */   
/*    */   public void save() {
/* 78 */     IpBanEntry entry = new IpBanEntry(this.target, this.created, this.source, this.expiration, this.reason);
/* 79 */     this.list.add((JsonListEntry)entry);
/*    */     try {
/* 81 */       this.list.save();
/* 82 */     } catch (IOException ex) {
/* 83 */       Bukkit.getLogger().log(Level.SEVERE, "Failed to save banned-ips.json, {0}", ex.getMessage());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\CraftIpBanEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */