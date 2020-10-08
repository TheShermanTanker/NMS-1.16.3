/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ import java.util.function.Predicate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorRunIf<E extends EntityLiving>
/*    */   extends Behavior<E>
/*    */ {
/*    */   private final Predicate<E> b;
/*    */   private final Behavior<? super E> c;
/*    */   private final boolean d;
/*    */   
/*    */   public BehaviorRunIf(Map<MemoryModuleType<?>, MemoryStatus> var0, Predicate<E> var1, Behavior<? super E> var2, boolean var3) {
/* 26 */     super(a(var0, var2.a));
/* 27 */     this.b = var1;
/* 28 */     this.c = var2;
/* 29 */     this.d = var3;
/*    */   }
/*    */   
/*    */   private static Map<MemoryModuleType<?>, MemoryStatus> a(Map<MemoryModuleType<?>, MemoryStatus> var0, Map<MemoryModuleType<?>, MemoryStatus> var1) {
/* 33 */     Map<MemoryModuleType<?>, MemoryStatus> var2 = Maps.newHashMap();
/* 34 */     var2.putAll(var0);
/* 35 */     var2.putAll(var1);
/* 36 */     return var2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BehaviorRunIf(Predicate<E> var0, Behavior<? super E> var1) {
/* 44 */     this((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(), var0, var1, false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, E var1) {
/* 53 */     return (this.b.test(var1) && this.c.a(var0, var1));
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean b(WorldServer var0, E var1, long var2) {
/* 58 */     return (this.d && this.b.test(var1) && this.c.b(var0, var1, var2));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean a(long var0) {
/* 64 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, E var1, long var2) {
/* 69 */     this.c.a(var0, var1, var2);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void d(WorldServer var0, E var1, long var2) {
/* 74 */     this.c.d(var0, var1, var2);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void c(WorldServer var0, E var1, long var2) {
/* 79 */     this.c.c(var0, var1, var2);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 84 */     return "RunIf: " + this.c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorRunIf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */