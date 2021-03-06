package start;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

import analytics.DayWeekComparator;
import analytics.Siumulator;
import dataImport.ExcelReader;
import stockBody.Chart;

public class Starter {
	public static void main(String[] args) {
		// String path =
		// "/Users/Adrian/Documents/AktienDaten/wkn_823212_historic1.csv";
		String path = "C:\\Users\\adria\\Documents\\Stock_Data\\Apple\\wkn_865985_historic.csv";
		ExcelReader reader = new ExcelReader(path);
		Chart chart = reader.importData();

		DayWeekComparator dwC = new DayWeekComparator(chart);
		dwC.getCheapestDay();
		dwC.getHighestDay();

		Siumulator sim = new Siumulator(chart);
		sim.checkBuySellByDay(5, 3);
		
		
//		 Siumulator sim = new Siumulator(chart); for (int a = 1; a < 6; a++) {
//		  for (int b = 1; b < 6; b++) { sim.checkBuySellByDay(a, b); } }
//		  
//		  
//		 HashMap<String, BigDecimal> resultMap = sim.getResultMap();
//		 HashMap<String, BigDecimal> sortedResults = new HashMap<>();
//		 SortedSet<BigDecimal> keys = new
//		 TreeSet<BigDecimal>(resultMap.values());
//		 
//		 for (BigDecimal bd : keys) { for (String key : resultMap.keySet()) {
//		 if (resultMap.get(key).compareTo(bd) == 0) {
//		 System.out.println(resultMap.get(key) + "<-------->" + key);
//		 sortedResults.put(key, resultMap.get(key)); break; } } }
		 
	}
}
