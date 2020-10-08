/*    */ package co.aikar.timings;
/*    */ 
/*    */ import co.aikar.util.JSONUtil;
/*    */ import com.google.common.base.Function;
/*    */ import java.util.List;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class TimingHistoryEntry
/*    */ {
/*    */   final TimingData data;
/*    */   private final TimingData[] children;
/*    */   
/*    */   TimingHistoryEntry(@NotNull TimingHandler handler) {
/* 38 */     this.data = handler.record.clone();
/* 39 */     this.children = handler.cloneChildren();
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   List<Object> export() {
/* 44 */     List<Object> result = this.data.export();
/* 45 */     if (this.children.length > 0) {
/* 46 */       result.add(
/* 47 */           JSONUtil.toArrayMapper((Object[])this.children, new Function<TimingData, Object>()
/*    */             {
/*    */               @NotNull
/*    */               public Object apply(TimingData child) {
/* 51 */                 return child.export();
/*    */               }
/*    */             }));
/*    */     }
/*    */     
/* 56 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\co\aikar\timings\TimingHistoryEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */