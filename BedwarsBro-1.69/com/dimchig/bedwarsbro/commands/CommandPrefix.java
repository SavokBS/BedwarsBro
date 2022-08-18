/*    */ package com.dimchig.bedwarsbro.commands;
/*    */ 
/*    */ import com.dimchig.bedwarsbro.ChatSender;
/*    */ import com.dimchig.bedwarsbro.ColorCodesManager;
/*    */ import com.dimchig.bedwarsbro.Main;
/*    */ import com.dimchig.bedwarsbro.MyChatListener;
/*    */ import net.minecraft.command.CommandBase;
/*    */ import net.minecraft.command.CommandException;
/*    */ import net.minecraft.command.ICommandSender;
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
/*    */ public class CommandPrefix
/*    */   extends CommandBase
/*    */ {
/* 29 */   public static String command_text = "bwprefix";
/*    */   
/*    */   public CommandPrefix() {
/* 32 */     this; this; command_text = command_text.replace("/", "");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_71519_b(ICommandSender sender) {
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71517_b() {
/* 42 */     this; return command_text;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71518_a(ICommandSender sender) {
/* 47 */     return "Makes message rainbow setter";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/* 53 */     String rules = "&c - Нельзя использовать цвета\n&c - префикс до 10 символов\n&c - можно только буквы";
/*    */     
/* 55 */     if (args.length != 1 || args[0].equals("help")) {
/* 56 */       ChatSender.addText("Можешь поставить любой префикс &7(&e/bwprefix &bBro&7) &fкакой захочешь\n" + rules);
/*    */       
/*    */       return;
/*    */     } 
/* 60 */     String new_prefix = ColorCodesManager.removeColorCodes(args[0]);
/*    */     
/* 62 */     if (Main.namePlateRenderer.isPrefixBad(new_prefix)) {
/* 63 */       ChatSender.addText("&cПожалуйста, не используй плохие и запрещенные слова");
/* 64 */       MyChatListener.playSound(MyChatListener.SOUND_REJECT);
/*    */       
/*    */       return;
/*    */     } 
/* 68 */     if (!Main.namePlateRenderer.isPrefixValid(new_prefix)) {
/* 69 */       ChatSender.addText("&cНеправильный префикс!\n" + rules);
/* 70 */       MyChatListener.playSound(MyChatListener.SOUND_REJECT);
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 75 */     Main.clientConfig.get(Main.CONFIG_MSG.BRO_PREFIX.text).set(new_prefix);
/* 76 */     Main.saveConfig();
/* 77 */     ChatSender.addText("Префикс " + Main.namePlateRenderer.generateColourfulPrefix(new_prefix) + "&fустановлен! Измемения войдут в силу в течении &e10 &fминут");
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\commands\CommandPrefix.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */