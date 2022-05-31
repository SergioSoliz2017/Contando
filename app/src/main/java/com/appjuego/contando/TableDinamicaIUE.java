package com.appjuego.contando;

import android.content.Context;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class TableDinamicaIUE {
    private TableLayout tableLayout;
    private Context context;
    private String [] header;
    private ArrayList<ArrayList<String>> data;
    private TableRow tableRow;
    private TextView txtCell;

    public TableDinamicaIUE (TableLayout tableLayout , Context context) {
        this.tableLayout = tableLayout;
        this.context = context;
    }

    public void addHeader (String [] header){
        this.header = header;
        createHeader();
    }

    public void addData (ArrayList<ArrayList<String>> data){
        this.data = data;
        createDataTable();
    }

    private void newRow (){
        tableRow = new TableRow(context);
    }

    private void newCell (){
        txtCell = new TextView(context);
    }

    private void createHeader (){
        newRow();
        for (int i = 0 ; i < header.length ; i++){
            newCell();
            txtCell.setText(header[i]);
            tableRow.addView(txtCell);
        }
        tableLayout.addView(tableRow);
    }

    private void createDataTable (){
        String info;
        for (int i = 0 ; i < data.size() ; i++){
            newRow();
            for (int j = 0 ; j < 7 ; j++){
                newCell();
                info = data.get(i).get(j);
                txtCell.setText(info);
                tableRow.addView(txtCell);
            }
            tableLayout.addView(tableRow);
        }
    }
}
