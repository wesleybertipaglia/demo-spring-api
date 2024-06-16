package com.wesleybertipaglia.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demos")
public class DemoController {

    @Autowired
    DemoService demoService;

    @GetMapping("/")
    public List<Demo> listDemos() {
        return demoService.listDemos();
    }

    @GetMapping("/{id}")
    public Demo getDemo(@PathVariable("id") Long id) {
        return demoService.getDemo(id).orElse(null);
    }

    @PostMapping("/")
    public Demo createDemo(@RequestBody Demo demo) {
        return demoService.createDemo(demo);
    }

    @PutMapping("/{id}")
    public Demo updateDemo(@PathVariable("id") Long id, @RequestBody Demo demo) {
        return demoService.updateDemo(id, demo);
    }

    @DeleteMapping("/{id}")
    public void deleteDemo(@PathVariable("id") Long id) {
        demoService.deleteDemo(id);
    }
}
