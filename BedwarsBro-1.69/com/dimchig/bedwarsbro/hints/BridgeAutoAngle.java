/*    */ package com.dimchig.bedwarsbro.hints;
/*    */ 
/*    */ import com.dimchig.bedwarsbro.ChatSender;
/*    */ import com.dimchig.bedwarsbro.Main;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityPlayerSP;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ 
/*    */ public class BridgeAutoAngle
/*    */ {
/* 12 */   public static float rotation_godbridge_pitch = 75.0F;
/*    */   public static boolean prop_show_chat_messages = true;
/* 14 */   public static String mod_prefix = "&6&lAutoAngle: &r";
/*    */   
/*    */   public static void aim() {
/* 17 */     rotation_godbridge_pitch = (float)Main.getConfigDouble(Main.CONFIG_MSG.BRIDGE_AUTOANGLE_PITCH);
/* 18 */     prop_show_chat_messages = Main.getConfigBool(Main.CONFIG_MSG.BRIDGE_AUTOANGLE_MESSAGES);
/*    */     
/* 20 */     EntityPlayerSP player = (Minecraft.func_71410_x()).field_71439_g;
/*    */     
/* 22 */     if (Math.abs(player.field_70125_A - rotation_godbridge_pitch) > 10.0F) {
/* 23 */       if (prop_show_chat_messages == true) {
/* 24 */         ChatSender.addText(mod_prefix + "&cПосмотри на угол блока!");
/*    */       }
/*    */       
/*    */       return;
/*    */     } 
/* 29 */     float[] directions = { 45.0F, -45.0F, 135.0F, -135.0F, 225.0F, -225.0F, 315.0F, -315.0F };
/*    */     
/* 31 */     float min_dist = 1000.0F;
/* 32 */     float direction = 0.0F;
/*    */     
/* 34 */     for (float dir : directions) {
/* 35 */       float dist = Math.abs(getPlayerYaw((Entity)player) - dir);
/* 36 */       if (dist < min_dist) {
/* 37 */         min_dist = dist;
/* 38 */         direction = dir;
/*    */       } 
/*    */     } 
/*    */     
/* 42 */     if (direction == 0.0F && prop_show_chat_messages == true) {
/*    */       return;
/*    */     }
/*    */     
/* 46 */     rotateTo((Entity)player, direction, rotation_godbridge_pitch);
/*    */   }
/*    */   
/*    */   public static float getPlayerYaw(Entity player) {
/* 50 */     return player.field_70177_z % 360.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void rotateTo(Entity player, float target_angle_yaw, float target_angle_pitch) {
/* 55 */     float prev_rot_yaw = player.field_70177_z;
/* 56 */     float prev_rot_pitch = player.field_70125_A;
/*    */     
/* 58 */     float angle_yaw = target_angle_yaw - prev_rot_yaw;
/* 59 */     float angle_pitch = target_angle_pitch - prev_rot_pitch;
/*    */     
/* 61 */     rotateAngles(player, angle_yaw, angle_pitch);
/*    */     
/* 63 */     double delta_yaw = (player.field_70177_z - prev_rot_yaw);
/* 64 */     double delta_pitch = (player.field_70125_A - prev_rot_pitch);
/*    */     
/* 66 */     if (target_angle_yaw != player.field_70177_z || target_angle_pitch != player.field_70125_A) {
/* 67 */       rotateTo(player, target_angle_yaw, target_angle_pitch);
/*    */     }
/* 69 */     else if (prop_show_chat_messages == true) {
/* 70 */       int yaw = (int)target_angle_yaw % 180;
/* 71 */       ChatSender.addText(mod_prefix + "&fУгол установлен в (&b" + yaw + ", " + target_angle_pitch + "&f)");
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void rotateAngles(Entity player, float angle_yaw, float angle_pitch) {
/* 77 */     player.func_70082_c(angle_yaw / 0.15F, angle_pitch / -0.15F);
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\hints\BridgeAutoAngle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */