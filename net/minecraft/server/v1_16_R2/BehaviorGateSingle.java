/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorGateSingle<E extends EntityLiving>
/*    */   extends BehaviorGate<E>
/*    */ {
/*    */   public BehaviorGateSingle(List<Pair<Behavior<? super E>, Integer>> var0) {
/* 19 */     this(
/* 20 */         (Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(), var0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public BehaviorGateSingle(Map<MemoryModuleType<?>, MemoryStatus> var0, List<Pair<Behavior<? super E>, Integer>> var1) {
/* 26 */     super(var0, 
/*    */         
/* 28 */         (Set<MemoryModuleType<?>>)ImmutableSet.of(), BehaviorGate.Order.SHUFFLED, BehaviorGate.Execution.RUN_ONE, var1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorGateSingle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */