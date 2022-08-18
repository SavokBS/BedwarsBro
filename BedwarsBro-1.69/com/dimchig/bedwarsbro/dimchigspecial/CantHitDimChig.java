/*    */ package com.dimchig.bedwarsbro.dimchigspecial;
/*    */ 
/*    */ import com.dimchig.bedwarsbro.Main;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityPlayerSP;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.entity.player.AttackEntityEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ 
/*    */ public class CantHitDimChig
/*    */ {
/*    */   static Minecraft mc;
/*    */   public static boolean isActive = false;
/*    */   
/*    */   public CantHitDimChig() {
/* 18 */     mc = Minecraft.func_71410_x();
/*    */   }
/*    */   
/*    */   public void handle() {
/* 22 */     if (!isActive)
/* 23 */       return;  EntityPlayerSP player = mc.field_71439_g;
/* 24 */     if (player == null)
/* 25 */       return;  if (mc.field_71476_x == null || mc.field_71476_x.field_72308_g == null)
/* 26 */       return;  if (!(mc.field_71476_x.field_72308_g instanceof net.minecraft.client.entity.EntityOtherPlayerMP))
/* 27 */       return;  if (mc.field_71476_x.field_72308_g.func_70005_c_() != null && !mc.field_71476_x.field_72308_g.func_70005_c_().equals("DimChig"))
/* 28 */       return;  if (player.field_71071_by == null)
/*    */       return; 
/* 30 */     if (!player.func_70075_an() || player.func_71045_bC() == null || !player.func_71045_bC().func_77977_a().contains("sword")) {
/* 31 */       int idx = Main.shopManager.findItemInHotbar("sword");
/* 32 */       if (idx == -1)
/* 33 */         return;  player.field_71071_by.func_70453_c(1);
/*    */     } 
/*    */     
/* 36 */     if (player.func_71045_bC() == null)
/*    */       return; 
/* 38 */     mc.field_71442_b.func_78769_a((EntityPlayer)player, (World)mc.field_71441_e, mc.field_71439_g.func_71045_bC());
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void AttackEntityEvent(AttackEntityEvent event) {}
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\dimchigspecial\CantHitDimChig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */