/*     */ package com.dimchig.bedwarsbro.emotes;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.Main;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EmojiGui
/*     */   extends GuiScreen
/*     */ {
/*     */   static Minecraft mc;
/*     */   private ResourceLocation[] resourceLoc_emotes;
/*     */   private ResourceLocation resourceLoc_emoteWheel;
/*     */   private TextureManager textureManager;
/*     */   private int current_emote_id;
/*     */   private MyButton[] buttons;
/*     */   
/*     */   public EmojiGui() {
/*  43 */     this.buttons = new MyButton[0];
/*     */     mc = Minecraft.func_71410_x();
/*     */     this.resourceLoc_emotes = EmotesDrawer.resourceLoc_emotes;
/*     */     this.resourceLoc_emoteWheel = new ResourceLocation("bedwarschatmod:textures/gui/emoteWheel.png");
/*     */     this.textureManager = mc.func_110434_K();
/*     */     this.current_emote_id = -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73866_w_() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73876_c() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private class MyButton
/*     */   {
/*     */     public int x;
/*     */ 
/*     */ 
/*     */     
/*     */     public int y;
/*     */ 
/*     */     
/*     */     public int size;
/*     */ 
/*     */     
/*     */     public EmotesDrawer.EMOTE_TYPE emote_type;
/*     */ 
/*     */     
/*     */     public boolean isActive;
/*     */ 
/*     */ 
/*     */     
/*     */     public MyButton(int x, int y, int size, EmotesDrawer.EMOTE_TYPE emote_type) {
/*  84 */       this.x = x;
/*  85 */       this.y = y;
/*  86 */       this.size = size;
/*  87 */       this.emote_type = emote_type;
/*  88 */       this.isActive = false;
/*     */     }
/*     */   }
/*     */   
/*  92 */   static int current_button_idx = -1;
/*     */ 
/*     */   
/*     */   public void func_73863_a(int parWidth, int parHeight, float p_73863_3_) {
/*  96 */     if (this.textureManager == null) this.textureManager = mc.func_110434_K();
/*     */     
/*  98 */     ScaledResolution sr = new ScaledResolution(mc);
/*  99 */     int screen_width = sr.func_78326_a();
/* 100 */     int screen_height = sr.func_78328_b();
/*     */     
/* 102 */     int cell_size = screen_width / 18;
/* 103 */     int cx = screen_width / 2;
/* 104 */     int cy = screen_height / 2;
/*     */     
/* 106 */     int distance_from_center = cell_size * 2;
/*     */ 
/*     */     
/* 109 */     float mouse_angle = (float)(Math.toDegrees(Math.atan2((cy - parHeight), (cx - parWidth))) + 360.0D - 180.0D) % 360.0F;
/* 110 */     current_button_idx = (int)mouse_angle / 45;
/*     */     
/* 112 */     this.buttons = new MyButton[(EmotesDrawer.EMOTE_TYPE.values()).length];
/* 113 */     for (int i = 0; i < (EmotesDrawer.EMOTE_TYPE.values()).length; i++) {
/* 114 */       EmotesDrawer.EMOTE_TYPE type = EmotesDrawer.EMOTE_TYPE.values()[i];
/*     */       
/* 116 */       float angle = (float)Math.toRadians((360.0F / (EmotesDrawer.EMOTE_TYPE.values()).length * i) + 22.5D);
/*     */       
/* 118 */       int x = (int)(cx + distance_from_center * MathHelper.func_76134_b(angle));
/* 119 */       int y = (int)(cy + distance_from_center * MathHelper.func_76126_a(angle));
/* 120 */       MyButton b = new MyButton(x, y, cell_size, type);
/* 121 */       this.buttons[i] = b;
/* 122 */       if (i == current_button_idx) b.isActive = true;
/*     */     
/*     */     } 
/*     */     
/* 126 */     float scale = screen_width / 800.0F;
/*     */     
/* 128 */     GL11.glPushMatrix();
/* 129 */     GL11.glTranslatef(cx, cy, 0.0F);
/* 130 */     GL11.glScaled(scale, scale, scale);
/* 131 */     GL11.glRotatef(45.0F * current_button_idx + 90.0F, 0.0F, 0.0F, 1.0F);
/* 132 */     GlStateManager.func_179147_l();
/*     */     
/* 134 */     mc.func_110434_K().func_110577_a(this.resourceLoc_emoteWheel);
/* 135 */     func_73729_b(-128, -128, 0, 0, 256, 256);
/* 136 */     GL11.glPopMatrix();
/*     */ 
/*     */ 
/*     */     
/* 140 */     for (int j = 0; j < this.buttons.length; j++) {
/* 141 */       drawCell(this.buttons[j]);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawCell(MyButton btn) {
/* 149 */     GL11.glPushMatrix();
/*     */     
/* 151 */     GL11.glTranslated(btn.x, btn.y, 0.0D);
/*     */     
/* 153 */     float scale = 0.35F + (btn.isActive ? 0.05F : 0.0F);
/* 154 */     scale = scale * 1.0F * (new ScaledResolution(mc)).func_78326_a() / 800.0F;
/* 155 */     GL11.glScaled(scale, scale, scale);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 161 */     mc.field_71446_o.func_110577_a(this.resourceLoc_emotes[btn.emote_type.tex_id]);
/* 162 */     func_73729_b(-64, -64, btn.emote_type.posX * 128, btn.emote_type.posY * 128, 128, 128);
/*     */     
/* 164 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_73868_f() {
/* 170 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_73864_a(int mouseX, int mouseY, int mouseButton) {
/* 175 */     if (mc.field_71439_g == null)
/* 176 */       return;  EmotesDrawer.EMOTE_TYPE emoteType = null;
/* 177 */     for (EmotesDrawer.EMOTE_TYPE e : EmotesDrawer.EMOTE_TYPE.values()) {
/* 178 */       if (e.id == current_button_idx + 1) {
/* 179 */         emoteType = e;
/*     */         break;
/*     */       } 
/*     */     } 
/* 183 */     if (emoteType == null)
/* 184 */       return;  Main.emotesDrawer.sendEmote(emoteType);
/* 185 */     mc.field_71439_g.func_71053_j();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_146286_b(int mouseX, int mouseY, int state) {
/* 190 */     super.func_146286_b(mouseX, mouseY, state);
/*     */   }
/*     */   
/*     */   public void func_146281_b() {}
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\emotes\EmojiGui.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */