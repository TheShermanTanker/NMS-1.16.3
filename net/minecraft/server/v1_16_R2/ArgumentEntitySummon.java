/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.Message;
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArgumentEntitySummon
/*    */   implements ArgumentType<MinecraftKey>
/*    */ {
/* 18 */   private static final Collection<String> b = Arrays.asList(new String[] { "minecraft:pig", "cow" }); static {
/* 19 */     a = new DynamicCommandExceptionType(var0 -> new ChatMessage("entity.notFound", new Object[] { var0 }));
/*    */   }
/*    */   
/*    */   public static final DynamicCommandExceptionType a;
/*    */   
/*    */   public static ArgumentEntitySummon a() {
/* 25 */     return new ArgumentEntitySummon();
/*    */   }
/*    */   
/*    */   public static MinecraftKey a(CommandContext<CommandListenerWrapper> var0, String var1) throws CommandSyntaxException {
/* 29 */     return a((MinecraftKey)var0.getArgument(var1, MinecraftKey.class));
/*    */   }
/*    */   
/*    */   private static MinecraftKey a(MinecraftKey var0) throws CommandSyntaxException {
/* 33 */     IRegistry.ENTITY_TYPE.getOptional(var0).filter(EntityTypes::b).orElseThrow(() -> a.create(var0));
/* 34 */     return var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public MinecraftKey parse(StringReader var0) throws CommandSyntaxException {
/* 39 */     return a(MinecraftKey.a(var0));
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 44 */     return b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentEntitySummon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */