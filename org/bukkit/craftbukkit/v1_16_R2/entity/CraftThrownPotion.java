/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import java.util.Collection;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityPotion;
/*    */ import net.minecraft.server.v1_16_R2.IProjectile;
/*    */ import net.minecraft.server.v1_16_R2.MobEffect;
/*    */ import net.minecraft.server.v1_16_R2.PotionUtil;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.potion.CraftPotionUtil;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ 
/*    */ public class CraftThrownPotion extends CraftProjectile implements ThrownPotion {
/*    */   public CraftThrownPotion(CraftServer server, EntityPotion entity) {
/* 20 */     super(server, (IProjectile)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<PotionEffect> getEffects() {
/* 25 */     ImmutableList.Builder<PotionEffect> builder = ImmutableList.builder();
/* 26 */     for (MobEffect effect : PotionUtil.getEffects(getHandle().getItem())) {
/* 27 */       builder.add(CraftPotionUtil.toBukkit(effect));
/*    */     }
/* 29 */     return (Collection<PotionEffect>)builder.build();
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getItem() {
/* 34 */     return CraftItemStack.asBukkitCopy(getHandle().getItem());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setItem(ItemStack item) {
/* 40 */     Validate.notNull(item, "ItemStack cannot be null.");
/*    */ 
/*    */     
/* 43 */     Validate.isTrue((item.getType() == Material.LINGERING_POTION || item.getType() == Material.SPLASH_POTION), "ItemStack must be a lingering or splash potion. This item stack was " + item.getType() + ".");
/*    */     
/* 45 */     getHandle().setItem(CraftItemStack.asNMSCopy(item));
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityPotion getHandle() {
/* 50 */     return (EntityPotion)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 55 */     return EntityType.SPLASH_POTION;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftThrownPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */