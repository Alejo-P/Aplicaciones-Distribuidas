public class ConsoleStyles {
    public static final String RESET = "\u001B[0m";

    // Colores para el texto la consola
    public static final String FG_BLACK = "\u001B[30m";
    public static final String FG_RED = "\u001B[31m";
    public static final String FG_GREEN = "\u001B[32m";
    public static final String FG_YELLOW = "\u001B[33m";
    public static final String FG_BLUE = "\u001B[34m";
    public static final String FG_PURPLE = "\u001B[35m";
    public static final String FG_CYAN = "\u001B[36m";
    public static final String FG_WHITE = "\u001B[37m";

    // Colores para el fondo de la consola
    public static final String BG_BLACK = "\u001B[40m";
    public static final String BG_RED = "\u001B[41m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String BG_YELLOW = "\u001B[43m";
    public static final String BG_BLUE = "\u001B[44m";
    public static final String BG_PURPLE = "\u001B[45m";
    public static final String BG_CYAN = "\u001B[46m";
    public static final String BG_WHITE = "\u001B[47m";

    // Estilos de texto
    public static final String BOLD = "\u001B[1m";
    public static final String UNDERLINE = "\u001B[4m";
    public static final String REVERSED = "\u001B[7m";

    public void examplePrints() {
        System.out.println(FG_RED + "Texto en rojo" + RESET);
        System.out.println(FG_GREEN + "Texto en verde" + RESET);
        System.out.println(FG_BLUE + "Texto en azul" + RESET);
        System.out.println(FG_YELLOW + "Texto en amarillo" + RESET);
        System.out.println(FG_PURPLE + "Texto en morado" + RESET);
        System.out.println(FG_CYAN + "Texto en cyan" + RESET);
        System.out.println(FG_WHITE + "Texto en blanco" + RESET);

        System.out.println(BG_RED + "Fondo rojo" + RESET);
        System.out.println(BG_GREEN + "Fondo verde" + RESET);
        System.out.println(BG_BLUE + "Fondo azul" + RESET);
        System.out.println(BG_YELLOW + "Fondo amarillo" + RESET);
        System.out.println(BG_PURPLE + "Fondo morado" + RESET);
        System.out.println(BG_CYAN + "Fondo cyan" + RESET);
        System.out.println(BG_WHITE + "Fondo blanco" + RESET);

        System.out.println(BOLD + "Texto en negrita" + RESET);
        System.out.println(UNDERLINE + "Texto subrayado" + RESET);
        System.out.println(REVERSED + "Texto invertido" + RESET);
    }
}
