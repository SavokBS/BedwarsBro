/*    */ package com.dimchig.bedwarsbro;
/*    */ 
/*    */ import net.minecraft.util.EnumChatFormatting;
/*    */ 
/*    */ public class ColorCodesManager {
/*    */   public static String replaceColorCodesInString(String text) {
/*  7 */     String str = text;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 32 */     str = str.replace("&4", "" + EnumChatFormatting.DARK_RED).replace("&c", "" + EnumChatFormatting.RED).replace("&6", "" + EnumChatFormatting.GOLD).replace("&e", "" + EnumChatFormatting.YELLOW).replace("&2", "" + EnumChatFormatting.DARK_GREEN).replace("&a", "" + EnumChatFormatting.GREEN).replace("&b", "" + EnumChatFormatting.AQUA).replace("&3", "" + EnumChatFormatting.DARK_AQUA).replace("&1", "" + EnumChatFormatting.DARK_BLUE).replace("&9", "" + EnumChatFormatting.BLUE).replace("&d", "" + EnumChatFormatting.LIGHT_PURPLE).replace("&5", "" + EnumChatFormatting.DARK_PURPLE).replace("&f", "" + EnumChatFormatting.WHITE).replace("&F", "" + EnumChatFormatting.WHITE).replace("&7", "" + EnumChatFormatting.GRAY).replace("&8", "" + EnumChatFormatting.DARK_GRAY).replace("&0", "" + EnumChatFormatting.BLACK).replace("&k", "" + EnumChatFormatting.OBFUSCATED).replace("&m", "" + EnumChatFormatting.STRIKETHROUGH).replace("&o", "" + EnumChatFormatting.ITALIC).replace("&l", "" + EnumChatFormatting.BOLD).replace("&n", "" + EnumChatFormatting.UNDERLINE).replace("&r", "" + EnumChatFormatting.RESET);
/* 33 */     return str;
/*    */   }
/*    */   
/*    */   public static String removeColorCodes(String text) {
/* 37 */     String str = text;
/*    */     
/* 39 */     String[] colorcodes = { "4", "c", "6", "e", "2", "a", "b", "3", "1", "9", "d", "5", "f", "F", "7", "8", "0", "k", "m", "o", "l", "n", "r" };
/*    */ 
/*    */     
/* 42 */     for (String c : colorcodes) {
/* 43 */       str = str.replace("&" + c, "").replace("§" + c, "");
/*    */     }
/* 45 */     return str.trim();
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\ColorCodesManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */