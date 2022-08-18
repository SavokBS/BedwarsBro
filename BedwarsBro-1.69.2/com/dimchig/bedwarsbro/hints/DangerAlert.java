/*    */ package com.dimchig.bedwarsbro.hints;
/*    */ 
/*    */ import com.dimchig.bedwarsbro.ChatSender;
/*    */ import com.dimchig.bedwarsbro.Main;
/*    */ import com.dimchig.bedwarsbro.MyChatListener;
/*    */ import com.dimchig.bedwarsbro.gui.GuiPlayerFocus;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityPlayerSP;
/*    */ import net.minecraft.client.multiplayer.WorldClient;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.util.Vec3;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DangerAlert
/*    */ {
/* 23 */   private static int danger_zone_radius = 5;
/* 24 */   private static int max_ray_distance = 100;
/* 25 */   private static long prev_sound_time = 0L;
/* 26 */   private static long sound_freq = 150L;
/* 27 */   private static long prev_message_time = 0L;
/* 28 */   private static long message_freq = 3000L;
/*    */   
/*    */   public void scan(ArrayList<HintsPlayerScanner.BWPlayer> players, EntityPlayerSP mod_player) {
/* 31 */     long t = (new Date()).getTime();
/* 32 */     WorldClient worldClient = (Minecraft.func_71410_x()).field_71441_e;
/* 33 */     Main.playerFocus.clearLines();
/* 34 */     for (HintsPlayerScanner.BWPlayer p : players) {
/* 35 */       if (!p.en.func_70005_c_().equals(mod_player.func_70005_c_()) && 
/* 36 */         mod_player.func_96124_cp() != p.en.func_96124_cp() && 
/* 37 */         p.item_in_hand != null && (
/* 38 */         p.item_in_hand.type == BWItemsHandler.BWItemType.BOW || p.item_in_hand.type == BWItemsHandler.BWItemType.FIREBALL)) {
/*    */         
/* 40 */         MovingObjectPosition ray = null;
/* 41 */         for (int i = 1; i < max_ray_distance; i++) {
/* 42 */           ray = p.en.func_174822_a(i, 1.0F);
/* 43 */           if (ray != null) {
/* 44 */             boolean isInDanger = isPlayerInDangerZone(mod_player, ray.field_72307_f.field_72450_a, ray.field_72307_f.field_72448_b, ray.field_72307_f.field_72449_c);
/* 45 */             if (isInDanger) {
/*    */               
/* 47 */               if (GuiPlayerFocus.STATE == true) {
/* 48 */                 Vec3 p1 = null;
/* 49 */                 Vec3 p2 = new Vec3(p.en.field_70165_t, p.en.field_70163_u + p.en.eyeHeight, p.en.field_70161_v);
/*    */                 
/* 51 */                 Main.playerFocus.addLine(p1, p2, Main.playerFocus.getColorByTeam(MyChatListener.getEntityTeamColor(p.en)));
/*    */               } 
/*    */ 
/*    */               
/* 55 */               if (t - prev_sound_time > sound_freq && Main.getConfigBool(Main.CONFIG_MSG.DANGER_ALERT_SOUND)) {
/* 56 */                 prev_sound_time = t;
/* 57 */                 float volume = mod_player.func_70032_d((Entity)p.en) / 12.0F;
/*    */                 
/* 59 */                 worldClient.func_72980_b(p.en.field_70165_t, p.en.field_70163_u + p.en.eyeHeight, p.en.field_70161_v, "note.pling", volume, 1.0F, false);
/*    */               } 
/* 61 */               if (t - prev_message_time > message_freq) {
/* 62 */                 prev_message_time = t;
/* 63 */                 if (p.item_in_hand.type == BWItemsHandler.BWItemType.BOW) {
/* 64 */                   ChatSender.addText(MyChatListener.PREFIX_DANGER_ALERT + "&fНа тебя целятся из &cЛУКА"); break;
/* 65 */                 }  if (p.item_in_hand.type == BWItemsHandler.BWItemType.FIREBALL) {
/* 66 */                   ChatSender.addText(MyChatListener.PREFIX_DANGER_ALERT + "&fНа тебя целятся &6ФАЕРБОЛОМ");
/*    */                 }
/*    */               } 
/*    */               break;
/*    */             } 
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   boolean isPlayerInDangerZone(EntityPlayerSP mod_player, double x, double y, double z) {
/* 78 */     double dist = Math.sqrt(Math.pow(mod_player.field_70165_t - x, 2.0D) + Math.pow(mod_player.field_70163_u + mod_player.func_70047_e() - y, 2.0D) + Math.pow(mod_player.field_70161_v - z, 2.0D));
/* 79 */     if (dist < danger_zone_radius) return true; 
/* 80 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\hints\DangerAlert.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */