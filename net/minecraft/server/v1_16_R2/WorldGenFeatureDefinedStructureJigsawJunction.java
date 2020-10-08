/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import com.mojang.serialization.DynamicOps;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureDefinedStructureJigsawJunction
/*    */ {
/*    */   private final int a;
/*    */   private final int b;
/*    */   
/*    */   public WorldGenFeatureDefinedStructureJigsawJunction(int var0, int var1, int var2, int var3, WorldGenFeatureDefinedStructurePoolTemplate.Matching var4) {
/* 15 */     this.a = var0;
/* 16 */     this.b = var1;
/* 17 */     this.c = var2;
/* 18 */     this.d = var3;
/* 19 */     this.e = var4;
/*    */   }
/*    */   private final int c; private final int d; private final WorldGenFeatureDefinedStructurePoolTemplate.Matching e;
/*    */   public int a() {
/* 23 */     return this.a;
/*    */   }
/*    */   
/*    */   public int b() {
/* 27 */     return this.b;
/*    */   }
/*    */   
/*    */   public int c() {
/* 31 */     return this.c;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public <T> Dynamic<T> a(DynamicOps<T> var0) {
/* 43 */     ImmutableMap.Builder<T, T> var1 = ImmutableMap.builder();
/* 44 */     var1
/* 45 */       .put(var0.createString("source_x"), var0.createInt(this.a))
/* 46 */       .put(var0.createString("source_ground_y"), var0.createInt(this.b))
/* 47 */       .put(var0.createString("source_z"), var0.createInt(this.c))
/* 48 */       .put(var0.createString("delta_y"), var0.createInt(this.d))
/* 49 */       .put(var0.createString("dest_proj"), var0.createString(this.e.b()));
/*    */     
/* 51 */     return new Dynamic(var0, var0.createMap((Map)var1.build()));
/*    */   }
/*    */   
/*    */   public static <T> WorldGenFeatureDefinedStructureJigsawJunction a(Dynamic<T> var0) {
/* 55 */     return new WorldGenFeatureDefinedStructureJigsawJunction(var0
/* 56 */         .get("source_x").asInt(0), var0
/* 57 */         .get("source_ground_y").asInt(0), var0
/* 58 */         .get("source_z").asInt(0), var0
/* 59 */         .get("delta_y").asInt(0), 
/* 60 */         WorldGenFeatureDefinedStructurePoolTemplate.Matching.a(var0.get("dest_proj").asString("")));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object var0) {
/* 66 */     if (this == var0) return true; 
/* 67 */     if (var0 == null || getClass() != var0.getClass()) return false;
/*    */     
/* 69 */     WorldGenFeatureDefinedStructureJigsawJunction var1 = (WorldGenFeatureDefinedStructureJigsawJunction)var0;
/*    */     
/* 71 */     if (this.a != var1.a) return false; 
/* 72 */     if (this.c != var1.c) return false; 
/* 73 */     if (this.d != var1.d) return false; 
/* 74 */     return (this.e == var1.e);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 79 */     int var0 = this.a;
/* 80 */     var0 = 31 * var0 + this.b;
/* 81 */     var0 = 31 * var0 + this.c;
/* 82 */     var0 = 31 * var0 + this.d;
/* 83 */     var0 = 31 * var0 + this.e.hashCode();
/* 84 */     return var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 89 */     return "JigsawJunction{sourceX=" + this.a + ", sourceGroundY=" + this.b + ", sourceZ=" + this.c + ", deltaY=" + this.d + ", destProjection=" + this.e + '}';
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureDefinedStructureJigsawJunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */