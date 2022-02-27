package analytics;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

import stockBody.Chart;
import stockBody.TagKurs;

public class Siumulator {

	private Chart chart = null;
	private BigDecimal capital = null;
	private HashMap<String, BigDecimal> resultMap = null;

	public Siumulator(Chart chart) {
		this.chart = chart;
	}

	public void checkBuySellByDay(int buy, int sell) {
		checkBuySellByDay(new BigDecimal("10000"), buy, sell);
	}

	public void checkBuySellByDay(BigDecimal capital, int buy, int sell) {
		getKonservativ(capital);
		System.out.println("--------------------------------------------------------");
		System.out.println("Buy: " + buy + " | Sell: " + sell);

		//getIdeal(capital, buy, sell);
		//getCapFirstPrice(capital, buy, sell);
		//getCapLastPrice(capital, buy, sell);
		//getBuyFirstSellLastPrice(capital, buy, sell);
		getBuyLastSellFirstPrice(capital, buy, sell);
	}

	private void addResultMap(String key, BigDecimal result) {
		if (this.resultMap == null) {
			this.resultMap = new HashMap<String, BigDecimal>();
		}
		this.resultMap.put(key, result);
	}

	public HashMap<String, BigDecimal> getResultMap() {
		return this.resultMap;
	}

	private void getKonservativ(BigDecimal capital) {
		HashMap<Integer, TagKurs> map = chart.getChart();
		BigDecimal buyValue = new BigDecimal("0");
		boolean isBuy = false;
		for (int i = 0; i < map.size(); i++) {
			TagKurs kurs = map.get(i);
			if (!isBuy) {
				buyValue = capital.divide(kurs.getLowPrice(), 0, RoundingMode.DOWN);
				capital = capital.subtract(buyValue.multiply(kurs.getFirstPrice()));
				isBuy = true;
			}
			// Letzer Tag Verkauf
			if (i == map.size() - 1 && isBuy) {
				capital = capital.add(buyValue.multiply(kurs.getLastPrice()));
				buyValue = new BigDecimal("0");
				isBuy = false;
			}
		}

		addResultMap("KONSERVATIV-", capital);

		System.out.println("KONSERVATIV: " + capital);

	}

	private void getIdeal(BigDecimal capital, int buy, int sell) {
		HashMap<Integer, TagKurs> map = chart.getChart();
		BigDecimal buyValue = new BigDecimal("0");
		boolean isBuy = false;
		for (int i = 0; i < map.size(); i++) {
			TagKurs kurs = map.get(i);
			if (kurs.getDayI() == buy && !isBuy) {
				buyValue = capital.divide(kurs.getLowPrice(), 0, RoundingMode.DOWN);
				capital = capital.subtract(buyValue.multiply(kurs.getLowPrice()));
				isBuy = true;
				// System.out.println("Kapital: " + capital + " | " + "Aktien: "
				// + buyValue);
			} else if (kurs.getDayI() == sell && isBuy) {
				capital = capital.add(buyValue.multiply(kurs.getHighPrice()));
				buyValue = new BigDecimal("0");
				isBuy = false;
				// System.out.println(capital);
			}
			// Letzer Tag Verkauf
			if (i == map.size() - 1 && isBuy) {
				capital = capital.add(buyValue.multiply(kurs.getLastPrice()));
				buyValue = new BigDecimal("0");
				isBuy = false;
			}
		}

		addResultMap("IDEAL-" + buy + sell, capital);

		System.out.println("IDEAL: " + capital);

	}

	private void getCapFirstPrice(BigDecimal capital, int buy, int sell) {
		HashMap<Integer, TagKurs> map = chart.getChart();
		BigDecimal buyValue = new BigDecimal("0");
		boolean isBuy = false;
		for (int i = 0; i < map.size(); i++) {
			TagKurs kurs = map.get(i);
			if (kurs.getDayI() == buy && !isBuy) {
				buyValue = capital.divide(kurs.getFirstPrice(), 0, RoundingMode.DOWN);
				capital = capital.subtract(buyValue.multiply(kurs.getFirstPrice()));
				isBuy = true;
				// System.out.println("Kapital: " + capital + " | " + "Aktien: "
				// + buyValue);
			} else if (kurs.getDayI() == sell && isBuy) {
				capital = capital.add(buyValue.multiply(kurs.getFirstPrice()));
				buyValue = new BigDecimal("0");
				isBuy = false;
				// System.out.println(capital);
			}
			// Letzer Tag Verkauf
			if (i == map.size() - 1 && isBuy) {
				capital = capital.add(buyValue.multiply(kurs.getLastPrice()));
				buyValue = new BigDecimal("0");
				isBuy = false;
			}
		}
		addResultMap("FIRST-" + buy + sell, capital);
		System.out.println("FIRST: " + capital);
	}

	private void getCapLastPrice(BigDecimal capital, int buy, int sell) {
		HashMap<Integer, TagKurs> map = chart.getChart();
		BigDecimal buyValue = new BigDecimal("0");
		boolean isBuy = false;
		for (int i = 0; i < map.size(); i++) {
			TagKurs kurs = map.get(i);
			if (kurs.getDayI() == buy && !isBuy) {
				buyValue = capital.divide(kurs.getLastPrice(), 0, RoundingMode.DOWN);
				capital = capital.subtract(buyValue.multiply(kurs.getLastPrice()));
				isBuy = true;
				// System.out.println("Kapital: " + capital + " | " + "Aktien: "
				// + buyValue);
			} else if (kurs.getDayI() == sell && isBuy) {
				capital = capital.add(buyValue.multiply(kurs.getLastPrice()));
				buyValue = new BigDecimal("0");
				isBuy = false;
				// System.out.println(capital);
			}
			// Letzer Tag Verkauf
			if (i == map.size() - 1 && isBuy) {
				capital = capital.add(buyValue.multiply(kurs.getLastPrice()));
				buyValue = new BigDecimal("0");
				isBuy = false;
			}
		}
		addResultMap("LAST-" + buy + sell, capital);
		System.out.println("LAST: " + capital);
	}

	private void getBuyFirstSellLastPrice(BigDecimal capital, int buy, int sell) {
		HashMap<Integer, TagKurs> map = chart.getChart();
		BigDecimal buyValue = new BigDecimal("0");
		boolean isBuy = false;
		for (int i = 0; i < map.size(); i++) {
			TagKurs kurs = map.get(i);
			if (kurs.getDayI() == buy && !isBuy) {
				buyValue = capital.divide(kurs.getFirstPrice(), 0, RoundingMode.DOWN);
				capital = capital.subtract(buyValue.multiply(kurs.getFirstPrice()));
				isBuy = true;
				// System.out.println("Kapital: " + capital + " | " + "Aktien: "
				// + buyValue);
			} else if (kurs.getDayI() == sell && isBuy) {
				capital = capital.add(buyValue.multiply(kurs.getLastPrice()));
				buyValue = new BigDecimal("0");
				isBuy = false;
				// System.out.println(capital);
			}
			// Letzer Tag Verkauf
			if (i == map.size() - 1 && isBuy) {
				capital = capital.add(buyValue.multiply(kurs.getLastPrice()));
				buyValue = new BigDecimal("0");
				isBuy = false;
			}
		}
		addResultMap("BFSL-" + buy + sell, capital);
		System.out.println("Buy First Sell Last: " + capital);
	}

	private void getBuyLastSellFirstPrice(BigDecimal capital, int buy, int sell) {
		HashMap<Integer, TagKurs> map = chart.getChart();
		BigDecimal buyValue = new BigDecimal("0");
		BigDecimal invest = BigDecimal.ZERO;
		BigDecimal outPut = BigDecimal.ZERO;
		boolean isBuy = false;
		int trades = 0;
		for (int i = 0; i < map.size(); i++) {
			TagKurs kurs = map.get(i);
			if (kurs.getDayI() == buy && !isBuy) {
				buyValue = capital.divide(kurs.getLastPrice(), 0, RoundingMode.DOWN);
				invest = buyValue.multiply(kurs.getLastPrice());
				capital = capital.subtract(invest);
				isBuy = true;
				int nextTrade = trades + 1;
				System.out.println("Trade: " + nextTrade + " " + kurs.getDateDay() + "." + kurs.getDateMonth() + "." + kurs.getDateYear() + " | " + invest);
				// + buyValue);
			} else if (kurs.getDayI() == sell && isBuy) {
				outPut = buyValue.multiply(kurs.getFirstPrice());
				capital = capital.add(outPut);
				isBuy = false;
				trades++;
				System.out.println("Trade: " + trades + " " + kurs.getDateDay() + "." + kurs.getDateMonth() + "." + kurs.getDateYear() + " | " + capital + " => " + outPut.subtract(invest));
			}
			// Letzer Tag Verkauf
			if (i == map.size() - 1 && isBuy) {
				capital = capital.add(buyValue.multiply(kurs.getFirstPrice()));
				buyValue = new BigDecimal("0");
				isBuy = false;
				trades++;
				System.out.println("Trade: " + trades + " " + kurs.getDateDay() + "." + kurs.getDateMonth() + "." + kurs.getDateYear() + "." + " | " + capital);
			}
		}
		addResultMap("BLSF-" + buy + sell, capital);
		System.out.println("Buy Last Sell First: " + capital);
	}
}
