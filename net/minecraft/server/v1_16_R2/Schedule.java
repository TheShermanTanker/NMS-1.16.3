/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Comparator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.stream.Collectors;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Schedule
/*    */ {
/* 15 */   public static final Schedule EMPTY = a("empty")
/* 16 */     .a(0, Activity.IDLE)
/* 17 */     .a();
/* 18 */   public static final Schedule SIMPLE = a("simple")
/* 19 */     .a(5000, Activity.WORK)
/* 20 */     .a(11000, Activity.REST)
/* 21 */     .a();
/* 22 */   public static final Schedule VILLAGER_BABY = a("villager_baby")
/* 23 */     .a(10, Activity.IDLE)
/* 24 */     .a(3000, Activity.PLAY)
/* 25 */     .a(6000, Activity.IDLE)
/* 26 */     .a(10000, Activity.PLAY)
/* 27 */     .a(12000, Activity.REST)
/* 28 */     .a();
/* 29 */   public static final Schedule VILLAGER_DEFAULT = a("villager_default")
/* 30 */     .a(10, Activity.IDLE)
/* 31 */     .a(2000, Activity.WORK)
/* 32 */     .a(9000, Activity.MEET)
/* 33 */     .a(11000, Activity.IDLE)
/* 34 */     .a(12000, Activity.REST)
/* 35 */     .a();
/* 36 */   private final Map<Activity, ScheduleActivity> e = Maps.newHashMap();
/*    */   
/*    */   protected static ScheduleBuilder a(String var0) {
/* 39 */     Schedule var1 = IRegistry.<Schedule>a(IRegistry.SCHEDULE, var0, new Schedule());
/* 40 */     return new ScheduleBuilder(var1);
/*    */   }
/*    */   
/*    */   protected void a(Activity var0) {
/* 44 */     if (!this.e.containsKey(var0)) {
/* 45 */       this.e.put(var0, new ScheduleActivity());
/*    */     }
/*    */   }
/*    */   
/*    */   protected ScheduleActivity b(Activity var0) {
/* 50 */     return this.e.get(var0);
/*    */   }
/*    */   
/*    */   protected List<ScheduleActivity> c(Activity var0) {
/* 54 */     return (List<ScheduleActivity>)this.e.entrySet()
/* 55 */       .stream()
/* 56 */       .filter(var1 -> (var1.getKey() != var0))
/* 57 */       .map(Map.Entry::getValue)
/* 58 */       .collect(Collectors.toList());
/*    */   }
/*    */   
/*    */   public Activity a(int var0) {
/* 62 */     return this.e.entrySet()
/* 63 */       .stream()
/* 64 */       .max(Comparator.comparingDouble(var1 -> ((ScheduleActivity)var1.getValue()).a(var0)))
/* 65 */       .map(Map.Entry::getKey)
/* 66 */       .orElse(Activity.IDLE);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Schedule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */