package ru.soldatov.android.todolist.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.soldatov.android.todolist.R
import ru.soldatov.android.todolist.databinding.ItemListFragmentBinding
import ru.soldatov.android.todolist.presentation.adapters.ToDoItemAdapter
import ru.soldatov.android.todolist.presentation.viewModel.MainViewModel
import ru.soldatov.android.todolist.presentation.viewModel.ViewModelFactory
import javax.inject.Inject

class ToDoListFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ToDoItemAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as ToDoApplication).component
    }

    private var _binding: ItemListFragmentBinding? = null
    private val binding: ItemListFragmentBinding
        get() = _binding ?: throw RuntimeException("ItemListFragmentBinding == null")

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ItemListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        setupRecyclerView()
        viewModel.toDoList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        binding.taskLayout.text = getString(R.string.all_to_dos)
        binding.flabAddTask.setOnClickListener {
            val intent = ToDoItemDetailActivity.newIntentAdd(requireContext())
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        adapter = ToDoItemAdapter()
        binding.rvItemList.adapter = adapter
        setupClickItemListener()
    }

    private fun setupClickItemListener() {
        adapter.toDoItemClickListener = {
            val intent = ToDoItemDetailActivity.newIntentEdit(requireContext(), it.id)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        fun newInstance(): ToDoListFragment {
            return ToDoListFragment()
        }
    }

}