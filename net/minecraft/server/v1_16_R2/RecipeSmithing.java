/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftRecipe;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftSmithingRecipe;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftNamespacedKey;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.Recipe;
/*     */ 
/*     */ public class RecipeSmithing
/*     */   implements IRecipe<IInventory>
/*     */ {
/*     */   private final RecipeItemStack a;
/*     */   private final RecipeItemStack b;
/*     */   private final ItemStack c;
/*     */   private final MinecraftKey d;
/*     */   
/*     */   public RecipeSmithing(MinecraftKey minecraftkey, RecipeItemStack recipeitemstack, RecipeItemStack recipeitemstack1, ItemStack itemstack) {
/*  21 */     this.d = minecraftkey;
/*  22 */     this.a = recipeitemstack;
/*  23 */     this.b = recipeitemstack1;
/*  24 */     this.c = itemstack;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IInventory iinventory, World world) {
/*  29 */     return (this.a.test(iinventory.getItem(0)) && this.b.test(iinventory.getItem(1)));
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(IInventory iinventory) {
/*  34 */     ItemStack itemstack = this.c.cloneItemStack();
/*  35 */     NBTTagCompound nbttagcompound = iinventory.getItem(0).getTag();
/*     */     
/*  37 */     if (nbttagcompound != null) {
/*  38 */       itemstack.setTag(nbttagcompound.clone());
/*     */     }
/*     */     
/*  41 */     return itemstack;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getResult() {
/*  46 */     return this.c;
/*     */   }
/*     */   
/*     */   public boolean a(ItemStack itemstack) {
/*  50 */     return this.b.test(itemstack);
/*     */   }
/*     */ 
/*     */   
/*     */   public MinecraftKey getKey() {
/*  55 */     return this.d;
/*     */   }
/*     */ 
/*     */   
/*     */   public RecipeSerializer<?> getRecipeSerializer() {
/*  60 */     return RecipeSerializer.u;
/*     */   }
/*     */ 
/*     */   
/*     */   public Recipes<?> g() {
/*  65 */     return Recipes.SMITHING;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Recipe toBukkitRecipe() {
/*  71 */     CraftItemStack result = CraftItemStack.asCraftMirror(this.c);
/*     */     
/*  73 */     CraftSmithingRecipe recipe = new CraftSmithingRecipe(CraftNamespacedKey.fromMinecraft(this.d), (ItemStack)result, CraftRecipe.toBukkit(this.a), CraftRecipe.toBukkit(this.b));
/*     */     
/*  75 */     return (Recipe)recipe;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class a
/*     */     implements RecipeSerializer<RecipeSmithing>
/*     */   {
/*     */     public RecipeSmithing a(MinecraftKey minecraftkey, JsonObject jsonobject) {
/*  85 */       RecipeItemStack recipeitemstack = RecipeItemStack.a((JsonElement)ChatDeserializer.t(jsonobject, "base"));
/*  86 */       RecipeItemStack recipeitemstack1 = RecipeItemStack.a((JsonElement)ChatDeserializer.t(jsonobject, "addition"));
/*  87 */       ItemStack itemstack = ShapedRecipes.a(ChatDeserializer.t(jsonobject, "result"));
/*     */       
/*  89 */       return new RecipeSmithing(minecraftkey, recipeitemstack, recipeitemstack1, itemstack);
/*     */     }
/*     */ 
/*     */     
/*     */     public RecipeSmithing a(MinecraftKey minecraftkey, PacketDataSerializer packetdataserializer) {
/*  94 */       RecipeItemStack recipeitemstack = RecipeItemStack.b(packetdataserializer);
/*  95 */       RecipeItemStack recipeitemstack1 = RecipeItemStack.b(packetdataserializer);
/*  96 */       ItemStack itemstack = packetdataserializer.n();
/*     */       
/*  98 */       return new RecipeSmithing(minecraftkey, recipeitemstack, recipeitemstack1, itemstack);
/*     */     }
/*     */     
/*     */     public void a(PacketDataSerializer packetdataserializer, RecipeSmithing recipesmithing) {
/* 102 */       recipesmithing.a.a(packetdataserializer);
/* 103 */       recipesmithing.b.a(packetdataserializer);
/* 104 */       packetdataserializer.a(recipesmithing.c);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RecipeSmithing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */