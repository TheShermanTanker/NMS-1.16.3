/*   */ package com.destroystokyo.paper.profile;
/*   */ 
/*   */ import com.mojang.authlib.Agent;
/*   */ import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
/*   */ import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
/*   */ 
/*   */ public class PaperUserAuthentication extends YggdrasilUserAuthentication {
/*   */   public PaperUserAuthentication(YggdrasilAuthenticationService authenticationService, Agent agent) {
/* 9 */     super(authenticationService, agent);
/*   */   }
/*   */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\profile\PaperUserAuthentication.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */