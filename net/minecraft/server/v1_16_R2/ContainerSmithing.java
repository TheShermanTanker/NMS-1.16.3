/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventorySmithing;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryView;
/*    */ import org.bukkit.entity.HumanEntity;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.InventoryView;
/*    */ 
/*    */ public class ContainerSmithing
/*    */   extends ContainerAnvilAbstract {
/*    */   private final World g;
/*    */   @Nullable
/*    */   private RecipeSmithing h;
/*    */   
/*    */   public ContainerSmithing(int i, PlayerInventory playerinventory) {
/* 19 */     this(i, playerinventory, ContainerAccess.a);
/*    */   }
/*    */   private final List<RecipeSmithing> i; private CraftInventoryView bukkitEntity;
/*    */   public ContainerSmithing(int i, PlayerInventory playerinventory, ContainerAccess containeraccess) {
/* 23 */     super(Containers.SMITHING, i, playerinventory, containeraccess);
/* 24 */     this.g = playerinventory.player.world;
/* 25 */     this.i = this.g.getCraftingManager().a(Recipes.SMITHING);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(IBlockData iblockdata) {
/* 30 */     return iblockdata.a(Blocks.SMITHING_TABLE);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean b(EntityHuman entityhuman, boolean flag) {
/* 35 */     return (this.h != null && this.h.a(this.repairInventory, this.g));
/*    */   }
/*    */ 
/*    */   
/*    */   protected ItemStack a(EntityHuman entityhuman, ItemStack itemstack) {
/* 40 */     itemstack.a(entityhuman.world, entityhuman, itemstack.getCount());
/* 41 */     this.resultInventory.b(entityhuman);
/* 42 */     d(0);
/* 43 */     d(1);
/* 44 */     this.containerAccess.a((world, blockposition) -> world.triggerEffect(1044, blockposition, 0));
/*    */ 
/*    */     
/* 47 */     return itemstack;
/*    */   }
/*    */   
/*    */   private void d(int i) {
/* 51 */     ItemStack itemstack = this.repairInventory.getItem(i);
/*    */     
/* 53 */     itemstack.subtract(1);
/* 54 */     this.repairInventory.setItem(i, itemstack);
/*    */   }
/*    */ 
/*    */   
/*    */   public void e() {
/* 59 */     List<RecipeSmithing> list = this.g.getCraftingManager().b(Recipes.SMITHING, this.repairInventory, this.g);
/*    */     
/* 61 */     if (list.isEmpty()) {
/* 62 */       CraftEventFactory.callPrepareSmithingEvent((InventoryView)getBukkitView(), ItemStack.b);
/*    */     } else {
/* 64 */       this.h = list.get(0);
/* 65 */       ItemStack itemstack = this.h.a(this.repairInventory);
/*    */       
/* 67 */       this.resultInventory.a(this.h);
/* 68 */       CraftEventFactory.callPrepareSmithingEvent((InventoryView)getBukkitView(), itemstack);
/*    */     } 
/*    */     
/* 71 */     CraftEventFactory.callPrepareResultEvent(this, 2);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(ItemStack itemstack) {
/* 76 */     return this.i.stream().anyMatch(recipesmithing -> recipesmithing.a(itemstack));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean a(ItemStack itemstack, Slot slot) {
/* 83 */     return (slot.inventory != this.resultInventory && super.a(itemstack, slot));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CraftInventoryView getBukkitView() {
/* 89 */     if (this.bukkitEntity != null) {
/* 90 */       return this.bukkitEntity;
/*    */     }
/*    */ 
/*    */     
/* 94 */     CraftInventorySmithing craftInventorySmithing = new CraftInventorySmithing(this.containerAccess.getLocation(), this.repairInventory, this.resultInventory);
/* 95 */     this.bukkitEntity = new CraftInventoryView((HumanEntity)this.player.getBukkitEntity(), (Inventory)craftInventorySmithing, this);
/* 96 */     return this.bukkitEntity;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ContainerSmithing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */