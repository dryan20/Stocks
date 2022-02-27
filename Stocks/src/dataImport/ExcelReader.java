package dataImport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;

import stockBody.Chart;
import stockBody.TagKurs;

public class ExcelReader {
	private String path = "";

	/*
	 * File have to be a CSV File.
	 */
	public ExcelReader(String path) {
		this.path = path;
	}

	public Chart importData() {
		return importData(path);
	}

	public Chart importData(String path) {
		Chart chart = new Chart("Xetra");
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String line = "";
			int zeile = 0;

			while ((line = br.readLine()) != null) {
				if (zeile == 0) {
					zeile++;
					continue;
				}

				String[] values = line.split(";");

				TagKurs kurs = new TagKurs();
				int i = 1;

				if (values.length == 9) {

					// 1: Mittwoch, 23. Dezember 2020;
					// 2: 9,518;
					// 3: 9,888;
					// 4: 9,496;
					// 5: 9,86;
					// 6: 3.549.694;
					// 7: 34.586.404;
					// 8: ;
					// 9: 2020-12-23;9,86

					for (String val : values) {
						if (i == 2) {
							val = val.replace(".", "");
							val = val.replace(",", ".");
							kurs.setFirstPrice(new BigDecimal(val));
						} else if (i == 3) {
							val = val.replace(".", "");
							val = val.replace(",", ".");
							kurs.setHighPrice(new BigDecimal(val));
						} else if (i == 4) {
							val = val.replace(".", "");
							val = val.replace(",", ".");
							kurs.setLowPrice(new BigDecimal(val));
						} else if (i == 5) {
							val = val.replace(".", "");
							val = val.replace(",", ".");
							kurs.setLastPrice(new BigDecimal(val));
						} else if (i == 6) {
							val = val.replace(".", "");
							val = val.replace(",", ".");
							kurs.setPieces(new BigDecimal(val));
						} else if (i == 7) {
							val = val.replace(".", "");
							val = val.replace(",", ".");
							kurs.setVolumn(new BigDecimal(val));
						} else if (i == 1) {
							String day = val.substring(0, val.indexOf(","));
							kurs.setDayI(getDayFromName(day));
							kurs.setDayS(day);
						} else if (i == 9) {
							String date = val.replace("-", "");
							kurs.setDateYear(Integer.parseInt(date.substring(0, 4)));
							kurs.setDateMonth(Integer.parseInt(date.substring(4, 6)));
							kurs.setDateDay(Integer.parseInt(date.substring(6, 8)));
						}
						i++;
					}
				} else if (values.length == 7) {
					for (String val : values) {
						if (i == 2) {
							val = val.replace(".", "");
							val = val.replace(",", ".");
							kurs.setFirstPrice(new BigDecimal(val));
						} else if (i == 3) {
							val = val.replace(".", "");
							val = val.replace(",", ".");
							kurs.setHighPrice(new BigDecimal(val));
						} else if (i == 4) {
							val = val.replace(".", "");
							val = val.replace(",", ".");
							kurs.setLowPrice(new BigDecimal(val));
						} else if (i == 5) {
							val = val.replace(".", "");
							val = val.replace(",", ".");
							kurs.setLastPrice(new BigDecimal(val));
						} else if (i == 6) {
							val = val.replace(".", "");
							val = val.replace(",", ".");
							kurs.setPieces(new BigDecimal(val));
						} else if (i == 7) {
							val = val.replace(".", "");
							val = val.replace(",", ".");
							kurs.setVolumn(new BigDecimal(val));
						} else if (i == 0) {
							String day = val.substring(0, val.indexOf(","));
							kurs.setDayI(getDayFromName(day));
							kurs.setDayS(day);
						} else if (i == 1) {
							String date = val.replace("-", "");
							kurs.setDateYear(Integer.parseInt(date.substring(0, 4)));
							kurs.setDateMonth(Integer.parseInt(date.substring(4, 6)));
							kurs.setDateDay(Integer.parseInt(date.substring(6, 8)));

							String tempDate = val;
							LocalDate date1 = LocalDate.parse(tempDate);
							DayOfWeek day = date1.getDayOfWeek();

							kurs.setDayS(day.toString());
							kurs.setDayI(day.getValue());
						}
						i++;
					}
				}

				kurs.calcKW();
				chart.addKurs(kurs);
			}
		} catch (Exception e) {

		}
		return convertChart(chart);
	}

	private Chart convertChart(Chart chart) {
		HashMap<Integer, TagKurs> map = chart.getChart();
		HashMap<Integer, TagKurs> map2 = new HashMap<>();

		int i = map.size() - 1;
		int i2 = 0;
		while (i >= 0) {
			map2.put(i2, map.get(i));
			i--;
			i2++;
		}
		chart.setChart(map2);
		return chart;
	}

	private int getDayFromName(String val) {
		if (val.contains("ontag")) {
			return 1;
		} else if (val.contains("ienstag")) {
			return 2;
		} else if (val.contains("ittwoch")) {
			return 3;
		} else if (val.contains("onnerstag")) {
			return 4;
		} else if (val.contains("reitag")) {
			return 5;
		} else {
			return 0;
		}
	}
}
