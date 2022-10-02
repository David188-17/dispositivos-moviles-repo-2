package com.example.lugares.repository

import androidx.lifecycle.LiveData
import com.example.lugares.data.LugarDao
import com.example.lugares.model.Lugar

class LugarRepository(private val lugarDao: LugarDao) {
    suspend fun saveLugar(lugar: Lugar) {
        if (lugar.id == null) { //Es un lugar nuevo...
            lugarDao.addLugar(lugar)

        } else {// Es un lugar ya registrado
            lugarDao.updateLugar(lugar)
        }

    }
    suspend fun  deleteLugar(lugar:Lugar){
        if (lugar.id!=null){ //si el id tiene un valor ... lo intento eliminar
lugarDao.deleteLugar(lugar)

        }
    }
    val getLugares : LiveData<List<Lugar>> =lugarDao.getLugares()

}