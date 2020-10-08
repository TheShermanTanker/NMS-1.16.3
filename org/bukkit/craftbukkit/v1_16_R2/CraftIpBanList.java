/*    */ package org.bukkit.craftbukkit.v1_16_R2;
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import java.io.IOException;
/*    */ import java.net.InetSocketAddress;
/*    */ import java.util.Date;
/*    */ import java.util.Set;
/*    */ import java.util.logging.Level;
/*    */ import net.minecraft.server.v1_16_R2.IpBanEntry;
/*    */ import net.minecraft.server.v1_16_R2.IpBanList;
/*    */ import net.minecraft.server.v1_16_R2.JsonListEntry;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.BanEntry;
/*    */ import org.bukkit.BanList;
/*    */ import org.bukkit.Bukkit;
/*    */ 
/*    */ public class CraftIpBanList implements BanList {
/*    */   public CraftIpBanList(IpBanList list) {
/* 19 */     this.list = list;
/*    */   }
/*    */   private final IpBanList list;
/*    */   
/*    */   public BanEntry getBanEntry(String target) {
/* 24 */     Validate.notNull(target, "Target cannot be null");
/*    */     
/* 26 */     IpBanEntry entry = (IpBanEntry)this.list.get(target);
/* 27 */     if (entry == null) {
/* 28 */       return null;
/*    */     }
/*    */     
/* 31 */     return new CraftIpBanEntry(target, entry, this.list);
/*    */   }
/*    */ 
/*    */   
/*    */   public BanEntry addBan(String target, String reason, Date expires, String source) {
/* 36 */     Validate.notNull(target, "Ban target cannot be null");
/*    */ 
/*    */ 
/*    */     
/* 40 */     IpBanEntry entry = new IpBanEntry(target, new Date(), StringUtils.isBlank(source) ? null : source, expires, StringUtils.isBlank(reason) ? null : reason);
/*    */     
/* 42 */     this.list.add((JsonListEntry)entry);
/*    */     
/*    */     try {
/* 45 */       this.list.save();
/* 46 */     } catch (IOException ex) {
/* 47 */       Bukkit.getLogger().log(Level.SEVERE, "Failed to save banned-ips.json, {0}", ex.getMessage());
/*    */     } 
/*    */     
/* 50 */     return new CraftIpBanEntry(target, entry, this.list);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BanEntry> getBanEntries() {
/* 55 */     ImmutableSet.Builder<BanEntry> builder = ImmutableSet.builder();
/* 56 */     for (String target : this.list.getEntries()) {
/* 57 */       builder.add(new CraftIpBanEntry(target, (IpBanEntry)this.list.get(target), this.list));
/*    */     }
/*    */     
/* 60 */     return (Set<BanEntry>)builder.build();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBanned(String target) {
/* 65 */     Validate.notNull(target, "Target cannot be null");
/*    */     
/* 67 */     return this.list.isBanned(InetSocketAddress.createUnresolved(target, 0));
/*    */   }
/*    */ 
/*    */   
/*    */   public void pardon(String target) {
/* 72 */     Validate.notNull(target, "Target cannot be null");
/*    */     
/* 74 */     this.list.remove(target);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\CraftIpBanList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */