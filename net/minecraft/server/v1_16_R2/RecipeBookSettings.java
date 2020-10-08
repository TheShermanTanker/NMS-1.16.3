/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import java.util.EnumMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class RecipeBookSettings
/*     */ {
/*  14 */   private static final Map<RecipeBookType, Pair<String, String>> a = (Map<RecipeBookType, Pair<String, String>>)ImmutableMap.of(RecipeBookType.CRAFTING, 
/*  15 */       Pair.of("isGuiOpen", "isFilteringCraftable"), RecipeBookType.FURNACE, 
/*  16 */       Pair.of("isFurnaceGuiOpen", "isFurnaceFilteringCraftable"), RecipeBookType.BLAST_FURNACE, 
/*  17 */       Pair.of("isBlastingFurnaceGuiOpen", "isBlastingFurnaceFilteringCraftable"), RecipeBookType.SMOKER, 
/*  18 */       Pair.of("isSmokerGuiOpen", "isSmokerFilteringCraftable"));
/*     */   private final Map<RecipeBookType, a> b;
/*     */   
/*     */   static final class a {
/*     */     private boolean a;
/*     */     private boolean b;
/*     */     
/*     */     public a(boolean var0, boolean var1) {
/*  26 */       this.a = var0;
/*  27 */       this.b = var1;
/*     */     }
/*     */     
/*     */     public a a() {
/*  31 */       return new a(this.a, this.b);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object var0) {
/*  36 */       if (this == var0) {
/*  37 */         return true;
/*     */       }
/*     */       
/*  40 */       if (var0 instanceof a) {
/*  41 */         a var1 = (a)var0;
/*  42 */         return (this.a == var1.a && this.b == var1.b);
/*     */       } 
/*  44 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/*  49 */       int var0 = this.a ? 1 : 0;
/*  50 */       var0 = 31 * var0 + (this.b ? 1 : 0);
/*  51 */       return var0;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  56 */       return "[open=" + this.a + ", filtering=" + this.b + ']';
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private RecipeBookSettings(Map<RecipeBookType, a> var0) {
/*  63 */     this.b = var0;
/*     */   }
/*     */   
/*     */   public RecipeBookSettings() {
/*  67 */     this(SystemUtils.<Map<RecipeBookType, a>>a(Maps.newEnumMap(RecipeBookType.class), var0 -> {
/*     */             for (RecipeBookType var4 : RecipeBookType.values()) {
/*     */               var0.put(var4, new a(false, false));
/*     */             }
/*     */           }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(RecipeBookType var0, boolean var1) {
/*  79 */     a.a(this.b.get(var0), var1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(RecipeBookType var0, boolean var1) {
/*  87 */     a.b(this.b.get(var0), var1);
/*     */   }
/*     */   
/*     */   public static RecipeBookSettings a(PacketDataSerializer var0) {
/*  91 */     Map<RecipeBookType, a> var1 = Maps.newEnumMap(RecipeBookType.class);
/*  92 */     for (RecipeBookType var5 : RecipeBookType.values()) {
/*  93 */       boolean var6 = var0.readBoolean();
/*  94 */       boolean var7 = var0.readBoolean();
/*  95 */       var1.put(var5, new a(var6, var7));
/*     */     } 
/*  97 */     return new RecipeBookSettings(var1);
/*     */   }
/*     */   
/*     */   public void b(PacketDataSerializer var0) {
/* 101 */     for (RecipeBookType var4 : RecipeBookType.values()) {
/* 102 */       a var5 = this.b.get(var4);
/* 103 */       if (var5 == null) {
/* 104 */         var0.writeBoolean(false);
/* 105 */         var0.writeBoolean(false);
/*     */       } else {
/* 107 */         var0.writeBoolean(a.a(var5));
/* 108 */         var0.writeBoolean(a.b(var5));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static RecipeBookSettings a(NBTTagCompound var0) {
/* 114 */     Map<RecipeBookType, a> var1 = Maps.newEnumMap(RecipeBookType.class);
/* 115 */     a.forEach((var2, var3) -> {
/*     */           boolean var4 = var0.getBoolean((String)var3.getFirst());
/*     */           boolean var5 = var0.getBoolean((String)var3.getSecond());
/*     */           var1.put(var2, new a(var4, var5));
/*     */         });
/* 120 */     return new RecipeBookSettings(var1);
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound var0) {
/* 124 */     a.forEach((var1, var2) -> {
/*     */           a var3 = this.b.get(var1);
/*     */           var0.setBoolean((String)var2.getFirst(), a.a(var3));
/*     */           var0.setBoolean((String)var2.getSecond(), a.b(var3));
/*     */         });
/*     */   }
/*     */   
/*     */   public RecipeBookSettings a() {
/* 132 */     Map<RecipeBookType, a> var0 = Maps.newEnumMap(RecipeBookType.class);
/* 133 */     for (RecipeBookType var4 : RecipeBookType.values()) {
/* 134 */       a var5 = this.b.get(var4);
/* 135 */       var0.put(var4, var5.a());
/*     */     } 
/* 137 */     return new RecipeBookSettings(var0);
/*     */   }
/*     */   
/*     */   public void a(RecipeBookSettings var0) {
/* 141 */     this.b.clear();
/* 142 */     for (RecipeBookType var4 : RecipeBookType.values()) {
/* 143 */       a var5 = var0.b.get(var4);
/* 144 */       this.b.put(var4, var5.a());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object var0) {
/* 150 */     return (this == var0 || (var0 instanceof RecipeBookSettings && this.b.equals(((RecipeBookSettings)var0).b)));
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 155 */     return this.b.hashCode();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RecipeBookSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */