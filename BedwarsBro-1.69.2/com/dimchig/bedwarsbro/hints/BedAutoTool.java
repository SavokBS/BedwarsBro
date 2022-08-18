/*    */ package com.dimchig.bedwarsbro.hints;
/*    */ 
/*    */ import com.dimchig.bedwarsbro.gui.GuiMinimap;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import org.lwjgl.input.Mouse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BedAutoTool
/*    */ {
/*    */   static Minecraft mc;
/*    */   public boolean isActive = false;
/*    */   
/*    */   public BedAutoTool() {
/* 27 */     mc = Minecraft.func_71410_x();
/* 28 */     updateBooleans();
/*    */   }
/*    */   
/*    */   public void updateBooleans() {
/* 32 */     this.isActive = HintsValidator.isBedAutoToolActive();
/*    */   }
/*    */   
/*    */   public void handleTools() {
/* 36 */     if (!this.isActive)
/* 37 */       return;  if (!Mouse.isButtonDown(0))
/* 38 */       return;  MovingObjectPosition obj = mc.field_71476_x;
/* 39 */     if (obj == null || obj.field_72313_a != MovingObjectPosition.MovingObjectType.BLOCK)
/* 40 */       return;  IBlockState state = mc.field_71441_e.func_180495_p(obj.func_178782_a());
/* 41 */     if (state == null || state.func_177230_c() == null)
/* 42 */       return;  ItemStack is = mc.field_71439_g.func_71045_bC();
/* 43 */     if (is == null || is.func_77973_b() == null)
/* 44 */       return;  String name = is.func_77973_b().func_77658_a();
/* 45 */     if (!name.contains("pickaxe") && !name.contains("hatchet") && !name.contains("shears"))
/*    */       return; 
/* 47 */     double min_dist = 99999.0D;
/* 48 */     for (GuiMinimap.MyBed bed : GuiMinimap.bedsFound) {
/* 49 */       double dist = Math.sqrt(Math.pow(mc.field_71439_g.field_70165_t - bed.pos.func_177958_n(), 2.0D) + Math.pow(mc.field_71439_g.field_70161_v - bed.pos.func_177952_p(), 2.0D));
/* 50 */       if (dist < min_dist) min_dist = dist;
/*    */     
/*    */     } 
/* 53 */     if (min_dist > 10.0D)
/*    */       return; 
/* 55 */     Block block = state.func_177230_c();
/*    */ 
/*    */     
/* 58 */     Block[] available_blocks = { Blocks.field_150325_L, Blocks.field_150344_f, Blocks.field_150468_ap, Blocks.field_150406_ce, Blocks.field_150377_bs, Blocks.field_150343_Z };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 67 */     boolean isFound = false;
/* 68 */     for (Block b : available_blocks) {
/* 69 */       if (b == block) {
/* 70 */         isFound = true;
/*    */         break;
/*    */       } 
/*    */     } 
/* 74 */     if (!isFound)
/*    */       return; 
/* 76 */     int pickaxe_slot = -1;
/* 77 */     int axe_slot = -1;
/* 78 */     int shears_slot = -1;
/*    */     
/* 80 */     for (int i = 0; i < InventoryPlayer.func_70451_h(); i++) {
/* 81 */       ItemStack stack = mc.field_71439_g.field_71071_by.field_70462_a[i];
/* 82 */       if (stack != null) {
/* 83 */         Item item = stack.func_77973_b();
/* 84 */         if (item != null && 
/* 85 */           stack.func_82833_r() != null)
/* 86 */         { if (item.func_77658_a().contains("pickaxe")) pickaxe_slot = i; 
/* 87 */           if (item.func_77658_a().contains("hatchet")) axe_slot = i; 
/* 88 */           if (item.func_77658_a().contains("shears")) shears_slot = i;  } 
/*    */       } 
/* 90 */     }  int slot = -1;
/* 91 */     if (shears_slot != -1 && block == Blocks.field_150325_L) { slot = shears_slot; }
/* 92 */     else if (pickaxe_slot != -1 && (block == Blocks.field_150377_bs || block == Blocks.field_150343_Z || block == Blocks.field_150406_ce)) { slot = pickaxe_slot; }
/* 93 */     else if (axe_slot != -1 && (block == Blocks.field_150344_f || block == Blocks.field_150468_ap)) { slot = axe_slot; }
/* 94 */      if (slot == -1)
/*    */       return; 
/* 96 */     mc.field_71439_g.field_71071_by.field_70461_c = slot;
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\hints\BedAutoTool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */