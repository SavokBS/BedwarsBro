/*     */ package com.dimchig.bedwarsbro.hints;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.ChatSender;
/*     */ import com.dimchig.bedwarsbro.ColorCodesManager;
/*     */ import com.dimchig.bedwarsbro.CustomScoreboard;
/*     */ import com.dimchig.bedwarsbro.Main;
/*     */ import com.dimchig.bedwarsbro.MyChatListener;
/*     */ import java.awt.AWTException;
/*     */ import java.awt.Robot;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.gui.inventory.GuiChest;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import org.lwjgl.input.Mouse;
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
/*     */ public class ShopManager
/*     */ {
/*     */   Minecraft mc;
/*     */   private KeyBinding key_rclick;
/*     */   private KeyBinding key_lclick;
/*     */   private int unpress_counter;
/*     */   private boolean mouseFlag = false;
/*  49 */   private ArrayList<MyShopItem> shopItems = new ArrayList<MyShopItem>();
/*  50 */   private int BUY_TICK_RATE = 5;
/*  51 */   private int BUY_TICK_RATE_COUNTER = 0;
/*     */   
/*     */   private static Robot robot;
/*  54 */   private String[] favourite_maps = new String[0];
/*     */   
/*     */   public ShopManager() {
/*  57 */     this.mc = Minecraft.func_71410_x();
/*  58 */     this.key_rclick = this.mc.field_71474_y.field_74313_G;
/*  59 */     this.key_lclick = this.mc.field_71474_y.field_74312_F;
/*  60 */     this.shopItems = new ArrayList<MyShopItem>();
/*  61 */     initShopItems();
/*  62 */     initRobot();
/*     */   }
/*     */   
/*     */   void initRobot() {
/*     */     try {
/*  67 */       robot = new Robot();
/*  68 */     } catch (AWTException e) {
/*  69 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updateBooleans() {
/*  74 */     String[] split = Main.getConfigString(Main.CONFIG_MSG.MAP_AUTO_SELECTER).split(",");
/*  75 */     ArrayList<String> arr = new ArrayList<String>();
/*  76 */     ArrayList<String> all_maps = new ArrayList<String>();
/*  77 */     for (String s : split) {
/*  78 */       String map = s.trim();
/*  79 */       if (map.length() >= 3)
/*  80 */         all_maps.add(map); 
/*     */     } 
/*  82 */     this.favourite_maps = new String[all_maps.size()];
/*  83 */     for (int i = 0; i < this.favourite_maps.length; i++) {
/*  84 */       this.favourite_maps[i] = all_maps.get(i);
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean isShopOpenedFlag = false;
/*  89 */   public static long time_last_teams_closen = 0L;
/*     */   
/*     */   void initShopItems() {
/*  92 */     this.shopItems = new ArrayList<MyShopItem>();
/*  93 */     String category_quickbuy = "Быстрые покупки";
/*  94 */     String category_blocks = "Блоки";
/*  95 */     String category_swords = "Мечи";
/*  96 */     String category_armour = "Броня";
/*  97 */     String category_tools = "Инструменты";
/*  98 */     String category_bows = "Луки";
/*  99 */     String category_potions = "Зелья";
/* 100 */     String category_other = "Разное";
/* 101 */     String category_trackers = "Трекеры";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public class MyShopItem
/*     */   {
/*     */     public int slot_idx;
/*     */ 
/*     */     
/*     */     public ItemStack itemStack;
/*     */ 
/*     */     
/*     */     public int[] categories;
/*     */ 
/*     */     
/*     */     public int price_iron;
/*     */ 
/*     */     
/*     */     public int price_gold;
/*     */ 
/*     */     
/*     */     public int price_emeralds;
/*     */ 
/*     */     
/*     */     public boolean isCountable;
/*     */     
/*     */     public int cnt_can_buy;
/*     */ 
/*     */     
/*     */     public MyShopItem(int[] categories, int slot_idx, Item item, Block block, Enchantment enchantment, int enchantment_level, int stackSize, int metadata, String display_name, String price, boolean isCountable) {
/* 132 */       this.slot_idx = slot_idx;
/* 133 */       this.categories = categories;
/* 134 */       this.cnt_can_buy = 0;
/*     */       
/* 136 */       if (item != null) { this.itemStack = new ItemStack(item, stackSize, metadata); }
/* 137 */       else if (block != null) { this.itemStack = new ItemStack(block, stackSize, metadata); }
/* 138 */        if (enchantment != null) this.itemStack.func_77966_a(enchantment, enchantment_level); 
/* 139 */       if (display_name.length() > 0) this.itemStack.func_151001_c(display_name);
/*     */       
/* 141 */       this.price_iron = 0;
/* 142 */       this.price_gold = 0;
/* 143 */       this.price_emeralds = 0;
/* 144 */       if (price.startsWith("i")) this.price_iron = Integer.parseInt(price.substring(1)); 
/* 145 */       if (price.startsWith("g")) this.price_gold = Integer.parseInt(price.substring(1)); 
/* 146 */       if (price.startsWith("e")) this.price_emeralds = Integer.parseInt(price.substring(1)); 
/* 147 */       this.isCountable = isCountable;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void scan(boolean isBetterShopActive) {
/* 158 */     EntityPlayerSP player = this.mc.field_71439_g;
/* 159 */     if (this.mc.field_71439_g.field_71071_by == null || this.mc.field_71439_g.field_71071_by.field_70462_a == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 165 */     int idx1 = findItemInGui("Быстрые покупки", true);
/* 166 */     int idx2 = findItemInGui("Заостренные мечи", true);
/* 167 */     int idx3 = findItemInHotbar("Быстрый старт");
/*     */ 
/*     */     
/* 170 */     if (findItemInGui("item.skull", true) != -1 && findItemInHotbar("Наблюдение за") != -1) {
/*     */       
/* 172 */       if (this.mc.field_71462_r == null)
/* 173 */         return;  if (!(this.mc.field_71462_r instanceof GuiChest))
/* 174 */         return;  GuiChest guiChest = (GuiChest)this.mc.field_71462_r;
/* 175 */       if (guiChest == null)
/* 176 */         return;  List<Slot> list = guiChest.field_147002_h.field_75151_b;
/*     */       
/* 178 */       if (list == null || list.size() == 0)
/* 179 */         return;  for (Slot slot : list) {
/*     */         
/* 181 */         if (slot == null || slot.func_75211_c() == null || slot.func_75211_c().func_82833_r() == null)
/* 182 */           continue;  List<String> descriptions = slot.func_75211_c().func_82840_a((EntityPlayer)player, false);
/* 183 */         if (descriptions == null || descriptions.size() < 2)
/* 184 */           continue;  String name = descriptions.get(0);
/* 185 */         if (name.length() < 5)
/* 186 */           continue;  CustomScoreboard.TEAM_COLOR team_color = CustomScoreboard.getTeamColorByCode("&" + name.charAt(3));
/*     */         
/* 188 */         ItemStack is = slot.func_75211_c();
/* 189 */         is.func_150996_a(Item.func_150898_a(Blocks.field_150325_L));
/*     */         
/* 191 */         int meta = 0;
/* 192 */         if (team_color == CustomScoreboard.TEAM_COLOR.RED) {
/* 193 */           meta = 14;
/* 194 */         } else if (team_color == CustomScoreboard.TEAM_COLOR.YELLOW) {
/* 195 */           meta = 4;
/* 196 */         } else if (team_color == CustomScoreboard.TEAM_COLOR.GREEN) {
/* 197 */           meta = 5;
/* 198 */         } else if (team_color == CustomScoreboard.TEAM_COLOR.AQUA) {
/* 199 */           meta = 3;
/* 200 */         } else if (team_color == CustomScoreboard.TEAM_COLOR.BLUE) {
/* 201 */           meta = 11;
/* 202 */         } else if (team_color == CustomScoreboard.TEAM_COLOR.PINK) {
/* 203 */           meta = 6;
/* 204 */         } else if (team_color == CustomScoreboard.TEAM_COLOR.WHITE) {
/* 205 */           meta = 0;
/* 206 */         } else if (team_color == CustomScoreboard.TEAM_COLOR.GRAY) {
/* 207 */           meta = 8;
/*     */         } 
/*     */         
/* 210 */         is.func_77964_b(meta);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 216 */     if (findItemInGui("tile.stainedGlass", false) != -1 && findItemInGui("item.bed", false) != -1 && findItemInGui("item.arrow", false) != -1) {
/*     */ 
/*     */ 
/*     */       
/* 220 */       if (this.mc.field_71462_r == null)
/* 221 */         return;  if (!(this.mc.field_71462_r instanceof GuiChest))
/* 222 */         return;  GuiChest guiChest = (GuiChest)this.mc.field_71462_r;
/* 223 */       if (guiChest == null)
/* 224 */         return;  List<Slot> list = guiChest.field_147002_h.field_75151_b;
/*     */       
/* 226 */       if (list == null || list.size() == 0)
/* 227 */         return;  if (this.favourite_maps == null || this.favourite_maps.length == 0)
/* 228 */         return;  for (Slot slot : list) {
/*     */         
/* 230 */         if (slot == null || slot.func_75211_c() == null || slot.func_75211_c().func_82833_r() == null || slot.field_75222_d > 34)
/* 231 */           continue;  String slot_name = slot.func_75211_c().func_82833_r();
/* 232 */         if (slot_name.contains("Нет доступной")) {
/* 233 */           slot.func_75215_d(null);
/*     */           
/*     */           continue;
/*     */         } 
/* 237 */         if (slot.field_75222_d > 34)
/*     */           continue; 
/* 239 */         boolean isFavMap = false;
/* 240 */         for (String fav_map : this.favourite_maps) {
/* 241 */           if (slot_name.contains(fav_map)) {
/* 242 */             isFavMap = true;
/*     */             break;
/*     */           } 
/*     */         } 
/* 246 */         if (!isFavMap) {
/* 247 */           slot.func_75215_d(new ItemStack((Block)Blocks.field_150399_cn, 1, 14));
/* 248 */           slot.func_75211_c().func_151001_c(slot_name);
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 254 */       ArrayList<String> fastMaps = new ArrayList<String>();
/* 255 */       for (int k = 0; k < this.favourite_maps.length; k++) {
/* 256 */         boolean isSlotFound = false;
/* 257 */         for (Slot slot : list) {
/* 258 */           if (slot != null && slot.func_75211_c() != null && slot.func_75211_c().func_82833_r() != null && slot.field_75222_d <= 34 && 
/* 259 */             slot.func_75211_c().func_82833_r().contains(this.favourite_maps[k])) {
/* 260 */             fastMaps.add(this.favourite_maps[k]);
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 266 */       boolean isMousePressed = Mouse.isButtonDown(0);
/*     */       
/* 268 */       for (int i = 0; i < 9 && 
/* 269 */         i < this.favourite_maps.length; i++) {
/* 270 */         boolean isMapFound = fastMaps.contains(this.favourite_maps[i]);
/* 271 */         int slot_number = i + 37;
/* 272 */         for (Slot slot1 : list) {
/* 273 */           if (slot1.field_75222_d != slot_number)
/* 274 */             continue;  slot1.func_75215_d(new ItemStack((Block)Blocks.field_150399_cn, 1, isMapFound ? 3 : 8));
/* 275 */           slot1.func_75211_c().func_151001_c(ColorCodesManager.replaceColorCodesInString((isMapFound ? "&b" : "&7") + this.favourite_maps[i]));
/*     */         } 
/*     */         
/* 278 */         Slot slot = guiChest.getSlotUnderMouse();
/* 279 */         if (isMousePressed && slot != null && 
/* 280 */           slot.field_75222_d == slot_number) {
/* 281 */           if (isMapFound) {
/* 282 */             useItemInGui(this.favourite_maps[i]);
/* 283 */             ChatSender.addText(MyChatListener.PREFIX_BEDWARSBRO + "Запускаю &a" + this.favourite_maps[i] + "&f...");
/*     */           } else {
/* 285 */             int slot_idx = findItemInGui("item.arrow", false);
/* 286 */             if (slot_idx == -1)
/* 287 */               return;  clickItemInGui(slot_idx);
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 296 */     if (!isBetterShopActive)
/*     */       return; 
/* 298 */     if (idx1 == -1 && idx2 == -1 && idx3 == -1) {
/*     */       return;
/*     */     }
/* 301 */     if (this.mc.field_71462_r == null) {
/*     */       return;
/*     */     }
/* 304 */     if (!(this.mc.field_71462_r instanceof GuiChest))
/* 305 */       return;  GuiChest chest = (GuiChest)this.mc.field_71462_r;
/* 306 */     if (chest == null) {
/*     */       return;
/*     */     }
/* 309 */     if (idx3 != -1) {
/* 310 */       List<Slot> list = chest.field_147002_h.field_75151_b;
/*     */       
/* 312 */       if (list == null || list.size() == 0)
/* 313 */         return;  for (Slot slot : list) {
/*     */         
/* 315 */         if (slot == null || slot.func_75211_c() == null || slot.func_75211_c().func_82833_r() == null)
/*     */           continue; 
/* 317 */         if (slot.field_75222_d > 34)
/*     */           continue; 
/* 319 */         if (slot.func_75211_c().func_77973_b().func_77658_a().contains("tile.")) {
/*     */           
/* 321 */           String display_name = slot.func_75211_c().func_82833_r();
/* 322 */           if (display_name == null || !display_name.contains("["))
/*     */             continue;  try {
/* 324 */             int cnt = Integer.parseInt("" + display_name.split(" ")[1].charAt(1));
/* 325 */             int max_cnt = Integer.parseInt("" + display_name.split(" ")[1].charAt(3));
/* 326 */             ItemStack itemStack = slot.func_75211_c();
/* 327 */             itemStack.field_77994_a = cnt;
/* 328 */             if (cnt != max_cnt && max_cnt != 0)
/*     */             {
/*     */ 
/*     */               
/* 332 */               ItemStack is = slot.func_75211_c();
/* 333 */               is.func_151001_c(slot.func_75211_c().func_82833_r());
/*     */               
/* 335 */               slot.func_75211_c().func_150996_a(Item.func_150898_a((Block)Blocks.field_150399_cn));
/*     */             }
/*     */           
/*     */           }
/* 339 */           catch (Exception ex) {
/* 340 */             ex.printStackTrace();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
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
/* 367 */     ArrayList<Item> items2hide = new ArrayList<Item>();
/* 368 */     if (findItemInHotbar("stick") > 0) {
/* 369 */       items2hide.add(Items.field_151055_y);
/*     */     }
/*     */     
/* 372 */     List<Slot> chest_slots = chest.field_147002_h.field_75151_b;
/*     */     
/* 374 */     if (chest_slots == null || chest_slots.size() == 0)
/* 375 */       return;  for (Slot slot : chest_slots) {
/*     */       
/* 377 */       if (slot == null || slot.func_75211_c() == null || slot.func_75211_c().func_77973_b() == null || 
/* 378 */         slot.field_75222_d >= 45)
/*     */         continue; 
/* 380 */       List<String> descriptions = slot.func_75211_c().func_82840_a((EntityPlayer)player, false);
/* 381 */       if (descriptions == null)
/* 382 */         return;  for (String s : descriptions) {
/* 383 */         if (s.contains("Недостаточно ресурсов") || s.contains("Уже куплено")) {
/* 384 */           slot.func_75215_d(null);
/*     */           break;
/*     */         } 
/*     */       } 
/* 388 */       if (slot.func_75211_c() == null || slot.func_75211_c().func_77973_b() == null)
/* 389 */         continue;  for (Item it : items2hide) {
/* 390 */         if (slot.func_75211_c().func_77973_b() == it) {
/* 391 */           slot.func_75215_d(null);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int findItemInHotbar(String item2find) {
/* 399 */     for (int i = 0; i < InventoryPlayer.func_70451_h(); i++) {
/* 400 */       ItemStack stack = this.mc.field_71439_g.field_71071_by.field_70462_a[i];
/* 401 */       if (stack != null) {
/* 402 */         Item item = stack.func_77973_b();
/* 403 */         if (item != null && 
/* 404 */           stack.func_82833_r() != null && (
/* 405 */           stack.func_82833_r().contains(item2find) || item.func_77658_a().contains(item2find))) return i; 
/*     */       } 
/* 407 */     }  return -1;
/*     */   }
/*     */   
/*     */   public int findItemInGui(String item2find, boolean justGui) {
/* 411 */     if (this.mc.field_71462_r == null) return -1; 
/* 412 */     if (!(this.mc.field_71462_r instanceof GuiChest)) return -1; 
/* 413 */     GuiChest chest = (GuiChest)this.mc.field_71462_r;
/* 414 */     if (chest == null) return -1; 
/* 415 */     List<Slot> chest_slots = chest.field_147002_h.field_75151_b;
/*     */     
/* 417 */     if (chest_slots == null || chest_slots.size() == 0) return -1; 
/* 418 */     for (Slot slot : chest_slots)
/*     */     
/* 420 */     { if (slot == null || slot.func_75211_c() == null || slot.func_75211_c().func_82833_r() == null)
/*     */         continue; 
/* 422 */       if (justGui && slot.field_75222_d >= 45)
/*     */         continue; 
/* 424 */       if (slot.func_75211_c().func_77973_b().func_77658_a().contains(item2find) || slot.func_75211_c().func_82833_r().contains(item2find))
/*     */       { try {
/* 426 */           return slot.field_75222_d;
/* 427 */         } catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */         
/* 431 */         return -1; }  }  return -1;
/*     */   }
/*     */   public void clickItemInGui(int slotIdx) {
/* 434 */     clickItemInGui(slotIdx, 0);
/*     */   } public void clickItemInGui(int slotIdx, int mode) {
/* 436 */     this.mc.field_71442_b.func_78753_a(this.mc.field_71439_g.field_71070_bA.field_75152_c, slotIdx, 0, mode, (EntityPlayer)this.mc.field_71439_g);
/*     */   }
/*     */   
/*     */   public boolean useItemInHotbar(String display_name) {
/* 440 */     int idx = findItemInHotbar(display_name);
/* 441 */     if (idx == -1) return false; 
/* 442 */     return clickItemInHotbar(display_name);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean clickItemInHotbar(String item2find) {
/* 447 */     if (this.mc.field_71439_g.field_71071_by.func_70448_g() == null || this.mc.field_71439_g.field_71071_by.func_70448_g().func_82833_r() == null || !this.mc.field_71439_g.field_71071_by.func_70448_g().func_82833_r().contains(item2find)) {
/* 448 */       this.mc.field_71439_g.field_71071_by.func_70453_c(1);
/*     */     }
/*     */     
/* 451 */     if (this.mc.field_71439_g.field_71071_by.func_70448_g() != null && this.mc.field_71439_g.field_71071_by.func_70448_g().func_82833_r() != null && this.mc.field_71439_g.field_71071_by.func_70448_g().func_82833_r().contains(item2find)) {
/* 452 */       if (robot == null) initRobot(); 
/*     */       try {
/* 454 */         robot.mousePress(4096);
/* 455 */         robot.mouseRelease(4096);
/* 456 */       } catch (Exception exception) {}
/*     */       
/* 458 */       return true;
/*     */     } 
/* 460 */     return false;
/*     */   }
/*     */   public void useItemInGui(String display_name) {
/* 463 */     useItemInGui(display_name, 0);
/*     */   } public void useItemInGui(String display_name, int click_mode) {
/* 465 */     int slot_idx = findItemInGui(display_name, true);
/* 466 */     if (slot_idx == -1)
/* 467 */       return;  clickItemInGui(slot_idx, click_mode);
/*     */   }
/*     */   
/*     */   void printGuiContent() {
/* 471 */     if (this.mc.field_71462_r == null)
/* 472 */       return;  if (!(this.mc.field_71462_r instanceof GuiChest))
/* 473 */       return;  GuiChest chest = (GuiChest)this.mc.field_71462_r;
/* 474 */     if (chest == null)
/* 475 */       return;  List<Slot> chest_slots = chest.field_147002_h.field_75151_b;
/*     */     
/* 477 */     if (chest_slots == null || chest_slots.size() == 0)
/* 478 */       return;  for (Slot slot : chest_slots) {
/*     */       
/* 480 */       if (slot == null || slot.func_75211_c() == null || slot.func_75211_c().func_82833_r() == null)
/* 481 */         continue;  ChatSender.addText(slot.field_75222_d + ") " + slot.func_75211_c().func_82833_r() + " | " + slot.func_75211_c().func_77973_b().func_77658_a());
/*     */     } 
/* 483 */     ChatSender.addText("");
/*     */   }
/*     */   
/*     */   void printHotbarContent() {
/* 487 */     if (this.mc.field_71439_g.field_71071_by == null || this.mc.field_71439_g.field_71071_by.field_70462_a == null)
/* 488 */       return;  for (int i = 0; i < InventoryPlayer.func_70451_h(); i++) {
/* 489 */       ItemStack stack = this.mc.field_71439_g.field_71071_by.field_70462_a[i];
/* 490 */       if (stack != null) {
/* 491 */         Item item = stack.func_77973_b();
/* 492 */         if (item != null && 
/* 493 */           stack.func_82833_r() != null)
/* 494 */           ChatSender.addText(stack.func_77977_a()); 
/*     */       } 
/* 496 */     }  ChatSender.addText("");
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\hints\ShopManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */