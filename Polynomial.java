public class Polynomial{
	double [] coef;
	public Polynomial(){
		coef = new double[1];
		coef[0] = 0;
	}
	public Polynomial(double[] arr){
		coef = new double[arr.length];
		for(int i=0;i<arr.length;i++){
			coef[i] = arr[i];
		}
	}
	public double getC(int i){
		return coef[i];
	}
	public int getD(){
		return coef.length;
	}
	public Polynomial add(Polynomial s){
		int a = getD();
		int b = s.getD();
		if(a>b){
			Polynomial p = new Polynomial(coef);
			for(int i = 0; i<b;i++){
				p.coef[i] = p.coef[i] + s.coef[i];
			}
			return p;
		}
		else{
			double[] arr = new double[b];
			for(int i=0;i<b;i++){
				arr[i]=s.getC(i);
			}
			Polynomial p = new Polynomial(arr);
			for(int i = 0; i<a;i++){
				p.coef[i] = p.coef[i] + coef[i];
			}
			return p;
		}
	}
	public double evaluate(double x){
		double result = 0;
		int degree = 0;
		for(int i=0;i<coef.length;i++){
			double n=1;
			for(int j =0;j<degree;j++){
				n=n*x;
			}
			result = result + coef[i]*n;
			degree=degree+1;
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