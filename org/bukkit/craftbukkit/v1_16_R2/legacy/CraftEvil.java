/*     */ package org.bukkit.craftbukkit.v1_16_R2.legacy;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectLinkedOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockState;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlock;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlockState;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public final class CraftEvil
/*     */ {
/*  21 */   private static final Int2ObjectMap<Material> byId = (Int2ObjectMap<Material>)new Int2ObjectLinkedOpenHashMap();
/*     */   
/*     */   static {
/*  24 */     for (Material material : Material.values()) {
/*  25 */       if (material.isLegacy()) {
/*     */ 
/*     */ 
/*     */         
/*  29 */         Preconditions.checkState(!byId.containsKey(material.getId()), "Duplicate material ID for", material);
/*  30 */         byId.put(material.getId(), material);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getBlockTypeIdAt(World world, int x, int y, int z) {
/*  39 */     return getId(world.getBlockAt(x, y, z).getType());
/*     */   }
/*     */   
/*     */   public static int getBlockTypeIdAt(World world, Location location) {
/*  43 */     return getId(world.getBlockAt(location).getType());
/*     */   }
/*     */   
/*     */   public static Material getType(Block block) {
/*  47 */     return CraftLegacy.toLegacyMaterial(((CraftBlock)block).getNMS());
/*     */   }
/*     */   
/*     */   public static Material getType(BlockState block) {
/*  51 */     return CraftLegacy.toLegacyMaterial(((CraftBlockState)block).getHandle());
/*     */   }
/*     */   
/*     */   public static int getTypeId(Block block) {
/*  55 */     return getId(block.getType());
/*     */   }
/*     */   
/*     */   public static boolean setTypeId(Block block, int type) {
/*  59 */     block.setType(getMaterial(type));
/*  60 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean setTypeId(Block block, int type, boolean applyPhysics) {
/*  64 */     block.setType(getMaterial(type), applyPhysics);
/*  65 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean setTypeIdAndData(Block block, int type, byte data, boolean applyPhysics) {
/*  69 */     block.setType(getMaterial(type), applyPhysics);
/*  70 */     setData(block, data);
/*  71 */     return true;
/*     */   }
/*     */   
/*     */   public static void setData(Block block, byte data) {
/*  75 */     ((CraftBlock)block).setData(data);
/*     */   }
/*     */   
/*     */   public static void setData(Block block, byte data, boolean applyPhysics) {
/*  79 */     ((CraftBlock)block).setData(data, applyPhysics);
/*     */   }
/*     */   
/*     */   public static int getTypeId(BlockState state) {
/*  83 */     return getId(state.getType());
/*     */   }
/*     */   
/*     */   public static boolean setTypeId(BlockState state, int type) {
/*  87 */     state.setType(getMaterial(type));
/*  88 */     return true;
/*     */   }
/*     */   
/*     */   public static int getTypeId(ItemStack stack) {
/*  92 */     return getId(stack.getType());
/*     */   }
/*     */   
/*     */   public static void setTypeId(ItemStack stack, int type) {
/*  96 */     stack.setType(getMaterial(type));
/*     */   }
/*     */   
/*     */   public static Material getMaterial(int id) {
/* 100 */     return (Material)byId.get(id);
/*     */   }
/*     */   
/*     */   public static int getId(Material material) {
/* 104 */     return CraftLegacy.toLegacy(material).getId();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\legacy\CraftEvil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */