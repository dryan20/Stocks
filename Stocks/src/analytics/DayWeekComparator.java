package analytics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import stockBody.Chart;
import stockBody.TagKurs;

public class DayWeekComparator {
	private Chart chart = null;

	public DayWeekComparator(Chart chart) {
		this.chart = chart;
	}

	public int getCheapestDay() {
		HashMap<Integer, TagKurs> map = chart.getChart();

		ArrayList<TagKurs> tempList = new ArrayList<>();
		ArrayList<TagKurs> list = new ArrayList<>();

		int kw = 0;
		for (int i = 0; i < map.size(); i++) {
			TagKurs kurs = map.get(i);

			if (kw == 0) {
				kw = kurs.getKW();
				tempList.add(kurs);
			} else if (kw == kurs.getKW()) {
				tempList.add(kurs);
			} else if (kw != kurs.getKW()) {
				if (!tempList.isEmpty()) {
					// auswertung der abgeschlossenen tempList
					list.add(getLowestPriceDayOfWeek(tempList));
				}

				// beginn neuer Liste
				kw = kurs.getKW();
				tempList = new ArrayList<>();
				tempList.add(kurs);
			}
		}
		// auswertung der abgeschlossenen tempList
		list.add(getLowestPriceDayOfWeek(tempList));

		// System.out.println("--------------------------------------------------------");
		// System.out.println("LOWEST");
		printStatistic(list);
		return 0;
	}

	private void printStatistic(ArrayList<TagKurs> list) {
		int montag = 0;
		int dienstag = 0;
		int mittwoch = 0;
		int donnerstag = 0;
		int freitag = 0;

		for (TagKurs kurs : list) {
			if (kurs.getDayI() == 1) {
				montag++;
			} else if (kurs.getDayI() == 2) {
				dienstag++;
			} else if (kurs.getDayI() == 3) {
				mittwoch++;
			} else if (kurs.getDayI() == 4) {
				donnerstag++;
			} else if (kurs.getDayI() == 5) {
				freitag++;
			}
		}

		// System.out.println("Montag: " + montag);
		// System.out.println("Dienstag: " + dienstag);
		// System.out.println("Mittwoch: " + mittwoch);
		// System.out.println("Donnerstag: " + donnerstag);
		// System.out.println("Freitag: " + freitag);

	}

	private TagKurs getLowestPriceDayOfWeek(ArrayList<TagKurs> list) {
		TagKurs cheapestKurs = null;
		BigDecimal cheapest = new BigDecimal("0");
		for (TagKurs kurs : list) {
			if (cheapest.signum() == 0) {
				cheapest = kurs.getLowPrice();
				cheapestKurs = kurs;
			} else if (cheapest.compareTo(kurs.getLowPrice()) == 1) {
				cheapest = kurs.getLowPrice();
				cheapestKurs = kurs;
			}
		}

		return cheapestKurs;
	}

	public int getHighestDay() {
		HashMap<Integer, TagKurs> map = chart.getChart();

		ArrayList<TagKurs> tempList = new ArrayList<>();
		ArrayList<TagKurs> list = new ArrayList<>();

		int kw = 0;
		for (int i = 0; i < map.size(); i++) {
			TagKurs kurs = map.get(i);

			if (kw == 0) {
				kw = kurs.getKW();
				tempList.add(kurs);
			} else if (kw == kurs.getKW()) {
				tempList.add(kurs);
			} else if (kw != kurs.getKW()) {
				if (!tempList.isEmpty()) {
					// auswertung der abgeschlossenen tempList
					list.add(getHighestPriceDayOfWeek(tempList));
				}

				// beginn neuer Liste
				kw = kurs.getKW();
				tempList = new ArrayList<>();
				tempList.add(kurs);
			}
		}
		// auswertung der abgeschlossenen tempList
		list.add(getHighestPriceDayOfWeek(tempList));

		// System.out.println("--------------------------------------------------------");
		// System.out.println("HIGHEST");
		printStatistic(list);
		return 0;
	}

	private TagKurs getHighestPriceDayOfWeek(ArrayList<TagKurs> list) {
		TagKurs highestKurs = null;
		BigDecimal highest = new BigDecimal("0");
		for (TagKurs kurs : list) {
			if (highest.signum() == 0) {
				highest = kurs.getHighPrice();
				highestKurs = kurs;
			} else if (highest.compareTo(kurs.getHighPrice()) == -1) {
				highest = kurs.getHighPrice();
				highestKurs = kurs;
			}
		}

		return highestKurs;
	}
}
