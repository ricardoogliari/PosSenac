package com.senac.rsilva.possenac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Desafio extends AppCompatActivity implements OnMapReadyCallback{

    private EditText edtOrigem;
    private EditText edtDestino;

    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desafio);

        //butter knife
        edtOrigem = (EditText)findViewById(R.id.edtOrigem);
        edtDestino = (EditText)findViewById(R.id.edtDestino);

        MapFragment map = (MapFragment)getFragmentManager().findFragmentById(R.id.map);
        map.getMapAsync(this);
    }

    public void rota(View view){
        Call<Retorno> call = ((MyApplication) getApplication()).service.buscaRota(
                edtOrigem.getText().toString(),
                edtDestino.getText().toString(),
                "AIzaSyBKEowT7iU_U2MOXhDIrR61whJ8356Z6kE"
        );
        call.enqueue(
                new Callback<Retorno>() {
                    @Override
                    public void onResponse(Call<Retorno> call, Response<Retorno> response) {
                        String points = response.body().routes.get(0).overview_polyline.points;

                        PolylineOptions polylineOptions = new PolylineOptions();
                        polylineOptions.addAll(PolyUtil.decode(points));

                        googleMap.addPolyline(polylineOptions);
                    }

                    @Override
                    public void onFailure(Call<Retorno> call, Throwable t) {

                    }
                }
        );
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }
}
