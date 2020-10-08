/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.function.Consumer;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GameTestHarnessBatch
/*    */ {
/*    */   private final String a;
/*    */   private final Collection<GameTestHarnessTestFunction> b;
/*    */   @Nullable
/*    */   private final Consumer<WorldServer> c;
/*    */   
/*    */   public GameTestHarnessBatch(String var0, Collection<GameTestHarnessTestFunction> var1, @Nullable Consumer<WorldServer> var2) {
/* 19 */     if (var1.isEmpty()) {
/* 20 */       throw new IllegalArgumentException("A GameTestBatch must include at least one TestFunction!");
/*    */     }
/*    */     
/* 23 */     this.a = var0;
/* 24 */     this.b = var1;
/* 25 */     this.c = var2;
/*    */   }
/*    */   
/*    */   public String a() {
/* 29 */     return this.a;
/*    */   }
/*    */   
/*    */   public Collection<GameTestHarnessTestFunction> b() {
/* 33 */     return this.b;
/*    */   }
/*    */   
/*    */   public void a(WorldServer var0) {
/* 37 */     if (this.c != null)
/* 38 */       this.c.accept(var0); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GameTestHarnessBatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */