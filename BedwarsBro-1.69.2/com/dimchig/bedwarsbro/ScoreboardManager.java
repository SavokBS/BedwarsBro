/*     */ package com.dimchig.bedwarsbro;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.scoreboard.ScorePlayerTeam;
/*     */ import net.minecraft.scoreboard.Scoreboard;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ScoreboardManager
/*     */ {
/*  13 */   public static List<MyScoreboardLine> lines = new ArrayList<MyScoreboardLine>();
/*     */   
/*     */   public static class MyScoreboardLine {
/*     */     public ScorePlayerTeam team;
/*     */     String value;
/*     */     
/*     */     public MyScoreboardLine(ScorePlayerTeam team, String value) {
/*  20 */       this.team = team;
/*  21 */       this.value = value;
/*     */     }
/*     */     
/*     */     public void setText(String text) {
/*  25 */       String new_val = text.replace("&", "§");
/*  26 */       this.team.func_96666_b(new_val);
/*  27 */       this.team.func_96662_c("");
/*  28 */       this.value = new_val;
/*     */     }
/*     */   }
/*     */   
/*     */   public static String readRawScoreboard() {
/*  33 */     String s = "";
/*     */     try {
/*  35 */       Scoreboard scoreboard = (Minecraft.func_71410_x()).field_71441_e.func_96441_U();
/*     */       
/*  37 */       for (ScorePlayerTeam team : scoreboard.func_96525_g()) {
/*  38 */         s = s + (team.func_96668_e() + team.func_96663_f()).trim();
/*     */       }
/*  40 */       return s;
/*  41 */     } catch (Exception ex) {
/*  42 */       ex.printStackTrace();
/*  43 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void readScoreboard() {
/*     */     try {
/*  49 */       lines = new ArrayList<MyScoreboardLine>();
/*     */       
/*  51 */       Scoreboard scoreboard = (Minecraft.func_71410_x()).field_71441_e.func_96441_U();
/*     */       
/*  53 */       for (ScorePlayerTeam team : scoreboard.func_96525_g()) {
/*  54 */         String s = ColorCodesManager.removeColorCodes(team.func_96668_e() + team.func_96663_f());
/*  55 */         if (s.length() > 1) {
/*  56 */           lines.add(new MyScoreboardLine(team, s));
/*     */         }
/*     */       }
/*     */     
/*     */     }
/*  61 */     catch (Exception ex) {
/*  62 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void hardReplaceText(String from, String to) {
/*  67 */     hardReplaceText(from, from, to);
/*     */   }
/*     */   
/*     */   public static void hardReplaceText(String findby, String from, String to) {
/*  71 */     readScoreboard();
/*     */     
/*  73 */     for (MyScoreboardLine l : lines) {
/*  74 */       String prefix = l.team.func_96668_e();
/*  75 */       String suffix = l.team.func_96663_f();
/*  76 */       String new_text = (prefix + suffix).replace("&", "§").replace("§r", "").trim();
/*  77 */       l.team.func_96662_c(new_text);
/*  78 */       l.team.func_96666_b("");
/*     */       
/*  80 */       if (new_text.contains(findby)) {
/*  81 */         l.team.func_96662_c(l.team.func_96663_f().replace(from, ColorCodesManager.replaceColorCodesInString(to)));
/*     */       }
/*  83 */       l.value = ColorCodesManager.removeColorCodes(new_text);
/*     */     } 
/*     */   }
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
/*     */   public static void replaceText(String from, String to) {
/* 101 */     readScoreboard();
/*     */     
/* 103 */     for (MyScoreboardLine l : lines) {
/* 104 */       if (l.value.contains(from)) {
/* 105 */         l.setText(to);
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void replaceTextPartly(String from, String to) {
/* 112 */     readScoreboard();
/*     */     
/* 114 */     for (MyScoreboardLine l : lines) {
/* 115 */       if (l.value.contains(from)) {
/* 116 */         l.setText(l.value.replace(from, to));
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void replaceText(String[] from_array, String to) {
/* 123 */     for (String s : from_array) {
/* 124 */       replaceText(s, to);
/*     */     }
/*     */   }
/*     */   
/*     */   public static String getText(String ref) {
/* 129 */     readScoreboard();
/*     */     
/* 131 */     for (MyScoreboardLine l : lines) {
/* 132 */       if (l != null && l.value != null && 
/* 133 */         l.value.contains(ref)) {
/* 134 */         return l.value;
/*     */       }
/*     */     } 
/* 137 */     return null;
/*     */   }
/*     */   
/*     */   public static String getRawText(String ref) {
/* 141 */     readScoreboard();
/*     */     
/* 143 */     for (MyScoreboardLine l : lines) {
/* 144 */       if (l.value.contains(ref)) {
/* 145 */         return (l.team.func_96668_e() + l.team.func_96663_f()).trim();
/*     */       }
/*     */     } 
/* 148 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\ScoreboardManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */