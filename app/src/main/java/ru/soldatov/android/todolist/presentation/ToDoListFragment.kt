package ru.soldatov.android.todolist.presentation

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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
        setupObserver()
        binding.taskLayout.text = getString(R.string.all_to_dos)
        binding.flabAddTask.setOnClickListener {
            val intent = ToDoItemDetailActivity.newIntentAdd(requireContext())
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_filter -> showPopupMenu()
            R.id.action_settings -> {
                Toast.makeText(requireContext(), "Clear all task", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupRecyclerView() {
        adapter = ToDoItemAdapter()
        binding.rvItemList.adapter = adapter
        setupClickItemListener()
        setupClickCheckBox()
        setupSwipeListener(binding.rvItemList)
    }

    private fun setupClickItemListener() {
        adapter.toDoItemClickListener = {
            val intent = ToDoItemDetailActivity.newIntentEdit(requireContext(), it.id)
            startActivity(intent)
        }
    }

    private fun setupClickCheckBox() {
        adapter.toDoItemClickListenerDone = {
            viewModel.changeIsDone(it)
        }
    }

    private fun setupSwipeListener(rv: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteToDoItem(item)
                Toast.makeText(
                    requireContext(),
                    "Delete Task ${item.taskName}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rv)
    }

    private fun showPopupMenu() {
        val view = activity?.findViewById<View>(R.id.action_filter) ?:
            throw java.lang.RuntimeException("view for popup menu not find")
        val popupMenu = PopupMenu(requireContext(), view)
        popupMenu.inflate(R.menu.popupmenu)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.filter_all_task -> {
                    viewModel.setFilterAll(R.string.all_to_dos)
                    Toast.makeText(requireContext(), "Filter All Task", Toast.LENGTH_SHORT)
                        .show()
                }
                R.id.filter_complete_task -> {
                    viewModel.setFilterComplete(R.string.complete_to_dos)
                    Toast.makeText(requireContext(), "Filter Complete Task", Toast.LENGTH_SHORT)
                        .show()
                }
                R.id.filter_action_task -> {
                    viewModel.setFilterActive(R.string.action_to_dos)
                    Toast.makeText(requireContext(), "Filter Action Task", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            false
        }
        popupMenu.show()
    }

    private fun setupObserver() {
        viewModel.labelLayoutText.observe(viewLifecycleOwner) {
            binding.taskLayout.text = getString(it)
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