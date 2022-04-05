package ru.soldatov.android.todolist.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.soldatov.android.todolist.R
import ru.soldatov.android.todolist.databinding.ItemTaskFragmentBinding
import ru.soldatov.android.todolist.domain.ToDoItem
import ru.soldatov.android.todolist.presentation.viewModel.ToDoItemViewModel
import ru.soldatov.android.todolist.presentation.viewModel.ViewModelFactory
import javax.inject.Inject

class ToDoDetailFragment : Fragment() {

    private lateinit var onFinished: OnEditingFinished

    private var screenMode = UNDEFINED_MODE
    private var taskItemId = ToDoItem.UNDEFINED_ID

    lateinit var viewModel: ToDoItemViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as ToDoApplication).component
    }

    private var _binding: ItemTaskFragmentBinding? = null
    private val binding: ItemTaskFragmentBinding
        get() = _binding ?: throw RuntimeException("ItemTaskFragmentBinding == null")

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
        if (context is OnEditingFinished)
            onFinished = context
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
        _binding = ItemTaskFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[ToDoItemViewModel::class.java]
        parseParams()
        launchRightFragment()
        onObserverViewModel()
    }

    private fun parseParams() {

        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE))
            throw java.lang.RuntimeException("Parameter MODE for fragment not passed")

        val mode = args.getString(SCREEN_MODE)
        if (mode != MODE_ADD && mode != MODE_EDIT)
            throw java.lang.RuntimeException("MODE $mode for fragment not find")

        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!args.containsKey(TASK_ID))
                throw java.lang.RuntimeException("Parameter ID not passed for fragment")
            taskItemId = args.getInt(TASK_ID)
        }

    }

    private fun launchRightFragment() {
        when (screenMode) {
            MODE_ADD -> launchAddMode()
            MODE_EDIT -> launchEditMode()
        }
    }

    private fun launchAddMode() {

        binding.flabSaveTaskDone.setOnClickListener {
            viewModel.addToDoItem(
                binding.titleTask.text?.toString(),
                binding.descriptionTask.text?.toString()
            )
        }
    }

    private fun launchEditMode() {

        viewModel.getToDoItem(taskItemId)
        viewModel.toDoItem.observe(viewLifecycleOwner) {
            binding.titleTask.setText(it.taskName)
            binding.descriptionTask.setText(it.taskDescription)
        }

        binding.flabSaveTaskDone.setOnClickListener {
            viewModel.editToDoItem(
                binding.titleTask.text?.toString(),
                binding.descriptionTask.text?.toString()
            )
        }
    }

    private fun onObserverViewModel() {
        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            onFinished.onEditingFinished()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    interface OnEditingFinished {
        fun onEditingFinished()
    }

    companion object {

        private const val SCREEN_MODE = "extra_mode"
        private const val TASK_ID = "extra_task_id"
        private const val MODE_ADD = "mode_task_add"
        private const val MODE_EDIT = "mode_task_edit"

        private const val UNDEFINED_MODE = ""

        fun newInstanceAdd(): ToDoDetailFragment {
            return ToDoDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }

        fun newInstanceEdit(taskItemId: Int): ToDoDetailFragment {
            return ToDoDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(TASK_ID, taskItemId)
                }
            }
        }
    }
}