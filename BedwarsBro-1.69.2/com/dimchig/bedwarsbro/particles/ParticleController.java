/*     */ package com.dimchig.bedwarsbro.particles;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.CustomScoreboard;
/*     */ import com.dimchig.bedwarsbro.hints.HintsValidator;
/*     */ import java.awt.Color;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ParticleController
/*     */ {
/*     */   static boolean isActive = false;
/*     */   
/*     */   public void updateBooleans() {
/*  18 */     isActive = HintsValidator.isParticlesActive();
/*     */   }
/*     */   
/*     */   public ParticleController() {
/*  22 */     updateBooleans();
/*     */   }
/*     */   
/*     */   public static void spawnFinalKillParticles(double posX, double posY, double posZ, CustomScoreboard.TEAM_COLOR team_color) {
/*  26 */     if (!isActive)
/*     */       return; 
/*  28 */     Random rand = new Random();
/*  29 */     int n = 150;
/*  30 */     float motion_randomness = 0.2F;
/*  31 */     float position_randomness = 0.1F;
/*     */ 
/*     */     
/*  34 */     Color color = getParticleColorForTeam(team_color);
/*  35 */     float color_r = color.getRed() / 255.0F;
/*  36 */     float color_g = color.getGreen() / 255.0F;
/*  37 */     float color_b = color.getBlue() / 255.0F;
/*     */     
/*  39 */     for (int i = 0; i < n; i++) {
/*  40 */       spawnColorParticle(motion_randomness, position_randomness, posX, posY, posZ, rand, color_r, color_g, color_b, false);
/*     */     }
/*     */   }
/*     */   
/*     */   public static Color getParticleColorForTeam(CustomScoreboard.TEAM_COLOR team_color) {
/*  45 */     if (team_color == CustomScoreboard.TEAM_COLOR.RED) return new Color(255, 0, 0); 
/*  46 */     if (team_color == CustomScoreboard.TEAM_COLOR.YELLOW) return new Color(255, 255, 0); 
/*  47 */     if (team_color == CustomScoreboard.TEAM_COLOR.GREEN) return new Color(0, 255, 0); 
/*  48 */     if (team_color == CustomScoreboard.TEAM_COLOR.AQUA) return new Color(0, 255, 255); 
/*  49 */     if (team_color == CustomScoreboard.TEAM_COLOR.BLUE) return new Color(0, 0, 255); 
/*  50 */     if (team_color == CustomScoreboard.TEAM_COLOR.PINK) return new Color(255, 0, 255); 
/*  51 */     if (team_color == CustomScoreboard.TEAM_COLOR.GRAY) return new Color(128, 128, 128); 
/*  52 */     if (team_color == CustomScoreboard.TEAM_COLOR.WHITE) return new Color(255, 255, 255); 
/*  53 */     return new Color(0, 0, 0);
/*     */   }
/*     */   
/*     */   public static void spawnGenDiamondParticles(double posX, double posY, double posZ, int cnt_diamonds) {
/*  57 */     if (!isActive)
/*     */       return; 
/*  59 */     Random rand = new Random();
/*  60 */     int n = 100 + 50 * cnt_diamonds;
/*  61 */     float motion_randomness = 0.2F;
/*  62 */     float position_randomness = 0.0F;
/*     */ 
/*     */     
/*  65 */     Color color = new Color(0, 255, 255);
/*  66 */     float color_r = color.getRed() / 255.0F;
/*  67 */     float color_g = color.getGreen() / 255.0F;
/*  68 */     float color_b = color.getBlue() / 255.0F;
/*     */     
/*  70 */     for (int i = 0; i < n; i++) {
/*  71 */       spawnColorParticle(motion_randomness, position_randomness, posX, posY, posZ, rand, color_r, color_g, color_b, true);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void spawnGenEmeraldParticles(double posX, double posY, double posZ, int cnt_emeralds) {
/*  76 */     if (!isActive)
/*     */       return; 
/*  78 */     Random rand = new Random();
/*  79 */     int n = 100 + 50 * cnt_emeralds;
/*  80 */     float motion_randomness = 0.3F;
/*  81 */     float position_randomness = 0.0F;
/*     */ 
/*     */     
/*  84 */     Color color = new Color(0, 255, 0);
/*  85 */     float color_r = color.getRed() / 255.0F;
/*  86 */     float color_g = color.getGreen() / 255.0F;
/*  87 */     float color_b = color.getBlue() / 255.0F;
/*     */     
/*  89 */     for (int i = 0; i < n; i++) {
/*  90 */       spawnColorParticle(motion_randomness, position_randomness, posX, posY, posZ, rand, color_r, color_g, color_b, true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void spawnColorParticle(double motion_rnd, double pos_rnd, double posX, double posY, double posZ, Random rand, float color_r, float color_g, float color_b, boolean isOnlyTop) {
/*  97 */     posX += rand.nextGaussian() * pos_rnd;
/*  98 */     posY += rand.nextGaussian() * pos_rnd;
/*  99 */     posZ += rand.nextGaussian() * pos_rnd;
/* 100 */     double motionX = rand.nextGaussian() * motion_rnd;
/* 101 */     double motionY = rand.nextGaussian() * motion_rnd;
/* 102 */     double motionZ = rand.nextGaussian() * motion_rnd;
/* 103 */     if (motion_rnd == -1000.0D) {
/* 104 */       motionX = -1000.0D;
/* 105 */       motionY = -1000.0D;
/* 106 */       motionZ = -1000.0D;
/*     */     } 
/*     */     
/* 109 */     if (isOnlyTop == true) motionY = Math.abs(motionY);
/*     */     
/* 111 */     spawnColorParticle(posX, posY, posZ, motionX, motionY, motionZ, color_r, color_g, color_b);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void spawnColorParticle(double posX, double posY, double posZ, double motionX, double motionY, double motionZ, float color_r, float color_g, float color_b) {
/* 117 */     ParticleFinalKillEffect pe = new ParticleFinalKillEffect((World)(Minecraft.func_71410_x()).field_71441_e, posX, posY, posZ);
/* 118 */     if (motionX != -1000.0D) pe.field_70159_w = motionX; 
/* 119 */     if (motionY != -1000.0D) pe.field_70181_x = motionY; 
/* 120 */     if (motionZ != -1000.0D) pe.field_70179_y = motionZ; 
/* 121 */     pe.func_70538_b(color_r, color_g, color_b);
/*     */     
/* 123 */     (Minecraft.func_71410_x()).field_71452_i.func_78873_a((EntityFX)pe);
/*     */   }
/*     */   
/*     */   public static void spawnColorParticleSharpness(double posX, double posY, double posZ, double motionX, double motionY, double motionZ, float color_r, float color_g, float color_b) {
/* 127 */     if (!isActive)
/*     */       return; 
/* 129 */     ParticleSharpnessEffect pe = new ParticleSharpnessEffect((World)(Minecraft.func_71410_x()).field_71441_e, posX, posY, posZ);
/* 130 */     pe.field_70159_w = motionX;
/* 131 */     pe.field_70181_x = motionY;
/* 132 */     pe.field_70179_y = motionZ;
/* 133 */     pe.func_70538_b(color_r, color_g, color_b);
/*     */     
/* 135 */     (Minecraft.func_71410_x()).field_71452_i.func_78873_a((EntityFX)pe);
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\particles\ParticleController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */