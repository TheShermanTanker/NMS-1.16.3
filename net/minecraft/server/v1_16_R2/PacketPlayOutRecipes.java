/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.google.common.collect.Lists;
/*    */ import java.io.IOException;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class PacketPlayOutRecipes implements Packet<PacketListenerPlayOut> {
/*    */   private Action a;
/*    */   private List<MinecraftKey> b;
/*    */   private List<MinecraftKey> c;
/*    */   private RecipeBookSettings d;
/*    */   
/*    */   public enum Action {
/* 16 */     INIT, ADD, REMOVE;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PacketPlayOutRecipes() {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PacketPlayOutRecipes(Action var0, Collection<MinecraftKey> var1, Collection<MinecraftKey> var2, RecipeBookSettings var3) {
/* 29 */     this.a = var0;
/* 30 */     this.b = (List<MinecraftKey>)ImmutableList.copyOf(var1);
/* 31 */     this.c = (List<MinecraftKey>)ImmutableList.copyOf(var2);
/* 32 */     this.d = var3;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 37 */     var0.a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 42 */     this.a = var0.<Action>a(Action.class);
/*    */     
/* 44 */     this.d = RecipeBookSettings.a(var0);
/*    */     
/* 46 */     int var1 = var0.i();
/* 47 */     this.b = Lists.newArrayList(); int var2;
/* 48 */     for (var2 = 0; var2 < var1; var2++) {
/* 49 */       this.b.add(var0.p());
/*    */     }
/*    */     
/* 52 */     if (this.a == Action.INIT) {
/* 53 */       var1 = var0.i();
/* 54 */       this.c = Lists.newArrayList();
/* 55 */       for (var2 = 0; var2 < var1; var2++) {
/* 56 */         this.c.add(var0.p());
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 63 */     var0.a(this.a);
/*    */     
/* 65 */     this.d.b(var0);
/*    */     
/* 67 */     var0.d(this.b.size());
/* 68 */     for (MinecraftKey var2 : this.b) {
/* 69 */       var0.a(var2);
/*    */     }
/*    */     
/* 72 */     if (this.a == Action.INIT) {
/* 73 */       var0.d(this.c.size());
/* 74 */       for (MinecraftKey var2 : this.c)
/* 75 */         var0.a(var2); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutRecipes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */