/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public interface DataPalette<T> {
/*    */   default int getOrCreateIdFor(T object) {
/*  8 */     return a(object);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   default T getObject(int dataBits) {
/* 13 */     return a(dataBits);
/*    */   }
/*    */   
/*    */   int a(T paramT);
/*    */   
/*    */   boolean a(Predicate<T> paramPredicate);
/*    */   
/*    */   @Nullable
/*    */   T a(int paramInt);
/*    */   
/*    */   void b(PacketDataSerializer paramPacketDataSerializer);
/*    */   
/*    */   int a();
/*    */   
/*    */   void a(NBTTagList paramNBTTagList);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataPalette.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */