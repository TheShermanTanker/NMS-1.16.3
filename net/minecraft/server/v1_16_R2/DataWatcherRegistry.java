/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Optional;
/*     */ import java.util.OptionalInt;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataWatcherRegistry
/*     */ {
/*  25 */   private static final RegistryID<DataWatcherSerializer<?>> t = new RegistryID<>(16);
/*     */   
/*  27 */   public static final DataWatcherSerializer<Byte> a = new DataWatcherSerializer<Byte>()
/*     */     {
/*     */       public void a(PacketDataSerializer var0, Byte var1) {
/*  30 */         var0.writeByte(var1.byteValue());
/*     */       }
/*     */ 
/*     */       
/*     */       public Byte a(PacketDataSerializer var0) {
/*  35 */         return Byte.valueOf(var0.readByte());
/*     */       }
/*     */ 
/*     */       
/*     */       public Byte a(Byte var0) {
/*  40 */         return var0;
/*     */       }
/*     */     };
/*     */   
/*  44 */   public static final DataWatcherSerializer<Integer> b = new DataWatcherSerializer<Integer>()
/*     */     {
/*     */       public void a(PacketDataSerializer var0, Integer var1) {
/*  47 */         var0.d(var1.intValue());
/*     */       }
/*     */ 
/*     */       
/*     */       public Integer a(PacketDataSerializer var0) {
/*  52 */         return Integer.valueOf(var0.i());
/*     */       }
/*     */ 
/*     */       
/*     */       public Integer a(Integer var0) {
/*  57 */         return var0;
/*     */       }
/*     */     };
/*     */   
/*  61 */   public static final DataWatcherSerializer<Float> c = new DataWatcherSerializer<Float>()
/*     */     {
/*     */       public void a(PacketDataSerializer var0, Float var1) {
/*  64 */         var0.writeFloat(var1.floatValue());
/*     */       }
/*     */ 
/*     */       
/*     */       public Float a(PacketDataSerializer var0) {
/*  69 */         return Float.valueOf(var0.readFloat());
/*     */       }
/*     */ 
/*     */       
/*     */       public Float a(Float var0) {
/*  74 */         return var0;
/*     */       }
/*     */     };
/*     */   
/*  78 */   public static final DataWatcherSerializer<String> d = new DataWatcherSerializer<String>()
/*     */     {
/*     */       public void a(PacketDataSerializer var0, String var1) {
/*  81 */         var0.a(var1);
/*     */       }
/*     */ 
/*     */       
/*     */       public String a(PacketDataSerializer var0) {
/*  86 */         return var0.e(32767);
/*     */       }
/*     */ 
/*     */       
/*     */       public String a(String var0) {
/*  91 */         return var0;
/*     */       }
/*     */     };
/*     */   
/*  95 */   public static final DataWatcherSerializer<IChatBaseComponent> e = new DataWatcherSerializer<IChatBaseComponent>()
/*     */     {
/*     */       public void a(PacketDataSerializer var0, IChatBaseComponent var1) {
/*  98 */         var0.a(var1);
/*     */       }
/*     */ 
/*     */       
/*     */       public IChatBaseComponent a(PacketDataSerializer var0) {
/* 103 */         return var0.h();
/*     */       }
/*     */ 
/*     */       
/*     */       public IChatBaseComponent a(IChatBaseComponent var0) {
/* 108 */         return var0;
/*     */       }
/*     */     };
/*     */   
/* 112 */   public static final DataWatcherSerializer<Optional<IChatBaseComponent>> f = new DataWatcherSerializer<Optional<IChatBaseComponent>>()
/*     */     {
/*     */       public void a(PacketDataSerializer var0, Optional<IChatBaseComponent> var1) {
/* 115 */         if (var1.isPresent()) {
/* 116 */           var0.writeBoolean(true);
/* 117 */           var0.a(var1.get());
/*     */         } else {
/* 119 */           var0.writeBoolean(false);
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/*     */       public Optional<IChatBaseComponent> a(PacketDataSerializer var0) {
/* 125 */         return var0.readBoolean() ? Optional.<IChatBaseComponent>of(var0.h()) : Optional.<IChatBaseComponent>empty();
/*     */       }
/*     */ 
/*     */       
/*     */       public Optional<IChatBaseComponent> a(Optional<IChatBaseComponent> var0) {
/* 130 */         return var0;
/*     */       }
/*     */     };
/*     */   
/* 134 */   public static final DataWatcherSerializer<ItemStack> g = new DataWatcherSerializer<ItemStack>()
/*     */     {
/*     */       public void a(PacketDataSerializer var0, ItemStack var1) {
/* 137 */         var0.a(var1);
/*     */       }
/*     */ 
/*     */       
/*     */       public ItemStack a(PacketDataSerializer var0) {
/* 142 */         return var0.n();
/*     */       }
/*     */ 
/*     */       
/*     */       public ItemStack a(ItemStack var0) {
/* 147 */         return var0.cloneItemStack();
/*     */       }
/*     */     };
/*     */   
/* 151 */   public static final DataWatcherSerializer<Optional<IBlockData>> h = new DataWatcherSerializer<Optional<IBlockData>>()
/*     */     {
/*     */       public void a(PacketDataSerializer var0, Optional<IBlockData> var1) {
/* 154 */         if (var1.isPresent()) {
/* 155 */           var0.d(Block.getCombinedId(var1.get()));
/*     */         } else {
/* 157 */           var0.d(0);
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/*     */       public Optional<IBlockData> a(PacketDataSerializer var0) {
/* 163 */         int var1 = var0.i();
/* 164 */         if (var1 == 0) {
/* 165 */           return Optional.empty();
/*     */         }
/* 167 */         return Optional.of(Block.getByCombinedId(var1));
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public Optional<IBlockData> a(Optional<IBlockData> var0) {
/* 173 */         return var0;
/*     */       }
/*     */     };
/*     */   
/* 177 */   public static final DataWatcherSerializer<Boolean> i = new DataWatcherSerializer<Boolean>()
/*     */     {
/*     */       public void a(PacketDataSerializer var0, Boolean var1) {
/* 180 */         var0.writeBoolean(var1.booleanValue());
/*     */       }
/*     */ 
/*     */       
/*     */       public Boolean a(PacketDataSerializer var0) {
/* 185 */         return Boolean.valueOf(var0.readBoolean());
/*     */       }
/*     */ 
/*     */       
/*     */       public Boolean a(Boolean var0) {
/* 190 */         return var0;
/*     */       }
/*     */     };
/*     */   
/* 194 */   public static final DataWatcherSerializer<ParticleParam> j = new DataWatcherSerializer<ParticleParam>()
/*     */     {
/*     */       public void a(PacketDataSerializer var0, ParticleParam var1) {
/* 197 */         var0.d(IRegistry.PARTICLE_TYPE.a(var1.getParticle()));
/* 198 */         var1.a(var0);
/*     */       }
/*     */ 
/*     */       
/*     */       public ParticleParam a(PacketDataSerializer var0) {
/* 203 */         return a(var0, (Particle<ParticleParam>)IRegistry.PARTICLE_TYPE.fromId(var0.i()));
/*     */       }
/*     */       
/*     */       private <T extends ParticleParam> T a(PacketDataSerializer var0, Particle<T> var1) {
/* 207 */         return var1.d().b(var1, var0);
/*     */       }
/*     */ 
/*     */       
/*     */       public ParticleParam a(ParticleParam var0) {
/* 212 */         return var0;
/*     */       }
/*     */     };
/*     */   
/* 216 */   public static final DataWatcherSerializer<Vector3f> k = new DataWatcherSerializer<Vector3f>()
/*     */     {
/*     */       public void a(PacketDataSerializer var0, Vector3f var1) {
/* 219 */         var0.writeFloat(var1.getX());
/* 220 */         var0.writeFloat(var1.getY());
/* 221 */         var0.writeFloat(var1.getZ());
/*     */       }
/*     */ 
/*     */       
/*     */       public Vector3f a(PacketDataSerializer var0) {
/* 226 */         return new Vector3f(var0.readFloat(), var0.readFloat(), var0.readFloat());
/*     */       }
/*     */ 
/*     */       
/*     */       public Vector3f a(Vector3f var0) {
/* 231 */         return var0;
/*     */       }
/*     */     };
/*     */   
/* 235 */   public static final DataWatcherSerializer<BlockPosition> l = new DataWatcherSerializer<BlockPosition>()
/*     */     {
/*     */       public void a(PacketDataSerializer var0, BlockPosition var1) {
/* 238 */         var0.a(var1);
/*     */       }
/*     */ 
/*     */       
/*     */       public BlockPosition a(PacketDataSerializer var0) {
/* 243 */         return var0.e();
/*     */       }
/*     */ 
/*     */       
/*     */       public BlockPosition a(BlockPosition var0) {
/* 248 */         return var0;
/*     */       }
/*     */     };
/*     */   
/* 252 */   public static final DataWatcherSerializer<Optional<BlockPosition>> m = new DataWatcherSerializer<Optional<BlockPosition>>()
/*     */     {
/*     */       public void a(PacketDataSerializer var0, Optional<BlockPosition> var1) {
/* 255 */         var0.writeBoolean(var1.isPresent());
/* 256 */         if (var1.isPresent()) {
/* 257 */           var0.a(var1.get());
/*     */         }
/*     */       }
/*     */ 
/*     */       
/*     */       public Optional<BlockPosition> a(PacketDataSerializer var0) {
/* 263 */         if (!var0.readBoolean()) {
/* 264 */           return Optional.empty();
/*     */         }
/* 266 */         return Optional.of(var0.e());
/*     */       }
/*     */ 
/*     */       
/*     */       public Optional<BlockPosition> a(Optional<BlockPosition> var0) {
/* 271 */         return var0;
/*     */       }
/*     */     };
/*     */   
/* 275 */   public static final DataWatcherSerializer<EnumDirection> n = new DataWatcherSerializer<EnumDirection>()
/*     */     {
/*     */       public void a(PacketDataSerializer var0, EnumDirection var1) {
/* 278 */         var0.a(var1);
/*     */       }
/*     */ 
/*     */       
/*     */       public EnumDirection a(PacketDataSerializer var0) {
/* 283 */         return var0.<EnumDirection>a(EnumDirection.class);
/*     */       }
/*     */ 
/*     */       
/*     */       public EnumDirection a(EnumDirection var0) {
/* 288 */         return var0;
/*     */       }
/*     */     };
/*     */   
/* 292 */   public static final DataWatcherSerializer<Optional<UUID>> o = new DataWatcherSerializer<Optional<UUID>>()
/*     */     {
/*     */       public void a(PacketDataSerializer var0, Optional<UUID> var1) {
/* 295 */         var0.writeBoolean(var1.isPresent());
/* 296 */         if (var1.isPresent()) {
/* 297 */           var0.a(var1.get());
/*     */         }
/*     */       }
/*     */ 
/*     */       
/*     */       public Optional<UUID> a(PacketDataSerializer var0) {
/* 303 */         if (!var0.readBoolean()) {
/* 304 */           return Optional.empty();
/*     */         }
/* 306 */         return Optional.of(var0.k());
/*     */       }
/*     */ 
/*     */       
/*     */       public Optional<UUID> a(Optional<UUID> var0) {
/* 311 */         return var0;
/*     */       }
/*     */     };
/*     */   
/* 315 */   public static final DataWatcherSerializer<NBTTagCompound> p = new DataWatcherSerializer<NBTTagCompound>()
/*     */     {
/*     */       public void a(PacketDataSerializer var0, NBTTagCompound var1) {
/* 318 */         var0.a(var1);
/*     */       }
/*     */ 
/*     */       
/*     */       public NBTTagCompound a(PacketDataSerializer var0) {
/* 323 */         return var0.l();
/*     */       }
/*     */ 
/*     */       
/*     */       public NBTTagCompound a(NBTTagCompound var0) {
/* 328 */         return var0.clone();
/*     */       }
/*     */     };
/*     */   
/* 332 */   public static final DataWatcherSerializer<VillagerData> q = new DataWatcherSerializer<VillagerData>()
/*     */     {
/*     */       public void a(PacketDataSerializer var0, VillagerData var1) {
/* 335 */         var0.d(IRegistry.VILLAGER_TYPE.a(var1.getType()));
/* 336 */         var0.d(IRegistry.VILLAGER_PROFESSION.a(var1.getProfession()));
/* 337 */         var0.d(var1.getLevel());
/*     */       }
/*     */ 
/*     */       
/*     */       public VillagerData a(PacketDataSerializer var0) {
/* 342 */         return new VillagerData(IRegistry.VILLAGER_TYPE
/* 343 */             .fromId(var0.i()), IRegistry.VILLAGER_PROFESSION
/* 344 */             .fromId(var0.i()), var0
/* 345 */             .i());
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public VillagerData a(VillagerData var0) {
/* 351 */         return var0;
/*     */       }
/*     */     };
/*     */   
/* 355 */   public static final DataWatcherSerializer<OptionalInt> r = new DataWatcherSerializer<OptionalInt>()
/*     */     {
/*     */       public void a(PacketDataSerializer var0, OptionalInt var1) {
/* 358 */         var0.d(var1.orElse(-1) + 1);
/*     */       }
/*     */ 
/*     */       
/*     */       public OptionalInt a(PacketDataSerializer var0) {
/* 363 */         int var1 = var0.i();
/* 364 */         return (var1 == 0) ? OptionalInt.empty() : OptionalInt.of(var1 - 1);
/*     */       }
/*     */ 
/*     */       
/*     */       public OptionalInt a(OptionalInt var0) {
/* 369 */         return var0;
/*     */       }
/*     */     };
/*     */   
/* 373 */   public static final DataWatcherSerializer<EntityPose> s = new DataWatcherSerializer<EntityPose>()
/*     */     {
/*     */       public void a(PacketDataSerializer var0, EntityPose var1) {
/* 376 */         var0.a(var1);
/*     */       }
/*     */ 
/*     */       
/*     */       public EntityPose a(PacketDataSerializer var0) {
/* 381 */         return var0.<EntityPose>a(EntityPose.class);
/*     */       }
/*     */ 
/*     */       
/*     */       public EntityPose a(EntityPose var0) {
/* 386 */         return var0;
/*     */       }
/*     */     };
/*     */   
/*     */   static {
/* 391 */     a(a);
/* 392 */     a(b);
/* 393 */     a(c);
/* 394 */     a(d);
/* 395 */     a(e);
/* 396 */     a(f);
/* 397 */     a(g);
/* 398 */     a(i);
/* 399 */     a(k);
/* 400 */     a(l);
/* 401 */     a(m);
/* 402 */     a(n);
/* 403 */     a(o);
/* 404 */     a(h);
/* 405 */     a(p);
/* 406 */     a(j);
/* 407 */     a(q);
/* 408 */     a(r);
/* 409 */     a(s);
/*     */   }
/*     */   
/*     */   public static void a(DataWatcherSerializer<?> var0) {
/* 413 */     t.c(var0);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static DataWatcherSerializer<?> a(int var0) {
/* 418 */     return t.fromId(var0);
/*     */   }
/*     */   
/*     */   public static int b(DataWatcherSerializer<?> var0) {
/* 422 */     return t.getId(var0);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataWatcherRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */