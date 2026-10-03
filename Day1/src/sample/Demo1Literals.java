package sample;

public class Demo1Literals {
	
	public static void main(String[] args) {
		int markerCount = 12;
		double price = 99.50;
		
		System.out.println("items in dabba:" + markerCount);
		System.out.println("price tagged:"+price);
		
		//1. Boolean Literals
		boolean isJavaFun=true;
		boolean isPythonStrict=false;
		
		System.out.println("Boolean True:"+ isJavaFun);
		System.out.println("Boolean False:"+ isPythonStrict);
		
		//2.Byte and Short
		
		byte explicitByte = 120;
		short explicitShort = 32000;
		System.out.println("__2.Byte & Short(Implicit Narrowing)__");
		System.out.println("Byte Value:"+explicitByte);
		System.out.println("Short Value:"+explicitShort);
		
		
		//3.Character literals
		
		char standardChar='A';
		char unicodeChar='\u0041';
		char devanagariChar='\u0905';
		char escapeChar='\n';
		
		System.out.println("___3.Character Literals___");
		System.out.println("Standard Char:" + standardChar);
		System.out.println("UnicodeChar(\\u0041):"+unicodeChar);
		System.out.println("Indian Devanagari Unicode(\\u0905):"+devanagariChar);
		System.out.println("Testing Escape Character(line break below):"+ escapeChar);
		System.out.println();
		
		//4. Integer Literals
		
		int decimalInt=1_500_000;
		int binaryInt=0b1010;
		int hexInt=0x1A;
		int octalInt=012;
		
		System.out.println("___4.integer literals(Different bases)__");
		System.out.println("Decimal Int:"+decimalInt);
		System.out.println("Binary Int:"+binaryInt);
		System.out.println("Hexadecimal Int:"+hexInt);
		System.out.println("Octal Int:"+octalInt);
		System.out.println();
		
		//5.Long literals
		
		long bigPopulation= 1400000000L;
		long massiveNumber=999999999999L;
		
		System.out.println("5.__Long Literals__");
		System.out.println("Standard Long:"+bigPopulation);
		System.out.println("Massive Long:"+massiveNumber);
		System.out.println();
		
		
		//6. Float and Double
		
		double defaultDouble=3.1415926535;
		double explicitDouble=2.5d;
		float standardFloat=3.14F;
		
		System.out.println("__6.Floating point literals");
		System.out.println("Default Doubles:"+ defaultDouble);
		System.out.println("Explicit  Doubles:"+ explicitDouble);
		System.out.println("Standard Float:"+ standardFloat);
		System.out.println();
		
		
		
	}

}
