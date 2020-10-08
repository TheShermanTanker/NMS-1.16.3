/*     */ package org.bukkit.craftbukkit.v1_16_R2;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.io.File;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.server.v1_16_R2.JsonListEntry;
/*     */ import net.minecraft.server.v1_16_R2.NBTBase;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import net.minecraft.server.v1_16_R2.ServerStatisticManager;
/*     */ import net.minecraft.server.v1_16_R2.WhiteListEntry;
/*     */ import net.minecraft.server.v1_16_R2.WorldNBTStorage;
/*     */ import org.bukkit.BanList;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.Statistic;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.configuration.serialization.ConfigurationSerializable;
/*     */ import org.bukkit.configuration.serialization.SerializableAs;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.metadata.MetadataValue;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ @SerializableAs("Player")
/*     */ public class CraftOfflinePlayer implements OfflinePlayer, ConfigurationSerializable {
/*     */   private final GameProfile profile;
/*     */   
/*     */   protected CraftOfflinePlayer(CraftServer server, GameProfile profile) {
/*  34 */     this.server = server;
/*  35 */     this.profile = profile;
/*  36 */     this.storage = server.console.worldNBTStorage;
/*     */   }
/*     */   private final CraftServer server; private final WorldNBTStorage storage;
/*     */   
/*     */   public GameProfile getProfile() {
/*  41 */     return this.profile;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOnline() {
/*  46 */     return (getPlayer() != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  51 */     Player player = getPlayer();
/*  52 */     if (player != null) {
/*  53 */       return player.getName();
/*     */     }
/*     */ 
/*     */     
/*  57 */     if (this.profile.getName() != null) {
/*  58 */       return this.profile.getName();
/*     */     }
/*     */     
/*  61 */     NBTTagCompound data = getBukkitData();
/*     */     
/*  63 */     if (data != null && 
/*  64 */       data.hasKey("lastKnownName")) {
/*  65 */       return data.getString("lastKnownName");
/*     */     }
/*     */ 
/*     */     
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public UUID getUniqueId() {
/*  74 */     return this.profile.getId();
/*     */   }
/*     */   
/*     */   public Server getServer() {
/*  78 */     return this.server;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOp() {
/*  83 */     return this.server.getHandle().isOp(this.profile);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOp(boolean value) {
/*  88 */     if (value == isOp()) {
/*     */       return;
/*     */     }
/*     */     
/*  92 */     if (value) {
/*  93 */       this.server.getHandle().addOp(this.profile);
/*     */     } else {
/*  95 */       this.server.getHandle().removeOp(this.profile);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBanned() {
/* 101 */     if (getName() == null) {
/* 102 */       return false;
/*     */     }
/*     */     
/* 105 */     return this.server.getBanList(BanList.Type.NAME).isBanned(getName());
/*     */   }
/*     */   
/*     */   public void setBanned(boolean value) {
/* 109 */     if (getName() == null) {
/*     */       return;
/*     */     }
/*     */     
/* 113 */     if (value) {
/* 114 */       this.server.getBanList(BanList.Type.NAME).addBan(getName(), null, null, null);
/*     */     } else {
/* 116 */       this.server.getBanList(BanList.Type.NAME).pardon(getName());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isWhitelisted() {
/* 122 */     return this.server.getHandle().getWhitelist().isWhitelisted(this.profile);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWhitelisted(boolean value) {
/* 127 */     if (value) {
/* 128 */       this.server.getHandle().getWhitelist().add((JsonListEntry)new WhiteListEntry(this.profile));
/*     */     } else {
/* 130 */       this.server.getHandle().getWhitelist().remove(this.profile);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, Object> serialize() {
/* 136 */     Map<String, Object> result = new LinkedHashMap<>();
/*     */     
/* 138 */     result.put("UUID", this.profile.getId().toString());
/*     */     
/* 140 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static OfflinePlayer deserialize(Map<String, Object> args) {
/* 145 */     if (args.get("name") != null) {
/* 146 */       return Bukkit.getServer().getOfflinePlayer((String)args.get("name"));
/*     */     }
/*     */     
/* 149 */     return Bukkit.getServer().getOfflinePlayer(UUID.fromString((String)args.get("UUID")));
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 154 */     return getClass().getSimpleName() + "[UUID=" + this.profile.getId() + "]";
/*     */   }
/*     */ 
/*     */   
/*     */   public Player getPlayer() {
/* 159 */     return this.server.getPlayer(getUniqueId());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 164 */     if (obj == null || !(obj instanceof OfflinePlayer)) {
/* 165 */       return false;
/*     */     }
/*     */     
/* 168 */     OfflinePlayer other = (OfflinePlayer)obj;
/* 169 */     if (getUniqueId() == null || other.getUniqueId() == null) {
/* 170 */       return false;
/*     */     }
/*     */     
/* 173 */     return getUniqueId().equals(other.getUniqueId());
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 178 */     int hash = 5;
/* 179 */     hash = 97 * hash + ((getUniqueId() != null) ? getUniqueId().hashCode() : 0);
/* 180 */     return hash;
/*     */   }
/*     */   
/*     */   private NBTTagCompound getData() {
/* 184 */     return this.storage.getPlayerData(getUniqueId().toString());
/*     */   }
/*     */   
/*     */   private NBTTagCompound getBukkitData() {
/* 188 */     NBTTagCompound result = getData();
/*     */     
/* 190 */     if (result != null) {
/* 191 */       if (!result.hasKey("bukkit")) {
/* 192 */         result.set("bukkit", (NBTBase)new NBTTagCompound());
/*     */       }
/* 194 */       result = result.getCompound("bukkit");
/*     */     } 
/*     */     
/* 197 */     return result;
/*     */   }
/*     */   
/*     */   private File getDataFile() {
/* 201 */     return new File(this.storage.getPlayerDir(), getUniqueId() + ".dat");
/*     */   }
/*     */ 
/*     */   
/*     */   public long getFirstPlayed() {
/* 206 */     Player player = getPlayer();
/* 207 */     if (player != null) return player.getFirstPlayed();
/*     */     
/* 209 */     NBTTagCompound data = getBukkitData();
/*     */     
/* 211 */     if (data != null) {
/* 212 */       if (data.hasKey("firstPlayed")) {
/* 213 */         return data.getLong("firstPlayed");
/*     */       }
/* 215 */       File file = getDataFile();
/* 216 */       return file.lastModified();
/*     */     } 
/*     */     
/* 219 */     return 0L;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLastPlayed() {
/* 225 */     Player player = getPlayer();
/* 226 */     if (player != null) return player.getLastPlayed();
/*     */     
/* 228 */     NBTTagCompound data = getBukkitData();
/*     */     
/* 230 */     if (data != null) {
/* 231 */       if (data.hasKey("lastPlayed")) {
/* 232 */         return data.getLong("lastPlayed");
/*     */       }
/* 234 */       File file = getDataFile();
/* 235 */       return file.lastModified();
/*     */     } 
/*     */     
/* 238 */     return 0L;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasPlayedBefore() {
/* 244 */     return (getData() != null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLastLogin() {
/* 250 */     Player player = getPlayer();
/* 251 */     if (player != null) return player.getLastLogin();
/*     */     
/* 253 */     NBTTagCompound data = getPaperData();
/*     */     
/* 255 */     if (data != null) {
/* 256 */       if (data.hasKey("LastLogin")) {
/* 257 */         return data.getLong("LastLogin");
/*     */       }
/*     */       
/* 260 */       File file = getDataFile();
/* 261 */       return file.lastModified();
/*     */     } 
/*     */     
/* 264 */     return 0L;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLastSeen() {
/* 270 */     Player player = getPlayer();
/* 271 */     if (player != null) return player.getLastSeen();
/*     */     
/* 273 */     NBTTagCompound data = getPaperData();
/*     */     
/* 275 */     if (data != null) {
/* 276 */       if (data.hasKey("LastSeen")) {
/* 277 */         return data.getLong("LastSeen");
/*     */       }
/*     */       
/* 280 */       File file = getDataFile();
/* 281 */       return file.lastModified();
/*     */     } 
/*     */     
/* 284 */     return 0L;
/*     */   }
/*     */ 
/*     */   
/*     */   private NBTTagCompound getPaperData() {
/* 289 */     NBTTagCompound result = getData();
/*     */     
/* 291 */     if (result != null) {
/* 292 */       if (!result.hasKey("Paper")) {
/* 293 */         result.set("Paper", (NBTBase)new NBTTagCompound());
/*     */       }
/* 295 */       result = result.getCompound("Paper");
/*     */     } 
/*     */     
/* 298 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Location getBedSpawnLocation() {
/* 304 */     NBTTagCompound data = getData();
/* 305 */     if (data == null) return null;
/*     */     
/* 307 */     if (data.hasKey("SpawnX") && data.hasKey("SpawnY") && data.hasKey("SpawnZ")) {
/* 308 */       String spawnWorld = data.getString("SpawnWorld");
/* 309 */       if (spawnWorld.equals("")) {
/* 310 */         spawnWorld = ((World)this.server.getWorlds().get(0)).getName();
/*     */       }
/* 312 */       return new Location(this.server.getWorld(spawnWorld), data.getInt("SpawnX"), data.getInt("SpawnY"), data.getInt("SpawnZ"));
/*     */     } 
/* 314 */     return null;
/*     */   }
/*     */   
/*     */   public void setMetadata(String metadataKey, MetadataValue metadataValue) {
/* 318 */     this.server.getPlayerMetadata().setMetadata(this, metadataKey, metadataValue);
/*     */   }
/*     */   
/*     */   public List<MetadataValue> getMetadata(String metadataKey) {
/* 322 */     return this.server.getPlayerMetadata().getMetadata(this, metadataKey);
/*     */   }
/*     */   
/*     */   public boolean hasMetadata(String metadataKey) {
/* 326 */     return this.server.getPlayerMetadata().hasMetadata(this, metadataKey);
/*     */   }
/*     */   
/*     */   public void removeMetadata(String metadataKey, Plugin plugin) {
/* 330 */     this.server.getPlayerMetadata().removeMetadata(this, metadataKey, plugin);
/*     */   }
/*     */   
/*     */   private ServerStatisticManager getStatisticManager() {
/* 334 */     return this.server.getHandle().getStatisticManager(getUniqueId(), getName());
/*     */   }
/*     */ 
/*     */   
/*     */   public void incrementStatistic(Statistic statistic) {
/* 339 */     if (isOnline()) {
/* 340 */       getPlayer().incrementStatistic(statistic);
/*     */     } else {
/* 342 */       ServerStatisticManager manager = getStatisticManager();
/* 343 */       CraftStatistic.incrementStatistic(manager, statistic);
/* 344 */       manager.save();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void decrementStatistic(Statistic statistic) {
/* 350 */     if (isOnline()) {
/* 351 */       getPlayer().decrementStatistic(statistic);
/*     */     } else {
/* 353 */       ServerStatisticManager manager = getStatisticManager();
/* 354 */       CraftStatistic.decrementStatistic(manager, statistic);
/* 355 */       manager.save();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStatistic(Statistic statistic) {
/* 361 */     if (isOnline()) {
/* 362 */       return getPlayer().getStatistic(statistic);
/*     */     }
/* 364 */     return CraftStatistic.getStatistic(getStatisticManager(), statistic);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void incrementStatistic(Statistic statistic, int amount) {
/* 370 */     if (isOnline()) {
/* 371 */       getPlayer().incrementStatistic(statistic, amount);
/*     */     } else {
/* 373 */       ServerStatisticManager manager = getStatisticManager();
/* 374 */       CraftStatistic.incrementStatistic(manager, statistic, amount);
/* 375 */       manager.save();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void decrementStatistic(Statistic statistic, int amount) {
/* 381 */     if (isOnline()) {
/* 382 */       getPlayer().decrementStatistic(statistic, amount);
/*     */     } else {
/* 384 */       ServerStatisticManager manager = getStatisticManager();
/* 385 */       CraftStatistic.decrementStatistic(manager, statistic, amount);
/* 386 */       manager.save();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStatistic(Statistic statistic, int newValue) {
/* 392 */     if (isOnline()) {
/* 393 */       getPlayer().setStatistic(statistic, newValue);
/*     */     } else {
/* 395 */       ServerStatisticManager manager = getStatisticManager();
/* 396 */       CraftStatistic.setStatistic(manager, statistic, newValue);
/* 397 */       manager.save();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void incrementStatistic(Statistic statistic, Material material) {
/* 403 */     if (isOnline()) {
/* 404 */       getPlayer().incrementStatistic(statistic, material);
/*     */     } else {
/* 406 */       ServerStatisticManager manager = getStatisticManager();
/* 407 */       CraftStatistic.incrementStatistic(manager, statistic, material);
/* 408 */       manager.save();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void decrementStatistic(Statistic statistic, Material material) {
/* 414 */     if (isOnline()) {
/* 415 */       getPlayer().decrementStatistic(statistic, material);
/*     */     } else {
/* 417 */       ServerStatisticManager manager = getStatisticManager();
/* 418 */       CraftStatistic.decrementStatistic(manager, statistic, material);
/* 419 */       manager.save();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStatistic(Statistic statistic, Material material) {
/* 425 */     if (isOnline()) {
/* 426 */       return getPlayer().getStatistic(statistic, material);
/*     */     }
/* 428 */     return CraftStatistic.getStatistic(getStatisticManager(), statistic, material);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void incrementStatistic(Statistic statistic, Material material, int amount) {
/* 434 */     if (isOnline()) {
/* 435 */       getPlayer().incrementStatistic(statistic, material, amount);
/*     */     } else {
/* 437 */       ServerStatisticManager manager = getStatisticManager();
/* 438 */       CraftStatistic.incrementStatistic(manager, statistic, material, amount);
/* 439 */       manager.save();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void decrementStatistic(Statistic statistic, Material material, int amount) {
/* 445 */     if (isOnline()) {
/* 446 */       getPlayer().decrementStatistic(statistic, material, amount);
/*     */     } else {
/* 448 */       ServerStatisticManager manager = getStatisticManager();
/* 449 */       CraftStatistic.decrementStatistic(manager, statistic, material, amount);
/* 450 */       manager.save();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStatistic(Statistic statistic, Material material, int newValue) {
/* 456 */     if (isOnline()) {
/* 457 */       getPlayer().setStatistic(statistic, material, newValue);
/*     */     } else {
/* 459 */       ServerStatisticManager manager = getStatisticManager();
/* 460 */       CraftStatistic.setStatistic(manager, statistic, material, newValue);
/* 461 */       manager.save();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void incrementStatistic(Statistic statistic, EntityType entityType) {
/* 467 */     if (isOnline()) {
/* 468 */       getPlayer().incrementStatistic(statistic, entityType);
/*     */     } else {
/* 470 */       ServerStatisticManager manager = getStatisticManager();
/* 471 */       CraftStatistic.incrementStatistic(manager, statistic, entityType);
/* 472 */       manager.save();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void decrementStatistic(Statistic statistic, EntityType entityType) {
/* 478 */     if (isOnline()) {
/* 479 */       getPlayer().decrementStatistic(statistic, entityType);
/*     */     } else {
/* 481 */       ServerStatisticManager manager = getStatisticManager();
/* 482 */       CraftStatistic.decrementStatistic(manager, statistic, entityType);
/* 483 */       manager.save();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStatistic(Statistic statistic, EntityType entityType) {
/* 489 */     if (isOnline()) {
/* 490 */       return getPlayer().getStatistic(statistic, entityType);
/*     */     }
/* 492 */     return CraftStatistic.getStatistic(getStatisticManager(), statistic, entityType);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void incrementStatistic(Statistic statistic, EntityType entityType, int amount) {
/* 498 */     if (isOnline()) {
/* 499 */       getPlayer().incrementStatistic(statistic, entityType, amount);
/*     */     } else {
/* 501 */       ServerStatisticManager manager = getStatisticManager();
/* 502 */       CraftStatistic.incrementStatistic(manager, statistic, entityType, amount);
/* 503 */       manager.save();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void decrementStatistic(Statistic statistic, EntityType entityType, int amount) {
/* 509 */     if (isOnline()) {
/* 510 */       getPlayer().decrementStatistic(statistic, entityType, amount);
/*     */     } else {
/* 512 */       ServerStatisticManager manager = getStatisticManager();
/* 513 */       CraftStatistic.decrementStatistic(manager, statistic, entityType, amount);
/* 514 */       manager.save();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStatistic(Statistic statistic, EntityType entityType, int newValue) {
/* 520 */     if (isOnline()) {
/* 521 */       getPlayer().setStatistic(statistic, entityType, newValue);
/*     */     } else {
/* 523 */       ServerStatisticManager manager = getStatisticManager();
/* 524 */       CraftStatistic.setStatistic(manager, statistic, entityType, newValue);
/* 525 */       manager.save();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\CraftOfflinePlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */