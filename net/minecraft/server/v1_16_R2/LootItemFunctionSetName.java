/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.util.Set;
/*     */ import java.util.function.UnaryOperator;
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
/*     */ public class LootItemFunctionSetName
/*     */   extends LootItemFunctionConditional
/*     */ {
/*  26 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   private final IChatBaseComponent b;
/*     */   
/*     */   @Nullable
/*     */   private final LootTableInfo.EntityTarget d;
/*     */   
/*     */   private LootItemFunctionSetName(LootItemCondition[] var0, @Nullable IChatBaseComponent var1, @Nullable LootTableInfo.EntityTarget var2) {
/*  34 */     super(var0);
/*  35 */     this.b = var1;
/*  36 */     this.d = var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public LootItemFunctionType b() {
/*  41 */     return LootItemFunctions.j;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<LootContextParameter<?>> a() {
/*  46 */     return (this.d != null) ? (Set<LootContextParameter<?>>)ImmutableSet.of(this.d.a()) : (Set<LootContextParameter<?>>)ImmutableSet.of();
/*     */   }
/*     */   
/*     */   public static UnaryOperator<IChatBaseComponent> a(LootTableInfo var0, @Nullable LootTableInfo.EntityTarget var1) {
/*  50 */     if (var1 != null) {
/*  51 */       Entity var2 = var0.<Entity>getContextParameter((LootContextParameter)var1.a());
/*  52 */       if (var2 != null) {
/*     */ 
/*     */         
/*  55 */         CommandListenerWrapper var3 = var2.getCommandListener().a(2);
/*  56 */         return var2 -> {
/*     */             try {
/*     */               return ChatComponentUtils.filterForDisplay(var0, var2, var1, 0);
/*  59 */             } catch (CommandSyntaxException var3) {
/*     */               LOGGER.warn("Failed to resolve text component", (Throwable)var3);
/*     */               return var2;
/*     */             } 
/*     */           };
/*     */       } 
/*     */     } 
/*  66 */     return var0 -> var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(ItemStack var0, LootTableInfo var1) {
/*  71 */     if (this.b != null) {
/*  72 */       var0.a(a(var1, this.d).apply(this.b));
/*     */     }
/*  74 */     return var0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class a
/*     */     extends LootItemFunctionConditional.c<LootItemFunctionSetName>
/*     */   {
/*     */     public void a(JsonObject var0, LootItemFunctionSetName var1, JsonSerializationContext var2) {
/*  88 */       super.a(var0, var1, var2);
/*     */       
/*  90 */       if (LootItemFunctionSetName.a(var1) != null) {
/*  91 */         var0.add("name", IChatBaseComponent.ChatSerializer.b(LootItemFunctionSetName.a(var1)));
/*     */       }
/*     */       
/*  94 */       if (LootItemFunctionSetName.b(var1) != null) {
/*  95 */         var0.add("entity", var2.serialize(LootItemFunctionSetName.b(var1)));
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public LootItemFunctionSetName b(JsonObject var0, JsonDeserializationContext var1, LootItemCondition[] var2) {
/* 101 */       IChatBaseComponent var3 = IChatBaseComponent.ChatSerializer.a(var0.get("name"));
/* 102 */       LootTableInfo.EntityTarget var4 = ChatDeserializer.<LootTableInfo.EntityTarget>a(var0, "entity", null, var1, LootTableInfo.EntityTarget.class);
/* 103 */       return new LootItemFunctionSetName(var2, var3, var4);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemFunctionSetName.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */