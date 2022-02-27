package stockBody;

import java.util.HashMap;

public class Chart {
	private String exchange;
	private HashMap<Integer, TagKurs> chart;
	private String currency;
	private int lastIndex;

	public Chart(String exchange) {
		this.exchange = exchange;
		this.chart = new HashMap<Integer, TagKurs>();
		this.lastIndex = 0;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public HashMap<Integer, TagKurs> getChart() {
		return chart;
	}

	public void addKurs(TagKurs kurs) {
		this.chart.put(lastIndex, kurs);
		lastIndex++;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setChart(HashMap<Integer, TagKurs> chart) {
		this.chart = chart;
	}
}
