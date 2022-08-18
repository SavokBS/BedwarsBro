/*     */ package com.dimchig.bedwarsbro.gui;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.CustomScoreboard;
/*     */ import com.dimchig.bedwarsbro.Main;
/*     */ import com.dimchig.bedwarsbro.MyChatListener;
/*     */ import com.dimchig.bedwarsbro.hints.HintsValidator;
/*     */ import com.dimchig.bedwarsbro.hints.InvulnerableTime;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.multiplayer.WorldClient;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
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
/*     */ public class GuiPlayerFocus
/*     */ {
/*     */   static Minecraft mc;
/*     */   public static boolean STATE = false;
/*     */   public static boolean isAutoWaterDropActive = false;
/*     */   public static boolean isInvulnerableTimerActive = false;
/*     */   public static boolean isInvulnerableTimerSoundsActive = false;
/*     */   public static boolean isNamePlateActive = false;
/*     */   public static boolean isNamePlateRainbowActive = false;
/*     */   public static boolean isResourcesHologramActive = false;
/*  75 */   public static int rainbowSpeed = 1;
/*  76 */   public static String namePlateCustomColor = "";
/*     */   
/*     */   public static boolean isT_Active = false;
/*     */   private ArrayList<MyLine> lines;
/*     */   
/*     */   public GuiPlayerFocus() {
/*  82 */     mc = Minecraft.func_71410_x();
/*  83 */     this.lines = new ArrayList<MyLine>();
/*  84 */     updateBooleans();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateBooleans() {
/*  89 */     this; isAutoWaterDropActive = HintsValidator.isAutoWaterDropActive();
/*  90 */     this; isInvulnerableTimerActive = HintsValidator.isInvulnerableTimerActive();
/*  91 */     this; isInvulnerableTimerSoundsActive = HintsValidator.isInvulnerableTimerSoundsActive();
/*  92 */     this; isNamePlateActive = HintsValidator.isNamePlateActive();
/*  93 */     this; isNamePlateRainbowActive = HintsValidator.isNamePlateRainbowActive();
/*  94 */     this; rainbowSpeed = HintsValidator.getRainbowSpeed();
/*  95 */     this; namePlateCustomColor = HintsValidator.getNamePlateCustomColor();
/*  96 */     this; isResourcesHologramActive = HintsValidator.isResourcesHologramActive();
/*     */   }
/*     */   
/*     */   private class MyLine { public Vec3 pos1;
/*     */     public Vec3 pos2;
/*     */     public Color color;
/*     */     
/*     */     public MyLine(Vec3 pos1, Vec3 pos2, Color color) {
/* 104 */       this.pos1 = pos1;
/* 105 */       this.pos2 = pos2;
/* 106 */       this.color = color;
/*     */     } }
/*     */ 
/*     */   
/*     */   private static boolean cflag = false;
/*     */   
/* 112 */   private static ArrayList<Long> cps = new ArrayList<Long>();
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
/*     */   @SubscribeEvent
/*     */   public void onRenderWorldLastEvent(RenderWorldLastEvent event) {
/* 149 */     float partialTicks = event.partialTicks;
/*     */     
/* 151 */     EntityPlayerSP player = mc.field_71439_g;
/* 152 */     WorldClient worldClient = mc.field_71441_e;
/* 153 */     if (player == null || worldClient == null) {
/*     */       return;
/*     */     }
/* 156 */     double d0 = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * partialTicks;
/* 157 */     double d1 = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * partialTicks;
/* 158 */     double d2 = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * partialTicks;
/* 159 */     Vec3 pos = new Vec3(d0, d1, d2);
/*     */     
/* 161 */     this; if (isAutoWaterDropActive) {
/* 162 */       Main.autoWaterDrop.check(player, pos);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 167 */     this; if (isInvulnerableTimerActive && MyChatListener.IS_IN_GAME) {
/* 168 */       InvulnerableTime.scan(((World)worldClient).field_73010_i, pos, partialTicks, isInvulnerableTimerSoundsActive);
/*     */     }
/*     */     
/* 171 */     this; if (isInvulnerableTimerActive && MyChatListener.IS_IN_GAME) {
/* 172 */       InvulnerableTime.scan(((World)worldClient).field_73010_i, pos, partialTicks, isInvulnerableTimerSoundsActive);
/*     */     }
/*     */ 
/*     */     
/* 176 */     this; if (isNamePlateActive) {
/* 177 */       Main.namePlate.draw(pos, isNamePlateRainbowActive, rainbowSpeed, namePlateCustomColor);
/*     */     }
/*     */     
/* 180 */     this; if (isResourcesHologramActive) {
/* 181 */       Main.guiResourceHologram.draw(pos);
/*     */     }
/*     */     
/* 184 */     Main.tntjump.draw(pos, partialTicks);
/*     */     
/* 186 */     Main.cantHitDimChig.handle();
/*     */     
/* 188 */     Main.emotesDrawer.draw3D(pos, partialTicks, player.field_70177_z, player.field_70125_A);
/*     */     
/* 190 */     if (!STATE)
/*     */       return; 
/* 192 */     if ((Minecraft.func_71410_x()).field_71474_y.field_152399_aq.func_151468_f()) {
/* 193 */       isT_Active = !isT_Active;
/* 194 */       MyChatListener.playSound(isT_Active ? MyChatListener.SOUND_PARTY_CHAT : MyChatListener.SOUND_REJECT);
/*     */     } 
/*     */     
/* 197 */     GL11.glPushMatrix();
/* 198 */     GL11.glPushAttrib(8192);
/*     */     
/* 200 */     GL11.glTranslated(-pos.field_72450_a, -pos.field_72448_b, -pos.field_72449_c);
/* 201 */     GL11.glDisable(2896);
/* 202 */     GL11.glDisable(3553);
/* 203 */     GL11.glDisable(2929);
/* 204 */     GlStateManager.func_179084_k();
/* 205 */     GL11.glLineWidth(1.0F);
/*     */ 
/*     */     
/* 208 */     List<EntityPlayer> entities = (Minecraft.func_71410_x()).field_71441_e.field_73010_i;
/* 209 */     double playerSpeedY = mc.field_71439_g.field_70163_u - mc.field_71439_g.field_70167_r;
/* 210 */     for (EntityPlayer en : entities) {
/* 211 */       if (en == null || 
/* 212 */         en == player || 
/* 213 */         en.func_96124_cp() == player.func_96124_cp())
/*     */         continue; 
/* 215 */       double posX = en.field_70169_q + (en.field_70165_t - en.field_70169_q) * partialTicks;
/* 216 */       double posY = en.field_70167_r + (en.field_70163_u - en.field_70167_r) * partialTicks;
/* 217 */       double posZ = en.field_70166_s + (en.field_70161_v - en.field_70166_s) * partialTicks;
/*     */       
/* 219 */       double posX1 = posX - (en.field_70130_N / 2.0F);
/* 220 */       double posZ1 = posZ - (en.field_70130_N / 2.0F);
/* 221 */       double posX2 = posX + (en.field_70130_N / 2.0F);
/* 222 */       double posZ2 = posZ + (en.field_70130_N / 2.0F);
/*     */       
/* 224 */       CustomScoreboard.TEAM_COLOR team_color = MyChatListener.getEntityTeamColor(en);
/* 225 */       setLineColor(getColorByTeam(team_color));
/*     */       
/* 227 */       drawBox(posX1, posY, posZ1, posX2, posY + en.field_70131_O, posZ2);
/*     */ 
/*     */       
/* 230 */       double enSpeedY = en.field_70163_u - en.field_70167_r;
/* 231 */       if (isT_Active && enSpeedY > -1.0D && playerSpeedY > -1.0D) {
/* 232 */         Vec3 head_pos = new Vec3(d0, d1 + player.func_70047_e(), d2);
/*     */         
/* 234 */         drawLineWithGL(head_pos, new Vec3(posX, posY + en.eyeHeight, posZ));
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 239 */     if (this.lines.size() > 0) {
/* 240 */       for (MyLine l : this.lines) {
/* 241 */         Vec3 p1 = l.pos1;
/* 242 */         if (p1 == null) p1 = new Vec3(d0, d1 + player.func_70047_e(), d2);
/*     */         
/* 244 */         setLineColor(l.color);
/*     */         
/* 246 */         drawLineWithGL(p1, l.pos2);
/*     */       } 
/*     */     }
/*     */     
/* 250 */     GL11.glPopAttrib();
/* 251 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getColorByTeam(CustomScoreboard.TEAM_COLOR team_color) {
/* 256 */     Color color = new Color(0.0F, 0.0F, 0.0F, 1.0F);
/* 257 */     if (team_color == CustomScoreboard.TEAM_COLOR.RED) {
/* 258 */       color = new Color(1.0F, 0.0F, 0.0F, 1.0F);
/* 259 */     } else if (team_color == CustomScoreboard.TEAM_COLOR.YELLOW) {
/* 260 */       color = new Color(1.0F, 1.0F, 0.0F, 1.0F);
/* 261 */     } else if (team_color == CustomScoreboard.TEAM_COLOR.GREEN) {
/* 262 */       color = new Color(0.0F, 1.0F, 0.0F, 1.0F);
/* 263 */     } else if (team_color == CustomScoreboard.TEAM_COLOR.AQUA) {
/* 264 */       color = new Color(0.0F, 1.0F, 1.0F, 1.0F);
/* 265 */     } else if (team_color == CustomScoreboard.TEAM_COLOR.BLUE) {
/* 266 */       color = new Color(0.0F, 0.0F, 1.0F, 1.0F);
/* 267 */     } else if (team_color == CustomScoreboard.TEAM_COLOR.PINK) {
/* 268 */       color = new Color(1.0F, 0.0F, 1.0F, 1.0F);
/* 269 */     } else if (team_color == CustomScoreboard.TEAM_COLOR.WHITE) {
/* 270 */       color = new Color(1.0F, 1.0F, 1.0F, 1.0F);
/* 271 */     } else if (team_color == CustomScoreboard.TEAM_COLOR.GRAY) {
/* 272 */       color = new Color(0.6F, 0.6F, 0.6F, 1.0F);
/*     */     } 
/* 274 */     return color;
/*     */   }
/*     */   
/*     */   private void setLineColor(Color color) {
/* 278 */     GL11.glColor4f(color.getRed(), color.getGreen(), color.getBlue(), 1.0F);
/*     */   }
/*     */   
/*     */   public void addLine(Vec3 pos1, Vec3 pos2, Color color) {
/* 282 */     this.lines.add(new MyLine(pos1, pos2, color));
/*     */   }
/*     */   
/*     */   public void clearLines() {
/* 286 */     this.lines.clear();
/*     */   }
/*     */   
/*     */   public void drawSquare(double x1, double y1, double z1, double x2, double y2, double z2) {
/* 290 */     drawLineWithGL(new Vec3(x1, y1, z1), new Vec3(x2, y1, z2));
/* 291 */     drawLineWithGL(new Vec3(x1, y2, z1), new Vec3(x2, y2, z2));
/* 292 */     drawLineWithGL(new Vec3(x1, y1, z1), new Vec3(x1, y2, z1));
/* 293 */     drawLineWithGL(new Vec3(x2, y1, z2), new Vec3(x2, y2, z2));
/*     */   }
/*     */   
/*     */   public void drawBox(double x1, double y1, double z1, double x2, double y2, double z2) {
/* 297 */     drawLineWithGL(new Vec3(x1, y1, z1), new Vec3(x2, y1, z1));
/* 298 */     drawLineWithGL(new Vec3(x2, y1, z1), new Vec3(x2, y1, z2));
/* 299 */     drawLineWithGL(new Vec3(x2, y1, z2), new Vec3(x1, y1, z2));
/* 300 */     drawLineWithGL(new Vec3(x1, y1, z2), new Vec3(x1, y1, z1));
/* 301 */     drawLineWithGL(new Vec3(x1, y2, z1), new Vec3(x2, y2, z1));
/* 302 */     drawLineWithGL(new Vec3(x2, y2, z1), new Vec3(x2, y2, z2));
/* 303 */     drawLineWithGL(new Vec3(x2, y2, z2), new Vec3(x1, y2, z2));
/* 304 */     drawLineWithGL(new Vec3(x1, y2, z2), new Vec3(x1, y2, z1));
/*     */     
/* 306 */     drawLineWithGL(new Vec3(x1, y1, z1), new Vec3(x1, y2, z1));
/* 307 */     drawLineWithGL(new Vec3(x1, y1, z2), new Vec3(x1, y2, z2));
/* 308 */     drawLineWithGL(new Vec3(x2, y1, z1), new Vec3(x2, y2, z1));
/* 309 */     drawLineWithGL(new Vec3(x2, y1, z2), new Vec3(x2, y2, z2));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawLineWithGL(Vec3 blockA, Vec3 blockB) {
/* 314 */     GL11.glBegin(3);
/*     */     
/* 316 */     GL11.glVertex3d(blockA.field_72450_a, blockA.field_72448_b, blockA.field_72449_c);
/* 317 */     GL11.glVertex3d(blockB.field_72450_a, blockB.field_72448_b, blockB.field_72449_c);
/*     */     
/* 319 */     GL11.glEnd();
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\gui\GuiPlayerFocus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */