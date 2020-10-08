/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileEntityMobSpawner
/*    */   extends TileEntity
/*    */   implements ITickable
/*    */ {
/* 16 */   private final MobSpawnerAbstract a = new MobSpawnerAbstract(this)
/*    */     {
/*    */       public void a(int var0) {
/* 19 */         this.a.world.playBlockAction(this.a.position, Blocks.SPAWNER, var0, 0);
/*    */       }
/*    */ 
/*    */       
/*    */       public World a() {
/* 24 */         return this.a.world;
/*    */       }
/*    */ 
/*    */       
/*    */       public BlockPosition b() {
/* 29 */         return this.a.position;
/*    */       }
/*    */ 
/*    */       
/*    */       public void setSpawnData(MobSpawnerData var0) {
/* 34 */         super.setSpawnData(var0);
/* 35 */         if (a() != null) {
/* 36 */           IBlockData var1 = a().getType(b());
/* 37 */           a().notify(this.a.position, var1, var1, 4);
/*    */         } 
/*    */       }
/*    */     };
/*    */   
/*    */   public TileEntityMobSpawner() {
/* 43 */     super(TileEntityTypes.MOB_SPAWNER);
/*    */   }
/*    */ 
/*    */   
/*    */   public void load(IBlockData var0, NBTTagCompound var1) {
/* 48 */     super.load(var0, var1);
/* 49 */     this.a.a(var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound save(NBTTagCompound var0) {
/* 54 */     super.save(var0);
/* 55 */     this.a.b(var0);
/*    */     
/* 57 */     return var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick() {
/* 62 */     this.a.c();
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public PacketPlayOutTileEntityData getUpdatePacket() {
/* 68 */     return new PacketPlayOutTileEntityData(this.position, 1, b());
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound b() {
/* 73 */     NBTTagCompound var0 = save(new NBTTagCompound());
/* 74 */     var0.remove("SpawnPotentials");
/* 75 */     return var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean setProperty(int var0, int var1) {
/* 80 */     if (this.a.b(var0)) {
/* 81 */       return true;
/*    */     }
/* 83 */     return super.setProperty(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isFilteredNBT() {
/* 88 */     return true;
/*    */   }
/*    */   
/*    */   public MobSpawnerAbstract getSpawner() {
/* 92 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityMobSpawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */