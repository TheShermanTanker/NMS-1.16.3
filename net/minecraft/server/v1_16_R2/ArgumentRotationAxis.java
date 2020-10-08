/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ import java.util.EnumSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArgumentRotationAxis
/*    */   implements ArgumentType<EnumSet<EnumDirection.EnumAxis>>
/*    */ {
/* 17 */   private static final Collection<String> a = Arrays.asList(new String[] { "xyz", "x" });
/* 18 */   private static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("arguments.swizzle.invalid"));
/*    */   
/*    */   public static ArgumentRotationAxis a() {
/* 21 */     return new ArgumentRotationAxis();
/*    */   }
/*    */ 
/*    */   
/*    */   public static EnumSet<EnumDirection.EnumAxis> a(CommandContext<CommandListenerWrapper> var0, String var1) {
/* 26 */     return (EnumSet<EnumDirection.EnumAxis>)var0.getArgument(var1, EnumSet.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumSet<EnumDirection.EnumAxis> parse(StringReader var0) throws CommandSyntaxException {
/* 31 */     EnumSet<EnumDirection.EnumAxis> var1 = EnumSet.noneOf(EnumDirection.EnumAxis.class);
/*    */     
/* 33 */     while (var0.canRead() && var0.peek() != ' ') {
/* 34 */       EnumDirection.EnumAxis var3; char var2 = var0.read();
/*    */ 
/*    */       
/* 37 */       switch (var2) {
/*    */         case 'x':
/* 39 */           var3 = EnumDirection.EnumAxis.X;
/*    */           break;
/*    */         case 'y':
/* 42 */           var3 = EnumDirection.EnumAxis.Y;
/*    */           break;
/*    */         case 'z':
/* 45 */           var3 = EnumDirection.EnumAxis.Z;
/*    */           break;
/*    */         default:
/* 48 */           throw b.create();
/*    */       } 
/*    */       
/* 51 */       if (var1.contains(var3)) {
/* 52 */         throw b.create();
/*    */       }
/* 54 */       var1.add(var3);
/*    */     } 
/*    */     
/* 57 */     return var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 62 */     return a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentRotationAxis.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */