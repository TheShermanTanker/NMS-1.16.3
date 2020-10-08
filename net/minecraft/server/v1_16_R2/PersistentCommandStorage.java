/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PersistentCommandStorage
/*    */ {
/*    */   static class a
/*    */     extends PersistentBase
/*    */   {
/* 14 */     private final Map<String, NBTTagCompound> a = Maps.newHashMap();
/*    */     
/*    */     public a(String var0) {
/* 17 */       super(var0);
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(NBTTagCompound var0) {
/* 22 */       NBTTagCompound var1 = var0.getCompound("contents");
/* 23 */       for (String var3 : var1.getKeys()) {
/* 24 */         this.a.put(var3, var1.getCompound(var3));
/*    */       }
/*    */     }
/*    */ 
/*    */     
/*    */     public NBTTagCompound b(NBTTagCompound var0) {
/* 30 */       NBTTagCompound var1 = new NBTTagCompound();
/* 31 */       this.a.forEach((var1, var2) -> var0.set(var1, var2.clone()));
/* 32 */       var0.set("contents", var1);
/* 33 */       return var0;
/*    */     }
/*    */     
/*    */     public NBTTagCompound a(String var0) {
/* 37 */       NBTTagCompound var1 = this.a.get(var0);
/* 38 */       return (var1 != null) ? var1 : new NBTTagCompound();
/*    */     }
/*    */     
/*    */     public void a(String var0, NBTTagCompound var1) {
/* 42 */       if (var1.isEmpty()) {
/* 43 */         this.a.remove(var0);
/*    */       } else {
/* 45 */         this.a.put(var0, var1);
/*    */       } 
/* 47 */       b();
/*    */     }
/*    */     
/*    */     public Stream<MinecraftKey> b(String var0) {
/* 51 */       return this.a.keySet().stream().map(var1 -> new MinecraftKey(var0, var1));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/* 56 */   private final Map<String, a> a = Maps.newHashMap();
/*    */   private final WorldPersistentData b;
/*    */   
/*    */   public PersistentCommandStorage(WorldPersistentData var0) {
/* 60 */     this.b = var0;
/*    */   }
/*    */   
/*    */   private a a(String var0, String var1) {
/* 64 */     a var2 = new a(var1);
/* 65 */     this.a.put(var0, var2);
/* 66 */     return var2;
/*    */   }
/*    */   
/*    */   public NBTTagCompound a(MinecraftKey var0) {
/* 70 */     String var1 = var0.getNamespace();
/* 71 */     String var2 = a(var1);
/* 72 */     a var3 = this.b.<a>b(() -> a(var0, var1), var2);
/* 73 */     return (var3 != null) ? var3.a(var0.getKey()) : new NBTTagCompound();
/*    */   }
/*    */   
/*    */   public void a(MinecraftKey var0, NBTTagCompound var1) {
/* 77 */     String var2 = var0.getNamespace();
/* 78 */     String var3 = a(var2);
/* 79 */     ((a)this.b.<a>a(() -> a(var0, var1), var3)).a(var0.getKey(), var1);
/*    */   }
/*    */   
/*    */   public Stream<MinecraftKey> a() {
/* 83 */     return this.a.entrySet().stream().flatMap(var0 -> ((a)var0.getValue()).b((String)var0.getKey()));
/*    */   }
/*    */   
/*    */   private static String a(String var0) {
/* 87 */     return "command_storage_" + var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PersistentCommandStorage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */