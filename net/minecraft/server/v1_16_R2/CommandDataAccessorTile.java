/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.builder.ArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*    */ import java.util.Locale;
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
/*    */ public class CommandDataAccessorTile
/*    */   implements CommandDataAccessor
/*    */ {
/*    */   public static final Function<String, CommandData.c> a;
/* 27 */   private static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("commands.data.block.invalid"));
/*    */   static {
/* 29 */     a = (var0 -> new CommandData.c(var0)
/*    */       {
/*    */         public CommandDataAccessor a(CommandContext<CommandListenerWrapper> var0) throws CommandSyntaxException {
/* 32 */           BlockPosition var1 = ArgumentPosition.a(var0, this.a + "Pos");
/* 33 */           TileEntity var2 = ((CommandListenerWrapper)var0.getSource()).getWorld().getTileEntity(var1);
/* 34 */           if (var2 == null) {
/* 35 */             throw CommandDataAccessorTile.c().create();
/*    */           }
/* 37 */           return new CommandDataAccessorTile(var2, var1);
/*    */         }
/*    */ 
/*    */         
/*    */         public ArgumentBuilder<CommandListenerWrapper, ?> a(ArgumentBuilder var0, Function var1) {
/* 42 */           return var0.then(CommandDispatcher.a("block").then((ArgumentBuilder)var1.apply(CommandDispatcher.a(this.a + "Pos", ArgumentPosition.a()))));
/*    */         }
/*    */       });
/*    */   }
/*    */   private final TileEntity c;
/*    */   private final BlockPosition d;
/*    */   
/*    */   public CommandDataAccessorTile(TileEntity var0, BlockPosition var1) {
/* 50 */     this.c = var0;
/* 51 */     this.d = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(NBTTagCompound var0) {
/* 56 */     var0.setInt("x", this.d.getX());
/* 57 */     var0.setInt("y", this.d.getY());
/* 58 */     var0.setInt("z", this.d.getZ());
/* 59 */     IBlockData var1 = this.c.getWorld().getType(this.d);
/* 60 */     this.c.load(var1, var0);
/* 61 */     this.c.update();
/* 62 */     this.c.getWorld().notify(this.d, var1, var1, 3);
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound a() {
/* 67 */     return this.c.save(new NBTTagCompound());
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatBaseComponent b() {
/* 72 */     return new ChatMessage("commands.data.block.modified", new Object[] { Integer.valueOf(this.d.getX()), Integer.valueOf(this.d.getY()), Integer.valueOf(this.d.getZ()) });
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatBaseComponent a(NBTBase var0) {
/* 77 */     return new ChatMessage("commands.data.block.query", new Object[] { Integer.valueOf(this.d.getX()), Integer.valueOf(this.d.getY()), Integer.valueOf(this.d.getZ()), var0.l() });
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatBaseComponent a(ArgumentNBTKey.h var0, double var1, int var3) {
/* 82 */     return new ChatMessage("commands.data.block.get", new Object[] { var0, Integer.valueOf(this.d.getX()), Integer.valueOf(this.d.getY()), Integer.valueOf(this.d.getZ()), String.format(Locale.ROOT, "%.2f", new Object[] { Double.valueOf(var1) }), Integer.valueOf(var3) });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandDataAccessorTile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */