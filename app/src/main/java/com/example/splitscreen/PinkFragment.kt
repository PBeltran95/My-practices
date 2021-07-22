package com.example.splitscreen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.splitscreen.databinding.FragmentBlueBinding
import com.example.splitscreen.databinding.FragmentPinkBinding

class PinkFragment : Fragment() {
    private lateinit var mBinding: FragmentPinkBinding
    private var listener: OnFragmentsActionListener? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentPinkBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentsActionListener){listener = context}
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.btnPlusPink.setOnClickListener { listener?.onClickFragmentButton() }
    }
}