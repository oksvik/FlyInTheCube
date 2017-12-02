package com.example.dudar.flyinthecube;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

/**
 * Created by dudar on 25.08.2017.
 */

public class GameboardFragment extends Fragment {

    //Tag for logging
    private static final String TAG = GameboardFragment.class.getSimpleName();

    public static final String ROW_ITEM_WITH_FLY = "row_item";
    public static final String COLUMN_ITEM_WITH_FLY = "column_item";

    private int rowItemWithFly;
    private int columnItemWithFly;

    public void setRowItemWithFly(int rowItemWithFly) {
        this.rowItemWithFly = rowItemWithFly;
    }

    public void setColumnItemWithFly(int columnItemWithFly) {
        this.columnItemWithFly = columnItemWithFly;
    }

    public GameboardFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        int TABLE_ROWS = 5;
        int TABLE_COLUMNS = 5;
        // Load the saved state if there is one
        /*if (savedInstanceState != null) {
            rowItemWithFly = savedInstanceState.getInt(ROW_ITEM_WITH_FLY);
            columnItemWithFly = savedInstanceState.getInt(COLUMN_ITEM_WITH_FLY);
        }*/

        //Inflate the fragment layout
        View rootView = inflater.inflate(R.layout.fragment_gameboard, container, false);

        TableLayout tableLayout = (TableLayout) rootView.findViewById(R.id.gameboard_table);

        for (int i = 0; i < TABLE_ROWS; i++) {
            TableRow tableRow = new TableRow(getContext());
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            for (int j = 0; j < TABLE_COLUMNS; j++) {
                ImageView imageView = new ImageView(getContext());
                imageView.setImageResource(R.drawable.square_rounded_128);
                tableRow.addView(imageView, j);
            }
            tableLayout.addView(tableRow, i);
        }
        TableRow rowWithFly = (TableRow) tableLayout.getChildAt(rowItemWithFly);
        ImageView imgForFly = (ImageView) rowWithFly.getChildAt(columnItemWithFly);
        imgForFly.setImageResource(R.drawable.my_fly1);

        return rootView;
    }
/*
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(ROW_ITEM_WITH_FLY,rowItemWithFly);
        outState.putInt(COLUMN_ITEM_WITH_FLY,columnItemWithFly);
    }*/
}
