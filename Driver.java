import java.io.File;
public class Driver { 
 public static void main(String [] args) { 
  Polynomial p = new Polynomial(); 
  System.out.println(p.evaluate(3)); 
  double [] c1 = {6,5};
  int [] e1 = {0,3}; 
  Polynomial p1 = new Polynomial(c1,e1); 
  double [] c2 = {-2,-9};
  int [] e2 = {1,4}; 
  Polynomial p2 = new Polynomial(c2,e2); 
  Polynomial s = p1.add(p2); 
  System.out.println("s(0.1) = " + s.evaluate(0.1)); 
  if(s.hasRoot(1)) 
   System.out.println("1 is a root of s"); 
  else 
   System.out.println("1 is not a root of s"); 
  double [] c3 = {3,4};
  int [] e3 = {1,2};
  Polynomial p3 = new Polynomial(c3,e3);
  double [] c4 = {4};
  int [] e4 = {1};
  Polynomial p4 = new Polynomial(c4,e4);
  Polynomial s2 = p3.multiply(p4); 
  System.out.println("s(1) = " + s2.evaluate(1));
  File file = new File("text.txt");
  try{
	Polynomial filePoly = new Polynomial(file);
        System.out.println("Coefficients:");
	for(double coeff: filePoly.coef){
		System.out.println(coeff);
	}
	System.out.println("Exponents:");
	for(double expo: filePoly.exp){
		System.out.println(expo);
	}
	filePoly.saveToFile("save.txt");
  }
  catch(Exception e){
	System.out.println(e);
  }
 } 
} 