package Sesion10.Ejer04Aplicación;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class BTreeGUI extends JFrame {
    private Btree btree;
    private JTextField codigoField;
    private JTextField nombreField;
    private JTextArea outputArea;
    private BTreePanel treePanel;
    private JScrollPane treeScrollPane;

    public BTreeGUI() {
        btree = new Btree(3); // Árbol B de orden 3
        initializeComponents();
        setupLayout();
        setupEventHandlers();
    }

    private void initializeComponents() {
        setTitle("Gestor de Estudiantes - Árbol B");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        // Campos de entrada
        codigoField = new JTextField(10);
        nombreField = new JTextField(20);

        // Panel para visualizar el árbol
        treePanel = new BTreePanel();
        treeScrollPane = new JScrollPane(treePanel);
        treeScrollPane.setPreferredSize(new Dimension(800, 400));
        treeScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        treeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Área de salida para mensajes
        outputArea = new JTextArea(8, 50);
        outputArea.setEditable(false);
        outputArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        outputArea.setBackground(new Color(248, 248, 248));
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        // Panel superior para entrada de datos
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Gestión de Estudiantes"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JButton insertButton = new JButton("Insertar Estudiante");
        JButton searchButton = new JButton("Buscar por Código");
        JButton deleteButton = new JButton("Eliminar por Código");
        JButton clearButton = new JButton("Limpiar Árbol");

        // Agregar tooltips para mejor UX
        insertButton.setToolTipText("Insertar un nuevo estudiante con código y nombre");
        searchButton.setToolTipText("Buscar un estudiante por su código");
        deleteButton.setToolTipText("Eliminar un estudiante por su código");
        clearButton.setToolTipText("Eliminar todos los estudiantes del árbol");

        // Colores para los botones
        insertButton.setBackground(new Color(144, 238, 144)); // Verde claro
        searchButton.setBackground(new Color(173, 216, 230)); // Azul claro
        deleteButton.setBackground(new Color(255, 182, 193)); // Rosa claro
        clearButton.setBackground(new Color(255, 222, 173)); // Naranja claro

        // Primera fila - campos de entrada
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Código:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(codigoField, gbc);
        gbc.gridx = 2;
        inputPanel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 3;
        inputPanel.add(nombreField, gbc);

        // Segunda fila - botones
        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(insertButton, gbc);
        gbc.gridx = 1;
        inputPanel.add(searchButton, gbc);
        gbc.gridx = 2;
        inputPanel.add(deleteButton, gbc);
        gbc.gridx = 3;
        inputPanel.add(clearButton, gbc);

        // Panel central para visualización del árbol
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createTitledBorder("Visualización del Árbol B"));
        centerPanel.add(treeScrollPane, BorderLayout.CENTER);

        // Panel inferior para salida de texto
        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBorder(BorderFactory.createTitledBorder("Información del Árbol"));
        outputPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        add(inputPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(outputPanel, BorderLayout.SOUTH);

        // Event handlers
        insertButton.addActionListener(e -> insertStudent());
        searchButton.addActionListener(e -> searchStudent());
        deleteButton.addActionListener(e -> deleteStudent());
        clearButton.addActionListener(e -> clearTree());
    }

    private void setupEventHandlers() {
        // Enter key en los campos de texto
        ActionListener enterAction = e -> insertStudent();
        codigoField.addActionListener(enterAction);
        nombreField.addActionListener(enterAction);
    }

    private void insertStudent() {
        try {
            String codigoText = codigoField.getText().trim();
            String nombreText = nombreField.getText().trim();

            if (codigoText.isEmpty() || nombreText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int codigo = Integer.parseInt(codigoText);
            RegistroEstudiante estudiante = new RegistroEstudiante(codigo, nombreText);

            btree.insert(estudiante);

            // Limpiar campos
            codigoField.setText("");
            nombreField.setText("");
            codigoField.requestFocus();

            // Actualizar visualización
            updateDisplay();

            outputArea.append("✓ INSERTADO: " + estudiante + "\n");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El código debe ser un número válido.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchStudent() {
        try {
            String codigoText = codigoField.getText().trim();

            if (codigoText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un código para buscar.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int codigo = Integer.parseInt(codigoText);
            RegistroEstudiante estudiante = new RegistroEstudiante(codigo, "");

            // Redirigir la salida del sistema para capturar el mensaje de búsqueda
            java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
            java.io.PrintStream originalOut = System.out;
            System.setOut(new java.io.PrintStream(baos));

            boolean found = btree.search(estudiante);

            // Restaurar la salida original
            System.setOut(originalOut);
            String searchMessage = baos.toString().trim();

            if (found) {
                String nombre = btree.buscarNombre(codigo);
                outputArea.append("✓ ENCONTRADO: " + codigo + " - " + nombre + "\n");
                if (!searchMessage.isEmpty()) {
                    outputArea.append("  Ubicación: " + searchMessage + "\n");
                }

                // Mostrar información adicional en dialog
                JOptionPane.showMessageDialog(this,
                        "Estudiante encontrado:\n" +
                                "Código: " + codigo + "\n" +
                                "Nombre: " + nombre + "\n" +
                                "Ubicación: " + searchMessage,
                        "Búsqueda Exitosa",
                        JOptionPane.INFORMATION_MESSAGE);

                // Llenar el campo nombre si está vacío
                if (nombreField.getText().trim().isEmpty()) {
                    nombreField.setText(nombre);
                }
            } else {
                outputArea.append("✗ NO ENCONTRADO: Código " + codigo + "\n");
                JOptionPane.showMessageDialog(this,
                        "No se encontró ningún estudiante con el código: " + codigo,
                        "Estudiante No Encontrado",
                        JOptionPane.WARNING_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El código debe ser un número válido.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteStudent() {
        try {
            String codigoText = codigoField.getText().trim();

            if (codigoText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un código para eliminar.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int codigo = Integer.parseInt(codigoText);

            // Primero verificar si el estudiante existe
            String nombre = btree.buscarNombre(codigo);
            if (nombre.equals("No encontrado")) {
                JOptionPane.showMessageDialog(this,
                        "No se puede eliminar. No existe un estudiante con el código: " + codigo,
                        "Error de Eliminación",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Confirmar eliminación
            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro de que desea eliminar al estudiante?\n\n" +
                            "Código: " + codigo + "\n" +
                            "Nombre: " + nombre + "\n\n" +
                            "Esta acción no se puede deshacer.",
                    "Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (confirmacion == JOptionPane.YES_OPTION) {
                RegistroEstudiante estudiante = new RegistroEstudiante(codigo, nombre);
                btree.delete(estudiante);
                updateDisplay();

                outputArea.append("✓ ELIMINADO: " + codigo + " - " + nombre + "\n");

                // Limpiar campos
                codigoField.setText("");
                nombreField.setText("");
                codigoField.requestFocus();

                JOptionPane.showMessageDialog(this,
                        "Estudiante eliminado exitosamente:\n" + codigo + " - " + nombre,
                        "Eliminación Exitosa",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El código debe ser un número válido.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearTree() {
        int option = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de que desea limpiar todo el árbol?",
                "Confirmar", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            btree = new Btree(3);
            updateDisplay();
            outputArea.append("🌳 ÁRBOL LIMPIADO - Todos los estudiantes eliminados.\n");
        }
    }

    private void updateDisplay() {
        treePanel.setBTree(btree);
        treePanel.repaint();

        // Actualizar área de texto con representación tabular
        outputArea.append("\n" + btree.toString() + "\n");
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }

    // Clase interna para dibujar el árbol
    private class BTreePanel extends JPanel {
        private Btree tree;
        private Map<BNode, Point> nodePositions;
        private final int NODE_WIDTH = 120;
        private final int NODE_HEIGHT = 50;
        private final int LEVEL_HEIGHT = 100;
        private final int MIN_HORIZONTAL_SPACING = 140;

        public BTreePanel() {
            setBackground(Color.WHITE);
            nodePositions = new HashMap<>();
        }

        public void setBTree(Btree tree) {
            this.tree = tree;
            calculatePositions();
        }

        private void calculatePositions() {
            nodePositions.clear();
            if (tree == null || tree.isEmpty()) {
                setPreferredSize(new Dimension(400, 200));
                return;
            }

            // Asignar IDs a los nodos
            tree.assignIds(tree.root, -1);

            // Calcular el ancho total necesario para cada nivel
            int totalWidth = calculateSubtreeWidth(tree.root);
            int startX = Math.max(totalWidth / 2, 100);

            // Posicionar nodos comenzando desde la raíz
            calculateNodePositions(tree.root, startX, 50);

            // Ajustar tamaño del panel basado en las posiciones calculadas
            int maxX = nodePositions.values().stream()
                    .mapToInt(p -> p.x).max().orElse(400) + NODE_WIDTH + 50;
            int maxY = nodePositions.values().stream()
                    .mapToInt(p -> p.y).max().orElse(200) + NODE_HEIGHT + 50;

            setPreferredSize(new Dimension(Math.max(maxX, 600), Math.max(maxY, 300)));
            revalidate();
        }

        private int calculateSubtreeWidth(BNode node) {
            if (node == null) return 0;

            // Contar hijos no nulos
            java.util.List<BNode> children = new java.util.ArrayList<>();
            for (BNode child : node.childs) {
                if (child != null) {
                    children.add(child);
                }
            }

            if (children.isEmpty()) {
                // Nodo hoja: ancho mínimo
                return MIN_HORIZONTAL_SPACING;
            }

            // Nodo interno: suma del ancho de todos los subárboles hijos
            int totalChildWidth = 0;
            for (BNode child : children) {
                totalChildWidth += calculateSubtreeWidth(child);
            }

            // El ancho del subárbol es el máximo entre el ancho mínimo y la suma de hijos
            return Math.max(MIN_HORIZONTAL_SPACING, totalChildWidth);
        }

        private void calculateNodePositions(BNode node, int centerX, int y) {
            if (node == null) return;

            // Posicionar el nodo actual
            nodePositions.put(node, new Point(centerX - NODE_WIDTH/2, y));

            // Obtener hijos no nulos
            java.util.List<BNode> children = new java.util.ArrayList<>();
            for (BNode child : node.childs) {
                if (child != null) {
                    children.add(child);
                }
            }

            if (children.isEmpty()) return;

            // Calcular posiciones de los hijos
            int nextY = y + LEVEL_HEIGHT;

            // Calcular el ancho total requerido por todos los hijos
            int totalRequiredWidth = 0;
            for (BNode child : children) {
                totalRequiredWidth += calculateSubtreeWidth(child);
            }

            // Posición inicial para el primer hijo
            int startX = centerX - totalRequiredWidth / 2;
            int currentX = startX;

            // Posicionar cada hijo
            for (BNode child : children) {
                int childWidth = calculateSubtreeWidth(child);
                int childCenterX = currentX + childWidth / 2;

                calculateNodePositions(child, childCenterX, nextY);

                currentX += childWidth;
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (tree == null || tree.isEmpty()) {
                g.setColor(Color.GRAY);
                g.setFont(new Font("Arial", Font.PLAIN, 16));
                g.drawString("Árbol vacío", 50, 100);
                return;
            }

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Dibujar conexiones primero
            drawConnections(g2d, tree.root);

            // Dibujar nodos
            drawNodes(g2d, tree.root);
        }

        private void drawConnections(Graphics2D g2d, BNode node) {
            if (node == null) return;

            Point nodePos = nodePositions.get(node);
            if (nodePos == null || node.count == 0) return;

            g2d.setColor(Color.DARK_GRAY);
            g2d.setStroke(new BasicStroke(2));

            int nodeCenterX = nodePos.x + NODE_WIDTH / 2;
            int nodeBottomY = nodePos.y + NODE_HEIGHT;

            for (BNode child : node.childs) {
                if (child != null && child.count > 0) { // Solo conectar nodos con datos
                    Point childPos = nodePositions.get(child);
                    if (childPos != null) {
                        int childCenterX = childPos.x + NODE_WIDTH / 2;
                        int childTopY = childPos.y;

                        g2d.drawLine(nodeCenterX, nodeBottomY, childCenterX, childTopY);
                    }
                }
            }

            // Recursivamente dibujar conexiones de hijos
            for (BNode child : node.childs) {
                if (child != null && child.count > 0) {
                    drawConnections(g2d, child);
                }
            }
        }

        private void drawNodes(Graphics2D g2d, BNode node) {
            if (node == null) return;

            Point pos = nodePositions.get(node);
            if (pos == null) return;

            // Verificar que el nodo tenga datos válidos
            if (node.count == 0) {
                // Nodo vacío - no dibujarlo
                return;
            }

            // Dibujar rectángulo del nodo
            g2d.setColor(new Color(173, 216, 230)); // Azul claro
            g2d.fillRoundRect(pos.x, pos.y, NODE_WIDTH, NODE_HEIGHT, 10, 10);

            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawRoundRect(pos.x, pos.y, NODE_WIDTH, NODE_HEIGHT, 10, 10);

            // Dibujar las claves
            g2d.setFont(new Font("Arial", Font.BOLD, 11));
            FontMetrics fm = g2d.getFontMetrics();

            // Crear texto de las claves más compacto
            StringBuilder keys = new StringBuilder();
            for (int i = 0; i < node.count; i++) {
                if (i > 0) keys.append(" | ");
                RegistroEstudiante reg = node.keys.get(i);
                if (reg != null) {
                    keys.append(reg.getCodigo());
                }
            }

            String keyText = keys.toString();
            if (!keyText.isEmpty()) {
                int textWidth = fm.stringWidth(keyText);
                int textX = pos.x + (NODE_WIDTH - textWidth) / 2;
                int textY = pos.y + (NODE_HEIGHT + fm.getAscent()) / 2 - 5;

                g2d.setColor(Color.BLACK);
                g2d.drawString(keyText, textX, textY);
            }

            // Dibujar ID del nodo (más pequeño y en mejor posición)
            g2d.setFont(new Font("Arial", Font.PLAIN, 9));
            g2d.setColor(Color.BLUE);
            g2d.drawString("ID:" + node.idNodo, pos.x + 3, pos.y + 12);

            // Recursivamente dibujar hijos
            for (BNode child : node.childs) {
                if (child != null) {
                    drawNodes(g2d, child);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            new BTreeGUI().setVisible(true);
        });
    }
}