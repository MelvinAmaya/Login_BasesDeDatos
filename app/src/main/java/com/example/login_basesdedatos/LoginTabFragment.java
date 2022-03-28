package com.example.login_basesdedatos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class LoginTabFragment extends Fragment {
    EditText usuario,pass;
    Button login;
    TextView mensaje;
    float v = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment,container,false);

        usuario=(EditText) root.findViewById(R.id.usuario);
        pass = (EditText) root.findViewById(R.id.pass);
        mensaje = (TextView) root.findViewById(R.id.txtMensaje);
        login=(Button) root.findViewById(R.id.registra);

        usuario.setTranslationX(800);
        pass.setTranslationX(800);
        mensaje.setTranslationX(800);
        login.setTranslationX(800);

        usuario.setAlpha(v);
        pass.setAlpha(v);
        mensaje.setAlpha(v);
        login.setAlpha(v);

        usuario.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        mensaje.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        return root;

    }

}
