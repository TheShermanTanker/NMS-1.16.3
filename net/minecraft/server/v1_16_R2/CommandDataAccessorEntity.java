/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.builder.ArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*    */ import java.util.Locale;
/*    */ import java.util.UUID;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandDataAccessorEntity
/*    */   implements CommandDataAccessor
/*    */ {
/* 27 */   private static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("commands.data.entity.invalid")); public static final Function<String, CommandData.c> a;
/*    */   static {
/* 29 */     a = (var0 -> new CommandData.c(var0)
/*    */       {
/*    */         public CommandDataAccessor a(CommandContext<CommandListenerWrapper> var0) throws CommandSyntaxException {
/* 32 */           return new CommandDataAccessorEntity(ArgumentEntity.a(var0, this.a));
/*    */         }
/*    */ 
/*    */         
/*    */         public ArgumentBuilder<CommandListenerWrapper, ?> a(ArgumentBuilder var0, Function var1) {
/* 37 */           return var0.then(CommandDispatcher.a("entity").then((ArgumentBuilder)var1.apply(CommandDispatcher.a(this.a, ArgumentEntity.a()))));
/*    */         }
/*    */       });
/*    */   }
/*    */   private final Entity c;
/*    */   
/*    */   public CommandDataAccessorEntity(Entity var0) {
/* 44 */     this.c = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(NBTTagCompound var0) throws CommandSyntaxException {
/* 49 */     if (this.c instanceof EntityHuman) {
/* 50 */       throw b.create();
/*    */     }
/* 52 */     UUID var1 = this.c.getUniqueID();
/* 53 */     this.c.load(var0);
/* 54 */     this.c.a_(var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound a() {
/* 59 */     return CriterionConditionNBT.b(this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatBaseComponent b() {
/* 64 */     return new ChatMessage("commands.data.entity.modified", new Object[] { this.c.getScoreboardDisplayName() });
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatBaseComponent a(NBTBase var0) {
/* 69 */     return new ChatMessage("commands.data.entity.query", new Object[] { this.c.getScoreboardDisplayName(), var0.l() });
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatBaseComponent a(ArgumentNBTKey.h var0, double var1, int var3) {
/* 74 */     return new ChatMessage("commands.data.entity.get", new Object[] { var0, this.c.getScoreboardDisplayName(), String.format(Locale.ROOT, "%.2f", new Object[] { Double.valueOf(var1) }), Integer.valueOf(var3) });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandDataAccessorEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */