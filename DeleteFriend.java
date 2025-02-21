package addfriend;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DeleteFriend {

    public static void deleteContact(String inputName) {
        try {
            String nameNumberString;
            String name;
            long number;

            File file = new File("friendsContact.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            // Crear un archivo temporal
            File tmpFile = new File("friendsContact.txt");

            // Abrir el archivo en modo lectura/escritura
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            boolean found = false;

            // Verificar si el nombre del contacto existe
            while (raf.getFilePointer() < raf.length()) {
                nameNumberString = raf.readLine();
                if (nameNumberString == null) break; // Manejo de EOF
                String[] lineSplit = nameNumberString.split("!");
                name = lineSplit[0];
                number = Long.parseLong(lineSplit[1]);

                if (name.equals(inputName)) {
                    found = true;
                    break;
                }
            }

            // Eliminar el contacto si existe
            if (found) {
                // Abrir el archivo temporal en modo lectura/escritura
                RandomAccessFile tmpRaf = new RandomAccessFile(tmpFile, "rw");

                // Mover el puntero al inicio del archivo original
                raf.seek(0);

                // Recorrer el archivo original y escribir en el archivo temporal (excepto el contacto a eliminar)
                while (raf.getFilePointer() < raf.length()) {
                    nameNumberString = raf.readLine();
                    if (nameNumberString == null) break; // Manejo de EOF
                    if (!nameNumberString.startsWith(inputName)) {
                        tmpRaf.writeBytes(nameNumberString);
                        tmpRaf.writeBytes(System.lineSeparator());
                    }
                }

                // Cerrar los recursos
                raf.close();
                tmpRaf.close();

                // Eliminar el archivo original
                file.delete();

                // Renombrar el archivo temporal al nombre original
                tmpFile.renameTo(file);

                System.out.println("¡Contacto eliminado!");
            } else {
                System.out.println("El nombre ingresado no existe.");
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}

