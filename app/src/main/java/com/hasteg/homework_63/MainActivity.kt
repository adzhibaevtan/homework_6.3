package com.hasteg.homework_63

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.view.isVisible
import com.hasteg.homework_63.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AdviseAdapter
    private var list: ArrayList<String> = arrayListOf()
    private var replaceWords = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        saveText()
        initAdapter()
        hash()

    }

    private fun saveText() {
        binding.button.setOnClickListener {
            searchHash()
            binding.editText.text.clear()
        }
    }

    private fun searchHash() {
        val messages = binding.editText.text.split(" ")

        for (message in messages) {
            if (message.startsWith("#")) {
                val newWord = message.replace(Regex("[-+.^:;?!#,]"), "")
                list.add(newWord)
            }
        }
    }

    private fun initAdapter() {
        adapter = AdviseAdapter(list, this::clickListener)
        binding.recyclerView.adapter = adapter
    }

    @SuppressLint("SetTextI18n")
    private fun clickListener(hashTags: String) {
        binding.editText.setText(binding.editText.text.toString().replace(replaceWords, ""))
        binding.editText.setText("${binding.editText.text}#$hashTags ")
        binding.editText.setSelection(binding.editText.length())
    }

    private fun hash() {
        binding.editText.setOnClickListener {
            if (list.isNotEmpty()) {
                binding.recyclerView.isVisible = true
            }
        }

        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(text: CharSequence?, a1: Int, a2: Int, a3: Int) {
            }

            override fun onTextChanged(text: CharSequence?, a1: Int, a2: Int, a3: Int) {
                val messages = text?.split(" ")

                if (messages != null) {
                    for (word in messages) {
                        replaceWords = word
                        binding.recyclerView.isVisible = word.startsWith("#")
                    }
                }
            }

            override fun afterTextChanged(a0: Editable?) {

            }

        })
    }
}