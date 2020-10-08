/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.Optional;
/*    */ 
/*    */ public class WorldGenEndGatewayConfiguration implements WorldGenFeatureConfiguration {
/*    */   static {
/* 10 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)BlockPosition.a.optionalFieldOf("exit").forGetter(()), (App)Codec.BOOL.fieldOf("exact").forGetter(())).apply((Applicative)var0, WorldGenEndGatewayConfiguration::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<WorldGenEndGatewayConfiguration> a;
/*    */   private final Optional<BlockPosition> b;
/*    */   private final boolean c;
/*    */   
/*    */   private WorldGenEndGatewayConfiguration(Optional<BlockPosition> var0, boolean var1) {
/* 19 */     this.b = var0;
/* 20 */     this.c = var1;
/*    */   }
/*    */   
/*    */   public static WorldGenEndGatewayConfiguration a(BlockPosition var0, boolean var1) {
/* 24 */     return new WorldGenEndGatewayConfiguration(Optional.of(var0), var1);
/*    */   }
/*    */   
/*    */   public static WorldGenEndGatewayConfiguration b() {
/* 28 */     return new WorldGenEndGatewayConfiguration(Optional.empty(), false);
/*    */   }
/*    */   
/*    */   public Optional<BlockPosition> c() {
/* 32 */     return this.b;
/*    */   }
/*    */   
/*    */   public boolean d() {
/* 36 */     return this.c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenEndGatewayConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */