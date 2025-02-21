package addfriend;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class UpdateFriend {

    public static void updateContact(String inputName, long newNumber) {
        try {
            String nameNumberString;
            String name;
            long number;
            int index;

            File file = new File("friendsContact.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            // Crear un archivo temporal
            File tmpFile = new File("friendsContact.txt");

            // Abrir el archivo en modo lectura/escritura
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            boolean found = false;

            // Verificar si el nombre del contacto ya existe
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

            // Actualizar el contacto si existe
            if (found) {
                // Abrir el archivo temporal en modo lectura/escritura
                RandomAccessFile tmpRaf = new RandomAccessFile(tmpFile, "rw");

                // Mover el puntero al inicio del archivo original
                raf.seek(0);

                // Recorrer el archivo original y escribir en el archivo temporal
                while (raf.getFilePointer() < raf.length()) {
                    nameNumberString = raf.readLine();
                    if (nameNumberString == null) break; // Manejo de EOF
                    index = nameNumberString.indexOf('!');
                    name = nameNumberString.substring(0, index);

                    // Verificar si el contacto actual es el que se va a actualizar
                    if (name.equals(inputName)) {
                        // Actualizar el número de este contacto
                        nameNumberString = name + "!" + String.valueOf(newNumber);
                    }

                    // Escribir el contacto en el archivo temporal
                    tmpRaf.writeBytes(nameNumberString);
                    tmpRaf.writeBytes(System.lineSeparator());
                }

                // Cerrar los recursos
                raf.close();
                tmpRaf.close();

                // Eliminar el archivo original
                file.delete();

                // Renombrar el archivo temporal al nombre original
                tmpFile.renameTo(file);

                System.out.println("¡Contacto actualizado!");
            } else {
                System.out.println("El nombre ingresado no existe.");
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
