/*    */ package io.papermc.paper.world;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public enum MoonPhase
/*    */ {
/*  9 */   FULL_MOON(0L),
/* 10 */   WANING_GIBBOUS(1L),
/* 11 */   LAST_QUARTER(2L),
/* 12 */   WANING_CRESCENT(3L),
/* 13 */   NEW_MOON(4L),
/* 14 */   WAXING_CRESCENT(5L),
/* 15 */   FIRST_QUARTER(6L),
/* 16 */   WAXING_GIBBOUS(7L);
/*    */   private final long day;
/*    */   private static final Map<Long, MoonPhase> BY_DAY;
/*    */   
/*    */   MoonPhase(long day) {
/* 21 */     this.day = day;
/*    */   }
/*    */   static {
/* 24 */     BY_DAY = new HashMap<>();
/*    */ 
/*    */     
/* 27 */     for (MoonPhase phase : values()) {
/* 28 */       BY_DAY.put(Long.valueOf(phase.day), phase);
/*    */     }
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static MoonPhase getPhase(long day) {
/* 34 */     return BY_DAY.get(Long.valueOf(day % 8L));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\papermc\paper\world\MoonPhase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */