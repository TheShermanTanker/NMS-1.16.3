/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Function;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.ArrayUtils;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.advancement.CraftAdvancement;
/*     */ 
/*     */ public class Advancement {
/*     */   private final Advancement parent;
/*     */   private final AdvancementDisplay display;
/*     */   private final AdvancementRewards rewards;
/*     */   private final MinecraftKey key;
/*     */   private final Map<String, Criterion> criteria;
/*     */   private final String[][] requirements;
/*  27 */   private final Set<Advancement> children = Sets.newLinkedHashSet();
/*     */   private final IChatBaseComponent chatComponent;
/*  29 */   public final org.bukkit.advancement.Advancement bukkit = (org.bukkit.advancement.Advancement)new CraftAdvancement(this);
/*     */   
/*     */   public Advancement(MinecraftKey minecraftkey, @Nullable Advancement advancement, @Nullable AdvancementDisplay advancementdisplay, AdvancementRewards advancementrewards, Map<String, Criterion> map, String[][] astring) {
/*  32 */     this.key = minecraftkey;
/*  33 */     this.display = advancementdisplay;
/*  34 */     this.criteria = (Map<String, Criterion>)ImmutableMap.copyOf(map);
/*  35 */     this.parent = advancement;
/*  36 */     this.rewards = advancementrewards;
/*  37 */     this.requirements = astring;
/*  38 */     if (advancement != null) {
/*  39 */       advancement.a(this);
/*     */     }
/*     */     
/*  42 */     if (advancementdisplay == null) {
/*  43 */       this.chatComponent = new ChatComponentText(minecraftkey.toString());
/*     */     } else {
/*  45 */       IChatBaseComponent ichatbasecomponent = advancementdisplay.a();
/*  46 */       EnumChatFormat enumchatformat = advancementdisplay.e().c();
/*  47 */       IChatMutableComponent ichatmutablecomponent = ChatComponentUtils.a(ichatbasecomponent.mutableCopy(), ChatModifier.a.setColor(enumchatformat)).c("\n").addSibling(advancementdisplay.b());
/*  48 */       IChatMutableComponent ichatmutablecomponent1 = ichatbasecomponent.mutableCopy().format(chatmodifier -> chatmodifier.setChatHoverable(new ChatHoverable((ChatHoverable.EnumHoverAction)ChatHoverable.EnumHoverAction.SHOW_TEXT, (T)ichatmutablecomponent)));
/*     */ 
/*     */ 
/*     */       
/*  52 */       this.chatComponent = ChatComponentUtils.a(ichatmutablecomponent1).a(enumchatformat);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public SerializedAdvancement a() {
/*  58 */     return new SerializedAdvancement((this.parent == null) ? null : this.parent.getName(), this.display, this.rewards, this.criteria, this.requirements);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Advancement b() {
/*  63 */     return this.parent;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public AdvancementDisplay c() {
/*  68 */     return this.display;
/*     */   }
/*     */   
/*     */   public AdvancementRewards d() {
/*  72 */     return this.rewards;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  76 */     return "SimpleAdvancement{id=" + getName() + ", parent=" + ((this.parent == null) ? "null" : (String)this.parent.getName()) + ", display=" + this.display + ", rewards=" + this.rewards + ", criteria=" + this.criteria + ", requirements=" + Arrays.deepToString((Object[])this.requirements) + '}';
/*     */   }
/*     */   
/*     */   public Iterable<Advancement> e() {
/*  80 */     return this.children;
/*     */   }
/*     */   
/*     */   public Map<String, Criterion> getCriteria() {
/*  84 */     return this.criteria;
/*     */   }
/*     */   
/*     */   public void a(Advancement advancement) {
/*  88 */     this.children.add(advancement);
/*     */   }
/*     */   
/*     */   public MinecraftKey getName() {
/*  92 */     return this.key;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/*  96 */     if (this == object)
/*  97 */       return true; 
/*  98 */     if (!(object instanceof Advancement)) {
/*  99 */       return false;
/*     */     }
/* 101 */     Advancement advancement = (Advancement)object;
/*     */     
/* 103 */     return this.key.equals(advancement.key);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 108 */     return this.key.hashCode();
/*     */   }
/*     */   
/*     */   public String[][] i() {
/* 112 */     return this.requirements;
/*     */   }
/*     */   
/*     */   public IChatBaseComponent j() {
/* 116 */     return this.chatComponent;
/*     */   }
/*     */   
/*     */   public static class SerializedAdvancement
/*     */   {
/*     */     private MinecraftKey a;
/*     */     private Advancement b;
/*     */     private AdvancementDisplay c;
/*     */     private AdvancementRewards d;
/*     */     private Map<String, Criterion> e;
/*     */     private String[][] f;
/*     */     private AdvancementRequirements g;
/*     */     
/*     */     private SerializedAdvancement(@Nullable MinecraftKey minecraftkey, @Nullable AdvancementDisplay advancementdisplay, AdvancementRewards advancementrewards, Map<String, Criterion> map, String[][] astring) {
/* 130 */       this.d = AdvancementRewards.a;
/* 131 */       this.e = Maps.newLinkedHashMap();
/* 132 */       this.g = AdvancementRequirements.AND;
/* 133 */       this.a = minecraftkey;
/* 134 */       this.c = advancementdisplay;
/* 135 */       this.d = advancementrewards;
/* 136 */       this.e = map;
/* 137 */       this.f = astring;
/*     */     }
/*     */     
/*     */     private SerializedAdvancement() {
/* 141 */       this.d = AdvancementRewards.a;
/* 142 */       this.e = Maps.newLinkedHashMap();
/* 143 */       this.g = AdvancementRequirements.AND;
/*     */     }
/*     */     
/*     */     public static SerializedAdvancement a() {
/* 147 */       return new SerializedAdvancement();
/*     */     }
/*     */     
/*     */     public SerializedAdvancement a(Advancement advancement) {
/* 151 */       this.b = advancement;
/* 152 */       return this;
/*     */     }
/*     */     
/*     */     public SerializedAdvancement a(MinecraftKey minecraftkey) {
/* 156 */       this.a = minecraftkey;
/* 157 */       return this;
/*     */     }
/*     */     
/*     */     public SerializedAdvancement a(ItemStack itemstack, IChatBaseComponent ichatbasecomponent, IChatBaseComponent ichatbasecomponent1, @Nullable MinecraftKey minecraftkey, AdvancementFrameType advancementframetype, boolean flag, boolean flag1, boolean flag2) {
/* 161 */       return a(new AdvancementDisplay(itemstack, ichatbasecomponent, ichatbasecomponent1, minecraftkey, advancementframetype, flag, flag1, flag2));
/*     */     }
/*     */     
/*     */     public SerializedAdvancement a(IMaterial imaterial, IChatBaseComponent ichatbasecomponent, IChatBaseComponent ichatbasecomponent1, @Nullable MinecraftKey minecraftkey, AdvancementFrameType advancementframetype, boolean flag, boolean flag1, boolean flag2) {
/* 165 */       return a(new AdvancementDisplay(new ItemStack(imaterial.getItem()), ichatbasecomponent, ichatbasecomponent1, minecraftkey, advancementframetype, flag, flag1, flag2));
/*     */     }
/*     */     
/*     */     public SerializedAdvancement a(AdvancementDisplay advancementdisplay) {
/* 169 */       this.c = advancementdisplay;
/* 170 */       return this;
/*     */     }
/*     */     
/*     */     public SerializedAdvancement a(AdvancementRewards.a advancementrewards_a) {
/* 174 */       return a(advancementrewards_a.a());
/*     */     }
/*     */     
/*     */     public SerializedAdvancement a(AdvancementRewards advancementrewards) {
/* 178 */       this.d = advancementrewards;
/* 179 */       return this;
/*     */     }
/*     */     
/*     */     public SerializedAdvancement a(String s, CriterionInstance criterioninstance) {
/* 183 */       return a(s, new Criterion(criterioninstance));
/*     */     }
/*     */     
/*     */     public SerializedAdvancement a(String s, Criterion criterion) {
/* 187 */       if (this.e.containsKey(s)) {
/* 188 */         throw new IllegalArgumentException("Duplicate criterion " + s);
/*     */       }
/* 190 */       this.e.put(s, criterion);
/* 191 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public SerializedAdvancement a(AdvancementRequirements advancementrequirements) {
/* 196 */       this.g = advancementrequirements;
/* 197 */       return this;
/*     */     }
/*     */     
/*     */     public boolean a(Function<MinecraftKey, Advancement> function) {
/* 201 */       if (this.a == null) {
/* 202 */         return true;
/*     */       }
/* 204 */       if (this.b == null) {
/* 205 */         this.b = function.apply(this.a);
/*     */       }
/*     */       
/* 208 */       return (this.b != null);
/*     */     }
/*     */ 
/*     */     
/*     */     public Advancement b(MinecraftKey minecraftkey) {
/* 213 */       if (!a(minecraftkey1 -> null))
/*     */       {
/*     */         
/* 216 */         throw new IllegalStateException("Tried to build incomplete advancement!");
/*     */       }
/* 218 */       if (this.f == null) {
/* 219 */         this.f = this.g.createRequirements(this.e.keySet());
/*     */       }
/*     */       
/* 222 */       return new Advancement(minecraftkey, this.b, this.c, this.d, this.e, this.f);
/*     */     }
/*     */ 
/*     */     
/*     */     public Advancement a(Consumer<Advancement> consumer, String s) {
/* 227 */       Advancement advancement = b(new MinecraftKey(s));
/*     */       
/* 229 */       consumer.accept(advancement);
/* 230 */       return advancement;
/*     */     }
/*     */     
/*     */     public JsonObject b() {
/* 234 */       if (this.f == null) {
/* 235 */         this.f = this.g.createRequirements(this.e.keySet());
/*     */       }
/*     */       
/* 238 */       JsonObject jsonobject = new JsonObject();
/*     */       
/* 240 */       if (this.b != null) {
/* 241 */         jsonobject.addProperty("parent", this.b.getName().toString());
/* 242 */       } else if (this.a != null) {
/* 243 */         jsonobject.addProperty("parent", this.a.toString());
/*     */       } 
/*     */       
/* 246 */       if (this.c != null) {
/* 247 */         jsonobject.add("display", this.c.k());
/*     */       }
/*     */       
/* 250 */       jsonobject.add("rewards", this.d.b());
/* 251 */       JsonObject jsonobject1 = new JsonObject();
/* 252 */       Iterator<Map.Entry<String, Criterion>> iterator = this.e.entrySet().iterator();
/*     */       
/* 254 */       while (iterator.hasNext()) {
/* 255 */         Map.Entry<String, Criterion> entry = iterator.next();
/*     */         
/* 257 */         jsonobject1.add(entry.getKey(), ((Criterion)entry.getValue()).b());
/*     */       } 
/*     */       
/* 260 */       jsonobject.add("criteria", (JsonElement)jsonobject1);
/* 261 */       JsonArray jsonarray = new JsonArray();
/* 262 */       String[][] astring = this.f;
/* 263 */       int i = astring.length;
/*     */       
/* 265 */       for (int j = 0; j < i; j++) {
/* 266 */         String[] astring1 = astring[j];
/* 267 */         JsonArray jsonarray1 = new JsonArray();
/* 268 */         String[] astring2 = astring1;
/* 269 */         int k = astring1.length;
/*     */         
/* 271 */         for (int l = 0; l < k; l++) {
/* 272 */           String s = astring2[l];
/*     */           
/* 274 */           jsonarray1.add(s);
/*     */         } 
/*     */         
/* 277 */         jsonarray.add((JsonElement)jsonarray1);
/*     */       } 
/*     */       
/* 280 */       jsonobject.add("requirements", (JsonElement)jsonarray);
/* 281 */       return jsonobject;
/*     */     }
/*     */     
/*     */     public void a(PacketDataSerializer packetdataserializer) {
/* 285 */       if (this.a == null) {
/* 286 */         packetdataserializer.writeBoolean(false);
/*     */       } else {
/* 288 */         packetdataserializer.writeBoolean(true);
/* 289 */         packetdataserializer.a(this.a);
/*     */       } 
/*     */       
/* 292 */       if (this.c == null) {
/* 293 */         packetdataserializer.writeBoolean(false);
/*     */       } else {
/* 295 */         packetdataserializer.writeBoolean(true);
/* 296 */         this.c.a(packetdataserializer);
/*     */       } 
/*     */       
/* 299 */       Criterion.a(this.e, packetdataserializer);
/* 300 */       packetdataserializer.d(this.f.length);
/* 301 */       String[][] astring = this.f;
/* 302 */       int i = astring.length;
/*     */       
/* 304 */       for (int j = 0; j < i; j++) {
/* 305 */         String[] astring1 = astring[j];
/*     */         
/* 307 */         packetdataserializer.d(astring1.length);
/* 308 */         String[] astring2 = astring1;
/* 309 */         int k = astring1.length;
/*     */         
/* 311 */         for (int l = 0; l < k; l++) {
/* 312 */           String s = astring2[l];
/*     */           
/* 314 */           packetdataserializer.a(s);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 321 */       return "Task Advancement{parentId=" + this.a + ", display=" + this.c + ", rewards=" + this.d + ", criteria=" + this.e + ", requirements=" + Arrays.deepToString((Object[])this.f) + '}';
/*     */     }
/*     */     
/*     */     public static SerializedAdvancement a(JsonObject jsonobject, LootDeserializationContext lootdeserializationcontext) {
/* 325 */       MinecraftKey minecraftkey = jsonobject.has("parent") ? new MinecraftKey(ChatDeserializer.h(jsonobject, "parent")) : null;
/* 326 */       AdvancementDisplay advancementdisplay = jsonobject.has("display") ? AdvancementDisplay.a(ChatDeserializer.t(jsonobject, "display")) : null;
/* 327 */       AdvancementRewards advancementrewards = jsonobject.has("rewards") ? AdvancementRewards.a(ChatDeserializer.t(jsonobject, "rewards")) : AdvancementRewards.a;
/* 328 */       Map<String, Criterion> map = Criterion.b(ChatDeserializer.t(jsonobject, "criteria"), lootdeserializationcontext);
/*     */       
/* 330 */       if (map.isEmpty()) {
/* 331 */         throw new JsonSyntaxException("Advancement criteria cannot be empty");
/*     */       }
/* 333 */       JsonArray jsonarray = ChatDeserializer.a(jsonobject, "requirements", new JsonArray());
/* 334 */       String[][] astring = new String[jsonarray.size()][];
/*     */ 
/*     */       
/*     */       int i;
/*     */       
/* 339 */       for (i = 0; i < jsonarray.size(); i++) {
/* 340 */         JsonArray jsonarray1 = ChatDeserializer.n(jsonarray.get(i), "requirements[" + i + "]");
/*     */         
/* 342 */         astring[i] = new String[jsonarray1.size()];
/*     */         
/* 344 */         for (int m = 0; m < jsonarray1.size(); m++) {
/* 345 */           astring[i][m] = ChatDeserializer.a(jsonarray1.get(m), "requirements[" + i + "][" + m + "]");
/*     */         }
/*     */       } 
/*     */       
/* 349 */       if (astring.length == 0) {
/* 350 */         astring = new String[map.size()][];
/* 351 */         i = 0;
/*     */ 
/*     */ 
/*     */         
/* 355 */         for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext(); (new String[1])[0] = s, astring[i++] = new String[1]) {
/* 356 */           String s = iterator.next();
/*     */         }
/*     */       } 
/*     */       
/* 360 */       String[][] astring1 = astring;
/* 361 */       int k = astring.length;
/*     */ 
/*     */ 
/*     */       
/* 365 */       for (int j = 0; j < k; j++) {
/* 366 */         String[] astring2 = astring1[j];
/*     */         
/* 368 */         if (astring2.length == 0 && map.isEmpty()) {
/* 369 */           throw new JsonSyntaxException("Requirement entry cannot be empty");
/*     */         }
/*     */         
/* 372 */         String[] astring3 = astring2;
/*     */         
/* 374 */         int l = astring2.length;
/*     */         
/* 376 */         for (int i1 = 0; i1 < l; i1++) {
/* 377 */           String s1 = astring3[i1];
/*     */           
/* 379 */           if (!map.containsKey(s1)) {
/* 380 */             throw new JsonSyntaxException("Unknown required criterion '" + s1 + "'");
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 385 */       Iterator<String> iterator1 = map.keySet().iterator();
/*     */       
/* 387 */       while (iterator1.hasNext()) {
/* 388 */         String s2 = iterator1.next();
/* 389 */         boolean flag = false;
/* 390 */         String[][] astring4 = astring;
/* 391 */         int j1 = astring.length;
/*     */         
/* 393 */         int l = 0;
/*     */ 
/*     */         
/* 396 */         while (l < j1) {
/* 397 */           String[] astring5 = astring4[l];
/*     */           
/* 399 */           if (!ArrayUtils.contains((Object[])astring5, s2)) {
/* 400 */             l++;
/*     */             
/*     */             continue;
/*     */           } 
/* 404 */           flag = true;
/*     */         } 
/*     */         
/* 407 */         if (!flag) {
/* 408 */           throw new JsonSyntaxException("Criterion '" + s2 + "' isn't a requirement for completion. This isn't supported behaviour, all criteria must be required.");
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 414 */       return new SerializedAdvancement(minecraftkey, advancementdisplay, advancementrewards, map, astring);
/*     */     }
/*     */ 
/*     */     
/*     */     public static SerializedAdvancement b(PacketDataSerializer packetdataserializer) {
/* 419 */       MinecraftKey minecraftkey = packetdataserializer.readBoolean() ? packetdataserializer.p() : null;
/* 420 */       AdvancementDisplay advancementdisplay = packetdataserializer.readBoolean() ? AdvancementDisplay.b(packetdataserializer) : null;
/* 421 */       Map<String, Criterion> map = Criterion.c(packetdataserializer);
/* 422 */       String[][] astring = new String[packetdataserializer.i()][];
/*     */       
/* 424 */       for (int i = 0; i < astring.length; i++) {
/* 425 */         astring[i] = new String[packetdataserializer.i()];
/*     */         
/* 427 */         for (int j = 0; j < (astring[i]).length; j++) {
/* 428 */           astring[i][j] = packetdataserializer.e(32767);
/*     */         }
/*     */       } 
/*     */       
/* 432 */       return new SerializedAdvancement(minecraftkey, advancementdisplay, AdvancementRewards.a, map, astring);
/*     */     }
/*     */     
/*     */     public Map<String, Criterion> c() {
/* 436 */       return this.e;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Advancement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */