/*     */ package net.minecraft.server.v1_16_R2;
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
/*     */ public class OldChunkLoader
/*     */ {
/*     */   public static OldChunk a(NBTTagCompound var0) {
/*  18 */     int var1 = var0.getInt("xPos");
/*  19 */     int var2 = var0.getInt("zPos");
/*     */     
/*  21 */     OldChunk var3 = new OldChunk(var1, var2);
/*  22 */     var3.g = var0.getByteArray("Blocks");
/*  23 */     var3.f = new OldNibbleArray(var0.getByteArray("Data"), 7);
/*  24 */     var3.e = new OldNibbleArray(var0.getByteArray("SkyLight"), 7);
/*  25 */     var3.d = new OldNibbleArray(var0.getByteArray("BlockLight"), 7);
/*  26 */     var3.c = var0.getByteArray("HeightMap");
/*  27 */     var3.b = var0.getBoolean("TerrainPopulated");
/*  28 */     var3.h = var0.getList("Entities", 10);
/*  29 */     var3.i = var0.getList("TileEntities", 10);
/*  30 */     var3.j = var0.getList("TileTicks", 10);
/*     */ 
/*     */     
/*     */     try {
/*  34 */       var3.a = var0.getLong("LastUpdate");
/*  35 */     } catch (ClassCastException var4) {
/*  36 */       var3.a = var0.getInt("LastUpdate");
/*     */     } 
/*     */     
/*  39 */     return var3;
/*     */   }
/*     */   
/*     */   public static void a(IRegistryCustom.Dimension var0, OldChunk var1, NBTTagCompound var2, WorldChunkManager var3) {
/*  43 */     var2.setInt("xPos", var1.k);
/*  44 */     var2.setInt("zPos", var1.l);
/*  45 */     var2.setLong("LastUpdate", var1.a);
/*  46 */     int[] var4 = new int[var1.c.length];
/*  47 */     for (int i = 0; i < var1.c.length; i++) {
/*  48 */       var4[i] = var1.c[i];
/*     */     }
/*  50 */     var2.setIntArray("HeightMap", var4);
/*  51 */     var2.setBoolean("TerrainPopulated", var1.b);
/*     */     
/*  53 */     NBTTagList var5 = new NBTTagList();
/*  54 */     for (int var6 = 0; var6 < 8; var6++) {
/*     */       
/*  56 */       boolean var7 = true;
/*  57 */       for (int var8 = 0; var8 < 16 && var7; var8++) {
/*  58 */         for (int var9 = 0; var9 < 16 && var7; var9++) {
/*  59 */           for (int var10 = 0; var10 < 16; var10++) {
/*  60 */             int var11 = var8 << 11 | var10 << 7 | var9 + (var6 << 4);
/*  61 */             int var12 = var1.g[var11];
/*  62 */             if (var12 != 0) {
/*  63 */               var7 = false;
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*  70 */       if (!var7) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  75 */         byte[] arrayOfByte = new byte[4096];
/*  76 */         NibbleArray var9 = new NibbleArray();
/*  77 */         NibbleArray var10 = new NibbleArray();
/*  78 */         NibbleArray var11 = new NibbleArray();
/*     */         
/*  80 */         for (int j = 0; j < 16; j++) {
/*  81 */           for (int var13 = 0; var13 < 16; var13++) {
/*  82 */             for (int var14 = 0; var14 < 16; var14++) {
/*  83 */               int var15 = j << 11 | var14 << 7 | var13 + (var6 << 4);
/*  84 */               int var16 = var1.g[var15];
/*     */               
/*  86 */               arrayOfByte[var13 << 8 | var14 << 4 | j] = (byte)(var16 & 0xFF);
/*  87 */               var9.a(j, var13, var14, var1.f.a(j, var13 + (var6 << 4), var14));
/*  88 */               var10.a(j, var13, var14, var1.e.a(j, var13 + (var6 << 4), var14));
/*  89 */               var11.a(j, var13, var14, var1.d.a(j, var13 + (var6 << 4), var14));
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/*  94 */         NBTTagCompound var12 = new NBTTagCompound();
/*     */         
/*  96 */         var12.setByte("Y", (byte)(var6 & 0xFF));
/*  97 */         var12.setByteArray("Blocks", arrayOfByte);
/*  98 */         var12.setByteArray("Data", var9.asBytes());
/*  99 */         var12.setByteArray("SkyLight", var10.asBytes());
/* 100 */         var12.setByteArray("BlockLight", var11.asBytes());
/*     */         
/* 102 */         var5.add(var12);
/*     */       } 
/* 104 */     }  var2.set("Sections", var5);
/* 105 */     var2.setIntArray("Biomes", (new BiomeStorage(var0.b(IRegistry.ay), new ChunkCoordIntPair(var1.k, var1.l), var3)).a());
/* 106 */     var2.set("Entities", var1.h);
/* 107 */     var2.set("TileEntities", var1.i);
/* 108 */     if (var1.j != null) {
/* 109 */       var2.set("TileTicks", var1.j);
/*     */     }
/* 111 */     var2.setBoolean("convertedFromAlphaFormat", true);
/*     */   }
/*     */ 
/*     */   
/*     */   public static class OldChunk
/*     */   {
/*     */     public long a;
/*     */     public boolean b;
/*     */     public byte[] c;
/*     */     public OldNibbleArray d;
/*     */     public OldNibbleArray e;
/*     */     public OldNibbleArray f;
/*     */     public byte[] g;
/*     */     public NBTTagList h;
/*     */     public NBTTagList i;
/*     */     public NBTTagList j;
/*     */     public final int k;
/*     */     public final int l;
/*     */     
/*     */     public OldChunk(int var0, int var1) {
/* 131 */       this.k = var0;
/* 132 */       this.l = var1;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\OldChunkLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */