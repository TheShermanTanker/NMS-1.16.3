/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.annotation.Nullable;
/*    */ import net.md_5.bungee.api.chat.BaseComponent;
/*    */ import net.md_5.bungee.chat.ComponentSerializer;
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutTitle
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private EnumTitleAction a;
/*    */   private IChatBaseComponent b;
/*    */   private int c;
/*    */   
/*    */   public PacketPlayOutTitle(EnumTitleAction packetplayouttitle_enumtitleaction, IChatBaseComponent ichatbasecomponent) {
/* 17 */     this(packetplayouttitle_enumtitleaction, ichatbasecomponent, -1, -1, -1);
/*    */   } private int d; private int e; public BaseComponent[] components;
/*    */   public PacketPlayOutTitle() {}
/*    */   public PacketPlayOutTitle(int i, int j, int k) {
/* 21 */     this(EnumTitleAction.TIMES, (IChatBaseComponent)null, i, j, k);
/*    */   }
/*    */   
/*    */   public PacketPlayOutTitle(EnumTitleAction packetplayouttitle_enumtitleaction, @Nullable IChatBaseComponent ichatbasecomponent, int i, int j, int k) {
/* 25 */     this.a = packetplayouttitle_enumtitleaction;
/* 26 */     this.b = ichatbasecomponent;
/* 27 */     this.c = i;
/* 28 */     this.d = j;
/* 29 */     this.e = k;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer packetdataserializer) throws IOException {
/* 34 */     this.a = packetdataserializer.<EnumTitleAction>a(EnumTitleAction.class);
/* 35 */     if (this.a == EnumTitleAction.TITLE || this.a == EnumTitleAction.SUBTITLE || this.a == EnumTitleAction.ACTIONBAR) {
/* 36 */       this.b = packetdataserializer.h();
/*    */     }
/*    */     
/* 39 */     if (this.a == EnumTitleAction.TIMES) {
/* 40 */       this.c = packetdataserializer.readInt();
/* 41 */       this.d = packetdataserializer.readInt();
/* 42 */       this.e = packetdataserializer.readInt();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PacketPlayOutTitle(EnumTitleAction action, BaseComponent[] components, int fadeIn, int stay, int fadeOut) {
/* 50 */     this.a = action;
/* 51 */     this.components = components;
/* 52 */     this.c = fadeIn;
/* 53 */     this.d = stay;
/* 54 */     this.e = fadeOut;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer packetdataserializer) throws IOException {
/* 60 */     packetdataserializer.a(this.a);
/* 61 */     if (this.a == EnumTitleAction.TITLE || this.a == EnumTitleAction.SUBTITLE || this.a == EnumTitleAction.ACTIONBAR)
/*    */     {
/* 63 */       if (this.components != null) {
/* 64 */         packetdataserializer.a(ComponentSerializer.toString(this.components));
/*    */       } else {
/* 66 */         packetdataserializer.a(this.b);
/*    */       } 
/*    */     }
/*    */ 
/*    */     
/* 71 */     if (this.a == EnumTitleAction.TIMES) {
/* 72 */       packetdataserializer.writeInt(this.c);
/* 73 */       packetdataserializer.writeInt(this.d);
/* 74 */       packetdataserializer.writeInt(this.e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut packetlistenerplayout) {
/* 80 */     packetlistenerplayout.a(this);
/*    */   }
/*    */   
/*    */   public enum EnumTitleAction
/*    */   {
/* 85 */     TITLE, SUBTITLE, ACTIONBAR, TIMES, CLEAR, RESET;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutTitle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */