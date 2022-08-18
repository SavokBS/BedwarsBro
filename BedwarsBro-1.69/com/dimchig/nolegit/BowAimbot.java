/*     */ package com.dimchig.nolegit;
/*     */ 
/*     */ import com.dimchig.bedwarsbro.ChatSender;
/*     */ import com.dimchig.bedwarsbro.Main;
/*     */ import com.dimchig.bedwarsbro.MyChatListener;
/*     */ import com.dimchig.bedwarsbro.hints.HintsFinder;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EntitySelectors;
/*     */ import net.minecraft.util.MathHelper;
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
/*     */ public class BowAimbot
/*     */ {
/*     */   static Minecraft mc;
/*     */   public static boolean isActive;
/*     */   public static boolean isDrawActive;
/*     */   
/*     */   public BowAimbot() {
/*  51 */     mc = Minecraft.func_71410_x();
/*     */   }
/*     */ 
/*     */   
/*  55 */   public static ArrayList<Double> avg = new ArrayList<Double>();
/*  56 */   public static Vec3 last_movement_vector = new Vec3(0.0D, 0.0D, 0.0D);
/*  57 */   public static double last_predict_ticks = 0.0D;
/*  58 */   public static double prefict_delta = 0.05D;
/*  59 */   public static ArrayList<Vec3> temp_arr = new ArrayList<Vec3>();
/*  60 */   public static double gravity = 0.00625D;
/*     */   
/*  62 */   static Color normal_hitbox = new Color(1.0F, 1.0F, 1.0F, 1.0F);
/*  63 */   static Color predict_hitbox = new Color(1.0F, 0.0F, 0.0F, 1.0F);
/*  64 */   static Color predict_target = new Color(0.0F, 1.0F, 0.0F, 1.0F);
/*  65 */   static Color arrow_collider = new Color(0.0F, 1.0F, 0.0F, 1.0F);
/*     */   
/*     */   public void toggle() {
/*  68 */     isActive = !isActive;
/*  69 */     ChatSender.addText(MyChatListener.PREFIX_BEDWARSBRO + (isActive ? "&aВключен" : "&cВыключен") + " BowAimbot");
/*     */   }
/*     */   
/*     */   public void toggleDraw() {
/*  73 */     isDrawActive = !isDrawActive;
/*  74 */     ChatSender.addText(MyChatListener.PREFIX_BEDWARSBRO + (isDrawActive ? "&aВключен" : "&cВыключен") + " BowAimbot Visualizer");
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRenderWorldLastEvent(RenderWorldLastEvent event) {
/*  79 */     if (!isActive)
/*     */       return;  try {
/*  81 */       EntityPlayerSP player = mc.field_71439_g;
/*  82 */       float player_yaw = (player.field_70177_z % 360.0F + 360.0F) % 360.0F;
/*  83 */       float player_pitch = player.field_70125_A;
/*     */       
/*  85 */       Vec3 playerPos = getVectorWithPartialTicks((Entity)player, event.partialTicks);
/*     */       
/*  87 */       ArrayList<Vec3> positions = new ArrayList<Vec3>();
/*     */       
/*  89 */       if (player.func_71045_bC() == null || player.func_71045_bC().func_77973_b() != Items.field_151031_f) {
/*     */         return;
/*     */       }
/*  92 */       double timeLeft = player.func_71052_bv();
/*  93 */       double charge = Items.field_151031_f.func_77626_a(player.func_71045_bC()) - timeLeft;
/*     */       
/*  95 */       List<EntityPlayer> villagers = mc.field_71441_e.func_175644_a(EntityPlayer.class, EntitySelectors.field_94557_a);
/*     */       
/*  97 */       if (villagers == null || villagers.size() == 0)
/*  98 */         return;  EntityPlayer closestPlayer = getClosestPlayer(player, playerPos, event.partialTicks);
/*  99 */       if (closestPlayer == null)
/* 100 */         return;  Vec3 closestPlayerPos = getVectorWithPartialTicks((Entity)closestPlayer, event.partialTicks);
/*     */ 
/*     */       
/* 103 */       double dX = playerPos.field_72450_a - closestPlayerPos.field_72450_a;
/* 104 */       double dY = playerPos.field_72448_b - closestPlayerPos.field_72448_b;
/* 105 */       double dZ = playerPos.field_72449_c - closestPlayerPos.field_72449_c;
/* 106 */       double t_yaw = myMap(Math.atan2(dZ, dX), -3.141592653589793D, Math.PI, -180.0D, 180.0D);
/* 107 */       double t_pitch = myMap(Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI, Math.PI, 6.283185307179586D, 90.0D, -90.0D);
/*     */ 
/*     */       
/* 110 */       GL11.glPushMatrix();
/* 111 */       GL11.glPushAttrib(8192);
/* 112 */       GL11.glTranslated(-playerPos.field_72450_a, -playerPos.field_72448_b, -playerPos.field_72449_c);
/* 113 */       GL11.glDisable(2896);
/* 114 */       GL11.glDisable(3553);
/* 115 */       GL11.glDisable(2929);
/* 116 */       GlStateManager.func_179084_k();
/* 117 */       GL11.glLineWidth(1.0F);
/*     */ 
/*     */       
/* 120 */       if (isDrawActive) {
/* 121 */         List<EntityArrow> arrows = mc.field_71441_e.func_175644_a(EntityArrow.class, EntitySelectors.field_94557_a);
/* 122 */         if (arrows != null && arrows.size() > 0) {
/* 123 */           setLineColor(arrow_collider);
/* 124 */           for (EntityArrow en : arrows) {
/* 125 */             Vec3 pos = getVectorWithPartialTicks((Entity)en, event.partialTicks);
/* 126 */             double c = 0.1D;
/* 127 */             Main.playerFocus.drawBox(pos.field_72450_a - c, pos.field_72448_b - c, pos.field_72449_c - c, pos.field_72450_a + c, pos.field_72448_b + c, pos.field_72449_c + c);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 133 */       setLineColor(normal_hitbox);
/* 134 */       AxisAlignedBB box = closestPlayer.func_174813_aQ();
/* 135 */       drawBox(box.field_72340_a, box.field_72338_b, box.field_72339_c, box.field_72336_d, box.field_72337_e, box.field_72334_f);
/*     */       
/* 137 */       Vec3 target_pos = new Vec3(closestPlayerPos.field_72450_a, closestPlayerPos.field_72448_b + (closestPlayer.field_70131_O / 2.0F), closestPlayerPos.field_72449_c);
/*     */       
/* 139 */       ArrayList<MyHit> hits = new ArrayList<MyHit>();
/* 140 */       for (int i = -20; i <= 20; i++) {
/* 141 */         float new_pitch_delta = i / 1.0F;
/* 142 */         MyHit hit = getHitTrajectory(player, (World)mc.field_71441_e, t_yaw + 90.0D, t_pitch + new_pitch_delta, playerPos, target_pos);
/*     */         
/* 144 */         if (hit != null) {
/* 145 */           hits.add(hit);
/*     */         }
/*     */       } 
/* 148 */       if (hits.size() > 0) {
/* 149 */         MyHit hit = hits.get(hits.size() / 2);
/* 150 */         Vec3 pos = hit.pos;
/* 151 */         double c = 0.4D;
/* 152 */         double hit_length = hit.step_cnt * 0.1D;
/*     */ 
/*     */         
/* 155 */         double collider = 2.0D;
/* 156 */         BlockPos minPos = new BlockPos(hit.pos.field_72450_a - collider, hit.pos.field_72448_b - collider, hit.pos.field_72449_c - collider);
/* 157 */         BlockPos maxPos = new BlockPos(hit.pos.field_72450_a + collider, hit.pos.field_72448_b + collider, hit.pos.field_72449_c + collider);
/* 158 */         box = new AxisAlignedBB(minPos, maxPos);
/* 159 */         List<EntityPlayer> vs = mc.field_71441_e.func_72872_a(EntityPlayer.class, box);
/* 160 */         if (vs == null || vs.size() == 0)
/* 161 */           return;  EntityPlayer villager = vs.get(0);
/* 162 */         if (villager == null)
/*     */           return; 
/* 164 */         double predict_ticks_extender = 4.0D;
/*     */         
/* 166 */         double speedX = villager.field_70165_t - villager.field_70169_q;
/* 167 */         double speedY = villager.field_70163_u - villager.field_70167_r;
/* 168 */         double speedZ = villager.field_70161_v - villager.field_70166_s;
/* 169 */         double predict_ticks = hit_length + predict_ticks_extender;
/* 170 */         speedY = 0.0D;
/*     */         
/* 172 */         prefict_delta = 0.05D;
/*     */         
/* 174 */         last_predict_ticks += (predict_ticks - last_predict_ticks) * prefict_delta;
/* 175 */         predict_ticks = last_predict_ticks;
/*     */         
/* 177 */         if (charge < 20.0D) {
/* 178 */           predict_ticks += (20.0D - charge) * 0.1D;
/*     */         }
/*     */         
/* 181 */         double min_ticks = 5.0D;
/* 182 */         if (predict_ticks < min_ticks) predict_ticks = min_ticks;
/*     */ 
/*     */ 
/*     */         
/* 186 */         setLineColor(predict_hitbox);
/*     */         
/* 188 */         Vec3 predict_pos = new Vec3(closestPlayerPos.field_72450_a, closestPlayerPos.field_72448_b + (villager.field_70131_O / 2.0F), closestPlayerPos.field_72449_c);
/* 189 */         Vec3 movement_vector = new Vec3(speedX * predict_ticks, speedY * predict_ticks, speedZ * predict_ticks);
/* 190 */         double dfx = last_movement_vector.field_72450_a + (movement_vector.field_72450_a - last_movement_vector.field_72450_a) * prefict_delta;
/* 191 */         double dfy = last_movement_vector.field_72448_b + (movement_vector.field_72448_b - last_movement_vector.field_72448_b) * prefict_delta;
/* 192 */         double dfz = last_movement_vector.field_72449_c + (movement_vector.field_72449_c - last_movement_vector.field_72449_c) * prefict_delta;
/* 193 */         Vec3 vec_difference = new Vec3(dfx, dfy, dfz);
/* 194 */         predict_pos = predict_pos.func_178787_e(vec_difference);
/* 195 */         last_movement_vector = vec_difference;
/* 196 */         drawBox(predict_pos.field_72450_a - (villager.field_70130_N / 2.0F), predict_pos.field_72448_b - (villager.field_70131_O / 2.0F), predict_pos.field_72449_c - (villager.field_70130_N / 2.0F), predict_pos.field_72450_a + (villager.field_70130_N / 2.0F), predict_pos.field_72448_b + (villager.field_70131_O / 2.0F), predict_pos.field_72449_c + (villager.field_70130_N / 2.0F));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 202 */         dX = playerPos.field_72450_a - predict_pos.field_72450_a;
/* 203 */         dY = playerPos.field_72448_b - predict_pos.field_72448_b;
/* 204 */         dZ = playerPos.field_72449_c - predict_pos.field_72449_c;
/* 205 */         t_yaw = myMap(Math.atan2(dZ, dX), -3.1415927410125732D, 3.1415927410125732D, -180.0D, 180.0D);
/* 206 */         t_pitch = myMap(Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI, 3.1415927410125732D, 6.2831854820251465D, 90.0D, -90.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 211 */         hits = new ArrayList<MyHit>();
/* 212 */         for (int j = -20; j <= 20; j++) {
/* 213 */           float new_pitch_delta = j / 1.0F;
/* 214 */           hit = getHitTrajectory(player, (World)mc.field_71441_e, t_yaw + 90.0D, t_pitch + new_pitch_delta, playerPos, predict_pos);
/*     */           
/* 216 */           if (hit != null)
/* 217 */             hits.add(hit); 
/*     */         } 
/* 219 */         if (hits.size() > 0) {
/* 220 */           hit = hits.get(hits.size() / 2);
/* 221 */           pos = hit.pos;
/* 222 */           c = 0.1D;
/*     */           
/* 224 */           setLineColor(predict_target);
/* 225 */           drawBox(pos.field_72450_a - c, pos.field_72448_b - c, pos.field_72449_c - c, pos.field_72450_a + c, pos.field_72448_b + c, pos.field_72449_c + c);
/*     */           
/* 227 */           KeyBinding key = (Minecraft.func_71410_x()).field_71474_y.field_74313_G;
/* 228 */           if (charge <= 7000.0D)
/*     */           {
/*     */             
/* 231 */             HintsFinder.rotateTo((Entity)(Minecraft.func_71410_x()).field_71439_g, (float)hit.yaw, (float)hit.pitch);
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 240 */       GL11.glPopAttrib();
/* 241 */       GL11.glPopMatrix();
/*     */     } catch (Exception ex) {
/*     */       
/* 244 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private class MyHit {
/*     */     public Vec3 pos;
/*     */     public int step_cnt;
/*     */     public double yaw;
/*     */     public double pitch;
/*     */     
/*     */     public MyHit(Vec3 pos, int step_cnt, double player_yaw, double player_pitch) {
/* 255 */       this.pos = pos;
/* 256 */       this.step_cnt = step_cnt;
/* 257 */       this.yaw = player_yaw;
/* 258 */       this.pitch = player_pitch;
/*     */     }
/*     */   }
/*     */   
/*     */   private double getRealYaw(double yaw) {
/* 263 */     return (yaw % 360.0D + 360.0D) % 360.0D;
/*     */   }
/*     */   
/*     */   private EntityPlayer getClosestPlayer(EntityPlayerSP mod_player, Vec3 mod_player_pos, float partialTicks) {
/* 267 */     List<EntityPlayer> players = mc.field_71441_e.func_175644_a(EntityPlayer.class, EntitySelectors.field_94557_a);
/* 268 */     if (players == null || players.size() == 0) return null;
/*     */     
/* 270 */     double mod_player_yaw = getRealYaw(mod_player.field_70177_z);
/*     */     
/* 272 */     EntityPlayer closestPlayer = null;
/* 273 */     double dist = 1000.0D;
/* 274 */     for (EntityPlayer en : players) {
/* 275 */       if (en == mod_player) {
/*     */         continue;
/*     */       }
/*     */       
/* 279 */       Vec3 p_pos = getVectorWithPartialTicks((Entity)en, partialTicks);
/*     */ 
/*     */       
/* 282 */       double dX = mod_player_pos.field_72450_a - p_pos.field_72450_a;
/* 283 */       double dY = mod_player_pos.field_72448_b - p_pos.field_72448_b;
/* 284 */       double dZ = mod_player_pos.field_72449_c - p_pos.field_72449_c;
/* 285 */       double t_yaw = getRealYaw(myMap(Math.atan2(dZ, dX), -3.141592653589793D, Math.PI, -180.0D, 180.0D) + 90.0D);
/*     */       
/* 287 */       double d = Math.abs(mod_player_yaw - t_yaw);
/*     */       
/* 289 */       if (d < dist) {
/* 290 */         dist = d;
/* 291 */         closestPlayer = en;
/*     */       } 
/*     */     } 
/* 294 */     return closestPlayer;
/*     */   }
/*     */   
/*     */   private double myMap(double value, double leftMin, double leftMax, double rightMin, double rightMax) {
/* 298 */     double leftSpan = leftMax - leftMin;
/* 299 */     double rightSpan = rightMax - rightMin;
/* 300 */     double valueScaled = (value - leftMin) / leftSpan;
/* 301 */     return rightMin + valueScaled * rightSpan;
/*     */   }
/*     */   
/*     */   private Vec3 getVectorWithPartialTicks(Entity en, float partialTicks) {
/* 305 */     double x = en.field_70169_q + (en.field_70165_t - en.field_70169_q) * partialTicks;
/* 306 */     double y = en.field_70167_r + (en.field_70163_u - en.field_70167_r) * partialTicks;
/* 307 */     double z = en.field_70166_s + (en.field_70161_v - en.field_70166_s) * partialTicks;
/* 308 */     return new Vec3(x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   private MyHit getHitTrajectory(EntityPlayerSP player, World world, double player_yaw, double player_pitch, Vec3 player_pos, Vec3 target_pos) {
/* 313 */     double yaw = player_yaw;
/* 314 */     double pitch = player_pitch;
/* 315 */     double pitchWithOffset = player_pitch + 0.0D;
/*     */     
/* 317 */     double initialVelocity = 3.0D;
/* 318 */     double timeLeft = player.func_71052_bv();
/* 319 */     double charge = Items.field_151031_f.func_77626_a(player.func_71045_bC()) - timeLeft;
/* 320 */     double baseVelocity = Math.min(1.0F, HintsFinder.myMap((float)charge, 0.0F, 20.0F, 0.03F, 1.0F));
/* 321 */     initialVelocity = baseVelocity * 3.0D;
/*     */     
/* 323 */     double motionX = (-MathHelper.func_76126_a((float)(yaw * 0.01745329238474369D)) * MathHelper.func_76134_b((float)(pitch * 0.01745329238474369D)));
/* 324 */     double motionY = -MathHelper.func_76126_a((float)(pitchWithOffset * 0.01745329238474369D));
/* 325 */     double motionZ = (MathHelper.func_76134_b((float)(yaw * 0.01745329238474369D)) * MathHelper.func_76134_b((float)(pitch * 0.01745329238474369D)));
/* 326 */     float length = (float)Math.sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ);
/* 327 */     motionX /= length;
/* 328 */     motionY /= length;
/* 329 */     motionZ /= length;
/* 330 */     motionX *= initialVelocity;
/* 331 */     motionY *= initialVelocity;
/* 332 */     motionZ *= initialVelocity;
/*     */     
/* 334 */     if (player.field_70122_E) {
/* 335 */       motionY += (float)player.field_70181_x;
/*     */     }
/* 337 */     motionX += player.field_70159_w;
/* 338 */     motionZ += player.field_70179_y;
/* 339 */     double x = player_pos.field_72450_a;
/* 340 */     double y = player_pos.field_72448_b + player.func_70047_e() - 0.10000000149011612D;
/* 341 */     double z = player_pos.field_72449_c;
/*     */     
/* 343 */     BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(0, 0, 0);
/*     */     
/* 345 */     double gravity = 0.05000000074505806D;
/* 346 */     double drag = 0.99D;
/* 347 */     int MAX_ITERATIONS = 300;
/* 348 */     double percision = 0.1D;
/* 349 */     double collider = 1.0D;
/*     */     
/* 351 */     for (int i = 0; i < MAX_ITERATIONS; ) {
/* 352 */       pos.func_181079_c((int)x, (int)y, (int)z);
/* 353 */       Vec3 vec = new Vec3(x, y, z);
/*     */ 
/*     */       
/* 356 */       double lastX = x;
/* 357 */       double lastY = y;
/* 358 */       double lastZ = z;
/*     */       
/* 360 */       motionY -= gravity * percision;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 365 */       x += motionX * percision;
/* 366 */       y += motionY * percision;
/* 367 */       z += motionZ * percision;
/*     */       
/* 369 */       IBlockState iblockstate = world.func_180495_p((BlockPos)pos);
/*     */       
/* 371 */       if (iblockstate != null && iblockstate.func_177230_c() != Blocks.field_150350_a) {
/* 372 */         return null;
/*     */       }
/*     */       
/* 375 */       if (target_pos.func_72438_d(vec) > collider) {
/*     */         i++; continue;
/* 377 */       }  return new MyHit(vec, i, player_yaw, player_pitch);
/*     */     } 
/* 379 */     return null;
/*     */   }
/*     */   
/*     */   private void setLineColor(Color color) {
/* 383 */     if (!isDrawActive)
/* 384 */       return;  GL11.glColor4f(color.getRed(), color.getGreen(), color.getBlue(), 1.0F);
/*     */   }
/*     */   
/*     */   public void drawBox(double x1, double y1, double z1, double x2, double y2, double z2) {
/* 388 */     if (!isDrawActive)
/*     */       return; 
/* 390 */     drawLineWithGL(new Vec3(x1, y1, z1), new Vec3(x2, y1, z1));
/* 391 */     drawLineWithGL(new Vec3(x2, y1, z1), new Vec3(x2, y1, z2));
/* 392 */     drawLineWithGL(new Vec3(x2, y1, z2), new Vec3(x1, y1, z2));
/* 393 */     drawLineWithGL(new Vec3(x1, y1, z2), new Vec3(x1, y1, z1));
/* 394 */     drawLineWithGL(new Vec3(x1, y2, z1), new Vec3(x2, y2, z1));
/* 395 */     drawLineWithGL(new Vec3(x2, y2, z1), new Vec3(x2, y2, z2));
/* 396 */     drawLineWithGL(new Vec3(x2, y2, z2), new Vec3(x1, y2, z2));
/* 397 */     drawLineWithGL(new Vec3(x1, y2, z2), new Vec3(x1, y2, z1));
/*     */     
/* 399 */     drawLineWithGL(new Vec3(x1, y1, z1), new Vec3(x1, y2, z1));
/* 400 */     drawLineWithGL(new Vec3(x1, y1, z2), new Vec3(x1, y2, z2));
/* 401 */     drawLineWithGL(new Vec3(x2, y1, z1), new Vec3(x2, y2, z1));
/* 402 */     drawLineWithGL(new Vec3(x2, y1, z2), new Vec3(x2, y2, z2));
/*     */   }
/*     */   
/*     */   public static void drawLineWithGL(Vec3 blockA, Vec3 blockB) {
/* 406 */     GL11.glBegin(3);
/*     */     
/* 408 */     GL11.glVertex3d(blockA.field_72450_a, blockA.field_72448_b, blockA.field_72449_c);
/* 409 */     GL11.glVertex3d(blockB.field_72450_a, blockB.field_72448_b, blockB.field_72449_c);
/*     */     
/* 411 */     GL11.glEnd();
/*     */   }
/*     */ 
/*     */   
/*     */   private Vec3 calcVelocityForArrow(double yaw, double pitch) {
/* 416 */     double power = 1.0D;
/* 417 */     double actualPower = power * 3.0D;
/*     */     
/* 419 */     double xVel = -Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch));
/* 420 */     double yVel = -Math.sin(Math.toRadians(pitch));
/* 421 */     double zVel = Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch));
/*     */     
/* 423 */     return (new Vec3(xVel, yVel, zVel)).func_72432_b();
/*     */   }
/*     */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\nolegit\BowAimbot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */