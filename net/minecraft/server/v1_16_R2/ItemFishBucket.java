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
/*    */ public class ItemFishBucket
/*    */   extends ItemBucket
/*    */ {
/*    */   private final EntityTypes<?> a;
/*    */   
/*    */   public ItemFishBucket(EntityTypes<?> var0, FluidType var1, Item.Info var2) {
/* 30 */     super(var1, var2);
/* 31 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(World var0, ItemStack var1, BlockPosition var2) {
/* 36 */     if (var0 instanceof WorldServer) {
/* 37 */       a((WorldServer)var0, var1, var2);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(@Nullable EntityHuman var0, GeneratorAccess var1, BlockPosition var2) {
/* 43 */     var1.playSound(var0, var2, SoundEffects.ITEM_BUCKET_EMPTY_FISH, SoundCategory.NEUTRAL, 1.0F, 1.0F);
/*    */   }
/*    */   
/*    */   private void a(WorldServer var0, ItemStack var1, BlockPosition var2) {
/* 47 */     Entity var3 = this.a.spawnCreature(var0, var1, null, var2, EnumMobSpawn.BUCKET, true, false);
/*    */     
/* 49 */     if (var3 != null)
/* 50 */       ((EntityFish)var3).setFromBucket(true); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemFishBucket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */