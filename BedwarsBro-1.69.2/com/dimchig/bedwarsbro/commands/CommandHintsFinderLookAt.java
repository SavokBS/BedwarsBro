/*    */ package com.dimchig.bedwarsbro.commands;
/*    */ 
/*    */ import com.dimchig.bedwarsbro.ChatSender;
/*    */ import com.dimchig.bedwarsbro.hints.HintsFinder;
/*    */ import com.dimchig.bedwarsbro.particles.ParticleController;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityPlayerSP;
/*    */ import net.minecraft.command.CommandBase;
/*    */ import net.minecraft.command.CommandException;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandHintsFinderLookAt
/*    */   extends CommandBase
/*    */ {
/* 22 */   public static String command_text = "/bedwarsChatModLookAt";
/*    */   
/*    */   public CommandHintsFinderLookAt() {
/* 25 */     this; command_text = command_text.replace("/", "");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_71519_b(ICommandSender sender) {
/* 30 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71517_b() {
/* 35 */     this; return command_text;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71518_a(ICommandSender sender) {
/* 40 */     return "Makes message rainbow";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/* 46 */     if (args.length != 4)
/*    */       return;  try {
/* 48 */       double posX = Double.parseDouble(args[0]);
/* 49 */       double posY = Double.parseDouble(args[1]);
/* 50 */       double posZ = Double.parseDouble(args[2]);
/* 51 */       int isEmerald = Integer.parseInt(args[3]);
/* 52 */       EntityPlayerSP entityPlayerSP = (Minecraft.func_71410_x()).field_71439_g;
/* 53 */       HintsFinder.lookAtPlayer(((Entity)entityPlayerSP).field_70165_t, ((Entity)entityPlayerSP).field_70163_u + entityPlayerSP.func_70047_e(), ((Entity)entityPlayerSP).field_70161_v, posX, posY, posZ);
/* 54 */       if (isEmerald == 1) { ParticleController.spawnGenEmeraldParticles(posX, posY, posZ, 1); }
/* 55 */       else { ParticleController.spawnGenDiamondParticles(posX, posY, posZ, 1); } 
/* 56 */     } catch (Exception ex) {
/* 57 */       ChatSender.addText("&cInvalid arguments!");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\commands\CommandHintsFinderLookAt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */