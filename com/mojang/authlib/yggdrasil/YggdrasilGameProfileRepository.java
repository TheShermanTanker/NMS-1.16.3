/*    */ package com.mojang.authlib.yggdrasil;
/*    */ 
/*    */ import com.google.common.base.Strings;
/*    */ import com.google.common.collect.Iterables;
/*    */ import com.google.common.collect.Sets;
/*    */ import com.mojang.authlib.Agent;
/*    */ import com.mojang.authlib.Environment;
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import com.mojang.authlib.GameProfileRepository;
/*    */ import com.mojang.authlib.HttpAuthenticationService;
/*    */ import com.mojang.authlib.ProfileLookupCallback;
/*    */ import com.mojang.authlib.exceptions.AuthenticationException;
/*    */ import com.mojang.authlib.yggdrasil.response.ProfileSearchResultsResponse;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class YggdrasilGameProfileRepository
/*    */   implements GameProfileRepository {
/* 21 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   private final String searchPageUrl;
/*    */   private static final int ENTRIES_PER_PAGE = 2;
/*    */   private static final int MAX_FAIL_COUNT = 3;
/*    */   private static final int DELAY_BETWEEN_PAGES = 100;
/*    */   private static final int DELAY_BETWEEN_FAILURES = 750;
/*    */   private final YggdrasilAuthenticationService authenticationService;
/*    */   
/*    */   public YggdrasilGameProfileRepository(YggdrasilAuthenticationService authenticationService, Environment environment) {
/* 31 */     this.authenticationService = authenticationService;
/* 32 */     this.searchPageUrl = environment.getAccountsHost() + "/profiles/";
/*    */   }
/*    */ 
/*    */   
/*    */   public void findProfilesByNames(String[] names, Agent agent, ProfileLookupCallback callback) {
/* 37 */     Set<String> criteria = Sets.newHashSet();
/*    */     
/* 39 */     for (String name : names) {
/* 40 */       if (!Strings.isNullOrEmpty(name)) {
/* 41 */         criteria.add(name.toLowerCase());
/*    */       }
/*    */     } 
/*    */     
/* 45 */     int page = 0;
/* 46 */     boolean hasRequested = false;
/*    */     
/* 48 */     label51: for (List<String> request : (Iterable<List<String>>)Iterables.partition(criteria, 2)) {
/* 49 */       int failCount = 0;
/*    */ 
/*    */       
/*    */       while (true) {
/* 53 */         boolean failed = false;
/*    */         
/*    */         try {
/* 56 */           ProfileSearchResultsResponse response = this.authenticationService.<ProfileSearchResultsResponse>makeRequest(HttpAuthenticationService.constantURL(this.searchPageUrl + agent.getName().toLowerCase()), request, ProfileSearchResultsResponse.class);
/* 57 */           failCount = 0;
/*    */           
/* 59 */           LOGGER.debug("Page {} returned {} results, parsing", Integer.valueOf(0), Integer.valueOf((response.getProfiles()).length));
/*    */           
/* 61 */           Set<String> missing = Sets.newHashSet(request);
/* 62 */           for (GameProfile profile : response.getProfiles()) {
/* 63 */             LOGGER.debug("Successfully looked up profile {}", profile);
/* 64 */             missing.remove(profile.getName().toLowerCase());
/* 65 */             callback.onProfileLookupSucceeded(profile);
/*    */           } 
/*    */           
/* 68 */           for (String name : missing) {
/* 69 */             LOGGER.debug("Couldn't find profile {}", name);
/* 70 */             callback.onProfileLookupFailed(new GameProfile(null, name), new ProfileNotFoundException("Server did not find the requested profile"));
/*    */           } 
/*    */           
/* 73 */           if (!hasRequested) {
/* 74 */             hasRequested = true;
/*    */           } else {
/*    */ 
/*    */             
/*    */             try {
/*    */               
/* 80 */               Thread.sleep(100L);
/* 81 */             } catch (InterruptedException interruptedException) {}
/*    */           } 
/* 83 */         } catch (AuthenticationException e) {
/* 84 */           failCount++;
/*    */           
/* 86 */           if (failCount == 3) {
/* 87 */             for (String name : request) {
/* 88 */               LOGGER.debug("Couldn't find profile {} because of a server error", name);
/* 89 */               callback.onProfileLookupFailed(new GameProfile(null, name), (Exception)e);
/*    */             } 
/*    */           } else {
/*    */             try {
/* 93 */               Thread.sleep(750L);
/* 94 */             } catch (InterruptedException interruptedException) {}
/*    */             
/* 96 */             failed = true;
/*    */           } 
/*    */         } 
/* 99 */         if (!failed)
/*    */           continue label51; 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mojang\authlib\yggdrasil\YggdrasilGameProfileRepository.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */