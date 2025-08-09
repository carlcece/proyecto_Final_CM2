package main;

import entidades.Animal;
import entidades.Ubicacion;
import config.Configuracion;
import util.Coordenada;

import javax.swing.*;
import java.awt.*;

public class VentanaSimulacion extends JFrame {

    private Isla isla;
    private PanelIsla panelIsla;
    private JButton btnIniciar;
    private JButton btnDetener;
    private JLabel lblDimensionesIsla;

    // Cambiamos a dos JTextArea, uno para stats fijas y otro para el historial
    private JTextArea areaEstadisticasFijas;
    private JTextArea areaHistorialEventos;


    public VentanaSimulacion(Isla isla) {
        this.isla = isla;
        setTitle("Simulación de Ecosistema en la Isla");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 1000);
        setLayout(new BorderLayout());

        JPanel panelEstadisticasRapidas = new JPanel();
        panelEstadisticasRapidas.setLayout(new FlowLayout());

        // NUEVO: Inicializa la etiqueta de dimensiones con los valores de Configuracion
        lblDimensionesIsla = new JLabel("Dimensiones de la Isla: " + Configuracion.FILAS_ISLA + "x" + Configuracion.COLUMNAS_ISLA);

        panelEstadisticasRapidas.add(lblDimensionesIsla);
        add(panelEstadisticasRapidas, BorderLayout.NORTH);

        panelIsla = new PanelIsla();
        add(panelIsla, BorderLayout.CENTER);

        // Panel de estadísticas a la derecha
        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new BorderLayout());

        // 1. JTextArea para las estadísticas fijas (totales, etc.)
        areaEstadisticasFijas = new JTextArea();
        areaEstadisticasFijas.setEditable(false);
        areaEstadisticasFijas.setBorder(BorderFactory.createTitledBorder("Resumen del Ciclo"));
        panelDerecho.add(areaEstadisticasFijas, BorderLayout.NORTH);

        // 2. JTextArea para el historial de eventos (desplazable)
        areaHistorialEventos = new JTextArea();
        areaHistorialEventos.setEditable(false);
        JScrollPane scrollHistorial = new JScrollPane(areaHistorialEventos);
        panelDerecho.add(scrollHistorial, BorderLayout.CENTER);

        add(panelDerecho, BorderLayout.EAST);

        JPanel panelControles = new JPanel();
        btnIniciar = new JButton("Iniciar");
        btnDetener = new JButton("Detener");

        panelControles.add(btnIniciar);
        panelControles.add(btnDetener);
        add(panelControles, BorderLayout.SOUTH);

        btnIniciar.addActionListener(e -> SimulacionIsla.iniciarSimulacion());
        btnDetener.addActionListener(e -> SimulacionIsla.detenerSimulacion());

        //pack();
        setVisible(true);
    }

    public void actualizarContadores(long totalAnimales, long totalPlantas, long nacimientos, long fallecimientos, double kgConsumidos) {
        SwingUtilities.invokeLater(() -> {
        });
    }

    public void repaintPanelIsla() {
        panelIsla.repaint();
    }

    // Este método es para el historial desplazable
    public void actualizarHistorial(String texto) {
        SwingUtilities.invokeLater(() -> areaHistorialEventos.setText(texto));
    }

    // NUEVO: Método para actualizar solo las estadísticas fijas
    public void actualizarEstadisticasFijas(String texto) {
        SwingUtilities.invokeLater(() -> areaEstadisticasFijas.setText(texto));
    }

    private class PanelIsla extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int celdaAncho = getWidth() / Configuracion.COLUMNAS_ISLA;
            int celdaAlto = getHeight() / Configuracion.FILAS_ISLA;

            Font fuente = new Font("SansSerif", Font.PLAIN, Math.min(celdaAncho, celdaAlto));
            g.setFont(fuente);

            for (int r = 0; r < Configuracion.FILAS_ISLA; r++) {
                for (int c = 0; c < Configuracion.COLUMNAS_ISLA; c++) {
                    Ubicacion ubicacion = isla.getUbicacion(new Coordenada(r, c));
                    if (ubicacion != null) {
                        String simbolo = "";
                        Color colorFondo;

                        // NUEVO: Verifica el tipo de terreno de la ubicación
                        String terreno = ubicacion.getTerreno();
                        if (terreno.equals(Configuracion.AGUA)) {
                            colorFondo = Configuracion.COLORES_TERRENO.get(Configuracion.AGUA);
                            simbolo = Configuracion.UNICODE_SIMBOLOS.getOrDefault("Agua", "🌊");
                        } else {
                            // 1. Prioridad: ¿Hay un animal?
                            if (!ubicacion.getAnimales().isEmpty()) {
                                Animal animal = ubicacion.getAnimales().get(0);
                                simbolo = Configuracion.UNICODE_SIMBOLOS.getOrDefault(animal.getEspecie(), "?");
                                colorFondo = Color.CYAN;
                                // 2. Segunda prioridad: ¿Es una planta letal?
                            } else if (ubicacion.getPlantas().getCantidad() > 0 && ubicacion.getPlantas().getTipo().equals(Configuracion.PLANTA_LETAL)) {
                                simbolo = Configuracion.UNICODE_SIMBOLOS.getOrDefault(Configuracion.PLANTA_LETAL, "🌹");
                                colorFondo = Color.RED; // Un color que resalte el peligro
                                // 3. Tercera prioridad: ¿Es una planta normal?
                            } else if (ubicacion.getPlantas().getCantidad() > 0) {
                                simbolo = Configuracion.UNICODE_SIMBOLOS.getOrDefault("Planta", "🌿");
                                colorFondo = Color.GREEN;
                            } else {
                                colorFondo = new Color(139, 69, 19);
                                }
                            }
                            g.setColor(colorFondo);
                            g.fillRect(c * celdaAncho, r * celdaAlto, celdaAncho, celdaAlto);

                            g.setColor(Color.BLACK);
                            g.drawRect(c * celdaAncho, r * celdaAlto, celdaAncho, celdaAlto);

                            if (!simbolo.isEmpty()) {
                                g.setColor(Color.BLACK);
                                FontMetrics fm = g.getFontMetrics(g.getFont());
                                int x = c * celdaAncho + (celdaAncho - fm.stringWidth(simbolo)) / 2;
                                int y = r * celdaAlto + (celdaAlto + fm.getAscent()) / 2;
                                g.drawString(simbolo, x, y);
                            }
                        }
                    }
                }
            }
        }

    }