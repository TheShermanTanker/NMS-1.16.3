/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ItemRecord
/*    */   extends Item {
/*  8 */   private static final Map<SoundEffect, ItemRecord> a = Maps.newHashMap();
/*    */   private final int b;
/*    */   private final SoundEffect c;
/*    */   
/*    */   protected ItemRecord(int i, SoundEffect soundeffect, Item.Info item_info) {
/* 13 */     super(item_info);
/* 14 */     this.b = i;
/* 15 */     this.c = soundeffect;
/* 16 */     a.put(this.c, this);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult a(ItemActionContext itemactioncontext) {
/* 21 */     World world = itemactioncontext.getWorld();
/* 22 */     BlockPosition blockposition = itemactioncontext.getClickPosition();
/* 23 */     IBlockData iblockdata = world.getType(blockposition);
/*    */     
/* 25 */     if (iblockdata.a(Blocks.JUKEBOX) && !((Boolean)iblockdata.get(BlockJukeBox.HAS_RECORD)).booleanValue()) {
/* 26 */       ItemStack itemstack = itemactioncontext.getItemStack();
/*    */       
/* 28 */       if (!world.isClientSide) {
/* 29 */         return EnumInteractionResult.SUCCESS;
/*    */       }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 40 */       return EnumInteractionResult.a(world.isClientSide);
/*    */     } 
/* 42 */     return EnumInteractionResult.PASS;
/*    */   }
/*    */ 
/*    */   
/*    */   public int f() {
/* 47 */     return this.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */