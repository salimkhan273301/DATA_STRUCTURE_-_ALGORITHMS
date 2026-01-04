package basic.questions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PossibleMaxLoss {
	
	/*
	 * private static int findPossibleMaxLoss(int[] prices) { int maxLoss=0; int
	 * maxBoughtCost=prices[0];
	 * 
	 * for(int i=1; i<prices.length; i++) {
	 * 
	 * if(prices[i]>maxBoughtCost) { maxBoughtCost=prices[i]; }else { int
	 * loss=prices[i]-maxBoughtCost; maxLoss=Math.min(maxLoss, loss); } }
	 * 
	 * return maxLoss; }
	 */
	
	
	/*
	 * private static int findPossibleMaxLoss(int[] prices) { int maxLoss=0; int
	 * maxBoughtCost=prices[0]; for(int i=1; i<prices.length; i++) {
	 * maxLoss=Math.min(maxLoss,prices[i]-maxBoughtCost);
	 * maxBoughtCost=Math.max(maxBoughtCost, prices[i]); }
	 * 
	 * return maxLoss; }
	 */
	 
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] prices = {100, 180, 260, 40, 535,695 , 30};
		
		int maxLoss=findPossibleMaxLoss(prices);
		
		System.out.println(maxLoss);
		

	}

	
	  /**
	 * @param prices
	 * @return
	 */
	private static int findPossibleMaxLoss(int[] prices) { 
		  
		  int[] max_loss= {0};
	  int[] maxPriceSoFar= {prices[0]};
	  
/*Arrays.stream(prices,1,prices.length).map(e->{
		  
		  int loss=e-maxPriceSoFar[0];
		  max_loss[0]=Math.min(max_loss[0], loss);
		  maxPriceSoFar[0]=Math.max(maxPriceSoFar[0], e);
		  return max_loss[0];
			  
	  }).min().orElse(0);*/
	  
List<Integer>lost_list=Arrays.stream(prices,1,prices.length).map(e->{
		  System.out.println(e);
		  int loss=e-maxPriceSoFar[0];
		  System.out.println("loss::"+loss);
		  max_loss[0]=Math.min(max_loss[0], loss);
		  System.out.println("Max_loss::"+ max_loss[0]);
		  maxPriceSoFar[0]=Math.max(maxPriceSoFar[0], e);
		  return max_loss[0];
			  
	  }).boxed().collect(Collectors.toList());


System.out.println(lost_list);

	  return max_loss[0];
	  
	  
	  }
	 
	



}
