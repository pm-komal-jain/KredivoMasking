/*
	Write a simple program to mask digits in credit card number.
    Program will take as input character to use as mask (e.g. #) and credit card number.
    Add necessary validations and unit test cases to test your program.
    You can use any language of your choice.

    You can make any assumptions you feel necessary and state them in the assignment submission
    (say characters to be masked can be specified as input).

    The program is for a utility tool that will be used across various services that need card number masking
     for eg. notification service that sends out sms or email on settled transactions.
 */

import java.util.Scanner;
//import org.apache.commons.lang3.StringUtils;
import java.util.regex.Pattern;
import java.lang.StringBuilder;

public class CreditCardMasking {

    private String creditCardNumber;
    private char maskingChar;

    public void CreditCardMasking(String creditCardNumber, char maskingChar) {
        this.creditCardNumber = creditCardNumber;
        this.maskingChar = maskingChar;
        validateCreditCardNumber();
        validateMaskingChar();
    }

    public String setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
        return validateCreditCardNumber();
    }

    public String setMaskingChar(char maskingChar) {
        this.maskingChar = maskingChar;
        return validateMaskingChar();
    }

    public String validateCreditCardNumber(){
        if(creditCardNumber.length() < 13 | creditCardNumber.length() > 16){
            throw new IllegalArgumentException("Credit card length should be between 13-16 numbers long, actually found - " + creditCardNumber.length());
        } else  if(Pattern.compile("[^0-9]").matcher(creditCardNumber).find()) {
            throw new NumberFormatException("Only digits are accepted as Credit Card Number found - " + creditCardNumber);
        }
        return "Acceptable Credit Card #";
    }

    public String validateMaskingChar(){
        String specialChars = "/*!@#%^&*()\"{}_[]|\\?/<>,.Xx";
        if((""+maskingChar).equals("$")) {
            throw new IllegalArgumentException("The program will not support $ as special charater for now");
        } else if(!specialChars.contains(""+maskingChar)) {
            return "Its advised to use a special char or 'X' for masking";
        }
        return "Acceptable Masking char";
    }

    public StringBuilder maskCreditCardNumber(){
        /* To-DO -
        Using StringUtils as below , will allow using '$' as masking char.
        Right now running program from command line is not able to resolve StringUtils.
        And so as an alternative replaceAll option is used. This however has a limitation on Using '$' for masking char.
        Since 'replaceAll' uses regex and '$' has a special meaning in regex.
         */
        //String maskedSeq = StringUtils.repeat(""+maskingChar, creditCardNumber.length()-4) + creditCardNumber.substring(creditCardNumber.length()-4);
        String maskedSeq =  (creditCardNumber.substring(0,creditCardNumber.length()-4)).replaceAll(".",""+maskingChar) + creditCardNumber.substring(creditCardNumber.length()-4);

        StringBuilder str = new StringBuilder(maskedSeq);
        return (str.insert(4,'-')).insert(str.length()-4, '-');
    }

    public static void main(String[] arguments) {
        System.out.println("*****************************************************************************");
        System.out.println("Enter below Credit Card number of 13 to 16 digits, and Character to Mask with");
        System.out.println("Note - The program doesnt support $ for masking for now.");
        System.out.println("*****************************************************************************");

        CreditCardMasking ccm = new CreditCardMasking();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter credit card number");
        ccm.setCreditCardNumber(in.nextLine());
        System.out.println("Enter charter for masking");
        ccm.setMaskingChar(in.next().charAt(0));
        System.out.println("Your Credit Card number " + ccm.maskCreditCardNumber() + " Has Won the inFamous COCA COLA LOTTERY!!!");
    }
}
