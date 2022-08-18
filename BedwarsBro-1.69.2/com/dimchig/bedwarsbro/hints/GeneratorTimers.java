/*     */ package com.dimchig.bedwarsbro.hints;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.Main;
/*     */ import com.dimchig.bedwarsbro.MyChatListener;
/*     */ import java.awt.Color;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.entity.boss.EntityDragon;
/*     */ import net.minecraft.entity.item.EntityArmorStand;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EntitySelectors;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GeneratorTimers
/*     */   extends Gui
/*     */ {
/*     */   static Minecraft mc;
/*     */   static boolean isActive = false;
/*  30 */   static int start_diamond_time = 30;
/*  31 */   static long time_last_diamond = 0L;
/*  32 */   static int time_diamond_max = start_diamond_time;
/*  33 */   static int time_diamond_buffer = time_diamond_max;
/*     */   
/*  35 */   static int start_emerald_time = 65;
/*  36 */   static long time_last_emerald = 0L;
/*  37 */   static int time_emerald_max = start_emerald_time;
/*  38 */   static int time_emerald_buffer = time_emerald_max;
/*     */   
/*  40 */   static long time_game_start = 0L;
/*     */   
/*  42 */   static int corner_position = 2;
/*     */   static boolean isAdvanced = false;
/*     */   static boolean isGameTime = false;
/*     */   static boolean isTimeline = false;
/*  46 */   static int timeline_width_percentage = 80;
/*     */   ResourceLocation resourceLoc_textures;
/*     */   
/*     */   public GeneratorTimers() {
/*  50 */     mc = Minecraft.func_71410_x();
/*  51 */     this.resourceLoc_textures = new ResourceLocation("bedwarschatmod:textures/gui/timeline_icons.png");
/*     */   }
/*     */   
/*     */   public void updateBooleans() {
/*  55 */     isActive = Main.getConfigBool(Main.CONFIG_MSG.GENERATOR_TIMERS);
/*  56 */     corner_position = Main.getConfigInt(Main.CONFIG_MSG.GENERATOR_TIMERS_POSITION);
/*  57 */     isAdvanced = Main.getConfigBool(Main.CONFIG_MSG.GENERATOR_TIMERS_ADVANCED);
/*  58 */     isTimeline = Main.getConfigBool(Main.CONFIG_MSG.GENERATOR_TIMERS_TIMELINE);
/*  59 */     isGameTime = Main.getConfigBool(Main.CONFIG_MSG.GENERATOR_TIMERS_GAME_TIME);
/*  60 */     timeline_width_percentage = Main.getConfigInt(Main.CONFIG_MSG.GENERATOR_TIMERS_TIMELINE_WIDTH);
/*     */   }
/*     */   
/*     */   public void setMaxTimeDiamonds(int new_time) {
/*  64 */     time_diamond_buffer = new_time;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxTimeEmeralds(int new_time) {
/*  70 */     time_emerald_buffer = new_time;
/*     */   }
/*     */   
/*     */   public void setHardTimeDiamonds(int hard_time) {
/*  74 */     time_last_diamond = (new Date()).getTime() - ((time_diamond_max - hard_time + 1) * 1000);
/*     */   }
/*     */   
/*     */   public void setHardTimeEmeralds(int hard_time) {
/*  78 */     time_last_emerald = (new Date()).getTime() - ((time_emerald_max - hard_time + 1) * 1000);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onTick() {
/*     */     try {
/*  86 */       List<EntityArmorStand> entities = mc.field_71441_e.func_175644_a(EntityArmorStand.class, EntitySelectors.field_94557_a);
/*  87 */       if (entities != null) {
/*  88 */         boolean isDiamondGenSet = false;
/*  89 */         boolean isEmeraldGenSet = false;
/*  90 */         for (EntityArmorStand en : entities) {
/*  91 */           if (en == null || en.func_145748_c_() == null)
/*  92 */             continue;  String name = en.func_145748_c_().func_150260_c();
/*     */           
/*  94 */           if (name.contains("енератор")) {
/*     */             
/*  96 */             String connected_stand_text = null;
/*     */             
/*  98 */             BlockPos minPos = new BlockPos(en.field_70165_t - 1.0D, en.field_70163_u - 1.0D, en.field_70161_v - 1.0D);
/*  99 */             BlockPos maxPos = new BlockPos(en.field_70165_t + 1.0D, en.field_70163_u + 1.0D, en.field_70161_v + 1.0D);
/* 100 */             AxisAlignedBB box = new AxisAlignedBB(minPos, maxPos);
/* 101 */             List<EntityArmorStand> armorStands = mc.field_71441_e.func_72872_a(EntityArmorStand.class, box);
/* 102 */             if (armorStands == null)
/* 103 */               continue;  for (EntityArmorStand en2 : armorStands) {
/* 104 */               if (en2 == null || en2.func_145748_c_() == null)
/* 105 */                 continue;  String en2_name = en2.func_145748_c_().func_150254_d();
/* 106 */               if (en2_name.contains("через")) {
/* 107 */                 connected_stand_text = en2_name;
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } 
/* 112 */             if (connected_stand_text == null) {
/*     */               continue;
/*     */             }
/* 115 */             int hard_time = -1;
/*     */             try {
/* 117 */               hard_time = Integer.parseInt(connected_stand_text.split("§c")[1].trim().split(" ")[0].trim());
/* 118 */             } catch (Exception exception) {}
/* 119 */             if (hard_time != -1) {
/* 120 */               if (!isDiamondGenSet && name.contains("алмаз")) {
/* 121 */                 Main.generatorTimers.setHardTimeDiamonds(hard_time);
/* 122 */                 isDiamondGenSet = true; continue;
/*     */               } 
/* 124 */               if (!isEmeraldGenSet && name.contains("изумр")) {
/* 125 */                 Main.generatorTimers.setHardTimeEmeralds(hard_time);
/* 126 */                 isEmeraldGenSet = true;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 132 */     } catch (Exception ex) {
/* 133 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setStartTimesOnGameStart() {
/* 138 */     start_diamond_time = 30;
/* 139 */     long t = (new Date()).getTime();
/*     */     
/* 141 */     time_last_diamond = t;
/* 142 */     time_diamond_max = start_diamond_time;
/* 143 */     time_diamond_buffer = start_diamond_time;
/*     */     
/* 145 */     time_last_emerald = t;
/* 146 */     time_emerald_max = start_emerald_time;
/* 147 */     time_emerald_buffer = start_emerald_time;
/*     */     
/* 149 */     time_game_start = t;
/*     */   }
/*     */   
/*     */   public void draw(int screen_width, int screen_height) {
/* 153 */     if (isActive) if (MyChatListener.IS_IN_GAME) {
/*     */         
/* 155 */         int padding = 6;
/* 156 */         int gameTime_posX = padding;
/* 157 */         int gameTime_posY = padding;
/*     */ 
/*     */         
/* 160 */         if (corner_position == 1) {
/* 161 */           gameTime_posX = 2;
/* 162 */           gameTime_posY = padding;
/* 163 */         } else if (corner_position == 2) {
/* 164 */           gameTime_posX = screen_width - 23 - padding;
/* 165 */           gameTime_posY = padding;
/* 166 */         } else if (corner_position == 3) {
/* 167 */           gameTime_posX = screen_width - 23 - padding;
/* 168 */           gameTime_posY = screen_height - 24 - padding + (isAdvanced ? -16 : 0) + (isGameTime ? -16 : 0) - 18;
/*     */         } else {
/* 170 */           gameTime_posX = 2;
/* 171 */           gameTime_posY = screen_height - 24 - padding + (isAdvanced ? -16 : 0) + (isGameTime ? -16 : 0);
/*     */         } 
/*     */         
/* 174 */         float opacity = 0.1F;
/*     */         
/* 176 */         long t = (new Date()).getTime();
/*     */         
/* 178 */         boolean isAliningToLeft = (corner_position == 1 || corner_position == 4);
/*     */         
/* 180 */         if (isGameTime) {
/*     */           
/* 182 */           int game_time = Math.max((int)((float)(t - time_game_start) / 1000.0F) - 1, 0);
/* 183 */           int j = game_time % 60;
/* 184 */           String text_gameTime = (game_time / 60) + ":" + ((j >= 10) ? "" : "0") + j;
/*     */           
/* 186 */           int k = mc.field_71466_p.func_78256_a(text_gameTime) + padding + mc.field_71466_p.func_78256_a(text_gameTime) / 2;
/*     */ 
/*     */ 
/*     */           
/* 190 */           int m = gameTime_posX + 1;
/*     */           
/* 192 */           if (!isAliningToLeft) {
/* 193 */             m = screen_width - k - 3;
/*     */           }
/*     */           
/* 196 */           Gui.func_73734_a(m - 1, gameTime_posY - 4, m + k, gameTime_posY + 12, (new Color(0.0F, 0.0F, 0.0F, opacity)).getRGB());
/*     */           
/* 198 */           int color_gameTime = (new Color(1.0F, 1.0F, 1.0F, 1.0F)).getRGB();
/*     */           
/* 200 */           float scale_clock = 0.8F;
/* 201 */           mc.field_71446_o.func_110577_a(this.resourceLoc_textures);
/* 202 */           GlStateManager.func_179094_E();
/* 203 */           GlStateManager.func_179141_d();
/* 204 */           GlStateManager.func_179109_b((m + 7), (gameTime_posY + 3), 0.0F);
/* 205 */           GlStateManager.func_179152_a(scale_clock, scale_clock, scale_clock);
/* 206 */           GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 207 */           func_73729_b(-6, -6, 0, 0, 12, 12);
/* 208 */           GlStateManager.func_179121_F();
/*     */           
/* 210 */           mc.field_71466_p.func_175065_a(text_gameTime, (m + 15), gameTime_posY, color_gameTime, true);
/*     */         } 
/*     */ 
/*     */         
/* 214 */         int diamonds_posX = gameTime_posX;
/* 215 */         int diamonds_posY = gameTime_posY + (isGameTime ? 16 : 0);
/*     */ 
/*     */         
/* 218 */         int time_diamonds = (int)((float)(t - time_last_diamond) / 1000.0F);
/*     */         
/* 220 */         if (time_diamonds > time_diamond_max) {
/* 221 */           time_last_diamond = t;
/* 222 */           time_diamond_max = time_diamond_buffer;
/*     */         } 
/*     */         
/* 225 */         int time_diamonds_diff = time_diamond_max - time_diamonds + 1;
/*     */         
/* 227 */         String text_diamonds = "" + time_diamonds_diff;
/* 228 */         int color_diamonds = (new Color(0.0F, 1.0F, 1.0F, 1.0F)).getRGB();
/*     */         
/* 230 */         if (time_diamonds_diff > time_diamond_max) {
/* 231 */           text_diamonds = "0";
/* 232 */           color_diamonds = (new Color(1.0F, 0.0F, 0.0F, 1.0F)).getRGB();
/*     */         } 
/*     */         
/* 235 */         Gui.func_73734_a(diamonds_posX, diamonds_posY - 4, diamonds_posX + 26, diamonds_posY + 12, (new Color(0.0F, 0.0F, 0.0F, opacity)).getRGB());
/*     */         
/* 237 */         mc.func_175599_af().func_175042_a(new ItemStack(Items.field_151045_i), diamonds_posX - 2, diamonds_posY - 5);
/* 238 */         mc.field_71466_p.func_175065_a(text_diamonds, (diamonds_posX + 19 - mc.field_71466_p.func_78256_a(text_diamonds) / 2), diamonds_posY, color_diamonds, true);
/*     */ 
/*     */         
/* 241 */         int time_emeralds = (int)((float)(t - time_last_emerald) / 1000.0F);
/*     */         
/* 243 */         if (time_emeralds > time_emerald_max) {
/* 244 */           time_last_emerald = t;
/* 245 */           time_emerald_max = time_emerald_buffer;
/*     */         } 
/*     */         
/* 248 */         int time_emeralds_diff = time_emerald_max - time_emeralds + 1;
/*     */         
/* 250 */         String text_emeralds = "" + time_emeralds_diff;
/* 251 */         int color_emeralds = (new Color(0.0F, 1.0F, 0.0F, 1.0F)).getRGB();
/*     */         
/* 253 */         if (time_emeralds_diff > time_emerald_max) {
/* 254 */           text_emeralds = "0";
/* 255 */           color_emeralds = (new Color(1.0F, 0.0F, 0.0F, 1.0F)).getRGB();
/*     */         } 
/*     */         
/* 258 */         int emeralds_posX = diamonds_posX;
/* 259 */         int emeralds_posY = diamonds_posY + 16;
/*     */         
/* 261 */         Gui.func_73734_a(emeralds_posX, emeralds_posY - 4, emeralds_posX + 26, emeralds_posY + 12, (new Color(0.0F, 0.0F, 0.0F, opacity)).getRGB());
/*     */         
/* 263 */         mc.func_175599_af().func_175042_a(new ItemStack(Items.field_151166_bC), emeralds_posX - 2, emeralds_posY - 4);
/* 264 */         mc.field_71466_p.func_175065_a(text_emeralds, (emeralds_posX + 19 - mc.field_71466_p.func_78256_a(text_emeralds) / 2), emeralds_posY, color_emeralds, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 270 */         int[] times = { 300, 450, 900, 1400, 2400, 3000 };
/* 271 */         int diff = (int)((float)(t - time_game_start) / 1000.0F) - 1;
/* 272 */         String name = "";
/*     */         
/* 274 */         if (isTimeline) drawTimeline(diff, times, screen_width, screen_height);
/*     */         
/* 276 */         if (!isAdvanced)
/*     */           return; 
/* 278 */         int nearest_upgrate_time = -1;
/* 279 */         for (int i = 0; i < times.length; i++) {
/* 280 */           if (diff < times[i]) {
/* 281 */             nearest_upgrate_time = times[i] - diff - 1;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 286 */         if (nearest_upgrate_time == -1)
/*     */           return; 
/* 288 */         int seconds = nearest_upgrate_time % 60;
/* 289 */         String time = (nearest_upgrate_time / 60) + ":" + ((seconds >= 10) ? "" : "0") + seconds;
/* 290 */         int color = (new Color(1.0F, 1.0F, 1.0F, 1.0F)).getRGB();
/*     */ 
/*     */ 
/*     */         
/* 294 */         int advanced_posX = diamonds_posX;
/* 295 */         if (isAliningToLeft) advanced_posX = diamonds_posX; 
/* 296 */         int advanced_posY = emeralds_posY + 16;
/*     */         
/* 298 */         int offsetX = 0;
/* 299 */         ItemStack itemStack = new ItemStack(Items.field_151126_ay);
/* 300 */         double itemStackOffsetX = 0.0D;
/* 301 */         double itemStackOffsetY = 0.0D;
/* 302 */         int itemStackWidth = 0;
/* 303 */         float scale = 1.0F;
/*     */         
/* 305 */         if (diff < times[0]) {
/* 306 */           name = "I";
/* 307 */           color = (new Color(0.0F, 1.0F, 1.0F, 1.0F)).getRGB();
/* 308 */           itemStack = new ItemStack(Items.field_151045_i);
/* 309 */           itemStackOffsetX = -2.0D;
/* 310 */           itemStackOffsetY = -5.0D;
/* 311 */           itemStackWidth = 12;
/* 312 */         } else if (diff < times[1]) {
/* 313 */           name = "II";
/* 314 */           color = (new Color(0.0F, 1.0F, 1.0F, 1.0F)).getRGB();
/* 315 */           itemStack = new ItemStack(Items.field_151045_i);
/* 316 */           itemStackOffsetX = -2.0D;
/* 317 */           itemStackOffsetY = -5.0D;
/* 318 */           itemStackWidth = 12;
/* 319 */         } else if (diff < times[2]) {
/* 320 */           name = "I";
/* 321 */           color = (new Color(0.0F, 1.0F, 0.0F, 1.0F)).getRGB();
/* 322 */           itemStack = new ItemStack(Items.field_151166_bC);
/* 323 */           itemStackOffsetX = -2.0D;
/* 324 */           itemStackOffsetY = -4.0D;
/* 325 */           itemStackWidth = 11;
/* 326 */         } else if (diff < times[3]) {
/* 327 */           name = "II";
/* 328 */           color = (new Color(0.0F, 1.0F, 0.0F, 1.0F)).getRGB();
/* 329 */           itemStack = new ItemStack(Items.field_151166_bC);
/* 330 */           itemStackOffsetX = -2.0D;
/* 331 */           itemStackOffsetY = -4.0D;
/* 332 */           itemStackWidth = 11;
/* 333 */         } else if (diff < times[4]) {
/* 334 */           name = "Без кроватей";
/* 335 */           color = (new Color(1.0F, 0.0F, 0.0F, 1.0F)).getRGB();
/* 336 */           itemStack = new ItemStack(Items.field_151104_aV);
/* 337 */           itemStackOffsetX = 0.0D;
/* 338 */           itemStackOffsetY = -5.0D;
/* 339 */           itemStackWidth = 15;
/* 340 */         } else if (diff < times[5]) {
/* 341 */           name = "Конец игры";
/* 342 */           color = (new Color(1.0F, 1.0F, 1.0F, 1.0F)).getRGB();
/* 343 */           itemStack = new ItemStack(Item.func_150898_a(Blocks.field_180401_cv));
/* 344 */           itemStackOffsetX = 1.0D;
/* 345 */           itemStackOffsetY = -2.5D;
/* 346 */           itemStackWidth = 10;
/* 347 */           scale = 0.8F;
/*     */         } 
/*     */         
/* 350 */         padding = 15;
/*     */         
/* 352 */         int total_width = mc.field_71466_p.func_78256_a(name) + itemStackWidth + padding + mc.field_71466_p.func_78256_a(time) / 2;
/*     */         
/* 354 */         int px = advanced_posX + 1;
/*     */         
/* 356 */         if (!isAliningToLeft) {
/* 357 */           px = screen_width - total_width - 3;
/*     */         }
/*     */         
/* 360 */         Gui.func_73734_a(px - 1, advanced_posY - 4, px + total_width, advanced_posY + 12, (new Color(0.0F, 0.0F, 0.0F, opacity)).getRGB());
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 365 */         mc.field_71466_p.func_175065_a(name, px, advanced_posY, color, true);
/* 366 */         px += mc.field_71466_p.func_78256_a(name);
/*     */         
/* 368 */         if (scale != 1.0F) {
/* 369 */           GlStateManager.func_179094_E();
/* 370 */           GlStateManager.func_179152_a(scale, scale, scale);
/*     */         } 
/*     */         
/* 373 */         mc.func_175599_af().func_175042_a(itemStack, (int)((px + itemStackOffsetX) / scale), (int)((advanced_posY + itemStackOffsetY) / scale));
/*     */         
/* 375 */         if (scale != 1.0F) GlStateManager.func_179121_F();
/*     */         
/* 377 */         px += itemStackWidth;
/*     */ 
/*     */         
/* 380 */         px += padding;
/* 381 */         mc.field_71466_p.func_175065_a(time, (px - mc.field_71466_p.func_78256_a(time) / 2), advanced_posY, color, true);
/*     */         return;
/*     */       }  
/*     */   } public void drawTimeline(int game_time, int[] times, int screen_width, int screen_height) {
/* 385 */     int margin_x = 40;
/* 386 */     int margin_y = 12;
/* 387 */     int offset_from_right = 20;
/* 388 */     int tm_width = (int)((screen_width * timeline_width_percentage) / 100.0F) - margin_x;
/* 389 */     tm_width = tm_width * 2 / 2;
/*     */     
/* 391 */     int tm_height = 2;
/*     */     
/* 393 */     int x1 = (screen_width - tm_width) / 2;
/* 394 */     tm_width += -offset_from_right;
/* 395 */     int x2 = x1 + tm_width;
/*     */     
/* 397 */     List<EntityDragon> dragons = mc.field_71441_e.func_175644_a(EntityDragon.class, EntitySelectors.field_94557_a);
/* 398 */     if (dragons != null && dragons.size() > 0) {
/* 399 */       margin_y += 17;
/* 400 */       int py = margin_y + 20;
/* 401 */       for (EntityDragon dragon : dragons) {
/* 402 */         if (dragon == null || dragon.func_145748_c_() == null)
/*     */           continue; 
/* 404 */         String health_s = "" + (int)(dragon.func_110143_aJ() / 2.0F) + "%";
/* 405 */         mc.field_71466_p.func_78276_b(dragon.func_145748_c_().func_150254_d() + "" + EnumChatFormatting.GRAY + " ▸ " + EnumChatFormatting.RED + health_s, x1, py, (new Color(1.0F, 1.0F, 1.0F, 1.0F)).getRGB());
/* 406 */         py += 10;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 412 */     int y1 = margin_y;
/* 413 */     int y2 = y1 + tm_height;
/*     */     
/* 415 */     Gui.func_73734_a(x1, y1, x2, y2, (new Color(1.0F, 1.0F, 1.0F, 1.0F)).getRGB());
/*     */ 
/*     */     
/* 418 */     int[] anchor_times = new int[times.length + 2];
/* 419 */     for (int i = 0; i < times.length; i++) {
/* 420 */       anchor_times[i + 1] = times[i];
/*     */     }
/* 422 */     anchor_times[0] = 0;
/* 423 */     anchor_times[anchor_times.length - 1] = 4000;
/*     */ 
/*     */     
/* 426 */     float scaling_factor = tm_width * 1.0F / times[times.length - 1];
/* 427 */     int stick_height = 10;
/* 428 */     int stick_width = 2;
/*     */     
/* 430 */     int color_lightblue = (new Color(0.0F, 1.0F, 1.0F, 1.0F)).getRGB();
/* 431 */     int color_green = (new Color(0.0F, 1.0F, 0.0F, 1.0F)).getRGB();
/* 432 */     int color_purple = (new Color(0.69803923F, 0.32941177F, 1.0F, 1.0F)).getRGB();
/* 433 */     int color_white = (new Color(1.0F, 1.0F, 1.0F, 1.0F)).getRGB();
/* 434 */     int color_black = (new Color(0.0F, 0.0F, 0.0F, 1.0F)).getRGB();
/*     */     
/* 436 */     for (int j = 1; j < anchor_times.length; j++) {
/* 437 */       int tx1 = x1 + (int)(scaling_factor * anchor_times[j - 1]);
/* 438 */       int tx2 = x1 + (int)(scaling_factor * anchor_times[j]);
/*     */       
/* 440 */       String text_name = "-";
/* 441 */       ItemStack itemStack = new ItemStack(Blocks.field_150348_b);
/*     */ 
/*     */       
/* 444 */       int color = color_black;
/* 445 */       if (j == 1) {
/* 446 */         color = color_white;
/* 447 */         text_name = "";
/* 448 */         itemStack = null;
/* 449 */       } else if (j == 2) {
/* 450 */         color = color_lightblue;
/* 451 */         text_name = "I";
/* 452 */         itemStack = new ItemStack(Items.field_151045_i);
/* 453 */       } else if (j == 3) {
/* 454 */         color = color_lightblue;
/* 455 */         text_name = "II";
/* 456 */         itemStack = new ItemStack(Items.field_151045_i);
/* 457 */       } else if (j == 4) {
/* 458 */         color = color_green;
/* 459 */         text_name = "I";
/* 460 */         itemStack = new ItemStack(Items.field_151166_bC);
/* 461 */       } else if (j == 5) {
/* 462 */         color = color_green;
/* 463 */         text_name = "II";
/* 464 */         itemStack = new ItemStack(Items.field_151166_bC);
/* 465 */       } else if (j >= 6) {
/* 466 */         color = color_purple;
/* 467 */         text_name = "Драконы";
/* 468 */         itemStack = new ItemStack(Item.func_150898_a(Blocks.field_150380_bt));
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 473 */       Gui.func_73734_a(tx1 - stick_width / 2, y1 - stick_height / 2, tx1 + stick_width / 2, y2 + stick_height / 2, color);
/*     */       
/* 475 */       int label_time = anchor_times[j - 1];
/* 476 */       int seconds = label_time % 60;
/* 477 */       String text_time = (label_time / 60) + ":" + ((seconds >= 10) ? "" : "0") + seconds;
/* 478 */       float width = mc.field_71466_p.func_78256_a(text_time);
/* 479 */       mc.field_71466_p.func_175065_a(text_time, tx1 - width / 2.0F, (y2 + stick_height / 2 + 1), color, true);
/*     */       
/* 481 */       if (j != 7) {
/*     */         
/* 483 */         Gui.func_73734_a(tx1, y1, tx2, y2, color);
/*     */         
/* 485 */         if (itemStack != null) {
/* 486 */           width = mc.field_71466_p.func_78256_a(text_name);
/* 487 */           int px = (tx1 + tx2) / 2 - 4;
/* 488 */           int py = y1 - stick_height / 2 - 5;
/* 489 */           mc.field_71466_p.func_175065_a(text_name, px - width / 2.0F, py, color, true);
/*     */           
/* 491 */           float f = 0.6F;
/* 492 */           GlStateManager.func_179094_E();
/* 493 */           GlStateManager.func_179152_a(f, f, f);
/* 494 */           mc.func_175599_af().func_175042_a(itemStack, (int)((px + width / 2.0F) / f), (int)(py / f) - 0);
/* 495 */           GlStateManager.func_179121_F();
/*     */         } 
/*     */       } 
/*     */     } 
/* 499 */     if (!isGameTime) {
/*     */ 
/*     */ 
/*     */       
/* 503 */       game_time = (game_time > 0) ? game_time : 0;
/* 504 */       int offsetX = 10;
/* 505 */       int seconds = game_time % 60;
/* 506 */       String text_time = (game_time / 60) + ":" + ((seconds >= 10) ? "" : "0") + seconds;
/* 507 */       float width = mc.field_71466_p.func_78256_a(text_time);
/* 508 */       mc.field_71466_p.func_175065_a(text_time, (x2 + offsetX + 7), (y2 - 4), (new Color(1.0F, 1.0F, 1.0F, 1.0F)).getRGB(), true);
/*     */       
/* 510 */       float f1 = 0.8F;
/* 511 */       mc.field_71446_o.func_110577_a(this.resourceLoc_textures);
/* 512 */       GlStateManager.func_179094_E();
/* 513 */       GlStateManager.func_179141_d();
/* 514 */       GlStateManager.func_179109_b((x2 + offsetX), y2, 0.0F);
/* 515 */       GlStateManager.func_179152_a(f1, f1, f1);
/* 516 */       GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 517 */       func_73729_b(-6, -6, 0, 0, 12, 12);
/* 518 */       GlStateManager.func_179121_F();
/*     */     } 
/*     */ 
/*     */     
/* 522 */     mc.field_71446_o.func_110577_a(this.resourceLoc_textures);
/* 523 */     float scale = 0.5F;
/* 524 */     GlStateManager.func_179094_E();
/* 525 */     GlStateManager.func_179109_b(x1 + game_time * scaling_factor, ((y1 + y2) / 2), 0.0F);
/* 526 */     GlStateManager.func_179152_a(scale, scale, scale);
/* 527 */     GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 528 */     func_73729_b(-8, -8, 12, 0, 16, 16);
/* 529 */     GlStateManager.func_179121_F();
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\hints\GeneratorTimers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */