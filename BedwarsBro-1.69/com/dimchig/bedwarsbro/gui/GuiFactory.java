/*    */ package com.dimchig.bedwarsbro.gui;
/*    */ 
/*    */ import java.util.Set;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraftforge.fml.client.IModGuiFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuiFactory
/*    */   implements IModGuiFactory
/*    */ {
/*    */   public void initialize(Minecraft minecraftInstance) {}
/*    */   
/*    */   public boolean hasConfigGui() {
/* 16 */     return true;
/*    */   }
/*    */   
/*    */   public GuiScreen createConfigGui(GuiScreen parentScreen) {
/* 20 */     return (GuiScreen)new ConfigGui(parentScreen);
/*    */   }
/*    */   
/*    */   public Class<? extends GuiScreen> mainConfigGuiClass() {
/* 24 */     return (Class)ConfigGui.class;
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<IModGuiFactory.RuntimeOptionCategoryElement> runtimeGuiCategories() {
/* 29 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public IModGuiFactory.RuntimeOptionGuiHandler getHandlerFor(IModGuiFactory.RuntimeOptionCategoryElement element) {
/* 34 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\gui\GuiFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */