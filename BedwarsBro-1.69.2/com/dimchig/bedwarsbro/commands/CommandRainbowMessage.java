/*     */ package com.dimchig.bedwarsbro.commands;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.ChatSender;
/*     */ import com.dimchig.bedwarsbro.Main;
/*     */ import com.google.common.base.Splitter;
/*     */ import java.util.regex.Pattern;
/*     */ import net.minecraft.client.Minecraft;
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
/*     */ public class CommandRainbowMessage
/*     */   extends CommandBase
/*     */ {
/*  25 */   public static String command_text = "r";
/*     */   
/*     */   public CommandRainbowMessage(String command) {
/*  28 */     this; command_text = command.replace("/", "");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_71519_b(ICommandSender sender) {
/*  33 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71517_b() {
/*  38 */     this; return command_text;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71518_a(ICommandSender sender) {
/*  43 */     return "Makes message rainbow";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/*  49 */     if (args.length <= 0) {
/*  50 */       ChatSender.addText("Напиши &e/r &aтекст");
/*     */       
/*     */       return;
/*     */     } 
/*  54 */     String str = "";
/*  55 */     for (String s : args) {
/*  56 */       str = str + s + " ";
/*     */     }
/*  58 */     str = str.trim();
/*  59 */     if (str.length() <= 0)
/*     */       return; 
/*  61 */     boolean isGlobal = Main.getConfigBool(Main.CONFIG_MSG.RAINBOW_MESSAGE_GLOBAL);
/*  62 */     if (str.startsWith("!")) {
/*  63 */       isGlobal = true;
/*  64 */       str = str.substring(1, str.length());
/*     */     } 
/*     */     
/*  67 */     int text_mode = Main.getConfigInt(Main.CONFIG_MSG.RAINBOW_MESSAGE_MODE);
/*     */ 
/*     */     
/*  70 */     String[] config_replace_character = Main.getConfigString(Main.CONFIG_MSG.RAINBOW_MESSAGE_REPLACE_CHARS).split("=");
/*  71 */     if (config_replace_character.length == 2) {
/*  72 */       str = str.replace(config_replace_character[0], config_replace_character[1]);
/*     */     }
/*     */     
/*  75 */     String generated_message = generateRainbowMessage(str, 1, text_mode);
/*  76 */     if (generated_message == null) {
/*  77 */       ChatSender.addText("&cСообщение слишком большое!");
/*     */       
/*     */       return;
/*     */     } 
/*  81 */     str = "" + generated_message;
/*     */ 
/*     */ 
/*     */     
/*  85 */     if (Main.shopManager.findItemInHotbar("ыбор лобби") == -1 && isGlobal) str = "!" + str;
/*     */ 
/*     */     
/*  88 */     (Minecraft.func_71410_x()).field_71439_g.func_71165_d(str);
/*     */   }
/*     */ 
/*     */   
/*  92 */   public static String[] color_codes = new String[] { "c", "6", "e", "a", "b", "9", "d" };
/*  93 */   public static int MAX_MESSAGE_LENGTH = 100;
/*     */   
/*     */   private static void readConfigParams() {
/*  96 */     String color_codes_str = Main.getConfigString(Main.CONFIG_MSG.RAINBOW_MESSAGE_COLORS);
/*  97 */     color_codes = color_codes_str.split(",");
/*     */     
/*  99 */     if (color_codes_str.contains("+")) {
/*     */       
/*     */       try {
/* 102 */         String colors = color_codes_str.split(Pattern.quote("+"))[0];
/* 103 */         String additional = color_codes_str.split(Pattern.quote("+"))[1];
/* 104 */         color_codes = new String[colors.length()];
/*     */         
/* 106 */         for (int i = 0; i < colors.length(); i++) {
/* 107 */           color_codes[i] = colors.charAt(i) + additional;
/*     */         }
/*     */       }
/* 110 */       catch (Exception ex) {
/* 111 */         ChatSender.addText("&cОшибка цветов в конфиге! &7(&e/bwbro&7)");
/*     */         
/*     */         return;
/*     */       } 
/* 115 */     } else if (!color_codes_str.contains(",")) {
/* 116 */       color_codes = color_codes_str.split("");
/*     */     } 
/*     */ 
/*     */     
/* 120 */     if (color_codes_str.length() == 0 || color_codes.length <= 0) {
/* 121 */       ChatSender.addText("&cДобавь цвета в конфиге! &7(&e/bwbro&7)");
/*     */       return;
/*     */     } 
/*     */   }
/*     */   public static String generateRainbowMessage(String s, int step, int text_mode) {
/* 126 */     readConfigParams();
/*     */     
/* 128 */     if (step > MAX_MESSAGE_LENGTH) {
/* 129 */       return null;
/*     */     }
/* 131 */     if (text_mode == -1) text_mode = Main.getConfigInt(Main.CONFIG_MSG.RAINBOW_MESSAGE_MODE); 
/* 132 */     s = s.replace("і", "ы");
/*     */     
/* 134 */     String str = "";
/*     */ 
/*     */     
/* 137 */     Iterable<String> chunks = Splitter.fixedLength(step).split(s);
/* 138 */     if (text_mode == 1) chunks = Splitter.on(" ").split(s); 
/* 139 */     int code_idx = -1;
/* 140 */     for (String i_chunk : chunks) {
/* 141 */       String chunk = i_chunk;
/*     */       
/* 143 */       if (text_mode == 1) {
/* 144 */         str = str + "&0 ";
/*     */       }
/*     */       
/* 147 */       code_idx = (code_idx + 1) % color_codes.length;
/* 148 */       String this_chunk_code = "";
/* 149 */       for (String c : color_codes[code_idx].split("")) {
/* 150 */         this_chunk_code = this_chunk_code + "&" + c;
/*     */       }
/*     */       
/* 153 */       if (this_chunk_code.contains("n") && step == 1 && chunk.contains(" ")) {
/* 154 */         chunk = chunk.replace(" ", "&0 " + this_chunk_code);
/* 155 */         code_idx = (code_idx - 1) % color_codes.length;
/*     */       } 
/*     */       
/* 158 */       str = str + this_chunk_code + chunk;
/*     */     } 
/*     */     
/* 161 */     if (color_codes.length > 0) str = str + "&" + color_codes[0].charAt(0); 
/* 162 */     str = str.trim();
/*     */     
/* 164 */     if (str.length() > MAX_MESSAGE_LENGTH) {
/* 165 */       return generateRainbowMessage(s, step + 1, text_mode);
/*     */     }
/*     */     
/* 168 */     return str;
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\commands\CommandRainbowMessage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */