/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.base.Predicate;
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.util.UUID;
/*    */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.StringUtils;
/*    */ 
/*    */ public class ItemSkullPlayer extends ItemBlockWallable {
/*    */   public ItemSkullPlayer(Block block, Block block1, Item.Info item_info) {
/* 10 */     super(block, block1, item_info);
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatBaseComponent h(ItemStack itemstack) {
/* 15 */     if (itemstack.getItem() == Items.PLAYER_HEAD && itemstack.hasTag()) {
/* 16 */       String s = null;
/* 17 */       NBTTagCompound nbttagcompound = itemstack.getTag();
/*    */       
/* 19 */       if (nbttagcompound.hasKeyOfType("SkullOwner", 8)) {
/* 20 */         s = nbttagcompound.getString("SkullOwner");
/* 21 */       } else if (nbttagcompound.hasKeyOfType("SkullOwner", 10)) {
/* 22 */         NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("SkullOwner");
/*    */         
/* 24 */         if (nbttagcompound1.hasKeyOfType("Name", 8)) {
/* 25 */           s = nbttagcompound1.getString("Name");
/*    */         }
/*    */       } 
/*    */       
/* 29 */       if (s != null) {
/* 30 */         return new ChatMessage(getName() + ".named", new Object[] { s });
/*    */       }
/*    */     } 
/*    */     
/* 34 */     return super.h(itemstack);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b(final NBTTagCompound nbttagcompound) {
/* 39 */     super.b(nbttagcompound);
/* 40 */     if (nbttagcompound.hasKeyOfType("SkullOwner", 8) && !StringUtils.isBlank(nbttagcompound.getString("SkullOwner"))) {
/* 41 */       GameProfile gameprofile = new GameProfile((UUID)null, nbttagcompound.getString("SkullOwner"));
/*    */ 
/*    */       
/* 44 */       TileEntitySkull.b(gameprofile, new Predicate<GameProfile>()
/*    */           {
/*    */             public boolean apply(GameProfile gameprofile)
/*    */             {
/* 48 */               nbttagcompound.set("SkullOwner", GameProfileSerializer.serialize(new NBTTagCompound(), gameprofile));
/* 49 */               return false;
/*    */             }
/*    */           }false);
/*    */       
/* 53 */       return true;
/*    */     } 
/*    */ 
/*    */     
/* 57 */     NBTTagCompound properties = nbttagcompound.getCompound("SkullOwner").getCompound("Properties");
/* 58 */     for (String key : properties.getKeys()) {
/* 59 */       NBTTagList values = properties.getList(key, 10);
/* 60 */       if (values.size() > 1) {
/* 61 */         NBTBase texture = values.get(values.size() - 1);
/* 62 */         values = new NBTTagList();
/* 63 */         values.add(texture);
/* 64 */         properties.set(key, values);
/*    */       } 
/*    */     } 
/*    */     
/* 68 */     NBTTagList textures = nbttagcompound.getCompound("SkullOwner").getCompound("Properties").getList("textures", 10);
/* 69 */     for (int i = 0; i < textures.size(); i++) {
/* 70 */       if (textures.get(i) instanceof NBTTagCompound && !((NBTTagCompound)textures.get(i)).hasKeyOfType("Signature", 8) && ((NBTTagCompound)textures.get(i)).getString("Value").trim().isEmpty()) {
/* 71 */         nbttagcompound.remove("SkullOwner");
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 76 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemSkullPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */