                                        
//.oPYo. 8                                  
//8      8                                  
//`Yooo. 8oPYo. .oPYo. .oPYo. .oPYo. .oPYo. 
//    `8 8    8 .oooo8 8    8 8oooo8 Yb..   
//     8 8    8 8    8 8    8 8.       'Yb. 
//`YooP' 8    8 `YooP8 8YooP' `Yooo' `YooP' 
//:.....:..:::..:.....:8 ....::.....::.....:
//:::::::::::::::::::::8 :::::::::::::::::::
//:::::::::::::::::::::..:::::::::::::::::::
//                                           
// .oPYo.               o                 o  
// 8    8                                 8  
//o8YooP' oPYo. .oPYo. o8 .oPYo. .oPYo.  o8P 
// 8      8  `' 8    8  8 8oooo8 8    '   8  
// 8      8     8    8  8 8.     8    .   8  
// 8      8     `YooP'  8 `Yooo' `YooP'   8  
//:..:::::..:::::.....::8 :.....::.....:::..:
//:::::::::::::::::::::oP :::::::::::::::::::
//:::::::::::::::::::::..::::::::::::::::::::

// This interactive class allows users to paint different things on a screen.
// Just like any normal paint editor, it only ends once the user voluntarily
// exits the program.

import java.util.Scanner;
public class Paint {

	public static void main(String[] args){
		//This sets the width
		System.out.println("Please provide the width of the screen.");
		Scanner scanner = new Scanner(System.in);
		int width;
		
		//This checks to make sure that the user input is an integer between 0 and 150. 150 was selected as
		//an arbitrary maximum since that is the largest width that can fit on the console without having
		//to scroll.
		do{	
			while(!scanner.hasNextInt()){
				System.out.println("Please enter an integer greater than 0 and less than 150.");
				scanner.next();
			}
			width = scanner.nextInt();
			if(width<=0 || width>150)
				System.out.println("Please enter an integer greater than 0 and less than 150.");
		}while(width<=0 || width > 150);
		
		//This sets the height
		System.out.println("Please provide the height of the screen.");
		int height;
		
		//This checks to make sure that the user input is an integer between 0 and 50. 50 was selected as
		//an arbitrary maximum since that is the largest height that can fit on the console without having
		//to scroll.
		do{
			while(!scanner.hasNextInt()){
				System.out.println("Please enter an integer greater than 0 and less than 50.");
				scanner.next();
			}
			height = scanner.nextInt();
			if(height<=0 || height > 50)
				System.out.println("Please enter an integer greater than 0 and less than 50.");
		}while(height<=0 || height > 50);
		
		
		System.out.println("Please enter the character you would like for the border.");
		char borderCharacter;
		String tempString = scanner.next();
		
		//This checks to make sure the user entered only a single character.
		while(tempString.length()!=1){
			System.out.println("Please enter a single character.");
			tempString = scanner.next();
		}

		borderCharacter = tempString.charAt(0);
		
		//This creates and draws the new screen.
		Screen screen = new Screen(width,height,borderCharacter);
		screen.draw();
		
		//This sets the paint character
		System.out.println();
		System.out.println("Please enter the paint character you would like to use.");
		char paintCharacter;
		tempString = scanner.next();
		
		//This checks to make sure the user entered a single character.
		while(tempString.length()!=1){
			System.out.println("Please enter a single character.");
			tempString = scanner.next();
		}
		
		paintCharacter = tempString.charAt(0);
		
		//This will let the user constantly paint on the screen. There is no exit option since
		//like other paint programs, the paint program should only stop when the user voluntarily
		//closes the program.
		while(true){
			
			//To streamline things, the user only has to type in the number for the thing
			//he would like to paint.
			System.out.println();
			System.out.println("Type the number for the shape you would like to draw or action you would like to do.");
			System.out.println("1)Horizontal Line 2)Vertical Line 3)Box 4)Frame 5)TextLine 6)Clear Screen 7)Resize Screen 8)Change Paint Character");
			int shapeVal;
			
			//This checks to make sure the user selected a valid option
			do{
				while(!scanner.hasNextInt()){
					System.out.println("Please enter a valid integer");
					scanner.next();
				}
				shapeVal = scanner.nextInt();
				if(shapeVal < 1 || shapeVal > 8)
					System.out.println("Please enter a valid integer.");
			}while(shapeVal<1 || shapeVal>8);
			
			switch(shapeVal){
				//If the user wanted a horizontal line, the program will prompt them for a length,
				//x, and y location, checking according for valid inputs. Then it will draw it on the screen.
				case 1: {System.out.println("Type the length of the line.");
					int length;
					do{
						while(!scanner.hasNextInt()){
							System.out.println("Please enter a valid integer.");
							scanner.next();
						}
						length = scanner.nextInt();
						if(length<=0)
							System.out.println("Please enter an integer greater than 0.");
					
					}while(length<=0);
					System.out.println("Enter the x coordinate where you would like to place the line.");
					int x;
					while(!scanner.hasNextInt()){
						System.out.println("Please enter a valid integer.");
						scanner.next();
					}
					x = scanner.nextInt();
					System.out.println("Enter the y coordinate where you would like to place the line.");
					int y;
					while(!scanner.hasNextInt()){
						System.out.println("Please enter a valid integer.");
						scanner.next();
					}
					y = scanner.nextInt();
					HLine line = new HLine(length, paintCharacter);
					line.paintOn(screen, x, y);
					screen.draw();
					break;
				}
				
				//If the user wanted a vertical line, the program will prompt them for a length,
				//x, and y location, checking according for valid inputs. Then it will draw it on the screen.
				case 2: {
					System.out.println("Type the length of the line.");
					int length;
					do{
						while(!scanner.hasNextInt()){
							System.out.println("Please enter a valid integer.");
							scanner.next();
						}
						length = scanner.nextInt();
						if(length<=0)
							System.out.println("Please enter an integer greater than 0.");
					
					}while(length<=0);
					System.out.println("Enter the x coordinate where you would like to place the line.");
					int x;
					while(!scanner.hasNextInt()){
						System.out.println("Please enter a valid integer.");
						scanner.next();
					}
					x = scanner.nextInt();
					System.out.println("Enter the y coordinate where you would like to place the line.");
					int y;
					while(!scanner.hasNextInt()){
						System.out.println("Please enter a valid integer.");
						scanner.next();
					}
					y = scanner.nextInt();
					VLine line = new VLine(length, paintCharacter);
					line.paintOn(screen, x, y);
					screen.draw();
					break;
				}
				
				//If the user wanted a box, the program will prompt them for a width, height,
				//x, and y location, checking according for valid inputs. Then it will draw it on the screen.
				case 3: {
					System.out.println("Type the width of the box.");
					int widthBox = 0;
					do{
						while(!scanner.hasNextInt()){
							System.out.println("Please enter an integer greater than 0.");
							scanner.next();
						}
						widthBox = scanner.nextInt();
						if(widthBox<=0)
							System.out.println("Please enter an integer greater than 0.");
					
					}while(widthBox<=0);
					System.out.println("Type the height of the box.");
					int heightBox = 0;
					do{
						while(!scanner.hasNextInt()){
							System.out.println("Please enter a valid integer.");
							scanner.next();
						}
						heightBox = scanner.nextInt();
						if(heightBox<=0)
							System.out.println("Please enter an integer greater than 0.");
					
					}while(heightBox<=0);
					System.out.println("Enter the x coordinate where you would like to place the box.");
					int x;
					while(!scanner.hasNextInt()){
						System.out.println("Please enter a valid integer.");
						scanner.next();
					}
					x = scanner.nextInt();
					System.out.println("Enter the y coordinate where you would like to place the box.");
					int y;
					while(!scanner.hasNextInt()){
						System.out.println("Please enter a valid integer.");
						scanner.next();
					}
					y = scanner.nextInt();
					Box box = new Box(widthBox,heightBox, paintCharacter);
					box.paintOn(screen, x, y);
					System.out.println(x+ " " + y + " " + paintCharacter);
					screen.draw();
					break;
				}
				
				//If the user wanted a frame, the program will prompt them for a width, height,
				//x, and y location, checking according for valid inputs. Then it will draw it on the screen.
				case 4: {
					System.out.println("Type the width of the frame.");
					int widthFrame = 0;
					do{
						while(!scanner.hasNextInt()){
							System.out.println("Please enter an integer greater than 0.");
							scanner.next();
						}
						widthFrame = scanner.nextInt();
						if(widthFrame<=0)
							System.out.println("Please enter an integer greater than 0.");
					
					}while(widthFrame<=0);
					System.out.println("Type the height of the box.");
					int heightFrame = 0;
					do{
						while(!scanner.hasNextInt()){
							System.out.println("Please enter a valid integer.");
							scanner.next();
						}
						heightFrame = scanner.nextInt();
						if(heightFrame<=0)
							System.out.println("Please enter an integer greater than 0.");
					
					}while(heightFrame<=0);
					System.out.println("Enter the x coordinate where you would like to place the frame.");
					int x;
					while(!scanner.hasNextInt()){
						System.out.println("Please enter a valid integer.");
						scanner.next();
					}
					x = scanner.nextInt();
					System.out.println("Enter the y coordinate where you would like to place the frame.");
					int y;
					while(!scanner.hasNextInt()){
						System.out.println("Please enter a valid integer.");
						scanner.next();
					}
					y = scanner.nextInt();
					Frame frame = new Frame(widthFrame,heightFrame, paintCharacter);
					frame.paintOn(screen, x, y);
					screen.draw();
					break;
				}
				
				//If the user wanted a text line, the program will prompt them for the text,
				//x, and y location, checking according for valid inputs. Then it will draw it on the screen.
				case 5: {
					System.out.println("Type the text you would like to display.");
					scanner.nextLine();
					String text = scanner.nextLine();
					System.out.println("Enter the x coordinate where you would like to place the text.");
					int x;
					while(!scanner.hasNextInt()){
						System.out.println("Please enter a valid integer.");
						scanner.next();
					}
					x = scanner.nextInt();
					System.out.println("Enter the y coordinate where you would like to place the text.");
					int y;
					while(!scanner.hasNextInt()){
						System.out.println("Please enter a valid integer.");
						scanner.next();
					}
					y = scanner.nextInt();
					TextLine textLine = new TextLine(text);
					textLine.paintOn(screen, x, y);
					screen.draw();
					break;
				}
				
				//This clears the screen
				case 6: {
					screen.clearScreen();
					screen.draw();
				}
				
				//If the user wants to resize the screen, this prompts the user for the new width,
				//height, and border character.
				case 7: {
					System.out.println("Please provide the width of the screen.");
					do{	
						while(!scanner.hasNextInt()){
							System.out.println("Please enter an integer greater than 0 and less than 150.");
							scanner.next();
						}
						width = scanner.nextInt();
						if(width<=0 || width>150)
							System.out.println("Please enter an integer greater than 0 and less than 150.");
					}while(width<=0 || width > 150);
					
					System.out.println("Please provide the height of the screen.");
					do{
						while(!scanner.hasNextInt()){
							System.out.println("Please enter an integer greater than 0 and less than 50.");
							scanner.next();
						}
						height = scanner.nextInt();
						if(height<=0 || height > 50)
							System.out.println("Please enter an integer greater than 0 and less than 50.");
					}while(height<=0 || height > 50);
					
					System.out.println("Please enter the character you would like for the border.");
					tempString = scanner.next();
					while(tempString.length()!=1){
						System.out.println("Please enter a single character.");
						tempString = scanner.next();
					}

					borderCharacter = tempString.charAt(0);
					System.out.println(borderCharacter);
					screen = new Screen(width,height,borderCharacter);
					screen.draw();
					break;
				}
				
				//If the user wants a new paint character, this prompts the user for the new paint character.
				case 8:{
					System.out.println("Please enter the paint character you would like to use.");
					tempString = scanner.next();
					while(tempString.length()!=1){
						System.out.println("Please enter a single character.");
						tempString = scanner.next();
					}
					
					paintCharacter = tempString.charAt(0);
				}
			}

		}
	}
}
