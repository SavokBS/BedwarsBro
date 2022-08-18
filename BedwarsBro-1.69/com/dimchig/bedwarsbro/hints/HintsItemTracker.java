/*    */ package com.dimchig.bedwarsbro.hints;
/*    */ 
/*    */ import com.dimchig.bedwarsbro.Main;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HintsItemTracker
/*    */ {
/*    */   public void scan() {
/* 13 */     ItemStack[] items = (Minecraft.func_71410_x()).field_71439_g.field_71071_by.field_70462_a;
/*    */     
/* 15 */     int cnt_emeralds = 0;
/* 16 */     int cnt_diamonds = 0;
/*    */     
/* 18 */     for (ItemStack item : items) {
/* 19 */       if (item != null) {
/*    */         
/* 21 */         if (item.func_77977_a().equals("item.diamond")) cnt_diamonds += item.field_77994_a; 
/* 22 */         if (item.func_77977_a().equals("item.emerald")) cnt_emeralds += item.field_77994_a; 
/*    */       } 
/*    */     } 
/* 25 */     Main.guiOnScreen.setDiamonds(cnt_diamonds);
/* 26 */     Main.guiOnScreen.setEmeralds(cnt_emeralds);
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\hints\HintsItemTracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */