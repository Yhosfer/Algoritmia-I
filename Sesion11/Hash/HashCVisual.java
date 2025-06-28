package Sesion11.Hash;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HashCVisual<T> extends JFrame {
    private HashC<T> hashT;
    private JTable visualT;
    private DefaultTableModel modeloTabla;
    private JTextField campoKey;
    private JTextField campoValue;
    private JLabel labelEstado;
    private final int tamaño;

    public HashCVisual(int tamaño) {

        this.tamaño = tamaño;
        this.hashT = new HashC<>(tamaño);
        initComponents();
        setupUI();
        actualizarVisualizacion();

    }

    private void initComponents() {

        setTitle("Hash Cerrado - Visualizador Interactivo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel panelOperaciones = crearPanelOperaciones();
        panelPrincipal.add(panelOperaciones, BorderLayout.NORTH);

        JPanel panelVisualizacion = crearPanelVisualizacion();
        panelPrincipal.add(panelVisualizacion, BorderLayout.CENTER);

        add(panelPrincipal, BorderLayout.CENTER);

        labelEstado = new JLabel("Listo para operaciones");
        labelEstado.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        labelEstado.setOpaque(true);
        labelEstado.setBackground(new Color(230, 255, 230));
        add(labelEstado, BorderLayout.SOUTH);

    }

    private JPanel crearPanelOperaciones() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Operaciones Hash Cerrado"));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Clave (Key):"), gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        campoKey = new JTextField(10);
        campoKey.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(campoKey, gbc);

        gbc.gridx = 2; gbc.gridy = 0;
        panel.add(new JLabel("Valor (Value):"), gbc);

        gbc.gridx = 3; gbc.gridy = 0;
        campoValue = new JTextField(10);
        campoValue.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(campoValue, gbc);

        JPanel panelBotones = new JPanel(new FlowLayout());

        JButton btnInsertar = crearBoton("Insertar", new Color(76, 175, 80));
        JButton btnEliminar = crearBoton("Eliminar", new Color(244, 67, 54));
        JButton btnLimpiar = crearBoton("Limpiar Todo", new Color(156, 39, 176));

        panelBotones.add(btnInsertar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 4;
        gbc.insets = new Insets(10, 0, 0, 0);
        panel.add(panelBotones, gbc);


        btnInsertar.addActionListener(e -> insertar());
        btnEliminar.addActionListener(e -> eliminar());
        btnLimpiar.addActionListener(e -> limpiarTodo());

        campoKey.addActionListener(e -> insertar());
        campoValue.addActionListener(e -> insertar());

        return panel;
    }

    private JPanel crearPanelVisualizacion() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("Visualización de la Tabla Hash (Tamaño: " + tamaño + ")");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setForeground(new Color(51, 102, 153));
        titulo.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titulo, BorderLayout.NORTH);

        String[] columnas = {"Índice", "Estado", "Clave", "Valor", "Hash"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        visualT = new JTable(modeloTabla);
        visualT.setFont(new Font("Monospaced", Font.PLAIN, 12));
        visualT.setRowHeight(25);
        visualT.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        visualT.getTableHeader().setBackground(new Color(51, 102, 153));
        visualT.getTableHeader().setForeground(Color.WHITE);

        visualT.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (!isSelected) {
                    String estado = (String) table.getValueAt(row, 1);
                    switch (estado) {
                        case "OCUPADO":
                            setBackground(new Color(200, 255, 200)); // Verde claro
                            break;
                        case "VACÍO":
                            setBackground(new Color(240, 240, 240)); // Gris claro
                            break;
                        case "BORRADO":
                            setBackground(new Color(255, 220, 220)); // Rojo claro
                            break;
                        default:
                            setBackground(Color.WHITE);
                    }
                } else {
                    setBackground(table.getSelectionBackground());
                }

                setHorizontalAlignment(JLabel.CENTER);
                return this;
            }
        });

        JScrollPane scrollPane = new JScrollPane(visualT);
        scrollPane.setPreferredSize(new Dimension(600, 300));
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(color.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(color);
            }
        });

        return btn;
    }

    private void insertar() {
        try {
            String keyText = campoKey.getText().trim();
            String valueText = campoValue.getText().trim();

            if (keyText.isEmpty()) {
                mostrarError("Por favor ingresa una clave");
                return;
            }

            if (valueText.isEmpty()) {
                mostrarError("Por favor ingresa un valor");
                return;
            }

            int clave = Integer.parseInt(keyText);
            Register<T> registro = new Register<>(clave, (T) valueText);

            int posicion = hashT.insertar(registro);

            if (posicion != -1) {
                mostrarExito("Elemento insertado en posición " + posicion);
                actualizarVisualizacion();
                limpiarCampos();
            } else {
                mostrarError("No se pudo insertar: tabla llena");
            }

        } catch (NumberFormatException e) {
            mostrarError("La clave debe ser un número entero");
        }
    }

    private void eliminar() {
        try {
            String keyText = campoKey.getText().trim();

            if (keyText.isEmpty()) {
                mostrarError("Por favor ingresa una clave para eliminar");
                return;
            }

            int clave = Integer.parseInt(keyText);
            boolean eliminado = hashT.eliminar(clave);

            if (eliminado) {
                mostrarExito("Elemento eliminado correctamente");
                actualizarVisualizacion();
                limpiarCampos();
            } else {
                mostrarError("Elemento no encontrado para eliminar");
            }

        } catch (NumberFormatException e) {
            mostrarError("La clave debe ser un número entero");
        }
    }

    private void limpiarTodo() {
        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de que quieres limpiar toda la tabla?",
                "Confirmar limpieza",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            hashT = new HashC<>(tamaño);
            actualizarVisualizacion();
            limpiarCampos();
            mostrarExito("Tabla limpiada completamente");
        }
    }

    private void actualizarVisualizacion() {
        modeloTabla.setRowCount(0);

        try {
            java.lang.reflect.Field campoTabla = HashC.class.getDeclaredField("tabla");
            campoTabla.setAccessible(true);
            Object[] tabla = (Object[]) campoTabla.get(hashT);

            for (int i = 0; i < tabla.length; i++) {
                Object elemento = tabla[i];

                java.lang.reflect.Field campoEstado = elemento.getClass().getDeclaredField("estado");
                campoEstado.setAccessible(true);
                int estado = (Integer) campoEstado.get(elemento);

                java.lang.reflect.Field campoRegistro = elemento.getClass().getDeclaredField("registro");
                campoRegistro.setAccessible(true);
                Object registro = campoRegistro.get(elemento);

                String estadoTexto;
                String clave = "-";
                String valor = "-";
                String hash = "-";

                switch (estado) {
                    case 0:
                        estadoTexto = "VACÍO";
                        break;
                    case -1:
                        estadoTexto = "BORRADO";
                        break;
                    case 1:
                        estadoTexto = "OCUPADO";
                        if (registro != null) {
                            Register<?> reg = (Register<?>) registro;
                            clave = String.valueOf(reg.getClave());
                            valor = reg.getValor().toString();
                            hash = String.valueOf(reg.getClave() % tamaño);
                        }
                        break;
                    default:
                        estadoTexto = "DESCONOCIDO";
                }

                modeloTabla.addRow(new Object[]{i, estadoTexto, clave, valor, hash});
            }

        } catch (Exception e) {
            System.err.println("Error al actualizar visualización: " + e.getMessage());
        }
    }

    private void limpiarCampos() {
        campoKey.setText("");
        campoValue.setText("");
        campoKey.requestFocus();
    }

    private void mostrarExito(String mensaje) {
        labelEstado.setText(mensaje);
        labelEstado.setBackground(new Color(230, 255, 230));
        labelEstado.setForeground(new Color(0, 128, 0));
    }

    private void mostrarError(String mensaje) {
        labelEstado.setText(mensaje);
        labelEstado.setBackground(new Color(255, 230, 230));
        labelEstado.setForeground(new Color(178, 34, 34));
    }

    private void setupUI() {
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(true);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {

        }
    }


}