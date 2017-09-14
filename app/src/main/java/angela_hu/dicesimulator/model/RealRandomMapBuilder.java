package angela_hu.dicesimulator.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Reference: https://www.random.org/clients/http/
 *
 * @author anrou_hu
 */

public class RealRandomMapBuilder {

    //Name
    private final static String NUMBER = "num";
    private final static String MIN = "min";
    private final static String MAX = "max";
    private final static String COLUMN = "col";
    private final static String BASE = "base";
    private final static String FORMAT = "format";
    private final static String RANDOM = "rnd";

    //Value
    private final static int DEFAULT_MIN = 1;
    private final static int DEFAULT_NUMBER_OF_COLUMN = 1;
    private final static int DEFAULT_BASE = 10;
    public final static String FORMAT_PLAIN = "plain";
    public final static String FORMAT_HTML = "html";

    //If new is specified, a new randomization will created from the truly random bit stream at RANDOM.ORG
    public final static String RANDOM_TYPE_NEW = "new";

    //If id.identifier is specified,the identifier is used to determine the randomization in
    //a deterministic fashion from a large pool of pre-generated random bits
    public final static String RANDOM_TYPE_ID_IDENTIFIER = "id.identifier";


    //Similar to the id.identifier; it allows the randomization to be based on one of the daily pre-generated files.
    //This form must refer to one of the dates for which files exist,
    //so it must be the current day (according to UTC) or a day in the past.
    public final static String RANDOM_TYPE_DATE_ISODATE = "date.isodate";


    private int mNumber = 0;
    private int mMin = DEFAULT_MIN;
    private int mMax = -1;
    private int mNumberOfColumn = DEFAULT_NUMBER_OF_COLUMN;
    private int mBase = DEFAULT_BASE;
    private String mFormat = FORMAT_PLAIN;
    private String mRandomType = RANDOM_TYPE_NEW;

    public RealRandomMapBuilder setNumber(int number) {
        mNumber = number;
        return this;
    }

    public RealRandomMapBuilder setMin(int min) {
        mMin = min;
        return this;
    }

    public RealRandomMapBuilder setMax(int max) {
        mMax = max;
        return this;
    }

    public RealRandomMapBuilder setNumberOfColumn(int numberOfColumn) {
        mNumberOfColumn = numberOfColumn;
        return this;
    }

    public RealRandomMapBuilder setBase(int base) {
        mBase = base;
        return this;
    }

    public RealRandomMapBuilder setFormat(String formatType) {
        mFormat = formatType;
        return this;
    }

    public RealRandomMapBuilder setRandomType(String randomType) {
        mRandomType = randomType;
        return this;
    }


    public RealRandomMapBuilder setDefaultSetting(int number, int max) {
        this.setNumber(number)
            .setMin(DEFAULT_MIN)
            .setMax(max)
            .setNumberOfColumn(DEFAULT_NUMBER_OF_COLUMN)
            .setBase(DEFAULT_BASE)
            .setFormat(FORMAT_PLAIN)
            .setRandomType(RANDOM_TYPE_NEW);

        return this;
    }


    public Map<String, String> build() {
        Map<String,String> map = new HashMap<>();

        if (mNumber > 0) {
            map.put(NUMBER, String.valueOf(mNumber));
        }

        if (mMin > 0) {
            map.put(MIN, String.valueOf(mMin));
        }

        if (mMax > 0) {
            map.put(MAX, String.valueOf(mMax));
        }

        if (mNumberOfColumn > 0) {
            map.put(COLUMN, String.valueOf(mNumberOfColumn));
        }

        if (mBase > 0) {
            map.put(BASE, String.valueOf(mBase));
        }

        if (!mFormat.isEmpty()) {
            map.put(FORMAT, mFormat);
        }

        if (!mRandomType.isEmpty()) {
            map.put(RANDOM, mRandomType);
        }

        return map;
    }


}
