/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SensorVillagerHostiles
/*    */   extends Sensor<EntityLiving>
/*    */ {
/* 20 */   private static final ImmutableMap<EntityTypes<?>, Float> a = ImmutableMap.builder()
/* 21 */     .put(EntityTypes.DROWNED, Float.valueOf(8.0F))
/* 22 */     .put(EntityTypes.EVOKER, Float.valueOf(12.0F))
/* 23 */     .put(EntityTypes.HUSK, Float.valueOf(8.0F))
/* 24 */     .put(EntityTypes.ILLUSIONER, Float.valueOf(12.0F))
/* 25 */     .put(EntityTypes.PILLAGER, Float.valueOf(15.0F))
/* 26 */     .put(EntityTypes.RAVAGER, Float.valueOf(12.0F))
/* 27 */     .put(EntityTypes.VEX, Float.valueOf(8.0F))
/* 28 */     .put(EntityTypes.VINDICATOR, Float.valueOf(10.0F))
/* 29 */     .put(EntityTypes.ZOGLIN, Float.valueOf(10.0F))
/* 30 */     .put(EntityTypes.ZOMBIE, Float.valueOf(8.0F))
/* 31 */     .put(EntityTypes.ZOMBIE_VILLAGER, Float.valueOf(8.0F))
/* 32 */     .build();
/*    */ 
/*    */   
/*    */   public Set<MemoryModuleType<?>> a() {
/* 36 */     return (Set<MemoryModuleType<?>>)ImmutableSet.of(MemoryModuleType.NEAREST_HOSTILE);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityLiving var1) {
/* 43 */     var1.getBehaviorController().setMemory(MemoryModuleType.NEAREST_HOSTILE, a(var1));
/*    */   }
/*    */   
/*    */   private Optional<EntityLiving> a(EntityLiving var0) {
/* 47 */     return b(var0).flatMap(var1 -> var1.stream().filter(this::c).filter(()).min(()));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private Optional<List<EntityLiving>> b(EntityLiving var0) {
/* 56 */     return var0.getBehaviorController().getMemory(MemoryModuleType.VISIBLE_MOBS);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private int a(EntityLiving var0, EntityLiving var1, EntityLiving var2) {
/* 64 */     return MathHelper.floor(var1.h(var0) - var2.h(var0));
/*    */   }
/*    */   
/*    */   private boolean b(EntityLiving var0, EntityLiving var1) {
/* 68 */     float var2 = ((Float)a.get(var1.getEntityType())).floatValue();
/* 69 */     return (var1.h(var0) <= (var2 * var2));
/*    */   }
/*    */   
/*    */   private boolean c(EntityLiving var0) {
/* 73 */     return a.containsKey(var0.getEntityType());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SensorVillagerHostiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */