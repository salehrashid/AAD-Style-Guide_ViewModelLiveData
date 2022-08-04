package com.app.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.livedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var getScoreViewModel: GetScoreViewModel
    private lateinit var binding: ActivityMainBinding

//    private var scoreA = 0
//    private var scoreB = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getScoreViewModel = ViewModelProvider(this).get(GetScoreViewModel::class.java)

        binding.btnPlusScoreTeamA.setOnClickListener(this)
        binding.btnMinusScoreTeamA.setOnClickListener(this)
        binding.btnPlusScoreTeamB.setOnClickListener(this)
        binding.btnMinusScoreTeamB.setOnClickListener(this)
        binding.btnReset.setOnClickListener(this)

        initView()
    }

    private fun initView() {

        /** Kita akan mendapatkan data scorenya menggunakan LiveData
        agar data yang sudah dirubah masih bisa di observe oleh UI nya **/
        getScoreViewModel.getScoreA()?.observe(this, Observer {

            /** disini kita akan menampilkan LiveData agar setiap
            setiap perubahan yang ada di LiveData bisa di tampilkan **/
            if (it != null) {
                binding.tvScoreTeamA.text = it.toString()
            }
        })

        getScoreViewModel.getScoreB()?.observe(this, Observer {
            if (it != null) {
                binding.tvScoreTeamB.text = it.toString()
            }
        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnPlusScoreTeamA -> {
                getScoreViewModel.addScoreA()
            }
            R.id.btnMinusScoreTeamA -> {
                getScoreViewModel.minScoreA()
            }
            R.id.btnPlusScoreTeamB -> {
                getScoreViewModel.addScoreB()
            }
            R.id.btnMinusScoreTeamB -> {
                getScoreViewModel.minScoreB()
            }
            R.id.btnReset -> {
                getScoreViewModel.resetScore()
            }
        }
    }

//    private fun addScoreA() {
//        scoreA += 1
//        binding.tvScoreTeamA.text = scoreA.toString()
//    }
//
//    private fun addScoreB() {
//        scoreB += 1
//        binding.tvScoreTeamB.text = scoreB.toString()
//    }
//
//    private fun minScoreA() {
//        scoreA -= 1
//        binding.tvScoreTeamA.text = scoreA.toString()
//    }
//
//    private fun minScoreB() {
//        scoreB -= 1
//        binding.tvScoreTeamB.text = scoreB.toString()
//    }
//
//    private fun resetScore() {
//        scoreA = 0
//        scoreB = 0
//        binding.tvScoreTeamA.text = scoreA.toString()
//        binding.tvScoreTeamB.text = scoreB.toString()
//    }
}