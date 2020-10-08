/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.destroystokyo.paper.event.player.PlayerElytraBoostEvent;
/*    */ import java.util.Arrays;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class ItemFireworks extends Item {
/*    */   public ItemFireworks(Item.Info item_info) {
/*  9 */     super(item_info);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult a(ItemActionContext itemactioncontext) {
/* 14 */     World world = itemactioncontext.getWorld();
/*    */     
/* 16 */     if (!world.isClientSide) {
/* 17 */       ItemStack itemstack = itemactioncontext.getItemStack();
/* 18 */       Vec3D vec3d = itemactioncontext.getPos();
/* 19 */       EnumDirection enumdirection = itemactioncontext.getClickedFace();
/* 20 */       EntityFireworks entityfireworks = new EntityFireworks(world, itemactioncontext.getEntity(), vec3d.x + enumdirection.getAdjacentX() * 0.15D, vec3d.y + enumdirection.getAdjacentY() * 0.15D, vec3d.z + enumdirection.getAdjacentZ() * 0.15D, itemstack);
/* 21 */       entityfireworks.spawningEntity = itemactioncontext.getEntity().getUniqueID();
/*    */       
/* 23 */       world.addEntity(entityfireworks);
/* 24 */       itemstack.subtract(1);
/*    */     } 
/*    */     
/* 27 */     return EnumInteractionResult.a(world.isClientSide);
/*    */   }
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
/* 32 */     if (entityhuman.isGliding()) {
/* 33 */       ItemStack itemstack = entityhuman.b(enumhand);
/*    */       
/* 35 */       if (!world.isClientSide) {
/*    */         
/* 37 */         EntityFireworks entityfireworks = new EntityFireworks(world, itemstack, entityhuman);
/* 38 */         entityfireworks.spawningEntity = entityhuman.getUniqueID();
/*    */         
/* 40 */         PlayerElytraBoostEvent event = new PlayerElytraBoostEvent((Player)entityhuman.getBukkitEntity(), (ItemStack)CraftItemStack.asCraftMirror(itemstack), (Firework)entityfireworks.getBukkitEntity());
/* 41 */         if (event.callEvent() && world.addEntity(entityfireworks)) {
/* 42 */           if (event.shouldConsume() && !entityhuman.abilities.canInstantlyBuild)
/* 43 */           { itemstack.subtract(1); }
/* 44 */           else { ((EntityPlayer)entityhuman).getBukkitEntity().updateInventory(); } 
/* 45 */         } else if (entityhuman instanceof EntityPlayer) {
/* 46 */           ((EntityPlayer)entityhuman).getBukkitEntity().updateInventory();
/*    */         } 
/*    */       } 
/*    */ 
/*    */       
/* 51 */       return InteractionResultWrapper.a(entityhuman.b(enumhand), world.s_());
/*    */     } 
/* 53 */     return InteractionResultWrapper.pass(entityhuman.b(enumhand));
/*    */   }
/*    */ 
/*    */   
/*    */   public enum EffectType
/*    */   {
/* 59 */     SMALL_BALL(0, "small_ball"), LARGE_BALL(1, "large_ball"), STAR(2, "star"), CREEPER(3, "creeper"), BURST(4, "burst"); private final String h; private final int g;
/*    */     private static final EffectType[] f;
/*    */     
/*    */     static {
/* 63 */       f = (EffectType[])Arrays.<EffectType>stream(values()).sorted(Comparator.comparingInt(itemfireworks_effecttype -> itemfireworks_effecttype.g)).toArray(i -> new EffectType[i]);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     EffectType(int i, String s) {
/* 70 */       this.g = i;
/* 71 */       this.h = s;
/*    */     }
/*    */     
/*    */     public int a() {
/* 75 */       return this.g;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemFireworks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */