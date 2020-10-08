/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public class Activity
/*    */ {
/*  6 */   public static final Activity CORE = a("core");
/*  7 */   public static final Activity IDLE = a("idle");
/*  8 */   public static final Activity WORK = a("work");
/*  9 */   public static final Activity PLAY = a("play");
/* 10 */   public static final Activity REST = a("rest");
/* 11 */   public static final Activity MEET = a("meet");
/* 12 */   public static final Activity PANIC = a("panic");
/* 13 */   public static final Activity RAID = a("raid");
/* 14 */   public static final Activity PRE_RAID = a("pre_raid");
/* 15 */   public static final Activity HIDE = a("hide");
/* 16 */   public static final Activity FLIGHT = a("fight");
/* 17 */   public static final Activity CELEBRATE = a("celebrate");
/* 18 */   public static final Activity ADMIRE_ITEM = a("admire_item");
/* 19 */   public static final Activity AVOID = a("avoid");
/* 20 */   public static final Activity RIDE = a("ride");
/*    */   
/*    */   private final String p;
/*    */   private final int q;
/*    */   
/*    */   private Activity(String var0) {
/* 26 */     this.p = var0;
/* 27 */     this.q = var0.hashCode();
/*    */   }
/*    */   
/*    */   public String a() {
/* 31 */     return this.p;
/*    */   }
/*    */   
/*    */   private static Activity a(String var0) {
/* 35 */     return IRegistry.<Activity>a(IRegistry.ACTIVITY, var0, new Activity(var0));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object var0) {
/* 40 */     if (this == var0) {
/* 41 */       return true;
/*    */     }
/* 43 */     if (var0 == null || getClass() != var0.getClass()) {
/* 44 */       return false;
/*    */     }
/*    */     
/* 47 */     Activity var1 = (Activity)var0;
/*    */     
/* 49 */     return this.p.equals(var1.p);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 54 */     return this.q;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 59 */     return a();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Activity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */