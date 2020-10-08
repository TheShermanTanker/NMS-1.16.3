/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public interface ReputationEvent {
/*  4 */   public static final ReputationEvent a = a("zombie_villager_cured");
/*  5 */   public static final ReputationEvent b = a("golem_killed");
/*  6 */   public static final ReputationEvent c = a("villager_hurt");
/*  7 */   public static final ReputationEvent d = a("villager_killed");
/*  8 */   public static final ReputationEvent e = a("trade");
/*    */   
/*    */   static ReputationEvent a(String var0) {
/* 11 */     return new ReputationEvent(var0)
/*    */       {
/*    */         public String toString() {
/* 14 */           return this.f;
/*    */         }
/*    */       };
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ReputationEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */