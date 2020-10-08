/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.Message;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
/*    */ import java.util.function.Predicate;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public class ArgumentPredicateItemStack implements Predicate<ItemStack> {
/*    */   private static final Dynamic2CommandExceptionType a;
/*    */   private final Item b;
/*    */   @Nullable
/*    */   private final NBTTagCompound c;
/*    */   
/*    */   static {
/* 16 */     a = new Dynamic2CommandExceptionType((var0, var1) -> new ChatMessage("arguments.item.overstacked", new Object[] { var0, var1 }));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ArgumentPredicateItemStack(Item var0, @Nullable NBTTagCompound var1) {
/* 23 */     this.b = var0;
/* 24 */     this.c = var1;
/*    */   }
/*    */   
/*    */   public Item a() {
/* 28 */     return this.b;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean test(ItemStack var0) {
/* 38 */     return (var0.getItem() == this.b && GameProfileSerializer.a(this.c, var0.getTag(), true));
/*    */   }
/*    */   
/*    */   public ItemStack a(int var0, boolean var1) throws CommandSyntaxException {
/* 42 */     ItemStack var2 = new ItemStack(this.b, var0);
/* 43 */     if (this.c != null) {
/* 44 */       var2.setTag(this.c);
/*    */     }
/* 46 */     if (var1 && var0 > var2.getMaxStackSize()) {
/* 47 */       throw a.create(IRegistry.ITEM.getKey(this.b), Integer.valueOf(var2.getMaxStackSize()));
/*    */     }
/* 49 */     return var2;
/*    */   }
/*    */   
/*    */   public String c() {
/* 53 */     StringBuilder var0 = new StringBuilder(IRegistry.ITEM.a(this.b));
/* 54 */     if (this.c != null) {
/* 55 */       var0.append(this.c);
/*    */     }
/* 57 */     return var0.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentPredicateItemStack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */