/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.serialization.Codec;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ParticleParamBlock
/*    */   implements ParticleParam
/*    */ {
/*    */   public static Codec<ParticleParamBlock> a(Particle<ParticleParamBlock> var0) {
/* 14 */     return IBlockData.b.xmap(var1 -> new ParticleParamBlock(var0, var1), var0 -> var0.c);
/*    */   }
/*    */   
/* 17 */   public static final ParticleParam.a<ParticleParamBlock> a = new ParticleParam.a<ParticleParamBlock>()
/*    */     {
/*    */       public ParticleParamBlock b(Particle<ParticleParamBlock> var0, StringReader var1) throws CommandSyntaxException {
/* 20 */         var1.expect(' ');
/* 21 */         return new ParticleParamBlock(var0, (new ArgumentBlock(var1, false)).a(false).getBlockData());
/*    */       }
/*    */ 
/*    */       
/*    */       public ParticleParamBlock b(Particle<ParticleParamBlock> var0, PacketDataSerializer var1) {
/* 26 */         return new ParticleParamBlock(var0, Block.REGISTRY_ID.fromId(var1.i()));
/*    */       }
/*    */     };
/*    */   
/*    */   private final Particle<ParticleParamBlock> b;
/*    */   private final IBlockData c;
/*    */   
/*    */   public ParticleParamBlock(Particle<ParticleParamBlock> var0, IBlockData var1) {
/* 34 */     this.b = var0;
/* 35 */     this.c = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) {
/* 40 */     var0.d(Block.REGISTRY_ID.getId(this.c));
/*    */   }
/*    */ 
/*    */   
/*    */   public String a() {
/* 45 */     return IRegistry.PARTICLE_TYPE.getKey(getParticle()) + " " + ArgumentBlock.a(this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public Particle<ParticleParamBlock> getParticle() {
/* 50 */     return this.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ParticleParamBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */