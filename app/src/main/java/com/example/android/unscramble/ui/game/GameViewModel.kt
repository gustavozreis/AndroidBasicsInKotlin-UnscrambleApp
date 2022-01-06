package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

    private var _score = 0
    private var _currentWordCount = 0
    private lateinit var _currentScrambledWord: String

    private var wordsList: MutableList<String> = mutableListOf() // lista de palavras já jogadas
    private lateinit var currentWord: String

    val currentScrambledWord: String
        get() = _currentScrambledWord

    init {
        Log.d("GameFragment", "GameViewModel created!")
        getNextWord()
    }

    private fun getNextWord() {
        // escolha palavra aleatória da lista de palavras
        currentWord = allWordsList.random()
        // transforma a palavra escolhida em char array e embaralha ela
        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()

        // checa se a palavra embaralhada não é igual à palavra original
        while(String(tempWord).equals(currentWord, false)) {
                tempWord.shuffle()
            }

        // checa se a palavra escolhida está na lista de palavras já jogadas
        // altera palavra mostrada e adiciona ao numero de palavras jogadas
        if (wordsList.contains(currentWord)) {
            getNextWord()
        } else {
            _currentScrambledWord = String(tempWord)
            ++_currentWordCount
            wordsList.add(currentWord)
        }
    }

    private fun nextWord(): Boolean {
        return if (currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }


}