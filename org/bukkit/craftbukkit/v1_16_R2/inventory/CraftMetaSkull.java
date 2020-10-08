/*     */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*     */ import com.destroystokyo.paper.profile.CraftPlayerProfile;
/*     */ import com.destroystokyo.paper.profile.PlayerProfile;
/*     */ import com.google.common.base.Predicates;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.util.concurrent.Futures;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.server.v1_16_R2.EntityPlayer;
/*     */ import net.minecraft.server.v1_16_R2.GameProfileSerializer;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*     */ import net.minecraft.server.v1_16_R2.NBTBase;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import net.minecraft.server.v1_16_R2.TileEntitySkull;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.configuration.serialization.DelegateDeserialization;
/*     */ import org.bukkit.inventory.meta.Damageable;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.SkullMeta;
/*     */ 
/*     */ @DelegateDeserialization(CraftMetaItem.SerializableMeta.class)
/*     */ class CraftMetaSkull extends CraftMetaItem implements SkullMeta {
/*  26 */   static final CraftMetaItem.ItemMetaKey SKULL_PROFILE = new CraftMetaItem.ItemMetaKey("SkullProfile");
/*     */   
/*  28 */   static final CraftMetaItem.ItemMetaKey SKULL_OWNER = new CraftMetaItem.ItemMetaKey("SkullOwner", "skull-owner");
/*     */   
/*     */   static final int MAX_OWNER_LENGTH = 16;
/*     */   private GameProfile profile;
/*     */   private NBTTagCompound serializedProfile;
/*     */   
/*     */   CraftMetaSkull(CraftMetaItem meta) {
/*  35 */     super(meta);
/*  36 */     if (!(meta instanceof CraftMetaSkull)) {
/*     */       return;
/*     */     }
/*  39 */     CraftMetaSkull skullMeta = (CraftMetaSkull)meta;
/*  40 */     setProfile(skullMeta.profile);
/*     */   }
/*     */   
/*     */   CraftMetaSkull(NBTTagCompound tag) {
/*  44 */     super(tag);
/*     */     
/*  46 */     if (tag.hasKeyOfType(SKULL_OWNER.NBT, 10)) {
/*  47 */       setProfile(GameProfileSerializer.deserialize(tag.getCompound(SKULL_OWNER.NBT)));
/*  48 */     } else if (tag.hasKeyOfType(SKULL_OWNER.NBT, 8) && !tag.getString(SKULL_OWNER.NBT).isEmpty()) {
/*  49 */       setProfile(new GameProfile(null, tag.getString(SKULL_OWNER.NBT)));
/*     */     } 
/*     */   }
/*     */   
/*     */   CraftMetaSkull(Map<String, Object> map) {
/*  54 */     super(map);
/*  55 */     if (this.profile == null) {
/*  56 */       setOwner(CraftMetaItem.SerializableMeta.getString(map, SKULL_OWNER.BUKKIT, true));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void deserializeInternal(NBTTagCompound tag, Object context) {
/*  62 */     super.deserializeInternal(tag, context);
/*     */     
/*  64 */     if (tag.hasKeyOfType(SKULL_PROFILE.NBT, 10)) {
/*  65 */       setProfile(GameProfileSerializer.deserialize(tag.getCompound(SKULL_PROFILE.NBT)));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void serializeInternal(Map<String, NBTBase> internalTags) {
/*  71 */     if (this.profile != null) {
/*  72 */       internalTags.put(SKULL_PROFILE.NBT, this.serializedProfile);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void setProfile(GameProfile profile) {
/*  78 */     if (profile != null) {
/*  79 */       CraftPlayerProfile paperProfile = new CraftPlayerProfile(profile);
/*  80 */       paperProfile.completeFromCache(false, true);
/*  81 */       profile = paperProfile.getGameProfile();
/*     */     } 
/*     */     
/*  84 */     this.profile = profile;
/*  85 */     this.serializedProfile = (profile == null) ? null : GameProfileSerializer.serialize(new NBTTagCompound(), profile);
/*     */   }
/*     */ 
/*     */   
/*     */   void applyToItem(NBTTagCompound tag) {
/*  90 */     super.applyToItem(tag);
/*     */     
/*  92 */     if (this.profile != null) {
/*     */ 
/*     */       
/*  95 */       setProfile((GameProfile)Futures.getUnchecked(TileEntitySkull.b(this.profile, Predicates.alwaysTrue(), true)));
/*     */       
/*  97 */       tag.set(SKULL_OWNER.NBT, (NBTBase)this.serializedProfile);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   boolean isEmpty() {
/* 103 */     return (super.isEmpty() && isSkullEmpty());
/*     */   }
/*     */   
/*     */   boolean isSkullEmpty() {
/* 107 */     return (this.profile == null);
/*     */   }
/*     */ 
/*     */   
/*     */   boolean applicableTo(Material type) {
/* 112 */     switch (type) {
/*     */       case CREEPER_HEAD:
/*     */       case CREEPER_WALL_HEAD:
/*     */       case DRAGON_HEAD:
/*     */       case DRAGON_WALL_HEAD:
/*     */       case PLAYER_HEAD:
/*     */       case PLAYER_WALL_HEAD:
/*     */       case SKELETON_SKULL:
/*     */       case SKELETON_WALL_SKULL:
/*     */       case WITHER_SKELETON_SKULL:
/*     */       case WITHER_SKELETON_WALL_SKULL:
/*     */       case ZOMBIE_HEAD:
/*     */       case ZOMBIE_WALL_HEAD:
/* 125 */         return true;
/*     */     } 
/* 127 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CraftMetaSkull clone() {
/* 133 */     return (CraftMetaSkull)super.clone();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasOwner() {
/* 138 */     return (this.profile != null && this.profile.getName() != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOwner() {
/* 143 */     return hasOwner() ? this.profile.getName() : null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPlayerProfile(@Nullable PlayerProfile profile) {
/* 149 */     setProfile((profile == null) ? null : CraftPlayerProfile.asAuthlibCopy(profile));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public PlayerProfile getPlayerProfile() {
/* 155 */     return (this.profile != null) ? CraftPlayerProfile.asBukkitCopy(this.profile) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public OfflinePlayer getOwningPlayer() {
/* 161 */     if (hasOwner()) {
/* 162 */       if (this.profile.getId() != null) {
/* 163 */         return Bukkit.getOfflinePlayer(this.profile.getId());
/*     */       }
/*     */       
/* 166 */       if (this.profile.getName() != null) {
/* 167 */         return Bukkit.getOfflinePlayer(this.profile.getName());
/*     */       }
/*     */     } 
/*     */     
/* 171 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setOwner(String name) {
/* 176 */     if (name != null && name.length() > 16) {
/* 177 */       return false;
/*     */     }
/*     */     
/* 180 */     if (name == null) {
/* 181 */       setProfile(null);
/*     */     } else {
/*     */       
/* 184 */       GameProfile newProfile = null;
/* 185 */       EntityPlayer player = MinecraftServer.getServer().getPlayerList().getPlayer(name);
/* 186 */       if (player != null) newProfile = player.getProfile(); 
/* 187 */       if (newProfile == null) newProfile = new GameProfile(null, name); 
/* 188 */       setProfile(newProfile);
/*     */     } 
/*     */ 
/*     */     
/* 192 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setOwningPlayer(OfflinePlayer owner) {
/* 197 */     if (owner == null) {
/* 198 */       setProfile(null);
/* 199 */     } else if (owner instanceof CraftPlayer) {
/* 200 */       setProfile(((CraftPlayer)owner).getProfile());
/*     */     } else {
/* 202 */       setProfile(new GameProfile(owner.getUniqueId(), owner.getName()));
/*     */     } 
/*     */     
/* 205 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   int applyHash() {
/* 211 */     int original = super.applyHash(), hash = original;
/* 212 */     if (hasOwner()) {
/* 213 */       hash = 61 * hash + this.profile.hashCode();
/*     */     }
/* 215 */     return (original != hash) ? (CraftMetaSkull.class.hashCode() ^ hash) : hash;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean equalsCommon(CraftMetaItem meta) {
/* 220 */     if (!super.equalsCommon(meta)) {
/* 221 */       return false;
/*     */     }
/* 223 */     if (meta instanceof CraftMetaSkull) {
/* 224 */       CraftMetaSkull that = (CraftMetaSkull)meta;
/*     */ 
/*     */       
/* 227 */       return (this.profile != null) ? ((that.profile != null && this.serializedProfile.equals(that.serializedProfile))) : ((that.profile == null));
/*     */     } 
/* 229 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean notUncommon(CraftMetaItem meta) {
/* 234 */     return (super.notUncommon(meta) && (meta instanceof CraftMetaSkull || isSkullEmpty()));
/*     */   }
/*     */ 
/*     */   
/*     */   ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
/* 239 */     super.serialize(builder);
/* 240 */     if (hasOwner()) {
/* 241 */       return builder.put(SKULL_OWNER.BUKKIT, this.profile.getName());
/*     */     }
/* 243 */     return builder;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftMetaSkull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */