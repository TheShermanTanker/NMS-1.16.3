/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.tree.ArgumentCommandNode;
/*     */ import com.mojang.brigadier.tree.CommandNode;
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArgumentRegistry
/*     */ {
/*  65 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*  67 */   private static final Map<Class<?>, a<?>> b = Maps.newHashMap();
/*  68 */   private static final Map<MinecraftKey, a<?>> c = Maps.newHashMap();
/*     */   
/*     */   public static <T extends ArgumentType<?>> void a(String var0, Class<T> var1, ArgumentSerializer<T> var2) {
/*  71 */     MinecraftKey var3 = new MinecraftKey(var0);
/*     */     
/*  73 */     if (b.containsKey(var1)) {
/*  74 */       throw new IllegalArgumentException("Class " + var1.getName() + " already has a serializer!");
/*     */     }
/*  76 */     if (c.containsKey(var3)) {
/*  77 */       throw new IllegalArgumentException("'" + var3 + "' is already a registered serializer!");
/*     */     }
/*  79 */     a<T> var4 = new a<>(var1, var2, var3);
/*  80 */     b.put(var1, var4);
/*  81 */     c.put(var3, var4);
/*     */   }
/*     */   
/*     */   public static void a() {
/*  85 */     ArgumentSerializers.a();
/*  86 */     a("entity", ArgumentEntity.class, new ArgumentEntity.a());
/*  87 */     a("game_profile", ArgumentProfile.class, new ArgumentSerializerVoid<>(ArgumentProfile::a));
/*  88 */     a("block_pos", ArgumentPosition.class, new ArgumentSerializerVoid<>(ArgumentPosition::a));
/*  89 */     a("column_pos", ArgumentVec2I.class, new ArgumentSerializerVoid<>(ArgumentVec2I::a));
/*  90 */     a("vec3", ArgumentVec3.class, new ArgumentSerializerVoid<>(ArgumentVec3::a));
/*  91 */     a("vec2", ArgumentVec2.class, new ArgumentSerializerVoid<>(ArgumentVec2::a));
/*  92 */     a("block_state", ArgumentTile.class, new ArgumentSerializerVoid<>(ArgumentTile::a));
/*  93 */     a("block_predicate", ArgumentBlockPredicate.class, new ArgumentSerializerVoid<>(ArgumentBlockPredicate::a));
/*  94 */     a("item_stack", ArgumentItemStack.class, new ArgumentSerializerVoid<>(ArgumentItemStack::a));
/*  95 */     a("item_predicate", ArgumentItemPredicate.class, new ArgumentSerializerVoid<>(ArgumentItemPredicate::a));
/*  96 */     a("color", ArgumentChatFormat.class, new ArgumentSerializerVoid<>(ArgumentChatFormat::a));
/*  97 */     a("component", ArgumentChatComponent.class, new ArgumentSerializerVoid<>(ArgumentChatComponent::a));
/*  98 */     a("message", ArgumentChat.class, new ArgumentSerializerVoid<>(ArgumentChat::a));
/*  99 */     a("nbt_compound_tag", ArgumentNBTTag.class, new ArgumentSerializerVoid<>(ArgumentNBTTag::a));
/* 100 */     a("nbt_tag", ArgumentNBTBase.class, new ArgumentSerializerVoid<>(ArgumentNBTBase::a));
/* 101 */     a("nbt_path", ArgumentNBTKey.class, new ArgumentSerializerVoid<>(ArgumentNBTKey::a));
/* 102 */     a("objective", ArgumentScoreboardObjective.class, new ArgumentSerializerVoid<>(ArgumentScoreboardObjective::a));
/* 103 */     a("objective_criteria", ArgumentScoreboardCriteria.class, new ArgumentSerializerVoid<>(ArgumentScoreboardCriteria::a));
/* 104 */     a("operation", ArgumentMathOperation.class, new ArgumentSerializerVoid<>(ArgumentMathOperation::a));
/* 105 */     a("particle", ArgumentParticle.class, new ArgumentSerializerVoid<>(ArgumentParticle::a));
/* 106 */     a("angle", ArgumentAngle.class, new ArgumentSerializerVoid<>(ArgumentAngle::a));
/* 107 */     a("rotation", ArgumentRotation.class, new ArgumentSerializerVoid<>(ArgumentRotation::a));
/* 108 */     a("scoreboard_slot", ArgumentScoreboardSlot.class, new ArgumentSerializerVoid<>(ArgumentScoreboardSlot::a));
/* 109 */     a("score_holder", ArgumentScoreholder.class, new ArgumentScoreholder.c());
/* 110 */     a("swizzle", ArgumentRotationAxis.class, new ArgumentSerializerVoid<>(ArgumentRotationAxis::a));
/* 111 */     a("team", ArgumentScoreboardTeam.class, new ArgumentSerializerVoid<>(ArgumentScoreboardTeam::a));
/* 112 */     a("item_slot", ArgumentInventorySlot.class, new ArgumentSerializerVoid<>(ArgumentInventorySlot::a));
/* 113 */     a("resource_location", ArgumentMinecraftKeyRegistered.class, new ArgumentSerializerVoid<>(ArgumentMinecraftKeyRegistered::a));
/* 114 */     a("mob_effect", ArgumentMobEffect.class, new ArgumentSerializerVoid<>(ArgumentMobEffect::a));
/* 115 */     a("function", ArgumentTag.class, new ArgumentSerializerVoid<>(ArgumentTag::a));
/* 116 */     a("entity_anchor", ArgumentAnchor.class, new ArgumentSerializerVoid<>(ArgumentAnchor::a));
/* 117 */     a("int_range", ArgumentCriterionValue.b.class, new ArgumentSerializerVoid<>(ArgumentCriterionValue::a));
/* 118 */     a("float_range", ArgumentCriterionValue.a.class, new ArgumentSerializerVoid<>(ArgumentCriterionValue::b));
/* 119 */     a("item_enchantment", ArgumentEnchantment.class, new ArgumentSerializerVoid<>(ArgumentEnchantment::a));
/* 120 */     a("entity_summon", ArgumentEntitySummon.class, new ArgumentSerializerVoid<>(ArgumentEntitySummon::a));
/* 121 */     a("dimension", ArgumentDimension.class, new ArgumentSerializerVoid<>(ArgumentDimension::a));
/* 122 */     a("time", ArgumentTime.class, new ArgumentSerializerVoid<>(ArgumentTime::a));
/* 123 */     a("uuid", ArgumentUUID.class, new ArgumentSerializerVoid<>(ArgumentUUID::a));
/*     */     
/* 125 */     if (SharedConstants.d) {
/* 126 */       a("test_argument", GameTestHarnessTestFunctionArgument.class, new ArgumentSerializerVoid<>(GameTestHarnessTestFunctionArgument::a));
/* 127 */       a("test_class", GameTestHarnessTestClassArgument.class, new ArgumentSerializerVoid<>(GameTestHarnessTestClassArgument::a));
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static a<?> a(MinecraftKey var0) {
/* 133 */     return c.get(var0);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static a<?> b(ArgumentType<?> var0) {
/* 138 */     return b.get(var0.getClass());
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends ArgumentType<?>> void a(PacketDataSerializer var0, T var1) {
/* 143 */     a<T> var2 = (a)b((ArgumentType<?>)var1);
/* 144 */     if (var2 == null) {
/* 145 */       LOGGER.error("Could not serialize {} ({}) - will not be sent to client!", var1, var1.getClass());
/* 146 */       var0.a(new MinecraftKey(""));
/*     */       
/*     */       return;
/*     */     } 
/* 150 */     var0.a(var2.c);
/* 151 */     var2.b.a(var1, var0);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static ArgumentType<?> a(PacketDataSerializer var0) {
/* 156 */     MinecraftKey var1 = var0.p();
/* 157 */     a<?> var2 = a(var1);
/*     */     
/* 159 */     if (var2 == null) {
/* 160 */       LOGGER.error("Could not deserialize {}", var1);
/* 161 */       return null;
/*     */     } 
/*     */     
/* 164 */     return (ArgumentType<?>)var2.b.b(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   private static <T extends ArgumentType<?>> void a(JsonObject var0, T var1) {
/* 169 */     a<T> var2 = (a)b((ArgumentType<?>)var1);
/* 170 */     if (var2 == null) {
/* 171 */       LOGGER.error("Could not serialize argument {} ({})!", var1, var1.getClass());
/* 172 */       var0.addProperty("type", "unknown");
/*     */     } else {
/* 174 */       var0.addProperty("type", "argument");
/* 175 */       var0.addProperty("parser", var2.c.toString());
/*     */       
/* 177 */       JsonObject var3 = new JsonObject();
/* 178 */       var2.b.a(var1, var3);
/* 179 */       if (var3.size() > 0) {
/* 180 */         var0.add("properties", (JsonElement)var3);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static <S> JsonObject a(CommandDispatcher<S> var0, CommandNode<S> var1) {
/* 186 */     JsonObject var2 = new JsonObject();
/*     */     
/* 188 */     if (var1 instanceof com.mojang.brigadier.tree.RootCommandNode) {
/* 189 */       var2.addProperty("type", "root");
/* 190 */     } else if (var1 instanceof com.mojang.brigadier.tree.LiteralCommandNode) {
/* 191 */       var2.addProperty("type", "literal");
/* 192 */     } else if (var1 instanceof ArgumentCommandNode) {
/* 193 */       a(var2, ((ArgumentCommandNode)var1).getType());
/*     */     } else {
/* 195 */       LOGGER.error("Could not serialize node {} ({})!", var1, var1.getClass());
/*     */       
/* 197 */       var2.addProperty("type", "unknown");
/*     */     } 
/*     */     
/* 200 */     JsonObject var3 = new JsonObject();
/* 201 */     for (CommandNode<S> var5 : (Iterable<CommandNode<S>>)var1.getChildren()) {
/* 202 */       var3.add(var5.getName(), (JsonElement)a(var0, var5));
/*     */     }
/* 204 */     if (var3.size() > 0) {
/* 205 */       var2.add("children", (JsonElement)var3);
/*     */     }
/*     */     
/* 208 */     if (var1.getCommand() != null) {
/* 209 */       var2.addProperty("executable", Boolean.valueOf(true));
/*     */     }
/*     */     
/* 212 */     if (var1.getRedirect() != null) {
/* 213 */       Collection<String> var4 = var0.getPath(var1.getRedirect());
/* 214 */       if (!var4.isEmpty()) {
/* 215 */         JsonArray var5 = new JsonArray();
/* 216 */         for (String var7 : var4) {
/* 217 */           var5.add(var7);
/*     */         }
/* 219 */         var2.add("redirect", (JsonElement)var5);
/*     */       } 
/*     */     } 
/*     */     
/* 223 */     return var2;
/*     */   }
/*     */   
/*     */   public static boolean a(ArgumentType<?> var0) {
/* 227 */     return (b(var0) != null);
/*     */   }
/*     */   
/*     */   public static <T> Set<ArgumentType<?>> a(CommandNode<T> var0) {
/* 231 */     Set<CommandNode<T>> var1 = Sets.newIdentityHashSet();
/* 232 */     Set<ArgumentType<?>> var2 = Sets.newHashSet();
/* 233 */     a(var0, var2, var1);
/* 234 */     return var2;
/*     */   }
/*     */   
/*     */   private static <T> void a(CommandNode<T> var0, Set<ArgumentType<?>> var1, Set<CommandNode<T>> var2) {
/* 238 */     if (!var2.add(var0)) {
/*     */       return;
/*     */     }
/*     */     
/* 242 */     if (var0 instanceof ArgumentCommandNode) {
/* 243 */       var1.add(((ArgumentCommandNode)var0).getType());
/*     */     }
/*     */     
/* 246 */     var0.getChildren().forEach(var2 -> a(var2, var0, var1));
/* 247 */     CommandNode<T> var3 = var0.getRedirect();
/* 248 */     if (var3 != null)
/* 249 */       a(var3, var1, var2); 
/*     */   }
/*     */   
/*     */   static class a<T extends ArgumentType<?>>
/*     */   {
/*     */     public final Class<T> a;
/*     */     public final ArgumentSerializer<T> b;
/*     */     public final MinecraftKey c;
/*     */     
/*     */     private a(Class<T> var0, ArgumentSerializer<T> var1, MinecraftKey var2) {
/* 259 */       this.a = var0;
/* 260 */       this.b = var1;
/* 261 */       this.c = var2;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */