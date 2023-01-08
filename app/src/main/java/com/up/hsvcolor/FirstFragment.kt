package com.up.hsvcolor

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.up.hsvcolor.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        initHSV()
    }

    private fun initHSV() {
        val seekChangeListener = object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                showColor()
                when (seekBar) {
                    binding.HSeek -> {
                        binding.hValue.text = progress.toString()
                    }
                    binding.SSeek -> binding.sValue.text = (progress / 100.0f).toString()
                    binding.VSeek -> binding.vValue.text = (progress / 100.0f).toString()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        }
        binding.HSeek.setOnSeekBarChangeListener(seekChangeListener)
        binding.SSeek.setOnSeekBarChangeListener(seekChangeListener)
        binding.VSeek.setOnSeekBarChangeListener(seekChangeListener)

    }

    private fun showColor() {
        val h = binding.HSeek.progress.toFloat()
        val s = binding.SSeek.progress / 100f
        val v = binding.VSeek.progress / 100f
        val hsvToColor = Color.HSVToColor(floatArrayOf(h, s, v))
        binding.colorShow.setBackgroundColor(hsvToColor)
        binding.colorCircle.setBackgroundColor(hsvToColor)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}