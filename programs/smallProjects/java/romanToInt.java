import java.util.*;
class Roman{

public static int romanToInt(String s) {
        int output = 0;
        int len = s.length();
        for(int i = 0; i< len; i++){
            char sec = 'A';
            if(i+1 < len){
                sec = s.charAt(i+1);
            }
            if(s.charAt(i) == 'I'){
                if(sec == 'V'){
                    output += 4;
                    i++;
                }else if(sec == 'X'){
                    output += 9;
                    i++;
                }else{
                    output+=1;
                } 
            }else if(s.charAt(i) == 'V'){
                output+=5;
            }else if(s.charAt(i) == 'X'){
                if(sec == 'L'){
                    output += 40;
                    i++;
                }else if(sec == 'C'){
                    output += 90;
                    i++;
                }else{
                    output+=10;
                }
            }else if(s.charAt(i) == 'L'){
                output+=50;
            }else if(s.charAt(i) == 'C'){
                if(sec == 'D'){
                    output += 400;
                    i++;
                }else if(sec == 'M'){
                    output += 900;
                    i++;
                }else{
                    output+=100;
                }
            }else if(s.charAt(i) == 'D'){
                output+=500;
            }else if(s.charAt(i) == 'M'){
                output+=1000;
            }
        }
        return output;
    }	

	public static void main(String[] args){
		System.out.println("Type in a Roman Number");
		Scanner sc = new Scanner(System.in);
		String a = sc.next();
	    System.out.println(romanToInt(a));
        sc.close();	
	}
}
