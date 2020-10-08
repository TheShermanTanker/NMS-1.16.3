/*    */ package com.destroystokyo.paper.profile;
/*    */ import com.mojang.authlib.Agent;
/*    */ import com.mojang.authlib.Environment;
/*    */ import com.mojang.authlib.GameProfileRepository;
/*    */ import com.mojang.authlib.UserAuthentication;
/*    */ import com.mojang.authlib.minecraft.MinecraftSessionService;
/*    */ import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
/*    */ import com.mojang.authlib.yggdrasil.YggdrasilEnvironment;
/*    */ import java.net.Proxy;
/*    */ 
/*    */ public class PaperAuthenticationService extends YggdrasilAuthenticationService {
/*    */   public PaperAuthenticationService(Proxy proxy, String clientToken) {
/* 13 */     super(proxy, clientToken);
/* 14 */     this.environment = (Environment)EnvironmentParser.getEnvironmentFromProperties().orElse(YggdrasilEnvironment.PROD);
/*    */   }
/*    */   private final Environment environment;
/*    */   
/*    */   public UserAuthentication createUserAuthentication(Agent agent) {
/* 19 */     return (UserAuthentication)new PaperUserAuthentication(this, agent);
/*    */   }
/*    */ 
/*    */   
/*    */   public MinecraftSessionService createMinecraftSessionService() {
/* 24 */     return (MinecraftSessionService)new PaperMinecraftSessionService(this, this.environment);
/*    */   }
/*    */ 
/*    */   
/*    */   public GameProfileRepository createProfileRepository() {
/* 29 */     return (GameProfileRepository)new PaperGameProfileRepository(this, this.environment);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\profile\PaperAuthenticationService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */