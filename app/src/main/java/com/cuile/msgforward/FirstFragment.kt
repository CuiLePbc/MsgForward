package com.cuile.msgforward

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.github.dfqin.grantor.PermissionListener
import com.github.dfqin.grantor.PermissionsUtil

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestSMSPermission()

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment("From FirstFragment")
            findNavController().navigate(action)
        }
    }

    private fun requestSMSPermission() {
        PermissionsUtil.requestPermission(activity, object : PermissionListener{
            override fun permissionDenied(permission: Array<out String>) {
            }

            override fun permissionGranted(permission: Array<out String>) {
            }
        }, "android.permission.RECEIVE_SMS")

        PermissionsUtil.requestPermission(activity, object : PermissionListener{
            override fun permissionDenied(permission: Array<out String>) {
            }

            override fun permissionGranted(permission: Array<out String>) {
            }
        }, "android.permission.SEND_SMS")
    }
}
