package stockHead;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import stockBody.Chart;

public class Aktie {
	private String id;
	private String name;
	private Chart chart;

	private Aktie(String id, String name, String exchange) {
		this.id = id;
		this.name = name;
		this.chart = new Chart(exchange);
	}

	private Aktie(String name, String exchange) {
		try {
			this.id = getId(name);
		} catch (Exception e) {
			this.id = name + name.substring(0, 1).toUpperCase();
		}

		this.name = name;
		this.chart = new Chart(exchange);
	}

	private String getId(String name) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(name.getBytes());
		String stringHash = new String(messageDigest.digest());

		return stringHash;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Chart getChart() {
		return chart;
	}

	public void setChart(Chart chart) {
		this.chart = chart;
	}

}
