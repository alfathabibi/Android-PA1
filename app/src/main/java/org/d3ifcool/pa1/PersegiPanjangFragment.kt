package org.d3ifcool.pa1


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.fragment_persegi_panjang.*
import kotlinx.android.synthetic.main.fragment_segitiga.*
import org.d3ifcool.pa1.databinding.FragmentMainBinding
import org.d3ifcool.pa1.databinding.FragmentPersegiPanjangBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class PersegiPanjangFragment : Fragment() {

    var luas = 0
    var keliling = 0
    private lateinit var binding:FragmentPersegiPanjangBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentPersegiPanjangBinding>(inflater,R.layout.fragment_persegi_panjang,container,false)

        binding.buttonHitungPP.setOnClickListener {
            if (isEmpty()){
                binding.textViewLuasHasilPP.text = "Silakan Isi Semua Form"
            }
            else{
                hitung(editTextPanjangPP.text.toString().toInt(),editTextLebarPP.text.toString().toInt())
            }
        }
        if (savedInstanceState != null){
            luas = savedInstanceState.getInt("key_luas")
            keliling = savedInstanceState.getInt("key_keliling")

            binding.textViewLuasHasilPP.text = luas.toString()
            binding.textViewKelilingHasilPP.text = keliling.toString()
        }

        binding.buttonSharePP.setOnClickListener {
            startActivity(shareHasil())
        }
        return binding.root
    }
    private fun isEmpty(): Boolean{
        if(editTextPanjangPP.text.isEmpty() || editTextLebarPP.text.isEmpty()){
            return true
        }
        return false
    }

    private fun hitung(panjang : Int, lebar: Int){
        luas = panjang * lebar
        keliling = (2*panjang) + (2*lebar)

        binding.textViewLuasHasilPP.text = luas.toString()
        binding.textViewKelilingHasilPP.text = keliling.toString()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("key_luas",luas)
        outState.putInt("key_keliling",keliling)
        super.onSaveInstanceState(outState)
    }
    private fun shareHasil (): Intent {
        return ShareCompat.IntentBuilder.from(activity!!).
                setText(getString(R.string.share_hasil,luas.toString(),keliling.toString())).
                setType("text/plain").intent
    }
}
