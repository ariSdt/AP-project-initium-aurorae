import ViewPage.LoginSignupPage;

import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {

        System.out.println("\u001B[34m" + "Welcome to: ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        printAsciiArt();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\u001B[0m");
        LoginSignupPage.run();
    }

    private static void printAsciiArt(){
        System.out.println("8888888                   888             888    d8P  d8b 888                \n" +
                "  888                     888             888   d8P   Y8P 888                \n" +
                "  888                     888             888  d8P        888                \n" +
                "  888   88888b.  .d8888b  888888  8888b.  888d88K     888 888  .d88b.        \n" +
                "  888   888 \"88b 88K      888        \"88b 8888888b    888 888 d88\"\"88b       \n" +
                "  888   888  888 \"Y8888b. 888    .d888888 888  Y88b   888 888 888  888       \n" +
                "  888   888  888      X88 Y88b.  888  888 888   Y88b  888 888 Y88..88P       \n" +
                "8888888 888  888  88888P'  \"Y888 \"Y888888 888    Y88b 888 888  \"Y88P\"        ");
    }
}
