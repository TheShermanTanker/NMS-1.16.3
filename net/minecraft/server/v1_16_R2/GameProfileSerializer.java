/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.annotations.VisibleForTesting;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.properties.Property;
/*     */ import com.mojang.datafixers.DataFixer;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public final class GameProfileSerializer
/*     */ {
/*  20 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   @Nullable
/*     */   public static GameProfile deserialize(NBTTagCompound nbttagcompound) {
/*  24 */     String s = null;
/*  25 */     UUID uuid = null;
/*     */     
/*  27 */     if (nbttagcompound.hasKeyOfType("Name", 8)) {
/*  28 */       s = nbttagcompound.getString("Name");
/*     */     }
/*     */ 
/*     */     
/*  32 */     if (nbttagcompound.hasKeyOfType("Id", 8)) {
/*  33 */       uuid = UUID.fromString(nbttagcompound.getString("Id"));
/*     */     }
/*     */     
/*  36 */     if (nbttagcompound.b("Id")) {
/*  37 */       uuid = nbttagcompound.a("Id");
/*     */     }
/*     */     
/*     */     try {
/*  41 */       GameProfile gameprofile = new GameProfile(uuid, s);
/*     */       
/*  43 */       if (nbttagcompound.hasKeyOfType("Properties", 10)) {
/*  44 */         NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Properties");
/*  45 */         Iterator<String> iterator = nbttagcompound1.getKeys().iterator();
/*     */         
/*  47 */         while (iterator.hasNext()) {
/*  48 */           String s1 = iterator.next();
/*  49 */           NBTTagList nbttaglist = nbttagcompound1.getList(s1, 10);
/*  50 */           if (nbttaglist.size() == 0)
/*  51 */             continue;  for (int i = nbttaglist.size() - 1; i < nbttaglist.size(); i++) {
/*  52 */             NBTTagCompound nbttagcompound2 = nbttaglist.getCompound(i);
/*  53 */             String s2 = nbttagcompound2.getString("Value");
/*     */             
/*  55 */             if (nbttagcompound2.hasKeyOfType("Signature", 8)) {
/*  56 */               gameprofile.getProperties().put(s1, new Property(s1, s2, nbttagcompound2.getString("Signature")));
/*     */             } else {
/*  58 */               gameprofile.getProperties().put(s1, new Property(s1, s2));
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  64 */       return gameprofile;
/*  65 */     } catch (Throwable throwable) {
/*  66 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static NBTTagCompound serialize(NBTTagCompound nbttagcompound, GameProfile gameprofile) {
/*  71 */     if (!UtilColor.b(gameprofile.getName())) {
/*  72 */       nbttagcompound.setString("Name", gameprofile.getName());
/*     */     }
/*     */     
/*  75 */     if (gameprofile.getId() != null) {
/*  76 */       nbttagcompound.a("Id", gameprofile.getId());
/*     */     }
/*     */     
/*  79 */     if (!gameprofile.getProperties().isEmpty()) {
/*  80 */       NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*  81 */       Iterator<String> iterator = gameprofile.getProperties().keySet().iterator();
/*     */       
/*  83 */       while (iterator.hasNext()) {
/*  84 */         String s = iterator.next();
/*  85 */         NBTTagList nbttaglist = new NBTTagList();
/*     */ 
/*     */ 
/*     */         
/*  89 */         for (Iterator<Property> iterator1 = gameprofile.getProperties().get(s).iterator(); iterator1.hasNext(); nbttaglist.add(nbttagcompound2)) {
/*  90 */           Property property = iterator1.next();
/*     */           
/*  92 */           NBTTagCompound nbttagcompound2 = new NBTTagCompound();
/*  93 */           nbttagcompound2.setString("Value", property.getValue());
/*  94 */           if (property.hasSignature()) {
/*  95 */             nbttagcompound2.setString("Signature", property.getSignature());
/*     */           }
/*     */         } 
/*     */         
/*  99 */         nbttagcompound1.set(s, nbttaglist);
/*     */       } 
/*     */       
/* 102 */       nbttagcompound.set("Properties", nbttagcompound1);
/*     */     } 
/*     */     
/* 105 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   @VisibleForTesting
/*     */   public static boolean a(@Nullable NBTBase nbtbase, @Nullable NBTBase nbtbase1, boolean flag) {
/* 110 */     if (nbtbase == nbtbase1)
/* 111 */       return true; 
/* 112 */     if (nbtbase == null)
/* 113 */       return true; 
/* 114 */     if (nbtbase1 == null)
/* 115 */       return false; 
/* 116 */     if (!nbtbase.getClass().equals(nbtbase1.getClass()))
/* 117 */       return false; 
/* 118 */     if (nbtbase instanceof NBTTagCompound) {
/* 119 */       String s; NBTBase nbtbase2; NBTTagCompound nbttagcompound = (NBTTagCompound)nbtbase;
/* 120 */       NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbtbase1;
/* 121 */       Iterator<String> iterator = nbttagcompound.getKeys().iterator();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       do {
/* 127 */         if (!iterator.hasNext()) {
/* 128 */           return true;
/*     */         }
/*     */         
/* 131 */         s = iterator.next();
/* 132 */         nbtbase2 = nbttagcompound.get(s);
/* 133 */       } while (a(nbtbase2, nbttagcompound1.get(s), flag));
/*     */       
/* 135 */       return false;
/* 136 */     }  if (nbtbase instanceof NBTTagList && flag) {
/* 137 */       NBTTagList nbttaglist = (NBTTagList)nbtbase;
/* 138 */       NBTTagList nbttaglist1 = (NBTTagList)nbtbase1;
/*     */       
/* 140 */       if (nbttaglist.isEmpty()) {
/* 141 */         return nbttaglist1.isEmpty();
/*     */       }
/* 143 */       int i = 0;
/*     */       
/* 145 */       while (i < nbttaglist.size()) {
/* 146 */         NBTBase nbtbase3 = nbttaglist.get(i);
/* 147 */         boolean flag1 = false;
/* 148 */         int j = 0;
/*     */ 
/*     */         
/* 151 */         while (j < nbttaglist1.size()) {
/* 152 */           if (!a(nbtbase3, nbttaglist1.get(j), flag)) {
/* 153 */             j++;
/*     */             
/*     */             continue;
/*     */           } 
/* 157 */           flag1 = true;
/*     */         } 
/*     */         
/* 160 */         if (!flag1) {
/* 161 */           return false;
/*     */         }
/*     */         
/* 164 */         i++;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 169 */       return true;
/*     */     } 
/*     */     
/* 172 */     return nbtbase.equals(nbtbase1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static NBTTagIntArray a(UUID uuid) {
/* 177 */     return new NBTTagIntArray(MinecraftSerializableUUID.a(uuid));
/*     */   }
/*     */   
/*     */   public static UUID a(NBTBase nbtbase) {
/* 181 */     if (nbtbase.b() != NBTTagIntArray.a) {
/* 182 */       throw new IllegalArgumentException("Expected UUID-Tag to be of type " + NBTTagIntArray.a.a() + ", but found " + nbtbase.b().a() + ".");
/*     */     }
/* 184 */     int[] aint = ((NBTTagIntArray)nbtbase).getInts();
/*     */     
/* 186 */     if (aint.length != 4) {
/* 187 */       throw new IllegalArgumentException("Expected UUID-Array to be of length 4, but found " + aint.length + ".");
/*     */     }
/* 189 */     return MinecraftSerializableUUID.a(aint);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static BlockPosition b(NBTTagCompound nbttagcompound) {
/* 195 */     return new BlockPosition(nbttagcompound.getInt("X"), nbttagcompound.getInt("Y"), nbttagcompound.getInt("Z"));
/*     */   }
/*     */   
/*     */   public static NBTTagCompound a(BlockPosition blockposition) {
/* 199 */     NBTTagCompound nbttagcompound = new NBTTagCompound();
/*     */     
/* 201 */     nbttagcompound.setInt("X", blockposition.getX());
/* 202 */     nbttagcompound.setInt("Y", blockposition.getY());
/* 203 */     nbttagcompound.setInt("Z", blockposition.getZ());
/* 204 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   public static IBlockData c(NBTTagCompound nbttagcompound) {
/* 208 */     if (!nbttagcompound.hasKeyOfType("Name", 8)) {
/* 209 */       return Blocks.AIR.getBlockData();
/*     */     }
/* 211 */     Block block = IRegistry.BLOCK.get(new MinecraftKey(nbttagcompound.getString("Name")));
/* 212 */     IBlockData iblockdata = block.getBlockData();
/*     */     
/* 214 */     if (nbttagcompound.hasKeyOfType("Properties", 10)) {
/* 215 */       NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Properties");
/* 216 */       BlockStateList<Block, IBlockData> blockstatelist = block.getStates();
/* 217 */       Iterator<String> iterator = nbttagcompound1.getKeys().iterator();
/*     */       
/* 219 */       while (iterator.hasNext()) {
/* 220 */         String s = iterator.next();
/* 221 */         IBlockState<?> iblockstate = blockstatelist.a(s);
/*     */         
/* 223 */         if (iblockstate != null) {
/* 224 */           iblockdata = a(iblockdata, iblockstate, s, nbttagcompound1, nbttagcompound);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 229 */     return iblockdata;
/*     */   }
/*     */ 
/*     */   
/*     */   private static <S extends IBlockDataHolder<?, S>, T extends Comparable<T>> S a(S s0, IBlockState<T> iblockstate, String s, NBTTagCompound nbttagcompound, NBTTagCompound nbttagcompound1) {
/* 234 */     Optional<T> optional = iblockstate.b(nbttagcompound.getString(s));
/*     */     
/* 236 */     if (optional.isPresent()) {
/* 237 */       return (S)s0.set(iblockstate, (Comparable)optional.get());
/*     */     }
/* 239 */     LOGGER.warn("Unable to read property: {} with value: {} for blockstate: {}", s, nbttagcompound.getString(s), nbttagcompound1.toString());
/* 240 */     return s0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static NBTTagCompound a(IBlockData iblockdata) {
/* 245 */     NBTTagCompound nbttagcompound = new NBTTagCompound();
/*     */     
/* 247 */     nbttagcompound.setString("Name", IRegistry.BLOCK.getKey(iblockdata.getBlock()).toString());
/* 248 */     ImmutableMap<IBlockState<?>, Comparable<?>> immutablemap = iblockdata.getStateMap();
/*     */     
/* 250 */     if (!immutablemap.isEmpty()) {
/* 251 */       NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 252 */       UnmodifiableIterator unmodifiableiterator = immutablemap.entrySet().iterator();
/*     */       
/* 254 */       while (unmodifiableiterator.hasNext()) {
/* 255 */         Map.Entry<IBlockState<?>, Comparable<?>> entry = (Map.Entry<IBlockState<?>, Comparable<?>>)unmodifiableiterator.next();
/* 256 */         IBlockState<?> iblockstate = entry.getKey();
/*     */         
/* 258 */         nbttagcompound1.setString(iblockstate.getName(), a(iblockstate, entry.getValue()));
/*     */       } 
/*     */       
/* 261 */       nbttagcompound.set("Properties", nbttagcompound1);
/*     */     } 
/*     */     
/* 264 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   private static <T extends Comparable<T>> String a(IBlockState<T> iblockstate, Comparable<T> comparable) {
/* 268 */     return iblockstate.a((T)comparable);
/*     */   }
/*     */   
/*     */   public static NBTTagCompound a(DataFixer datafixer, DataFixTypes datafixtypes, NBTTagCompound nbttagcompound, int i) {
/* 272 */     return a(datafixer, datafixtypes, nbttagcompound, i, SharedConstants.getGameVersion().getWorldVersion());
/*     */   }
/*     */   
/*     */   public static NBTTagCompound a(DataFixer datafixer, DataFixTypes datafixtypes, NBTTagCompound nbttagcompound, int i, int j) {
/* 276 */     return (NBTTagCompound)datafixer.update(datafixtypes.a(), new Dynamic(DynamicOpsNBT.a, nbttagcompound), i, j).getValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GameProfileSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */