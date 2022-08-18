/*     */ package com.dimchig.bedwarsbro.hints;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.CustomScoreboard;
/*     */ import com.dimchig.bedwarsbro.MyChatListener;
/*     */ import com.dimchig.bedwarsbro.particles.ParticleController;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.BlockStainedGlass;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.multiplayer.WorldClient;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.util.BlockPos;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WinEmote
/*     */ {
/*  24 */   public static int emoteStage = -1;
/*  25 */   public static int all_positions_idx = -1;
/*  26 */   public static int maxEmoteBlocksPerTick = 10000;
/*  27 */   public static int targetRange = 120;
/*  28 */   public static int currentRange = -1;
/*  29 */   public static BlockPos startingPos = null;
/*  30 */   public static long startingTime = 0L;
/*  31 */   public static ArrayList<BlockPos> all_positions = new ArrayList<BlockPos>();
/*     */   
/*     */   public static CustomScoreboard.TEAM_COLOR emoteStage_team_color;
/*     */   
/*     */   public static void handleEmote() {
/*  36 */     if (!MyChatListener.IS_IN_GAME) {
/*  37 */       emoteStage = -1;
/*  38 */       all_positions.clear();
/*     */       
/*     */       return;
/*     */     } 
/*  42 */     if (emoteStage > 0 && startingPos != null) {
/*     */       
/*  44 */       if ((new Date()).getTime() - startingTime > 15000L) {
/*  45 */         emoteStage = -1;
/*     */         return;
/*     */       } 
/*  48 */       if (all_positions_idx >= all_positions.size()) {
/*  49 */         if (currentRange == targetRange) {
/*  50 */           emoteStage = -1;
/*     */           return;
/*     */         } 
/*  53 */         fillRange(currentRange);
/*  54 */         currentRange++;
/*     */       } 
/*     */ 
/*     */       
/*  58 */       ArrayList<IBlockState> states = getStates(emoteStage_team_color);
/*  59 */       Random rnd = new Random();
/*     */       
/*  61 */       WorldClient worldClient = (Minecraft.func_71410_x()).field_71441_e;
/*     */       
/*  63 */       BlockPos pos = null;
/*  64 */       int cnt = 0;
/*     */       while (true) {
/*  66 */         cnt++;
/*  67 */         all_positions_idx++;
/*  68 */         if (cnt > maxEmoteBlocksPerTick || all_positions_idx >= all_positions.size())
/*  69 */           break;  pos = all_positions.get(all_positions_idx);
/*     */         
/*  71 */         if (worldClient.func_180495_p(pos).func_177230_c() != Blocks.field_150350_a) {
/*     */           
/*  73 */           cnt++;
/*     */ 
/*     */ 
/*     */           
/*  77 */           worldClient.func_175656_a(pos, states.get(rnd.nextInt(states.size())));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void fillRange(int range) {
/*  88 */     BlockPos pos = new BlockPos(0, 0, 0);
/*  89 */     for (int xi = -range; xi <= range; xi++) {
/*  90 */       for (int zi = -range; zi <= range; zi++) {
/*     */         
/*  92 */         int dist = Math.abs(xi);
/*  93 */         if (Math.abs(xi) < Math.abs(zi)) dist = Math.abs(zi); 
/*  94 */         if (dist == range)
/*     */         {
/*  96 */           for (int yi = 0; yi <= 120; yi++) {
/*     */             
/*  98 */             pos = new BlockPos(startingPos.func_177958_n() + xi, yi, startingPos.func_177952_p() + zi);
/*     */             
/* 100 */             all_positions.add(pos);
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void changeWorldBlocks(CustomScoreboard.TEAM_COLOR team_color) {
/* 108 */     EntityPlayerSP entityPlayerSP = (Minecraft.func_71410_x()).field_71439_g;
/* 109 */     emoteStage = 1;
/* 110 */     emoteStage_team_color = team_color;
/* 111 */     all_positions = new ArrayList<BlockPos>();
/* 112 */     all_positions_idx = -1;
/* 113 */     currentRange = 0;
/* 114 */     startingTime = (new Date()).getTime();
/* 115 */     startingPos = new BlockPos((Entity)entityPlayerSP);
/*     */ 
/*     */     
/* 118 */     for (int i = 0; i < 10; i++) {
/* 119 */       ParticleController.spawnFinalKillParticles(((Entity)entityPlayerSP).field_70165_t, ((Entity)entityPlayerSP).field_70163_u + (entityPlayerSP.func_70047_e() / 2.0F), ((Entity)entityPlayerSP).field_70161_v, team_color);
/*     */     }
/*     */   }
/*     */   
/*     */   public static ArrayList<IBlockState> getStates(CustomScoreboard.TEAM_COLOR team_color) {
/* 124 */     ArrayList<IBlockState> states = new ArrayList<IBlockState>();
/* 125 */     if (team_color == CustomScoreboard.TEAM_COLOR.RED) {
/* 126 */       states.add(Blocks.field_150451_bX.func_176223_P());
/* 127 */       states.add(Blocks.field_150325_L.func_176223_P().func_177226_a((IProperty)BlockStainedGlass.field_176547_a, (Comparable)EnumDyeColor.RED));
/* 128 */       states.add(Blocks.field_150406_ce.func_176223_P().func_177226_a((IProperty)BlockStainedGlass.field_176547_a, (Comparable)EnumDyeColor.RED));
/* 129 */     } else if (team_color == CustomScoreboard.TEAM_COLOR.YELLOW) {
/* 130 */       states.add(Blocks.field_150340_R.func_176223_P());
/* 131 */       states.add(Blocks.field_150325_L.func_176223_P().func_177226_a((IProperty)BlockStainedGlass.field_176547_a, (Comparable)EnumDyeColor.YELLOW));
/* 132 */     } else if (team_color == CustomScoreboard.TEAM_COLOR.GREEN) {
/* 133 */       states.add(Blocks.field_150475_bE.func_176223_P());
/* 134 */       states.add(Blocks.field_150325_L.func_176223_P().func_177226_a((IProperty)BlockStainedGlass.field_176547_a, (Comparable)EnumDyeColor.LIME));
/* 135 */     } else if (team_color == CustomScoreboard.TEAM_COLOR.AQUA) {
/* 136 */       states.add(Blocks.field_150484_ah.func_176223_P());
/* 137 */       states.add(Blocks.field_150325_L.func_176223_P().func_177226_a((IProperty)BlockStainedGlass.field_176547_a, (Comparable)EnumDyeColor.LIGHT_BLUE));
/* 138 */     } else if (team_color == CustomScoreboard.TEAM_COLOR.BLUE) {
/* 139 */       states.add(Blocks.field_150368_y.func_176223_P());
/* 140 */       states.add(Blocks.field_150325_L.func_176223_P().func_177226_a((IProperty)BlockStainedGlass.field_176547_a, (Comparable)EnumDyeColor.BLUE));
/* 141 */     } else if (team_color == CustomScoreboard.TEAM_COLOR.PINK) {
/* 142 */       states.add(Blocks.field_150325_L.func_176223_P().func_177226_a((IProperty)BlockStainedGlass.field_176547_a, (Comparable)EnumDyeColor.PINK));
/* 143 */       states.add(Blocks.field_150325_L.func_176223_P().func_177226_a((IProperty)BlockStainedGlass.field_176547_a, (Comparable)EnumDyeColor.MAGENTA));
/* 144 */     } else if (team_color == CustomScoreboard.TEAM_COLOR.GRAY) {
/* 145 */       states.add(Blocks.field_150348_b.func_176223_P());
/* 146 */       states.add(Blocks.field_150347_e.func_176223_P());
/* 147 */       states.add(Blocks.field_150417_aV.func_176223_P());
/* 148 */     } else if (team_color == CustomScoreboard.TEAM_COLOR.WHITE) {
/* 149 */       states.add(Blocks.field_150339_S.func_176223_P());
/* 150 */       states.add(Blocks.field_150371_ca.func_176223_P());
/* 151 */       states.add(Blocks.field_150325_L.func_176223_P().func_177226_a((IProperty)BlockStainedGlass.field_176547_a, (Comparable)EnumDyeColor.WHITE));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 161 */     return states;
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\hints\WinEmote.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */