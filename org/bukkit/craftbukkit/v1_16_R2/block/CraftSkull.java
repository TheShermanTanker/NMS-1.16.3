/*     */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*     */ 
/*     */ import com.destroystokyo.paper.profile.CraftPlayerProfile;
/*     */ import com.destroystokyo.paper.profile.PlayerProfile;
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*     */ import net.minecraft.server.v1_16_R2.TileEntity;
/*     */ import net.minecraft.server.v1_16_R2.TileEntitySkull;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.SkullType;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.block.Skull;
/*     */ import org.bukkit.block.data.BlockData;
/*     */ import org.bukkit.block.data.Directional;
/*     */ import org.bukkit.block.data.Rotatable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
/*     */ 
/*     */ public class CraftSkull extends CraftBlockEntityState<TileEntitySkull> implements Skull {
/*     */   private static final int MAX_OWNER_LENGTH = 16;
/*     */   private GameProfile profile;
/*     */   
/*     */   public CraftSkull(Block block) {
/*  28 */     super(block, TileEntitySkull.class);
/*     */   }
/*     */   
/*     */   public CraftSkull(Material material, TileEntitySkull te) {
/*  32 */     super(material, te);
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(TileEntitySkull skull) {
/*  37 */     super.load(skull);
/*     */     
/*  39 */     this.profile = skull.gameProfile;
/*     */   }
/*     */   
/*     */   static int getSkullType(SkullType type) {
/*  43 */     switch (type)
/*     */     
/*     */     { default:
/*  46 */         return 0;
/*     */       case SKELETON_WALL_SKULL:
/*  48 */         return 1;
/*     */       case WITHER_SKELETON_SKULL:
/*  50 */         return 2;
/*     */       case WITHER_SKELETON_WALL_SKULL:
/*  52 */         return 3;
/*     */       case ZOMBIE_HEAD:
/*  54 */         return 4;
/*     */       case ZOMBIE_WALL_HEAD:
/*  56 */         break; }  return 5;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasOwner() {
/*  62 */     return (this.profile != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOwner() {
/*  67 */     return hasOwner() ? this.profile.getName() : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setOwner(String name) {
/*  72 */     if (name == null || name.length() > 16) {
/*  73 */       return false;
/*     */     }
/*     */     
/*  76 */     GameProfile profile = MinecraftServer.getServer().getUserCache().getProfile(name);
/*  77 */     if (profile == null) {
/*  78 */       return false;
/*     */     }
/*     */     
/*  81 */     this.profile = profile;
/*  82 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public OfflinePlayer getOwningPlayer() {
/*  87 */     if (this.profile != null) {
/*  88 */       if (this.profile.getId() != null) {
/*  89 */         return Bukkit.getOfflinePlayer(this.profile.getId());
/*     */       }
/*     */       
/*  92 */       if (this.profile.getName() != null) {
/*  93 */         return Bukkit.getOfflinePlayer(this.profile.getName());
/*     */       }
/*     */     } 
/*     */     
/*  97 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOwningPlayer(OfflinePlayer player) {
/* 102 */     Preconditions.checkNotNull(player, "player");
/*     */     
/* 104 */     if (player instanceof CraftPlayer) {
/* 105 */       this.profile = ((CraftPlayer)player).getProfile();
/*     */     } else {
/* 107 */       this.profile = new GameProfile(player.getUniqueId(), player.getName());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPlayerProfile(PlayerProfile profile) {
/* 114 */     Preconditions.checkNotNull(profile, "profile");
/* 115 */     this.profile = CraftPlayerProfile.asAuthlibCopy(profile);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public PlayerProfile getPlayerProfile() {
/* 121 */     return (this.profile != null) ? CraftPlayerProfile.asBukkitCopy(this.profile) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockFace getRotation() {
/* 127 */     BlockData blockData = getBlockData();
/* 128 */     return (blockData instanceof Rotatable) ? ((Rotatable)blockData).getRotation() : ((Directional)blockData).getFacing();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotation(BlockFace rotation) {
/* 133 */     BlockData blockData = getBlockData();
/* 134 */     if (blockData instanceof Rotatable) {
/* 135 */       ((Rotatable)blockData).setRotation(rotation);
/*     */     } else {
/* 137 */       ((Directional)blockData).setFacing(rotation);
/*     */     } 
/* 139 */     setBlockData(blockData);
/*     */   }
/*     */ 
/*     */   
/*     */   public SkullType getSkullType() {
/* 144 */     switch (getType()) {
/*     */       case SKELETON_SKULL:
/*     */       case SKELETON_WALL_SKULL:
/* 147 */         return SkullType.SKELETON;
/*     */       case WITHER_SKELETON_SKULL:
/*     */       case WITHER_SKELETON_WALL_SKULL:
/* 150 */         return SkullType.WITHER;
/*     */       case ZOMBIE_HEAD:
/*     */       case ZOMBIE_WALL_HEAD:
/* 153 */         return SkullType.ZOMBIE;
/*     */       case PLAYER_HEAD:
/*     */       case PLAYER_WALL_HEAD:
/* 156 */         return SkullType.PLAYER;
/*     */       case CREEPER_HEAD:
/*     */       case CREEPER_WALL_HEAD:
/* 159 */         return SkullType.CREEPER;
/*     */       case DRAGON_HEAD:
/*     */       case DRAGON_WALL_HEAD:
/* 162 */         return SkullType.DRAGON;
/*     */     } 
/* 164 */     throw new IllegalArgumentException("Unknown SkullType for " + getType());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSkullType(SkullType skullType) {
/* 170 */     throw new UnsupportedOperationException("Must change block type");
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyTo(TileEntitySkull skull) {
/* 175 */     super.applyTo(skull);
/*     */     
/* 177 */     if (getSkullType() == SkullType.PLAYER)
/* 178 */       skull.setGameProfile(this.profile); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftSkull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */