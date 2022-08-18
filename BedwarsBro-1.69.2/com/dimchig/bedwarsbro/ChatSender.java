/*    */ package com.dimchig.bedwarsbro;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.event.ClickEvent;
/*    */ import net.minecraft.event.HoverEvent;
/*    */ import net.minecraft.util.ChatComponentText;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ 
/*    */ 
/*    */ public class ChatSender
/*    */ {
/*    */   public static void addText(String text) {
/* 15 */     if (Minecraft.func_71410_x() == null)
/* 16 */       return;  if ((Minecraft.func_71410_x()).field_71439_g == null)
/* 17 */       return;  (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(ColorCodesManager.replaceColorCodesInString(text)));
/*    */   }
/* 19 */   public static void addText(boolean b) { addText((b ? "&a" : "&c") + b); }
/* 20 */   public static void addText(int x) { addText("" + x); }
/* 21 */   public static void addText(double x) { addText("" + x); }
/* 22 */   public static void addText(float x) { addText("" + x); }
/* 23 */   public static <T> void addText(T[] x) { addText("" + x); } public static <T> void addText(List<T> x) {
/* 24 */     addText("" + x);
/*    */   }
/*    */   public static void sendText(String text) {
/* 27 */     if (Minecraft.func_71410_x() == null)
/* 28 */       return;  if ((Minecraft.func_71410_x()).field_71439_g == null)
/* 29 */       return;  (Minecraft.func_71410_x()).field_71439_g.func_71165_d(text.replace("§", "&"));
/*    */   }
/*    */   
/*    */   public static void addHoverText(String text, String hover_text) {
/* 33 */     ChatComponentText chatComponentText1 = new ChatComponentText(ColorCodesManager.replaceColorCodesInString(text));
/* 34 */     ChatComponentText chatComponentText2 = new ChatComponentText(ColorCodesManager.replaceColorCodesInString(hover_text));
/* 35 */     HoverEvent hover = new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)chatComponentText2);
/* 36 */     ClickEvent click = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwarsChatModLookAtPlayer");
/* 37 */     chatComponentText1.func_150256_b().func_150209_a(hover);
/* 38 */     chatComponentText1.func_150256_b().func_150241_a(click);
/* 39 */     (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)chatComponentText1);
/*    */   }
/*    */   
/*    */   public static void addHoverFileText(String text, String hover_text, String filepath) throws IOException {
/* 43 */     ChatComponentText chatComponentText1 = new ChatComponentText(ColorCodesManager.replaceColorCodesInString(text));
/* 44 */     ChatComponentText chatComponentText2 = new ChatComponentText(ColorCodesManager.replaceColorCodesInString(hover_text));
/* 45 */     HoverEvent hover = new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)chatComponentText2);
/* 46 */     ClickEvent click = new ClickEvent(ClickEvent.Action.OPEN_FILE, filepath);
/* 47 */     chatComponentText1.func_150256_b().func_150209_a(hover);
/* 48 */     chatComponentText1.func_150256_b().func_150241_a(click);
/* 49 */     (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)chatComponentText1);
/*    */   }
/*    */   
/*    */   public static void addClickText(String text, String commandText) {
/* 53 */     ChatComponentText chatComponentText = new ChatComponentText(ColorCodesManager.replaceColorCodesInString(text));
/* 54 */     ClickEvent click = new ClickEvent(ClickEvent.Action.RUN_COMMAND, commandText);
/* 55 */     chatComponentText.func_150256_b().func_150241_a(click);
/* 56 */     (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)chatComponentText);
/*    */   }
/*    */   
/*    */   public static void addClickAndHoverText(String text, String hoverText, String commandText) {
/* 60 */     ChatComponentText chatComponentText1 = new ChatComponentText(ColorCodesManager.replaceColorCodesInString(text));
/* 61 */     ChatComponentText chatComponentText2 = new ChatComponentText(ColorCodesManager.replaceColorCodesInString(hoverText));
/* 62 */     HoverEvent hover = new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)chatComponentText2);
/* 63 */     ClickEvent click = new ClickEvent(ClickEvent.Action.RUN_COMMAND, commandText);
/* 64 */     chatComponentText1.func_150256_b().func_150209_a(hover);
/* 65 */     chatComponentText1.func_150256_b().func_150241_a(click);
/* 66 */     (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)chatComponentText1);
/*    */   }
/*    */   
/*    */   public static void addLinkAndHoverText(String text, String hoverText, String url) {
/* 70 */     ChatComponentText chatComponentText1 = new ChatComponentText(ColorCodesManager.replaceColorCodesInString(text));
/* 71 */     ChatComponentText chatComponentText2 = new ChatComponentText(ColorCodesManager.replaceColorCodesInString(hoverText));
/* 72 */     HoverEvent hover = new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)chatComponentText2);
/* 73 */     ClickEvent click = new ClickEvent(ClickEvent.Action.OPEN_URL, url);
/* 74 */     chatComponentText1.func_150256_b().func_150209_a(hover);
/* 75 */     chatComponentText1.func_150256_b().func_150241_a(click);
/* 76 */     (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)chatComponentText1);
/*    */   }
/*    */   
/*    */   public static void addClickSuggestAndHoverText(String text, String hoverText, String commandText) {
/* 80 */     ChatComponentText chatComponentText1 = new ChatComponentText(ColorCodesManager.replaceColorCodesInString(text));
/* 81 */     ChatComponentText chatComponentText2 = new ChatComponentText(ColorCodesManager.replaceColorCodesInString(hoverText));
/* 82 */     HoverEvent hover = new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)chatComponentText2);
/* 83 */     ClickEvent click = new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, commandText);
/* 84 */     chatComponentText1.func_150256_b().func_150209_a(hover);
/* 85 */     chatComponentText1.func_150256_b().func_150241_a(click);
/* 86 */     (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)chatComponentText1);
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\ChatSender.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */