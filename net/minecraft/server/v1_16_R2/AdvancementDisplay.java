/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
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
/*     */ public class AdvancementDisplay
/*     */ {
/*     */   private final IChatBaseComponent a;
/*     */   private final IChatBaseComponent b;
/*     */   private final ItemStack c;
/*     */   private final MinecraftKey d;
/*     */   private final AdvancementFrameType e;
/*     */   private final boolean f;
/*     */   private final boolean g;
/*     */   private final boolean h;
/*     */   private float i;
/*     */   private float j;
/*     */   
/*     */   public AdvancementDisplay(ItemStack var0, IChatBaseComponent var1, IChatBaseComponent var2, @Nullable MinecraftKey var3, AdvancementFrameType var4, boolean var5, boolean var6, boolean var7) {
/*  33 */     this.a = var1;
/*  34 */     this.b = var2;
/*  35 */     this.c = var0;
/*  36 */     this.d = var3;
/*  37 */     this.e = var4;
/*  38 */     this.f = var5;
/*  39 */     this.g = var6;
/*  40 */     this.h = var7;
/*     */   }
/*     */   
/*     */   public void a(float var0, float var1) {
/*  44 */     this.i = var0;
/*  45 */     this.j = var1;
/*     */   }
/*     */   
/*     */   public IChatBaseComponent a() {
/*  49 */     return this.a;
/*     */   }
/*     */   
/*     */   public IChatBaseComponent b() {
/*  53 */     return this.b;
/*     */   }
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
/*     */   public AdvancementFrameType e() {
/*  66 */     return this.e;
/*     */   }
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
/*     */   public boolean i() {
/*  82 */     return this.g;
/*     */   }
/*     */   
/*     */   public boolean j() {
/*  86 */     return this.h;
/*     */   }
/*     */   
/*     */   public static AdvancementDisplay a(JsonObject var0) {
/*  90 */     IChatBaseComponent var1 = IChatBaseComponent.ChatSerializer.a(var0.get("title"));
/*  91 */     IChatBaseComponent var2 = IChatBaseComponent.ChatSerializer.a(var0.get("description"));
/*  92 */     if (var1 == null || var2 == null) {
/*  93 */       throw new JsonSyntaxException("Both title and description must be set");
/*     */     }
/*  95 */     ItemStack var3 = b(ChatDeserializer.t(var0, "icon"));
/*  96 */     MinecraftKey var4 = var0.has("background") ? new MinecraftKey(ChatDeserializer.h(var0, "background")) : null;
/*  97 */     AdvancementFrameType var5 = var0.has("frame") ? AdvancementFrameType.a(ChatDeserializer.h(var0, "frame")) : AdvancementFrameType.TASK;
/*  98 */     boolean var6 = ChatDeserializer.a(var0, "show_toast", true);
/*  99 */     boolean var7 = ChatDeserializer.a(var0, "announce_to_chat", true);
/* 100 */     boolean var8 = ChatDeserializer.a(var0, "hidden", false);
/* 101 */     return new AdvancementDisplay(var3, var1, var2, var4, var5, var6, var7, var8);
/*     */   }
/*     */   
/*     */   private static ItemStack b(JsonObject var0) {
/* 105 */     if (!var0.has("item")) {
/* 106 */       throw new JsonSyntaxException("Unsupported icon type, currently only items are supported (add 'item' key)");
/*     */     }
/* 108 */     Item var1 = ChatDeserializer.i(var0, "item");
/* 109 */     if (var0.has("data")) {
/* 110 */       throw new JsonParseException("Disallowed data tag found");
/*     */     }
/* 112 */     ItemStack var2 = new ItemStack(var1);
/* 113 */     if (var0.has("nbt")) {
/*     */       try {
/* 115 */         NBTTagCompound var3 = MojangsonParser.parse(ChatDeserializer.a(var0.get("nbt"), "nbt"));
/* 116 */         var2.setTag(var3);
/* 117 */       } catch (CommandSyntaxException var3) {
/* 118 */         throw new JsonSyntaxException("Invalid nbt tag: " + var3.getMessage());
/*     */       } 
/*     */     }
/*     */     
/* 122 */     return var2;
/*     */   }
/*     */   
/*     */   public void a(PacketDataSerializer var0) {
/* 126 */     var0.a(this.a);
/* 127 */     var0.a(this.b);
/* 128 */     var0.a(this.c);
/* 129 */     var0.a(this.e);
/* 130 */     int var1 = 0;
/* 131 */     if (this.d != null) {
/* 132 */       var1 |= 0x1;
/*     */     }
/* 134 */     if (this.f) {
/* 135 */       var1 |= 0x2;
/*     */     }
/* 137 */     if (this.h) {
/* 138 */       var1 |= 0x4;
/*     */     }
/* 140 */     var0.writeInt(var1);
/* 141 */     if (this.d != null) {
/* 142 */       var0.a(this.d);
/*     */     }
/* 144 */     var0.writeFloat(this.i);
/* 145 */     var0.writeFloat(this.j);
/*     */   }
/*     */   
/*     */   public static AdvancementDisplay b(PacketDataSerializer var0) {
/* 149 */     IChatBaseComponent var1 = var0.h();
/* 150 */     IChatBaseComponent var2 = var0.h();
/* 151 */     ItemStack var3 = var0.n();
/* 152 */     AdvancementFrameType var4 = var0.<AdvancementFrameType>a(AdvancementFrameType.class);
/* 153 */     int var5 = var0.readInt();
/* 154 */     MinecraftKey var6 = ((var5 & 0x1) != 0) ? var0.p() : null;
/* 155 */     boolean var7 = ((var5 & 0x2) != 0);
/* 156 */     boolean var8 = ((var5 & 0x4) != 0);
/* 157 */     AdvancementDisplay var9 = new AdvancementDisplay(var3, var1, var2, var6, var4, var7, false, var8);
/* 158 */     var9.a(var0.readFloat(), var0.readFloat());
/* 159 */     return var9;
/*     */   }
/*     */   
/*     */   public JsonElement k() {
/* 163 */     JsonObject var0 = new JsonObject();
/*     */     
/* 165 */     var0.add("icon", (JsonElement)l());
/* 166 */     var0.add("title", IChatBaseComponent.ChatSerializer.b(this.a));
/* 167 */     var0.add("description", IChatBaseComponent.ChatSerializer.b(this.b));
/* 168 */     var0.addProperty("frame", this.e.a());
/* 169 */     var0.addProperty("show_toast", Boolean.valueOf(this.f));
/* 170 */     var0.addProperty("announce_to_chat", Boolean.valueOf(this.g));
/* 171 */     var0.addProperty("hidden", Boolean.valueOf(this.h));
/*     */     
/* 173 */     if (this.d != null) {
/* 174 */       var0.addProperty("background", this.d.toString());
/*     */     }
/*     */     
/* 177 */     return (JsonElement)var0;
/*     */   }
/*     */   
/*     */   private JsonObject l() {
/* 181 */     JsonObject var0 = new JsonObject();
/* 182 */     var0.addProperty("item", IRegistry.ITEM.getKey(this.c.getItem()).toString());
/* 183 */     if (this.c.hasTag()) {
/* 184 */       var0.addProperty("nbt", this.c.getTag().toString());
/*     */     }
/* 186 */     return var0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\AdvancementDisplay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */