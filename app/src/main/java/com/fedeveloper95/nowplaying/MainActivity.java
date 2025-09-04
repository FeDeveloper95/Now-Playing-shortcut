package com.fedeveloper95.nowplaying;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler; // <-- IMPORT NECESSARIO
import android.os.Looper;  // <-- IMPORT NECESSARIO
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Intent per lanciare la cronologia di Now Playing
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(
                "com.google.android.as",
                "com.google.intelligence.sense.ambientmusic.history.HistoryActivity"
        ));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        try {
            // Avvia l'attività
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // Se non la trova, mostra un messaggio
            Toast.makeText(this, "Cronologia 'Now Playing' non trovata.", Toast.LENGTH_LONG).show();
        }

        // --- INIZIO MODIFICA ---
        // Invece di chiamare finish() subito, aspettiamo 250 millisecondi.
        // Questo dà al launcher il tempo di registrare l'icona a tema correttamente.
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 250); // 250 millisecondi di ritardo
        // --- FINE MODIFICA ---
    }
}