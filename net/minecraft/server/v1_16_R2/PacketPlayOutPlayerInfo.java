/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.base.MoreObjects;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.properties.Property;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PacketPlayOutPlayerInfo
/*     */   implements Packet<PacketListenerPlayOut>
/*     */ {
/*     */   private EnumPlayerInfoAction a;
/*  20 */   private final List<PlayerInfoData> b = Lists.newArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PacketPlayOutPlayerInfo(EnumPlayerInfoAction var0, EntityPlayer... var1) {
/*  26 */     this.a = var0;
/*     */     
/*  28 */     for (EntityPlayer var5 : var1) {
/*  29 */       this.b.add(new PlayerInfoData(this, var5.getProfile(), var5.ping, var5.playerInteractManager.getGameMode(), var5.getPlayerListName()));
/*     */     }
/*     */   }
/*     */   
/*     */   public PacketPlayOutPlayerInfo(EnumPlayerInfoAction var0, Iterable<EntityPlayer> var1) {
/*  34 */     this.a = var0;
/*     */     
/*  36 */     for (EntityPlayer var3 : var1) {
/*  37 */       this.b.add(new PlayerInfoData(this, var3.getProfile(), var3.ping, var3.playerInteractManager.getGameMode(), var3.getPlayerListName()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketDataSerializer var0) throws IOException {
/*  43 */     this.a = var0.<EnumPlayerInfoAction>a(EnumPlayerInfoAction.class);
/*     */     
/*  45 */     int var1 = var0.i();
/*  46 */     for (int var2 = 0; var2 < var1; var2++) {
/*  47 */       int var7, var8; GameProfile var3 = null;
/*  48 */       int var4 = 0;
/*  49 */       EnumGamemode var5 = null;
/*  50 */       IChatBaseComponent var6 = null;
/*     */       
/*  52 */       switch (null.a[this.a.ordinal()]) {
/*     */         case 1:
/*  54 */           var3 = new GameProfile(var0.k(), var0.e(16));
/*  55 */           var7 = var0.i();
/*  56 */           for (var8 = 0; var8 < var7; var8++) {
/*  57 */             String var9 = var0.e(32767);
/*  58 */             String var10 = var0.e(32767);
/*     */             
/*  60 */             if (var0.readBoolean()) {
/*  61 */               var3.getProperties().put(var9, new Property(var9, var10, var0.e(32767)));
/*     */             } else {
/*  63 */               var3.getProperties().put(var9, new Property(var9, var10));
/*     */             } 
/*     */           } 
/*  66 */           var5 = EnumGamemode.getById(var0.i());
/*  67 */           var4 = var0.i();
/*  68 */           if (var0.readBoolean()) {
/*  69 */             var6 = var0.h();
/*     */           }
/*     */           break;
/*     */         case 2:
/*  73 */           var3 = new GameProfile(var0.k(), null);
/*  74 */           var5 = EnumGamemode.getById(var0.i());
/*     */           break;
/*     */         case 3:
/*  77 */           var3 = new GameProfile(var0.k(), null);
/*  78 */           var4 = var0.i();
/*     */           break;
/*     */         case 4:
/*  81 */           var3 = new GameProfile(var0.k(), null);
/*  82 */           if (var0.readBoolean()) {
/*  83 */             var6 = var0.h();
/*     */           }
/*     */           break;
/*     */         case 5:
/*  87 */           var3 = new GameProfile(var0.k(), null);
/*     */           break;
/*     */       } 
/*     */       
/*  91 */       this.b.add(new PlayerInfoData(this, var3, var4, var5, var6));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(PacketDataSerializer var0) throws IOException {
/*  97 */     var0.a(this.a);
/*     */     
/*  99 */     var0.d(this.b.size());
/* 100 */     for (PlayerInfoData var2 : this.b) {
/* 101 */       switch (null.a[this.a.ordinal()]) {
/*     */         case 1:
/* 103 */           var0.a(var2.a().getId());
/* 104 */           var0.a(var2.a().getName());
/* 105 */           var0.d(var2.a().getProperties().size());
/* 106 */           for (Property var4 : var2.a().getProperties().values()) {
/* 107 */             var0.a(var4.getName());
/* 108 */             var0.a(var4.getValue());
/* 109 */             if (var4.hasSignature()) {
/* 110 */               var0.writeBoolean(true);
/* 111 */               var0.a(var4.getSignature()); continue;
/*     */             } 
/* 113 */             var0.writeBoolean(false);
/*     */           } 
/*     */           
/* 116 */           var0.d(var2.c().getId());
/* 117 */           var0.d(var2.b());
/*     */           
/* 119 */           if (var2.d() == null) {
/* 120 */             var0.writeBoolean(false); continue;
/*     */           } 
/* 122 */           var0.writeBoolean(true);
/* 123 */           var0.a(var2.d());
/*     */ 
/*     */         
/*     */         case 2:
/* 127 */           var0.a(var2.a().getId());
/* 128 */           var0.d(var2.c().getId());
/*     */         
/*     */         case 3:
/* 131 */           var0.a(var2.a().getId());
/* 132 */           var0.d(var2.b());
/*     */         
/*     */         case 4:
/* 135 */           var0.a(var2.a().getId());
/* 136 */           if (var2.d() == null) {
/* 137 */             var0.writeBoolean(false); continue;
/*     */           } 
/* 139 */           var0.writeBoolean(true);
/* 140 */           var0.a(var2.d());
/*     */ 
/*     */         
/*     */         case 5:
/* 144 */           var0.a(var2.a().getId());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(PacketListenerPlayOut var0) {
/* 152 */     var0.a(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum EnumPlayerInfoAction
/*     */   {
/* 164 */     ADD_PLAYER,
/* 165 */     UPDATE_GAME_MODE,
/* 166 */     UPDATE_LATENCY,
/* 167 */     UPDATE_DISPLAY_NAME,
/* 168 */     REMOVE_PLAYER;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 174 */     return MoreObjects.toStringHelper(this)
/* 175 */       .add("action", this.a)
/* 176 */       .add("entries", this.b)
/* 177 */       .toString();
/*     */   }
/*     */   
/*     */   public PacketPlayOutPlayerInfo() {}
/*     */   
/*     */   public class PlayerInfoData {
/*     */     private final int b;
/*     */     private final EnumGamemode c;
/*     */     
/*     */     public PlayerInfoData(PacketPlayOutPlayerInfo var0, GameProfile var1, int var2, @Nullable EnumGamemode var3, @Nullable IChatBaseComponent var4) {
/* 187 */       this.d = var1;
/* 188 */       this.b = var2;
/* 189 */       this.c = var3;
/* 190 */       this.e = var4;
/*     */     }
/*     */     private final GameProfile d; private final IChatBaseComponent e;
/*     */     public GameProfile a() {
/* 194 */       return this.d;
/*     */     }
/*     */     
/*     */     public int b() {
/* 198 */       return this.b;
/*     */     }
/*     */     
/*     */     public EnumGamemode c() {
/* 202 */       return this.c;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public IChatBaseComponent d() {
/* 207 */       return this.e;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 212 */       return MoreObjects.toStringHelper(this)
/* 213 */         .add("latency", this.b)
/* 214 */         .add("gameMode", this.c)
/* 215 */         .add("profile", this.d)
/* 216 */         .add("displayName", (this.e == null) ? null : IChatBaseComponent.ChatSerializer.a(this.e))
/* 217 */         .toString();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutPlayerInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */