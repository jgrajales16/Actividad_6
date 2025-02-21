package addfriend;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class AddFriend {

    public static void addContact(String newName, long newNumber) {
        try {
            String nameNumberString = newName + "!" + newNumber;

            // Crear archivo en la misma carpeta donde se ejecuta el programa
            File file = new File("friendsContact.txt");



            if (!file.exists()) {
                file.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");

            // Mover el puntero al final del archivo
            raf.seek(raf.length());

            // Agregar el contacto en una nueva línea
            raf.writeBytes(nameNumberString);
            raf.writeBytes(System.lineSeparator());

            System.out.println("Contacto agregado correctamente.");

            raf.close();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
