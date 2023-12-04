package Days;

public abstract class Day {
    public static final Boolean PRINT_STATEMENT_PART_1 = Boolean.TRUE;
    public static final Boolean PRINT_STATEMENT_PART_2 = Boolean.TRUE;
    private int currentDay;

    public Day(int currentDay) {
        this.currentDay = currentDay;
    }

    public String getInputPath() {
        return "src/Days/Day%d/input.txt".formatted(this.currentDay);
    }

    public abstract void exec();

    public void printStatementPart1() {
        if (PRINT_STATEMENT_PART_1) {
            printStatement(1);
        }
    }

    public void printStatementPart2() {
        if (PRINT_STATEMENT_PART_2) {
            printStatement(2);
        }
    }

    private void printStatement(int part) {
        System.out.println();
        System.out.println();
        System.out.println("**************************");
        System.out.println("    Day "+ currentDay +" - Part " + part);
        System.out.println("**************************");
        System.out.println(Util.readFileOneLine("src/Days/Day%d/statementPart%d.txt".formatted(this.currentDay, part)));
        System.out.println("**************************");
        System.out.println();
        System.out.println();
    }

}
