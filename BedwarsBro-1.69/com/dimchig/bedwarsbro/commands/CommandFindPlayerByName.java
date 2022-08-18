/*    */ package com.dimchig.bedwarsbro.commands;
/*    */ 
/*    */ import com.dimchig.bedwarsbro.ChatSender;
/*    */ import com.dimchig.bedwarsbro.MyChatListener;
/*    */ import com.dimchig.bedwarsbro.OnMyTickEvent;
/*    */ import com.dimchig.bedwarsbro.hints.HintsFinder;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityPlayerSP;
/*    */ import net.minecraft.command.CommandBase;
/*    */ import net.minecraft.command.CommandException;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandFindPlayerByName
/*    */   extends CommandBase
/*    */ {
/* 21 */   public static String command_text = "/findplayer";
/*    */   
/*    */   public CommandFindPlayerByName() {
/* 24 */     this; command_text = command_text.replace("/", "");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_71519_b(ICommandSender sender) {
/* 29 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71517_b() {
/* 34 */     this; return command_text;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71518_a(ICommandSender sender) {
/* 39 */     return "Find player by name";
/*    */   }
/*    */   
/*    */   public void work(String name) {
/* 43 */     List<EntityPlayer> players = (Minecraft.func_71410_x()).field_71441_e.field_73010_i;
/* 44 */     for (EntityPlayer p : players) {
/* 45 */       if (p.func_70005_c_().equalsIgnoreCase(name)) {
/*    */         
/* 47 */         OnMyTickEvent.FIND_PLAYER_COMMAND_SEARCH = "";
/* 48 */         MyChatListener.playSound(MyChatListener.SOUND_PARTY_CHAT);
/* 49 */         ChatSender.addText("&fНайден &e" + p.func_70005_c_());
/* 50 */         EntityPlayerSP mod_palayer = (Minecraft.func_71410_x()).field_71439_g;
/* 51 */         HintsFinder.lookAtPlayer(mod_palayer.field_70165_t, mod_palayer.field_70163_u, mod_palayer.field_70161_v, p.field_70165_t, p.field_70163_u, p.field_70161_v);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/* 59 */     if (args.length != 1)
/*    */       return;  try {
/* 61 */       String name = args[0].trim();
/*    */       
/* 63 */       if (!OnMyTickEvent.FIND_PLAYER_COMMAND_SEARCH.equals(name)) {
/* 64 */         ChatSender.addText("&fПоиск &e" + name + "&f...");
/* 65 */         OnMyTickEvent.FIND_PLAYER_COMMAND_SEARCH = name;
/* 66 */         OnMyTickEvent.FIND_PLAYER_COMMAND_SEARCH_TIME = (new Date()).getTime();
/*    */       } 
/*    */       
/* 69 */       work(name);
/* 70 */     } catch (Exception ex) {
/* 71 */       ChatSender.addText("&cInvalid arguments!");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\commands\CommandFindPlayerByName.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */