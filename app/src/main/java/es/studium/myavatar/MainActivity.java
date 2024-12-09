package es.studium.myavatar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String name, gender, species, profession;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView avatarImageView = findViewById(R.id.avatarImageView);
        TextView statsTextView = findViewById(R.id.statsTextView);
        Button restartButton = findViewById(R.id.restartButton);

        // Botón para reiniciar
        restartButton.setOnClickListener(v -> startAvatarCreation());

        // Iniciar creación del avatar
        startAvatarCreation();
    }

    // Método para iniciar la creación del avatar
    private void startAvatarCreation() {
        askName();
    }

    // Diálogo para ingresar el nombre
    private void askName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.enter_name);

        // Inflar el diseño personalizado del cuadro de diálogo
        final View customLayout = getLayoutInflater().inflate(R.layout.dialog_name, null);
        builder.setView(customLayout);

        builder.setPositiveButton(R.string.accept, (dialog, which) -> {
            if (customLayout != null) {
                android.widget.EditText editText = customLayout.findViewById(R.id.nameEditText);
                if (editText != null) {
                    name = editText.getText().toString();
                    askGender();
                } else {
                    Log.e("Error", "EditText with ID nameEditText not found.");
                }
            } else {
                Log.e("Error", "customLayout is null.");
            }
        });

        builder.setNegativeButton(R.string.cancel, null);
        builder.show();
    }

    // Diálogo para seleccionar género
    private void askGender() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.select_gender)
                .setItems(R.array.genders, (dialog, which) -> {
                    gender = getResources().getStringArray(R.array.genders)[which];
                    askSpecies();
                });
        builder.show();
    }

    // Diálogo para seleccionar especie
    private void askSpecies() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.select_species)
                .setItems(R.array.species, (dialog, which) -> {
                    species = getResources().getStringArray(R.array.species)[which];
                    askProfession();
                });
        builder.show();
    }

    // Diálogo para seleccionar profesión
    private void askProfession() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.select_profession)
                .setItems(R.array.professions, (dialog, which) -> {
                    profession = getResources().getStringArray(R.array.professions)[which];
                    generateAvatar();
                });
        builder.show();
    }

    // Método para generar el avatar y mostrar los resultados
    private void generateAvatar() {
        ImageView avatarImageView = findViewById(R.id.avatarImageView);
        TextView statsTextView = findViewById(R.id.statsTextView);

        // Generar el nombre de la imagen basado en las selecciones
        String imageName = species.toLowerCase() + "_" + gender.toLowerCase() + "_" + profession.toLowerCase();
        int resId = getResources().getIdentifier(imageName, "drawable", getPackageName());
        if (resId != 0) {
            avatarImageView.setImageResource(resId);
        } else {
            Log.e("Error", "Image resource not found for: " + imageName);
            avatarImageView.setImageResource(R.drawable.placeholder_avatar); // Imagen predeterminada
        }

        // Generar atributos aleatorios
        int life = random.nextInt(101); // Entre 0 y 100
        int magic = random.nextInt(11); // Entre 0 y 10
        int strength = random.nextInt(21); // Entre 0 y 20
        int speed = random.nextInt(6); // Entre 0 y 5

        // Mostrar estadísticas y nombre en el TextView
        String stats = getString(R.string.stats, name, life, magic, strength, speed);
        statsTextView.setText(stats);
    }
}
