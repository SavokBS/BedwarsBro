/*     */ package com.dimchig.bedwarsbro.emotes;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.ChatSender;
/*     */ import com.dimchig.bedwarsbro.ColorCodesManager;
/*     */ import com.dimchig.bedwarsbro.Main;
/*     */ import com.dimchig.bedwarsbro.MyChatListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class EmotesDrawer
/*     */   extends Gui
/*     */ {
/*     */   static Minecraft mc;
/*     */   
/*     */   public enum EMOTE_TYPE
/*     */   {
/*  28 */     DISLIKE(1, 0, 0, 0, 2500),
/*  29 */     HEART(2, 0, 1, 0, 2500),
/*  30 */     LAUGH(3, 0, 0, 1, 2500),
/*  31 */     ANGRY(4, 0, 1, 1, 2500),
/*  32 */     SHIT(5, 1, 0, 0, 2500),
/*  33 */     BURP(6, 1, 0, 1, 2500),
/*  34 */     LIKE(7, 1, 1, 0, 2500),
/*  35 */     WOW(8, 1, 1, 1, 2500);
/*     */     
/*     */     public int id;
/*     */     public int tex_id;
/*     */     public int posX;
/*     */     public int posY;
/*     */     public int duration;
/*     */     
/*     */     EMOTE_TYPE(int id, int tex_id, int posX, int posY, int duration) {
/*  44 */       this.id = id;
/*  45 */       this.tex_id = tex_id;
/*  46 */       this.posX = posX;
/*  47 */       this.posY = posY;
/*  48 */       this.duration = duration;
/*     */     }
/*     */     
/*     */     private static EMOTE_TYPE getEmoteById(int id) {
/*  52 */       for (EMOTE_TYPE e : values()) {
/*  53 */         if (e.id == id) return e; 
/*     */       } 
/*  55 */       return null;
/*     */     }
/*     */   }
/*     */   
/*     */   public class MyEmote {
/*     */     public String player_name;
/*     */     public long time_start;
/*     */     public EmotesDrawer.EMOTE_TYPE type;
/*     */     
/*     */     public MyEmote(String player_name, long time_start, EmotesDrawer.EMOTE_TYPE type) {
/*  65 */       this.player_name = player_name;
/*  66 */       this.time_start = time_start;
/*  67 */       this.type = type;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*  72 */   public static ResourceLocation[] resourceLoc_emotes = new ResourceLocation[] { new ResourceLocation("bedwarschatmod:textures/gui/emotes0.png"), new ResourceLocation("bedwarschatmod:textures/gui/emotes1.png") };
/*     */ 
/*     */   
/*     */   private TextureManager textureManager;
/*     */   
/*  77 */   private static ArrayList<MyEmote> emotes = new ArrayList<MyEmote>();
/*  78 */   private String prefix = "BedwarsBro-emote-";
/*     */   private static boolean isSoundActive = true;
/*     */   
/*     */   public EmotesDrawer() {
/*  82 */     mc = Minecraft.func_71410_x();
/*  83 */     emotes = new ArrayList<MyEmote>();
/*  84 */     this.textureManager = mc.func_110434_K();
/*  85 */     updateBooleans();
/*     */   }
/*     */   
/*     */   public void updateBooleans() {
/*  89 */     isSoundActive = !Main.getConfigBool(Main.CONFIG_MSG.EMOTIONS_REMOVE_SOUND);
/*     */   }
/*     */   
/*     */   public void addEmote(String player_name, EMOTE_TYPE emote_type) {
/*  93 */     if (mc.field_71439_g == null)
/*  94 */       return;  long t = (new Date()).getTime();
/*  95 */     emotes.add(new MyEmote(player_name, t, emote_type));
/*     */     
/*  97 */     if (!isSoundActive)
/*     */       return; 
/*  99 */     if (player_name.equals(mc.field_71439_g.func_70005_c_())) {
/* 100 */       MyChatListener.playSound(MyChatListener.SOUND_EMOTE);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 105 */     List<EntityPlayer> entities = mc.field_71441_e.field_73010_i;
/* 106 */     if (entities.size() == 0)
/* 107 */       return;  for (EntityPlayer en : entities) {
/* 108 */       if (en != null && en.func_70005_c_() != null && en.func_145748_c_() != null && 
/* 109 */         en.func_70005_c_().equals(player_name)) {
/* 110 */         mc.field_71441_e.func_72980_b(en.field_70165_t, en.field_70163_u + en.field_70131_O, en.field_70161_v, MyChatListener.SOUND_EMOTE, 1.0F, 1.0F, false);
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendEmote(EMOTE_TYPE emote_type) {
/* 118 */     String cmd = "!" + this.prefix + emote_type.id;
/* 119 */     if (Main.shopManager.findItemInHotbar("ыбор лобби") != -1) {
/* 120 */       ChatSender.addText("&cТебя могут замутить за эмоции в лобби! Используй только в игре!");
/* 121 */       MyChatListener.playSound(MyChatListener.SOUND_REJECT);
/*     */     } 
/* 123 */     ChatSender.sendText(cmd);
/*     */   }
/*     */   
/*     */   public boolean handleChat(String player_name, String text) {
/* 127 */     if (text.contains(this.prefix)) {
/*     */       try {
/* 129 */         String cmd = text.split(this.prefix)[1].trim();
/* 130 */         int emote_id = Integer.parseInt(cmd);
/* 131 */         EMOTE_TYPE emoteType = EMOTE_TYPE.getEmoteById(emote_id);
/* 132 */         if (emoteType == null) return true; 
/* 133 */         addEmote(ColorCodesManager.removeColorCodes(player_name), emoteType);
/* 134 */       } catch (Exception e) {
/* 135 */         return true;
/*     */       } 
/* 137 */       return true;
/*     */     } 
/* 139 */     return false;
/*     */   }
/*     */   
/*     */   public void draw3D(Vec3 playerPos, float partialTicks, float player_yaw, float player_pitch) {
/* 143 */     if (emotes.size() == 0)
/* 144 */       return;  if (mc.field_71439_g == null)
/* 145 */       return;  long t = (new Date()).getTime();
/* 146 */     if (this.textureManager == null) this.textureManager = mc.func_110434_K();
/*     */     
/* 148 */     List<MyEmote> emotes_active = getActiveEmotes(t);
/* 149 */     List<EntityPlayer> entities = mc.field_71441_e.field_73010_i;
/* 150 */     if (emotes_active.size() == 0 || entities.size() == 0)
/*     */       return; 
/* 152 */     for (MyEmote emote : emotes_active) {
/*     */       
/* 154 */       Vec3 pos = null;
/* 155 */       for (EntityPlayer en : entities) {
/* 156 */         if (en != null && en.func_70005_c_() != null && en.func_145748_c_() != null && 
/* 157 */           en.func_70005_c_().equals(emote.player_name)) {
/* 158 */           double x = en.field_70169_q + (en.field_70165_t - en.field_70169_q) * partialTicks;
/* 159 */           double y = en.field_70167_r + (en.field_70163_u - en.field_70167_r) * partialTicks;
/* 160 */           double z = en.field_70166_s + (en.field_70161_v - en.field_70166_s) * partialTicks;
/* 161 */           pos = new Vec3(x, y + en.func_70047_e() + 1.4D, z);
/*     */           break;
/*     */         } 
/*     */       } 
/* 165 */       if (pos == null)
/*     */         continue; 
/* 167 */       double scale = getScaleForEmote(emote, t);
/*     */       
/* 169 */       GL11.glPushMatrix();
/* 170 */       mc.field_71446_o.func_110577_a(resourceLoc_emotes[emote.type.tex_id]);
/* 171 */       GL11.glTranslated(-playerPos.field_72450_a + pos.field_72450_a, -playerPos.field_72448_b + pos.field_72448_b, -playerPos.field_72449_c + pos.field_72449_c);
/* 172 */       GL11.glRotatef(-player_yaw, 0.0F, 1.0F, 0.0F);
/* 173 */       GL11.glRotatef(player_pitch, 1.0F, 0.0F, 0.0F);
/* 174 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 175 */       GL11.glScaled((mc.field_71474_y.field_74320_O < 2) ? -scale : scale, -scale, scale);
/*     */       
/* 177 */       func_73729_b(-64, -64, emote.type.posX * 128, emote.type.posY * 128, 128, 128);
/*     */       
/* 179 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void draw2D() {
/* 185 */     if (emotes.size() == 0)
/* 186 */       return;  if (mc.field_71439_g == null)
/* 187 */       return;  long t = (new Date()).getTime();
/* 188 */     if (this.textureManager == null) this.textureManager = mc.func_110434_K();
/*     */     
/* 190 */     List<MyEmote> emotes_active = getActiveEmotes(t);
/*     */     
/* 192 */     if (emotes_active.size() == 0)
/*     */       return; 
/* 194 */     for (MyEmote emote : emotes_active) {
/*     */       
/* 196 */       mc.field_71446_o.func_110577_a(resourceLoc_emotes[emote.type.tex_id]);
/* 197 */       if (mc.field_71439_g.func_70005_c_().equals(emote.player_name) && mc.field_71474_y.field_74320_O == 0) {
/*     */         
/* 199 */         GlStateManager.func_179094_E();
/* 200 */         GlStateManager.func_179109_b(((new ScaledResolution(mc)).func_78326_a() / 2), 30.0F, 0.0F);
/* 201 */         double scale = getScaleForEmote(emote, t) * 75.0D;
/* 202 */         GlStateManager.func_179139_a(scale, scale, scale);
/* 203 */         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 204 */         func_73729_b(-64, -64, emote.type.posX * 128, emote.type.posY * 128, 128, 128);
/* 205 */         GlStateManager.func_179121_F();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private ArrayList<MyEmote> getActiveEmotes(long current_time) {
/* 213 */     ArrayList<MyEmote> emotes_active = new ArrayList<MyEmote>();
/*     */     
/* 215 */     Iterator<MyEmote> i = emotes.iterator();
/* 216 */     while (i.hasNext()) {
/* 217 */       MyEmote emote = i.next();
/* 218 */       if (current_time - emote.time_start > emote.type.duration) {
/* 219 */         i.remove();
/*     */         continue;
/*     */       } 
/* 222 */       emotes_active.add(emote);
/*     */     } 
/* 224 */     return emotes_active;
/*     */   }
/*     */   
/*     */   private double getScaleForEmote(MyEmote emote, long current_time) {
/* 228 */     double max_scale = 0.005D;
/* 229 */     double scale = 0.0D;
/* 230 */     double dist = ((float)(current_time - emote.time_start) * 1.0F / emote.type.duration);
/*     */     
/* 232 */     double pop_time = 0.05D;
/* 233 */     double keycode1 = 0.04D;
/* 234 */     double keycode2 = keycode1 + pop_time;
/* 235 */     double keycode3 = 0.9D;
/* 236 */     double keycode4 = keycode3 + pop_time;
/* 237 */     double bounciness = 1.15D;
/*     */     
/* 239 */     if (dist < keycode1) {
/* 240 */       scale = myMap(dist, 0.0D, keycode1, 0.0D, max_scale * bounciness);
/* 241 */     } else if (dist < keycode2) {
/* 242 */       scale = myMap(dist, keycode1, keycode2, max_scale * bounciness, max_scale);
/* 243 */     } else if (dist < keycode3) {
/* 244 */       scale = max_scale;
/* 245 */     } else if (dist < keycode4) {
/* 246 */       scale = myMap(dist, keycode3, keycode4, max_scale, max_scale * bounciness);
/*     */     } else {
/* 248 */       scale = myMap(dist, keycode4, 1.0D, max_scale * bounciness, 0.0D);
/*     */     } 
/* 250 */     return scale;
/*     */   }
/*     */   
/*     */   private double myMap(double value, double leftMin, double leftMax, double rightMin, double rightMax) {
/* 254 */     double leftSpan = leftMax - leftMin;
/* 255 */     double rightSpan = rightMax - rightMin;
/* 256 */     double valueScaled = (value - leftMin) / leftSpan;
/* 257 */     return rightMin + valueScaled * rightSpan;
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\emotes\EmotesDrawer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */