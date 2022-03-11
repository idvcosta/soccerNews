package com.ingrid.newssoccer.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ingrid.newssoccer.News

class NewsViewModel : ViewModel() {

    private val listNews =
        mutableListOf<News>(
            News("Brasil joga nessa quarta-feira", "Segundo comunicado do clube celeste, projeto da base envolverá intercâmbio de atletas"),
            News("Clube estuda contratar atleta Ucraniano", "Segundo comunicado do clube celeste, projeto da base envolverá intercâmbio de atletas"),
            News("Estádio passará por reformas no gramado", "Segundo comunicado do clube celeste, projeto da base envolverá intercâmbio de atletas"),
            News("Atacante do Sport, Jorguinho, inicia tratamento", "Segundo comunicado do clube celeste, projeto da base envolverá intercâmbio de atletas"),
            News("Time Pernambucano faz parceria com iFood", "Segundo comunicado do clube celeste, projeto da base envolverá intercâmbio de atletas")
        )

    private val news by lazy { MutableLiveData<List<News>>(listNews) }

    fun getNews(): LiveData<List<News>> = news
}