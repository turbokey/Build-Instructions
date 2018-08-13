package com.serogen.sbsifmc;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class animatedslidingmenu extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "com.serogen.sbsifmc.animatedslidingmenu");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            if (BA.isShellModeRuntimeCheck(ba)) {
			    ba.raiseEvent2(null, true, "CREATE", true, "com.serogen.sbsifmc.animatedslidingmenu",
                    ba);
                return;
		    }
        }
        ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.phone.Phone _p = null;
public anywheresoftware.b4a.objects.Timer _tweenupdate = null;
public com.rootsoft.tweenengine.RSTween[] _mytween = null;
public com.rootsoft.tweenengine.RSTweenManager _manager = null;
public com.rootsoft.tweenengine.RSTweenConstants _constants = null;
public com.rootsoft.tweenengine.RSTweenCallback _callback = null;
public anywheresoftware.b4a.objects.PanelWrapper _fakepanel = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _menupanel = null;
public anywheresoftware.b4a.objects.LabelWrapper _menutitle = null;
public anywheresoftware.b4a.objects.ListViewWrapper[] _menuitem = null;
public Object _mmodule = null;
public String _meventname = "";
public String _mlocation = "";
public int _mwidth = 0;
public int _mtop = 0;
public int _itemsinmenu = 0;
public int _titlepad1 = 0;
public int _titlepad2 = 0;
public boolean _isopen = false;
public com.serogen.sbsifmc.main _main = null;
public com.serogen.sbsifmc.starter _starter = null;
public String  _additem(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _icon,String _text,int _textcolor,int _backgroundcolor,int _index) throws Exception{
 //BA.debugLineNum = 123;BA.debugLine="Public Sub AddItem(Icon As Bitmap, Text As String,";
 //BA.debugLineNum = 125;BA.debugLine="MenuItem(Index).Initialize(\"MenuItem\")";
_menuitem[_index].Initialize(ba,"MenuItem");
 //BA.debugLineNum = 127;BA.debugLine="MenuItem(Index).ScrollingBackgroundColor =";
_menuitem[_index].setScrollingBackgroundColor(__c.Colors.Transparent);
 //BA.debugLineNum = 128;BA.debugLine="MenuItem(Index).TwoLinesAndBitmap.SecondLab";
_menuitem[_index].getTwoLinesAndBitmap().SecondLabel.setVisible(__c.False);
 //BA.debugLineNum = 129;BA.debugLine="MenuItem(Index).TwoLinesAndBitmap.ItemHeigh";
_menuitem[_index].getTwoLinesAndBitmap().setItemHeight(__c.DipToCurrent((int) (50)));
 //BA.debugLineNum = 130;BA.debugLine="MenuItem(Index).TwoLinesAndBitmap.Label.Tex";
_menuitem[_index].getTwoLinesAndBitmap().Label.setTextColor(_textcolor);
 //BA.debugLineNum = 131;BA.debugLine="MenuItem(Index).TwoLinesAndBitmap.Label.Gra";
_menuitem[_index].getTwoLinesAndBitmap().Label.setGravity(__c.Gravity.CENTER_VERTICAL);
 //BA.debugLineNum = 132;BA.debugLine="MenuItem(Index).TwoLinesAndBitmap.Label.Hei";
_menuitem[_index].getTwoLinesAndBitmap().Label.setHeight(_menuitem[_index].getTwoLinesAndBitmap().getItemHeight());
 //BA.debugLineNum = 133;BA.debugLine="MenuItem(Index).TwoLinesAndBitmap.Label.Top";
_menuitem[_index].getTwoLinesAndBitmap().Label.setTop(__c.DipToCurrent((int) (0)));
 //BA.debugLineNum = 135;BA.debugLine="If Icon.IsInitialized Then";
if (_icon.IsInitialized()) { 
 //BA.debugLineNum = 137;BA.debugLine="MenuItem(Index).TwoLinesAndBitmap.Label.";
_menuitem[_index].getTwoLinesAndBitmap().Label.setLeft(__c.DipToCurrent((int) (45)));
 //BA.debugLineNum = 138;BA.debugLine="MenuItem(Index).AddTwoLinesAndBitmap(Tex";
_menuitem[_index].AddTwoLinesAndBitmap(_text,"",(android.graphics.Bitmap)(_icon.getObject()));
 }else {
 //BA.debugLineNum = 142;BA.debugLine="MenuItem(Index).TwoLinesAndBitmap.Label.";
_menuitem[_index].getTwoLinesAndBitmap().Label.setLeft(__c.DipToCurrent((int) (10)));
 //BA.debugLineNum = 143;BA.debugLine="MenuItem(Index).AddTwoLinesAndBitmap(Tex";
_menuitem[_index].AddTwoLinesAndBitmap(_text,"",(android.graphics.Bitmap)(__c.Null));
 };
 //BA.debugLineNum = 147;BA.debugLine="MenuItem(Index).TwoLinesAndBitmap.ImageView";
_menuitem[_index].getTwoLinesAndBitmap().ImageView.SetLayout(__c.DipToCurrent((int) (12)),__c.DipToCurrent((int) (12)),__c.DipToCurrent((int) (25)),__c.DipToCurrent((int) (25)));
 //BA.debugLineNum = 148;BA.debugLine="MenuItem(Index).Color = BackgroundColor";
_menuitem[_index].setColor(_backgroundcolor);
 //BA.debugLineNum = 149;BA.debugLine="MenuItem(Index).Tag = Index";
_menuitem[_index].setTag((Object)(_index));
 //BA.debugLineNum = 151;BA.debugLine="MenuPanel.Panel.AddView(MenuItem(Index), 0d";
_menupanel.getPanel().AddView((android.view.View)(_menuitem[_index].getObject()),__c.DipToCurrent((int) (0)),(int) ((_index*__c.DipToCurrent((int) (51)))-_titlepad2),__c.PerXToCurrent((float) (100),ba),__c.DipToCurrent((int) (51)));
 //BA.debugLineNum = 152;BA.debugLine="MenuPanel.Panel.Height = (Index *	51dip) +";
_menupanel.getPanel().setHeight((int) ((_index*__c.DipToCurrent((int) (51)))+_titlepad1));
 //BA.debugLineNum = 154;BA.debugLine="ItemsInMenu = ItemsInMenu + 1";
_itemsinmenu = (int) (_itemsinmenu+1);
 //BA.debugLineNum = 156;BA.debugLine="End Sub";
return "";
}
public String  _animatetween(String _viewtype,int _id,Object _viewname,double _tweenconstant,int _xpos,int _ypos,Object _easing,float _duration,float _startdelay) throws Exception{
 //BA.debugLineNum = 531;BA.debugLine="Private Sub AnimateTween(ViewType As String, ID As";
 //BA.debugLineNum = 533;BA.debugLine="MyTween(ID).Initialize(\"Tween\")";
_mytween[_id].Initialize(ba,"Tween");
 //BA.debugLineNum = 535;BA.debugLine="Duration = Duration * 10 'It was said that";
_duration = (float) (_duration*10);
 //BA.debugLineNum = 536;BA.debugLine="StartDelay = StartDelay * 10";
_startdelay = (float) (_startdelay*10);
 //BA.debugLineNum = 538;BA.debugLine="Select Case ViewType";
switch (BA.switchObjectToInt(_viewtype,"Panel","HorizontalScrollView","ListView","ScrollView","Spinner","TabHost","WebView","ImageView","Label","Button","AutoCompleteEditText","CheckBox","EditText","RadioButton","ToggleButton")) {
case 0:
case 1:
case 2:
case 3:
case 4:
case 5:
case 6:
 //BA.debugLineNum = 542;BA.debugLine="MyTween(ID).registerPanel";
_mytween[_id].registerPanelAccessor();
 //BA.debugLineNum = 543;BA.debugLine="MyTween(ID).PanelTo(ViewN";
_mytween[_id].PanelTo((anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_viewname)),(int) (_tweenconstant),_duration);
 break;
case 7:
 //BA.debugLineNum = 547;BA.debugLine="MyTween(ID).registerImage";
_mytween[_id].registerImageAccessor();
 //BA.debugLineNum = 548;BA.debugLine="MyTween(ID).ImageTo(ViewN";
_mytween[_id].ImageTo((anywheresoftware.b4a.objects.ImageViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ImageViewWrapper(), (android.widget.ImageView)(_viewname)),(int) (_tweenconstant),_duration);
 break;
case 8:
case 9:
case 10:
case 11:
case 12:
case 13:
case 14:
 //BA.debugLineNum = 552;BA.debugLine="MyTween(ID).registerLabel";
_mytween[_id].registerLabelAccessor();
 //BA.debugLineNum = 553;BA.debugLine="MyTween(ID).LabelTo(ViewN";
_mytween[_id].LabelTo((anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_viewname)),(int) (_tweenconstant),_duration);
 break;
}
;
 //BA.debugLineNum = 557;BA.debugLine="MyTween(ID).setTarget(xPos, yPos)";
_mytween[_id].setTarget((float) (_xpos),(float) (_ypos));
 //BA.debugLineNum = 558;BA.debugLine="MyTween(ID).Ease(Easing)";
_mytween[_id].Ease((aurelienribon.tweenengine.TweenEquation)(_easing));
 //BA.debugLineNum = 559;BA.debugLine="MyTween(ID).Delay = StartDelay";
_mytween[_id].setDelay(_startdelay);
 //BA.debugLineNum = 561;BA.debugLine="Manager.addTween(MyTween(ID).Tween)";
_manager.addTween(_mytween[_id].getTween());
 //BA.debugLineNum = 563;BA.debugLine="End Sub";
return "";
}
public String  _animcascade(int _xpos,int _ypos) throws Exception{
int[] _buffer = null;
int _i = 0;
 //BA.debugLineNum = 430;BA.debugLine="Private Sub AnimCascade(xPos As Int, yPos As Int)";
 //BA.debugLineNum = 432;BA.debugLine="Dim Buffer(51) As Int";
_buffer = new int[(int) (51)];
;
 //BA.debugLineNum = 434;BA.debugLine="For I = 1 To ItemsInMenu";
{
final int step222 = 1;
final int limit222 = _itemsinmenu;
for (_i = (int) (1); (step222 > 0 && _i <= limit222) || (step222 < 0 && _i >= limit222); _i = ((int)(0 + _i + step222))) {
 //BA.debugLineNum = 436;BA.debugLine="Buffer(I) = MenuItem(I).Top";
_buffer[_i] = _menuitem[_i].getTop();
 //BA.debugLineNum = 437;BA.debugLine="MenuItem(I).Top = -MenuPanel.Height";
_menuitem[_i].setTop((int) (-_menupanel.getHeight()));
 }
};
 //BA.debugLineNum = 441;BA.debugLine="AnimateTween(\"Panel\", 2, MenuPanel, Consta";
_animatetween("Panel",(int) (2),(Object)(_menupanel.getObject()),_constants.POSITION_XY,_xpos,_ypos,(Object)(_constants.BACK_INOUT),(float) (2),(float) (0));
 //BA.debugLineNum = 443;BA.debugLine="For I = ItemsInMenu To 1 Step -1";
{
final int step227 = (int) (-1);
final int limit227 = (int) (1);
for (_i = _itemsinmenu; (step227 > 0 && _i <= limit227) || (step227 < 0 && _i >= limit227); _i = ((int)(0 + _i + step227))) {
 //BA.debugLineNum = 445;BA.debugLine="AnimateTween(\"ListView\", 1, MenuItem(I";
_animatetween("ListView",(int) (1),(Object)(_menuitem[_i].getObject()),_constants.POSITION_XY,(int) (0),_buffer[_i],(Object)(_constants.QUAD_OUT),(float) (3),(float) (1/(double)_i));
 }
};
 //BA.debugLineNum = 449;BA.debugLine="End Sub";
return "";
}
public String  _animcasino(int _xpos,int _ypos) throws Exception{
int _i = 0;
 //BA.debugLineNum = 373;BA.debugLine="Private Sub AnimCasino(xPos As Int, yPos As Int)";
 //BA.debugLineNum = 375;BA.debugLine="For I = 1 To ItemsInMenu";
{
final int step192 = 1;
final int limit192 = _itemsinmenu;
for (_i = (int) (1); (step192 > 0 && _i <= limit192) || (step192 < 0 && _i >= limit192); _i = ((int)(0 + _i + step192))) {
 //BA.debugLineNum = 377;BA.debugLine="MenuItem(I).Left = -MenuItem(I).Width";
_menuitem[_i].setLeft((int) (-_menuitem[_i].getWidth()));
 }
};
 //BA.debugLineNum = 381;BA.debugLine="AnimateTween(\"Panel\", 2, MenuPanel, Consta";
_animatetween("Panel",(int) (2),(Object)(_menupanel.getObject()),_constants.POSITION_XY,_xpos,_ypos,(Object)(_constants.BACK_INOUT),(float) (2),(float) (0));
 //BA.debugLineNum = 383;BA.debugLine="For I = 1 To ItemsInMenu";
{
final int step196 = 1;
final int limit196 = _itemsinmenu;
for (_i = (int) (1); (step196 > 0 && _i <= limit196) || (step196 < 0 && _i >= limit196); _i = ((int)(0 + _i + step196))) {
 //BA.debugLineNum = 385;BA.debugLine="AnimateTween(\"ListView\", 1, MenuItem(I";
_animatetween("ListView",(int) (1),(Object)(_menuitem[_i].getObject()),_constants.POSITION_XY,(int) (0),_menuitem[_i].getTop(),(Object)(_constants.BACK_INOUT),(float) (2),(float) (_i/(double)5));
 }
};
 //BA.debugLineNum = 389;BA.debugLine="End Sub";
return "";
}
public String  _animcasinoreverse(int _xpos,int _ypos) throws Exception{
int _i = 0;
 //BA.debugLineNum = 391;BA.debugLine="Private Sub AnimCasinoReverse(xPos As Int, yPos As";
 //BA.debugLineNum = 393;BA.debugLine="For I = 1 To ItemsInMenu";
{
final int step201 = 1;
final int limit201 = _itemsinmenu;
for (_i = (int) (1); (step201 > 0 && _i <= limit201) || (step201 < 0 && _i >= limit201); _i = ((int)(0 + _i + step201))) {
 //BA.debugLineNum = 395;BA.debugLine="MenuItem(I).Left = MenuPanel.Width";
_menuitem[_i].setLeft(_menupanel.getWidth());
 }
};
 //BA.debugLineNum = 399;BA.debugLine="AnimateTween(\"Panel\", 2, MenuPanel, Consta";
_animatetween("Panel",(int) (2),(Object)(_menupanel.getObject()),_constants.POSITION_XY,_xpos,_ypos,(Object)(_constants.BACK_INOUT),(float) (2),(float) (0));
 //BA.debugLineNum = 401;BA.debugLine="For I = 1 To ItemsInMenu";
{
final int step205 = 1;
final int limit205 = _itemsinmenu;
for (_i = (int) (1); (step205 > 0 && _i <= limit205) || (step205 < 0 && _i >= limit205); _i = ((int)(0 + _i + step205))) {
 //BA.debugLineNum = 403;BA.debugLine="AnimateTween(\"ListView\", 1, MenuItem(I";
_animatetween("ListView",(int) (1),(Object)(_menuitem[_i].getObject()),_constants.POSITION_XY,(int) (0),_menuitem[_i].getTop(),(Object)(_constants.BACK_INOUT),(float) (2),(float) (_i/(double)5));
 }
};
 //BA.debugLineNum = 407;BA.debugLine="End Sub";
return "";
}
public String  _animdefault(int _xpos,int _ypos) throws Exception{
 //BA.debugLineNum = 367;BA.debugLine="Private Sub AnimDefault(xPos As Int, yPos As Int)";
 //BA.debugLineNum = 369;BA.debugLine="AnimateTween(\"Panel\", 1, MenuPanel, Consta";
_animatetween("Panel",(int) (1),(Object)(_menupanel.getObject()),_constants.POSITION_XY,_xpos,_ypos,(Object)(_constants.BACK_INOUT),(float) (2),(float) (0));
 //BA.debugLineNum = 371;BA.debugLine="End Sub";
return "";
}
public String  _animfade(int _xpos,int _ypos) throws Exception{
int _i = 0;
 //BA.debugLineNum = 477;BA.debugLine="Private Sub AnimFade(xPos As Int, yPos As Int)";
 //BA.debugLineNum = 479;BA.debugLine="For I = 1 To ItemsInMenu";
{
final int step245 = 1;
final int limit245 = _itemsinmenu;
for (_i = (int) (1); (step245 > 0 && _i <= limit245) || (step245 < 0 && _i >= limit245); _i = ((int)(0 + _i + step245))) {
 //BA.debugLineNum = 481;BA.debugLine="FadeTween(\"ListView\", 1, MenuItem(I),";
_fadetween("ListView",(int) (1),(Object)(_menuitem[_i].getObject()),(int) (0),(float) (0),(float) (0));
 }
};
 //BA.debugLineNum = 485;BA.debugLine="AnimateTween(\"Panel\", 2, MenuPanel, Consta";
_animatetween("Panel",(int) (2),(Object)(_menupanel.getObject()),_constants.POSITION_XY,_xpos,_ypos,(Object)(_constants.BACK_INOUT),(float) (2),(float) (0));
 //BA.debugLineNum = 487;BA.debugLine="For I = 1 To ItemsInMenu";
{
final int step249 = 1;
final int limit249 = _itemsinmenu;
for (_i = (int) (1); (step249 > 0 && _i <= limit249) || (step249 < 0 && _i >= limit249); _i = ((int)(0 + _i + step249))) {
 //BA.debugLineNum = 489;BA.debugLine="FadeTween(\"ListView\", 1, MenuItem(I),";
_fadetween("ListView",(int) (1),(Object)(_menuitem[_i].getObject()),(int) (1),(float) (2),(float) (_i/(double)5));
 }
};
 //BA.debugLineNum = 493;BA.debugLine="End Sub";
return "";
}
public String  _animflip(int _xpos,int _ypos,String _location) throws Exception{
 //BA.debugLineNum = 451;BA.debugLine="Private Sub AnimFlip(xPos As Int, yPos As Int, Loc";
 //BA.debugLineNum = 453;BA.debugLine="If Location = \"T\" OR Location = \"B\" Then";
if ((_location).equals("T") || (_location).equals("B")) { 
 //BA.debugLineNum = 455;BA.debugLine="RotateTween(\"Panel\", 1, MenuPanel, Cons";
_rotatetween("Panel",(int) (1),(Object)(_menupanel.getObject()),_constants.ROT_X,(int) (180),(float) (0),(int) (0),(float) (0),(float) (0));
 }else {
 //BA.debugLineNum = 459;BA.debugLine="RotateTween(\"Panel\", 1, MenuPanel, Cons";
_rotatetween("Panel",(int) (1),(Object)(_menupanel.getObject()),_constants.ROT_Y,(int) (180),(float) (0),(int) (0),(float) (0),(float) (0));
 };
 //BA.debugLineNum = 463;BA.debugLine="AnimateTween(\"Panel\", 1, MenuPanel, Consta";
_animatetween("Panel",(int) (1),(Object)(_menupanel.getObject()),_constants.POSITION_XY,_xpos,_ypos,(Object)(_constants.BACK_INOUT),(float) (2),(float) (1));
 //BA.debugLineNum = 465;BA.debugLine="If Location = \"T\" OR Location = \"B\" Then";
if ((_location).equals("T") || (_location).equals("B")) { 
 //BA.debugLineNum = 467;BA.debugLine="RotateTween(\"Panel\", 1, MenuPanel, Cons";
_rotatetween("Panel",(int) (1),(Object)(_menupanel.getObject()),_constants.ROT_X,(int) (0),(float) (.5),(int) (0),(float) (0),(float) (3));
 }else {
 //BA.debugLineNum = 471;BA.debugLine="RotateTween(\"Panel\", 1, MenuPanel, Cons";
_rotatetween("Panel",(int) (1),(Object)(_menupanel.getObject()),_constants.ROT_Y,(int) (0),(float) (.5),(int) (0),(float) (0),(float) (3));
 };
 //BA.debugLineNum = 475;BA.debugLine="End Sub";
return "";
}
public String  _animshootup(int _xpos,int _ypos) throws Exception{
int[] _buffer = null;
int _i = 0;
 //BA.debugLineNum = 409;BA.debugLine="Private Sub AnimShootUp(xPos As Int, yPos As Int)";
 //BA.debugLineNum = 411;BA.debugLine="Dim Buffer(51) As Int";
_buffer = new int[(int) (51)];
;
 //BA.debugLineNum = 413;BA.debugLine="For I = 1 To ItemsInMenu";
{
final int step211 = 1;
final int limit211 = _itemsinmenu;
for (_i = (int) (1); (step211 > 0 && _i <= limit211) || (step211 < 0 && _i >= limit211); _i = ((int)(0 + _i + step211))) {
 //BA.debugLineNum = 415;BA.debugLine="Buffer(I) = MenuItem(I).Top";
_buffer[_i] = _menuitem[_i].getTop();
 //BA.debugLineNum = 416;BA.debugLine="MenuItem(I).Top = MenuPanel.Height + 5";
_menuitem[_i].setTop((int) (_menupanel.getHeight()+__c.DipToCurrent((int) (50))));
 }
};
 //BA.debugLineNum = 420;BA.debugLine="AnimateTween(\"Panel\", 2, MenuPanel, Consta";
_animatetween("Panel",(int) (2),(Object)(_menupanel.getObject()),_constants.POSITION_XY,_xpos,_ypos,(Object)(_constants.BACK_INOUT),(float) (2),(float) (0));
 //BA.debugLineNum = 422;BA.debugLine="For I = 1 To ItemsInMenu";
{
final int step216 = 1;
final int limit216 = _itemsinmenu;
for (_i = (int) (1); (step216 > 0 && _i <= limit216) || (step216 < 0 && _i >= limit216); _i = ((int)(0 + _i + step216))) {
 //BA.debugLineNum = 424;BA.debugLine="AnimateTween(\"ListView\", 1, MenuItem(I";
_animatetween("ListView",(int) (1),(Object)(_menuitem[_i].getObject()),_constants.POSITION_XY,(int) (0),_buffer[_i],(Object)(_constants.QUAD_OUT),(float) (2),(float) (_i/(double)5));
 }
};
 //BA.debugLineNum = 428;BA.debugLine="End Sub";
return "";
}
public String  _animzigzag(int _xpos,int _ypos) throws Exception{
boolean _flag = false;
int _i = 0;
 //BA.debugLineNum = 495;BA.debugLine="Private Sub AnimZigZag(xPos As Int, yPos As Int)";
 //BA.debugLineNum = 497;BA.debugLine="Dim Flag As Boolean";
_flag = false;
 //BA.debugLineNum = 499;BA.debugLine="Flag = True";
_flag = __c.True;
 //BA.debugLineNum = 501;BA.debugLine="For I = 1 To ItemsInMenu";
{
final int step256 = 1;
final int limit256 = _itemsinmenu;
for (_i = (int) (1); (step256 > 0 && _i <= limit256) || (step256 < 0 && _i >= limit256); _i = ((int)(0 + _i + step256))) {
 //BA.debugLineNum = 503;BA.debugLine="If Flag Then";
if (_flag) { 
 //BA.debugLineNum = 505;BA.debugLine="MenuItem(I).Left = -MenuItem(I).Wid";
_menuitem[_i].setLeft((int) (-_menuitem[_i].getWidth()));
 //BA.debugLineNum = 506;BA.debugLine="Flag = False";
_flag = __c.False;
 }else {
 //BA.debugLineNum = 510;BA.debugLine="MenuItem(I).Left = MenuPanel.Width";
_menuitem[_i].setLeft(_menupanel.getWidth());
 //BA.debugLineNum = 511;BA.debugLine="Flag = True";
_flag = __c.True;
 };
 }
};
 //BA.debugLineNum = 517;BA.debugLine="AnimateTween(\"Panel\", 2, MenuPanel, Consta";
_animatetween("Panel",(int) (2),(Object)(_menupanel.getObject()),_constants.POSITION_XY,_xpos,_ypos,(Object)(_constants.BACK_INOUT),(float) (2),(float) (0));
 //BA.debugLineNum = 519;BA.debugLine="For I = 1 To ItemsInMenu";
{
final int step266 = 1;
final int limit266 = _itemsinmenu;
for (_i = (int) (1); (step266 > 0 && _i <= limit266) || (step266 < 0 && _i >= limit266); _i = ((int)(0 + _i + step266))) {
 //BA.debugLineNum = 521;BA.debugLine="AnimateTween(\"ListView\", 1, MenuItem(I";
_animatetween("ListView",(int) (1),(Object)(_menuitem[_i].getObject()),_constants.POSITION_XY,(int) (0),_menuitem[_i].getTop(),(Object)(_constants.LINEAR_INOUT),(float) (2),(float) (_i/(double)5));
 }
};
 //BA.debugLineNum = 525;BA.debugLine="End Sub";
return "";
}
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Private Sub Class_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Private p As Phone";
_p = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 12;BA.debugLine="Private TweenUpdate As Timer";
_tweenupdate = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 15;BA.debugLine="Private MyTween(5) As Tween";
_mytween = new com.rootsoft.tweenengine.RSTween[(int) (5)];
{
int d0 = _mytween.length;
for (int i0 = 0;i0 < d0;i0++) {
_mytween[i0] = new com.rootsoft.tweenengine.RSTween();
}
}
;
 //BA.debugLineNum = 16;BA.debugLine="Private Manager As TweenManager";
_manager = new com.rootsoft.tweenengine.RSTweenManager();
 //BA.debugLineNum = 17;BA.debugLine="Private Constants As TweenConstants";
_constants = new com.rootsoft.tweenengine.RSTweenConstants();
 //BA.debugLineNum = 18;BA.debugLine="Private Callback As TweenCallback";
_callback = new com.rootsoft.tweenengine.RSTweenCallback();
 //BA.debugLineNum = 20;BA.debugLine="Private FakePanel As Panel";
_fakepanel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private MenuPanel As ScrollView";
_menupanel = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private MenuTitle As Label";
_menutitle = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private MenuItem(51) As ListView";
_menuitem = new anywheresoftware.b4a.objects.ListViewWrapper[(int) (51)];
{
int d0 = _menuitem.length;
for (int i0 = 0;i0 < d0;i0++) {
_menuitem[i0] = new anywheresoftware.b4a.objects.ListViewWrapper();
}
}
;
 //BA.debugLineNum = 25;BA.debugLine="Private mModule As Object";
_mmodule = new Object();
 //BA.debugLineNum = 26;BA.debugLine="Private mEventName As String";
_meventname = "";
 //BA.debugLineNum = 27;BA.debugLine="Private mLocation As String";
_mlocation = "";
 //BA.debugLineNum = 28;BA.debugLine="Private mWidth As Int";
_mwidth = 0;
 //BA.debugLineNum = 29;BA.debugLine="Private mTop As Int";
_mtop = 0;
 //BA.debugLineNum = 31;BA.debugLine="Private ItemsInMenu As Int";
_itemsinmenu = 0;
 //BA.debugLineNum = 32;BA.debugLine="Private TitlePad1 As Int";
_titlepad1 = 0;
 //BA.debugLineNum = 33;BA.debugLine="Private TitlePad2 As Int";
_titlepad2 = 0;
 //BA.debugLineNum = 35;BA.debugLine="Public IsOpen As Boolean";
_isopen = false;
 //BA.debugLineNum = 37;BA.debugLine="End Sub";
return "";
}
public String  _closemenu() throws Exception{
 //BA.debugLineNum = 296;BA.debugLine="Public Sub CloseMenu";
 //BA.debugLineNum = 298;BA.debugLine="IsOpen = False";
_isopen = __c.False;
 //BA.debugLineNum = 300;BA.debugLine="If p.SdkVersion < 11 Then";
if (_p.getSdkVersion()<11) { 
 //BA.debugLineNum = 302;BA.debugLine="FakePanel.Visible = False";
_fakepanel.setVisible(__c.False);
 //BA.debugLineNum = 303;BA.debugLine="MenuPanel.Visible = False";
_menupanel.setVisible(__c.False);
 //BA.debugLineNum = 305;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 309;BA.debugLine="FakePanel.Visible = False";
_fakepanel.setVisible(__c.False);
 //BA.debugLineNum = 311;BA.debugLine="ResetTween(\"Panel\", 2, MenuPanel)";
_resettween("Panel",(int) (2),(Object)(_menupanel.getObject()));
 //BA.debugLineNum = 313;BA.debugLine="If mLocation = \"R\" Then";
if ((_mlocation).equals("R")) { 
 //BA.debugLineNum = 315;BA.debugLine="AnimateTween(\"Panel\", 1, MenuPanel, Cons";
_animatetween("Panel",(int) (1),(Object)(_menupanel.getObject()),_constants.POSITION_XY,__c.PerXToCurrent((float) (100),ba),_menupanel.getTop(),(Object)(_constants.BACK_INOUT),(float) (1),(float) (0));
 }else {
 //BA.debugLineNum = 319;BA.debugLine="AnimateTween(\"Panel\", 1, MenuPanel, Cons";
_animatetween("Panel",(int) (1),(Object)(_menupanel.getObject()),_constants.POSITION_XY,(int) (-_menupanel.getWidth()),_menupanel.getTop(),(Object)(_constants.BACK_INOUT),(float) (1),(float) (0));
 };
 //BA.debugLineNum = 324;BA.debugLine="TweenUpdate.Initialize(\"TweenUpdate\", 10)";
_tweenupdate.Initialize(ba,"TweenUpdate",(long) (10));
 //BA.debugLineNum = 325;BA.debugLine="TweenUpdate.Enabled = True";
_tweenupdate.setEnabled(__c.True);
 //BA.debugLineNum = 328;BA.debugLine="Callback.Initialize(\"TweenCallback\")";
_callback.Initialize(ba,"TweenCallback");
 //BA.debugLineNum = 329;BA.debugLine="Callback.Listener = True";
_callback.setListener(__c.True);
 //BA.debugLineNum = 332;BA.debugLine="MyTween(1).Callback = Callback.TweenCallbac";
_mytween[(int) (1)].setCallback(_callback.getTweenCallback());
 //BA.debugLineNum = 334;BA.debugLine="Manager.StartTween";
_manager.StartTween();
 //BA.debugLineNum = 336;BA.debugLine="End Sub";
return "";
}
public String  _fadetween(String _viewtype,int _id,Object _viewname,int _fadetype,float _duration,float _startdelay) throws Exception{
 //BA.debugLineNum = 600;BA.debugLine="Private Sub FadeTween(ViewType As String, ID As In";
 //BA.debugLineNum = 602;BA.debugLine="MyTween(ID).Initialize(\"Tween\")";
_mytween[_id].Initialize(ba,"Tween");
 //BA.debugLineNum = 604;BA.debugLine="Duration = Duration * 10";
_duration = (float) (_duration*10);
 //BA.debugLineNum = 605;BA.debugLine="StartDelay = StartDelay * 10";
_startdelay = (float) (_startdelay*10);
 //BA.debugLineNum = 607;BA.debugLine="Select Case ViewType";
switch (BA.switchObjectToInt(_viewtype,"Panel","HorizontalScrollView","ListView","ScrollView","Spinner","TabHost","WebView","ImageView","Label","Button","AutoCompleteEditText","CheckBox","EditText","RadioButton","ToggleButton")) {
case 0:
case 1:
case 2:
case 3:
case 4:
case 5:
case 6:
 //BA.debugLineNum = 611;BA.debugLine="MyTween(ID).registerPanel";
_mytween[_id].registerPanelAccessor();
 //BA.debugLineNum = 612;BA.debugLine="MyTween(ID).PanelTo(ViewN";
_mytween[_id].PanelTo((anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_viewname)),_constants.ALPHA,_duration);
 break;
case 7:
 //BA.debugLineNum = 616;BA.debugLine="MyTween(ID).registerImage";
_mytween[_id].registerImageAccessor();
 //BA.debugLineNum = 617;BA.debugLine="MyTween(ID).ImageTo(ViewN";
_mytween[_id].ImageTo((anywheresoftware.b4a.objects.ImageViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ImageViewWrapper(), (android.widget.ImageView)(_viewname)),_constants.ALPHA,_duration);
 break;
case 8:
case 9:
case 10:
case 11:
case 12:
case 13:
case 14:
 //BA.debugLineNum = 621;BA.debugLine="MyTween(ID).registerLabel";
_mytween[_id].registerLabelAccessor();
 //BA.debugLineNum = 622;BA.debugLine="MyTween(ID).LabelTo(ViewN";
_mytween[_id].LabelTo((anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_viewname)),_constants.ALPHA,_duration);
 break;
}
;
 //BA.debugLineNum = 626;BA.debugLine="MyTween(ID).setTarget(FadeType, 0)";
_mytween[_id].setTarget((float) (_fadetype),(float) (0));
 //BA.debugLineNum = 627;BA.debugLine="MyTween(ID).Delay = StartDelay";
_mytween[_id].setDelay(_startdelay);
 //BA.debugLineNum = 629;BA.debugLine="Manager.addTween(MyTween(ID).Tween)";
_manager.addTween(_mytween[_id].getTween());
 //BA.debugLineNum = 631;BA.debugLine="End Sub";
return "";
}
public String  _fakepanel_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 357;BA.debugLine="Private Sub FakePanel_Touch(Action As Int, X As Fl";
 //BA.debugLineNum = 359;BA.debugLine="CloseMenu";
_closemenu();
 //BA.debugLineNum = 361;BA.debugLine="End Sub";
return "";
}
public String  _initialize(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ActivityWrapper _activity,Object _module,String _title,String _eventname,String _menulocation,int _menutop,int _menuwidth,int _menucolor,anywheresoftware.b4a.objects.drawable.ColorDrawable _menubackgroundbitmap) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 48;BA.debugLine="Public Sub Initialize(Activity As Activity, Module";
 //BA.debugLineNum = 50;BA.debugLine="mModule = Module";
_mmodule = _module;
 //BA.debugLineNum = 51;BA.debugLine="mEventName = EventName";
_meventname = _eventname;
 //BA.debugLineNum = 52;BA.debugLine="mLocation = MenuLocation.ToUpperCase.Trim";
_mlocation = _menulocation.toUpperCase().trim();
 //BA.debugLineNum = 53;BA.debugLine="mWidth = MenuWidth";
_mwidth = _menuwidth;
 //BA.debugLineNum = 54;BA.debugLine="mTop = MenuTop";
_mtop = _menutop;
 //BA.debugLineNum = 56;BA.debugLine="Manager.Initialize";
_manager.Initialize();
 //BA.debugLineNum = 58;BA.debugLine="FakePanel.Initialize(\"FakePanel\")";
_fakepanel.Initialize(ba,"FakePanel");
 //BA.debugLineNum = 60;BA.debugLine="MenuPanel.Initialize(100%y - MenuTop)";
_menupanel.Initialize(ba,(int) (__c.PerYToCurrent((float) (100),ba)-_menutop));
 //BA.debugLineNum = 61;BA.debugLine="MenuPanel.Color = MenuColor";
_menupanel.setColor(_menucolor);
 //BA.debugLineNum = 63;BA.debugLine="MenuTitle.Initialize(\"\")";
_menutitle.Initialize(ba,"");
 //BA.debugLineNum = 65;BA.debugLine="If MenuBackgroundBitmap.IsInitialized Then";
if (_menubackgroundbitmap.IsInitialized()) { 
 //BA.debugLineNum = 67;BA.debugLine="MenuPanel.Background = MenuBackgroundBit";
_menupanel.setBackground((android.graphics.drawable.Drawable)(_menubackgroundbitmap.getObject()));
 };
 //BA.debugLineNum = 71;BA.debugLine="Activity.AddView(FakePanel, 0dip, MenuTop,";
_activity.AddView((android.view.View)(_fakepanel.getObject()),__c.DipToCurrent((int) (0)),_menutop,__c.PerXToCurrent((float) (100),ba),(int) (__c.PerYToCurrent((float) (100),ba)-_menutop));
 //BA.debugLineNum = 72;BA.debugLine="Activity.AddView(MenuPanel, 0dip, 0dip, 0di";
_activity.AddView((android.view.View)(_menupanel.getObject()),__c.DipToCurrent((int) (0)),__c.DipToCurrent((int) (0)),__c.DipToCurrent((int) (0)),__c.DipToCurrent((int) (0)));
 //BA.debugLineNum = 74;BA.debugLine="If Title.Trim <> \"\" Or Title.Trim = \"*\" The";
if ((_title.trim()).equals("") == false || (_title.trim()).equals("*")) { 
 //BA.debugLineNum = 76;BA.debugLine="MenuPanel.Panel.AddView(MenuTitle, 0dip,";
_menupanel.getPanel().AddView((android.view.View)(_menutitle.getObject()),__c.DipToCurrent((int) (0)),__c.DipToCurrent((int) (0)),_menupanel.getPanel().getWidth(),__c.DipToCurrent((int) (30)));
 //BA.debugLineNum = 78;BA.debugLine="If Title.Trim <> \"*\" Then";
if ((_title.trim()).equals("*") == false) { 
 //BA.debugLineNum = 80;BA.debugLine="MenuTitle.Text = \" \" & Title.Trim & \"";
_menutitle.setText((Object)(" "+_title.trim()+" "));
 };
 //BA.debugLineNum = 84;BA.debugLine="MenuTitle.Color = Colors.White";
_menutitle.setColor(__c.Colors.White);
 //BA.debugLineNum = 85;BA.debugLine="MenuTitle.TextColor = Colors.Black";
_menutitle.setTextColor(__c.Colors.Black);
 //BA.debugLineNum = 86;BA.debugLine="MenuTitle.TextSize = 18";
_menutitle.setTextSize((float) (18));
 //BA.debugLineNum = 87;BA.debugLine="MenuTitle.Gravity = Gravity.CENTER_HORIZ";
_menutitle.setGravity(__c.Gravity.CENTER_HORIZONTAL);
 //BA.debugLineNum = 89;BA.debugLine="TitlePad1 = 31dip";
_titlepad1 = __c.DipToCurrent((int) (31));
 //BA.debugLineNum = 90;BA.debugLine="TitlePad2 = 21dip";
_titlepad2 = __c.DipToCurrent((int) (21));
 }else {
 //BA.debugLineNum = 94;BA.debugLine="TitlePad1 = 0dip";
_titlepad1 = __c.DipToCurrent((int) (0));
 //BA.debugLineNum = 95;BA.debugLine="TitlePad2 = 51dip";
_titlepad2 = __c.DipToCurrent((int) (51));
 };
 //BA.debugLineNum = 99;BA.debugLine="MenuPanel.Panel.Color = MenuColor";
_menupanel.getPanel().setColor(_menucolor);
 //BA.debugLineNum = 100;BA.debugLine="FakePanel.Visible = False";
_fakepanel.setVisible(__c.False);
 //BA.debugLineNum = 102;BA.debugLine="End Sub";
return "";
}
public String  _menuitem_itemclick(int _position,Object _value) throws Exception{
anywheresoftware.b4a.objects.ListViewWrapper _lvclicked = null;
 //BA.debugLineNum = 338;BA.debugLine="Private Sub MenuItem_ItemClick(Position As Int, Va";
 //BA.debugLineNum = 340;BA.debugLine="Dim lvClicked As ListView";
_lvclicked = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 342;BA.debugLine="IsOpen = False";
_isopen = __c.False;
 //BA.debugLineNum = 344;BA.debugLine="lvClicked = Sender";
_lvclicked.setObject((anywheresoftware.b4a.objects.ListViewWrapper.SimpleListView)(__c.Sender(ba)));
 //BA.debugLineNum = 346;BA.debugLine="FakePanel.Visible = False";
_fakepanel.setVisible(__c.False);
 //BA.debugLineNum = 347;BA.debugLine="MenuPanel.Visible = False";
_menupanel.setVisible(__c.False);
 //BA.debugLineNum = 349;BA.debugLine="If SubExists(mModule, mEventName & \"_Click";
if (__c.SubExists(ba,_mmodule,_meventname+"_Click")) { 
 //BA.debugLineNum = 351;BA.debugLine="CallSubDelayed2(mModule, mEventName & \"";
__c.CallSubDelayed2(ba,_mmodule,_meventname+"_Click",_lvclicked.getTag());
 };
 //BA.debugLineNum = 355;BA.debugLine="End Sub";
return "";
}
public String  _openmenu(String _animation) throws Exception{
String _xpos = "";
String _ypos = "";
 //BA.debugLineNum = 166;BA.debugLine="Public Sub OpenMenu(Animation As String)";
 //BA.debugLineNum = 168;BA.debugLine="IsOpen = True";
_isopen = __c.True;
 //BA.debugLineNum = 170;BA.debugLine="MenuPanel.ScrollPosition = 0";
_menupanel.setScrollPosition((int) (0));
 //BA.debugLineNum = 172;BA.debugLine="MenuPanel.Visible = True";
_menupanel.setVisible(__c.True);
 //BA.debugLineNum = 173;BA.debugLine="FakePanel.Visible = True";
_fakepanel.setVisible(__c.True);
 //BA.debugLineNum = 175;BA.debugLine="Select Case mLocation";
switch (BA.switchObjectToInt(_mlocation,"L","R","T","B")) {
case 0:
 //BA.debugLineNum = 179;BA.debugLine="MenuPanel.SetLayout(-mWidth, mT";
_menupanel.SetLayout((int) (-_mwidth),_mtop,_mwidth,(int) (__c.PerYToCurrent((float) (100),ba)-_mtop));
 //BA.debugLineNum = 181;BA.debugLine="xPos = 0dip";
_xpos = BA.NumberToString(__c.DipToCurrent((int) (0)));
 //BA.debugLineNum = 182;BA.debugLine="yPos = mTop";
_ypos = BA.NumberToString(_mtop);
 break;
case 1:
 //BA.debugLineNum = 186;BA.debugLine="MenuPanel.SetLayout(100%x + mWi";
_menupanel.SetLayout((int) (__c.PerXToCurrent((float) (100),ba)+_mwidth),_mtop,_mwidth,(int) (__c.PerYToCurrent((float) (100),ba)-_mtop));
 //BA.debugLineNum = 188;BA.debugLine="xPos = 100%x - mWidth";
_xpos = BA.NumberToString(__c.PerXToCurrent((float) (100),ba)-_mwidth);
 //BA.debugLineNum = 189;BA.debugLine="yPos = mTop";
_ypos = BA.NumberToString(_mtop);
 break;
case 2:
 //BA.debugLineNum = 193;BA.debugLine="If MenuPanel.Panel.Height > 50%";
if (_menupanel.getPanel().getHeight()>__c.PerYToCurrent((float) (50),ba)) { 
 //BA.debugLineNum = 195;BA.debugLine="MenuPanel.SetLayout(0dip, -5";
_menupanel.SetLayout(__c.DipToCurrent((int) (0)),(int) (-__c.PerYToCurrent((float) (50),ba)),__c.PerXToCurrent((float) (100),ba),(int) (__c.PerYToCurrent((float) (50),ba)-_mtop));
 //BA.debugLineNum = 197;BA.debugLine="xPos = 0dip";
_xpos = BA.NumberToString(__c.DipToCurrent((int) (0)));
 //BA.debugLineNum = 198;BA.debugLine="yPos = mTop";
_ypos = BA.NumberToString(_mtop);
 }else {
 //BA.debugLineNum = 202;BA.debugLine="MenuPanel.SetLayout(0dip, -M";
_menupanel.SetLayout(__c.DipToCurrent((int) (0)),(int) (-_menupanel.getPanel().getHeight()),__c.PerXToCurrent((float) (100),ba),_menupanel.getPanel().getHeight());
 //BA.debugLineNum = 204;BA.debugLine="xPos = 0dip";
_xpos = BA.NumberToString(__c.DipToCurrent((int) (0)));
 //BA.debugLineNum = 205;BA.debugLine="yPos = mTop";
_ypos = BA.NumberToString(_mtop);
 };
 break;
case 3:
 //BA.debugLineNum = 211;BA.debugLine="If MenuPanel.Panel.Height > 50%";
if (_menupanel.getPanel().getHeight()>__c.PerYToCurrent((float) (50),ba)) { 
 //BA.debugLineNum = 213;BA.debugLine="MenuPanel.SetLayout(0dip, 10";
_menupanel.SetLayout(__c.DipToCurrent((int) (0)),(int) (__c.PerYToCurrent((float) (100),ba)+_menupanel.getHeight()),__c.PerXToCurrent((float) (100),ba),__c.PerYToCurrent((float) (50),ba));
 //BA.debugLineNum = 215;BA.debugLine="xPos = 0dip";
_xpos = BA.NumberToString(__c.DipToCurrent((int) (0)));
 //BA.debugLineNum = 216;BA.debugLine="yPos = 50%y";
_ypos = BA.NumberToString(__c.PerYToCurrent((float) (50),ba));
 }else {
 //BA.debugLineNum = 220;BA.debugLine="MenuPanel.SetLayout(0dip, 10";
_menupanel.SetLayout(__c.DipToCurrent((int) (0)),(int) (__c.PerYToCurrent((float) (100),ba)+_menupanel.getPanel().getHeight()),__c.PerXToCurrent((float) (100),ba),_menupanel.getPanel().getHeight());
 //BA.debugLineNum = 222;BA.debugLine="xPos = 0dip";
_xpos = BA.NumberToString(__c.DipToCurrent((int) (0)));
 //BA.debugLineNum = 223;BA.debugLine="yPos = 100%y - MenuPanel.Hei";
_ypos = BA.NumberToString(__c.PerYToCurrent((float) (100),ba)-_menupanel.getHeight());
 };
 break;
}
;
 //BA.debugLineNum = 229;BA.debugLine="Select Case Animation";
switch (BA.switchObjectToInt(_animation,"Default","Casino","CasinoReverse","ShootUp","Cascade","Flip","Fade","ZigZag")) {
case 0:
 //BA.debugLineNum = 233;BA.debugLine="AnimDefault(xPos, yPos)";
_animdefault((int)(Double.parseDouble(_xpos)),(int)(Double.parseDouble(_ypos)));
 break;
case 1:
 //BA.debugLineNum = 237;BA.debugLine="AnimCasino(xPos, yPos)";
_animcasino((int)(Double.parseDouble(_xpos)),(int)(Double.parseDouble(_ypos)));
 break;
case 2:
 //BA.debugLineNum = 241;BA.debugLine="AnimCasinoReverse(xPos, yPos)";
_animcasinoreverse((int)(Double.parseDouble(_xpos)),(int)(Double.parseDouble(_ypos)));
 break;
case 3:
 //BA.debugLineNum = 245;BA.debugLine="AnimShootUp(xPos, yPos)";
_animshootup((int)(Double.parseDouble(_xpos)),(int)(Double.parseDouble(_ypos)));
 break;
case 4:
 //BA.debugLineNum = 249;BA.debugLine="AnimCascade(xPos, yPos)";
_animcascade((int)(Double.parseDouble(_xpos)),(int)(Double.parseDouble(_ypos)));
 break;
case 5:
 //BA.debugLineNum = 253;BA.debugLine="If p.SdkVersion < 11 Then";
if (_p.getSdkVersion()<11) { 
 //BA.debugLineNum = 255;BA.debugLine="AnimDefault(xPos, yPos)";
_animdefault((int)(Double.parseDouble(_xpos)),(int)(Double.parseDouble(_ypos)));
 }else {
 //BA.debugLineNum = 259;BA.debugLine="AnimFlip(xPos, yPos, mLocati";
_animflip((int)(Double.parseDouble(_xpos)),(int)(Double.parseDouble(_ypos)),_mlocation);
 };
 break;
case 6:
 //BA.debugLineNum = 265;BA.debugLine="If p.SdkVersion < 11 Then";
if (_p.getSdkVersion()<11) { 
 //BA.debugLineNum = 267;BA.debugLine="AnimDefault(xPos, yPos)";
_animdefault((int)(Double.parseDouble(_xpos)),(int)(Double.parseDouble(_ypos)));
 }else {
 //BA.debugLineNum = 271;BA.debugLine="AnimFade(xPos, yPos)";
_animfade((int)(Double.parseDouble(_xpos)),(int)(Double.parseDouble(_ypos)));
 };
 break;
case 7:
 //BA.debugLineNum = 277;BA.debugLine="AnimZigZag(xPos, yPos)";
_animzigzag((int)(Double.parseDouble(_xpos)),(int)(Double.parseDouble(_ypos)));
 break;
}
;
 //BA.debugLineNum = 282;BA.debugLine="TweenUpdate.Initialize(\"TweenUpdate\", 10)";
_tweenupdate.Initialize(ba,"TweenUpdate",(long) (10));
 //BA.debugLineNum = 283;BA.debugLine="TweenUpdate.Enabled = True";
_tweenupdate.setEnabled(__c.True);
 //BA.debugLineNum = 286;BA.debugLine="Callback.Initialize(\"TweenCallback\")";
_callback.Initialize(ba,"TweenCallback");
 //BA.debugLineNum = 287;BA.debugLine="Callback.Listener = True";
_callback.setListener(__c.True);
 //BA.debugLineNum = 290;BA.debugLine="MyTween(1).Callback = Callback.TweenCallbac";
_mytween[(int) (1)].setCallback(_callback.getTweenCallback());
 //BA.debugLineNum = 292;BA.debugLine="Manager.StartTween";
_manager.StartTween();
 //BA.debugLineNum = 294;BA.debugLine="End Sub";
return "";
}
public String  _resettween(String _viewtype,int _id,Object _viewname) throws Exception{
double[] _constant = null;
float[] _resetvalues1 = null;
float[] _resetvalues2 = null;
int _i = 0;
 //BA.debugLineNum = 668;BA.debugLine="Private Sub ResetTween(ViewType As String, ID As I";
 //BA.debugLineNum = 670;BA.debugLine="Dim Constant() As Double";
_constant = new double[(int) (0)];
;
 //BA.debugLineNum = 671;BA.debugLine="Dim ResetValues1() As Float";
_resetvalues1 = new float[(int) (0)];
;
 //BA.debugLineNum = 672;BA.debugLine="Dim ResetValues2() As Float";
_resetvalues2 = new float[(int) (0)];
;
 //BA.debugLineNum = 674;BA.debugLine="Constant = Array As Double(Constants.SCALE";
_constant = new double[]{_constants.SCALE_XY,_constants.ALPHA,_constants.ROT_X,_constants.ROT_Y,_constants.ROT_Z};
 //BA.debugLineNum = 676;BA.debugLine="ResetValues1 = Array As Float(1, 1, 0, 0,";
_resetvalues1 = new float[]{(float) (1),(float) (1),(float) (0),(float) (0),(float) (0)};
 //BA.debugLineNum = 677;BA.debugLine="ResetValues2 = Array As Float(1, 0, 0, 0,";
_resetvalues2 = new float[]{(float) (1),(float) (0),(float) (0),(float) (0),(float) (0)};
 //BA.debugLineNum = 679;BA.debugLine="Manager.Initialize";
_manager.Initialize();
 //BA.debugLineNum = 681;BA.debugLine="For I = 0 To Constant.Length - 1";
{
final int step359 = 1;
final int limit359 = (int) (_constant.length-1);
for (_i = (int) (0); (step359 > 0 && _i <= limit359) || (step359 < 0 && _i >= limit359); _i = ((int)(0 + _i + step359))) {
 //BA.debugLineNum = 683;BA.debugLine="MyTween(ID).Initialize(\"Tween\")";
_mytween[_id].Initialize(ba,"Tween");
 //BA.debugLineNum = 685;BA.debugLine="Select Case ViewType";
switch (BA.switchObjectToInt(_viewtype,"Panel","HorizontalScrollView","ListView","ScrollView","Spinner","TabHost","WebView","ImageView","Label","Button","AutoCompleteEditText","CheckBox","EditText","RadioButton","ToggleButton")) {
case 0:
case 1:
case 2:
case 3:
case 4:
case 5:
case 6:
 //BA.debugLineNum = 689;BA.debugLine="MyTween(ID).registerP";
_mytween[_id].registerPanelAccessor();
 //BA.debugLineNum = 690;BA.debugLine="MyTween(ID).PanelTo(V";
_mytween[_id].PanelTo((anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_viewname)),(int) (_constant[_i]),(float) (0));
 break;
case 7:
 //BA.debugLineNum = 694;BA.debugLine="MyTween(ID).registerI";
_mytween[_id].registerImageAccessor();
 //BA.debugLineNum = 695;BA.debugLine="MyTween(ID).ImageTo(V";
_mytween[_id].ImageTo((anywheresoftware.b4a.objects.ImageViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ImageViewWrapper(), (android.widget.ImageView)(_viewname)),(int) (_constant[_i]),(float) (0));
 break;
case 8:
case 9:
case 10:
case 11:
case 12:
case 13:
case 14:
 //BA.debugLineNum = 699;BA.debugLine="MyTween(ID).registerL";
_mytween[_id].registerLabelAccessor();
 //BA.debugLineNum = 700;BA.debugLine="MyTween(ID).LabelTo(V";
_mytween[_id].LabelTo((anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_viewname)),(int) (_constant[_i]),(float) (0));
 break;
}
;
 //BA.debugLineNum = 704;BA.debugLine="MyTween(ID).setTarget(ResetValues1(I),";
_mytween[_id].setTarget(_resetvalues1[_i],_resetvalues2[_i]);
 //BA.debugLineNum = 706;BA.debugLine="Manager.addTween(MyTween(ID).Tween)";
_manager.addTween(_mytween[_id].getTween());
 //BA.debugLineNum = 707;BA.debugLine="Manager.StartTween";
_manager.StartTween();
 //BA.debugLineNum = 709;BA.debugLine="Manager.Update(.1)";
_manager.Update((float) (.1));
 }
};
 //BA.debugLineNum = 713;BA.debugLine="End Sub";
return "";
}
public String  _rotatetween(String _viewtype,int _id,Object _viewname,double _tweenconstant,int _angle,float _duration,int _repeat,float _repeatdelay,float _startdelay) throws Exception{
 //BA.debugLineNum = 565;BA.debugLine="Private Sub RotateTween(ViewType As String, ID As";
 //BA.debugLineNum = 567;BA.debugLine="MyTween(ID).Initialize(\"Tween\")";
_mytween[_id].Initialize(ba,"Tween");
 //BA.debugLineNum = 569;BA.debugLine="Duration = Duration * 10";
_duration = (float) (_duration*10);
 //BA.debugLineNum = 570;BA.debugLine="RepeatDelay = RepeatDelay * 10";
_repeatdelay = (float) (_repeatdelay*10);
 //BA.debugLineNum = 571;BA.debugLine="StartDelay = StartDelay * 10";
_startdelay = (float) (_startdelay*10);
 //BA.debugLineNum = 573;BA.debugLine="Select Case ViewType";
switch (BA.switchObjectToInt(_viewtype,"Panel","HorizontalScrollView","ListView","ScrollView","Spinner","TabHost","WebView","ImageView","Label","Button","AutoCompleteEditText","CheckBox","EditText","RadioButton","ToggleButton")) {
case 0:
case 1:
case 2:
case 3:
case 4:
case 5:
case 6:
 //BA.debugLineNum = 577;BA.debugLine="MyTween(ID).registerPanel";
_mytween[_id].registerPanelAccessor();
 //BA.debugLineNum = 578;BA.debugLine="MyTween(ID).PanelTo(ViewN";
_mytween[_id].PanelTo((anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_viewname)),(int) (_tweenconstant),_duration);
 break;
case 7:
 //BA.debugLineNum = 582;BA.debugLine="MyTween(ID).registerImage";
_mytween[_id].registerImageAccessor();
 //BA.debugLineNum = 583;BA.debugLine="MyTween(ID).ImageTo(ViewN";
_mytween[_id].ImageTo((anywheresoftware.b4a.objects.ImageViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ImageViewWrapper(), (android.widget.ImageView)(_viewname)),(int) (_tweenconstant),_duration);
 break;
case 8:
case 9:
case 10:
case 11:
case 12:
case 13:
case 14:
 //BA.debugLineNum = 587;BA.debugLine="MyTween(ID).registerLabel";
_mytween[_id].registerLabelAccessor();
 //BA.debugLineNum = 588;BA.debugLine="MyTween(ID).LabelTo(ViewN";
_mytween[_id].LabelTo((anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_viewname)),(int) (_tweenconstant),_duration);
 break;
}
;
 //BA.debugLineNum = 592;BA.debugLine="MyTween(ID).Repeat(Repeat, RepeatDelay)";
