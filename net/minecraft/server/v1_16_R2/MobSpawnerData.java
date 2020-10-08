/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MobSpawnerData
/*    */   extends WeightedRandom.WeightedRandomChoice
/*    */ {
/*    */   private final NBTTagCompound entity;
/*    */   
/*    */   public MobSpawnerData() {
/* 15 */     super(1);
/*    */     
/* 17 */     this.entity = new NBTTagCompound();
/* 18 */     this.entity.setString("id", "minecraft:pig");
/*    */   }
/*    */   
/*    */   public MobSpawnerData(NBTTagCompound var0) {
/* 22 */     this(var0.hasKeyOfType("Weight", 99) ? var0.getInt("Weight") : 1, var0.getCompound("Entity"));
/*    */   }
/*    */   
/*    */   public MobSpawnerData(int var0, NBTTagCompound var1) {
/* 26 */     super(var0);
/*    */     
/* 28 */     this.entity = var1;
/*    */ 
/*    */     
/* 31 */     MinecraftKey var2 = MinecraftKey.a(var1.getString("id"));
/* 32 */     if (var2 != null) {
/* 33 */       var1.setString("id", var2.toString());
/*    */     } else {
/* 35 */       var1.setString("id", "minecraft:pig");
/*    */     } 
/*    */   }
/*    */   
/*    */   public NBTTagCompound a() {
/* 40 */     NBTTagCompound var0 = new NBTTagCompound();
/*    */     
/* 42 */     var0.set("Entity", this.entity);
/* 43 */     var0.setInt("Weight", this.a);
/*    */     
/* 45 */     return var0;
/*    */   }
/*    */   
/*    */   public NBTTagCompound getEntity() {
/* 49 */     return this.entity;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MobSpawnerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */