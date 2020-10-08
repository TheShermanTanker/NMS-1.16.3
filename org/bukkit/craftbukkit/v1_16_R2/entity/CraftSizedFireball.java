/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityFireball;
/*    */ import net.minecraft.server.v1_16_R2.EntityFireballFireball;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*    */ import org.bukkit.entity.SizedFireball;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class CraftSizedFireball extends CraftFireball implements SizedFireball {
/*    */   public CraftSizedFireball(CraftServer server, EntityFireballFireball entity) {
/* 13 */     super(server, (EntityFireball)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getDisplayItem() {
/* 18 */     if (getHandle().getItem().isEmpty()) {
/* 19 */       return new ItemStack(Material.FIRE_CHARGE);
/*    */     }
/* 21 */     return CraftItemStack.asBukkitCopy(getHandle().getItem());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setDisplayItem(ItemStack item) {
/* 27 */     getHandle().setItem(CraftItemStack.asNMSCopy(item));
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityFireballFireball getHandle() {
/* 32 */     return (EntityFireballFireball)this.entity;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftSizedFireball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */