/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
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
/*     */ public class GameTestHarnessStructures
/*     */ {
/*  48 */   public static String a = "gameteststructures";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static EnumBlockRotation a(int var0) {
/*  54 */     switch (var0) { case 0:
/*  55 */         return EnumBlockRotation.NONE;
/*  56 */       case 1: return EnumBlockRotation.CLOCKWISE_90;
/*  57 */       case 2: return EnumBlockRotation.CLOCKWISE_180;
/*  58 */       case 3: return EnumBlockRotation.COUNTERCLOCKWISE_90; }
/*  59 */      throw new IllegalArgumentException("rotationSteps must be a value from 0-3. Got value " + var0);
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
/*     */   public static AxisAlignedBB a(TileEntityStructure var0) {
/*  74 */     BlockPosition var1 = var0.getPosition();
/*  75 */     BlockPosition var2 = var1.a(var0.j().b(-1, -1, -1));
/*  76 */     BlockPosition var3 = DefinedStructure.a(var2, EnumBlockMirror.NONE, var0.l(), var1);
/*     */     
/*  78 */     return new AxisAlignedBB(var1, var3);
/*     */   }
/*     */   
/*     */   public static StructureBoundingBox b(TileEntityStructure var0) {
/*  82 */     BlockPosition var1 = var0.getPosition();
/*  83 */     BlockPosition var2 = var1.a(var0.j().b(-1, -1, -1));
/*  84 */     BlockPosition var3 = DefinedStructure.a(var2, EnumBlockMirror.NONE, var0.l(), var1);
/*     */     
/*  86 */     return new StructureBoundingBox(var1, var3);
/*     */   }
/*     */   
/*     */   public static void a(BlockPosition var0, BlockPosition var1, EnumBlockRotation var2, WorldServer var3) {
/*  90 */     BlockPosition var4 = DefinedStructure.a(var0.a(var1), EnumBlockMirror.NONE, var2, var0);
/*  91 */     var3.setTypeUpdate(var4, Blocks.COMMAND_BLOCK.getBlockData());
/*  92 */     TileEntityCommand var5 = (TileEntityCommand)var3.getTileEntity(var4);
/*  93 */     var5.getCommandBlock().setCommand("test runthis");
/*     */     
/*  95 */     BlockPosition var6 = DefinedStructure.a(var4.b(0, 0, -1), EnumBlockMirror.NONE, var2, var4);
/*     */     
/*  97 */     var3.setTypeUpdate(var6, Blocks.STONE_BUTTON.getBlockData().a(var2));
/*     */   }
/*     */   
/*     */   public static void a(String var0, BlockPosition var1, BlockPosition var2, EnumBlockRotation var3, WorldServer var4) {
/* 101 */     StructureBoundingBox var5 = a(var1, var2, var3);
/* 102 */     a(var5, var1.getY(), var4);
/*     */     
/* 104 */     var4.setTypeUpdate(var1, Blocks.STRUCTURE_BLOCK.getBlockData());
/*     */     
/* 106 */     TileEntityStructure var6 = (TileEntityStructure)var4.getTileEntity(var1);
/* 107 */     var6.a(false);
/* 108 */     var6.a(new MinecraftKey(var0));
/* 109 */     var6.c(var2);
/* 110 */     var6.setUsageMode(BlockPropertyStructureMode.SAVE);
/* 111 */     var6.e(true);
/*     */   }
/*     */   
/*     */   public static TileEntityStructure a(String var0, BlockPosition var1, EnumBlockRotation var2, int var3, WorldServer var4, boolean var5) {
/* 115 */     BlockPosition var8, var6 = a(var0, var4).a();
/* 116 */     StructureBoundingBox var7 = a(var1, var6, var2);
/*     */ 
/*     */     
/* 119 */     if (var2 == EnumBlockRotation.NONE) {
/* 120 */       var8 = var1;
/* 121 */     } else if (var2 == EnumBlockRotation.CLOCKWISE_90) {
/* 122 */       var8 = var1.b(var6.getZ() - 1, 0, 0);
/* 123 */     } else if (var2 == EnumBlockRotation.CLOCKWISE_180) {
/* 124 */       var8 = var1.b(var6.getX() - 1, 0, var6.getZ() - 1);
/* 125 */     } else if (var2 == EnumBlockRotation.COUNTERCLOCKWISE_90) {
/* 126 */       var8 = var1.b(0, 0, var6.getX() - 1);
/*     */     } else {
/* 128 */       throw new IllegalArgumentException("Invalid rotation: " + var2);
/*     */     } 
/*     */     
/* 131 */     a(var1, var4);
/*     */     
/* 133 */     a(var7, var1.getY(), var4);
/* 134 */     TileEntityStructure var9 = a(var0, var8, var2, var4, var5);
/* 135 */     var4.getBlockTickList().a(var7, true, false);
/* 136 */     var4.a(var7);
/* 137 */     return var9;
/*     */   }
/*     */   
/*     */   private static void a(BlockPosition var0, WorldServer var1) {
/* 141 */     ChunkCoordIntPair var2 = new ChunkCoordIntPair(var0);
/*     */ 
/*     */     
/* 144 */     for (int var3 = -1; var3 < 4; var3++) {
/* 145 */       for (int var4 = -1; var4 < 4; var4++) {
/* 146 */         int var5 = var2.x + var3;
/* 147 */         int var6 = var2.z + var4;
/* 148 */         var1.setForceLoaded(var5, var6, true);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void a(StructureBoundingBox var0, int var1, WorldServer var2) {
/* 155 */     StructureBoundingBox var3 = new StructureBoundingBox(var0.a - 2, var0.b - 3, var0.c - 3, var0.d + 3, var0.e + 20, var0.f + 3);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 164 */     BlockPosition.a(var3).forEach(var2 -> a(var0, var2, var1));
/* 165 */     var2.getBlockTickList().a(var3, true, false);
/* 166 */     var2.a(var3);
/* 167 */     AxisAlignedBB var4 = new AxisAlignedBB(var3.a, var3.b, var3.c, var3.d, var3.e, var3.f);
/* 168 */     List<Entity> var5 = var2.a(Entity.class, var4, var0 -> !(var0 instanceof EntityHuman));
/* 169 */     var5.forEach(Entity::die);
/*     */   }
/*     */   
/*     */   public static StructureBoundingBox a(BlockPosition var0, BlockPosition var1, EnumBlockRotation var2) {
/* 173 */     BlockPosition var3 = var0.a(var1).b(-1, -1, -1);
/* 174 */     BlockPosition var4 = DefinedStructure.a(var3, EnumBlockMirror.NONE, var2, var0);
/* 175 */     StructureBoundingBox var5 = StructureBoundingBox.a(var0.getX(), var0.getY(), var0.getZ(), var4.getX(), var4.getY(), var4.getZ());
/*     */     
/* 177 */     int var6 = Math.min(var5.a, var5.d);
/* 178 */     int var7 = Math.min(var5.c, var5.f);
/*     */ 
/*     */     
/* 181 */     BlockPosition var8 = new BlockPosition(var0.getX() - var6, 0, var0.getZ() - var7);
/* 182 */     var5.a(var8);
/* 183 */     return var5;
/*     */   }
/*     */   
/*     */   public static Optional<BlockPosition> a(BlockPosition var0, int var1, WorldServer var2) {
/* 187 */     return c(var0, var1, var2).stream()
/* 188 */       .filter(var2 -> a(var2, var0, var1))
/* 189 */       .findFirst();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static BlockPosition b(BlockPosition var0, int var1, WorldServer var2) {
/* 194 */     Comparator<BlockPosition> var3 = Comparator.comparingInt(var1 -> var1.k(var0));
/*     */     
/* 196 */     Collection<BlockPosition> var4 = c(var0, var1, var2);
/* 197 */     Optional<BlockPosition> var5 = var4.stream().min(var3);
/* 198 */     return var5.orElse(null);
/*     */   }
/*     */   
/*     */   public static Collection<BlockPosition> c(BlockPosition var0, int var1, WorldServer var2) {
/* 202 */     Collection<BlockPosition> var3 = Lists.newArrayList();
/*     */     
/* 204 */     AxisAlignedBB var4 = new AxisAlignedBB(var0);
/* 205 */     var4 = var4.g(var1);
/* 206 */     for (int var5 = (int)var4.minX; var5 <= (int)var4.maxX; var5++) {
/* 207 */       for (int var6 = (int)var4.minY; var6 <= (int)var4.maxY; var6++) {
/* 208 */         for (int var7 = (int)var4.minZ; var7 <= (int)var4.maxZ; var7++) {
/* 209 */           BlockPosition var8 = new BlockPosition(var5, var6, var7);
/* 210 */           IBlockData var9 = var2.getType(var8);
/* 211 */           if (var9.a(Blocks.STRUCTURE_BLOCK)) {
/* 212 */             var3.add(var8);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 217 */     return var3;
/*     */   }
/*     */   
/*     */   private static DefinedStructure a(String var0, WorldServer var1) {
/* 221 */     DefinedStructureManager var2 = var1.n();
/*     */ 
/*     */     
/* 224 */     DefinedStructure var3 = var2.b(new MinecraftKey(var0));
/* 225 */     if (var3 != null) {
/* 226 */       return var3;
/*     */     }
/*     */ 
/*     */     
/* 230 */     String var4 = var0 + ".snbt";
/* 231 */     Path var5 = Paths.get(a, new String[] { var4 });
/* 232 */     NBTTagCompound var6 = a(var5);
/* 233 */     if (var6 == null) {
/* 234 */       throw new RuntimeException("Could not find structure file " + var5 + ", and the structure is not available in the world structures either.");
/*     */     }
/*     */     
/* 237 */     return var2.a(var6);
/*     */   }
/*     */   
/*     */   private static TileEntityStructure a(String var0, BlockPosition var1, EnumBlockRotation var2, WorldServer var3, boolean var4) {
/* 241 */     var3.setTypeUpdate(var1, Blocks.STRUCTURE_BLOCK.getBlockData());
/*     */     
/* 243 */     TileEntityStructure var5 = (TileEntityStructure)var3.getTileEntity(var1);
/* 244 */     var5.setUsageMode(BlockPropertyStructureMode.LOAD);
/* 245 */     var5.b(var2);
/* 246 */     var5.a(false);
/* 247 */     var5.a(new MinecraftKey(var0));
/*     */     
/* 249 */     var5.a(var3, var4);
/* 250 */     if (var5.j() != BlockPosition.ZERO) {
/* 251 */       return var5;
/*     */     }
/* 253 */     DefinedStructure var6 = a(var0, var3);
/* 254 */     var5.a(var3, var4, var6);
/* 255 */     if (var5.j() == BlockPosition.ZERO) {
/* 256 */       throw new RuntimeException("Failed to load structure " + var0);
/*     */     }
/* 258 */     return var5;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private static NBTTagCompound a(Path var0) {
/*     */     try {
/* 266 */       BufferedReader var1 = Files.newBufferedReader(var0);
/* 267 */       String var2 = IOUtils.toString(var1);
/* 268 */       return MojangsonParser.parse(var2);
/* 269 */     } catch (IOException var1) {
/* 270 */       return null;
/* 271 */     } catch (CommandSyntaxException var1) {
/* 272 */       throw new RuntimeException("Error while trying to load structure " + var0, var1);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void a(int var0, BlockPosition var1, WorldServer var2) {
/* 277 */     IBlockData var3 = null;
/*     */     
/* 279 */     GeneratorSettingsFlat var4 = GeneratorSettingsFlat.a(var2.r().b(IRegistry.ay));
/* 280 */     if (var4 instanceof GeneratorSettingsFlat) {
/* 281 */       IBlockData[] arrayOfIBlockData = var4.g();
/* 282 */       if (var1.getY() < var0 && var1.getY() <= arrayOfIBlockData.length) {
/* 283 */         var3 = arrayOfIBlockData[var1.getY() - 1];
/*     */       }
/*     */     }
/* 286 */     else if (var1.getY() == var0 - 1) {
/* 287 */       var3 = var2.getBiome(var1).e().e().a();
/* 288 */     } else if (var1.getY() < var0 - 1) {
/* 289 */       var3 = var2.getBiome(var1).e().e().b();
/*     */     } 
/*     */     
/* 292 */     if (var3 == null) {
/* 293 */       var3 = Blocks.AIR.getBlockData();
/*     */     }
/* 295 */     ArgumentTileLocation var5 = new ArgumentTileLocation(var3, Collections.emptySet(), null);
/* 296 */     var5.a(var2, var1, 2);
/* 297 */     var2.update(var1, var3.getBlock());
/*     */   }
/*     */   
/*     */   private static boolean a(BlockPosition var0, BlockPosition var1, WorldServer var2) {
/* 301 */     TileEntityStructure var3 = (TileEntityStructure)var2.getTileEntity(var0);
/* 302 */     AxisAlignedBB var4 = a(var3).g(1.0D);
/* 303 */     return var4.d(Vec3D.a(var1));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GameTestHarnessStructures.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */