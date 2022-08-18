/*     */ package com.dimchig.bedwarsbro.gui;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.ColorCodesManager;
/*     */ import com.dimchig.bedwarsbro.Main;
/*     */ import com.dimchig.bedwarsbro.MyChatListener;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.network.NetworkPlayerInfo;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.client.config.GuiCheckBox;
/*     */ import org.lwjgl.opengl.GL11;
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
/*     */ public class ManipulatorGui
/*     */   extends GuiScreen
/*     */ {
/*     */   static Minecraft mc;
/*     */   private ResourceLocation resourceLoc_manipualtor;
/*     */   private TextureManager textureManager;
/*  39 */   private int current_screen = -1;
/*  40 */   private String chosen_player_name = "";
/*     */   private static boolean isPartyMod = false;
/*     */   
/*     */   public ManipulatorGui() {
/*  44 */     mc = Minecraft.func_71410_x();
/*     */     
/*  46 */     this.textureManager = mc.func_110434_K();
/*  47 */     this.current_screen = 0;
/*  48 */     this.chosen_player_name = "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73866_w_() {
/*  56 */     this.current_screen = 0;
/*  57 */     super.func_73866_w_();
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
/*  70 */     ScaledResolution sr = new ScaledResolution(mc);
/*  71 */     int screen_width = sr.func_78326_a();
/*  72 */     int screen_height = sr.func_78328_b();
/*     */     
/*  74 */     int cx = screen_width / 2;
/*  75 */     int cy = screen_height / 2;
/*     */     
/*  77 */     this.field_146292_n.clear();
/*  78 */     int btn_width = 100;
/*  79 */     int btn_height = 20;
/*  80 */     int x = cx - btn_width / 2;
/*  81 */     int y = cy;
/*     */ 
/*     */     
/*     */     try {
/*  85 */       if (this.current_screen == 0) {
/*     */ 
/*     */         
/*  88 */         Collection<NetworkPlayerInfo> players = Minecraft.func_71410_x().func_147114_u().func_175106_d();
/*  89 */         ArrayList<String> arr = new ArrayList<String>();
/*  90 */         int cnt = 0;
/*  91 */         for (NetworkPlayerInfo info : players) {
/*  92 */           if (info == null || info.func_178845_a() == null)
/*  93 */             continue;  String player_name = info.func_178845_a().getName();
/*  94 */           if (player_name.equals((Minecraft.func_71410_x()).field_71439_g.func_70005_c_()))
/*  95 */             continue;  if (Main.sameModePlayers.isPlayerWithMod(player_name)) arr.add(player_name);
/*     */           
/*  97 */           if (cnt > 1000)
/*  98 */             break;  cnt++;
/*     */         } 
/*     */         
/* 101 */         y -= (int)((arr.size() / 2.0F + 1.0F) * btn_height);
/* 102 */         for (int i = 0; i < arr.size(); i++) {
/* 103 */           this.field_146292_n.add(new GuiButton(i, x, y, btn_width, 20, arr.get(i)));
/* 104 */           y += btn_height + 5;
/*     */         } 
/* 106 */       } else if (this.current_screen == 1) {
/*     */         
/* 108 */         boolean isFound = false;
/* 109 */         Collection<NetworkPlayerInfo> players = Minecraft.func_71410_x().func_147114_u().func_175106_d();
/* 110 */         int cnt = 0;
/* 111 */         for (NetworkPlayerInfo info : players) {
/* 112 */           if (info == null || info.func_178845_a() == null)
/* 113 */             continue;  String player_name = info.func_178845_a().getName();
/* 114 */           if (player_name.equals(this.chosen_player_name)) {
/* 115 */             isFound = true;
/*     */             
/*     */             break;
/*     */           } 
/* 119 */           if (cnt > 1000)
/* 120 */             break;  cnt++;
/*     */         } 
/*     */         
/* 123 */         if (!isFound || this.chosen_player_name.length() == 0) {
/* 124 */           this.current_screen = 0;
/* 125 */           this.chosen_player_name = "";
/* 126 */           MyChatListener.playSound(MyChatListener.SOUND_REJECT);
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */         
/* 132 */         ArrayList<String> arr = new ArrayList<String>();
/* 133 */         arr.add("&6Ливнуть из катки");
/* 134 */         arr.add("&eСказать инфу про себя");
/* 135 */         arr.add("&aВключить  HitBlock");
/* 136 */         arr.add("&cВыключить HitBlock");
/* 137 */         arr.add("&eСказать свою версию мода");
/* 138 */         arr.add("&cКрашнуть ему игру");
/* 139 */         arr.add("&6Дропнуть слот");
/* 140 */         arr.add("&eУпасть с моста");
/* 141 */         arr.add("&aИнфа про пати");
/* 142 */         arr.add("&bВкл/Выкл esp");
/*     */ 
/*     */         
/* 145 */         btn_width = 200;
/* 146 */         x = cx - btn_width / 2;
/*     */         
/* 148 */         y -= (int)((arr.size() / 2.0F + 1.0F) * btn_height);
/*     */         
/* 150 */         this.field_146292_n.add(new GuiCheckBox(999, this.field_146294_l / 2 - 40, y - 20, ColorCodesManager.replaceColorCodesInString("Писать в &eпати&f?"), isPartyMod));
/*     */         
/* 152 */         for (int i = 0; i < arr.size(); i++) {
/* 153 */           this.field_146292_n.add(new GuiButton(i, x, y, btn_width, 20, ColorCodesManager.replaceColorCodesInString(arr.get(i))));
/* 154 */           y += btn_height + 5;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 159 */     } catch (Exception exception) {}
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
/* 174 */     if (this.textureManager == null) this.textureManager = mc.func_110434_K(); 
/* 175 */     func_146276_q_();
/* 176 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 178 */     ScaledResolution sr = new ScaledResolution(mc);
/* 179 */     int screen_width = sr.func_78326_a();
/* 180 */     int screen_height = sr.func_78328_b();
/*     */     
/* 182 */     int cx = screen_width / 2;
/* 183 */     int cy = screen_height / 2;
/*     */     
/* 185 */     if (this.current_screen == 1) {
/* 186 */       String text = ColorCodesManager.replaceColorCodesInString(Main.namePlateRenderer.getPrefixByName(this.chosen_player_name) + this.chosen_player_name);
/* 187 */       mc.field_71466_p.func_78276_b(text, cx - mc.field_71466_p.func_78256_a(text) / 2, 10, (new Color(1.0F, 1.0F, 1.0F, 1.0F)).getRGB());
/*     */     } 
/*     */ 
/*     */     
/* 191 */     super.func_73863_a(parWidth, parHeight, p_73863_3_);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_146284_a(GuiButton parButton) {
/* 197 */     for (GuiButton btn : this.field_146292_n) {
/* 198 */       if (btn == parButton) {
/*     */         
/* 200 */         if (this.current_screen == 0) {
/* 201 */           this.current_screen = 1;
/* 202 */           this.chosen_player_name = btn.field_146126_j; return;
/*     */         } 
/* 204 */         if (this.current_screen == 1) {
/* 205 */           if (btn.field_146127_k == 999) {
/* 206 */             isPartyMod = !isPartyMod;
/*     */             
/*     */             return;
/*     */           } 
/* 210 */           String action = "" + (btn.field_146127_k + 1);
/* 211 */           if (action.equals("10")) action = "a"; 
/* 212 */           if (isPartyMod) action = "pc" + action;
/*     */           
/* 214 */           Main.commandManipulate.sendCommand(this.chosen_player_name, action);
/*     */         } 
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_73868_f() {
/* 225 */     return true;
/*     */   }
/*     */   
/*     */   public void func_146281_b() {}
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\gui\ManipulatorGui.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */