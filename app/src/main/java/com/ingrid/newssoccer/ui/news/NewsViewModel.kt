package com.ingrid.newssoccer.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ingrid.newssoccer.api.ServiceFactoryAPI
import com.ingrid.newssoccer.model.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

//    private val listNews =
//        mutableListOf<News>(
//            News("Brasil joga nessa quarta-feira", "Segundo comunicado do clube celeste, projeto da base envolverá intercâmbio de atletas"),
//            News("Clube estuda contratar atleta Ucraniano", "Segundo comunicado do clube celeste, projeto da base envolverá intercâmbio de atletas"),
//            News("Estádio passará por reformas no gramado", "Segundo comunicado do clube celeste, projeto da base envolverá intercâmbio de atletas"),
//            News("Atacante do Sport, Jorguinho, inicia tratamento", "Segundo comunicado do clube celeste, projeto da base envolverá intercâmbio de atletas"),
//            News("Time Pernambucano faz parceria com iFood", "Segundo comunicado do clube celeste, projeto da base envolverá intercâmbio de atletas")
//        )
//
//    private val news by lazy { MutableLiveData<List<News>>(listNews) }


    private val news = MutableLiveData<List<News>>()

    init {
        requestNews()
    }

    fun getNews(): LiveData<List<News>> = news

    private fun requestNews() {
        viewModelScope.launch(Dispatchers.IO) {
            val service = ServiceFactoryAPI.createNewsService()
            val listNewsCall = service.listNews()

            listNewsCall.enqueue(object : Callback<List<News>> {
                override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                    response.body()?.let { news ->
                        this@NewsViewModel.news.postValue(news)
                    }
                }

                override fun onFailure(call: Call<List<News>>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }
}