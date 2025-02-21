package addfriend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal {

    private static JTextArea contactList;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Gestor de Contactos");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Bienvenido al Gestor de Contactos");

        JButton addButton = new JButton("Añadir Contacto");
        JButton displayButton = new JButton("Mostrar Contactos");
        JButton updateButton = new JButton("Actualizar Contacto");
        JButton deleteButton = new JButton("Eliminar Contacto");

        // JTextArea para mostrar la lista de contactos
        contactList = new JTextArea();
        contactList.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(contactList);
        scrollPane.setPreferredSize(new Dimension(350, 150));

        panel.add(label);
        panel.add(addButton);
        panel.add(displayButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(scrollPane);

        frame.add(panel);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setVisible(true);

        addButton.addActionListener(e -> {
            String newName = JOptionPane.showInputDialog("Ingrese el nombre del contacto:");
            long newNumber = Long.parseLong(JOptionPane.showInputDialog("Ingrese el número del contacto:"));
            AddFriend.addContact(newName, newNumber);
        });

        displayButton.addActionListener(e -> {
            // Limpiamos la lista antes de mostrar los contactos
            contactList.setText("");
            DisplayFriends.displayContacts();
        });

        updateButton.addActionListener(e -> {
            String inputName = JOptionPane.showInputDialog("Ingrese el nombre del contacto a actualizar:");
            long newNumber = Long.parseLong(JOptionPane.showInputDialog("Ingrese el nuevo número del contacto:"));
            UpdateFriend.updateContact(inputName, newNumber);
        });

        deleteButton.addActionListener(e -> {
            String inputName = JOptionPane.showInputDialog("Ingrese el nombre del contacto a eliminar:");
            DeleteFriend.deleteContact(inputName);
        });
    }

    public static void updateContactList(String contactInfo) {
        contactList.append(contactInfo + "\n");
    }
}

