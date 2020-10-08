/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.function.BiFunction;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public class DefinedStructureProcessorGravity
/*    */   extends DefinedStructureProcessor {
/*    */   static {
/* 13 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)HeightMap.Type.g.fieldOf("heightmap").orElse(HeightMap.Type.WORLD_SURFACE_WG).forGetter(()), (App)Codec.INT.fieldOf("offset").orElse(Integer.valueOf(0)).forGetter(())).apply((Applicative)var0, DefinedStructureProcessorGravity::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<DefinedStructureProcessorGravity> a;
/*    */   private final HeightMap.Type b;
/*    */   private final int c;
/*    */   
/*    */   public DefinedStructureProcessorGravity(HeightMap.Type var0, int var1) {
/* 22 */     this.b = var0;
/* 23 */     this.c = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public DefinedStructure.BlockInfo a(IWorldReader var0, BlockPosition var1, BlockPosition var2, DefinedStructure.BlockInfo var3, DefinedStructure.BlockInfo var4, DefinedStructureInfo var5) {
/*    */     HeightMap.Type var6;
/* 30 */     if (var0 instanceof WorldServer) {
/*    */       
/* 32 */       if (this.b == HeightMap.Type.WORLD_SURFACE_WG) {
/* 33 */         var6 = HeightMap.Type.WORLD_SURFACE;
/* 34 */       } else if (this.b == HeightMap.Type.OCEAN_FLOOR_WG) {
/* 35 */         var6 = HeightMap.Type.OCEAN_FLOOR;
/*    */       } else {
/* 37 */         var6 = this.b;
/*    */       } 
/*    */     } else {
/* 40 */       var6 = this.b;
/*    */     } 
/* 42 */     int var7 = var0.a(var6, var4.a.getX(), var4.a.getZ()) + this.c;
/* 43 */     int var8 = var3.a.getY();
/* 44 */     return new DefinedStructure.BlockInfo(new BlockPosition(var4.a.getX(), var7 + var8, var4.a.getZ()), var4.b, var4.c);
/*    */   }
/*    */ 
/*    */   
/*    */   protected DefinedStructureStructureProcessorType<?> a() {
/* 49 */     return DefinedStructureStructureProcessorType.c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DefinedStructureProcessorGravity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */