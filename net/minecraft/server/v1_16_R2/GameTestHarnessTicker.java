/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.Collection;
/*    */ 
/*    */ public class GameTestHarnessTicker
/*    */ {
/*  8 */   public static final GameTestHarnessTicker a = new GameTestHarnessTicker();
/*  9 */   private final Collection<GameTestHarnessInfo> b = Lists.newCopyOnWriteArrayList();
/*    */   
/*    */   public void a(GameTestHarnessInfo var0) {
/* 12 */     this.b.add(var0);
/*    */   }
/*    */   
/*    */   public void a() {
/* 16 */     this.b.clear();
/*    */   }
/*    */   
/*    */   public void b() {
/* 20 */     this.b.forEach(GameTestHarnessInfo::b);
/* 21 */     this.b.removeIf(GameTestHarnessInfo::k);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GameTestHarnessTicker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */