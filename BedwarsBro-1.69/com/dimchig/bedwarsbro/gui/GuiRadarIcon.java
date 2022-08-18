/*    */ package com.dimchig.bedwarsbro.gui;
/*    */ 
/*    */ import com.dimchig.bedwarsbro.hints.HintsValidator;
/*    */ import java.util.Date;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.Gui;
/*    */ import net.minecraft.client.gui.ScaledResolution;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuiRadarIcon
/*    */   extends Gui
/*    */ {
/*    */   public static boolean isActive = false;
/* 21 */   private ResourceLocation resourceLoc_other = new ResourceLocation("bedwarschatmod:textures/gui/other.png");
/*    */   
/*    */   private static Minecraft mc;
/*    */   private TextureManager textureManager;
/* 25 */   private long time_started = -1L;
/* 26 */   private int time_visible = 1500;
/*    */   private static boolean isBedIcon = true;
/*    */   
/*    */   public GuiRadarIcon() {
/* 30 */     mc = Minecraft.func_71410_x();
/* 31 */     this.textureManager = mc.func_110434_K();
/* 32 */     updateBooleans();
/*    */   }
/*    */   
/*    */   public static void updateBooleans() {
/* 36 */     isActive = HintsValidator.isRadarIconActive();
/*    */   }
/*    */   
/*    */   public void show(boolean b) {
/* 40 */     if (!isActive)
/* 41 */       return;  this.time_started = (new Date()).getTime();
/* 42 */     isBedIcon = b;
/*    */   }
/*    */ 
/*    */   
/*    */   public void draw() {
/* 47 */     if (!isActive)
/* 48 */       return;  if (this.time_started < 0L)
/* 49 */       return;  if (this.textureManager == null) this.textureManager = mc.func_110434_K();
/*    */     
/* 51 */     ScaledResolution sr = new ScaledResolution(mc);
/* 52 */     int screen_width = sr.func_78326_a();
/* 53 */     int screen_height = sr.func_78328_b();
/*    */     
/* 55 */     long t = (new Date()).getTime();
/*    */     
/* 57 */     if (t - this.time_started > this.time_visible) {
/* 58 */       this.time_started = -1L;
/*    */       
/*    */       return;
/*    */     } 
/* 62 */     float opacity = 1.0F;
/* 63 */     double dist = Math.abs(((float)(t - this.time_started) - this.time_visible / 2.0F) / this.time_visible);
/* 64 */     opacity = (float)Math.pow(1.0D - dist * 2.0D, 0.5D);
/*    */     
/* 66 */     GlStateManager.func_179094_E();
/* 67 */     GlStateManager.func_179109_b((screen_width / 2), 25.0F, 0.0F);
/* 68 */     float scale = 0.33F;
/* 69 */     GlStateManager.func_179152_a(scale, scale, scale);
/* 70 */     GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, opacity);
/*    */     
/* 72 */     mc.field_71446_o.func_110577_a(this.resourceLoc_other);
/*    */     
/* 74 */     if (isBedIcon) {
/* 75 */       func_73729_b(-88, -64, 0, 0, 176, 128);
/*    */     } else {
/* 77 */       func_73729_b(-64, -64, 0, 128, 128, 128);
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 82 */     GlStateManager.func_179121_F();
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\gui\GuiRadarIcon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */