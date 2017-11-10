package utils;


/**
 * @author alvin.tibor
 * */
public class UtilDecimalNumberToWords


	 {
		  private static String string;
		  private static String st1[] = { "ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX",
		    "SEVEN", "EIGHT", "NINE", };
		  private  static String st2[] = { "Hundred", "Thousand", "Lac", "Crore" };
		  private static String st3[] = { "TEN", "ELEVEN", "TWELVE", "THIRTEEN", "FOURTEEN",
		    "FIFTEEN", "SIXTEEN", "SEVENTEEN", "EIGHTEEN", "NINTEEN", };
		  private static String st4[] = { "TWENTY", "THIRTY", "FOURTY", "FIFTY", "SIXTY", "SEVENTY",
		    "EIGHTY", "NINETY" };

	public static String convert2(int number) {
		  int n = 1;
		  int word;
		  string = "";
		  while (number != 0) {
		   switch (n) {
		   case 1:
		    word = number % 100;
		    pass(word);
		    if (number > 100 && number % 100 != 0) {
		     show("and ");
		    }
		    number /= 100;
		    break;
		   case 2:
		    word = number % 10;
		    if (word != 0) {
		     show(" ");
		     show(st2[0]);
		     show(" ");
		     pass(word);
		    }
		    number /= 10;
		    break;
		   case 3:
		    word = number % 100;
		    if (word != 0) {
		     show(" ");
		     show(st2[1]);
		     show(" ");
		     pass(word);
		    }
		    number /= 100;
		    break;
		   case 4:
		    word = number % 100;
		    if (word != 0) {
		     show(" ");
		     show(st2[2]);
		     show(" ");
		     pass(word);
		    }
		    number /= 100;
		    break;
		   case 5:
		    word = number % 100;
		    if (word != 0) {
		     show(" ");
		     show(st2[3]);
		     show(" ");
		     pass(word);
		    }
		    number /= 100;
		    break;
		   }
		   n++;
		  }
		  return "AND " + string + " CENTAVOS";
		 }


		 public static void pass(int number) {
		  int word, q;
		  if (number < 10) {
		   show(st1[number]);
		  }
		  if (number > 9 && number < 20) {
		   show(st3[number - 10]);
		  }
		  if (number > 19) {
		   word = number % 10;
		   if (word == 0) {
		    q = number / 10;
		    show(st4[q - 2]);
		   } else {
		    q = number / 10;
		    show(st1[word]);
		    show(" ");
		    show(st4[q - 2]);
		   }
		  }
		 }
		 //show
		 public static void show(String s) {
			  String st;
			  st = string;
			  string = s;
			  string += st;
			 }
		 
		
	}
	
	


/*
//test
// NumToWords w= new NumToWords();
 String longNumToStr=Long.toString(AmountTextValueConvertToInt);
   String str2="";
   String splitDecimal=longNumToStr;
	  String[] parts = splitDecimal.split(".");
	  String wholeNumber = parts[0];
	  String decNumber = parts[1];
	  if (decNumber != "00") {
		  int decNumberIntValue=Integer.parseInt(decNumber);
		   str2 += " and";
		   str2 = jposUtilDecimalNumberToWords.convert2(decNumberIntValue);
		   str2 += " ";//Paise
	  }
	  //end test 

*/



