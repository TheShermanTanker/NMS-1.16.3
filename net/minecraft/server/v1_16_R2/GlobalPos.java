/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.Objects;
/*    */ 
/*    */ public final class GlobalPos {
/*    */   static {
/* 11 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)World.f.fieldOf("dimension").forGetter(GlobalPos::getDimensionManager), (App)BlockPosition.a.fieldOf("pos").forGetter(GlobalPos::getBlockPosition)).apply((Applicative)var0, GlobalPos::create));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<GlobalPos> a;
/*    */   private final ResourceKey<World> dimensionManager;
/*    */   private final BlockPosition blockPosition;
/*    */   
/*    */   private GlobalPos(ResourceKey<World> var0, BlockPosition var1) {
/* 20 */     this.dimensionManager = var0;
/* 21 */     this.blockPosition = var1;
/*    */   }
/*    */   
/*    */   public static GlobalPos create(ResourceKey<World> var0, BlockPosition var1) {
/* 25 */     return new GlobalPos(var0, var1);
/*    */   }
/*    */   
/*    */   public ResourceKey<World> getDimensionManager() {
/* 29 */     return this.dimensionManager;
/*    */   }
/*    */   
/*    */   public BlockPosition getBlockPosition() {
/* 33 */     return this.blockPosition;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object var0) {
/* 38 */     if (this == var0) {
/* 39 */       return true;
/*    */     }
/* 41 */     if (var0 == null || getClass() != var0.getClass()) {
/* 42 */       return false;
/*    */     }
/* 44 */     GlobalPos var1 = (GlobalPos)var0;
/* 45 */     return (Objects.equals(this.dimensionManager, var1.dimensionManager) && Objects.equals(this.blockPosition, var1.blockPosition));
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 50 */     return Objects.hash(new Object[] { this.dimensionManager, this.blockPosition });
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 55 */     return this.dimensionManager.toString() + " " + this.blockPosition;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GlobalPos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */