/*     */ package com.dimchig.bedwarsbro.gui;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.ColorCodesManager;
/*     */ import com.dimchig.bedwarsbro.Main;
/*     */ import com.dimchig.bedwarsbro.MyChatListener;
/*     */ import com.dimchig.bedwarsbro.SameModePlayers;
/*     */ import com.dimchig.bedwarsbro.dimchigspecial.HelloDimChig;
/*     */ import com.dimchig.bedwarsbro.hints.BWItemsHandler;
/*     */ import com.dimchig.bedwarsbro.hints.GeneratorTimers;
/*     */ import com.dimchig.bedwarsbro.hints.HintsValidator;
/*     */ import java.awt.Color;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*     */ import net.minecraftforge.fml.client.event.ConfigChangedEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
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
/*     */ public class GuiOnScreen
/*     */   extends Gui
/*     */ {
/*     */   private Main asInstance;
/*     */   private Minecraft mc;
/*     */   private Field renderEventTypeField;
/*     */   private Field configChangedEventModIDField;
/*     */   private MyItem item_diamond;
/*     */   private MyItem item_emerald;
/*     */   private boolean isPotionEffectTrackerActive = false;
/*     */   private boolean isPotionEffectTrackerSoundsActive = false;
/*     */   private boolean isTabBroListActive = false;
/*  55 */   public int COUNT_EMERALDS = 0;
/*  56 */   public int COUNT_DIAMONDS = 0;
/*     */   
/*  58 */   private ResourceLocation resourceLoc_potions = new ResourceLocation("bedwarschatmod:textures/gui/potions.png");
/*     */   private TextureManager textureManager;
/*     */   private GuiMinimap minimap;
/*     */   private GeneratorTimers generatorTimers;
/*     */   private KeyBinding keyTab;
/*     */   long lastPlaySoundTime;
/*     */   
/*     */   public class MyItem
/*     */   {
/*     */     public int offsetX;
/*     */     public int offsetY;
/*     */     public int width;
/*     */     public int height;
/*     */     public ItemStack stack;
/*     */     
/*     */     public MyItem(int offsetX, int offsetY, int width, int height, Item item) {
/*  74 */       this.offsetX = offsetX;
/*  75 */       this.offsetY = offsetY;
/*  76 */       this.width = width;
/*  77 */       this.height = height;
/*  78 */       this.stack = new ItemStack(item);
/*     */     }
/*     */     
/*     */     public void drawOnGui(int posX, int posY) {
/*  82 */       GuiOnScreen.this.mc.func_175599_af().func_175042_a(this.stack, posX + this.offsetX, posY + this.offsetY);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setDiamonds(int x) {
/*  87 */     this.COUNT_DIAMONDS = x;
/*     */   }
/*     */   public void setEmeralds(int x) {
/*  90 */     this.COUNT_EMERALDS = x;
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
/*     */   public void updateBooleans() {
/* 120 */     this.isPotionEffectTrackerActive = HintsValidator.isPotionEffectsTrackerActive();
/* 121 */     this.isPotionEffectTrackerSoundsActive = HintsValidator.isPotionEffectsTrackerSoundsActive();
/* 122 */     this.isTabBroListActive = Main.getConfigBool(Main.CONFIG_MSG.BRO_TAB_LIST);
/*     */   }
/*     */   
/* 125 */   public GuiOnScreen(Main asInstance) { this.lastPlaySoundTime = 0L; this.asInstance = asInstance; this.mc = Minecraft.func_71410_x(); this.minimap = Main.minimap; this.generatorTimers = Main.generatorTimers; this.item_diamond = new MyItem(-2, -2, 12, 13, Items.field_151045_i); this.item_emerald = new MyItem(-3, -1, 11, 14, Items.field_151166_bC); this.textureManager = this.mc.func_110434_K(); updateBooleans(); this.keyTab = this.mc.field_71474_y.field_74321_H; try { this.renderEventTypeField = RenderGameOverlayEvent.class.getDeclaredField("type"); this.renderEventTypeField.setAccessible(true); this.configChangedEventModIDField = ConfigChangedEvent.class.getDeclaredField("modID"); this.configChangedEventModIDField.setAccessible(true); }
/*     */     catch (NoSuchFieldException e)
/*     */     { throw new RuntimeException("Cannot find field", e); }
/* 128 */      } @SubscribeEvent public void onRender(RenderGameOverlayEvent event) throws IllegalAccessException { if (this.renderEventTypeField.get(event) == RenderGameOverlayEvent.ElementType.TEXT && !this.mc.field_71474_y.field_74330_P) {
/* 129 */       ScaledResolution sr = new ScaledResolution(this.mc);
/* 130 */       int screen_width = sr.func_78326_a();
/* 131 */       int screen_height = sr.func_78328_b();
/*     */       
/* 133 */       if (this.minimap != null) this.minimap.draw(this.mc);
/*     */       
/* 135 */       if (this.generatorTimers != null) this.generatorTimers.draw(screen_width, screen_height);
/*     */       
/* 137 */       if (this.item_emerald != null && this.item_diamond != null) {
/*     */         
/* 139 */         String text_emeralds = "" + this.COUNT_EMERALDS;
/* 140 */         int color_emerals = getColor("ffffffff");
/*     */         
/* 142 */         String text_diamonds = "" + this.COUNT_DIAMONDS;
/* 143 */         int color_diamonds = getColor("ffffffff");
/*     */ 
/*     */ 
/*     */         
/* 147 */         int font_emeralds_width = this.mc.field_71466_p.func_78256_a(text_emeralds);
/* 148 */         int font_emeralds_height = this.mc.field_71466_p.field_78288_b;
/* 149 */         int font_diamonds_width = this.mc.field_71466_p.func_78256_a(text_diamonds);
/* 150 */         int font_diamonds_height = this.mc.field_71466_p.field_78288_b;
/*     */         
/* 152 */         int offsetX = 0;
/* 153 */         if (this.COUNT_EMERALDS > 0) {
/* 154 */           if (text_emeralds.length() == 1) offsetX = 6; 
/* 155 */           if (text_emeralds.length() == 2) offsetX = 12; 
/* 156 */           if (text_emeralds.length() == 3) offsetX = 18;
/*     */           
/* 158 */           int offsetY = 5;
/* 159 */           int topX = screen_width - offsetX - this.item_emerald.width;
/* 160 */           int topY = screen_height - offsetY - this.item_emerald.height;
/* 161 */           int bottomX = screen_width - offsetX;
/* 162 */           int bottomY = screen_height - offsetY;
/*     */           
/* 164 */           this.item_emerald.drawOnGui(topX, topY);
/* 165 */           this.mc.field_71466_p.func_175065_a(text_emeralds, (bottomX - 1), (bottomY - 4), color_emerals, true);
/*     */         } 
/*     */         
/* 168 */         if (this.COUNT_DIAMONDS > 0) {
/* 169 */           if (text_diamonds.length() == 1) offsetX += 6; 
/* 170 */           if (text_diamonds.length() == 2) offsetX += 12; 
/* 171 */           if (text_diamonds.length() == 3) offsetX += 18;
/*     */           
/* 173 */           if (this.COUNT_EMERALDS > 0) offsetX += 12;
/*     */           
/* 175 */           int offsetY = 5;
/* 176 */           int topX = screen_width - offsetX - this.item_diamond.width;
/* 177 */           int topY = screen_height - offsetY - this.item_diamond.height;
/* 178 */           int bottomX = screen_width - offsetX;
/* 179 */           int bottomY = screen_height - offsetY;
/*     */           
/* 181 */           this.item_diamond.drawOnGui(topX, topY - 1);
/* 182 */           this.mc.field_71466_p.func_175065_a(text_diamonds, (bottomX - 1), (bottomY - 4), color_diamonds, true);
/*     */         } 
/*     */       } 
/* 185 */       if (this.isPotionEffectTrackerActive) {
/*     */ 
/*     */         
/* 188 */         EntityPlayerSP player = (Minecraft.func_71410_x()).field_71439_g;
/* 189 */         Collection<PotionEffect> pe = player.func_70651_bq();
/* 190 */         if (pe.size() > 0) {
/*     */           
/* 192 */           if (this.textureManager == null) this.textureManager = this.mc.func_110434_K();
/*     */ 
/*     */           
/* 195 */           ArrayList<PotionEffect> potion_effects = new ArrayList<PotionEffect>();
/* 196 */           BWItemsHandler.BWItemPotionsID[] potions_id_to_display = { BWItemsHandler.BWItemPotionsID.STRENGTH, BWItemsHandler.BWItemPotionsID.JUMP, BWItemsHandler.BWItemPotionsID.SPEED, BWItemsHandler.BWItemPotionsID.REGEN };
/*     */ 
/*     */           
/* 199 */           for (int i = 0; i < potions_id_to_display.length; i++) {
/* 200 */             for (PotionEffect effect : pe) {
/* 201 */               if (effect.func_76456_a() == (potions_id_to_display[i]).id) {
/* 202 */                 potion_effects.add(effect);
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/* 208 */           float scale = 3.0F;
/* 209 */           int cx = 5;
/* 210 */           int cy = screen_height / 2;
/* 211 */           int space_y = 30;
/* 212 */           int start_y = cy - space_y * potion_effects.size() / 2;
/* 213 */           if (potion_effects.size() % 2 == 0) start_y += space_y / 2; 
/* 214 */           int current_y = start_y;
/*     */           
/* 216 */           for (PotionEffect p : potion_effects) {
/* 217 */             BWItemsHandler.BWItemPotionsID potion = null;
/*     */             
/* 219 */             int texX = 0;
/* 220 */             int texY = 0;
/* 221 */             Color color = new Color(1.0F, 1.0F, 1.0F, 1.0F);
/* 222 */             String text = "-";
/* 223 */             this.mc.field_71446_o.func_110577_a(this.resourceLoc_potions);
/*     */             
/* 225 */             if (BWItemsHandler.BWItemPotionsID.STRENGTH.id == p.func_76456_a()) {
/* 226 */               texX = 0;
/* 227 */               texY = 0;
/* 228 */               color = new Color(1.0F, 0.0F, 0.0F, 1.0F);
/* 229 */               text = "Силка";
/* 230 */               potion = BWItemsHandler.BWItemPotionsID.STRENGTH;
/*     */             } 
/* 232 */             if (BWItemsHandler.BWItemPotionsID.JUMP.id == p.func_76456_a()) {
/* 233 */               texX = 1;
/* 234 */               texY = 0;
/* 235 */               potion = BWItemsHandler.BWItemPotionsID.JUMP;
/* 236 */               text = "Прыжок";
/* 237 */               color = new Color(0.0F, 1.0F, 0.0F, 1.0F);
/*     */             } 
/* 239 */             if (BWItemsHandler.BWItemPotionsID.SPEED.id == p.func_76456_a()) {
/* 240 */               texX = 2;
/* 241 */               texY = 0;
/* 242 */               color = new Color(0.0F, 1.0F, 1.0F, 1.0F);
/* 243 */               text = "Скорка";
/* 244 */               potion = BWItemsHandler.BWItemPotionsID.SPEED;
/*     */             } 
/* 246 */             if (BWItemsHandler.BWItemPotionsID.REGEN.id == p.func_76456_a()) {
/* 247 */               texX = 0;
/* 248 */               texY = 1;
/* 249 */               color = new Color(1.0F, 0.0F, 1.0F, 1.0F);
/* 250 */               text = "Реген";
/* 251 */               potion = BWItemsHandler.BWItemPotionsID.REGEN;
/*     */             } 
/* 253 */             if (potion == null)
/*     */               continue; 
/* 255 */             GlStateManager.func_179094_E();
/*     */             
/* 257 */             GlStateManager.func_179109_b(cx, current_y, 0.0F);
/* 258 */             GlStateManager.func_179152_a(1.0F / scale, 1.0F / scale, 1.0F / scale);
/* 259 */             GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 260 */             func_73729_b(0, -40, texX * 80, texY * 80, 80, 80);
/*     */ 
/*     */             
/* 263 */             GlStateManager.func_179121_F();
/*     */             
/* 265 */             this.mc.field_71466_p.func_175065_a(text, cx + 80.0F / scale + 5.0F, current_y - 30.0F / scale, color.getRGB(), false);
/*     */             
/* 267 */             int time = p.func_76459_b() / 20;
/* 268 */             String s_time = "" + time + "s";
/* 269 */             if (time > 60) {
/* 270 */               int mins = time / 60;
/* 271 */               int secs = time % 60;
/* 272 */               s_time = mins + ":" + ((secs < 10) ? "0" : "") + secs;
/*     */             } 
/* 274 */             Color text_color = new Color(1.0F, 1.0F, 1.0F, 1.0F);
/* 275 */             boolean isShadow = false;
/* 276 */             if (p.func_76459_b() <= 120) {
/* 277 */               text_color = new Color(1.0F, 0.0F, 0.0F, 1.0F);
/* 278 */               isShadow = true;
/*     */             } 
/*     */             
/* 281 */             if (this.isPotionEffectTrackerSoundsActive && p.func_76459_b() == 120 && potion.id != BWItemsHandler.BWItemPotionsID.REGEN.id) {
/* 282 */               long t = (new Date()).getTime();
/* 283 */               if (t - this.lastPlaySoundTime > 100L) {
/* 284 */                 MyChatListener.playSound("note.hat", 1.0F);
/* 285 */                 this.lastPlaySoundTime = t;
/*     */               } 
/*     */             } 
/* 288 */             if (this.isPotionEffectTrackerSoundsActive && p.func_76459_b() == 3 && potion.id != BWItemsHandler.BWItemPotionsID.REGEN.id) {
/* 289 */               long t = (new Date()).getTime();
/* 290 */               if (t - this.lastPlaySoundTime > 100L) {
/* 291 */                 MyChatListener.playSound("random.fizz", 1.0F);
/* 292 */                 this.lastPlaySoundTime = t;
/*     */               } 
/*     */             } 
/*     */             
/* 296 */             this.mc.field_71466_p.func_175065_a(s_time, cx + 80.0F / scale + 5.0F, current_y, text_color.getRGB(), isShadow);
/*     */             
/* 298 */             current_y += space_y;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 304 */       if (this.keyTab.func_151470_d() && this.isTabBroListActive) {
/*     */         
/* 306 */         GlStateManager.func_179094_E();
/*     */         
/* 308 */         float scale = 1.0F;
/* 309 */         GlStateManager.func_179109_b(0.0F, 0.0F, 10.0F);
/* 310 */         GlStateManager.func_179152_a(1.0F / scale, 1.0F / scale, 1.0F / scale);
/* 311 */         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 316 */         int padding = 50;
/* 317 */         int y = (int)((screen_height - padding) * scale);
/* 318 */         int x = (int)((screen_width / 2.0F - 60.0F) * scale);
/*     */         
/* 320 */         ArrayList<SameModePlayers.BroPlayer> mod_users = new ArrayList<SameModePlayers.BroPlayer>();
/* 321 */         mod_users.addAll(Main.sameModePlayers.getActivePlayers());
/* 322 */         ArrayList<SameModePlayers.BroPlayer> lobby_users = new ArrayList<SameModePlayers.BroPlayer>();
/*     */         
/* 324 */         for (SameModePlayers.BroPlayer bro : mod_users) {
/* 325 */           if (HelloDimChig.isPlayerOnServer(bro.name)) lobby_users.add(bro);
/*     */         
/*     */         } 
/* 328 */         if (lobby_users != null && lobby_users.size() > 0) {
/* 329 */           int color = (new Color(1.0F, 1.0F, 1.0F, 1.0F)).getRGB();
/* 330 */           int color_bg = (new Color(0.0F, 0.0F, 0.0F, 0.1F)).getRGB();
/* 331 */           int line_size = this.mc.field_71466_p.field_78288_b;
/*     */           
/* 333 */           for (SameModePlayers.BroPlayer bro : lobby_users) {
/* 334 */             String text = ColorCodesManager.replaceColorCodesInString(Main.namePlateRenderer.getPrefixForBro(bro) + "&f" + bro.name);
/* 335 */             int width = this.mc.field_71466_p.func_78256_a(text);
/* 336 */             func_73734_a(x - 1, y, x + width + 1, y + line_size, color_bg);
/* 337 */             this.mc.field_71466_p.func_175065_a(text, x, y, color, true);
/* 338 */             y -= line_size;
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 343 */         GlStateManager.func_179121_F();
/*     */       } 
/*     */       
/* 346 */       Main.guiRadarIcon.draw();
/*     */       
/* 348 */       Main.emotesDrawer.draw2D();
/*     */     }  }
/*     */ 
/*     */   
/*     */   private int getColor(String hexColor) {
/* 353 */     Color colorNoAlpha = Color.decode("0x" + hexColor.substring(0, 6));
/* 354 */     int alpha = Integer.parseInt(hexColor.substring(6, 8), 16);
/* 355 */     return (new Color(colorNoAlpha.getRed(), colorNoAlpha.getGreen(), colorNoAlpha.getBlue(), alpha)).getRGB();
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\gui\GuiOnScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */