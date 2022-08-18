/*     */ package com.dimchig.bedwarsbro.gui;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.ColorCodesManager;
/*     */ import com.dimchig.bedwarsbro.Main;
/*     */ import com.dimchig.nolegit.AimHelper;
/*     */ import com.dimchig.nolegit.BowAimbot;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.ScaledResolution;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiPlayer
/*     */   extends GuiScreen
/*     */ {
/*     */   static Minecraft mc;
/*  37 */   private String chosen_player_name = "";
/*     */   private static boolean isPartyMod = false;
/*     */   
/*     */   public GuiPlayer() {
/*  41 */     mc = Minecraft.func_71410_x();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73866_w_() {
/*  47 */     super.func_73866_w_();
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
/*     */   public void func_73876_c() {
/*  60 */     ScaledResolution sr = new ScaledResolution(mc);
/*  61 */     int screen_width = sr.func_78326_a();
/*  62 */     int screen_height = sr.func_78328_b();
/*     */     
/*  64 */     int cx = screen_width / 2;
/*  65 */     int cy = screen_height / 2;
/*     */     
/*  67 */     this.field_146292_n.clear();
/*  68 */     int btn_width = 100;
/*  69 */     int btn_height = 20;
/*  70 */     int x = cx - btn_width / 2;
/*  71 */     int y = cy;
/*     */     
/*  73 */     ArrayList<String> arr = new ArrayList<String>();
/*  74 */     arr.add((BowAimbot.isActive ? "&a" : "&c") + "Bow Aimbot");
/*  75 */     arr.add((BowAimbot.isDrawActive ? "&a" : "&c") + "Bow Aimbot Visualization");
/*  76 */     arr.add((AimHelper.isActive ? "&a" : "&c") + "Aim Helper");
/*  77 */     arr.add((GuiPlayerFocus.STATE ? "&a" : "&c") + "ESP");
/*  78 */     arr.add((GuiPlayerFocus.isT_Active ? "&a" : "&c") + "Tracers");
/*     */     
/*  80 */     btn_width = 200;
/*  81 */     x = cx - btn_width / 2;
/*     */     
/*  83 */     y -= (int)((arr.size() / 2.0F + 1.0F) * btn_height);
/*     */     
/*  85 */     for (int i = 0; i < arr.size(); i++) {
/*  86 */       this.field_146292_n.add(new GuiButton(i, x, y, btn_width, 20, ColorCodesManager.replaceColorCodesInString(arr.get(i))));
/*  87 */       y += btn_height + 5;
/*     */     } 
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
/*     */   public void func_73863_a(int parWidth, int parHeight, float p_73863_3_) {
/* 103 */     func_146276_q_();
/* 104 */     super.func_73863_a(parWidth, parHeight, p_73863_3_);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_146284_a(GuiButton parButton) {
/* 110 */     for (GuiButton btn : this.field_146292_n) {
/* 111 */       if (btn == parButton) {
/*     */         
/* 113 */         if (btn.field_146127_k == 0) {
/* 114 */           Main.bowAimbot.toggle();
/* 115 */         } else if (btn.field_146127_k == 1) {
/* 116 */           Main.bowAimbot.toggleDraw();
/* 117 */           if (BowAimbot.isDrawActive) BowAimbot.isActive = true; 
/* 118 */         } else if (btn.field_146127_k == 2) {
/* 119 */           Main.aimHelper.toggle();
/* 120 */         } else if (btn.field_146127_k == 3) {
/* 121 */           GuiPlayerFocus.STATE = !GuiPlayerFocus.STATE;
/* 122 */         } else if (btn.field_146127_k == 4) {
/* 123 */           GuiPlayerFocus.isT_Active = !GuiPlayerFocus.isT_Active;
/* 124 */           if (GuiPlayerFocus.isT_Active) GuiPlayerFocus.STATE = true;
/*     */         
/*     */         } 
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_73868_f() {
/* 135 */     return true;
/*     */   }
/*     */   
/*     */   public void func_146281_b() {}
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\gui\GuiPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */