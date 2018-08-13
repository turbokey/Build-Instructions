package com.serogen.sbsifmc;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = true;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isFirst) {
			processBA = new BA(this.getApplicationContext(), null, null, "com.serogen.sbsifmc", "com.serogen.sbsifmc.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
				p.finish();
			}
		}
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		mostCurrent = this;
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "com.serogen.sbsifmc", "com.serogen.sbsifmc.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "com.serogen.sbsifmc.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEvent(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null) //workaround for emulator bug (Issue 2423)
            return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
			if (mostCurrent == null || mostCurrent != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
		    processBA.raiseEvent(mostCurrent._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}

public anywheresoftware.b4a.keywords.Common __c = null;
public com.appodeal.basic4android.AppodealB4A _appodeal = null;
public static String _appkey = "";
public static String _actbartext = "";
public static int _actbartextcolor = 0;
public static int _actbarcolor = 0;
public static int _actbarlinecolor = 0;
public static int _actbarcornerradius = 0;
public static int _menuwidth = 0;
public static boolean _menufulllength = false;
public static int _menucolor = 0;
public static int _menutextcolor = 0;
public static int _menutextsize = 0;
public static boolean _showslidebutton = false;
public static int _slideduration = 0;
public static int _fadealphashade = 0;
public anywheresoftware.b4a.objects.ScrollViewWrapper _scvimages = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imvimage = null;
public anywheresoftware.b4a.objects.LabelWrapper _imvlabel = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmpimage = null;
public static int _imgwidth = 0;
public static int _imgheight = 0;
public static int _lblheight = 0;
public static int _imgspace = 0;
public anywheresoftware.b4a.objects.ImageViewWrapper _fullscreen = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label1 = null;
public static int _maxs = 0;
public static String _bykva = "";
public static boolean _adtime = false;
public com.serogen.sbsifmc.animatedslidingmenu _njmenu = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _icon = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _scin = null;
public anywheresoftware.b4a.objects.ButtonWrapper _menu_btn = null;
public static boolean _instruction_is_open = false;
public com.serogen.sbsifmc.starter _starter = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 177;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 178;BA.debugLine="Activity.LoadLayout(\"Build\")";
mostCurrent._activity.LoadLayout("Build",mostCurrent.activityBA);
 //BA.debugLineNum = 180;BA.debugLine="Scin.Initialize(Colors.RGB(0,0,205),0)";
mostCurrent._scin.Initialize(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (0),(int) (205)),(int) (0));
 //BA.debugLineNum = 181;BA.debugLine="If GetDefaultLanguage=\"en\" Then";
if ((_getdefaultlanguage()).equals("en")) { 
 //BA.debugLineNum = 182;BA.debugLine="menu_btn.Text = \"Open menu\"";
mostCurrent._menu_btn.setText((Object)("Open menu"));
 //BA.debugLineNum = 183;BA.debugLine="ActBarText=\"Step by Step instructions\"";
mostCurrent._actbartext = "Step by Step instructions";
 //BA.debugLineNum = 184;BA.debugLine="Label1.Text=\"This application contains step by st";
mostCurrent._label1.setText((Object)("This application contains step by step instructions of beautiful houses, various buildings, furniture, home decoration and not only. Each step has a detailed description, therefore, is not even able to build a nice person can easily build buildings, which will be proud of!"));
 }else {
 //BA.debugLineNum = 186;BA.debugLine="menu_btn.Text = \"Открыть меню\"";
mostCurrent._menu_btn.setText((Object)("Открыть меню"));
 //BA.debugLineNum = 187;BA.debugLine="ActBarText= \"Пошаговые инструкции\"";
mostCurrent._actbartext = "Пошаговые инструкции";
 //BA.debugLineNum = 188;BA.debugLine="Label1.Text=\"В этом приложении собраны пошаговые";
mostCurrent._label1.setText((Object)("В этом приложении собраны пошаговые инструкции красивых домов, различных построек, мебели, предметов интерьера и не только. Каждый шаг имеет подробное описание, поэтому даже не умеющий красиво строить человек сможет с лёгкостью возвести постройки, которыми будет гордиться!"));
 };
 //BA.debugLineNum = 191;BA.debugLine="BuildMenu";
_buildmenu();
 //BA.debugLineNum = 192;BA.debugLine="ImageView1.Visible=True";
mostCurrent._imageview1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 193;BA.debugLine="Label1.Visible=True";
mostCurrent._label1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 195;BA.debugLine="SetLabelTextSize(Label1,Label1.Text , maxs,1)";
_setlabeltextsize(mostCurrent._label1,mostCurrent._label1.getText(),(float) (_maxs),(float) (1));
 //BA.debugLineNum = 196;BA.debugLine="SetLabelTextSize(menu_btn,menu_btn.Text , maxs,1)";
_setlabeltextsize((anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(mostCurrent._menu_btn.getObject())),mostCurrent._menu_btn.getText(),(float) (_maxs),(float) (1));
 //BA.debugLineNum = 198;BA.debugLine="Appodeal.setEventHandler(\"Handler\")";
mostCurrent._appodeal.setEventHandler(mostCurrent.activityBA,"Handler");
 //BA.debugLineNum = 199;BA.debugLine="Appodeal.setBannerCallbacks()";
mostCurrent._appodeal.setBannerCallbacks(mostCurrent.activityBA);
 //BA.debugLineNum = 200;BA.debugLine="Appodeal.setInterstitialCallbacks()";
mostCurrent._appodeal.setInterstitialCallbacks(mostCurrent.activityBA);
 //BA.debugLineNum = 202;BA.debugLine="Appodeal.initialize(appKey, Bit.Or(Appodeal.INTER";
mostCurrent._appodeal.initialize(mostCurrent.activityBA,mostCurrent._appkey,anywheresoftware.b4a.keywords.Common.Bit.Or(mostCurrent._appodeal.INTERSTITIAL,mostCurrent._appodeal.BANNER));
 //BA.debugLineNum = 203;BA.debugLine="Appodeal.show(Appodeal.BANNER_BOTTOM)";
mostCurrent._appodeal.show(mostCurrent.activityBA,mostCurrent._appodeal.BANNER_BOTTOM);
 //BA.debugLineNum = 206;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
int _killapp = 0;
 //BA.debugLineNum = 325;BA.debugLine="Sub Activity_KeyPress(KeyCode As Int) As Boolean";
 //BA.debugLineNum = 326;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK And njMenu.IsO";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK && mostCurrent._njmenu._isopen) { 
 //BA.debugLineNum = 327;BA.debugLine="njMenu.CloseMenu";
mostCurrent._njmenu._closemenu();
 //BA.debugLineNum = 328;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 330;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK And Instructi";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK && _instruction_is_open) { 
 //BA.debugLineNum = 331;BA.debugLine="scvImages.Panel.RemoveAllViews";
mostCurrent._scvimages.getPanel().RemoveAllViews();
 //BA.debugLineNum = 332;BA.debugLine="scvImages.Visible =False";
mostCurrent._scvimages.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 333;BA.debugLine="Instruction_is_open =False";
_instruction_is_open = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 334;BA.debugLine="ImageView1.Visible=True";
mostCurrent._imageview1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 335;BA.debugLine="Label1.Visible=True";
mostCurrent._label1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 336;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 338;BA.debugLine="Dim KillApp As Int";
_killapp = 0;
 //BA.debugLineNum = 340;BA.debugLine="If GetDefaultLanguage=\"en\" Then";
if ((_getdefaultlanguage()).equals("en")) { 
 //BA.debugLineNum = 341;BA.debugLine="KillApp = Msgbox2(\"Are you sure you want to q";
_killapp = anywheresoftware.b4a.keywords.Common.Msgbox2("Are you sure you want to quit?","Exit","Yes","","No",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 343;BA.debugLine="KillApp = Msgbox2(\"Вы уверены, что хотите выйти?";
_killapp = anywheresoftware.b4a.keywords.Common.Msgbox2("Вы уверены, что хотите выйти?","Выход","Да","","Нет",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 345;BA.debugLine="If KillApp = DialogResponse.POSITIVE Then";
if (_killapp==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 346;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 //BA.debugLineNum = 347;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 }else {
 //BA.debugLineNum = 350;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 };
 };
 //BA.debugLineNum = 355;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 209;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 211;BA.debugLine="End Sub";
return "";
}
public static String  _animatedmenu_click(Object _selecteditem) throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _i = null;
 //BA.debugLineNum = 83;BA.debugLine="Sub AnimatedMenu_Click(SelectedItem As Object)";
 //BA.debugLineNum = 84;BA.debugLine="If SelectedItem<>18 Then";
if ((_selecteditem).equals((Object)(18)) == false) { 
 //BA.debugLineNum = 85;BA.debugLine="ImageView1.Visible=False";
mostCurrent._imageview1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 86;BA.debugLine="Label1.Visible=False";
mostCurrent._label1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 87;BA.debugLine="If adtime And Appodeal.isLoaded(Appodeal.INTERSTIT";
if (_adtime && mostCurrent._appodeal.isLoaded(mostCurrent.activityBA,mostCurrent._appodeal.INTERSTITIAL)) { 
 //BA.debugLineNum = 88;BA.debugLine="Appodeal.showWithPlacement(Appodeal.INTERSTITI";
mostCurrent._appodeal.showWithPlacement(mostCurrent.activityBA,mostCurrent._appodeal.INTERSTITIAL,"level_end");
 };
 //BA.debugLineNum = 90;BA.debugLine="adtime=Not(adtime)";
_adtime = anywheresoftware.b4a.keywords.Common.Not(_adtime);
 };
 //BA.debugLineNum = 92;BA.debugLine="If GetDefaultLanguage=\"en\" Then";
if ((_getdefaultlanguage()).equals("en")) { 
 //BA.debugLineNum = 93;BA.debugLine="Select SelectedItem";
switch (BA.switchObjectToInt(_selecteditem,(Object)(1),(Object)(2),(Object)(3),(Object)(4),(Object)(5),(Object)(6),(Object)(7),(Object)(8),(Object)(9),(Object)(10),(Object)(11),(Object)(12),(Object)(13),(Object)(14),(Object)(15),(Object)(16),(Object)(17),(Object)(18))) {
case 0:
 //BA.debugLineNum = 95;BA.debugLine="InitScrollView(\"A\", 21)";
_initscrollview("A",(int) (21));
 break;
case 1:
 //BA.debugLineNum = 97;BA.debugLine="InitScrollView(\"B\", 25)";
_initscrollview("B",(int) (25));
 break;
case 2:
 //BA.debugLineNum = 99;BA.debugLine="InitScrollView(\"C\", 21)";
_initscrollview("C",(int) (21));
 break;
case 3:
 //BA.debugLineNum = 101;BA.debugLine="InitScrollView(\"Z\", 36)";
_initscrollview("Z",(int) (36));
 break;
case 4:
 //BA.debugLineNum = 103;BA.debugLine="InitScrollView(\"D\",18)";
_initscrollview("D",(int) (18));
 break;
case 5:
 //BA.debugLineNum = 105;BA.debugLine="InitScrollView(\"E\", 21)";
_initscrollview("E",(int) (21));
 break;
case 6:
 //BA.debugLineNum = 107;BA.debugLine="InitScrollView(\"F\",11)";
_initscrollview("F",(int) (11));
 break;
case 7:
 //BA.debugLineNum = 109;BA.debugLine="InitScrollView(\"G\",32)";
_initscrollview("G",(int) (32));
 break;
case 8:
 //BA.debugLineNum = 111;BA.debugLine="InitScrollView(\"H\", 10)";
_initscrollview("H",(int) (10));
 break;
case 9:
 //BA.debugLineNum = 113;BA.debugLine="InitScrollView(\"Q\", 14)";
_initscrollview("Q",(int) (14));
 break;
case 10:
 //BA.debugLineNum = 115;BA.debugLine="InitScrollView(\"I\",4)";
_initscrollview("I",(int) (4));
 break;
case 11:
 //BA.debugLineNum = 117;BA.debugLine="InitScrollView(\"J\", 3)";
_initscrollview("J",(int) (3));
 break;
case 12:
 //BA.debugLineNum = 119;BA.debugLine="InitScrollView(\"K\", 4)";
_initscrollview("K",(int) (4));
 break;
case 13:
 //BA.debugLineNum = 121;BA.debugLine="InitScrollView(\"L\", 7)";
_initscrollview("L",(int) (7));
 break;
case 14:
 //BA.debugLineNum = 123;BA.debugLine="InitScrollView(\"M\",2)";
_initscrollview("M",(int) (2));
 break;
case 15:
 //BA.debugLineNum = 125;BA.debugLine="InitScrollView(\"N\", 3)";
_initscrollview("N",(int) (3));
 break;
case 16:
 //BA.debugLineNum = 127;BA.debugLine="InitScrollView(\"O\",23)";
_initscrollview("O",(int) (23));
 break;
case 17:
 //BA.debugLineNum = 129;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 130;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://play";
_i.Initialize(_i.ACTION_VIEW,"https://play.google.com/store/apps/details?id=com.serogen.sbsrifmcpe_new");
 //BA.debugLineNum = 131;BA.debugLine="StartActivity(i)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_i.getObject()));
 break;
}
;
 }else {
 //BA.debugLineNum = 134;BA.debugLine="Select SelectedItem";
switch (BA.switchObjectToInt(_selecteditem,(Object)(1),(Object)(2),(Object)(3),(Object)(4),(Object)(5),(Object)(6),(Object)(7),(Object)(8),(Object)(9),(Object)(10),(Object)(11),(Object)(12),(Object)(13),(Object)(14),(Object)(15),(Object)(16),(Object)(17),(Object)(18))) {
case 0:
 //BA.debugLineNum = 136;BA.debugLine="InitScrollView(\"A\", 21)";
_initscrollview("A",(int) (21));
 break;
case 1:
 //BA.debugLineNum = 138;BA.debugLine="InitScrollView(\"B\", 25)";
_initscrollview("B",(int) (25));
 break;
case 2:
 //BA.debugLineNum = 140;BA.debugLine="InitScrollView(\"C\", 21)";
_initscrollview("C",(int) (21));
 break;
case 3:
 //BA.debugLineNum = 142;BA.debugLine="InitScrollView(\"Z\", 36)";
_initscrollview("Z",(int) (36));
 break;
case 4:
 //BA.debugLineNum = 144;BA.debugLine="InitScrollView(\"D\",18)";
_initscrollview("D",(int) (18));
 break;
case 5:
 //BA.debugLineNum = 146;BA.debugLine="InitScrollView(\"E\", 21)";
_initscrollview("E",(int) (21));
 break;
case 6:
 //BA.debugLineNum = 148;BA.debugLine="InitScrollView(\"F\",11)";
_initscrollview("F",(int) (11));
 break;
case 7:
 //BA.debugLineNum = 150;BA.debugLine="InitScrollView(\"G\",32)";
_initscrollview("G",(int) (32));
 break;
case 8:
 //BA.debugLineNum = 152;BA.debugLine="InitScrollView(\"H\", 10)";
_initscrollview("H",(int) (10));
 break;
case 9:
 //BA.debugLineNum = 154;BA.debugLine="InitScrollView(\"Q\", 14)";
_initscrollview("Q",(int) (14));
 break;
case 10:
 //BA.debugLineNum = 156;BA.debugLine="InitScrollView(\"I\",4)";
_initscrollview("I",(int) (4));
 break;
case 11:
 //BA.debugLineNum = 158;BA.debugLine="InitScrollView(\"J\", 3)";
_initscrollview("J",(int) (3));
 break;
case 12:
 //BA.debugLineNum = 160;BA.debugLine="InitScrollView(\"K\", 4)";
_initscrollview("K",(int) (4));
 break;
case 13:
 //BA.debugLineNum = 162;BA.debugLine="InitScrollView(\"L\", 7)";
_initscrollview("L",(int) (7));
 break;
case 14:
 //BA.debugLineNum = 164;BA.debugLine="InitScrollView(\"M\",2)";
_initscrollview("M",(int) (2));
 break;
case 15:
 //BA.debugLineNum = 166;BA.debugLine="InitScrollView(\"N\", 3)";
_initscrollview("N",(int) (3));
 break;
case 16:
 //BA.debugLineNum = 168;BA.debugLine="InitScrollView(\"O\",23)";
_initscrollview("O",(int) (23));
 break;
case 17:
 //BA.debugLineNum = 170;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 171;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://play";
_i.Initialize(_i.ACTION_VIEW,"https://play.google.com/store/apps/details?id=com.serogen.sbsrifmcpe_new");
 //BA.debugLineNum = 172;BA.debugLine="StartActivity(i)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_i.getObject()));
 break;
}
;
 };
 //BA.debugLineNum = 176;BA.debugLine="End Sub";
return "";
}
public static String  _buildmenu() throws Exception{
 //BA.debugLineNum = 279;BA.debugLine="Sub BuildMenu";
 //BA.debugLineNum = 281;BA.debugLine="njMenu.Initialize(Activity, Me, \"\", \"AnimatedMenu";
mostCurrent._njmenu._initialize(mostCurrent.activityBA,mostCurrent._activity,main.getObject(),"","AnimatedMenu","T",(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.Colors.Transparent,mostCurrent._scin);
 //BA.debugLineNum = 282;BA.debugLine="If GetDefaultLanguage=\"en\" Then";
if ((_getdefaultlanguage()).equals("en")) { 
 //BA.debugLineNum = 283;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"A21.pn";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"A21.png"),"House 1",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (1));
 //BA.debugLineNum = 284;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"B23.pn";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"B23.png"),"Modern house",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (2));
 //BA.debugLineNum = 285;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"C21.pn";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"C21.png"),"Treehouse",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (3));
 //BA.debugLineNum = 286;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"Z36.pn";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Z36.png"),"Suburban house",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (4));
 //BA.debugLineNum = 287;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"D18.pn";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"D18.png"),"Medieval house 1",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (5));
 //BA.debugLineNum = 288;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"E21.pn";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"E21.png"),"Fountain 1",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (6));
 //BA.debugLineNum = 289;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"F11.pn";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"F11.png"),"Well",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (7));
 //BA.debugLineNum = 290;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"G32.pn";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"G32.png"),"Medieval house 2",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (8));
 //BA.debugLineNum = 291;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"H10.pn";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"H10.png"),"Fountain 2",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (9));
 //BA.debugLineNum = 292;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"Q14.pn";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Q14.png"),"Small house",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (10));
 //BA.debugLineNum = 293;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"I4.png";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"I4.png"),"Armchair",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (11));
 //BA.debugLineNum = 294;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"J3.png";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"J3.png"),"Piano",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (12));
 //BA.debugLineNum = 295;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"K4.png";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"K4.png"),"Beds",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (13));
 //BA.debugLineNum = 296;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"L7.png";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"L7.png"),"Computer table",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (14));
 //BA.debugLineNum = 297;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"M2.png";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"M2.png"),"Cart",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (15));
 //BA.debugLineNum = 298;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"N3.png";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"N3.png"),"Fireplace",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (16));
 //BA.debugLineNum = 299;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"O23.pn";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"O23.png"),"Lighthouse",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (17));
 //BA.debugLineNum = 300;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"Redsto";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Redstone.png"),"Redstone schemes",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (18));
 }else {
 //BA.debugLineNum = 302;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"A21.p";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"A21.png"),"Дом 1",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (1));
 //BA.debugLineNum = 303;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"B23.pn";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"B23.png"),"Модерн хаус",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (2));
 //BA.debugLineNum = 304;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"C21.pn";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"C21.png"),"Дом на дереве",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (3));
 //BA.debugLineNum = 305;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"Z36.pn";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Z36.png"),"Дачный дом",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (4));
 //BA.debugLineNum = 306;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"D18.pn";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"D18.png"),"Средневековый дом 1",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (5));
 //BA.debugLineNum = 307;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"E21.pn";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"E21.png"),"Фонтан 1",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (6));
 //BA.debugLineNum = 308;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"F11.pn";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"F11.png"),"Колодец",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (7));
 //BA.debugLineNum = 309;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"G32.pn";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"G32.png"),"Средневековый дом 2",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (8));
 //BA.debugLineNum = 310;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"H10.pn";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"H10.png"),"Фонтан 2",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (9));
 //BA.debugLineNum = 311;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"Q14.pn";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Q14.png"),"Небольшой дом",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (10));
 //BA.debugLineNum = 312;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"I4.png";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"I4.png"),"Кресло",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (11));
 //BA.debugLineNum = 313;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"J3.png";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"J3.png"),"Рояль",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (12));
 //BA.debugLineNum = 314;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"K4.png";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"K4.png"),"Кровати",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (13));
 //BA.debugLineNum = 315;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"L7.png";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"L7.png"),"Компьютерный стол",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (14));
 //BA.debugLineNum = 316;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"M2.png";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"M2.png"),"Телега",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (15));
 //BA.debugLineNum = 317;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"N3.png";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"N3.png"),"Камин",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (16));
 //BA.debugLineNum = 318;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"O23.pn";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"O23.png"),"Маяк",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (17));
 //BA.debugLineNum = 319;BA.debugLine="njMenu.AddItem(LoadBitmap(File.DirAssets, \"Redsto";
mostCurrent._njmenu._additem(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Redstone.png"),"Редстоун схемы",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (18));
 };
 //BA.debugLineNum = 321;BA.debugLine="End Sub";
return "";
}
public static String  _getdefaultlanguage() throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
 //BA.debugLineNum = 372;BA.debugLine="Sub GetDefaultLanguage As String";
 //BA.debugLineNum = 373;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 374;BA.debugLine="r.Target=r.RunStaticMethod(\"java.util.Locale\",\"ge";
_r.Target = _r.RunStaticMethod("java.util.Locale","getDefault",(Object[])(anywheresoftware.b4a.keywords.Common.Null),(String[])(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 375;BA.debugLine="Return r.RunMethod(\"getLanguage\")";
if (true) return BA.ObjectToString(_r.RunMethod("getLanguage"));
 //BA.debugLineNum = 376;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 44;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 45;BA.debugLine="Dim Appodeal As AppodealB4A";
mostCurrent._appodeal = new com.appodeal.basic4android.AppodealB4A();
 //BA.debugLineNum = 46;BA.debugLine="Dim appKey As String = \"93aef72b421788b5f55095fd2";
mostCurrent._appkey = "93aef72b421788b5f55095fd26319c22b6ddca464840a628";
 //BA.debugLineNum = 47;BA.debugLine="Dim ActBarText As String";
mostCurrent._actbartext = "";
 //BA.debugLineNum = 48;BA.debugLine="Dim ActBarTextColor As Int = Colors.White";
_actbartextcolor = anywheresoftware.b4a.keywords.Common.Colors.White;
 //BA.debugLineNum = 49;BA.debugLine="Dim ActBarcolor As Int = Colors.RGB(0,0,80)";
_actbarcolor = anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (0),(int) (80));
 //BA.debugLineNum = 50;BA.debugLine="Dim ActBarLineColor As Int = Colors.Transparent";
_actbarlinecolor = anywheresoftware.b4a.keywords.Common.Colors.Transparent;
 //BA.debugLineNum = 51;BA.debugLine="Dim ActBarCornerRadius As Int = 0dip";
_actbarcornerradius = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0));
 //BA.debugLineNum = 52;BA.debugLine="Dim MenuWidth As Int = 250dip";
_menuwidth = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250));
 //BA.debugLineNum = 53;BA.debugLine="Dim MenuFullLength As Boolean = True";
_menufulllength = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 54;BA.debugLine="Dim MenuColor As Int = Colors.RGB(0,0,80)";
_menucolor = anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (0),(int) (80));
 //BA.debugLineNum = 55;BA.debugLine="Dim MenuTextColor As Int = Colors.White";
_menutextcolor = anywheresoftware.b4a.keywords.Common.Colors.White;
 //BA.debugLineNum = 56;BA.debugLine="Dim MenuTextSize As Int = 20";
_menutextsize = (int) (20);
 //BA.debugLineNum = 57;BA.debugLine="Dim ShowSlideButton As Boolean = True";
_showslidebutton = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 58;BA.debugLine="Dim SlideDuration As Int = 200";
_slideduration = (int) (200);
 //BA.debugLineNum = 59;BA.debugLine="Dim FadeAlphaShade As Int = 125";
_fadealphashade = (int) (125);
 //BA.debugLineNum = 61;BA.debugLine="Dim scvImages As ScrollView";
mostCurrent._scvimages = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 62;BA.debugLine="Dim imvImage As ImageView";
mostCurrent._imvimage = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 63;BA.debugLine="Dim imvLabel As Label";
mostCurrent._imvlabel = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 64;BA.debugLine="Dim bmpImage As Bitmap";
mostCurrent._bmpimage = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 65;BA.debugLine="Dim imgWidth As Int			: imgWidth=100%x		' image w";
_imgwidth = 0;
 //BA.debugLineNum = 65;BA.debugLine="Dim imgWidth As Int			: imgWidth=100%x		' image w";
_imgwidth = anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA);
 //BA.debugLineNum = 66;BA.debugLine="Dim imgHeight As Int		: imgHeight=75%x";
_imgheight = 0;
 //BA.debugLineNum = 66;BA.debugLine="Dim imgHeight As Int		: imgHeight=75%x";
_imgheight = anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (75),mostCurrent.activityBA);
 //BA.debugLineNum = 67;BA.debugLine="Dim lblHeight As Int		: lblHeight=25%x";
_lblheight = 0;
 //BA.debugLineNum = 67;BA.debugLine="Dim lblHeight As Int		: lblHeight=25%x";
_lblheight = anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (25),mostCurrent.activityBA);
 //BA.debugLineNum = 68;BA.debugLine="Dim imgSpace As Int			: imgSpace=5dip			' space b";
_imgspace = 0;
 //BA.debugLineNum = 68;BA.debugLine="Dim imgSpace As Int			: imgSpace=5dip			' space b";
_imgspace = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5));
 //BA.debugLineNum = 69;BA.debugLine="Dim fullscreen As ImageView";
mostCurrent._fullscreen = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 70;BA.debugLine="Dim ImageView1 As ImageView";
mostCurrent._imageview1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 71;BA.debugLine="Dim Label1 As Label";
mostCurrent._label1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 72;BA.debugLine="Dim maxs As Int=100%x/36";
_maxs = (int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)/(double)36);
 //BA.debugLineNum = 73;BA.debugLine="Dim bykva As String";
mostCurrent._bykva = "";
 //BA.debugLineNum = 74;BA.debugLine="Dim adtime As Boolean";
_adtime = false;
 //BA.debugLineNum = 76;BA.debugLine="Dim njMenu As AnimatedSlidingMenu";
mostCurrent._njmenu = new com.serogen.sbsifmc.animatedslidingmenu();
 //BA.debugLineNum = 77;BA.debugLine="Dim Icon As Bitmap";
mostCurrent._icon = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 78;BA.debugLine="Dim Scin As ColorDrawable";
mostCurrent._scin = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 79;BA.debugLine="Dim menu_btn As Button";
mostCurrent._menu_btn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 80;BA.debugLine="Dim Instruction_is_open As Boolean = False";
_instruction_is_open = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 81;BA.debugLine="End Sub";
return "";
}
public static String  _imvimage_click() throws Exception{
anywheresoftware.b4a.objects.ConcreteViewWrapper _send = null;
anywheresoftware.b4a.objects.IntentWrapper _i = null;
anywheresoftware.b4a.objects.collections.List _filestodelete = null;
int _j = 0;
 //BA.debugLineNum = 254;BA.debugLine="Sub imvImage_Click";
 //BA.debugLineNum = 255;BA.debugLine="Dim Send As View";
_send = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
 //BA.debugLineNum = 256;BA.debugLine="Send=Sender";
_send.setObject((android.view.View)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 257;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 258;BA.debugLine="If adtime And Appodeal.isLoaded(Appodeal.INTERSTIT";
if (_adtime && mostCurrent._appodeal.isLoaded(mostCurrent.activityBA,mostCurrent._appodeal.INTERSTITIAL)) { 
 //BA.debugLineNum = 259;BA.debugLine="Appodeal.showWithPlacement(Appodeal.INTERSTITIAL,";
mostCurrent._appodeal.showWithPlacement(mostCurrent.activityBA,mostCurrent._appodeal.INTERSTITIAL,"level_end");
 };
 //BA.debugLineNum = 261;BA.debugLine="adtime=Not(adtime)";
_adtime = anywheresoftware.b4a.keywords.Common.Not(_adtime);
 //BA.debugLineNum = 262;BA.debugLine="Dim FilesToDelete As List";
_filestodelete = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 263;BA.debugLine="FilesToDelete.Initialize";
_filestodelete.Initialize();
 //BA.debugLineNum = 264;BA.debugLine="FilesToDelete.AddAll(File.ListFiles(File.DirDefaul";
_filestodelete.AddAll(anywheresoftware.b4a.keywords.Common.File.ListFiles(anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal()));
 //BA.debugLineNum = 265;BA.debugLine="For j = 0 To FilesToDelete.Size -1";
{
final int step209 = 1;
final int limit209 = (int) (_filestodelete.getSize()-1);
for (_j = (int) (0); (step209 > 0 && _j <= limit209) || (step209 < 0 && _j >= limit209); _j = ((int)(0 + _j + step209))) {
 //BA.debugLineNum = 266;BA.debugLine="File.Delete(File.DirDefaultExternal, FilesToDe";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal(),BA.ObjectToString(_filestodelete.Get(_j)));
 }
};
 //BA.debugLineNum = 268;BA.debugLine="If File.Exists(File.DirDefaultExternal, bykva&S";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal(),mostCurrent._bykva+BA.ObjectToString(_send.getTag())+".jpg")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 269;BA.debugLine="File.Copy(File.DirAssets, bykva&Send.Tag&\".j";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),mostCurrent._bykva+BA.ObjectToString(_send.getTag())+".jpg",anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal(),mostCurrent._bykva+BA.ObjectToString(_send.getTag())+".jpg");
 };
 //BA.debugLineNum = 271;BA.debugLine="i.Initialize(i.ACTION_VIEW,\"file://\"&File.DirDefau";
_i.Initialize(_i.ACTION_VIEW,"file://"+anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal()+"/"+mostCurrent._bykva+BA.ObjectToString(_send.getTag())+".jpg");
 //BA.debugLineNum = 272;BA.debugLine="i.SetType(\"image/*\")";
_i.SetType("image/*");
 //BA.debugLineNum = 273;BA.debugLine="StartActivity(i)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_i.getObject()));
 //BA.debugLineNum = 274;BA.debugLine="Log(\"error:\"&I)";
anywheresoftware.b4a.keywords.Common.Log("error:"+BA.ObjectToString(_i));
 //BA.debugLineNum = 275;BA.debugLine="End Sub";
return "";
}
public static String  _initscrollview(String _str,int _nom) throws Exception{
int _i = 0;
anywheresoftware.b4a.objects.streams.File.TextReaderWrapper _textreader1 = null;
 //BA.debugLineNum = 213;BA.debugLine="Sub InitScrollView(str As String, nom As Int)";
 //BA.debugLineNum = 214;BA.debugLine="Dim i As Int";
_i = 0;
 //BA.debugLineNum = 215;BA.debugLine="Dim TextReader1 As TextReader";
_textreader1 = new anywheresoftware.b4a.objects.streams.File.TextReaderWrapper();
 //BA.debugLineNum = 216;BA.debugLine="bykva=str";
mostCurrent._bykva = _str;
 //BA.debugLineNum = 217;BA.debugLine="scvImages.Panel.RemoveAllViews";
mostCurrent._scvimages.getPanel().RemoveAllViews();
 //BA.debugLineNum = 218;BA.debugLine="Instruction_is_open = True";
_instruction_is_open = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 219;BA.debugLine="If GetDefaultLanguage=\"en\" Then";
if ((_getdefaultlanguage()).equals("en")) { 
 //BA.debugLineNum = 220;BA.debugLine="TextReader1.Initialize(File.OpenInput(File.Dir";
_textreader1.Initialize((java.io.InputStream)(anywheresoftware.b4a.keywords.Common.File.OpenInput(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),_str+"_eng.txt").getObject()));
 }else {
 //BA.debugLineNum = 222;BA.debugLine="TextReader1.Initialize(File.OpenInput(File.DirAss";
_textreader1.Initialize((java.io.InputStream)(anywheresoftware.b4a.keywords.Common.File.OpenInput(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),_str+".txt").getObject()));
 };
 //BA.debugLineNum = 224;BA.debugLine="fullscreen.Initialize(Null)";
mostCurrent._fullscreen.Initialize(mostCurrent.activityBA,BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 225;BA.debugLine="fullscreen.Gravity=Gravity.FILL";
mostCurrent._fullscreen.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 //BA.debugLineNum = 226;BA.debugLine="scvImages.Visible=True";
mostCurrent._scvimages.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 227;BA.debugLine="For i=0 To nom";
{
final int step174 = 1;
final int limit174 = _nom;
for (_i = (int) (0); (step174 > 0 && _i <= limit174) || (step174 < 0 && _i >= limit174); _i = ((int)(0 + _i + step174))) {
 //BA.debugLineNum = 228;BA.debugLine="Dim imvImage As ImageView";
mostCurrent._imvimage = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 229;BA.debugLine="Dim bmpImage As Bitmap";
mostCurrent._bmpimage = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 230;BA.debugLine="bmpImage.Initialize(File.DirAssets, str&i&\".jpg\"";
mostCurrent._bmpimage.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),_str+BA.NumberToString(_i)+".jpg");
 //BA.debugLineNum = 233;BA.debugLine="imvImage.Initialize(\"imvImage\")";
mostCurrent._imvimage.Initialize(mostCurrent.activityBA,"imvImage");
 //BA.debugLineNum = 234;BA.debugLine="imvLabel.Initialize(\"imvLabel\")";
mostCurrent._imvlabel.Initialize(mostCurrent.activityBA,"imvLabel");
 //BA.debugLineNum = 235;BA.debugLine="imvLabel.Text=TextReader1.ReadLine";
mostCurrent._imvlabel.setText((Object)(_textreader1.ReadLine()));
 //BA.debugLineNum = 236;BA.debugLine="imvLabel.TextColor=Colors.White";
mostCurrent._imvlabel.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 237;BA.debugLine="imvLabel.Color = Colors.RGB(5,0,80)";
mostCurrent._imvlabel.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (5),(int) (0),(int) (80)));
 //BA.debugLineNum = 238;BA.debugLine="imvImage.Gravity=Gravity.FILL";
mostCurrent._imvimage.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 //BA.debugLineNum = 239;BA.debugLine="imvImage.Tag=i";
mostCurrent._imvimage.setTag((Object)(_i));
 //BA.debugLineNum = 240;BA.debugLine="imvImage.Bitmap=bmpImage";
mostCurrent._imvimage.setBitmap((android.graphics.Bitmap)(mostCurrent._bmpimage.getObject()));
 //BA.debugLineNum = 241;BA.debugLine="scvImages.Panel.AddView(imvImage,0,i*(imgHeight+";
mostCurrent._scvimages.getPanel().AddView((android.view.View)(mostCurrent._imvimage.getObject()),(int) (0),(int) (_i*(_imgheight+_imgspace+_lblheight)+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40))),_imgwidth,_imgheight);
 //BA.debugLineNum = 242;BA.debugLine="scvImages.Panel.AddView(imvLabel,0,imvImage.Top+";
mostCurrent._scvimages.getPanel().AddView((android.view.View)(mostCurrent._imvlabel.getObject()),(int) (0),(int) (mostCurrent._imvimage.getTop()+_imgheight),_imgwidth,_lblheight);
 //BA.debugLineNum = 243;BA.debugLine="SetLabelTextSize(imvLabel,imvLabel.Text, maxs,1)";
_setlabeltextsize(mostCurrent._imvlabel,mostCurrent._imvlabel.getText(),(float) (_maxs),(float) (1));
 }
};
 //BA.debugLineNum = 245;BA.debugLine="scvImages.ScrollPosition=0";
mostCurrent._scvimages.setScrollPosition((int) (0));
 //BA.debugLineNum = 246;BA.debugLine="scvImages.Panel.Height=(nom+1)*(imgHeight+imgSpac";
mostCurrent._scvimages.getPanel().setHeight((int) ((_nom+1)*(_imgheight+_imgspace+_lblheight)+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40))));
 //BA.debugLineNum = 247;BA.debugLine="scvImages.Left=0";
mostCurrent._scvimages.setLeft((int) (0));
 //BA.debugLineNum = 248;BA.debugLine="scvImages.Top=15%y";
mostCurrent._scvimages.setTop(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (15),mostCurrent.activityBA));
 //BA.debugLineNum = 249;BA.debugLine="scvImages.Width=100%x";
mostCurrent._scvimages.setWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA));
 //BA.debugLineNum = 250;BA.debugLine="scvImages.Height=85%y-90dip";
mostCurrent._scvimages.setHeight((int) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (90))));
 //BA.debugLineNum = 251;BA.debugLine="TextReader1.Close";
_textreader1.Close();
 //BA.debugLineNum = 252;BA.debugLine="End Sub";
return "";
}
public static String  _menu_btn_click() throws Exception{
 //BA.debugLineNum = 381;BA.debugLine="Sub menu_btn_Click";
 //BA.debugLineNum = 382;BA.debugLine="njMenu.OpenMenu(\"Cascade\")";
mostCurrent._njmenu._openmenu("Cascade");
 //BA.debugLineNum = 383;BA.debugLine="menu_btn.SendToBack";
mostCurrent._menu_btn.SendToBack();
 //BA.debugLineNum = 384;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main._process_globals();
starter._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 38;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 42;BA.debugLine="End Sub";
return "";
}
public static String  _setlabeltextsize(anywheresoftware.b4a.objects.LabelWrapper _lbl2,String _txt2,float _maxfontsize,float _minfontsize) throws Exception{
float _fontsize = 0f;
int _height = 0;
anywheresoftware.b4a.objects.StringUtils _stu = null;
 //BA.debugLineNum = 358;BA.debugLine="Sub SetLabelTextSize(lbl2 As Label, txt2 As String";
 //BA.debugLineNum = 359;BA.debugLine="Dim FontSize = MaxFontSize As Float";
_fontsize = _maxfontsize;
 //BA.debugLineNum = 360;BA.debugLine="Dim Height As Int";
_height = 0;
 //BA.debugLineNum = 361;BA.debugLine="Dim stu As StringUtils";
_stu = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 363;BA.debugLine="lbl2.TextSize = FontSize";
_lbl2.setTextSize(_fontsize);
 //BA.debugLineNum = 364;BA.debugLine="Height = stu.MeasureMultilineTextHeight(lbl2,";
_height = _stu.MeasureMultilineTextHeight((android.widget.TextView)(_lbl2.getObject()),_txt2);
 //BA.debugLineNum = 365;BA.debugLine="Do While Height > lbl2.Height And FontSize > M";
while (_height>_lbl2.getHeight() && _fontsize>_minfontsize) {
 //BA.debugLineNum = 366;BA.debugLine="FontSize = FontSize - 1";
_fontsize = (float) (_fontsize-1);
 //BA.debugLineNum = 367;BA.debugLine="lbl2.TextSize = FontSize";
_lbl2.setTextSize(_fontsize);
 //BA.debugLineNum = 368;BA.debugLine="Height = stu.MeasureMultilineTextHeight(lb";
_height = _stu.MeasureMultilineTextHeight((android.widget.TextView)(_lbl2.getObject()),_txt2);
 }
;
 //BA.debugLineNum = 370;BA.debugLine="End Sub";
return "";
}
}
