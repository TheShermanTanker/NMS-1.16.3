/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SensorDummy
/*    */   extends Sensor<EntityLiving>
/*    */ {
/*    */   protected void a(WorldServer var0, EntityLiving var1) {}
/*    */   
/*    */   public Set<MemoryModuleType<?>> a() {
/* 17 */     return (Set<MemoryModuleType<?>>)ImmutableSet.of();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SensorDummy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */