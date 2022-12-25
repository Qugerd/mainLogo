package com.example.logo.ui.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.logo.Constant.EMPTY
import com.example.logo.R
import com.example.logo.databinding.FragmentSearchBinding
import com.example.logo.ui.search.adapter.SearchAdapter
import com.example.logo.ui.search.adapter.SearchHistoryAdapter
import kotlin.properties.Delegates.notNull

// TODO: сохранение сотояния поиска, возрат с фрагмента не показывается история поиска, подключить апи для категории
class SearchFragment : Fragment(), SearchView.OnQueryTextListener {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val vm = SearchViewModel()

    private val recyclerView: RecyclerView by lazy { binding.rv }
    private val searchView: SearchView by lazy { binding.searchView }
    private val listView: ListView by lazy { binding.listView }
    private val containerEmpty: LinearLayout by lazy { binding.containerEmpty }
    private val containerSearch: LinearLayout by lazy { binding.containerSearch }
    private val containerZaglushka: LinearLayout by lazy { binding.containerZaglushka }
    private val itemCategory: LinearLayout by lazy { binding.itemCategory }

    private var deque: ArrayList<String> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchView.setOnQueryTextListener(this)
        searchView.showKeyboard()

        recyclerView.layoutManager = LinearLayoutManager(requireContext(),
        LinearLayoutManager.VERTICAL, false)

        listView.adapter = SearchHistoryAdapter(requireContext(), deque)
        listView.setOnItemClickListener { parent: AdapterView<*>, view:View, position:Int, id:Long ->

            Toast.makeText(requireContext(),"you click on ${position}",Toast.LENGTH_SHORT).show()
            insertInSearch(position)
        }





        itemCategory.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_categoryFragment)
        }


        binding.imBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    private fun insertInSearch(position: Int) {
        val value = deque[position]
        searchView.setQuery(value, true)
    }

    private fun SearchView.showKeyboard() {
        requestFocus()
        this.post {
            val inputMM = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMM.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
        }
    }


    override fun onQueryTextSubmit(query: String): Boolean {

        when (query){
            EMPTY -> {
                containerEmpty.visibility = View.VISIBLE
                containerSearch.visibility = View.GONE
                containerZaglushka.visibility = View.GONE
            }
            else -> {
                searchQuery(query)
                setAdapter()
                containerZaglushka.visibility = View.GONE
                containerEmpty.visibility = View.GONE
                containerSearch.visibility = View.VISIBLE

            }
        }

        addSearchQuery(query)
        listView.adapter = SearchHistoryAdapter(requireContext(), deque)

        return true
    }


    override fun onQueryTextChange(query: String): Boolean {

         when (query){
            EMPTY -> {
                containerEmpty.visibility = View.VISIBLE
                containerSearch.visibility = View.GONE
                containerZaglushka.visibility = View.GONE
            }
            else -> {
                searchQuery(query)
                setAdapter()
                containerZaglushka.visibility = View.GONE
                containerEmpty.visibility = View.GONE
                containerSearch.visibility = View.VISIBLE
            }
        }
        return true
    }

    private fun searchQuery(query: String){
        vm.getSearchQuery(query)
    }

    private fun addSearchQuery(query: String){

        if (query !in deque){
            if (deque.size >= 4){
                deque.removeLast()
                deque.reverse()
                deque.add(query)
                deque.reverse()
            }
            else deque.add(query)
        }
    }

    private fun setAdapter(){
        vm.searchList.observe(viewLifecycleOwner){
            if (it.isEmpty()){
                containerEmpty.visibility = View.GONE
                containerSearch.visibility = View.GONE
                containerZaglushka.visibility = View.VISIBLE
                // TODO это надо переделать
            }
            if (it.isNotEmpty()){
                recyclerView.adapter = SearchAdapter(it)
            }
        }
    }
}