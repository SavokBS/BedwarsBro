/*    */ package com.dimchig.bedwarsbro.gui;
/*    */ 
/*    */ import com.dimchig.bedwarsbro.Main;
/*    */ import java.awt.Color;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.util.Vec3;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuiResourceHologram
/*    */ {
/*    */   static Minecraft mc;
/*    */   private int[] last_item_cnt;
/*    */   private int[] last_item_time_counter;
/*    */   
/*    */   public GuiResourceHologram() {
/* 26 */     this.last_item_cnt = new int[] { 0, 0, 0, 0 };
/* 27 */     this.last_item_time_counter = new int[] { 0, 0, 0, 0 };
/*    */     mc = Minecraft.func_71410_x();
/*    */   } public void draw(Vec3 playerPos) {
/* 30 */     List<Entity> items = mc.field_71441_e.field_72996_f;
/*    */     
/* 32 */     ArrayList<GuiMinimap.PosItem> my_items = new ArrayList<GuiMinimap.PosItem>();
/* 33 */     int total_iron = 0;
/* 34 */     int cnt_iron = 0;
/* 35 */     for (Entity en : items) {
/*    */       
/* 37 */       if (en instanceof EntityItem) {
/* 38 */         EntityItem itemEntity = (EntityItem)en;
/* 39 */         Item item = itemEntity.func_92059_d().func_77973_b();
/* 40 */         if (item == null)
/*    */           continue; 
/* 42 */         int item_type = -1;
/* 43 */         if (item == Items.field_151166_bC) {
/* 44 */           item_type = 0;
/* 45 */         } else if (item == Items.field_151045_i) {
/* 46 */           item_type = 1;
/* 47 */         } else if (item == Items.field_151043_k) {
/* 48 */           item_type = 2;
/* 49 */         } else if (item == Items.field_151042_j) {
/* 50 */           item_type = 3;
/*    */         } 
/* 52 */         int cnt = (itemEntity.func_92059_d()).field_77994_a;
/*    */         
/* 54 */         if (en.field_70128_L) {
/*    */           continue;
/*    */         }
/* 57 */         boolean isFound = false;
/* 58 */         for (GuiMinimap.PosItem p : my_items) {
/* 59 */           if (p.type != item_type)
/* 60 */             continue;  double dist = Math.sqrt(Math.pow(p.x - en.field_70165_t, 2.0D) + Math.pow(p.z - en.field_70161_v, 2.0D));
/* 61 */           if (dist < 3.0D) {
/*    */             
/* 63 */             p.cnt += cnt;
/*    */             
/* 65 */             isFound = true;
/*    */             break;
/*    */           } 
/*    */         } 
/* 69 */         if (!isFound) my_items.add(new GuiMinimap.PosItem(en.field_70165_t, en.field_70163_u, en.field_70161_v, item_type, cnt));
/*    */       
/*    */       } 
/*    */     } 
/* 73 */     for (GuiMinimap.PosItem item : my_items) {
/* 74 */       if (item.type < 0)
/* 75 */         continue;  Color color = new Color(1.0F, 1.0F, 1.0F, 1.0F);
/* 76 */       if (item.type == 3) {
/* 77 */         color = new Color(0.5F, 0.5F, 0.5F, 1.0F);
/* 78 */       } else if (item.type == 2) {
/* 79 */         color = new Color(1.0F, 0.85F, 0.0F, 1.0F);
/* 80 */       } else if (item.type == 1) {
/* 81 */         color = new Color(0.0F, 1.0F, 1.0F, 1.0F);
/* 82 */       } else if (item.type == 0) {
/* 83 */         color = new Color(0.0F, 1.0F, 0.0F, 1.0F);
/*    */       } 
/*    */       
/* 86 */       Main.draw3DText.drawText(playerPos, new Vec3(item.x, item.y + 1.5D, item.z), mc.field_71439_g, "" + item.cnt, color.getRGB());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\gui\GuiResourceHologram.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */