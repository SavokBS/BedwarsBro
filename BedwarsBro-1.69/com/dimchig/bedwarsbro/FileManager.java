/*    */ package com.dimchig.bedwarsbro;
/*    */ 
/*    */ import java.io.BufferedWriter;
/*    */ import java.io.File;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStreamWriter;
/*    */ import java.io.Writer;
/*    */ import java.nio.charset.StandardCharsets;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Paths;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FileManager
/*    */ {
/*    */   public static void initFile(String name) {
/*    */     try {
/* 25 */       File myObj = new File(name);
/* 26 */       myObj.createNewFile();
/* 27 */     } catch (IOException e) {
/* 28 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void clearFile(String filename) {
/* 33 */     writeToFile(filename, "", false);
/*    */   }
/*    */   
/*    */   public static void writeToFile(String str, String name, boolean append) {
/* 37 */     initFile(name);
/* 38 */     Writer out = null;
/*    */     try {
/* 40 */       out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(name, append), "UTF-8"));
/* 41 */       out.write((append ? "\n" : "") + str);
/* 42 */     } catch (IOException e) {
/* 43 */       e.printStackTrace();
/*    */     } finally {
/*    */       try {
/* 46 */         out.close();
/* 47 */       } catch (IOException e) {
/* 48 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public static String readFile(String filename) {
/* 54 */     initFile(filename);
/*    */     try {
/* 56 */       List<String> list = Files.readAllLines(Paths.get(filename, new String[0]), StandardCharsets.UTF_8);
/* 57 */       StringBuilder builder = new StringBuilder();
/* 58 */       for (String s : list) builder.append(s + "\n"); 
/* 59 */       return builder.toString();
/* 60 */     } catch (IOException e) {
/* 61 */       e.printStackTrace();
/*    */       
/* 63 */       return "";
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\1337a\Downloads\bedwarsbro-1.69\!\com\dimchig\bedwarsbro\FileManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */