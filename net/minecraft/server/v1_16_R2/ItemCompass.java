/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Optional;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemCompass
/*     */   extends Item
/*     */   implements ItemVanishable
/*     */ {
/*  24 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemCompass(Item.Info var0) {
/*  31 */     super(var0);
/*     */   }
/*     */   
/*     */   public static boolean d(ItemStack var0) {
/*  35 */     NBTTagCompound var1 = var0.getTag();
/*  36 */     return (var1 != null && (var1.hasKey("LodestoneDimension") || var1.hasKey("LodestonePos")));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean e(ItemStack var0) {
/*  41 */     return (d(var0) || super.e(var0));
/*     */   }
/*     */   
/*     */   public static Optional<ResourceKey<World>> a(NBTTagCompound var0) {
/*  45 */     return World.f.parse(DynamicOpsNBT.a, var0.get("LodestoneDimension")).result();
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(ItemStack var0, World var1, Entity var2, int var3, boolean var4) {
/*  50 */     if (var1.isClientSide) {
/*     */       return;
/*     */     }
/*     */     
/*  54 */     if (d(var0)) {
/*  55 */       NBTTagCompound var5 = var0.getOrCreateTag();
/*  56 */       if (var5.hasKey("LodestoneTracked") && !var5.getBoolean("LodestoneTracked")) {
/*     */         return;
/*     */       }
/*     */       
/*  60 */       Optional<ResourceKey<World>> var6 = a(var5);
/*  61 */       if (var6.isPresent() && var6.get() == var1.getDimensionKey() && var5.hasKey("LodestonePos") && 
/*  62 */         !((WorldServer)var1).y().a(VillagePlaceType.w, GameProfileSerializer.b(var5.getCompound("LodestonePos")))) {
/*  63 */         var5.remove("LodestonePos");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumInteractionResult a(ItemActionContext var0) {
/*  71 */     BlockPosition var1 = var0.getClickPosition();
/*  72 */     World var2 = var0.getWorld();
/*     */     
/*  74 */     if (var2.getType(var1).a(Blocks.LODESTONE)) {
/*  75 */       var2.playSound((EntityHuman)null, var1, SoundEffects.ITEM_LODESTONE_COMPASS_LOCK, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*     */       
/*  77 */       EntityHuman var3 = var0.getEntity();
/*  78 */       ItemStack var4 = var0.getItemStack();
/*  79 */       boolean var5 = (!var3.abilities.canInstantlyBuild && var4.getCount() == 1);
/*     */       
/*  81 */       if (var5) {
/*  82 */         a(var2.getDimensionKey(), var1, var4.getOrCreateTag());
/*     */       } else {
/*  84 */         ItemStack var6 = new ItemStack(Items.COMPASS, 1);
/*  85 */         NBTTagCompound var7 = var4.hasTag() ? var4.getTag().clone() : new NBTTagCompound();
/*  86 */         var6.setTag(var7);
/*  87 */         if (!var3.abilities.canInstantlyBuild) {
/*  88 */           var4.subtract(1);
/*     */         }
/*  90 */         a(var2.getDimensionKey(), var1, var7);
/*  91 */         if (!var3.inventory.pickup(var6)) {
/*  92 */           var3.drop(var6, false);
/*     */         }
/*     */       } 
/*     */       
/*  96 */       return EnumInteractionResult.a(var2.isClientSide);
/*     */     } 
/*  98 */     return super.a(var0);
/*     */   }
/*     */   
/*     */   private void a(ResourceKey<World> var0, BlockPosition var1, NBTTagCompound var2) {
/* 102 */     var2.set("LodestonePos", GameProfileSerializer.a(var1));
/* 103 */     World.f.encodeStart(DynamicOpsNBT.a, var0).resultOrPartial(LOGGER::error).ifPresent(var1 -> var0.set("LodestoneDimension", var1));
/* 104 */     var2.setBoolean("LodestoneTracked", true);
/*     */   }
/*     */ 
/*     */   
/*     */   public String f(ItemStack var0) {
/* 109 */     return d(var0) ? "item.minecraft.lodestone_compass" : super.f(var0);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemCompass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */