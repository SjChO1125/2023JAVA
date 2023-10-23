import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class MethodReferenceLowerCase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> listOfNames=Arrays.asList(new String[] 
				{"Apple","Banana","Cherry"});
		
		Function<String,String> function1=(name)->name.toLowerCase();
		ArrayList<String> collect1=listOfNames.stream().map(function1)
		.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(collect1);
        
	}

}
