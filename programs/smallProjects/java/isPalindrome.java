import java.util.*;
class Solution {
    public static boolean isPalindrome(int x) {
        if(x<0){//If less than zero then -1 is 1- 
            return false;
        }else if(x < 10){//all numbers less than 10 are true
            return true;
        }else{
            String num = Integer.toString(x);
            int end = num.length()-1;//set the total length
            for(int i = 0; i<end; i++){//iterate through first half, end gets subtracted each time
                if(num.charAt(i) != num.charAt(end)){
                    return false;//if it is ever not the same then return false
                }
                end--;
            }
            return true;
        }
        
    }

    public static void main(String[] args){
	System.out.println("Type a number");
	Scanner sc = new Scanner(System.in);
	int a = sc.nextInt();
	if(isPalindrome(a)){
		System.out.println("This is a Palindrome Number");
	}else{
		System.out.println("This is not a Palindrome Nummber");
	}
    sc.close();
    }
}
