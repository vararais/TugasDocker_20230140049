package com.praktikum6.tugas6fix.controller;

import com.praktikum6.tugas6fix.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private final String USERNAME = "admin";
    private final String PASSWORD = "20230140049"; // NIM kamu

    // List temporary untuk menyimpan data tanpa database
    private List<User> dataMahasiswa = new ArrayList<>();

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        if(USERNAME.equals(username) && PASSWORD.equals(password)) {
            // Gunakan redirect agar url berubah ke /home
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/home")
    public String homepage(Model model){
        // Kirim list dataMahasiswa ke halaman home.html
        model.addAttribute("listMahasiswa", dataMahasiswa);
        return "home";
    }

    @GetMapping("/form")
    public String showForm() {
        return "form";
    }

    @PostMapping("/submit")
    public String submitData(@RequestParam String nama,
                             @RequestParam String nim,
                             @RequestParam String jenisKelamin) {
        // Tambahkan data baru ke dalam list
        dataMahasiswa.add(new User(nama, nim, jenisKelamin));

        // Setelah submit, kembalikan ke halaman home
        return "redirect:/home";
    }
}