/*    */ package com.dimchig.bedwarsbro.particles;
/*    */ 
/*    */ import com.dimchig.bedwarsbro.CustomScoreboard;
/*    */ import com.dimchig.bedwarsbro.Main;
/*    */ import com.dimchig.bedwarsbro.MyChatListener;
/*    */ import com.dimchig.bedwarsbro.hints.HintsValidator;
/*    */ import java.awt.Color;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityPlayerSP;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ParticleTrail
/*    */ {
/* 23 */   public static ParticleController particleController = Main.particleController;
/*    */   
/*    */   public static boolean isRainbowAlways = false;
/*    */   Minecraft mc;
/*    */   
/*    */   public ParticleTrail() {
/* 29 */     updateBooleans();
/* 30 */     this.mc = Minecraft.func_71410_x();
/*    */   }
/*    */   
/*    */   public void updateBooleans() {
/* 34 */     this; isRainbowAlways = HintsValidator.isParticleTrailRainbowActive();
/*    */   }
/*    */   
/*    */   public void drawPlayerTrail() {
/* 38 */     EntityPlayerSP player = this.mc.field_71439_g;
/*    */     
/* 40 */     if (player.field_70125_A > 50.0F) {
/*    */       return;
/*    */     }
/* 43 */     double px = player.field_70142_S + 4.0D;
/* 44 */     double py = player.field_70137_T + player.func_70047_e() - this.mc.field_71441_e.field_73012_v.nextFloat() * 0.5D;
/* 45 */     double pz = player.field_70136_U;
/*    */     
/* 47 */     double angle = Math.toRadians((player.field_70177_z - 90.0F));
/*    */     
/* 49 */     double distance = 0.6D;
/* 50 */     px = (float)(distance * Math.cos(angle)) + px - 4.0D;
/* 51 */     pz = (float)(distance * Math.sin(angle)) + pz;
/* 52 */     if ((player.field_70159_w == 0.0D && player.field_70179_y == 0.0D) || player.field_70181_x < -2.0D)
/*    */       return; 
/* 54 */     Color color = new Color(0, 0, 0);
/* 55 */     float color_r = 0.0F;
/* 56 */     float color_g = 0.0F;
/* 57 */     float color_b = 0.0F;
/*    */     
/* 59 */     CustomScoreboard.TEAM_COLOR mod_team_color = MyChatListener.getEntityTeamColor((EntityPlayer)player);
/* 60 */     if (!isRainbowAlways && mod_team_color != CustomScoreboard.TEAM_COLOR.NONE) {
/*    */       
/* 62 */       color = ParticleController.getParticleColorForTeam(mod_team_color);
/*    */     } else {
/* 64 */       color = Main.rainbowColorSynchronizer.getColor();
/*    */     } 
/*    */ 
/*    */     
/* 68 */     color_r = color.getRed() / 255.0F;
/* 69 */     color_g = color.getGreen() / 255.0F;
/* 70 */     color_b = color.getBlue() / 255.0F;
/*    */ 
/*    */     
/* 73 */     ParticleController.spawnColorParticle(-1000.0D, 0.05D, px, py, pz, this.mc.field_71441_e.field_73012_v, color_r, color_g, color_b, false);
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\particles\ParticleTrail.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */