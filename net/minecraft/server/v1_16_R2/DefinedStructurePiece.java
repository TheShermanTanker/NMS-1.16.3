/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
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
/*     */ public abstract class DefinedStructurePiece
/*     */   extends StructurePiece
/*     */ {
/*  30 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   protected DefinedStructure a;
/*     */   protected DefinedStructureInfo b;
/*     */   protected BlockPosition c;
/*     */   
/*     */   public DefinedStructurePiece(WorldGenFeatureStructurePieceType var0, int var1) {
/*  37 */     super(var0, var1);
/*     */   }
/*     */   
/*     */   public DefinedStructurePiece(WorldGenFeatureStructurePieceType var0, NBTTagCompound var1) {
/*  41 */     super(var0, var1);
/*  42 */     this.c = new BlockPosition(var1.getInt("TPX"), var1.getInt("TPY"), var1.getInt("TPZ"));
/*     */   }
/*     */   
/*     */   protected void a(DefinedStructure var0, BlockPosition var1, DefinedStructureInfo var2) {
/*  46 */     this.a = var0;
/*  47 */     a(EnumDirection.NORTH);
/*  48 */     this.c = var1;
/*  49 */     this.b = var2;
/*  50 */     this.n = var0.b(var2, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(NBTTagCompound var0) {
/*  55 */     var0.setInt("TPX", this.c.getX());
/*  56 */     var0.setInt("TPY", this.c.getY());
/*  57 */     var0.setInt("TPZ", this.c.getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/*  62 */     this.b.a(var4);
/*     */     
/*  64 */     this.n = this.a.b(this.b, this.c);
/*  65 */     if (this.a.a(var0, this.c, var6, this.b, var3, 2)) {
/*  66 */       List<DefinedStructure.BlockInfo> var7 = this.a.a(this.c, this.b, Blocks.STRUCTURE_BLOCK);
/*  67 */       for (DefinedStructure.BlockInfo var9 : var7) {
/*  68 */         if (var9.c == null) {
/*     */           continue;
/*     */         }
/*     */         
/*  72 */         BlockPropertyStructureMode var10 = BlockPropertyStructureMode.valueOf(var9.c.getString("mode"));
/*  73 */         if (var10 != BlockPropertyStructureMode.DATA) {
/*     */           continue;
/*     */         }
/*     */         
/*  77 */         a(var9.c.getString("metadata"), var9.a, var0, var3, var4);
/*     */       } 
/*     */       
/*  80 */       List<DefinedStructure.BlockInfo> var8 = this.a.a(this.c, this.b, Blocks.JIGSAW);
/*  81 */       for (DefinedStructure.BlockInfo var10 : var8) {
/*  82 */         if (var10.c == null) {
/*     */           continue;
/*     */         }
/*     */         
/*  86 */         String var11 = var10.c.getString("final_state");
/*  87 */         ArgumentBlock var12 = new ArgumentBlock(new StringReader(var11), false);
/*  88 */         IBlockData var13 = Blocks.AIR.getBlockData();
/*     */         try {
/*  90 */           var12.a(true);
/*  91 */           IBlockData var14 = var12.getBlockData();
/*     */           
/*  93 */           if (var14 != null) {
/*  94 */             var13 = var14;
/*     */           } else {
/*  96 */             LOGGER.error("Error while parsing blockstate {} in jigsaw block @ {}", var11, var10.a);
/*     */           } 
/*  98 */         } catch (CommandSyntaxException var14) {
/*  99 */           LOGGER.error("Error while parsing blockstate {} in jigsaw block @ {}", var11, var10.a);
/*     */         } 
/*     */         
/* 102 */         var0.setTypeAndData(var10.a, var13, 3);
/*     */       } 
/*     */     } 
/*     */     
/* 106 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract void a(String paramString, BlockPosition paramBlockPosition, WorldAccess paramWorldAccess, Random paramRandom, StructureBoundingBox paramStructureBoundingBox);
/*     */   
/*     */   public void a(int var0, int var1, int var2) {
/* 113 */     super.a(var0, var1, var2);
/* 114 */     this.c = this.c.b(var0, var1, var2);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumBlockRotation ap_() {
/* 119 */     return this.b.d();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DefinedStructurePiece.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */