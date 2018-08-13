Type=Class
Version=5.2
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
'Class module

'Animated Sliding Menu Plus
'By NJDude - Version 1.2
'Donations are appreciated.

Private Sub Class_Globals

				    Private p As Phone
				
				    'We create a timer to perform the animations.
				    Private TweenUpdate As Timer
				
	       'Declaration of libraries
				    Private MyTween(5) As Tween
	       Private Manager As TweenManager
	       Private Constants As TweenConstants
	       Private Callback As TweenCallback				

				    Private FakePanel As Panel
				    Private MenuPanel As ScrollView
				    Private MenuTitle As Label
				    Private MenuItem(51) As ListView
				
				    Private mModule As Object
				    Private mEventName As String
				    Private mLocation As String
				    Private mWidth As Int
				    Private mTop As Int

				    Private ItemsInMenu As Int
				    Private TitlePad1 As Int
				    Private TitlePad2 As Int
				
				    Public IsOpen As Boolean
					
End Sub

'Activity = Activity (mandatory)
'Module = Me (mandatory)
'EventName = Event that will trigger when clicking on a menu item
'Title = Menu title
'MenuLocation = Position of the menu on the screen (L = Left, R = Right, T = Top, B = Bottom)
'MenuTop = Menu position (in % or dip)
'MenuWidth = Menu's width (in % or dip)
'MenuColor = Background color for the menu panel
'MenuBackgroundBitmap = Backgrond image
Public Sub Initialize(Activity As Activity, Module As Object, Title As String, EventName As String, MenuLocation As String, MenuTop As Int, MenuWidth As Int, MenuColor As Int, MenuBackgroundBitmap As ColorDrawable)

							mModule = Module
							mEventName = EventName
							mLocation = MenuLocation.ToUpperCase.Trim
							mWidth = MenuWidth
							mTop = MenuTop

							Manager.Initialize

							FakePanel.Initialize("FakePanel")
							
							MenuPanel.Initialize(100%y - MenuTop)
							MenuPanel.Color = MenuColor
							
							MenuTitle.Initialize("")
							
							If MenuBackgroundBitmap.IsInitialized Then
							
							   MenuPanel.Background = MenuBackgroundBitmap
										
							End If
							
							Activity.AddView(FakePanel, 0dip, MenuTop, 100%x, 100%y - MenuTop)
							Activity.AddView(MenuPanel, 0dip, 0dip, 0dip, 0dip)

       If Title.Trim <> "" Or Title.Trim = "*" Then

          MenuPanel.Panel.AddView(MenuTitle, 0dip, 0dip, MenuPanel.Panel.Width, 30dip)
										
          If Title.Trim <> "*" Then	
										
										   MenuTitle.Text = " " & Title.Trim & " "
													
										End If
										
          MenuTitle.Color = Colors.White
          MenuTitle.TextColor = Colors.Black
										MenuTitle.TextSize = 18
										MenuTitle.Gravity = Gravity.CENTER_HORIZONTAL

										TitlePad1 = 31dip
										TitlePad2 = 21dip
       
							Else

										TitlePad1 = 0dip
          TitlePad2 = 51dip
       
							End If

							MenuPanel.Panel.Color = MenuColor
							FakePanel.Visible = False

End Sub

Public Sub SetTitle(TitleBackgroundColor As Int, TextColor As Int, Centering As Int, TitleBackgroundBitmap As Bitmap)

       MenuTitle.Color = TitleBackgroundColor
       MenuTitle.TextColor = TextColor
							MenuTitle.Gravity = Centering
							
							If TitleBackgroundBitmap.IsInitialized Then
							
							   MenuTitle.SetBackgroundImage(TitleBackgroundBitmap)
										
							End If							
										
End Sub

'Icon = Icon for the item
'Text = Item text
'TextColor = Color of the text
'BackgroundColor = Color of the background
'Index = Item number
Public Sub AddItem(Icon As Bitmap, Text As String, TextColor As Int, BackgroundColor As Int, Index As Int)

							MenuItem(Index).Initialize("MenuItem")

	      MenuItem(Index).ScrollingBackgroundColor = Colors.Transparent
							MenuItem(Index).TwoLinesAndBitmap.SecondLabel.Visible = False
	      MenuItem(Index).TwoLinesAndBitmap.ItemHeight = 50dip
				   MenuItem(Index).TwoLinesAndBitmap.Label.TextColor = TextColor
	      MenuItem(Index).TwoLinesAndBitmap.Label.Gravity = Gravity.CENTER_VERTICAL
	      MenuItem(Index).TwoLinesAndBitmap.Label.Height = MenuItem(Index).TwoLinesAndBitmap.ItemHeight
	      MenuItem(Index).TwoLinesAndBitmap.Label.Top = 0dip
							
							If Icon.IsInitialized Then
				   
							   MenuItem(Index).TwoLinesAndBitmap.Label.Left = 45dip
										MenuItem(Index).AddTwoLinesAndBitmap(Text, "", Icon)
							
							Else
							
							   MenuItem(Index).TwoLinesAndBitmap.Label.Left = 10dip
										MenuItem(Index).AddTwoLinesAndBitmap(Text, "", Null)
							
							End If
	      
							MenuItem(Index).TwoLinesAndBitmap.ImageView.SetLayout(12dip, 12dip, 25dip, 25dip)
	      MenuItem(Index).Color = BackgroundColor
				   MenuItem(Index).Tag = Index	

							MenuPanel.Panel.AddView(MenuItem(Index), 0dip, (Index * 51dip) - TitlePad2, 100%x, 51dip)
						 MenuPanel.Panel.Height = (Index *	51dip) + TitlePad1
							
							ItemsInMenu = ItemsInMenu + 1
				
End Sub

'Default
'Casino
'CasinoReverse
'ShootUp
'Cascade
'Flip
'Fade
'ZigZag
Public Sub OpenMenu(Animation As String)

							IsOpen = True

       MenuPanel.ScrollPosition = 0
							
							MenuPanel.Visible = True
							FakePanel.Visible = True
														
							Select Case mLocation
							
							       Case "L"

																	  MenuPanel.SetLayout(-mWidth, mTop, mWidth, 100%y - mTop)

																			xPos = 0dip
																			yPos = mTop
																			
														Case "R"

																			MenuPanel.SetLayout(100%x + mWidth, mTop, mWidth, 100%y - mTop)

																			xPos = 100%x - mWidth
																			yPos = mTop
																			
														Case "T"
													
																			If MenuPanel.Panel.Height > 50%y Then

																						MenuPanel.SetLayout(0dip, -50%y, 100%x, 50%y - mTop)

																						xPos = 0dip
																						yPos = mTop

							            Else

																						MenuPanel.SetLayout(0dip, -MenuPanel.Panel.Height, 100%x, MenuPanel.Panel.Height)

																						xPos = 0dip
																						yPos = mTop

							            End If

														Case "B"

																			If MenuPanel.Panel.Height > 50%y Then

																						MenuPanel.SetLayout(0dip, 100%y + MenuPanel.Height, 100%x, 50%y)

																						xPos = 0dip
																						yPos = 50%y

																			Else
																			
																			   MenuPanel.SetLayout(0dip, 100%y + MenuPanel.Panel.Height, 100%x, MenuPanel.Panel.Height)

																						xPos = 0dip
																						yPos = 100%y - MenuPanel.Height
																						
																			End If
							
							End Select	
							
							Select Case Animation

														Case "Default"
														
														     AnimDefault(xPos, yPos)
																			
														Case "Casino"
														
														     AnimCasino(xPos, yPos)
																			
														Case "CasinoReverse"
														
														     AnimCasinoReverse(xPos, yPos)
																			
														Case "ShootUp"
														
														     AnimShootUp(xPos, yPos)
																			
														Case "Cascade"
														
														     AnimCascade(xPos, yPos)
																			
														Case "Flip"
														
														     If p.SdkVersion < 11 Then
																			
																			   AnimDefault(xPos, yPos)
																			
																			Else
																			
																			   AnimFlip(xPos, yPos, mLocation)
																			
																			End If
																			
														Case	"Fade"
														
														     If p.SdkVersion < 11 Then
																			
																			   AnimDefault(xPos, yPos)
																			
																			Else
																			
																			   AnimFade(xPos, yPos)
																			
																			End If		
																			
														Case "ZigZag"
														
														     AnimZigZag(xPos, yPos)

							End Select

       'Start the timer.
				   TweenUpdate.Initialize("TweenUpdate", 10)
 			   TweenUpdate.Enabled = True
				
				   'Setup a callback in order to capture the end of the animation and stop the timer.
				   Callback.Initialize("TweenCallback")
				   Callback.Listener = True
				
       'After the last tween finishes, we activate the callback.
				   MyTween(1).Callback = Callback.TweenCallback
				
				   Manager.StartTween	

End Sub

Public Sub CloseMenu

							IsOpen = False
							
			    If p.SdkVersion < 11 Then
																			
							   FakePanel.Visible = False
										MenuPanel.Visible = False
							
										Return
							
							End If			
							
							FakePanel.Visible = False
							
							ResetTween("Panel", 2, MenuPanel)

 						If mLocation = "R" Then
							
							   AnimateTween("Panel", 1, MenuPanel, Constants.POSITION_XY, 100%x, MenuPanel.Top, Constants.BACK_INOUT, 1, 0)
							
							Else
							
							   AnimateTween("Panel", 1, MenuPanel, Constants.POSITION_XY, -MenuPanel.Width , MenuPanel.Top, Constants.BACK_INOUT, 1, 0)
							
							End If

       'Start the timer.
		 		  TweenUpdate.Initialize("TweenUpdate", 10)
 		 	  TweenUpdate.Enabled = True
				
			 	  'Setup a callback in order to capture the end of the animation and stop the timer.
				   Callback.Initialize("TweenCallback")
				   Callback.Listener = True
				
       'After the last tween finishes, we activate the callback.
				   MyTween(1).Callback = Callback.TweenCallback
				
				   Manager.StartTween	
							
End Sub

Private Sub MenuItem_ItemClick(Position As Int, Value As Object)

        Dim lvClicked As ListView
								
								IsOpen = False
								
								lvClicked = Sender

								FakePanel.Visible = False
								MenuPanel.Visible = False
        
								If SubExists(mModule, mEventName & "_Click") Then
											
								   CallSubDelayed2(mModule, mEventName & "_Click", lvClicked.Tag)

								End If	
								
End Sub

Private Sub FakePanel_Touch(Action As Int, X As Float, Y As Float)
			
		      CloseMenu

End Sub

'#########################################
'######## Menu Animation Routines ########
'#########################################

Private Sub AnimDefault(xPos As Int, yPos As Int)

        AnimateTween("Panel", 1, MenuPanel, Constants.POSITION_XY, xPos, yPos, Constants.BACK_INOUT, 2, 0)

End Sub

Private Sub AnimCasino(xPos As Int, yPos As Int)

								For I = 1 To ItemsInMenu
								
								    MenuItem(I).Left = -MenuItem(I).Width
								
								Next

								AnimateTween("Panel", 2, MenuPanel, Constants.POSITION_XY, xPos, yPos, Constants.BACK_INOUT, 2, 0)
								
								For I = 1 To ItemsInMenu

												AnimateTween("ListView", 1, MenuItem(I), Constants.POSITION_XY, 0, MenuItem(I).Top, Constants.BACK_INOUT, 2, I / 5)
								
								Next								

End Sub

Private Sub AnimCasinoReverse(xPos As Int, yPos As Int)
								
								For I = 1 To ItemsInMenu
								
								    MenuItem(I).Left = MenuPanel.Width
								
								Next
								
								AnimateTween("Panel", 2, MenuPanel, Constants.POSITION_XY, xPos, yPos, Constants.BACK_INOUT, 2, 0)
								
								For I = 1 To ItemsInMenu

												AnimateTween("ListView", 1, MenuItem(I), Constants.POSITION_XY, 0, MenuItem(I).Top, Constants.BACK_INOUT, 2, I / 5)
								
								Next								

End Sub

Private Sub AnimShootUp(xPos As Int, yPos As Int)

								Dim Buffer(51) As Int

								For I = 1 To ItemsInMenu
								
								    Buffer(I) = MenuItem(I).Top
												MenuItem(I).Top = MenuPanel.Height + 50dip

								Next								
								
								AnimateTween("Panel", 2, MenuPanel, Constants.POSITION_XY, xPos, yPos, Constants.BACK_INOUT, 2, 0)
								
								For I = 1 To ItemsInMenu

												AnimateTween("ListView", 1, MenuItem(I), Constants.POSITION_XY, 0, Buffer(I), Constants.QUAD_OUT, 2, I / 5)
								
								Next	

End Sub

Private Sub AnimCascade(xPos As Int, yPos As Int)

								Dim Buffer(51) As Int

								For I = 1 To ItemsInMenu
								
								    Buffer(I) = MenuItem(I).Top
												MenuItem(I).Top = -MenuPanel.Height

								Next								
								
								AnimateTween("Panel", 2, MenuPanel, Constants.POSITION_XY, xPos, yPos, Constants.BACK_INOUT, 2, 0)
								
								For I = ItemsInMenu To 1 Step -1

												AnimateTween("ListView", 1, MenuItem(I), Constants.POSITION_XY, 0, Buffer(I), Constants.QUAD_OUT, 3, 1 / I)
								
								Next			

End Sub

Private Sub AnimFlip(xPos As Int, yPos As Int, Location As String)

        If Location = "T" OR Location = "B" Then
								
								   RotateTween("Panel", 1, MenuPanel, Constants.ROT_X, 180, 0, 0, 0, 0)
											
								Else
								
								   RotateTween("Panel", 1, MenuPanel, Constants.ROT_Y, 180, 0, 0, 0, 0)
											
								End If
								
								AnimateTween("Panel", 1, MenuPanel, Constants.POSITION_XY, xPos, yPos, Constants.BACK_INOUT, 2, 1)
								
								If Location = "T" OR Location = "B" Then
								
								   RotateTween("Panel", 1, MenuPanel, Constants.ROT_X, 0, .5, 0, 0, 3)
								
								Else
								
								   RotateTween("Panel", 1, MenuPanel, Constants.ROT_Y, 0, .5, 0, 0, 3)
								
								End If

End Sub

Private Sub AnimFade(xPos As Int, yPos As Int)

								For I = 1 To ItemsInMenu

												FadeTween("ListView", 1, MenuItem(I), 0, 0, 0)

								Next								
								
								AnimateTween("Panel", 2, MenuPanel, Constants.POSITION_XY, xPos, yPos, Constants.BACK_INOUT, 2, 0)
								
								For I = 1 To ItemsInMenu

												FadeTween("ListView", 1, MenuItem(I), 1, 2, I / 5)
								
								Next		
								
End Sub

Private Sub AnimZigZag(xPos As Int, yPos As Int)

								Dim Flag As Boolean
								
								Flag = True
								
								For I = 1 To ItemsInMenu

												If Flag Then

															MenuItem(I).Left = -MenuItem(I).Width
															Flag = False
															
												Else
												
												   MenuItem(I).Left = MenuPanel.Width
															Flag = True
															
												End If

								Next								
								
								AnimateTween("Panel", 2, MenuPanel, Constants.POSITION_XY, xPos, yPos, Constants.BACK_INOUT, 2, 0)
								
								For I = 1 To ItemsInMenu

												AnimateTween("ListView", 1, MenuItem(I), Constants.POSITION_XY, 0, MenuItem(I).Top, Constants.LINEAR_INOUT, 2, I / 5)
								
								Next	

End Sub

'##########################################################
'################ Tween Animation Routines ################
'##########################################################

Private Sub AnimateTween(ViewType As String, ID As Int, ViewName As Object, TweenConstant As Double, xPos As Int, yPos As Int, Easing As Object, Duration As Float, StartDelay As Float)

        MyTween(ID).Initialize("Tween")
				
				    Duration = Duration * 10 'It was said that the duration is in tenths of a second, here we convert seconds to tenths of a second for easy handling.
			    	StartDelay = StartDelay * 10

        Select Case ViewType

                    Case "Panel", "HorizontalScrollView", "ListView", "ScrollView", "Spinner", "TabHost", "WebView"										

	                        MyTween(ID).registerPanelAccessor 
	                        MyTween(ID).PanelTo(ViewName, TweenConstant, Duration)

                    Case "ImageView"		
	
	                        MyTween(ID).registerImageAccessor 
	                        MyTween(ID).ImageTo(ViewName, TweenConstant, Duration)
																					
                    Case "Label", "Button", "AutoCompleteEditText", "CheckBox", "EditText", "RadioButton", "ToggleButton"											
	
	                        MyTween(ID).registerLabelAccessor 
	                        MyTween(ID).LabelTo(ViewName, TweenConstant, Duration)	

				    End Select
				
				    MyTween(ID).setTarget(xPos, yPos)
	       MyTween(ID).Ease(Easing)
	       MyTween(ID).Delay = StartDelay
	   
				    Manager.addTween(MyTween(ID).Tween)

End Sub

Private Sub RotateTween(ViewType As String, ID As Int, ViewName As Object, TweenConstant As Double, Angle As Int, Duration As Float, Repeat As Int, RepeatDelay As Float, StartDelay As Float)

        MyTween(ID).Initialize("Tween")
				
				    Duration = Duration * 10
				    RepeatDelay = RepeatDelay * 10
				    StartDelay = StartDelay * 10
				
        Select Case ViewType

                    Case "Panel", "HorizontalScrollView", "ListView", "ScrollView", "Spinner", "TabHost", "WebView"										

	                        MyTween(ID).registerPanelAccessor 
	                        MyTween(ID).PanelTo(ViewName, TweenConstant, Duration)

                    Case "ImageView"		
	
	                        MyTween(ID).registerImageAccessor 
	                        MyTween(ID).ImageTo(ViewName, TweenConstant, Duration)
																					
                    Case "Label", "Button", "AutoCompleteEditText", "CheckBox", "EditText", "RadioButton", "ToggleButton"											
	
	                        MyTween(ID).registerLabelAccessor 
	                        MyTween(ID).LabelTo(ViewName, TweenConstant, Duration)	

				    End Select			

				    MyTween(ID).Repeat(Repeat, RepeatDelay)
				    MyTween(ID).setTarget(Angle, 0)
				    MyTween(ID).Delay = StartDelay

				    Manager.addTween(MyTween(ID).Tween)

End Sub

Private Sub FadeTween(ViewType As String, ID As Int, ViewName As Object, FadeType As Int, Duration As Float, StartDelay As Float)

        MyTween(ID).Initialize("Tween")
				
				    Duration = Duration * 10
				    StartDelay = StartDelay * 10
				
        Select Case ViewType

                    Case "Panel", "HorizontalScrollView", "ListView", "ScrollView", "Spinner", "TabHost", "WebView"										

	                        MyTween(ID).registerPanelAccessor 
																    					MyTween(ID).PanelTo(ViewName, Constants.ALPHA, Duration)

                    Case "ImageView"		
	
	                        MyTween(ID).registerImageAccessor 
																		    			MyTween(ID).ImageTo(ViewName, Constants.ALPHA, Duration)
																					
                    Case "Label", "Button", "AutoCompleteEditText", "CheckBox", "EditText", "RadioButton", "ToggleButton"											
	
	                        MyTween(ID).registerLabelAccessor 
																					    MyTween(ID).LabelTo(ViewName, Constants.ALPHA, Duration)

				    End Select			

				    MyTween(ID).setTarget(FadeType, 0)
				    MyTween(ID).Delay = StartDelay

				    Manager.addTween(MyTween(ID).Tween)

End Sub

Private Sub ScaleTween(ViewType As String, ID As Int, ViewName As Object, XAxis As Float, YAxis As Float, Duration As Float, Repeat As Int, RepeatDelay As Float, StartDelay As Float)

        MyTween(ID).Initialize("Tween")
				
				    Duration = Duration * 10
				    RepeatDelay = RepeatDelay * 10
				    StartDelay = StartDelay * 10
				
        Select Case ViewType

                    Case "Panel", "HorizontalScrollView", "ListView", "ScrollView", "Spinner", "TabHost", "WebView"										

	                        MyTween(ID).registerPanelAccessor 
																    					MyTween(ID).PanelTo(ViewName, Constants.SCALE_XY, Duration)

                    Case "ImageView"		
	
	                        MyTween(ID).registerImageAccessor 
																					    MyTween(ID).ImageTo(ViewName, Constants.SCALE_XY, Duration)
																					
                    Case "Label", "Button", "AutoCompleteEditText", "CheckBox", "EditText", "RadioButton", "ToggleButton"											
	
	                        MyTween(ID).registerLabelAccessor 
																					    MyTween(ID).LabelTo(ViewName, Constants.SCALE_XY, Duration)

				    End Select			

				    MyTween(ID).Repeat(Repeat, RepeatDelay)
				    MyTween(ID).setTarget(XAxis, YAxis)
				    MyTween(ID).Delay = StartDelay

				    Manager.addTween(MyTween(ID).Tween)

End Sub

Private Sub ResetTween(ViewType As String, ID As Int, ViewName As Object)

        Dim Constant() As Double
				    Dim ResetValues1() As Float
				    Dim ResetValues2() As Float
				
				    Constant = Array As Double(Constants.SCALE_XY, Constants.ALPHA, Constants.ROT_X, Constants.ROT_Y, Constants.ROT_Z)
				
				    ResetValues1 = Array As Float(1, 1, 0, 0, 0)
				    ResetValues2 = Array As Float(1, 0, 0, 0, 0)
				
				    Manager.Initialize
				
				    For I = 0 To Constant.Length - 1
		
    				    MyTween(ID).Initialize("Tween")
				
            Select Case ViewType

                        Case "Panel", "HorizontalScrollView", "ListView", "ScrollView", "Spinner", "TabHost", "WebView"										

	                            MyTween(ID).registerPanelAccessor 
				    												    					MyTween(ID).PanelTo(ViewName, Constant(I), 0)
    
                        Case "ImageView"		
	
	                            MyTween(ID).registerImageAccessor 
																    				    	MyTween(ID).ImageTo(ViewName, Constant(I), 0)
																					
                        Case "Label", "Button", "AutoCompleteEditText", "CheckBox", "EditText", "RadioButton", "ToggleButton"											
	
	                            MyTween(ID).registerLabelAccessor 
														    							    MyTween(ID).LabelTo(ViewName, Constant(I), 0)

				        End Select			

				        MyTween(ID).setTarget(ResetValues1(I), ResetValues2(I))		
				
		    		    Manager.addTween(MyTween(ID).Tween)
				        Manager.StartTween	
				
				        Manager.Update(.1)
    
				    Next

End Sub

Private Sub TweenCallback_OnEvent(Event As Int)

				    'When the last tween finishes (in this example the tween with ID = 4) it triggers a completion, so we stop the timer
				    'I found out that 8 means completion, not sure or aware of any other values.
				    If Event = 8 Then

				       TweenUpdate.Enabled = False

				    End If			
	
End Sub

Private Sub TweenUpdate_Tick 'Updates the tween.

				    Manager.Update(.5)
				
End Sub