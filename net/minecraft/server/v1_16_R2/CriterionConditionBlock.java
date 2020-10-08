/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonNull;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSyntaxException;
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
/*     */ public class CriterionConditionBlock
/*     */ {
/*  22 */   public static final CriterionConditionBlock a = new CriterionConditionBlock(null, null, CriterionTriggerProperties.a, CriterionConditionNBT.a);
/*     */   
/*     */   @Nullable
/*     */   private final Tag<Block> b;
/*     */   @Nullable
/*     */   private final Block c;
/*     */   private final CriterionTriggerProperties d;
/*     */   private final CriterionConditionNBT e;
/*     */   
/*     */   public CriterionConditionBlock(@Nullable Tag<Block> var0, @Nullable Block var1, CriterionTriggerProperties var2, CriterionConditionNBT var3) {
/*  32 */     this.b = var0;
/*  33 */     this.c = var1;
/*  34 */     this.d = var2;
/*  35 */     this.e = var3;
/*     */   }
/*     */   
/*     */   public boolean a(WorldServer var0, BlockPosition var1) {
/*  39 */     if (this == a) {
/*  40 */       return true;
/*     */     }
/*  42 */     if (!var0.p(var1)) {
/*  43 */       return false;
/*     */     }
/*  45 */     IBlockData var2 = var0.getType(var1);
/*     */     
/*  47 */     Block var3 = var2.getBlock();
/*  48 */     if (this.b != null && !this.b.isTagged(var3)) {
/*  49 */       return false;
/*     */     }
/*  51 */     if (this.c != null && var3 != this.c) {
/*  52 */       return false;
/*     */     }
/*  54 */     if (!this.d.a(var2)) {
/*  55 */       return false;
/*     */     }
/*  57 */     if (this.e != CriterionConditionNBT.a) {
/*  58 */       TileEntity var4 = var0.getTileEntity(var1);
/*  59 */       if (var4 == null || !this.e.a(var4.save(new NBTTagCompound()))) {
/*  60 */         return false;
/*     */       }
/*     */     } 
/*  63 */     return true;
/*     */   }
/*     */   
/*     */   public static CriterionConditionBlock a(@Nullable JsonElement var0) {
/*  67 */     if (var0 == null || var0.isJsonNull()) {
/*  68 */       return a;
/*     */     }
/*  70 */     JsonObject var1 = ChatDeserializer.m(var0, "block");
/*     */     
/*  72 */     CriterionConditionNBT var2 = CriterionConditionNBT.a(var1.get("nbt"));
/*  73 */     Block var3 = null;
/*  74 */     if (var1.has("block")) {
/*  75 */       MinecraftKey minecraftKey = new MinecraftKey(ChatDeserializer.h(var1, "block"));
/*  76 */       var3 = IRegistry.BLOCK.get(minecraftKey);
/*     */     } 
/*     */     
/*  79 */     Tag<Block> var4 = null;
/*  80 */     if (var1.has("tag")) {
/*  81 */       MinecraftKey minecraftKey = new MinecraftKey(ChatDeserializer.h(var1, "tag"));
/*  82 */       var4 = TagsInstance.a().getBlockTags().a(minecraftKey);
/*  83 */       if (var4 == null) {
/*  84 */         throw new JsonSyntaxException("Unknown block tag '" + minecraftKey + "'");
/*     */       }
/*     */     } 
/*  87 */     CriterionTriggerProperties var5 = CriterionTriggerProperties.a(var1.get("state"));
/*  88 */     return new CriterionConditionBlock(var4, var3, var5, var2);
/*     */   }
/*     */   
/*     */   public JsonElement a() {
/*  92 */     if (this == a) {
/*  93 */       return (JsonElement)JsonNull.INSTANCE;
/*     */     }
/*     */     
/*  96 */     JsonObject var0 = new JsonObject();
/*  97 */     if (this.c != null) {
/*  98 */       var0.addProperty("block", IRegistry.BLOCK.getKey(this.c).toString());
/*     */     }
/* 100 */     if (this.b != null) {
/* 101 */       var0.addProperty("tag", TagsInstance.a().getBlockTags().b(this.b).toString());
/*     */     }
/* 103 */     var0.add("nbt", this.e.a());
/* 104 */     var0.add("state", this.d.a());
/*     */     
/* 106 */     return (JsonElement)var0;
/*     */   }
/*     */   
/*     */   public static class a {
/*     */     @Nullable
/*     */     private Block a;
/*     */     @Nullable
/*     */     private Tag<Block> b;
/* 114 */     private CriterionTriggerProperties c = CriterionTriggerProperties.a;
/* 115 */     private CriterionConditionNBT d = CriterionConditionNBT.a;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static a a() {
/* 121 */       return new a();
/*     */     }
/*     */     
/*     */     public a a(Block var0) {
/* 125 */       this.a = var0;
/* 126 */       return this;
/*     */     }
/*     */     
/*     */     public a a(Tag<Block> var0) {
/* 130 */       this.b = var0;
/* 131 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public a a(CriterionTriggerProperties var0) {
/* 140 */       this.c = var0;
/* 141 */       return this;
/*     */     }
/*     */     
/*     */     public CriterionConditionBlock b() {
/* 145 */       return new CriterionConditionBlock(this.b, this.a, this.c, this.d);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionConditionBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */