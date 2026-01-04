package java8String;


import java.util.*;

public class JSonConverson {
	


	    public static void main(String[] args) {
	        try {
	            // Create a sample List<Map<String, String>>
	            List<Map<String, String>> customerList = new ArrayList<>();

	            Map<String, String> customer1 = new HashMap<>();
	            customer1.put("Id", "1");
	            customer1.put("Name", "John Doe");
	            customer1.put("Email", "john.doe@example.com");
	            customerList.add(customer1);

	            Map<String, String> customer2 = new HashMap<>();
	            customer2.put("Id", "2");
	            customer2.put("Name", "Jane Smith");
	            customer2.put("Email", "jane.smith@example.com");
	            customerList.add(customer2);

	            // Use ObjectMapper to convert the list to a JSON string
	           

	            // Print the JSON
	          customerList.forEach(map->map.forEach((k,v)->{
	        	  
	        	  System.out.println(k+":: "+v);
	        	  
	          }));
	          
	          
	          System.out.println("=========================================");
	          
	       // Traversing the list of maps
	          for (Map<String, String> map : customerList) {
	              for (Map.Entry<String, String> entry : map.entrySet()) {
	                  System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
	              }
	          }
	          
	          
	          
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}



