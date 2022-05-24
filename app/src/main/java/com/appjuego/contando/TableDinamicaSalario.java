package com.appjuego.contando;

import android.content.Context;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class TableDinamicaSalario {

    private TableLayout tableLayout;
    private Context context;
    private ArrayList<String> header;
    private ArrayList<String> data;
    private TableRow tableRow;
    private TextView txtCell;

    public TableDinamicaSalario(TableLayout tableLayout , Context context) {
        this.tableLayout = tableLayout;
        this.context = context;
    }

    public void addHeader (ArrayList<String> header){
        this.header = header;
        createHeader();
    }

    public void addData (ArrayList<String> data){
        this.data = data;
        createDataTable();
    }

    private void newRow (){
        tableRow = new TableRow(context);
    }

    private void newCell (){
        txtCell = new TextView(context);
        txtCell.setGravity(Gravity.CENTER);
    }

    private void createHeader (){
        newRow();
        for (int i = 0 ; i < header.size() ; i++){
            newCell();
            txtCell.setText(header.get(i));
            tableRow.addView(txtCell);
        }
        tableLayout.addView(tableRow);
    }

    private void createDataTable (){
        String info;
        newRow();
        for (int i = 0 ; i < data.size() ; i++){
                newCell();
                info = data.get(i);
                txtCell.setText(info);
                tableRow.addView(txtCell);
        }
        tableLayout.addView(tableRow);
    }

}
