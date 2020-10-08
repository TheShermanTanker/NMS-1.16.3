/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.List;
/*    */ import java.util.function.BiFunction;
/*    */ 
/*    */ public class DataPackConfiguration {
/* 11 */   public static final DataPackConfiguration a = new DataPackConfiguration((List<String>)ImmutableList.of("vanilla"), (List<String>)ImmutableList.of());
/*    */   static {
/* 13 */     b = RecordCodecBuilder.create(var0 -> var0.group((App)Codec.STRING.listOf().fieldOf("Enabled").forGetter(()), (App)Codec.STRING.listOf().fieldOf("Disabled").forGetter(())).apply((Applicative)var0, DataPackConfiguration::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<DataPackConfiguration> b;
/*    */   private final List<String> c;
/*    */   private final List<String> d;
/*    */   
/*    */   public DataPackConfiguration(List<String> var0, List<String> var1) {
/* 22 */     this.c = (List<String>)ImmutableList.copyOf(var0);
/* 23 */     this.d = (List<String>)ImmutableList.copyOf(var1);
/*    */   }
/*    */   
/*    */   public List<String> a() {
/* 27 */     return this.c;
/*    */   }
/*    */   
/*    */   public List<String> b() {
/* 31 */     return this.d;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataPackConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */