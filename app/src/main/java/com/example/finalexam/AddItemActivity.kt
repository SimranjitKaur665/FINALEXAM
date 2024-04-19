package com.example.finalexam

class AddItemActivity {
}
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_add_item.*

class AddItemActivity : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        firestore = FirebaseFirestore.getInstance()

        add_item_button.setOnClickListener {
            val destinationName = destination_name_edit_text.text.toString()
            val description = description_edit_text.text.toString()
            val ranking = ranking_spinner.selectedItem.toString()

            if (destinationName.isEmpty() || description.isEmpty() || ranking.isEmpty()) {
                Toast.makeText(this, "All fields need to be populated.",
                    Toast.LENGTH_SHORT).show()
            } else {
                saveDestination(destinationName, description, ranking)
            }
        }
    }

    private fun saveDestination(destinationName: String, description: String, ranking: String) {
        val destination = hashMapOf(
            "name" to destinationName,
            "description" to description,
            "ranking" to ranking
        )

        firestore.collection("destinations")
            .add(destination)
            .addOnSuccessListener {
                Toast.makeText(this, "Save successful.",
                    Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Save failed.",
                    Toast.LENGTH_SHORT).show()
            }
    }
}