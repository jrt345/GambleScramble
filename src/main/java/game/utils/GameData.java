package game.utils;

import game.controllers.Controller;

import java.io.*;

public class GameData {

    public static final String userDataDir = System.getProperty("user.dir").concat("\\userdata\\");

    public static void serialize() throws IOException {
        FileOutputStream fileOut = new FileOutputStream(userDataDir.concat("player.dat"));
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(Controller.getPlayer());
        out.close();
        fileOut.close();
    }

    public static Player deserialize() throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(userDataDir.concat("player.dat"));
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Player player = (Player) in.readObject();
        in.close();
        fileIn.close();

        return player;
    }
}
