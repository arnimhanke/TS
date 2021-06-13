package de.hanke.arnim.TSTool;

public enum Raster {


    PT15M("PT15M"),

//    PT1S("PT1S"),

    PT15S("PT15S"),

    PT1D("PT1D"),

    PT1H("PT1H");

    private String value;

    Raster(String value) {
        this.value = value;
    }

    public static Raster fromValue(String text) {
        for (Raster b : Raster.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }

    public static int getDuration(Raster raster) {

        switch (raster) {
            case PT1D:
                return 60 * 60 * 24 * 1000;
            case PT1H:
                return 60 * 60 * 1000;
            case PT15M:
                return 60 * 15 * 1000;
            case PT15S:
                return 15 * 1000;
//            case PT1S:
//                return 1 * 1000;
        }

        return 0;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
