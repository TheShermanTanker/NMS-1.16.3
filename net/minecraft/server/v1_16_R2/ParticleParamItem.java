/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.serialization.Codec;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ParticleParamItem
/*    */   implements ParticleParam
/*    */ {
/*    */   public static Codec<ParticleParamItem> a(Particle<ParticleParamItem> var0) {
/* 14 */     return ItemStack.a.xmap(var1 -> new ParticleParamItem(var0, var1), var0 -> var0.c);
/*    */   }
/*    */   
/* 17 */   public static final ParticleParam.a<ParticleParamItem> a = new ParticleParam.a<ParticleParamItem>()
/*    */     {
/*    */       public ParticleParamItem b(Particle<ParticleParamItem> var0, StringReader var1) throws CommandSyntaxException {
/* 20 */         var1.expect(' ');
/* 21 */         ArgumentParserItemStack var2 = (new ArgumentParserItemStack(var1, false)).h();
/* 22 */         ItemStack var3 = (new ArgumentPredicateItemStack(var2.b(), var2.c())).a(1, false);
/* 23 */         return new ParticleParamItem(var0, var3);
/*    */       }
/*    */ 
/*    */       
/*    */       public ParticleParamItem b(Particle<ParticleParamItem> var0, PacketDataSerializer var1) {
/* 28 */         return new ParticleParamItem(var0, var1.n());
/*    */       }
/*    */     };
/*    */   
/*    */   private final Particle<ParticleParamItem> b;
/*    */   private final ItemStack c;
/*    */   
/*    */   public ParticleParamItem(Particle<ParticleParamItem> var0, ItemStack var1) {
/* 36 */     this.b = var0;
/* 37 */     this.c = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) {
/* 42 */     var0.a(this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public String a() {
/* 47 */     return IRegistry.PARTICLE_TYPE.getKey(getParticle()) + " " + (new ArgumentPredicateItemStack(this.c.getItem(), this.c.getTag())).c();
/*    */   }
/*    */ 
/*    */   
/*    */   public Particle<ParticleParamItem> getParticle() {
/* 52 */     return this.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ParticleParamItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */