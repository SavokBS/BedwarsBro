/*    */ package com.dimchig.bedwarsbro.hints;
/*    */ 
/*    */ import com.dimchig.bedwarsbro.Main;
/*    */ import com.dimchig.bedwarsbro.MyChatListener;
/*    */ import java.awt.Color;
/*    */ import java.text.DecimalFormat;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityPlayerSP;
/*    */ import net.minecraft.client.gui.Gui;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.Vec3;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InvulnerableTime
/*    */   extends Gui
/*    */ {
/*    */   static Minecraft mc;
/* 26 */   public static int INVULNERABLE_TICKS_AMOUNT = 40;
/* 27 */   public static long time_last_sound = -1L;
/*    */   
/*    */   public InvulnerableTime() {
/* 30 */     mc = Minecraft.func_71410_x();
/*    */   }
/*    */   
/*    */   public static void scan(List<EntityPlayer> players, Vec3 pos, float partialTicks, boolean areSoundsActive) {
/* 34 */     EntityPlayerSP player = mc.field_71439_g;
/*    */     
/* 36 */     if (players.size() < 2 || player == null)
/*    */       return; 
/* 38 */     BWBed bed = MyChatListener.GAME_BED;
/* 39 */     if (bed != null) {
/* 40 */       double dist = player.func_70011_f(bed.part1_posX, bed.part1_posY, bed.part1_posZ);
/* 41 */       if (dist < 30.0D) {
/*    */         return;
/*    */       }
/*    */     } 
/*    */     
/* 46 */     for (EntityPlayer en : players) {
/*    */       
/* 48 */       if (en != player && en.func_96124_cp() != player.func_96124_cp() && en.field_70163_u <= 100.0D && 
/* 49 */         en.field_70173_aa <= INVULNERABLE_TICKS_AMOUNT) {
/* 50 */         double dist = Math.sqrt(Math.pow(player.field_70165_t - en.field_70165_t, 2.0D) + Math.pow(player.field_70161_v - en.field_70161_v, 2.0D));
/* 51 */         if (dist > 15.0D) {
/*    */           continue;
/*    */         }
/*    */         
/* 55 */         if (Main.shopManager.findItemInHotbar("Наблюдение за") != -1)
/*    */           return; 
/* 57 */         double x = en.field_70169_q + (en.field_70165_t - en.field_70169_q) * partialTicks;
/* 58 */         double y = en.field_70167_r + (en.field_70163_u - en.field_70167_r) * partialTicks;
/* 59 */         double z = en.field_70166_s + (en.field_70161_v - en.field_70166_s) * partialTicks;
/* 60 */         y += en.field_70131_O + 1.3D;
/*    */         
/* 62 */         if (areSoundsActive) {
/* 63 */           String sound_name = "note.hat";
/* 64 */           long t = (new Date()).getTime();
/* 65 */           if (t - time_last_sound > 100L) {
/* 66 */             if (en.field_70173_aa == 2) {
/* 67 */               mc.field_71441_e.func_72980_b(x, y, z, sound_name, 0.5F, 0.6F, false);
/* 68 */               time_last_sound = t;
/* 69 */             } else if (en.field_70173_aa == 20) {
/* 70 */               mc.field_71441_e.func_72980_b(x, y, z, sound_name, 0.75F, 1.0F, false);
/* 71 */               time_last_sound = t;
/* 72 */             } else if (en.field_70173_aa == 40) {
/* 73 */               mc.field_71441_e.func_72980_b(x, y, z, sound_name, 1.0F, 1.4F, false);
/* 74 */               time_last_sound = t;
/*    */             } 
/*    */           }
/*    */         } 
/*    */         
/* 79 */         int ticks_cnt = INVULNERABLE_TICKS_AMOUNT - en.field_70173_aa;
/* 80 */         String text = (new DecimalFormat("0.0")).format((ticks_cnt / 20.0F));
/* 81 */         drawText(pos, new Vec3(x, y, z), player, ticks_cnt, text);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   static void drawText(Vec3 pos, Vec3 text_pos, EntityPlayerSP player, float ticks_cnt, String text) {
/* 89 */     float green = Math.min((INVULNERABLE_TICKS_AMOUNT - ticks_cnt) / INVULNERABLE_TICKS_AMOUNT, 1.0F);
/* 90 */     Color color = new Color(1.0F - green, green, 0.0F);
/*    */     
/* 92 */     Main.draw3DText.drawText(pos, text_pos, player, text, color.getRGB());
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\hints\InvulnerableTime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */