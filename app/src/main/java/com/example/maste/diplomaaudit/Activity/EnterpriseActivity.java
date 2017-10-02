package com.example.maste.diplomaaudit.Activity;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maste.diplomaaudit.Adapters.EmployeeAdapter;
import com.example.maste.diplomaaudit.Adapters.EnterpriseAdapter;
import com.example.maste.diplomaaudit.DB.DatabaseHelper;
import com.example.maste.diplomaaudit.Model.Enterprise;
import com.example.maste.diplomaaudit.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.R.id.list;


public class EnterpriseActivity extends AppCompatActivity {

    private Button createEnterpriseButton;
    private ListView enterpriseListView;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise);

        createEnterpriseButton = (Button) findViewById(R.id.create_enterprise_btn);
        enterpriseListView = (ListView) findViewById(R.id.listViewEnterprise);

        createEnterpriseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EnterpriseActivity.this, CreateEnterpriseActivity.class);
                startActivity(intent);
            }
        });

        final DatabaseHelper db = new DatabaseHelper(this);
        final ArrayList<Enterprise> enterpriseList = new ArrayList<>();
        enterpriseList.addAll(db.getAllEnterprises());

        final EnterpriseAdapter adapter = new EnterpriseAdapter(this, enterpriseList);
        enterpriseListView.setAdapter(adapter);



        enterpriseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog);
                final String enterpriseName = String.valueOf(((TextView) view.getRootView().findViewById(R.id.list_item_text)).getText());
                dialog.setTitle(enterpriseName);
                LinearLayout nextBtn = (LinearLayout) dialog.findViewById(R.id.dialog_next_button);
                LinearLayout deleteBtn = (LinearLayout) dialog.findViewById(R.id.dialog_delete_button);

                nextBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(EnterpriseActivity.this, DepartmentsActivity.class);
                        intent.putExtra("enterprise_name", enterpriseName);
                        startActivity(intent);
                    }
                });

                deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.deleteEnterpriseDB(enterpriseName);
                        enterpriseList.remove(i);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

                dialog.show();



                /*
                DialogFragment dialog = new com.example.maste.diplomaaudit.Dialog();
                dialog.show(getFragmentManager(), "dialog");
                */

                /*
                Intent intent = new Intent(EnterpriseActivity.this, DepartmentsActivity.class);
                intent.putExtra("enterprise_name", list.get((int) l));
                startActivity(intent);
                */
            }
        });
    }


}
