package code;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.stereotype.Service;
@Service
public class Test {
	static int x=0,y=0;
	public static void setData() {
		x+=33;
		y+=4;
		System.out.println("addData");
	}
	public static String getData() {
		return x+" - "+y;
	}
	public String random() {
    	/*String a=null;
    	if(flag) {
    		randomnum=Math.abs((new Random()).nextInt());
    		a=""+randomnum;
    		flag=false;
    	}
    	else {
    		a=randomnum+": ";
    	}*/
		int a=Math.abs(new Random().nextInt());
		int b=a;
		ArrayList<Integer>po=new ArrayList<>();
		for(int i=2;i*i<a;i++){
            while(a%i==0){
                a/=i;
                po.add(i);
            }
        }
		if(a!=1)po.add(a);
		
    	return b+":"+po;
    }
}
