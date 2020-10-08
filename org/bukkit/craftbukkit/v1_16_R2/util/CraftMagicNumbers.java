/*     */ package org.bukkit.craftbukkit.v1_16_R2.util;
/*     */ import co.aikar.timings.TimingsExport;
/*     */ import com.destroystokyo.paper.util.SneakyThrow;
/*     */ import com.destroystokyo.paper.util.VersionFetcher;
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.io.Files;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import com.mojang.serialization.DynamicOps;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import net.minecraft.server.v1_16_R2.Advancement;
/*     */ import net.minecraft.server.v1_16_R2.AdvancementDataWorld;
/*     */ import net.minecraft.server.v1_16_R2.Block;
/*     */ import net.minecraft.server.v1_16_R2.DataConverterRegistry;
/*     */ import net.minecraft.server.v1_16_R2.DataConverterTypes;
/*     */ import net.minecraft.server.v1_16_R2.DynamicOpsNBT;
/*     */ import net.minecraft.server.v1_16_R2.EntityTypes;
/*     */ import net.minecraft.server.v1_16_R2.FluidType;
/*     */ import net.minecraft.server.v1_16_R2.IBlockData;
/*     */ import net.minecraft.server.v1_16_R2.IRegistry;
/*     */ import net.minecraft.server.v1_16_R2.Item;
/*     */ import net.minecraft.server.v1_16_R2.ItemStack;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftKey;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*     */ import net.minecraft.server.v1_16_R2.NBTBase;
/*     */ import net.minecraft.server.v1_16_R2.NBTCompressedStreamTools;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Fluid;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.bukkit.UnsafeValues;
/*     */ import org.bukkit.advancement.Advancement;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.data.BlockData;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlock;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.legacy.CraftLegacy;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.material.MaterialData;
/*     */ import org.bukkit.plugin.InvalidPluginException;
/*     */ import org.bukkit.plugin.PluginDescriptionFile;
/*     */ 
/*     */ public final class CraftMagicNumbers implements UnsafeValues {
/*  59 */   public static final UnsafeValues INSTANCE = new CraftMagicNumbers();
/*     */ 
/*     */ 
/*     */   
/*     */   public static IBlockData getBlock(MaterialData material) {
/*  64 */     return getBlock(material.getItemType(), material.getData());
/*     */   }
/*     */   
/*     */   public static IBlockData getBlock(Material material, byte data) {
/*  68 */     return CraftLegacy.fromLegacyData(CraftLegacy.toLegacy(material), data);
/*     */   }
/*     */   
/*     */   public static MaterialData getMaterial(IBlockData data) {
/*  72 */     return CraftLegacy.toLegacy(getMaterial(data.getBlock())).getNewData(toLegacyData(data));
/*     */   }
/*     */   
/*     */   public static Item getItem(Material material, short data) {
/*  76 */     if (material.isLegacy()) {
/*  77 */       return CraftLegacy.fromLegacyData(CraftLegacy.toLegacy(material), data);
/*     */     }
/*     */     
/*  80 */     return getItem(material);
/*     */   }
/*     */   
/*     */   public static MaterialData getMaterialData(Item item) {
/*  84 */     return CraftLegacy.toLegacyData(getMaterial(item));
/*     */   }
/*     */ 
/*     */   
/*  88 */   private static final Map<Block, Material> BLOCK_MATERIAL = new HashMap<>();
/*  89 */   private static final Map<Item, Material> ITEM_MATERIAL = new HashMap<>();
/*  90 */   private static final Map<FluidType, Fluid> FLUID_MATERIAL = new HashMap<>();
/*  91 */   private static final Map<Material, Item> MATERIAL_ITEM = new HashMap<>();
/*  92 */   private static final Map<Material, Block> MATERIAL_BLOCK = new HashMap<>();
/*  93 */   private static final Map<Material, FluidType> MATERIAL_FLUID = new HashMap<>();
/*     */   
/*     */   static {
/*  96 */     for (Block block : IRegistry.BLOCK) {
/*  97 */       BLOCK_MATERIAL.put(block, Material.getMaterial(IRegistry.BLOCK.getKey(block).getKey().toUpperCase(Locale.ROOT)));
/*     */     }
/*     */     
/* 100 */     for (Item item : IRegistry.ITEM) {
/* 101 */       ITEM_MATERIAL.put(item, Material.getMaterial(IRegistry.ITEM.getKey(item).getKey().toUpperCase(Locale.ROOT)));
/*     */     }
/*     */     
/* 104 */     for (FluidType fluid : IRegistry.FLUID) {
/* 105 */       FLUID_MATERIAL.put(fluid, (Fluid)Registry.FLUID.get(CraftNamespacedKey.fromMinecraft(IRegistry.FLUID.getKey(fluid))));
/*     */     }
/*     */     
/* 108 */     for (Material material : Material.values()) {
/* 109 */       if (!material.isLegacy()) {
/*     */ 
/*     */ 
/*     */         
/* 113 */         MinecraftKey key = key(material);
/* 114 */         IRegistry.ITEM.getOptional(key).ifPresent(item -> MATERIAL_ITEM.put(material, item));
/*     */ 
/*     */         
/* 117 */         IRegistry.BLOCK.getOptional(key).ifPresent(block -> MATERIAL_BLOCK.put(material, block));
/*     */ 
/*     */         
/* 120 */         IRegistry.FLUID.getOptional(key).ifPresent(fluid -> MATERIAL_FLUID.put(material, fluid));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Material getMaterial(Block block) {
/* 127 */     return BLOCK_MATERIAL.get(block);
/*     */   }
/*     */   
/*     */   public static Material getMaterial(Item item) {
/* 131 */     return ITEM_MATERIAL.getOrDefault(item, Material.AIR);
/*     */   }
/*     */   
/*     */   public static Fluid getFluid(FluidType fluid) {
/* 135 */     return FLUID_MATERIAL.get(fluid);
/*     */   }
/*     */   
/*     */   public static Item getItem(Material material) {
/* 139 */     if (material != null && material.isLegacy()) {
/* 140 */       material = CraftLegacy.fromLegacy(material);
/*     */     }
/*     */     
/* 143 */     return MATERIAL_ITEM.get(material);
/*     */   }
/*     */   
/*     */   public static Block getBlock(Material material) {
/* 147 */     if (material != null && material.isLegacy()) {
/* 148 */       material = CraftLegacy.fromLegacy(material);
/*     */     }
/*     */     
/* 151 */     return MATERIAL_BLOCK.get(material);
/*     */   }
/*     */   
/*     */   public static FluidType getFluid(Fluid fluid) {
/* 155 */     return MATERIAL_FLUID.get(fluid);
/*     */   }
/*     */   
/*     */   public static MinecraftKey key(Material mat) {
/* 159 */     return CraftNamespacedKey.toMinecraft(mat.getKey());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void reportTimings() {
/* 165 */     TimingsExport.reportTimings();
/*     */   }
/*     */ 
/*     */   
/*     */   public static byte toLegacyData(IBlockData data) {
/* 170 */     return CraftLegacy.toLegacyData(data);
/*     */   }
/*     */ 
/*     */   
/*     */   public Material toLegacy(Material material) {
/* 175 */     return CraftLegacy.toLegacy(material);
/*     */   }
/*     */ 
/*     */   
/*     */   public Material fromLegacy(Material material) {
/* 180 */     return CraftLegacy.fromLegacy(material);
/*     */   }
/*     */ 
/*     */   
/*     */   public Material fromLegacy(MaterialData material) {
/* 185 */     return CraftLegacy.fromLegacy(material);
/*     */   }
/*     */ 
/*     */   
/*     */   public Material fromLegacy(MaterialData material, boolean itemPriority) {
/* 190 */     return CraftLegacy.fromLegacy(material, itemPriority);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockData fromLegacy(Material material, byte data) {
/* 195 */     return (BlockData)CraftBlockData.fromData(getBlock(material, data));
/*     */   }
/*     */ 
/*     */   
/*     */   public Material getMaterial(String material, int version) {
/* 200 */     Preconditions.checkArgument((material != null), "material == null");
/* 201 */     Preconditions.checkArgument((version <= getDataVersion()), "Newer version! Server downgrades are not supported!");
/*     */ 
/*     */     
/* 204 */     if (version == getDataVersion()) {
/* 205 */       return Material.getMaterial(material);
/*     */     }
/*     */     
/* 208 */     Dynamic<NBTBase> name = new Dynamic((DynamicOps)DynamicOpsNBT.a, NBTTagString.a("minecraft:" + material.toLowerCase(Locale.ROOT)));
/* 209 */     Dynamic<NBTBase> converted = DataConverterRegistry.a().update(DataConverterTypes.ITEM_NAME, name, version, getDataVersion());
/*     */     
/* 211 */     if (name.equals(converted)) {
/* 212 */       converted = DataConverterRegistry.a().update(DataConverterTypes.BLOCK_NAME, name, version, getDataVersion());
/*     */     }
/*     */     
/* 215 */     return Material.matchMaterial(converted.asString(""));
/*     */   }
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
/*     */   public String getMappingsVersion() {
/* 234 */     return "09f04031f41cb54f1077c6ac348cc220";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDataVersion() {
/* 239 */     return SharedConstants.getGameVersion().getWorldVersion();
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack modifyItemStack(ItemStack stack, String arguments) {
/* 244 */     ItemStack nmsStack = CraftItemStack.asNMSCopy(stack);
/*     */     
/*     */     try {
/* 247 */       nmsStack.setTag(MojangsonParser.parse(arguments));
/* 248 */     } catch (CommandSyntaxException ex) {
/* 249 */       Logger.getLogger(CraftMagicNumbers.class.getName()).log(Level.SEVERE, (String)null, (Throwable)ex);
/*     */     } 
/*     */     
/* 252 */     stack.setItemMeta(CraftItemStack.getItemMeta(nmsStack));
/*     */     
/* 254 */     return stack;
/*     */   }
/*     */   
/*     */   private static File getBukkitDataPackFolder() {
/* 258 */     return new File(MinecraftServer.getServer().a(SavedFile.DATAPACKS).toFile(), "bukkit");
/*     */   }
/*     */ 
/*     */   
/*     */   public Advancement loadAdvancement(NamespacedKey key, String advancement) {
/* 263 */     if (Bukkit.getAdvancement(key) != null) {
/* 264 */       throw new IllegalArgumentException("Advancement " + key + " already exists.");
/*     */     }
/* 266 */     MinecraftKey minecraftkey = CraftNamespacedKey.toMinecraft(key);
/*     */     
/* 268 */     JsonElement jsonelement = (JsonElement)AdvancementDataWorld.DESERIALIZER.fromJson(advancement, JsonElement.class);
/* 269 */     JsonObject jsonobject = ChatDeserializer.m(jsonelement, "advancement");
/* 270 */     Advancement.SerializedAdvancement nms = Advancement.SerializedAdvancement.a(jsonobject, new LootDeserializationContext(minecraftkey, MinecraftServer.getServer().getLootPredicateManager()));
/* 271 */     if (nms != null) {
/* 272 */       (MinecraftServer.getServer().getAdvancementData()).REGISTRY.a(Maps.newHashMap(Collections.singletonMap(minecraftkey, nms)));
/* 273 */       Advancement bukkit = Bukkit.getAdvancement(key);
/*     */       
/* 275 */       if (bukkit != null) {
/* 276 */         File file = new File(getBukkitDataPackFolder(), "data" + File.separator + key.getNamespace() + File.separator + "advancements" + File.separator + key.getKey() + ".json");
/* 277 */         file.getParentFile().mkdirs();
/*     */         
/*     */         try {
/* 280 */           Files.write(advancement, file, Charsets.UTF_8);
/* 281 */         } catch (IOException ex) {
/* 282 */           Bukkit.getLogger().log(Level.SEVERE, "Error saving advancement " + key, ex);
/*     */         } 
/*     */         
/* 285 */         MinecraftServer.getServer().getPlayerList().reload();
/*     */         
/* 287 */         return bukkit;
/*     */       } 
/*     */     } 
/*     */     
/* 291 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removeAdvancement(NamespacedKey key) {
/* 296 */     File file = new File(getBukkitDataPackFolder(), "data" + File.separator + key.getNamespace() + File.separator + "advancements" + File.separator + key.getKey() + ".json");
/* 297 */     return file.delete();
/*     */   }
/*     */   
/* 300 */   private static final List<String> SUPPORTED_API = Arrays.asList(new String[] { "1.13", "1.14", "1.15", "1.16" });
/*     */ 
/*     */   
/*     */   public void checkSupported(PluginDescriptionFile pdf) throws InvalidPluginException {
/* 304 */     String minimumVersion = (MinecraftServer.getServer()).server.minimumAPI;
/* 305 */     int minimumIndex = SUPPORTED_API.indexOf(minimumVersion);
/*     */     
/* 307 */     if (pdf.getAPIVersion() != null) {
/* 308 */       int pluginIndex = SUPPORTED_API.indexOf(pdf.getAPIVersion());
/*     */       
/* 310 */       if (pluginIndex == -1) {
/* 311 */         throw new InvalidPluginException("Unsupported API version " + pdf.getAPIVersion());
/*     */       }
/*     */       
/* 314 */       if (pluginIndex < minimumIndex) {
/* 315 */         throw new InvalidPluginException("Plugin API version " + pdf.getAPIVersion() + " is lower than the minimum allowed version. Please update or replace it.");
/*     */       }
/*     */     }
/* 318 */     else if (minimumIndex == -1) {
/* 319 */       CraftLegacy.init();
/* 320 */       Bukkit.getLogger().log(Level.WARNING, "Legacy plugin " + pdf.getFullName() + " does not specify an api-version.");
/*     */     } else {
/* 322 */       throw new InvalidPluginException("Plugin API version " + pdf.getAPIVersion() + " is lower than the minimum allowed version. Please update or replace it.");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isLegacy(PluginDescriptionFile pdf) {
/* 328 */     return (pdf.getAPIVersion() == null);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] processClass(PluginDescriptionFile pdf, String path, byte[] clazz) {
/*     */     try {
/* 334 */       clazz = Commodore.convert(clazz, !isLegacy(pdf));
/* 335 */     } catch (Exception ex) {
/* 336 */       Bukkit.getLogger().log(Level.SEVERE, "Fatal error trying to convert " + pdf.getFullName() + ":" + path, ex);
/*     */     } 
/*     */     
/* 339 */     return clazz;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTimingsServerName() {
/* 345 */     return PaperConfig.timingsServerName;
/*     */   }
/*     */ 
/*     */   
/*     */   public VersionFetcher getVersionFetcher() {
/* 350 */     return (VersionFetcher)new PaperVersionFetcher();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSupportedApiVersion(String apiVersion) {
/* 355 */     return (apiVersion != null && SUPPORTED_API.contains(apiVersion));
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] serializeItem(ItemStack item) {
/* 360 */     Preconditions.checkNotNull(item, "null cannot be serialized");
/* 361 */     Preconditions.checkArgument((item.getType() != Material.AIR), "air cannot be serialized");
/*     */     
/* 363 */     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
/* 364 */     NBTTagCompound compound = ((item instanceof CraftItemStack) ? ((CraftItemStack)item).getHandle() : CraftItemStack.asNMSCopy(item)).save(new NBTTagCompound());
/* 365 */     compound.setInt("DataVersion", getDataVersion());
/*     */     try {
/* 367 */       NBTCompressedStreamTools.writeNBT(compound, outputStream);
/*     */ 
/*     */     
/*     */     }
/* 371 */     catch (IOException ex) {
/* 372 */       throw new RuntimeException(ex);
/*     */     } 
/*     */     
/* 375 */     return outputStream.toByteArray();
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack deserializeItem(byte[] data) {
/* 380 */     Preconditions.checkNotNull(data, "null cannot be deserialized");
/* 381 */     Preconditions.checkArgument((data.length > 0), "cannot deserialize nothing");
/*     */     
/*     */     try {
/* 384 */       NBTTagCompound compound = NBTCompressedStreamTools.readNBT(new ByteArrayInputStream(data));
/*     */ 
/*     */       
/* 387 */       int dataVersion = compound.getInt("DataVersion");
/*     */       
/* 389 */       Preconditions.checkArgument((dataVersion <= getDataVersion()), "Newer version! Server downgrades are not supported!");
/* 390 */       Dynamic<NBTBase> converted = DataConverterRegistry.getDataFixer().update(DataConverterTypes.ITEM_STACK, new Dynamic((DynamicOps)DynamicOpsNBT.a, compound), dataVersion, getDataVersion());
/* 391 */       return (ItemStack)CraftItemStack.asCraftMirror(ItemStack.fromCompound((NBTTagCompound)converted.getValue()));
/* 392 */     } catch (IOException ex) {
/* 393 */       SneakyThrow.sneaky(ex);
/* 394 */       throw new RuntimeException();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTranslationKey(Material mat) {
/* 400 */     return getItem(mat).getOrCreateDescriptionId();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTranslationKey(Block block) {
/* 405 */     return ((CraftBlock)block).getNMS().getBlock().getDescriptionId();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTranslationKey(EntityType type) {
/* 410 */     return EntityTypes.getByName(type.getName()).map(EntityTypes::getDescriptionId).orElse(null);
/*     */   }
/*     */   
/*     */   public static class NBT {
/*     */     public static final int TAG_END = 0;
/*     */     public static final int TAG_BYTE = 1;
/*     */     public static final int TAG_SHORT = 2;
/*     */     public static final int TAG_INT = 3;
/*     */     public static final int TAG_LONG = 4;
/*     */     public static final int TAG_FLOAT = 5;
/*     */     public static final int TAG_DOUBLE = 6;
/*     */     public static final int TAG_BYTE_ARRAY = 7;
/*     */     public static final int TAG_STRING = 8;
/*     */     public static final int TAG_LIST = 9;
/*     */     public static final int TAG_COMPOUND = 10;
/*     */     public static final int TAG_INT_ARRAY = 11;
/*     */     public static final int TAG_ANY_NUMBER = 99;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R\\util\CraftMagicNumbers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */