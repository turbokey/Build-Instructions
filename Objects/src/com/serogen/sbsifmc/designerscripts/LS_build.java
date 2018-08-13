package com.serogen.sbsifmc.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_build{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
//BA.debugLineNum = 2;BA.debugLine="If 100%x > 100%y Then"[build/General script]
if (((100d / 100 * width)>(100d / 100 * height))) { 
;
//BA.debugLineNum = 3;BA.debugLine="menu_btn.Width = 50%x"[build/General script]
views.get("menu_btn").vw.setWidth((int)((50d / 100 * width)));
//BA.debugLineNum = 4;BA.debugLine="menu_btn.Height = 15%y"[build/General script]
views.get("menu_btn").vw.setHeight((int)((15d / 100 * height)));
//BA.debugLineNum = 5;BA.debugLine="menu_btn.Left = 25%x"[build/General script]
views.get("menu_btn").vw.setLeft((int)((25d / 100 * width)));
//BA.debugLineNum = 6;BA.debugLine="menu_btn.Top = 0"[build/General script]
views.get("menu_btn").vw.setTop((int)(0d));
//BA.debugLineNum = 7;BA.debugLine="ImageView1.Width=70%y"[build/General script]
views.get("imageview1").vw.setWidth((int)((70d / 100 * height)));
//BA.debugLineNum = 8;BA.debugLine="ImageView1.Height=85%y-90dip"[build/General script]
views.get("imageview1").vw.setHeight((int)((85d / 100 * height)-(90d * scale)));
//BA.debugLineNum = 9;BA.debugLine="ImageView1.Top=15%y"[build/General script]
views.get("imageview1").vw.setTop((int)((15d / 100 * height)));
//BA.debugLineNum = 10;BA.debugLine="ImageView1.left=100%x-70%y"[build/General script]
views.get("imageview1").vw.setLeft((int)((100d / 100 * width)-(70d / 100 * height)));
//BA.debugLineNum = 11;BA.debugLine="Label1.Left=0"[build/General script]
views.get("label1").vw.setLeft((int)(0d));
//BA.debugLineNum = 12;BA.debugLine="Label1.Top=15%y"[build/General script]
views.get("label1").vw.setTop((int)((15d / 100 * height)));
//BA.debugLineNum = 13;BA.debugLine="Label1.Width=100%x-70%y"[build/General script]
views.get("label1").vw.setWidth((int)((100d / 100 * width)-(70d / 100 * height)));
//BA.debugLineNum = 14;BA.debugLine="Label1.Height=85%y-90dip"[build/General script]
views.get("label1").vw.setHeight((int)((85d / 100 * height)-(90d * scale)));
//BA.debugLineNum = 15;BA.debugLine="Else"[build/General script]
;}else{ 
;
//BA.debugLineNum = 16;BA.debugLine="menu_btn.Width = 50%x"[build/General script]
views.get("menu_btn").vw.setWidth((int)((50d / 100 * width)));
//BA.debugLineNum = 17;BA.debugLine="menu_btn.Height = 10%y"[build/General script]
views.get("menu_btn").vw.setHeight((int)((10d / 100 * height)));
//BA.debugLineNum = 18;BA.debugLine="menu_btn.Left = 25%x"[build/General script]
views.get("menu_btn").vw.setLeft((int)((25d / 100 * width)));
//BA.debugLineNum = 19;BA.debugLine="menu_btn.Top = 0"[build/General script]
views.get("menu_btn").vw.setTop((int)(0d));
//BA.debugLineNum = 20;BA.debugLine="ImageView1.Width=50%y"[build/General script]
views.get("imageview1").vw.setWidth((int)((50d / 100 * height)));
//BA.debugLineNum = 21;BA.debugLine="ImageView1.Height=50%y"[build/General script]
views.get("imageview1").vw.setHeight((int)((50d / 100 * height)));
//BA.debugLineNum = 22;BA.debugLine="ImageView1.Top=10%y"[build/General script]
views.get("imageview1").vw.setTop((int)((10d / 100 * height)));
//BA.debugLineNum = 23;BA.debugLine="ImageView1.left=(100%x-50%y)/2"[build/General script]
views.get("imageview1").vw.setLeft((int)(((100d / 100 * width)-(50d / 100 * height))/2d));
//BA.debugLineNum = 24;BA.debugLine="Label1.Left=0"[build/General script]
views.get("label1").vw.setLeft((int)(0d));
//BA.debugLineNum = 25;BA.debugLine="Label1.Top=60%y"[build/General script]
views.get("label1").vw.setTop((int)((60d / 100 * height)));
//BA.debugLineNum = 26;BA.debugLine="Label1.Width=100%x"[build/General script]
views.get("label1").vw.setWidth((int)((100d / 100 * width)));
//BA.debugLineNum = 27;BA.debugLine="Label1.Height=40%y-90dip"[build/General script]
views.get("label1").vw.setHeight((int)((40d / 100 * height)-(90d * scale)));
//BA.debugLineNum = 28;BA.debugLine="End If"[build/General script]
;};

}
}