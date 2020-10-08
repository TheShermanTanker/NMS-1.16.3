/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import com.google.common.base.Preconditions;
/*    */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityEnderSignal;
/*    */ import net.minecraft.server.v1_16_R2.Items;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*    */ import org.bukkit.entity.EnderSignal;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class CraftEnderSignal extends CraftEntity implements EnderSignal {
/*    */   public CraftEnderSignal(CraftServer server, EntityEnderSignal entity) {
/* 16 */     super(server, (Entity)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityEnderSignal getHandle() {
/* 21 */     return (EntityEnderSignal)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 26 */     return "CraftEnderSignal";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 31 */     return EntityType.ENDER_SIGNAL;
/*    */   }
/*    */ 
/*    */   
/*    */   public Location getTargetLocation() {
/* 36 */     return new Location(getWorld(), (getHandle()).targetX, (getHandle()).targetY, (getHandle()).targetZ, (getHandle()).yaw, (getHandle()).pitch);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTargetLocation(Location location) {
/* 41 */     Preconditions.checkArgument(getWorld().equals(location.getWorld()), "Cannot target EnderSignal across worlds");
/* 42 */     getHandle().a(new BlockPosition(location.getX(), location.getY(), location.getZ()));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean getDropItem() {
/* 47 */     return (getHandle()).shouldDropItem;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDropItem(boolean shouldDropItem) {
/* 52 */     (getHandle()).shouldDropItem = shouldDropItem;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getItem() {
/* 57 */     return CraftItemStack.asBukkitCopy(getHandle().getItem());
/*    */   }
/*    */ 
/*    */   
/*    */   public void setItem(ItemStack item) {
/* 62 */     getHandle().setItem((item != null) ? CraftItemStack.asNMSCopy(item) : Items.ENDER_EYE.createItemStack());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getDespawnTimer() {
/* 67 */     return (getHandle()).despawnTimer;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDespawnTimer(int time) {
/* 72 */     (getHandle()).despawnTimer = time;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftEnderSignal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */