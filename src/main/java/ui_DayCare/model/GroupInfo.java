package ui_DayCare.model;

import ui_DayCare.utils.Helper;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.sql.Date;

public enum GroupInfo {

    SixToTwelve(1),
    ThirteenToTwentyFour(2),
    TwentyFiveToThirtyFive(3),
    ThirtySixToFortySeven(4),
    FortyEightToFiftyNine(5),
    SixtyAbove(6);

    private int value;

    GroupInfo(int value) {
        this.value = value;
    }

    GroupInfo(String strValue) {
       switch (strValue) {
           case "6 - 12":
               this.value = 1;
               break;
           case "13 - 24":
               this.value = 2;
               break;
           case "25 - 35":
               this.value = 3;
               break;
           case "36 - 47":
               this.value = 4;
               break;
           case "48 - 59":
               this.value = 5;
               break;
           case "60+":
               this.value = 6;
               break;
       }
    }

    public int getValue() {
        return value;
    }
    public String getStringValue() {
        return GroupInfo.getDisplayStrings()[getValue() -1];
    }

    public int capacity() {
        return switch (this) {
            case SixToTwelve -> 4;
            case ThirteenToTwentyFour -> 5;
            case TwentyFiveToThirtyFive -> 6;
            case ThirtySixToFortySeven -> 8;
            case FortyEightToFiftyNine -> 12;
            case SixtyAbove -> 15;
        };
    }

    public static GroupInfo getGroupInfo(Long dateOfBirth) {
        java.util.Date currentDate = new java.util.Date();
        Long age = Helper.convertStringDateToLong("2021-12-16") - dateOfBirth;
        Long oneMonth = Helper.get1MonthLong();

        GroupInfo groupInfo;
        if ((age >= 6 * oneMonth) && (age <= 12 * oneMonth)) {
            groupInfo = SixToTwelve;
        } else if ((age >= 13 * oneMonth) && (age <= 24 * oneMonth)) {
            groupInfo = ThirteenToTwentyFour;
        } else if ((age >= 25 * oneMonth) && (age <= 35 * oneMonth)) {
            groupInfo = TwentyFiveToThirtyFive;
        } else if ((age >= 36 * oneMonth) && (age <= 47 * oneMonth)) {
            groupInfo = ThirtySixToFortySeven;
        } else if ((age >= 48 * oneMonth) && (age <= 59 * oneMonth)) {
            groupInfo = FortyEightToFiftyNine;
        } else {
            groupInfo = SixtyAbove;
        }
        return groupInfo;
    }

    public static String[] getDisplayStrings() {
        return new String[] {
                "6 - 12", "13 - 24",
                "25 - 35","36 - 47",
                "48 - 59", "60+" };
    }
}
