/*     */ package com.destroystokyo.paper.profile;
/*     */ 
/*     */ import com.destroystokyo.paper.PaperConfig;
/*     */ import com.google.common.base.Charsets;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.properties.Property;
/*     */ import com.mojang.authlib.properties.PropertyMap;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nonnull;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*     */ import net.minecraft.server.v1_16_R2.UserCache;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.Validate;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
/*     */ import org.spigotmc.SpigotConfig;
/*     */ 
/*     */ public class CraftPlayerProfile
/*     */   implements PlayerProfile
/*     */ {
/*     */   private GameProfile profile;
/*  26 */   private final PropertySet properties = new PropertySet();
/*     */   
/*     */   public CraftPlayerProfile(CraftPlayer player) {
/*  29 */     this.profile = player.getHandle().getProfile();
/*     */   }
/*     */   
/*     */   public CraftPlayerProfile(UUID id, String name) {
/*  33 */     this.profile = new GameProfile(id, name);
/*     */   }
/*     */   
/*     */   public CraftPlayerProfile(GameProfile profile) {
/*  37 */     Validate.notNull(profile, "GameProfile cannot be null!", new Object[0]);
/*  38 */     this.profile = profile;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasProperty(String property) {
/*  43 */     return this.profile.getProperties().containsKey(property);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setProperty(ProfileProperty property) {
/*  48 */     String name = property.getName();
/*  49 */     PropertyMap properties = this.profile.getProperties();
/*  50 */     properties.removeAll(name);
/*  51 */     properties.put(name, new Property(name, property.getValue(), property.getSignature()));
/*     */   }
/*     */   
/*     */   public GameProfile getGameProfile() {
/*  55 */     return this.profile;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public UUID getId() {
/*  61 */     return this.profile.getId();
/*     */   }
/*     */ 
/*     */   
/*     */   public UUID setId(@Nullable UUID uuid) {
/*  66 */     GameProfile prev = this.profile;
/*  67 */     this.profile = new GameProfile(uuid, prev.getName());
/*  68 */     copyProfileProperties(prev, this.profile);
/*  69 */     return prev.getId();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public String getName() {
/*  75 */     return this.profile.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String setName(@Nullable String name) {
/*  80 */     GameProfile prev = this.profile;
/*  81 */     this.profile = new GameProfile(prev.getId(), name);
/*  82 */     copyProfileProperties(prev, this.profile);
/*  83 */     return prev.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public Set<ProfileProperty> getProperties() {
/*  89 */     return this.properties;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setProperties(Collection<ProfileProperty> properties) {
/*  94 */     properties.forEach(this::setProperty);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearProperties() {
/*  99 */     this.profile.getProperties().clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removeProperty(String property) {
/* 104 */     return !this.profile.getProperties().removeAll(property).isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 109 */     if (this == o) return true; 
/* 110 */     if (o == null || getClass() != o.getClass()) return false; 
/* 111 */     CraftPlayerProfile that = (CraftPlayerProfile)o;
/* 112 */     return Objects.equals(this.profile, that.profile);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 117 */     return this.profile.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 122 */     return this.profile.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public CraftPlayerProfile clone() {
/* 127 */     CraftPlayerProfile clone = new CraftPlayerProfile(getId(), getName());
/* 128 */     clone.setProperties(getProperties());
/* 129 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isComplete() {
/* 134 */     return this.profile.isComplete();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean completeFromCache() {
/* 139 */     MinecraftServer server = MinecraftServer.getServer();
/* 140 */     return completeFromCache(false, (server.getOnlineMode() || (SpigotConfig.bungee && PaperConfig.bungeeOnlineMode)));
/*     */   }
/*     */   
/*     */   public boolean completeFromCache(boolean onlineMode) {
/* 144 */     return completeFromCache(false, onlineMode);
/*     */   }
/*     */   
/*     */   public boolean completeFromCache(boolean lookupUUID, boolean onlineMode) {
/* 148 */     MinecraftServer server = MinecraftServer.getServer();
/* 149 */     String name = this.profile.getName();
/* 150 */     UserCache userCache = server.getUserCache();
/* 151 */     if (this.profile.getId() == null) {
/*     */       GameProfile profile;
/* 153 */       if (onlineMode) {
/* 154 */         profile = lookupUUID ? userCache.getProfile(name) : userCache.getProfileIfCached(name);
/*     */       } else {
/*     */         
/* 157 */         profile = new GameProfile(UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(Charsets.UTF_8)), name);
/*     */       } 
/* 159 */       if (profile != null) {
/*     */         
/* 161 */         copyProfileProperties(this.profile, profile);
/* 162 */         this.profile = profile;
/*     */       } 
/*     */     } 
/*     */     
/* 166 */     if ((this.profile.getName() == null || !hasTextures()) && this.profile.getId() != null) {
/* 167 */       GameProfile profile = userCache.getProfile(this.profile.getId());
/* 168 */       if (profile != null) {
/*     */         
/* 170 */         copyProfileProperties(this.profile, profile);
/* 171 */         this.profile = profile;
/*     */       } 
/*     */     } 
/* 174 */     return this.profile.isComplete();
/*     */   }
/*     */   
/*     */   public boolean complete(boolean textures) {
/* 178 */     MinecraftServer server = MinecraftServer.getServer();
/* 179 */     return complete(textures, (server.getOnlineMode() || (SpigotConfig.bungee && PaperConfig.bungeeOnlineMode)));
/*     */   }
/*     */   public boolean complete(boolean textures, boolean onlineMode) {
/* 182 */     MinecraftServer server = MinecraftServer.getServer();
/*     */     
/* 184 */     boolean isCompleteFromCache = completeFromCache(true, onlineMode);
/* 185 */     if (onlineMode && (!isCompleteFromCache || (textures && !hasTextures()))) {
/* 186 */       GameProfile result = server.getMinecraftSessionService().fillProfileProperties(this.profile, true);
/* 187 */       if (result != null) {
/* 188 */         copyProfileProperties(result, this.profile, true);
/*     */       }
/* 190 */       if (this.profile.isComplete()) {
/* 191 */         server.getUserCache().saveProfile(this.profile);
/*     */       }
/*     */     } 
/* 194 */     return (this.profile.isComplete() && (!onlineMode || !textures || hasTextures()));
/*     */   }
/*     */   
/*     */   private static void copyProfileProperties(GameProfile source, GameProfile target) {
/* 198 */     copyProfileProperties(source, target, false);
/*     */   }
/*     */   
/*     */   private static void copyProfileProperties(GameProfile source, GameProfile target, boolean clearTarget) {
/* 202 */     PropertyMap sourceProperties = source.getProperties();
/* 203 */     PropertyMap targetProperties = target.getProperties();
/* 204 */     if (clearTarget) targetProperties.clear(); 
/* 205 */     if (sourceProperties.isEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/* 209 */     for (Property property : sourceProperties.values()) {
/* 210 */       targetProperties.removeAll(property.getName());
/* 211 */       targetProperties.put(property.getName(), property);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static ProfileProperty toBukkit(Property property) {
/* 216 */     return new ProfileProperty(property.getName(), property.getValue(), property.getSignature());
/*     */   }
/*     */   
/*     */   public static PlayerProfile asBukkitCopy(GameProfile gameProfile) {
/* 220 */     CraftPlayerProfile profile = new CraftPlayerProfile(gameProfile.getId(), gameProfile.getName());
/* 221 */     copyProfileProperties(gameProfile, profile.profile);
/* 222 */     return profile;
/*     */   }
/*     */   
/*     */   public static PlayerProfile asBukkitMirror(GameProfile profile) {
/* 226 */     return new CraftPlayerProfile(profile);
/*     */   }
/*     */   
/*     */   public static Property asAuthlib(ProfileProperty property) {
/* 230 */     return new Property(property.getName(), property.getValue(), property.getSignature());
/*     */   }
/*     */   
/*     */   public static GameProfile asAuthlibCopy(PlayerProfile profile) {
/* 234 */     CraftPlayerProfile craft = (CraftPlayerProfile)profile;
/* 235 */     return asAuthlib(craft.clone());
/*     */   }
/*     */   
/*     */   public static GameProfile asAuthlib(PlayerProfile profile) {
/* 239 */     CraftPlayerProfile craft = (CraftPlayerProfile)profile;
/* 240 */     return craft.getGameProfile();
/*     */   }
/*     */   
/*     */   private class PropertySet extends AbstractSet<ProfileProperty> {
/*     */     private PropertySet() {}
/*     */     
/*     */     @Nonnull
/*     */     public Iterator<ProfileProperty> iterator() {
/* 248 */       return new ProfilePropertyIterator(CraftPlayerProfile.this.profile.getProperties().values().iterator());
/*     */     }
/*     */ 
/*     */     
/*     */     public int size() {
/* 253 */       return CraftPlayerProfile.this.profile.getProperties().size();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean add(ProfileProperty property) {
/* 258 */       CraftPlayerProfile.this.setProperty(property);
/* 259 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean addAll(Collection<? extends ProfileProperty> c) {
/* 265 */       CraftPlayerProfile.this.setProperties((Collection)c);
/* 266 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean contains(Object o) {
/* 271 */       return (o instanceof ProfileProperty && CraftPlayerProfile.this.profile.getProperties().containsKey(((ProfileProperty)o).getName()));
/*     */     }
/*     */     
/*     */     private class ProfilePropertyIterator implements Iterator<ProfileProperty> {
/*     */       private final Iterator<Property> iterator;
/*     */       
/*     */       ProfilePropertyIterator(Iterator<Property> iterator) {
/* 278 */         this.iterator = iterator;
/*     */       }
/*     */ 
/*     */       
/*     */       public boolean hasNext() {
/* 283 */         return this.iterator.hasNext();
/*     */       }
/*     */ 
/*     */       
/*     */       public ProfileProperty next() {
/* 288 */         return CraftPlayerProfile.toBukkit(this.iterator.next());
/*     */       }
/*     */ 
/*     */       
/*     */       public void remove() {
/* 293 */         this.iterator.remove();
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\profile\CraftPlayerProfile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */