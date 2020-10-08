/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.datafixers.util.Function4;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.Objects;
/*    */ 
/*    */ public class VillagePlaceRecord
/*    */ {
/*    */   public static Codec<VillagePlaceRecord> a(Runnable var0) {
/* 13 */     return RecordCodecBuilder.create(var1 -> var1.group((App)BlockPosition.a.fieldOf("pos").forGetter(()), (App)IRegistry.POINT_OF_INTEREST_TYPE.fieldOf("type").forGetter(()), (App)Codec.INT.fieldOf("free_tickets").orElse(Integer.valueOf(0)).forGetter(()), (App)RecordCodecBuilder.point(var0)).apply((Applicative)var1, VillagePlaceRecord::new));
/*    */   }
/*    */ 
/*    */   
/*    */   private final BlockPosition a;
/*    */   
/*    */   private final VillagePlaceType b;
/*    */   
/*    */   private int c;
/*    */   
/*    */   private final Runnable d;
/*    */ 
/*    */   
/*    */   private VillagePlaceRecord(BlockPosition var0, VillagePlaceType var1, int var2, Runnable var3) {
/* 27 */     this.a = var0.immutableCopy();
/* 28 */     this.b = var1;
/* 29 */     this.c = var2;
/* 30 */     this.d = var3;
/*    */   }
/*    */   
/*    */   public VillagePlaceRecord(BlockPosition var0, VillagePlaceType var1, Runnable var2) {
/* 34 */     this(var0, var1, var1.b(), var2);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean b() {
/* 44 */     if (this.c <= 0) {
/* 45 */       return false;
/*    */     }
/*    */     
/* 48 */     this.c--;
/* 49 */     this.d.run();
/* 50 */     return true;
/*    */   }
/*    */   
/*    */   protected boolean c() {
/* 54 */     if (this.c >= this.b.b()) {
/* 55 */       return false;
/*    */     }
/*    */     
/* 58 */     this.c++;
/* 59 */     this.d.run();
/* 60 */     return true;
/*    */   }
/*    */   
/*    */   public boolean d() {
/* 64 */     return (this.c > 0);
/*    */   }
/*    */   
/*    */   public boolean e() {
/* 68 */     return (this.c != this.b.b());
/*    */   }
/*    */   
/*    */   public BlockPosition f() {
/* 72 */     return this.a;
/*    */   }
/*    */   
/*    */   public VillagePlaceType g() {
/* 76 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object var0) {
/* 81 */     if (this == var0) {
/* 82 */       return true;
/*    */     }
/* 84 */     if (var0 == null || getClass() != var0.getClass()) {
/* 85 */       return false;
/*    */     }
/*    */     
/* 88 */     return Objects.equals(this.a, ((VillagePlaceRecord)var0).a);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 93 */     return this.a.hashCode();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\VillagePlaceRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */