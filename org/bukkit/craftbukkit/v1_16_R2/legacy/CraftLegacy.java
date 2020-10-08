/*     */ package org.bukkit.craftbukkit.v1_16_R2.legacy;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import com.mojang.serialization.DynamicOps;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import net.minecraft.server.v1_16_R2.Block;
/*     */ import net.minecraft.server.v1_16_R2.BlockStateList;
/*     */ import net.minecraft.server.v1_16_R2.Blocks;
/*     */ import net.minecraft.server.v1_16_R2.DataConverterFlattenData;
/*     */ import net.minecraft.server.v1_16_R2.DataConverterMaterialId;
/*     */ import net.minecraft.server.v1_16_R2.DataConverterRegistry;
/*     */ import net.minecraft.server.v1_16_R2.DataConverterTypes;
/*     */ import net.minecraft.server.v1_16_R2.DispenserRegistry;
/*     */ import net.minecraft.server.v1_16_R2.DynamicOpsNBT;
/*     */ import net.minecraft.server.v1_16_R2.IBlockData;
/*     */ import net.minecraft.server.v1_16_R2.IBlockState;
/*     */ import net.minecraft.server.v1_16_R2.IRegistry;
/*     */ import net.minecraft.server.v1_16_R2.Item;
/*     */ import net.minecraft.server.v1_16_R2.Items;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftKey;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*     */ import net.minecraft.server.v1_16_R2.NBTBase;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftMagicNumbers;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.material.MaterialData;
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
/*     */ @Deprecated
/*     */ public final class CraftLegacy
/*     */ {
/*  47 */   private static final Map<Byte, Material> SPAWN_EGGS = new HashMap<>();
/*  48 */   private static final Set<String> whitelistedStates = new HashSet<>(Arrays.asList(new String[] { "explode", "check_decay", "decayable", "facing" }));
/*  49 */   private static final Map<MaterialData, Item> materialToItem = new HashMap<>(16384);
/*  50 */   private static final Map<Item, MaterialData> itemToMaterial = new HashMap<>(1024);
/*  51 */   private static final Map<MaterialData, IBlockData> materialToData = new HashMap<>(4096);
/*  52 */   private static final Map<IBlockData, MaterialData> dataToMaterial = new HashMap<>(4096);
/*  53 */   private static final Map<MaterialData, Block> materialToBlock = new HashMap<>(4096);
/*  54 */   private static final Map<Block, MaterialData> blockToMaterial = new HashMap<>(1024);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Material toLegacy(Material material) {
/*  61 */     if (material == null || material.isLegacy()) {
/*  62 */       return material;
/*     */     }
/*     */     
/*  65 */     return toLegacyData(material).getItemType();
/*     */   }
/*     */   public static MaterialData toLegacyData(Material material) {
/*     */     MaterialData mappedData;
/*  69 */     Preconditions.checkArgument(!material.isLegacy(), "toLegacy on legacy Material");
/*     */ 
/*     */     
/*  72 */     if (material.isBlock()) {
/*  73 */       Block block = CraftMagicNumbers.getBlock(material);
/*  74 */       IBlockData blockData = block.getBlockData();
/*     */ 
/*     */       
/*  77 */       mappedData = dataToMaterial.get(blockData);
/*     */       
/*  79 */       if (mappedData == null) {
/*  80 */         mappedData = blockToMaterial.get(block);
/*     */         
/*  82 */         if (mappedData == null) {
/*  83 */           mappedData = itemToMaterial.get(block.getItem());
/*     */         }
/*     */       } 
/*     */     } else {
/*  87 */       Item item = CraftMagicNumbers.getItem(material);
/*  88 */       mappedData = itemToMaterial.get(item);
/*     */     } 
/*     */     
/*  91 */     return (mappedData == null) ? new MaterialData(Material.LEGACY_AIR) : mappedData;
/*     */   }
/*     */   
/*     */   public static IBlockData fromLegacyData(Material material, byte data) {
/*  95 */     Preconditions.checkArgument(material.isLegacy(), "fromLegacyData on modern Material");
/*     */     
/*  97 */     MaterialData materialData = new MaterialData(material, data);
/*     */ 
/*     */     
/* 100 */     IBlockData converted = materialToData.get(materialData);
/* 101 */     if (converted != null) {
/* 102 */       return converted;
/*     */     }
/*     */ 
/*     */     
/* 106 */     Block convertedBlock = materialToBlock.get(materialData);
/* 107 */     if (convertedBlock != null) {
/* 108 */       return convertedBlock.getBlockData();
/*     */     }
/*     */ 
/*     */     
/* 112 */     return Blocks.AIR.getBlockData();
/*     */   }
/*     */   
/*     */   public static Item fromLegacyData(Material material, short data) {
/* 116 */     Preconditions.checkArgument(material.isLegacy(), "fromLegacyData on modern Material. Did you forget to define a modern (1.13+) api-version in your plugin.yml?");
/*     */     
/* 118 */     MaterialData materialData = new MaterialData(material, (byte)data);
/*     */ 
/*     */     
/* 121 */     Item convertedItem = materialToItem.get(materialData);
/* 122 */     if (convertedItem != null) {
/* 123 */       return convertedItem;
/*     */     }
/*     */ 
/*     */     
/* 127 */     if (material.isBlock()) {
/*     */       
/* 129 */       IBlockData converted = materialToData.get(materialData);
/* 130 */       if (converted != null) {
/* 131 */         return converted.getBlock().getItem();
/*     */       }
/*     */ 
/*     */       
/* 135 */       Block convertedBlock = materialToBlock.get(materialData);
/* 136 */       if (convertedBlock != null) {
/* 137 */         return convertedBlock.getItem();
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 142 */     return Items.AIR;
/*     */   }
/*     */   
/*     */   public static byte toLegacyData(IBlockData blockData) {
/* 146 */     return toLegacy(blockData).getData();
/*     */   }
/*     */   
/*     */   public static Material toLegacyMaterial(IBlockData blockData) {
/* 150 */     return toLegacy(blockData).getItemType();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static MaterialData toLegacy(IBlockData blockData) {
/* 157 */     MaterialData mappedData = dataToMaterial.get(blockData);
/*     */     
/* 159 */     if (mappedData == null) {
/* 160 */       mappedData = blockToMaterial.get(blockData.getBlock());
/*     */     }
/*     */     
/* 163 */     return (mappedData == null) ? new MaterialData(Material.LEGACY_AIR) : mappedData;
/*     */   }
/*     */   
/*     */   public static Material fromLegacy(Material material) {
/* 167 */     if (material == null || !material.isLegacy()) {
/* 168 */       return material;
/*     */     }
/*     */     
/* 171 */     return fromLegacy(new MaterialData(material));
/*     */   }
/*     */   
/*     */   public static Material fromLegacy(MaterialData materialData) {
/* 175 */     return fromLegacy(materialData, false);
/*     */   }
/*     */   
/*     */   public static Material fromLegacy(MaterialData materialData, boolean itemPriority) {
/* 179 */     Material material = materialData.getItemType();
/* 180 */     if (material == null || !material.isLegacy()) {
/* 181 */       return material;
/*     */     }
/*     */     
/* 184 */     Material mappedData = null;
/*     */ 
/*     */     
/* 187 */     if (itemPriority) {
/* 188 */       Item item = materialToItem.get(materialData);
/* 189 */       if (item != null) {
/* 190 */         mappedData = CraftMagicNumbers.getMaterial(item);
/*     */       }
/*     */     } 
/*     */     
/* 194 */     if (mappedData == null && material.isBlock()) {
/*     */       
/* 196 */       IBlockData iblock = materialToData.get(materialData);
/* 197 */       if (iblock != null) {
/* 198 */         mappedData = CraftMagicNumbers.getMaterial(iblock.getBlock());
/*     */       }
/*     */ 
/*     */       
/* 202 */       if (mappedData == null) {
/* 203 */         Block block = materialToBlock.get(materialData);
/* 204 */         if (block != null) {
/* 205 */           mappedData = CraftMagicNumbers.getMaterial(block);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 211 */     if (!itemPriority && mappedData == null) {
/* 212 */       Item item = materialToItem.get(materialData);
/* 213 */       if (item != null) {
/* 214 */         mappedData = CraftMagicNumbers.getMaterial(item);
/*     */       }
/*     */     } 
/*     */     
/* 218 */     return (mappedData == null) ? Material.AIR : mappedData;
/*     */   }
/*     */   
/*     */   public static Material[] values() {
/* 222 */     Material[] values = Material.values();
/* 223 */     return Arrays.<Material>copyOfRange(values, Material.LEGACY_AIR.ordinal(), values.length);
/*     */   }
/*     */   
/*     */   public static Material valueOf(String name) {
/* 227 */     return name.startsWith("LEGACY_") ? Material.valueOf(name) : Material.valueOf("LEGACY_" + name);
/*     */   }
/*     */   
/*     */   public static Material getMaterial(String name) {
/* 231 */     return name.startsWith("LEGACY_") ? Material.getMaterial(name) : Material.getMaterial("LEGACY_" + name);
/*     */   }
/*     */   
/*     */   public static Material matchMaterial(String name) {
/* 235 */     return name.startsWith("LEGACY_") ? Material.matchMaterial(name) : Material.matchMaterial("LEGACY_" + name);
/*     */   }
/*     */   
/*     */   public static int ordinal(Material material) {
/* 239 */     Preconditions.checkArgument(material.isLegacy(), "ordinal on modern Material");
/*     */     
/* 241 */     return material.ordinal() - Material.LEGACY_AIR.ordinal();
/*     */   }
/*     */   
/*     */   public static String name(Material material) {
/* 245 */     return material.name().substring("LEGACY_".length());
/*     */   }
/*     */   
/*     */   public static String toString(Material material) {
/* 249 */     return name(material);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void init() {}
/*     */ 
/*     */   
/*     */   static {
/* 257 */     System.err.println("Initializing Legacy Material Support. Unless you have legacy plugins and/or data this is a bug!");
/* 258 */     if (MinecraftServer.getServer() != null && MinecraftServer.getServer().isDebugging()) {
/* 259 */       (new Exception()).printStackTrace();
/*     */     }
/*     */     
/* 262 */     SPAWN_EGGS.put(Byte.valueOf((byte)0), Material.PIG_SPAWN_EGG);
/*     */     
/* 264 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.BAT.getTypeId()), Material.BAT_SPAWN_EGG);
/* 265 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.BLAZE.getTypeId()), Material.BLAZE_SPAWN_EGG);
/* 266 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.CAVE_SPIDER.getTypeId()), Material.CAVE_SPIDER_SPAWN_EGG);
/* 267 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.CHICKEN.getTypeId()), Material.CHICKEN_SPAWN_EGG);
/* 268 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.COD.getTypeId()), Material.COD_SPAWN_EGG);
/* 269 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.COW.getTypeId()), Material.COW_SPAWN_EGG);
/* 270 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.CREEPER.getTypeId()), Material.CREEPER_SPAWN_EGG);
/* 271 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.DOLPHIN.getTypeId()), Material.DOLPHIN_SPAWN_EGG);
/* 272 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.DONKEY.getTypeId()), Material.DONKEY_SPAWN_EGG);
/* 273 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.ELDER_GUARDIAN.getTypeId()), Material.ELDER_GUARDIAN_SPAWN_EGG);
/* 274 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.ENDERMAN.getTypeId()), Material.ENDERMAN_SPAWN_EGG);
/* 275 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.ENDERMITE.getTypeId()), Material.ENDERMITE_SPAWN_EGG);
/* 276 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.EVOKER.getTypeId()), Material.EVOKER_SPAWN_EGG);
/* 277 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.GHAST.getTypeId()), Material.GHAST_SPAWN_EGG);
/* 278 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.GUARDIAN.getTypeId()), Material.GUARDIAN_SPAWN_EGG);
/* 279 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.HORSE.getTypeId()), Material.HORSE_SPAWN_EGG);
/* 280 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.HUSK.getTypeId()), Material.HUSK_SPAWN_EGG);
/* 281 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.LLAMA.getTypeId()), Material.LLAMA_SPAWN_EGG);
/* 282 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.MAGMA_CUBE.getTypeId()), Material.MAGMA_CUBE_SPAWN_EGG);
/* 283 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.MUSHROOM_COW.getTypeId()), Material.MOOSHROOM_SPAWN_EGG);
/* 284 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.MULE.getTypeId()), Material.MULE_SPAWN_EGG);
/* 285 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.OCELOT.getTypeId()), Material.OCELOT_SPAWN_EGG);
/* 286 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.PARROT.getTypeId()), Material.PARROT_SPAWN_EGG);
/* 287 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.PIG.getTypeId()), Material.PIG_SPAWN_EGG);
/* 288 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.PHANTOM.getTypeId()), Material.PHANTOM_SPAWN_EGG);
/* 289 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.POLAR_BEAR.getTypeId()), Material.POLAR_BEAR_SPAWN_EGG);
/* 290 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.PUFFERFISH.getTypeId()), Material.PUFFERFISH_SPAWN_EGG);
/* 291 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.RABBIT.getTypeId()), Material.RABBIT_SPAWN_EGG);
/* 292 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.SALMON.getTypeId()), Material.SALMON_SPAWN_EGG);
/* 293 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.SHEEP.getTypeId()), Material.SHEEP_SPAWN_EGG);
/* 294 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.SHULKER.getTypeId()), Material.SHULKER_SPAWN_EGG);
/* 295 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.SILVERFISH.getTypeId()), Material.SILVERFISH_SPAWN_EGG);
/* 296 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.SKELETON.getTypeId()), Material.SKELETON_SPAWN_EGG);
/* 297 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.SKELETON_HORSE.getTypeId()), Material.SKELETON_HORSE_SPAWN_EGG);
/* 298 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.SLIME.getTypeId()), Material.SLIME_SPAWN_EGG);
/* 299 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.SPIDER.getTypeId()), Material.SPIDER_SPAWN_EGG);
/* 300 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.SQUID.getTypeId()), Material.SQUID_SPAWN_EGG);
/* 301 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.STRAY.getTypeId()), Material.STRAY_SPAWN_EGG);
/* 302 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.TROPICAL_FISH.getTypeId()), Material.TROPICAL_FISH_SPAWN_EGG);
/* 303 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.TURTLE.getTypeId()), Material.TURTLE_SPAWN_EGG);
/* 304 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.VEX.getTypeId()), Material.VEX_SPAWN_EGG);
/* 305 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.VILLAGER.getTypeId()), Material.VILLAGER_SPAWN_EGG);
/* 306 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.VINDICATOR.getTypeId()), Material.VINDICATOR_SPAWN_EGG);
/* 307 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.WITCH.getTypeId()), Material.WITCH_SPAWN_EGG);
/* 308 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.WITHER_SKELETON.getTypeId()), Material.WITHER_SKELETON_SPAWN_EGG);
/* 309 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.WOLF.getTypeId()), Material.WOLF_SPAWN_EGG);
/* 310 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.ZOMBIE.getTypeId()), Material.ZOMBIE_SPAWN_EGG);
/* 311 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.ZOMBIE_HORSE.getTypeId()), Material.ZOMBIE_HORSE_SPAWN_EGG);
/* 312 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.ZOMBIFIED_PIGLIN.getTypeId()), Material.ZOMBIFIED_PIGLIN_SPAWN_EGG);
/* 313 */     SPAWN_EGGS.put(Byte.valueOf((byte)EntityType.ZOMBIE_VILLAGER.getTypeId()), Material.ZOMBIE_VILLAGER_SPAWN_EGG);
/*     */     
/* 315 */     DispenserRegistry.init();
/*     */     
/* 317 */     for (Material material : Material.values()) {
/* 318 */       if (material.isLegacy()) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 323 */         if (material.isBlock()) {
/* 324 */           byte b; for (b = 0; b < 16; b = (byte)(b + 1)) {
/* 325 */             MaterialData matData = new MaterialData(material, b);
/* 326 */             Dynamic blockTag = DataConverterFlattenData.b(material.getId() << 4 | b);
/* 327 */             blockTag = DataConverterRegistry.a().update(DataConverterTypes.BLOCK_STATE, blockTag, 100, CraftMagicNumbers.INSTANCE.getDataVersion());
/*     */             
/* 329 */             if (!blockTag.get("Name").asString("").contains("%%FILTER_ME%%")) {
/*     */ 
/*     */ 
/*     */               
/* 333 */               String name = blockTag.get("Name").asString("");
/* 334 */               Block block = (Block)IRegistry.BLOCK.get(new MinecraftKey(name));
/* 335 */               if (block != null) {
/*     */ 
/*     */                 
/* 338 */                 IBlockData blockData = block.getBlockData();
/* 339 */                 BlockStateList states = block.getStates();
/*     */                 
/* 341 */                 Optional<NBTTagCompound> propMap = blockTag.getElement("Properties").result();
/* 342 */                 if (propMap.isPresent()) {
/* 343 */                   NBTTagCompound properties = propMap.get();
/* 344 */                   for (String dataKey : properties.getKeys()) {
/* 345 */                     IBlockState state = states.a(dataKey);
/*     */                     
/* 347 */                     if (state == null) {
/* 348 */                       if (whitelistedStates.contains(dataKey)) {
/*     */                         continue;
/*     */                       }
/* 351 */                       throw new IllegalStateException("No state for " + dataKey);
/*     */                     } 
/*     */                     
/* 354 */                     Preconditions.checkState(!properties.getString(dataKey).isEmpty(), "Empty data string");
/* 355 */                     Optional<Comparable> opt = state.b(properties.getString(dataKey));
/* 356 */                     if (!opt.isPresent()) {
/* 357 */                       throw new IllegalStateException("No state value " + properties.getString(dataKey) + " for " + dataKey);
/*     */                     }
/*     */                     
/* 360 */                     blockData = (IBlockData)blockData.set(state, opt.get());
/*     */                   } 
/*     */                 } 
/*     */                 
/* 364 */                 if (block != Blocks.AIR) {
/*     */ 
/*     */ 
/*     */                   
/* 368 */                   materialToData.put(matData, blockData);
/* 369 */                   if (!dataToMaterial.containsKey(blockData)) {
/* 370 */                     dataToMaterial.put(blockData, matData);
/*     */                   }
/*     */                   
/* 373 */                   materialToBlock.put(matData, block);
/* 374 */                   if (!blockToMaterial.containsKey(block))
/* 375 */                     blockToMaterial.put(block, matData); 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/* 381 */         int maxData = (material.getMaxDurability() == 0) ? 16 : 1;
/*     */         
/* 383 */         if (material == Material.LEGACY_MONSTER_EGG) {
/* 384 */           maxData = 121;
/*     */         }
/*     */         byte data;
/* 387 */         for (data = 0; data < maxData; data = (byte)(data + 1)) {
/*     */           
/* 389 */           if (material != Material.LEGACY_MONSTER_EGG)
/*     */           {
/*     */ 
/*     */             
/* 393 */             if (DataConverterMaterialId.a(material.getId()) != null) {
/*     */ 
/*     */ 
/*     */               
/* 397 */               MaterialData matData = new MaterialData(material, data);
/*     */               
/* 399 */               NBTTagCompound stack = new NBTTagCompound();
/* 400 */               stack.setInt("id", material.getId());
/* 401 */               stack.setShort("Damage", (short)data);
/*     */               
/* 403 */               Dynamic<NBTBase> converted = DataConverterRegistry.a().update(DataConverterTypes.ITEM_STACK, new Dynamic((DynamicOps)DynamicOpsNBT.a, stack), -1, CraftMagicNumbers.INSTANCE.getDataVersion());
/*     */               
/* 405 */               String newId = converted.get("id").asString("");
/*     */               
/* 407 */               if (newId.equals("minecraft:spawn_egg")) {
/* 408 */                 newId = "minecraft:pig_spawn_egg";
/*     */               }
/*     */ 
/*     */               
/* 412 */               Item newMaterial = (Item)IRegistry.ITEM.get(new MinecraftKey(newId));
/*     */               
/* 414 */               if (newMaterial != Items.AIR) {
/*     */ 
/*     */ 
/*     */                 
/* 418 */                 materialToItem.put(matData, newMaterial);
/* 419 */                 if (!itemToMaterial.containsKey(newMaterial))
/* 420 */                   itemToMaterial.put(newMaterial, matData); 
/*     */               } 
/*     */             }  } 
/*     */         } 
/* 424 */         for (Map.Entry<Byte, Material> entry : SPAWN_EGGS.entrySet()) {
/* 425 */           MaterialData matData = new MaterialData(Material.LEGACY_MONSTER_EGG, ((Byte)entry.getKey()).byteValue());
/* 426 */           Item newMaterial = CraftMagicNumbers.getItem(entry.getValue());
/*     */           
/* 428 */           materialToItem.put(matData, newMaterial);
/* 429 */           itemToMaterial.put(newMaterial, matData);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public static void main(String[] args) {
/* 435 */     System.err.println("");
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\legacy\CraftLegacy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */