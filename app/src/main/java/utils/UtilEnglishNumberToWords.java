package utils;

import java.text.DecimalFormat;

/**
 * @author alvin.tibor
 * */
public class UtilEnglishNumberToWords {
	NumToWords w= new NumToWords();
	static String str2= "";
	 
  private static final String[] tensNames = {
    "",
    " TEN",
    " TWENTY",
    " THIRTY",
    " FORTY",
    " FIFTY",
    " SIXTY",
    " SEVENTY",
    " EIGHTY",
    " NINETY"
  };

  
  
  
  private static final String[] numNames = {
    "",
    " ONE",
    " TWO",
    " THREE",
    " FOUR",
    " FIVE",
    " SIX",
    " SEVEN",
    " EIGHT",
    " NINE",
    " TEN",
    " ELEVEN",
    " TWELVE",
    " THIRTEEN",
    " FOURTEEN",
    " FIFTEEN",
    " SIXTEEN",
    " SEVENTEEN",
    " EIGHTEEN",
    " NINETEEN"
  };

  private static String convertLessThanOneThousand(int number) {
    String soFar;
   
    if (number % 100 < 20){
      soFar = numNames[number % 100];
      number /= 100;
    }
    else {
      soFar = numNames[number % 10];
      number /= 10;

      soFar = tensNames[number % 10] + soFar;
      number /= 10;
    }
    if (number == 0) return soFar;
    return numNames[number] + " HUNDRED" + soFar;
  }


  public static String convert(long number) {
    // 0 to 999 999 999 999
	 
    if (number == 0) { return "ZERO"; }

    String snumber = Long.toString(number);

    // pad with "0"
    String mask = "000000000000";
    DecimalFormat df = new DecimalFormat(mask);
    snumber = df.format(number);

    // XXXnnnnnnnnn 
    int billions = Integer.parseInt(snumber.substring(0,3));
    // nnnXXXnnnnnn
    int millions  = Integer.parseInt(snumber.substring(3,6)); 
    // nnnnnnXXXnnn
    int hundredThousands = Integer.parseInt(snumber.substring(6,9)); 
    // nnnnnnnnnXXX
    int thousands = Integer.parseInt(snumber.substring(9,12));    

    String tradBillions;
    switch (billions) {
    case 0:
      tradBillions = "";
      break;
    case 1 :
      tradBillions = convertLessThanOneThousand(billions) 
      + " BILLION ";
      break;
    default :
      tradBillions = convertLessThanOneThousand(billions) 
      + " BILLION ";
    }
    String result =  tradBillions;

    String tradMillions;
    switch (millions) {
    case 0:
      tradMillions = "";
      break;
    case 1 :
      tradMillions = convertLessThanOneThousand(millions) 
      + " MILLION ";
      break;
    default :
      tradMillions = convertLessThanOneThousand(millions) 
      + " MILLION ";
    }
    result =  result + tradMillions;

    String tradHundredThousands;
    switch (hundredThousands) {
    case 0:
      tradHundredThousands = "";
      break;
    case 1 :
      tradHundredThousands = "ONE THOUSAND ";
      break;
    default :
      tradHundredThousands = convertLessThanOneThousand(hundredThousands) 
      + " THOUSAND ";
    }
    result =  result + tradHundredThousands;

    String tradThousand;
    tradThousand = convertLessThanOneThousand(thousands);
    result =  result + tradThousand;

    // remove extra spaces!
    return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
  }
  
  
  }


//Added for cents for test
class NumToWords {
	  String string;
	  String st1[] = { "Zero", "One", "Two", "Three", "Four", "Five", "Six",
	    "Seven", "Eight", "Nine", };
	  String st2[] = { "Hundred", "Thousand", "Lac", "Crore" };
	  String st3[] = { "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",
	    "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Ninteen", };
	  String st4[] = { "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy",
	    "Eighty", "Ninety" };

public String convert2(int number) {
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
	  return string;
	 }


	 public void pass(int number) {
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
	 public void show(String s) {
		  String st;
		  st = string;
		  string = s;
		  string += st;
		 }
	 
	
} 
