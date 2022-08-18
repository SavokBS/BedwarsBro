/*    */ package com.dimchig.bedwarsbro.commands;
/*    */ 
/*    */ import com.dimchig.bedwarsbro.ChatSender;
/*    */ import com.dimchig.bedwarsbro.ColorCodesManager;
/*    */ import com.dimchig.bedwarsbro.Main;
/*    */ import com.dimchig.bedwarsbro.MyChatListener;
/*    */ import com.dimchig.bedwarsbro.SameModePlayers;
/*    */ import net.minecraft.client.Minecraft;
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
/*    */ public class CommandPrefix
/*    */   extends CommandBase
/*    */ {
/* 30 */   public static String command_text = "bwprefix";
/*    */   
/*    */   public CommandPrefix() {
/* 33 */     this; this; command_text = command_text.replace("/", "");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_71519_b(ICommandSender sender) {
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71517_b() {
/* 43 */     this; return command_text;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71518_a(ICommandSender sender) {
/* 48 */     return "Makes message rainbow setter";
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/* 53 */     if ((Minecraft.func_71410_x()).field_71439_g == null)
/*    */       return; 
/* 55 */     String rules = "&c - Нельзя использовать цвета\n&c - префикс до 10 символов\n&c - можно только буквы";
/* 56 */     if (args.length != 1 || args[0].equals("help")) {
/* 57 */       ChatSender.addText("Можешь поставить любой префикс &7(&e/bwprefix &bBro&7) &fкакой захочешь\n" + rules);
/*    */       
/*    */       return;
/*    */     } 
/* 61 */     String new_prefix = ColorCodesManager.removeColorCodes(args[0]);
/*    */     
/* 63 */     SameModePlayers.BroPlayer bro = Main.namePlateRenderer.findBroPlayerByName((Minecraft.func_71410_x()).field_71439_g.func_70005_c_());
/*    */     
/* 65 */     if (bro == null || !bro.isAdmin) {
/* 66 */       if (Main.namePlateRenderer.isPrefixBad(new_prefix)) {
/* 67 */         ChatSender.addText("&cПожалуйста, не используй плохие и запрещенные слова");
/* 68 */         MyChatListener.playSound(MyChatListener.SOUND_REJECT);
/*    */         
/*    */         return;
/*    */       } 
/* 72 */       if (!Main.namePlateRenderer.isPrefixValid(new_prefix)) {
/* 73 */         ChatSender.addText("&cНеправильный префикс!\n" + rules);
/* 74 */         MyChatListener.playSound(MyChatListener.SOUND_REJECT);
/*    */         
/*    */         return;
/*    */       } 
/*    */     } else {
/* 79 */       ChatSender.addText("Тебе можно любой, ты ж админ");
/*    */     } 
/*    */ 
/*    */     
/* 83 */     Main.clientConfig.get(Main.CONFIG_MSG.BRO_PREFIX.text).set(new_prefix);
/* 84 */     Main.saveConfig();
/* 85 */     ChatSender.addText("Префикс " + Main.namePlateRenderer.generateColourfulPrefix(new_prefix) + "&fустановлен! Измемения войдут в силу в течении &e10 &fминут");
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\commands\CommandPrefix.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */