/*     */ package org.bukkit.craftbukkit.v1_16_R2.projectiles;
/*     */ import java.util.Random;
/*     */ import net.minecraft.server.v1_16_R2.BlockDispenser;
/*     */ import net.minecraft.server.v1_16_R2.Entity;
/*     */ import net.minecraft.server.v1_16_R2.EntityArrow;
/*     */ import net.minecraft.server.v1_16_R2.EntityEgg;
/*     */ import net.minecraft.server.v1_16_R2.EntityEnderPearl;
/*     */ import net.minecraft.server.v1_16_R2.EntityFireball;
/*     */ import net.minecraft.server.v1_16_R2.EntityPotion;
/*     */ import net.minecraft.server.v1_16_R2.EntityProjectile;
/*     */ import net.minecraft.server.v1_16_R2.EntitySmallFireball;
/*     */ import net.minecraft.server.v1_16_R2.EntitySnowball;
/*     */ import net.minecraft.server.v1_16_R2.EntitySpectralArrow;
/*     */ import net.minecraft.server.v1_16_R2.EntityThrownExpBottle;
/*     */ import net.minecraft.server.v1_16_R2.EntityTippedArrow;
/*     */ import net.minecraft.server.v1_16_R2.EntityTypes;
/*     */ import net.minecraft.server.v1_16_R2.EnumDirection;
/*     */ import net.minecraft.server.v1_16_R2.IBlockState;
/*     */ import net.minecraft.server.v1_16_R2.IPosition;
/*     */ import net.minecraft.server.v1_16_R2.ISourceBlock;
/*     */ import net.minecraft.server.v1_16_R2.MathHelper;
/*     */ import net.minecraft.server.v1_16_R2.SourceBlock;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityDispenser;
/*     */ import net.minecraft.server.v1_16_R2.World;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.potion.CraftPotionUtil;
/*     */ import org.bukkit.entity.AbstractArrow;
/*     */ import org.bukkit.entity.Egg;
/*     */ import org.bukkit.entity.EnderPearl;
/*     */ import org.bukkit.entity.Fireball;
/*     */ import org.bukkit.entity.LingeringPotion;
/*     */ import org.bukkit.entity.Projectile;
/*     */ import org.bukkit.entity.SmallFireball;
/*     */ import org.bukkit.entity.Snowball;
/*     */ import org.bukkit.entity.SpectralArrow;
/*     */ import org.bukkit.entity.ThrownExpBottle;
/*     */ import org.bukkit.entity.ThrownPotion;
/*     */ import org.bukkit.entity.TippedArrow;
/*     */ import org.bukkit.entity.WitherSkull;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.potion.PotionData;
/*     */ import org.bukkit.potion.PotionType;
/*     */ import org.bukkit.projectiles.BlockProjectileSource;
/*     */ import org.bukkit.projectiles.ProjectileSource;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public class CraftBlockProjectileSource implements BlockProjectileSource {
/*     */   public CraftBlockProjectileSource(TileEntityDispenser dispenserBlock) {
/*  52 */     this.dispenserBlock = dispenserBlock;
/*     */   }
/*     */   private final TileEntityDispenser dispenserBlock;
/*     */   
/*     */   public Block getBlock() {
/*  57 */     return this.dispenserBlock.getWorld().getWorld().getBlockAt(this.dispenserBlock.getPosition().getX(), this.dispenserBlock.getPosition().getY(), this.dispenserBlock.getPosition().getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends Projectile> T launchProjectile(Class<? extends T> projectile) {
/*  62 */     return launchProjectile(projectile, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends Projectile> T launchProjectile(Class<? extends T> projectile, Vector velocity) {
/*  67 */     Validate.isTrue((getBlock().getType() == Material.DISPENSER), "Block is no longer dispenser");
/*     */     
/*  69 */     SourceBlock isourceblock = new SourceBlock((WorldServer)this.dispenserBlock.getWorld(), this.dispenserBlock.getPosition());
/*     */     
/*  71 */     IPosition iposition = BlockDispenser.a((ISourceBlock)isourceblock);
/*  72 */     EnumDirection enumdirection = (EnumDirection)isourceblock.getBlockData().get((IBlockState)BlockDispenser.FACING);
/*  73 */     World world = this.dispenserBlock.getWorld();
/*  74 */     Entity launch = null;
/*     */     
/*  76 */     if (Snowball.class.isAssignableFrom(projectile)) {
/*  77 */       EntitySnowball entitySnowball = new EntitySnowball(world, iposition.getX(), iposition.getY(), iposition.getZ());
/*  78 */     } else if (Egg.class.isAssignableFrom(projectile)) {
/*  79 */       EntityEgg entityEgg = new EntityEgg(world, iposition.getX(), iposition.getY(), iposition.getZ());
/*  80 */     } else if (EnderPearl.class.isAssignableFrom(projectile)) {
/*  81 */       EntityEnderPearl entityEnderPearl = new EntityEnderPearl(world, null);
/*  82 */       entityEnderPearl.setPosition(iposition.getX(), iposition.getY(), iposition.getZ());
/*  83 */     } else if (ThrownExpBottle.class.isAssignableFrom(projectile)) {
/*  84 */       EntityThrownExpBottle entityThrownExpBottle = new EntityThrownExpBottle(world, iposition.getX(), iposition.getY(), iposition.getZ());
/*  85 */     } else if (ThrownPotion.class.isAssignableFrom(projectile)) {
/*  86 */       if (LingeringPotion.class.isAssignableFrom(projectile)) {
/*  87 */         EntityPotion entityPotion = new EntityPotion(world, iposition.getX(), iposition.getY(), iposition.getZ());
/*  88 */         entityPotion.setItem(CraftItemStack.asNMSCopy(new ItemStack(Material.LINGERING_POTION, 1)));
/*     */       } else {
/*  90 */         EntityPotion entityPotion = new EntityPotion(world, iposition.getX(), iposition.getY(), iposition.getZ());
/*  91 */         entityPotion.setItem(CraftItemStack.asNMSCopy(new ItemStack(Material.SPLASH_POTION, 1)));
/*     */       } 
/*  93 */     } else if (AbstractArrow.class.isAssignableFrom(projectile)) {
/*  94 */       EntityTippedArrow entityTippedArrow; if (TippedArrow.class.isAssignableFrom(projectile)) {
/*  95 */         entityTippedArrow = new EntityTippedArrow(world, iposition.getX(), iposition.getY(), iposition.getZ());
/*  96 */         entityTippedArrow.setType(CraftPotionUtil.fromBukkit(new PotionData(PotionType.WATER, false, false)));
/*  97 */       } else if (SpectralArrow.class.isAssignableFrom(projectile)) {
/*  98 */         EntitySpectralArrow entitySpectralArrow = new EntitySpectralArrow(world, iposition.getX(), iposition.getY(), iposition.getZ());
/*     */       } else {
/* 100 */         entityTippedArrow = new EntityTippedArrow(world, iposition.getX(), iposition.getY(), iposition.getZ());
/*     */       } 
/* 102 */       ((EntityArrow)entityTippedArrow).fromPlayer = EntityArrow.PickupStatus.ALLOWED;
/* 103 */       ((EntityArrow)entityTippedArrow).projectileSource = (ProjectileSource)this;
/* 104 */     } else if (Fireball.class.isAssignableFrom(projectile)) {
/* 105 */       double d0 = iposition.getX() + (enumdirection.getAdjacentX() * 0.3F);
/* 106 */       double d1 = iposition.getY() + (enumdirection.getAdjacentY() * 0.3F);
/* 107 */       double d2 = iposition.getZ() + (enumdirection.getAdjacentZ() * 0.3F);
/* 108 */       Random random = world.random;
/* 109 */       double d3 = random.nextGaussian() * 0.05D + enumdirection.getAdjacentX();
/* 110 */       double d4 = random.nextGaussian() * 0.05D + enumdirection.getAdjacentY();
/* 111 */       double d5 = random.nextGaussian() * 0.05D + enumdirection.getAdjacentZ();
/*     */       
/* 113 */       if (SmallFireball.class.isAssignableFrom(projectile)) {
/* 114 */         EntitySmallFireball entitySmallFireball = new EntitySmallFireball(world, null, d0, d1, d2);
/* 115 */       } else if (WitherSkull.class.isAssignableFrom(projectile)) {
/* 116 */         launch = EntityTypes.WITHER_SKULL.a(world);
/* 117 */         launch.setPosition(d0, d1, d2);
/* 118 */         double d6 = MathHelper.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
/*     */         
/* 120 */         ((EntityFireball)launch).dirX = d3 / d6 * 0.1D;
/* 121 */         ((EntityFireball)launch).dirY = d4 / d6 * 0.1D;
/* 122 */         ((EntityFireball)launch).dirZ = d5 / d6 * 0.1D;
/*     */       } else {
/* 124 */         launch = EntityTypes.FIREBALL.a(world);
/* 125 */         launch.setPosition(d0, d1, d2);
/* 126 */         double d6 = MathHelper.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
/*     */         
/* 128 */         ((EntityFireball)launch).dirX = d3 / d6 * 0.1D;
/* 129 */         ((EntityFireball)launch).dirY = d4 / d6 * 0.1D;
/* 130 */         ((EntityFireball)launch).dirZ = d5 / d6 * 0.1D;
/*     */       } 
/*     */       
/* 133 */       ((EntityFireball)launch).projectileSource = (ProjectileSource)this;
/*     */     } 
/*     */     
/* 136 */     Validate.notNull(launch, "Projectile not supported");
/*     */     
/* 138 */     if (launch instanceof IProjectile) {
/* 139 */       if (launch instanceof EntityProjectile) {
/* 140 */         ((EntityProjectile)launch).projectileSource = (ProjectileSource)this;
/*     */       }
/*     */       
/* 143 */       float a = 6.0F;
/* 144 */       float b = 1.1F;
/* 145 */       if (launch instanceof EntityPotion || launch instanceof ThrownExpBottle) {
/*     */         
/* 147 */         a *= 0.5F;
/* 148 */         b *= 1.25F;
/*     */       } 
/*     */       
/* 151 */       ((IProjectile)launch).shoot(enumdirection.getAdjacentX(), (enumdirection.getAdjacentY() + 0.1F), enumdirection.getAdjacentZ(), b, a);
/*     */     } 
/*     */     
/* 154 */     if (velocity != null) {
/* 155 */       ((Projectile)launch.getBukkitEntity()).setVelocity(velocity);
/*     */     }
/*     */     
/* 158 */     world.addEntity(launch);
/* 159 */     return (T)launch.getBukkitEntity();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\projectiles\CraftBlockProjectileSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */