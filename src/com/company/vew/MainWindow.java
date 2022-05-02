package com.company.vew;

import com.company.MyTableModel;

import com.company.data.ListPlace;
import com.company.data.Place;


import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Filter;

public class MainWindow extends JFrame {
    private JTable jTable;
    private MyTableModel myTableModel;
    private JButton buttonDelete, buttonAdd,buttonAccept, buttonFilter;
    private JTextField textField2,textField3,textField4, textFieldFilter;
    private JComboBox comboBox;
    private JLabel  labelType,label1, label2, label3;
    private JScrollPane jScrollPane;
    private TableRowSorter sorter;
    private JPanel panelDown;
    private GridLayout layout;


    public MainWindow(){
        //
        super("Список мест");
        myTableModel = new MyTableModel(new ListPlace());
        jTable = new JTable();
        jScrollPane = new JScrollPane(jTable);
        comboBox = new JComboBox(new String[]{
                "Область",
                "Город",
                "Столица"
        });
        jTable.setModel(myTableModel);
        buttonFilter = new JButton("Фильтр");
        buttonDelete = new JButton("Удалить место");
        buttonAdd = new JButton("Добавить место");
        textFieldFilter = new JTextField("",30);
        label3 = new JLabel("   Кол-во районов:");

        sorter = new TableRowSorter<>(jTable.getModel());
        jTable.setRowSorter(sorter);

        //События

        textFieldFilter.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = textFieldFilter.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = textFieldFilter.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    myTableModel.delete(jTable.convertRowIndexToModel(jTable.getSelectedRow()));
                }catch (IndexOutOfBoundsException ex){
                    JDialog jDialog = new JDialog(MainWindow.this,"Выделите строку для удаления",true);
                    jDialog.setLocationRelativeTo(null);
                    jDialog.setSize(350,15);
                    jDialog.setVisible(true);
                }
            }
        });

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LogPanAdd();

            }

        });


        //Панель
        panelDown = new JPanel(new FlowLayout());
        panelDown.add(buttonAdd);
        panelDown.add(new JLabel("Фильтр:"));
        panelDown.add(textFieldFilter);

        //Отображение
        this.add(panelDown,BorderLayout.NORTH);
        this.add(buttonDelete,BorderLayout.SOUTH);

        this.add(jScrollPane);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }



    public void KeyAccept(){
        try{
            myTableModel.ellAdd(comboBox.getSelectedIndex(), textField2.getText(),Integer.parseInt(textField3.getText()),Integer.parseInt(textField4.getText()));
        }catch (NumberFormatException ex){
            JDialog jDialog = new JDialog(MainWindow.this,"Не все поля заполнены!",true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setSize(250,15);
            jDialog.setVisible(true);
        }
    }
    //Логика кнопки добавления
    public void LogPanAdd(){
        //
        textField2 = new JTextField("",30);
        textField3 = new JTextField("",30);
        textField4 = new JTextField("",30);

        labelType = new JLabel("    Тип");
        label1 = new JLabel("   Название:");
        label2 = new JLabel("   Кол-во жителей:");


        buttonAccept = new JButton("Принять");

        layout = new GridLayout(5,2,5,12);
        //События
        textField3.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
                if (c == KeyEvent.VK_ENTER){
                    KeyAccept();
                }
            }
        });

        textField4.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
                if (c == KeyEvent.VK_ENTER){
                    KeyAccept();
                }
            }
        });


        buttonAccept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KeyAccept();
            }
        });

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox.getSelectedIndex() == 2){
                    label3.setText("   Прощадь:");
                }else {
                    label3.setText("   Кол-во районов:");
                }

            }
        });

        //Отображение
        JDialog jDialog = new JDialog(MainWindow.this, "Добавление",true);

        jDialog.setLayout(layout);

        jDialog.add(labelType);
        jDialog.add(comboBox);
        jDialog.add(label1);
        jDialog.add(textField2);
        jDialog.add(label2);
        jDialog.add(textField3);
        jDialog.add(label3);
        jDialog.add(textField4);
        jDialog.add(new JLabel(""));
        jDialog.add(buttonAccept);

        jDialog.setLocationRelativeTo(null);
        jDialog.pack();
        jDialog.setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

}
