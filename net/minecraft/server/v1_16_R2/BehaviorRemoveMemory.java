/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ import java.util.function.Predicate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorRemoveMemory<E extends EntityLiving>
/*    */   extends Behavior<E>
/*    */ {
/*    */   private final Predicate<E> b;
/*    */   private final MemoryModuleType<?> c;
/*    */   
/*    */   public BehaviorRemoveMemory(Predicate<E> var0, MemoryModuleType<?> var1) {
/* 17 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(var1, MemoryStatus.VALUE_PRESENT));
/*    */ 
/*    */     
/* 20 */     this.b = var0;
/* 21 */     this.c = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, E var1) {
/* 26 */     return this.b.test(var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, E var1, long var2) {
/* 31 */     var1.getBehaviorController().removeMemory(this.c);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorRemoveMemory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */