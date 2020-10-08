/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import javax.swing.JList;
/*    */ 
/*    */ public class PlayerListBox
/*    */   extends JList<String>
/*    */ {
/*    */   private final MinecraftServer a;
/*    */   private int b;
/*    */   
/*    */   public PlayerListBox(MinecraftServer var0) {
/* 13 */     this.a = var0;
/* 14 */     var0.b(this::tick);
/*    */   }
/*    */   
/*    */   public void tick() {
/* 18 */     if (this.b++ % 20 == 0) {
/* 19 */       Vector<String> var0 = new Vector<>();
/* 20 */       for (int var1 = 0; var1 < this.a.getPlayerList().getPlayers().size(); var1++) {
/* 21 */         var0.add(((EntityPlayer)this.a.getPlayerList().getPlayers().get(var1)).getProfile().getName());
/*    */       }
/* 23 */       setListData(var0);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PlayerListBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */