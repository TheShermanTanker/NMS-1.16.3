/*    */ package com.destroystokyo.paper.profile;
/*    */ 
/*    */ import com.destroystokyo.paper.event.profile.FillProfileEvent;
/*    */ import com.destroystokyo.paper.event.profile.PreFillProfileEvent;
/*    */ import com.mojang.authlib.Environment;
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import com.mojang.authlib.minecraft.MinecraftProfileTexture;
/*    */ import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
/*    */ import com.mojang.authlib.yggdrasil.YggdrasilMinecraftSessionService;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class PaperMinecraftSessionService
/*    */   extends YggdrasilMinecraftSessionService {
/*    */   protected PaperMinecraftSessionService(YggdrasilAuthenticationService authenticationService, Environment environment) {
/* 15 */     super(authenticationService, environment);
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> getTextures(GameProfile profile, boolean requireSecure) {
/* 20 */     return super.getTextures(profile, requireSecure);
/*    */   }
/*    */ 
/*    */   
/*    */   public GameProfile fillProfileProperties(GameProfile profile, boolean requireSecure) {
/* 25 */     CraftPlayerProfile playerProfile = (CraftPlayerProfile)CraftPlayerProfile.asBukkitMirror(profile);
/* 26 */     (new PreFillProfileEvent(playerProfile)).callEvent();
/* 27 */     profile = playerProfile.getGameProfile();
/* 28 */     if (profile.isComplete() && profile.getProperties().containsKey("textures")) {
/* 29 */       return profile;
/*    */     }
/* 31 */     GameProfile gameProfile = super.fillProfileProperties(profile, requireSecure);
/* 32 */     (new FillProfileEvent(CraftPlayerProfile.asBukkitMirror(gameProfile))).callEvent();
/* 33 */     return gameProfile;
/*    */   }
/*    */ 
/*    */   
/*    */   protected GameProfile fillGameProfile(GameProfile profile, boolean requireSecure) {
/* 38 */     return super.fillGameProfile(profile, requireSecure);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\profile\PaperMinecraftSessionService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */