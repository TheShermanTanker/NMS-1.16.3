/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.io.IOException;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DedicatedPlayerList
/*     */   extends PlayerList
/*     */ {
/*  13 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   public DedicatedPlayerList(DedicatedServer var0, IRegistryCustom.Dimension var1, WorldNBTStorage var2) {
/*  16 */     super(var0, var1, var2, (var0.getDedicatedServerProperties()).maxPlayers);
/*     */     
/*  18 */     DedicatedServerProperties var3 = var0.getDedicatedServerProperties();
/*  19 */     a(var3.viewDistance);
/*  20 */     super.setHasWhitelist(((Boolean)var3.whiteList.get()).booleanValue());
/*     */     
/*  22 */     y();
/*  23 */     w();
/*  24 */     x();
/*  25 */     v();
/*  26 */     z();
/*  27 */     B();
/*  28 */     A();
/*  29 */     if (!getWhitelist().b().exists()) {
/*  30 */       C();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHasWhitelist(boolean var0) {
/*  36 */     super.setHasWhitelist(var0);
/*  37 */     getServer().setHasWhitelist(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addOp(GameProfile var0) {
/*  42 */     super.addOp(var0);
/*  43 */     A();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeOp(GameProfile var0) {
/*  48 */     super.removeOp(var0);
/*  49 */     A();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reloadWhitelist() {
/*  66 */     B();
/*     */   }
/*     */   
/*     */   private void v() {
/*     */     try {
/*  71 */       getIPBans().save();
/*  72 */     } catch (IOException var0) {
/*  73 */       LOGGER.warn("Failed to save ip banlist: ", var0);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void w() {
/*     */     try {
/*  79 */       getProfileBans().save();
/*  80 */     } catch (IOException var0) {
/*  81 */       LOGGER.warn("Failed to save user banlist: ", var0);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void x() {
/*     */     try {
/*  87 */       getIPBans().load();
/*  88 */     } catch (IOException var0) {
/*  89 */       LOGGER.warn("Failed to load ip banlist: ", var0);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void y() {
/*     */     try {
/*  95 */       getProfileBans().load();
/*  96 */     } catch (IOException var0) {
/*  97 */       LOGGER.warn("Failed to load user banlist: ", var0);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void z() {
/*     */     try {
/* 103 */       getOPs().load();
/* 104 */     } catch (Exception var0) {
/* 105 */       LOGGER.warn("Failed to load operators list: ", var0);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void A() {
/*     */     try {
/* 111 */       getOPs().save();
/* 112 */     } catch (Exception var0) {
/* 113 */       LOGGER.warn("Failed to save operators list: ", var0);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void B() {
/*     */     try {
/* 119 */       getWhitelist().load();
/* 120 */     } catch (Exception var0) {
/* 121 */       LOGGER.warn("Failed to load white-list: ", var0);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void C() {
/*     */     try {
/* 127 */       getWhitelist().save();
/* 128 */     } catch (Exception var0) {
/* 129 */       LOGGER.warn("Failed to save white-list: ", var0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isWhitelisted(GameProfile var0) {
/* 135 */     return (!getHasWhitelist() || isOp(var0) || getWhitelist().isWhitelisted(var0));
/*     */   }
/*     */ 
/*     */   
/*     */   public DedicatedServer getServer() {
/* 140 */     return (DedicatedServer)super.getServer();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean f(GameProfile var0) {
/* 145 */     return getOPs().b(var0);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DedicatedPlayerList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */