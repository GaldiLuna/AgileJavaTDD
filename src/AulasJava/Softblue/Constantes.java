package AulasJava.Softblue;

public class Constantes {
    private static double AV1;
    private static double AV2;
    private static double AV3;
    private static double AV4;
    public static final double MEDIA_FINAL;
    //public static double mediaProvas;
    public static final String APROVADO;
    public static final String REPROVADO;
    public static final String WINNER;
    public static final String LOSER;

    static {
        MEDIA_FINAL = 7.0;
        APROVADO = "Aprovado";
        REPROVADO = "Reprovado";
        WINNER = "Parabéns você subiu de nível!";
        LOSER = "Estude mais no próximo ano!";
    }

    public static double mediaProvas() {
        return (AV1 + AV2 + AV3 + AV4) / 4;
    }

    public static double getAV1() {
        return AV1;
    }

    public static void setAV1(double w) {
        AV1 = w;
    }

    public static double getAV2() {
        return AV2;
    }

    public static void setAV2(double x) {
        AV2 = x;
    }

    public static double getAV3() {
        return AV3;
    }

    public static void setAV3(double y) {
        AV3 = y;
    }

    public static double getAV4() {
        return AV4;
    }

    public static void setAV4(double z) {
        AV4 = z;
    }
}
