/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.Message;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.DoubleArgumentType;
/*     */ import com.mojang.brigadier.arguments.StringArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
/*     */ import com.mojang.brigadier.exceptions.Dynamic3CommandExceptionType;
/*     */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*     */ import com.mojang.brigadier.suggestion.SuggestionProvider;
/*     */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.CompletableFuture;
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
/*     */ public class CommandAttribute
/*     */ {
/*     */   private static final SuggestionProvider<CommandListenerWrapper> a;
/*     */   private static final DynamicCommandExceptionType b;
/*     */   private static final Dynamic2CommandExceptionType c;
/*     */   private static final Dynamic3CommandExceptionType d;
/*     */   private static final Dynamic3CommandExceptionType e;
/*     */   
/*     */   static {
/*  38 */     a = ((var0, var1) -> ICompletionProvider.a(IRegistry.ATTRIBUTE.keySet(), var1));
/*     */     
/*  40 */     b = new DynamicCommandExceptionType(var0 -> new ChatMessage("commands.attribute.failed.entity", new Object[] { var0 }));
/*  41 */     c = new Dynamic2CommandExceptionType((var0, var1) -> new ChatMessage("commands.attribute.failed.no_attribute", new Object[] { var0, var1 }));
/*  42 */     d = new Dynamic3CommandExceptionType((var0, var1, var2) -> new ChatMessage("commands.attribute.failed.no_modifier", new Object[] { var1, var0, var2 }));
/*  43 */     e = new Dynamic3CommandExceptionType((var0, var1, var2) -> new ChatMessage("commands.attribute.failed.modifier_already_present", new Object[] { var2, var1, var0 }));
/*     */   }
/*     */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/*  46 */     var0.register(
/*  47 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("attribute")
/*  48 */         .requires(var0 -> var0.hasPermission(2)))
/*  49 */         .then(
/*  50 */           CommandDispatcher.<T>a("target", ArgumentEntity.a())
/*  51 */           .then((
/*  52 */             (RequiredArgumentBuilder)((RequiredArgumentBuilder)CommandDispatcher.<T>a("attribute", ArgumentMinecraftKeyRegistered.a())
/*  53 */             .suggests(a)
/*  54 */             .then((
/*  55 */               (LiteralArgumentBuilder)CommandDispatcher.a("get")
/*  56 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.a(var0, "target"), ArgumentMinecraftKeyRegistered.d(var0, "attribute"), 1.0D)))
/*  57 */               .then(
/*  58 */                 CommandDispatcher.<T>a("scale", (ArgumentType<T>)DoubleArgumentType.doubleArg())
/*  59 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.a(var0, "target"), ArgumentMinecraftKeyRegistered.d(var0, "attribute"), DoubleArgumentType.getDouble(var0, "scale"))))))
/*     */ 
/*     */             
/*  62 */             .then((
/*  63 */               (LiteralArgumentBuilder)CommandDispatcher.a("base")
/*  64 */               .then(
/*  65 */                 CommandDispatcher.a("set")
/*  66 */                 .then(
/*  67 */                   CommandDispatcher.<T>a("value", (ArgumentType<T>)DoubleArgumentType.doubleArg())
/*  68 */                   .executes(var0 -> c((CommandListenerWrapper)var0.getSource(), ArgumentEntity.a(var0, "target"), ArgumentMinecraftKeyRegistered.d(var0, "attribute"), DoubleArgumentType.getDouble(var0, "value"))))))
/*     */ 
/*     */               
/*  71 */               .then((
/*  72 */                 (LiteralArgumentBuilder)CommandDispatcher.a("get")
/*  73 */                 .executes(var0 -> b((CommandListenerWrapper)var0.getSource(), ArgumentEntity.a(var0, "target"), ArgumentMinecraftKeyRegistered.d(var0, "attribute"), 1.0D)))
/*  74 */                 .then(
/*  75 */                   CommandDispatcher.<T>a("scale", (ArgumentType<T>)DoubleArgumentType.doubleArg())
/*  76 */                   .executes(var0 -> b((CommandListenerWrapper)var0.getSource(), ArgumentEntity.a(var0, "target"), ArgumentMinecraftKeyRegistered.d(var0, "attribute"), DoubleArgumentType.getDouble(var0, "scale")))))))
/*     */ 
/*     */ 
/*     */             
/*  80 */             .then((
/*  81 */               (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("modifier")
/*  82 */               .then(
/*  83 */                 CommandDispatcher.a("add")
/*  84 */                 .then(
/*  85 */                   CommandDispatcher.<T>a("uuid", ArgumentUUID.a())
/*  86 */                   .then(
/*  87 */                     CommandDispatcher.<T>a("name", (ArgumentType<T>)StringArgumentType.string())
/*  88 */                     .then((
/*  89 */                       (RequiredArgumentBuilder)((RequiredArgumentBuilder)CommandDispatcher.<T>a("value", (ArgumentType<T>)DoubleArgumentType.doubleArg())
/*  90 */                       .then(
/*  91 */                         CommandDispatcher.a("add")
/*  92 */                         .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.a(var0, "target"), ArgumentMinecraftKeyRegistered.d(var0, "attribute"), ArgumentUUID.a(var0, "uuid"), StringArgumentType.getString(var0, "name"), DoubleArgumentType.getDouble(var0, "value"), AttributeModifier.Operation.ADDITION))))
/*     */                       
/*  94 */                       .then(
/*  95 */                         CommandDispatcher.a("multiply")
/*  96 */                         .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.a(var0, "target"), ArgumentMinecraftKeyRegistered.d(var0, "attribute"), ArgumentUUID.a(var0, "uuid"), StringArgumentType.getString(var0, "name"), DoubleArgumentType.getDouble(var0, "value"), AttributeModifier.Operation.MULTIPLY_TOTAL))))
/*     */                       
/*  98 */                       .then(
/*  99 */                         CommandDispatcher.a("multiply_base")
/* 100 */                         .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.a(var0, "target"), ArgumentMinecraftKeyRegistered.d(var0, "attribute"), ArgumentUUID.a(var0, "uuid"), StringArgumentType.getString(var0, "name"), DoubleArgumentType.getDouble(var0, "value"), AttributeModifier.Operation.MULTIPLY_BASE))))))))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 106 */               .then(
/* 107 */                 CommandDispatcher.a("remove")
/* 108 */                 .then(CommandDispatcher.<T>a("uuid", ArgumentUUID.a())
/* 109 */                   .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.a(var0, "target"), ArgumentMinecraftKeyRegistered.d(var0, "attribute"), ArgumentUUID.a(var0, "uuid"))))))
/*     */ 
/*     */               
/* 112 */               .then(
/* 113 */                 CommandDispatcher.a("value")
/* 114 */                 .then(
/* 115 */                   CommandDispatcher.a("get")
/* 116 */                   .then((
/* 117 */                     (RequiredArgumentBuilder)CommandDispatcher.<T>a("uuid", ArgumentUUID.a())
/* 118 */                     .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.a(var0, "target"), ArgumentMinecraftKeyRegistered.d(var0, "attribute"), ArgumentUUID.a(var0, "uuid"), 1.0D)))
/* 119 */                     .then(
/* 120 */                       CommandDispatcher.<T>a("scale", (ArgumentType<T>)DoubleArgumentType.doubleArg())
/* 121 */                       .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.a(var0, "target"), ArgumentMinecraftKeyRegistered.d(var0, "attribute"), ArgumentUUID.a(var0, "uuid"), DoubleArgumentType.getDouble(var0, "scale")))))))))));
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
/*     */   private static AttributeModifiable a(Entity var0, AttributeBase var1) throws CommandSyntaxException {
/* 133 */     AttributeModifiable var2 = a(var0).getAttributeMap().a(var1);
/* 134 */     if (var2 == null) {
/* 135 */       throw c.create(var0.getDisplayName(), new ChatMessage(var1.getName()));
/*     */     }
/* 137 */     return var2;
/*     */   }
/*     */   
/*     */   private static EntityLiving a(Entity var0) throws CommandSyntaxException {
/* 141 */     if (!(var0 instanceof EntityLiving)) {
/* 142 */       throw b.create(var0.getDisplayName());
/*     */     }
/* 144 */     return (EntityLiving)var0;
/*     */   }
/*     */   
/*     */   private static EntityLiving b(Entity var0, AttributeBase var1) throws CommandSyntaxException {
/* 148 */     EntityLiving var2 = a(var0);
/* 149 */     if (!var2.getAttributeMap().b(var1)) {
/* 150 */       throw c.create(var0.getDisplayName(), new ChatMessage(var1.getName()));
/*     */     }
/* 152 */     return var2;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, Entity var1, AttributeBase var2, double var3) throws CommandSyntaxException {
/* 156 */     EntityLiving var5 = b(var1, var2);
/* 157 */     double var6 = var5.b(var2);
/* 158 */     var0.sendMessage(new ChatMessage("commands.attribute.value.get.success", new Object[] { new ChatMessage(var2.getName()), var1.getDisplayName(), Double.valueOf(var6) }), false);
/* 159 */     return (int)(var6 * var3);
/*     */   }
/*     */   
/*     */   private static int b(CommandListenerWrapper var0, Entity var1, AttributeBase var2, double var3) throws CommandSyntaxException {
/* 163 */     EntityLiving var5 = b(var1, var2);
/* 164 */     double var6 = var5.c(var2);
/* 165 */     var0.sendMessage(new ChatMessage("commands.attribute.base_value.get.success", new Object[] { new ChatMessage(var2.getName()), var1.getDisplayName(), Double.valueOf(var6) }), false);
/* 166 */     return (int)(var6 * var3);
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, Entity var1, AttributeBase var2, UUID var3, double var4) throws CommandSyntaxException {
/* 170 */     EntityLiving var6 = b(var1, var2);
/*     */     
/* 172 */     AttributeMapBase var7 = var6.getAttributeMap();
/*     */     
/* 174 */     if (!var7.a(var2, var3)) {
/* 175 */       throw d.create(var1.getDisplayName(), new ChatMessage(var2.getName()), var3);
/*     */     }
/*     */     
/* 178 */     double var8 = var7.b(var2, var3);
/* 179 */     var0.sendMessage(new ChatMessage("commands.attribute.modifier.value.get.success", new Object[] { var3, new ChatMessage(var2.getName()), var1.getDisplayName(), Double.valueOf(var8) }), false);
/* 180 */     return (int)(var8 * var4);
/*     */   }
/*     */   
/*     */   private static int c(CommandListenerWrapper var0, Entity var1, AttributeBase var2, double var3) throws CommandSyntaxException {
/* 184 */     a(var1, var2).setValue(var3);
/* 185 */     var0.sendMessage(new ChatMessage("commands.attribute.base_value.set.success", new Object[] { new ChatMessage(var2.getName()), var1.getDisplayName(), Double.valueOf(var3) }), false);
/* 186 */     return 1;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, Entity var1, AttributeBase var2, UUID var3, String var4, double var5, AttributeModifier.Operation var7) throws CommandSyntaxException {
/* 190 */     AttributeModifiable var8 = a(var1, var2);
/* 191 */     AttributeModifier var9 = new AttributeModifier(var3, var4, var5, var7);
/* 192 */     if (var8.a(var9)) {
/* 193 */       throw e.create(var1.getDisplayName(), new ChatMessage(var2.getName()), var3);
/*     */     }
/* 195 */     var8.addModifier(var9);
/* 196 */     var0.sendMessage(new ChatMessage("commands.attribute.modifier.add.success", new Object[] { var3, new ChatMessage(var2.getName()), var1.getDisplayName() }), false);
/* 197 */     return 1;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, Entity var1, AttributeBase var2, UUID var3) throws CommandSyntaxException {
/* 201 */     AttributeModifiable var4 = a(var1, var2);
/* 202 */     if (var4.c(var3)) {
/* 203 */       var0.sendMessage(new ChatMessage("commands.attribute.modifier.remove.success", new Object[] { var3, new ChatMessage(var2.getName()), var1.getDisplayName() }), false);
/* 204 */       return 1;
/*     */     } 
/* 206 */     throw d.create(var1.getDisplayName(), new ChatMessage(var2.getName()), var3);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandAttribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */