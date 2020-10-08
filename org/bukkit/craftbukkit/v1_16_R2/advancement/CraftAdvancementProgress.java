/*    */ package org.bukkit.craftbukkit.v1_16_R2.advancement;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import java.util.Date;
/*    */ import net.minecraft.server.v1_16_R2.AdvancementDataPlayer;
/*    */ import net.minecraft.server.v1_16_R2.AdvancementProgress;
/*    */ import net.minecraft.server.v1_16_R2.CriterionProgress;
/*    */ import org.bukkit.advancement.Advancement;
/*    */ import org.bukkit.advancement.AdvancementProgress;
/*    */ 
/*    */ public class CraftAdvancementProgress implements AdvancementProgress {
/*    */   private final CraftAdvancement advancement;
/*    */   private final AdvancementDataPlayer playerData;
/*    */   private final AdvancementProgress handle;
/*    */   
/*    */   public CraftAdvancementProgress(CraftAdvancement advancement, AdvancementDataPlayer player, AdvancementProgress handle) {
/* 19 */     this.advancement = advancement;
/* 20 */     this.playerData = player;
/* 21 */     this.handle = handle;
/*    */   }
/*    */ 
/*    */   
/*    */   public Advancement getAdvancement() {
/* 26 */     return this.advancement;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isDone() {
/* 31 */     return this.handle.isDone();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean awardCriteria(String criteria) {
/* 36 */     return this.playerData.grantCriteria(this.advancement.getHandle(), criteria);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean revokeCriteria(String criteria) {
/* 41 */     return this.playerData.revokeCritera(this.advancement.getHandle(), criteria);
/*    */   }
/*    */ 
/*    */   
/*    */   public Date getDateAwarded(String criteria) {
/* 46 */     CriterionProgress criterion = this.handle.getCriterionProgress(criteria);
/* 47 */     return (criterion == null) ? null : criterion.getDate();
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<String> getRemainingCriteria() {
/* 52 */     return Collections.unmodifiableCollection(Lists.newArrayList(this.handle.getRemainingCriteria()));
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<String> getAwardedCriteria() {
/* 57 */     return Collections.unmodifiableCollection(Lists.newArrayList(this.handle.getAwardedCriteria()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\advancement\CraftAdvancementProgress.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */