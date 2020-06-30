package pl.jedrus.finance.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateConverter {



    public static LocalDate dateFromStringYearMonthToLocalDate(String yearMonth) {
        String[] yearMonthValue = yearMonth.split("-");
        int year = -1;
        int month = -1;
        try {
            year = Integer.parseInt(yearMonthValue[0]);
            month = Integer.parseInt(yearMonthValue[1]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return LocalDate.of(year, month, 1);
    }



    public static List<String> datesListFromStringDateToStringYearMonth(List<String> allDates) {
        List<String> resultDates = new ArrayList<>();
        for (String date : allDates) {
            String[] singleDate = date.split("-");
            String year = singleDate[0];
            String month = singleDate[1];
            resultDates.add(year + "-" + month);
        }
        return resultDates;
    }

    public  static  String dateFromStringDateToStringYearMonth(String date){
        String[] split = date.split("-");
        String year = split[0];
        String month = split[1];
        return year + "-" + month;
    }
}
