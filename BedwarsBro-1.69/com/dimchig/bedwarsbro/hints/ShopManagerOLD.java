/*     */ package com.dimchig.bedwarsbro.hints;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.ChatSender;
/*     */ import com.dimchig.bedwarsbro.CustomScoreboard;
/*     */ import com.dimchig.bedwarsbro.MyChatListener;
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
/*     */ public class ShopManagerOLD
/*     */ {
/*     */   Minecraft mc;
/*     */   private KeyBinding key_rclick;
/*     */   private KeyBinding key_lclick;
/*     */   private int unpress_counter;
/*     */   private boolean mouseFlag = false;
/*  38 */   private ArrayList<BuyQuee> buyQueue = new ArrayList<BuyQuee>();
/*  39 */   private ArrayList<MyShopItem> shopItems = new ArrayList<MyShopItem>();
/*  40 */   private int BUY_TICK_RATE = 5;
/*  41 */   private int BUY_TICK_RATE_COUNTER = 0;
/*     */   
/*     */   public ShopManagerOLD() {
/*  44 */     this.mc = Minecraft.func_71410_x();
/*  45 */     this.key_rclick = this.mc.field_71474_y.field_74313_G;
/*  46 */     this.key_lclick = this.mc.field_71474_y.field_74312_F;
/*  47 */     this.buyQueue = new ArrayList<BuyQuee>();
/*  48 */     this.shopItems = new ArrayList<MyShopItem>();
/*  49 */     initShopItems();
/*     */   }
/*     */   
/*     */   public static boolean isShopOpenedFlag = false;
/*     */   
/*     */   void initShopItems() {
/*  55 */     this.shopItems = new ArrayList<MyShopItem>();
/*  56 */     String category_quickbuy = "Быстрые покупки";
/*  57 */     String category_blocks = "Блоки";
/*  58 */     String category_swords = "Мечи";
/*  59 */     String category_armour = "Броня";
/*  60 */     String category_tools = "Инструменты";
/*  61 */     String category_bows = "Луки";
/*  62 */     String category_potions = "Зелья";
/*  63 */     String category_other = "Разное";
/*  64 */     String category_trackers = "Трекеры";
/*     */     
/*  66 */     String item_name_stick = "item.stick";
/*  67 */     String item_name_wool = "tile.cloth";
/*  68 */     String item_name_pickaxe = "item.pickaxe";
/*  69 */     String item_name_axe = "item.hatchet";
/*  70 */     String item_name_shears = "item.shears";
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
/* 172 */     this.shopItems.add(new MyShopItem(19, null, Blocks.field_150325_L, null, 1, 1, 0, "", "i4", true));
/* 173 */     this.shopItems.add(new MyShopItem(20, Items.field_151052_q, null, null, 1, 1, 0, "", "i10", false));
/* 174 */     this.shopItems.add(new MyShopItem(21, (Item)Items.field_151029_X, null, null, 1, 1, 0, "", "i40", false));
/* 175 */     this.shopItems.add(new MyShopItem(23, (Item)Items.field_151031_f, null, null, 1, 1, 0, "", "g12", false));
/* 176 */     this.shopItems.add(new MyShopItem(24, (Item)Items.field_151068_bn, null, null, 1, 1, 8194, "Скорка", "e1", true));
/* 177 */     this.shopItems.add(new MyShopItem(25, null, Blocks.field_150335_W, null, 1, 1, 0, "", "g4", true));
/*     */     
/* 179 */     this.shopItems.add(new MyShopItem(28, null, Blocks.field_150344_f, null, 1, 1, 0, "", "g4", true));
/* 180 */     this.shopItems.add(new MyShopItem(29, Items.field_151040_l, null, null, 1, 1, 0, "", "g7", false));
/* 181 */     this.shopItems.add(new MyShopItem(30, (Item)Items.field_151167_ab, null, null, 1, 1, 0, "", "g12", false));
/* 182 */     this.shopItems.add(new MyShopItem(31, (Item)Items.field_151097_aZ, null, null, 1, 1, 0, "", "i16", false));
/* 183 */     this.shopItems.add(new MyShopItem(32, Items.field_151032_g, null, null, 1, 1, 0, "", "g2", true));
/* 184 */     this.shopItems.add(new MyShopItem(33, (Item)Items.field_151068_bn, null, null, 1, 1, 8197, "", "e1", true));
/* 185 */     this.shopItems.add(new MyShopItem(34, Items.field_151131_as, null, null, 1, 1, 0, "", "g4", false));
/*     */   }
/*     */   
/*     */   public class BuyQuee { public String item_name;
/*     */     public int click_mode;
/*     */     
/*     */     public BuyQuee(String item_name, int click_mode) {
/* 192 */       this.item_name = item_name;
/* 193 */       this.click_mode = click_mode;
/*     */     } }
/*     */ 
/*     */   
/*     */   public class MyShopItem {
/*     */     public int slot_idx;
/*     */     public ItemStack itemStack;
/*     */     public ArrayList<ShopManagerOLD.BuyQuee> buyQueue;
/*     */     public int price_iron;
/*     */     public int price_gold;
/*     */     public int price_emeralds;
/*     */     public boolean isCountable;
/*     */     public int cnt_can_buy;
/*     */     
/*     */     public MyShopItem(int slot_idx, Item item, Block block, Enchantment enchantment, int enchantment_level, int stackSize, int metadata, String display_name, String price, boolean isCountable) {
/* 208 */       this.slot_idx = slot_idx;
/* 209 */       this.buyQueue = new ArrayList<ShopManagerOLD.BuyQuee>();
/* 210 */       this.cnt_can_buy = 0;
/*     */       
/* 212 */       if (item != null) { this.itemStack = new ItemStack(item, stackSize, metadata); }
/* 213 */       else if (block != null) { this.itemStack = new ItemStack(block, stackSize, metadata); }
/* 214 */        if (enchantment != null) this.itemStack.func_77966_a(enchantment, enchantment_level); 
/* 215 */       if (display_name.length() > 0) this.itemStack.func_151001_c(display_name);
/*     */       
/* 217 */       this.price_iron = 0;
/* 218 */       this.price_gold = 0;
/* 219 */       this.price_emeralds = 0;
/* 220 */       if (price.startsWith("i")) this.price_iron = Integer.parseInt(price.substring(1)); 
/* 221 */       if (price.startsWith("g")) this.price_gold = Integer.parseInt(price.substring(1)); 
/* 222 */       if (price.startsWith("e")) this.price_emeralds = Integer.parseInt(price.substring(1)); 
/* 223 */       this.isCountable = isCountable;
/*     */     }
/*     */     public void addBuyQuee(String item_name) {
/* 226 */       addBuyQuee(item_name, 0);
/*     */     } public void addBuyQuee(String item_name, int click_mode) {
/* 228 */       this.buyQueue.add(new ShopManagerOLD.BuyQuee(item_name, click_mode));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void scan() {
/* 238 */     EntityPlayerSP player = this.mc.field_71439_g;
/* 239 */     if (this.mc.field_71439_g.field_71071_by == null || this.mc.field_71439_g.field_71071_by.field_70462_a == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 244 */     String item_name = "Быстрые покупки";
/* 245 */     int idx = findItemInGui(item_name);
/* 246 */     if (idx == -1) {
/* 247 */       if (this.buyQueue.size() > 0) this.buyQueue.clear(); 
/* 248 */       if (isShopOpenedFlag == true) {
/* 249 */         isShopOpenedFlag = false;
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 255 */     handleBuyQueue();
/*     */ 
/*     */     
/* 258 */     boolean isMousePressed = false;
/* 259 */     if (Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) isMousePressed = true;
/*     */ 
/*     */     
/* 262 */     if (this.mc.field_71462_r == null) {
/*     */       return;
/*     */     }
/* 265 */     if (!(this.mc.field_71462_r instanceof GuiChest))
/* 266 */       return;  GuiChest chest = (GuiChest)this.mc.field_71462_r;
/* 267 */     if (chest == null)
/*     */       return; 
/* 269 */     if (!isShopOpenedFlag) {
/* 270 */       isShopOpenedFlag = true;
/* 271 */       this.mouseFlag = false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 276 */     if (((Slot)chest.field_147002_h.field_75151_b.get(9)).func_75211_c().func_77960_j() == 5) {
/*     */ 
/*     */       
/* 279 */       int cnt_iron = 0;
/* 280 */       int cnt_gold = 0;
/* 281 */       int cnt_emeralds = 0;
/* 282 */       for (ItemStack itemStack : player.field_71071_by.field_70462_a) {
/* 283 */         if (itemStack != null && itemStack.func_77973_b() != null) {
/* 284 */           if (itemStack.func_77973_b() == Items.field_151042_j) {
/* 285 */             cnt_iron += itemStack.field_77994_a;
/* 286 */           } else if (itemStack.func_77973_b() == Items.field_151043_k) {
/* 287 */             cnt_gold += itemStack.field_77994_a;
/* 288 */           } else if (itemStack.func_77973_b() == Items.field_151166_bC) {
/* 289 */             cnt_emeralds += itemStack.field_77994_a;
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/* 294 */       Item[] single_items = { Items.field_151055_y, Items.field_151131_as };
/* 295 */       ArrayList<Item> items2hide = new ArrayList<Item>();
/*     */       
/* 297 */       if (this.mc.field_71439_g.func_70035_c() != null && (this.mc.field_71439_g.func_70035_c()).length == 4 && this.mc.field_71439_g.func_70035_c()[1] != null) {
/* 298 */         String armour_name = this.mc.field_71439_g.func_70035_c()[1].func_77977_a().substring(5);
/*     */         
/* 300 */         if (armour_name.contains("Chain")) {
/* 301 */           items2hide.add(Items.field_151029_X);
/* 302 */         } else if (armour_name.contains("Iron")) {
/* 303 */           items2hide.add(Items.field_151167_ab);
/* 304 */           items2hide.add(Items.field_151029_X);
/* 305 */         } else if (armour_name.contains("Diamond")) {
/* 306 */           items2hide.add(Items.field_151167_ab);
/* 307 */           items2hide.add(Items.field_151029_X);
/* 308 */           items2hide.add(Items.field_151175_af);
/*     */         } 
/*     */       } 
/*     */       
/* 312 */       if (findItemInHotbar("pickaxe") > 0 && findItemInHotbar("hatchet") > 0 && findItemInHotbar("shears") > 0) {
/* 313 */         items2hide.add(Items.field_151038_n);
/*     */       }
/*     */       
/* 316 */       if (findItemInHotbar("shears") > 0) {
/* 317 */         items2hide.add(Items.field_151097_aZ);
/*     */       }
/*     */ 
/*     */       
/* 321 */       for (MyShopItem shopItem : this.shopItems) {
/*     */         
/* 323 */         if (shopItem.slot_idx == 19) {
/*     */           
/* 325 */           int wool_id = 0;
/* 326 */           CustomScoreboard.TEAM_COLOR team_color = MyChatListener.getEntityTeamColor((EntityPlayer)this.mc.field_71439_g);
/* 327 */           if (team_color == CustomScoreboard.TEAM_COLOR.RED) { wool_id = 14; }
/* 328 */           else if (team_color == CustomScoreboard.TEAM_COLOR.YELLOW) { wool_id = 4; }
/* 329 */           else if (team_color == CustomScoreboard.TEAM_COLOR.GREEN) { wool_id = 5; }
/* 330 */           else if (team_color == CustomScoreboard.TEAM_COLOR.AQUA) { wool_id = 3; }
/* 331 */           else if (team_color == CustomScoreboard.TEAM_COLOR.BLUE) { wool_id = 11; }
/* 332 */           else if (team_color == CustomScoreboard.TEAM_COLOR.PINK) { wool_id = 6; }
/* 333 */           else if (team_color == CustomScoreboard.TEAM_COLOR.WHITE) { wool_id = 0; }
/* 334 */           else if (team_color == CustomScoreboard.TEAM_COLOR.GRAY) { wool_id = 7; }
/* 335 */            shopItem = new MyShopItem(19, null, Blocks.field_150325_L, null, 1, 1, wool_id, "", "i4", true);
/*     */         } 
/*     */         
/* 338 */         int cnt = 0;
/* 339 */         if (shopItem.price_iron > 0 && cnt_iron >= shopItem.price_iron) { cnt = cnt_iron / shopItem.price_iron; }
/* 340 */         else if (shopItem.price_gold > 0 && cnt_gold >= shopItem.price_gold) { cnt = cnt_gold / shopItem.price_gold; }
/* 341 */         else if (shopItem.price_emeralds > 0 && cnt_emeralds >= shopItem.price_emeralds) { cnt = cnt_emeralds / shopItem.price_emeralds; }
/* 342 */          if (!shopItem.isCountable && cnt > 1) cnt = 1; 
/* 343 */         shopItem.cnt_can_buy = cnt;
/* 344 */         shopItem.itemStack.field_77994_a = (cnt > 1) ? cnt : 1;
/* 345 */         Slot slot = chest.field_147002_h.field_75151_b.get(shopItem.slot_idx);
/* 346 */         if (cnt == 0)
/* 347 */         { slot.func_75215_d(null); }
/* 348 */         else { slot.func_75215_d(shopItem.itemStack); }
/*     */ 
/*     */ 
/*     */         
/* 352 */         for (Item single_item : single_items) {
/* 353 */           if (shopItem.itemStack.func_77973_b() == single_item)
/*     */           {
/* 355 */             if (findItemInHotbar(shopItem.itemStack.func_77973_b().func_77658_a()) != -1) {
/* 356 */               slot.func_75215_d(null);
/*     */               
/*     */               break;
/*     */             } 
/*     */           }
/*     */         } 
/* 362 */         for (Item i : items2hide) {
/* 363 */           if (shopItem.itemStack.func_77973_b() == i) {
/* 364 */             slot.func_75215_d(null);
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 372 */       if (isMousePressed == true) {
/* 373 */         if (this.mouseFlag == true) {
/* 374 */           this.mouseFlag = false;
/*     */           
/* 376 */           if (chest.getSlotUnderMouse() == null) {
/*     */             return;
/*     */           }
/*     */           try {
/* 380 */             if (chest.getSlotUnderMouse() == null)
/* 381 */               return;  int slot_idx = (chest.getSlotUnderMouse()).field_75222_d;
/*     */             
/* 383 */             for (MyShopItem shopItem : this.shopItems) {
/* 384 */               if (slot_idx == shopItem.slot_idx) {
/* 385 */                 if (shopItem.cnt_can_buy > 0) {
/* 386 */                   for (BuyQuee b : shopItem.buyQueue) {
/* 387 */                     int click_mode = b.click_mode;
/* 388 */                     if (shopItem.isCountable && Mouse.isButtonDown(1)) {
/* 389 */                       click_mode = 1;
/*     */                     }
/*     */                   } 
/*     */                 }
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } 
/* 400 */           } catch (Exception ex) {
/* 401 */             ex.printStackTrace();
/*     */           } 
/*     */         } 
/* 404 */       } else if (!this.mouseFlag) {
/* 405 */         this.mouseFlag = true;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void handleBuyQueue() {
/* 414 */     if (this.buyQueue == null) this.buyQueue = new ArrayList<BuyQuee>(); 
/* 415 */     if (this.buyQueue.size() <= 0) {
/* 416 */       this.BUY_TICK_RATE_COUNTER = -1;
/*     */       return;
/*     */     } 
/* 419 */     if (this.BUY_TICK_RATE_COUNTER >= 0 && this.BUY_TICK_RATE_COUNTER <= this.BUY_TICK_RATE) {
/* 420 */       this.BUY_TICK_RATE_COUNTER++;
/*     */       return;
/*     */     } 
/* 423 */     this.BUY_TICK_RATE_COUNTER = 0;
/*     */     
/* 425 */     BuyQuee b = this.buyQueue.get(0);
/* 426 */     this.buyQueue.remove(0);
/*     */     
/*     */     try {
/* 429 */       int slot_id = Integer.parseInt(b.item_name);
/* 430 */       clickItemInGui(slot_id, b.click_mode);
/*     */       return;
/* 432 */     } catch (Exception exception) {
/* 433 */       useItemInGui(b.item_name, b.click_mode);
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void click_r(boolean state) {
/* 444 */     click_key(this.key_rclick, state);
/*     */   }
/*     */   void click_l(boolean state) {
/* 447 */     click_key(this.key_lclick, state);
/*     */   }
/*     */   
/*     */   void click_key(KeyBinding key, boolean state) {
/* 451 */     KeyBinding.func_74510_a(key.func_151463_i(), state);
/* 452 */     if (state) this.unpress_counter = 5; 
/*     */   }
/*     */   
/*     */   int findItemInHotbar(String item2find) {
/* 456 */     for (int i = 0; i < InventoryPlayer.func_70451_h(); i++) {
/* 457 */       ItemStack stack = this.mc.field_71439_g.field_71071_by.field_70462_a[i];
/* 458 */       if (stack != null) {
/* 459 */         Item item = stack.func_77973_b();
/* 460 */         if (item != null && 
/* 461 */           stack.func_82833_r() != null && (
/* 462 */           stack.func_82833_r().contains(item2find) || item.func_77658_a().contains(item2find))) return i; 
/*     */       } 
/* 464 */     }  return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean clickItemInHotbar(String item2find) {
/* 469 */     if (this.mc.field_71439_g.field_71071_by.func_70448_g() == null || this.mc.field_71439_g.field_71071_by.func_70448_g().func_82833_r() == null || !this.mc.field_71439_g.field_71071_by.func_70448_g().func_82833_r().contains(item2find)) {
/* 470 */       this.mc.field_71439_g.field_71071_by.func_70453_c(1);
/*     */     }
/*     */     
/* 473 */     if (this.mc.field_71439_g.field_71071_by.func_70448_g() != null && this.mc.field_71439_g.field_71071_by.func_70448_g().func_82833_r() != null && this.mc.field_71439_g.field_71071_by.func_70448_g().func_82833_r().contains(item2find)) {
/* 474 */       click_r(true);
/* 475 */       return true;
/*     */     } 
/* 477 */     return false;
/*     */   }
/*     */   
/*     */   int findItemInGui(String item2find) {
/* 481 */     if (this.mc.field_71462_r == null) return -1; 
/* 482 */     if (!(this.mc.field_71462_r instanceof GuiChest)) return -1; 
/* 483 */     GuiChest chest = (GuiChest)this.mc.field_71462_r;
/* 484 */     if (chest == null) return -1; 
/* 485 */     List<Slot> chest_slots = chest.field_147002_h.field_75151_b;
/*     */     
/* 487 */     if (chest_slots == null || chest_slots.size() == 0) return -1; 
/* 488 */     for (Slot slot : chest_slots)
/*     */     
/* 490 */     { if (slot != null && slot.func_75211_c() != null && slot.func_75211_c().func_82833_r() != null && 
/* 491 */         slot.field_75222_d < 72 && (
/* 492 */         slot.func_75211_c().func_77973_b().func_77658_a().contains(item2find) || slot.func_75211_c().func_82833_r().contains(item2find)))
/*     */       { try {
/* 494 */           return slot.field_75222_d;
/* 495 */         } catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */         
/* 499 */         return -1; }  }  return -1;
/*     */   }
/*     */   void clickItemInGui(int slotIdx) {
/* 502 */     clickItemInGui(slotIdx, 0);
/*     */   } void clickItemInGui(int slotIdx, int mode) {
/* 504 */     this.mc.field_71442_b.func_78753_a(this.mc.field_71439_g.field_71070_bA.field_75152_c, slotIdx, 0, mode, (EntityPlayer)this.mc.field_71439_g);
/*     */   }
/*     */   
/*     */   boolean useItemInHotbar(String display_name) {
/* 508 */     int idx = findItemInHotbar(display_name);
/* 509 */     if (idx == -1) return false; 
/* 510 */     return clickItemInHotbar(display_name);
/*     */   }
/*     */   void useItemInGui(String display_name) {
/* 513 */     useItemInGui(display_name, 0);
/*     */   } void useItemInGui(String display_name, int click_mode) {
/* 515 */     int slot_idx = findItemInGui(display_name);
/* 516 */     if (slot_idx == -1)
/* 517 */       return;  clickItemInGui(slot_idx, click_mode);
/*     */   }
/*     */   
/*     */   void printGuiContent() {
/* 521 */     if (this.mc.field_71462_r == null)
/* 522 */       return;  if (!(this.mc.field_71462_r instanceof GuiChest))
/* 523 */       return;  GuiChest chest = (GuiChest)this.mc.field_71462_r;
/* 524 */     if (chest == null)
/* 525 */       return;  List<Slot> chest_slots = chest.field_147002_h.field_75151_b;
/*     */     
/* 527 */     if (chest_slots == null || chest_slots.size() == 0)
/* 528 */       return;  for (Slot slot : chest_slots) {
/*     */       
/* 530 */       if (slot == null || slot.func_75211_c() == null || slot.func_75211_c().func_82833_r() == null)
/* 531 */         continue;  ChatSender.addText(slot.field_75222_d + ") " + slot.func_75211_c().func_82833_r() + " | " + slot.func_75211_c().func_77973_b().func_77658_a());
/*     */     } 
/* 533 */     ChatSender.addText("");
/*     */   }
/*     */   
/*     */   void printHotbarContent() {
/* 537 */     if (this.mc.field_71439_g.field_71071_by == null || this.mc.field_71439_g.field_71071_by.field_70462_a == null)
/* 538 */       return;  for (int i = 0; i < InventoryPlayer.func_70451_h(); i++) {
/* 539 */       ItemStack stack = this.mc.field_71439_g.field_71071_by.field_70462_a[i];
/* 540 */       if (stack != null) {
/* 541 */         Item item = stack.func_77973_b();
/* 542 */         if (item != null && 
/* 543 */           stack.func_82833_r() != null)
/* 544 */           ChatSender.addText(stack.func_82833_r()); 
/*     */       } 
/* 546 */     }  ChatSender.addText("");
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\hints\ShopManagerOLD.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */