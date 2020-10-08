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
/*    */ 
/*    */ 
/*    */ public class ItemActionContext
/*    */ {
/*    */   @Nullable
/*    */   private final EntityHuman a;
/*    */   private final EnumHand b;
/*    */   private final MovingObjectPositionBlock c;
/*    */   private final World d;
/*    */   private final ItemStack e;
/*    */   
/*    */   public ItemActionContext(EntityHuman var0, EnumHand var1, MovingObjectPositionBlock var2) {
/* 24 */     this(var0.world, var0, var1, var0.b(var1), var2);
/*    */   }
/*    */   
/*    */   public ItemActionContext(World var0, @Nullable EntityHuman var1, EnumHand var2, ItemStack var3, MovingObjectPositionBlock var4) {
/* 28 */     this.a = var1;
/* 29 */     this.b = var2;
/* 30 */     this.c = var4;
/*    */     
/* 32 */     this.e = var3;
/* 33 */     this.d = var0;
/*    */   }
/*    */   
/*    */   protected final MovingObjectPositionBlock i() {
/* 37 */     return this.c;
/*    */   }
/*    */   
/*    */   public BlockPosition getClickPosition() {
/* 41 */     return this.c.getBlockPosition();
/*    */   }
/*    */   
/*    */   public EnumDirection getClickedFace() {
/* 45 */     return this.c.getDirection();
/*    */   }
/*    */   
/*    */   public Vec3D getPos() {
/* 49 */     return this.c.getPos();
/*    */   }
/*    */   
/*    */   public boolean l() {
/* 53 */     return this.c.d();
/*    */   }
/*    */   
/*    */   public ItemStack getItemStack() {
/* 57 */     return this.e;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public EntityHuman getEntity() {
/* 62 */     return this.a;
/*    */   }
/*    */   
/*    */   public EnumHand getHand() {
/* 66 */     return this.b;
/*    */   }
/*    */   
/*    */   public World getWorld() {
/* 70 */     return this.d;
/*    */   }
/*    */   
/*    */   public EnumDirection f() {
/* 74 */     return (this.a == null) ? EnumDirection.NORTH : this.a.getDirection();
/*    */   }
/*    */   
/*    */   public boolean isSneaking() {
/* 78 */     return (this.a != null && this.a.ep());
/*    */   }
/*    */   
/*    */   public float h() {
/* 82 */     return (this.a == null) ? 0.0F : this.a.yaw;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemActionContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */