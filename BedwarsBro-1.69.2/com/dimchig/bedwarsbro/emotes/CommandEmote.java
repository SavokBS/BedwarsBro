/*    */ package com.dimchig.bedwarsbro.emotes;
/*    */ 
/*    */ import com.dimchig.bedwarsbro.ChatSender;
/*    */ import com.dimchig.bedwarsbro.Main;
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
/*    */ 
/*    */ public class CommandEmote
/*    */   extends CommandBase
/*    */ {
/* 28 */   public static String command_text = "/bwemote";
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   String randomFilterString;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CommandEmote()
/*    */   {
/* 49 */     this.randomFilterString = "lno";
/*    */     this;
/*    */     this;
/*    */     command_text = command_text.replace("/", "");
/*    */   }
/*    */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/* 55 */     if (args.length <= 0 || args[0].equals("help")) {
/* 56 */       this; ChatSender.addText("/" + command_text + " &edislike");
/* 57 */       this; ChatSender.addText("/" + command_text + " &elove");
/* 58 */       this; ChatSender.addText("/" + command_text + " &ehaha");
/* 59 */       this; ChatSender.addText("/" + command_text + " &eangry");
/* 60 */       this; ChatSender.addText("/" + command_text + " &elike");
/* 61 */       this; ChatSender.addText("/" + command_text + " &eshit");
/* 62 */       this; ChatSender.addText("/" + command_text + " &ebarf &7(рвота)");
/* 63 */       this; ChatSender.addText("/" + command_text + " &ewow");
/*    */       
/*    */       return;
/*    */     } 
/* 67 */     String arg0 = args[0];
/*    */     
/* 69 */     EmotesDrawer.EMOTE_TYPE emoteType = null;
/* 70 */     if (arg0.equals("dislike")) { emoteType = EmotesDrawer.EMOTE_TYPE.DISLIKE; }
/* 71 */     else if (arg0.equals("love")) { emoteType = EmotesDrawer.EMOTE_TYPE.HEART; }
/* 72 */     else if (arg0.equals("haha")) { emoteType = EmotesDrawer.EMOTE_TYPE.LAUGH; }
/* 73 */     else if (arg0.equals("angry")) { emoteType = EmotesDrawer.EMOTE_TYPE.ANGRY; }
/* 74 */     else if (arg0.equals("like")) { emoteType = EmotesDrawer.EMOTE_TYPE.LIKE; }
/* 75 */     else if (arg0.equals("shit")) { emoteType = EmotesDrawer.EMOTE_TYPE.SHIT; }
/* 76 */     else if (arg0.equals("barf")) { emoteType = EmotesDrawer.EMOTE_TYPE.BURP; }
/* 77 */     else if (arg0.equals("wow")) { emoteType = EmotesDrawer.EMOTE_TYPE.WOW; }
/* 78 */      if (emoteType == null) {
/* 79 */       ChatSender.addText("&cНету такой эмоции!");
/*    */       
/*    */       return;
/*    */     } 
/* 83 */     Main.emotesDrawer.sendEmote(emoteType);
/*    */   }
/*    */   
/*    */   public boolean func_71519_b(ICommandSender sender) {
/*    */     return true;
/*    */   }
/*    */   
/*    */   public String func_71517_b() {
/*    */     this;
/*    */     return command_text;
/*    */   }
/*    */   
/*    */   public String func_71518_a(ICommandSender sender) {
/*    */     return "Makes message rainbow setter";
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\emotes\CommandEmote.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */