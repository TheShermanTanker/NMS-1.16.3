/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Arrays;
/*    */ import java.util.Map;
/*    */ import java.util.stream.Collectors;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public class WorldGenStage
/*    */ {
/*    */   public enum Decoration
/*    */   {
/* 13 */     RAW_GENERATION,
/* 14 */     LAKES,
/* 15 */     LOCAL_MODIFICATIONS,
/* 16 */     UNDERGROUND_STRUCTURES,
/* 17 */     SURFACE_STRUCTURES,
/* 18 */     STRONGHOLDS,
/* 19 */     UNDERGROUND_ORES,
/* 20 */     UNDERGROUND_DECORATION,
/* 21 */     VEGETAL_DECORATION,
/* 22 */     TOP_LAYER_MODIFICATION;
/*    */   }
/*    */   
/*    */   public enum Features
/*    */     implements INamable {
/* 27 */     AIR("air"),
/* 28 */     LIQUID("liquid");
/*    */ 
/*    */     
/* 31 */     public static final Codec<Features> c = INamable.a(Features::values, Features::a); private static final Map<String, Features> d;
/*    */     static {
/* 33 */       d = (Map<String, Features>)Arrays.<Features>stream(values()).collect(Collectors.toMap(Features::b, var0 -> var0));
/*    */     }
/*    */     private final String e;
/*    */     Features(String var2) {
/* 37 */       this.e = var2;
/*    */     }
/*    */     
/*    */     public String b() {
/* 41 */       return this.e;
/*    */     }
/*    */     
/*    */     @Nullable
/*    */     public static Features a(String var0) {
/* 46 */       return d.get(var0);
/*    */     }
/*    */ 
/*    */     
/*    */     public String getName() {
/* 51 */       return this.e;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenStage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */