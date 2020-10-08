/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.base.Throwables;
/*     */ import com.google.common.cache.CacheBuilder;
/*     */ import com.google.common.cache.CacheLoader;
/*     */ import com.google.common.cache.LoadingCache;
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.util.concurrent.Futures;
/*     */ import com.google.common.util.concurrent.ThreadFactoryBuilder;
/*     */ import com.mojang.authlib.Agent;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.ProfileLookupCallback;
/*     */ import com.mojang.authlib.minecraft.MinecraftSessionService;
/*     */ import com.mojang.authlib.properties.Property;
/*     */ import java.util.Locale;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.Future;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileEntitySkull
/*     */   extends TileEntity
/*     */ {
/*     */   @Nullable
/*     */   private static UserCache userCache;
/*     */   @Nullable
/*     */   private static MinecraftSessionService sessionService;
/*     */   @Nullable
/*     */   public GameProfile gameProfile;
/*     */   private int g;
/*     */   private boolean h;
/*  38 */   public static final ExecutorService executor = Executors.newFixedThreadPool(3, (new ThreadFactoryBuilder())
/*     */       
/*  40 */       .setNameFormat("Head Conversion Thread - %1$d")
/*  41 */       .build());
/*     */   
/*  43 */   public static final LoadingCache<String, GameProfile> skinCache = CacheBuilder.newBuilder()
/*  44 */     .maximumSize(5000L)
/*  45 */     .expireAfterAccess(60L, TimeUnit.MINUTES)
/*  46 */     .build(new CacheLoader<String, GameProfile>()
/*     */       {
/*     */         
/*     */         public GameProfile load(String key) throws Exception
/*     */         {
/*  51 */           final GameProfile[] profiles = new GameProfile[1];
/*  52 */           ProfileLookupCallback gameProfileLookup = new ProfileLookupCallback()
/*     */             {
/*     */               public void onProfileLookupSucceeded(GameProfile gp)
/*     */               {
/*  56 */                 profiles[0] = gp;
/*     */               }
/*     */ 
/*     */               
/*     */               public void onProfileLookupFailed(GameProfile gp, Exception excptn) {
/*  61 */                 profiles[0] = gp;
/*     */               }
/*     */             };
/*     */           
/*  65 */           MinecraftServer.getServer().getGameProfileRepository().findProfilesByNames(new String[] { key }, Agent.MINECRAFT, gameProfileLookup);
/*     */           
/*  67 */           GameProfile profile = profiles[0];
/*  68 */           if (profile == null) {
/*  69 */             UUID uuid = EntityHuman.a(new GameProfile(null, key));
/*  70 */             profile = new GameProfile(uuid, key);
/*     */             
/*  72 */             gameProfileLookup.onProfileLookupSucceeded(profile);
/*     */           }
/*     */           else {
/*     */             
/*  76 */             Property property = (Property)Iterables.getFirst(profile.getProperties().get("textures"), null);
/*     */             
/*  78 */             if (property == null)
/*     */             {
/*  80 */               profile = TileEntitySkull.sessionService.fillProfileProperties(profile, true);
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/*  85 */           return profile;
/*     */         }
/*     */       });
/*     */ 
/*     */   
/*     */   public TileEntitySkull() {
/*  91 */     super(TileEntityTypes.SKULL);
/*     */   }
/*     */   
/*     */   public static void a(UserCache usercache) {
/*  95 */     userCache = usercache;
/*     */   }
/*     */   
/*     */   public static void a(MinecraftSessionService minecraftsessionservice) {
/*  99 */     sessionService = minecraftsessionservice;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound save(NBTTagCompound nbttagcompound) {
/* 104 */     super.save(nbttagcompound);
/* 105 */     if (this.gameProfile != null) {
/* 106 */       NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*     */       
/* 108 */       GameProfileSerializer.serialize(nbttagcompound1, this.gameProfile);
/* 109 */       nbttagcompound.set("SkullOwner", nbttagcompound1);
/*     */     } 
/*     */     
/* 112 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(IBlockData iblockdata, NBTTagCompound nbttagcompound) {
/* 117 */     super.load(iblockdata, nbttagcompound);
/* 118 */     if (nbttagcompound.hasKeyOfType("SkullOwner", 10)) {
/* 119 */       setGameProfile(GameProfileSerializer.deserialize(nbttagcompound.getCompound("SkullOwner")));
/* 120 */     } else if (nbttagcompound.hasKeyOfType("ExtraType", 8)) {
/* 121 */       String s = nbttagcompound.getString("ExtraType");
/*     */       
/* 123 */       if (!UtilColor.b(s)) {
/* 124 */         setGameProfile(new GameProfile((UUID)null, s));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 132 */     IBlockData iblockdata = getBlock();
/*     */     
/* 134 */     if (iblockdata.a(Blocks.DRAGON_HEAD) || iblockdata.a(Blocks.DRAGON_WALL_HEAD)) {
/* 135 */       if (this.world.isBlockIndirectlyPowered(this.position)) {
/* 136 */         this.h = true;
/* 137 */         this.g++;
/*     */       } else {
/* 139 */         this.h = false;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public PacketPlayOutTileEntityData getUpdatePacket() {
/* 148 */     return new PacketPlayOutTileEntityData(this.position, 4, sanitizeTileEntityUUID(b()));
/*     */   }
/*     */ 
/*     */   
/*     */   static NBTTagCompound sanitizeTileEntityUUID(NBTTagCompound cmp) {
/* 153 */     NBTTagCompound owner = cmp.getCompound("Owner");
/* 154 */     if (!owner.isEmpty()) {
/* 155 */       sanitizeUUID(owner);
/*     */     }
/* 157 */     return cmp;
/*     */   }
/*     */   
/*     */   static void sanitizeUUID(NBTTagCompound owner) {
/* 161 */     NBTTagCompound properties = owner.getCompound("Properties");
/* 162 */     NBTTagList list = null;
/* 163 */     if (!properties.isEmpty()) {
/* 164 */       list = properties.getList("textures", 10);
/*     */     }
/*     */     
/* 167 */     if (list != null && !list.isEmpty()) {
/* 168 */       String textures = ((NBTTagCompound)list.get(0)).getString("Value");
/* 169 */       if (textures != null && textures.length() > 3) {
/* 170 */         UUID uuid = UUID.nameUUIDFromBytes(textures.getBytes());
/* 171 */         owner.setUUID("Id", uuid);
/*     */         return;
/*     */       } 
/*     */     } 
/* 175 */     owner.setUUID("Id", UUID.randomUUID());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound b() {
/* 181 */     return save(new NBTTagCompound());
/*     */   }
/*     */   
/*     */   public void setGameProfile(@Nullable GameProfile gameprofile) {
/* 185 */     this.gameProfile = gameprofile;
/* 186 */     f();
/*     */   }
/*     */ 
/*     */   
/*     */   private void f() {
/* 191 */     GameProfile profile = this.gameProfile;
/* 192 */     b(profile, new Predicate<GameProfile>()
/*     */         {
/*     */           public boolean apply(GameProfile input)
/*     */           {
/* 196 */             TileEntitySkull.this.gameProfile = input;
/* 197 */             TileEntitySkull.this.update();
/* 198 */             return false;
/*     */           }
/*     */         },  false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Future<GameProfile> b(@Nullable final GameProfile gameprofile, final Predicate<GameProfile> callback, boolean sync) {
/* 206 */     if (gameprofile != null && !UtilColor.b(gameprofile.getName())) {
/* 207 */       if (gameprofile.isComplete() && gameprofile.getProperties().containsKey("textures")) {
/* 208 */         callback.apply(gameprofile);
/* 209 */       } else if (MinecraftServer.getServer() == null) {
/* 210 */         callback.apply(gameprofile);
/*     */       } else {
/* 212 */         GameProfile profile = (GameProfile)skinCache.getIfPresent(gameprofile.getName().toLowerCase(Locale.ROOT));
/* 213 */         if (profile != null && Iterables.getFirst(profile.getProperties().get("textures"), null) != null) {
/* 214 */           callback.apply(profile);
/*     */           
/* 216 */           return (Future<GameProfile>)Futures.immediateFuture(profile);
/*     */         } 
/* 218 */         Callable<GameProfile> callable = new Callable<GameProfile>()
/*     */           {
/*     */             public GameProfile call() {
/* 221 */               final GameProfile profile = (GameProfile)TileEntitySkull.skinCache.getUnchecked(gameprofile.getName().toLowerCase(Locale.ROOT));
/* 222 */               (MinecraftServer.getServer()).processQueue.add(new Runnable()
/*     */                   {
/*     */                     public void run() {
/* 225 */                       if (profile == null) {
/* 226 */                         callback.apply(gameprofile);
/*     */                       } else {
/* 228 */                         callback.apply(profile);
/*     */                       } 
/*     */                     }
/*     */                   });
/* 232 */               return profile;
/*     */             }
/*     */           };
/* 235 */         if (sync) {
/*     */           try {
/* 237 */             return (Future<GameProfile>)Futures.immediateFuture(callable.call());
/* 238 */           } catch (Exception ex) {
/* 239 */             Throwables.throwIfUnchecked(ex);
/* 240 */             throw new RuntimeException(ex);
/*     */           } 
/*     */         }
/* 243 */         return executor.submit(callable);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 248 */       callback.apply(gameprofile);
/*     */     } 
/*     */     
/* 251 */     return (Future<GameProfile>)Futures.immediateFuture(gameprofile);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntitySkull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */