/*     */ package com.dimchig.bedwarsbro.hints;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.CustomScoreboard;
/*     */ import com.dimchig.bedwarsbro.MyChatListener;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HintsPlayerScanner
/*     */ {
/*     */   public static class BWPlayer
/*     */   {
/*     */     public EntityPlayer en;
/*     */     public String name;
/*     */     public BWItem item_in_hand;
/*     */     public int item_in_hand_amount;
/*     */     public BWItemsHandler.BWItemArmourLevel armourLevel;
/*     */     public double posX;
/*     */     public double posY;
/*     */     public double posZ;
/*     */     public double distToPlayer;
/*     */     public CustomScoreboard.TEAM_COLOR team_color;
/*     */     
/*     */     public BWPlayer(EntityPlayer en, String name, BWItem item_in_hand, int item_in_hand_amount, BWItemsHandler.BWItemArmourLevel armourLevel, double posX, double posY, double posZ, double distToPlayer) {
/*  36 */       this.en = en;
/*  37 */       this.name = name;
/*  38 */       this.item_in_hand = item_in_hand;
/*  39 */       this.item_in_hand_amount = item_in_hand_amount;
/*  40 */       this.armourLevel = armourLevel;
/*  41 */       this.posX = posX;
/*  42 */       this.posY = posY;
/*  43 */       this.posZ = posZ;
/*  44 */       this.distToPlayer = distToPlayer;
/*     */       
/*  46 */       this.team_color = CustomScoreboard.TEAM_COLOR.NONE;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static ArrayList<BWPlayer> scanPlayers() {
/*     */     try {
/*  53 */       ArrayList<BWPlayer> players = new ArrayList<BWPlayer>();
/*     */       
/*  55 */       EntityPlayerSP entityPlayerSP = (Minecraft.func_71410_x()).field_71439_g;
/*  56 */       ArrayList<EntityPlayer> entities = (ArrayList<EntityPlayer>)(Minecraft.func_71410_x()).field_71441_e.field_73010_i;
/*  57 */       for (EntityPlayer en : entities) {
/*     */         try {
/*  59 */           double posX = en.field_70165_t;
/*  60 */           double posY = en.field_70163_u;
/*  61 */           double posZ = en.field_70161_v;
/*  62 */           double dist = Math.sqrt(Math.pow(((Entity)entityPlayerSP).field_70165_t - posX, 2.0D) + Math.pow(((Entity)entityPlayerSP).field_70161_v - posZ, 2.0D));
/*     */           
/*  64 */           BWPlayer player = new BWPlayer(en, en.func_70005_c_(), null, 0, BWItemsHandler.BWItemArmourLevel.NONE, posX, posY, posZ, dist);
/*  65 */           player.team_color = MyChatListener.getEntityTeamColor(en);
/*     */           
/*  67 */           setPlayerHoldingItem(en, player);
/*     */           
/*  69 */           player.armourLevel = getPlayerArmourLevel(en);
/*     */           
/*  71 */           players.add(player);
/*     */         }
/*  73 */         catch (Exception exception) {}
/*     */       } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 115 */       return players;
/* 116 */     } catch (Exception exception) {
/*     */ 
/*     */       
/* 119 */       return new ArrayList<BWPlayer>();
/*     */     } 
/*     */   }
/*     */   public static void setPlayerHoldingItem(EntityPlayer en, BWPlayer player) {
/* 123 */     if (en.func_70035_c() != null) {
/*     */       
/* 125 */       ItemStack item = en.field_71071_by.func_70448_g();
/* 126 */       if (item != null) {
/* 127 */         String displayer_name = item.func_82833_r();
/* 128 */         String local_name = item.func_77977_a().substring(5);
/*     */ 
/*     */         
/* 131 */         player.item_in_hand = BWItemsHandler.findItem(local_name, displayer_name);
/* 132 */         player.item_in_hand_amount = item.field_77994_a;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static BWItemsHandler.BWItemArmourLevel getPlayerArmourLevel(EntityPlayer en) {
/* 140 */     if ((en.func_70035_c()).length == 0) return BWItemsHandler.BWItemArmourLevel.NONE; 
/* 141 */     ItemStack itemStack = en.func_70035_c()[1];
/* 142 */     if (itemStack == null) {
/* 143 */       return BWItemsHandler.BWItemArmourLevel.NONE;
/*     */     }
/* 145 */     String displayer_name = itemStack.func_82833_r();
/* 146 */     String local_name = itemStack.func_77977_a().substring(5);
/* 147 */     BWItem item_armour = BWItemsHandler.findItem(local_name, displayer_name);
/*     */     
/* 149 */     return item_armour.armourLevel;
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\hints\HintsPlayerScanner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */