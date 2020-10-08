/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.concurrent.Immutable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Immutable
/*    */ public class ChestLock
/*    */ {
/* 11 */   public static final ChestLock a = new ChestLock("");
/*    */   
/*    */   public final String key;
/*    */ 
/*    */   
/*    */   public ChestLock(String var0) {
/* 17 */     this.key = var0;
/*    */   }
/*    */   
/*    */   public boolean a(ItemStack var0) {
/* 21 */     return (this.key.isEmpty() || (!var0.isEmpty() && var0.hasName() && this.key.equals(var0.getName().getString())));
/*    */   }
/*    */   
/*    */   public void a(NBTTagCompound var0) {
/* 25 */     if (!this.key.isEmpty()) {
/* 26 */       var0.setString("Lock", this.key);
/*    */     }
/*    */   }
/*    */   
/*    */   public static ChestLock b(NBTTagCompound var0) {
/* 31 */     if (var0.hasKeyOfType("Lock", 8)) {
/* 32 */       return new ChestLock(var0.getString("Lock"));
/*    */     }
/* 34 */     return a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChestLock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */