/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.mojang.brigadier.context.StringRange;
/*    */ import com.mojang.brigadier.suggestion.Suggestion;
/*    */ import com.mojang.brigadier.suggestion.Suggestions;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutTabComplete
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private Suggestions b;
/*    */   
/*    */   public PacketPlayOutTabComplete() {}
/*    */   
/*    */   public PacketPlayOutTabComplete(int var0, Suggestions var1) {
/* 23 */     this.a = var0;
/* 24 */     this.b = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 29 */     this.a = var0.i();
/* 30 */     int var1 = var0.i();
/* 31 */     int var2 = var0.i();
/* 32 */     StringRange var3 = StringRange.between(var1, var1 + var2);
/*    */     
/* 34 */     int var4 = var0.i();
/* 35 */     List<Suggestion> var5 = Lists.newArrayListWithCapacity(var4);
/*    */     
/* 37 */     for (int var6 = 0; var6 < var4; var6++) {
/* 38 */       String var7 = var0.e(32767);
/* 39 */       IChatBaseComponent var8 = var0.readBoolean() ? var0.h() : null;
/* 40 */       var5.add(new Suggestion(var3, var7, var8));
/*    */     } 
/*    */     
/* 43 */     this.b = new Suggestions(var3, var5);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 48 */     var0.d(this.a);
/* 49 */     var0.d(this.b.getRange().getStart());
/* 50 */     var0.d(this.b.getRange().getLength());
/* 51 */     var0.d(this.b.getList().size());
/*    */     
/* 53 */     for (Suggestion var2 : this.b.getList()) {
/* 54 */       var0.a(var2.getText());
/* 55 */       var0.writeBoolean((var2.getTooltip() != null));
/* 56 */       if (var2.getTooltip() != null) {
/* 57 */         var0.a(ChatComponentUtils.a(var2.getTooltip()));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 64 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutTabComplete.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */