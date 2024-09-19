package com.ablancotunsa.lab01_registroasistentes;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ablancotunsa.lab01_registroasistentes.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final String TAG = "MainActivity";  // Declare TAG here
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        EditText txtNombre = binding.txtNombre;
        EditText txtApellido = binding.txtApellido;
        EditText txtCorreo = binding.txtCorreo;
        EditText txtTelefono = binding.txtTelefono;
        EditText txtGrupoSanguineo = binding.txtGrupoSanguineo;
        Button btnRegistrar = binding.btnRegistrar;
        Button btnMostrar = binding.btnMostrar;

        btnRegistrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String fileContent = "";
                fileContent += txtNombre.getText().toString();
                fileContent += txtApellido.getText().toString();
                fileContent += txtCorreo.getText().toString();
                fileContent += txtTelefono.getText().toString();
                fileContent += txtGrupoSanguineo.getText().toString();

                saveFile(fileContent);
            }
        });
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFile();
            }
        });
    }

    private void saveFile(String testToSave) {
        String filename = "userdataRegistro.txt";

        // Create a new ContentValues object
        ContentValues values = new ContentValues();
        values.put(MediaStore.Files.FileColumns.DISPLAY_NAME, filename);
        values.put(MediaStore.Files.FileColumns.MIME_TYPE, "text/plain");

        // Insert the new file into the MediaStore
        Uri uri = getContentResolver().insert(MediaStore.Files.getContentUri("external"), values);

        if (uri != null) {
            try {
                // Open an output stream to write data
                getContentResolver().openOutputStream(uri).write(testToSave.getBytes());
                Toast.makeText(this, "File saved successfully!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Error saving file", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Failed to create file", Toast.LENGTH_SHORT).show();
        }
    }

    private void readFile() {
        String filename = "userdataRegistro.txt";

        // Query the MediaStore for the saved file
        Uri collection = MediaStore.Files.getContentUri("external");
        String selection = MediaStore.Files.FileColumns.DISPLAY_NAME + "=?";
        String[] selectionArgs = new String[]{filename};

        try (Cursor cursor = getContentResolver().query(collection, null, selection, selectionArgs, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex(MediaStore.Files.FileColumns._ID);
                long id = cursor.getLong(columnIndex);

                Uri fileUri = Uri.withAppendedPath(collection, String.valueOf(id));

                // Read the content of the file
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(getContentResolver().openInputStream(fileUri)))) {
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    // Log the content of the file
                    Log.d(TAG, "File content:\n" + stringBuilder.toString());
                }
            } else {
                Log.e(TAG, "File not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Error reading file", e);
        }
    }
}