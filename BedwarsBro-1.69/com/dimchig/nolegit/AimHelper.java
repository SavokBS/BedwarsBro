/*     */ package com.dimchig.nolegit;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.ChatSender;
/*     */ import com.dimchig.bedwarsbro.MyChatListener;
/*     */ import com.dimchig.bedwarsbro.hints.HintsFinder;
/*     */ import java.awt.Color;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.multiplayer.WorldClient;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EntitySelectors;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraftforge.client.event.RenderWorldLastEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AimHelper
/*     */ {
/*     */   static Minecraft mc;
/*  34 */   static double aim_speed = 0.02D;
/*     */   static boolean isActive;
/*  36 */   static double aim_treshold = 50.0D;
/*     */   
/*     */   public AimHelper() {
/*  39 */     mc = Minecraft.func_71410_x();
/*     */   }
/*     */   
/*     */   public void toggle() {
/*  43 */     isActive = !isActive;
/*  44 */     ChatSender.addText(MyChatListener.PREFIX_BEDWARSBRO + (isActive ? "&aВключен" : "&cВыключен") + " AimHelper");
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRenderWorldLastEvent(RenderWorldLastEvent event) {
/*  49 */     if (isActive) if (MyChatListener.IS_IN_GAME) {
/*     */         try {
/*  51 */           EntityPlayerSP player = mc.field_71439_g;
/*     */           
/*  53 */           Vec3 playerPos = getVectorWithPartialTicks((Entity)player, event.partialTicks);
/*     */           
/*  55 */           if (!isHoldingSword(player))
/*     */             return; 
/*  57 */           EntityPlayer closestPlayer = getClosestPlayer(player, playerPos, event.partialTicks);
/*  58 */           if (closestPlayer == null) {
/*     */             return;
/*     */           }
/*  61 */           boolean isEnviromentSafe = isEnviromentSafe(player, playerPos);
/*  62 */           if (!isEnviromentSafe)
/*     */             return; 
/*  64 */           Vec3 closestPlayerPos = getVectorWithPartialTicks((Entity)closestPlayer, event.partialTicks);
/*     */ 
/*     */           
/*  67 */           double dX = playerPos.field_72450_a - closestPlayerPos.field_72450_a;
/*  68 */           double dY = playerPos.field_72448_b - closestPlayerPos.field_72448_b;
/*  69 */           double dZ = playerPos.field_72449_c - closestPlayerPos.field_72449_c;
/*  70 */           double t_yaw = myMap(Math.atan2(dZ, dX), -3.141592653589793D, Math.PI, -180.0D, 180.0D) + 90.0D;
/*  71 */           double t_pitch = myMap(Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI, Math.PI, 6.283185307179586D, 90.0D, -90.0D);
/*     */           
/*  73 */           smoothRotate(player, playerPos, closestPlayer, closestPlayerPos, t_yaw, t_pitch);
/*     */         }
/*  75 */         catch (Exception e) {
/*  76 */           e.printStackTrace();
/*     */         } 
/*     */         return;
/*     */       }  
/*     */   }
/*     */   private boolean isEnviromentSafe(EntityPlayerSP player, Vec3 playerPos) {
/*  82 */     if (player.func_70093_af()) return false;
/*     */ 
/*     */     
/*  85 */     if (mc.field_71462_r != null) return false;
/*     */ 
/*     */     
/*  88 */     int range = 1;
/*     */     
/*  90 */     int x = (int)Math.floor(player.field_70165_t);
/*  91 */     int y = (int)Math.floor(player.field_70163_u);
/*  92 */     int z = (int)Math.floor(player.field_70161_v);
/*     */     
/*  94 */     int block_cnt = 0;
/*  95 */     WorldClient worldClient = mc.field_71441_e;
/*  96 */     BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos(0, 0, 0);
/*     */     
/*  98 */     for (int xi = -range; xi <= range; xi++) {
/*  99 */       for (int zi = -range; zi <= range; zi++) {
/* 100 */         boolean isBlockFound = false;
/* 101 */         for (int yi = 0; yi >= -2; yi--) {
/* 102 */           blockPos.func_181079_c(x + xi, y + yi, z + zi);
/* 103 */           IBlockState state = worldClient.func_180495_p((BlockPos)blockPos);
/* 104 */           if (state != null && state.func_177230_c() != Blocks.field_150350_a) {
/* 105 */             isBlockFound = true;
/*     */             break;
/*     */           } 
/*     */         } 
/* 109 */         if (isBlockFound) block_cnt++; 
/*     */       } 
/*     */     } 
/* 112 */     int must_have_blocks = (int)Math.pow((range * 2 + 1), 2.0D);
/* 113 */     return (must_have_blocks == block_cnt);
/*     */   }
/*     */   
/*     */   private void smoothRotate(EntityPlayerSP player, Vec3 playerPos, EntityPlayer closestPlayer, Vec3 closestPlayerPos, double target_yaw, double target_pitch) {
/* 117 */     float player_yaw = (float)getRealYaw(player.field_70177_z);
/* 118 */     target_yaw = (float)getRealYaw(target_yaw);
/* 119 */     float player_pitch = player.field_70125_A;
/*     */ 
/*     */     
/* 122 */     double dist = closestPlayerPos.func_72438_d(playerPos);
/* 123 */     if (dist > 5.0D) {
/*     */       return;
/*     */     }
/* 126 */     double yaw_distance = Math.abs(player_yaw - target_yaw);
/*     */     
/* 128 */     if (yaw_distance > aim_treshold) {
/*     */       return;
/*     */     }
/*     */     
/* 132 */     double diff = (target_yaw > player_yaw) ? 1.0D : -1.0D;
/* 133 */     double new_yaw = player_yaw + Math.max(diff * 5.0D, Math.abs(target_yaw - player_yaw)) * diff * aim_speed;
/* 134 */     double new_pitch = player_pitch;
/* 135 */     HintsFinder.rotateTo((Entity)(Minecraft.func_71410_x()).field_71439_g, (float)new_yaw, (float)new_pitch);
/*     */   }
/*     */   
/*     */   private void GL_start(Vec3 playerPos) {
/* 139 */     GL11.glPushMatrix();
/* 140 */     GL11.glPushAttrib(8192);
/* 141 */     GL11.glTranslated(-playerPos.field_72450_a, -playerPos.field_72448_b, -playerPos.field_72449_c);
/* 142 */     GL11.glDisable(2896);
/* 143 */     GL11.glDisable(3553);
/* 144 */     GL11.glDisable(2929);
/* 145 */     GlStateManager.func_179084_k();
/* 146 */     GL11.glLineWidth(1.0F);
/*     */   }
/*     */   
/*     */   private void GL_end() {
/* 150 */     GL11.glPopAttrib();
/* 151 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   private boolean isHoldingSword(EntityPlayerSP player) {
/* 155 */     if (player.func_71045_bC() == null) return false; 
/* 156 */     String[] items = { "sword", "stick" };
/* 157 */     for (String item : items) {
/* 158 */       if (player.func_71045_bC().func_77973_b().getRegistryName().contains(item)) return true; 
/*     */     } 
/* 160 */     return false;
/*     */   }
/*     */   
/*     */   private double getRealYaw(double yaw) {
/* 164 */     return (yaw % 360.0D + 360.0D) % 360.0D;
/*     */   }
/*     */   
/*     */   private EntityPlayer getClosestPlayer(EntityPlayerSP mod_player, Vec3 mod_player_pos, float partialTicks) {
/* 168 */     List<EntityPlayer> players = mc.field_71441_e.func_175644_a(EntityPlayer.class, EntitySelectors.field_94557_a);
/* 169 */     if (players == null || players.size() == 0) return null;
/*     */     
/* 171 */     double mod_player_yaw = getRealYaw(mod_player.field_70177_z);
/*     */     
/* 173 */     EntityPlayer closestPlayer = null;
/* 174 */     double dist = 1000.0D;
/* 175 */     for (EntityPlayer en : players) {
/* 176 */       if (en == mod_player || en.func_96124_cp() == mod_player.func_96124_cp()) {
/*     */         continue;
/*     */       }
/*     */       
/* 180 */       Vec3 p_pos = getVectorWithPartialTicks((Entity)en, partialTicks);
/* 181 */       if (p_pos.func_72438_d(mod_player_pos) > 5.0D)
/*     */         continue; 
/* 183 */       double dX = mod_player_pos.field_72450_a - p_pos.field_72450_a;
/* 184 */       double dY = mod_player_pos.field_72448_b - p_pos.field_72448_b;
/* 185 */       double dZ = mod_player_pos.field_72449_c - p_pos.field_72449_c;
/* 186 */       double t_yaw = getRealYaw(myMap(Math.atan2(dZ, dX), -3.141592653589793D, Math.PI, -180.0D, 180.0D) + 90.0D);
/*     */       
/* 188 */       double d = Math.abs(mod_player_yaw - t_yaw);
/*     */       
/* 190 */       if (d < dist) {
/* 191 */         dist = d;
/* 192 */         closestPlayer = en;
/*     */       } 
/*     */     } 
/* 195 */     return closestPlayer;
/*     */   }
/*     */   
/*     */   private double myMap(double value, double leftMin, double leftMax, double rightMin, double rightMax) {
/* 199 */     double leftSpan = leftMax - leftMin;
/* 200 */     double rightSpan = rightMax - rightMin;
/* 201 */     double valueScaled = (value - leftMin) / leftSpan;
/* 202 */     return rightMin + valueScaled * rightSpan;
/*     */   }
/*     */   
/*     */   private Vec3 getVectorWithPartialTicks(Entity en, float partialTicks) {
/* 206 */     double x = en.field_70169_q + (en.field_70165_t - en.field_70169_q) * partialTicks;
/* 207 */     double y = en.field_70167_r + (en.field_70163_u - en.field_70167_r) * partialTicks;
/* 208 */     double z = en.field_70166_s + (en.field_70161_v - en.field_70166_s) * partialTicks;
/* 209 */     return new Vec3(x, y, z);
/*     */   }
/*     */   
/*     */   private void setLineColor(Color color) {
/* 213 */     GL11.glColor4f(color.getRed(), color.getGreen(), color.getBlue(), 1.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\nolegit\AimHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */