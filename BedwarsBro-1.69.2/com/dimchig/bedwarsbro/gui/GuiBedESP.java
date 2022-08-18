/*    */ package com.dimchig.bedwarsbro.gui;
/*    */ 
/*    */ import com.dimchig.bedwarsbro.Main;
/*    */ import com.dimchig.bedwarsbro.hints.HintsValidator;
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityPlayerSP;
/*    */ import net.minecraft.client.multiplayer.WorldClient;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraftforge.client.event.RenderWorldLastEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuiBedESP
/*    */ {
/*    */   static Minecraft mc;
/*    */   public static boolean STATE = false;
/*    */   
/*    */   public GuiBedESP() {
/* 27 */     mc = Minecraft.func_71410_x();
/* 28 */     updateBooleans();
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateBooleans() {
/* 33 */     this; STATE = HintsValidator.isBedESPActive();
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onRenderWorldLastEvent(RenderWorldLastEvent event) {
/* 38 */     if (!STATE)
/*    */       return; 
/* 40 */     ArrayList<GuiMinimap.MyBed> beds = GuiMinimap.bedsFound;
/* 41 */     if (beds == null || beds.size() == 0)
/*    */       return; 
/* 43 */     float partialTicks = event.partialTicks;
/*    */     
/* 45 */     EntityPlayerSP player = mc.field_71439_g;
/* 46 */     WorldClient worldClient = mc.field_71441_e;
/* 47 */     if (player == null || worldClient == null) {
/*    */       return;
/*    */     }
/* 50 */     double d0 = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * partialTicks;
/* 51 */     double d1 = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * partialTicks;
/* 52 */     double d2 = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * partialTicks;
/* 53 */     Vec3 pos = new Vec3(d0, d1, d2);
/*    */     
/* 55 */     GL11.glPushMatrix();
/* 56 */     GL11.glPushAttrib(8192);
/*    */     
/* 58 */     GL11.glTranslated(-pos.field_72450_a, -pos.field_72448_b, -pos.field_72449_c);
/* 59 */     GL11.glDisable(2896);
/* 60 */     GL11.glDisable(3553);
/* 61 */     GL11.glDisable(2929);
/* 62 */     GlStateManager.func_179084_k();
/* 63 */     GL11.glLineWidth(1.0F);
/*    */ 
/*    */     
/* 66 */     if (beds != null && beds.size() > 0) {
/* 67 */       for (GuiMinimap.MyBed b : beds) {
/* 68 */         double x = b.pos.func_177958_n();
/* 69 */         double y = b.pos.func_177956_o();
/* 70 */         double z = b.pos.func_177952_p();
/* 71 */         double x2 = b.pos_feet.func_177958_n();
/* 72 */         double y2 = b.pos_feet.func_177956_o();
/* 73 */         double z2 = b.pos_feet.func_177952_p();
/*    */         
/* 75 */         GL11.glColor4f(1.0F, 0.0F, 0.0F, 1.0F);
/* 76 */         if (b.isPlayersBed) GL11.glColor4f(0.0F, 1.0F, 0.0F, 1.0F);
/*    */         
/* 78 */         Main.playerFocus.drawBox(x, y, z, x2 + 1.0D, y2 + 0.56D, z2 + 1.0D);
/*    */         
/* 80 */         ArrayList<BlockPos> obby = b.obsidianPoses;
/* 81 */         if (obby.size() > 0 && mc.field_71439_g.func_70011_f(x, y, z) > 10.0D) {
/* 82 */           GL11.glColor4f(0.0F, 1.0F, 1.0F, 1.0F);
/* 83 */           for (BlockPos bp : obby) {
/* 84 */             double px = bp.func_177958_n();
/* 85 */             double py = bp.func_177956_o();
/* 86 */             double pz = bp.func_177952_p();
/* 87 */             double c = 0.02D;
/* 88 */             Main.playerFocus.drawBox(px + c, py + c, pz + c, px + 1.0D - c, py + 1.0D - c, pz + 1.0D - c);
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     }
/*    */     
/* 94 */     GL11.glPopAttrib();
/* 95 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\gui\GuiBedESP.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */