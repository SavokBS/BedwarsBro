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
/*     */   static boolean isTimeline = false;
/*  45 */   static int timeline_width_percentage = 80;
/*     */   ResourceLocation resourceLoc_textures;
/*     */   
/*     */   public GeneratorTimers() {
/*  49 */     mc = Minecraft.func_71410_x();
/*  50 */     this.resourceLoc_textures = new ResourceLocation("bedwarschatmod:textures/gui/timeline_icons.png");
/*     */   }
/*     */   
/*     */   public void updateBooleans() {
/*  54 */     isActive = Main.getConfigBool(Main.CONFIG_MSG.GENERATOR_TIMERS);
/*  55 */     corner_position = Main.getConfigInt(Main.CONFIG_MSG.GENERATOR_TIMERS_POSITION);
/*  56 */     isAdvanced = Main.getConfigBool(Main.CONFIG_MSG.GENERATOR_TIMERS_ADVANCED);
/*  57 */     isTimeline = Main.getConfigBool(Main.CONFIG_MSG.GENERATOR_TIMERS_TIMELINE);
/*  58 */     timeline_width_percentage = Main.getConfigInt(Main.CONFIG_MSG.GENERATOR_TIMERS_TIMELINE_WIDTH);
/*     */   }
/*     */   
/*     */   public void setMaxTimeDiamonds(int new_time) {
/*  62 */     time_diamond_buffer = new_time;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxTimeEmeralds(int new_time) {
/*  68 */     time_emerald_buffer = new_time;
/*     */   }
/*     */   
/*     */   public void setHardTimeDiamonds(int hard_time) {
/*  72 */     time_last_diamond = (new Date()).getTime() - ((time_diamond_max - hard_time + 1) * 1000);
/*     */   }
/*     */   
/*     */   public void setHardTimeEmeralds(int hard_time) {
/*  76 */     time_last_emerald = (new Date()).getTime() - ((time_emerald_max - hard_time + 1) * 1000);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onTick() {
/*     */     try {
/*  84 */       List<EntityArmorStand> entities = mc.field_71441_e.func_175644_a(EntityArmorStand.class, EntitySelectors.field_94557_a);
/*  85 */       if (entities != null) {
/*  86 */         boolean isDiamondGenSet = false;
/*  87 */         boolean isEmeraldGenSet = false;
/*  88 */         for (EntityArmorStand en : entities) {
/*  89 */           if (en == null || en.func_145748_c_() == null)
/*  90 */             continue;  String name = en.func_145748_c_().func_150260_c();
/*     */           
/*  92 */           if (name.contains("енератор")) {
/*     */             
/*  94 */             String connected_stand_text = null;
/*     */             
/*  96 */             BlockPos minPos = new BlockPos(en.field_70165_t - 1.0D, en.field_70163_u - 1.0D, en.field_70161_v - 1.0D);
/*  97 */             BlockPos maxPos = new BlockPos(en.field_70165_t + 1.0D, en.field_70163_u + 1.0D, en.field_70161_v + 1.0D);
/*  98 */             AxisAlignedBB box = new AxisAlignedBB(minPos, maxPos);
/*  99 */             List<EntityArmorStand> armorStands = mc.field_71441_e.func_72872_a(EntityArmorStand.class, box);
/* 100 */             if (armorStands == null)
/* 101 */               continue;  for (EntityArmorStand en2 : armorStands) {
/* 102 */               if (en2 == null || en2.func_145748_c_() == null)
/* 103 */                 continue;  String en2_name = en2.func_145748_c_().func_150254_d();
/* 104 */               if (en2_name.contains("через")) {
/* 105 */                 connected_stand_text = en2_name;
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } 
/* 110 */             if (connected_stand_text == null) {
/*     */               continue;
/*     */             }
/* 113 */             int hard_time = -1;
/*     */             try {
/* 115 */               hard_time = Integer.parseInt(connected_stand_text.split("§c")[1].trim().split(" ")[0].trim());
/* 116 */             } catch (Exception exception) {}
/* 117 */             if (hard_time != -1) {
/* 118 */               if (!isDiamondGenSet && name.contains("алмаз")) {
/* 119 */                 Main.generatorTimers.setHardTimeDiamonds(hard_time);
/* 120 */                 isDiamondGenSet = true; continue;
/*     */               } 
/* 122 */               if (!isEmeraldGenSet && name.contains("изумр")) {
/* 123 */                 Main.generatorTimers.setHardTimeEmeralds(hard_time);
/* 124 */                 isEmeraldGenSet = true;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 130 */     } catch (Exception ex) {
/* 131 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setStartTimesOnGameStart() {
/* 136 */     start_diamond_time = 30;
/* 137 */     long t = (new Date()).getTime();
/*     */     
/* 139 */     time_last_diamond = t;
/* 140 */     time_diamond_max = start_diamond_time;
/* 141 */     time_diamond_buffer = start_diamond_time;
/*     */     
/* 143 */     time_last_emerald = t;
/* 144 */     time_emerald_max = start_emerald_time;
/* 145 */     time_emerald_buffer = start_emerald_time;
/*     */     
/* 147 */     time_game_start = t;
/*     */   }
/*     */   
/*     */   public void draw(int screen_width, int screen_height) {
/* 151 */     if (isActive) if (MyChatListener.IS_IN_GAME) {
/*     */ 
/*     */         
/* 154 */         long t = (new Date()).getTime();
/* 155 */         int time_diamonds = (int)((float)(t - time_last_diamond) / 1000.0F);
/*     */         
/* 157 */         if (time_diamonds > time_diamond_max) {
/* 158 */           time_last_diamond = t;
/* 159 */           time_diamond_max = time_diamond_buffer;
/*     */         } 
/*     */         
/* 162 */         int time_diamonds_diff = time_diamond_max - time_diamonds + 1;
/*     */         
/* 164 */         String text_diamonds = "" + time_diamonds_diff;
/* 165 */         int color_diamonds = (new Color(0.0F, 1.0F, 1.0F, 1.0F)).getRGB();
/*     */         
/* 167 */         if (time_diamonds_diff > time_diamond_max) {
/* 168 */           text_diamonds = "0";
/* 169 */           color_diamonds = (new Color(1.0F, 0.0F, 0.0F, 1.0F)).getRGB();
/*     */         } 
/*     */         
/* 172 */         int padding = 6;
/* 173 */         int diamonds_posX = padding;
/* 174 */         int diamonds_posY = padding;
/*     */         
/* 176 */         if (corner_position == 1) {
/* 177 */           diamonds_posX = 2;
/* 178 */           diamonds_posY = padding;
/* 179 */         } else if (corner_position == 2) {
/* 180 */           diamonds_posX = screen_width - 23 - padding;
/* 181 */           diamonds_posY = padding;
/* 182 */         } else if (corner_position == 3) {
/* 183 */           diamonds_posX = screen_width - 23 - padding;
/* 184 */           diamonds_posY = screen_height - 24 - padding + (isAdvanced ? -16 : 0) - 18;
/*     */         } else {
/* 186 */           diamonds_posX = 2;
/* 187 */           diamonds_posY = screen_height - 24 - padding + (isAdvanced ? -16 : 0);
/*     */         } 
/*     */ 
/*     */         
/* 191 */         float opacity = 0.1F;
/* 192 */         Gui.func_73734_a(diamonds_posX, diamonds_posY - 4, diamonds_posX + 26, diamonds_posY + 12, (new Color(0.0F, 0.0F, 0.0F, opacity)).getRGB());
/*     */         
/* 194 */         mc.func_175599_af().func_175042_a(new ItemStack(Items.field_151045_i), diamonds_posX - 2, diamonds_posY - 5);
/* 195 */         mc.field_71466_p.func_175065_a(text_diamonds, (diamonds_posX + 19 - mc.field_71466_p.func_78256_a(text_diamonds) / 2), diamonds_posY, color_diamonds, true);
/*     */ 
/*     */         
/* 198 */         int time_emeralds = (int)((float)(t - time_last_emerald) / 1000.0F);
/*     */         
/* 200 */         if (time_emeralds > time_emerald_max) {
/* 201 */           time_last_emerald = t;
/* 202 */           time_emerald_max = time_emerald_buffer;
/*     */         } 
/*     */         
/* 205 */         int time_emeralds_diff = time_emerald_max - time_emeralds + 1;
/*     */         
/* 207 */         String text_emeralds = "" + time_emeralds_diff;
/* 208 */         int color_emeralds = (new Color(0.0F, 1.0F, 0.0F, 1.0F)).getRGB();
/*     */         
/* 210 */         if (time_emeralds_diff > time_emerald_max) {
/* 211 */           text_emeralds = "0";
/* 212 */           color_emeralds = (new Color(1.0F, 0.0F, 0.0F, 1.0F)).getRGB();
/*     */         } 
/*     */         
/* 215 */         int emeralds_posX = diamonds_posX;
/* 216 */         int emeralds_posY = diamonds_posY + 16;
/*     */         
/* 218 */         Gui.func_73734_a(emeralds_posX, emeralds_posY - 4, emeralds_posX + 26, emeralds_posY + 12, (new Color(0.0F, 0.0F, 0.0F, opacity)).getRGB());
/*     */         
/* 220 */         mc.func_175599_af().func_175042_a(new ItemStack(Items.field_151166_bC), emeralds_posX - 2, emeralds_posY - 4);
/* 221 */         mc.field_71466_p.func_175065_a(text_emeralds, (emeralds_posX + 19 - mc.field_71466_p.func_78256_a(text_emeralds) / 2), emeralds_posY, color_emeralds, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 227 */         int[] times = { 300, 450, 900, 1400, 2400, 3000 };
/* 228 */         int diff = (int)((float)(t - time_game_start) / 1000.0F) - 1;
/* 229 */         String name = "";
/*     */         
/* 231 */         if (isTimeline) drawTimeline(diff, times, screen_width, screen_height);
/*     */         
/* 233 */         if (!isAdvanced)
/*     */           return; 
/* 235 */         int nearest_upgrate_time = -1;
/* 236 */         for (int i = 0; i < times.length; i++) {
/* 237 */           if (diff < times[i]) {
/* 238 */             nearest_upgrate_time = times[i] - diff - 1;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 243 */         if (nearest_upgrate_time == -1)
/*     */           return; 
/* 245 */         int seconds = nearest_upgrate_time % 60;
/* 246 */         String time = (nearest_upgrate_time / 60) + ":" + ((seconds >= 10) ? "" : "0") + seconds;
/* 247 */         int color = (new Color(1.0F, 1.0F, 1.0F, 1.0F)).getRGB();
/*     */         
/* 249 */         boolean isAliningToLeft = (corner_position == 1 || corner_position == 4);
/*     */         
/* 251 */         int advanced_posX = diamonds_posX;
/* 252 */         if (isAliningToLeft) advanced_posX = diamonds_posX; 
/* 253 */         int advanced_posY = emeralds_posY + 16;
/*     */         
/* 255 */         int offsetX = 0;
/* 256 */         ItemStack itemStack = new ItemStack(Items.field_151126_ay);
/* 257 */         double itemStackOffsetX = 0.0D;
/* 258 */         double itemStackOffsetY = 0.0D;
/* 259 */         int itemStackWidth = 0;
/* 260 */         float scale = 1.0F;
/*     */         
/* 262 */         if (diff < times[0]) {
/* 263 */           name = "I";
/* 264 */           color = (new Color(0.0F, 1.0F, 1.0F, 1.0F)).getRGB();
/* 265 */           itemStack = new ItemStack(Items.field_151045_i);
/* 266 */           itemStackOffsetX = -2.0D;
/* 267 */           itemStackOffsetY = -5.0D;
/* 268 */           itemStackWidth = 12;
/* 269 */         } else if (diff < times[1]) {
/* 270 */           name = "II";
/* 271 */           color = (new Color(0.0F, 1.0F, 1.0F, 1.0F)).getRGB();
/* 272 */           itemStack = new ItemStack(Items.field_151045_i);
/* 273 */           itemStackOffsetX = -2.0D;
/* 274 */           itemStackOffsetY = -5.0D;
/* 275 */           itemStackWidth = 12;
/* 276 */         } else if (diff < times[2]) {
/* 277 */           name = "I";
/* 278 */           color = (new Color(0.0F, 1.0F, 0.0F, 1.0F)).getRGB();
/* 279 */           itemStack = new ItemStack(Items.field_151166_bC);
/* 280 */           itemStackOffsetX = -2.0D;
/* 281 */           itemStackOffsetY = -4.0D;
/* 282 */           itemStackWidth = 11;
/* 283 */         } else if (diff < times[3]) {
/* 284 */           name = "II";
/* 285 */           color = (new Color(0.0F, 1.0F, 0.0F, 1.0F)).getRGB();
/* 286 */           itemStack = new ItemStack(Items.field_151166_bC);
/* 287 */           itemStackOffsetX = -2.0D;
/* 288 */           itemStackOffsetY = -4.0D;
/* 289 */           itemStackWidth = 11;
/* 290 */         } else if (diff < times[4]) {
/* 291 */           name = "Без кроватей";
/* 292 */           color = (new Color(1.0F, 0.0F, 0.0F, 1.0F)).getRGB();
/* 293 */           itemStack = new ItemStack(Items.field_151104_aV);
/* 294 */           itemStackOffsetX = 0.0D;
/* 295 */           itemStackOffsetY = -5.0D;
/* 296 */           itemStackWidth = 15;
/* 297 */         } else if (diff < times[5]) {
/* 298 */           name = "Конец игры";
/* 299 */           color = (new Color(1.0F, 1.0F, 1.0F, 1.0F)).getRGB();
/* 300 */           itemStack = new ItemStack(Item.func_150898_a(Blocks.field_180401_cv));
/* 301 */           itemStackOffsetX = 1.0D;
/* 302 */           itemStackOffsetY = -2.5D;
/* 303 */           itemStackWidth = 10;
/* 304 */           scale = 0.8F;
/*     */         } 
/*     */         
/* 307 */         padding = 15;
/*     */         
/* 309 */         int total_width = mc.field_71466_p.func_78256_a(name) + itemStackWidth + padding + mc.field_71466_p.func_78256_a(time) / 2;
/*     */         
/* 311 */         int px = advanced_posX + 1;
/*     */         
/* 313 */         if (!isAliningToLeft) {
/* 314 */           px = screen_width - total_width - 3;
/*     */         }
/*     */         
/* 317 */         Gui.func_73734_a(px - 1, advanced_posY - 4, px + total_width, advanced_posY + 12, (new Color(0.0F, 0.0F, 0.0F, opacity)).getRGB());
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 322 */         mc.field_71466_p.func_175065_a(name, px, advanced_posY, color, true);
/* 323 */         px += mc.field_71466_p.func_78256_a(name);
/*     */         
/* 325 */         if (scale != 1.0F) {
/* 326 */           GlStateManager.func_179094_E();
/* 327 */           GlStateManager.func_179152_a(scale, scale, scale);
/*     */         } 
/*     */         
/* 330 */         mc.func_175599_af().func_175042_a(itemStack, (int)((px + itemStackOffsetX) / scale), (int)((advanced_posY + itemStackOffsetY) / scale));
/*     */         
/* 332 */         if (scale != 1.0F) GlStateManager.func_179121_F();
/*     */         
/* 334 */         px += itemStackWidth;
/*     */ 
/*     */         
/* 337 */         px += padding;
/* 338 */         mc.field_71466_p.func_175065_a(time, (px - mc.field_71466_p.func_78256_a(time) / 2), advanced_posY, color, true);
/*     */         return;
/*     */       }  
/*     */   } public void drawTimeline(int game_time, int[] times, int screen_width, int screen_height) {
/* 342 */     int margin_x = 40;
/* 343 */     int margin_y = 12;
/* 344 */     int offset_from_right = 20;
/* 345 */     int tm_width = (int)((screen_width * timeline_width_percentage) / 100.0F) - margin_x;
/* 346 */     tm_width = tm_width * 2 / 2;
/*     */     
/* 348 */     int tm_height = 2;
/*     */     
/* 350 */     int x1 = (screen_width - tm_width) / 2;
/* 351 */     tm_width += -offset_from_right;
/* 352 */     int x2 = x1 + tm_width;
/*     */     
/* 354 */     List<EntityDragon> dragons = mc.field_71441_e.func_175644_a(EntityDragon.class, EntitySelectors.field_94557_a);
/* 355 */     if (dragons != null && dragons.size() > 0) {
/* 356 */       margin_y += 17;
/* 357 */       int py = margin_y + 20;
/* 358 */       for (EntityDragon dragon : dragons) {
/* 359 */         if (dragon == null || dragon.func_145748_c_() == null)
/*     */           continue; 
/* 361 */         String health_s = "" + (int)(dragon.func_110143_aJ() / 2.0F) + "%";
/* 362 */         mc.field_71466_p.func_78276_b(dragon.func_145748_c_().func_150254_d() + "" + EnumChatFormatting.GRAY + " ▸ " + EnumChatFormatting.RED + health_s, x1, py, (new Color(1.0F, 1.0F, 1.0F, 1.0F)).getRGB());
/* 363 */         py += 10;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 369 */     int y1 = margin_y;
/* 370 */     int y2 = y1 + tm_height;
/*     */     
/* 372 */     Gui.func_73734_a(x1, y1, x2, y2, (new Color(1.0F, 1.0F, 1.0F, 1.0F)).getRGB());
/*     */ 
/*     */     
/* 375 */     int[] anchor_times = new int[times.length + 2];
/* 376 */     for (int i = 0; i < times.length; i++) {
/* 377 */       anchor_times[i + 1] = times[i];
/*     */     }
/* 379 */     anchor_times[0] = 0;
/* 380 */     anchor_times[anchor_times.length - 1] = 4000;
/*     */ 
/*     */     
/* 383 */     float scaling_factor = tm_width * 1.0F / times[times.length - 1];
/* 384 */     int stick_height = 10;
/* 385 */     int stick_width = 2;
/*     */     
/* 387 */     int color_lightblue = (new Color(0.0F, 1.0F, 1.0F, 1.0F)).getRGB();
/* 388 */     int color_green = (new Color(0.0F, 1.0F, 0.0F, 1.0F)).getRGB();
/* 389 */     int color_purple = (new Color(0.69803923F, 0.32941177F, 1.0F, 1.0F)).getRGB();
/* 390 */     int color_white = (new Color(1.0F, 1.0F, 1.0F, 1.0F)).getRGB();
/* 391 */     int color_black = (new Color(0.0F, 0.0F, 0.0F, 1.0F)).getRGB();
/*     */     
/* 393 */     for (int j = 1; j < anchor_times.length; j++) {
/* 394 */       int tx1 = x1 + (int)(scaling_factor * anchor_times[j - 1]);
/* 395 */       int tx2 = x1 + (int)(scaling_factor * anchor_times[j]);
/*     */       
/* 397 */       String text_name = "-";
/* 398 */       ItemStack itemStack = new ItemStack(Blocks.field_150348_b);
/*     */ 
/*     */       
/* 401 */       int color = color_black;
/* 402 */       if (j == 1) {
/* 403 */         color = color_white;
/* 404 */         text_name = "";
/* 405 */         itemStack = null;
/* 406 */       } else if (j == 2) {
/* 407 */         color = color_lightblue;
/* 408 */         text_name = "I";
/* 409 */         itemStack = new ItemStack(Items.field_151045_i);
/* 410 */       } else if (j == 3) {
/* 411 */         color = color_lightblue;
/* 412 */         text_name = "II";
/* 413 */         itemStack = new ItemStack(Items.field_151045_i);
/* 414 */       } else if (j == 4) {
/* 415 */         color = color_green;
/* 416 */         text_name = "I";
/* 417 */         itemStack = new ItemStack(Items.field_151166_bC);
/* 418 */       } else if (j == 5) {
/* 419 */         color = color_green;
/* 420 */         text_name = "II";
/* 421 */         itemStack = new ItemStack(Items.field_151166_bC);
/* 422 */       } else if (j >= 6) {
/* 423 */         color = color_purple;
/* 424 */         text_name = "Драконы";
/* 425 */         itemStack = new ItemStack(Item.func_150898_a(Blocks.field_150380_bt));
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 430 */       Gui.func_73734_a(tx1 - stick_width / 2, y1 - stick_height / 2, tx1 + stick_width / 2, y2 + stick_height / 2, color);
/*     */       
/* 432 */       int label_time = anchor_times[j - 1];
/* 433 */       int k = label_time % 60;
/* 434 */       String str1 = (label_time / 60) + ":" + ((k >= 10) ? "" : "0") + k;
/* 435 */       float f = mc.field_71466_p.func_78256_a(str1);
/* 436 */       mc.field_71466_p.func_175065_a(str1, tx1 - f / 2.0F, (y2 + stick_height / 2 + 1), color, true);
/*     */       
/* 438 */       if (j != 7) {
/*     */         
/* 440 */         Gui.func_73734_a(tx1, y1, tx2, y2, color);
/*     */         
/* 442 */         if (itemStack != null) {
/* 443 */           f = mc.field_71466_p.func_78256_a(text_name);
/* 444 */           int px = (tx1 + tx2) / 2 - 4;
/* 445 */           int py = y1 - stick_height / 2 - 5;
/* 446 */           mc.field_71466_p.func_175065_a(text_name, px - f / 2.0F, py, color, true);
/*     */           
/* 448 */           float f1 = 0.6F;
/* 449 */           GlStateManager.func_179094_E();
/* 450 */           GlStateManager.func_179152_a(f1, f1, f1);
/* 451 */           mc.func_175599_af().func_175042_a(itemStack, (int)((px + f / 2.0F) / f1), (int)(py / f1) - 0);
/* 452 */           GlStateManager.func_179121_F();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 458 */     game_time = (game_time > 0) ? game_time : 0;
/* 459 */     int offsetX = 10;
/* 460 */     int seconds = game_time % 60;
/* 461 */     String text_time = (game_time / 60) + ":" + ((seconds >= 10) ? "" : "0") + seconds;
/* 462 */     float width = mc.field_71466_p.func_78256_a(text_time);
/* 463 */     mc.field_71466_p.func_175065_a(text_time, (x2 + offsetX + 7), (y2 - 4), (new Color(1.0F, 1.0F, 1.0F, 1.0F)).getRGB(), true);
/*     */     
/* 465 */     float scale = 0.8F;
/* 466 */     mc.field_71446_o.func_110577_a(this.resourceLoc_textures);
/* 467 */     GlStateManager.func_179094_E();
/* 468 */     GlStateManager.func_179109_b((x2 + offsetX), y2, 0.0F);
/* 469 */     GlStateManager.func_179152_a(scale, scale, scale);
/* 470 */     GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 471 */     func_73729_b(-6, -6, 0, 0, 12, 12);
/* 472 */     GlStateManager.func_179121_F();
/*     */     
/* 474 */     scale = 0.5F;
/* 475 */     GlStateManager.func_179094_E();
/* 476 */     GlStateManager.func_179109_b(x1 + game_time * scaling_factor, ((y1 + y2) / 2), 0.0F);
/* 477 */     GlStateManager.func_179152_a(scale, scale, scale);
/* 478 */     GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 479 */     func_73729_b(-8, -8, 12, 0, 16, 16);
/* 480 */     GlStateManager.func_179121_F();
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\hints\GeneratorTimers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */