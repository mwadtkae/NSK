package com.naukri.naukrisuchakkendra.Candidate;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.abdeveloper.library.MultiSelectDialog;
import com.abdeveloper.library.MultiSelectModel;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.naukri.naukrisuchakkendra.DataModel;
import com.naukri.naukrisuchakkendra.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class Profilecn extends AppCompatActivity {

    GridAdapter listAdapter;
    ArrayList<DataModel> dataModelArrayList;
    ArrayList<DataModel> dataModelArrayList1;
    String request_view = "http://naukrisuchak.com/app/api/candidate/jobrole.php?user_id=0";
    String skr = "http://naukri.giggadaproducts.com/product/Api/candidate/jobtitlec.php";
    String url = "http://naukrisuchak.com/app/global/";

    GridView gridView;
    Button show;
    String[] arrOfStr;
    LinearLayout linearLayout, lG;
    ArrayList<MultiSelectModel> listOfCountries1 = new ArrayList<>();
    ArrayList<MultiSelectModel> listOfCountries2 = new ArrayList<>();
    ArrayList<MultiSelectModel> listOfCountries3 = new ArrayList<>();
    MultiSelectDialog multiSelectDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilecn);
        gridView = findViewById(R.id.grid_products);

        linearLayout = findViewById(R.id.linear_layout);
        lG = findViewById(R.id.gl);

        linearLayout.setVisibility(View.INVISIBLE);
        emp();

        show = findViewById(R.id.show_button);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SparseBooleanArray selectedRows = listAdapter.getSelectedIds();
                if (selectedRows.size() > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < selectedRows.size(); i++) {
                        if (selectedRows.valueAt(i)) {
                            String selectedRowLabel = dataModelArrayList.get(selectedRows.keyAt(i)).getId();
                            stringBuilder.append(selectedRowLabel + ",");
                        }
                    }

                    linearLayout.setVisibility(View.VISIBLE);
                    lG.setVisibility(View.INVISIBLE);
                    arrOfStr = stringBuilder.toString().split(",");
                   sk();
                    LinearLayout linearLayout = findViewById(R.id.linear_layout);
                    //Adding 2 TextViews
                    for (int i = 0; i < arrOfStr.length; i++) {
                        View view1 = getLayoutInflater().inflate(R.layout.diloagview, null);


                        Button b = view1.findViewById(R.id.btn_no);
                        final EditText b1 = view1.findViewById(R.id.r1);
                        // sk(skr+arrOfStr[i]);
                        b.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {




                                multiSelectDialog.show(getSupportFragmentManager(), "multiSelectDialog");
                            }
                        });
                        linearLayout.addView(view1);
                    }

                    // Toast.makeText(Profilecn.this, "Selected Rows\n" + stringBuilder.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void emp() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, request_view,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject obj = new JSONObject(response);
                            if (obj.optString("responce").equals("success")) {
                                dataModelArrayList = new ArrayList<>();
                                JSONArray dataArray = obj.getJSONArray("data");
                                for (int i = 0; i < dataArray.length(); i++) {

                                    DataModel playerModel = new DataModel();
                                    JSONObject dataobj = dataArray.getJSONObject(i);
                                    playerModel.setId(dataobj.getString("id"));
                                    playerModel.setName(dataobj.getString("name"));
                                    playerModel.setImgURL(url + dataobj.getString("path"));
                                    dataModelArrayList.add(playerModel);

                                }

                                if (dataModelArrayList.size() > 0) {
                                    setupListview();
                                }
                            }


                            if (obj.optString("responce").equals("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("data").trim(), Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "Failure (" + error.networkResponse.statusCode + ")", Toast.LENGTH_SHORT).show();

                    }
                });


        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void setupListview() {

        listAdapter = new GridAdapter(this, dataModelArrayList);
        gridView.setAdapter(listAdapter);
    }


    private void sk() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, skr,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject obj = new JSONObject(response);
                            if (obj.optString("responce").equals("success")) {
                                dataModelArrayList1 = new ArrayList<>();
                                JSONArray dataArray = obj.getJSONArray("data");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    DataModel playerModel = new DataModel();
                                    JSONObject dataobj = dataArray.getJSONObject(i);
                                    playerModel.setProdid(dataobj.getString("id"));
                                    playerModel.setName(dataobj.getString("title"));
                                    playerModel.setCrid(dataobj.getString("job_id"));

                                    dataModelArrayList1.add(playerModel);


                                }

                                if (dataModelArrayList1.size() > 0) {
                                    setupListview6();
                                }

                            }

                            if (obj.optString("responce").equals("error"))
                                Toast.makeText(getApplicationContext(), obj.getString("data").trim(), Toast.LENGTH_LONG).show();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), "Failure (" + error.networkResponse.statusCode + ")", Toast.LENGTH_SHORT).show();

                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }

    private void setupListview6() {




        //for(int i=0;i<arrOfStr.length;i++) {


        listOfCountries1.clear();
        listOfCountries2.clear();
        listOfCountries3.clear();
        //}
        for (int q = 0; q < arrOfStr.length; q++) {

            for (int i = 0, j = 1; i < dataModelArrayList1.size(); i++, j++) {


                if (q == 0) {
                    if (dataModelArrayList1.get(i).getCrid().contains(arrOfStr[q]))
                        listOfCountries1.add(new MultiSelectModel(j, dataModelArrayList1.get(i).getName()));

                }
                if (q == 1) {
                    if (dataModelArrayList1.get(i).getCrid().contains(arrOfStr[q]))
                        listOfCountries2.add(new MultiSelectModel(j, dataModelArrayList1.get(i).getName()));
                }
                if (q == 2) {
                    if (dataModelArrayList1.get(i).getCrid().contains(arrOfStr[q]))
                        listOfCountries3.add(new MultiSelectModel(j, dataModelArrayList1.get(i).getName()));
                }

            }
            switch (arrOfStr[q])
            {
                case "0":
                    set(listOfCountries1);
                    break;
                case "1":
                    set(listOfCountries2);
                    break;
                case "2":
                    set(listOfCountries3);
                    break;
            }
        }


    }


    public void set(  ArrayList<MultiSelectModel> listOfCountries1) {
        multiSelectDialog = new MultiSelectDialog()
                .title("Show Multi Select Dialog") //setting title for dialog
                .titleSize(25)
                .positiveText("Done")
                .negativeText("Cancel")
                .setMinSelectionLimit(0)
                .setMaxSelectionLimit(listOfCountries1.size())
                .multiSelectList(listOfCountries1) // the multi select model list with ids and name
                .onSubmit(new MultiSelectDialog.SubmitCallbackListener() {
                    @Override
                    public void onSelected(ArrayList<Integer> selectedIds, ArrayList<String> selectedNames, String dataString) {
                        //will return list of selected IDS
                        for (int i = 0; i < selectedIds.size(); i++) {


                            dataString = dataString.replaceAll(", ", ",");
                            //      skillt.setText(dataString);
                        }


                        Toast.makeText(getApplicationContext(), dataString, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        //    Log.d(TAG,"Dialog cancelled");

                    }
                });


    }

}