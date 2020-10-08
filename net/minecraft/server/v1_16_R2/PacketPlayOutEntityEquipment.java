/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutEntityEquipment
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private final List<Pair<EnumItemSlot, ItemStack>> b;
/*    */   
/*    */   public PacketPlayOutEntityEquipment() {
/* 19 */     this.b = Lists.newArrayList();
/*    */   }
/*    */   
/*    */   public PacketPlayOutEntityEquipment(int var0, List<Pair<EnumItemSlot, ItemStack>> var1) {
/* 23 */     this.a = var0;
/* 24 */     this.b = var1;
/*    */   }
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/*    */     int var2;
/* 29 */     this.a = var0.i();
/* 30 */     EnumItemSlot[] var1 = EnumItemSlot.values();
/*    */     
/*    */     do {
/* 33 */       var2 = var0.readByte();
/* 34 */       EnumItemSlot var3 = var1[var2 & 0x7F];
/* 35 */       ItemStack var4 = var0.n();
/* 36 */       this.b.add(Pair.of(var3, var4));
/* 37 */     } while ((var2 & 0xFFFFFF80) != 0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 45 */     var0.d(this.a);
/*    */     
/* 47 */     int var1 = this.b.size();
/* 48 */     for (int var2 = 0; var2 < var1; var2++) {
/* 49 */       Pair<EnumItemSlot, ItemStack> var3 = this.b.get(var2);
/* 50 */       EnumItemSlot var4 = (EnumItemSlot)var3.getFirst();
/* 51 */       boolean var5 = (var2 != var1 - 1);
/* 52 */       int var6 = var4.ordinal();
/* 53 */       var0.writeByte(var5 ? (var6 | 0xFFFFFF80) : var6);
/* 54 */       var0.a((ItemStack)var3.getSecond());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 60 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutEntityEquipment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */