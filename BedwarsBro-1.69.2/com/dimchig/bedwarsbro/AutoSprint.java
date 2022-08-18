/*    */ package com.dimchig.bedwarsbro;
/*    */ 
/*    */ import com.dimchig.bedwarsbro.hints.HintsValidator;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.settings.KeyBinding;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.gameevent.InputEvent;
/*    */ 
/*    */ 
/*    */ public class AutoSprint
/*    */ {
/*    */   private Minecraft mc;
/*    */   private int sprintKeyBind;
/*    */   private boolean isAutoSprintActive = false;
/*    */   
/*    */   public AutoSprint() {
/* 17 */     this.mc = Minecraft.func_71410_x();
/* 18 */     updateBooleans();
/*    */   }
/*    */   
/*    */   public void updateBooleans() {
/* 22 */     this.isAutoSprintActive = HintsValidator.isAutoSprintActive();
/* 23 */     setSprint(this.isAutoSprintActive);
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onKeyInput(InputEvent.KeyInputEvent event) {
/* 28 */     if (this.isAutoSprintActive) {
/* 29 */       setSprint(true);
/*    */     }
/*    */   }
/*    */   
/*    */   public void setSprint(boolean state) {
/* 34 */     this.sprintKeyBind = (Minecraft.func_71410_x()).field_71474_y.field_151444_V.func_151463_i();
/* 35 */     KeyBinding.func_74510_a(this.sprintKeyBind, state);
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69.2.jar!\com\dimchig\bedwarsbro\AutoSprint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */