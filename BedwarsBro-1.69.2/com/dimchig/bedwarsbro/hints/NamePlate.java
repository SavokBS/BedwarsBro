/*     */ package com.dimchig.bedwarsbro.hints;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.Main;
/*     */ import java.awt.Color;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.util.Vec3;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NamePlate
/*     */   extends Gui
/*     */ {
/*     */   static Minecraft mc;
/*     */   private static float color_idx;
/*     */   
/*     */   public NamePlate() {
/*  24 */     mc = Minecraft.func_71410_x();
/*     */   }
/*     */ 
/*     */   
/*     */   public void draw(Vec3 pos, boolean isRainbow, int rainbowSpeed, String constantColor) {
/*  29 */     if (mc == null || mc.field_71439_g == null)
/*  30 */       return;  int view_idx = mc.field_71474_y.field_74320_O;
/*  31 */     if (view_idx == 0)
/*     */       return; 
/*  33 */     double name_plate_scale = 0.03D;
/*     */     
/*  35 */     Vec3 text_pos = pos.func_178787_e(new Vec3(0.0D, mc.field_71439_g.func_70047_e() + 0.6D, 0.0D));
/*     */     
/*  37 */     GL11.glPushMatrix();
/*  38 */     GlStateManager.func_179084_k();
/*  39 */     GL11.glTranslated(-pos.field_72450_a + text_pos.field_72450_a, -pos.field_72448_b + text_pos.field_72448_b, -pos.field_72449_c + text_pos.field_72449_c);
/*     */ 
/*     */     
/*  42 */     GL11.glRotatef(-mc.field_71439_g.field_70177_z, 0.0F, 1.0F, 0.0F);
/*  43 */     GL11.glRotatef(mc.field_71439_g.field_70125_A, 1.0F, 0.0F, 0.0F);
/*     */     
/*  45 */     GL11.glScaled((view_idx == 1) ? -name_plate_scale : name_plate_scale, -name_plate_scale, name_plate_scale);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  51 */     FontRenderer fontRenderer = mc.field_71466_p;
/*     */     
/*  53 */     String player_name = mc.field_71439_g.func_70005_c_();
/*  54 */     String text = player_name;
/*  55 */     int text_width = fontRenderer.func_78256_a(player_name);
/*  56 */     if (!isRainbow && constantColor.length() != 7) {
/*  57 */       text = mc.field_71439_g.func_145748_c_().func_150254_d();
/*  58 */       text_width = fontRenderer.func_78256_a(mc.field_71439_g.func_145748_c_().func_150260_c());
/*     */     } 
/*     */     
/*  61 */     GL11.glTranslated(0.0D, (-fontRenderer.field_78288_b / 2), 0.0D);
/*     */     
/*  63 */     if (constantColor.length() == 7 && constantColor.startsWith("#")) {
/*  64 */       int c = -1;
/*     */       try {
/*  66 */         c = getColor(constantColor.substring(1) + "ff");
/*  67 */       } catch (Exception exception) {}
/*  68 */       if (c == -1) {
/*  69 */         String str = "Ошибка цвета в конфиге!";
/*  70 */         fontRenderer.func_78276_b(str, -fontRenderer.func_78256_a(str) / 2, 0, (new Color(1.0F, 0.0F, 0.0F, 1.0F)).getRGB());
/*     */       } else {
/*  72 */         fontRenderer.func_78276_b(text, -text_width / 2, 0, c);
/*     */       } 
/*  74 */     } else if (!isRainbow) {
/*  75 */       fontRenderer.func_78276_b(text, -text_width / 2, 0, (new Color(1.0F, 1.0F, 1.0F, 1.0F)).getRGB());
/*     */     } else {
/*     */       
/*  78 */       int start_x = -text_width / 2;
/*  79 */       int gradient_hargness = 10;
/*     */       
/*  81 */       for (int i = 0; i < player_name.length(); i++) {
/*  82 */         String t = "" + player_name.charAt(i);
/*  83 */         int t_width = fontRenderer.func_78256_a(t);
/*  84 */         fontRenderer.func_78276_b(t, start_x, 0, Main.rainbowColorSynchronizer.getColor(i * gradient_hargness - text.length() / 2).getRGB());
/*  85 */         start_x += t_width;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  90 */     double d = 0.10000000149011612D;
/*  91 */     GL11.glTranslated(0.0D, 0.0D, d);
/*     */ 
/*     */     
/*  94 */     func_73734_a(-text_width / 2 - 1, -1, text_width / 2 + 1, 8, (new Color(0.0F, 0.0F, 0.0F, 0.2F)).getRGB());
/*     */ 
/*     */ 
/*     */     
/*  98 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   private int getColor(String hexColor) {
/* 102 */     Color colorNoAlpha = Color.decode("0x" + hexColor.substring(0, 6));
/* 103 */     int alpha = Integer.parseInt(hexColor.substring(6, 8), 16);
/* 104 */     return (new Color(colorNoAlpha.getRed(), colorNoAlpha.getGreen(), colorNoAlpha.getBlue(), alpha)).getRGB();
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\hints\NamePlate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */