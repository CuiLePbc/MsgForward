package com.cuile.msgforward

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_second.*
import okhttp3.internal.notify

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private val args: SecondFragmentArgs by navArgs()
    private lateinit var secondViewModel: SecondViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = getString(R.string.hello_second_fragment, args.myArg)


        secondViewModel.msgHistorys.observe(this, Observer<MutableList<ForwardInfo>> {
            msgHistoryRecyclerView.notify()
        })

        msgHistoryRecyclerView.layoutManager = LinearLayoutManager(context)
        msgHistoryRecyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        msgHistoryRecyclerView.itemAnimator = DefaultItemAnimator()
        msgHistoryRecyclerView.adapter =
            context?.let { MsgHistoryRecyclerViewAdapter(it, secondViewModel.msgHistorys.value!!) }
    }
}