_mytween[_id].Repeat(_repeat,_repeatdelay);
 //BA.debugLineNum = 593;BA.debugLine="MyTween(ID).setTarget(Angle, 0)";
_mytween[_id].setTarget((float) (_angle),(float) (0));
 //BA.debugLineNum = 594;BA.debugLine="MyTween(ID).Delay = StartDelay";
_mytween[_id].setDelay(_startdelay);
 //BA.debugLineNum = 596;BA.debugLine="Manager.addTween(MyTween(ID).Tween)";
_manager.addTween(_mytween[_id].getTween());
 //BA.debugLineNum = 598;BA.debugLine="End Sub";
return "";
}
public String  _scaletween(String _viewtype,int _id,Object _viewname,float _xaxis,float _yaxis,float _duration,int _repeat,float _repeatdelay,float _startdelay) throws Exception{
 //BA.debugLineNum = 633;BA.debugLine="Private Sub ScaleTween(ViewType As String, ID As I";
 //BA.debugLineNum = 635;BA.debugLine="MyTween(ID).Initialize(\"Tween\")";
_mytween[_id].Initialize(ba,"Tween");
 //BA.debugLineNum = 637;BA.debugLine="Duration = Duration * 10";
_duration = (float) (_duration*10);
 //BA.debugLineNum = 638;BA.debugLine="RepeatDelay = RepeatDelay * 10";
_repeatdelay = (float) (_repeatdelay*10);
 //BA.debugLineNum = 639;BA.debugLine="StartDelay = StartDelay * 10";
_startdelay = (float) (_startdelay*10);
 //BA.debugLineNum = 641;BA.debugLine="Select Case ViewType";
switch (BA.switchObjectToInt(_viewtype,"Panel","HorizontalScrollView","ListView","ScrollView","Spinner","TabHost","WebView","ImageView","Label","Button","AutoCompleteEditText","CheckBox","EditText","RadioButton","ToggleButton")) {
case 0:
case 1:
case 2:
case 3:
case 4:
case 5:
case 6:
 //BA.debugLineNum = 645;BA.debugLine="MyTween(ID).registerPanel";
_mytween[_id].registerPanelAccessor();
 //BA.debugLineNum = 646;BA.debugLine="MyTween(ID).PanelTo(ViewN";
_mytween[_id].PanelTo((anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_viewname)),_constants.SCALE_XY,_duration);
 break;
case 7:
 //BA.debugLineNum = 650;BA.debugLine="MyTween(ID).registerImage";
_mytween[_id].registerImageAccessor();
 //BA.debugLineNum = 651;BA.debugLine="MyTween(ID).ImageTo(ViewN";
_mytween[_id].ImageTo((anywheresoftware.b4a.objects.ImageViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ImageViewWrapper(), (android.widget.ImageView)(_viewname)),_constants.SCALE_XY,_duration);
 break;
case 8:
case 9:
case 10:
case 11:
case 12:
case 13:
case 14:
 //BA.debugLineNum = 655;BA.debugLine="MyTween(ID).registerLabel";
_mytween[_id].registerLabelAccessor();
 //BA.debugLineNum = 656;BA.debugLine="MyTween(ID).LabelTo(ViewN";
_mytween[_id].LabelTo((anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_viewname)),_constants.SCALE_XY,_duration);
 break;
}
;
 //BA.debugLineNum = 660;BA.debugLine="MyTween(ID).Repeat(Repeat, RepeatDelay)";
_mytween[_id].Repeat(_repeat,_repeatdelay);
 //BA.debugLineNum = 661;BA.debugLine="MyTween(ID).setTarget(XAxis, YAxis)";
_mytween[_id].setTarget(_xaxis,_yaxis);
 //BA.debugLineNum = 662;BA.debugLine="MyTween(ID).Delay = StartDelay";
_mytween[_id].setDelay(_startdelay);
 //BA.debugLineNum = 664;BA.debugLine="Manager.addTween(MyTween(ID).Tween)";
_manager.addTween(_mytween[_id].getTween());
 //BA.debugLineNum = 666;BA.debugLine="End Sub";
return "";
}
public String  _settitle(int _titlebackgroundcolor,int _textcolor,int _centering,anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _titlebackgroundbitmap) throws Exception{
 //BA.debugLineNum = 104;BA.debugLine="Public Sub SetTitle(TitleBackgroundColor As Int, T";
 //BA.debugLineNum = 106;BA.debugLine="MenuTitle.Color = TitleBackgroundColor";
_menutitle.setColor(_titlebackgroundcolor);
 //BA.debugLineNum = 107;BA.debugLine="MenuTitle.TextColor = TextColor";
_menutitle.setTextColor(_textcolor);
 //BA.debugLineNum = 108;BA.debugLine="MenuTitle.Gravity = Centering";
_menutitle.setGravity(_centering);
 //BA.debugLineNum = 110;BA.debugLine="If TitleBackgroundBitmap.IsInitialized Then";
if (_titlebackgroundbitmap.IsInitialized()) { 
 //BA.debugLineNum = 112;BA.debugLine="MenuTitle.SetBackgroundImage(TitleBackgr";
_menutitle.SetBackgroundImage((android.graphics.Bitmap)(_titlebackgroundbitmap.getObject()));
 };
 //BA.debugLineNum = 116;BA.debugLine="End Sub";
return "";
}
public String  _tweencallback_onevent(int _event) throws Exception{
 //BA.debugLineNum = 715;BA.debugLine="Private Sub TweenCallback_OnEvent(Event As Int)";
 //BA.debugLineNum = 719;BA.debugLine="If Event = 8 Then";
if (_event==8) { 
 //BA.debugLineNum = 721;BA.debugLine="TweenUpdate.Enabled = False";
_tweenupdate.setEnabled(__c.False);
 };
 //BA.debugLineNum = 725;BA.debugLine="End Sub";
return "";
}
public String  _tweenupdate_tick() throws Exception{
 //BA.debugLineNum = 727;BA.debugLine="Private Sub TweenUpdate_Tick 'Updates the tween.";
 //BA.debugLineNum = 729;BA.debugLine="Manager.Update(.5)";
_manager.Update((float) (.5));
 //BA.debugLineNum = 731;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
