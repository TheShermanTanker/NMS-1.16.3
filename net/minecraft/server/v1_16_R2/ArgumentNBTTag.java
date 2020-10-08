/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ 
/*    */ 
/*    */ public class ArgumentNBTTag
/*    */   implements ArgumentType<NBTTagCompound>
/*    */ {
/* 14 */   private static final Collection<String> a = Arrays.asList(new String[] { "{}", "{foo=bar}" });
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ArgumentNBTTag a() {
/* 20 */     return new ArgumentNBTTag();
/*    */   }
/*    */   
/*    */   public static <S> NBTTagCompound a(CommandContext<S> var0, String var1) {
/* 24 */     return (NBTTagCompound)var0.getArgument(var1, NBTTagCompound.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound parse(StringReader var0) throws CommandSyntaxException {
/* 29 */     return (new MojangsonParser(var0)).f();
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 34 */     return a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentNBTTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */