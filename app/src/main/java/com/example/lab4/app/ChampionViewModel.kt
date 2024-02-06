package com.example.lab4.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.util.Log
import com.example.lab4.api.ChampionData
import com.example.lab4.api.ChampionService
import com.example.lab4.api.VersionService
import com.squareup.moshi.Json
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
class ChampionViewModel : ViewModel() {
    private val _champions = MutableLiveData<ChampionData>()
    val champions: LiveData<ChampionData> = _champions



    private val _versionUpdateRequired = MutableLiveData<Boolean>()
    val versionUpdateRequired: LiveData<Boolean> = _versionUpdateRequired

    private val _currentVersion = MutableLiveData<String>("12.6.1")
   val currentVersion: LiveData<String> = _currentVersion
    private val _latestVersion = MutableLiveData<String?>()
   val latestVersion: MutableLiveData<String?> = _latestVersion

    init {
        checkForNewVersion()
    }

    private fun checkForNewVersion() {
        viewModelScope.launch {
            _latestVersion.value = fetchLatestVersion()
            _versionUpdateRequired.value = _currentVersion.value != _latestVersion.value
        }
    }

    fun updateVersionIfNeeded(confirmUpdate: Boolean) {
        viewModelScope.launch {
            if (confirmUpdate) {
                val newVersion = fetchLatestVersion()
                _currentVersion.value = newVersion
            }
            fetchChampionData(_currentVersion.value ?: "12.6.1")
        }
    }

    private fun fetchChampionData(version: String) {
        viewModelScope.launch {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://ddragon.leagueoflegends.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            val service = retrofit.create(ChampionService::class.java)
            _champions.value = service.getChampions(version)
            try {
                val championData = service.getChampions(version)
                _champions.postValue(championData)
            } catch (e: Exception) {
                // Handle any errors here
                e.printStackTrace()
            }

        }
    }
}
    suspend fun fetchLatestVersion(): String {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://ddragon.leagueoflegends.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create(VersionService::class.java)
        val versions = service.getVersions()

        return versions.firstOrNull() ?: ""
    }


