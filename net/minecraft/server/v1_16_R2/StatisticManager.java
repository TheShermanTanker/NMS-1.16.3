/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import it.unimi.dsi.fastutil.objects.Object2IntMap;
/*    */ import it.unimi.dsi.fastutil.objects.Object2IntMaps;
/*    */ import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ import org.bukkit.event.Cancellable;
/*    */ 
/*    */ public class StatisticManager {
/*  9 */   protected final Object2IntMap<Statistic<?>> a = Object2IntMaps.synchronize((Object2IntMap)new Object2IntOpenHashMap());
/*    */   
/*    */   public StatisticManager() {
/* 12 */     this.a.defaultReturnValue(0);
/*    */   }
/*    */   
/*    */   public void b(EntityHuman entityhuman, Statistic<?> statistic, int i) {
/* 16 */     int j = (int)Math.min(getStatisticValue(statistic) + i, 2147483647L);
/*    */ 
/*    */     
/* 19 */     Cancellable cancellable = CraftEventFactory.handleStatisticsIncrease(entityhuman, statistic, getStatisticValue(statistic), j);
/* 20 */     if (cancellable != null && cancellable.isCancelled()) {
/*    */       return;
/*    */     }
/*    */     
/* 24 */     setStatistic(entityhuman, statistic, j);
/*    */   }
/*    */   
/*    */   public void setStatistic(EntityHuman entityhuman, Statistic<?> statistic, int i) {
/* 28 */     this.a.put(statistic, i);
/*    */   }
/*    */   
/*    */   public int getStatisticValue(Statistic<?> statistic) {
/* 32 */     return this.a.getInt(statistic);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\StatisticManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */