/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class PacketPlayOutCombatEvent
/*    */   implements Packet<PacketListenerPlayOut> {
/*    */   public EnumCombatEventType a;
/*    */   public int b;
/*    */   public int c;
/*    */   public int d;
/*    */   public IChatBaseComponent e;
/*    */   
/*    */   public enum EnumCombatEventType {
/* 14 */     ENTER_COMBAT,
/* 15 */     END_COMBAT,
/* 16 */     ENTITY_DIED;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PacketPlayOutCombatEvent() {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PacketPlayOutCombatEvent(CombatTracker var0, EnumCombatEventType var1) {
/* 30 */     this(var0, var1, ChatComponentText.d);
/*    */   }
/*    */   
/*    */   public PacketPlayOutCombatEvent(CombatTracker var0, EnumCombatEventType var1, IChatBaseComponent var2) {
/* 34 */     this.a = var1;
/*    */     
/* 36 */     EntityLiving var3 = var0.c();
/*    */     
/* 38 */     switch (null.a[var1.ordinal()]) {
/*    */       case 1:
/* 40 */         this.d = var0.f();
/* 41 */         this.c = (var3 == null) ? -1 : var3.getId();
/*    */         break;
/*    */       case 2:
/* 44 */         this.b = var0.h().getId();
/* 45 */         this.c = (var3 == null) ? -1 : var3.getId();
/* 46 */         this.e = var2;
/*    */         break;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 53 */     this.a = var0.<EnumCombatEventType>a(EnumCombatEventType.class);
/*    */     
/* 55 */     if (this.a == EnumCombatEventType.END_COMBAT) {
/* 56 */       this.d = var0.i();
/* 57 */       this.c = var0.readInt();
/* 58 */     } else if (this.a == EnumCombatEventType.ENTITY_DIED) {
/* 59 */       this.b = var0.i();
/* 60 */       this.c = var0.readInt();
/* 61 */       this.e = var0.h();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 67 */     var0.a(this.a);
/*    */     
/* 69 */     if (this.a == EnumCombatEventType.END_COMBAT) {
/* 70 */       var0.d(this.d);
/* 71 */       var0.writeInt(this.c);
/* 72 */     } else if (this.a == EnumCombatEventType.ENTITY_DIED) {
/* 73 */       var0.d(this.b);
/* 74 */       var0.writeInt(this.c);
/* 75 */       var0.a(this.e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 81 */     var0.a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 86 */     return (this.a == EnumCombatEventType.ENTITY_DIED);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutCombatEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */