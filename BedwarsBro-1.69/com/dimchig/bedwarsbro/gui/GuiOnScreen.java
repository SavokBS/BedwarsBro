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
/*  53 */   public int COUNT_EMERALDS = 0;
/*  54 */   public int COUNT_DIAMONDS = 0;
/*     */   
/*  56 */   private ResourceLocation resourceLoc_potions = new ResourceLocation("bedwarschatmod:textures/gui/potions.png");
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
/*  72 */       this.offsetX = offsetX;
/*  73 */       this.offsetY = offsetY;
/*  74 */       this.width = width;
/*  75 */       this.height = height;
/*  76 */       this.stack = new ItemStack(item);
/*     */     }
/*     */     
/*     */     public void drawOnGui(int posX, int posY) {
/*  80 */       GuiOnScreen.this.mc.func_175599_af().func_175042_a(this.stack, posX + this.offsetX, posY + this.offsetY);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setDiamonds(int x) {
/*  85 */     this.COUNT_DIAMONDS = x;
/*     */   }
/*     */   public void setEmeralds(int x) {
/*  88 */     this.COUNT_EMERALDS = x;
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
/* 118 */     this.isPotionEffectTrackerActive = HintsValidator.isPotionEffectsTrackerActive();
/* 119 */     this.isPotionEffectTrackerSoundsActive = HintsValidator.isPotionEffectsTrackerSoundsActive();
/*     */   }
/*     */   
/* 122 */   public GuiOnScreen(Main asInstance) { this.lastPlaySoundTime = 0L; this.asInstance = asInstance; this.mc = Minecraft.func_71410_x(); this.minimap = Main.minimap; this.generatorTimers = Main.generatorTimers; this.item_diamond = new MyItem(-2, -2, 12, 13, Items.field_151045_i); this.item_emerald = new MyItem(-3, -1, 11, 14, Items.field_151166_bC); this.textureManager = this.mc.func_110434_K(); updateBooleans(); this.keyTab = this.mc.field_71474_y.field_74321_H; try { this.renderEventTypeField = RenderGameOverlayEvent.class.getDeclaredField("type"); this.renderEventTypeField.setAccessible(true); this.configChangedEventModIDField = ConfigChangedEvent.class.getDeclaredField("modID"); this.configChangedEventModIDField.setAccessible(true); }
/*     */     catch (NoSuchFieldException e)
/*     */     { throw new RuntimeException("Cannot find field", e); }
/* 125 */      } @SubscribeEvent public void onRender(RenderGameOverlayEvent event) throws IllegalAccessException { if (this.renderEventTypeField.get(event) == RenderGameOverlayEvent.ElementType.TEXT && !this.mc.field_71474_y.field_74330_P) {
/* 126 */       ScaledResolution sr = new ScaledResolution(this.mc);
/* 127 */       int screen_width = sr.func_78326_a();
/* 128 */       int screen_height = sr.func_78328_b();
/*     */       
/* 130 */       if (this.minimap != null) this.minimap.draw(this.mc);
/*     */       
/* 132 */       if (this.generatorTimers != null) this.generatorTimers.draw(screen_width, screen_height);
/*     */       
/* 134 */       if (this.item_emerald != null && this.item_diamond != null) {
/*     */         
/* 136 */         String text_emeralds = "" + this.COUNT_EMERALDS;
/* 137 */         int color_emerals = getColor("ffffffff");
/*     */         
/* 139 */         String text_diamonds = "" + this.COUNT_DIAMONDS;
/* 140 */         int color_diamonds = getColor("ffffffff");
/*     */ 
/*     */ 
/*     */         
/* 144 */         int font_emeralds_width = this.mc.field_71466_p.func_78256_a(text_emeralds);
/* 145 */         int font_emeralds_height = this.mc.field_71466_p.field_78288_b;
/* 146 */         int font_diamonds_width = this.mc.field_71466_p.func_78256_a(text_diamonds);
/* 147 */         int font_diamonds_height = this.mc.field_71466_p.field_78288_b;
/*     */         
/* 149 */         int offsetX = 0;
/* 150 */         if (this.COUNT_EMERALDS > 0) {
/* 151 */           if (text_emeralds.length() == 1) offsetX = 6; 
/* 152 */           if (text_emeralds.length() == 2) offsetX = 12; 
/* 153 */           if (text_emeralds.length() == 3) offsetX = 18;
/*     */           
/* 155 */           int offsetY = 5;
/* 156 */           int topX = screen_width - offsetX - this.item_emerald.width;
/* 157 */           int topY = screen_height - offsetY - this.item_emerald.height;
/* 158 */           int bottomX = screen_width - offsetX;
/* 159 */           int bottomY = screen_height - offsetY;
/*     */           
/* 161 */           this.item_emerald.drawOnGui(topX, topY);
/* 162 */           this.mc.field_71466_p.func_175065_a(text_emeralds, (bottomX - 1), (bottomY - 4), color_emerals, true);
/*     */         } 
/*     */         
/* 165 */         if (this.COUNT_DIAMONDS > 0) {
/* 166 */           if (text_diamonds.length() == 1) offsetX += 6; 
/* 167 */           if (text_diamonds.length() == 2) offsetX += 12; 
/* 168 */           if (text_diamonds.length() == 3) offsetX += 18;
/*     */           
/* 170 */           if (this.COUNT_EMERALDS > 0) offsetX += 12;
/*     */           
/* 172 */           int offsetY = 5;
/* 173 */           int topX = screen_width - offsetX - this.item_diamond.width;
/* 174 */           int topY = screen_height - offsetY - this.item_diamond.height;
/* 175 */           int bottomX = screen_width - offsetX;
/* 176 */           int bottomY = screen_height - offsetY;
/*     */           
/* 178 */           this.item_diamond.drawOnGui(topX, topY - 1);
/* 179 */           this.mc.field_71466_p.func_175065_a(text_diamonds, (bottomX - 1), (bottomY - 4), color_diamonds, true);
/*     */         } 
/*     */       } 
/* 182 */       if (this.isPotionEffectTrackerActive) {
/*     */ 
/*     */         
/* 185 */         EntityPlayerSP player = (Minecraft.func_71410_x()).field_71439_g;
/* 186 */         Collection<PotionEffect> pe = player.func_70651_bq();
/* 187 */         if (pe.size() > 0) {
/*     */           
/* 189 */           if (this.textureManager == null) this.textureManager = this.mc.func_110434_K();
/*     */ 
/*     */           
/* 192 */           ArrayList<PotionEffect> potion_effects = new ArrayList<PotionEffect>();
/* 193 */           BWItemsHandler.BWItemPotionsID[] potions_id_to_display = { BWItemsHandler.BWItemPotionsID.STRENGTH, BWItemsHandler.BWItemPotionsID.JUMP, BWItemsHandler.BWItemPotionsID.SPEED, BWItemsHandler.BWItemPotionsID.REGEN };
/*     */ 
/*     */           
/* 196 */           for (int i = 0; i < potions_id_to_display.length; i++) {
/* 197 */             for (PotionEffect effect : pe) {
/* 198 */               if (effect.func_76456_a() == (potions_id_to_display[i]).id) {
/* 199 */                 potion_effects.add(effect);
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/* 205 */           float scale = 3.0F;
/* 206 */           int cx = 5;
/* 207 */           int cy = screen_height / 2;
/* 208 */           int space_y = 30;
/* 209 */           int start_y = cy - space_y * potion_effects.size() / 2;
/* 210 */           if (potion_effects.size() % 2 == 0) start_y += space_y / 2; 
/* 211 */           int current_y = start_y;
/*     */           
/* 213 */           for (PotionEffect p : potion_effects) {
/* 214 */             BWItemsHandler.BWItemPotionsID potion = null;
/*     */             
/* 216 */             int texX = 0;
/* 217 */             int texY = 0;
/* 218 */             Color color = new Color(1.0F, 1.0F, 1.0F, 1.0F);
/* 219 */             String text = "-";
/* 220 */             this.mc.field_71446_o.func_110577_a(this.resourceLoc_potions);
/*     */             
/* 222 */             if (BWItemsHandler.BWItemPotionsID.STRENGTH.id == p.func_76456_a()) {
/* 223 */               texX = 0;
/* 224 */               texY = 0;
/* 225 */               color = new Color(1.0F, 0.0F, 0.0F, 1.0F);
/* 226 */               text = "Силка";
/* 227 */               potion = BWItemsHandler.BWItemPotionsID.STRENGTH;
/*     */             } 
/* 229 */             if (BWItemsHandler.BWItemPotionsID.JUMP.id == p.func_76456_a()) {
/* 230 */               texX = 1;
/* 231 */               texY = 0;
/* 232 */               potion = BWItemsHandler.BWItemPotionsID.JUMP;
/* 233 */               text = "Прыжок";
/* 234 */               color = new Color(0.0F, 1.0F, 0.0F, 1.0F);
/*     */             } 
/* 236 */             if (BWItemsHandler.BWItemPotionsID.SPEED.id == p.func_76456_a()) {
/* 237 */               texX = 2;
/* 238 */               texY = 0;
/* 239 */               color = new Color(0.0F, 1.0F, 1.0F, 1.0F);
/* 240 */               text = "Скорка";
/* 241 */               potion = BWItemsHandler.BWItemPotionsID.SPEED;
/*     */             } 
/* 243 */             if (BWItemsHandler.BWItemPotionsID.REGEN.id == p.func_76456_a()) {
/* 244 */               texX = 0;
/* 245 */               texY = 1;
/* 246 */               color = new Color(1.0F, 0.0F, 1.0F, 1.0F);
/* 247 */               text = "Реген";
/* 248 */               potion = BWItemsHandler.BWItemPotionsID.REGEN;
/*     */             } 
/* 250 */             if (potion == null)
/*     */               continue; 
/* 252 */             GlStateManager.func_179094_E();
/*     */             
/* 254 */             GlStateManager.func_179109_b(cx, current_y, 0.0F);
/* 255 */             GlStateManager.func_179152_a(1.0F / scale, 1.0F / scale, 1.0F / scale);
/* 256 */             GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 257 */             func_73729_b(0, -40, texX * 80, texY * 80, 80, 80);
/*     */ 
/*     */             
/* 260 */             GlStateManager.func_179121_F();
/*     */             
/* 262 */             this.mc.field_71466_p.func_175065_a(text, cx + 80.0F / scale + 5.0F, current_y - 30.0F / scale, color.getRGB(), false);
/*     */             
/* 264 */             int time = p.func_76459_b() / 20;
/* 265 */             String s_time = "" + time + "s";
/* 266 */             if (time > 60) {
/* 267 */               int mins = time / 60;
/* 268 */               int secs = time % 60;
/* 269 */               s_time = mins + ":" + ((secs < 10) ? "0" : "") + secs;
/*     */             } 
/* 271 */             Color text_color = new Color(1.0F, 1.0F, 1.0F, 1.0F);
/* 272 */             boolean isShadow = false;
/* 273 */             if (p.func_76459_b() <= 120) {
/* 274 */               text_color = new Color(1.0F, 0.0F, 0.0F, 1.0F);
/* 275 */               isShadow = true;
/*     */             } 
/*     */             
/* 278 */             if (this.isPotionEffectTrackerSoundsActive && p.func_76459_b() == 120 && potion.id != BWItemsHandler.BWItemPotionsID.REGEN.id) {
/* 279 */               long t = (new Date()).getTime();
/* 280 */               if (t - this.lastPlaySoundTime > 100L) {
/* 281 */                 MyChatListener.playSound("note.hat", 1.0F);
/* 282 */                 this.lastPlaySoundTime = t;
/*     */               } 
/*     */             } 
/* 285 */             if (this.isPotionEffectTrackerSoundsActive && p.func_76459_b() == 3 && potion.id != BWItemsHandler.BWItemPotionsID.REGEN.id) {
/* 286 */               long t = (new Date()).getTime();
/* 287 */               if (t - this.lastPlaySoundTime > 100L) {
/* 288 */                 MyChatListener.playSound("random.fizz", 1.0F);
/* 289 */                 this.lastPlaySoundTime = t;
/*     */               } 
/*     */             } 
/*     */             
/* 293 */             this.mc.field_71466_p.func_175065_a(s_time, cx + 80.0F / scale + 5.0F, current_y, text_color.getRGB(), isShadow);
/*     */             
/* 295 */             current_y += space_y;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 301 */       if (this.keyTab.func_151470_d()) {
/*     */         
/* 303 */         GlStateManager.func_179094_E();
/*     */         
/* 305 */         float scale = 1.0F;
/* 306 */         GlStateManager.func_179109_b(0.0F, 0.0F, 10.0F);
/* 307 */         GlStateManager.func_179152_a(1.0F / scale, 1.0F / scale, 1.0F / scale);
/* 308 */         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 313 */         int padding = 5;
/* 314 */         int y = (int)((GuiMinimap.map_size + padding) * scale);
/* 315 */         int x = (int)(padding * scale);
/*     */         
/* 317 */         ArrayList<SameModePlayers.BroPlayer> mod_users = new ArrayList<SameModePlayers.BroPlayer>();
/* 318 */         mod_users.addAll(Main.sameModePlayers.getActivePlayers());
/* 319 */         ArrayList<SameModePlayers.BroPlayer> lobby_users = new ArrayList<SameModePlayers.BroPlayer>();
/*     */         
/* 321 */         for (SameModePlayers.BroPlayer bro : mod_users) {
/* 322 */           if (HelloDimChig.isPlayerOnServer(bro.name)) lobby_users.add(bro);
/*     */         
/*     */         } 
/* 325 */         if (lobby_users != null && lobby_users.size() > 0) {
/* 326 */           int color = (new Color(1.0F, 1.0F, 1.0F, 1.0F)).getRGB();
/* 327 */           int color_bg = (new Color(0.0F, 0.0F, 0.0F, 0.1F)).getRGB();
/* 328 */           int line_size = this.mc.field_71466_p.field_78288_b;
/*     */           
/* 330 */           func_73734_a(x - 1, y, x + 1 + this.mc.field_71466_p.func_78256_a("Брошники:"), y + line_size, color_bg);
/* 331 */           this.mc.field_71466_p.func_175065_a(ColorCodesManager.replaceColorCodesInString("&fБрошники:"), x, y, color, true);
/*     */           
/* 333 */           y += line_size;
/*     */           
/* 335 */           for (SameModePlayers.BroPlayer bro : lobby_users) {
/* 336 */             String text = ColorCodesManager.replaceColorCodesInString(Main.namePlateRenderer.getPrefixForBro(bro) + "&f" + bro.name);
/* 337 */             func_73734_a(x - 1, y, x + 1 + this.mc.field_71466_p.func_78256_a(text), y + line_size, color_bg);
/* 338 */             this.mc.field_71466_p.func_175065_a(text, x, y, color, true);
/* 339 */             y += line_size;
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 344 */         GlStateManager.func_179121_F();
/*     */       } 
/*     */       
/* 347 */       Main.guiRadarIcon.draw();
/*     */       
/* 349 */       Main.emotesDrawer.draw2D();
/*     */     }  }
/*     */ 
/*     */   
/*     */   private int getColor(String hexColor) {
/* 354 */     Color colorNoAlpha = Color.decode("0x" + hexColor.substring(0, 6));
/* 355 */     int alpha = Integer.parseInt(hexColor.substring(6, 8), 16);
/* 356 */     return (new Color(colorNoAlpha.getRed(), colorNoAlpha.getGreen(), colorNoAlpha.getBlue(), alpha)).getRGB();
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\gui\GuiOnScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */