/*     */ package com.dimchig.bedwarsbro.hints;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BWItemsHandler
/*     */ {
/*  12 */   public static ArrayList<BWItem> bwitems = new ArrayList<BWItem>();
/*     */   
/*     */   public enum BWItemType {
/*  15 */     NONE,
/*     */     
/*  17 */     BLOCK_WOOL,
/*  18 */     BLOCK_CLAY,
/*  19 */     BLOCK_WOOD,
/*  20 */     BLOCK_ENDSTONE,
/*  21 */     BLOCK_GLASS,
/*  22 */     BLOCK_LADDER,
/*  23 */     BLOCK_OBSIDIAN,
/*     */     
/*  25 */     ARMOUR_BOOTS,
/*  26 */     ARMOUR_LEGGINGS,
/*  27 */     ARMOUR_CHESTPLATE,
/*  28 */     ARMOUR_HELMET,
/*     */     
/*  30 */     SWORD,
/*  31 */     PICKAXE,
/*  32 */     AXE,
/*  33 */     SHEARS,
/*  34 */     STICK,
/*     */     
/*  36 */     FOOD,
/*  37 */     BOW,
/*  38 */     ARROW,
/*  39 */     FIREBALL,
/*  40 */     EMERALD,
/*  41 */     DIAMOND,
/*  42 */     IRON_INGOT,
/*  43 */     GOLD_INGOT,
/*  44 */     BUCKET,
/*  45 */     COMPASS,
/*  46 */     BRIDGE_EGG,
/*  47 */     PEARL,
/*  48 */     SPONGE,
/*  49 */     TNT,
/*     */     
/*  51 */     POTION_SPEED,
/*  52 */     POTION_HEAL,
/*  53 */     POTION_REGENERATION,
/*  54 */     POTION_STRENGTH,
/*  55 */     POTION_JUMP,
/*  56 */     POTION_EMPTY,
/*     */     
/*  58 */     IGNORE;
/*     */   }
/*     */   
/*     */   public enum BWItemToolLevel {
/*  62 */     NONE,
/*  63 */     WOOD,
/*  64 */     STONE,
/*  65 */     IRON,
/*  66 */     DIAMOND;
/*     */   }
/*     */   
/*     */   public enum BWItemArmourLevel {
/*  70 */     NONE,
/*  71 */     LEATHER,
/*  72 */     CHAIN,
/*  73 */     IRON,
/*  74 */     DIAMOND;
/*     */   }
/*     */   
/*     */   public enum BWItemColor {
/*  78 */     NONE,
/*  79 */     RED,
/*  80 */     YELLOW,
/*  81 */     GREEN,
/*  82 */     AQUA,
/*  83 */     BLUE,
/*  84 */     PINK,
/*  85 */     GRAY,
/*  86 */     WHITE;
/*     */   }
/*     */   
/*     */   public enum BWItemPotionsID
/*     */   {
/*  91 */     NONE(-1),
/*  92 */     STRENGTH(5),
/*  93 */     JUMP(8),
/*  94 */     SPEED(1),
/*  95 */     REGEN(10),
/*  96 */     INVIS(14);
/*     */     
/*     */     public int id;
/*     */     
/*     */     BWItemPotionsID(int id) {
/* 101 */       this.id = id;
/*     */     }
/*     */   }
/*     */   
/*     */   public static BWItem findItem(String local_name, String display_name) {
/* 106 */     if (bwitems.size() == 0) initBWItems();
/*     */     
/* 108 */     for (BWItem item : bwitems) {
/* 109 */       if (local_name.contains(item.local_name) && display_name.contains(item.display_name)) {
/* 110 */         if (item.type == BWItemType.BLOCK_WOOL || item.type == BWItemType.BLOCK_CLAY) {
/* 111 */           BWItemColor color = BWItemColor.WHITE;
/* 112 */           if (local_name.contains(".red")) {
/* 113 */             color = BWItemColor.RED;
/* 114 */           } else if (local_name.contains(".yellow")) {
/* 115 */             color = BWItemColor.YELLOW;
/* 116 */           } else if (local_name.contains(".lime")) {
/* 117 */             color = BWItemColor.GREEN;
/* 118 */           } else if (local_name.contains(".lightBlue")) {
/* 119 */             color = BWItemColor.AQUA;
/* 120 */           } else if (local_name.contains(".blue")) {
/* 121 */             color = BWItemColor.BLUE;
/* 122 */           } else if (local_name.contains(".pink")) {
/* 123 */             color = BWItemColor.PINK;
/* 124 */           } else if (local_name.contains(".gray")) {
/* 125 */             color = BWItemColor.GRAY;
/*     */           } 
/*     */           
/* 128 */           BWItem new_item = new BWItem(local_name, display_name, item.type, BWItemToolLevel.NONE, BWItemArmourLevel.NONE);
/* 129 */           new_item.color = color;
/*     */           
/* 131 */           return new_item;
/* 132 */         }  if (item.type == BWItemType.POTION_REGENERATION) {
/* 133 */           if (display_name.contains("егенерац") || display_name.contains("egeneratio")) return item;  continue;
/*     */         } 
/* 135 */         if (item.type == BWItemType.POTION_SPEED) {
/* 136 */           if (display_name.contains("тремительнос") || display_name.contains("wiftnes")) return item;  continue;
/*     */         } 
/* 138 */         if (item.type == BWItemType.POTION_HEAL) {
/* 139 */           if (display_name.contains("ечебно") || display_name.contains("ealin")) return item;
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/* 144 */         return item;
/*     */       } 
/*     */     } 
/*     */     
/* 148 */     return null;
/*     */   }
/*     */   
/*     */   public static void initBWItems() {
/* 152 */     bwitems = new ArrayList<BWItem>();
/* 153 */     bwitems.add(new BWItem("appleGold", "", BWItemType.FOOD, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/* 154 */     bwitems.add(new BWItem("arrow", "", BWItemType.ARROW, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/*     */     
/* 156 */     bwitems.add(new BWItem("bootsCloth", "", BWItemType.ARMOUR_BOOTS, BWItemToolLevel.NONE, BWItemArmourLevel.LEATHER));
/* 157 */     bwitems.add(new BWItem("bootsChain", "", BWItemType.ARMOUR_BOOTS, BWItemToolLevel.NONE, BWItemArmourLevel.CHAIN));
/* 158 */     bwitems.add(new BWItem("bootsIron", "", BWItemType.ARMOUR_BOOTS, BWItemToolLevel.NONE, BWItemArmourLevel.IRON));
/* 159 */     bwitems.add(new BWItem("bootsDiamond", "", BWItemType.ARMOUR_BOOTS, BWItemToolLevel.NONE, BWItemArmourLevel.DIAMOND));
/*     */     
/* 161 */     bwitems.add(new BWItem("bow", "", BWItemType.BOW, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/* 162 */     bwitems.add(new BWItem("bucketWater", "", BWItemType.BUCKET, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/*     */     
/* 164 */     bwitems.add(new BWItem("chestplateCloth", "", BWItemType.ARMOUR_CHESTPLATE, BWItemToolLevel.NONE, BWItemArmourLevel.LEATHER));
/* 165 */     bwitems.add(new BWItem("helmetCloth", "", BWItemType.ARMOUR_HELMET, BWItemToolLevel.NONE, BWItemArmourLevel.LEATHER));
/*     */     
/* 167 */     bwitems.add(new BWItem("clayHardenedStained", "", BWItemType.BLOCK_CLAY, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/* 168 */     bwitems.add(new BWItem("cloth", "", BWItemType.BLOCK_WOOL, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/*     */     
/* 170 */     bwitems.add(new BWItem("compass", "§aТрекер команды", BWItemType.COMPASS, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/* 171 */     bwitems.add(new BWItem("diamond", "", BWItemType.DIAMOND, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/* 172 */     bwitems.add(new BWItem("egg", "§aСпавнер моста", BWItemType.BRIDGE_EGG, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/* 173 */     bwitems.add(new BWItem("emerald", "", BWItemType.EMERALD, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/* 174 */     bwitems.add(new BWItem("enderPearl", "", BWItemType.PEARL, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/* 175 */     bwitems.add(new BWItem("fireball", "", BWItemType.FIREBALL, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/* 176 */     bwitems.add(new BWItem("glass", "", BWItemType.BLOCK_GLASS, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/* 177 */     bwitems.add(new BWItem("glassBottle", "", BWItemType.POTION_EMPTY, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/*     */     
/* 179 */     bwitems.add(new BWItem("hatchetWood", "", BWItemType.AXE, BWItemToolLevel.WOOD, BWItemArmourLevel.NONE));
/* 180 */     bwitems.add(new BWItem("hatchetStone", "", BWItemType.AXE, BWItemToolLevel.STONE, BWItemArmourLevel.NONE));
/* 181 */     bwitems.add(new BWItem("hatchetIron", "", BWItemType.AXE, BWItemToolLevel.IRON, BWItemArmourLevel.NONE));
/* 182 */     bwitems.add(new BWItem("hatchetDiamond", "", BWItemType.AXE, BWItemToolLevel.DIAMOND, BWItemArmourLevel.NONE));
/*     */     
/* 184 */     bwitems.add(new BWItem("ingotGold", "", BWItemType.GOLD_INGOT, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/* 185 */     bwitems.add(new BWItem("ingotIron", "", BWItemType.IRON_INGOT, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/*     */     
/* 187 */     bwitems.add(new BWItem("ladder", "", BWItemType.BLOCK_LADDER, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/*     */     
/* 189 */     bwitems.add(new BWItem("leggingsChain", "", BWItemType.ARMOUR_LEGGINGS, BWItemToolLevel.NONE, BWItemArmourLevel.CHAIN));
/* 190 */     bwitems.add(new BWItem("leggingsCloth", "", BWItemType.ARMOUR_LEGGINGS, BWItemToolLevel.NONE, BWItemArmourLevel.LEATHER));
/* 191 */     bwitems.add(new BWItem("leggingsDiamond", "", BWItemType.ARMOUR_LEGGINGS, BWItemToolLevel.NONE, BWItemArmourLevel.DIAMOND));
/* 192 */     bwitems.add(new BWItem("leggingsIron", "", BWItemType.ARMOUR_LEGGINGS, BWItemToolLevel.NONE, BWItemArmourLevel.IRON));
/*     */     
/* 194 */     bwitems.add(new BWItem("obsidian", "", BWItemType.BLOCK_OBSIDIAN, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/*     */     
/* 196 */     bwitems.add(new BWItem("pickaxeWood", "", BWItemType.PICKAXE, BWItemToolLevel.WOOD, BWItemArmourLevel.NONE));
/* 197 */     bwitems.add(new BWItem("pickaxeStone", "", BWItemType.PICKAXE, BWItemToolLevel.STONE, BWItemArmourLevel.NONE));
/* 198 */     bwitems.add(new BWItem("pickaxeIron", "", BWItemType.PICKAXE, BWItemToolLevel.IRON, BWItemArmourLevel.NONE));
/* 199 */     bwitems.add(new BWItem("pickaxeDiamond", "", BWItemType.PICKAXE, BWItemToolLevel.DIAMOND, BWItemArmourLevel.NONE));
/*     */     
/* 201 */     bwitems.add(new BWItem("potion", "§fЗелье силы", BWItemType.POTION_STRENGTH, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/* 202 */     bwitems.add(new BWItem("potion", "§fЗелье прыгучести", BWItemType.POTION_JUMP, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/* 203 */     bwitems.add(new BWItem("potion", "", BWItemType.POTION_SPEED, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/* 204 */     bwitems.add(new BWItem("potion", "", BWItemType.POTION_REGENERATION, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/* 205 */     bwitems.add(new BWItem("potion", "", BWItemType.POTION_HEAL, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/*     */     
/* 207 */     bwitems.add(new BWItem("shears", "", BWItemType.SHEARS, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/* 208 */     bwitems.add(new BWItem("sponge", "", BWItemType.SPONGE, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/* 209 */     bwitems.add(new BWItem("stick", "", BWItemType.STICK, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/*     */     
/* 211 */     bwitems.add(new BWItem("swordWood", "", BWItemType.SWORD, BWItemToolLevel.WOOD, BWItemArmourLevel.NONE));
/* 212 */     bwitems.add(new BWItem("swordStone", "", BWItemType.SWORD, BWItemToolLevel.STONE, BWItemArmourLevel.NONE));
/* 213 */     bwitems.add(new BWItem("swordIron", "", BWItemType.SWORD, BWItemToolLevel.IRON, BWItemArmourLevel.NONE));
/* 214 */     bwitems.add(new BWItem("swordDiamond", "", BWItemType.SWORD, BWItemToolLevel.DIAMOND, BWItemArmourLevel.NONE));
/*     */     
/* 216 */     bwitems.add(new BWItem("tnt", "", BWItemType.TNT, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/* 217 */     bwitems.add(new BWItem("whiteStone", "", BWItemType.BLOCK_ENDSTONE, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/* 218 */     bwitems.add(new BWItem("wood", "", BWItemType.BLOCK_WOOD, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/*     */ 
/*     */     
/* 221 */     bwitems.add(new BWItem("compass", "Наблюдение за игроками", BWItemType.IGNORE, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/* 222 */     bwitems.add(new BWItem("enderPearl", "Начать новую игру", BWItemType.IGNORE, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/* 223 */     bwitems.add(new BWItem("bed", "Назад в лобби", BWItemType.IGNORE, BWItemToolLevel.NONE, BWItemArmourLevel.NONE));
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\hints\BWItemsHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */