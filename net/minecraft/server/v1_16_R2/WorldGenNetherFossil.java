/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenNetherFossil
/*    */ {
/* 24 */   private static final MinecraftKey[] a = new MinecraftKey[] { new MinecraftKey("nether_fossils/fossil_1"), new MinecraftKey("nether_fossils/fossil_2"), new MinecraftKey("nether_fossils/fossil_3"), new MinecraftKey("nether_fossils/fossil_4"), new MinecraftKey("nether_fossils/fossil_5"), new MinecraftKey("nether_fossils/fossil_6"), new MinecraftKey("nether_fossils/fossil_7"), new MinecraftKey("nether_fossils/fossil_8"), new MinecraftKey("nether_fossils/fossil_9"), new MinecraftKey("nether_fossils/fossil_10"), new MinecraftKey("nether_fossils/fossil_11"), new MinecraftKey("nether_fossils/fossil_12"), new MinecraftKey("nether_fossils/fossil_13"), new MinecraftKey("nether_fossils/fossil_14") };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void a(DefinedStructureManager var0, List<StructurePiece> var1, Random var2, BlockPosition var3) {
/* 42 */     EnumBlockRotation var4 = EnumBlockRotation.a(var2);
/* 43 */     var1.add(new a(var0, SystemUtils.<MinecraftKey>a(a, var2), var3, var4));
/*    */   }
/*    */   
/*    */   public static class a extends DefinedStructurePiece {
/*    */     private final MinecraftKey d;
/*    */     private final EnumBlockRotation e;
/*    */     
/*    */     public a(DefinedStructureManager var0, MinecraftKey var1, BlockPosition var2, EnumBlockRotation var3) {
/* 51 */       super(WorldGenFeatureStructurePieceType.ac, 0);
/* 52 */       this.d = var1;
/* 53 */       this.c = var2;
/* 54 */       this.e = var3;
/* 55 */       a(var0);
/*    */     }
/*    */     
/*    */     public a(DefinedStructureManager var0, NBTTagCompound var1) {
/* 59 */       super(WorldGenFeatureStructurePieceType.ac, var1);
/* 60 */       this.d = new MinecraftKey(var1.getString("Template"));
/* 61 */       this.e = EnumBlockRotation.valueOf(var1.getString("Rot"));
/* 62 */       a(var0);
/*    */     }
/*    */     
/*    */     private void a(DefinedStructureManager var0) {
/* 66 */       DefinedStructure var1 = var0.a(this.d);
/* 67 */       DefinedStructureInfo var2 = (new DefinedStructureInfo()).a(this.e).a(EnumBlockMirror.NONE).a(DefinedStructureProcessorBlockIgnore.d);
/* 68 */       a(var1, this.c, var2);
/*    */     }
/*    */ 
/*    */     
/*    */     protected void a(NBTTagCompound var0) {
/* 73 */       super.a(var0);
/* 74 */       var0.setString("Template", this.d.toString());
/* 75 */       var0.setString("Rot", this.e.name());
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     protected void a(String var0, BlockPosition var1, WorldAccess var2, Random var3, StructureBoundingBox var4) {}
/*    */ 
/*    */     
/*    */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/* 84 */       var4.c(this.a.b(this.b, this.c));
/* 85 */       return super.a(var0, var1, var2, var3, var4, var5, var6);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenNetherFossil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */