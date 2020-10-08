/*    */ package org.bukkit.craftbukkit.v1_16_R2.scoreboard;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ import net.minecraft.server.v1_16_R2.IScoreboardCriteria;
/*    */ import net.minecraft.server.v1_16_R2.ScoreboardObjective;
/*    */ 
/*    */ final class CraftCriteria
/*    */ {
/*    */   static final Map<String, CraftCriteria> DEFAULTS;
/*    */   
/*    */   static {
/* 13 */     ImmutableMap.Builder<String, CraftCriteria> defaults = ImmutableMap.builder();
/*    */     
/* 15 */     for (Map.Entry<?, ?> entry : (Iterable<Map.Entry<?, ?>>)IScoreboardCriteria.criteria.entrySet()) {
/* 16 */       String name = entry.getKey().toString();
/* 17 */       IScoreboardCriteria criteria = (IScoreboardCriteria)entry.getValue();
/*    */       
/* 19 */       defaults.put(name, new CraftCriteria(criteria));
/*    */     } 
/*    */     
/* 22 */     DEFAULTS = (Map<String, CraftCriteria>)defaults.build();
/* 23 */   } static final CraftCriteria DUMMY = DEFAULTS.get("dummy");
/*    */   
/*    */   final IScoreboardCriteria criteria;
/*    */   
/*    */   final String bukkitName;
/*    */   
/*    */   private CraftCriteria(String bukkitName) {
/* 30 */     this.bukkitName = bukkitName;
/* 31 */     this.criteria = DUMMY.criteria;
/*    */   }
/*    */   
/*    */   private CraftCriteria(IScoreboardCriteria criteria) {
/* 35 */     this.criteria = criteria;
/* 36 */     this.bukkitName = criteria.getName();
/*    */   }
/*    */   
/*    */   static CraftCriteria getFromNMS(ScoreboardObjective objective) {
/* 40 */     return DEFAULTS.get(objective.getCriteria().getName());
/*    */   }
/*    */   
/*    */   static CraftCriteria getFromBukkit(String name) {
/* 44 */     CraftCriteria criteria = DEFAULTS.get(name);
/* 45 */     if (criteria != null) {
/* 46 */       return criteria;
/*    */     }
/* 48 */     return new CraftCriteria(name);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object that) {
/* 53 */     if (!(that instanceof CraftCriteria)) {
/* 54 */       return false;
/*    */     }
/* 56 */     return ((CraftCriteria)that).bukkitName.equals(this.bukkitName);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 61 */     return this.bukkitName.hashCode() ^ CraftCriteria.class.hashCode();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\scoreboard\CraftCriteria.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */