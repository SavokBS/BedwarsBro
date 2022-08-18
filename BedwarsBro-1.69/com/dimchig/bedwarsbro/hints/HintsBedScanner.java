/*     */ package com.dimchig.bedwarsbro.hints;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.ChatSender;
/*     */ import com.dimchig.bedwarsbro.Main;
/*     */ import com.dimchig.bedwarsbro.MyChatListener;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.multiplayer.WorldClient;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HintsBedScanner
/*     */ {
/*     */   public static void scanBed() {
/*  21 */     EntityPlayerSP entityPlayerSP1 = (Minecraft.func_71410_x()).field_71439_g;
/*  22 */     MovingObjectPosition ray = entityPlayerSP1.func_174822_a(100.0D, 1.0F);
/*     */     
/*  24 */     if (ray == null) {
/*     */       return;
/*     */     }
/*  27 */     int blockHitX = (int)ray.field_72307_f.field_72450_a;
/*  28 */     int blockHitY = (int)ray.field_72307_f.field_72448_b;
/*  29 */     int blockHitZ = (int)ray.field_72307_f.field_72449_c;
/*     */     
/*  31 */     String prefix = MyChatListener.PREFIX_HINT_BED_SCANNER;
/*     */     
/*  33 */     ArrayList<BWBed> beds = findBeds(blockHitX, blockHitY, blockHitZ);
/*  34 */     if (beds == null || beds.size() == 0) {
/*     */       return;
/*     */     }
/*     */     
/*  38 */     EntityPlayerSP entityPlayerSP2 = (Minecraft.func_71410_x()).field_71439_g;
/*     */     
/*  40 */     BWBed bed = null;
/*  41 */     int min_dist = 999999;
/*  42 */     for (BWBed b : beds) {
/*  43 */       int dist = (int)Math.sqrt(Math.pow((b.part1_posX - blockHitX), 2.0D) + Math.pow((b.part1_posZ - blockHitZ), 2.0D));
/*  44 */       if (dist < min_dist) {
/*  45 */         min_dist = dist;
/*  46 */         bed = b;
/*     */       } 
/*     */     } 
/*     */     
/*  50 */     if (bed == null) {
/*  51 */       ChatSender.addText(prefix + "&fКровать не найдена!");
/*     */       
/*     */       return;
/*     */     } 
/*  55 */     String bed_analisys = bed.getAnalysis();
/*     */     
/*  57 */     if (Main.getConfigBool(Main.CONFIG_MSG.BED_SCANNER_ANIMATION) == true) {
/*     */       try {
/*  59 */         bed.showLayers((World)(Minecraft.func_71410_x()).field_71441_e);
/*  60 */       } catch (Exception e) {
/*  61 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */     
/*  65 */     ChatSender.addText(prefix + bed_analisys);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<BWBed> findBeds(int rayPosX, int rayPosY, int rayPosZ) {
/*     */     try {
/*  73 */       ArrayList<BWBed> beds_parts = new ArrayList<BWBed>();
/*  74 */       EntityPlayerSP entityPlayerSP = (Minecraft.func_71410_x()).field_71439_g;
/*     */       
/*  76 */       int range = 30;
/*  77 */       boolean bed_level = false;
/*  78 */       int cnt = 0;
/*  79 */       for (int yi = -range; yi < range; yi++) {
/*  80 */         for (int xi = -range; xi < range; xi++) {
/*  81 */           for (int zi = -range; zi < range; zi++) {
/*     */             
/*  83 */             int bx = rayPosX + xi;
/*  84 */             int by = rayPosY + yi;
/*  85 */             int bz = rayPosZ + zi;
/*  86 */             cnt++;
/*  87 */             Block block = (Minecraft.func_71410_x()).field_71441_e.func_180495_p(new BlockPos(bx, by, bz)).func_177230_c();
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*  92 */             if (block != null && block != null && 
/*  93 */               block.func_149739_a().substring(5).equals("bed")) {
/*  94 */               bed_level = true;
/*     */               
/*  96 */               beds_parts.add(new BWBed(bx, by, bz, 0, 0, 0));
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 101 */         if (bed_level == true) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */       
/* 106 */       ArrayList<BWBed> beds = new ArrayList<BWBed>();
/*     */       
/* 108 */       int cnt_prevent_loop = 0;
/*     */       
/* 110 */       cnt_prevent_loop++;
/* 111 */       while (beds_parts.size() > 0 && cnt_prevent_loop <= 1000) {
/* 112 */         for (BWBed bed1 : beds_parts) {
/* 113 */           boolean isBreak = false;
/* 114 */           for (BWBed bed2 : beds_parts) {
/* 115 */             if (bed1.part1_posX != bed2.part1_posX || bed1.part1_posY != bed2.part1_posY || bed1.part1_posZ != bed2.part1_posZ)
/*     */             {
/* 117 */               if (BWBed.isBlockConnectsToBlock(bed1.part1_posX, bed1.part1_posY, bed1.part1_posZ, bed2.part1_posX, bed2.part1_posY, bed2.part1_posZ)) {
/* 118 */                 beds.add(new BWBed(bed1.part1_posX, bed1.part1_posY, bed1.part1_posZ, bed2.part1_posX, bed2.part1_posY, bed2.part1_posZ));
/* 119 */                 beds_parts.remove(bed1);
/* 120 */                 beds_parts.remove(bed2);
/* 121 */                 isBreak = true;
/*     */                 break;
/*     */               } 
/*     */             }
/*     */           } 
/* 126 */           if (isBreak)
/*     */             break; 
/*     */         } 
/*     */       } 
/* 130 */       WorldClient worldClient = (Minecraft.func_71410_x()).field_71441_e;
/* 131 */       for (BWBed bed : beds) {
/* 132 */         bed.scanDefence((World)worldClient);
/*     */       }
/*     */       
/* 135 */       return beds;
/*     */     }
/* 137 */     catch (Exception ex) {
/* 138 */       ex.printStackTrace();
/*     */       
/* 140 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\hints\HintsBedScanner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */