/*     */ package com.dimchig.bedwarsbro.commands;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.ChatSender;
/*     */ import com.dimchig.bedwarsbro.ColorCodesManager;
/*     */ import com.dimchig.bedwarsbro.FileManager;
/*     */ import com.dimchig.bedwarsbro.Main;
/*     */ import com.dimchig.bedwarsbro.MyChatListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.regex.Pattern;
/*     */ import net.minecraft.command.CommandBase;
/*     */ import net.minecraft.command.CommandException;
/*     */ import net.minecraft.command.ICommandSender;
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
/*     */ public class CommandHistoryShop
/*     */   extends CommandBase
/*     */ {
/*  34 */   public static String command_text = "/bwbroshop";
/*     */   Main main_instance;
/*     */   
/*     */   public CommandHistoryShop(Main main) {
/*  38 */     this; command_text = command_text.replace("/", "");
/*  39 */     this.main_instance = main;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_71519_b(ICommandSender sender) {
/*  44 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71517_b() {
/*  49 */     this; return command_text;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71518_a(ICommandSender sender) {
/*  54 */     return "Help mod";
/*     */   }
/*     */   
/*     */   private class MyItem {
/*     */     public String name;
/*     */     public int total_amount;
/*     */     public int buy_amount;
/*  61 */     public ArrayList<MyItem> game_log = new ArrayList<MyItem>();
/*     */     
/*     */     public MyItem(String name, int total_amount, int buy_amount) {
/*  64 */       this.name = name;
/*  65 */       this.total_amount = total_amount;
/*  66 */       this.buy_amount = buy_amount;
/*  67 */       this.game_log = new ArrayList<MyItem>();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/*  73 */     String str = "";
/*  74 */     str = str + "&8<===============================================>\n";
/*     */     
/*  76 */     ArrayList<MyItem> items = new ArrayList<MyItem>();
/*     */     
/*     */     try {
/*  79 */       String text = FileManager.readFile("resourceBedwarslog.txt");
/*  80 */       String[] games = text.split(Pattern.quote("========"));
/*     */       
/*  82 */       int games_count = 0;
/*     */       
/*  84 */       if (games != null && games.length != 0) {
/*  85 */         for (String game : games) {
/*     */ 
/*     */           
/*  88 */           String[] log = game.split(Pattern.quote(";&;"));
/*  89 */           if (log != null && log.length != 0) {
/*     */             
/*  91 */             boolean isGameEmpty = true;
/*     */             
/*  93 */             for (String item : log) {
/*     */               
/*  95 */               String s = item.replace(Pattern.quote("\n"), "").replace("\r", "").replace("\n", "");
/*  96 */               if (s.length() > 3 && !s.contains("�")) {
/*  97 */                 String[] split = s.split(";");
/*  98 */                 if (split.length == 2)
/*     */                   try {
/* 100 */                     String name = ColorCodesManager.removeColorCodes(split[0].trim());
/* 101 */                     int count = Integer.parseInt(split[1].trim());
/*     */ 
/*     */                     
/* 104 */                     boolean isFound = false;
/* 105 */                     for (MyItem i : items) {
/* 106 */                       if (i.name.equals(name)) {
/* 107 */                         isFound = true;
/*     */                         
/* 109 */                         i.total_amount += count;
/* 110 */                         i.buy_amount++;
/*     */                         
/*     */                         break;
/*     */                       } 
/*     */                     } 
/* 115 */                     if (!isFound) items.add(new MyItem(name, count, 1));
/*     */                     
/* 117 */                     isGameEmpty = false;
/*     */                   }
/* 119 */                   catch (Exception exception) {} 
/*     */               } 
/*     */             } 
/* 122 */             if (!isGameEmpty) games_count++;
/*     */           
/*     */           } 
/*     */         } 
/*     */       }
/* 127 */       str = str + "\n                      &f&lВсего сыграно &a&l" + games_count + " &f&lигр\n\n";
/* 128 */       str = str + "                    &f&lТоп &e&lпопулярных &f&lпредметов:\n\n";
/*     */       
/* 130 */       Collections.sort(items, new Comparator<MyItem>()
/*     */           {
/*     */             public int compare(CommandHistoryShop.MyItem i1, CommandHistoryShop.MyItem i2) {
/* 133 */               return -Integer.compare(i1.buy_amount, i2.buy_amount);
/*     */             }
/*     */           });
/*     */       
/* 137 */       int cnt = 0;
/* 138 */       for (MyItem i : items) {
/* 139 */         cnt++;
/* 140 */         String total_amount = "" + i.total_amount + " &7шт";
/* 141 */         if (i.total_amount > 64) {
/* 142 */           int x = (int)(i.total_amount / 64.0F);
/* 143 */           total_amount = x + " &7стак" + MyChatListener.getNumberEnding(x, "", "а", "ов");
/*     */         } 
/* 145 */         String percentage = "" + (int)(i.buy_amount * 100.0F / games_count) + "%";
/*     */         
/* 147 */         String name = i.name;
/* 148 */         if (name.equals("Wool")) { name = "Шерсть"; }
/* 149 */         else if (name.equals("Stick")) { name = "&cП&6а&eл&aк&bа"; }
/* 150 */         else if (name.equals("Arrow")) { name = "Стрелы"; }
/* 151 */         else if (name.equals("Stone Sword")) { name = "Меч каменный"; }
/* 152 */         else if (name.equals("Golden Apple")) { name = "Золотое &eяблоко"; }
/* 153 */         else if (name.equals("Fire Charge")) { name = "&6Фаербол"; }
/* 154 */         else if (name.equals("TNT")) { name = "&cДинамит"; }
/* 155 */         else if (name.equals("Железная броня")) { name = "Броня железная"; }
/* 156 */         else if (name.equals("Iron Sword")) { name = "Меч железный"; }
/* 157 */         else if (name.equals("Wooden Pickaxe")) { name = "Кирка деревянная"; }
/* 158 */         else if (name.equals("Wooden Axe")) { name = "Топор деревянный"; }
/* 159 */         else if (name.equals("Shears")) { name = "Ножницы"; }
/* 160 */         else if (name.equals("Bow")) { name = "&6Лук"; }
/* 161 */         else if (name.equals("Зелье силы")) { name = "&4Зелье силы"; }
/* 162 */         else if (name.equals("End Stone")) { name = "Эндерняк"; }
/* 163 */         else if (name.equals("Stone Pickaxe")) { name = "Кирка каменная"; }
/* 164 */         else if (name.equals("Ender Pearl")) { name = "&9Эндер перл"; }
/* 165 */         else if (name.equals("Water Bucket")) { name = "Ведро воды"; }
/* 166 */         else if (name.equals("Stone Axe")) { name = "Топор каменный"; }
/* 167 */         else if (name.equals("Зелье прыгучести")) { name = "Зелье прыгучести"; }
/* 168 */         else if (name.equals("Iron Pickaxe")) { name = "Кирка железная"; }
/* 169 */         else if (name.equals("Алмазная броня")) { name = "Броня алмазная"; }
/* 170 */         else if (name.equals("Diamond Pickaxe")) { name = "Кирка алмазная"; }
/* 171 */         else if (name.equals("Wooden Planks")) { name = "Дерево"; }
/* 172 */         else if (name.equals("Спавнер моста")) { name = "Спавнер моста"; }
/* 173 */         else if (name.equals("Stained Clay")) { name = "Глина"; }
/* 174 */         else if (name.equals("Obsidian")) { name = "&9Обсидиан"; }
/* 175 */         else if (name.equals("Iron Axe")) { name = "Железный топор"; }
/* 176 */         else if (name.equals("Diamond Sword")) { name = "Меч алмазный"; }
/* 177 */         else if (name.equals("Potion")) { name = "Зелье регена"; }
/* 178 */         else if (name.equals("Diamond Axe")) { name = "Топор алмазный"; }
/* 179 */         else if (name.equals("Кольчужная броня")) { name = "Броня кольчужная"; }
/* 180 */         else if (name.equals("Ladder")) { name = "Лестницы"; }
/* 181 */         else if (name.equals("Glass")) { name = "Стелко"; }
/* 182 */         else if (name.equals("tile.sponge.name")) { name = "Губка"; }
/*     */ 
/*     */ 
/*     */         
/* 186 */         str = str + "&7" + cnt + ". " + ((cnt <= 3) ? "&e&l" : "&f") + (name.contains("алмаз") ? "&b" : "") + name + " &8(&7куплено &a" + i.buy_amount + " &7раз, &b" + total_amount + ", частота &c" + percentage + "&8)\n";
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 191 */     catch (Exception ex) {
/* 192 */       ex.printStackTrace();
/*     */     } 
/*     */     
/* 195 */     str = str + "&8<===============================================>";
/* 196 */     ChatSender.addText(str);
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\commands\CommandHistoryShop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */