/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public class ItemDebugStick
/*    */   extends Item {
/*    */   public ItemDebugStick(Item.Info item_info) {
/*  9 */     super(item_info);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean e(ItemStack itemstack) {
/* 14 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman) {
/* 19 */     if (!world.isClientSide) {
/* 20 */       a(entityhuman, iblockdata, world, blockposition, false, entityhuman.b(EnumHand.MAIN_HAND));
/*    */     }
/*    */     
/* 23 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult a(ItemActionContext itemactioncontext) {
/* 28 */     EntityHuman entityhuman = itemactioncontext.getEntity();
/* 29 */     World world = itemactioncontext.getWorld();
/*    */     
/* 31 */     if (!world.isClientSide && entityhuman != null) {
/* 32 */       BlockPosition blockposition = itemactioncontext.getClickPosition();
/*    */       
/* 34 */       a(entityhuman, world.getType(blockposition), world, blockposition, true, itemactioncontext.getItemStack());
/*    */     } 
/*    */     
/* 37 */     return EnumInteractionResult.a(world.isClientSide);
/*    */   }
/*    */   
/*    */   private void a(EntityHuman entityhuman, IBlockData iblockdata, GeneratorAccess generatoraccess, BlockPosition blockposition, boolean flag, ItemStack itemstack) {
/* 41 */     if (entityhuman.isCreativeAndOp() || (entityhuman.abilities.canInstantlyBuild && entityhuman.getBukkitEntity().hasPermission("minecraft.debugstick")) || entityhuman.getBukkitEntity().hasPermission("minecraft.debugstick.always")) {
/* 42 */       Block block = iblockdata.getBlock();
/* 43 */       BlockStateList<Block, IBlockData> blockstatelist = block.getStates();
/* 44 */       Collection<IBlockState<?>> collection = blockstatelist.d();
/* 45 */       String s = IRegistry.BLOCK.getKey(block).toString();
/*    */       
/* 47 */       if (collection.isEmpty()) {
/* 48 */         a(entityhuman, new ChatMessage(getName() + ".empty", new Object[] { s }));
/*    */       } else {
/* 50 */         NBTTagCompound nbttagcompound = itemstack.a("DebugProperty");
/* 51 */         String s1 = nbttagcompound.getString(s);
/* 52 */         IBlockState<?> iblockstate = blockstatelist.a(s1);
/*    */         
/* 54 */         if (flag) {
/* 55 */           if (iblockstate == null) {
/* 56 */             iblockstate = collection.iterator().next();
/*    */           }
/*    */           
/* 59 */           IBlockData iblockdata1 = a(iblockdata, iblockstate, entityhuman.ep());
/*    */           
/* 61 */           generatoraccess.setTypeAndData(blockposition, iblockdata1, 18);
/* 62 */           a(entityhuman, new ChatMessage(getName() + ".update", new Object[] { iblockstate.getName(), a(iblockdata1, iblockstate) }));
/*    */         } else {
/* 64 */           iblockstate = a((Iterable)collection, iblockstate, entityhuman.ep());
/* 65 */           String s2 = iblockstate.getName();
/*    */           
/* 67 */           nbttagcompound.setString(s, s2);
/* 68 */           a(entityhuman, new ChatMessage(getName() + ".select", new Object[] { s2, a(iblockdata, iblockstate) }));
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private static <T extends Comparable<T>> IBlockData a(IBlockData iblockdata, IBlockState<T> iblockstate, boolean flag) {
/* 76 */     return iblockdata.set(iblockstate, a(iblockstate.getValues(), iblockdata.get(iblockstate), flag));
/*    */   }
/*    */   
/*    */   private static <T> T a(Iterable<T> iterable, @Nullable T t0, boolean flag) {
/* 80 */     return flag ? SystemUtils.<T>b(iterable, t0) : SystemUtils.<T>a(iterable, t0);
/*    */   }
/*    */   
/*    */   private static void a(EntityHuman entityhuman, IChatBaseComponent ichatbasecomponent) {
/* 84 */     ((EntityPlayer)entityhuman).a(ichatbasecomponent, ChatMessageType.GAME_INFO, SystemUtils.b);
/*    */   }
/*    */   
/*    */   private static <T extends Comparable<T>> String a(IBlockData iblockdata, IBlockState<T> iblockstate) {
/* 88 */     return iblockstate.a((T)iblockdata.get(iblockstate));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemDebugStick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */