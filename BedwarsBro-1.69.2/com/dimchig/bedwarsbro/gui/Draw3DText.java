/*    */ package com.dimchig.bedwarsbro.gui;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityPlayerSP;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.gui.Gui;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.util.Vec3;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ public class Draw3DText
/*    */   extends Gui
/*    */ {
/*    */   static Minecraft mc;
/*    */   
/*    */   public Draw3DText() {
/* 19 */     mc = Minecraft.func_71410_x();
/*    */   }
/*    */   
/*    */   public void drawText(Vec3 pos, Vec3 text_pos, EntityPlayerSP player, String text, int color) {
/* 23 */     GL11.glPushMatrix();
/*    */     
/* 25 */     GL11.glTranslated(-pos.field_72450_a + text_pos.field_72450_a, -pos.field_72448_b + text_pos.field_72448_b, -pos.field_72449_c + text_pos.field_72449_c);
/*    */ 
/*    */     
/* 28 */     GL11.glRotatef(-player.field_70177_z, 0.0F, 1.0F, 0.0F);
/* 29 */     GL11.glRotatef(player.field_70125_A, 1.0F, 0.0F, 0.0F);
/*    */     
/* 31 */     GL11.glScaled(-0.05D, -0.05D, 0.05D);
/*    */ 
/*    */ 
/*    */     
/* 35 */     GlStateManager.func_179084_k();
/* 36 */     GlStateManager.func_179097_i();
/* 37 */     FontRenderer fontRenderer = mc.field_71466_p;
/*    */ 
/*    */     
/* 40 */     fontRenderer.func_78276_b(text, -fontRenderer.func_78256_a(text) / 2, 0, color);
/*    */ 
/*    */     
/* 43 */     double d = 0.10000000149011612D;
/* 44 */     GL11.glTranslated(0.0D, 0.0D, d);
/* 45 */     int width = fontRenderer.func_78256_a(text);
/*    */     
/* 47 */     func_73734_a(-width / 2, -1, width / 2, 8, (new Color(0.0F, 0.0F, 0.0F, 0.2F)).getRGB());
/*    */     
/* 49 */     GlStateManager.func_179147_l();
/* 50 */     GlStateManager.func_179126_j();
/*    */     
/* 52 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\gui\Draw3DText.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */