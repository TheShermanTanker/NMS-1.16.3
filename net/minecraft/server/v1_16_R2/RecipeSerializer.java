/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ 
/*    */ public interface RecipeSerializer<T extends IRecipe<?>>
/*    */ {
/*  9 */   public static final RecipeSerializer<ShapedRecipes> a = a("crafting_shaped", new ShapedRecipes.a());
/* 10 */   public static final RecipeSerializer<ShapelessRecipes> b = a("crafting_shapeless", new ShapelessRecipes.a());
/* 11 */   public static final RecipeSerializerComplex<RecipeArmorDye> c = a("crafting_special_armordye", new RecipeSerializerComplex<>(RecipeArmorDye::new));
/* 12 */   public static final RecipeSerializerComplex<RecipeBookClone> d = a("crafting_special_bookcloning", new RecipeSerializerComplex<>(RecipeBookClone::new));
/* 13 */   public static final RecipeSerializerComplex<RecipeMapClone> e = a("crafting_special_mapcloning", new RecipeSerializerComplex<>(RecipeMapClone::new));
/* 14 */   public static final RecipeSerializerComplex<RecipeMapExtend> f = a("crafting_special_mapextending", new RecipeSerializerComplex<>(RecipeMapExtend::new));
/* 15 */   public static final RecipeSerializerComplex<RecipeFireworks> g = a("crafting_special_firework_rocket", new RecipeSerializerComplex<>(RecipeFireworks::new));
/* 16 */   public static final RecipeSerializerComplex<RecipeFireworksStar> h = a("crafting_special_firework_star", new RecipeSerializerComplex<>(RecipeFireworksStar::new));
/* 17 */   public static final RecipeSerializerComplex<RecipeFireworksFade> i = a("crafting_special_firework_star_fade", new RecipeSerializerComplex<>(RecipeFireworksFade::new));
/* 18 */   public static final RecipeSerializerComplex<RecipeTippedArrow> j = a("crafting_special_tippedarrow", new RecipeSerializerComplex<>(RecipeTippedArrow::new));
/* 19 */   public static final RecipeSerializerComplex<RecipeBannerDuplicate> k = a("crafting_special_bannerduplicate", new RecipeSerializerComplex<>(RecipeBannerDuplicate::new));
/* 20 */   public static final RecipeSerializerComplex<RecipiesShield> l = a("crafting_special_shielddecoration", new RecipeSerializerComplex<>(RecipiesShield::new));
/* 21 */   public static final RecipeSerializerComplex<RecipeShulkerBox> m = a("crafting_special_shulkerboxcoloring", new RecipeSerializerComplex<>(RecipeShulkerBox::new));
/* 22 */   public static final RecipeSerializerComplex<RecipeSuspiciousStew> n = a("crafting_special_suspiciousstew", new RecipeSerializerComplex<>(RecipeSuspiciousStew::new));
/* 23 */   public static final RecipeSerializerComplex<RecipeRepair> o = a("crafting_special_repairitem", new RecipeSerializerComplex<>(RecipeRepair::new));
/* 24 */   public static final RecipeSerializerCooking<FurnaceRecipe> p = a("smelting", new RecipeSerializerCooking<>(FurnaceRecipe::new, 200));
/* 25 */   public static final RecipeSerializerCooking<RecipeBlasting> q = a("blasting", new RecipeSerializerCooking<>(RecipeBlasting::new, 100));
/* 26 */   public static final RecipeSerializerCooking<RecipeSmoking> r = a("smoking", new RecipeSerializerCooking<>(RecipeSmoking::new, 100));
/* 27 */   public static final RecipeSerializerCooking<RecipeCampfire> s = a("campfire_cooking", new RecipeSerializerCooking<>(RecipeCampfire::new, 100));
/* 28 */   public static final RecipeSerializer<RecipeStonecutting> t = a("stonecutting", new RecipeSingleItem.a<>(RecipeStonecutting::new));
/* 29 */   public static final RecipeSerializer<RecipeSmithing> u = a("smithing", new RecipeSmithing.a());
/*    */   
/*    */   T a(MinecraftKey paramMinecraftKey, JsonObject paramJsonObject);
/*    */   
/*    */   T a(MinecraftKey paramMinecraftKey, PacketDataSerializer paramPacketDataSerializer);
/*    */   
/*    */   void a(PacketDataSerializer paramPacketDataSerializer, T paramT);
/*    */   
/*    */   static <S extends RecipeSerializer<T>, T extends IRecipe<?>> S a(String var0, S var1) {
/* 38 */     return (S)IRegistry.<RecipeSerializer>a((IRegistry)IRegistry.RECIPE_SERIALIZER, var0, (RecipeSerializer)var1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RecipeSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */