package com.example.login_basesdedatos;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class BlankFragment_register extends Fragment {

    private Button registro;
    private EditText user,passw,conpassw;
    String newuse,newpas,newconpass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_blank_register, container, false);
        registro = root.findViewById(R.id.btnRegistro);
        user = root.findViewById(R.id.txtUsuario2);
        passw = root.findViewById(R.id.txtpass2);
        conpassw = root.findViewById(R.id.txtconpass2);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newuse = user.getText().toString().trim();
                newpas = passw.getText().toString().trim();
                newconpass = conpassw.getText().toString().trim();
                new enviaregistro(getContext()).execute(newuse,newpas,newconpass);
                user.getText().clear();
                passw.getText().clear();
                conpassw.getText().clear();
            }
        });

        return root;
    }
    public static class enviaregistro extends AsyncTask<String,Void,String> {

        private WeakReference<Context> context;

        public enviaregistro(Context context){
            this.context = new WeakReference<>(context);
        }

        protected String doInBackground(String... params){
            String jsonurl = "https://melvinprueba100.000webhostapp.com/jsonprueba/usuario.php";
            String resultado;

            try{
                URL url = new URL(jsonurl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));

                String nom = params[0];
                String direcc = params[1];
                String pedi = params[2];

                String data = URLEncoder.encode("nuevousuario","UTF-8")+"="+URLEncoder.encode(nom,"UTF-8")+"&"+URLEncoder.encode("contra","UTF-8")+"="+URLEncoder.encode(direcc,"UTF-8")+"&"+URLEncoder.encode("concontra","UTF-8")+"="+URLEncoder.encode(pedi,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,StandardCharsets.UTF_8));
                StringBuilder stringBuilder = new StringBuilder();

                String line;
                while ((line = bufferedReader.readLine())!=null){ stringBuilder.append(line);
                }
                resultado = stringBuilder.toString();

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

            } catch (MalformedURLException e) {
                Log.d("MIAPP","Se esta utilizando una direccion de json incorrecta");
                resultado="Se a producido un error";
            } catch (IOException e) {
                Log.d("MIAPP","Error inesperado!, posible problema de conexion de red.");
                resultado="Se a producido un error, comprueba tu conexion";
            }

            return resultado;
        }
        protected void onPostExecute(String resultado){
            Toast.makeText(context.get(), resultado, Toast.LENGTH_SHORT).show();
        }


    }
}