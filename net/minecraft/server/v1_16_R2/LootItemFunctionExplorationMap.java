/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import java.util.Locale;
/*     */ import java.util.Set;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class LootItemFunctionExplorationMap
/*     */   extends LootItemFunctionConditional {
/*  14 */   private static final Logger LOGGER = LogManager.getLogger();
/*  15 */   public static final StructureGenerator<?> a = StructureGenerator.BURIED_TREASURE;
/*  16 */   public static final MapIcon.Type b = MapIcon.Type.MANSION;
/*     */   private final StructureGenerator<?> e;
/*     */   private final MapIcon.Type f;
/*     */   private final byte g;
/*     */   private final int h;
/*     */   private final boolean i;
/*     */   
/*     */   private LootItemFunctionExplorationMap(LootItemCondition[] alootitemcondition, StructureGenerator<?> structuregenerator, MapIcon.Type mapicon_type, byte b0, int i, boolean flag) {
/*  24 */     super(alootitemcondition);
/*  25 */     this.e = structuregenerator;
/*  26 */     this.f = mapicon_type;
/*  27 */     this.g = b0;
/*  28 */     this.h = i;
/*  29 */     this.i = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public LootItemFunctionType b() {
/*  34 */     return LootItemFunctions.k;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<LootContextParameter<?>> a() {
/*  39 */     return (Set<LootContextParameter<?>>)ImmutableSet.of(LootContextParameters.ORIGIN);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(ItemStack itemstack, LootTableInfo loottableinfo) {
/*  44 */     if (itemstack.getItem() != Items.MAP) {
/*  45 */       return itemstack;
/*     */     }
/*  47 */     Vec3D vec3d = loottableinfo.<Vec3D>getContextParameter(LootContextParameters.ORIGIN);
/*     */     
/*  49 */     if (vec3d != null) {
/*  50 */       WorldServer worldserver = loottableinfo.getWorld();
/*     */       
/*  52 */       if (!worldserver.paperConfig.enableTreasureMaps)
/*     */       {
/*     */ 
/*     */ 
/*     */         
/*  57 */         return itemstack;
/*     */       }
/*     */       
/*  60 */       BlockPosition blockposition = worldserver.a(this.e, new BlockPosition(vec3d), this.h, this.i);
/*     */       
/*  62 */       if (blockposition != null) {
/*  63 */         ItemStack itemstack1 = ItemWorldMap.createFilledMapView(worldserver, blockposition.getX(), blockposition.getZ(), this.g, true, true);
/*     */         
/*  65 */         ItemWorldMap.applySepiaFilter(worldserver, itemstack1);
/*  66 */         WorldMap.decorateMap(itemstack1, blockposition, "+", this.f);
/*  67 */         itemstack1.a(new ChatMessage("filled_map." + this.e.i().toLowerCase(Locale.ROOT)));
/*  68 */         return itemstack1;
/*     */       } 
/*     */     } 
/*     */     
/*  72 */     return itemstack;
/*     */   }
/*     */ 
/*     */   
/*     */   public static a c() {
/*  77 */     return new a();
/*     */   }
/*     */ 
/*     */   
/*     */   public static class b
/*     */     extends LootItemFunctionConditional.c<LootItemFunctionExplorationMap>
/*     */   {
/*     */     public void a(JsonObject jsonobject, LootItemFunctionExplorationMap lootitemfunctionexplorationmap, JsonSerializationContext jsonserializationcontext) {
/*  85 */       super.a(jsonobject, lootitemfunctionexplorationmap, jsonserializationcontext);
/*  86 */       if (!lootitemfunctionexplorationmap.e.equals(LootItemFunctionExplorationMap.a)) {
/*  87 */         jsonobject.add("destination", jsonserializationcontext.serialize(lootitemfunctionexplorationmap.e.i()));
/*     */       }
/*     */       
/*  90 */       if (lootitemfunctionexplorationmap.f != LootItemFunctionExplorationMap.b) {
/*  91 */         jsonobject.add("decoration", jsonserializationcontext.serialize(lootitemfunctionexplorationmap.f.toString().toLowerCase(Locale.ROOT)));
/*     */       }
/*     */       
/*  94 */       if (lootitemfunctionexplorationmap.g != 2) {
/*  95 */         jsonobject.addProperty("zoom", Byte.valueOf(lootitemfunctionexplorationmap.g));
/*     */       }
/*     */       
/*  98 */       if (lootitemfunctionexplorationmap.h != 50) {
/*  99 */         jsonobject.addProperty("search_radius", Integer.valueOf(lootitemfunctionexplorationmap.h));
/*     */       }
/*     */       
/* 102 */       if (!lootitemfunctionexplorationmap.i) {
/* 103 */         jsonobject.addProperty("skip_existing_chunks", Boolean.valueOf(lootitemfunctionexplorationmap.i));
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public LootItemFunctionExplorationMap b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext, LootItemCondition[] alootitemcondition) {
/* 110 */       StructureGenerator<?> structuregenerator = a(jsonobject);
/* 111 */       String s = jsonobject.has("decoration") ? ChatDeserializer.h(jsonobject, "decoration") : "mansion";
/* 112 */       MapIcon.Type mapicon_type = LootItemFunctionExplorationMap.b;
/*     */       
/*     */       try {
/* 115 */         mapicon_type = MapIcon.Type.valueOf(s.toUpperCase(Locale.ROOT));
/* 116 */       } catch (IllegalArgumentException illegalargumentexception) {
/* 117 */         LootItemFunctionExplorationMap.LOGGER.error("Error while parsing loot table decoration entry. Found {}. Defaulting to " + LootItemFunctionExplorationMap.b, s);
/*     */       } 
/*     */       
/* 120 */       byte b0 = ChatDeserializer.a(jsonobject, "zoom", (byte)2);
/* 121 */       int i = ChatDeserializer.a(jsonobject, "search_radius", 50);
/* 122 */       boolean flag = ChatDeserializer.a(jsonobject, "skip_existing_chunks", true);
/*     */       
/* 124 */       return new LootItemFunctionExplorationMap(alootitemcondition, structuregenerator, mapicon_type, b0, i, flag);
/*     */     }
/*     */     
/*     */     private static StructureGenerator<?> a(JsonObject jsonobject) {
/* 128 */       if (jsonobject.has("destination")) {
/* 129 */         String s = ChatDeserializer.h(jsonobject, "destination");
/* 130 */         StructureGenerator<?> structuregenerator = (StructureGenerator)StructureGenerator.a.get(s.toLowerCase(Locale.ROOT));
/*     */         
/* 132 */         if (structuregenerator != null) {
/* 133 */           return structuregenerator;
/*     */         }
/*     */       } 
/*     */       
/* 137 */       return LootItemFunctionExplorationMap.a;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class a
/*     */     extends LootItemFunctionConditional.a<a>
/*     */   {
/* 150 */     private StructureGenerator<?> a = LootItemFunctionExplorationMap.a;
/* 151 */     private MapIcon.Type b = LootItemFunctionExplorationMap.b;
/* 152 */     private byte c = 2;
/* 153 */     private int d = 50;
/*     */     
/*     */     private boolean e = true;
/*     */ 
/*     */     
/*     */     protected a d() {
/* 159 */       return this;
/*     */     }
/*     */     
/*     */     public a a(StructureGenerator<?> structuregenerator) {
/* 163 */       this.a = structuregenerator;
/* 164 */       return this;
/*     */     }
/*     */     
/*     */     public a a(MapIcon.Type mapicon_type) {
/* 168 */       this.b = mapicon_type;
/* 169 */       return this;
/*     */     }
/*     */     
/*     */     public a a(byte b0) {
/* 173 */       this.c = b0;
/* 174 */       return this;
/*     */     }
/*     */     
/*     */     public a a(boolean flag) {
/* 178 */       this.e = flag;
/* 179 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public LootItemFunction b() {
/* 184 */       return new LootItemFunctionExplorationMap(g(), this.a, this.b, this.c, this.d, this.e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemFunctionExplorationMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */