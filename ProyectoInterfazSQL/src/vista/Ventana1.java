/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fernando Ortega Castro
 */

package vista;
import Clases.InventarioCompartido;
import Clases.Jugador;
import Clases.Partida;
import Clases.Servidor;


import Controller.Controller;


import java.awt.Color;
import java.awt.Font;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;


/**
 *
 * @author Patricia Burgos
 */
public class Ventana1 extends javax.swing.JFrame {
    
    Controller controller;

    /**
     * Creates new form Ventana1
     */
    public Ventana1() throws IOException, FileNotFoundException, ClassNotFoundException, NotSerializableException, SAXException {
        initComponents();
        controller=new Controller();
        
        IdServerSeleccionado="";
        IdInventarioSeleccionado="";
        IdJugadorSeleccionado="";
        IdPartidaSeleccionado="";
        antiguaIdServerPartida="";
        
        servidores_vista = controller.getServidores_sistema();
        inventarios_vista = controller.getInventarios_sistema();
        jugadores_vista = controller.getJugadores_sistema();
        partidas_vista = controller.getPartidas_sistema();
        
        table_model_server = (DefaultTableModel) jTable_server.getModel();
        table_model_inventario = (DefaultTableModel) jTable_Inventario.getModel();
        table_model_jugador = (DefaultTableModel) jTable_jugador.getModel();
        table_model_partida = (DefaultTableModel) jTable_Partida.getModel();

        actualizar_vista_server();
        actualizar_vista_inventario();
        actualizar_vista_jugador();
        actualizar_vista_partida();
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane = new javax.swing.JTabbedPane();
        jPanel_biblioteca = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_Partida = new javax.swing.JTable();
        jLabel_numEspectadores = new javax.swing.JLabel();
        jLabel_Servidor = new javax.swing.JLabel();
        jTextField_NumEspectadores = new javax.swing.JTextField();
        jTextField_IdServer_Partida = new javax.swing.JTextField();
        jButton_guardar_partida = new javax.swing.JButton();
        jButton_cancelar_partida = new javax.swing.JButton();
        jButton_aniadir_partida = new javax.swing.JButton();
        jButton_modificar_partida = new javax.swing.JButton();
        jButton_borrar_partida = new javax.swing.JButton();
        jLabel_JugadoresPartida = new javax.swing.JLabel();
        jTextField_JugadoresPartida = new javax.swing.JTextField();
        jPanel_server = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_server = new javax.swing.JTable();
        jLabel_region = new javax.swing.JLabel();
        jTextField_region = new javax.swing.JTextField();
        jButton_guardar_server = new javax.swing.JButton();
        jButton_cancelar_server = new javax.swing.JButton();
        jButton_borrar_server = new javax.swing.JButton();
        jButton_modificar_server = new javax.swing.JButton();
        jButton_aniadir_server = new javax.swing.JButton();
        jPanel_ciudad = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_jugador = new javax.swing.JTable();
        jButton_aniadir_jugador = new javax.swing.JButton();
        jButton_modificar_jugador = new javax.swing.JButton();
        jButton_borrar_jugador = new javax.swing.JButton();
        jLabel_NickName = new javax.swing.JLabel();
        jLabel_nivel = new javax.swing.JLabel();
        jTextField_NickName = new javax.swing.JTextField();
        jTextField_InventariosJugador = new javax.swing.JTextField();
        jButton_guardar_jugador = new javax.swing.JButton();
        jButton_cancelar_jugador = new javax.swing.JButton();
        jTextField_Nivel = new javax.swing.JTextField();
        jLabel_Inventarios = new javax.swing.JLabel();
        jPanel_persona = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_Inventario = new javax.swing.JTable();
        jLabel_nombre_SlotsMaximos = new javax.swing.JLabel();
        jLabel_SlotsOcupados = new javax.swing.JLabel();
        jTextField_SlotsMaximos = new javax.swing.JTextField();
        jTextField_SlotsOcupados = new javax.swing.JTextField();
        jButton_guardar_inventario = new javax.swing.JButton();
        jButton_cancelar_inventario = new javax.swing.JButton();
        jButton_borrar_inventario = new javax.swing.JButton();
        jButton_modificar_inventario = new javax.swing.JButton();
        jButton_aniadir_iventario = new javax.swing.JButton();
        jTextField_JugadoresConAcceso = new javax.swing.JTextField();
        jLabel_JugadoresConAcceso = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTable_Partida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdPartida", "NumEspectadores"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_Partida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_PartidaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable_Partida);
        if (jTable_Partida.getColumnModel().getColumnCount() > 0) {
            jTable_Partida.getColumnModel().getColumn(0).setResizable(false);
            jTable_Partida.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel_numEspectadores.setText("NumEspectadores:");

        jLabel_Servidor.setText("Servidor:");

        jTextField_NumEspectadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_NumEspectadoresActionPerformed(evt);
            }
        });

        jButton_guardar_partida.setText("Guardar");
        jButton_guardar_partida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_guardar_partidaActionPerformed(evt);
            }
        });

        jButton_cancelar_partida.setText("Cancelar");
        jButton_cancelar_partida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancelar_partidaActionPerformed(evt);
            }
        });

        jButton_aniadir_partida.setText("Añadir");
        jButton_aniadir_partida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_aniadir_partidaActionPerformed(evt);
            }
        });

        jButton_modificar_partida.setText("Modificar");
        jButton_modificar_partida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_modificar_partidaActionPerformed(evt);
            }
        });

        jButton_borrar_partida.setText("Borrar");
        jButton_borrar_partida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_borrar_partidaActionPerformed(evt);
            }
        });

        jLabel_JugadoresPartida.setText("Jugadores:");

        javax.swing.GroupLayout jPanel_bibliotecaLayout = new javax.swing.GroupLayout(jPanel_biblioteca);
        jPanel_biblioteca.setLayout(jPanel_bibliotecaLayout);
        jPanel_bibliotecaLayout.setHorizontalGroup(
            jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_bibliotecaLayout.createSequentialGroup()
                .addGroup(jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_bibliotecaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_modificar_partida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_aniadir_partida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_borrar_partida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel_bibliotecaLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_numEspectadores)
                            .addComponent(jLabel_Servidor)
                            .addComponent(jLabel_JugadoresPartida))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_NumEspectadores, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addComponent(jTextField_IdServer_Partida)
                            .addComponent(jTextField_JugadoresPartida))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_guardar_partida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_cancelar_partida, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_bibliotecaLayout.setVerticalGroup(
            jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_bibliotecaLayout.createSequentialGroup()
                .addGroup(jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_bibliotecaLayout.createSequentialGroup()
                        .addGroup(jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_bibliotecaLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jButton_aniadir_partida)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_modificar_partida, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_borrar_partida))
                            .addGroup(jPanel_bibliotecaLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_numEspectadores)
                            .addComponent(jTextField_NumEspectadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton_guardar_partida, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Servidor)
                    .addComponent(jTextField_IdServer_Partida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_cancelar_partida))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_JugadoresPartida)
                    .addComponent(jTextField_JugadoresPartida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(151, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Partida", jPanel_biblioteca);

        jTable_server.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdServer", "Region"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_server.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_serverMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable_server);
        if (jTable_server.getColumnModel().getColumnCount() > 0) {
            jTable_server.getColumnModel().getColumn(0).setResizable(false);
            jTable_server.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel_region.setText("Region:");

        jTextField_region.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_region_servidor(evt);
            }
        });

        jButton_guardar_server.setText("Guardar");
        jButton_guardar_server.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_guardar_Servidor(evt);
            }
        });

        jButton_cancelar_server.setText("Cancelar");
        jButton_cancelar_server.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancelar_serverActionPerformed(evt);
            }
        });

        jButton_borrar_server.setText("Borrar");
        jButton_borrar_server.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_borrar_serverActionPerformed(evt);
            }
        });

        jButton_modificar_server.setText("Modificar");
        jButton_modificar_server.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_modificar_serverActionPerformed(evt);
            }
        });

        jButton_aniadir_server.setText("Añadir");
        jButton_aniadir_server.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_aniadir_serverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_serverLayout = new javax.swing.GroupLayout(jPanel_server);
        jPanel_server.setLayout(jPanel_serverLayout);
        jPanel_serverLayout.setHorizontalGroup(
            jPanel_serverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_serverLayout.createSequentialGroup()
                .addGroup(jPanel_serverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_serverLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_serverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_modificar_server, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_aniadir_server, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_borrar_server, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel_serverLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel_region)
                        .addGap(50, 50, 50)
                        .addComponent(jTextField_region, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addGroup(jPanel_serverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_guardar_server, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_cancelar_server, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_serverLayout.setVerticalGroup(
            jPanel_serverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_serverLayout.createSequentialGroup()
                .addGroup(jPanel_serverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_serverLayout.createSequentialGroup()
                        .addGroup(jPanel_serverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_serverLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jButton_aniadir_server)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_modificar_server, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_borrar_server))
                            .addGroup(jPanel_serverLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel_serverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_region)
                            .addComponent(jTextField_region, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton_guardar_server, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_cancelar_server)
                .addContainerGap(185, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Servidor", jPanel_server);

        jTable_jugador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdPlayer", "Nivel", "NickName"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_jugador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_jugadorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_jugador);
        if (jTable_jugador.getColumnModel().getColumnCount() > 0) {
            jTable_jugador.getColumnModel().getColumn(0).setResizable(false);
            jTable_jugador.getColumnModel().getColumn(1).setResizable(false);
            jTable_jugador.getColumnModel().getColumn(2).setResizable(false);
        }

        jButton_aniadir_jugador.setText("Añadir");
        jButton_aniadir_jugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_aniadir_jugadorActionPerformed(evt);
            }
        });

        jButton_modificar_jugador.setText("Modificar");
        jButton_modificar_jugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_modificar_jugadorActionPerformed(evt);
            }
        });

        jButton_borrar_jugador.setText("Borrar");
        jButton_borrar_jugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_borrar_jugadorActionPerformed(evt);
            }
        });

        jLabel_NickName.setText("NickName:");

        jLabel_nivel.setText("Nivel:");

        jTextField_NickName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_NickNameActionPerformed(evt);
            }
        });

        jTextField_InventariosJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_InventariosJugadorActionPerformed(evt);
            }
        });

        jButton_guardar_jugador.setText("Guardar");
        jButton_guardar_jugador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_guardar_jugadorMouseClicked(evt);
            }
        });
        jButton_guardar_jugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_guardar_jugadorActionPerformed(evt);
            }
        });

        jButton_cancelar_jugador.setText("Cancelar");
        jButton_cancelar_jugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancelar_jugadorActionPerformed(evt);
            }
        });

        jTextField_Nivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_NivelActionPerformed(evt);
            }
        });

        jLabel_Inventarios.setText("Inventarios:");

        javax.swing.GroupLayout jPanel_ciudadLayout = new javax.swing.GroupLayout(jPanel_ciudad);
        jPanel_ciudad.setLayout(jPanel_ciudadLayout);
        jPanel_ciudadLayout.setHorizontalGroup(
            jPanel_ciudadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ciudadLayout.createSequentialGroup()
                .addGroup(jPanel_ciudadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_ciudadLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel_ciudadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_NickName)
                            .addComponent(jLabel_nivel)
                            .addComponent(jLabel_Inventarios))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel_ciudadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_NickName, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addComponent(jTextField_InventariosJugador)
                            .addComponent(jTextField_Nivel))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel_ciudadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_guardar_jugador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_cancelar_jugador, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)))
                    .addGroup(jPanel_ciudadLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_ciudadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_modificar_jugador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_aniadir_jugador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_borrar_jugador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_ciudadLayout.setVerticalGroup(
            jPanel_ciudadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ciudadLayout.createSequentialGroup()
                .addGroup(jPanel_ciudadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_ciudadLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton_aniadir_jugador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_modificar_jugador, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_borrar_jugador))
                    .addGroup(jPanel_ciudadLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42)
                .addGroup(jPanel_ciudadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_NickName)
                    .addComponent(jTextField_NickName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_guardar_jugador))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_ciudadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_nivel)
                    .addComponent(jButton_cancelar_jugador)
                    .addComponent(jTextField_Nivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_ciudadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_InventariosJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_Inventarios))
                .addContainerGap(150, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Jugador", jPanel_ciudad);

        jTable_Inventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdInventario", "SlotsMaximos", "SlotsOcupados"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_Inventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_InventarioMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable_Inventario);
        if (jTable_Inventario.getColumnModel().getColumnCount() > 0) {
            jTable_Inventario.getColumnModel().getColumn(0).setResizable(false);
            jTable_Inventario.getColumnModel().getColumn(1).setResizable(false);
            jTable_Inventario.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel_nombre_SlotsMaximos.setText("SlotsMaximos:");

        jLabel_SlotsOcupados.setText("SlotsOcupados:");

        jTextField_SlotsMaximos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_SlotsMaximosActionPerformed(evt);
            }
        });

        jButton_guardar_inventario.setText("Guardar");
        jButton_guardar_inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_guardar_inventarioActionPerformed(evt);
            }
        });

        jButton_cancelar_inventario.setText("Cancelar");
        jButton_cancelar_inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancelar_inventarioActionPerformed(evt);
            }
        });

        jButton_borrar_inventario.setText("Borrar");
        jButton_borrar_inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_borrar_inventarioActionPerformed(evt);
            }
        });

        jButton_modificar_inventario.setText("Modificar");
        jButton_modificar_inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_modificar_inventarioActionPerformed(evt);
            }
        });

        jButton_aniadir_iventario.setText("Añadir");
        jButton_aniadir_iventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_aniadir_iventarioActionPerformed(evt);
            }
        });

        jLabel_JugadoresConAcceso.setText("Jugadores:");

        javax.swing.GroupLayout jPanel_personaLayout = new javax.swing.GroupLayout(jPanel_persona);
        jPanel_persona.setLayout(jPanel_personaLayout);
        jPanel_personaLayout.setHorizontalGroup(
            jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_personaLayout.createSequentialGroup()
                .addGroup(jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_personaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_modificar_inventario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_aniadir_iventario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_borrar_inventario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel_personaLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_nombre_SlotsMaximos)
                            .addComponent(jLabel_SlotsOcupados)
                            .addComponent(jLabel_JugadoresConAcceso))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_SlotsMaximos, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addComponent(jTextField_SlotsOcupados)
                            .addComponent(jTextField_JugadoresConAcceso, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_guardar_inventario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_cancelar_inventario, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_personaLayout.setVerticalGroup(
            jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_personaLayout.createSequentialGroup()
                .addGroup(jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_personaLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton_aniadir_iventario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_modificar_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_borrar_inventario))
                    .addGroup(jPanel_personaLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42)
                .addGroup(jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_nombre_SlotsMaximos)
                    .addComponent(jTextField_SlotsMaximos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_guardar_inventario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_SlotsOcupados)
                    .addComponent(jTextField_SlotsOcupados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_cancelar_inventario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_JugadoresConAcceso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_JugadoresConAcceso))
                .addContainerGap(150, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("InventarioCompartido", jPanel_persona);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane)
                .addContainerGap())
        );

        jTabbedPane.getAccessibleContext().setAccessibleName("Ciudad");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_aniadir_iventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_aniadir_iventarioActionPerformed
        //Filtros 
        
        if(!jTextField_SlotsMaximos.getText().toString().trim().equals("") && !jTextField_SlotsOcupados.getText().toString().trim().equals("")){

            if (esEntero(jTextField_SlotsMaximos.getText().toString()) && esEntero(jTextField_SlotsOcupados.getText().toString())){
                                
                if (((Integer.parseInt(jTextField_SlotsMaximos.getText().toString()))>=0) && ((Integer.parseInt(jTextField_SlotsOcupados.getText().toString()))>=0)){
                    
                    idsJugadores_Inventario=jTextField_JugadoresConAcceso.getText().toString().split(", ");
                    Boolean existen=true;
                    for (String id : idsJugadores_Inventario){
                        if (!controller.existeIDJugador(id)){
                            existen=false;
                        }
                    }
                    
                    if ((idsJugadores_Inventario[0].trim().equals("") && idsJugadores_Inventario.length==1) ){
                        existen=true;
                    }

                    
                    
                    if (existen){
                        
                        Boolean duplicados = false;
                                    
                                    for (int num1=0; num1<idsJugadores_Inventario.length-1; num1++) {
                                        
                                        for (int num2 = num1+1; num2<idsJugadores_Inventario.length; num2++){
                                            if (idsJugadores_Inventario[num1].equals(idsJugadores_Inventario[num2])){
                                                duplicados=true;
                                            }
                                        }
                                    }


                        if (!duplicados){
                    

                            //Pasamos datos al controlador, este crea el objeto y lo almacena
                            controller.crearInventario(Integer.parseInt(jTextField_SlotsMaximos.getText().toString()), Integer.parseInt(jTextField_SlotsOcupados.getText().toString()), idsJugadores_Inventario);

                            //Se actualiza la vista
                            actualizar_vista_inventario();
                            limpiar_vista_Inventario();

                        }
                    }
                    
                }
            
            }
        }
    }//GEN-LAST:event_jButton_aniadir_iventarioActionPerformed

    private void jButton_modificar_inventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_modificar_inventarioActionPerformed
        //Seleccionamos y cargamos los datos del seleccionado para la modificacion
        if (jTable_Inventario.getSelectedRow() != -1) {
            limpiar_vista_Inventario();
            IdInventarioSeleccionado = inventarios_vista.get(jTable_Inventario.getSelectedRow()).getIdInventario();
            jTextField_SlotsMaximos.setText(Integer.toString(inventarios_vista.get(jTable_Inventario.getSelectedRow()).getSlotsMaximos()));
            jTextField_SlotsOcupados.setText(Integer.toString(inventarios_vista.get(jTable_Inventario.getSelectedRow()).getSlotsOcupados()));
            String ids="";
            for (int i=0; i<inventarios_vista.get(jTable_Inventario.getSelectedRow()).getJugadores().size(); i++){

                ids += (inventarios_vista.get(jTable_Inventario.getSelectedRow()).getJugadorConAcceso(i).getIdPlayer());

                if (i != inventarios_vista.get(jTable_Inventario.getSelectedRow()).getJugadores().size()-1){
                    ids+= ", ";
                }
            }
            jTextField_JugadoresConAcceso.setText(ids);
        
        }
    }//GEN-LAST:event_jButton_modificar_inventarioActionPerformed

    private void jButton_borrar_inventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_borrar_inventarioActionPerformed
        //Seleccionamos y mandamos a eliminar el seleccionado en caso de poderse eliminar
        if (jTable_Inventario.getSelectedRow() != -1) { 
            if (inventarios_vista.get(jTable_Inventario.getSelectedRow()).getJugadores().size()==0){

                //Ponemos la id del que se eliminara
                IdInventarioSeleccionado = inventarios_vista.get(jTable_Inventario.getSelectedRow()).getIdInventario();

                //Eliminamos
                controller.EliminarInventario(IdInventarioSeleccionado);
                //Actualizamos la vista
                actualizar_vista_inventario();
                limpiar_vista_Inventario();
            }
        }
    }//GEN-LAST:event_jButton_borrar_inventarioActionPerformed

    private void jButton_cancelar_inventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancelar_inventarioActionPerformed
            //Quitamos seleccion y escritura
            limpiar_vista_Inventario();
    }//GEN-LAST:event_jButton_cancelar_inventarioActionPerformed

    private void jButton_guardar_inventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_guardar_inventarioActionPerformed
        //Filtros 
        if((!jTextField_SlotsMaximos.getText().toString().trim().equals("") && !jTextField_SlotsOcupados.getText().toString().trim().equals(""))&& (IdInventarioSeleccionado!="")){

            if (esEntero(jTextField_SlotsMaximos.getText().toString()) && esEntero(jTextField_SlotsOcupados.getText().toString())){
                
                if (((Integer.parseInt(jTextField_SlotsMaximos.getText().toString()))>=0) && ((Integer.parseInt(jTextField_SlotsOcupados.getText().toString()))>=0)){

                    
                    idsJugadores_Inventario=jTextField_JugadoresConAcceso.getText().toString().split(", ");
                    Boolean existen=true;
                    for (String id : idsJugadores_Inventario){
                        if (!controller.existeIDJugador(id)){
                            existen=false;
                        }
                    }
                    
                    if ((idsJugadores_Inventario[0].trim().equals("") && idsJugadores_Inventario.length==1) ){
                        existen=true;
                    }

                    
                    
                    if (existen){
                        
                        Boolean duplicados = false;
                                    
                                    for (int num1=0; num1<idsJugadores_Inventario.length-1; num1++) {
                                        
                                        for (int num2 = num1+1; num2<idsJugadores_Inventario.length; num2++){
                                            if (idsJugadores_Inventario[num1].equals(idsJugadores_Inventario[num2])){
                                                duplicados=true;
                                            }
                                        }
                                    }


                        if (!duplicados){

                            //Pasamos datos al controlador, este modifica el objeto y lo actualiza
                            controller.ModificarInventario(IdInventarioSeleccionado, Integer.parseInt(jTextField_SlotsMaximos.getText().toString()), Integer.parseInt(jTextField_SlotsOcupados.getText().toString()), idsJugadores_Inventario);
                            

                            // Actualizamos vista
                            actualizar_vista_inventario();
                            limpiar_vista_Inventario();
                            
                            
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_jButton_guardar_inventarioActionPerformed

    private void jTextField_SlotsMaximosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_SlotsMaximosActionPerformed
        
    }//GEN-LAST:event_jTextField_SlotsMaximosActionPerformed

    private void jTable_InventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_InventarioMouseClicked
        
    }//GEN-LAST:event_jTable_InventarioMouseClicked

    private void jButton_aniadir_serverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_aniadir_serverActionPerformed
        //Filtros
        if(!jTextField_region.getText().toString().trim().equals("")){

            //Pasamos datos al controlador, este crea el objeto y lo almacena
            controller.crearServidor(jTextField_region.getText());

            //Actualizar vista
            actualizar_vista_server();
            limpiar_vista_Servidor();
        }
    }//GEN-LAST:event_jButton_aniadir_serverActionPerformed

    private void jButton_modificar_serverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_modificar_serverActionPerformed
        //Seleccionamos y cargamos los datos del seleccionado para la modificacion
        if (jTable_server.getSelectedRow() != -1) {
            limpiar_vista_Servidor();
            IdServerSeleccionado = servidores_vista.get(jTable_server.getSelectedRow()).getIdServer();
            jTextField_region.setText(servidores_vista.get(jTable_server.getSelectedRow()).getRegion());
        }

    }//GEN-LAST:event_jButton_modificar_serverActionPerformed

    private void jButton_borrar_serverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_borrar_serverActionPerformed
        //Seleccionamos y mandamos a eliminar el seleccionado en caso de poderse eliminar
        if (jTable_server.getSelectedRow() != -1) { 
            if (controller.estaLibreIDServer(servidores_vista.get(jTable_server.getSelectedRow()).getIdServer())){
                
                //Ponemos la id del que se eliminara
                IdServerSeleccionado = servidores_vista.get(jTable_server.getSelectedRow()).getIdServer();

                //Eliminamos
                controller.EliminarServidor(IdServerSeleccionado);
                //Actualizamos la vista
                actualizar_vista_server();
                limpiar_vista_Servidor();
                
            }
        }
    }//GEN-LAST:event_jButton_borrar_serverActionPerformed

    private void jButton_cancelar_serverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancelar_serverActionPerformed
        //Quitamos seleccion y escritura
        limpiar_vista_Servidor();
    }//GEN-LAST:event_jButton_cancelar_serverActionPerformed

    private void jButton_guardar_Servidor(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_guardar_Servidor
        //Filtros
        if((!jTextField_region.getText().toString().trim().equals("")) && (IdServerSeleccionado!="")){
            
            //Pasamos datos al controlador, este modifica el objeto y lo actualiza
            controller.ModificarServidor(IdServerSeleccionado, jTextField_region.getText().toString());

            // Actualizar vista
            actualizar_vista_server();
            limpiar_vista_Servidor();

        }
    }//GEN-LAST:event_jButton_guardar_Servidor

    private void jTextField_region_servidor(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_region_servidor

    }//GEN-LAST:event_jTextField_region_servidor

    private void jTable_serverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_serverMouseClicked

    }//GEN-LAST:event_jTable_serverMouseClicked

    private void jButton_borrar_partidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_borrar_partidaActionPerformed
        //Seleccionamos y mandamos a eliminar el seleccionado en caso de poderse eliminar
        if (jTable_Partida.getSelectedRow() != -1) {

            //Ponemos la id del que se eliminara
            IdPartidaSeleccionado = partidas_vista.get(jTable_Partida.getSelectedRow()).getIdPartida();

            //Eliminamos
            controller.EliminarPartida(IdPartidaSeleccionado);
            //Actualizamos la vista
            actualizar_vista_partida();
            limpiar_vista_Partida();
        }
    }//GEN-LAST:event_jButton_borrar_partidaActionPerformed

    private void jButton_modificar_partidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_modificar_partidaActionPerformed
        //Seleccionamos y cargamos los datos del seleccionado para la modificacion
        if (jTable_Partida.getSelectedRow() != -1) {
            limpiar_vista_Partida();
            IdPartidaSeleccionado = partidas_vista.get(jTable_Partida.getSelectedRow()).getIdPartida();
            antiguaIdServerPartida = partidas_vista.get(jTable_Partida.getSelectedRow()).getServerPartida().getIdServer();
            jTextField_NumEspectadores.setText(String.valueOf(partidas_vista.get(jTable_Partida.getSelectedRow()).getNumEspectadores()));
            jTextField_IdServer_Partida.setText(partidas_vista.get(jTable_Partida.getSelectedRow()).getServerPartida().getIdServer());
            String ids="";
            for (int i=0; i<partidas_vista.get(jTable_Partida.getSelectedRow()).getJugadores().size(); i++){
                ids += (partidas_vista.get(jTable_Partida.getSelectedRow()).getJugadorpos(i).getIdPlayer());
                if (i != partidas_vista.get(jTable_Partida.getSelectedRow()).getJugadores().size()-1){
                    ids+= ", ";
                }
            }
            jTextField_JugadoresPartida.setText(ids);
            idsJugadores_Partida=jTextField_JugadoresPartida.getText().toString().split(", ");
       }
    }//GEN-LAST:event_jButton_modificar_partidaActionPerformed

    private void jButton_aniadir_partidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_aniadir_partidaActionPerformed
        //Filtros

        if(!jTextField_IdServer_Partida.getText().toString().trim().equals("") && !jTextField_NumEspectadores.getText().toString().trim().equals("")){
            if (esEntero(jTextField_NumEspectadores.getText().toString())){

                if (((Integer.parseInt(jTextField_NumEspectadores.getText().toString()))>=0)){

                    if (controller.existeIDServer(jTextField_IdServer_Partida.getText().toString())){

                        if (controller.estaLibreIDServer(jTextField_IdServer_Partida.getText().toString())){
                            
                            Boolean existen=true;
                            String[] idsjugadores=jTextField_JugadoresPartida.getText().toString().split(", ");
                            for (String ide : idsjugadores) {
                                if(!controller.comprobarExistenciaIdJugador(ide)){
                                    existen=true;
                                }
                            }
                            if (jTextField_JugadoresPartida.getText().toString().trim().equals("")){
                                existen=true;
                            }
                            if (existen){
                                Boolean usadas=false;

                                for (String idu : idsjugadores) {
                                    if(controller.comprobarUsoIdJugador(idu)){
                                        usadas=true;

                                    }
                                }
                                
                                if (!usadas){ 
                                    Boolean duplicados = false;
                                    
                                    for (int num1=0; num1<idsjugadores.length-1; num1++) {
                                        
                                        for (int num2 = num1+1; num2<idsjugadores.length; num2++){
                                            if (idsjugadores[num1].equals(idsjugadores[num2])){
                                                duplicados=true;
                                            }
                                        }
                                    }
                                    
                                    
                                    if (!duplicados){

                                        
                                        idsJugadores_Partida=jTextField_JugadoresPartida.getText().toString().split(", ");
                                        //Pasamos datos al controlador, este crea el objeto y lo almacena
                                        controller.crearPartida(Integer.parseInt(jTextField_NumEspectadores.getText().toString()), jTextField_IdServer_Partida.getText().toString(), idsJugadores_Partida);

                                        //Actualizar la vista
                                        actualizar_vista_partida();
                                        limpiar_vista_Partida();
                                    
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_jButton_aniadir_partidaActionPerformed

    private void jButton_cancelar_partidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancelar_partidaActionPerformed
        //Quitamos seleccion y escritura
        limpiar_vista_Partida();
    }//GEN-LAST:event_jButton_cancelar_partidaActionPerformed

    private void jButton_guardar_partidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_guardar_partidaActionPerformed
        //Filtros
        if(!jTextField_IdServer_Partida.getText().toString().trim().equals("") && !jTextField_NumEspectadores.getText().toString().trim().equals("")){
            if (esEntero(jTextField_NumEspectadores.getText().toString())){

                if (((Integer.parseInt(jTextField_NumEspectadores.getText().toString()))>=0)){

                    if (controller.existeIDServer(jTextField_IdServer_Partida.getText().toString())){

                        if (controller.estaLibreIDServer(jTextField_IdServer_Partida.getText().toString()) || antiguaIdServerPartida.equals(jTextField_IdServer_Partida.getText().toString())){

                            Boolean existen=true;
                            String[] idsjugadores=jTextField_JugadoresPartida.getText().toString().split(", ");
                            for (String ide : idsjugadores) {
                                if(!controller.comprobarExistenciaIdJugador(ide)){
                                    existen=false;
                                }
                            }
                            if (jTextField_JugadoresPartida.getText().toString().trim().equals("")){
                                existen=true;
                            }
                            if (existen){
                                Boolean usadas=false;
                                for (String idu : idsjugadores) {

                                    if(controller.comprobarUsoIdJugador(idu)){

                                        usadas=true;
                                        for (String idA : idsJugadores_Partida) {

                                            if (idu.equals(idA)){
                                                usadas=false;
                                            }
                                        }

                                    }
                                }
                                
                                if (!usadas){

                                    Boolean duplicados = false;
                                    
                                    for (int num1=0; num1<idsjugadores.length-1; num1++) {
                                        
                                        for (int num2 = num1+1; num2<idsjugadores.length; num2++){
                                            if (idsjugadores[num1].equals(idsjugadores[num2])){
                                                duplicados=true;
                                            }
                                        }
                                    }
                                    

                                    if (!duplicados){


                                        idsJugadores_Partida=jTextField_JugadoresPartida.getText().toString().split(", ");
                                        //Pasamos datos al controlador, este modifica el objeto y lo actualiza
                                        controller.ModificarPartida(IdPartidaSeleccionado, Integer.parseInt(jTextField_NumEspectadores.getText().toString()), jTextField_IdServer_Partida.getText().toString(), idsJugadores_Partida);

                                        //Actualizar vista
                                        actualizar_vista_partida();
                                        limpiar_vista_Partida();
                                        
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_jButton_guardar_partidaActionPerformed

    private void jTextField_NumEspectadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_NumEspectadoresActionPerformed
        
    }//GEN-LAST:event_jTextField_NumEspectadoresActionPerformed

    private void jTable_PartidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_PartidaMouseClicked
        
    }//GEN-LAST:event_jTable_PartidaMouseClicked

    private void jTextField_NivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_NivelActionPerformed
        
    }//GEN-LAST:event_jTextField_NivelActionPerformed

    private void jButton_cancelar_jugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancelar_jugadorActionPerformed
        //Quitamos seleccion y escritura
        limpiar_vista_Jugador();
    }//GEN-LAST:event_jButton_cancelar_jugadorActionPerformed

    private void jButton_guardar_jugadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_guardar_jugadorMouseClicked
        
    }//GEN-LAST:event_jButton_guardar_jugadorMouseClicked

    private void jTextField_InventariosJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_InventariosJugadorActionPerformed
        
    }//GEN-LAST:event_jTextField_InventariosJugadorActionPerformed

    private void jTextField_NickNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_NickNameActionPerformed
        
    }//GEN-LAST:event_jTextField_NickNameActionPerformed

    private void jButton_borrar_jugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_borrar_jugadorActionPerformed
        //Seleccionamos y mandamos a eliminar el seleccionado en caso de poderse eliminar
        if (jTable_jugador.getSelectedRow() != -1) { 
            if (!controller.comprobarUsoIdJugador(jugadores_vista.get(jTable_jugador.getSelectedRow()).getIdPlayer())){
                
                //Ponemos la id del que se eliminara
                IdJugadorSeleccionado = jugadores_vista.get(jTable_jugador.getSelectedRow()).getIdPlayer();
                //Eliminamos
                controller.EliminarJugador(IdJugadorSeleccionado);
                //Actualizamos la vista
                actualizar_vista_jugador();
                //Terminamos con el seleccionado
                limpiar_vista_Jugador();
                
            }
        }
    }//GEN-LAST:event_jButton_borrar_jugadorActionPerformed

    private void jButton_modificar_jugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_modificar_jugadorActionPerformed
        //Seleccionamos y cargamos los datos del seleccionado para la modificacion
        if (jTable_jugador.getSelectedRow() != -1) {
            limpiar_vista_Jugador();
            IdJugadorSeleccionado = jugadores_vista.get(jTable_jugador.getSelectedRow()).getIdPlayer();
            jTextField_NickName.setText(jugadores_vista.get(jTable_jugador.getSelectedRow()).getNickName());
            jTextField_Nivel.setText (String.valueOf(jugadores_vista.get(jTable_jugador.getSelectedRow()).getNivel()));        
            String ids="";
            for (int i=0; i<jugadores_vista.get(jTable_jugador.getSelectedRow()).getInventarios().size(); i++){
                ids += (jugadores_vista.get(jTable_jugador.getSelectedRow()).getInventarioConAcceso(i).getIdInventario());
                if (i != jugadores_vista.get(jTable_jugador.getSelectedRow()).getInventarios().size()-1){
                    ids+= ", ";
                }
            }
            jTextField_InventariosJugador.setText(ids);
        }
    }//GEN-LAST:event_jButton_modificar_jugadorActionPerformed

    private void jButton_aniadir_jugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_aniadir_jugadorActionPerformed
        //Filtros
        if(!jTextField_NickName.getText().toString().trim().equals("") && !jTextField_Nivel.getText().toString().trim().equals("")){

            if (esEntero(jTextField_Nivel.getText().toString())){
                                
                if (((Integer.parseInt(jTextField_Nivel.getText().toString()))>=0)){
                    idsInventarios_Jugador=jTextField_InventariosJugador.getText().toString().split(", ");
                    Boolean existen=true;
                    for (String id : idsInventarios_Jugador){
                        if (!controller.existeIDInventario(id)){
                            existen=false;
                        }
                    }
                    if ((idsInventarios_Jugador[0].trim().equals("") && idsInventarios_Jugador.length==1) ){

                        existen=true;
                    }

                    if (existen){
                        
                        Boolean duplicados = false;
                                    
                                    for (int num1=0; num1<idsInventarios_Jugador.length-1; num1++) {
                                        
                                        for (int num2 = num1+1; num2<idsInventarios_Jugador.length; num2++){
                                            if (idsInventarios_Jugador[num1].equals(idsInventarios_Jugador[num2])){
                                                duplicados=true;
                                            }
                                        }
                                    }


                        if (!duplicados){

                            //Pasamos datos al controlador, este crea el objeto y lo almacena
                            controller.crearJugador(jTextField_NickName.getText().toString(), Integer.parseInt(jTextField_Nivel.getText().toString()), idsInventarios_Jugador);

                            //Actualizar vista
                            actualizar_vista_jugador();
                            limpiar_vista_Jugador();
                            
                        }
                    }
                    
                }
            
            }
        }
    }//GEN-LAST:event_jButton_aniadir_jugadorActionPerformed

    private void jTable_jugadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_jugadorMouseClicked
        
    }//GEN-LAST:event_jTable_jugadorMouseClicked

    private void jButton_guardar_jugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_guardar_jugadorActionPerformed
        //Filtros
        if(!jTextField_NickName.getText().toString().trim().equals("") && !jTextField_Nivel.getText().toString().trim().equals("")){

            if (esEntero(jTextField_Nivel.getText().toString())){
                                
                if (((Integer.parseInt(jTextField_Nivel.getText().toString()))>=0)){
                    
                    idsInventarios_Jugador=jTextField_InventariosJugador.getText().toString().split(", ");
                    Boolean existen=true;
                    for (String id : idsInventarios_Jugador){
                        if (!controller.existeIDInventario(id)){
                            existen=false;
                        }
                    }
                    
                    if ((idsInventarios_Jugador[0].trim().equals("") && idsInventarios_Jugador.length==1) ){
                        existen=true;
                    }

                    if (existen){
                        
                        Boolean duplicados = false;

                        for (int num1=0; num1<idsInventarios_Jugador.length-1; num1++) {

                            for (int num2 = num1+1; num2<idsInventarios_Jugador.length; num2++){
                                if (idsInventarios_Jugador[num1].equals(idsInventarios_Jugador[num2])){
                                    duplicados=true;
                                }
                            }
                        }


                        if (!duplicados){
                    
                            //Pasamos datos al controlador, este modifica el objeto y lo actualiza
                            controller.ModificarJugador(IdJugadorSeleccionado, jTextField_NickName.getText().toString(), Integer.parseInt(jTextField_Nivel.getText().toString()), idsInventarios_Jugador);

                            //Actualizar vista
                            actualizar_vista_jugador();
                            limpiar_vista_Jugador();
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_jButton_guardar_jugadorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButton_aniadir_iventario;
    private javax.swing.JButton jButton_aniadir_jugador;
    private javax.swing.JButton jButton_aniadir_partida;
    private javax.swing.JButton jButton_aniadir_server;
    public javax.swing.JButton jButton_borrar_inventario;
    private javax.swing.JButton jButton_borrar_jugador;
    private javax.swing.JButton jButton_borrar_partida;
    private javax.swing.JButton jButton_borrar_server;
    private javax.swing.JButton jButton_cancelar_inventario;
    private javax.swing.JButton jButton_cancelar_jugador;
    private javax.swing.JButton jButton_cancelar_partida;
    public javax.swing.JButton jButton_cancelar_server;
    private javax.swing.JButton jButton_guardar_inventario;
    private javax.swing.JButton jButton_guardar_jugador;
    private javax.swing.JButton jButton_guardar_partida;
    public javax.swing.JButton jButton_guardar_server;
    public javax.swing.JButton jButton_modificar_inventario;
    private javax.swing.JButton jButton_modificar_jugador;
    private javax.swing.JButton jButton_modificar_partida;
    private javax.swing.JButton jButton_modificar_server;
    private javax.swing.JLabel jLabel_Inventarios;
    private javax.swing.JLabel jLabel_JugadoresConAcceso;
    private javax.swing.JLabel jLabel_JugadoresPartida;
    private javax.swing.JLabel jLabel_NickName;
    private javax.swing.JLabel jLabel_Servidor;
    private javax.swing.JLabel jLabel_SlotsOcupados;
    private javax.swing.JLabel jLabel_nivel;
    private javax.swing.JLabel jLabel_nombre_SlotsMaximos;
    private javax.swing.JLabel jLabel_numEspectadores;
    private javax.swing.JLabel jLabel_region;
    private javax.swing.JPanel jPanel_biblioteca;
    private javax.swing.JPanel jPanel_ciudad;
    private javax.swing.JPanel jPanel_persona;
    private javax.swing.JPanel jPanel_server;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane;
    public javax.swing.JTable jTable_Inventario;
    private javax.swing.JTable jTable_Partida;
    private javax.swing.JTable jTable_jugador;
    private javax.swing.JTable jTable_server;
    public javax.swing.JTextField jTextField_IdServer_Partida;
    public javax.swing.JTextField jTextField_InventariosJugador;
    private javax.swing.JTextField jTextField_JugadoresConAcceso;
    public javax.swing.JTextField jTextField_JugadoresPartida;
    public javax.swing.JTextField jTextField_NickName;
    public javax.swing.JTextField jTextField_Nivel;
    public javax.swing.JTextField jTextField_NumEspectadores;
    private javax.swing.JTextField jTextField_SlotsMaximos;
    private javax.swing.JTextField jTextField_SlotsOcupados;
    private javax.swing.JTextField jTextField_region;
    // End of variables declaration//GEN-END:variables
   

    public void formWindowClosing (java.awt.event.WindowEvent evt){
        
    }
    
    /**
     * @brief Actualizar la vista de la tabla servidor en funcion del vector de la vista
     */
    private void actualizar_vista_server (){
            
            for (int i=table_model_server.getRowCount()-1; i>=0; i--){
                table_model_server.removeRow(i);
            }
            
            for (int i=0; i<servidores_vista.size(); i++){
                table_model_server.addRow(new Object[]{servidores_vista.get(i).getIdServer(), servidores_vista.get(i).getRegion()});
            }
            
    }
    
    /**
     * @brief Actualizar la vista de la tabla inventario en funcion del vector de la vista
     */
    private void actualizar_vista_inventario (){ 
            
            for (int i=table_model_inventario.getRowCount()-1; i>=0; i--){
                table_model_inventario.removeRow(i);
            }
            
            for (int i=0; i<inventarios_vista.size(); i++){
                table_model_inventario.addRow(new Object[]{inventarios_vista.get(i).getIdInventario(), inventarios_vista.get(i).getSlotsMaximos(), inventarios_vista.get(i).getSlotsOcupados()});
            }

    }
    
    /**
     * @brief Actualizar la vista de la tabla jugador en funcion del vector de la vista
     */
    private void actualizar_vista_jugador (){ 
            
            for (int i=table_model_jugador.getRowCount()-1; i>=0; i--){
                table_model_jugador.removeRow(i);
            }
            
            for (int i=0; i<jugadores_vista.size(); i++){
                table_model_jugador.addRow(new Object[]{jugadores_vista.get(i).getIdPlayer(), jugadores_vista.get(i).getNivel(), jugadores_vista.get(i).getNickName()});
                
            }

    }
    
    /**
     * @brief Actualizar la vista de la tabla partida en funcion del vector de la vista
     */
    private void actualizar_vista_partida (){ 
            
            for (int i=table_model_partida.getRowCount()-1; i>=0; i--){
                table_model_partida.removeRow(i);
            }
            
            for (int i=0; i<partidas_vista.size(); i++){
                table_model_partida.addRow(new Object[]{partidas_vista.get(i).getIdPartida(), partidas_vista.get(i).getNumEspectadores()});
            }

    }
    
    /**
     * @brief 
     * @param String s 
     * @return Boolean esEntero
     */
    public boolean esEntero(String s) {
        Boolean esEntero;
        try {
            Integer.parseInt(s);
            esEntero = true;
        } catch (NumberFormatException e) {
            esEntero = false;
        }
        return esEntero;
    }
    
    /**
     * @brief Vacia los campos de escritura y la seleccion de la pestaña partida
     */
    public void limpiar_vista_Partida (){
        jTextField_NumEspectadores.setText("");
        jTextField_IdServer_Partida.setText("");
        jTextField_JugadoresPartida.setText("");
        IdPartidaSeleccionado="";
        antiguaIdServerPartida="";
    }
    
    /**
     * @brief Vacia los campos de escritura y la seleccion de la pestaña servidor
     */
    public void limpiar_vista_Servidor (){
        IdServerSeleccionado="";
        jTextField_region.setText("");
    }
    
    /**
     * @brief Vacia los campos de escritura y la seleccion de la pestaña jugador
     */
    public void limpiar_vista_Jugador (){
        IdJugadorSeleccionado="";
        jTextField_NickName.setText("");
        jTextField_Nivel.setText("");
        jTextField_InventariosJugador.setText("");
    }
    
    /**
     * @brief Vacia los campos de escritura y la seleccion de la pestaña inventario
     */
    public void limpiar_vista_Inventario (){
            jTextField_SlotsMaximos.setText("");
            jTextField_SlotsOcupados.setText("");
            IdInventarioSeleccionado="";
            jTextField_JugadoresConAcceso.setText("");
    }
    
    
    
    /*////////////////////////////////////////////////////////
    ///////////////////     Atirbutos     ///////////////////
    //////////////////////////////////////////////////////*/
    
    private ArrayList<Servidor> servidores_vista;
    private DefaultTableModel table_model_server;
    private String IdServerSeleccionado;
    
    private ArrayList<InventarioCompartido> inventarios_vista;
    private DefaultTableModel table_model_inventario;
    private String IdInventarioSeleccionado;
    private String[] idsJugadores_Inventario;

    private ArrayList<Jugador> jugadores_vista;
    private DefaultTableModel table_model_jugador;
    private String IdJugadorSeleccionado;
    private String[] idsInventarios_Jugador;
    
    private ArrayList<Partida> partidas_vista;
    private DefaultTableModel table_model_partida;
    private String IdPartidaSeleccionado;
    private String antiguaIdServerPartida;
    private String[] idsJugadores_Partida;
    
}
