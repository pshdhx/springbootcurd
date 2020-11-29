package com.pshdhx.controller;

import com.pshdhx.dao.DepartmentDao;
import com.pshdhx.dao.EmployeeDao;
import com.pshdhx.entities.Department;
import com.pshdhx.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @Authtor pshdhx
 * @Date 2020/11/2914:35
 * @Version 1.0
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao dao;
    @Autowired
    DepartmentDao dao2;

    @GetMapping("/emps")
    public String emps(Model model){
        model.addAttribute("emps",dao.getAll());

        //前缀：ThymeleafProperities 	public static final String DEFAULT_PREFIX = "classpath:/templates/";
        //后缀：private String mode = "HTML";

        return "emp/list";
    }

    /**
     * 跳转到添加页面
     * @param model
     * @return
     */
    @GetMapping("/emp")
    public String toAddPage(Model model){
        model.addAttribute("dept",dao2.getDepartments());
        return "emp/add";
    }
    /**
     * 添加方法
     */
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        //redirect 表示重定向到一个地址  /代表当前项目路径
        //forward：表示转发到一个地址
        dao.save(employee);
        return "redirect:/emps";  //这里的emps不是页面，而是方法
    }
    /**
     * 来到修改页面，查出当前员工，在页面回显
     */
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        Employee employee = dao.get(id);
        model.addAttribute("emp",employee);
        Collection<Department> departments = dao2.getDepartments();
        model.addAttribute("dept",departments);
        //回到修改页面 add2是添加和修改二合一的页面
        return "emp/add";
    }

    /**
     * 员工修改
     */
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        dao.save(employee);
        //System.out.println(employee);
        return "redirect:/emps";
    }

    /**
     * 员工删除
     */
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        dao.delete(id);
        return "redirect:/emps";
    }
}
