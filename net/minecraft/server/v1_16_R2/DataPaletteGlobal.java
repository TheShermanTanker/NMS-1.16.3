/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataPaletteGlobal<T>
/*    */   implements DataPalette<T>
/*    */ {
/*    */   private final RegistryBlockID<T> a;
/*    */   private final T b;
/*    */   
/*    */   public DataPaletteGlobal(RegistryBlockID<T> var0, T var1) {
/* 14 */     this.a = var0;
/* 15 */     this.b = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(T var0) {
/* 20 */     int var1 = this.a.getId(var0);
/* 21 */     return (var1 == -1) ? 0 : var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(Predicate<T> var0) {
/* 26 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public T a(int var0) {
/* 31 */     T var1 = this.a.fromId(var0);
/* 32 */     return (var1 == null) ? this.b : var1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int a() {
/* 45 */     return PacketDataSerializer.a(0);
/*    */   }
/*    */   
/*    */   public void a(NBTTagList var0) {}
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataPaletteGlobal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */