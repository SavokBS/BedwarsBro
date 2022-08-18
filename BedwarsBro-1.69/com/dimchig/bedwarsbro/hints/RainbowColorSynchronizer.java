/*    */ package com.dimchig.bedwarsbro.hints;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.Minecraft;
/*    */ 
/*    */ public class RainbowColorSynchronizer
/*    */ {
/*    */   public static ArrayList<Color> gradient_colors;
/*    */   static Random rnd;
/*    */   static Minecraft mc;
/* 13 */   public static int rainbowSpeed = 1;
/*    */   
/*    */   public RainbowColorSynchronizer() {
/* 16 */     mc = Minecraft.func_71410_x();
/*    */     
/* 18 */     gradient_colors = new ArrayList<Color>();
/* 19 */     for (int k = 0; k < 100; ) { gradient_colors.add(new Color(k * 255 / 100, 255, 0)); k++; }
/* 20 */      for (int j = 100; j > 0; ) { gradient_colors.add(new Color(255, j * 255 / 100, 0)); j--; }
/* 21 */      for (int i = 0; i < 100; ) { gradient_colors.add(new Color(255, 0, i * 255 / 100)); i++; }
/* 22 */      for (int r = 100; r > 0; ) { gradient_colors.add(new Color(r * 255 / 100, 0, 255)); r--; }
/* 23 */      for (int g = 0; g < 100; ) { gradient_colors.add(new Color(0, g * 255 / 100, 255)); g++; }
/* 24 */      for (int b = 100; b > 0; ) { gradient_colors.add(new Color(0, 255, b * 255 / 100)); b--; }
/* 25 */      gradient_colors.add(new Color(0, 255, 0));
/*    */     
/* 27 */     updateBooleans();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateBooleans() {
/* 33 */     rainbowSpeed = HintsValidator.getRainbowSpeed();
/*    */   }
/*    */   
/*    */   public Color getColor() {
/* 37 */     return getColor(0);
/*    */   }
/*    */   
/*    */   public Color getColor(int x) {
/* 41 */     if (gradient_colors.size() == 0) return new Color(1.0F, 1.0F, 1.0F); 
/* 42 */     int idx = (int)((mc.field_71441_e.func_82737_E() * rainbowSpeed + x + gradient_colors.size()) % gradient_colors.size());
/* 43 */     return gradient_colors.get((gradient_colors.size() - idx) % gradient_colors.size());
/*    */   }
/*    */   
/*    */   public Color getRandomColor() {
/* 47 */     if (rnd == null) rnd = mc.field_71441_e.field_73012_v; 
/* 48 */     return gradient_colors.get(rnd.nextInt(gradient_colors.size()));
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\hints\RainbowColorSynchronizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */