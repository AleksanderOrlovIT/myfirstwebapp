package com.in28minutes.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@SessionAttributes("name")
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping("/list-todos")
    public String listAllTodos(ModelMap modelMap){
        modelMap.put("todos", todoService.findByUsername("in28minutes"));
        return "listTodos";
    }

    @GetMapping("/add-todo")
    public String showNewToPage(ModelMap modelMap) {
        Todo todo = new Todo(0, (String) modelMap.get("name"),"", LocalDate.now().plusYears(1), false);
        modelMap.put("todo", todo);
        return "todo";
    }

    @PostMapping("/add-todo")
    public String addNewTodo(ModelMap modelMap, @Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "todo";
        }
        todoService.addTodo((String) modelMap.get("name"), todo.getDescription(), todo.getTargetDate(), false);
        return "redirect:/list-todos";
    }

    @RequestMapping("/delete-todo")
    public String deleteTodo(@RequestParam int id){
        todoService.deleteById(id);
        return "redirect:list-todos";
    }

    @GetMapping("/update-todo")
    public String showUpdateTodoPage(@RequestParam int id, ModelMap modelMap){
        Todo todo = todoService.findById(id);
        modelMap.addAttribute("todo", todo);
        return "todo";
    }

    @PostMapping("/update-todo")
    public String updateTodo(ModelMap modelMap, @Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "todo";
        }
        String userName = (String) modelMap.get("name");
        todo.setUserName(userName);
        todoService.updateTodo(todo);
        return "redirect:/list-todos";
    }
}
