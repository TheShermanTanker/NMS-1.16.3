/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorSwim
/*    */   extends Behavior<EntityInsentient>
/*    */ {
/*    */   private final float b;
/*    */   
/*    */   public BehaviorSwim(float var0) {
/* 15 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of());
/* 16 */     this.b = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, EntityInsentient var1) {
/* 21 */     return ((var1.isInWater() && var1.b(TagsFluid.WATER) > var1.cw()) || var1.aP());
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean b(WorldServer var0, EntityInsentient var1, long var2) {
/* 26 */     return a(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void d(WorldServer var0, EntityInsentient var1, long var2) {
/* 31 */     if (var1.getRandom().nextFloat() < this.b)
/* 32 */       var1.getControllerJump().jump(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorSwim.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */