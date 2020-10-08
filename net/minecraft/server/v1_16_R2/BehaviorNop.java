/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorNop
/*    */   extends Behavior<EntityLiving>
/*    */ {
/*    */   public BehaviorNop(int var0, int var1) {
/* 14 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(), var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean b(WorldServer var0, EntityLiving var1, long var2) {
/* 19 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorNop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */