/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ import java.util.Spliterator;
/*    */ import java.util.Spliterators;
/*    */ import java.util.function.Consumer;
/*    */ import java.util.stream.Stream;
/*    */ import java.util.stream.StreamSupport;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StreamAccumulator<T>
/*    */ {
/* 16 */   private final List<T> a = Lists.newArrayList();
/*    */   private final Spliterator<T> b;
/*    */   
/*    */   public StreamAccumulator(Stream<T> var0) {
/* 20 */     this.b = var0.spliterator();
/*    */   }
/*    */ 
/*    */   
/*    */   public Stream<T> a() {
/* 25 */     return StreamSupport.stream(new Spliterators.AbstractSpliterator<T>(this, Long.MAX_VALUE, 0)
/*    */         {
/*    */           private int b;
/*    */           
/*    */           public boolean tryAdvance(Consumer<? super T> var0) {
/* 30 */             while (this.b >= StreamAccumulator.a(this.a).size()) {
/* 31 */               if (!StreamAccumulator.b(this.a).tryAdvance(StreamAccumulator.a(this.a)::add)) {
/* 32 */                 return false;
/*    */               }
/*    */             } 
/* 35 */             var0.accept(StreamAccumulator.a(this.a).get(this.b++));
/* 36 */             return true;
/*    */           }
/*    */         }false);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\StreamAccumulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */