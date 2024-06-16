package com.wesleybertipaglia.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemoService {

    @Autowired
    DemoRepository demoRepository;

    public List<Demo> listDemos() {
        return demoRepository.findAll();
    }

    public Optional<Demo> getDemo(Long id) {
        return demoRepository.findById(id);
    }

    public Demo createDemo(Demo demo) {
        return demoRepository.save(demo);
    }

    public Demo updateDemo(Long id, Demo demo) {
        Optional<Demo> existingDemo = demoRepository.findById(id);
        if (existingDemo.isPresent()) {
            Demo demoToUpdate = existingDemo.get();
            demoToUpdate.setDescription(demo.getDescription());
            return demoRepository.save(demoToUpdate);
        } else {
            return null;
        }
    }

    public void deleteDemo(Long id) {
        demoRepository.deleteById(id);
    }
}
