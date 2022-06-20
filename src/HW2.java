import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;


public class HW2 {
	public static void main(String[] args) {
		try {
			// Creates scanner object.
			// Using custom input, plesae change this to "./hw2sample.txt" for original sample input.
			Scanner scanner = new Scanner(new File("./hw2sample-custom.txt"));
			
			// Consumes the first line of the input which includes column titles: "Date,SKU,Price,Discount".
			scanner.nextLine();
			
			// Sets the delimiter to a comma or a new line.
			scanner.useDelimiter(",|\n");
			
			// Creates print stream object.
			String outputFile = "./report.txt";
			PrintStream ps = new PrintStream(outputFile);
			
			// Declares and initializes variables used to assign start and end dates of transactions.
			String start = "";
			String end = "";
			int i = 0;
			
			// Declares variables used in computing sum of discounted prices. 
			String token1 = "";
			float sum = 0.0f;
			
			// Declares and initializes variables to represent minimum and maximum prices.
			float minPrice = Float.MAX_VALUE;
			int minSKU = -1;
			float maxPrice = Float.MIN_VALUE;
			int maxSKU = -1;
			
			while (scanner.hasNext()) {
				
				token1 = scanner.next();
				// Runs an if statement to assign the the value of the first token1 as the start date.
				if (i == 0) {
					start = token1;
					i++;
				}
				// Consumes tokens for computation.
				String token2 = scanner.next();
				String token3 = scanner.next();
				String token4 = scanner.next();
				
				if (Float.parseFloat(token3) < minPrice) {
					minPrice = Float.parseFloat(token3);
					minSKU = Integer.parseInt(token2);
				}
				
				if (Float.parseFloat(token3) > maxPrice) {
					maxPrice = Float.parseFloat(token3);
					maxSKU = Integer.parseInt(token2);
				}
		
		
				// Computes sum of all items' discounted price.
				sum += Float.parseFloat(token3) - (Float.parseFloat(token3) * Float.parseFloat(token4)) / 100;
			}
			
			// Computes tax for the transaction with 8.875% tax.  
			float tax = sum * 0.0875f;
			
			// Assigns the final token1 as the end date of the transaction.
			end = token1;
			
			// Write to file.
			ps.printf("Report from %s to %s.\n", start, end);
			ps.printf("The total is $%.2f\n", sum);
			ps.printf("The tax is $%.2f\n", tax);
			ps.printf("The highest price item is #%d at $%.2f\n", maxSKU, maxPrice);
			ps.printf("The lowest price item is #%d at $%.2f\n", minSKU, minPrice);
			ps.flush();
			
			System.out.println("Report completed, file generated: " + outputFile);
			
		} 
		// Catch any exceptions if filename or filepath not available.
		catch(FileNotFoundException e) {
			System.out.println("file not found");
		}
	}
}
