/*     */ package com.dimchig.bedwarsbro.particles;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.CustomScoreboard;
/*     */ import com.dimchig.bedwarsbro.Main;
/*     */ import com.dimchig.bedwarsbro.MyChatListener;
/*     */ import java.awt.Color;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityOtherPlayerMP;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import scala.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ParticlesAlwaysSharpness
/*     */ {
/*     */   private Minecraft mc;
/*     */   private boolean hasSharpness;
/*     */   private boolean flag = false;
/*     */   private boolean areCritsRemoved = false;
/*     */   
/*     */   public ParticlesAlwaysSharpness() {
/*  32 */     this.mc = Minecraft.func_71410_x();
/*  33 */     MinecraftForge.EVENT_BUS.register(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onMyLeftClick() {
/*  49 */     if (!ParticleController.isActive)
/*  50 */       return;  if (!this.areCritsRemoved) {
/*  51 */       this.areCritsRemoved = true;
/*  52 */       (Minecraft.func_71410_x()).field_71452_i.func_178929_a(9, null);
/*     */     } 
/*     */     
/*  55 */     if (!this.mc.field_71415_G || this.mc.field_71476_x == null)
/*  56 */       return;  attemptParticleSpawn();
/*     */   }
/*     */   
/*     */   private boolean shouldSpawnParticles() {
/*  60 */     Entity entity = this.mc.field_71476_x.field_72308_g;
/*  61 */     return (entity instanceof net.minecraft.entity.EntityLiving || (entity instanceof EntityOtherPlayerMP && ((EntityOtherPlayerMP)entity).func_70089_S()));
/*     */   }
/*     */   
/*     */   private void attemptParticleSpawn() {
/*     */     try {
/*  66 */       Entity ent = this.mc.field_71476_x.field_72308_g;
/*  67 */       if (!(ent instanceof EntityPlayer))
/*  68 */         return;  EntityPlayer en = (EntityPlayer)ent;
/*  69 */       float color_r = 1.0F;
/*  70 */       float color_g = 1.0F;
/*  71 */       float color_b = 1.0F;
/*  72 */       if (en == null || en.func_145748_c_() == null || en.func_70005_c_() == null)
/*     */         return; 
/*  74 */       CustomScoreboard.TEAM_COLOR team_color = MyChatListener.getEntityTeamColor(en);
/*     */       
/*  76 */       Random rnd = new Random();
/*  77 */       Color color = new Color(0, 0, 0);
/*  78 */       color_r = color.getRed() / 255.0F;
/*  79 */       color_g = color.getGreen() / 255.0F;
/*  80 */       color_b = color.getBlue() / 255.0F;
/*     */       
/*  82 */       if (team_color == CustomScoreboard.TEAM_COLOR.NONE) {
/*  83 */         color = Main.rainbowColorSynchronizer.getColor();
/*     */       } else {
/*  85 */         color = ParticleController.getParticleColorForTeam(team_color);
/*     */       } 
/*     */       
/*  88 */       color_r = color.getRed() / 255.0F;
/*  89 */       color_g = color.getGreen() / 255.0F;
/*  90 */       color_b = color.getBlue() / 255.0F;
/*     */ 
/*     */       
/*  93 */       for (int i = 0; i < 20; i++) {
/*  94 */         double x = this.mc.field_71476_x.field_72308_g.field_70165_t + (Math.random() - 0.5D) * 0.1D;
/*  95 */         double y = this.mc.field_71476_x.field_72308_g.field_70163_u + this.mc.field_71476_x.field_72308_g.func_70047_e() - 0.3D + (Math.random() - 0.5D) * 0.5D;
/*  96 */         double z = this.mc.field_71476_x.field_72308_g.field_70161_v + (Math.random() - 0.5D) * 0.1D;
/*  97 */         double speed = 0.2D - Math.random() * 0.1D;
/*  98 */         double xOffset = (Math.random() > 0.5D) ? speed : -speed;
/*  99 */         double yOffset = (Math.random() > 0.5D) ? speed : -speed;
/* 100 */         double zOffset = (Math.random() > 0.5D) ? speed : -speed;
/* 101 */         if (this.mc.field_71441_e.field_72995_K)
/*     */         {
/* 103 */           ParticleController.spawnColorParticleSharpness(x, y, z, xOffset, yOffset, zOffset, color_r, color_g, color_b);
/*     */         }
/*     */       }
/*     */     
/* 107 */     } catch (Exception exception) {}
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\particles\ParticlesAlwaysSharpness.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */