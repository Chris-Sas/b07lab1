import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Polynomial{
	double [] coef;
	int [] exp;
	public Polynomial(){
		this.coef = null;
		this.exp = null;
	}
	public Polynomial(double[] coef, int[] exp){
		this.coef = coef;
		this.exp = exp;
	}
	public Polynomial(File file) throws Exception{
		Scanner myScanner = new Scanner(file);
		if(!myScanner.hasNextLine()){
			this.coef = null;
			this.exp = null;
		}
		else{
			String line = myScanner.nextLine();
			line = line.replace("-", "+-");	
			String[] poly_arr = line.split("\\+");
			this.exp = new int[poly_arr.length];
			this.coef = new double[poly_arr.length];
			for(int i = 0; i < poly_arr.length; i++){
				String[] subArray = poly_arr[i].split("x");
				coef[i] = Double.parseDouble(subArray[0]);
				if(subArray.length >1){
					exp[i] = Integer.parseInt(subArray[1]);
				}
				else{
					exp[i] = 0;
				}
			} 
		}
		myScanner.close();
	}
	public void saveToFile(String myFile) throws Exception{
		if(this.coef == null) return;
		if(this.coef.length != this.exp.length){
		}
		else{
			String writeString = "";
			for(int i = 0; i < this.coef.length; i++){
				writeString += coef[i];
				if(exp[i] != 0){
					writeString += "x" +exp[i];
				}
				writeString += "+";
			}
			if(writeString.endsWith("+")){
				writeString = writeString.substring(0, writeString.length()-1);
			}
			FileWriter myWriter = new FileWriter(new File(myFile));
			myWriter.write(writeString);
			myWriter.close();
		}
	}
	public double getC(int i){
		return coef[i];
	}
	public int getD(){
		return coef.length;
	}
	public Polynomial add(Polynomial s){
		int highest = 0;
		for(int i = 0; i<exp.length; i++){
			if(exp[i]>highest){
				highest = exp[i];
			}
		}
		for(int i = 0; i<s.exp.length; i++){
			if(s.exp[i]>highest){
				highest = s.exp[i];
			}
		}
		double[] arr = new double[highest+1];
		int[] arr2 = new int[highest+1];
		for(int i = 0; i<highest+1; i++){
			for(int j = 0; j<exp.length; j++){
				if(i == exp[j]){
					if(arr2[i] == exp[j]){
						arr[i]=arr[i]+coef[j];	
					}
					else{
						arr[i]=coef[j];
						arr2[i]=exp[j];
					}
				}	
			}
			for(int j = 0; j<s.exp.length; j++){
				if(i == s.exp[j]){
					if(arr2[i] == s.exp[j]){
						arr[i]=arr[i]+s.coef[j];	
					}
					else{
						arr[i]=s.coef[j];
						arr2[i]=s.exp[j];
					}
				}	
			}
		}
		int x=0;
		for(int i = 0; i<highest+1; i++){
			if((arr[i] == 0.0) && (arr2[i] == 0)){
			}
			else{
				x=x+1;
			}
		}
		double[] fin = new double[x];
		int[] fin2 = new int[x];
		int y=0;
		for(int i = 0; i<highest+1; i++){
			if((arr[i] == 0.0) && (arr2[i] == 0)){
			}
			else{
				fin[y]=arr[i];
				fin2[y]=arr2[i];
				y=y+1;
			}
		}
		Polynomial p = new Polynomial(fin, fin2);
		return p;
	}
	public Polynomial multiply(Polynomial s){
		int highest = 0;
		for(int i = 0; i<exp.length; i++){
			if(exp[i]>highest){
				highest = exp[i];
			}
		}
		for(int i = 0; i<s.exp.length; i++){
			if(s.exp[i]>highest){
				highest = s.exp[i];
			}
		}
		double[] arr = new double[highest+1];
		int[] arr2 = new int[highest+1];
		for(int i = 0; i<highest+1; i++){
			for(int j = 0; j<exp.length; j++){
				if(i == exp[j]){
					arr[i]=coef[j];
					arr2[i]=exp[j];
				}	
			}
		}
		for(int i = 0; i<highest+1; i++){
			for(int j = 0; j<s.exp.length; j++){
				if((arr[i] == 0.0) && (arr2[i] == 0)){
				}
				else{
					arr[i]=arr[i]*s.coef[j];
					arr2[i]=arr2[i]+s.exp[j];
				}
			}
		}
		int x=0;
		for(int i = 0; i<highest+1; i++){
			if((arr[i] == 0.0) && (arr2[i] == 0)){
			}
			else{
				x=x+1;
			}
		}
		double[] fin = new double[x];
		int[] fin2 = new int[x];
		int y=0;
		for(int i = 0; i<highest+1; i++){
			if((arr[i] == 0.0) && (arr2[i] == 0)){
			}
			else{
				fin[y]=arr[i];
				fin2[y]=arr2[i];
				y=y+1;
			}
		}
		Polynomial p = new Polynomial(fin, fin2);
		return p;
	}
	public double evaluate(double x){
		if(coef == null) return 0;
		if(coef.length != exp.length){
			return 0;
		}
		double result = 0;
		for(int i=0;i<coef.length;i++){
			result = result + coef[i]*Math.pow(x, exp[i]);
		}
		return result;
	}
	public boolean hasRoot(double n){
		if(evaluate(n)==0){
			return true;
		}
		return false;
	}
}