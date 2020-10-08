/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityProjectileThrowable;
/*    */ import net.minecraft.server.v1_16_R2.IMaterial;
/*    */ import net.minecraft.server.v1_16_R2.IProjectile;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public abstract class CraftThrowableProjectile extends CraftProjectile implements ThrowableProjectile {
/*    */   public CraftThrowableProjectile(CraftServer server, EntityProjectileThrowable entity) {
/* 12 */     super(server, (IProjectile)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getItem() {
/* 17 */     if (getHandle().getItem().isEmpty()) {
/* 18 */       return CraftItemStack.asBukkitCopy(new ItemStack((IMaterial)getHandle().getDefaultItemPublic()));
/*    */     }
/* 20 */     return CraftItemStack.asBukkitCopy(getHandle().getItem());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setItem(ItemStack item) {
/* 26 */     getHandle().setItem(CraftItemStack.asNMSCopy(item));
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityProjectileThrowable getHandle() {
/* 31 */     return (EntityProjectileThrowable)this.entity;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftThrowableProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */