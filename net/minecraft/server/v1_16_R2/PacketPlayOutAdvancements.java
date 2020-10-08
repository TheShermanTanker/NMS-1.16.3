/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.common.collect.Sets;
/*    */ import java.io.IOException;
/*    */ import java.util.Collection;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutAdvancements
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private boolean a;
/*    */   private Map<MinecraftKey, Advancement.SerializedAdvancement> b;
/*    */   private Set<MinecraftKey> c;
/*    */   private Map<MinecraftKey, AdvancementProgress> d;
/*    */   
/*    */   public PacketPlayOutAdvancements() {}
/*    */   
/*    */   public PacketPlayOutAdvancements(boolean var0, Collection<Advancement> var1, Set<MinecraftKey> var2, Map<MinecraftKey, AdvancementProgress> var3) {
/* 26 */     this.a = var0;
/* 27 */     this.b = Maps.newHashMap();
/* 28 */     for (Advancement var5 : var1) {
/* 29 */       this.b.put(var5.getName(), var5.a());
/*    */     }
/* 31 */     this.c = var2;
/* 32 */     this.d = Maps.newHashMap(var3);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 37 */     var0.a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 42 */     this.a = var0.readBoolean();
/* 43 */     this.b = Maps.newHashMap();
/* 44 */     this.c = Sets.newLinkedHashSet();
/* 45 */     this.d = Maps.newHashMap();
/*    */     
/* 47 */     int var1 = var0.i(); int var2;
/* 48 */     for (var2 = 0; var2 < var1; var2++) {
/* 49 */       MinecraftKey var3 = var0.p();
/* 50 */       Advancement.SerializedAdvancement var4 = Advancement.SerializedAdvancement.b(var0);
/* 51 */       this.b.put(var3, var4);
/*    */     } 
/*    */     
/* 54 */     var1 = var0.i();
/* 55 */     for (var2 = 0; var2 < var1; var2++) {
/* 56 */       MinecraftKey var3 = var0.p();
/* 57 */       this.c.add(var3);
/*    */     } 
/*    */     
/* 60 */     var1 = var0.i();
/* 61 */     for (var2 = 0; var2 < var1; var2++) {
/* 62 */       MinecraftKey var3 = var0.p();
/* 63 */       this.d.put(var3, AdvancementProgress.b(var0));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 69 */     var0.writeBoolean(this.a);
/*    */     
/* 71 */     var0.d(this.b.size());
/* 72 */     for (Map.Entry<MinecraftKey, Advancement.SerializedAdvancement> var2 : this.b.entrySet()) {
/* 73 */       MinecraftKey var3 = var2.getKey();
/* 74 */       Advancement.SerializedAdvancement var4 = var2.getValue();
/* 75 */       var0.a(var3);
/* 76 */       var4.a(var0);
/*    */     } 
/*    */     
/* 79 */     var0.d(this.c.size());
/* 80 */     for (MinecraftKey var2 : this.c) {
/* 81 */       var0.a(var2);
/*    */     }
/*    */     
/* 84 */     var0.d(this.d.size());
/* 85 */     for (Map.Entry<MinecraftKey, AdvancementProgress> var2 : this.d.entrySet()) {
/* 86 */       var0.a(var2.getKey());
/* 87 */       ((AdvancementProgress)var2.getValue()).a(var0);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutAdvancements.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */