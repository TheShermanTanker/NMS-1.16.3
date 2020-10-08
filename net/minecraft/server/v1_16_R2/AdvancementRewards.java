/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonNull;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AdvancementRewards
/*     */ {
/*  28 */   public static final AdvancementRewards a = new AdvancementRewards(0, new MinecraftKey[0], new MinecraftKey[0], CustomFunction.a.a);
/*     */   
/*     */   private final int b;
/*     */   private final MinecraftKey[] c;
/*     */   private final MinecraftKey[] d;
/*     */   private final CustomFunction.a e;
/*     */   
/*     */   public AdvancementRewards(int var0, MinecraftKey[] var1, MinecraftKey[] var2, CustomFunction.a var3) {
/*  36 */     this.b = var0;
/*  37 */     this.c = var1;
/*  38 */     this.d = var2;
/*  39 */     this.e = var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(EntityPlayer var0) {
/*  47 */     var0.giveExp(this.b);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  52 */     LootTableInfo var1 = (new LootTableInfo.Builder(var0.getWorldServer())).<Entity>set(LootContextParameters.THIS_ENTITY, var0).<Vec3D>set(LootContextParameters.ORIGIN, var0.getPositionVector()).a(var0.getRandom()).build(LootContextParameterSets.ADVANCEMENT_REWARD);
/*     */     
/*  54 */     boolean var2 = false;
/*  55 */     for (MinecraftKey var6 : this.c) {
/*  56 */       for (ItemStack var8 : var0.server.getLootTableRegistry().getLootTable(var6).populateLoot(var1)) {
/*  57 */         if (var0.g(var8)) {
/*  58 */           var0.world.playSound(null, var0.locX(), var0.locY(), var0.locZ(), SoundEffects.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, ((var0.getRandom().nextFloat() - var0.getRandom().nextFloat()) * 0.7F + 1.0F) * 2.0F);
/*  59 */           var2 = true; continue;
/*     */         } 
/*  61 */         EntityItem var9 = var0.drop(var8, false);
/*  62 */         if (var9 != null) {
/*  63 */           var9.n();
/*  64 */           var9.setOwner(var0.getUniqueID());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  69 */     if (var2) {
/*  70 */       var0.defaultContainer.c();
/*     */     }
/*  72 */     if (this.d.length > 0) {
/*  73 */       var0.a(this.d);
/*     */     }
/*  75 */     MinecraftServer var3 = var0.server;
/*  76 */     this.e.a(var3.getFunctionData())
/*  77 */       .ifPresent(var2 -> var0.getFunctionData().a(var2, var1.getCommandListener().a().a(2)));
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  82 */     return "AdvancementRewards{experience=" + this.b + ", loot=" + 
/*     */       
/*  84 */       Arrays.toString((Object[])this.c) + ", recipes=" + 
/*  85 */       Arrays.toString((Object[])this.d) + ", function=" + this.e + '}';
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonElement b() {
/*  91 */     if (this == a) {
/*  92 */       return (JsonElement)JsonNull.INSTANCE;
/*     */     }
/*     */     
/*  95 */     JsonObject var0 = new JsonObject();
/*     */     
/*  97 */     if (this.b != 0) {
/*  98 */       var0.addProperty("experience", Integer.valueOf(this.b));
/*     */     }
/*     */     
/* 101 */     if (this.c.length > 0) {
/* 102 */       JsonArray var1 = new JsonArray();
/* 103 */       for (MinecraftKey var5 : this.c) {
/* 104 */         var1.add(var5.toString());
/*     */       }
/* 106 */       var0.add("loot", (JsonElement)var1);
/*     */     } 
/*     */     
/* 109 */     if (this.d.length > 0) {
/* 110 */       JsonArray var1 = new JsonArray();
/* 111 */       for (MinecraftKey var5 : this.d) {
/* 112 */         var1.add(var5.toString());
/*     */       }
/* 114 */       var0.add("recipes", (JsonElement)var1);
/*     */     } 
/*     */     
/* 117 */     if (this.e.a() != null) {
/* 118 */       var0.addProperty("function", this.e.a().toString());
/*     */     }
/*     */     
/* 121 */     return (JsonElement)var0;
/*     */   }
/*     */   
/*     */   public static AdvancementRewards a(JsonObject var0) throws JsonParseException {
/*     */     CustomFunction.a var6;
/* 126 */     int var1 = ChatDeserializer.a(var0, "experience", 0);
/* 127 */     JsonArray var2 = ChatDeserializer.a(var0, "loot", new JsonArray());
/* 128 */     MinecraftKey[] var3 = new MinecraftKey[var2.size()];
/* 129 */     for (int i = 0; i < var3.length; i++) {
/* 130 */       var3[i] = new MinecraftKey(ChatDeserializer.a(var2.get(i), "loot[" + i + "]"));
/*     */     }
/* 132 */     JsonArray var4 = ChatDeserializer.a(var0, "recipes", new JsonArray());
/* 133 */     MinecraftKey[] var5 = new MinecraftKey[var4.size()];
/* 134 */     for (int j = 0; j < var5.length; j++) {
/* 135 */       var5[j] = new MinecraftKey(ChatDeserializer.a(var4.get(j), "recipes[" + j + "]"));
/*     */     }
/*     */     
/* 138 */     if (var0.has("function")) {
/* 139 */       var6 = new CustomFunction.a(new MinecraftKey(ChatDeserializer.h(var0, "function")));
/*     */     } else {
/* 141 */       var6 = CustomFunction.a.a;
/*     */     } 
/* 143 */     return new AdvancementRewards(var1, var3, var5, var6);
/*     */   }
/*     */   
/*     */   public static class a {
/*     */     private int a;
/* 148 */     private final List<MinecraftKey> b = Lists.newArrayList();
/* 149 */     private final List<MinecraftKey> c = Lists.newArrayList();
/*     */     @Nullable
/*     */     private MinecraftKey d;
/*     */     
/*     */     public static a a(int var0) {
/* 154 */       return (new a()).b(var0);
/*     */     }
/*     */     
/*     */     public a b(int var0) {
/* 158 */       this.a += var0;
/* 159 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static a c(MinecraftKey var0) {
/* 172 */       return (new a()).d(var0);
/*     */     }
/*     */     
/*     */     public a d(MinecraftKey var0) {
/* 176 */       this.c.add(var0);
/* 177 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public AdvancementRewards a() {
/* 190 */       return new AdvancementRewards(this.a, this.b.<MinecraftKey>toArray(new MinecraftKey[0]), this.c.<MinecraftKey>toArray(new MinecraftKey[0]), (this.d == null) ? CustomFunction.a.a : new CustomFunction.a(this.d));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\AdvancementRewards.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */