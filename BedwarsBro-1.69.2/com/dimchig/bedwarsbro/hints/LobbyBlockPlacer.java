/*    */ package com.dimchig.bedwarsbro.hints;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.BlockStainedGlass;
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityPlayerSP;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.item.EnumDyeColor;
/*    */ import net.minecraft.util.BlockPos;
/*    */ 
/*    */ public class LobbyBlockPlacer {
/* 13 */   public static int block_idx = 0; public static boolean state;
/* 14 */   public static EnumDyeColor[] colors = new EnumDyeColor[] { EnumDyeColor.RED, EnumDyeColor.ORANGE, EnumDyeColor.YELLOW, EnumDyeColor.LIME, EnumDyeColor.LIGHT_BLUE, EnumDyeColor.BLUE, EnumDyeColor.PURPLE, EnumDyeColor.MAGENTA, EnumDyeColor.PINK };
/*    */ 
/*    */ 
/*    */   
/*    */   public static void place() {
/* 19 */     if (!state)
/* 20 */       return;  Minecraft mc = Minecraft.func_71410_x();
/* 21 */     if (mc == null || mc.field_71439_g == null)
/*    */       return; 
/* 23 */     EntityPlayerSP player = mc.field_71439_g;
/* 24 */     Random rnd = new Random();
/* 25 */     BlockPos pos = new BlockPos(player.field_70165_t, player.field_70163_u - 1.0D, player.field_70161_v);
/* 26 */     if ((Minecraft.func_71410_x()).field_71441_e.func_180495_p(pos).func_177230_c() != Blocks.field_150350_a)
/*    */       return; 
/* 28 */     block_idx = (block_idx + 1) % colors.length;
/* 29 */     (Minecraft.func_71410_x()).field_71441_e.func_175656_a(pos, Blocks.field_150325_L.func_176223_P().func_177226_a((IProperty)BlockStainedGlass.field_176547_a, (Comparable)colors[block_idx]));
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\hints\LobbyBlockPlacer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */