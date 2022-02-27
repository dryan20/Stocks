package stockBody;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.IsoFields;

public class TagKurs {
	String dayS;
	int dayI; // 1 - 7

	int dateDay; // 1 - 31
	int dateMonth; // 1 - 12
	int dateYear; // vierstellig
	int kw; // Kalenderwoche

	BigDecimal firstPrice;
	BigDecimal highPrice;
	BigDecimal lowPrice;
	BigDecimal lastPrice;

	BigDecimal pieces;
	BigDecimal volumn;

	public TagKurs() {
		this.dayS = "";

		this.dayI = 0;
		this.dateDay = 0;
		this.dateMonth = 0;
		this.dateYear = 0;

		this.firstPrice = new BigDecimal("0");
		this.highPrice = new BigDecimal("0");
		this.lowPrice = new BigDecimal("0");
		this.lastPrice = new BigDecimal("0");
		this.pieces = new BigDecimal("0");
		this.volumn = new BigDecimal("0");
	}

	public String getDayS() {
		return dayS;
	}

	public void setDayS(String dayS) {
		this.dayS = dayS;
	}

	public int getDayI() {
		return dayI;
	}

	public void setDayI(int dayI) {
		this.dayI = dayI;
	}

	public int getDateDay() {
		return dateDay;
	}

	public void setDateDay(int dateDay) {
		this.dateDay = dateDay;
	}

	public int getDateMonth() {
		return dateMonth;
	}

	public void setDateMonth(int dateMonth) {
		this.dateMonth = dateMonth;
	}

	public int getDateYear() {
		return dateYear;
	}

	public void setDateYear(int dateYear) {
		this.dateYear = dateYear;
	}

	public BigDecimal getFirstPrice() {
		return firstPrice;
	}

	public void setFirstPrice(BigDecimal firstPrice) {
		this.firstPrice = firstPrice;
	}

	public BigDecimal getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(BigDecimal highPrice) {
		this.highPrice = highPrice;
	}

	public BigDecimal getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(BigDecimal lowPrice) {
		this.lowPrice = lowPrice;
	}

	public BigDecimal getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(BigDecimal lastPrice) {
		this.lastPrice = lastPrice;
	}

	public BigDecimal getPieces() {
		return pieces;
	}

	public void setPieces(BigDecimal pieces) {
		this.pieces = pieces;
	}

	public BigDecimal getVolumn() {
		return volumn;
	}

	public void setVolumn(BigDecimal volumn) {
		this.volumn = volumn;
	}

	public int getKW() {
		return kw;
	}

	public void setKW(int kw) {
		this.kw = kw;
	}

	public void calcKW() {
		LocalDate localDate = null;

		int mon = this.dateMonth;
		if (mon == 1) {
			localDate = LocalDate.of(this.dateYear, Month.JANUARY, this.dateDay);
		} else if (mon == 2) {
			localDate = LocalDate.of(this.dateYear, Month.FEBRUARY, this.dateDay);
		} else if (mon == 3) {
			localDate = LocalDate.of(this.dateYear, Month.MARCH, this.dateDay);
		} else if (mon == 4) {
			localDate = LocalDate.of(this.dateYear, Month.APRIL, this.dateDay);
		} else if (mon == 5) {
			localDate = LocalDate.of(this.dateYear, Month.MAY, this.dateDay);
		} else if (mon == 6) {
			localDate = LocalDate.of(this.dateYear, Month.JUNE, this.dateDay);
		} else if (mon == 7) {
			localDate = LocalDate.of(this.dateYear, Month.JULY, this.dateDay);
		} else if (mon == 8) {
			localDate = LocalDate.of(this.dateYear, Month.AUGUST, this.dateDay);
		} else if (mon == 9) {
			localDate = LocalDate.of(this.dateYear, Month.SEPTEMBER, this.dateDay);
		} else if (mon == 10) {
			localDate = LocalDate.of(this.dateYear, Month.OCTOBER, this.dateDay);
		} else if (mon == 11) {
			localDate = LocalDate.of(this.dateYear, Month.NOVEMBER, this.dateDay);
		} else if (mon == 12) {
			localDate = LocalDate.of(this.dateYear, Month.DECEMBER, this.dateDay);
		}

		int weekNumber = localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);

		this.kw = weekNumber;
	}

}
