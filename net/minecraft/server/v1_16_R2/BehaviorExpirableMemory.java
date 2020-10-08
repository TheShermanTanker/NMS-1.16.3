/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ import java.util.function.Predicate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorExpirableMemory<E extends EntityInsentient, T>
/*    */   extends Behavior<E>
/*    */ {
/*    */   private final Predicate<E> b;
/*    */   private final MemoryModuleType<? extends T> c;
/*    */   private final MemoryModuleType<T> d;
/*    */   private final IntRange e;
/*    */   
/*    */   public BehaviorExpirableMemory(Predicate<E> var0, MemoryModuleType<? extends T> var1, MemoryModuleType<T> var2, IntRange var3) {
/* 20 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(var1, MemoryStatus.VALUE_PRESENT, var2, MemoryStatus.VALUE_ABSENT));
/*    */ 
/*    */ 
/*    */     
/* 24 */     this.b = var0;
/* 25 */     this.c = var1;
/* 26 */     this.d = var2;
/* 27 */     this.e = var3;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, E var1) {
/* 32 */     return this.b.test(var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, E var1, long var2) {
/* 37 */     BehaviorController<?> var4 = var1.getBehaviorController();
/* 38 */     var4.a(this.d, var4.<T>getMemory((MemoryModuleType)this.c).get(), this.e.a(var0.random));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorExpirableMemory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */