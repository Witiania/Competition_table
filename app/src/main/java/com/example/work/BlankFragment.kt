package com.example.work

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.work.databinding.FragmentBlankBinding


class BlankFragment : Fragment() {

    private var _binding: FragmentBlankBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBlankBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val firstLine = listOf(
            binding.et12,
            binding.et13,
            binding.et14,
            binding.et15,
            binding.et16,
            binding.et17
        )
        val secondLine = listOf(
            binding.et21,
            binding.et22,
            binding.et23,
            binding.et24,
            binding.et25,
            binding.et26
        )
        val thirdLine = listOf(
            binding.et31,
            binding.et32,
            binding.et33,
            binding.et34,
            binding.et35,
            binding.et36
        )
        val fourthLine = listOf(
            binding.et41,
            binding.et42,
            binding.et43,
            binding.et44,
            binding.et45,
            binding.et46
        )
        val fifthLine = listOf(
            binding.et51,
            binding.et52,
            binding.et53,
            binding.et54,
            binding.et55,
            binding.et56
        )
        val sixthLine = listOf(
            binding.et61,
            binding.et62,
            binding.et63,
            binding.et64,
            binding.et65,
            binding.et66
        )
        val seventhLine = listOf(
            binding.et71,
            binding.et72,
            binding.et73,
            binding.et74,
            binding.et75,
            binding.et76
        )

        lineChecker(firstLine,binding.tvSum1)
        lineChecker(secondLine,binding.tvSum2)
        lineChecker(thirdLine,binding.tvSum3)
        lineChecker(fourthLine, binding.tvSum4)
        lineChecker(fifthLine,binding.tvSum5)
        lineChecker(sixthLine,binding.tvSum6)
        lineChecker(seventhLine, binding.tvSum7)
    }


    fun lineChecker(list:List<EditText>, tvSum:TextView) {
        list.forEachIndexed { index, it ->

            it.addTextChangedListener(object : TextWatcher {

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {

                    val et = it.text.toString().toIntOrNull()

                    if (et != null && et in 0..5) {
                        it.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                        if (index < 5) {
                            list[index + 1].requestFocus()
                        }
                        var count = 0
                        list.forEach {
                            if (it.text.toString() != "") {
                                count++
                            }
                        }
                        if (count == 6) {
                            var sumCurrentMember: Int = 0

                            for (element in list) {
                                val lastNum = element.text.toString().toIntOrNull()
                                if (lastNum != null) {
                                    sumCurrentMember += lastNum
                                }
                            }

                            tvSum.text = sumCurrentMember.toString()

                            if(binding.tvSum1.text != "" && binding.tvSum2.text != ""
                                && binding.tvSum3.text != "" && binding.tvSum4.text != ""
                                && binding.tvSum5.text != "" && binding.tvSum6.text != ""
                                && binding.tvSum7.text != "") {

                                val totalScoreMember1 = binding.tvSum1.text.toString().toInt()
                                val totalScoreMember2 = binding.tvSum2.text.toString().toInt()
                                val totalScoreMember3 = binding.tvSum3.text.toString().toInt()
                                val totalScoreMember4 = binding.tvSum4.text.toString().toInt()
                                val totalScoreMember5 = binding.tvSum5.text.toString().toInt()
                                val totalScoreMember6 = binding.tvSum6.text.toString().toInt()
                                val totalScoreMember7 = binding.tvSum7.text.toString().toInt()

                                val matrix = listOf(totalScoreMember1,totalScoreMember2,totalScoreMember3,totalScoreMember4,totalScoreMember5,totalScoreMember6,totalScoreMember7)
                                val listSum = matrix.sortedDescending()

                                listSum.forEach { totalScoreCurrentMember ->
                                    matrix.forEach{totalScoreToCompare->
                                      if(totalScoreCurrentMember == totalScoreToCompare){
                                          val place = listSum.indexOf(totalScoreToCompare) + 1
                                          when(matrix.indexOf(totalScoreToCompare)){
                                              0->binding.tvResult1.text = place.toString()
                                              1->binding.tvResult2.text = place.toString()
                                              2->binding.tvResult3.text = place.toString()
                                              3->binding.tvResult4.text = place.toString()
                                              4->binding.tvResult5.text = place.toString()
                                              5->binding.tvResult6.text = place.toString()
                                              6->binding.tvResult7.text = place.toString()
                                          }
                                       }
                                    }
                                }
                            }
                        }
                    } else if (et == null) {
                        tvSum.text = ""
                    } else {
                        Toast.makeText(activity, "Достуаный диапазон от 1 до 5", Toast.LENGTH_SHORT).show()
                        it.error = "Ошибка!"
                        it.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
                        tvSum.text = ""
                    }
                }
            })
        }
    }

}







