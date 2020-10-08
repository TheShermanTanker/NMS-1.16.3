/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class PathfinderGoalDoorInteract
/*    */   extends PathfinderGoal
/*    */ {
/*    */   protected EntityInsentient entity;
/* 14 */   protected BlockPosition door = BlockPosition.ZERO;
/*    */   protected boolean f;
/*    */   private boolean a;
/*    */   private float b;
/*    */   private float c;
/*    */   
/*    */   public PathfinderGoalDoorInteract(EntityInsentient var0) {
/* 21 */     this.entity = var0;
/* 22 */     if (!PathfinderGoalUtil.a(var0)) {
/* 23 */       throw new IllegalArgumentException("Unsupported mob type for DoorInteractGoal");
/*    */     }
/*    */   }
/*    */   
/*    */   protected boolean g() {
/* 28 */     if (!this.f) {
/* 29 */       return false;
/*    */     }
/* 31 */     IBlockData var0 = this.entity.world.getType(this.door);
/* 32 */     if (!(var0.getBlock() instanceof BlockDoor)) {
/* 33 */       this.f = false;
/* 34 */       return false;
/*    */     } 
/* 36 */     return ((Boolean)var0.get(BlockDoor.OPEN)).booleanValue();
/*    */   }
/*    */   
/*    */   protected void a(boolean var0) {
/* 40 */     if (this.f) {
/* 41 */       IBlockData var1 = this.entity.world.getType(this.door);
/* 42 */       if (var1.getBlock() instanceof BlockDoor) {
/* 43 */         ((BlockDoor)var1.getBlock()).setDoor(this.entity.world, var1, this.door, var0);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 50 */     if (!PathfinderGoalUtil.a(this.entity)) {
/* 51 */       return false;
/*    */     }
/* 53 */     if (!this.entity.positionChanged) {
/* 54 */       return false;
/*    */     }
/* 56 */     Navigation var0 = (Navigation)this.entity.getNavigation();
/* 57 */     PathEntity var1 = var0.k();
/* 58 */     if (var1 == null || var1.c() || !var0.f()) {
/* 59 */       return false;
/*    */     }
/*    */     
/* 62 */     for (int var2 = 0; var2 < Math.min(var1.f() + 2, var1.e()); var2++) {
/* 63 */       PathPoint var3 = var1.a(var2);
/* 64 */       this.door = new BlockPosition(var3.a, var3.b + 1, var3.c);
/* 65 */       if (this.entity.h(this.door.getX(), this.entity.locY(), this.door.getZ()) <= 2.25D) {
/*    */ 
/*    */         
/* 68 */         this.f = BlockDoor.a(this.entity.world, this.door);
/* 69 */         if (this.f) {
/* 70 */           return true;
/*    */         }
/*    */       } 
/*    */     } 
/* 74 */     this.door = this.entity.getChunkCoordinates().up();
/* 75 */     this.f = BlockDoor.a(this.entity.world, this.door);
/* 76 */     return this.f;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/* 81 */     return !this.a;
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 86 */     this.a = false;
/* 87 */     this.b = (float)(this.door.getX() + 0.5D - this.entity.locX());
/* 88 */     this.c = (float)(this.door.getZ() + 0.5D - this.entity.locZ());
/*    */   }
/*    */ 
/*    */   
/*    */   public void e() {
/* 93 */     float var0 = (float)(this.door.getX() + 0.5D - this.entity.locX());
/* 94 */     float var1 = (float)(this.door.getZ() + 0.5D - this.entity.locZ());
/* 95 */     float var2 = this.b * var0 + this.c * var1;
/* 96 */     if (var2 < 0.0F)
/* 97 */       this.a = true; 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalDoorInteract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */