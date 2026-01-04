package generalproblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class findDuplicate {
	
	public static ArrayList<Integer> findDuplicates(int[] arr) {
        // code here
        
        return Arrays.stream(arr).boxed().collect(Collectors.toMap(e->e, e->1,Integer::sum))
        		.entrySet().stream().filter(e->e.getValue()>1).map(Map.Entry::getKey)
        		.collect(Collectors.toCollection(ArrayList::new));
        		
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] arr= {2, 3, 1, 2, 3};
		
		List<Integer> result=findDuplicates(arr);
		
		System.out.println(result);

	}

}
