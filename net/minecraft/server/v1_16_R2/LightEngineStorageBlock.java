/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.destroystokyo.paper.util.map.QueuedChangesMapLong2Object;
/*    */ 
/*    */ public class LightEngineStorageBlock
/*    */   extends LightEngineStorage<LightEngineStorageBlock.a> {
/*    */   protected LightEngineStorageBlock(ILightAccess ilightaccess) {
/*  8 */     super(EnumSkyBlock.BLOCK, ilightaccess, new a(new QueuedChangesMapLong2Object(), false));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected int d(long i) {
/* 14 */     int baseX = (int)(i >> 38L);
/* 15 */     int baseY = (int)(i << 52L >> 52L);
/* 16 */     int baseZ = (int)(i << 26L >> 38L);
/* 17 */     long j = ((baseX >> 4) & 0x3FFFFFL) << 42L | (baseY >> 4) & 0xFFFFFL | ((baseZ >> 4) & 0x3FFFFFL) << 20L;
/* 18 */     NibbleArray nibblearray = this.e_visible.lookup.apply(j);
/* 19 */     return (nibblearray == null) ? 0 : nibblearray.a(baseX & 0xF, baseY & 0xF, baseZ & 0xF);
/*    */   }
/*    */   
/*    */   public static final class a
/*    */     extends LightEngineStorageArray<a>
/*    */   {
/*    */     public a(QueuedChangesMapLong2Object<NibbleArray> long2objectopenhashmap, boolean isVisible) {
/* 26 */       super(long2objectopenhashmap, isVisible);
/*    */     }
/*    */ 
/*    */     
/*    */     public a b() {
/* 31 */       return new a(this.data, true);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LightEngineStorageBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */