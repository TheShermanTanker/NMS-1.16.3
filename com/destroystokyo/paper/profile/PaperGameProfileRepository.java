/*    */ package com.destroystokyo.paper.profile;
/*    */ 
/*    */ import com.destroystokyo.paper.event.profile.LookupProfileEvent;
/*    */ import com.destroystokyo.paper.event.profile.PreLookupProfileEvent;
/*    */ import com.google.common.collect.Sets;
/*    */ import com.mojang.authlib.Agent;
/*    */ import com.mojang.authlib.Environment;
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import com.mojang.authlib.ProfileLookupCallback;
/*    */ import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
/*    */ import com.mojang.authlib.yggdrasil.YggdrasilGameProfileRepository;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class PaperGameProfileRepository extends YggdrasilGameProfileRepository {
/*    */   public PaperGameProfileRepository(YggdrasilAuthenticationService authenticationService, Environment environment) {
/* 16 */     super(authenticationService, environment);
/*    */   }
/*    */ 
/*    */   
/*    */   public void findProfilesByNames(String[] names, Agent agent, ProfileLookupCallback callback) {
/* 21 */     Set<String> unfoundNames = Sets.newHashSet();
/* 22 */     for (String name : names) {
/* 23 */       PreLookupProfileEvent event = new PreLookupProfileEvent(name);
/* 24 */       event.callEvent();
/* 25 */       if (event.getUUID() != null) {
/*    */         
/* 27 */         GameProfile gameprofile = new GameProfile(event.getUUID(), name);
/*    */         
/* 29 */         Set<ProfileProperty> profileProperties = event.getProfileProperties();
/* 30 */         if (!profileProperties.isEmpty()) {
/* 31 */           for (ProfileProperty property : profileProperties) {
/* 32 */             gameprofile.getProperties().put(property.getName(), CraftPlayerProfile.asAuthlib(property));
/*    */           }
/*    */         }
/* 35 */         callback.onProfileLookupSucceeded(gameprofile);
/*    */       } else {
/* 37 */         unfoundNames.add(name);
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 42 */     if (!unfoundNames.isEmpty()) {
/* 43 */       String[] namesArr = unfoundNames.<String>toArray(new String[unfoundNames.size()]);
/* 44 */       super.findProfilesByNames(namesArr, agent, new PreProfileLookupCallback(callback));
/*    */     } 
/*    */   }
/*    */   
/*    */   private static class PreProfileLookupCallback implements ProfileLookupCallback {
/*    */     private final ProfileLookupCallback callback;
/*    */     
/*    */     PreProfileLookupCallback(ProfileLookupCallback callback) {
/* 52 */       this.callback = callback;
/*    */     }
/*    */ 
/*    */     
/*    */     public void onProfileLookupSucceeded(GameProfile gameProfile) {
/* 57 */       PlayerProfile from = CraftPlayerProfile.asBukkitMirror(gameProfile);
/* 58 */       (new LookupProfileEvent(from)).callEvent();
/* 59 */       this.callback.onProfileLookupSucceeded(gameProfile);
/*    */     }
/*    */ 
/*    */     
/*    */     public void onProfileLookupFailed(GameProfile gameProfile, Exception e) {
/* 64 */       this.callback.onProfileLookupFailed(gameProfile, e);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\profile\PaperGameProfileRepository.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */