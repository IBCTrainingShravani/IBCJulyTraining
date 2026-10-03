package sample;

public class InitializationBlocksDemo {
    private static String globalFestivalName;
    private int customStallId;

    //static initialization block
    static {
        System.out.println("[STATIC BLOCK] Executing once when class bytecode loads into JVM.");
        globalFestivalName = "Mega Diwali Mela";
    }

    // 2. INSTANCE INITIALIZATION BLOCK
    {
        System.out.println("[INSTANCE BLOCK] Executing automatically before constructor runs.");
        customStallId = 5000; 
    }

    // 3. CONSTRUCTOR
    public InitializationBlocksDemo(int explicitId) {
        System.out.println("[CONSTRUCTOR] Executing main constructor body logic.");
        this.customStallId = explicitId; 
    }

    public static void main(String[] args) {
        System.out.println("\n--- Starting Main Method Execution ---");
        System.out.println("Global Festival Context Name: " + globalFestivalName);

        System.out.println("\nCreating Stall Instance 1:");
        InitializationBlocksDemo stallOne = new InitializationBlocksDemo(101);
        System.out.println("Stall One Final ID: " + stallOne.customStallId);

        System.out.println("\nCreating Stall Instance 2:");
        InitializationBlocksDemo stallTwo = new InitializationBlocksDemo(202);
        System.out.println("Stall Two Final ID: " + stallTwo.customStallId);
    }

}
