package com.wesleybertipaglia.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wesleybertipaglia.demo.dtos.DemoCreateDTO;
import com.wesleybertipaglia.demo.dtos.DemoReadDTO;
import com.wesleybertipaglia.demo.dtos.DemoUpdateDTO;
import com.wesleybertipaglia.demo.entities.Demo;
import com.wesleybertipaglia.demo.repositories.DemoRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DemoService {

    @Autowired
    DemoRepository demoRepository;

    public List<DemoReadDTO> listDemos() {
        return demoRepository.findAll().stream()
                .map(this::demoToDemoReadDTOMapper).collect(Collectors.toList());
    }

    public DemoReadDTO getDemo(Long id) {
        Demo demo = demoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Demo not found"));

        return demoToDemoReadDTOMapper(demo);
    }

    public DemoReadDTO createDemo(DemoCreateDTO demoCreateDTO) {
        Demo demo = new Demo(demoCreateDTO.title(), demoCreateDTO.description());
        return demoToDemoReadDTOMapper(demoRepository.save(demo));
    }

    public DemoReadDTO updateDemo(Long id, DemoUpdateDTO demoUpdateDTO) {
        Demo demo = demoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Demo not found"));

        if (demoUpdateDTO.title().isPresent()) {
            demo.setTitle(demoUpdateDTO.title().get());
        }

        if (demoUpdateDTO.description().isPresent()) {
            demo.setDescription(demoUpdateDTO.description().get());
        }

        return demoToDemoReadDTOMapper(demoRepository.save(demo));
    }

    public void deleteDemo(Long id) {
        Demo demo = demoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Demo not found"));

        demoRepository.delete(demo);
    }

    private DemoReadDTO demoToDemoReadDTOMapper(Demo demo) {
        return new DemoReadDTO(demo.getId(), demo.getTitle(), demo.getDescription());
    }
}
