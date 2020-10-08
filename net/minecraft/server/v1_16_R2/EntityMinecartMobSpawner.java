/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityMinecartMobSpawner
/*    */   extends EntityMinecartAbstract
/*    */ {
/* 12 */   private final MobSpawnerAbstract b = new MobSpawnerAbstract(this)
/*    */     {
/*    */       public void a(int var0) {
/* 15 */         this.a.world.broadcastEntityEffect(this.a, (byte)var0);
/*    */       }
/*    */ 
/*    */       
/*    */       public World a() {
/* 20 */         return this.a.world;
/*    */       }
/*    */ 
/*    */       
/*    */       public BlockPosition b() {
/* 25 */         return this.a.getChunkCoordinates();
/*    */       }
/*    */     };
/*    */   
/*    */   public EntityMinecartMobSpawner(EntityTypes<? extends EntityMinecartMobSpawner> var0, World var1) {
/* 30 */     super(var0, var1);
/*    */   }
/*    */   
/*    */   public EntityMinecartMobSpawner(World var0, double var1, double var3, double var5) {
/* 34 */     super(EntityTypes.SPAWNER_MINECART, var0, var1, var3, var5);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityMinecartAbstract.EnumMinecartType getMinecartType() {
/* 39 */     return EntityMinecartAbstract.EnumMinecartType.SPAWNER;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData q() {
/* 44 */     return Blocks.SPAWNER.getBlockData();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void loadData(NBTTagCompound var0) {
/* 49 */     super.loadData(var0);
/* 50 */     this.b.a(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void saveData(NBTTagCompound var0) {
/* 55 */     super.saveData(var0);
/* 56 */     this.b.b(var0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick() {
/* 66 */     super.tick();
/* 67 */     this.b.c();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean ci() {
/* 76 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityMinecartMobSpawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */