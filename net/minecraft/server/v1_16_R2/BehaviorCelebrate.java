/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Random;
/*    */ import javax.annotation.Nullable;
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
/*    */ public class BehaviorCelebrate
/*    */   extends Behavior<EntityVillager>
/*    */ {
/*    */   @Nullable
/*    */   private Raid b;
/*    */   
/*    */   public BehaviorCelebrate(int var0, int var1) {
/* 27 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(), var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, EntityVillager var1) {
/* 32 */     BlockPosition var2 = var1.getChunkCoordinates();
/* 33 */     this.b = var0.b_(var2);
/* 34 */     return (this.b != null && this.b.isVictory() && BehaviorOutside.a(var0, var1, var2));
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean b(WorldServer var0, EntityVillager var1, long var2) {
/* 39 */     return (this.b != null && !this.b.isStopped());
/*    */   }
/*    */ 
/*    */   
/*    */   protected void c(WorldServer var0, EntityVillager var1, long var2) {
/* 44 */     this.b = null;
/* 45 */     var1.getBehaviorController().a(var0.getDayTime(), var0.getTime());
/*    */   }
/*    */ 
/*    */   
/*    */   protected void d(WorldServer var0, EntityVillager var1, long var2) {
/* 50 */     Random var4 = var1.getRandom();
/*    */     
/* 52 */     if (var4.nextInt(100) == 0) {
/* 53 */       var1.eR();
/*    */     }
/*    */     
/* 56 */     if (var4.nextInt(200) == 0 && BehaviorOutside.a(var0, var1, var1.getChunkCoordinates())) {
/* 57 */       EnumColor var5 = SystemUtils.<EnumColor>a(EnumColor.values(), var4);
/* 58 */       int var6 = var4.nextInt(3);
/* 59 */       ItemStack var7 = a(var5, var6);
/*    */       
/* 61 */       EntityFireworks var8 = new EntityFireworks(var1.world, var1, var1.locX(), var1.getHeadY(), var1.locZ(), var7);
/* 62 */       var1.world.addEntity(var8);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private ItemStack a(EnumColor var0, int var1) {
/* 68 */     ItemStack var2 = new ItemStack(Items.FIREWORK_ROCKET, 1);
/*    */     
/* 70 */     ItemStack var3 = new ItemStack(Items.FIREWORK_STAR);
/* 71 */     NBTTagCompound var4 = var3.a("Explosion");
/*    */     
/* 73 */     List<Integer> var5 = Lists.newArrayList();
/* 74 */     var5.add(Integer.valueOf(var0.getFireworksColor()));
/*    */     
/* 76 */     var4.b("Colors", var5);
/* 77 */     var4.setByte("Type", (byte)ItemFireworks.EffectType.BURST.a());
/*    */     
/* 79 */     NBTTagCompound var6 = var2.a("Fireworks");
/* 80 */     NBTTagList var7 = new NBTTagList();
/*    */     
/* 82 */     NBTTagCompound var8 = var3.b("Explosion");
/* 83 */     if (var8 != null) {
/* 84 */       var7.add(var8);
/*    */     }
/*    */     
/* 87 */     var6.setByte("Flight", (byte)var1);
/* 88 */     if (!var7.isEmpty()) {
/* 89 */       var6.set("Explosions", var7);
/*    */     }
/*    */     
/* 92 */     return var2;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorCelebrate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */