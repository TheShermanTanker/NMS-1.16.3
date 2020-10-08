/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Arrays;
/*    */ import java.util.Map;
/*    */ import java.util.stream.Collectors;
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
/*    */ public class WorldGenMineshaft
/*    */   extends StructureGenerator<WorldGenMineshaftConfiguration>
/*    */ {
/*    */   public WorldGenMineshaft(Codec<WorldGenMineshaftConfiguration> var0) {
/* 24 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(ChunkGenerator var0, WorldChunkManager var1, long var2, SeededRandom var4, int var5, int var6, BiomeBase var7, ChunkCoordIntPair var8, WorldGenMineshaftConfiguration var9) {
/* 29 */     var4.c(var2, var5, var6);
/*    */     
/* 31 */     double var10 = var9.b;
/* 32 */     return (var4.nextDouble() < var10);
/*    */   }
/*    */ 
/*    */   
/*    */   public StructureGenerator.a<WorldGenMineshaftConfiguration> a() {
/* 37 */     return a::new;
/*    */   }
/*    */   
/*    */   public enum Type implements INamable {
/* 41 */     NORMAL("normal"),
/* 42 */     MESA("mesa");
/*    */ 
/*    */     
/* 45 */     public static final Codec<Type> c = INamable.a(Type::values, Type::a); private static final Map<String, Type> d; static {
/* 46 */       d = (Map<String, Type>)Arrays.<Type>stream(values()).collect(Collectors.toMap(Type::b, var0 -> var0));
/*    */     }
/*    */     private final String e;
/*    */     Type(String var2) {
/* 50 */       this.e = var2;
/*    */     }
/*    */     
/*    */     public String b() {
/* 54 */       return this.e;
/*    */     }
/*    */     
/*    */     private static Type a(String var0) {
/* 58 */       return d.get(var0);
/*    */     }
/*    */     
/*    */     public static Type a(int var0) {
/* 62 */       if (var0 < 0 || var0 >= (values()).length) {
/* 63 */         return NORMAL;
/*    */       }
/* 65 */       return values()[var0];
/*    */     }
/*    */ 
/*    */     
/*    */     public String getName() {
/* 70 */       return this.e;
/*    */     }
/*    */   }
/*    */   
/*    */   public static class a extends StructureStart<WorldGenMineshaftConfiguration> {
/*    */     public a(StructureGenerator<WorldGenMineshaftConfiguration> var0, int var1, int var2, StructureBoundingBox var3, int var4, long var5) {
/* 76 */       super(var0, var1, var2, var3, var4, var5);
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(IRegistryCustom var0, ChunkGenerator var1, DefinedStructureManager var2, int var3, int var4, BiomeBase var5, WorldGenMineshaftConfiguration var6) {
/* 81 */       WorldGenMineshaftPieces.WorldGenMineshaftRoom var7 = new WorldGenMineshaftPieces.WorldGenMineshaftRoom(0, this.d, (var3 << 4) + 2, (var4 << 4) + 2, var6.c);
/* 82 */       this.b.add(var7);
/* 83 */       var7.a(var7, this.b, this.d);
/*    */       
/* 85 */       b();
/* 86 */       if (var6.c == WorldGenMineshaft.Type.MESA) {
/*    */         
/* 88 */         int var8 = -5;
/* 89 */         int var9 = var1.getSeaLevel() - this.c.e + this.c.e() / 2 - -5;
/*    */ 
/*    */         
/* 92 */         this.c.a(0, var9, 0);
/* 93 */         for (StructurePiece var11 : this.b) {
/* 94 */           var11.a(0, var9, 0);
/*    */         }
/*    */       } else {
/* 97 */         a(var1.getSeaLevel(), this.d, 10);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenMineshaft.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */