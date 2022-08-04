package com.app.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GetScoreViewModel : ViewModel() {

    /** kita akan menyimpan data menggunakan LiveData
    MutableLiveData = read & write data
    LiveData = hanya read data **/
    private val scoreA: MutableLiveData<Int>? = MutableLiveData()
    private val scoreB: MutableLiveData<Int>? = MutableLiveData()

    fun addScoreA() {
        val result = getScoreA()?.value?.plus(1)
        scoreA?.value = result
    }

    fun addScoreB() {
        val result = getScoreB()?.value?.plus(1)
        scoreB?.value = result
    }

    fun minScoreA() {
        val result = getScoreA()?.value?.minus(1)
        if (result!! < 0) {
            scoreA?.value = 0
        } else {
            scoreA?.value = result
        }
    }

    fun minScoreB() {
        val result = getScoreB()?.value?.minus(1)
        if (result!! < 0) {
            scoreB?.value = 0
        } else {
            scoreB?.value = result
        }
    }

    fun resetScore() {
        scoreA?.value = 0
        scoreB?.value = 0
    }

    fun getScoreA(): LiveData<Int>? {
        //value getData()
        if (scoreA?.value == null) {

            //ketika berada di UI atau main thread
            // value setData()
            scoreA?.value = 0

            /** ketika berada di backdround thread(API/retrofit)
            scoreA?.postValue(0) **/
        }
        return scoreA
    }

    fun getScoreB(): LiveData<Int>? {
        if (scoreB?.value == null) {
            scoreB?.value = 0
        }
        return scoreB
    }
}