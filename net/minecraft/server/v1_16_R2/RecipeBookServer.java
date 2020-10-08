/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Consumer;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public class RecipeBookServer
/*     */   extends RecipeBook {
/*  16 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */ 
/*     */ 
/*     */   
/*     */   public int a(Collection<IRecipe<?>> collection, EntityPlayer entityplayer) {
/*  21 */     List<MinecraftKey> list = Lists.newArrayList();
/*  22 */     int i = 0;
/*  23 */     Iterator<IRecipe<?>> iterator = collection.iterator();
/*     */     
/*  25 */     while (iterator.hasNext()) {
/*  26 */       IRecipe<?> irecipe = iterator.next();
/*  27 */       MinecraftKey minecraftkey = irecipe.getKey();
/*     */       
/*  29 */       if (!this.recipes.contains(minecraftkey) && !irecipe.isComplex() && CraftEventFactory.handlePlayerRecipeListUpdateEvent(entityplayer, minecraftkey)) {
/*  30 */         a(minecraftkey);
/*  31 */         d(minecraftkey);
/*  32 */         list.add(minecraftkey);
/*  33 */         CriterionTriggers.f.a(entityplayer, irecipe);
/*  34 */         i++;
/*     */       } 
/*     */     } 
/*     */     
/*  38 */     a(PacketPlayOutRecipes.Action.ADD, entityplayer, list);
/*  39 */     return i;
/*     */   }
/*     */   
/*     */   public int b(Collection<IRecipe<?>> collection, EntityPlayer entityplayer) {
/*  43 */     List<MinecraftKey> list = Lists.newArrayList();
/*  44 */     int i = 0;
/*  45 */     Iterator<IRecipe<?>> iterator = collection.iterator();
/*     */     
/*  47 */     while (iterator.hasNext()) {
/*  48 */       IRecipe<?> irecipe = iterator.next();
/*  49 */       MinecraftKey minecraftkey = irecipe.getKey();
/*     */       
/*  51 */       if (this.recipes.contains(minecraftkey)) {
/*  52 */         c(minecraftkey);
/*  53 */         list.add(minecraftkey);
/*  54 */         i++;
/*     */       } 
/*     */     } 
/*     */     
/*  58 */     a(PacketPlayOutRecipes.Action.REMOVE, entityplayer, list);
/*  59 */     return i;
/*     */   }
/*     */   
/*     */   private void a(PacketPlayOutRecipes.Action packetplayoutrecipes_action, EntityPlayer entityplayer, List<MinecraftKey> list) {
/*  63 */     if (entityplayer.playerConnection == null)
/*  64 */       return;  entityplayer.playerConnection.sendPacket(new PacketPlayOutRecipes(packetplayoutrecipes_action, list, Collections.emptyList(), a()));
/*     */   }
/*     */   
/*     */   public NBTTagCompound save() {
/*  68 */     NBTTagCompound nbttagcompound = new NBTTagCompound();
/*     */     
/*  70 */     a().b(nbttagcompound);
/*  71 */     NBTTagList nbttaglist = new NBTTagList();
/*  72 */     Iterator<MinecraftKey> iterator = this.recipes.iterator();
/*     */     
/*  74 */     while (iterator.hasNext()) {
/*  75 */       MinecraftKey minecraftkey = iterator.next();
/*     */       
/*  77 */       nbttaglist.add(NBTTagString.a(minecraftkey.toString()));
/*     */     } 
/*     */     
/*  80 */     nbttagcompound.set("recipes", nbttaglist);
/*  81 */     NBTTagList nbttaglist1 = new NBTTagList();
/*  82 */     Iterator<MinecraftKey> iterator1 = this.toBeDisplayed.iterator();
/*     */     
/*  84 */     while (iterator1.hasNext()) {
/*  85 */       MinecraftKey minecraftkey1 = iterator1.next();
/*     */       
/*  87 */       nbttaglist1.add(NBTTagString.a(minecraftkey1.toString()));
/*     */     } 
/*     */     
/*  90 */     nbttagcompound.set("toBeDisplayed", nbttaglist1);
/*  91 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound, CraftingManager craftingmanager) {
/*  95 */     a(RecipeBookSettings.a(nbttagcompound));
/*  96 */     NBTTagList nbttaglist = nbttagcompound.getList("recipes", 8);
/*     */     
/*  98 */     a(nbttaglist, this::a, craftingmanager);
/*  99 */     NBTTagList nbttaglist1 = nbttagcompound.getList("toBeDisplayed", 8);
/*     */     
/* 101 */     a(nbttaglist1, this::f, craftingmanager);
/*     */   }
/*     */   
/*     */   private void a(NBTTagList nbttaglist, Consumer<IRecipe<?>> consumer, CraftingManager craftingmanager) {
/* 105 */     for (int i = 0; i < nbttaglist.size(); i++) {
/* 106 */       String s = nbttaglist.getString(i);
/*     */       
/*     */       try {
/* 109 */         MinecraftKey minecraftkey = new MinecraftKey(s);
/* 110 */         Optional<? extends IRecipe<?>> optional = craftingmanager.getRecipe(minecraftkey);
/*     */         
/* 112 */         if (!optional.isPresent()) {
/* 113 */           LOGGER.error("Tried to load unrecognized recipe: {} removed now.", minecraftkey);
/*     */         } else {
/* 115 */           consumer.accept(optional.get());
/*     */         } 
/* 117 */       } catch (ResourceKeyInvalidException resourcekeyinvalidexception) {
/* 118 */         LOGGER.error("Tried to load improperly formatted recipe: {} removed now.", s);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(EntityPlayer entityplayer) {
/* 125 */     entityplayer.playerConnection.sendPacket(new PacketPlayOutRecipes(PacketPlayOutRecipes.Action.INIT, this.recipes, this.toBeDisplayed, a()));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RecipeBookServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */